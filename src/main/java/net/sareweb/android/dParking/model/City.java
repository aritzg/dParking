package net.sareweb.android.dParking.model;

import java.util.List;

import net.sareweb.android.dParking.exception.NoSuchParkingException;

public class City {
	
	private String name;
	private List<Parking> parkings;
	
	
	public City(String name, List<Parking> parkings){
		this.name = name;
		this.parkings = parkings;
	}
	
	public String getCityName(){
		return name;
	}
	
	public int getParkingAmount(){
		if(parkings!=null) return parkings.size();
		else return 0;
	}
	
	public Parking getParking(int i) throws NoSuchParkingException{
		if(parkings==null) {
			throw new NoSuchParkingException();
		}
		return parkings.get(i);
	}
	
	public List<Parking> getParkings(){
		return parkings;
	}
	
}
