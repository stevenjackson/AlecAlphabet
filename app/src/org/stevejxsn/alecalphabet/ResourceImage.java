package org.stevejxsn.alecalphabet;

public class ResourceImage implements WordImageUpdater {
	private WordImage view;

	public ResourceImage(WordImage view) {
		this.view = view;
	}
	
	public void updateView(Word word) {
		view.setImageResource(word.resourceId);
	}
}
