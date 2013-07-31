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

@EFragment(R.layout.about)
public class AboutFragment extends SherlockFragment {
	private static String TAG = "AboutFragment";
}
