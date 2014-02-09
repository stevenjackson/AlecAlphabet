package org.stevejxsn.alecalphabet;

public class Word {
	public final String word;
	public final String url;
	public final int resourceId;
	private WordImageUpdater handler;
	
	public static Word createWord(String word, String url) {
		return new Word(word, url, Integer.MIN_VALUE);
	}
	
	public static Word createWord(String word, int resourceId) {
		return new Word(word, null, resourceId);
	}
	
	private Word(String word, String url, int resourceId) {
		this.word = word;
		this.url = url;
		this.resourceId = resourceId;
	}
	
	public boolean hasUrl() {
		return url != null;
	}
	
	public void setImageUpdater(WordImageUpdater handler) {
		this.handler = handler;
	}
	
	public void updateImage() {
		handler.updateView(this);
	}
}
