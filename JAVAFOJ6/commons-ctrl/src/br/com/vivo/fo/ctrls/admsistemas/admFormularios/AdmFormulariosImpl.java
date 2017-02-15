package br.com.vivo.fo.ctrls.admsistemas.admFormularios;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument;
import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "AdmFormularios", mappedName = "AdmFormularios")
@Local(AdmFormularios.class)
@Session(ejbName = "AdmFormularios", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AdmFormulariosImpl implements AdmFormularios {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("admsistemas");

	/**
	 * @common:operation
	 */
	public ClienteFormularioVO getClienteFormularioVO(String user, ClienteFormularioVO filtros) throws Exception {

	    String xmlIN = ConstantesCRM.SVAZIO;
	    String xmlOUT = ConstantesCRM.SVAZIO;

		try {
			ClienteFormularioVODocument resultado = ClienteFormularioVODocument.Factory.newInstance();

			xmlIN = XmlManager.MakeXmlIn(user, "ADMPSQCONRELA", filtros.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
			log.debug("[AdmFormulariosImpl.getClienteFormularioVO] XMLIN = " + xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			log.debug("[AdmFormulariosImpl.getClienteFormularioVO] xmlOUT = " + xmlOUT);

			resultado = ClienteFormularioVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

			return resultado.getClienteFormularioVO();

		} catch (Exception ex) {
			log.error("Exception - AdmFormulariosImpl:getClienteFormularioVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (ex);
		}
	}

	/**
	 * @common:operation
	 */
	public ClienteFormularioVO setClienteFormularioVO(String user, ClienteFormularioVO clienteFormularioVO) throws Exception {

        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;

		try {
			xmlIN = clienteFormularioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO).replaceAll(" xmlns:vo1=\"commons.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ADMGRVCONRELA", xmlIN);
			log.debug("[AdmFormulariosImpl.setClienteFormularioVO] XMLIN = " + xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			log.error("[RelacionarGestorImpl:setClienteFormularioVO] xmlOUT [" + xmlOUT + "]");

			if (xmlOUT != null && (!ConstantesCRM.SVAZIO.equals(xmlOUT)&& !"<xml-fragment/>".equals(xmlOUT))) {
				return ClienteFormularioVODocument.Factory.parse(xmlOUT).getClienteFormularioVO();
			}

			return null;

		} catch (Exception ex) {
			log.error("Exception - AdmFormulariosImpl:setClienteFormularioVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException("AdmFormulariosImpl:setClienteFormularioVO - [" + ex.getMessage() + "]", ex);
		}
	}

	@SuppressWarnings("unused")
	private String testeXML() {

		StringBuffer sb = new StringBuffer();

		sb.append("<tns:ClienteFormularioVO xsi:schemaLocation=\"admsistemas.fo.vivo.com.br/vo\" xmlns:tns=\"admsistemas.fo.vivo.com.br/vo\" xmlns:ns1=\"commons.fo.vivo.com.br/vo\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
		sb.append("	<tns:tpOperacao>pesquisarClienteVO</tns:tpOperacao>");
		sb.append("	<tns:inAssociado>1</tns:inAssociado>");
		sb.append("	<tns:msgRetorno>String</tns:msgRetorno>");
		sb.append("	<tns:ClienteVO>");
		sb.append("		<tns:nrCNPJ>47096136000100</tns:nrCNPJ>");
		sb.append("		<tns:nmRazaoSocial>Vivo SA</tns:nmRazaoSocial>");
		sb.append("	</tns:ClienteVO>");
		sb.append("	<tns:Lista>");
		sb.append("		<tns:nmSelect>ClienteVO</tns:nmSelect>");
		sb.append("		<tns:Disponivel>");
		sb.append("			<ns1:IDValorClienteVO>");
		sb.append("				<ns1:id>123456789</ns1:id>");
		sb.append("				<ns1:valor>Vivo SA</ns1:valor>");
		sb.append("				<ns1:nrDocumentoFmt>10.000.000/0001.03</ns1:nrDocumentoFmt>");
		sb.append("			</ns1:IDValorClienteVO>");
		sb.append("			<ns1:IDValorClienteVO>");
		sb.append("				<ns1:id>987654231</ns1:id>");
		sb.append("				<ns1:valor>Telesp Celular SA</ns1:valor>");
		sb.append("				<ns1:nrDocumentoFmt>10.000.000/0001.03</ns1:nrDocumentoFmt>");
		sb.append("			</ns1:IDValorClienteVO>");
		sb.append("		</tns:Disponivel>");
		sb.append("		<tns:Selecionado>");
		sb.append("			<ns1:IDValorClienteVO>");
		sb.append("				<ns1:id>123145654</ns1:id>");
		sb.append("				<ns1:valor>Claro Celular</ns1:valor>");
		sb.append("				<ns1:nrDocumentoFmt>10.000.000/0001.03</ns1:nrDocumentoFmt>");
		sb.append("			</ns1:IDValorClienteVO>");
		sb.append("			<ns1:IDValorClienteVO>");
		sb.append("				<ns1:id>741852</ns1:id>");
		sb.append("				<ns1:valor>Tim Celular</ns1:valor>");
		sb.append("				<ns1:nrDocumentoFmt>10.000.000/0001.03</ns1:nrDocumentoFmt>");
		sb.append("			</ns1:IDValorClienteVO>");
		sb.append("		</tns:Selecionado>");
		sb.append("	</tns:Lista>");
		sb.append("</tns:ClienteFormularioVO>");

		return sb.toString();

	}
}
