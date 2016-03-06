/*
    ADD YOUR HEADER HERE
 */

package assignment4;

import java.util.List;
import java.util.*;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface {
	// delcare class members here.
	private String beginningWord;
	private String endingWord;
	private Dictionary wordList;
	private List<String> solutionList;

	// add a constructor for this object. HINT: it would be a good idea to set
	// up the dictionary there
	public WordLadderSolver(String startWord, String endWord, Dictionary words, List<String> solutions) {
		beginningWord = startWord;
		endingWord = endWord;
		wordList = words;
		solutionList = solutions;
	}

	// do not change signature of the method implemented from the interface
	@Override
	public List<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
		//solves word ladder returns list of word ladder, throws NoSuchLadderException if there is no possible word ladder
		NoSuchLadderException invalid = new NoSuchLadderException("At least one of the words " + startWord + " and " + endWord + " are not legitimate 5-letter words from the dictionary.");
		try{
			wordList.isValid(startWord);
			wordList.isValid(endWord);
		}
		catch(InvalidWordException e){
			throw invalid;
		}
		NoSuchLadderException error = new NoSuchLadderException("Error: There is no word ladder between " + startWord + " and " + endWord + ".");
		if(makeLadder(startWord, endWord, -1) == false)
		{
			throw error;
		}
		else
		{
			return solutionList;
		}
	}
	//check if the word ladder is correct
	@Override
	public boolean validateResult(String startWord, String endWord, List<String> wordLadder) {
		if(wordLadder.get(0).equals(startWord) && wordLadder.get(wordLadder.size()-1).equals(endWord)){
			for(int i = 0; i < wordLadder.size()-1; i++){
				if(wordList.changedDifference(wordLadder.get(i), wordLadder.get(i+1))!=1){
					return false;
				}
			}
			return true;
		}
		else{
			return false;
		}
	}

	//recursively solves the word ladder from startWord to endWord. Index saves the last changed index.
	//Returns false if no word ladder for that candidate, returns true when a word ladder is found
	public boolean makeLadder(String startWord, String endWord, int Index) {
		ArrayList<String> candidate = new ArrayList<String>();
		if(startWord.equals(endWord)){
			solutionList.add(startWord);
			solutionList.add(endWord);
			return true;
		}
		solutionList.add(startWord);
		boolean complete = false;
		for (int q = 0; q < solutionList.size(); q++) {
			for (int j = 0; j < wordList.wordList.size(); j++) {
				if (complete == false) {
					if (solutionList.get(q).compareTo(wordList.wordList.get(j)) != 0) {
						if (wordList.stringDifference(startWord, wordList.wordList.get(j), Index) == true) {
							String temp = wordList.wordList.get(j);
							int difference = 0;
							difference = wordList.changedDifference(temp, endWord);
							String candidateTemp = difference + temp;
							if (wordList.changedDifference(startWord, endWord) == 1) {
								complete = true;
								solutionList.add(endWord);
								continue;
							} 
							else if (difference == 1) {
								complete = true;
								solutionList.add(temp);
								solutionList.add(endWord);
								continue;
							} 
							else if (!candidate.contains(candidateTemp) && !solutionList.contains(temp)) {
								candidate.add(candidateTemp);
								Collections.sort(candidate);
							}
						}
					}
				}
			}
		}
		if (complete == false) {
			for (int k = 0; k < candidate.size(); k++) {
				String temp2 = candidate.get(k).substring(1);
				for (int l = 0; l < temp2.length(); l++) {
					if (startWord.charAt(l) != temp2.charAt(l)) {
						Index = l;
					}
				}
				if (makeLadder(temp2, endWord, Index) == false) {
					solutionList.remove(solutionList.size() - 1);
				} 
				else {
					return true;
				}
			}
			return false;
		}
		return true;
	}
	//sorts the candidate list
	class candidateCompare implements Comparator<String> {
		public int compare(String word1, String word2) {
			return word1.compareTo(word2);
		}
	}
}
