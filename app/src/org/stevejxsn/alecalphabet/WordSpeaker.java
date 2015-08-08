package org.stevejxsn.alecalphabet;

import java.util.HashMap;
import java.util.Locale;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

public class WordSpeaker implements OnInitListener {
	private static final HashMap<String, String> TTS_PARAMS = new HashMap<String, String>();
	static {
		TTS_PARAMS.put(TextToSpeech.Engine.KEY_FEATURE_NETWORK_SYNTHESIS, "true");
	}
	
	private TextToSpeech tts;

	//For testing
	protected WordSpeaker() {}
	
	public WordSpeaker(Context context) {
		tts = new TextToSpeech(context, this);
	}

	@Override
	public void onInit(int status) {
		if(status >= 0) {
			tts.setLanguage(Locale.US);
		} else {
			tts = null;
		}
	}
	
	
	public void speak(String s) {
		if(tts == null) return;

		tts.speak(s, TextToSpeech.QUEUE_FLUSH, TTS_PARAMS);
	}

}
