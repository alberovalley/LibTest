package com.alberovalley.googleApis.geocoding.dao;

/**
 * 
 * @author frank @ alberovalley 
 * 
 * Encapsula los datos de Geometría obtenidos del proceso de geocoding de Google
 *
 */
public class GoogleGeocodeGeometry {
	/*
	 * indica si el resultado devuelto es un geocódigo preciso del que se tenga información de localización precisa a precisión de calle
	 */
	public static final String LOCATION_TYPE_ROOFTOP = "ROOFTOP";
	/*
	 * indica que el resultado devuelto refleja una aproximación (normalmente en carretera) interpolada entre dos puntos precisos 
	 * (como intersecciones). Los resultados interpolados generalmente se devuelven cuando no hay geocódigos ROOFTOP disponibles para una
	 * dirección de calle
	 */
	public static final String LOCATION_TYPE_RANGE_INTERPOLATED = "RANGE_INTERPOLATED"; 
	/* 
	 * Indica que el resultado devuelto es el centro geométrico de un resultado como una polilínea (por ejemplo, una calle) o un polígono (región).
	 */
	public static final String LOCATION_TYPE_GEOMETRIC_CENTER = "GEOMETRIC_CENTER";
	/*
	 * Indica que el resultado devuelto es aproximado
	 */
	public static final String LOCATION_TYPE_APPROXIMATE = "APPROXIMATE";
	
	
	private GoogleGeocodeLocation location;
	private String locationType=""; // Datos adicionales sobre la localización especificada (constantes)
	private GoogleGeocodeBoundingBox viewport;// Elviewport recomendado para mostrar el resultado devuelto
	/*
	 *  Opcional, el bounding box que puede contener por completo el resultado devuelto. Hay que notar 
	 *  que estos límites pueden no encajar con el viewport recomendado
	 */
	private GoogleGeocodeBoundingBox bounds;
	
	
	public GoogleGeocodeGeometry(GoogleGeocodeLocation location,
			String locationType, GoogleGeocodeBoundingBox viewport) {
		super();
		this.location = location;
		this.locationType = locationType;
		this.viewport = viewport;
		this.bounds = null;
	}
	public GoogleGeocodeGeometry(GoogleGeocodeLocation location,
			String locationType, GoogleGeocodeBoundingBox viewport,
			GoogleGeocodeBoundingBox bounds) {
		super();
		this.location = location;
		this.locationType = locationType;
		this.viewport = viewport;
		this.bounds = bounds;
	}
	public GoogleGeocodeLocation getLocation() {
		return location;
	}
	public void setLocation(GoogleGeocodeLocation location) {
		this.location = location;
	}
	public String getLocationType() {
		return locationType;
	}
	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}
	public GoogleGeocodeBoundingBox getViewport() {
		return viewport;
	}
	public void setViewport(GoogleGeocodeBoundingBox viewport) {
		this.viewport = viewport;
	}
	public GoogleGeocodeBoundingBox getBounds() {
		return bounds;
	}
	public void setBounds(GoogleGeocodeBoundingBox bounds) {
		this.bounds = bounds;
	}
	@Override
	public String toString() {
		return "GoogleGeocodeGeometry [location=" + location.toString()
				+ ", locationType=" + locationType + ", viewport=" + viewport.toString()
				+ bounds!=null?", bounds=" + bounds.toString():"" + "]";
	}
	
	
	
}
