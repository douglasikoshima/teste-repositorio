package br.com.vivo.fo.cliente.assembler;

import noNamespace.ARGDocument;
import noNamespace.ARGDocument.ARG;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class ServiceRouter {

	private String SERVICE_ROUTER_URL;

	public ServiceRouter(String serviceRouterPath) throws Exception {

		if (serviceRouterPath == null || serviceRouterPath == ConstantesCRM.SVAZIO) {
			throw new Exception("A URL do ServiceRouter nao foi informada!");
		}

		this.SERVICE_ROUTER_URL = serviceRouterPath;

		// this.SERVICE_ROUTER_URL =
		// "http://10.128.8.15:8081/ServiceRouter/service";
	}

	public ARG consulta(String xmlIn) throws Exception {

		// Envia o xml de entrada por http
		String url = SERVICE_ROUTER_URL + "?ARG=" + xmlIn;
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);

		int statusCode = -1;
		// Tenta, por 3 vezes, receber um retorno
		for (int attempt = 0; statusCode == -1 && attempt < 3; attempt++) {
			statusCode = client.executeMethod(method);
		}

		// Verifica se ocorreu algum problema
		if (statusCode == -1) {
			throw new Exception("Não foi possível se conectar ao servidor. Tente novamente mais tarde. ");
		}

		// Recebe a pagina correspondente a url chamada
		byte[] responseBody = method.getResponseBody();

		method.releaseConnection();
		String response = new String(responseBody);
		ARGDocument xmlOut = ARGDocument.Factory.parse(response);
		return xmlOut.getARG();
	}
}
