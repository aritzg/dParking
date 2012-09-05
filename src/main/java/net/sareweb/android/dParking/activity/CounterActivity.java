package net.sareweb.android.dParking.activity;

import java.util.Date;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.exception.NoTarifaException;
import net.sareweb.android.dParking.tarifa.TarifaFactory;
import net.sareweb.android.dParking.util.Prefs_;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity
public class CounterActivity extends Activity implements OnCheckedChangeListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.counter);
		rdGrTarifas.setOnCheckedChangeListener(this);
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

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rdTarifa1:
			prefs.counterTarifa().put(1);
			break;
		case R.id.rdTarifa2:
			prefs.counterTarifa().put(2);
			break;
		case R.id.rdTarifa3:
			prefs.counterTarifa().put(3);
			break;

		default:
			break;
		}
		paintLayout();
	}
	
	@Background
	void countOneMinute() {
    	try {
			Thread.currentThread().sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	paintLayout();
	}
    
    @UiThread
	void paintLayout() {
		setButtonText();
		int tarifa = prefs.counterTarifa().getOr(3);
		updateData(prefs.counterTimeStamp().get(), tarifa);
		toggleSelectedRadio(tarifa);
		countOneMinute();
	}

	private void setButtonText() {
		if (prefs.counterTimeStamp().get() == 0) {// Counter stoped
			btnStartStop.setText(R.string.start);
		} else {
			btnStartStop.setText(R.string.counting_stop);
		}
	}

	private void updateData(long counterTimeStamp, int tarifa) {
		
		if (counterTimeStamp == 0) {
			txTime.setText(" --");
			txPrice.setText(" --");
		}else{
			long minutos = calculateMinutes(counterTimeStamp);
			txTime.setText(getString(R.string.x_min, minutos));
			try {
				double precio = TarifaFactory.getTarifaUtil(tarifa).obtenerCostePorMinutos(minutos);
				txPrice.setText(getString(R.string.x_euro, precio));
			} catch (NoTarifaException e) {
				txPrice.setText(getString(R.string.x_euro, 0));
				Log.e(TAG, "No such tarifa");
			}
		}
	}
	
	private void toggleSelectedRadio(int tarifa) {
		switch (tarifa) {
		case 1:
			rdTarifa1.toggle();
			break;
		case 2:
			rdTarifa2.toggle();
			break;
		case 3:
			rdTarifa3.toggle();
			break;

		default:
			break;
		}
		
	}

	private long calculateMinutes(long counterTimeStamp) {
		Date now = new Date();
		long nowTimeStamp = now.getTime();
		long millDiff = nowTimeStamp - counterTimeStamp;
		if(millDiff /(1000*60*60*24)>=1){
			prefs.counterTimeStamp().put(0);
			millDiff=0;
		}
		return millDiff / 60000;
	}

	@ViewById
	Button btnStartStop;
	@ViewById
	TextView txTime;
	@ViewById
	TextView txPrice;
	@ViewById
	RadioGroup rdGrTarifas;
	
	@ViewById
	RadioButton rdTarifa1;
	@ViewById
	RadioButton rdTarifa2;
	@ViewById
	RadioButton rdTarifa3;
	
	@Pref
	Prefs_ prefs;
	private static String TAG = "CounterActivity";
	

}
