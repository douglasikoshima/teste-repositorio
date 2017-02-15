package WebServicesWSDL.Atendimento;

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

public class AtendimentoController extends AbstractAction {
    
    private static final long serialVersionUID = 1058414592238835239L;
    
    public static final String URA_STATCOM_OK = "1";

    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("AtendimentoController");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("BuscaGrupoAtendimento".equals(mapping.getParameter())) {
            return BuscaGrupoAtendimento(mapping, form, request, response);
        }

        return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return null;
    }

    public ActionForward BuscaGrupoAtendimento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");
        String saida     =  "";
        String     xmlOut     = "";
        XmlManager xmlManager = new XmlManager();
        
        if(validaInput(input)){
            // Continuar com execução normal
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));

                String xmlTux = XmlManager.MakeXmlIn("30", "BUSCAGRPATD", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
               
                logURA.log("[XMLOUT]>>"+xmlOut+"<<");
                
                xmlManager.parseXmlOut(xmlOut);
                
                response.setContentType(Constant.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(xmlOut.toString().getBytes(Constant.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                return null;
             
            }catch(TuxedoErrorException tee){
            	logURA.log("::BuscaGrupoAtendimento[Exception]", tee);
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tee.getXmlHeader().getStatusText());
                
            } catch(TuxedoWarningException twe){
                logURA.log("::BuscaGrupoAtendimento[TuxedoWarningException]", twe);
                if(twe.getXmlHeader().getError().equals("0001")) {
                    saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO, "Parâmetros obrigatórios nao informados.");
                } else if (twe.getXmlHeader().getError().equals("0002")) {
                    saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_OUTROS_RESTR, "Não foram encontrados registros.");
                } else if (twe.getXmlHeader().getError().equals("0999")){
                	saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_BASE, "Erro desconhecido / Erro oracle.");
                } 
                
                saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
                     
            }catch(Exception ex){
                logURA.log("BuscaGrupoAtendimento::BuscaGrupoAtendimento[Exception]", ex);
                String msgErro = ex.getMessage();
                if(ex instanceof TPException){
                    try {
                        String exception = ControlXMLExceptionLookup.getXMLString(ex);
                        String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                        msgErro = xmlRetorno[3];
                    }catch(Exception exX) {}
                }
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR, Constant.URA_CODIRET_ERRO_BASE, "");
                
            }
	        
            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
        } else {
        	saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);
        }
        
        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
        
	  }

    public boolean validaInput(String stringInput){
        try{
            if(stringInput!=null && !stringInput.isEmpty() 
            		&& stringInput.indexOf("<numeroInformado>")> -1 && stringInput.indexOf("</numeroInformado>") > -1){
                return true; // String OK
            }
        }catch(Exception e){
            logURA.log("uraUREN::validaInput[Exception]", e);
        }
        return false; // String Não OK
    }
    
}
