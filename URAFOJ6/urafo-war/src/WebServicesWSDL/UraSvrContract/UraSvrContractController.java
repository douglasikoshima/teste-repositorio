package WebServicesWSDL.UraSvrContract;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import noNamespace.MsgDocument;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import tuxProxyBE.vo.CONTADocument.CONTA;
import tuxProxyBE.vo.DetalhesSaldoItemDocument.DetalhesSaldoItem;
import tuxProxyBE.vo.DetalhesSaldoVODocument.DetalhesSaldoVO;
import tuxProxyBE.vo.FavoritosVODocument.FavoritosVO;
import tuxProxyBE.vo.HistoricoMovimentosItemDocument.HistoricoMovimentosItem;
import tuxProxyBE.vo.HistoricoMovimentosVODocument.HistoricoMovimentosVO;
import tuxProxyBE.vo.LINHADocument;
import tuxProxyBE.vo.LINHADocument.LINHA;
import tuxProxyBE.vo.ServicoVODocument.ServicoVO;
import tuxProxyBE.vo.ServicosItemDocument.ServicosItem;
import weblogic.apache.xml.serialize.OutputFormat;
import weblogic.apache.xml.serialize.XMLSerializer;
import weblogic.wtc.jatmi.TPException;
import WebServicesWSDL.commons.utils.beans.LinhaBean;
import WebServicesWSDL.commons.utils.businessDelegate.JATMIBusinessDelegate;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.ControlXMLExceptionLookup;
import WebServicesWSDL.exception.TuxedoErrorException;
import WebServicesWSDL.exception.TuxedoWarningException;
import WebServicesWSDL.xml.XmlManager;
import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked","unused"})
public class UraSvrContractController extends AbstractAction {

    private static final long serialVersionUID = 6637907469088778525L;

    public static final String URA_ATIVA = "1";
    public static final String URA_DESATIVA = "0";

    public static final String URA_SERV_BLOQUEIO_DDD = "01";
    public static final String URA_SERV_BLOQUEIO_PERDA = "02";
    public static final String URA_SERV_BLOQUEIO_ROUBO = "03";
    public static final String URA_SERV_CAIXA_POSTAL = "04";
    public static final String URA_SERV_CONFERENCIA = "05";
    public static final String URA_SERV_DESVIO_CHAMADA = "06";
    public static final String URA_SERV_CHAMADA_ESPERA = "07";
    public static final String URA_SERV_IDENTIFICADOR = "08";

    public static final String URA_SERV_BLOQUEIO_DDI = "09";
    public static final String URA_SERV_AVISO_MSG = "10";
    public static final String URA_SERV_VIVO_WAP = "11";
    public static final String URA_SERV_ANTIBINA = "12";

    public static final String URA_DESVIO_TOTAL = "1";
    public static final String URA_DESVIO_OCUPADO = "2";
    public static final String URA_DESVIO_NAO_OCUPADO = "3";

    public static final String URA_STATUS_PESQUISA = "P";
    public static final String URA_STATUS_CONFIRMA = "C";

    public static final String URA_STATCOM_OK = "1";
    public static final String URA_STATCOM_ERRO = "2";
    public static final String URA_STATCOM_FORA_AR = "3";

    public static final String URA_CODIRET_PROSSEGUE_ATEND = "00";
    public static final String URA_CODIRET_CONTA_INEXISTE = "01";
    public static final String URA_CODIRET_PARAM_INVALIDO = "02";
    public static final String URA_CODIRET_ERRO_BASE = "03";
    public static final String URA_CODIRET_ERRO_SUBROTINA = "04";
    public static final String URA_CODIRET_CLIENTE_RESTR = "05";
    public static final String URA_CODIRET_OUTROS_RESTR = "06";
    public static final String URA_CODIRET_REGIONAL_RESTR = "07";
    public static final String URA_CODIRET_HOTLINE = "11";

    public static final String URA_CODIRET_NUMERO_INVALIDO    = "01";
    public static final String URA_CODIRET_FAVORI_REPETIDO    = "05";
    public static final String URA_CODIRET_FAVORI_SUBSCRITO   = "06";
    public static final String URA_CODIRET_SALDO_INSUFICIENTE = "07";
    public static final String URA_CODIRET_DDD_INDISPONIVEL   = "08";
    public static final String URA_CODIRET_SEM_ID             = "99";

    private transient XmlManager xmlManager = new XmlManager();

    private String statusURAD = "";
    private String statusURVF = "";
    private String codigoServico = "";
    private boolean perdaRoubo = false;

    private transient LinhaBean linhaBean = new LinhaBean();
    private transient ArrayList favoritos = new ArrayList();
    private transient WebServicesWSDL.commons.utils.geral.Log logURA = new WebServicesWSDL.commons.utils.geral.Log("UraSvrContractController");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

    	if ("URAD".equals(mapping.getParameter())) {
			return URAD(mapping, form, request, response);
		} else if ("URCS".equals(mapping.getParameter())) {
			return URCS(mapping, form, request, response);
		}else if ("URVF".equals(mapping.getParameter())) {
			return URVF(mapping, form, request, response);
		}else if ("oldURVF".equals(mapping.getParameter())) {
			return oldURVF(mapping, form, request, response);
		}else if ("UREX".equals(mapping.getParameter())) {
			return UREX(mapping, form, request, response);
		}else if ("URSD".equals(mapping.getParameter())) {
			return URSD(mapping, form, request, response);
		}else if ("URIP".equals(mapping.getParameter())) {
			return URIP(mapping, form, request, response);
		}

