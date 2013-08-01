package net.sareweb.android.dParking.tarifa;

public class Tarifa2Util extends TarifaUtil{
	
	/*Tarifas de : Buen Pastor, Easo, Txofre, P. Cataluña, Atotxa*//*Zona roja*/
	private double precios2[] = {0.044,0.0293333333,0.0296666667,0.0293333333,0.0283333333,0.0291666667,0.0350833333,0.0359583333,0.0208888889,0.0208333333};
	
	public Tarifa2Util() {
		super();
		precios = precios2;
	}

	@Override
	public int getTipoTarifa() {
		return 2;
	}
}
