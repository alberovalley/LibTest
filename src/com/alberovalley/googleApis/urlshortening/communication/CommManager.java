package com.alberovalley.googleApis.urlshortening.communication;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import android.util.Log;

import com.alberovalley.googleApis.response.ResponseEnvelope;
import com.alberovalley.googleApis.urlshortening.communication.requests.GoogleUrlShorteningRequest;
import com.alberovalley.googleApis.urlshortening.dao.GoogleUrlShorteningDAO;
import com.alberovalley.googleApis.urlshortening.parsers.json.UrlShorteningParser;
/**
 * 
 * @author frank @ alberovalley
 * 
 * En esta clase se centralizan todas las llamadas al exterior relacionadas con la 
 * API de acortamiento de url de google.
 * Todas las llamadas se hacen de forma estática.
 * Todos los métodos y funciones de las clases aquí empleadas susceptibles de 
 * provocar excepciones deben tirarlas (THROW) y se gestionan aquí, en el método 
 * correspondiente.
 * Las excepciones se gestionan atrapándolas y dando parte del error ocasionado en
 * los campos correspondientes de la ResponseEnvelope
 */
public class CommManager {
	/**
	 * 
	 * @param longUrl String
	 * @return ResponseEnvelope incluyendo un GoogleUrlShorteningDAO
	 */
	public static ResponseEnvelope shortenUrl(String longUrl){
		ResponseEnvelope resp = new ResponseEnvelope();
		
		try {
			String json = GoogleUrlShorteningRequest.getGoogleUrlshorteningJson(longUrl);
			Log.d("URLSHORTENING", "Json: " + json);
			GoogleUrlShorteningDAO dao = UrlShorteningParser.getShortUrl(json);
			resp.setDao(dao);
		} catch (ClientProtocolException e) {
			Log.e("URLSHORTENING", "CommManager ClientProtocolException " + e.getMessage());
			resp.setStatusCode(ResponseEnvelope.STATUS_KO);
			resp.setErrMessage(e.getMessage());
		} catch (IOException e) {
			Log.e("URLSHORTENING", "CommManager IOException " + e.getMessage());
			resp.setStatusCode(ResponseEnvelope.STATUS_KO);
			resp.setErrMessage(e.getMessage());
		} catch (JSONException e) {
			Log.e("URLSHORTENING", "CommManager JSONException " + e.getMessage());
			resp.setStatusCode(ResponseEnvelope.STATUS_KO);
			resp.setErrMessage(e.getMessage());
		}
		
		return resp;
	}
}
