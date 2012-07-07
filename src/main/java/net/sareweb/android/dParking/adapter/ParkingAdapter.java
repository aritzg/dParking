package net.sareweb.android.dParking.adapter;

import java.net.URLDecoder;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.dialog.ParkingInfoDialog;
import net.sareweb.android.dParking.exception.NoSuchParkingException;
import net.sareweb.android.dParking.model.City;
import net.sareweb.android.dParking.model.Parking;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ParkingAdapter extends BaseAdapter implements OnClickListener{

	private Context context;
	private City city;
	
	public ParkingAdapter(Context context, City city) {
		this.context = context;
		this.city = city;
	}
	
	@Override
	public int getCount() {
		return city.getParkingAmount();
	}

	@Override
	public Object getItem(int i) {
		try {
			return city.getParking(Integer.valueOf(i));
		} catch (NoSuchParkingException e) {
			return null;
		}
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.parking_entry, null);
		}
		
		TextView name = (TextView) convertView.findViewById(R.id.name);
		TextView info = (TextView) convertView.findViewById(R.id.info);
		
		Parking parking = null;
		try {
			parking = city.getParking(position);
		} catch (NoSuchParkingException e) {
			info.setText("Error!");
			return convertView;
		}
		
		String nameDecoded = URLDecoder.decode(parking.getNombre());
		name.setText(nameDecoded);
		info.setText(parking.getPlazasLibres() + " / "
				+ parking.getPlazasTotales());
		convertView.setTag(parking);
		convertView.setOnClickListener(this);
		
		return convertView;
	}

	@Override
	public void onClick(View view) {
		Parking parking = ((Parking)view.getTag());
		ParkingInfoDialog dialog = new ParkingInfoDialog(view.getContext(), parking);
		dialog.show();
	}

}
