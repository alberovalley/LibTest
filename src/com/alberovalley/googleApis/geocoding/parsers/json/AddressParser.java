package com.alberovalley.googleApis.geocoding.parsers.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alberovalley.googleApis.geocoding.dao.GoogleGeocodeAddressDAO;
import com.alberovalley.googleApis.geocoding.dao.GoogleGeocodeBoundingBox;
import com.alberovalley.googleApis.geocoding.dao.GoogleGeocodeGeometry;
import com.alberovalley.googleApis.geocoding.dao.GoogleGeocodeLocation;
/**
 * 
 * @author frank @ alberovalley
 * 
 * Clase que analiza el json que recibe y extrae de él la información necesaria
 * para crear el objeto GoogleGeocodeAddressDAO
 *
 */
public class AddressParser {
	
	/**
	 * 
	 * @param json String con los datos en formato JSON
	 * @return GoogleGeocodeAddressDAO
	 * @throws JSONException
	 */
	public static GoogleGeocodeAddressDAO getAddressComponents(String json) throws JSONException{
		GoogleGeocodeAddressDAO addressComponents = new GoogleGeocodeAddressDAO();
		if (json.length()>0){
			JSONObject results;
			Log.d("ggeocoding", "aquí el json: " + json);

			results = new JSONObject(json);
			// fetch the outer, containing object
			JSONArray resultsArray = results.getJSONArray("results");
			// get the response status
			String status = results.getString("status");
			// get the object address components. It contains the array which properly houses said components
			JSONObject jAddressComponents = resultsArray.getJSONObject(0);
			// get the array full of address components
			JSONArray addressComponentsArray = jAddressComponents.getJSONArray("address_components");
			// get the different address components
			JSONObject streetNumber  = addressComponentsArray.getJSONObject(0);
			JSONObject routeName  = addressComponentsArray.getJSONObject(1);
			JSONObject locality  = addressComponentsArray.getJSONObject(2);
			JSONObject province  = addressComponentsArray.getJSONObject(3);
			JSONObject region  = addressComponentsArray.getJSONObject(4);
			JSONObject country  = addressComponentsArray.getJSONObject(5);
			JSONObject postalCode  = addressComponentsArray.getJSONObject(6);

			// get the formatted address
			String formattedAddress = resultsArray.getJSONObject(0).getString("formatted_address");
			
			JSONObject geometry = jAddressComponents.getJSONObject("geometry");
			// get the location type
			String locationType = geometry.getString("location_type");
			// get the location object
			JSONObject location = geometry.getJSONObject("location");		
			// get the viewport
			JSONObject jViewport= geometry.getJSONObject("viewport");
			JSONObject locationVpNE = jViewport.getJSONObject("northeast");
			JSONObject locationVpSW = jViewport.getJSONObject("southwest");
			// get the bounds object if any
			JSONObject jBounds= geometry.optJSONObject("bounds");//this method returns null if it doesn't find such object
			JSONObject locationBoundNE = null;
			JSONObject locationBoundSW = null;
			if(jBounds!=null){ // if there was a bounds object, get the corners
				locationBoundNE = jBounds.getJSONObject("northeast");
				locationBoundSW = jBounds.getJSONObject("southwest");
			}
			// get the status of the response
			addressComponents.setStatus(status);
			// set the different components of the address
			addressComponents.setNumber(streetNumber.getString("long_name"));
			addressComponents.setRoadName(routeName.getString("long_name"));
			addressComponents.setLocality(locality.getString("long_name"));
			addressComponents.setProvince(province.getString("long_name"));
			addressComponents.setRegion(region.getString("long_name"));
			addressComponents.setCountry(country.getString("long_name"));
			addressComponents.setPostalCode(postalCode.getString("long_name"));
			// set the formatted address
			addressComponents.setFormattedAddress(formattedAddress);
			// prepare the viewport object
			GoogleGeocodeLocation loc = new GoogleGeocodeLocation(location.getDouble("lat"), location.getDouble("lng"));
			GoogleGeocodeLocation vpNortheast = new GoogleGeocodeLocation(locationVpNE.getDouble("lat"), locationVpNE.getDouble("lng"));
			GoogleGeocodeLocation vpSouthwest = new GoogleGeocodeLocation(locationVpSW.getDouble("lat"), locationVpSW.getDouble("lng"));
			GoogleGeocodeBoundingBox viewport = new GoogleGeocodeBoundingBox(vpNortheast, vpSouthwest);
			// initialize the Geometry object with all mandatory data
			GoogleGeocodeGeometry addressGeometry = new GoogleGeocodeGeometry(loc, locationType, viewport);
			
			if (jBounds!=null){// if the json contained bounds, create the object and insert it into the geometry
				GoogleGeocodeLocation bNortheast = new GoogleGeocodeLocation(locationBoundNE.getDouble("lat"), locationBoundNE.getDouble("lng"));
				GoogleGeocodeLocation bSouthwest = new GoogleGeocodeLocation(locationBoundSW.getDouble("lat"), locationBoundSW.getDouble("lng"));
				GoogleGeocodeBoundingBox bounds = new GoogleGeocodeBoundingBox(vpNortheast, vpSouthwest);
				addressGeometry.setBounds(bounds);
			}
			// with the Geometry object initialized, insert it into addressComponents				
			addressComponents.setGeometry(addressGeometry);

			
		}else{
			// controlar que el json sea 0? debería estar controlado por las diferentes excepciones
		}
	return addressComponents;
}

}
