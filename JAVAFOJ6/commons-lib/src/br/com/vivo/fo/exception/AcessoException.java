/*
 * Created on 29/05/2004
 */
package br.com.vivo.fo.exception;

/**
 * Classe responsável por controlar exceções lançadas pelo controle de acesso
 * 
 * @modulo  Global
 * @usecase Não Definido
 * @author  Ricardo Miele Bredes
 * @version $Revision: 1.1 $
 **/  
public class AcessoException extends FOException
{ 
   
	private static final long serialVersionUID = -7373242147651946871L;

	/**
	 * 
	 */
	public AcessoException() {
		super();

	}

	/**
	 * @param message
	 */
	public AcessoException(String message) {
		super(message);
		
	}

	/**
	 * @param cause
	 */
	public AcessoException(Throwable cause) {
		super(cause);
		
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AcessoException(String message, Throwable cause) {
		super(message, cause);
		
	} 
} 
