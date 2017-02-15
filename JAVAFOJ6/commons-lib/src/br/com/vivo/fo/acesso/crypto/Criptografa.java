package br.com.vivo.fo.acesso.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class Criptografa {

	private static byte[] msg;
	private static byte[] ret;

	// private static byte[] teste;
	// private static Byte[] teste;

	public String criptografaMD5(String input) throws NoSuchAlgorithmException {

		msg = new byte[input.length()];
		StringBuffer retorno = new StringBuffer(ConstantesCRM.SVAZIO);

		// int j = 0;
		// teste= new Byte[5];
		// j=Integer.parseInt("1");
		// teste[0].decode(input.substring(0,1));
		/*
		 * teste[1]=Byte.parseByte("E"); teste[2]=Byte.parseByte("S");
		 * teste[3]=Byte.parseByte("T"); teste[4]=Byte.parseByte("E");
		 */

		try {
			for (int i = 0; i < input.length(); i++) {
				msg[i] = Byte.parseByte(input.substring(i, i + 1));
			}
		} catch (NullPointerException ex) {
			ex.getMessage();
		}

		MessageDigest message = MessageDigest.getInstance("MD5");
		message.update(msg);
		ret = message.digest();
		for (int i = 0; i < ret.length; i++) {
			retorno.append(Byte.toString(ret[i]));
		}
		return retorno.toString();
	}
}
