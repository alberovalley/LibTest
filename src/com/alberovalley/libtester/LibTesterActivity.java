package com.alberovalley.libtester;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.alberovalley.googleApis.geocoding.asyntasks.ReverseGeocodingAsynctask;
import com.alberovalley.googleApis.geocoding.asyntasks.listeners.ReverseGeocodingListener;
import com.alberovalley.googleApis.geocoding.dao.GoogleGeocodeAddressDAO;
import com.alberovalley.googleApis.response.ResponseEnvelope;
import com.alberovalley.googleApis.urlshortening.asyntasks.UrlShorteningAsyncTask;
import com.alberovalley.googleApis.urlshortening.asyntasks.listeners.UrlShorteningListener;
import com.alberovalley.googleApis.urlshortening.dao.GoogleUrlShorteningDAO;

public class LibTesterActivity extends Activity implements OnClickListener, ReverseGeocodingListener, UrlShorteningListener{
	
	TextView coordenadas;
	TextView direccion;
	TextView enlacelargo;
	TextView enlacecorto;
	// datos estáticos para la prueba
	private static double latitude = 40.466815;
	private static double longitude  = -3.689623;
	private static String url ="http://www.rtve.es/noticias/20120704/gobierno-ultimaria-otro-ajuste-hasta-30000-millones-para-reducir-deficit/542288";
	
	Button searchBtn = null;
    Intent locatorService = null;
    AlertDialog alertDialog = null;
	
    
	private ProgressDialog pd; 
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        coordenadas = (TextView) findViewById(R.id.coordenadas);
        direccion = (TextView) findViewById(R.id.direccion);
        enlacelargo = (TextView) findViewById(R.id.enlacelargo);
        enlacecorto = (TextView) findViewById(R.id.enlacecorto);
      
        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(this);
 		
        
    }

	@Override
	public void onClick(View v) {
		if(v.getId()!=R.id.searchBtn){
			/*
			 *  cómo sólo el botón es clicable, sólo trazamos un warning si saliera otra cosa. 
			 *  No debería, pero nunca está de mas el "defensive programming"
			 */
			Log.w("libTest","No-botón clicado");
		}
		coordenadas.setText(latitude+","+longitude);
		Log.i("libTest","botón clicado");
		// instanciamos la AsyncTask adecuada
		ReverseGeocodingAsynctask rga = new ReverseGeocodingAsynctask();
		// pasamos la activity como listener de la AsyncTask 
		rga.setReverseGeocodingListener(this);
		// ejecutamos la AsyncTask con los parámetros adecuados
		rga.execute(new Double[]{latitude, longitude});
		// mostramos diálogo de carga
		pd = ProgressDialog.show(this,
        		"geocoding",
        		"Obteniendo dirección textual a partir de las coordenadas",
        		true, false);
		
	}
	// se ejecutará al acabar el proceso de geocoding inverso
	@Override
	public void onReverseGeocoding(ResponseEnvelope response) {
		//como ya ha terminado el proceso, descartamos el diálogo de carga
		pd.dismiss();
		// confirmamos el estado del proceso
		if (response.getStatusCode()== ResponseEnvelope.STATUS_OK){
			// obtenemos el DAO adecuado del ResponseEnvelope cuidando de hacer el casting correspondiente
			GoogleGeocodeAddressDAO dao = (GoogleGeocodeAddressDAO) response.getDao();
			//damos uso a los datos que contiene el dao
			direccion.setText(dao.getFormattedAddress());
		}else{
			// gestionamos el caso de que el proceso terminara con errores
			Log.e("GEOCODING","onReverseGeocoding "+ response.getErrMessage());
		}
		// inicializamos el campo enlacelargo
		enlacelargo.setText(url);
		// instanciamos AsyncTask para el acortamiento de url
		UrlShorteningAsyncTask async = new UrlShorteningAsyncTask();
		// pasamos la activity como listener de la AsyncTask
		async.setUrlShorteningListener(this);
		// ejecutamos la AsyncTask con los parámetros adecuados
		async.execute(new String[]{url});
		// mostramos el diálogo de carga
		pd = ProgressDialog.show(this,
        		"urlshortening",
        		"Obteniendo enlace corto",
        		true, false);
	}
	// se ejecutará al acabar el proceso de acortamiento de URL
	@Override
	public void onUrlShortening(ResponseEnvelope response) {
		// proceso finalizado, descartamos el diálogo de carga
		pd.dismiss();
		// confirmamos el estado del proceso
		if (response.getStatusCode()== ResponseEnvelope.STATUS_OK){
			// obtenemos el DAO adecuado del ResponseEnvelope cuidando de hacer el casting correspondiente
			GoogleUrlShorteningDAO dao = (GoogleUrlShorteningDAO) response.getDao();
			//damos uso a los datos que contiene el dao			
			enlacecorto.setText(dao.getShortUrl());
		}else{
			// gestionamos el caso de que el proceso terminara con errores
			Log.e("URLSHORTENING", response.getErrMessage());
		}
		
	}


	

	
}