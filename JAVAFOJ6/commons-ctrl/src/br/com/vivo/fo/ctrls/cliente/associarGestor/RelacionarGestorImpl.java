package br.com.vivo.fo.ctrls.cliente.associarGestor;

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
import org.apache.xmlbeans.XmlOptions;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.cliente.vo.AssociarCRVODocument;
import br.com.vivo.fo.cliente.vo.AssociarCRVODocument.AssociarCRVO;
import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument;
import br.com.vivo.fo.cliente.vo.ConsultorRelacionamentoVODocument.ConsultorRelacionamentoVO;
import br.com.vivo.fo.cliente.vo.GestorContasVODocument;
import br.com.vivo.fo.cliente.vo.GestorContasVODocument.GestorContasVO;
import br.com.vivo.fo.cliente.vo.GrupoCRIVODocument;
import br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.associarGestor.db.ClienteEspecial;
import br.com.vivo.fo.ctrls.cliente.associarGestor.db.Gestor;
import br.com.vivo.fo.ctrls.cliente.associarGestor.db.GestorDB;
import br.com.vivo.fo.ctrls.cliente.associarGestor.db.PessoaConsultor;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RelacionarGestor", mappedName = "RelacionarGestor")
@Local(RelacionarGestor.class)
@Session(ejbName = "RelacionarGestor", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelacionarGestorImpl implements RelacionarGestor {

    @EJB
    private TuxedoServiceCall tuxedo;

    @EJB
    private GestorDB          gestorDB;

    private static Logger     log    = new Logger("clientes");

    private String            xmlIN  = ConstantesCRM.SVAZIO;
    private String            xmlOUT = ConstantesCRM.SVAZIO;

    public AssociarCRVO getListasAssociar(String user) throws TuxedoException, FacadeException {
        AssociarCRVODocument listasAssociarDoc = AssociarCRVODocument.Factory.newInstance();
        try {
            xmlIN = ConstantesCRM.SVAZIO;
            xmlIN = XmlManager.MakeXmlIn(user, "ConRelIni", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            listasAssociarDoc = AssociarCRVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

        } catch (XmlException ex) {
            log.error("XmlException - RelacionarGestorImpl:getListasAssociar(" + user + ") - [" + ex.getMessage() + "]");
            log.error("xmlOUT:= " + xmlOUT);
            throw (new FacadeException("Erro na montagem do XML de entrada: RelacionarGestorImpl:getListasAssociar", ex));

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:getListasAssociar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return listasAssociarDoc.getAssociarCRVO();
    }

    /**
     * @common:operation
     */
    public AssociarCRVO getClientesPesquisa(String user, AssociarCRVO filtroPesquisa) throws TuxedoException, FacadeException {
        AssociarCRVODocument listaClienteDoc = AssociarCRVODocument.Factory.newInstance();
        try {
            xmlIN = filtroPesquisa.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ConRelFiltro", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            listaClienteDoc = AssociarCRVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

        } catch (XmlException ex) {
            log.error("XmlException - RelacionarGestorImpl:getClientesPesquisa(" + user + ") - [" + ex.getMessage() + "]");
            log.error("xmlOUT:= " + xmlOUT);
            throw (new FacadeException("Erro na montagem do XML de entrada: RelacionarGestorImpl:getClientesPesquisa", ex));

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:getClientesPesquisa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return listaClienteDoc.getAssociarCRVO();
    }

    /**
     * @common:operation
     */
    public void setGravarAssociar(String user, AssociarCRVO salvarAssociar, String idConsultor) throws TuxedoException, FacadeException {
        try {
            xmlIN = salvarAssociar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            if (idConsultor != null) { // INSERÇÃO OU ALTERAÇÃO
                xmlIN = xmlIN + "<idConsultor>" + idConsultor + "</idConsultor>";
            }
            xmlIN = XmlManager.MakeXmlIn(user, "ConRelIns", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            tuxedo.callService("TuxConnector", xmlIN);

        } catch (XmlException ex) {
            log.error("XmlException - RelacionarGestorImpl:setGravarAssociar(" + user + ") - [" + ex.getMessage() + "]");
            log.error("xmlOUT:= " + xmlOUT);
            throw (new FacadeException("Erro na montagem do XML de entrada: RelacionarGestorImpl:setGravarAssociar", ex));

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:setGravarAssociar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public GrupoCRIVO getGruposCRI(String user) throws Exception {
        try {
            GrupoCRIVODocument grupo = GrupoCRIVODocument.Factory.newInstance();

            xmlIN = ConstantesCRM.SVAZIO;
            xmlIN = XmlManager.MakeXmlIn(user, "GETCOMBOGRUPO", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            grupo = GrupoCRIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

            return grupo.getGrupoCRIVO();

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:getGruposCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw (ex);
        }
    }

    /**
     * @common:operation
     */
    public GrupoCRIVO getPesquisaGruposCRI(String user, GrupoCRIVO filtro) throws Exception {
        try {
            GrupoCRIVO envio = GrupoCRIVO.Factory.newInstance();
            envio.setPesquisa(filtro.getPesquisa());

            xmlIN = envio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "GETPESQGRUPO", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return GrupoCRIVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getGrupoCRIVO();

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:getPesquisaGruposCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw (ex);
        }
    }

    /**
     * @common:operation
     */
    public void setGravarGruposCRI(String user, GrupoCRIVO salvar) throws Exception {
        try {
            xmlIN = salvar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "SETLINHAS", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:setGravarGruposCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("RelacionarGestorImpl:setGravarGruposCRI - [" + ex.getMessage() + "]", ex));
        }
    }

    /**
     * @common:operation
     */
    public ConsultorRelacionamentoVO getConsultorRelacionamentoVO(String user, ConsultorRelacionamentoVO filtros) throws Exception {

        try {
            ConsultorRelacionamentoVODocument resultado = ConsultorRelacionamentoVODocument.Factory.newInstance();

            xmlIN = filtros.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMPSQCONRELA", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            resultado = ConsultorRelacionamentoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText());

            return resultado.getConsultorRelacionamentoVO();

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:getConsultorRelacionamentoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (ex);
        }
    }

    /**
     * @common:operation
     */
    public ConsultorRelacionamentoVO setConsultorRelacionamentoVO(String user, ConsultorRelacionamentoVO consultorRelacionamento) throws Exception {

        try {
            xmlIN = consultorRelacionamento.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO).replaceAll(" xmlns:vo1=\"commons.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMGRVCONRELA", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            if (!ConstantesCRM.SVAZIO.equals(xmlOUT) && !"<xml-fragment/>".equals(xmlOUT)) {
                return ConsultorRelacionamentoVODocument.Factory.parse(xmlOUT).getConsultorRelacionamentoVO();
            }

            return null;

		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - RelacionarGestorImpl:setConsultorRelacionamentoVO(" + user + ") - [" + te.getMessage() + "]");
			throw te;	
        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:setConsultorRelacionamentoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException("RelacionarGestorImpl:setConsultorRelacionamentoVO - [" + ex.getMessage() + "]", ex);
        }
    }

    /**
     * @common:operation
     */
    public GestorContasVO getGestorContas(String user, GestorContasVO gestorContasVO) throws Exception {
        try {
            XmlOptions xmlOptions = new XmlOptions().setLoadStripWhitespace().setLoadStripProcinsts().setLoadStripComments();
            xmlIN = gestorContasVO.xmlText(xmlOptions).replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO).replaceAll(" xmlns:vo1=\"commons.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "GETGESTORCONT", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return GestorContasVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getGestorContasVO();

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:getGestorContas(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException("RelacionarGestorImpl:getGestorContas - [" + ex.getMessage() + "]", ex);
        }
    }

    /**
     * @common:operation
     */
    public void setGestorContas(String user, GestorContasVO gestorContasVO) throws Exception {
        try {
            XmlOptions xmlOptions = new XmlOptions().setLoadStripWhitespace().setLoadStripProcinsts().setLoadStripComments();
            xmlIN = gestorContasVO.xmlText(xmlOptions).replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO).replaceAll(" xmlns:vo1=\"commons.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "SETGESTORCONT", xmlIN);

            // (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE",xmlIN);
            tuxedo.callService("TuxConnector", xmlIN);

        } catch (Exception ex) {
            log.error("Exception - RelacionarGestorImpl:setGestorContas(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException("RelacionarGestorImpl:setGestorContas - [" + ex.getMessage() + "]", ex);
        }
    }

    /**
     * @common:operation
     */
    public PessoaConsultor[] getConsultorRelacionamento(String cdCnpjEmpresa) throws Exception {
        return gestorDB.getConsultorRelacionamento(cdCnpjEmpresa);
    }

    /**
     * @common:operation
     */
    public Gestor[] getGestoresConta(String cdCnpjEmpresa, String idconta) throws Exception {
        return gestorDB.getGestoresConta(cdCnpjEmpresa, idconta);
    }

    /**
     * @common:operation
     */
    public ClienteEspecial[] getClienteEspecial(String cdCnpjEmpresa) throws Exception {
        return gestorDB.getClienteEspecial(cdCnpjEmpresa);
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
