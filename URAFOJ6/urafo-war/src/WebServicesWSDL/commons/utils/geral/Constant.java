package WebServicesWSDL.commons.utils.geral; 

import java.io.Serializable;

public class Constant implements Serializable {

	public static final long serialVersionUID = -1L;

    public static final String URA_STATCOM_OK = "1";
    public static final String URA_STATCOM_ERRO = "2";
    public static final String URA_STATCOM_FORA_AR = "3";
    
    public static final String URA_CODIRET_PROSSEGUE_ATEND = "00";
    public static final String URA_CODIRET_CONTA_INEXISTE = "01";
    public static final String URA_CODIRET_PARAM_INVALIDO = "02";
    public static final String URA_CODIRET_ERRO_BASE = "03";
    public static final String URA_CODIRET_ERRO_SUBROTINA = "04";
    public static final String URA_CODIRET_CLIENTE_RESTR = "05";
    public static final String URA_CODIRET_OUTROS_RESTR = "06";
    public static final String URA_CODIRET_REGIONAL_RESTR = "07";

    public static final String URA_CODILR_DESBLOQUEADO = "0";
    public static final String URA_CODILR_BLOQUEADO = "1";

    public static String SContentType = "text/xml;charset=ISO-8859-1";
    public static String SISO = "ISO-8859-1";
} 
