package br.com.vivo.catalogoPRS.aspect.auditlog;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import br.com.vivo.catalogoPRS.exception.ExecutionServiceException;

/**
 * @author Luiz
 *
 * Classe responsável em realizar o log de auditoria de acesso aos serviços.
 */
@Aspect
public class AuditLogAspect {
	
	private static Logger logger = Logger.getLogger(AuditLogAspect.class);
	
	private StringBuffer logRequest;
	private StringBuffer logResponse;
	 
	/**
	 * @param jp
	 * 
	 * Join Point responsável em monitorar todos os métodos de acesso aos serviços do Catalogo.
	 */
	@Pointcut("execution(public * br.com.vivo.catalogoPRS.controls.*.*(..))")  
	public void monitored(JoinPoint jp){}  

	/**
	 * @param jp
	 * @throws ExecutionServiceException
	 * 
	 * Responsável em obter o parametro de entrada antes do método ser executado.
	 */
	@Before("monitored(jp)")
	public void preExecution(JoinPoint jp) throws ExecutionServiceException {
		
		try {
			if (jp.getArgs() != null && jp.getArgs().length > 0) {
				XmlObject xmlObject = (XmlObject) jp.getArgs()[0];
				logRequest = new StringBuffer("REQUEST: \n " + xmlObject.toString());
			}
		} catch (Exception e) {
			throw new ExecutionServiceException("Erro ao processar o log do serviço.");
		}
	}
	
	/**
	 * @param jp
	 * @param obj
	 * @throws ExecutionServiceException
	 * 
	 * Responsável em obter o retorno do métodos após o mesmo ser executado.
	 */
	@AfterReturning(pointcut = "monitored(jp)", returning = "obj")
	public void posExecution(JoinPoint jp, Object obj) throws ExecutionServiceException {
		try {
			if (obj != null) {
				logger.info(logRequest.toString());
				logResponse = new StringBuffer("RESPONSE: \n " + obj.toString());
				logger.info(logResponse.toString());
			}
		} catch (Exception e) {
			throw new ExecutionServiceException("Erro ao processar o log do serviço.");
		}
	}
	
	/**
	 * @param jp
	 * @param throwable
	 * @throws ExecutionServiceException
	 * 
	 * Responsável em obter os logs de entrada e saída em caso de exceção.
	 */
	@AfterThrowing(pointcut = "monitored(jp)", throwing = "throwable", argNames = "jp, throwable")
	public void posExecutionThrowning(JoinPoint jp, Throwable throwable) throws ExecutionServiceException {
		
		/*if (jp.getArgs() != null && jp.getArgs().length > 0 && throwable != null) {
			ServiceControlException ex = (ServiceControlException) throwable;
			
			XmlObject xmlObject = (XmlObject) jp.getArgs()[0];
			StringBuffer logRequest = new StringBuffer("REQUEST: \n " + xmlObject.toString());
			logger.info(logRequest.toString());
			
			StringBuffer logResponse = null;
			
			if (ex.getSoapFault() != null && ex.getSoapFault().get11Fault() != null) {
				logResponse = new StringBuffer("RESPONSE: \n " + ex.getSoapFault().get11Fault().toString());
				logger.info(logResponse.toString());
			} else {
				logResponse = new StringBuffer("RESPONSE: NÃO FOI POSSÍVEL CAPTURAR O RESPONSE." );
				logger.info(logResponse.toString());
			}
			
			throw new ExecutionServiceException(throwable.getMessage(), throwable, logRequest, logResponse);
		}*/
	}

}
