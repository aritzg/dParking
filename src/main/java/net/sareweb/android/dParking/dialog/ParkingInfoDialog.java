package net.sareweb.android.dParking.dialog;

import net.sareweb.android.dParking.R;
import net.sareweb.android.dParking.exception.NoTarifaException;
import net.sareweb.android.dParking.model.Parking;
import net.sareweb.android.dParking.tarifa.TarifaFactory;
import net.sareweb.android.dParking.tarifa.TarifaUtil;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ParkingInfoDialog extends Dialog implements android.view.View.OnClickListener, TextWatcher{

	private Parking parking;
	private EditText txMin;
	private TextView txCoste;
	private TarifaUtil tarifaUtil;
	
	public ParkingInfoDialog(Context context, Parking parking) {
		super(context);
		this.parking=parking;
		setContentView(R.layout.parking_dialog);
		setTitle(parking.getNombre());
		TextView info = (TextView) findViewById(R.id.info);
		info.setText(parking.getPlazasLibres() + " / " + parking.getPlazasTotales());
		
		LinearLayout layoutTarifa = (LinearLayout) findViewById( R.id.layoutTarifa);
		layoutTarifa.setOnClickListener(this);
		LinearLayout layoutGasto = (LinearLayout) findViewById( R.id.layoutGasto);
		layoutGasto.setOnClickListener(this);
		LinearLayout layoutDirections = (LinearLayout) findViewById( R.id.layoutDirections);
		layoutDirections.setOnClickListener(this);
		LinearLayout layoutNavigate = (LinearLayout) findViewById( R.id.layoutNavigate);
		layoutNavigate.setOnClickListener(this);
		
		Button btnPlus = (Button) findViewById( R.id.btnPlus);
		btnPlus.setOnClickListener(this);
		Button btnMinus = (Button) findViewById( R.id.btnMinus);
		btnMinus.setOnClickListener(this);
		
	}


	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.layoutGasto:
			clickOnGasto();
			break;
		case R.id.layoutTarifa:
			clickOnTarifa();
			break;
		case R.id.layoutDirections:
			clickOnDirections();
			break;
		case R.id.layoutNavigate:
			clickOnNavigate();
			break;
		case R.id.btnPlus:
			incMin(1);
			break;
		case R.id.btnMinus:
			incMin(-1);
			break;
		default:
			break;
		}
	}
	
	
	@Override
	public void afterTextChanged(Editable s) {
		String minString = txMin.getText().toString();
		int min=0;
		if(minString!=null && !minString.equals("")){
			min = Integer.parseInt(minString);
		}
		calcCoste(min);
	}


	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}
	
	
	private void clickOnGasto(){
		LinearLayout layoutCounter = (LinearLayout) findViewById( R.id.layoutCounter);
		showHide(layoutCounter);
	}
	
	private void clickOnTarifa(){
		LinearLayout layoutTarifaTabla = (LinearLayout) findViewById( R.id.layoutTarifaTabla);
		setDatosTarifa();
		showHide(layoutTarifaTabla);
	}
	
	private void clickOnDirections() {
		Intent intent = new Intent(
				android.content.Intent.ACTION_VIEW,
				Uri.parse("http://maps.google.com/maps?daddr=" + parking.getLatitud().toString() + "," + parking.getLongitud().toString()));
		getContext().startActivity(intent);
	}
	
	private void clickOnNavigate() {
		String location = parking.getLatitud().toString() + "," + parking.getLongitud().toString();
		Intent i = new Intent(Intent.ACTION_VIEW,
		Uri.parse("google.navigation:q=" + location));
		getContext().startActivity(i);
	}
	
	private void showHide(View v){
		if(v.getVisibility()==View.VISIBLE)
			v.setVisibility(View.GONE);
		else
			v.setVisibility(View.VISIBLE);
	}
	
	private void incMin(int i) {
		if(txMin==null)txMin=(EditText)findViewById( R.id.txMin);
		int min = Integer.parseInt(txMin.getText().toString()) + i;
		if(min>=0){
			txMin.setText(String.valueOf(min));
			calcCoste(min);
		}
	}
	
	private void calcCoste(int min) {
		if(tarifaUtil==null)
			try {
				tarifaUtil = TarifaFactory.getTarifaUtil(parking);
			} catch (NoTarifaException e) {
				e.printStackTrace();
				return;
			}
		if(txCoste==null)txCoste=(TextView)findViewById( R.id.txCoste);
		txCoste.setText(tarifaUtil.obtenerCostePorMinutos(min) + "€");
	}
	
	private void setDatosTarifa() {
		if(tarifaUtil==null)
			try {
				tarifaUtil = TarifaFactory.getTarifaUtil(parking);
			} catch (NoTarifaException e) {
				e.printStackTrace();
				return;
			}
		TextView precio1 = (TextView) findViewById(R.id.precio1);
		precio1.setText(tarifaUtil.getPrecioTramo(0) + "€/min");
		TextView precio2 = (TextView) findViewById(R.id.precio2);
		precio2.setText(tarifaUtil.getPrecioTramo(1) + "€/min");
		TextView precio3 = (TextView) findViewById(R.id.precio3);
		precio3.setText(tarifaUtil.getPrecioTramo(2) + "€/min");
		TextView precio4 = (TextView) findViewById(R.id.precio4);
		precio4.setText(tarifaUtil.getPrecioTramo(3) + "€/min");
		TextView precio5 = (TextView) findViewById(R.id.precio5);
		precio5.setText(tarifaUtil.getPrecioTramo(4) + "€/min");
		TextView precio6 = (TextView) findViewById(R.id.precio6);
		precio6.setText(tarifaUtil.getPrecioTramo(5) + "€/min");
		
		
	}




}
