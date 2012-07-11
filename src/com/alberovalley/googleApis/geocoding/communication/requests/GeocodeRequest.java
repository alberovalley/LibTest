package com.alberovalley.googleApis.geocoding.communication.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;
/**
 * 
 * @author frank @ alberovalley
 * Aquí se inclyen todas las llamadas a la API de geocoding de Google
 * (dicha API incluye mas cosas que el convertir un par de coordenadas a dirección
 * aunque no esté aquí implementado)
 */
public class GeocodeRequest {
	/**
	 * 
	 * @param lat double, latitud
	 * @param lng double, longitud 
	 * @return String los datos en formato JSON
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getGeocodeFromCoordinates(double lat, double lng) throws ClientProtocolException, IOException{	
		String url = makeUrlFromCoordinates(lat, lng);
		return getGeocodeFromURL(url);
	}
	/**
	 * 
	 * @param textAddress String la dirección textual
	 * @return String los datos en formato JSON
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String getGeocodeFromTextAddress(String textAddress) throws ClientProtocolException, IOException{
		String url = makeUrlFromTextAddress(textAddress);
		return getGeocodeFromURL(url);
	}
	/**
	 * 
	 * @param url String con la url formada con las variables
	 * @return String los datos en formato JSON
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private static String getGeocodeFromURL(String url) throws ClientProtocolException, IOException{
		String json ="";
		
		StringBuilder builder = new StringBuilder();

		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		Log.d("ggeocoding", "la url:  " +url);
		HttpResponse response;
		response = client.execute(httpGet);

		StatusLine statusLine = response.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		Log.d("ggeocoding", "status code  " + statusCode);
		if (statusCode == 200) {
			HttpEntity entity = response.getEntity();
			InputStream content = entity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(content));
			String line;
			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}
			json = builder.toString();
		}
		
		
		return json;
	}
	
	
	
	/**
	 * 
	 * @param lat double latitud
	 * @param lng double longitud
	 * @return String la url para pedir los datos en función a las coordenadas
	 */
	private  static String makeUrlFromCoordinates(double lat, double lng){
		return "http://maps.googleapis.com/maps/api/geocode/json?sensor=true&latlng="+lat+","+lng+"&language="
					+ Locale.getDefault().getLanguage();
	}
	/**
	 * 
	 * @param textAddress String la dirección a consultar
	 * @return String la url para pedir los datos en función a la direcciónreturn
	 */
	private static String makeUrlFromTextAddress(String textAddress){
		return "http://maps.googleapis.com/maps/api/geocode/json?address="+ textAddress + "&sensor=true&language="
					+Locale.getDefault().getLanguage();
	}
}
