package br.com.vivo.catalogoPRS.exception;


public class CatalogoPRSException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object object;

	public CatalogoPRSException(String message, Throwable e) {
		super(message, e);
	}

	public CatalogoPRSException(String message) {
		super(message);
	}
	
	public CatalogoPRSException(String message, Object object) {
		super(message);
		this.object = object;
	}
	
	public Object getObject() {
		return this.object;
	}
}
