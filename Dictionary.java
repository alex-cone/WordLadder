package assignment4;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary {
	
	ArrayList<String> wordList;
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