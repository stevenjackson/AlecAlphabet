package org.stevejxsn.alecalphabet;

import java.util.HashMap;
import java.util.Locale;

import android.view.View;
import android.widget.ImageView;

public class WordImage {
	private ImageView view;
	private HashMap<String, Integer> KNOWN_WORDS = new HashMap<String, Integer>();
	{
		KNOWN_WORDS.put("OTIS", R.drawable.otis);
		KNOWN_WORDS.put("IGGY", R.drawable.iggy);
		KNOWN_WORDS.put("ALEC", R.drawable.alec);
	}

	public WordImage(ImageView view) {
		this.view = view;
	}
	
	public void updateImage(String word) {
		if(handledSpecial(word)) return;

		word = word.toLowerCase(Locale.US);
		String url = "https://s3.amazonaws.com/alecalphabet/" + word + ".jpg";
		new DownloadImageTask(view).execute(url);
	}

	private boolean handledSpecial(String word) {
		if(KNOWN_WORDS.containsKey(word)) {
			view.setImageResource(KNOWN_WORDS.get(word));
			return true;
		} else {
			return false;
		}
	}

	public void clear() {
		view.setImageResource(android.R.color.transparent);
	}

	public View getView() {
		return view;
	}
}
