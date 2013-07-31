package net.sareweb.android.dParking.fragment;

import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.model.Parking;
import net.sareweb.android.dParking.util.CityUtil;
import net.sareweb.android.dParking.util.DParkingConstants;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.UiThread;

@EFragment
public class ParkingMapFragment extends SupportMapFragment {
	private static final String TAG = "ParkingMapFragment";
	City city;

	@Override
	public void onResume() {
		super.onResume();

		LatLng position = new LatLng(DParkingConstants.DPARKING_DEFAULT_LAT,
				DParkingConstants.DPARKING_DEFAULT_LNG);

		CameraPosition cameraPosition = new CameraPosition(position,
				DParkingConstants.DPARKING_DEFAULT_ZOOM, 0, 0);
		CameraUpdate cu = CameraUpdateFactory.newCameraPosition(cameraPosition);
		getMap().moveCamera(cu);

		loadStations();

	}

	@Background
	void loadStations() {
		city = CityUtil.initCity(DParkingConstants.IDIOMA_CAS);
		paintStationsInMap();
	}

	@UiThread
	void paintStationsInMap() {
		getMap().clear();
		if (city == null){
			Log.e(TAG, "City was null!!");
			return;
		}
		for (Parking p : city.getParkings()) {

			Double lat = p.getLatitud();
			Double lng = p.getLongitud();

			if (lat != 0 && lng != 0) {
				Log.d(TAG, "Adding marker! (" + lat + " , " + lng + ")");
				LatLng stationPosition = new LatLng(lat, lng);
				MarkerOptions mo = new MarkerOptions();
				mo.position(stationPosition);
				getMap().addMarker(mo);
			}
		}
	}

}
