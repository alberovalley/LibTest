package com.alberovalley.googleApis.geocoding.communication;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.util.Log;

import com.alberovalley.googleApis.geocoding.communication.requests.GeocodeRequest;
import com.alberovalley.googleApis.geocoding.dao.GoogleGeocodeAddressDAO;
import com.alberovalley.googleApis.geocoding.parsers.json.AddressParser;
import com.alberovalley.googleApis.response.ResponseEnvelope;
/**
 * 
 * @author frank @ alberovalley
 * En esta clase se centralizan todas las llamadas al exterior relacionadas con la 
 * API de geocoding de google.
 * Todas las llamadas se hacen de forma estática.
 * Todos los métodos y funciones de las clases aquí empleadas susceptibles de 
 * provocar excepciones deben tirarlas (THROW) y se gestionan aquí, en el método 
 * correspondiente.
 * Las excepciones se gestionan atrapándolas y dando parte del error ocasionado en
 * los campos correspondientes de la ResponseEnvelope
 *
 */
public class CommManager {
	/**
	 * 
	 * @param lat double, latitud
	 * @param lng double, longitud
	 * @return ResponseEnvelope incluyendo un GoogleGeocodeAddressDAO
	 */
	public static ResponseEnvelope getAddress(double lat, double lng) {
		ResponseEnvelope resp = new ResponseEnvelope();
		
		String json = "";
		try {
			json = GeocodeRequest.getGeocodeFromCoordinates(lat, lng);
		} catch (ClientProtocolException e) {
			resp.setStatusCode(ResponseEnvelope.STATUS_KO);
			resp.setErrMessage("CommManager ClientProtocolException " + e.getMessage());
			Log.e("GEOCODING", "CommManager ClientProtocolException: " + e.getMessage());
		} catch (IOException e) {
			resp.setStatusCode(ResponseEnvelope.STATUS_KO);
			resp.setErrMessage("CommManager IOException " + e.getMessage());
			Log.e("GEOCODING", "CommManager IOException:" + e.getMessage());
		}
		Log.d("GEOCODING", "el json antes de mandarlo al parser  " + json);
		GoogleGeocodeAddressDAO dao = new GoogleGeocodeAddressDAO();
		try {
			dao = AddressParser.getAddressComponents(json);
		} catch (JSONException e) {
			resp.setStatusCode(ResponseEnvelope.STATUS_KO);
			resp.setErrMessage("CommManager JSONException " + e.getMessage());
			Log.e("GEOCODING", "CommManager JSONException:" + e.getMessage());
		}

		resp.setDao(dao);
		return resp;
	}

	// TODO método para obtener la información recibida de una dirección textual
	
}
