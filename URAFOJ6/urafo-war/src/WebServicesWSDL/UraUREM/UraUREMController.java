package WebServicesWSDL.UraUREM;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;


public class UraUREMController extends AbstractAction {

    private static final long serialVersionUID = 5735653802815104395L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraUREMController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("UREM".equals(mapping.getParameter())) {
			return UREM(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


    public ActionForward UREM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception  {
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");
        String xmlOut = "";
        String saida  = "";
        XmlManager xmlManager = new XmlManager();
        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "GETEMAIL", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

            saida = XmlManager.MakeXmlOut("","GETEMAIL",xmlOut);

        }catch(TuxedoWarningException twe){
            logURA.log("UREM::UREM[TuxedoWarningException]", twe);
            if(twe.getXmlHeader().getError().equals("0001")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01",twe);
            } else if(twe.getXmlHeader().getError().equals("0002")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",twe);
            } else if(twe.getXmlHeader().getError().equals("0003")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",twe);
            } else {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_ERRO);
            }

        }catch(TuxedoErrorException tee){
            logURA.log("UREM::UREM[TuxedoErrorException]", tee);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR, "99", tee);

        }catch(Exception ex){
            logURA.log("UREM::UREM[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR, "", "<!--"+msgErro+"-->");

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
