package com.alberovalley.googleApis.urlshortening.dao;

import com.alberovalley.googleApis.dao.AbstractDAO;
/**
 * 
 * @author frank @ alberovalley
 * Encapsula los datos obtenidos del proceso de acortamiento de url
 * Hereda de AbstractDao para poder emplearse en los ResponseEnvelope
 * 
 *
 */
public class GoogleUrlShorteningDAO extends AbstractDAO {

	private String shortUrl;
	private String longUrl;
	

	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	
	
	
}
