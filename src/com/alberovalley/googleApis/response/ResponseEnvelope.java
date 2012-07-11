package com.alberovalley.googleApis.response;

import com.alberovalley.googleApis.dao.AbstractDAO;
/**
 * 
 * @author frank @ alberovalley
 * 
 * Esta clase sirve de envoltorio para los datos obtenidos así como para la 
 * información del proceso de obtención de dichos datos
 * El estado del proceso puede ser OK o KO (hubo algún problema).
 * También permite añadir un mensaje de error para poder usarlo para trazas o 
 * para decidir qué mostrar por pantalla
 * 
 * El objeto Abstract permite envolver cualquiera de sus subclases
 *
 */
public class ResponseEnvelope {

	protected String errMessage;
	protected int statusCode;
	
	public static int STATUS_OK = 0;
	public static int STATUS_KO = -1;
	public static String NO_ERROR = "";
	
	
	private AbstractDAO dao;
	
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * El constructor por defecto inicializa los datos de estado
	 * Como acaba de empezar, se considera que no hay - aún - problema alguno
	 */
	public ResponseEnvelope() {
		super();
		this.errMessage = NO_ERROR;
		this.statusCode = STATUS_OK;
		this.dao = new AbstractDAO();
	}
	/**
	 * 
	 * @return AbstractDAO
	 * Requiere hacer casting a la subclase de AbstractDAO concreta que se use
	 */
	public AbstractDAO getDao() {
		return dao;
	}
	public void setDao(AbstractDAO dao) {
		this.dao = dao;
	}
	
	
	
}
