package cliente.ConsultaSerialSAP;

import java.io.BufferedOutputStream;
import java.rmi.RemoteException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.www.MC.Assinatura.AtributoLinha;
import br.com.vivo.www.MC.Geral.ErroInfo;
import br.com.vivo.www.tracking.ConsultaImeiSap.ConsultaImeiSapBindingStub;
import br.com.vivo.www.tracking.ConsultaImeiSap.ConsultaImeiSapPortType;
import br.com.vivo.www.tracking.ConsultaImeiSap.ConsultaImeiSapPortTypeProxy;
import br.com.vivo.www.tracking.ConsultaImeiSap.ConsultaImeiSapRequest;
import br.com.vivo.www.tracking.ConsultaImeiSap.ConsultaImeiSapResponse;

import com.indracompany.actions.AbstractAction;

public class ConsultaSerialSAPController extends AbstractAction {

	private static final long serialVersionUID = -1767058774315871553L;

	protected global.Global globalApp = new global.Global();

	private ConsultaForm consultaForm = null;
	private final String PARAMETRO_ENDPOINT_BUSCA_SERIALSAP = "PARAMETRO_ENDPOINT_BUSCA_SERIALSAP";

	private transient Logger log = new Logger("tracking");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)  {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public ConsultaForm getConsultaForm(){
		if(this.consultaForm==null){
			this.consultaForm = new ConsultaForm();
		}
		return this.consultaForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String nrSerial = request.getParameter("nrSerial");
		StringBuffer xmlDados = new StringBuffer();
		ConsultaImeiSapResponse resposta = null;
		AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
		String msgError = "Serviço indisponível no momento";
		try{
			if(nrSerial==null || "".equals(nrSerial)){
				log.error("Número Serial não informado!");
				throw new Exception("Favor informar o Serial");
			}

			ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, PARAMETRO_ENDPOINT_BUSCA_SERIALSAP);
			log.debug("Chave para busca de parametro de pesquisa Serial SAP: "+parametro.getCdParametro());
			log.debug("End Point para pesquisa Serial SAP: "+parametro.getDsValorParametro());

			ConsultaImeiSapPortTypeProxy proxy = new ConsultaImeiSapPortTypeProxy();
			proxy.setEndpoint(parametro.getDsValorParametro());

			ConsultaImeiSapPortType type = proxy.getConsultaImeiSapPortType();
			((ConsultaImeiSapBindingStub) type).setTimeout(20 * 1000); //20 segundos...
			log.debug("Definido tempo limite de 20 segundos para WS Consulta Serial SAP");

			ConsultaImeiSapRequest consultaImeiSapRequest = new ConsultaImeiSapRequest(nrSerial);
			log.debug("Chamando WS para o serial: "+nrSerial);

			resposta = type.consultaImeiSap(consultaImeiSapRequest);

			AtributoLinha atributoLinha = resposta.getCliente().getAssinaturas().getAssinatura(0).getAtributosLinha().getAtributoLinha(0);
			xmlDados
			.append("<xml-fragment>")
			.append("<dsStatus>").append(resposta.getErroInfo().getCodigo()).append("</dsStatus>")
			.append("<dsRazaoSocial>").append(resposta.getCliente().getRazaoSocial()).append("</dsRazaoSocial>")
			.append("<dsMaterial>").append(atributoLinha.getProduto().getCodigoSAP()).append("</dsMaterial>")
			.append("<dsDescricao>").append(atributoLinha.getProduto().getDescricao()).append("</dsDescricao>")
			.append("</xml-fragment>");

			log.debug("Chamado efetuado com sucesso: "+xmlDados.toString());

		}catch(ErroInfo e){
			if(e.getFaultString().indexOf("time") > -1){
				msgError += "...(Time Out)";
			}
			log.error("ErroInfo: "+e.getFaultString(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getFaultString());
			ajaxErrorHandlerVO.setFriendlyMessage(e.getDescricao());
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		}catch(org.apache.axis.AxisFault e){
			if(e.getFaultString().indexOf("time") > -1){
				msgError += "...(Time Out)";
			}
			log.error("AxisFault: "+e.getFaultString(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getFaultString());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		}catch(RemoteException e){
			if(e.getMessage().indexOf("time") > -1){
				msgError += "...(Time Out)";
			}
			log.error("RemoteException: "+e.getMessage(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		}catch(Exception e){
			log.error("Exception: "+e.getMessage(), e);
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(msgError);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		}finally{
			try{
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			}catch(Exception e){}
		}
		return null;
	}

	public static class ConsultaForm extends ActionForm {

		private static final long serialVersionUID = 8064281605393780573L;

		private String tpNrSerial;
		private String nrSerial;

		public void setNrSerial(String nrSerial){
			this.nrSerial = nrSerial;
		}

		public String getNrSerial(){
			return this.nrSerial;
		}

		public void setTpNrSerial(String tpNrSerial){
			this.tpNrSerial = tpNrSerial;
		}

		public String getTpNrSerial(){
			return this.tpNrSerial;
		}
	}
}
