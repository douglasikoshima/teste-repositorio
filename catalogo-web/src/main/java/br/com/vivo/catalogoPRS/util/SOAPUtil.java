package br.com.vivo.catalogoPRS.util;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.AxisFault;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import br.com.vivo.catalogoPRS.ws.catalogoCanal.mc.geral.CabecalhoVivo;
import br.com.vivo.catalogoPRS.ws.catalogoCanal.mc.geral.CabecalhoVivoNomeAplicacao;

/*import com.bea.control.ServiceControlException;
import com.bea.control.SoapFault;*/

public abstract class SOAPUtil {
	
	public static Element[] createOutputHeader(String loginUsuario, String codigoFuncionalidade, String ip, HttpServletRequest request, String nomeServico){
		CabecalhoVivo cabecalhoVivo = new CabecalhoVivo();
		
		cabecalhoVivo.setLoginUsuario(loginUsuario);
		cabecalhoVivo.setCodigoSessao(request.getSession().getId());
		cabecalhoVivo.setNomeAplicacao(CabecalhoVivoNomeAplicacao.value11);
		cabecalhoVivo.setEnderecoIP(request.getRemoteAddr());
		cabecalhoVivo.setCodigoFuncionalidade(codigoFuncionalidade);
		cabecalhoVivo.setDataTransacao(Calendar.getInstance());
		cabecalhoVivo.setNomeServico(nomeServico);
		
		Element header = (Element) cabecalhoVivo;
		
		return new Element[] { header };
	}
	
	public static Long obterCodigoErroSoapFault(Exception e) {
		Long codErro = null;
		/*if (e instanceof ServiceControlException) {
			try {
				ServiceControlException serviceException = (ServiceControlException)e;
				if (serviceException.hasSoapFault()) {
					SoapFault soapFault = serviceException.getSoapFault();
					XmlObject xmlErroInfo = soapFault.getDetail();
					Node erroInfoNode = xmlErroInfo.getDomNode().getFirstChild();
					String erroInfoXmlText = xmlErroInfo.xmlText();
					String codigoNodeName = erroInfoNode.getFirstChild().getNodeName().trim();
					String tagAbrindo = "<" + codigoNodeName + ">";
					String tagFechando = "</" + codigoNodeName + ">";
					String codigoErro = erroInfoXmlText.substring(erroInfoXmlText.indexOf(tagAbrindo) + tagAbrindo.length(), erroInfoXmlText.indexOf(tagFechando));
					codErro = Long.valueOf(codigoErro);
				}
			} catch (Exception ex) {
				codErro = null;
			}
		}*/
		return codErro;
	}
	
	public static String obterCodigoErroAxisFault(AxisFault e) {
		String codErro = null;
		
		if (e.getFaultDetails().length > 0) {
			Node node = e.getFaultDetails()[0];
			for (int i = 0; i < node.getChildNodes().getLength(); i++) {
				Node nodefilho = node.getChildNodes().item(i).getFirstChild();
				
				if (!nodefilho.hasChildNodes()) {
					if (node.getChildNodes().item(i).getNodeName().contentEquals("ger:codigo")) {
						codErro = nodefilho.getNodeValue();
						break;
					}					
				}				
			}
		}	
		return codErro;
	}

}
