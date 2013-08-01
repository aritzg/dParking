package net.sareweb.android.dParking.tarifa;

public class Tarifa3Util extends TarifaUtil{
	
	/*Tarifas de : Pío XII, Arco, Illumbe, Antiguo Berri, Zuatzu*//*Zona verde*/
	private double precios3[] = {0.0393333333,0.0266666667,0.0266666667,0.0266666667,0.0253333333,0.0255,0.0255,0.0252916667,0.0249444444,0.025};
	
	public Tarifa3Util() {
		super();
		precios = precios3;
	}

	@Override
	public int getTipoTarifa() {
		return 3;
	}
}
