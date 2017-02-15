package cliente.TelaInicial.DetalheFatura; 

import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO;
import java.io.ByteArrayInputStream;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpRecoverableException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Classe utilizada para obter as imagens de Boleto, Conta Resumida e Conta Detalhada
 */
public class ImagemConta {

    /**
     * @common:control
     */
    private br.com.vivo.fo.ctrls.cliente.telaInicial.detalheFatura.DetalheFaturaFacade detalheFaturaFacade;
    
    public static final String BOLETO           = "IMPBOLETO";
    public static final String CONTA_RESUMIDA   = "CONTARES";
    public static final String CONTA_DETALHADA  = "CONTADET";

    private static String SERVICE_ROUTER_URL = "";

    public String gerarImagem(String tipoConta, String ddd, String celular, String idConta, String dataConta, ApoioParametroVO param) throws HttpRecoverableException, Exception {
                
        SERVICE_ROUTER_URL = param.getDsValorParametro();
        
        // Cria xml de entrada
        String input = new String();
        String dsOrigem = "VOL";
        String dsSenha = "123456";
        String dsPlataforma = "0";
        String dsCanal = "VOL";
        String dsUsuario = "VOL";

        input += "<?xml version='1.0' encoding='ISO-8859-1'?>";
        input += "<ARG>";
        input += "<REG>";
        input += "<COD_AREA>" + ddd + "</COD_AREA>";
        input += "<NUM_LINE>" + celular  + "</NUM_LINE>";
        input += "<PLATAFORMA>" + dsPlataforma + "</PLATAFORMA>";
        input += "<SERVICO>CONTA</SERVICO>";
        input += "<OPERACAO>" + tipoConta + "</OPERACAO>";
        input += "<CANAL>" + dsCanal + "</CANAL>";
        input += "<USUARIO>" + dsUsuario + "</USUARIO>";
        input += "<SENHA>" + dsSenha + "</SENHA>";
        input += "<ORIGEM>" + dsOrigem + "</ORIGEM>";
        input += "</REG>";
        input += "<DADOS>";
        input += "<CONTA>" + idConta + "</CONTA>";
        input += "<DATA></DATA>";
        input += "</DADOS>";
        input += "</ARG>";

        // Envia o xml de entrada por http
        String url = SERVICE_ROUTER_URL + "?ARG=" + input;
        HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		int statusCode = -1;
		// Tenta, por 3 vezes, receber um retorno
		for (int attempt = 0; statusCode == -1 && attempt < 3; attempt++) {            
            statusCode = client.executeMethod(method);            
		}
		// Verifica se ocorreu algum problema
		if (statusCode == -1) {
			throw new Exception("Erro na chamada a URL " + url);
		}
		// Recebe a pagina correspondente a url chamada
		byte[] responseBody = method.getResponseBody();
        method.releaseConnection();
        String response = new String(responseBody);

        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(responseBody); 
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
        // Procura as datas
        NodeList dates = document.getElementsByTagName("billDt");
        if (dates.getLength() <= 0 )
            throw new Exception("Não existem contas para essa linha");
        String date = dates.item(dates.getLength()-1).getAttributes().getNamedItem("cycleEndDt").getFirstChild().getNodeValue();

        input = "<?xml version='1.0' encoding='ISO-8859-1'?>";
        input += "<ARG>";
        input += "<REG>";
        input += "<COD_AREA>" + ddd + "</COD_AREA>";
        input += "<NUM_LINE>" + celular + "</NUM_LINE>";
        input += "<PLATAFORMA>" + dsPlataforma + "</PLATAFORMA>";
        input += "<SERVICO>CONTA</SERVICO>";
        input += "<OPERACAO>" + tipoConta + "</OPERACAO>";
        input += "<CANAL>" + dsCanal + "</CANAL>";
        input += "<USUARIO>" + dsUsuario + "</USUARIO>";
        input += "<SENHA>" + dsSenha + "</SENHA>";
        input += "<ORIGEM>" + dsOrigem + "</ORIGEM>";
        input += "</REG>";
        input += "<DADOS>";
        input += "<CONTA>" + idConta + "</CONTA>";
        input += "<DATA>" + date + "</DATA>";
        input += "</DADOS>";
        input += "</ARG>";

         // Envia o xml de entrada por http
        url = SERVICE_ROUTER_URL + "?ARG=" + input;
        client = new HttpClient();
		method = new GetMethod(url);
		statusCode = -1;
		// Tenta, por 3 vezes, receber um retorno
		for (int attempt = 0; statusCode == -1 && attempt < 3; attempt++) {            
            statusCode = client.executeMethod(method);            
		}
		// Verifica se ocorreu algum problema
		if (statusCode == -1) {
			throw new Exception("Erro na chamada a URL " + url);
		}
		// Recebe a pagina correspondente a url chamada
		responseBody = method.getResponseBody();
		method.releaseConnection();
        response = new String(responseBody);
        
         // efetua o parse do documento XML recebido
        bais = new ByteArrayInputStream(responseBody); 
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
       
        return document.getElementsByTagName("outputGetBillImage").item(0).getFirstChild().getNodeValue();
    }

}