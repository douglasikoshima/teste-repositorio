package br.com.vivo.fo.ctrls.admsistemas.variaveisArvoreContato;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import noNamespace.MsgHeaderVO;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO;
import br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument;
import br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "VariaveisArvoreContato", mappedName = "VariaveisArvoreContato")
@Local(VariaveisArvoreContato.class)
@Session(ejbName = "VariaveisArvoreContato", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class VariaveisArvoreContatoImpl implements VariaveisArvoreContato {

	@EJB
	private TuxedoServiceCall tuxedo;

	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	private static Logger log = new Logger("admsistemas");

	/**
	 * @common:operation
	 */
	public void exportarVariaveisArvoreContato(VariaveisArvoreContatoVO variaveisArvoreContatoVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ArvoreSatisfacaoImpl:getListaQuestionarios(" + user + ")");

			xmlIN = variaveisArvoreContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN.replaceAll(" xmlns:vo=\"admsistemas.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "FILTROARVCNTO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

		} catch (XmlException ex) {
			log.error("XmlException - VariaveisArvoreContatoImpl:exportarVariaveisArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: VariaveisArvoreContatoImpl:exportarVariaveisArvoreContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - VariaveisArvoreContatoImpl:exportarVariaveisArvoreContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;				
		} catch (Exception ex) {
			log.error("Exception - VariaveisArvoreContatoImpl:exportarVariaveisArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public VariaveisArvoreContatoVO getFiltrosSelecionados(VariaveisArvoreContatoVO variaveisArvoreContatoVO, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = variaveisArvoreContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN.replaceAll(" xmlns:vo=\"admsistemas.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "FILTROARVCNTO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			VariaveisArvoreContatoVODocument msg = VariaveisArvoreContatoVODocument.Factory.parse(xmlOUT);

			return msg.getVariaveisArvoreContatoVO();

		} catch (XmlException ex) {
			log.error("XmlException - VariaveisArvoreContatoImpl:getFiltrosSelecionados(" + user + ", String descricao) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreSatisfacaoImpl:getFiltrosSelecionados", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - VariaveisArvoreContatoImpl:getFiltrosSelecionados(" + user + ") - [" + te.getMessage() + "]");
			throw te;				
		} catch (Exception ex) {
			log.error("Exception - VariaveisArvoreContatoImpl:getFiltrosSelecionados(" + user + ", String descricao) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmVarAContatoArqGeradosVO getListaArquivosGerados(VariaveisArvoreContatoVO variaveisArvoreContatoVO, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = variaveisArvoreContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN.replaceAll(" xmlns:vo=\"admsistemas.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "FILTROARVCNTO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
            MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

			AdmVarAContatoArqGeradosVODocument msg = AdmVarAContatoArqGeradosVODocument.Factory.parse(xmlOUT);

			return msg.getAdmVarAContatoArqGeradosVO();

		} catch (XmlException ex) {
			log.error("XmlException - VariaveisArvoreContatoImpl:getListaArquivosGerados(" + user + ", String descricao) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreSatisfacaoImpl:getListaArquivosGerados", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - VariaveisArvoreContatoImpl:getListaArquivosGerados(" + user + ") - [" + te.getMessage() + "]");
			throw te;				
		} catch (Exception ex) {
			log.error("Exception - VariaveisArvoreContatoImpl:getListaArquivosGerados(" + user + ", String descricao) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
	
	 private void tratarWarningException(MsgHeaderVO msgHeaderVO) throws TuxedoWarningException {
			
			if(msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("W")>-1) {
				XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(),msgHeaderVO.getUser(), "fo", 'W', msgHeaderVO.getStatusCode().substring(0,4), msgHeaderVO.getStatusText());
				throw new TuxedoWarningException(xmlHeader);
			} else if(msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("E")>-1) {
				XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(),msgHeaderVO.getUser(), "fo", 'E', msgHeaderVO.getStatusCode().substring(0,4), msgHeaderVO.getStatusText());
				throw new TuxedoWarningException(xmlHeader);
			}

		}
}