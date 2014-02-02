package org.stevejxsn.alecalphabet;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WordDisplay word = new WordDisplay((TextView)findViewById(R.id.wordView));
        setupKeyboard(word);
    }
    
    private void setupKeyboard(WordDisplay word) {
    	KeyboardView keyboardView = (KeyboardView)findViewById(R.id.keyboardview);
        keyboardView.setKeyboard(new Keyboard(this, R.xml.aleckbd));
        keyboardView.setPreviewEnabled(false);
        keyboardView.setOnKeyboardActionListener(new KeyboardListener(word));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
