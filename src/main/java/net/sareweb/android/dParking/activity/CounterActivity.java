package net.sareweb.android.dParking.activity;

import net.sareweb.android.dParking.R;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity
public class CounterActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter);
	}

	private SharedPreferences.Editor editor;
	private SharedPreferences userPrefs;
	private static String TAG = "CounterActivity";

}
