package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class NoPossibleWordLadder {

	@Test
	public void test() {
		Dictionary wordList = new Dictionary("A4words.dat");
		String startWord = "zebra";
		String endWord = "atlas";
		List<String> solutions = new ArrayList<String>();
		WordLadderSolver test = new WordLadderSolver(startWord, endWord, wordList, solutions);
		try {
			test.computeLadder(startWord, endWord);
		} catch (NoSuchLadderException e) {
			e.printStackTrace();
		}
		assertTrue(test.validateResult(startWord, endWord, solutions));
	}

}
