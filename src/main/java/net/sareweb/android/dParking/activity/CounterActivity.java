package net.sareweb.android.dParking.activity;

import java.util.Date;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.util.Prefs_;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity
public class CounterActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter);
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		paintLayout();
	}



	@Click(R.id.btnStartStop)
	void clickOnStartStop() {
		if (prefs.counterTimeStamp().get() == 0) {
			Date now = new Date();
			prefs.counterTimeStamp().put(now.getTime());
		} else {
			prefs.counterTimeStamp().put(0);
		}
		paintLayout();
	}

	private void paintLayout() {
		setButtonText();
		updateData(prefs.counterTimeStamp().get());
	}

	private void setButtonText() {
		if (prefs.counterTimeStamp().get() == 0) {// Counter stoped
			btnStartStop.setText(R.string.start);
		} else {
			btnStartStop.setText(R.string.stop);
		}
	}

	private void updateData(long counterTimeStamp) {
		if (counterTimeStamp == 0) {
			txTime.setText(" 0 min");
			txPrice.setText(" 0 €");
		}else{
			txTime.setText("" + String.valueOf(calculateMinutes(counterTimeStamp)) + " min");
		}
	}

	private long calculateMinutes(long counterTimeStamp) {
		Date now = new Date();
		long nowTimeStamp = now.getTime();
		return (nowTimeStamp - counterTimeStamp) / 60000;
	}

	@ViewById
	Button btnStartStop;
	@ViewById
	TextView txTime;
	@ViewById
	TextView txPrice;
	@Pref
	Prefs_ prefs;
	private static String TAG = "CounterActivity";

}
