package org.stevejxsn.alecalphabet;

import java.util.Arrays;

import android.test.AndroidTestCase;

public class AlecDictionaryTest extends AndroidTestCase {

	public void testNextPossibleLettersForZeroKnownWords() {
		AlecDictionary dictionary = new AlecDictionary();
		assertEquals(0, dictionary.nextPossibleLetters("").length);
	}
	
	public void testNextPossibleLettersForOneKnownWords() {
		AlecDictionary dictionary = new AlecDictionary(Arrays.asList("Apple"));
		assertEquals('p', dictionary.nextPossibleLetters("A")[0]);
	}
	
	public void testIsWord() {
		AlecDictionary dictionary = new AlecDictionary(Arrays.asList("Apple"));
		assertFalse(dictionary.isWord("Appl"));
		assertTrue(dictionary.isWord("Apple"));
	}
}
