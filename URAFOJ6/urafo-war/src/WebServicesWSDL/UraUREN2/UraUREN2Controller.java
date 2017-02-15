package WebServicesWSDL.UraUREN2;

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
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.CadastroPrePagoVODocument;
import br.com.vivo.fo.atendimento.vo.CadastroPrePagoVODocument.CadastroPrePagoVO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloTODocument.FechaProtocoloTO;

import com.indracompany.actions.AbstractAction;

public class UraUREN2Controller extends AbstractAction {

    private static final long serialVersionUID = -5807422643071319184L;

    private URENPrePagoVODocument inputVODoc;
    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraUREN2Controller");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("UREN2".equals(mapping.getParameter())) {
			return UREN2(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward UREN2(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String            xmlOut = "";
        String             saida = "";
        String idLinhaTelefonica = "";
        String    idPessoaDePara = "";
        String tagProtocoloVazio = "<nrProtocolo></nrProtocolo>";

        XmlManager    xmlManager = new XmlManager();
        int operacao = 0;
        URENPrePagoVO inputVO;

        if(validaInput(input)){
            inputVO = URENPrePagoVO.Factory.newInstance();
            inputVO = inputVODoc.getURENPrePagoVO();

            // Verificar Tags necessárias
            try{
                if(inputVO.getOperacao()==null || inputVO.getOperacao().trim().equals("")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",tagProtocoloVazio);
                }
                operacao = Integer.parseInt(inputVO.getOperacao().trim());

            }catch(NumberFormatException nfe){
                 saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",tagProtocoloVazio);
            }

            if(saida.length()>0){
            	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            	
                response.setContentType(Constant.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                return null;
            }

            if(operacao == 2){
                if(null == inputVO.getCdDDD()         || inputVO.getCdDDD().trim().equals("")             /*DDD*/ ||
                   null == inputVO.getCdNumTelefone() || inputVO.getCdNumTelefone().trim().equals("")   /*LINHA*/ ||
                   null == inputVO.getNrCEP()         || inputVO.getNrCEP().trim().equals("")             /*CEP*/ ||
                   null == inputVO.getDsEndereco()    || inputVO.getDsEndereco().trim().equals("") /*LOGRADOURO*/ ||
                   null == inputVO.getCdTransacao()   || inputVO.getCdTransacao().trim().equals("")   /*cdTrans*/ ||
                   null == inputVO.getCdUsuario()     || inputVO.getCdUsuario().trim().equals("")     /*usuario*/ ||
                   null == inputVO.getNrEndereco()    || inputVO.getNrEndereco().trim().equals("")   /*nrEndereco*/ ||
                   null == inputVO.getDsBairro()      || inputVO.getDsBairro().trim().equals("")    /*Bairro*/ ||
                   null == inputVO.getDsMunicipio()   || inputVO.getDsMunicipio().trim().equals("") /*Municipio*/ ||
                   null == inputVO.getDsCidade()      || inputVO.getDsCidade().trim().equals("")  /*cidade*/ ||
                   null == inputVO.getSgUF()          || inputVO.getSgUF().trim().equals("") /*UF*/){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",tagProtocoloVazio);

                }else if(inputVO.getNrCPF().length() > 11 || inputVO.getCdDDD().length() != 2   ||
                      inputVO.getCdNumTelefone().length() < 8 || inputVO.getCdNumTelefone().length() > 9){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", tagProtocoloVazio);
                }

            } else if (operacao == 4) {/* RECADASTRO CPF  */
            	if(null == inputVO.getNrCPF()         || inputVO.getNrCPF().trim().equals("")             	   /*CPF*/ ||
            			null == inputVO.getCdDDD()         || inputVO.getCdDDD().trim().equals("")             /*DDD*/ ||
                        null == inputVO.getCdNumTelefone() || inputVO.getCdNumTelefone().trim().equals("")   /*LINHA*/ ||
                        null == inputVO.getNrCEP()         || inputVO.getNrCEP().trim().equals("")             /*CEP*/ ||
                        null == inputVO.getDsEndereco()    || inputVO.getDsEndereco().trim().equals("") /*LOGRADOURO*/ ||
                        null == inputVO.getCdTransacao()   || inputVO.getCdTransacao().trim().equals("")   /*cdTrans*/ ||
                        null == inputVO.getCdUsuario()     || inputVO.getCdUsuario().trim().equals("")     /*usuario*/ ||
                        null == inputVO.getNrEndereco()    || inputVO.getNrEndereco().trim().equals("")   /*nrEndereco*/ ||
                        null == inputVO.getDsBairro()      || inputVO.getDsBairro().trim().equals("")    /*Bairro*/ ||
                        null == inputVO.getDsMunicipio()   || inputVO.getDsMunicipio().trim().equals("") /*Municipio*/ ||
                        null == inputVO.getDsCidade()      || inputVO.getDsCidade().trim().equals("")  /*cidade*/ ||
                        null == inputVO.getSgUF()          || inputVO.getSgUF().trim().equals("") /*UF*/){
            		
                     saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",tagProtocoloVazio);

                     }else if(inputVO.getNrCPF().length() > 11 || inputVO.getCdDDD().length() != 2   ||
                           inputVO.getCdNumTelefone().length() < 8 || inputVO.getCdNumTelefone().length() > 9){
                         saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", tagProtocoloVazio);
                     }
            }else if(operacao == 1 || operacao == 3){
                if(null == inputVO.getNmCliente()     || inputVO.getNmCliente().trim().equals("")        /*NOME*/ ||
                   null == inputVO.getNrCPF()         || inputVO.getNrCPF().trim().equals("")             /*CPF*/ ||
                   null == inputVO.getCdDDD()         || inputVO.getCdDDD().trim().equals("")             /*DDD*/ ||
                   null == inputVO.getCdNumTelefone() || inputVO.getCdNumTelefone().trim().equals("")   /*LINHA*/ ||
                   null == inputVO.getCdTransacao()   || inputVO.getCdTransacao().trim().equals("")   /*cdTrans*/ ||
                   null == inputVO.getCdUsuario()     || inputVO.getCdUsuario().trim().equals("")){
                        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",tagProtocoloVazio);
                }else if(inputVO.getNrCPF().length() > 11 || inputVO.getCdDDD().length() != 2   ||
                      inputVO.getCdNumTelefone().length() < 8 || inputVO.getCdNumTelefone().length() > 9){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",tagProtocoloVazio);
                }

            }else{
                // demais operações
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",tagProtocoloVazio);
            }

            if(saida.length()>0){
            	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            	
                response.setContentType(Constant.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                return null;
            }

            // Continuar com execução normal
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));
                String xmlTux = XmlManager.MakeXmlIn("30", "GRVNOVOPRE", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.log("[XMLOUT]>>"+xmlOut+"<<");

                xmlManager.parseXmlOut(xmlOut);

                try{
                    MsgDocument msgInput = MsgDocument.Factory.parse(xmlOut);
                    CadastroPrePagoVODocument doc = CadastroPrePagoVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
                    CadastroPrePagoVO cadastroPrePagoVO = doc.getCadastroPrePagoVO();
                    idPessoaDePara = cadastroPrePagoVO.getIdPessoaDepara();
                    idLinhaTelefonica = cadastroPrePagoVO.getIdLinhaTelefonica();

                }catch(Exception ex){
                    logURA.log("UREN2::UREN2[Exception]", ex);
                    logURA.log("[UREN_ERROR]="+ex.getMessage());
                }

                String palitagem = "";
                switch(operacao){
                    case 1 : palitagem="CADPRE";
                    break;
                    case 2 : palitagem="ALTERACAOCADPRE";
                    break;
                    case 3 : palitagem="TROCATITPRE";
                    break;
                    case 4 : palitagem="ALTERACAOCADPRE";
                    break;
                    default: break;
                }

                String nrProtocolo = "";
                if((inputVO.getPalitagem() == null || !"1".equals(inputVO.getPalitagem())) && operacao == 1) {
                    nrProtocolo = this.registrarProtocolo(inputVO,palitagem,idLinhaTelefonica,idPessoaDePara,operacao);
                }

                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_PROSSEGUE_ATEND,"<nrProtocolo>"+nrProtocolo+"</nrProtocolo>");

            }catch (TuxedoWarningException twe){
                logURA.log("UREN2::UREN2[TuxedoWarningException]", twe);
                if(twe.getXmlHeader().getError().equals("0001")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("0002")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"05",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("0003")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("0004")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("0005")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"03",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("1005") || twe.getXmlHeader().getError().equals("1006") || twe.getXmlHeader().getError().equals("1007")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"01",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("0599") || twe.getXmlHeader().getError().equals("1100") || twe.getXmlHeader().getError().equals("1123")){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"06",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("1124")  ){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"09",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("1125")  ){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"02",tagProtocoloVazio);

                }else if (twe.getXmlHeader().getError().equals("1126")  ){
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"10",tagProtocoloVazio);

                }else{
                    saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA,tagProtocoloVazio);
                }

            }catch (TuxedoErrorException tee){
                logURA.log("UREN2::UREN2[TuxedoErrorException]", tee);
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, tagProtocoloVazio);

            }catch (Exception ex){
                logURA.log("UREN2::UREN2[Exception]", ex);
                String msgErro = ex.getMessage();
                if(ex instanceof TPException){
                    try {
                        String exception = ControlXMLExceptionLookup.getXMLString(ex);
                        String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                        msgErro = xmlRetorno[3];
                    }catch(Exception e) {}
                }
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_FORA_AR, "", "<!--"+msgErro+"-->");
            }
        }else{
            // Abortar por erro de Parse dos dados enviados pela URA
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", tagProtocoloVazio);
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
            // validar tamanho de campos de entrada
            /*if(inputVODoc.getURENPrePagoVO().getCdDDD().length() != 2 ||
            inputVODoc.getURENPrePagoVO().getCdNumTelefone().length() != 8 ||
            inputVODoc.getURENPrePagoVO().getNrCPF() .length() > 11)
                return false;*/
            // String OK
            return true;

        }catch(Exception e){// String Não OK
            logURA.log("UREN2::validaInput[Exception]", e);
            return false;
        }
    }

    /**
     * @description abrir protocolo
     * @param
     */
    public String registrarProtocolo(URENPrePagoVO inputVO,String palitagem,String idLinhaTelefonica,String idPessoaDePara,int operacao){
        String nrProtocolo = "";
        XmlManager xmlManager = new XmlManager();

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();

            StringBuffer inputXMLAbreProtocolo = new StringBuffer();
            //inputXMLAbreProtocolo.append("<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>1</user><service/><subSystem/></msgHdr><msgBody>");
            inputXMLAbreProtocolo.append("<idTipoAberturaProtocolo>2</idTipoAberturaProtocolo>");
            inputXMLAbreProtocolo.append("<idSistemaOrigem>30</idSistemaOrigem>");
            inputXMLAbreProtocolo.append("<cdAreaRegistro>"+inputVO.getCdDDD()+"</cdAreaRegistro>");
            inputXMLAbreProtocolo.append("<nrTelefone>"+inputVO.getCdNumTelefone()+"</nrTelefone>");
            //inputXMLAbreProtocolo.append("</msgBody></msg>");

            String xmlTux = XmlManager.MakeXmlIn("30", "ATDABREPROT", inputXMLAbreProtocolo.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOutAbreProt = jatmi.executeCommnad("TuxConnectorURA", xmlTux);

            MsgDocument msgInput = MsgDocument.Factory.parse(xmlOutAbreProt);
            AbreProtocoloOutTODocument doc = AbreProtocoloOutTODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            AbreProtocoloOutTO abreProtocoloOutTO =  doc.getAbreProtocoloOutTO();
            nrProtocolo = abreProtocoloOutTO.getNrProtocolo();

            if(operacao == 1 || operacao == 3){
                StringBuffer sb = new StringBuffer();
                //sb.append("<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>1</user><service/><subSystem/></msgHdr><msgBody>");
                sb.append("<nrProtocolo>").append(nrProtocolo).append("</nrProtocolo>");
                sb.append("<operacao>alterar</operacao>");
                sb.append("<incAberto>0</incAberto>");
                sb.append("<incPendente>0</incPendente>");
                sb.append("<AlterProtocoloTO>");
                sb.append("<cdAreaRegistro>").append(inputVO.getCdDDD()).append("</cdAreaRegistro>");
                sb.append("<nrTelefone>").append(inputVO.getCdNumTelefone()).append("</nrTelefone>");
                sb.append("<idSistemaOrigem>30</idSistemaOrigem>");
                sb.append("<idLinhaTelefonica>"+idLinhaTelefonica+"</idLinhaTelefonica>");
                sb.append("<idPessoaDePara>"+idPessoaDePara+"</idPessoaDePara>");
                sb.append("</AlterProtocoloTO>");
                //sb.append("</msgBody></msg>");

                xmlTux = XmlManager.MakeXmlIn("30", "ATDALTERPROT", sb.toString());
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                String xmlOutAlteracao = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            }

            // palitagem
            StringBuffer inputXMLPalitagem = new StringBuffer();
            //inputXMLPalitagem.append("<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>1</user><service/><subSystem/></msgHdr><msgBody>");
            inputXMLPalitagem.append("<nrProtocolo>"+nrProtocolo+"</nrProtocolo>");
            inputXMLPalitagem.append("<idSistema>30</idSistema>");
            inputXMLPalitagem.append("<idGrupoAbertura>1534</idGrupoAbertura>");
            inputXMLPalitagem.append("<cdServico>"+palitagem+"</cdServico>");
            inputXMLPalitagem.append("<dsComentario>"+palitagem+"</dsComentario>");
            inputXMLPalitagem.append("<idCanal>919</idCanal>");
            inputXMLPalitagem.append("<idUsuario>1</idUsuario>");
            //inputXMLPalitagem.append("</msgBody></msg>");

            xmlTux = XmlManager.MakeXmlIn("30", "REGPALITAGEFO", inputXMLPalitagem.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOutPalitagem = jatmi.executeCommnad("TuxConnectorURA", xmlTux);

            FechaProtocoloTO protocoloFecha = FechaProtocoloTO.Factory.newInstance();
            protocoloFecha.setNrProtocolo(nrProtocolo);
            protocoloFecha.setIdSistemaOrigem("30");

            StringBuffer inputXMLFecharProtocolo = new StringBuffer();
            //inputXMLFecharProtocolo.append("<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>1</user><service/><subSystem/></msgHdr><msgBody>");
            inputXMLFecharProtocolo.append("<nrProtocolo>"+nrProtocolo+"</nrProtocolo>");
            inputXMLFecharProtocolo.append("<idSistemaOrigem>30</idSistemaOrigem>");
            //inputXMLFecharProtocolo.append("</msgBody></msg>");

            xmlTux = XmlManager.MakeXmlIn("30", "ATDFECHAPROT", inputXMLFecharProtocolo.toString());
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            String xmlOutFechamento = jatmi.executeCommnad("TuxConnectorURA", xmlTux);

        }catch (Exception twe){
            logURA.log("UREN2::registrarProtocolo[Exception]", twe);
            return "";
        }
        return nrProtocolo;
    }

}
