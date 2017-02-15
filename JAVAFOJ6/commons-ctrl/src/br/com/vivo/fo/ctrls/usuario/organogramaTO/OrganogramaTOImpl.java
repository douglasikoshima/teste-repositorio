package br.com.vivo.fo.ctrls.usuario.organogramaTO;

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
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument;
import br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="OrganogramaTO",mappedName="OrganogramaTO")
@Local(OrganogramaTO.class)
@Session(ejbName = "OrganogramaTO", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OrganogramaTOImpl implements OrganogramaTO {

	@EJB
	private TuxedoServiceCall tuxedo;

    static final long serialVersionUID = 1L;
    private String xmlIN;
    private String xmlOUT;

    private static Logger log = new Logger("usuario");

    /**
     * @common:operation
     */
    public ListaTipoOrganizacaoVO getListaTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacaoVO, String user) throws TuxedoException, FacadeException {

        try {

            log.debug("OrganogramaTOImpl:getListaTipoOrganizacao(" + user + ")" );

            xmlIN = tipoOrganizacaoVO.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "TOLISTAPAR", xmlIN);

            //xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
            listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

            return listaTipoOrganizacaoVO;

        } catch(XmlException ex) {

            log.error("XmlException - OrganogramaTOImpl:getListaTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: ", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - OrganogramaTOImpl:getListaTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {

            log.error("Exception - OrganogramaTOImpl:getListaTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));

        }

    }


    /**
     * @common:operation
     */
    public ListaTipoOrganizacaoVO getListaTipoOrganizacaos(String user) throws TuxedoException, FacadeException {

        try {

            log.debug("OrganogramaTOImpl:getListaTipoOrganizacaos(" + user + ")" );

            TipoOrganizacaoVO tipoOrganizacaoVO = TipoOrganizacaoVO.Factory.newInstance();
            tipoOrganizacaoVO.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
            tipoOrganizacaoVO.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
            xmlIN = tipoOrganizacaoVO.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "TOLISTAPAR", xmlIN);

            //xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
            listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

            return listaTipoOrganizacaoVO;

        } catch(XmlException ex) {

            log.error("XmlException - OrganogramaTOImpl:getListaTipoOrganizacaos(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: OrganogramaTOImpl:getListaTipoOrganizacaos", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - OrganogramaTOImpl:getListaTipoOrganizacaos(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {

            log.error("Exception - OrganogramaTOImpl:getListaTipoOrganizacaos(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));

        }

    }


    /**
     * @common:operation
     */
    public ListaTipoOrganizacaoVO getListaTipoOrganizacaoTodas(String user) throws TuxedoException, FacadeException {

        try {

            log.debug("OrganogramaTOImpl:getListaTipoOrganizacaoTodas(" + user + ")" );

            TipoOrganizacaoVO tipoOrganizacaoVO  = TipoOrganizacaoVO.Factory.newInstance();
            tipoOrganizacaoVO.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
            tipoOrganizacaoVO.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);

            xmlIN = tipoOrganizacaoVO.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "TOLISTAPAR", xmlIN);

            //xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
            listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

            return listaTipoOrganizacaoVO;

        } catch(XmlException ex) {

            log.error("XmlException - OrganogramaTOImpl:getListaTipoOrganizacaoTodas(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: OrganogramaTOImpl:getListaTipoOrganizacaoTodas", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("OrganogramaTOImpl:getListaTipoOrganizacaoTodas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {

            log.error("Exception - OrganogramaTOImpl:getListaTipoOrganizacaoTodas(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));

        }

    }


    /**
     * @common:operation
     */
    public ListaTipoOrganizacaoVO insertTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String operacion, String user) throws TuxedoException, FacadeException {

        try {

            log.debug("OrganogramaTOImpl:insertTipoOrganizacao(" + user + ")" );

            xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "TOINSERIR", xmlIN);

            //xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
            listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

            return listaTipoOrganizacaoVO;

        } catch(XmlException ex) {

            log.error("XmlException - OrganogramaTOImpl:insertTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: OrganogramaTOImpl:insertTipoOrganizacao", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - OrganogramaTOImpl:insertTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {

            log.error("Exception - OrganogramaTOImpl:insertTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));

        }

    }


/**
     * @common:operation
     */
    public ListaTipoOrganizacaoVO editarTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String user) throws TuxedoException, FacadeException {

        try {

            log.debug("OrganogramaTOImpl:editarTipoOrganizacao(" + user + ")" );

            xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "TOEDITAR", xmlIN);

            //xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
            listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

            return listaTipoOrganizacaoVO;

        } catch(XmlException ex) {

            log.error("XmlException - OrganogramaTOImpl:editarTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: OrganogramaTOImpl:editarTipoOrganizacao", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - OrganogramaTOImpl:editarTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {

            log.error("Exception - OrganogramaTOImpl:editarTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));

        }

    }


    /**
     * @common:operation
     */
    public ListaTipoOrganizacaoVO removeTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String user) throws TuxedoException, FacadeException {

        try {

            log.debug("OrganogramaTOImpl:removeTipoOrganizacao(" + user + ")" );

            xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "TOREMOVE", xmlIN);

            //xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
            listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

            return listaTipoOrganizacaoVO;

        } catch(XmlException ex) {

            log.error("XmlException - OrganogramaTOImpl:removeTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: OrganogramaTOImpl:removeTipoOrganizacao", ex));

        } catch (TuxedoServiceCallerException ex) {

            log.error("TuxedoException - OrganogramaTOImpl:removeTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {

            log.error("Exception - OrganogramaTOImpl:removeTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));

        }

    }


}