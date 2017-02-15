package WebServicesWSDL.UraWSCADASTROPRE;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noNamespace.MsgDocument;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import tuxProxyBE.vo.URENPrePagoVODocument;
import tuxProxyBE.vo.URENPrePagoVODocument.URENPrePagoVO;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;

public class UraWSCADASTROPREController extends AbstractAction {

    private static final long serialVersionUID = 4771257476780528885L;

    private URENPrePagoVODocument inputVODoc;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraWSCADASTROPREController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("WSCADASTROPRE".equals(mapping.getParameter())) {
			return WSCADASTROPRE(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward WSCADASTROPRE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String     xmlOut     = "";
        String     saida      = "";
        XmlManager xmlManager = new XmlManager();
        URENPrePagoVO inputVO;

        if(validaInput(input)){
            inputVO = URENPrePagoVO.Factory.newInstance();
            inputVO = inputVODoc.getURENPrePagoVO();

            // Verificar Tags necessárias
            if(null == inputVO.getNmCliente()     || inputVO.getNmCliente().trim().equals("")        /*NOME*/ ||
                null == inputVO.getNrCPF()         || inputVO.getNrCPF().trim().equals("")             /*CPF*/ ||
                null == inputVO.getCdDDD()         || inputVO.getCdDDD().trim().equals("")             /*DDD*/ ||
                null == inputVO.getCdNumTelefone() || inputVO.getCdNumTelefone().trim().equals("")   /*LINHA*/ ||
                null == inputVO.getNrCEP()         || inputVO.getNrCEP().trim().equals("")             /*CEP*/ ||
                null == inputVO.getDsEndereco()    || inputVO.getDsEndereco().trim().equals("") /*LOGRADOURO*/ ){
                // Abortar por erro de Tags obrigatórias não enviadas
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06");

            }else{
                // Continuar com execução normal
                try{
                    JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                    String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
                    String xmlTux = XmlManager.MakeXmlIn("30", "CADPREPAGO", xmlEnt);
                    logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                    xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                    logURA.log("[XMLOUT]>>"+xmlOut+"<<");

                    xmlManager.parseXmlOut(xmlOut);

                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PROSSEGUE_ATEND);

                }catch (TuxedoWarningException twe){
                    logURA.log("WSCADASTROPRE::WSCADASTROPRE[TuxedoWarningException]", twe);
                    if(twe.getXmlHeader().getError().equals("0001")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",twe);
                    }else if (twe.getXmlHeader().getError().equals("0002")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05",twe);
                    }else if (twe.getXmlHeader().getError().equals("0003")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",twe);
                    }else if (twe.getXmlHeader().getError().equals("0004")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe);
                    }else if (twe.getXmlHeader().getError().equals("0005")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",twe);
                    }else if (twe.getXmlHeader().getError().equals("1005") || twe.getXmlHeader().getError().equals("1006")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01",twe);
                    }else if (twe.getXmlHeader().getError().equals("0599") || twe.getXmlHeader().getError().equals("1100")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",twe);
                    }else if (twe.getXmlHeader().getError().equals("1007") || twe.getXmlHeader().getError().equals("1124")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"09",twe);
                    }else{
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe);
                    }

                }catch (TuxedoErrorException tee){
                    logURA.log("WSCADASTROPRE::WSCADASTROPRE[TuxedoErrorException]", tee);
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee);

                }catch (Exception ex){
                    logURA.log("WSCADASTROPRE::WSCADASTROPRE[Exception]", ex);
                    String msgErro = ex.getMessage();
                    if(ex instanceof TPException){
                        try {
                            String exception = ControlXMLExceptionLookup.getXMLString(ex);
                            String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                            msgErro = xmlRetorno[3];
                        }catch(Exception e) {}
                    }
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR,"","<!--"+msgErro+"-->");
                }
            }
        }else{
            // Abortar por erro de Parse dos dados enviados pela URA
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02");
        }
        
        saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    public boolean validaInput(String stringInput){
        try{
            // Verificando a integridade da String enviada pela URA:
            MsgDocument msgInput = MsgDocument.Factory.parse(stringInput);
            inputVODoc = URENPrePagoVODocument.Factory.newInstance();
            inputVODoc = URENPrePagoVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            return true;// String OK

        }catch(Exception e){
            logURA.log("WSCADASTROPRE::validaInput[Exception]", e);
            return false;// String Não OK
        }
    }

}
