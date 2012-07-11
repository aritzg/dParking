package net.sareweb.android.dParking.activity;


import java.util.List;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.model.Parking;
import net.sareweb.android.dParking.overlay.ParkingItemizedOverlay;
import net.sareweb.android.dParking.overlay.ParkingOverlayItem;
import net.sareweb.android.dParking.util.CityUtil;
import net.sareweb.android.dParking.util.ConnectionUtil;
import net.sareweb.android.dParking.util.DParkingConstants;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class ParkingMapActivity extends MapActivity {
	
    private static String TAG = "StationListActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!ConnectionUtil.isOnline(this)){
    		setContentView(R.layout.not_connected);
    	}else{
            setContentView(R.layout.parking_map);
            
            MapView mapView = (MapView) findViewById(R.id.mapview);
    	    mapView.setBuiltInZoomControls(true);
    	    MapController controller =  mapView.getController();
    	    controller.setCenter(getDefaultGeoPoint());
    	    controller.setZoom(DParkingConstants.DPARKING_DEFAULT_ZOOM);
    	    
    	    mapOverlays = mapView.getOverlays();
    	    city = CityUtil.initCity(DParkingConstants.IDIOMA_CAS);
    	    loadStationsInMap();
    	}
    }
    
    private void loadStationsInMap(){
		Drawable drawable = this.getResources().getDrawable(android.R.drawable.btn_star);
	    itemizedoverlay = new ParkingItemizedOverlay(drawable, this);
	 
	    mapOverlays.clear();
	    
	    for (Parking p : city.getParkings()) {

			Double lat = p.getLatitud() * 1000000;
			Double lng = p.getLongitud() * 1000000;

			if (lat.intValue() != 0 && lng.intValue() != 0) {
				GeoPoint point = new GeoPoint(lat.intValue(), lng.intValue());
				ParkingOverlayItem overlayitem = new ParkingOverlayItem(point,
						p.getNombre(), "", p);
				itemizedoverlay.addOverlay(overlayitem);
				mapOverlays.add(itemizedoverlay);
			}
		}
	}
    
    private GeoPoint getDefaultGeoPoint(){
    	return new GeoPoint(DParkingConstants.DPARKING_DEFAULT_LAT, DParkingConstants.DPARKING_DEFAULT_LNG);
    }

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	City city;
	List<Overlay> mapOverlays;
	ParkingItemizedOverlay itemizedoverlay;
	SharedPreferences userPrefs;

}

