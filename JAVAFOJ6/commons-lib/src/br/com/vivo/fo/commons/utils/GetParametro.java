package br.com.vivo.fo.commons.utils;

import br.com.vivo.fo.cliente.vo.ParametroVODocument;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.commons.utils.businessDelegate.JATMIBusinessDelegate;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.xml.XmlManager;
import java.io.Serializable;

import noNamespace.MsgDocument;

public class GetParametro implements Serializable {

	private static final long serialVersionUID = 7760491189297514077L;
	private static GetParametro instance = new GetParametro();

	private GetParametro() {
	}

	public static synchronized GetParametro getInstace() throws Exception {
		return instance;
	}

	public ParametroVO getParametroVO(String user, String parametro) throws Exception {
		if (parametro == null || "".equals(parametro)) {
			throw new Exception("Valor do Parametro requerido");
		}
		String xmlIN = "<cdParametro>" + parametro + "</cdParametro>";
		JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();

		String xmlOUT = jatmi.executeCommnad(user,"GETPARAMETRO", xmlIN);
		String[] xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);

		if ("w".equalsIgnoreCase(xmlRetorno[1])) {
			throw new TuxedoWarningException((new XmlManager(xmlOUT)).getXmlHeader());
		}
		
		if ("e".equalsIgnoreCase(xmlRetorno[1])) {
			throw new TuxedoWarningException((new XmlManager(xmlOUT)).getXmlHeader());
		}

		return ParametroVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getParametroVO();
	}

}
