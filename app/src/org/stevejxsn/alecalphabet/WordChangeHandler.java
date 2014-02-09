package org.stevejxsn.alecalphabet;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class WordChangeHandler {
	private AlecKeyboard keyboard;
	private UrlImage urlHandler;
	private ResourceImage resourceHandler;
	private AlecDictionary dictionary;
	private Map<String, Word> words;

	public WordChangeHandler(AlecKeyboard keyboard, WordImage image) {
		this.keyboard = keyboard;
		this.urlHandler = new UrlImage(image);
		this.resourceHandler = new ResourceImage(image);
		this.words = new HashMap<String, Word>();
	}

	public void addWords(Word... toAdd) {
		addWords(Arrays.asList(toAdd));
	}
	
	public void addWords(Collection<Word> toAdd) {
		for(Word word : toAdd) {
			addWord(word);
		}
		this.dictionary = new AlecDictionary(words.keySet());
	}

	public void currentWordChanged(String currentWord) {
		if(dictionary.isWord(currentWord)) {
			words.get(currentWord).updateImage();
		}
		keyboard.setActiveKeys(dictionary.nextPossibleLetters(currentWord));
	}

	private void addWord(Word word) {
		word.setImageUpdater(getImageUpdater(word));
		words.put(word.word, word);
	}

	private WordImageUpdater getImageUpdater(Word word) {
		if(word.hasUrl()) {
			return urlHandler;
		} else {
			return resourceHandler;
		}
	}
}
