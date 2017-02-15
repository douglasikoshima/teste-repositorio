package br.com.vivo.fo.acesso.senha;

import java.util.regex.Pattern;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class Senha {
	
	public boolean verificaLogin(String password, String login) throws Exception {

		String val = ConstantesCRM.SVAZIO;
		boolean b = false;
		if (login.length() > password.length()) {
			for (int i = 0; i <= (password.length() - 3); i++) {
				val = password.substring(i, i + 3);
				b = Pattern.matches("^.*" + val + ".*$", login);
				if (b) {
					return true;
				}
			}
		} else {
			for (int i = 0; i <= (login.length() - 3); i++) {
				val = login.substring(i, i + 3);
				b = Pattern.matches("^.*" + val + ".*$", password);
				if (b) {
					return true;
				}
			}
		}
		return b;
	}

	public boolean verificaAscIgual(String password) throws Exception {

		boolean retorno = false;
		String caracteres = ConstantesCRM.SVAZIO;
		String val = ConstantesCRM.SVAZIO;

		for (int i = 32; i < 255; i++) {
			char c = (char) i;
			caracteres = String.valueOf(c) + String.valueOf(c) + String.valueOf(c);
			for (int j = 0; j <= (password.length() - 3); j++) {
				val = password.substring(j, j + 3);
				if (val.equals(caracteres)) {
					retorno = true;
				}
			}
		}
		return retorno;
	}

	public boolean verificaSeqCresDecresAscii(String password) throws Exception {

		boolean b = false;
		boolean retorno = false;
		String[] numeros = { "012", "123", "234", "345", "456", "567", "678", "789", "890", "901", "098", "987", "876",
				"765", "654", "543", "432", "321", "210", "109" };

		for (int i = 0; i < numeros.length; i++) {

			b = Pattern.matches("^.*" + numeros[i] + ".*$", password);
			if (b) {
				retorno = true;
			}
		}
		return retorno;
	}
}
