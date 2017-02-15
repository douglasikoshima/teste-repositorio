package WebServicesWSDL.UraUCEP;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noNamespace.MsgDocument;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ura.servicos.vo.EnderecoClienteVODocument.EnderecoClienteVO;
import ura.servicos.vo.EnderecoClienteVODocument.EnderecoClienteVO.EnderecoClienteVOItem;
import ura.servicos.vo.EnderecoClienteVOInputDocument;
import ura.servicos.vo.EnderecoClienteVOInputDocument.EnderecoClienteVOInput;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;


public class UraUCEPController extends AbstractAction {

    private static final long serialVersionUID = 5468754739717456835L;

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraUCEPController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("UCEP".equals(mapping.getParameter())) {
			return UCEP(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    public ActionForward UCEP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><EnderecoClienteVOInput xmlns="servicos.ura/vo"><nrCEP></nrCEP><limiteEndereco></limiteEndereco></EnderecoClienteVOInput></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        XmlManager xmlManager = new XmlManager();

        String xmlOut = "";
        String saida  = "";

        StringBuffer retorno = new StringBuffer();

        if(!validaXML(input)){
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);

        }else{
            // chamar o serviço BUSCAPORCEP
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
                String xmlTux = XmlManager.MakeXmlIn("30", "BUSCAPORCEP", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.log("[XMLOUT]>>"+xmlOut+"<<");

                xmlManager.parseXmlOut(xmlOut);

                EnderecoClienteVO endereco = (EnderecoClienteVO)xmlManager.outputInnerBody( xmlOut );
                EnderecoClienteVOItem[] itens = endereco.getEnderecoClienteVOItemArray();
                if(itens != null){
                    for(int i=0;i<itens.length;i++){
                        retorno.append("<EnderecoClienteVOItem>");
                        retorno.append("<bairro>").append(itens[i].getBairro()).append("</bairro>");
                        retorno.append("<cep>").append(itens[i].getCep()).append("</cep>");
                        retorno.append("<cidade>").append(itens[i].getCidade()).append("</cidade>");
                        retorno.append("<rua>").append(itens[i].getRua()).append("</rua>");
                        retorno.append("<uf>").append(itens[i].getUf()).append("</uf>");
                        retorno.append("</EnderecoClienteVOItem>");
                    }
                }

                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PROSSEGUE_ATEND, retorno.toString());

            }catch(TuxedoErrorException tee){
                logURA.log("uraUCEP::UCEP[TuxedoErrorException]", tee);
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee.getXmlHeader().getStatusText());

            }catch(TuxedoWarningException twe){
                logURA.log("uraUCEP::UCEP[TuxedoWarningException]", twe);
                if(twe.getXmlHeader().getError().equals("0001")) {
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01",twe.getXmlHeader().getStatusText());
                } else if(twe.getXmlHeader().getError().equals("0002")) {
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",twe.getXmlHeader().getStatusText());
                } else if(twe.getXmlHeader().getError().equals("0003")) {
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",twe.getXmlHeader().getStatusText());
                } else if(twe.getXmlHeader().getError().equals("0005")) {
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05",twe.getXmlHeader().getStatusText());
                } else {
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,twe.getXmlHeader().getStatusText());
                }

            }catch(Exception e){
                logURA.log("uraUCEP::UCEP[Exception]", e);
                String msgErro = e.getMessage();
                if(e instanceof TPException){
                    try {
                        String exception = ControlXMLExceptionLookup.getXMLString(e);
                        String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                        msgErro = xmlRetorno[3];
                    }catch(Exception ex) {}
                }
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR, "","<!--"+msgErro+"-->");
            }
        }
        
        saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        
        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    /**
     * Validar o XML de entrada
     */
    public boolean validaXML(String xmlIn){
        try{
            MsgDocument msgInput = MsgDocument.Factory.parse(xmlIn);
            EnderecoClienteVOInputDocument inputVODoc = EnderecoClienteVOInputDocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            EnderecoClienteVOInput inputVO = inputVODoc.getEnderecoClienteVOInput();
            if(inputVO.getNrCEP().length() == 0 || inputVO.getLimiteEndereco().length() == 0 ||
            inputVO.getCdTransacao().length() == 0 || inputVO.getCdUsuario().length() == 0) {
                return false;
            }
            return true;

        }catch(Exception e){
            logURA.log("uraUCEP::validaXML[Exception]", e);
            return false;
        }
    }

}
