import java.util.*;
import java.text.*;

public class RequestRequisicaoJudicial {

	public static void main (String[] args) {

		String endPoint = args[0];
		String codigoSessao = args[1];
		String enderecoIP = args[2];
		String codigoOrdem = args[3];
		String codigoRequisicao = args[4];
		String numeroSolicitacao = args[5];
		String arquivoRetornoEsperado = args[6];
		String nomeArquivoGerado = args[7];
		String statusProcessamento = args[8];
		String descricaoStatusProcessamento = args[9];

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZ");        
        GregorianCalendar gc = new GregorianCalendar();
        String dataTransacao = sdf.format(gc.getTime());

		try {
			String soapXml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?> <env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\"> <env:Header> <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" env:mustUnderstand=\"false\"> <wsse:UsernameToken wsu:Id=\"UsernameToken-1\"> <wsse:Username>VivoNetPrd</wsse:Username> <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">vivoNetPrd</wsse:Password> </wsse:UsernameToken> </wsse:Security> <ger:cabecalhoVivo xmlns:ger=\"http://www.vivo.com.br/MC/Geral\"> <ger:loginUsuario>sittel</ger:loginUsuario> <ger:canalAtendimento>Vivo</ger:canalAtendimento> <ger:codigoSessao>"+codigoSessao+"</ger:codigoSessao> <ger:nomeAplicacao>VivoProcessum</ger:nomeAplicacao> <ger:token>0</ger:token> <ger:enderecoIP>"+enderecoIP+"</ger:enderecoIP> <ger:codigoFuncionalidade>EncaminharResultadoProcessamentoRequest</ger:codigoFuncionalidade> <ger:dataTransacao>"+dataTransacao+"</ger:dataTransacao> <ger:nomeServico>EncaminharResultadoProcessamento</ger:nomeServico> <ger:versao>1.1</ger:versao> </ger:cabecalhoVivo> </env:Header> <env:Body> <enc:EncaminharResultadoProcessamentoRequest xmlns:enc=\"http://www.vivo.com.br/SN/EncaminharResultadoProcessamento\"> <enc:codigoOrdem>"+codigoOrdem+"</enc:codigoOrdem> <enc:codigoRequisicao>"+codigoRequisicao+"</enc:codigoRequisicao> <enc:numeroSolicitacao>"+numeroSolicitacao+"</enc:numeroSolicitacao> <enc:arquivoRetornoEsperado>"+arquivoRetornoEsperado+"</enc:arquivoRetornoEsperado> <enc:nomeArquivoGerado>"+nomeArquivoGerado+"</enc:nomeArquivoGerado> <enc:statusProcessamento>"+statusProcessamento+"</enc:statusProcessamento> <enc:descricaoStatusProcessamento>"+descricaoStatusProcessamento+"</enc:descricaoStatusProcessamento> </enc:EncaminharResultadoProcessamentoRequest> </env:Body> </env:Envelope> ";

			java.net.URL url = new java.net.URL(endPoint);
			java.net.URLConnection conn = url.openConnection();
			
			// Campos necessario para o request
			conn.setRequestProperty("SOAPAction", endPoint);
            conn.setRequestProperty("Content-Type", "text/xml");
			conn.setDoOutput(true);
			
            // Enviando o request
            //System.out.println("Request :");
            //System.out.println(soapXml);
			
            java.io.OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream());
			wr.write(soapXml);
			wr.flush();
			// Lendo a resposta
			java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
			String line;
			//System.out.println("Response :");
			while ((line = rd.readLine()) != null) { System.out.println(line); }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}



