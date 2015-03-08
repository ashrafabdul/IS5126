package com.rtsing.www.dto;

public class HeatMapDataPoint {

	String lat;
	String lng;
	int checkIns;
	
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public int getCount() {
		return checkIns;
	}
	public void setCount(int count) {
		this.checkIns = count;
	}
	
	
}
