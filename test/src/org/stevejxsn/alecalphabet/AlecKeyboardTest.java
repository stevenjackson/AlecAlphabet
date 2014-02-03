package org.stevejxsn.alecalphabet;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.test.AndroidTestCase;

public class AlecKeyboardTest extends AndroidTestCase {
	
	private AlecKeyboard keyboard;
	private ListenerSpy listener;

	protected void setUp() {
		keyboard = new AlecKeyboard(new KeyboardView(getContext(), null), new Keyboard(getContext(), R.xml.aleckbd));
		listener = new ListenerSpy();
	}

	public void testTellsListenerOnKeyEvent() {
		keyboard.keyPressed(listener, 'C');
		assertTrue(listener.wasCalled());
	}
	
	public void testDoesNotTellsListenerAboutDisabledKeys() {
		keyboard.disableKeys('C');
		keyboard.keyPressed(listener, 'C');
		assertFalse(listener.wasCalled());
	}
	
	public void testSetActiveKeys() {
		keyboard.setActiveKeys('A', 'B');
		keyboard.keyPressed(listener, 'C');
		assertFalse(listener.wasCalled());
		keyboard.keyPressed(listener, 'A');
		assertTrue(listener.wasCalled());
	}
	
	
	
	class ListenerSpy implements AlecKeyboardListener {

		private boolean called;

		@Override
		public void onLetterKey(char key) {
			this.called = true;
		}
		public boolean wasCalled() {
			return called;
		}
	}
}
