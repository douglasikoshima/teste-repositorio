package commons.errors; 

import br.com.vivo.fo.constantes.ConstantesCRM;
import java.io.PrintWriter;
import java.io.StringWriter;

public class AjaxError {

    public static final String MSG_ERRO          = "Ocorreu um problema durante acesso a esta funcionalidade.";
    public static final int    STATUS_CODE_ERROR = 406;

    public static final String getStackTrace(Exception e) {
        String stackTrace = ConstantesCRM.SVAZIO;
        try {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            stackTrace = sw.toString().replaceAll("\\n",";").replaceAll("\\r","").replaceAll("\\t","");
        } catch (Exception eP) {
            stackTrace = ConstantesCRM.SVAZIO;
        }
        return stackTrace;
    }

    public static final String getMessage(Exception e) {
        String message = ConstantesCRM.SVAZIO;
        try {
            /*message = (e.getMessage() == null) ? 
                MSG_ERRO :
                e.getMessage();*/
            message = MSG_ERRO;
        } catch (Exception eP) {
            message = ConstantesCRM.SVAZIO;
        }
        return message;
    }

}