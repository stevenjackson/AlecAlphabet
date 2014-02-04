package org.stevejxsn.alecalphabet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class AlecDictionary {
	private List<String> words;
	
	public AlecDictionary() {
		words = Collections.emptyList();
	}

	public AlecDictionary(List<String> words) {
		setWords(words);
	}

	public void setWords(List<String> words) {
		this.words = new ArrayList<String>(words);
	}

	public char[] nextPossibleLetters(String text) {
		List<String> possibleWords = possibleWords(text);
		Collection<Character> nextChars = new HashSet<Character>();
		for(String word : possibleWords) {
			nextChars.add(nextChar(word, text));
		}
		nextChars.remove(null);
		return toCharArray(nextChars);
	}
	
	public boolean isWord(String text) {
		return words.contains(text);
	}

	private List<String> possibleWords(String text) {
		List<String> filtered = longerWords(text);
		List<String> possibleWords = new ArrayList<String>();
		for(String word : filtered) 
			if(word.startsWith(text)) 
				possibleWords.add(word);
		return possibleWords;
	}

	private List<String> longerWords(String text) {
		int wordLength = text.length();
		List<String> longerWords = new ArrayList<String>();
		for(String word : words)
			if(word.length() > wordLength)
				longerWords.add(word);
		
		return longerWords;
	}

	private Character nextChar(String word, String prefix) {
		String remainder = word.replaceFirst(prefix, "");
		return remainder.charAt(0);
	}

	private char[] toCharArray(Collection<Character> nextChars) {
		StringBuilder builder = new StringBuilder();
		for(Character c : nextChars) {
			builder.append(c);
		}
		
		return builder.toString().toCharArray();
	}
}
