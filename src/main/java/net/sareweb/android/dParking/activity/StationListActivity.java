package net.sareweb.android.dParking.activity;


import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.adapter.StationAdapter;
import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.util.CityUtil;
import net.sareweb.android.dParking.util.ConnectionUtil;
import net.sareweb.android.dParking.util.DBiziConstants;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;

@EActivity
public class StationListActivity extends Activity {
	
    private static String TAG = "StationListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	if(!ConnectionUtil.isOnline(this)){
    		setContentView(R.layout.not_connected);
    	}else{
            setContentView(R.layout.station_list);
            
            dialog = ProgressDialog.show(this, "", getString(R.string.loading), true);
		dialog.show();
		
        loadData();
    	}
    }
    
    
    @Background
	void loadData() {
    	userPrefs = getSharedPreferences(DBiziConstants.USER_PREFS, MODE_PRIVATE);
        String idioma = userPrefs.getString(DBiziConstants.USER_PREFS_LANG, DBiziConstants.USER_PREF_LANG_EU);
        city = CityUtil.initCity(idioma);
    	finishedBackgroundThread(0);
	}

	@UiThread
	void finishedBackgroundThread(int result) {
		sAdapter = new StationAdapter(this, city);
        ListView list = (ListView)findViewById(android.R.id.list);
		list.setAdapter(sAdapter);
		dialog.cancel();
	}
	
	ProgressDialog dialog;
    StationAdapter sAdapter;
    SharedPreferences userPrefs;
    City city;

}

