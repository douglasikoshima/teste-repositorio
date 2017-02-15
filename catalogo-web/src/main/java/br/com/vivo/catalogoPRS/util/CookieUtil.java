package br.com.vivo.catalogoPRS.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blowfishj.BlowfishEasy;
import br.com.vivo.catalogoPRS.bo.DadosCookie;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;

public class CookieUtil {

	private static final boolean desativado = true;
	
	private static final String blowfishpwd = "KwTzCCB3uIZF8hZ";

	private static final BlowfishEasy blowfish = new BlowfishEasy(blowfishpwd
			.toCharArray());

	public static void gravar(HttpServletResponse response,
			UserCatalogo userCatalogo) {
		if(desativado) return;
		Cookie cookie = new Cookie(ApplicationConfiguration.getNomeCookie(),
				criarDadosCookie(userCatalogo));
		cookie.setPath("/");
		cookie.setMaxAge(100000);// expira depois de 30h

		response.addCookie(cookie);
	}

	public static void remove(HttpServletResponse response) {
		if(desativado) return;
		Cookie cookie = new Cookie(ApplicationConfiguration.getNomeCookie(),
				"removingCookieNoValue");
		cookie.setPath("/");
		cookie.setMaxAge(0);

		response.addCookie(cookie);
	}

	public static DadosCookie getDados(HttpServletRequest request) {
		if(desativado) return null;
		Cookie[] cookies = request.getCookies();
		Cookie cookieCatalogo = null;
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals(
						ApplicationConfiguration.getNomeCookie())) {
					cookieCatalogo = cookie;
					break;
				}
			}
			if(cookieCatalogo == null)
				return null;
		}
		return extrairDadosCookie(cookieCatalogo.getValue());
	}

	private static String criarDadosCookie(UserCatalogo userCatalogo) {
		String dados = userCatalogo.getUserSession().getId() + "|"
				+ userCatalogo.getClientHostname() + "|"
				+ userCatalogo.getClientIP();
		return blowfish.encryptString(dados);
	}

	private static DadosCookie extrairDadosCookie(String cryptedDados) {
		String dados = blowfish.decryptString(cryptedDados);
		String[] arrayDados = dados.split("\\|");
		if (arrayDados != null && arrayDados.length == 3) {
			DadosCookie dadosCookie = new DadosCookie();
			dadosCookie.setIdSessaoCliente(arrayDados[0]);
			dadosCookie.setHostnameCliente(arrayDados[1]);
			dadosCookie.setIpCliente(arrayDados[2]);
			return dadosCookie;
		} else {
			return null;
		}
	}

}
