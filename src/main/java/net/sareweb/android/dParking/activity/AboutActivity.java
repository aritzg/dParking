package net.sareweb.android.dParking.activity;


import net.sareweb.android.dParking.R;
import android.app.Activity;
import android.os.Bundle;

import com.googlecode.androidannotations.annotations.EActivity;

@EActivity
public class AboutActivity extends Activity {
	
    private static String TAG = "AboutActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
    }

}

