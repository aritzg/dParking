package net.sareweb.android.dParking.overlay;

import java.util.ArrayList;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.dialog.ParkingInfoDialog;
import net.sareweb.android.dParking.model.Parking;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.TextView;


public class ParkingItemizedOverlay{}/* extends ItemizedOverlay<ParkingOverlayItem> {

	private ArrayList<ParkingOverlayItem> mOverlayItems = new ArrayList<ParkingOverlayItem>();
	Context mContext;

	public ParkingItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public ParkingItemizedOverlay(Drawable defaultMarker, Context context) {
		super(boundCenterBottom(defaultMarker));
		mContext = context;
	}

	public void addOverlay(ParkingOverlayItem overlay) {
		mOverlayItems.add(overlay);
		populate();
	}

	@Override
	protected ParkingOverlayItem createItem(int i) {
		return mOverlayItems.get(i);
	}

	@Override
	public int size() {
		return mOverlayItems.size();
	}
	
	@Override
	protected boolean onTap(int index) {
		ParkingOverlayItem item = mOverlayItems.get(index);
		Parking p = item.getparking();
		ParkingInfoDialog dialog = new ParkingInfoDialog(mContext, p);
		dialog.show();
		return true;
	}

	
}*/
