package assignment4;

import static org.junit.Assert.*;
import java.util.*;
import assignment4.Dictionary;
import assignment4.WordLadderSolver;
import org.junit.Test;
import org.junit.runners.Parameterized;
public class WordLadderTest {
	@Test
	public void test() {
		Dictionary wordList = new Dictionary("A4words.dat");
		String startWord = "hello";
		String endWord = "buddy";
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
