package WebServicesWSDL.UraURPUP;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.Properties;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import noNamespace.MsgDocument;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import WebServicesWSDL.commons.utils.geral.Constant;
import WebServicesWSDL.commons.utils.geral.Log;
import WebServicesWSDL.xml.XmlManager;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.ConsultarLinhaPUPServiceHttpBindingStub;
import br.com.indrasistemas.vivoservices.listapup.webservice.ConsultarLinhaPUPServiceLocator;
import br.com.indrasistemas.vivoservices.listapup.webservice.GravarLinhaPUPServiceHttpBindingStub;
import br.com.indrasistemas.vivoservices.listapup.webservice.GravarLinhaPUPServiceLocator;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;
import br.com.vivo.fo.pup.vo.UraPUPVODocument;
import br.com.vivo.fo.pup.vo.UraPUPVODocument.UraPUPVO;
import com.indracompany.actions.AbstractAction;

public class UraURPUPController extends AbstractAction {

    private static final long serialVersionUID = -7808430532409776155L;

    private transient Log     logURA           = new Log("UraURPUPController");

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("URPUP".equals(mapping.getParameter())) {
            return URPUP(mapping, form, request, response);
        }

        return begin(mapping, form, request, response);
    }

    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return null;
    }

    /** @jpf:action */
    public ActionForward URPUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // <?xml version="1.0" encoding="UTF-8"?><msg><msgHdr><user>1</user><service>URPUP</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00 00:00:00000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><UraPUPVO xmlns="pup.fo.vivo.com.br/vo" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"><cdTransacao>URPUP</cdTransacao><cdUsuario>24</cdUsuario><cdDDD>11</cdDDD><cdNumTelefone>99906840</cdNumTelefone><cdOperacao>0</cdOperacao><SMSPROT></SMSPROT><SMSPROM></SMSPROM><SMSPROD></SMSPROD><SMSPARC></SMSPARC></UraPUPVO></msgBody></msg>
        ConsultarLinhaPUPServiceHttpBindingStub proxyConsulta;
        GravarLinhaPUPServiceHttpBindingStub proxyGrava;

        String input = request.getParameter("input");

        logURA.log("[XMLIN-WS]>>" + input + "<<");

        UraPUPVO pupVO = UraPUPVO.Factory.newInstance();
        String saida = "";

        StringBuffer retorno = new StringBuffer();

        try {
            pupVO = this.validarXML(input);

        } catch (Exception ex) {
            logURA.log("URPUP::URPUP[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "02", ex.getMessage());

            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        Properties props = new Properties();
        try {
            //prop.load(new FileInputStream(request.getRealPath("/WEB-INF/common.properties")));
            if(Thread.currentThread().getContextClassLoader().getResource("/common.properties")!=null) {
                props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/common.properties"));
            } else {
                throw new IOException("Nenhum arquivo common.properties encontrado.");
            }

            String consulta = props.getProperty("urlConsultaPUP");
            String gravar = props.getProperty("urlGravaPUP");
            URL urlConsultaPUP = new URL(consulta);
            URL urlGravaPUP = new URL(gravar);

            proxyConsulta = new ConsultarLinhaPUPServiceHttpBindingStub(urlConsultaPUP, new ConsultarLinhaPUPServiceLocator());
            proxyConsulta.setUserTimeout(new Integer(10000));

            proxyGrava = new GravarLinhaPUPServiceHttpBindingStub(urlGravaPUP, new GravarLinhaPUPServiceLocator());
            proxyGrava.setUserTimeout(new Integer(10000));

        } catch (Exception ex) {
            logURA.log("URPUP::URPUP[Exception]", ex);
            saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "04", "erro na parametrização do endereço do webservice");
            response.setContentType(Constant.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;
        }

        saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "99");

        // consulta
        if (pupVO.getCdOperacao() == 0) {
            // faz a consulta
            RequestInfoTO requestInfo = new RequestInfoTO();
            requestInfo.setIp("");
            requestInfo.setUserName("ura");
            requestInfo.setSystemModule("URA");
            requestInfo.setUserId(new Long(0));

            BigInteger cdCanal = new BigInteger("9");
            BigInteger cdProcedencia = new BigInteger("19");

            // ResultadoLinhaPUPTO consultaPUP = consultarFacade.consultarLinhaPUP(requestInfo,cdCanal,cdProcedencia,null,null,pupVO.getCdDDD(),pupVO.getCdNumTelefone());
            ResultadoLinhaPUPTO consultaPUP = proxyConsulta.consultarLinhaPUP(requestInfo, cdCanal.intValue(), cdProcedencia.intValue(), null, null, pupVO.getCdDDD(), pupVO.getCdNumTelefone());

            if (consultaPUP == null || consultaPUP.getCdRetorno() == null) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "04", "erro no WebService de consulta");
            }

            if (consultaPUP.getCdRetorno().equals("01") || consultaPUP.getCdRetorno().equals("05")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "01");
            }

            if (consultaPUP.getLinhaPUPWSTO() == null) {
                retorno.append("<SMSPROT></SMSPROT>");
                retorno.append("<SMSPROM></SMSPROM>");
                retorno.append("<SMSPROD></SMSPROD>");
                retorno.append("<SMSPARC></SMSPARC>");
            } else {
                retorno.append("<SMSPROT>" + checkInt(consultaPUP.getLinhaPUPWSTO().getInSMSProtocolos()) + "</SMSPROT>");
                retorno.append("<SMSPROM>" + checkInt(consultaPUP.getLinhaPUPWSTO().getInSMSPromocoes()) + "</SMSPROM>");
                retorno.append("<SMSPROD>" + checkInt(consultaPUP.getLinhaPUPWSTO().getInSMSProdutos()) + "</SMSPROD>");
                retorno.append("<SMSPARC>" + checkInt(consultaPUP.getLinhaPUPWSTO().getInSMSParceiros()) + "</SMSPARC>");
            }
            if (consultaPUP.getCdRetorno().equals("00")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "00", retorno.toString());
            }

        } else { // gravar
            Integer inSMSProtocolos = checkValor(pupVO.getSMSPROT());
            Integer inSMSPromocoes = checkValor(pupVO.getSMSPROM());
            Integer inSMSProdutos = checkValor(pupVO.getSMSPROD());
            Integer inSMSParceiros = checkValor(pupVO.getSMSPARC());
            Integer inIVRPromocoes = null;
            Integer inIVRProdutos = null;
            Integer inIVRParceiros = null;
            //Long[] nrMin = new Long[] { new Long(String.valueOf(pupVO.getCdDDD()) + String.valueOf(pupVO.getCdNumTelefone())) };
            String temp = String.valueOf(pupVO.getCdDDD()) + String.valueOf(pupVO.getCdNumTelefone());
            long[] nrMin = new long[1];
            nrMin[0] = new Long(temp).longValue();

            BigInteger cdCanal = new BigInteger("9");
            BigInteger cdProcedencia = new BigInteger("19");

            RequestInfoTO requestInfo = new RequestInfoTO();
            requestInfo.setIp("");
            requestInfo.setUserName("ura");
            requestInfo.setSystemModule("URA");
            requestInfo.setUserId(new Long(0));

            // ResultadoLinhaPUPTO consultaPUP =
            // gravarFacade.gravarLinhaPUP(requestInfo, cdCanal,
            // cdProcedencia,null,null, nrMIN, 0, inSMSProtocolos,
            // inSMSPromocoes, inSMSProdutos, inSMSParceiros, inIVRPromocoes,
            // inIVRProdutos, inIVRParceiros);

            ResultadoLinhaPUPTO consultaPUP = proxyGrava.gravarLinhaPUP(requestInfo, cdCanal.intValue(), cdProcedencia.intValue(), null, null, nrMin, 0, inSMSProtocolos, inSMSPromocoes,
                    inSMSProdutos, inSMSParceiros, inIVRPromocoes, inIVRProdutos, inIVRParceiros, null);

            if (consultaPUP == null || consultaPUP.getCdRetorno() == null) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "04", "erro no WebService de gravação");
            }

            if (consultaPUP.getCdRetorno().equals("01") || consultaPUP.getCdRetorno().equals("05")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "01");
            }

            if (consultaPUP.getCdRetorno().equals("00")) {
                saida = XmlManager.createRetorno(Constant.URA_STATCOM_OK, "00");
            }
        }

        response.setContentType(Constant.SContentType);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
        bufferedOutputStream.write(saida.toString().getBytes(Constant.SISO));
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
        return null;
    }

    private Integer checkValor(String valor) {
        try {
            Integer.parseInt(valor);

        } catch (Exception ex) {
            return null;
        }

        if (valor != null && valor.trim().length() > 0) {
            Integer inteiro = new Integer(valor);
            return inteiro;
        }
        return null;
    }

    private int checkInt(Integer inteiro) {
        if (inteiro != null) {
            return inteiro.intValue();
        } else {
            return 0;
        }
    }

    private UraPUPVO validarXML(String xml) throws Exception {
        MsgDocument msgInput = MsgDocument.Factory.parse(xml);
        UraPUPVODocument doc  = UraPUPVODocument.Factory.parse(msgInput.getMsg().getMsgBody().toString());
        UraPUPVO pupVO = doc.getUraPUPVO();

        if(pupVO.getCdDDD() == 0 || pupVO.getCdDDD() > 99){
            throw new Exception("DDD inválido");
        }
        if(pupVO.getCdNumTelefone() == 0 || (String.valueOf(pupVO.getCdNumTelefone()).length() < 8 || String.valueOf(pupVO.getCdNumTelefone()).length() > 9 )){
            throw new Exception("Número de linha inválida");
        }
        if(String.valueOf(pupVO.getCdOperacao())==null || pupVO.getCdOperacao() > 1){
            throw new Exception("Operação inválida");
        }
        if(pupVO.getCdTransacao() == null || !pupVO.getCdTransacao().equals("URPUP")){
            throw new Exception("Código de transação inválido");
        }
        if(pupVO.getCdUsuario() == null || !pupVO.getCdUsuario().equals("24")){
            throw new Exception("Código de usuário inválido");
        }
      /*  if(String.valueOf(pupVO.getSMSPROT()) == null || pupVO.getSMSPROT() > 1 ){
            throw new Exception("Código de SMSPROT inválido");
        }
        if(String.valueOf(pupVO.getSMSPROM()) == null || pupVO.getSMSPROM() > 1 ){
            throw new Exception("Código de SMSPROM inválido");
        }
        if(String.valueOf(pupVO.getSMSPROD()) == null || pupVO.getSMSPROD() > 1 ){
            throw new Exception("Código de SMSPROD inválido");
        }
        if(String.valueOf(pupVO.getSMSPARC()) == null || pupVO.getSMSPARC() > 1 ){
            throw new Exception("Código de SMSPARC inválido");
        }*/
        return pupVO;
    }
}
