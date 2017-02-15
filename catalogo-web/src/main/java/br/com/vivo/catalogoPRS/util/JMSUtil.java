package br.com.vivo.catalogoPRS.util;

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
import br.com.vivo.catalogoPRS.thread.WorkerSendFileJMSThread;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortType;
import br.com.vivo.catalogoPRS.ws.catalogoMatrizOferta.sn.MatrizOfertaPortTypeProxy;

public class JMSUtil {

	// Defines the JMS connection factory.
	public final static String JMS_FACTORY = "vivo_catalogo_wlp_connectionfactory";
	
	//Defines the queue.
	public final static String QUEUE = "vivo_catalogo_matrizoferta_wlp_distributed_queue";
	
	public static void enviarArquivo(byte[] arquivo, Long identificador, String canalVendas, String tipoMensagem, Context initialContext) throws JMSException, NamingException, IOException {
		Context ctx = initialContext;
		QueueConnectionFactory qconFactory;
		QueueConnection qcon;
		QueueSession qsession;
		QueueSender qsender;
		Queue queue;
		BytesMessage fileImportacao;
		
		//181114 - Melhoria para Convers�o do arquivo ANSI para UTF-8
		String arquivoUTF = new String(arquivo, "ISO-8859-1");  
		byte arquivoFileData[] = arquivoUTF.getBytes("UTF-8");		
		
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
		qcon = qconFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(QUEUE);
		qsender = qsession.createSender(queue);
		
		fileImportacao  = qsession.createBytesMessage();
		fileImportacao.setJMSCorrelationID( String.valueOf(((int) 1 + (Math.random() * 1000))));	
		fileImportacao.setJMSReplyTo(queue);
		
		qcon.start();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream objOs = new ObjectOutputStream(os);
		objOs.writeObject(arquivoFileData);
		objOs.flush();
		objOs.close();
		fileImportacao.writeObject(os.toByteArray());
		fileImportacao.setLongProperty("identificadorArquivo", identificador.longValue());
		fileImportacao.setStringProperty("cdCanalVendas", canalVendas);
		fileImportacao.setStringProperty("tipoMensagem", tipoMensagem);

		qsender.send(fileImportacao);
		
		qsender.close();
		qsession.close();
		qcon.close();
	}
	
	public static void enviarArquivo(byte[] arquivo, String nomeArquivo, String usuarioImportacao, String canalVendas, String tipoMensagem, Context initialContext, Integer idCanalAtendimento) throws JMSException, NamingException, IOException {
		Context ctx = initialContext;
		QueueConnectionFactory qconFactory;
		QueueConnection qcon;
		QueueSession qsession;
		QueueSender qsender;
		Queue queue;
		BytesMessage fileImportacao;
		
		//181114 - Melhoria para Convers�o do arquivo ANSI para UTF-8
		String arquivoUTF = new String(arquivo, "ISO-8859-1");  
		byte arquivoFileData[] = arquivoUTF.getBytes("UTF-8");
		
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
		qcon = qconFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(QUEUE);
		qsender = qsession.createSender(queue);
		
		fileImportacao  = qsession.createBytesMessage();
		fileImportacao.setJMSCorrelationID( String.valueOf(((int) 1 + (Math.random() * 1000))));	
		fileImportacao.setJMSReplyTo(queue);
		
		qcon.start();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream objOs = new ObjectOutputStream(os);
		objOs.writeObject(arquivoFileData);
		objOs.flush();
		objOs.close();
		fileImportacao.writeObject(os.toByteArray());
		fileImportacao.setStringProperty("nomeArquivo", nomeArquivo);
		fileImportacao.setStringProperty("usuarioImportacao", usuarioImportacao);
		fileImportacao.setStringProperty("cdCanalVendas", canalVendas);
		fileImportacao.setStringProperty("tipoMensagem", tipoMensagem);
		fileImportacao.setStringProperty("idCanalAtendimento", idCanalAtendimento.toString());

		qsender.send(fileImportacao);
		
		qsender.close();
		qsession.close();
		qcon.close();
		
	}

	
	/*public static void enviarArquivo(byte[] arquivo, Long identificador, String canalVendas, String tipoMensagem, Context initialContext,MatrizOfertaPortType matrizOfertaPortType) throws JMSException, NamingException, IOException {
		ArquivoImportacaoMatrizOfertaDTO matrizOfertaDTO = new ArquivoImportacaoMatrizOfertaDTO(arquivo, identificador, canalVendas, tipoMensagem, initialContext);
		ExecutorServiceHelper.getInstance().execute(new WorkerSendFileJMSThread(matrizOfertaDTO));
	}*/

}
