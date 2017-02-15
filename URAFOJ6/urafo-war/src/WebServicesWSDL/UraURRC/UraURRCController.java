package WebServicesWSDL.UraURRC;

import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.xml.XmlManager;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noNamespace.MsgDocument;
import org.w3c.dom.Document;

import com.indracompany.actions.AbstractAction;

import tuxProxyBE.vo.LINHADocument;
import tuxProxyBE.vo.LINHADocument.LINHA;
import ura.servicos.vo.CaixaPostalVODocument;
import ura.servicos.vo.CaixaPostalVODocument.CaixaPostalVO;
import ura.servicos.vo.CaixaPostalVOInputDocument.CaixaPostalVOInput;
import ura.servicos.vo.RegContatoDocument.RegContato;
import weblogic.wtc.jatmi.TPException;

public class UraURRCController extends AbstractAction {

    private static final long serialVersionUID = -4995634462303006543L;
    private CaixaPostalVODocument inputVODoc;
    private int reinicializa = 0;
    private String user = "";

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURRCController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URRC".equals(mapping.getParameter())) {
			return URRC(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward URRC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URRC</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><CaixaPostalVO xmlns="servicos.ura/vo"><cdDDD>11</cdDDD><cdNumTelefone>95257318</cdNumTelefone><reinicializa>01</reinicializa></CaixaPostalVO></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        CaixaPostalVO caixaPostalVO = null;
        CaixaPostalVOInput caixaPostalInput = CaixaPostalVOInput.Factory.newInstance();
        String xmlOut = "";
        String xmlIn  = "";
        String saida  = "";

        XmlManager xmlManager = new XmlManager();

        Document document = null;
        RegContato regContato = RegContato.Factory.newInstance();
        String servico = new String();

        if(!validaInput(input)){
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        caixaPostalVO = inputVODoc.getCaixaPostalVO();

        if(reinicializa == 1){
            caixaPostalInput.setOperacao("ReiniciaSenha");
            regContato.setCdContato("URRC_SENHA_CAIXA_POSTAL");
        }else if(reinicializa == 2){
            caixaPostalInput.setOperacao("ReiniciaCaixaPostal");
            regContato.setCdContato("URRC_CAIXA_POSTAL");
        }

        caixaPostalInput.setProxyLinha(caixaPostalVO.getCdDDD()+caixaPostalVO.getCdNumTelefone());
        caixaPostalInput.setProxyOperacao("setCaixaPostal");
        caixaPostalInput.setUsuario(user);

        regContato.setCdAreaRegistro(caixaPostalVO.getCdDDD());
        regContato.setNrLinha(caixaPostalVO.getCdNumTelefone());
        regContato.setIdCanal("9");
        regContato.setIdGrupoAbertura("1534");
        regContato.setInRegistrarContato("1");
        regContato.setIdTipoRelacionamento("2");

        //xmlIn = XmlManager.MakeXmlIn(user,"TuxProxyBE",caixaPostalInput.toString()+regContato.toString());
        // Verificar qual o tipo de linha
        try{
            String xmlTipoLinha = "<cdDDD>"+caixaPostalVO.getCdDDD()+"</cdDDD><cdNumTelefone>"+caixaPostalVO.getCdNumTelefone()+"</cdNumTelefone>";
            servico = this.getServicoLegado(xmlTipoLinha);

        }catch(Exception ex){
            logURA.log("uraURRC::URRC[Exception]", ex);
            servico = "TuxProxyBE";
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xml = caixaPostalInput.toString().replaceAll("vo:", "");
            String regContatoXml = regContato.toString().replaceAll("vo:", "");
            String xmlTux = XmlManager.MakeXmlIn("30", servico, xml + regContatoXml);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"00");

        }catch(TuxedoErrorException tee){
            logURA.log("uraURRC::URRC[TuxedoErrorException]", tee);
            String e = tee.getXmlHeader().getError();
            saida = tratarError(e,tee);

        }catch(Exception ex){
            logURA.log("uraURRC::URRC[Exception]", ex);
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");

        }finally{
        	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }  
        return null;
    }

    /**
     * tratar o erro de retorno do Serviço
     */
    private String tratarError(String erro,TuxedoErrorException tee){
        int codigo = Integer.parseInt(erro);
        if(codigo==102 || codigo==1036) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01",tee);
        } else if(codigo==1262) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee);
        } else {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee);
        }
    }

    /**
     * validar o XML de entrada
     */
    public boolean validaInput(String stringInput){
        try{
            // Verificando a integridade da String enviada pela URA:
            MsgDocument msgInput = MsgDocument.Factory.parse(stringInput);
            user = msgInput.getMsg().getMsgHdr().getUser();
            inputVODoc = CaixaPostalVODocument.Factory.newInstance();
            inputVODoc = CaixaPostalVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());

            CaixaPostalVO caixaPostalVO = inputVODoc.getCaixaPostalVO();
            if(caixaPostalVO == null) {
                return false;
            } else if(caixaPostalVO.getCdDDD().equals("") || caixaPostalVO.getCdNumTelefone().equals("") || caixaPostalVO.getReinicializa().equals("")) {
                return false;
            }

            reinicializa = Integer.parseInt(caixaPostalVO.getReinicializa());
            if(reinicializa != 1 && reinicializa != 2) {
                return false;
            }

            return true;
        }catch(Exception e){
            logURA.log("uraURRC::validaInput[Exception]", e);
            return false;
        }
    }

    /**
     *  Verificar qual o tipo de linha e qual serviço de legado chamar
     */
    private String getServicoLegado(String xmlIn){
        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();

            String xmlTux = XmlManager.MakeXmlIn("30", "GETTIPOLINHA", xmlIn);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            MsgDocument msgInput = MsgDocument.Factory.parse(xmlOut);
            LINHADocument linhaDoc = LINHADocument.Factory.newInstance();
            linhaDoc = LINHADocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            LINHA linhaVO = linhaDoc.getLINHA();

            if(linhaVO.getTipoLinha().equals("POS") || linhaVO.getTipoLinha().equals("POSCHIP")) {
                return "TUXATLYSURA";
            } else if(linhaVO.getTipoLinha().equals("PRÉ") || linhaVO.getTipoLinha().equals("PRÉCHIP") || linhaVO.getTipoLinha().equals("CONT") || linhaVO.getTipoLinha().equals("CONTCHIP")) {
                return "TUXNGINBE";
            } else {
                return "TuxProxyBE";
            }

        }catch(Exception ex){
            String msgErro = ex.getMessage();
            if(ex instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(ex);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception e) {}
            }
            logURA.log("uraURRC::getServicoLegado[Exception]("+msgErro+")", ex);
            return "TuxProxyBE";
        }
    }

}
