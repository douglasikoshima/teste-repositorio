/**
 * 
 */
package br.com.vivo.catalogoPRS.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author rjunior2
 *
 */
public class ExecutorServiceHelper {
	
	
	private static final ExecutorService executor = Executors.newFixedThreadPool(1);	
	
	private static final ExecutorServiceHelper INSTANCE = new ExecutorServiceHelper();
	
//	public Integer qtdExec = 1;
	
	protected ExecutorServiceHelper(){}
	
	public static ExecutorServiceHelper getInstance(){
		
		return INSTANCE;
	}
	
	public void execute(Runnable command){
//		qtdExec++;
		executor.execute(command);
	}
	
}
