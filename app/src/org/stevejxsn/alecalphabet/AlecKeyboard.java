package org.stevejxsn.alecalphabet;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.Keyboard.Key;
import android.inputmethodservice.KeyboardView;
import android.inputmethodservice.KeyboardView.OnKeyboardActionListener;
import android.util.SparseArray;

public class AlecKeyboard {

	private KeyboardView view;
	private SparseArray<AlecKey> keys;
	
	//For testing
	protected AlecKeyboard() {}

	public AlecKeyboard(KeyboardView view, Keyboard keyboard) {
		this.view = view;
		storeKeys(keyboard);
		view.setKeyboard(keyboard);
		view.setPreviewEnabled(false);
	}
	
	private void storeKeys(Keyboard keyboard) {
		keys = new SparseArray<AlecKey>();
		for(Key key : keyboard.getKeys()) {
			keys.put(key.codes[0], new AlecKey(key));
		}
	}

	public void setListener(final AlecKeyboardListener listener) {
		view.setOnKeyboardActionListener(new OnKeyboardActionListener() {
			@Override
			public void onKey(int primaryCode, int[] keyCodes) {
				keyPressed(listener, primaryCode);
			}
			@Override
			public void swipeUp() {}
			@Override
			public void swipeRight() {}
			@Override
			public void swipeLeft() {}
			@Override
			public void swipeDown() {}
			@Override
			public void onText(CharSequence text) {}
			@Override
			public void onRelease(int primaryCode) {}
			@Override
			public void onPress(int primaryCode) {}
		});
	}

	protected void keyPressed(AlecKeyboardListener listener, int primaryCode) {
		if(keys.get(primaryCode).isDisabled()) return;
		
		listener.onLetterKey((char)primaryCode);
	}

	public void disableKeys(char... chars) {
		for(int code : chars) {
			keys.get(code).disable();
		}
		view.invalidateAllKeys();
	}
	
	public void disableAllKeys() {
		for(int i = 0; i < keys.size(); i++) {
			keys.valueAt(i).disable();
		}
	}
	
	public void enableAllKeys() {
		for(int i = 0; i < keys.size(); i++) {
			keys.valueAt(i).enable();
		}
	}

	public void setActiveKeys(char... chars) {
		disableAllKeys();
		for(int code : chars) {
			keys.get(code).enable();
		}
		view.invalidateAllKeys();
	}
	
	public void reset() {
		enableAllKeys();
		view.invalidateAllKeys();
	}
	
	private class AlecKey {
		private Key key;
		private CharSequence text;
		private boolean enabled = true;

		public AlecKey(Key key) {
			this.key = key;
			this.text = key.label;
		}

		public void enable() {
			this.enabled = true;
			key.label = this.text;
		}

		public void disable() {
			this.enabled = false;
			key.label = "";
		}
		
		public boolean isDisabled() {
			return !enabled;
		}
	}
}
