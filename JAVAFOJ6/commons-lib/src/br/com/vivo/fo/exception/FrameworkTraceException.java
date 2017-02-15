package br.com.vivo.fo.exception; 

/**
 * Encapsula o tratamento de outra exce��o<p/>
 * Quando o framework encontra uma <code>FrameworkTraceException</code>, isto significa a que
 * a exce��o original j� foi capturada e devidamente empacotada. Esta abordagem permite ao framework
 * distinguir entre as exce��es propagadas pela aplica��o e as exce��es que eventualmente tenham sido
 * geradas pelo pr�prio framework.
 * 
 * @author Fernando Gomes
 */
public class FrameworkTraceException extends Exception { 

	private static final long serialVersionUID = -2916754965801331222L;


	/**
     * 
	 * @param cause
	 */
	public FrameworkTraceException(Throwable cause) {
		super("Offending stack trace follows:", cause);
	}
    

    /**
     * Analisa uma <code>Exception</code> e procura pela causa prim�ria, propagada pela aplica��o.
     * Despreza as instancias de <code>FrameworkTraceException</code> que estiverem encadeadas.
     * 
     * @param  exception <code>Exception</code> a ser analisada
     * @return <code>Throwable</code> 
     */
    static public Throwable findCause(Throwable t) {
        Throwable tmp = t;
        while ((tmp != null) && (tmp instanceof FrameworkTraceException)) {
            tmp = tmp.getCause();
        }
        return tmp;
    }

} 
