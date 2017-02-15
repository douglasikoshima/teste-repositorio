package WebServicesWSDL.UraURCC;

import java.io.BufferedOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import noNamespace.MsgDocument;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ura.servicos.vo.ControleConsumoItemDocument.ControleConsumoItem;
import ura.servicos.vo.ControleConsumoVODocument;
import ura.servicos.vo.ControleConsumoVODocument.ControleConsumoVO;
import ura.servicos.vo.ListaControleConsumoVODocument.ListaControleConsumoVO;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;

import com.indracompany.actions.AbstractAction;


public class UraURCCController extends AbstractAction {

    private static final long serialVersionUID = 8635093415101753640L;

    private ControleConsumoVO inputVO = ControleConsumoVO.Factory.newInstance();
    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraURCCController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("URCC".equals(mapping.getParameter())) {
			return URCC(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }


    public ActionForward URCC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><ControleConsumoVO xmlns="tuxProxyBE/vo"><cdDDD>41</cdDDD><cdNumTelefone>92490020</cdNumTelefone><status>P</status><opcao></opcao><servico>01</servico><dia></dia></ControleConsumoVO></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOut = "";
        String saida  = "";

        XmlManager xmlManager = new XmlManager();
        int servico = 0;
        ListaControleConsumoVO lista = ListaControleConsumoVO.Factory.newInstance();

        if(!validaXML(input)){
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PARAM_INVALIDO);
        }else{
            try{
                servico = Integer.parseInt(inputVO.getServico());
                int dia = 0;
                switch(servico){
                    case 1:
                        inputVO.setServico("DEMANDA");
                        break;
                    case 2:
                        inputVO.setServico("MV_DAILY");
                        break;
                    case 3:
                        inputVO.setServico("MV_DAYOFWEEK");
                        if(inputVO.getStatus().equals("C") && inputVO.getOpcao().equals("1")){
                            dia = Integer.parseInt(inputVO.getDia());
                            if((dia < 1 || dia > 7)) {
                                throw new Exception("dia inválido");
                            }
                        }
                        break;
                    case 4:
                        inputVO.setServico("MV_INVOICERELEASE");
                        break;
                    case 5:
                        inputVO.setServico("MV_THRESHOLD");
                        break;
                    case 6:
                        inputVO.setServico("MV_HOTLINETHRESHOLD");
                        break;
                    default:
                        throw new Exception("Código inválido");
                }

                if(inputVO.getStatus().equals("C") && (!inputVO.getOpcao().equals("0") && !inputVO.getOpcao().equals("1"))) {
                    throw new Exception("Opção inválida");
                }

                if(inputVO.getStatus().equals("P") && servico == 1) {
                    throw new Exception("Opção inválida");
                }

            }catch(Exception e){
                logURA.log("uraURCC::URCC[Exception]", e);
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_PARAM_INVALIDO);

                saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
                
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
                String xmlTux = XmlManager.MakeXmlIn("30", "CONSUMO", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.log("[XMLOUT]>>"+xmlOut+"<<");

                xmlManager.parseXmlOut(xmlOut);

                if(servico == 1){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PROSSEGUE_ATEND);

                }else{
                    // Consulta se o serviço está ativo ou desativo
                    if(inputVO.getStatus().equalsIgnoreCase("p")){
                        StringBuffer tarifa = new StringBuffer();
                        tarifa.append("<indTarifa>N</indTarifa><vlTarifa>0</vlTarifa>");
                        lista = (ListaControleConsumoVO) xmlManager.outputInnerBody(xmlOut);

                        if(lista == null){
                           saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04");

                        }else{
                            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04");
                            for(int i=0; i<lista.sizeOfControleConsumoItemArray(); i++){
                                ControleConsumoItem item = lista.getControleConsumoItemArray(i);
                                if(item == null) {
                                    break;
                                }

                                if(inputVO.getServico().equals(item.getNome())){
                                    if(item.getValor().equals("")){
                                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04");
                                        break;

                                    }else if(item.getValor().equals("OFF")){
                                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"09",tarifa.toString());
                                        break;

                                    }else{
                                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"08",tarifa.toString());
                                        break;
                                    }
                                }
                            }
                        }

                    }else{
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PROSSEGUE_ATEND);
                    }
                }

            }catch(TuxedoErrorException tee){
                logURA.log("uraURCC::URCC[Exception]", tee);
                String e = tee.getXmlHeader().getError();
                saida = tratarError(e, tee);

            }catch(TuxedoWarningException twe){
                logURA.log("uraURCC::URCC[Exception]", twe);
                String e = twe.getXmlHeader().getError();
                saida = tratarWarnning(e, twe);

            }catch(Exception ex){
                logURA.log("uraURCC::URCC[Exception]", ex);
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
        saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        
        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    /**
     * Faz o tratamento de erros quando for um E
     */
    private String tratarError(String erro,TuxedoErrorException tee){
        return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04",tee);
    }

    /**
     * Faz o tratamento de erros quando for W
     */
    private String tratarWarnning(String erro, TuxedoWarningException twe){
        int codigo = Integer.parseInt(erro);

        if(codigo==999) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR);
        }

        int subrotina[] ={1263,1269,1277,1289,3031,5109,4276,9029,4287,2659,3029,3000};

        for(int i=0;i<subrotina.length;i++) {
            if(codigo==subrotina[i]) {
                return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04",twe);
            }
        }

        if(codigo == 4275) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",twe);
        }

        if(codigo>=50 && codigo <=56) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",twe);
        } else if(codigo == 1 || codigo == 1001 || codigo==3 || codigo==6 || codigo==100 || codigo==7890) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"04",twe);
        } else if(codigo==2 || codigo==7) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",twe);
        } else if(codigo==4) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"09",twe);
        } else if(codigo==5) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"08",twe);
        } else if(codigo==501) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01",twe);
        } else if(codigo==502) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",twe);
        } else if(codigo==57) {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05",twe);
        } else {
            return XmlManager.createRetorno(Constant.URA_STATCOM_OK,"99",twe);
        }
    }

    /**
     * Validar o XML de entrada
     */
    public boolean validaXML(String xmlIn){
        ControleConsumoVODocument inputVODoc;
        try{
            MsgDocument msgInput = MsgDocument.Factory.parse(xmlIn);
            inputVODoc = ControleConsumoVODocument.Factory.newInstance();
            inputVODoc = ControleConsumoVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            this.inputVO = inputVODoc.getControleConsumoVO();
            if(!inputVO.getStatus().equals("P") && !inputVO.getStatus().equals("C")) {
                return false;
            }
            return true;

        }catch(Exception e){
            logURA.log("uraURCC::validaXML[Exception]", e);
            return false;
        }
    }
}
