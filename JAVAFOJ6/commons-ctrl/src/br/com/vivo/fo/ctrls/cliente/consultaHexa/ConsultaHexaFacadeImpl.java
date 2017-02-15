package br.com.vivo.fo.ctrls.cliente.consultaHexa;

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
import br.com.vivo.fo.cliente.vo.ConsultaHexaVODocument;
import br.com.vivo.fo.cliente.vo.ConsultaHexaVODocument.ConsultaHexaVO;
import br.com.vivo.fo.cliente.vo.MonitorarHexaVODocument;
import br.com.vivo.fo.cliente.vo.MonitorarHexaVODocument.MonitorarHexaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ConsultaHexaFacade", mappedName = "ConsultaHexaFacade")
@Local(ConsultaHexaFacade.class)
@Session(ejbName = "ConsultaHexaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConsultaHexaFacadeImpl implements ConsultaHexaFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("clientes");

	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public MonitorarHexaVO getMonitoramentoHexa(MonitorarHexaVO hexaVO, String user) throws TuxedoException, FacadeException {

		MonitorarHexaVO monitorarHexa = MonitorarHexaVO.Factory.newInstance();
		try {
			xmlIN = hexaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "HexaConsLog", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			monitorarHexa = MonitorarHexaVODocument.Factory.parse(xmlOUT).getMonitorarHexaVO();

		} catch (XmlException ex) {
			log.error("XmlException - ConsultaHexaFacadeImpl:getMonitoramentoHexa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ConsultaHexaFacadeImpl:getMonitoramentoHexa", ex));

		} catch (Exception ex) {
			log.error("Exception - ConsultaHexaFacadeImpl:getMonitoramentoHexa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return monitorarHexa;
	}

	/**
	 * @common:operation
	 */
	public ConsultaHexaVO getConsultaHexa(ConsultaHexaVO hexaVO, String user) throws TuxedoException, FacadeException {

		ConsultaHexaVO consultaHexa = ConsultaHexaVO.Factory.newInstance();
		try {
			xmlIN = hexaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "HexaChecaIni", xmlIN);

			// xmlOUT = jatmi.executeCommnad("TuxConnector",xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			// xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);

			if(String.valueOf(xmlOUT).length()>0) {
	            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();
	            consultaHexa = ConsultaHexaVODocument.Factory.parse(xmlOUT).getConsultaHexaVO();
			}else {
			    consultaHexa.addNewRecebimento().setErro("O Serviço Tuxedo não retornou mensagem.");
			}

		} catch (XmlException ex) {
			log.error("XmlException - ConsultaHexaFacadeImpl:getConsultaHexa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ConsultaHexaFacadeImpl:getConsultaHexa", ex));

		} catch (Exception ex) {
			log.error("Exception - ConsultaHexaFacadeImpl:getConsultaHexa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return consultaHexa;
	}

}
