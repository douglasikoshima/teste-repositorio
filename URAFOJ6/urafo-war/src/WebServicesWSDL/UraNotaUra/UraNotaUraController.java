package WebServicesWSDL.UraNotaUra;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import tuxProxyBE.vo.AtendimentoConsultorVODocument.AtendimentoConsultorVO;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.commons.utils.geral.Log;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;
import com.indracompany.actions.AbstractAction;

public class UraNotaUraController extends AbstractAction {

    private static final long serialVersionUID = -4296985858821008599L;

    private transient Log     logURA           = new Log("UraNotaUraController");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if ("URCN".equals(mapping.getParameter())) {
            return URCN(mapping, form, request, response);
        } else if ("URCR".equals(mapping.getParameter())) {
            return URCR(mapping, form, request, response);
        }

        return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward URCN(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0"
        // encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URCN</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00
        // 00:00:00
        // 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>92490020</cdNumTelefone><idAtendimento>41</idAtendimento><tipoNotaAtendimento>0</tipoNotaAtendimento><nmLoginUsuarioCTI></nmLoginUsuarioCTI><comentario>Comentario
        // teste na URA</comentario></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>" + input + "<<");

        String xmlOut = null;
        String saida = "";
        XmlManager xmlManager = new XmlManager();

        try {
            parseXmlIn(input, 1);

        } catch (Exception ex) {
            logURA.log("uraNotaUra::URCN[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO);
            
            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();

            String xml = input.substring(input.indexOf("<msgBody>") + 9, input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "ATDSETNOTA", xml);
            logURA.log("[XMLIN-TX]>>" + xmlTux + "<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>" + xmlOut + "<<");

            xmlManager.parseXmlOut(xmlOut);

            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND);

        } catch (TuxedoWarningException tee) {
            logURA.log("uraNotaUra::URCN[TuxedoWarningException]", tee);
            if ("0001".equals(tee.getXmlHeader().getError())) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", tee);

            } else if (tee.getXmlHeader().getError().equals("0002") || tee.getXmlHeader().getError().equals("0003")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "03", tee);

            } else {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, tee);
            }

        } catch (Exception ex) {
            logURA.log("uraNotaUra::URCN[Exception]", ex);
            String msgErro = ex.getMessage();
            if (ex instanceof TPException) {
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                } catch (Exception e) {
                }
            }
            saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>" + "<!--" + msgErro + "-->");

        } finally {
            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    /** @jpf:action */
    public ActionForward URCR(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0"
        // encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URCR</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00
        // 00:00:00
        // 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>92490020</cdNumTelefone><idAtendimento>76862</idAtendimento><tipoConsulta>2</tipoConsulta></msgBody></msg>
        // <?xml version="1.0"
        // encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URCR</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00
        // 00:00:00
        // 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><cdNumTelefone>12345681</cdNumTelefone><idAtendimento>76862</idAtendimento><tipoConsulta>1</tipoConsulta></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>" + input + "<<");

        String xmlOut = null;
        String saida = "";
        AtendimentoConsultorVO atdConsultorVO = AtendimentoConsultorVO.Factory.newInstance();
        XmlManager xmlManager = new XmlManager();
        StringBuffer xml = new StringBuffer();

        try {
            parseXmlIn(input, 2);

        } catch (Exception ex) {
            logURA.log("uraNotaUra::URCR[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO);

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();

            String xmlEnt = input.substring(input.indexOf("<msgBody>") + 9, input.lastIndexOf("</msgBody>"));
            String xmlTux = XmlManager.MakeXmlIn("30", "ATDGETCONSUL", xmlEnt);
            logURA.log("[XMLIN-TX]>>" + xmlTux + "<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>" + xmlOut + "<<");

            xmlManager.parseXmlOut(xmlOut);

        } catch (TuxedoWarningException tee) {
            logURA.log("uraNotaUra::URCR[TuxedoWarningException]", tee);
            logURA.log("uraNotaUra::URCR:: Erro: " + tee.getXmlHeader().getError() + " descrição:" + tee);
            if ("0001".equals(tee.getXmlHeader().getError())) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", tee);

            } else if ("0002".equals(tee.getXmlHeader().getError())) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "06", tee);

            } else if ("0003".equals(tee.getXmlHeader().getError())) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "03", tee);

            } else if ("0004".equals(tee.getXmlHeader().getError())) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "05", tee);

            } else {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, tee);
            }

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        } catch (Exception ex) {
            logURA.log("uraNotaUra::URCR[Exception]", ex);
            String msgErro = ex.getMessage();
            if (ex instanceof TPException) {
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                } catch (Exception e) {
                }
            }
            saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>" + "<!--" + msgErro + "-->");

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try {
            atdConsultorVO = (AtendimentoConsultorVO) xmlManager.outputInnerBody(xmlOut);
            xml = new StringBuffer();

            if (atdConsultorVO.getIdAtendimento() != null) {
                xml.append("<idAtendimento>").append(atdConsultorVO.getIdAtendimento()).append("</idAtendimento>");
            }

            xml.append("<nmLoginUsuarioCTI>").append(atdConsultorVO.getNmLoginUsuarioCTI()).append("</nmLoginUsuarioCTI>");

            if (atdConsultorVO.getNrLinha() != null) {
                xml.append("<nrLinha>").append(atdConsultorVO.getNrLinha()).append("</nrLinha>");
            }

            if ("".equals(atdConsultorVO.getNmLoginUsuarioCTI().trim())) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "06", xml.toString());
            } else {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND, xml.toString());
            }

        } catch (Exception ex) {
            logURA.log("uraNotaUra::URCR[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA);

        } finally {
            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    public void parseXmlIn(String input, int type) throws Exception {
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