    	return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
        return null;
    }

    public ActionForward URAD(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><cdNumTelefone>71010132</cdNumTelefone><servico>02</servico><opcao>1</opcao><status>C</status></msgBody></msg>
        String input = request.getParameter("input");

        logURA.logInfo("uraSvrContract::URAD[XML-IN]: "+ input);

        String saida     =  "";
        String xmlInput  = null;
        String xmlOutput = null;
        String servico   = "";
        String statusEntrada = "";

        this.perdaRoubo  = false;
        
        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

            try { statusEntrada = document.getElementsByTagName("status").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {logURA.logWarn("uraSvrContract::convertInputXML[Exception]", e);}
            
            logURA.logInfo("uraSvrContract::URAD[statusEntrada]: "+ statusEntrada);
        } catch (Exception ex ) {
        	logURA.logWarn("uraSvrContract::URAD[Exception:statusEntrada]", ex);
        }

        try {
        	
            xmlInput = convertInputXML( input, "setServico","URAD");
            logURA.logInfo("uraSvrContract::URAD[convertInputXML-OUT]: "+xmlInput);

        }catch(Exception e){
            logURA.logWarn("uraSvrContract::URAD[Exception]", e);

            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO );

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        // Verificar qual o tipo de linha
        try{
            String xmlTipoLinha = "<cdDDD>"+this.linhaBean.getDdd()+"</cdDDD><cdNumTelefone>"+this.linhaBean.getNrLinha()+"</cdNumTelefone>";
            servico = this.getServicoLegado(xmlTipoLinha);
            logURA.logInfo("uraSvrContract::URAD[getServicoLegado-OUT]: "+servico);

        } catch(TuxedoErrorException tee){
			logURA.logWarn("uraSvrContract::URAD[TuxedoErrorException]", tee);
			if(tee.getXmlHeader().getError().equals("0013")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "0013", tee); // erro de timeout
            } else if(tee.getXmlHeader().getError().equals("0010")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "0010", tee); // erro de comunicação de tuxedo
            } else{
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, tee);
            }
        }catch(TuxedoWarningException twe){
            logURA.logWarn("uraSvrContract::URAD[TuxedoWarningException]", twe);
            if(twe.getXmlHeader().getError().equals("0001")) {
                saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_CONTA_INEXISTE);
            } else {
                saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA);
            }

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        }catch(Exception ex){
            logURA.logWarn("uraSvrContract::URAD[Exception]", ex);
            saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA);

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
            
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        // caso for perda ou roubo
        //if(this.perdaRoubo && this.statusURAD.equalsIgnoreCase("P")){
         if(this.perdaRoubo && "P".equalsIgnoreCase(this.statusURAD)){
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlLinha = "<cdDDD>"+this.linhaBean.getDdd()+"</cdDDD><cdNumTelefone>"+this.linhaBean.getNrLinha()+"</cdNumTelefone>";
                LINHA linhaVO = LINHA.Factory.newInstance();

                String xmlTux = XmlManager.MakeXmlIn("30", "GETSTATUSLN", xmlLinha);
                logURA.logInfo("uraSvrContract::URAD[GETSTATUSLN]: "+xmlTux);

                String xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.logInfo("uraSvrContract::URAD[GETSTATUSLN:XMLOUT]: "+xmlOut);

                linhaVO.setEstadoLinha(getEstadoLinha(xmlOut));

                if(linhaVO == null) {
                    saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_ERRO_SUBROTINA);
                }

                if(linhaVO.getEstadoLinha().equalsIgnoreCase("A")){
                    saida = XmlManager.createRetorno(URA_STATCOM_OK,"10");

                }else{
                    saida = XmlManager.createRetorno(URA_STATCOM_OK,"05");
                }

            }catch(Exception ex){
                logURA.logWarn("uraSvrContract::URAD[Exception]", ex);
                String msgErro = ex.getMessage();
                if(ex instanceof TPException){
                    try {
                        String exception = ControlXMLExceptionLookup.getXMLString(ex);
                        String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                        msgErro = xmlRetorno[3];
                    }catch(Exception e) {}
                }
                saida = XmlManager.createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA,"<!--"+msgErro+"-->");

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

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", servico, xmlEnt);
            logURA.logInfo("uraSvrContract::URAD[XML-IN:" + servico+ "]: " +xmlTux);


            xmlOutput = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            //xmlOutput = "<?xml version=\'1.0\' encoding=\'ISO-8859-1\'?><msg><msgHdr><user>30</user><service>TUXNGINBE</service><subSystem>Undefined Data</subSystem><serverElapsedTime>-112913</serverElapsedTime><statusCode>24I0000</statusCode><statusText>Execução OK</statusText></msgHdr><msgBody><ServicoVO xmlns=\"tuxProxyBE/vo\"><ServicosItem><codigo>CALLID</codigo><status>Ativado</status><nome>BASIC</nome><descricao>BASIC</descricao><validade></validade></ServicosItem><ServicosItem><codigo>CALLWAIT</codigo><status>Ativado</status><nome>BASIC</nome><descricao>BASIC</descricao><validade></validade></ServicosItem><ServicosItem><codigo>VOICEMAIL</codigo><status>Ativado</status><nome>BASIC</nome><descricao>BASIC</descricao><validade></validade></ServicosItem><ServicosItem><codigo>UNBLOCKDDI</codigo><nome>Bloqueio LDI</nome><descricao>Bloqueio LDI</descricao><status>Ativado</status><validadeInicial></validadeInicial><validade></validade></ServicosItem></ServicoVO></msgBody></msg>";
         
            logURA.logInfo("uraSvrContract::URAD[XML-OUT:" + servico+ "]: "+ xmlOutput);
            logURA.logInfo("uraSvrContract::URAD[statusURAD]: "+ this.statusURAD);
            logURA.logInfo("uraSvrContract::URAD[statusEntrada]: "+ statusEntrada);
            
            /* POG - Variavel statusURAD estava perdendo valor  */
            if (this.statusURAD == null || "".equals(this.statusURAD)) {
            	this.statusURAD  = statusEntrada;
            	logURA.logInfo("uraSvrContract::URAD[]statusURAD sem Valor : Utilizado valor de statusEntrada: "+ this.statusURAD);
            	logURA.logInfo("uraSvrContract::URAD[statusURAD]: "+ this.statusURAD);
            }

            xmlManager.parseXmlOut(xmlOutput);
            if(!this.perdaRoubo && "P".equalsIgnoreCase(this.statusURAD)){
                saida = this.getStatusServico(xmlOutput, servico);
            }else{
                saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND);
            }
            logURA.logInfo("uraSvrContract::URAD[XML-OUT]:"+saida);

        }catch(TuxedoWarningException twe){
            logURA.logWarn("uraSvrContract::URAD[TuxedoWarningException]", twe);
            if(twe.getXmlHeader().getError().equals("0000")){
                if(twe.getXmlHeader().getStatusText().equalsIgnoreCase("Operacao de DESATIVACAO Não realizada")){
                    saida = createRetorno(URA_STATCOM_OK, "10" );

                }else if(twe.getXmlHeader().getStatusText().equalsIgnoreCase("Operacao de ATIVACAO Não realizada")){
                    saida = createRetorno(URA_STATCOM_OK, "09" );
                }
            }else{
                saida = XmlManager.createRetorno(URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND, twe);
            }

        }catch(TuxedoErrorException tee){
            logURA.log("uraSvrContract::URAD[TuxedoErrorException]", tee);
			if(tee.getXmlHeader().getError().equals("9015")){
				saida = XmlManager.createRetorno(URA_STATCOM_OK, "05", tee);
			} else if(tee.getXmlHeader().getError().equals("9016") || tee.getXmlHeader().getError().equals("9017")
			   || tee.getXmlHeader().getError().equals("9018")){
				saida = XmlManager.createRetorno(URA_STATCOM_OK, "99", tee);
			} else if(tee.getXmlHeader().getError().equals("9019")){
				saida = XmlManager.createRetorno(URA_STATCOM_OK, "02", tee);
			} else if(tee.getXmlHeader().getError().equals("9020") || 
			          tee.getXmlHeader().getError().equals("9021") || 
					  tee.getXmlHeader().getError().equals("9022")){
				saida = XmlManager.createRetorno(URA_STATCOM_OK, "10", tee);
			} else if(tee.getXmlHeader().getError().equals("5001")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK, URA_CODIRET_CONTA_INEXISTE, tee);
            } else if(tee.getXmlHeader().getError().equals("3002")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK, tee.getXmlHeader());
            }else if(tee.getXmlHeader().getError().equals("0013")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "0013", tee); // erro de timeout
            } else if(tee.getXmlHeader().getError().equals("0010")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "0010", tee); // erro de comunicação de tuxedo
            } else{
                saida = XmlManager.createRetornoURAD(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA, tee);
            }

        }catch(Exception ex){
            logURA.logWarn("uraSvrContract::URAD[Exception]", ex);
            ex.printStackTrace();
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
        	saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);
        	
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    public ActionForward URCS(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Dado de teste.
        //<?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URCS</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>91148018</cdNumTelefone></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String saida         = "";
        String xmlInputSaldo = null;
        String xmlInputMovim = null;
        String xmlOutput     = null;

        try{
            xmlInputSaldo = convertInputXML( input, "getDetalhesSaldo","URCS");
            xmlInputMovim = convertInputXML( input, "getHistoricoMovimentos","URCS");

        }catch(Exception e){
            logURA.log("uraSvrContract::URCS[Exception]", e);
            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO );

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            StringBuffer result = new StringBuffer();
            DetalhesSaldoVO saldo = null;
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlEnt = xmlInputSaldo.substring(xmlInputSaldo.indexOf("<msgBody>")+9,xmlInputSaldo.lastIndexOf("</msgBody>"));

                String xmlTux = XmlManager.MakeXmlIn("30", "TUXNGINBE", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOutput = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.log("[XMLOUT]>>"+xmlOutput+"<<");

                if(xmlOutput!=null) {
                    xmlOutput = xmlOutput.replaceAll("\n","");
                }

                saldo = (DetalhesSaldoVO) xmlManager.outputInnerBody( xmlOutput );

            }catch(Exception e){
                logURA.log("uraSvrContract::URCS[Exception]", e);
                // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
                // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
                // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(e);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    String erroComment = "<!-- " + xmlRetorno[3] + " -->";

                    if (xmlRetorno[2] != null && xmlRetorno[2].trim().equalsIgnoreCase("5001")) {
                        saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>" + erroComment);
                    } else {
                        saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>" + erroComment );
                    }

                }catch(Exception ex) {
                    logURA.log("uraSvrContract::URCS[Exception]", ex);
                    saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>");

                }finally{
                    response.setContentType(Constant.SContentType);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                    bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
                return null;
            }

            HistoricoMovimentosVO movim = null;
            try{
                JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
                String xmlEnt = xmlInputMovim.substring(xmlInputMovim.indexOf("<msgBody>")+9,xmlInputMovim.lastIndexOf("</msgBody>"));

                String xmlTux = XmlManager.MakeXmlIn("30", "TUXNGINBE", xmlEnt);
                logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

                xmlOutput = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
                logURA.log("[XMLOUT]>>"+xmlOutput+"<<");

                if(xmlOutput!=null) {
                    xmlOutput = xmlOutput.replaceAll("\n","");
                }

                movim = (HistoricoMovimentosVO) xmlManager.outputInnerBody( xmlOutput );

            }catch(Exception e){
                logURA.log("uraSvrContract::URCS[Exception]", e);
                // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
                // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
                // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(e);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    String erroComment = "<!-- " + xmlRetorno[3] + " -->";

                    if (xmlRetorno[2] != null && xmlRetorno[2].trim().equalsIgnoreCase("5001")) {
                        saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>" + erroComment );
                    } else {
                        saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>" + erroComment );
                    }

                }catch(Exception ex){
                    logURA.log("uraSvrContract::URCS[Exception]", ex);
                    saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>");

                }finally{
                    response.setContentType(Constant.SContentType);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                    bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                }
                return null;
            }

            // soma saldos
            double valor = 0.00;
            for(int i=0; i<saldo.sizeOfDetalhesSaldoItemArray(); i++){
                DetalhesSaldoItem item = saldo.getDetalhesSaldoItemArray(i);
                try { valor += Double.parseDouble(item.getSaldo()); }
                catch ( Exception e ) {}
            }

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.set( 1900, 00, 01 );
            Date lastRecarga = cal.getTime();
            Date bonusDate = null;
            double bonusValue = 0.00;

            for(int i=0; i < movim.sizeOfHistoricoMovimentosItemArray(); i++){
                HistoricoMovimentosItem item = movim.getHistoricoMovimentosItemArray(i);
                if(item.getMovimento().toLowerCase().indexOf( "recarga" ) > -1){
                    Date valid = fmt.parse( item.getRecargaData() );
                    if(valid.compareTo( lastRecarga ) > 0) {
                        lastRecarga = valid;
                    }
                }else if(item.getMovimento().toLowerCase().indexOf( "bonus" ) > -1){
                    bonusDate = fmt.parse( item.getRecargaData() );
                    bonusValue = Double.parseDouble( item.getRecargaValor() );
                }
            }

            Date validade =  null;
            try{
                if(!saldo.getPrValidadeReal().equals( "" )) {
                    validade = fmt.parse(saldo.getPrValidadeReal());
                }

            }catch(Exception e){
                logURA.log("uraSvrContract::URCS[Exception]", e);
                saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA );

                response.setContentType(Constant.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                return null;
            }

            // monta retorno
            DecimalFormat nrFmt = new DecimalFormat( "####.00" );

            fmt = new SimpleDateFormat( "dd/MM/yyyy" );
            result.append("<dataAtivacao>" + fmt.format( lastRecarga ) + "</dataAtivacao>" );
            result.append("<saldo>" + nrFmt.format( valor ) + "</saldo>");
            result.append("<validade>" + ( validade == null ? "" : fmt.format( validade ) ) + "</validade>");
            result.append("<bonus>" + ( bonusDate == null ? "" : fmt.format( bonusDate ) ) + "</bonus>");
            result.append("<validBonus>" + ( bonusValue <= 0.00 ? "" : nrFmt.format( bonusValue ) ) + "</validBonus>");

            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND, result.toString() );

        }catch(Exception e){
            logURA.log("uraSvrContract::URCS[Exception]", e);
            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA );
        }

        logURA.log("[XMLOUT]"+saida);

        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    /** @jpf:action */
    public ActionForward URVF(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // Dado de teste.
        /*
        <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>91009650</cdNumTelefone><status>P</status></msgBody></msg>
        <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>91868236</cdNumTelefone><status>C</status><telefones><favorito>4191009650</favorito><favorito>4191148018</favorito></telefones></msgBody></msg>
        */
        String input = request.getParameter("input");
        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String saida   = "";
        int maxFavoritos = 0;

        // Verificar qual o tipo de linha
        try{
            maxFavoritos = this.getQuantidadeMaxFavoritos(input);

        }catch(TuxedoWarningException twe){
            logURA.log("uraSvrContract::URVF[TuxedoWarningException]", twe);
            String erroComment = "<!-- " + twe.getXmlHeader().getStatusText() + " -->";
            saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_SEM_ID,twe);

            if(twe.getXmlHeader().getError().equals("9013") || twe.getXmlHeader().getError().equals("9012") || twe.getXmlHeader().getError().equals("9008")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_DDD_INDISPONIVEL, "O DDD nao foi cadastrado na tabela de autorizacao para favoritos");

            } else if(twe.getXmlHeader().getError().equals("9007")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_SALDO_INSUFICIENTE, "Saldo Insuficiente para realizar esta operacao");

            } else if(twe.getXmlHeader().getError().equals("9014")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_FAVORI_SUBSCRITO, "Servico nao subscrito");

            } else if(twe.getXmlHeader().getError().equals("8015") || twe.getXmlHeader().getError().equals("3002")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_FAVORI_REPETIDO,twe);

            }else if(twe.getXmlHeader().getError().equals("8001") || twe.getXmlHeader().getError().equals("7073")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"07",twe);
            }
            /*
            if(twe.getXmlHeader().getError().equals("0001"))
                saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_CONTA_INEXISTE, erroComment);
            else
                saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, erroComment);
            */

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        }catch(Exception ex){
            logURA.log("uraSvrContract::URVF[Exception]", ex);
            saida = XmlManager.createRetorno(URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA, "<!-- "+ex.getMessage()+" -->");

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        String xmlInput = null;
        String xmlOutput = null;
        String service = "getFavoritos";
        String servico = "";

        try{
            initFavoriteInputXML(input);
            if(this.statusURVF.equalsIgnoreCase("P")) {
                service = "getFavoritos";
                xmlInput = favoriteInputXML( input, service ,"URVF", null);
            }else{
                service = "setFavorito";
                if ( favoritos != null && favoritos.size() > 0 ) {
                    xmlInput = favoriteInputXML( input, service ,"URVF", String.valueOf(maxFavoritos));

                }else{
                    saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO );

                    saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

                    response.setContentType(Constant.SContentType);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                    bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    return null;
                }
            }

        }catch(IndexOutOfBoundsException e){
            logURA.log("uraSvrContract::URVF[IndexOutOfBoundsException]", e);

        }catch(Exception e){
            logURA.log("uraSvrContract::URVF[Exception]", e);
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO, "<!--"+ e.getMessage() +"-->");

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        // Verificar qual o tipo de linha
        try{
            String xmlTipoLinha = "<cdDDD>"+this.linhaBean.getDdd()+"</cdDDD><cdNumTelefone>"+this.linhaBean.getNrLinha()+"</cdNumTelefone>";
            servico = this.getServicoLegado(xmlTipoLinha);

        }catch(TuxedoWarningException twe){
            logURA.log("uraSvrContract::URVF[TuxedoWarningException]", twe);
            String erroComment = "<!-- " + twe.getXmlHeader().getStatusText() + " -->";
            if(twe.getXmlHeader().getError().equals("0001")) {
                saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_CONTA_INEXISTE, erroComment);
            } else {
                saida = XmlManager.createRetorno(URA_STATCOM_OK, Constant.URA_CODIRET_ERRO_SUBROTINA, erroComment);
            }

            saida = PREFIXO_SAIDA_XML.concat(saida).concat(SUFIXO_SAIDA_XML);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        }catch(Exception ex){
            logURA.log("uraSvrContract::URVF[Exception]", ex);
            saida = XmlManager.createRetorno(URA_STATCOM_OK,Constant.URA_CODIRET_ERRO_SUBROTINA);

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

            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", servico, xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOutput = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOutput+"<<");

            xmlManager.parseXmlOut(xmlOutput);

            if ( service.equals( "getFavoritos" ) ) {
                FavoritosVO fav = (FavoritosVO) xmlManager.outputInnerBody( xmlOutput );
                StringBuffer favs = new StringBuffer();
                int quantidadeIncRestante = 0;
                int quantidadeCadRestante = 0;
                //int maxFavoritos = this.getQuantidadeMaxFavoritos(input);
                quantidadeIncRestante = this.getInclusoesRestantes(XmlManager.getTag(fav.getQtIncRestantes()),fav.sizeOfFavoritosItemArray(),maxFavoritos);
                quantidadeCadRestante = this.getInclusoesRestantes(XmlManager.getTag(fav.getQtCadRestantes()),fav.sizeOfFavoritosItemArray(),maxFavoritos);
                favs.append("<qtMaxInclusao>"+maxFavoritos+"</qtMaxInclusao>");
                favs.append("<qtIncRestantes>"+quantidadeIncRestante+"</qtIncRestantes>");
                favs.append("<qtCadRestantes>"+quantidadeCadRestante+"</qtCadRestantes>");
                favs.append("<qtAltGratuitas>"+XmlManager.getTag(fav.getQtAltGratuitas())+"</qtAltGratuitas>");
                favs.append("<qtLinFavAtivas>"+XmlManager.getTag(fav.getQtLinFavAtivas())+"</qtLinFavAtivas>");
                favs.append("<qtLinFavJaCadastradas>"+XmlManager.getTag(fav.getQtLinFavJaCadastradas())+"</qtLinFavJaCadastradas>");
                favs.append("<indTarifa>"+XmlManager.getTag(fav.getIndTarifa())+"</indTarifa>");
                favs.append("<valTarifaInclusao>"+XmlManager.getTag(fav.getValTarifaInclusao())+"</valTarifaInclusao>");
                favs.append("<valTarifaAlteracao>"+XmlManager.getTag(fav.getValTarifaAlteracao())+"</valTarifaAlteracao>");
                favs.append( "<qtdeTelefones>" + fav.sizeOfFavoritosItemArray() + "</qtdeTelefones>" );
                favs.append("<telefones>");
                for(int i=0; i < fav.sizeOfFavoritosItemArray(); i++){
                    favs.append("<favorito>").append(fav.getFavoritosItemArray(i).getNumero()).append("</favorito>");
                }
                favs.append("</telefones>");
                saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND, favs.toString() );

            }else{
                saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND );
            }

        }catch(ClassCastException e){
            logURA.log("uraSvrContract::URVF[ClassCastException]", e);
            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND );

        }catch(TuxedoWarningException twe){
            logURA.log("uraSvrContract::URVF[TuxedoWarningException]", twe);
            saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_ERRO_SUBROTINA,twe);

        }catch(TuxedoErrorException tee){
            logURA.log("uraSvrContract::URVF[TuxedoErrorException]", tee);
            if(tee.getXmlHeader().getError().equals("8015") || tee.getXmlHeader().getError().equals("3002")){
                saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_FAVORI_REPETIDO, tee);

            }else if(tee.getXmlHeader().getError().equals("8001") || tee.getXmlHeader().getError().equals("7073")){
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK,"07", tee);

            }else{
                saida = XmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_ERRO_SUBROTINA,tee);
            }

        }catch(Exception e){
            logURA.log("uraSvrContract::URVF[Exception]", e);
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            try {
                String exception = ControlXMLExceptionLookup.getXMLString(e);
                String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                String erroComment = "<!-- " + xmlRetorno[3] + " -->";
                System.out.println( erroComment );
                if (xmlRetorno[2] != null && xmlRetorno[2].trim().equalsIgnoreCase("5001")) {
                    saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>" + erroComment);
                } else {
                    saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>" + erroComment);
                }

            }catch(Exception ex) {
                logURA.log("uraSvrContract::URVF[Exception]", e);
                saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>");
            }
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
     * Esse método é para atender a funcionalidade de favoritos quando o legado for ALTYS
     */
    public int getInclusoesRestantes(String qtInclusoesRestantes,int totalFavoritos,int maxFavoritos){
        int inclusoesRestantes = 0;
        try{
            inclusoesRestantes = Integer.parseInt(qtInclusoesRestantes);
        }catch(NumberFormatException nfe){
            inclusoesRestantes = maxFavoritos - totalFavoritos;
        }
        return inclusoesRestantes;
    }

    public ActionForward oldURVF(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Dado de teste.
        //<?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>41</cdDDD><cdNumTelefone>91009650</cdNumTelefone><cdDDDFavorito>41</cdDDDFavorito><cdNumFavorito>91009999</cdNumFavorito></msgBody></msg>
        String input = request.getParameter("input");

        String xmlInput    = null;
        String xmlOutput   = null;
        String numFavorito = null;
        String saida       = "";

        // efetua o parse do documento XML recebido
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
            String ddd = document.getElementsByTagName("cdDDDFavorito").item(0).getFirstChild().getNodeValue();
            String fone = document.getElementsByTagName("cdNumFavorito").item(0).getFirstChild().getNodeValue();
            if (ddd == null || fone == null) {
                throw new Exception("DDD ou FONE favorito não encontrado");
            }
            numFavorito = ddd + fone;

        } catch (Exception e) {
            logURA.log("uraSvrContract::oldURVF[Exception]", e);
            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO );

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            xmlInput = convertInputXML(input, "getFavoritos", "URVF");

        }catch ( Exception e ){
            logURA.log("uraSvrContract::oldURVF[Exception]", e);
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));

            xmlOutput = jatmi.executeCommnad("TuxConnectorURA", XmlManager.MakeXmlIn("30", "TuxProxyBE", xmlEnt));

            FavoritosVO fav = (FavoritosVO) xmlManager.outputInnerBody(xmlOutput);
            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND);

            for(int i=0; i<fav.sizeOfFavoritosItemArray();i++){
                try{
                    if(fav.getFavoritosItemArray(i).getNumero().equals(numFavorito)){
                        saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_FAVORI_REPETIDO);
                        break;
                    }
                }catch(Exception e){}
            }

        }catch(Exception e){
            logURA.log("uraSvrContract::oldURVF[Exception]", e);
            // Erro na chamada TUXEDO. Gera XML de erro inesperado, utilizando API de xmlIn.
            // IMPORTANTE: Pode ser que seja necessário fazer parse do XML de entrada e gerar um XML de saida
            // com o mesmo cabeçalho de entrada. Por enquanto, usa-se a API de entrada.
            try {
                String exception = ControlXMLExceptionLookup.getXMLString(e);
                String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                String erroComment = "<!-- " + xmlRetorno[3] + " -->";
                System.out.println( erroComment );
                if (xmlRetorno[2] != null && xmlRetorno[2].trim().equalsIgnoreCase("5001")) {
                    saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>1</statCom><cdCodigoRetorno>01</cdCodigoRetorno>" + erroComment);
                } else {
                    saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>" + erroComment);
                }

            } catch (Exception ex) {
                logURA.log("uraSvrContract::oldURVF[Exception]", ex);
                saida = XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>3</statCom>");
            }

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    public ActionForward UREX(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Dado de teste.
        //<?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URAD</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>21</cdDDD><cdNumTelefone>99778911</cdNumTelefone><dataPesquisa>30/11/2003</dataPesquisa><qtdePesquisa>6</qtdePesquisa><cpf>99988877755</cpf></msgBody></msg>
        //xmlOutput = "<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>URA</user><service>UREX</service><subSystem>Undefined Data</subSystem><corrid>99999</corrid><serverElapsedTime>236140</serverElapsedTime><statusCode>24I0000</statusCode><statusText>Successfully executed</statusText></msgHdr><msgBody><CONTA><NUM_CONTA>4572270</NUM_CONTA><SALDO>45896</SALDO><DATA_SALDO>31/03/2004 00:00:00</DATA_SALDO><CPF>432943327-34</CPF><LINHAS><LINHA><AREA>21</AREA><NUM_LINHA>96128374</NUM_LINHA></LINHA><LINHA><AREA>21</AREA><NUM_LINHA>96768852</NUM_LINHA></LINHA></LINHAS><CONTEM_LINHAINPUT>S</CONTEM_LINHAINPUT><DATA_INICIAL>30/11/2003</DATA_INICIAL><DATA_ATUAL>31/03/2004</DATA_ATUAL><SALDO_INICIAL>39539</SALDO_INICIAL><SALDO_ATUAL>6357</SALDO_ATUAL><TOTAL_LANCAMENTOS>6357</TOTAL_LANCAMENTOS><MES_FATURA>02/2004</MES_FATURA><EXTRATO><DATA_LANCAMENTO>30/11/2003 01:00:00</DATA_LANCAMENTO><DEBITO_CREDITO>C</DEBITO_CREDITO><VLR_LANCAMENTO>1091</VLR_LANCAMENTO><COD_LANCAMENTO>1</COD_LANCAMENTO><DESCR_LANCAMENTO>Crédito por ligações e serviços</DESCR_LANCAMENTO></EXTRATO><EXTRATO><DATA_LANCAMENTO>31/12/2003 01:00:00</DATA_LANCAMENTO><DEBITO_CREDITO>C</DEBITO_CREDITO><VLR_LANCAMENTO>1115</VLR_LANCAMENTO><COD_LANCAMENTO>1</COD_LANCAMENTO><DESCR_LANCAMENTO>Crédito por ligações e serviços</DESCR_LANCAMENTO></EXTRATO><EXTRATO><DATA_LANCAMENTO>31/01/2004 01:00:00</DATA_LANCAMENTO><DEBITO_CREDITO>C</DEBITO_CREDITO><VLR_LANCAMENTO>1333</VLR_LANCAMENTO><COD_LANCAMENTO>1</COD_LANCAMENTO><DESCR_LANCAMENTO>Crédito por ligações e serviços</DESCR_LANCAMENTO></EXTRATO><EXTRATO><DATA_LANCAMENTO>29/02/2004 00:00:00</DATA_LANCAMENTO><DEBITO_CREDITO>C</DEBITO_CREDITO><VLR_LANCAMENTO>1618</VLR_LANCAMENTO><COD_LANCAMENTO>1</COD_LANCAMENTO><DESCR_LANCAMENTO>Crédito por ligações e serviços</DESCR_LANCAMENTO></EXTRATO><EXTRATO><DATA_LANCAMENTO>31/03/2004 00:00:00</DATA_LANCAMENTO><DEBITO_CREDITO>C</DEBITO_CREDITO><VLR_LANCAMENTO>1200</VLR_LANCAMENTO><COD_LANCAMENTO>1</COD_LANCAMENTO><DESCR_LANCAMENTO>Crédito por ligações e serviços</DESCR_LANCAMENTO></EXTRATO></CONTA></msgBody></msg>";
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlOutput = "";
        String saida     = "";
        input = XmlManager.registrarContato(input, "UREX");// registra o contato
        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = input.substring(input.indexOf("<msgBody>")+9,input.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "UREXPONTOS", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOutput = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOutput+"<<");

            xmlManager.parseXmlOut(xmlOutput);

            if (xmlOutput != null) {
                xmlOutput = xmlOutput.replaceAll("<CONTA>","<CONTA xmlns=\"tuxProxyBE/vo\">");
            }

        }catch(TuxedoErrorException tee){
            logURA.log("uraSvrContract::UREX[TuxedoErrorException]", tee);
            if(tee.getXmlHeader().getError().equals("0006")) {
                saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA);
            } else {
                saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA);
            }

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        }catch(Exception e){
            logURA.log("uraSvrContract::UREX[Exception]", e);
            String msgErro = e.getMessage();
            if(e instanceof TPException){
                try {
                    String exception = ControlXMLExceptionLookup.getXMLString(e);
                    String[] xmlRetorno = XmlManager.ParseXmlOut(exception);
                    msgErro = xmlRetorno[3];
                }catch(Exception ex) {}
            }
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA,"<!-- "+msgErro+" -->");

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        CONTA conta = null;
        try{
            conta = (CONTA) xmlManager.outputInnerBody( xmlOutput );

        }catch(Exception e){
            logURA.log("uraSvrContract::UREX[Exception]", e);
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            SimpleDateFormat dtFmt = new SimpleDateFormat( "dd/MM/yyyy hh:mm:ss" );
            SimpleDateFormat outFmt = new SimpleDateFormat( "dd/MM/yyyy" );

            StringBuffer result = new StringBuffer();
            result.append("<cdCPF>").append(conta.getCPF()).append("</cdCPF>" );
            result.append("<nrConta>").append(conta.getNUMCONTA()).append("</nrConta>" );
            result.append("<dtSaldo>").append(outFmt.format(dtFmt.parse(conta.getDATASALDO()))).append("</dtSaldo>" );
            result.append("<saldo>").append(conta.getSALDO()).append("</saldo>" );
            result.append("<numSaldoInicial>").append(conta.getSALDOINICIAL()).append("</numSaldoInicial>");
            result.append("<dtInicial>").append(conta.getDATAINICIAL()).append("</dtInicial>");
            result.append("<numSaldoAtual>").append(conta.getSALDOATUAL()).append("</numSaldoAtual>");
            result.append("<dtAtual>").append(conta.getDATAATUAL()).append("</dtAtual>");

            if(conta.sizeOfLINHASArray() > 0){
                result.append("<nrLinhas>").append(conta.getLINHASArray(0).sizeOfLINHAArray()).append("</nrLinhas>");
                for ( int i = 0; i < conta.getLINHASArray(0).sizeOfLINHAArray(); i ++ ){
                    result.append("<linha>");
                    result.append("<cdDDD>").append(conta.getLINHASArray(0).getLINHAArray(i).getAREA()).append("</cdDDD>");
                    result.append("<cdNumTelefone>").append(conta.getLINHASArray(0).getLINHAArray(i).getNUMLINHA()).append("</cdNumTelefone>");
                    result.append("</linha>");
                }
            }else{
                result.append("<nrLinhas>0</nrLinhas>");
            }

            if(conta.sizeOfEXTRATOArray() > 0){
                result.append("<nrExtratos>").append(conta.sizeOfEXTRATOArray()).append("</nrExtratos>");
                Extrato[] extratos = new Extrato[conta.sizeOfEXTRATOArray()];
                for ( int i = 0; i < conta.sizeOfEXTRATOArray() ; i ++ ){
                    extratos[i] = new Extrato();
                    extratos[i].pontos = Integer.parseInt( conta.getEXTRATOArray(i).getVLRLANCAMENTO() );
                    extratos[i].lancamento = dtFmt.parse( conta.getEXTRATOArray( i ).getDATALANCAMENTO() );
                    extratos[i].flag = conta.getEXTRATOArray( i ).getDEBITOCREDITO();
                    extratos[i].descricao = conta.getEXTRATOArray(i).getDESCRLANCAMENTO();
                }

                // TODO: sort para fazer depois calculos de saldo anterior e posterior, mas o tux nao traz saldo anterior
                Arrays.sort( extratos, new Comparator() {
                    public int compare(Object a, Object b){
                        Extrato e1 = (Extrato) a;
                        Extrato e2 = (Extrato) b;
                        return e1.lancamento.compareTo( e2.lancamento );
                    }
                } );

                int saldo = Integer.parseInt( conta.getSALDO() );
                for ( int i = extratos.length-1; i >= 0; i--){
                    result.append("<extrato>");
                    result.append("<dtLancamento>").append(outFmt.format(extratos[i].lancamento)).append("</dtLancamento>");
                    result.append("<cdDebitoCredito>").append(extratos[i].flag).append("</cdDebitoCredito>");
                    result.append("<pontos>").append(extratos[i].pontos).append("</pontos>");
                    result.append("<descricao>").append(extratos[i].descricao).append("</descricao>");
                    result.append("<saldoPosterior>").append(saldo).append("</saldoPosterior>");
                    saldo -= extratos[i].pontos;
                    result.append("<saldoAnterior>").append(saldo).append("</saldoAnterior>");
                    result.append("</extrato>");
                }
            }else{
                result.append( "<nrExtratos>0</nrExtratos>" );
            }

            if(conta.sizeOfEXPIRACAOArray() > 0){
                result.append("<nrExpiracoes>").append(conta.sizeOfEXPIRACAOArray()).append("</nrExpiracoes>");
                Extrato[] extratos = new Extrato[conta.sizeOfEXPIRACAOArray()];
                for(int j=0; j<conta.sizeOfEXPIRACAOArray();j++){
                    result.append("<expiracao>");
                    result.append("<dtExpiracao>").append(outFmt.format(dtFmt.parse(conta.getEXPIRACAOArray(j).getDATAEXPIRACAO()))).append("</dtExpiracao>");
                    result.append("<pontos>").append(conta.getEXPIRACAOArray(j).getQTDPONTOS()).append("</pontos>");
                    result.append("</expiracao>");
                }
            }else{
                result.append( "<nrExpiracoes>0</nrExpiracoes>" );
            }
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_PROSSEGUE_ATEND, result.toString());

        }catch(Exception e){
            logURA.log("uraSvrContract::UREX[Exception]", e);
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA);

        }finally{
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return null;
    }

    // helper para UREX
    class Extrato{
        public Date lancamento = null;
        public String flag = "";
        public int pontos = 0;
        public String descricao = "";
        public int saldoAnterior = 0;
        public int saldoPosterior = 0;
    }

    // cria retorno de erro
    private String createRetorno( String statCom) {
        return XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>" + statCom + "</statCom>");
    }

    private String createRetorno( String statCom, String codiRet ){
        return createRetorno ( statCom, codiRet, "" );
    }

    private String createRetorno( String statCom, String codiRet, String append ){
        return XmlManager.MakeXmlOut("ura", "TuxProxyBE", "<statCom>" + statCom + "</statCom><cdCodigoRetorno>" + codiRet + "</cdCodigoRetorno>" + append );
    }

    private Document convertOutputXml(String output) throws ParserConfigurationException, SAXException, IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(output.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
        return document;
    }

    // inicializa loop de favoritos
    private void initFavoriteInputXML( String input ) throws Exception {
        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

        try{
            this.statusURVF = document.getElementsByTagName("status").item(0).getFirstChild().getNodeValue();

        }catch(Exception e){
            logURA.log("uraSvrContract::initFavoriteInputXML[Exception]", e);
            throw new Exception("Parametros de entrada inválidos - tag status não encontrada");
        }

        if(!this.statusURVF.equalsIgnoreCase("P") && !this.statusURVF.equalsIgnoreCase("C")) {
            throw new Exception("Parametros de entrada inválidos - status inválido");
        }

        try{
            favoritos = new ArrayList();
            NodeList list = document.getElementsByTagName("telefones").item(0).getChildNodes();
            for(int i=0; i<list.getLength();i++){
                favoritos.add( list.item( i ).getFirstChild().getNodeValue() );
            }

        }catch(Exception e){
            logURA.log("uraSvrContract::initFavoriteInputXML[Exception]", e);
        }
    }

    // alimenta loop de favoritos
    private String favoriteInputXML(String input, String service, String operacaoURA, String qtMaxInclusao) throws Exception {
        // efetua o parse do documento XML recebido
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

        if(document.getElementsByTagName("msg").item(0) == null) {
            throw new Exception("msg não encontrada");
        }

        if(document.getElementsByTagName("msgHdr").item(0) == null) {
            throw new Exception("Header não encontrado");
        }

        if(document.getElementsByTagName("msgBody").item(0).getChildNodes() == null) {
            throw new Exception("Body não encontrado");
        }

        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try{
            String ddd = null;
            String fone = null;
            String status = null;
            String cdDDDFavorito = null;
            String cdNumFavorito = null;
            String operacao = null;
            String user = null;

            try { user = document.getElementsByTagName("user").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { fone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { status = document.getElementsByTagName("status").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { cdDDDFavorito = document.getElementsByTagName("cdDDDFavorito").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { cdNumFavorito = document.getElementsByTagName("cdNumFavorito").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { operacao = document.getElementsByTagName("operacao").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}

            this.linhaBean.setDdd(ddd);
            this.linhaBean.setNrLinha(fone);

            String xmlMsg = "<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>"+user+"</user><service>TuxProxyBE</service></msgHdr><msgBody></msgBody></msg>";
            // zera o documento para retorno
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlMsg.getBytes()));

            if(ddd!=null && fone!=null){
                Node proxyLinha = document.createElement("ProxyLinha");
                proxyLinha.appendChild(document.createTextNode(ddd.trim() + fone.trim()));
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyLinha);
            }

            if(status.equalsIgnoreCase("C")){
                Node proxyOperacao = document.createElement("ProxyOperacao");
                proxyOperacao.appendChild(document.createTextNode("setFavorito"));
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);

                Node serv = document.createElement("servico");
                serv.appendChild(document.createTextNode("FAVORNOVPLN"));
                document.getElementsByTagName("msgBody").item(0).appendChild(serv);

                Node nodeOperacao = document.createElement("operacao");
                nodeOperacao.appendChild(document.createTextNode(operacao));
                document.getElementsByTagName("msgBody").item(0).appendChild(nodeOperacao);

                // quando o arraylist acabar ele vai dar exception e terminar o loop
                String numFavorito = (String) favoritos.get( 0 );
                favoritos.remove( 0 );
                Node numeroAntigo = document.createElement("numeroAntigo");
                Node numeroNovo = document.createElement("numeroNovo");
                document.getElementsByTagName("msgBody").item(0).appendChild(numeroAntigo);
                document.getElementsByTagName("msgBody").item(0).appendChild(numeroNovo);

                // inclusão
                if(operacao.equals("1")){
                    numeroAntigo.appendChild(document.createTextNode("") );
                    numeroNovo.appendChild(document.createTextNode(numFavorito));
                    operacaoURA = "URVF_INCLUIR";

                }else if(operacao.equals("2")){// exclusão
                    numeroAntigo.appendChild(document.createTextNode(numFavorito) );
                    numeroNovo.appendChild(document.createTextNode(""));
                    operacaoURA = "URVF_EXCLUIR";

                }else if(operacao.equals("3")){// alteração
                    numeroAntigo.appendChild(document.createTextNode(numFavorito) );

                    if(favoritos.size() == 0) {
                        throw new Exception("Tag de entrada em favoritos inválida para alteração");
                    }

                    numFavorito = (String) favoritos.get( 0 );
                    numeroNovo.appendChild(document.createTextNode(numFavorito));
                    operacaoURA = "URVF_ALTERAR";
                }else{
                    throw new Exception("Operação inválida");
                }
                XmlManager.registrarContato(document,operacaoURA,ddd,fone,qtMaxInclusao);

            }else{
                Node proxyOperacao = document.createElement("ProxyOperacao");
                proxyOperacao.appendChild(document.createTextNode("getFavoritos"));
                Node idCanal = document.createElement("idCanal");
                idCanal.appendChild(document.createTextNode("9"));
                document.getElementsByTagName("msgBody").item(0).appendChild(idCanal);
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);
            }

            Node xmlns = document.createElement("xmlns");
            xmlns.appendChild(document.createTextNode("tuxProxyBE/vo"));
            document.getElementsByTagName("msgBody").item(0).appendChild(xmlns);

            Node usuario = document.createElement("usuario");
            usuario.appendChild(document.createTextNode("URA"));
            document.getElementsByTagName("msgBody").item(0).appendChild(usuario);

            OutputFormat    format  = new OutputFormat( document );     //Serialize DOM
            StringWriter  stringOut = new StringWriter();               //Writer will be a String
            XMLSerializer    serial = new XMLSerializer( stringOut, format );
            serial.asDOMSerializer();                                   // As a DOM Serializer
            serial.serialize( document.getDocumentElement() );
            return stringOut.toString() ;

        } catch (Exception e) {
            logURA.log("uraSvrContract::favoriteInputXML[Exception]", e);
            throw new Exception("Erro de input");
        }
    }

    private String convertLSEXTRATOPOInputXml(String input) throws Exception {
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

        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            String ddd = null;
            String fone = null;

            String dataPesquisa = null;
            String qtdePesquisa = null;
            String cpf = null;

            try { ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { fone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { dataPesquisa = document.getElementsByTagName("dataPesquisa").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { qtdePesquisa = document.getElementsByTagName("qtdePesquisa").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { cpf = document.getElementsByTagName("cpf").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}

            // zera o documento para retorno
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream("<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>2</user><service>LSTEXTRATOPO</service></msgHdr><msgBody></msgBody></msg>".getBytes()));

            if(ddd!=null && fone!=null){
                Node dddNode = document.createElement("cdAreaRegistro");
                dddNode.appendChild(document.createTextNode(ddd));
                document.getElementsByTagName("msgBody").item(0).appendChild(dddNode);

                Node foneNode = document.createElement("nrLinha");
                foneNode.appendChild(document.createTextNode(fone));
                document.getElementsByTagName("msgBody").item(0).appendChild(foneNode);
            }

            Node qtdePesquisaNode = document.createElement("nrLancamentos");
            qtdePesquisaNode.appendChild(document.createTextNode(qtdePesquisa));
            document.getElementsByTagName("msgBody").item(0).appendChild(qtdePesquisaNode);

            OutputFormat    format  = new OutputFormat( document );     //Serialize DOM
            StringWriter  stringOut = new StringWriter();               //Writer will be a String
            XMLSerializer    serial = new XMLSerializer( stringOut, format );
            serial.asDOMSerializer();                                   // As a DOM Serializer
            serial.serialize( document.getDocumentElement() );
            return stringOut.toString() ;

        }catch(Exception e){
            logURA.log("uraSvrContract::convertLSEXTRATOPOInputXml[Exception]", e);
            throw new Exception("Erro de input");
        }
    }

    /**
     * A funçao de serviços que a URA espera nao bate totalmente com o especificado no TuxProxyBE
     * Falta a funçao de Conferencia
     * A URA nao diferencia Bloqueio de DDD com DDI
     * O TuxProxyBE nao suporta diferentes opcoes de desvio
     * O TuxProxyBE nao devolve informacoes de tarifa sobre os servicos
     *
     * ultima atualizacao: 2004-09-08 - Fabio Akita
     */
    private String convertInputXML(String input, String service,String cdContato) throws Exception {
    	
    	logURA.logInfo("uraSvrContract::convertInputXML[service]: "+ service);
    	logURA.logInfo("uraSvrContract::convertInputXML[cdContato]: "+ cdContato);

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

        // Cria as TAGs do proxy (ProxyLinha e ProxyOperacao)
        try {
            String ddd = null;
            String fone = null;
            String servico = null;
            String opcao = null;
            String user = null;

            //TODO: por enquanto nao usado para nada
            String opcaoDesvio = null;
            String status = null;

            String cdDDDFavorito = null;
            String cdNumFavorito = null;

            String dtDataPagto = null;
            String vlPagto = null;
            String mesRef = null;

            try { user = document.getElementsByTagName("user").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { ddd = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { fone = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { servico = document.getElementsByTagName("servico").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { opcao = document.getElementsByTagName("opcao").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { opcaoDesvio = document.getElementsByTagName("opcaoDesvio").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { status = document.getElementsByTagName("status").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {logURA.logWarn("uraSvrContract::convertInputXML[Exception]", e);}
            try { cdDDDFavorito = document.getElementsByTagName("cdDDDFavorito").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try { cdNumFavorito = document.getElementsByTagName("cdNumFavorito").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            try {dtDataPagto = document.getElementsByTagName("dtDataPagto").item(0).getFirstChild().getNodeValue();}
            catch ( Exception e ) {}
            try {vlPagto = document.getElementsByTagName("vlPagto").item(0).getFirstChild().getNodeValue();}
            catch ( Exception e ) {}
            try {mesRef = document.getElementsByTagName("mesRef").item(0).getFirstChild().getNodeValue(); }
            catch ( Exception e ) {}
            
            
            logURA.logInfo("uraSvrContract::convertInputXML[status]: "+ status);

            this.statusURAD = status;
            this.linhaBean.setDdd(ddd);
            this.linhaBean.setNrLinha(fone);

            String xmlMsg = "<?xml version='1.0' encoding='ISO-8859-1'?><msg><msgHdr><user>"+user+"</user><service>TuxProxyBE</service></msgHdr><msgBody></msgBody></msg>";

            // zera o documento para retorno
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(xmlMsg.getBytes()));

            if(ddd!=null && fone!=null){
                Node proxyLinha = document.createElement("ProxyLinha");
                proxyLinha.appendChild(document.createTextNode(ddd.trim() + fone.trim()));
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyLinha);
            }

            if(service!=null && service!="setServico"){
                Node proxyOperacao = document.createElement("ProxyOperacao");
                proxyOperacao.appendChild(document.createTextNode(service));
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);

                if("getHistoricoMovimentos".equals(service)){
                    Date today = new Date();
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd 00:00:00" );
                    Calendar cal = Calendar.getInstance();
                    cal.setTime( today );
                    cal.set( Calendar.MONTH, cal.get( Calendar.MONTH ) - 3 );

                    Node dataIni = document.createElement("dataIni");
                    dataIni.appendChild(document.createTextNode(fmt.format( cal.getTime() )));
                    document.getElementsByTagName("msgBody").item(0).appendChild(dataIni);

                    Node dataFim = document.createElement("dataFim");
                    dataFim.appendChild(document.createTextNode(fmt.format( today )));
                    document.getElementsByTagName("msgBody").item(0).appendChild(dataFim);

                }else if("setReligueCelular".equals(service)){
                    Node dtDataPagtoNode = document.createElement("dataPgto");
                    Node vlPagtoNode = document.createElement("valor");
                    Node mesRefNode = document.createElement("refFatura");
                    Node banco = document.createElement("banco");

                    if(dtDataPagto == null || vlPagto == null || mesRef == null && mesRef.length() < 6){
                        throw new Exception("Parâmetro(s) de entrada inválido(s)");
                    }
                    mesRef = mesRef.substring(4,6) + "/" + mesRef.substring(0,4);
                    dtDataPagtoNode.appendChild(document.createTextNode(dtDataPagto));
                    vlPagtoNode.appendChild(document.createTextNode(vlPagto));
                    mesRefNode.appendChild(document.createTextNode(mesRef));
                    banco.appendChild(document.createTextNode("937"));
                    document.getElementsByTagName("msgBody").item(0).appendChild(dtDataPagtoNode);
                    document.getElementsByTagName("msgBody").item(0).appendChild(vlPagtoNode);
                    document.getElementsByTagName("msgBody").item(0).appendChild(mesRefNode);
                    document.getElementsByTagName("msgBody").item(0).appendChild(banco);
                }
            }

            if(servico!=null){
                Node proxyOperacao = document.createElement("ProxyOperacao");
                if(servico.equals( URA_SERV_BLOQUEIO_ROUBO ) || servico.equals( URA_SERV_BLOQUEIO_PERDA)){
                    this.perdaRoubo = true;
                    cdContato = (opcao.equals("1"))?"URAD_BLOQUEIO_PERDA_ATIVAR":"URAD_BLOQUEIO_PERDA_DESATIVAR";
                    proxyOperacao.appendChild(document.createTextNode("setSuspendeCelular"));
                    Node Motivo = document.createElement("motivo");

                    if(servico.equals( URA_SERV_BLOQUEIO_ROUBO)){
                        Motivo.appendChild(document.createTextNode("25"));
                    }else if(servico.equals( URA_SERV_BLOQUEIO_PERDA)){
                        Motivo.appendChild(document.createTextNode("3"));
                    }
                    document.getElementsByTagName("msgBody").item(0).appendChild(Motivo);

                }else{
                    if("C".equalsIgnoreCase(status)) {
                        proxyOperacao.appendChild(document.createTextNode("setServico"));
                    } else
                    if("P".equalsIgnoreCase(status)) {
                        proxyOperacao.appendChild(document.createTextNode("getServicos"));
                    } else {
                        throw new Exception ( "Parâmetros de entrada inválidos");
                    }

                    Node proxyOpcao = document.createElement("servico");
                    if ( servico.equals( URA_SERV_BLOQUEIO_DDD ) ){
                        this.codigoServico = "UNBLOCKDDD";
                        cdContato = (opcao.equals("1"))?"URAD_BLOQUEIO_DDD_ATIVAR":"URAD_BLOQUEIO_DDD_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_BLOQUEIO_DDI ) ){
                        this.codigoServico = "UNBLOCKDDI";
                        cdContato = (opcao.equals("1"))?"URAD_BLOQUEIODDI_ATIVAR":"URAD_BLOQUEIODDI_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_CAIXA_POSTAL ) ){
                        this.codigoServico = "VOICEMAIL";
                        cdContato = (opcao.equals("1"))?"URAD_CAIXAPOSTAL_ATIVAR":"URAD_CAIXAPOSTAL_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_CHAMADA_ESPERA ) ){
                        this.codigoServico = "CALLWAIT";
                        cdContato = (opcao.equals("1"))?"URAD_CHAMADAESPERA_ATIVAR":"URAD_CHAMADAESPERA_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_DESVIO_CHAMADA ) ){
                        this.codigoServico = "CALLFWD";
                        cdContato = (opcao.equals("1"))?"URAD_DESVIOCHAMADAS_ATIVAR":"URAD_DESVIOCHAMADAS_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_IDENTIFICADOR ) ){
                        this.codigoServico = "CALLID";
                        cdContato = (opcao.equals("1"))?"URAD_IDENTIFICADOR_ATIVAR":"URAD_IDENTIFICADOR_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_AVISO_MSG ) ){
                        this.codigoServico = "WARN";
                        cdContato = (opcao.equals("1"))?"URAD_AVISOMENSAGEM_ATIVAR":"URAD_AVISOMENSAGEM_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if ( servico.equals( URA_SERV_VIVO_WAP ) ){
                        this.codigoServico = "WAP";
                        cdContato = (opcao.equals("1"))?"URAD_VIVOWAP_ATIVAR":"URAD_VIVOWAP_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if( servico.equals(URA_SERV_CONFERENCIA)){
                        this.codigoServico = "CONFERENCIA";
                        cdContato = (opcao.equals("1"))?"URAD_CONFERENCIA_ATIVAR":"URAD_CONFERENCIA_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else if( servico.equals(URA_SERV_ANTIBINA)){
                        this.codigoServico = "ANTIBINA";
                        cdContato = (opcao.equals("1"))?"URAD_ANTIBINA_ATIVAR":"URAD_ANTIBINA_DESATIVAR";
                        proxyOpcao.appendChild(document.createTextNode(this.codigoServico));

                    }else{
                        throw new Exception ( "Serviço indefinido" );
                    }
                    document.getElementsByTagName("msgBody").item(0).appendChild(proxyOpcao);
                }
                document.getElementsByTagName("msgBody").item(0).appendChild(proxyOperacao);
            }

            if(opcao!=null){
                // por coincidencia os codigos de ativar/desativar da ura sao os mesmos do TuxProxyBE
                Node operacao = document.createElement("operacao");
                operacao.appendChild(document.createTextNode(opcao));
                document.getElementsByTagName("msgBody").item(0).appendChild(operacao);
            }

            if(service.equals("setServico")){
                if(status.equalsIgnoreCase("C")){
                    XmlManager.registrarContato(document,cdContato,ddd,fone,null);
                }else{
                    Node idCanal = document.createElement("idCanal");
                    idCanal.appendChild(document.createTextNode("9"));
                    document.getElementsByTagName("msgBody").item(0).appendChild(idCanal);
                }
            } else {
                XmlManager.registrarContato(document,cdContato,ddd,fone,null);
            }

            Node xmlns = document.createElement("xmlns");
            xmlns.appendChild(document.createTextNode("tuxProxyBE/vo"));
            document.getElementsByTagName("msgBody").item(0).appendChild(xmlns);

            Node usuario = document.createElement("usuario");
            usuario.appendChild(document.createTextNode("URA"));
            document.getElementsByTagName("msgBody").item(0).appendChild(usuario);

            OutputFormat    format  = new OutputFormat( document );     //Serialize DOM
            StringWriter  stringOut = new StringWriter();               //Writer will be a String
            XMLSerializer    serial = new XMLSerializer( stringOut, format );
            serial.asDOMSerializer();                                   // As a DOM Serializer
            serial.serialize( document.getDocumentElement() );
            
            logURA.logInfo("uraSvrContract::convertInputXML[statusURAD]: "+ this.statusURAD);
            
            return stringOut.toString() ;
            
            

        } catch (Exception e) {
            logURA.log("uraSvrContract::convertInputXML[Exception]", e);
            throw new Exception("Erro de input");
        }
    }

    public ActionForward URSD(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{

        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlInput = null;
        String xmlOut   = null;
        String saida    = "";

        try{
            xmlInput = this.convertInputXML(input,"getEstimativaSaldo","URSD");

        }catch(Exception ex){
            logURA.log("uraSvrContract::URSD[Exception]", ex);
            saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO);

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "TUXATLYSURA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            XmlManager xmlManager = new XmlManager();
            xmlManager.parseXmlOut(xmlOut);

            saida = saldoParcialOutput(xmlOut);

        }catch(TuxedoErrorException tee){
            logURA.log("uraSvrContract::URSD[TuxedoErrorException]", tee);

            StringBuffer sb = new StringBuffer("<!-- ").append(tee.getXmlHeader().getStatusText()).append(" -->");
            if(tee.getXmlHeader().getError().equalsIgnoreCase("0710")){
                saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_CONTA_INEXISTE, sb.toString());
            }else{
                saida = createRetorno(URA_STATCOM_OK, URA_CODIRET_ERRO_SUBROTINA, sb.toString());
            }

        }catch(Exception ex){
            logURA.log("uraSvrContract::URSD[Exception]", ex);
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

    /**
     * Deve tratar a resposta da API de saldo parcial e criar as tags que a URA espera
     * caso alguma tag não exista, um erro de subrotina será lançado
     */
    private String saldoParcialOutput(String xml) throws Exception {
        String xmlOut = null;
        String saldoParcial = null;
        String dataReferencia = null;
        String dataVencimento = null;
        String qtdeMinutos = null;
        String qtdeTorpedos = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

        try{
            saldoParcial = document.getElementsByTagName("dsEstimativaSaldo").item(0).getFirstChild().getNodeValue();
            dataReferencia = document.getElementsByTagName("dtFechamentoCiclo").item(0).getFirstChild().getNodeValue();
            dataVencimento = document.getElementsByTagName("dtProximaFatura").item(0).getFirstChild().getNodeValue();
            qtdeMinutos = document.getElementsByTagName("qtMinutosUtilizados").item(0).getFirstChild().getNodeValue();
            qtdeTorpedos = document.getElementsByTagName("qtTorpedosUtilizados").item(0).getFirstChild().getNodeValue();

            StringBuffer sb = new StringBuffer();
            sb.append("<saldoParcial>").append(saldoParcial).append("</saldoParcial>");
            sb.append("<dataReferencia>").append(dataReferencia).append("</dataReferencia>");
            sb.append("<dataVencimento>").append(dataVencimento).append("</dataVencimento>");
            sb.append("<qtdeMinutos>").append(qtdeMinutos).append("</qtdeMinutos>");
            sb.append("<qtdeTorpedos>").append(qtdeTorpedos).append("</qtdeTorpedos>");
            xmlOut = createRetorno(URA_STATCOM_OK,URA_CODIRET_PROSSEGUE_ATEND,sb.toString());

        }catch(Exception ex){
            logURA.log("uraSvrContract::saldoParcialOutput[Exception]", ex);
            xmlOut = createRetorno(URA_STATCOM_OK,URA_CODIRET_ERRO_SUBROTINA);
        }
        return xmlOut;
    }

    public ActionForward URIP(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // Religue Celular
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>URA</user><service>URIP</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00 000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>11</cdDDD><cdNumTelefone>71527088</cdNumTelefone><dtDataPagto>01/01/2005</dtDataPagto><vlPagto>10</vlPagto><mesRef>200504</mesRef></msgBody></msg>
        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>"+input+"<<");

        String xmlInput = null;
        String xmlOut   = null;
        String saida    = "";

        try{
            xmlInput = this.convertInputXML(input,"setReligueCelular","URIP");

        }catch(Exception ex){
            logURA.log("uraSvrContract::URIP[Exception]", ex);
            saida = createRetorno( URA_STATCOM_OK, URA_CODIRET_PARAM_INVALIDO );
            
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
            String xmlEnt = xmlInput.substring(xmlInput.indexOf("<msgBody>")+9,xmlInput.lastIndexOf("</msgBody>"));

            String xmlTux = XmlManager.MakeXmlIn("30", "TUXATLYSURA", xmlEnt);
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOut+"<<");

            xmlManager.parseXmlOut(xmlOut);

            saida = createRetorno(URA_STATCOM_OK,URA_CODIRET_PROSSEGUE_ATEND);

        }catch(TuxedoErrorException tee){
            logURA.log("uraSvrContract::URIP[TuxedoErrorException]", tee);
            saida = tuxedoErrorURIP(tee);

        }catch(Exception ex){
            logURA.log("uraSvrContract::URIP[Exception]", ex);
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
     * Tratamento de erros do serviço tuxedo
     */
    public String tuxedoErrorURIP(TuxedoErrorException tee){
        int codigo = Integer.parseInt(tee.getXmlHeader().getError());
        switch(codigo){
            case 9003: return xmlManager.createRetorno(URA_STATCOM_OK,"05",tee);
            case 9010: return xmlManager.createRetorno(URA_STATCOM_OK,"02",tee);
            case 9012: return xmlManager.createRetorno(URA_STATCOM_OK,"06",tee);
            case 9013: return xmlManager.createRetorno(URA_STATCOM_OK,"06",tee);
            case 9014: return xmlManager.createRetorno(URA_STATCOM_OK,"09",tee);
            case 9015: return xmlManager.createRetorno(URA_STATCOM_OK,"13",tee);
            case 9016: return xmlManager.createRetorno(URA_STATCOM_OK,"08",tee);
            case 9017: return xmlManager.createRetorno(URA_STATCOM_OK,"14",tee);
            case 9018: return xmlManager.createRetorno(URA_STATCOM_OK,"17",tee);
            case 9019: return xmlManager.createRetorno(URA_STATCOM_OK,"15",tee);
            case 9020: return xmlManager.createRetorno(URA_STATCOM_OK,"18",tee);
            case 9021: return xmlManager.createRetorno(URA_STATCOM_OK,"19",tee);
        }
        return xmlManager.createRetorno(URA_STATCOM_OK,URA_CODIRET_ERRO_SUBROTINA,tee);
    }

    /**
     * verifica se o serviço está ativo ou inativo
     */
    private String getStatusServico(String xmlOutput, String servico) throws Exception{
        StringBuffer strOut = new StringBuffer();
        ServicoVO servicoVO = (ServicoVO)xmlManager.outputInnerBody(xmlOutput);
        ServicosItem[] servicos = null;

        logURA.logInfo("uraSvrContract::getStatusServico [Servico]: "+ servico);

        if(servicoVO == null) {
            return createRetorno(URA_STATCOM_OK,"04","Erro de parse");
        }
        servicos = servicoVO.getServicosItemArray();

        if(servicos==null) {
            return createRetorno(URA_STATCOM_OK,"04","Erro ao montar itens");
        }

        // verifica valor de tarifa
        if("TUXATLYSURA".equals(servico)){
            String valor = servicoVO.getValorTarifa();
            if(valor == null) {
                return createRetorno(URA_STATCOM_OK,URA_CODIRET_ERRO_SUBROTINA,"Valor de tarifa não encontrado");
            }
            strOut.append("<indTarifa>");
            if(valor.equals("0.00") || valor.equals("0") || valor.equals("0,00")) {
                strOut.append("N");
            } else {
                strOut.append("S");
            }
            strOut.append("</indTarifa>");
            strOut.append("<vlTarifa>"+valor+"</vlTarifa>");

        }else{
            strOut.append("<indTarifa>").append("</indTarifa>");
            strOut.append("<vlTarifa>").append("</vlTarifa>");
        }

        for(int i=0;i<servicos.length;i++){
        	logURA.logInfo("uraSvrContract::getStatusServico [CodigoServico]: "+ this.codigoServico);
            if(servicos[i].getCodigo().equalsIgnoreCase(this.codigoServico)){
                if("ATIVADO".equalsIgnoreCase(servicos[i].getStatus())) {
                    return createRetorno(URA_STATCOM_OK,"09",strOut.toString());
                } else
                    if("DESATIVADO".equalsIgnoreCase(servicos[i].getStatus())) {
                        return createRetorno(URA_STATCOM_OK,"10",strOut.toString());
                    }
            }
        }
        return createRetorno(URA_STATCOM_OK,"10",strOut.toString());
    }

    private String getEstadoLinha(String input) throws Exception{
        ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes());
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
        return document.getElementsByTagName("estadoLinha").item(0).getFirstChild().getNodeValue();
    }

    /**
     * Retornar a quantidade maxima de favoritos da base
     */
    private int getQuantidadeMaxFavoritos(String input) throws Exception {
        String xmlOutput = "";
        ByteArrayInputStream bais = null;
        Document document = null;
        String cdAreaRegistro = "";
        String cdNumTelefone = "";
        String maxFavoritos = null;

        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();
            bais = new ByteArrayInputStream(input.getBytes());
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
            cdAreaRegistro = document.getElementsByTagName("cdDDD").item(0).getFirstChild().getNodeValue();
            cdNumTelefone  = document.getElementsByTagName("cdNumTelefone").item(0).getFirstChild().getNodeValue();

            String xmlTux = XmlManager.MakeXmlIn("30", "LSTQTDFAVOR", "<cdAreaRegistro>"+cdAreaRegistro+"</cdAreaRegistro>" + "<nrLinha>" + cdNumTelefone + "</nrLinha> <usuario>URA</usuario>");
            logURA.log("[XMLIN-TX]>>"+xmlTux+"<<");

            xmlOutput = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
            logURA.log("[XMLOUT]>>"+xmlOutput+"<<");

            xmlManager.parseXmlOut(xmlOutput);

            bais = new ByteArrayInputStream(xmlOutput.getBytes());
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
            if(document.getElementsByTagName("qtFavoritos").item(0)!=null) {
                maxFavoritos = document.getElementsByTagName("qtFavoritos").item(0).getFirstChild().getNodeValue();
            }

        }catch(TuxedoErrorException tee){
            logURA.log("uraSvrContract::getQuantidadeMaxFavoritos[TuxedoErrorException]", tee);
            throw tee;

        }catch(TuxedoWarningException tee){
            logURA.log("uraSvrContract::getQuantidadeMaxFavoritos[TuxedoWarningException]", tee);
            throw tee;

        }catch(Exception e){
            logURA.log("uraSvrContract::getQuantidadeMaxFavoritos[Exception]", e);
            throw e;
        }

        try{
            return Integer.parseInt(maxFavoritos);

        }catch(NumberFormatException nfe){
            logURA.log("uraSvrContract::getQuantidadeMaxFavoritos[NumberFormatException]", nfe);
            throw new TuxedoErrorException("Quantidade de favoritos inválida");
        }
    }

    /**
     *  Verificar qual o tipo de linha e qual serviço de legado chamar
     */
    private String getServicoLegado(String xmlIn) throws TuxedoWarningException, TuxedoErrorException {
        try{
            JATMIBusinessDelegate jatmi = new  JATMIBusinessDelegate();

            //xmlIn = XmlManager.MakeXmlIn("1","GETTIPOLINHA",xmlIn);
            String xmlTux = XmlManager.MakeXmlIn("30", "GETTIPOLINHA", xmlIn);
            logURA.logInfo("uraSvrContract::getServicoLegado [XML-IN-GETTIPOLINHA]: "+xmlTux);


            String xmlOut = jatmi.executeCommnad("TuxConnectorURA", xmlTux);
           
            //String xmlOut = "<?xml version=\'1.0\' encoding=\'ISO-8859-1\'?><msg><msgHdr><user>30</user><service>GETTIPOLINHA</service><subSystem>Undefined Data</subSystem><serverElapsedTime>3916</serverElapsedTime><statusCode>00I0000</statusCode><statusText>Sucesso</statusText></msgHdr><msgBody><LINHA xmlns=\"tuxProxyBE/vo\"><tipoLinha>PRÉCHIP</tipoLinha><idLinhaTelefonica>367483505</idLinhaTelefonica></LINHA></msgBody></msg>";
            
            logURA.logInfo("uraSvrContract::getServicoLegado [XML-OUT-GETTIPOLINHA]: "+xmlOut);

            xmlManager.parseXmlOut(xmlOut);

            MsgDocument msgInput = MsgDocument.Factory.parse(xmlOut);
            LINHADocument linhaDoc = LINHADocument.Factory.newInstance();
            linhaDoc = LINHADocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
            LINHA linhaVO = linhaDoc.getLINHA();

            if(("CONT".equalsIgnoreCase(linhaVO.getTipoLinha()) || "CONTCHIP".equalsIgnoreCase(linhaVO.getTipoLinha())) && this.perdaRoubo == true) {
                return "TUXATLYSURA";
            } else if("POS".equals(linhaVO.getTipoLinha()) || "POSCHIP".equals(linhaVO.getTipoLinha())) {
                return "TUXATLYSURA";
            } else if("PRÉ".equals(linhaVO.getTipoLinha()) || "PRÉCHIP".equals(linhaVO.getTipoLinha()) || "CONT".equals(linhaVO.getTipoLinha()) || "CONTCHIP".equals(linhaVO.getTipoLinha())) {
                return "TUXNGINBE";
            } else {
                return null;
            }
        } catch(TuxedoErrorException tee) {
        	logURA.logWarn("uraSvrContract::getServicoLegado[TuxedoErrorException]", tee);
            throw tee;

        }catch(TuxedoWarningException twe){
            logURA.logWarn("uraSvrContract::getServicoLegado[TuxedoWarningException]", twe);
            throw twe;

        }catch(Exception ex){
            logURA.logWarn("uraSvrContract::getServicoLegado[Exception]", ex);
            return "TuxProxyBE";
        }
    }
}
