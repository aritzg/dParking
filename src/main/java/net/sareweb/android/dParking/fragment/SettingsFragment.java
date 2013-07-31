package net.sareweb.android.dParking.fragment;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.activity.MainActivity_;
import net.sareweb.android.dParking.util.DParkingConstants;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.settings)
public class SettingsFragment extends SherlockFragment implements
		OnClickListener {

	@ViewById
	RadioButton rdLangCas;
	@ViewById
	RadioButton rdLangEus;

	private SharedPreferences.Editor editor;
	private SharedPreferences userPrefs;
	private static String TAG = "SettingsFragment";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		userPrefs = getSherlockActivity().getSharedPreferences(
				DParkingConstants.USER_PREFS,
				getSherlockActivity().MODE_PRIVATE);
	}

	@Override
	public void onResume() {
		super.onResume();
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
			userPrefs = getSherlockActivity().getSharedPreferences(
					DParkingConstants.USER_PREFS,
					getSherlockActivity().MODE_PRIVATE);
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

		Intent intent = MainActivity_.intent(getSherlockActivity()).get();
		getSherlockActivity().overridePendingTransition(0, 0);
		intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
		getSherlockActivity().finish();
		getSherlockActivity().overridePendingTransition(0, 0);
		startActivity(intent);

	}

}
