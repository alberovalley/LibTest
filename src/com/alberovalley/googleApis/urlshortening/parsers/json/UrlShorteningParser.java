package com.alberovalley.googleApis.urlshortening.parsers.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.alberovalley.googleApis.urlshortening.dao.GoogleUrlShorteningDAO;
/**
 * 
 * @author frank @ alberovalley
 * 
 * Clase que analiza el json que recibe y extrae de él la información necesaria
 * para crear el objeto GoogleUrlShorteningDAO
 *
 */
public class UrlShorteningParser {
	/**
	 * 
	 * @param json String con los datos en formato JSON
	 * @return GoogleUrlShorteningDAO
	 * @throws JSONException
	 */
	public static GoogleUrlShorteningDAO getShortUrl(String json) throws JSONException{
		GoogleUrlShorteningDAO dao = new GoogleUrlShorteningDAO();
		if (json.length()>0){
			JSONObject results;
			Log.d("URLSHORTENING", "aquí el json: " + json);
			
			results = new JSONObject(json);
			String longUrl = results.getString("longUrl");
			String shortUrl = results.getString("id");
			
			dao.setLongUrl(longUrl);
			dao.setShortUrl(shortUrl);
		}
		Log.d("URLSHORTENING", "parser, clase del dao: " + dao.getClass().toString());
		return dao;
	}
}
