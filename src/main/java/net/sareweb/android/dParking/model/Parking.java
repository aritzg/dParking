package net.sareweb.android.dParking.model;


public class Parking {
	
	private int id;
	private String Nombre="";
	private Double Latitud;
	private Double Longitud;
	private String Tipo;
	private String PlazasTotales;
	private String Datos;


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return Nombre;
	}



	public void setNombre(String nombre) {
		Nombre = nombre;
	}



	public Double getLatitud() {
		return Latitud;
	}



	public void setLatitud(Double latitud) {
		Latitud = latitud;
	}



	public Double getLongitud() {
		return Longitud;
	}



	public void setLongitud(Double longitud) {
		Longitud = longitud;
	}



	public String getTipo() {
		return Tipo;
	}



	public void setTipo(String tipo) {
		Tipo = tipo;
	}



	public String getPlazasTotales() {
		return PlazasTotales;
	}



	public void setPlazasTotales(String plazasTotales) {
		PlazasTotales = plazasTotales;
	}



	public String getDatos() {
		return Datos;
	}



	public void setDatos(String datos) {
		Datos = datos;
	}



	public String getPlazasLibres() {
		String item1 = "/strong>";
		if(Datos==null) return "??";
		int pos1 = Datos.indexOf(item1);
		int pos2 = Datos.indexOf("<",pos1);
		if(pos1==-1 || pos2==-1) return "??";
		String libres = Datos.substring(pos1+item1.length()+1,pos2);
		return libres;
	}

}
