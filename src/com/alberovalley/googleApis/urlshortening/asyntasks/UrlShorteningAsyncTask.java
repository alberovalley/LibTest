package com.alberovalley.googleApis.urlshortening.asyntasks;

import android.os.AsyncTask;
import android.util.Log;

import com.alberovalley.googleApis.response.ResponseEnvelope;
import com.alberovalley.googleApis.urlshortening.asyntasks.listeners.UrlShorteningListener;
import com.alberovalley.googleApis.urlshortening.communication.CommManager;
/**
 * 
 * @author frank @ alberovalley
 * 
 * Esta AsyncTask emplea el patrón Observer con la interfaz UrlShorteningListener
 * Esta AsyncTask se encarga de, recibiendo una String(url a acortar) 
 * obtener, vía una API de acortamiento de url de Google,
 * la url corta
 * "Devuelve" a la Activity que lo llama (a través del método onUrlShortening
 * del interfaz del observador) un ResponseEnvelope 
 *
 */
public class UrlShorteningAsyncTask extends AsyncTask <String, Void, ResponseEnvelope>{
	/**
	 * El objeto listener permite enlazar la AsyncTask a la Activity que lo llama
	 */
	UrlShorteningListener listener;
	
	public void setUrlShorteningListener(UrlShorteningListener listen){
		this.listener = listen;
	}
	/**
	 * Se espera una única url. 
	 * Si se recibe un número diferente de parámetros, se marca error y 
	 * se devuelve el ResponseEnvelope con dicha información
	 */
	@Override
	protected ResponseEnvelope doInBackground(String... urls) {
		String longUrl="";
		ResponseEnvelope response = new ResponseEnvelope();
		if (urls.length==1){
			longUrl = urls[0];
				response = CommManager.shortenUrl(longUrl);
		}else{
			response.setStatusCode(ResponseEnvelope.STATUS_KO);
			response.setErrMessage("Asyntask: nº erróneo de urls");
			Log.e("URLSHORTENING", "Asyntask: nº erróneo de urls");
		}
		return response;
	}
	/**
	 * Una vez se ha realizado en Background el proceso de acortamiento de url
	 * la información envuelta en un ResponseEnvelope se envía a la Activity llamante
	 * a través del método de la interfaz UrlShorteningListener
	 */
	@Override
	protected void onPostExecute(ResponseEnvelope result) {
		super.onPostExecute(result);
		listener.onUrlShortening(result);
	}
	
	

}
