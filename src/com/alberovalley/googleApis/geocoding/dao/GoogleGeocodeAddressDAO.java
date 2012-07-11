package com.alberovalley.googleApis.geocoding.dao;

import com.alberovalley.googleApis.dao.AbstractDAO;

/**
 * 
 * @author frank @ alberovalley
 * Encapsula los datos obtenidos del proceso de geocoding
 * Hereda de AbstractDao para poder emplearse en los ResponseEnvelope
 * 
 *
 */
public class GoogleGeocodeAddressDAO extends AbstractDAO{
	
	private String number;
	private String roadName;
	private String locality;
	private String administrative_area_level_2;
	private String administrative_area_level_1;
	private String country;
	private String postalCode;
	private String formattedAddress;
	
	
	private GoogleGeocodeGeometry geometry;
		
	private String error="";
	private String status="";

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getProvince() {
		return administrative_area_level_2;
	}

	public void setProvince(String province) {
		this.administrative_area_level_2 = province;
	}

	public String getRegion() {
		return administrative_area_level_1;
	}

	public void setRegion(String region) {
		this.administrative_area_level_1 = region;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	
	
	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public GoogleGeocodeGeometry getGeometry() {
		return geometry;
	}

	public void setGeometry(GoogleGeocodeGeometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public String toString() {
		return "GoogleGeocodeAddressComponents [number=" + number
				+ ", roadName=" + roadName + ", locality=" + locality
				+ ", administrative_area_level_2="
				+ administrative_area_level_2
				+ ", administrative_area_level_1="
				+ administrative_area_level_1 + ", country=" + country
				+ ", postalCode=" + postalCode + ", formattedAddress="
				+ formattedAddress + ", geometry=" + geometry.toString() + ", error="
				+ error + ", status=" + status + "]";
	}
	
	
}
