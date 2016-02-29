package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.*;


public class Assign4Driver
{
    public static void main(String[] args)
    {
    	if (args.length != 2) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
    	Dictionary newWords = new Dictionary(args[0]);
    	try 
    	{
    		FileReader freader = new FileReader(args[1]);
    		BufferedReader reader = new BufferedReader(freader);
    		for(String s = reader.readLine(); s!=null; s = reader.readLine())
    		{
    			parseInput(s, newWords);
    		}
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
    public static void parseInput(String input, Dictionary wordList)
    {
    	String startingWord = "";
    	String endingWord = "";
    	startingWord = input.substring(0,5);
    	input = input.substring(5);
    	int i = 0;
    	if(input.indexOf(' ')!=-1)
    	{    		
    		while(input.charAt(i)== ' ')
    		{
    			i++;
    		}
    		endingWord = input.substring(i);
    	}
    	else
    	{
    		System.out.println("Error: Invalid Input");
    	}
       	ArrayList<String> solutions = new ArrayList<String>();
    	Assignment4Interface wordLadderSolver = new WordLadderSolver(startingWord, endingWord, wordList, solutions);
    	endingWord = input.substring(i);
    	wordLadderSolver.makeLadder(startingWord, endingWord, -1);
    	System.out.println("DONE");
    	/*
        try 
        {
            List<String> result = wordLadderSolver.computeLadder("money", "honey");
            boolean correct = wordLadderSolver.validateResult("money", "honey", result);
        } 
        catch (NoSuchLadderException e) 
        {
            e.printStackTrace();
        }*/
    }
}
