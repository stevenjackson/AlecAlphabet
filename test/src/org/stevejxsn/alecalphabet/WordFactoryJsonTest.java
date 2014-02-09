package org.stevejxsn.alecalphabet;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.test.AndroidTestCase;

public class WordFactoryJsonTest extends AndroidTestCase {
	public void testItParsesJSON() throws Exception {
		String json = "[{\"word\":\"APPLE\",\"img_link\":\"link/apple.jpg\"}]";

		List<Word> words = WordFactory.fromJSON(new JSONArray(json));
		assertEquals("APPLE", words.get(0).word);
	}
	
	public void testMultipleWords() throws Exception {
		String json = "[" +
				"{\"word\":\"APPLE\",\"img_link\":\"link/apple.jpg\"}," +
				"{\"word\":\"APPLET\",\"img_link\":\"link/applet.jpg\"}" +
				"]";

		List<Word> words = WordFactory.fromJSON(new JSONArray(json));
		assertEquals(2, words.size());
	}
	
	public void testItCapitalizes() throws Exception {
		String json = "{\"word\":\"cat\", \"img_link\":\"link\"}";
		Word word = WordFactory.toWord(new JSONObject(json));
		assertEquals("CAT", word.word);
	}
	
	public void testItAddsWhatItCanIfTheDataIsBad() throws Exception{
		String json = "[" +
				"{\"word\":\"NO_LINK\"}," +
				"{\"word\":\"APPLE\",\"img_link\":\"link/apple.jpg\"}," +
				"]";

		List<Word> words = WordFactory.fromJSON(new JSONArray(json));
		assertEquals(1, words.size());
	}	
}
