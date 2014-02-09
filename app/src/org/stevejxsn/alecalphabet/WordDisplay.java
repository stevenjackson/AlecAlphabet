package org.stevejxsn.alecalphabet;

import android.widget.TextView;

public class WordDisplay {

	private TextView view;

	public WordDisplay(TextView view) {
		this.view = view;
	}

	public void append(String s) {
		view.setText(view.getText() + s);
	}

	public CharSequence getText() {
		return view.getText();
	}

	public void clear() {
		view.setText("");
	}

}
