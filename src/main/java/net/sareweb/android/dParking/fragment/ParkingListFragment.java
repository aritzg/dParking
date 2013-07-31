package net.sareweb.android.dParking.fragment;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.adapter.ParkingAdapter;
import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.util.CityUtil;
import net.sareweb.android.dParking.util.DParkingConstants;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;

@EFragment(R.layout.parking_list)
public class ParkingListFragment extends SherlockFragment {

	private static String TAG = "ParkingListFragment";
	ProgressDialog dialog;
	SharedPreferences userPrefs;
	ParkingAdapter sAdapter;
	City city;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		dialog = ProgressDialog.show(getSherlockActivity(), "",
				getString(R.string.loading), true);
		dialog.show();

		loadData();

	}

	@Background
	void loadData() {
		userPrefs = getSherlockActivity().getSharedPreferences(
				DParkingConstants.USER_PREFS,
				getSherlockActivity().MODE_PRIVATE);
		String idioma = userPrefs.getString(DParkingConstants.USER_PREFS_LANG,
				DParkingConstants.USER_PREF_LANG_EU);
		city = CityUtil.initCity(idioma);
		finishedBackgroundThread(0);
	}

	@UiThread
	void finishedBackgroundThread(int result) {
		sAdapter = new ParkingAdapter(getSherlockActivity(), city);
		ListView list = (ListView) getView().findViewById(android.R.id.list);
		list.setAdapter(sAdapter);
		dialog.cancel();
	}
}
