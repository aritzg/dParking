package net.sareweb.android.dParking.overlay;

import java.util.ArrayList;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.model.Station;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.google.android.maps.ItemizedOverlay;

public class StationItemizedOverlay extends ItemizedOverlay<StationOverlayItem> {

	private ArrayList<StationOverlayItem> mOverlayItems = new ArrayList<StationOverlayItem>();
	Context mContext;

	public StationItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public StationItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(StationOverlayItem overlay) {
		mOverlayItems.add(overlay);
		populate();
	}

	@Override
	protected StationOverlayItem createItem(int i) {
		return mOverlayItems.get(i);
	}

	@Override
	public int size() {
		return mOverlayItems.size();
	}
	
	@Override
	protected boolean onTap(int index) {
		StationOverlayItem item = mOverlayItems.get(index);
		Station s = item.getStation();
		Dialog dialog = new Dialog(mContext);
		dialog.setContentView(R.layout.station_dialog);

		dialog.setTitle(item.getTitle());
		
		TextView info = (TextView) dialog.findViewById(R.id.info);
		info.setText(s.getBicisDisponibles() + " / " + s.getPlazasTotales());
	
		dialog.show();
		return true;
	}

	
}
