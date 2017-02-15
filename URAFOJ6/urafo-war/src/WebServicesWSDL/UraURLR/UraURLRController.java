package WebServicesWSDL.UraURLR;

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
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.exception.URAException;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;

public class UraURLRController extends AbstractAction {

    private static final long serialVersionUID = -4408842756639222907L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURLRController");

    private String _SERVICO = "SETLSTRESTRITA";
    private String  NUMERO_INVALIDO = "01";

    private String cdAreaRegistro;
    private String nrLinha;
    private String cdBloqueio;

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URLR".equals(mapping.getParameter())) {
			return URLR(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


    public ActionForward URLR(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URCN</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>92490020</cdNumTelefone><idAtendimento>41</idAtendimento><tipoNotaAtendimento>0</tipoNotaAtendimento><nmLoginUsuarioCTI/><comentario>Comentario teste na URA</comentario><cdBloqueio>1</cdBloqueio></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOut = null;
        String saida  = "";
        XmlManager xmlManager = new XmlManager();
        try{
            input = converterXmlIn(input);  // converte os nomes das tags

        }catch(URAException e){
            logURA.log("uraURLR::URLR[URAException]", e);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, e.getCodRetorno());

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        }catch(Exception e){
            logURA.log("uraURLR::URLR[Exception]", e);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", _SERVICO, xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);
            // Registra o contato conforme a operacão
            registrarContato(input);

            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND, "<CODILR>" + cdBloqueio + "<CODILR>");

        }catch(TuxedoWarningException e){
            logURA.log("uraURLR::URLR[TuxedoWarningException]", e);
            if(e.getXmlHeader().getError().equals("0001")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "01", e);

            }else if(e.getXmlHeader().getError().equals("0002")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", e);

            }else if(e.getXmlHeader().getError().equals("0003")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "03", e);

            }else if(e.getXmlHeader().getStatusCode().equals("13W0005")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "05");

            }else if(e.getXmlHeader().getStatusCode().equals("13W0006")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "06");

            }else if(e.getXmlHeader().getStatusCode().equals("13W0007")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "07");

            }else{
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, e);
            }

        }catch(Exception ex){
            logURA.log("uraURLR::URLR[Exception]", ex);
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

    public String converterXmlIn(String input) throws Exception {
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

        try { cdAreaRegistro = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue(); }
        catch (Exception e) {}
        try { nrLinha = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue(); }
        catch (Exception e) {}
        try { cdBloqueio = document.getElementsByTagName("cdBloqueio").item(0).getFirstChild().getNodeValue(); }
        catch (Exception e) {}

        if (cdAreaRegistro == null || cdAreaRegistro.length() != 2) {
            throw new URAException(NUMERO_INVALIDO);
        }
        if (nrLinha == null || nrLinha.length() < 8 || nrLinha.length() > 9) {
            throw new URAException(NUMERO_INVALIDO);
        }
        if (cdBloqueio == null || "01".indexOf(cdBloqueio) == -1) {
            throw new Exception("cdBloqueio = null");
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<cdAreaRegistro>" + cdAreaRegistro + "</cdAreaRegistro>");
        sb.append("<nrLinha>" + nrLinha + "</nrLinha>");
        sb.append("<cdBloqueio>" + cdBloqueio + "</cdBloqueio>");
        return XmlManager.MakeXmlIn("1", _SERVICO, sb.toString());
    }

    private void registrarContato(String xml) throws Exception {
        JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
        StringBuffer sb = new StringBuffer();
        String contato = "ContatoListaRestritivaOn";
        if ("0".equals(cdBloqueio)) {
            contato = "ContatoListaRestritivaOff";
        }
        sb.append("<idGrupoAbertura>1534</idGrupoAbertura>");
        sb.append("<inResponsavelAbertura>C</inResponsavelAbertura>");
        sb.append("<observacao/>");
        sb.append("<ddd>" + cdAreaRegistro + "</ddd>");
        sb.append("<nrLinha>" + nrLinha + "</nrLinha>");
        sb.append("<tpOperacao>1</tpOperacao>");
        sb.append("<ProcedenciaVO>");
        sb.append("  <idProcedencia>1</idProcedencia>");
        sb.append("</ProcedenciaVO>");
        sb.append("<CanalVO>");
        sb.append("  <idCanal>9</idCanal>");
        sb.append("</CanalVO>");
        sb.append("<ArvoreAtendimentoVO>");
        sb.append("  <cdContato>" + contato + "</cdContato>");
        sb.append("</ArvoreAtendimentoVO>");

        //String input = XmlManager.MakeXmlIn("1", "REGCONTATOFO", sb.toString());
        String xmlTux = XmlManager.MakeXmlIn("30", "REGCONTATOFO", sb.toString());
        logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

        String xmlOUT = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
    }

}
