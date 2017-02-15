package com.indracompany.actions;

import br.com.vivo.fo.constantes.ConstantesCRM;
import exception.AutenticadorException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class VerifySessionDAO extends DataSource {

	public VerifySessionDAO() {
	}

	@SuppressWarnings("unused")
	public HashMap<String, String> createSession(HashMap<String, String> params) throws Exception {

		HashMap<String, String> hashRetorno = new HashMap<String, String>();

		String returnAS = ConstantesCRM.SVAZIO;
		String paramsAS = ConstantesCRM.SVAZIO;
		String token = ConstantesCRM.SVAZIO;
		String separador = ConstantesCRM.SVAZIO;
		String retorno = ConstantesCRM.SVAZIO;

		String apiPassword = params.get("APIPASSWORD");
		String apiLogin = params.get("APILOGIN");
		String apiURL = params.get("APIURL");
		String subscriber = params.get("NRLINHA");
		String xUsuario = params.get("DSLOGIN");

		try {

			paramsAS = "command=createSession" + "&subscriber=" + subscriber + "&apilogin=" + apiLogin + "&apipassword=" + apiPassword + "&x-usuario=" + xUsuario;

			Iterator<String> it = params.keySet().iterator();
			while (it.hasNext()) {
				Object key = it.next();
				String val = params.get(key);
				if (!key.toString().equals("APIPASSWORD") && !key.toString().equals("APILOGIN") && !key.toString().equals("APIURL") && !key.toString().equals("NRLINHA") && !key.toString().equals("DSLOGIN")) {
					paramsAS += ("&" + key + "=" + val);
				}
			}

			returnAS = this.executeURL(apiURL, paramsAS);

			separador = (returnAS.indexOf("&") >= 0) ? "&" : ";";
			StringTokenizer st = new StringTokenizer(returnAS, "=" + separador);

			while (st.hasMoreTokens()) {
				String key = st.nextToken().trim();
				String val = st.nextToken().trim();
				hashRetorno.put(key, val);
			}

			retorno = hashRetorno.get("retorno");

			if (retorno.equals(ConstantesCRM.SZERO)) {
				return hashRetorno;

			} else if (retorno.equals(ConstantesCRM.SONE)) {
				throw new AutenticadorException("Cliente não autenticado ou senha não reconhecida.");

			} else if (retorno.equals(ConstantesCRM.STHREE)) {
				throw new AutenticadorException("Usuário inexistente no integrador de portais.");

			} else if (retorno.equals(ConstantesCRM.SFOUR)) {
				throw new AutenticadorException("Chamada efetuada não possui todos os parâmetros necessários.");

			} else {
				throw new AutenticadorException("Acesso não autorizado. [VSDao]");
			}

		} catch (Exception e) {
			if (e instanceof AutenticadorException) {
				throw new Exception(e.getMessage(), new Throwable(e.getMessage()));
			}
			throw new Exception("Houve um problema durante acesso à funcionalidade.", new Throwable(e.getLocalizedMessage() != null ? e.getLocalizedMessage() + " - " + e.toString() : e.toString()));
		}
	}

	public HashMap<String, String> getData(HashMap<String, String> params) throws Exception {

		String returnAS = ConstantesCRM.SVAZIO;
		String parametrosOpcionais = ConstantesCRM.SVAZIO;
		String paramsAS = ConstantesCRM.SVAZIO;
		String retorno = ConstantesCRM.SVAZIO;
		String separador = ConstantesCRM.SVAZIO;

		HashMap<String, String> hashRetorno = new HashMap<String, String>();

		String token = params.get("token");
		String apiPassword = params.get("APIPASSWORD");
		String apiLogin = params.get("APILOGIN");
		String apiURL = params.get("APIURL");

		if (token == null) {
			throw new Exception("É necessário o envio da chave &lt;token&gt; para esta requisição.");
		}

		try {

			paramsAS = "command=verifySession&apilogin=" + apiLogin + "&apipassword=" + apiPassword + "&token=<TOKEN>" + "&x-usuario=";
			paramsAS = paramsAS.replaceAll("<TOKEN>", token);

			returnAS = this.executeURL(apiURL, paramsAS).trim();
			parametrosOpcionais = returnAS.substring(returnAS.indexOf("\n") + 1, returnAS.length()).replaceAll("\n", "&");
			returnAS = returnAS.substring(0, returnAS.indexOf("\n")).trim();

			separador = (returnAS.indexOf("&") >= 0) ? "&" : ";";

			StringTokenizer st = new StringTokenizer(returnAS, "=" + separador);
			while (st.hasMoreTokens()) {
				String key = st.nextToken().trim();
				String val = st.nextToken().trim();
				hashRetorno.put(key, val);
			}

			st = new StringTokenizer(parametrosOpcionais, "=&");
			while (st.hasMoreTokens()) {
				String key = st.nextToken().trim();
				String val = st.nextToken().trim();
				hashRetorno.put(key, val);
			}

			retorno = hashRetorno.get("retorno");

			if (retorno.equals(ConstantesCRM.SZERO)) {
				return hashRetorno;

			} else if (retorno.equals(ConstantesCRM.SONE)) {
				throw new AutenticadorException("Cliente não autenticado ou senha não reconhecida.");

			} else if (retorno.equals(ConstantesCRM.STWO)) {
				throw new AutenticadorException("Token não enviado na chamada.");

			} else if (retorno.equals(ConstantesCRM.STHREE)) {
				throw new AutenticadorException("Usuário inexistente no integrador de portais.");

			} else if (retorno.equals(ConstantesCRM.SFOUR)) {
				throw new AutenticadorException("Chamada efetuada não possui todos os parâmetros necessários.");

			} else {
				throw new AutenticadorException("Acesso não autorizado. [VSDao]");
			}

		} catch (Exception e) {
			if (e instanceof AutenticadorException) {
				throw new Exception(e.getMessage(), new Throwable(e.getMessage()));
			}
			throw new Exception("Houve um problema durante acesso à funcionalidade.", new Throwable(e.getLocalizedMessage() != null ? e.getLocalizedMessage() + " - " + e.toString() : e.toString()));
		}
	}
}
