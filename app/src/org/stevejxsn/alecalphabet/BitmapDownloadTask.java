package org.stevejxsn.alecalphabet;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

public class BitmapDownloadTask extends AsyncTask<String, Void, Bitmap> {
	private UrlImage image;

	public BitmapDownloadTask(UrlImage image) {
		this.image = image;
	}
	
	@Override
	protected Bitmap doInBackground(String... urls){
		InputStream in = null;
		try {
			in = new java.net.URL(urls[0]).openStream();
			return BitmapFactory.decodeStream(in);
		} catch(Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try { in.close(); } catch(Exception ex) {}
		}
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		image.setBitmap(result);
	}
}
