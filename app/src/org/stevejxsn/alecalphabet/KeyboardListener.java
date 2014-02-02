package org.stevejxsn.alecalphabet;

import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;

public class KeyboardListener implements OnKeyboardActionListener {

	private WordDisplay word;
	public KeyboardListener(WordDisplay word) {
		this.word = word;
	}

	@Override
	public void onKey(int primaryCode, int[] keyCodes) {
		word.append(Character.toString((char)primaryCode));
	}

	//NO-OPs
	@Override
	public void onPress(int primaryCode) {}
	@Override
	public void onRelease(int primaryCode) {}
	@Override
	public void onText(CharSequence text) {}
	@Override
	public void swipeDown() {}
	@Override
	public void swipeLeft() {}
	@Override
	public void swipeRight() {}
	@Override
	public void swipeUp() {}

}
