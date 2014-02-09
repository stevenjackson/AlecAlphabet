package org.stevejxsn.alecalphabet;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class WordImage {
	private ImageView view;

	public WordImage(ImageView view) {
		this.view = view;
	}
	
	public void clear() {
		view.setImageResource(android.R.color.transparent);
	}

	public void setImageBitmap(Bitmap bitmap) {
		view.setImageBitmap(bitmap);
	}

	public void setImageResource(int resourceId) {
		view.setImageResource(resourceId);
	}
}
