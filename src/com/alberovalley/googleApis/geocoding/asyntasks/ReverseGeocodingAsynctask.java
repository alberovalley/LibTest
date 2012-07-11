package com.alberovalley.googleApis.geocoding.asyntasks;
import android.os.AsyncTask;
import android.util.Log;

import com.alberovalley.googleApis.geocoding.asyntasks.listeners.ReverseGeocodingListener;
import com.alberovalley.googleApis.geocoding.communication.CommManager;
import com.alberovalley.googleApis.response.ResponseEnvelope;

/**
 * 
 * @author frank @ alberovalley
 * 
 * Esta AsyncTask emplea el patrón Observer con la interfaz ReverseGeocodingListener
 * Esta AsyncTask se encarga de, recibiendo un par de doubles (coordenadas 
 * latitud y longitud) obtener, vía una API de geocoding inverso de Google,
 * la dirección textual (ej: Plaza del Sol nº3, Madrid, España)
 * "Devuelve" a la Activity que lo llama (a través del método onReverseGeocoding
 * del interfaz del observador) un ResponseEnvelope 
 *
 */

public class ReverseGeocodingAsynctask  extends AsyncTask <Double, Void, ResponseEnvelope>{
	/**
	 * El objeto listener permite enlazar la AsyncTask a la Activity que lo llama
	 */
	private ReverseGeocodingListener listener;
	
	public void setReverseGeocodingListener(ReverseGeocodingListener listener){
		this.listener = listener;
	}

	/**
	 * Se esperan sólo dos valores Double, uno para cada coordenada
	 * Si se recibe un número diferente de parámetros, se marca error y 
	 * se devuelve el ResponseEnvelope con dicha información
	 */
	@Override
	protected ResponseEnvelope doInBackground(Double... coordinates) {
		ResponseEnvelope response = new ResponseEnvelope();
		if (coordinates.length==2){
			double lat = coordinates[0];
			double lng = coordinates[1];
			response = CommManager.getAddress(lat, lng);
		}else{
			response.setStatusCode(ResponseEnvelope.STATUS_KO);
			response.setErrMessage("Número de coordenadas erróneo: " + coordinates.length);
			Log.e("GEOCODING","Número de coordenadas erróneo: " + coordinates.length);
		}
		
		return response;
	}

	/**
	 * Una vez se ha realizado en Background el proceso de geocoding inverso
	 * la información envuelta en un ResponseEnvelope se envía a la Activity llamante
	 * a través del método de la interfaz ReverseGeocodingListener
	 */
	@Override
	protected void onPostExecute(ResponseEnvelope result) {
		super.onPostExecute(result);
		listener.onReverseGeocoding(result);
	}
	
	
	
	

}
