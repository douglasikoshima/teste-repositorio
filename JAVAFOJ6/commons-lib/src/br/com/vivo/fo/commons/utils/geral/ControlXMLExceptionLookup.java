package br.com.vivo.fo.commons.utils.geral;

import java.lang.reflect.Field;
import java.rmi.RemoteException;

import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TuxedoReply;
import weblogic.wtc.jatmi.TypedBuffer;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

public class ControlXMLExceptionLookup {

    //Monta o mecanismo de log
    private static Logger log = new Logger("start");

    public static String getXMLString(Exception e) {
        // definições
        TPReplyException re;
        boolean evalTPException, evalControlException = false, evalTPExceptionReflect, evalTPReplyException;

        evalTPException = (e instanceof TPException);
        evalTPExceptionReflect = (e.getCause().getCause() instanceof TPException);
        evalTPReplyException = (e.getCause().getCause() instanceof TPReplyException);

        // verifica se conseguiu obter a sessão do Tuxedo
        if (evalTPException || (evalControlException && evalTPExceptionReflect && !evalTPReplyException)) {
            StringBuffer rc = new StringBuffer();
            rc.append("<?xml version='1.0' encoding='ISO-8859-1'?>");
            rc.append("    <msg>");
            rc.append("        <msgHdr>");
            rc.append("             <service>CustomService</service>");
            rc.append("             <user>CustomUser</user>");
            rc.append("             <host></host>");
            rc.append("             <subsystem>00</subsystem>");
            rc.append("             <corrid>000000002123E</corrid>");
            rc.append("             <statusCode>00E2123</statusCode>");
            rc.append("             <statusText>" + e.getMessage() + "</statusText>");
            rc.append("         </msgHdr>");
            rc.append("    <msgBody/>");
            rc.append("</msg>");

            return rc.toString();

        } else {
            // senão, verifica o tipo de erro e trata
            try {
                if (e instanceof Exception) {
                    re = (TPReplyException) e.getCause();
                } else if (e instanceof RemoteException) {
                    re = (TPReplyException) e.getCause().getCause();
                } else {
                    return "Exception not defined for this type " + e.getClass().getName();
                }

                // obtem descricao do erro
                Field f = re.getClass().getDeclaredField("myRplyRtn");
                f.setAccessible(true);
                Reply r = (TuxedoReply) f.get(re);
                Field f1 = r.getClass().getDeclaredField("myTypedBuffer");
                f1.setAccessible(true);
                TypedBuffer tb = (TypedBuffer) f1.get(r);

                //Obtém o ponto de inicio da mensagem
                int statutTextStart = tb.toString().toLowerCase().indexOf("<statustext>");
                int statutTextEnd = tb.toString().toLowerCase().indexOf("</statustext>");

                //Constroi a mensagem se necessário, sem os delimitadores internos
                String xmlOUT = null;
                if ((statutTextStart > 0) && (statutTextEnd > 0)) {
                    //Retira o corpo da mensagem
                    xmlOUT = tb.toString().substring(statutTextStart + 12, statutTextEnd);

                    //Retira os delimitadores xml para não expor erro de xerces
                    xmlOUT = xmlOUT.replaceAll("<", ConstantesCRM.SVAZIO);

                    //Retira os delimitadores xml para não expor erro de xerces
                    xmlOUT = xmlOUT.replaceAll(">", ConstantesCRM.SVAZIO);

                    //Monta a mensagem xml com a mensagem sem o erro xerces
                    xmlOUT = tb.toString().substring(0, statutTextStart + 12) + xmlOUT + tb.toString().substring(statutTextEnd);

                } else {
                    xmlOUT = tb.toString();
                }

                return xmlOUT;

            } catch (Exception ex) {
                log.error("Exception - ControlXMLExceptionLookup:getXMLString() - [" + ex.getMessage() + "]", ex);
                return ("Unknown error");
            }
        }
    }
}
