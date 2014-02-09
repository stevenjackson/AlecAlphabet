package org.stevejxsn.alecalphabet;

import java.util.Arrays;

import android.graphics.Bitmap;
import android.test.AndroidTestCase;

public class WordChangeHandlerTest extends AndroidTestCase {
	private WordChangeHandler handler;
	private WordImageSpy imageSpy = new WordImageSpy();
	private KeyboardSpy keyboardSpy = new KeyboardSpy();

	@Override
	protected void setUp() {
		handler = new WordChangeHandler(keyboardSpy, imageSpy);
		handler.addWords(Word.createWord("Apple", 1));
	}
	
	public void testSetsActiveKeysOnKeyboardBasedOnKnownWords() {
		handler.currentWordChanged("Ap");
		assertTrue(Arrays.equals(keyboardSpy.activeKeysSet(), new char[] {'p'}));
	}

	public void testUpdatesImageBasedOnKnownWords() {
		handler.currentWordChanged("Apple");
		assertTrue(imageSpy.wasUpdated());
	}

	
	private class WordImageSpy extends WordImage {

		@Override
		public void setImageBitmap(Bitmap bitmap) {
			wasUpdated = true;
		}

		@Override
		public void setImageResource(int resourceId) {
			wasUpdated = true;
		}

		private boolean wasUpdated = false;

		public WordImageSpy() {
			super(null);
		}

		public boolean wasUpdated() {
			return wasUpdated;
		}
		
	}
	
	private class KeyboardSpy extends KeyboardStub {
		private char[] lastChars;

		@Override
		public void setActiveKeys(char... chars) {
			lastChars = chars;
		}
		
		public char[] activeKeysSet() {
			return lastChars;
		}
		
	}
}
