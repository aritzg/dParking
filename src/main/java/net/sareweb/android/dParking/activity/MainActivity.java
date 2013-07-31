package net.sareweb.android.dParking.activity;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.drawerToggle.DrawerToggle;
import net.sareweb.android.dParking.fragment.AboutFragment_;
import net.sareweb.android.dParking.fragment.CounterFragment_;
import net.sareweb.android.dParking.fragment.ParkingListFragment_;
import net.sareweb.android.dParking.fragment.ParkingMapFragment_;
import net.sareweb.android.dParking.fragment.SettingsFragment_;
import net.sareweb.android.dParking.util.DParkingConstants;
import net.sareweb.android.dParking.util.LangUtil;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;

@EActivity(R.layout.main)
public class MainActivity extends SherlockFragmentActivity {

	private static String TAG = "dParking";
	DrawerLayout mDrawerLayout;
	DrawerToggle drawerToggle;
	SharedPreferences userPrefs;
	int fragmentToBeLoaded;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		userPrefs = getSharedPreferences(DParkingConstants.USER_PREFS,
				MODE_PRIVATE);

		LangUtil.updateLanguage(this, userPrefs.getString(
				DParkingConstants.USER_PREFS_LANG,
				DParkingConstants.USER_PREF_LANG_EU));

	}

	@Override
	protected void onResume() {
		super.onResume();

		switch (fragmentToBeLoaded) {

		default:
			clickOncounter();
			break;
		}

	}

	@Click(R.id.btnParking)
	public void clickOnParkingList() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new ParkingListFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
	}
	
	@Click(R.id.btnMap)
	public void clickOnParkingMap() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new ParkingMapFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
	}
	
	@Click(R.id.btnCounter)
	public void clickOncounter() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new CounterFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
	}

	@Click(R.id.btnSettings)
	public void clickOnSettings() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new SettingsFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
	}

	@Click(R.id.btnAbout)
	public void clickOnAbout() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new AboutFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
	}

	@OptionsItem
	boolean homeSelected() {
		Log.d(TAG, "homeSelected");
		mDrawerLayout.openDrawer(Gravity.LEFT);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		Log.d(TAG, "onPostCreate");
		if (mDrawerLayout == null) {
			mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
			drawerToggle = new DrawerToggle(this, mDrawerLayout,
					R.drawable.ic_drawer, R.string.ireki, R.string.itxi);

			mDrawerLayout.setDrawerListener(drawerToggle);
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
		}
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}
}
