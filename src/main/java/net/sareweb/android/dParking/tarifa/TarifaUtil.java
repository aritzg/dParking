package net.sareweb.android.dParking.tarifa;

import android.util.Log;

public abstract class TarifaUtil {

	protected double precios[] = {0,0,0,0,0,0,0,0,0,0};
	
	public double getPrecioTramo(int i){
		return precios[i];
	}
	
	public double obtenerCostePorMinutos(long minutos){
		long minutosRestantes = minutos;
		int tramo=1;
		double suma = 0.00;
		Log.d(TAG,"Minutos: " + minutos);

		while(tramo<11 && minutosRestantes>0){
			Log.d(TAG,"Minutos sin contabilizar: " + minutosRestantes);
			if(minutosRestantes< finTramo[tramo] - finTramo[tramo-1]){
				Log.d(TAG,"Se les aplica: " + precios[tramo-1]);
				suma = suma + minutosRestantes*precios[tramo-1];
				Log.d(TAG,"Suma final: " + suma);
				return roundTwoDecimals(suma);
			}
			else{
				minutosRestantes=minutosRestantes - (finTramo[tramo] - finTramo[tramo-1]);
				suma = suma + (finTramo[tramo] - finTramo[tramo-1])*precios[tramo-1];
				tramo=tramo+1;
			}
		}
		
		return roundTwoDecimals(suma);
	}
	
	double roundTwoDecimals(double d) {
		return (double)Math.round(d * 100) / 100;
	}
	
	public abstract int getTipoTarifa();
	
	private int finTramo[] = {0,15,30,60,90,120,180,300,540,720,840};
	private final String TAG ="TarifaUtil";		
}
