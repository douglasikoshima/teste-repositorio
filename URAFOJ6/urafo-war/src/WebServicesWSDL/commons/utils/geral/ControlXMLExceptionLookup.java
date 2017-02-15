package WebServicesWSDL.commons.utils.geral;

import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlHeader;

/**
 * Classe utilizada para o tratamento de erros do Tuxedo
 */
public class ControlXMLExceptionLookup {

	//Monta o mecanismo de log
    private static transient WebServicesWSDL.commons.utils.geral.Log log = new WebServicesWSDL.commons.utils.geral.Log();

    public static String getXMLString(Exception e) {

        String msgErro = "";
        StringBuffer rc = new StringBuffer();
        rc.append("<?xml version='1.0' encoding='UTF-8'?>");
        rc.append("    <msg>");
        rc.append("        <msgHdr>");
        rc.append("             <service>CustomService</service>");
        rc.append("             <user>CustomUser</user>");
        rc.append("             <host></host>");
        rc.append("             <subsystem>00</subsystem>");
        rc.append("             <corrid>000000002123E</corrid>");
        rc.append("             <statusCode>00E0000</statusCode>");
        try {
            if(e instanceof TPReplyException){
                TPReplyException re = (TPReplyException) e;
                Reply r = re.getExceptionReply();
                TypedBuffer tb = r.getReplyBuffer();
                return tb.toString();

            }else if( e instanceof TPException){
                TPException re = (TPException) e;
                Reply r = re.getReplyRtn();
                if(r!=null){
                    TypedBuffer tb = r.getReplyBuffer();
                    if(tb!=null)
                        return tb.toString();
                }
            }

            // obtem descricao do erro
            msgErro = e.getMessage()==null?e.getCause().getMessage()==null?"Unknown error":e.getCause().getMessage():e.getMessage();
            rc.append("             <statusText>").append(msgErro).append("</statusText>");
            rc.append("         </msgHdr>");
            rc.append("    <msgBody/>");
            rc.append("</msg>");
            return rc.toString();

        }catch(Exception ex) {
            log.log("ControlXMLExceptionLookup:getXMLString() - [" + ex.getMessage() + "]", ex);
            rc.append("             <statusText>").append(ex.getMessage()).append("</statusText>");
            rc.append("         </msgHdr>");
            rc.append("    <msgBody/>");
            rc.append("</msg>");
            return rc.toString();
        }
    }

    public static void dumpException( Exception e ) {
        if ( e instanceof TuxedoWarningException ) {
            XmlHeader header = ((TuxedoException) e).getXmlHeader();
            System.out.println( "Error ID: " + header.getStatusCode() );
            System.out.println( "Error Message: " + header.getStatusText() );
        } else if ( e instanceof TuxedoErrorException ) {
            XmlHeader header = ((TuxedoException) e).getXmlHeader();
            System.out.println( "Error ID: " + header.getStatusCode() );
            System.out.println( "Error Message: " + header.getStatusText() );
        }
    }
}
