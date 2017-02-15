package br.com.vivo.catalogoPRS.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class PermissionsConfiguration {

	private static final String SUBSISTEMA_PREFIX = "ss.";

	private static final String PONTO_INTERMEDIO_PREFIX = "pi.";
	
	private static final String DOMINIO_GERIDO_PREFIX = "dg.";

	private final static ResourceBundle bundle = ResourceBundle.getBundle("catalogoprs_permissoes");

	public static String getSubSistemaAcao(String acao) {
		return bundle.getString(SUBSISTEMA_PREFIX + acao);
	}

	public static String getPontoIntermedioAcao(String acao) {
		try {
			return bundle.getString(PONTO_INTERMEDIO_PREFIX + acao);
		} catch (MissingResourceException e) {
			return null;
		}
	}
	
	public static String getDominioGeridoAcaoNome(String acao, String nome) {
		return bundle.getString(DOMINIO_GERIDO_PREFIX+ acao + "." + nome);
	}

}
