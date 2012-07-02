package net.sareweb.android.dParking.model;


public class Station {
	
	private int id;
	private String Nombre;
	private String PlazasTotales;
	private String BicisDisponibles;
	private Double Latitud;
	private Double Longitud;
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
	public String getPlazasTotales() {
		return PlazasTotales;
	}
	public void setPlazasTotales(String plazasTotales) {
		PlazasTotales = plazasTotales;
	}
	public String getBicisDisponibles() {
		return BicisDisponibles;
	}
	public void setBicisDisponibles(String bicisDisponibles) {
		BicisDisponibles = bicisDisponibles;
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
	
	
}
