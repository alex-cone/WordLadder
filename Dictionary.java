package assignment4;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
	
	protected ArrayList<String> wordList;
	/*
	 * @param String of file name
	 * Creates a dictionary object by parsing 5 letter words from the file, ignoring lines that start with "*".
	 */
	public Dictionary(String words)
	{
		ArrayList<String> wordSet = new ArrayList<String>();
		try 
    	{
    		FileReader freader = new FileReader(words);
    		BufferedReader reader = new BufferedReader(freader);
    		for(String s = reader.readLine(); s!=null; s = reader.readLine())
    		{
    			if(s.charAt(0)!='*')
    			{
    				wordSet.add(s.substring(0,5));
    			}
    		}
    		wordList = wordSet;
    	} 
    	catch (FileNotFoundException e) 
    	{
    		System.err.println ("Error: File not found. Exiting...");
    		e.printStackTrace();
    		System.exit(-1);
    	} 
    	catch (IOException e) 
    	{
    		System.err.println ("Error: IO exception. Exiting...");
    		e.printStackTrace();
    		System.exit(-1);
    	}
	}
	/*
	 * @param String of 5 letter word
	 * Checks if the word is in the dictionary
	 * @returns True if the word is in the dictionary, false if the word is not in the dictionary
	 * Throws InvalidWordException if the word does not exist in the dictionary
	 */
	public boolean isValid(String check) throws InvalidWordException
	{
		InvalidWordException wrong = new InvalidWordException("The word " + check + " is not a valid word in the dictionary.");
		for(int i = 0; i < wordList.size(); i ++)
		{
			if(check.compareTo(wordList.get(i))==0)
			{
				return true;
			}
		}
		throw wrong;
	}
	/*
	 * @param 2 5 letter word strings, index
	 * @returns True if the word only is different from the other word by the char at index, 
	 * false if the word is different from the other word by more than one
	 */
	public boolean stringDifference(String word1, String word2, int index)
	{
		if(index!=-1 && (word1.charAt(index) != word2.charAt(index)))
		{
			return false;
		}
		else
		{
			if(index == -1)
			{
				int count = 0;
				for(int i = 0; i <=4; i++)
				{
					if(word1.charAt(i)!= word2.charAt(i))
					{
						count++;
					}
					if(count > 1)
					{
						return false;
					}
				}
				return true;
			}
			else
			{
				int changed = 0;
				for(int i = 0; i <=4; i++)
				{
					if(i != index && word1.charAt(i) != word2.charAt(i))
					{
						changed++;
						if(changed >1){
							return false;
						}
					}
				}
			return true;
			}
		}
	}
	/*
	 * @param 2 5 letter words
	 * Counts the difference between two words
	 * @returns the number difference between the two words
	 */
	public int changedDifference(String word1, String word2)
	{
		int count = 0;
		for(int i = 0; i< word1.length(); i++)
		{
			if(word1.charAt(i)!= word2.charAt(i))
			{
				count++;
			}
		}
		return count;
	}
}