package WebServicesWSDL.UraURAS;

import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.actions.AbstractAction;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weblogic.wtc.jatmi.TPException;


public class UraURASController extends AbstractAction {

    private static final long serialVersionUID = -5013421726436443480L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURASController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URAS".equals(mapping.getParameter())) {
			return URAS(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


    public ActionForward URAS(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //<?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAS</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><numTelefone>95257319</numTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlInput = input;
        String xmlOut = null;
        String saida  = "";

        try{
            xmlInput = input;

        }catch(Exception ex){
            logURA.log("uraURAS::URAS[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "BLOQSENHA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            new XmlManager().parseXmlOut(xmlOut);

            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND);

        }catch(TuxedoWarningException twe){
            logURA.log("uraURAS::URAS[Exception]", twe);
            if(twe.getXmlHeader().getError().equals("0002")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_CONTA_INEXISTE,twe);
            } else if(twe.getXmlHeader().getError().equals("0003")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO,twe);
            } else if(twe.getXmlHeader().getError().equals("0004")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05",twe);
            } else {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe);
            }

        }catch(TuxedoErrorException tee){
            logURA.log("uraURAS::URAS[Exception]", tee);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, tee);

        }catch(Exception ex){
            logURA.log("uraURAS::URAS[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

}
