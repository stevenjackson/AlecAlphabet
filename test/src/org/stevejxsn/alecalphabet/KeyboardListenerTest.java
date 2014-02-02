package org.stevejxsn.alecalphabet;

import android.test.AndroidTestCase;
import android.widget.TextView;

public class KeyboardListenerTest extends AndroidTestCase {
	
	private TextView view;
	private KeyboardListener listener;

	protected void setUp() {
	    view = new TextView(getContext());
		listener = new KeyboardListener(new WordDisplay(view));
	}
	
	public void testAddsKeyStrokeToView() {
		listener.onKey((int)'C', null);
		assertEquals("C", view.getText());
	}

}
