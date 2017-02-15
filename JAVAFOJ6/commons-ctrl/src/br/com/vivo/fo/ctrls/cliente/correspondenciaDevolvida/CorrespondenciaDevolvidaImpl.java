package br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida;

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
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaTelaInicialVODocument;
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaTelaInicialVODocument.CorrespDevolvidaTelaInicialVO;
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument;
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.CorrespDevolvidaVODocument.CorrespDevolvidaVO.FiltroCorrespDevolvida;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.ListaMotivoDevolucaoVODocument;
import br.com.vivo.fo.cliente.vo.ListaMotivoDevolucaoVODocument.ListaMotivoDevolucaoVO;
import br.com.vivo.fo.cliente.vo.ListaStatusCorrespVODocument;
import br.com.vivo.fo.cliente.vo.ListaStatusCorrespVODocument.ListaStatusCorrespVO;
import br.com.vivo.fo.cliente.vo.ListaTipoCorrespVODocument;
import br.com.vivo.fo.cliente.vo.ListaTipoCorrespVODocument.ListaTipoCorrespVO;
import br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument;
import br.com.vivo.fo.cliente.vo.ManterCorrespDevolvidaVODocument.ManterCorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.UnidadeVODocument;
import br.com.vivo.fo.cliente.vo.UnidadeVODocument.UnidadeVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "CorrespondenciaDevolvida", mappedName = "CorrespondenciaDevolvida")
@Local(CorrespondenciaDevolvida.class)
@Session(ejbName = "CorrespondenciaDevolvida", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CorrespondenciaDevolvidaImpl implements CorrespondenciaDevolvida {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("clientes");

	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public TratarCorrespDevolvidaVO getTratarCorrepDevolvida(String user, TratarCorrespDevolvidaVO tratarCorrespVO) throws TuxedoException, FacadeException {

		TratarCorrespDevolvidaVO tratarCorrespDevolvida = TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO.Factory.newInstance();
		try {
			xmlIN = ConstantesCRM.SVAZIO;
			xmlIN = tratarCorrespVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CorrDevMantIni", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			tratarCorrespDevolvida = TratarCorrespDevolvidaVODocument.Factory.parse(xmlOUT).getTratarCorrespDevolvidaVO();

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getTratarCorrepDevolvida(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getTratarCorrepDevolvida", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getTratarCorrepDevolvida(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return tratarCorrespDevolvida;
	}

	/**
	 * @common:operation
	 */
	public CorrespDevolvidaVO getCorrespDevolvida(String user, FiltroCorrespDevolvida filtroCorrespDevolvida) throws TuxedoException, FacadeException {

		CorrespDevolvidaVO correspDevolvida = CorrespDevolvidaVODocument.CorrespDevolvidaVO.Factory.newInstance();
		try {
			xmlIN = ConstantesCRM.SVAZIO;
			if (filtroCorrespDevolvida.getDtDevolucaoIni() != null || filtroCorrespDevolvida.getDtRegistroIni() != null) {
				xmlIN = filtroCorrespDevolvida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "CORRDEVFILTCO", xmlIN);
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			} else {
				xmlIN = filtroCorrespDevolvida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "CorrDevFiltro", xmlIN);
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			}
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			correspDevolvida = CorrespDevolvidaVODocument.Factory.parse(xmlOUT).getCorrespDevolvidaVO();

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getCorrespDevolvida(" + user + ", " + filtroCorrespDevolvida.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getCorrespDevolvida", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getCorrespDevolvida(" + user + ", " + filtroCorrespDevolvida.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return correspDevolvida;
	}

	/**
	 * @common:operation
	 */
	public ManterCorrespDevolvidaVO getManterCorrespDevolvidaBusca(String user, String pesquisa, ManterCorrespDevolvidaVO manterCorrespDevolvida) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			if (pesquisa == null) {
				pesquisa = " ";
			}

			if (pesquisa.trim().length() > 0) {
				xmlIN = "<pesquisa>" + pesquisa + "<pesquisa/>";
				xmlIN = XmlManager.MakeXmlIn(user, "CorrDevCadCons", manterCorrespDevolvida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);

			} else {
				xmlIN = "<NoSearch/>";
				xmlIN = XmlManager.MakeXmlIn(user, "CorrDevCadIni", manterCorrespDevolvida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			}
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			manterCorrespDevolvida = ManterCorrespDevolvidaVODocument.Factory.parse(xmlOUT).getManterCorrespDevolvidaVO();

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getManterCorrespDevolvidaBusca(" + user + ", " + pesquisa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getManterCorrespDevolvidaBusca", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getManterCorrespDevolvidaBusca(" + user + ", " + pesquisa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return manterCorrespDevolvida;
	}

	/**
	 * @common:operation
	 */
	public ListaMotivoDevolucaoVO getListaMotivoDevolucao(String user) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:getListasMotivoDevolucao(" + user + ")");

			ListaMotivoDevolucaoVO listaMotivoDevolucaoVO = null;
			xmlIN = XmlManager.MakeXmlIn(user, "MotDevList", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			listaMotivoDevolucaoVO = ListaMotivoDevolucaoVODocument.Factory.parse(xmlOUT).getListaMotivoDevolucaoVO();

			return listaMotivoDevolucaoVO;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getListasMotivoDevolucao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getListasMotivoDevolucao", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getListasMotivoDevolucao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setAlterarMotivoDevolucao(String user, ListaMotivoDevolucaoVO listaMotivoDevolucaoVO) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setAlterarMotivoDevolucao(" + user + ", " + listaMotivoDevolucaoVO + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "MotDevAlt", listaMotivoDevolucaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setAlterarMotivoDevolucao(" + user + ", " + listaMotivoDevolucaoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setAlterarMotivoDevolucao", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setAlterarMotivoDevolucao(" + user + ", " + listaMotivoDevolucaoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setGravarMotivoDevolucao(String user, ListaMotivoDevolucaoVO listaMotivoDevolucaoVO) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setGravarMotivoDevolucao(" + user + ", " + listaMotivoDevolucaoVO + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "MotDevInc", listaMotivoDevolucaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setGravarMotivoDevolucao(" + user + ", " + listaMotivoDevolucaoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setGravarMotivoDevolucao", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setGravarMotivoDevolucao(" + user + ", " + listaMotivoDevolucaoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ListaTipoCorrespVO getListaTipoCorresp(String user) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:getListaTipoCorresp(" + user + ")");
			ListaTipoCorrespVO listaTipoCorrespVO = null;

			xmlIN = ConstantesCRM.SVAZIO;
			xmlIN = XmlManager.MakeXmlIn(user, "TipoCorrList", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			listaTipoCorrespVO = ListaTipoCorrespVODocument.Factory.parse(xmlOUT).getListaTipoCorrespVO();

			return listaTipoCorrespVO;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getListaTipoCorresp(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getListaTipoCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getListaTipoCorresp(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setAlterarTipoCorresp(String user, ListaTipoCorrespVO listaTipoCorrespVO) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setAlterarTipoCorresp(" + user + ", " + listaTipoCorrespVO + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "TipoCorrAlt", listaTipoCorrespVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setAlterarTipoCorresp(" + user + ", " + listaTipoCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setAlterarTipoCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setAlterarTipoCorresp(" + user + ", " + listaTipoCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setGravarTipoCorresp(String user, ListaTipoCorrespVO listaTipoCorrespVO) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setGravarTipoCorresp(" + user + ", " + listaTipoCorrespVO + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "TipoCorrInc", listaTipoCorrespVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setGravarTipoCorresp(" + user + ", " + listaTipoCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setGravarTipoCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setGravarTipoCorresp(" + user + ", " + listaTipoCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public UnidadeVO getUnidadeDisponivelVO(String user) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:getUnidadeDisponivelVO(" + user + ")");

			UnidadeVO unidadeVO = UnidadeVO.Factory.newInstance();
			xmlIN = XmlManager.MakeXmlIn(user, "StatCorrIncIni", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			unidadeVO = UnidadeVODocument.Factory.parse(xmlOUT).getUnidadeVO();

			return unidadeVO;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getUnidadeDisponivelVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getUnidadeDisponivelVO", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getUnidadeDisponivelVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ListaStatusCorrespVO getListaStatusCorrespVO(String user) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:getListaStatusCorrespVO(" + user + ")");
			xmlIN = XmlManager.MakeXmlIn(user, "StatCorrList", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return ListaStatusCorrespVODocument.Factory.parse(xmlOUT).getListaStatusCorrespVO();

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getListaStatusCorrespVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getListaStatusCorrespVO", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getListaStatusCorrespVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setAlterarStatusCorresp(String user, ListaStatusCorrespVO listaStatusCorrespVO) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setAlterarStatusCorresp(" + user + ", " + listaStatusCorrespVO + ")");
			xmlIN = XmlManager.MakeXmlIn(user, "StatCorrAlt", listaStatusCorrespVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setAlterarStatusCorresp(" + user + ", " + listaStatusCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setAlterarStatusCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setAlterarStatusCorresp(" + user + ", " + listaStatusCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String setGravarStatusCorresp(String user, ListaStatusCorrespVO listaStatusCorrespVO) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setGravarStatusCorresp(" + user + ", " + listaStatusCorrespVO + ")");
			xmlIN = XmlManager.MakeXmlIn(user, "StatCorrInc", listaStatusCorrespVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			if ("24E0003".equals(msgDoc.getMsg().getMsgHdr().getStatusCode())) {
				throw new Exception(msgDoc.getMsg().getMsgHdr().getStatusText());
			}

			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setGravarStatusCorresp(" + user + ", " + listaStatusCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setGravarStatusCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setGravarStatusCorresp(" + user + ", " + listaStatusCorrespVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String removeTipoCorresp(String user, String codigo) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:removeTipoCorresp(" + user + ", " + codigo + ")");

			xmlIN = "<id>" + codigo + "</id>";
			xmlIN = XmlManager.MakeXmlIn(user, "TipoCorrExc", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:removeTipoCorresp(" + user + ", " + codigo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:removeTipoCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:removeTipoCorresp(" + user + ", " + codigo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String removeMotivo(String user, String codigo) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:removeMotivo(" + user + ", " + codigo + ")");

			xmlIN = "<id>" + codigo + "</id>";
			xmlIN = XmlManager.MakeXmlIn(user, "MotDevExc", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:removeMotivo(" + user + ", " + codigo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:removeMotivo", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:removeMotivo(" + user + ", " + codigo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public String removeStatus(String user, String codigo) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:removeStatus(" + user + ", " + codigo + ")");

			xmlIN = "<id>" + codigo + "</id>";
			xmlIN = XmlManager.MakeXmlIn(user, "StatCorrExc", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:removeStatus(" + user + ", " + codigo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:removeStatus", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:removeStatus(" + user + ", " + codigo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setSalvarManterCorrespDevolvida(String user, ManterCorrespDevolvidaVO manterCorrespDevolvida) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("CorrespondenciaDevolvidaImpl:setSalvarManterCorrespDevolvida(" + user + ", " + manterCorrespDevolvida + ")");

			xmlIN = XmlManager.MakeXmlIn(user, "CorrDevInsere", manterCorrespDevolvida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setSalvarManterCorrespDevolvida(" + user + ", " + manterCorrespDevolvida + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setSalvarManterCorrespDevolvida", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setSalvarManterCorrespDevolvida(" + user + ", " + manterCorrespDevolvida + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setSalvarTratarCorresp(String status, String user, TratarCorrespDevolvidaVO tratar, String ti) throws TuxedoException, FacadeException {

		try {
			log.debug("CorrespondenciaDevolvidaImpl:setSalvarTratarCorresp(" + user + ", " + tratar + ")");

			if (ti == null) {
				xmlIN = XmlManager.MakeXmlIn(user, "CorrDevAltera", tratar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO) + "<idPessoaUsuario>" + user + "</idPessoaUsuario>");
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			}

			tratar.setEnderecoBaseVO(EnderecoBaseVO.Factory.newInstance());

			if (status.equalsIgnoreCase(ConstantesCRM.SOK)) {
				xmlIN = XmlManager.MakeXmlIn(user, "CORRDEVMANTST", tratar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO) + "<idPessoaUsuario>" + user + "</idPessoaUsuario>");
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			}

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:setSalvarTratarCorresp(" + user + ", " + tratar + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:setSalvarTratarCorresp", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:setSalvarTratarCorresp(" + user + ", " + tratar + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public CorrespDevolvidaTelaInicialVO getCorrespDevolvidaTI(String user, CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida filtroCorrespDevolvida) throws TuxedoException, FacadeException {

		xmlIN = ConstantesCRM.SVAZIO;
		CorrespDevolvidaTelaInicialVO correspDevolvidaTI = CorrespDevolvidaTelaInicialVODocument.CorrespDevolvidaTelaInicialVO.Factory.newInstance();
		try {
			if (filtroCorrespDevolvida.getIdStatus() != null) {
				xmlIN = filtroCorrespDevolvida.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "CorrDevTICons", xmlIN);
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			} else {
				xmlIN = "<NoSearch/>";
				xmlIN = XmlManager.MakeXmlIn(user, "CorrDevTIIni", xmlIN);
				// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			}
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			correspDevolvidaTI = CorrespDevolvidaTelaInicialVODocument.Factory.parse(xmlOUT).getCorrespDevolvidaTelaInicialVO();

		} catch (XmlException ex) {
			log.error("CorrespondenciaDevolvidaImpl:getCorrespDevolvidaTI(" + user + ", " + filtroCorrespDevolvida.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: CorrespondenciaDevolvidaImpl:getCorrespDevolvidaTI", ex));

		} catch (Exception ex) {
			log.error("CorrespondenciaDevolvidaImpl:getCorrespDevolvidaTI(" + user + ", " + filtroCorrespDevolvida.toString() + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return (correspDevolvidaTI);
	}
}