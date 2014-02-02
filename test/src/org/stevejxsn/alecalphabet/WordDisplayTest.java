package org.stevejxsn.alecalphabet;

import org.stevejxsn.alecalphabet.WordDisplay;

import android.test.AndroidTestCase;
import android.widget.TextView;


public class WordDisplayTest extends AndroidTestCase {

	private TextView view;
	private WordDisplay word;

	protected void setUp() {
	    view = new TextView(getContext());
		word = new WordDisplay(view);
	}
	
	public void testAppendingCharToAView() {
		word.append("A");
		assertEquals("A", view.getText().toString());
	}
	
	public void testAppendingTextToAView() {
		word.append("A");
		word.append("L");
		assertEquals("AL", view.getText().toString());
	}
	
}
