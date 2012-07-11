package com.alberovalley.googleApis.urlshortening.communication.requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * 
 * @author frank @ alberovalley
 * Aquí se inclyen todas las llamadas a la API de acortamiento de URL de Google
 * (dicha API incluye el "desacortamiento" de una url acortada o info de analytics
 * sobre una url dada, aunque no esté aquí implementado)
 */
public class GoogleUrlShorteningRequest {
	
	/**
	 * Se precisa una apikey para que funcione
	 * Puedes obtenerla en https://code.google.com/apis/console
	 * (Precisa tener una cuenta Google)
	 */
	private static final String apikey="<TU_APIKEY_AQUÍ";
	/**
	 * 
	 * @param longUrl String, la url que se desea acortar
	 * @return String (los datos en formato JSON)
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws JSONException
	 */
	public static String getGoogleUrlshorteningJson(String longUrl) throws ClientProtocolException, IOException, JSONException{
		String json ="";
		final String targetUrl = "https://www.googleapis.com/urlshortener/v1/url?key=" + apikey;
		
		HttpClient httpClient = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
	    try {
	        HttpPost request = new HttpPost(targetUrl);
	        JSONObject JSONparam = new JSONObject();
	        /*
	         * A diferencia de otras API que funcionan por GET/POST con variables 
	         * sencillas, la API de acortamiento de URL de Google pide 
	         * un objeto JSON enviado por POST
	         */
	        JSONparam.put("longUrl", longUrl);// indicamos la url a acortar
	        StringEntity params =new StringEntity(JSONparam.toString());
	        /*
	         * añadimos los parámetros y la cabecera a la petición http
	         * y ejecutamos
	         */
	        request.addHeader("content-type", "application/json");
	        request.setEntity(params);
	        HttpResponse response = httpClient.execute(request);
	        
	        StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				/*
				 * Si todo fue ok, montamos la String con los datos en formato JSON
				 */
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

	    } finally {
	    	// cerramos conexiones
	        httpClient.getConnectionManager().shutdown();
	    }
		
		return json;		
	}

}
