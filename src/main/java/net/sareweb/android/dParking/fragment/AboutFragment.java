package net.sareweb.android.dParking.fragment;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.adapter.ParkingAdapter;
import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.util.CityUtil;
import net.sareweb.android.dParking.util.DParkingConstants;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.about)
public class AboutFragment extends SherlockFragment {
	private static String TAG = "AboutFragment";
	
	@ViewById(R.id.txVersion)
	TextView txVersion;
	
	@Override
	public void onResume() {
		super.onResume();
		try {
			txVersion.setText("v" + getSherlockActivity().getPackageManager().getPackageInfo(getSherlockActivity().getPackageName(), 0).versionName);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}		
	}
}
