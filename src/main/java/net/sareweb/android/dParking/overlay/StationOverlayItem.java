package net.sareweb.android.dParking.overlay;

import net.sareweb.android.dParking.model.Station;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class StationOverlayItem extends OverlayItem{

	private Station station;
	
	public StationOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
	}
	
	public StationOverlayItem(GeoPoint point, String title, String snippet, Station station) {
		super(point, title, snippet);
		this.station=station;
	}

	public Station getStation(){
		return station;
	}
	
}
