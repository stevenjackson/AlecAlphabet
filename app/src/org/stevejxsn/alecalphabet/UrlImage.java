package org.stevejxsn.alecalphabet;

import android.graphics.Bitmap;

public class UrlImage implements WordImageUpdater {
	private WordImage view;

	public UrlImage(WordImage view) {
		this.view = view;
	}
	
	public void updateView(Word word) {
		new BitmapDownloadTask(this).execute(word.url);
	}
	
	public void setBitmap(Bitmap bitmap) {
		view.setImageBitmap(bitmap);
	}
}
