package org.stevejxsn.alecalphabet;

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
	private WordChangeHandler wordChanged;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = createWordImage();
        word = createWordDisplay();
        keyboard = setupKeyboard();
       
        wordChanged = new WordChangeHandler(keyboard, image);
        new MainActivityInitializer(wordChanged).start();
      
    }

	private WordDisplay createWordDisplay() {
		TextView textView = (TextView)findViewById(R.id.wordView);
		textView.setOnTouchListener(resetOnTouchListener());
		return new WordDisplay(textView);
	}
	
	private WordImage createWordImage() {
		ImageView imageView = (ImageView)findViewById(R.id.imageView);
		imageView.setOnTouchListener(resetOnTouchListener());
		return new WordImage(imageView);
	}
    
    private AlecKeyboard setupKeyboard() {
    	AlecKeyboard keyboard = new AlecKeyboard((KeyboardView)findViewById(R.id.keyboardview), new Keyboard(this, R.xml.aleckbd));
    	keyboard.setListener(this);
    	return keyboard;
    }
    
    @Override
	public void onLetterKey(char key) {
		word.append(Character.toString(key));
		String currentWord = word.getText().toString();
		wordChanged.currentWordChanged(currentWord);
	}
    
    public void reset() {
    	word.clear();
		keyboard.reset();
		image.clear();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
