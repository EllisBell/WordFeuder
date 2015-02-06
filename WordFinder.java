/*
* This is a tiny little scrabble/wordfeud/words with friends aid to help find words from a set of letters.
* It takes a string and finds all the different permutations of that string.
* Then it checks to see which of those permutations are in a dictionary read in from a file
* and prints them out.
* Only finds words that use all the letters in the string - should try to make something
* that finds smaller words that can be made from the letters as well.
*/


import java.util.*;
import java.io.*;

public class WordFinder {

	private static List<String> getWords(String letters) {

		List<String> allWords = new ArrayList<String>();
		
		if(letters.length() == 0) {
			allWords.add(0, letters);
			return allWords;
		}

		for(int i=0; i<letters.length(); i++) {
			String lessLetters = letters.substring(0, i) + letters.substring(i+1);

			List<String> shorterPermutations = getWords(lessLetters);

			for(String perm : shorterPermutations) {
				allWords.add(letters.charAt(i) + perm);
			} 
		}
		return allWords;
	}


	public static void main(String [] args) {
		Set<String> dictionary = new HashSet<String>();
		String word = args[0];

		try {
			FileReader reader = null;

			try {
				reader = new FileReader("wordlist.txt");
				Scanner scan = new Scanner(reader);
				while(scan.hasNextLine()) {
					String dictWord = scan.nextLine();
					dictionary.add(dictWord);
				}
			}
			finally {
				if(reader != null)
					reader.close();
			}
		}
		catch(IOException e) {
				System.out.println("IO problem");
		}

		List<String> wordList = getWords(word);
		for(String w : wordList) {
			if(dictionary.contains(w))
				System.out.println(w);
		}
	} 

}