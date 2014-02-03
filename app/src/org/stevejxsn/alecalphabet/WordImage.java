package org.stevejxsn.alecalphabet;

import android.widget.ImageView;

public class WordImage {
	private ImageView view;

	public WordImage(ImageView view) {
		this.view = view;
	}
	
	public void updateImage(String word) {
		word = word.toLowerCase();
		String url = "http://photographicdictionary.com/sites/photographicdictionary.com/files/photos/" + word.charAt(0) +"/" + word + ".jpg";
		new DownloadImageTask(view).execute(url);
	}

	public void clear() {
		view.setImageResource(R.drawable.ic_launcher);
	}
	
}
