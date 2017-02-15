package br.com.vivo.fo.acesso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlException;

import br.com.vivo.fo.commons.utils.businessDelegate.JATMIBusinessDelegate;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ControleAcessoEnvioVODocument.ControleAcessoEnvioVO;
import br.com.vivo.fo.usuario.vo.RuleVODocument;
import br.com.vivo.fo.usuario.vo.RuleVODocument.RuleVO;
import br.com.vivo.fo.xml.XmlManager;

public class ControlAcesso implements Serializable {

	private static final long serialVersionUID = 6378078632046118967L;
	private static Logger log = new Logger(ControlAcesso.class.getName());

	public ControlAcesso(){
	}

	public HashMap<String, List<String>> checaAcesso(String user, String sessionId) throws TuxedoException, FacadeException {
	    String xmlIN  = ConstantesCRM.SVAZIO;
	    String xmlOUT = ConstantesCRM.SVAZIO;
		
	    ControleAcessoEnvioVO controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance();
		controleAcessoEnvioVO.setLogin(user);
		controleAcessoEnvioVO.setSessionId(sessionId);
		String xmlRetorno[] = null;

		try {
			xmlIN = controleAcessoEnvioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();

			long timeIni = System.currentTimeMillis();
			xmlOUT = jatmi.executeCommnad(user,"USRLISTID", xmlIN);

			log.debug((new StringBuffer("ControlAcesso:checaAcesso() - Tempo de execução do serviço UsrAcesso :")).append(System.currentTimeMillis() - timeIni));
			xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);

		} catch (XmlException ex) {
			log.error((new StringBuffer("XmlException - ControlAcesso:checaAcesso(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));

		} catch (Exception ex) {
			log.error((new StringBuffer("Exception - ControlAcesso:checaAcesso(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException(ex));
		}

		try {
			if ("w".equalsIgnoreCase(xmlRetorno[1])) {
				log.warn("ControlAcesso:checaAcesso() - TuxedoWarningException");
				throw new TuxedoWarningException((new XmlManager(xmlOUT)).getXmlHeader());
			}
			if ("e".equalsIgnoreCase(xmlRetorno[1])) {
				log.error("ControlAcesso:checaAcesso() - FacadeException");
				throw new TuxedoWarningException((new XmlManager(xmlOUT)).getXmlHeader());
			}

			RuleVO ruleVO = RuleVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRuleVO();
			HashMap<String, List<String>> hash = new HashMap<String, List<String>>();

			ArrayList<String> arrayUni = null;
			for (int i = 0; i < ruleVO.getArArray().length; i++) {
				arrayUni = new ArrayList<String>();
				for (int x = 0; x < ruleVO.getArArray(i).getUnArray().length; x++) {
					arrayUni.add(ruleVO.getArArray(i).getUnArray(x));
				}
				hash.put(ruleVO.getArArray(i).getUr(), arrayUni);
			}
			return hash;

		} catch (XmlException ex) {
			log.error("XmlException - ControlAcesso:checaAcesso("+user+") - ["+ex.getMessage()+"]", ex);
			throw new FacadeException("Erro na montagem do XML de entrada. (XMLIN)", ex);
		}
	}

	public void checaUsuarioEstaLogado(String user, String sessionId) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;

		ControleAcessoEnvioVO controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance();
		controleAcessoEnvioVO.setLogin(user);
		controleAcessoEnvioVO.setSessionId(sessionId);
		controleAcessoEnvioVO.setInSair(ConstantesCRM.SZERO);

		String xmlRetorno[] = null;
		try {
			xmlIN = controleAcessoEnvioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();

			long timeIni = System.currentTimeMillis();
			xmlOUT = jatmi.executeCommnad(user,"UsrAcesso", xmlIN);

			log.debug((new StringBuffer("ControlAcesso:checaAcesso() - Tempo de execução do serviço UsrAcesso :")).append(System.currentTimeMillis() - timeIni));
			xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);

		} catch (XmlException ex) {
			log.error((new StringBuffer("XmlException - ControlAcesso:checaAcesso(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));

		} catch (Exception ex) {
			log.error((new StringBuffer("Exception - ControlAcesso:checaAcesso(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException(ex));
		}

		if ("w".equalsIgnoreCase(xmlRetorno[1])) {
			log.warn("ControlAcesso:checaAcesso() - TuxedoWarningException");
			throw new TuxedoWarningException(xmlRetorno[3]);
		}
		return;
	}

	public void saidaSistema(String user, String sessionId) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;

		ControleAcessoEnvioVO controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance();
		controleAcessoEnvioVO.setLogin(user);
		controleAcessoEnvioVO.setSessionId(sessionId);
		controleAcessoEnvioVO.setInSair(ConstantesCRM.SONE);
		String xmlRetorno[] = null;

		try {
			xmlIN = controleAcessoEnvioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();

			xmlOUT = jatmi.executeCommnad(user, "UsrAcesso", xmlIN);
			xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);

		} catch (XmlException ex) {
			log.error((new StringBuffer("XmlException - ControlAcesso:saidaSistema(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));

		} catch (Exception ex) {
			log.error((new StringBuffer("Exception - ControlAcesso:saidaSistema(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException(ex));
		}

		try {
			if ("w".equalsIgnoreCase(xmlRetorno[1])) {
				log.warn("ControlAcesso:saidaSistema() - TuxedoWarningException");
				throw new TuxedoWarningException((new XmlManager(xmlOUT)).getXmlHeader());
			}
			if ("e".equalsIgnoreCase(xmlRetorno[1])) {
				log.error("ControlAcesso:saidaSistema() - TuxedoWarningException");
				throw new TuxedoWarningException((new XmlManager(xmlOUT)).getXmlHeader());
			}
		} catch (XmlException ex) {
			log.error((new StringBuffer("XmlException - ControlAcesso:saidaSistema(")).append(user).append(") - [").append(ex.getMessage()).append("]"));
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}
}
