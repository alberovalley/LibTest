package com.alberovalley.googleApis.geocoding.dao;
/**
 * Representa una "caja" geográfica que encuadra el resultado
 * Dicha caja se define en base a las esquinas noreste y sudoeste
 * @author frank @ alberovalley
 *
 */
public class GoogleGeocodeBoundingBox {
	
	private GoogleGeocodeLocation northeast;
	private GoogleGeocodeLocation southwest;
	
	
	
	public GoogleGeocodeBoundingBox(GoogleGeocodeLocation northeast,
			GoogleGeocodeLocation southwest) {
		super();
		this.northeast = northeast;
		this.southwest = southwest;
	}
	public GoogleGeocodeLocation getNortheast() {
		return northeast;
	}
	public void setNortheast(GoogleGeocodeLocation northeast) {
		this.northeast = northeast;
	}
	public GoogleGeocodeLocation getSouthwest() {
		return southwest;
	}
	public void setSouthwest(GoogleGeocodeLocation southwest) {
		this.southwest = southwest;
	}
	@Override
	public String toString() {
		return "GoogleGeocodeBoundingBox [northeast=" + northeast.toString()
				+ ", southwest=" + southwest.toString() + "]";
	}
	
	
}
