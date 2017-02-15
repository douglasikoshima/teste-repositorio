package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.AparelhosManterGetVODocument.AparelhosManterGetVO;
import br.com.vivo.fo.fidelizacao.vo.AparelhosPesquisaInVODocument.AparelhosPesquisaInVO;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ListaAparelhoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaAparelhoVODocument.ListaAparelhoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "AparelhoFacade", mappedName = "AparelhoFacade")
@Local(AparelhoFacade.class)
@Session(ejbName = "AparelhoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AparelhoFacadeImpl implements AparelhoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("fidelizacao");
	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public ListaAparelhoVO getAparelho(String user, AparelhosManterGetVO aparelhoVO) throws TuxedoException, FacadeException {
		log.debug("AparelhoFacadeImpl:getAparleho(" + user + ")");
		try {
			ListaAparelhoVO aparelho = null;
			xmlIN = "<getreg>" + user + "</getreg>" + aparelhoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "SELAPARELHO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			aparelho = ListaAparelhoVODocument.Factory.parse(xmlOUT).getListaAparelhoVO();

			return aparelho;

		} catch (XmlException ex) {
			log.error("XmlException - AparelhoFacadeImpl:getAparelho(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AparelhoFacadeImpl:getAparelho", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - AparelhoFacadeImpl:getAparelho(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - AparelhoFacadeImpl:getAparelho(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ArrayListaGeralVO getDadosIniciais(String user) throws TuxedoException, FacadeException {
		try {
			ArrayListaGeralVO arrayLista = null;

			// monta xml que servira de entrada para o servico tuxcedo
			xmlIN = "<getreg>GETAPAPARELHO</getreg>";
			xmlIN = XmlManager.MakeXmlIn(user, "GETAPAPARELHO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			arrayLista = ArrayListaGeralVODocument.Factory.parse(xmlOUT).getArrayListaGeralVO();
			return arrayLista;

		} catch (XmlException ex) {
			log.error("XmlException - AparelhoFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AparelhoFacadeImpl:getDadosIniciais", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - AparelhoFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - AparelhoFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String addNovoAparelho(String user, AparelhosPesquisaInVO aparelhoIN) throws TuxedoException, FacadeException {
		try {
			log.debug("GruposProcessosFacadeImpl:addNovoAparelho(" + user + ", " + aparelhoIN.getDsModelo() + ")");
			xmlIN = aparelhoIN.xmlText().replaceAll("vo:", "");
			xmlIN = XmlManager.MakeXmlIn(user, "INSAPARELHO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return (xmlOUT);

		} catch (XmlException ex) {
			log.error("XmlException - AprelhoFacadeImpl:addNovoAparelho(" + user + ", " + aparelhoIN.getDsModelo() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AprelhoFacadeImpl:addNovoAparelho", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - AprelhoFacadeImpl:addNovoAparelho(" + user + ", " + aparelhoIN.getDsModelo() + ") - [" + ex.getMessage() + "]");
			if (ex.getLocalizedMessage().lastIndexOf("Aparelho jah existe") > 0) {
				return (ex.getLocalizedMessage());
			} else {
				throw new TuxedoException(ex);
			}

		} catch (Exception ex) {
			log.error("Exception - AprelhoFacadeImpl:addNovoAparelho(" + user + ", " + aparelhoIN.getDsModelo() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String dellAparelho(String user, String id) throws FacadeException, TuxedoException {
		try {
			log.debug("AparelhoFacadeImpl:dellAparelho(" + user + ", " + id + ")");

			// Monta XML IN de acordo com o serviço a ser executado
			xmlIN = "<idAparelho>" + id + "</idAparelho>";
			xmlIN = XmlManager.MakeXmlIn(user, "EXCAPARELHO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("XmlException - AprelhoFacadeImpl:delNovoAparelho(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AprelhoFacadeImpl:delNovoAparelho", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - AprelhoFacadeImpl:delNovoAparelho(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
			if (ex.getLocalizedMessage().indexOf("Existe Matriz Associada!") > 0) {
				return (ex.getLocalizedMessage());
			} else {
				throw new TuxedoException(ex);
			}

		} catch (Exception ex) {
			log.error("Exception - AprelhoFacadeImpl:dellAparelho(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String editaAparelho(String user, AparelhosPesquisaInVO aparelho) throws FacadeException, TuxedoException {
		try {
			log.debug("AparelhoFacadeImpl:editaAparelho(" + user + ", " + aparelho.getIdAparelho() + aparelho.getIdMarca() + aparelho.getDsModelo() + aparelho.getDsManualURL() + ")");

			xmlIN = aparelho.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UPDAPARELHO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return (xmlOUT);

		} catch (XmlException ex) {
			log.error("XmlException - AprelhoFacadeImpl:editaAparelho(" + user + ", " + aparelho.getIdAparelho() + aparelho.getIdMarca() + aparelho.getDsModelo() + aparelho.getDsManualURL() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AprelhoFacadeImpl:editaAparelho", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - AprelhoFacadeImpl:editaAparelho(" + user + ", " + aparelho.getIdAparelho() + aparelho.getIdMarca() + aparelho.getDsModelo() + aparelho.getDsManualURL() + ") - [" + ex.getMessage() + "]");
			if (ex.getLocalizedMessage().indexOf("Aparelho jah existe") > 0) {
				return (ex.getLocalizedMessage());
			} else {
				throw new TuxedoException(ex);
			}

		} catch (Exception ex) {
			log.error("Exception - AprelhoFacadeImpl:editaAparelho(" + user + ", " + aparelho.getIdAparelho() + aparelho.getIdMarca() + aparelho.getDsModelo() + aparelho.getDsManualURL() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setCores(String user, AparelhosPesquisaInVO aparelhoVO) throws FacadeException, TuxedoException {
		try {
			// String operacao ,String[] id , String[] dadosAparelho
			log.debug("AparelhoFacadeImpl:editaAparelho(" + user + " cores selecionadas , cores excluidas)");

			// Monta XML IN de acordo com o serviço a ser executado
			xmlIN = aparelhoVO.xmlText().replaceAll("vo:", "");
			xmlIN = XmlManager.MakeXmlIn(user, "UPDAPARELHOCOR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - AparelhoFacadeImpl:editaAparelho(" + user + " cores selecionadas , cores excluidas) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AparelhoFacadeImpl:editaAparelho", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - AparelhoFacadeImpl:editaAparelho(" + user + " cores selecionadas , cores excluidas) - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - AparelhoFacadeImpl:editaAparelho(" + user + " cores selecionadas , cores excluidas) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/*
	 * private void writeFileXML(String xmlOUT, String fileName) throws Exception{
	 * 
	 * java.io.File outFile = new java.io.File("D:\\xmlsFO\\" + fileName + ".xml"); //O cara q escreve
	 * java.io.FileWriter out = new java.io.FileWriter(outFile); //Seu conteudo de saida out.write(xmlOUT); //Nao pode
	 * esquecer de fechar o objeto de saida out.close();
	 * 
	 * }
	 */
}
