package org.stevejxsn.alecalphabet;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class WordFactory {

	public static List<Word> fromJSON(JSONArray json) throws JSONException {
		WordList words = new WordList();
		for(int i = 0; i < json.length(); i++) {
			words.add(toWordAt(json, i));
		}
		return words.toList();
	}

	protected static Word toWordAt(JSONArray json, int i) {
		try {
			return toWord(json.getJSONObject(i));
		} catch (JSONException e) {
			Log.e("AlecAlphabet", "Bad json", e);
			return null;
		}
	}

	public static Word toWord(JSONObject json) throws JSONException {
		String word = json.getString("word").toUpperCase(Locale.US);
		String url = json.getString("img_link");
		return Word.createWord(word, url);
	}

	private static class WordList {
		List<Word> words = new ArrayList<Word>();
		
		public void add(Word word) {
			if(word == null) return;
			words.add(word);
		}
	
		public List<Word> toList() {
			return words;
		}
	}
}
