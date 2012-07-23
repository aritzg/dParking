package net.sareweb.android.dParking.tarifa;

public class Tarifa2Util extends TarifaUtil{
	
	/*Tarifas de : Buen Pastor, Easo, Txofre, P. Cataluña, Atotxa*/
	private double precios2[] = {0.040400,0.027318,0.025969,0.026932,0.037873,0.019289};
	
	public Tarifa2Util() {
		super();
		precios = precios2;
	}

	@Override
	public int getTipoTarifa() {
		return 2;
	}
}
