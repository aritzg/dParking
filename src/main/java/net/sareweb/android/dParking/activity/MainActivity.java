package net.sareweb.android.dParking.activity;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.drawerToggle.DrawerToggle;
import net.sareweb.android.dParking.fragment.AboutFragment_;
import net.sareweb.android.dParking.fragment.CounterFragment_;
import net.sareweb.android.dParking.fragment.ParkingListFragment_;
import net.sareweb.android.dParking.fragment.ParkingMapFragment_;
import net.sareweb.android.dParking.fragment.SettingsFragment_;
import net.sareweb.android.dParking.plus.PlusConnectionCallbacks;
import net.sareweb.android.dParking.plus.PlusOnConnectionFailedListener;
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
import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.PlusOneButton;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.OptionsItem;
import com.googlecode.androidannotations.annotations.ViewById;

@EActivity(R.layout.main)
public class MainActivity extends SherlockFragmentActivity {

	private static String TAG = "dParking";
	DrawerLayout mDrawerLayout;
	DrawerToggle drawerToggle;
	SharedPreferences userPrefs;
	int fragmentToBeLoaded;
	PlusClient mPlusClient;
	@ViewById(R.id.plus_one_button)
	PlusOneButton mPlusOneButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		userPrefs = getSharedPreferences(DParkingConstants.USER_PREFS,
				MODE_PRIVATE);
		if(savedInstanceState!=null){
			Log.d(TAG, "Restoring state info");
			fragmentToBeLoaded = savedInstanceState.getInt(FRAGMENT, FRAGMENT_PARKING_LIST);
			Log.d(TAG, "fragmentToBeLoaded " + fragmentToBeLoaded);
		}
		
		LangUtil.updateLanguage(this, userPrefs.getString(
				DParkingConstants.USER_PREFS_LANG,
				DParkingConstants.USER_PREF_LANG_EU));

		PlusClient.Builder pcBuilder = new PlusClient.Builder(this,
				new PlusConnectionCallbacks(),
				new PlusOnConnectionFailedListener());

		mPlusClient = pcBuilder.clearScopes().build();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		mPlusOneButton.initialize(mPlusClient,
				"https://play.google.com/store/apps/details?id=net.sareweb.android.dParking", PLUS_ONE_REQUEST_CODE);


		switch (fragmentToBeLoaded) {
		case FRAGMENT_PARKING_LIST:
			clickOnParkingList();
			break;
		case FRAGMENT_PARKING_MAP:
			clickOnParkingMap();
			break;
		case FRAGMENT_COUNTER:
			clickOnCounter();
			break;
		case FRAGMENT_SETTINGS:
			clickOnSettings();
			break;
		case FRAGMENT_ABOUT:
			clickOnAbout();
			break;
		
		default:
			clickOnParkingList();
			break;
		}

	}
	
	@Override
	protected void onStart() {
		super.onStart();
        mPlusClient.connect();
	}

	@Override
    protected void onStop() {
        super.onStop();
        mPlusClient.disconnect();
    }

	@Click(R.id.btnParking)
	public void clickOnParkingList() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new ParkingListFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
		fragmentToBeLoaded=FRAGMENT_PARKING_LIST;
	}
	
	@Click(R.id.btnMap)
	public void clickOnParkingMap() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new ParkingMapFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
		fragmentToBeLoaded=FRAGMENT_PARKING_MAP;
	}
	
	@Click(R.id.btnCounter)
	public void clickOnCounter() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new CounterFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
		fragmentToBeLoaded=FRAGMENT_COUNTER;
	}

	@Click(R.id.btnSettings)
	public void clickOnSettings() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new SettingsFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
		fragmentToBeLoaded=FRAGMENT_SETTINGS;
	}

	@Click(R.id.btnAbout)
	public void clickOnAbout() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		Fragment fragment = new AboutFragment_();

		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		mDrawerLayout.closeDrawers();
		fragmentToBeLoaded=FRAGMENT_ABOUT;
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
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG, "Saving current state info");
		outState.putInt(FRAGMENT, fragmentToBeLoaded);
	}
	
	private static final String FRAGMENT ="fragment";
	private static final int FRAGMENT_PARKING_LIST = 0;
	private static final int FRAGMENT_PARKING_MAP = 1;
	private static final int FRAGMENT_COUNTER = 2;
	private static final int FRAGMENT_SETTINGS = 3;
	private static final int FRAGMENT_ABOUT = 4;
	
	
	private static final int PLUS_ONE_REQUEST_CODE = 0;
}
