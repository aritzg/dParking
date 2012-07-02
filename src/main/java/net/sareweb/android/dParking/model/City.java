package net.sareweb.android.dParking.model;

import java.util.List;

import net.sareweb.android.dParking.exception.NoSuchStationException;

public class City {
	
	private String name;
	private List<Station> stations;
	
	public City(String name, List<Station> stations){
		this.name = name;
		this.stations = stations;
	}
	
	public String getCityName(){
		return name;
	}
	
	public int getStastionAmount(){
		if(stations!=null) return stations.size();
		else return 0;
	}
	
	public Station getStation(int i) throws NoSuchStationException{
		if(stations==null) {
			throw new NoSuchStationException();
		}
		return stations.get(i);
	}
	
	public List<Station> getStations(){
		return stations;
	}
	
}
