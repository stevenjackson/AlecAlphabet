package org.stevejxsn.alecalphabet;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;

public class MainActivity extends Activity implements AlecKeyboardListener {

    private WordImage image;
	private WordDisplay word;
	private AlecKeyboard keyboard;
	private AlecDictionary dictionary;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = new WordImage((ImageView)findViewById(R.id.imageView));
        word = new WordDisplay((TextView)findViewById(R.id.wordView));
        keyboard = setupKeyboard(word);
        dictionary = setupDictionary();
        word.getView().setOnTouchListener(resetOnTouchListener());
        image.getView().setOnTouchListener(resetOnTouchListener());
    }
    
    private AlecKeyboard setupKeyboard(WordDisplay word) {
    	AlecKeyboard keyboard = new AlecKeyboard((KeyboardView)findViewById(R.id.keyboardview), new Keyboard(this, R.xml.aleckbd));
    	keyboard.setListener(this);
    	return keyboard;
    }
    
    private AlecDictionary setupDictionary() {
    	List<String> words = Arrays.asList(
    		"ALEC", "APPLE",
    		"BALL",
    		"CAR",
    		"DOG",
    		"ELEPHANT",
    		"FOX",
    		"GRAPES",
    		"HORSE",
    		"IGLOO", "IGGY",
    		"JAR",
    		"KANGAROO",
    		"LION",
    		"MONKEY",
    		"NEST",
    		"ORANGE", "OTIS",
    		"PANDA",
    		"QUEEN",
    		"RABBIT",
    		"STRAWBERRY",
    		"TIGER",
    		"UMBRELLA",
    		"VIOLIN",
    		"WATERMELON",
    		"XYLOPHONE",
    		"YAK",
    		"ZEBRA"
    	);
    	return new AlecDictionary(words);
    }
    
    private OnTouchListener resetOnTouchListener() {
		return new OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				reset();
				return true;
			}
		};
	}
    
    @Override
	public void onLetterKey(char key) {
		word.append(Character.toString(key));
		String currentWord = word.getText().toString();
		if(dictionary.isWord(currentWord)) {
			image.updateImage(currentWord);
		}
		keyboard.setActiveKeys(dictionary.nextPossibleLetters(currentWord));
	}
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void reset() {
    	word.clear();
		keyboard.reset();
		image.clear();
    }
    
}
