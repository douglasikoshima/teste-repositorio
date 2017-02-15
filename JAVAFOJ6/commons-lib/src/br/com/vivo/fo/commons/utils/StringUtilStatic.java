package br.com.vivo.fo.commons.utils;

import java.io.Serializable;
import java.text.Normalizer;
import java.util.StringTokenizer;

public class StringUtilStatic implements Serializable {

	private static final long serialVersionUID = 2056651030674660926L;

	public static Object emptyIfNull(Object s) {
		if (s != null) {
			return s;
		} else {
			return "";
		}
	}

	/**
	 * Faz tratamento de string apenas para negar ' e " @ deprecated
	 */
	public static String escapaComillasJS(String cadena) {
		if (cadena == null) {
			return "";
		}

		cadena = cadena.replaceAll("\\\\", "\\\\\\\\");
		cadena = cadena.replaceAll("[\\n\\r]", " ");

		int index = 0;
		String tmpStr = "";

		if (cadena == null) {
			return tmpStr;
		}

		index = cadena.indexOf('\'');
		while (index >= 0) {
			tmpStr = cadena.substring(0, index) + "\\" + cadena.substring(index);
			cadena = tmpStr;
			if (cadena.length() >= (index + 2)) {
				index = cadena.indexOf('\'', index + 2);
			} else {
				index = -1;
			}
		}

		index = cadena.indexOf('\"');
		while (index >= 0) {
			tmpStr = cadena.substring(0, index) + "\\" + cadena.substring(index);
			cadena = tmpStr;
			if (cadena.length() >= (index + 2)) {
				index = cadena.indexOf('\"', index + 2);
			} else {
				index = -1;
			}
		}

		return cadena;
	}

	/**
	 * Faz tratamento de string para caso seja necessário chamar uma função js
	 * preenchido pelo controller @ deprecated
	 */
	public static String escapaComillasJS2(String strParam) {
		if (strParam == null) {
			return "";
		}
		strParam = strParam.replaceAll("'", "'+String.fromCharCode(39)+'");

		strParam = strParam.replaceAll("\"", "'+String.fromCharCode(34)+'");

		strParam = strParam.replaceAll("\\\\", "'+String.fromCharCode(92)+'");

		return strParam;
	}

	/**
	 * Monta mascara em um valor conforme informação da mascara
	 * 
	 * @param java
	 *            .lang.String value O valor a se aplicar a mascara.
	 * @param java
	 *            .lang.String A mascara a ser aplicada no valor.
	 * @return java.lang.String O valor com a aplicação da mascara
	 */
	public static String mask(String value, String mask) {

		// Valida entradas
		if (value == null) {
			return null;
		} else if ((mask == null) || (mask.trim().length() == 0)) {
			return value;
		}

		// Monta elementos
		int valueIndex = 0, maskIndex = 0;
		char maskChar = ' ', resultChar = ' ';
		StringBuffer buffer = new StringBuffer();

		// Processa a mascara
		while ((valueIndex < value.length()) && (maskIndex < mask.length())) {
			maskChar = mask.charAt(maskIndex++);

			if (maskChar == '#') {
				resultChar = value.charAt(valueIndex++);
			} else {
				resultChar = maskChar;
			}

			buffer.append(resultChar);
		}

		return buffer.toString();
	}

	/**
	 * Remoção de mascaras
	 * 
	 * @param java
	 *            .lang.String O valor com as mascaras a serem retirada
	 * @param java
	 *            .lang.String Os elementos presentes na mascara para remoção
	 * @return java.lang.String O elementos presentes no valor a se retirar
	 */
	public static String unmask(String value, String maskCharacters) {

		// Valida entradas
		if (value == null) {
			return null;
		} else if ((maskCharacters == null) || (maskCharacters.trim().length() == 0)) {
			return value;
		}

		// Monta elementos
		StringBuffer buffer = new StringBuffer(value.length());
		StringTokenizer cleaner = new StringTokenizer(value, maskCharacters);

		// Desmonta mascara
		while (cleaner.hasMoreTokens()) {
			buffer.append(cleaner.nextToken());
		}

		return buffer.toString();
	}

	/**
	 * Obtém uma data formata em um padrão pré-definido
	 * 
	 * @param oDate
	 *            A data a ser formatada em qualquer padrão
	 * @param formato
	 *            A mascara a ser aplicada para exibição da data
	 * @return Retorna um String com a data no formato pré-definido
	 **/
	/*
	 * public static String exibeData(Object oDate, String formato) {
	 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat(); //Valida
	 * entradas if( oDate == null ) return null; if( (formato == null) ||
	 * (formato.trim().length() == 0) ) return null;
	 * 
	 * //Monta o formato simpleDateFormat.applyPattern(formato);
	 * 
	 * //Retorna a data formata return simpleDateFormat.format((Date)oDate); }
	 */
	
	
	 public static String removeAccents(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "").replaceAll(" ", "_");
		return str;
	 }
}
