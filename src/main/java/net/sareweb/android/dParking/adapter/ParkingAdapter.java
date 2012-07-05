package net.sareweb.android.dParking.adapter;

import java.net.URLDecoder;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.exception.NoSuchParkingException;
import net.sareweb.android.dParking.model.City;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ParkingAdapter extends BaseAdapter{

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
		try {
			String nameDecoded = URLDecoder.decode(city.getParking(position).getNombre());
			name.setText(nameDecoded);
		} catch (NoSuchParkingException e) {
			name.setText("Error!");
		}

		TextView info = (TextView) convertView.findViewById(R.id.info);
		try {
			info.setText(city.getParking(position).getPlazasLibres() + " / " + city.getParking(position).getPlazasTotales());
		} catch (NoSuchParkingException e) {
			info.setText("Error!");
		}
		
		return convertView;
	}

}
