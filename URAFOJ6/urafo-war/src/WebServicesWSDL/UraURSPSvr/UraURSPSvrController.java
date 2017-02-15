package WebServicesWSDL.UraURSPSvr;

import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.xml.XmlManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.indracompany.actions.AbstractAction;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import weblogic.wtc.jatmi.TPException;

public class UraURSPSvrController extends AbstractAction {

    private static final long serialVersionUID = 5202534157055861349L;

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URSP".equals(mapping.getParameter())) {
			return URSP(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURSPSvrController");

    /** @jpf:action */
    public ActionForward URSP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dados de teste
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URSP</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>21</cdDDD><numTelefone>99259840</numTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String cdCodigoRetorno = "cdCodigoRetorno";
        String codErroTux = "04";
        String codErroXML = "02";
        String xmlOUT = null;
        try{
            validaXML(input);
            input = XmlManager.registrarContato(input,"URSP");

        }catch(Exception e){
            logURA.log("uraURSPSvr::URSP[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "URSPPONTOS", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "URSPPONTOS", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

        }catch(Exception ex){
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            logURA.log("uraURSPSvr::URSP[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "URSPPONTOS", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(xmlOUT.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    private void validaXML(String input) throws Exception {
        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

        if (document.getElementsByTagName("msg").item(0) == null) {
            throw new Exception("msg não encontrada");
        }

        if (document.getElementsByTagName("msgHdr").item(0) == null) {
            throw new Exception("Header não encontrado");
        }

        if (document.getElementsByTagName("msgBody").item(0).getChildNodes() == null) {
            throw new Exception("Body não encontrado");
        }
    }

}
