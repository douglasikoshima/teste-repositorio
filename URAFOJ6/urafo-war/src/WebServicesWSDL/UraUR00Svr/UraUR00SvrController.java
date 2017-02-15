package WebServicesWSDL.UraUR00Svr;

import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import weblogic.wtc.jatmi.TPException;

public class UraUR00SvrController extends AbstractAction {

    private static final long serialVersionUID = 1643884993537337872L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraUR00SvrController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("SONDA".equals(mapping.getParameter())) {
			return SONDA(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward SONDA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>UR00</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String saida  = "";
        try{
            validaXML(input);

        }catch(Exception e){
            logURA.log("uraUR00Svr::SONDA[Exception]", e);
            saida = XmlManager.MakeXmlIn("ura", "SONDA", "<statCom>2</statCom>");

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
            String xmlTux = XmlManager.MakeXmlIn("30", "SONDA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            saida = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+saida+"<<");

        }catch(Exception ex){
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            // IMPORTANTE: Nesse caso usa-se o código 7 dizendo que o FO está fora do ar, já que o FO é quem traz as
            // informações se os outros sistemas estão indisponíveis
            logURA.log("uraUR00Svr::SONDA[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.MakeXmlIn("ura", "SONDA", "<statCom>2</statCom>"+"<!--"+msgErro+"-->");

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
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
