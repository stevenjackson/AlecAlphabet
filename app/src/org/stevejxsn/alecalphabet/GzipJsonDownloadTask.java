package org.stevejxsn.alecalphabet;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.json.JSONArray;

import android.os.AsyncTask;
import android.util.Log;

public class GzipJsonDownloadTask extends AsyncTask<String, Void, JSONArray> {
	private MainActivityInitializer callback;

	public GzipJsonDownloadTask(MainActivityInitializer callback) {
		this.callback = callback;
	}
	
	@Override
	protected JSONArray doInBackground(String... urls){
		InputStream in = null;
		try {
			in = new java.net.URL(urls[0]).openStream();
			return new JSONArray(upzip(in));
		} catch(Exception ex) {
			Log.e("AlecAlphabet", "Could not retrieve json", ex);
			return null;
		} finally {
			try { in.close(); } catch(Exception ex) {}
		}
	}

	private String upzip(InputStream in) throws IOException {
		final int BUFFER_SIZE = 32;   //Why 32?  Why not?
	    GZIPInputStream zip = new GZIPInputStream(in, BUFFER_SIZE);
	    StringBuilder builder = new StringBuilder();
	    byte[] data = new byte[BUFFER_SIZE];
	    int bytesRead;
	    while ((bytesRead = zip.read(data)) != -1) {
	    	builder.append(new String(data, 0, bytesRead));
	    }
	    zip.close();
	    return builder.toString();
	}

	@Override
	protected void onPostExecute(JSONArray json) {
		callback.jsonRetrieved(json);
	}

}
