package org.stevejxsn.alecalphabet;

import android.content.Intent;
import android.test.ActivityUnitTestCase;

public class MainActivityTest extends ActivityUnitTestCase<MainActivity> {

	public MainActivityTest() {
		super(MainActivity.class);
	}
	
	public void testItDoesStuff() {
		startActivity(new Intent(), null, null);
	}

}
