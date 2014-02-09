package org.stevejxsn.alecalphabet;

import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class MainActivityInitializer {

	private static final String WORD_LIST_URL = "https://s3.amazonaws.com/alecalphabet/dictionary.json.gz";
	private WordChangeHandler dictionary;

	public MainActivityInitializer(WordChangeHandler dictionary) {
		this.dictionary = dictionary;
	}

	public void start() {
		dictionary.addWords(staticWords());
		new GzipJsonDownloadTask(this).execute(WORD_LIST_URL);
	}
	
	private static Word[] staticWords() {
		return new Word[] {
			Word.createWord("OTIS", R.drawable.otis),
			Word.createWord("IGGY", R.drawable.iggy),
			Word.createWord("ALEC", R.drawable.alec)
		};
	}

	public void jsonRetrieved(JSONArray json) {
		if(json == null) {
			Log.e("AlecAlphabet", "Could not retrieve dictionary");
		} try {
			dictionary.addWords(WordFactory.fromJSON(json));
		} catch(JSONException ex) {
			Log.e("AlecAlphabet", "Could not parse dictionary json", ex);
			Log.w("AlecAlphabet", json.toString());
		}
	}
}
