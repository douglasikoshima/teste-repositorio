package WebServicesWSDL.UraURPSSvr;

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
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;

public class UraURPSSvrController extends AbstractAction {

    private static final long serialVersionUID = -6155628395395939562L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURPSSvrController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("PESQSATISFACAO".equals(mapping.getParameter())) {
			return PESQSATISFACAO(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward PESQSATISFACAO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URPS</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><numTelefone>99765215</numTelefone><dtData>05082004</dtData><dsHora>1631</dsHora><indTitularidade>1</indTitularidade><clienteIdentificadoSenha>1</clienteIdentificadoSenha><nroProcessoAtendimento>2</nroProcessoAtendimento><dsNotaCliente>3</dsNotaCliente></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOUT          = null;
        String cdCodigoRetorno = "cdCodigoRetorno";
        String codErroXML      = "02";
        try{
            validaXML(input);

        }catch(Exception e){
            logURA.log("uraURPSSvr::PESQSATISFACAO[Exception]", e);
            xmlOUT = XmlManager.MakeXmlOut("ura", "PESQSATISFACAO", "<statCom>1</statCom><"+cdCodigoRetorno+">"+codErroXML+"</"+cdCodigoRetorno+">");

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
            String xmlTux = XmlManager.MakeXmlIn("30", "PESQSATISFACAO", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOUT+"<<");

        }catch(Exception ex){
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            logURA.log("uraURPSSvr::PESQSATISFACAO[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            xmlOUT = XmlManager.MakeXmlOut("ura", "PESQSATISFACAO", "<statCom>3</statCom>"+"<!--"+msgErro+"-->");

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
