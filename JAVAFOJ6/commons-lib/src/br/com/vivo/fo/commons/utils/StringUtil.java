package br.com.vivo.fo.commons.utils;

import java.io.Serializable;

public class StringUtil implements Serializable {
    
    private static final long serialVersionUID = 5308339323321046800L;

    public StringUtil() {
    }

    /**
     * Faz tratamento de string apenas para negar ' e "
     */
    public String escapaComillasJS(String cadena) {
        if (cadena == null) return "";
        cadena = cadena.replaceAll("\\\\", "\\\\\\\\");
        cadena = cadena.replaceAll("[\\n\\r]", " ");
        int index = 0;
        String tmpStr = "";
        if (cadena == null) return tmpStr;
        index = cadena.indexOf('\'');
        while (index >= 0) {
            tmpStr = cadena.substring(0, index) + "\\" + cadena.substring(index);
            cadena = tmpStr;
            if (cadena.length() >= (index + 2))
                index = cadena.indexOf('\'', index + 2);
            else
                index = -1;
        }
        index = cadena.indexOf('\"');
        while (index >= 0) {
            tmpStr = cadena.substring(0, index) + "\\" + cadena.substring(index);
            cadena = tmpStr;
            if (cadena.length() >= (index + 2))
                index = cadena.indexOf('\"', index + 2);
            else
                index = -1;
        }
        return cadena;
    }

    /**
     * Faz tratamento de string para caso seja necessário chamar uma função js
     * preenchido pelo controller
     */
    public String escapaComillasJS2(String strParam) {
        if (strParam == null) return "";
        strParam = strParam.replaceAll("'", "'+String.fromCharCode(39)+'");
        strParam = strParam.replaceAll("\"", "'+String.fromCharCode(34)+'");
        strParam = strParam.replaceAll("\\\\", "'+String.fromCharCode(92)+'");
        return strParam;
    }
}
