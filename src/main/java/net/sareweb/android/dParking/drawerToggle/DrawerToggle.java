package net.sareweb.android.dParking.drawerToggle;

import net.sareweb.android.dParking.R;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class DrawerToggle extends ActionBarDrawerToggle {

	SherlockFragmentActivity activity;
	
	public DrawerToggle(SherlockFragmentActivity activity, DrawerLayout drawerLayout,
			int drawerImageRes, int openDrawerContentDescRes,
			int closeDrawerContentDescRes) {
		super(activity, drawerLayout, drawerImageRes, openDrawerContentDescRes,
				closeDrawerContentDescRes);
		
		this.activity = activity;
		
	}
	
	 /** Called when a drawer has settled in a completely closed state. */
    public void onDrawerClosed(View view) {
    	activity.getSupportActionBar().setTitle("dParking");
    	//activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    }

    /** Called when a drawer has settled in a completely open state. */
    public void onDrawerOpened(View drawerView) {
    	activity.getSupportActionBar().setTitle(activity.getString(R.string.menu));
    	//activity.invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
    }

}