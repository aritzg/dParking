package net.sareweb.android.dParking.tarifa;

import android.util.Log;
import net.sareweb.android.dParking.exception.NoTarifaException;
import net.sareweb.android.dParking.model.Parking;

public class TarifaFactory {
	
	public static TarifaUtil getTarifaUtil(Parking parking) throws NoTarifaException{
		
		int tarifa = calculaTarifa(parking.getNombre());
		
		return getTarifaUtil(tarifa);
		
	}
	
	
	public static TarifaUtil getTarifaUtil(int tarifa) throws NoTarifaException{
		
		switch (tarifa) {
		case 1:
			Log.d(TAG,"Returning tarifa1");
			return new Tarifa1Util();
		case 2:
			Log.d(TAG,"Returning tarifa2");
			return new Tarifa2Util();
		case 3:
			Log.d(TAG,"Returning tarifa3");
			return new Tarifa3Util();
		default:
			throw new NoTarifaException();
		}
		
	}
	
	private static int calculaTarifa(String nombre){
		if(nombre == null) return 0;
		if(nombre.equals("Boulevard")||nombre.equals("Cervantes")||nombre.equals("Kursaal")||nombre.equals("Okendo")||nombre.equals("San Martin")) return 1;
		if(nombre.equals("Atotxa")||nombre.equals("Buen Pastor")||nombre.equals("Cataluña")||nombre.equals("Easo")||nombre.equals("Txofre")) return 2;
		if(nombre.equals("Antiguo Berri")||nombre.equals("Arco Amara")||nombre.equals("Illumbe")||nombre.equals("Pio XII")||nombre.equals("Zuatzu")) return 3;
		return 0;
	}

	private static final String TAG ="TarifaFactory";		
}
