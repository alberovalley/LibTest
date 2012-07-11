package com.alberovalley.googleApis.urlshortening.asyntasks.listeners;

import com.alberovalley.googleApis.response.ResponseEnvelope;
/**
 * 
 * @author frank @ alberovalley
 * Interfaz para implementar el patrón Observer y permitir así la comunicación
 * entre AsyncTask y Activity
 *
 */
public interface UrlShorteningListener {
	public void onUrlShortening(ResponseEnvelope response);
}
