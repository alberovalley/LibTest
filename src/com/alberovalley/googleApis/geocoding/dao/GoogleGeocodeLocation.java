package com.alberovalley.googleApis.geocoding.dao;

/**
 * Representa un punto geográfico con su latitud y longitud
 * @author frank @ alberovalley
 *
 */
public class GoogleGeocodeLocation {
	
	private double latitude;
	private double longitude;
	
	
	
	
	public GoogleGeocodeLocation(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "GoogleGeocodeLocation [latitude=" + latitude + ", longitude="
				+ longitude + "]";
	}
	
	
}
