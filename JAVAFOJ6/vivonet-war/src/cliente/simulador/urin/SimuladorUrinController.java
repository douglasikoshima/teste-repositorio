package cliente.simulador.urin;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;

public class SimuladorUrinController extends AbstractAction {

	private static final long serialVersionUID = 562659011830460316L;

	private transient Logger log = new Logger(
			SimuladorUrinController.class.getName());

	private final String INPUT_REQUEST_PREFIX_DDD = "?input=<msg><msgHdr><user>30</user><service>URINID</service><uuid>99999</uuid><subSystem>07</subSystem><creationTimestamp>0000-00-00%2000:00:00%20000</creationTimestamp><sequence>99999</sequence><corrid>99999</corrid><timeout>0</timeout></msgHdr><msgBody><cdDDD>";
	private final String INPUT_REQUEST_PREFIX_TEL = "</cdDDD><numTelefone>";
	private final String INPUT_REQUEST_SULFIX = "</numTelefone></msgBody></msg>";
	
	private final String NSSTRING_PREFIX = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<ns:string xmlns:ns=\"http://www.openuri.org/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">";
	private final String NSSTRING_SULFIX = "</ns:string>";
	
	private String saida = ConstantesCRM.SVAZIO;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("simularUrin".equals(mapping.getParameter())) {
			return simularUrin(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	protected ActionForward begin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	@SuppressWarnings("deprecation")
	protected ActionForward simularUrin(ActionMapping mapping,
			ActionForm formParam, HttpServletRequest request,
			HttpServletResponse response){

		this.saida = ConstantesCRM.SVAZIO;
		
		String ddd = request.getParameter("ddd");
		String tel = request.getParameter("tel");

		try {
			
			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
			
			GetParametro gp = GetParametro.getInstace();
			
			ParametroVO pvo = gp.getParametroVO(idUsuario, "LINK_REQUEST_URIN");

			String responseXML = pvo.getDsValorParametro().concat(INPUT_REQUEST_PREFIX_DDD)
					.concat(ddd).concat(INPUT_REQUEST_PREFIX_TEL).concat(tel.replaceAll("[^0-9]", ""))
					.concat(INPUT_REQUEST_SULFIX);
			log.error("Response: " + responseXML);
			URL url = new URL(responseXML);

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			
			String line = ConstantesCRM.SVAZIO;
			
			while ((line = reader.readLine()) != null) {
				saida += line;
			}
			
			log.error("Saida1: " + saida);

			saida = saida.substring(NSSTRING_PREFIX.length(), (saida.length() - NSSTRING_SULFIX.length()));
			
			saida = XmlPrettyFormatter.xmlPrettyFormat(saida, 5);
			
			saida = saida.replaceAll(">", "&gt;").replaceAll("<", "&lt;");
			
			log.error("Saida2: " + saida);

			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					response.getOutputStream());
			bufferedOutputStream.write(saida.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			return null;

		} catch (Exception e) {
			log.error("Nao Funciona: ", e);
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}

	}

}
