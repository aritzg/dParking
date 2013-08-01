package net.sareweb.android.dParking.tarifa;

public class Tarifa1Util extends TarifaUtil{
	
	/*Tarifas de : Okendo, Boulevard, Cervantes, Kursaal, San Martín*//*Zona especial*/
	double precios1[] = {0.0526666667,0.0353333333,0.0353333333,0.0356666667,0.0336666667,0.035,0.0409166667,0.038375,0.013,0.0129166667};
	
	public Tarifa1Util() {
		super();
		precios = precios1;
	}

	@Override
	public int getTipoTarifa() {
		return 1;
	}
}
