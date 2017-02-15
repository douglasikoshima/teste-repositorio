/**
 * 
 */
package br.com.vivo.catalogoPRS.thread;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

import br.com.vivo.catalogoPRS.dto.ArquivoImportacaoMatrizOfertaDTO;
import br.com.vivo.catalogoPRS.util.JMSUtil;

/**
 * @author rjunior2
 *
 */
public class WorkerSendFileJMSThread implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
/*	private ArquivoImportacaoMatrizOfertaDTO arquivoImportacaoMatrizOfertaDTO;
//	private MatrizOfertaServiceControl matrizOfertaServiceControl;
	
	public WorkerSendFileJMSThread(ArquivoImportacaoMatrizOfertaDTO arquivoImportacaoMatrizOfertaDTO){
		this.arquivoImportacaoMatrizOfertaDTO = arquivoImportacaoMatrizOfertaDTO;
//		this.matrizOfertaServiceControl = arquivoImportacaoMatrizOfertaDTO.getMatrizOfertaServiceControl();
	}
	
//	private Integer qtd = 2;
	

	public void run() {
		
//		if (arquivoImportacaoMatrizOfertaDTO.getFlagMockQtdExecutar() <= qtd){
		
			Context ctx = arquivoImportacaoMatrizOfertaDTO.getInitialContext();
			QueueConnectionFactory qconFactory;
			QueueConnection qcon;
			QueueSession qsession;
			QueueSender qsender;
			Queue queue;
			BytesMessage fileImportacao;
			
			try{
			
				qconFactory = (QueueConnectionFactory) ctx.lookup(JMSUtil.JMS_FACTORY);
				qcon = qconFactory.createQueueConnection();
				qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
				queue = (Queue) ctx.lookup(JMSUtil.QUEUE);
				qsender = qsession.createSender(queue);
			
				
				fileImportacao  = qsession.createBytesMessage();
				fileImportacao.setJMSCorrelationID( String.valueOf(((int) 1 + (Math.random() * 1000))));	
				fileImportacao.setJMSReplyTo(queue);
				
				qcon.start();
		
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ObjectOutputStream objOs = new ObjectOutputStream(os);
				objOs.writeObject(arquivoImportacaoMatrizOfertaDTO.getArquivoBinario());
				objOs.flush();
				objOs.close();
				fileImportacao.writeObject(os.toByteArray());
				fileImportacao.setLongProperty("identificadorArquivo", arquivoImportacaoMatrizOfertaDTO.getIdentificador().longValue());
				fileImportacao.setStringProperty("cdCanalVendas", arquivoImportacaoMatrizOfertaDTO.getCanalVendas());
				fileImportacao.setStringProperty("tipoMensagem", arquivoImportacaoMatrizOfertaDTO.getTipoMensagem());
		
				qsender.send(fileImportacao);
				
				qsender.close();
				qsession.close();
				qcon.close();
				
			}catch(JMSException e){
				e.printStackTrace();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
//		}else{
//			try {
//				qtd = 3;
//				Thread.sleep( 5000 * 60  );
//				qtd = 3;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} 
//		}
	}*/
}
