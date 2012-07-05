package net.sareweb.android.dParking.activity;

import net.sareweb.android.dParking.activity.DParkingMainActivity_;
import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.util.DParkingConstants;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity
public class SettingsActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		userPrefs = getSharedPreferences(DParkingConstants.USER_PREFS,
				MODE_PRIVATE);
		String lang = userPrefs.getString(DParkingConstants.USER_PREFS_LANG,
				DParkingConstants.USER_PREF_LANG_EU);

		if (lang.equals(DParkingConstants.USER_PREF_LANG_ES)) {
			rdLangCas.setChecked(true);
		} else {
			rdLangEus.setChecked(true);
		}

		rdLangCas.setOnClickListener(this);
		rdLangEus.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (userPrefs == null)
			userPrefs = getSharedPreferences(DParkingConstants.USER_PREFS,
					MODE_PRIVATE);
		editor = userPrefs.edit();

		switch (v.getId()) {
		case R.id.rdLangCas:
			editor.putString(DParkingConstants.USER_PREFS_LANG,
					DParkingConstants.USER_PREF_LANG_ES);
			break;

		default:
			editor.putString(DParkingConstants.USER_PREFS_LANG,
					DParkingConstants.USER_PREF_LANG_EU);
			break;
		}
		editor.commit();
		reload();
	}

	public void reload() {
		
		Intent intent = DParkingMainActivity_.intent(this).get();
		overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		finish();
		overridePendingTransition(0, 0);
		startActivity(intent);

	}

	@ViewById
	RadioButton rdLangCas;
	@ViewById
	RadioButton rdLangEus;

	private SharedPreferences.Editor editor;
	private SharedPreferences userPrefs;
	private static String TAG = "SettingsActivity";

}
