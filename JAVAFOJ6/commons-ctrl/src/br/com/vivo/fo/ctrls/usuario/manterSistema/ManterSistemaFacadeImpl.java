package br.com.vivo.fo.ctrls.usuario.manterSistema;

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
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.commons.utils.geral.ControlXMLExceptionLookup;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FOException;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.GrupamentosExistentesUsuarioVODocument.GrupamentosExistentesUsuarioVO;
import br.com.vivo.fo.usuario.vo.ItemMenuEditarVODocument.ItemMenuEditarVO;
import br.com.vivo.fo.usuario.vo.ItemMenuInserirVODocument.ItemMenuInserirVO;
import br.com.vivo.fo.usuario.vo.ItemMenuMoverVODocument.ItemMenuMoverVO;
import br.com.vivo.fo.usuario.vo.ItemMenuRemoverVODocument.ItemMenuRemoverVO;
import br.com.vivo.fo.usuario.vo.ItemMenuVODocument;
import br.com.vivo.fo.usuario.vo.ItemMenuVODocument.ItemMenuVO;
import br.com.vivo.fo.usuario.vo.ListaGrupamentosUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.ListaGrupamentosUsuarioVODocument.ListaGrupamentosUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaItensMenuVODocument;
import br.com.vivo.fo.usuario.vo.ListaItensMenuVODocument.ListaItensMenuVO;
import br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.ListaPaginasUsuarioVODocument.ListaPaginasUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaParamGrupamUnidVODocument;
import br.com.vivo.fo.usuario.vo.ListaParamGrupamUnidVODocument.ListaParamGrupamUnidVO;
import br.com.vivo.fo.usuario.vo.ListaServidoresVODocument;
import br.com.vivo.fo.usuario.vo.ListaServidoresVODocument.ListaServidoresVO;
import br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.ListaSubSistemasPaginasVODocument;
import br.com.vivo.fo.usuario.vo.ListaSubSistemasPaginasVODocument.ListaSubSistemasPaginasVO;
import br.com.vivo.fo.usuario.vo.ListaUnidadesGrupamentoVODocument;
import br.com.vivo.fo.usuario.vo.ListaUnidadesGrupamentoVODocument.ListaUnidadesGrupamentoVO;
import br.com.vivo.fo.usuario.vo.ListaUnidadesPesquisaVODocument;
import br.com.vivo.fo.usuario.vo.ListaUnidadesPesquisaVODocument.ListaUnidadesPesquisaVO;
import br.com.vivo.fo.usuario.vo.MenuParamPesquisaItensVODocument.MenuParamPesquisaItensVO;
import br.com.vivo.fo.usuario.vo.MenuVODocument;
import br.com.vivo.fo.usuario.vo.MenuVODocument.MenuVO;
import br.com.vivo.fo.usuario.vo.PaginaUsuarioVODocument.PaginaUsuarioVO;
import br.com.vivo.fo.usuario.vo.RelacionarSistemaServidorVODocument;
import br.com.vivo.fo.usuario.vo.RelacionarSistemaServidorVODocument.RelacionarSistemaServidorVO;
import br.com.vivo.fo.usuario.vo.SalvaUnidadesGrupamentoVODocument.SalvaUnidadesGrupamentoVO;
import br.com.vivo.fo.usuario.vo.SalvarItensMenuRelacionadosVODocument.SalvarItensMenuRelacionadosVO;
import br.com.vivo.fo.usuario.vo.SistemaIDArvoreVODocument.SistemaIDArvoreVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO;
import br.com.vivo.fo.usuario.vo.SistemaServidorExistenteUsuarioVODocument.SistemaServidorExistenteUsuarioVO;
import br.com.vivo.fo.usuario.vo.SistemaUsuarioVODocument.SistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument;
import br.com.vivo.fo.usuario.vo.SubSistemaPaginasVODocument.SubSistemaPaginasVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;
import br.com.vivo.fo.usuario.vo.UnidadeSalvaVODocument.UnidadeSalvaVO;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ManterSistemaFacade", mappedName = "ManterSistemaFacade")
@Local(ManterSistemaFacade.class)
@Session(ejbName = "ManterSistemaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterSistemaFacadeImpl implements ManterSistemaFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("usuario");

    /**
     * @common:operation
     */
    public ListaSistemaUsuarioVO adicionaSistema(SistemaManterUsuarioVO sistemaAdicionar, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaAdicionar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisInserir", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaSistemaUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaSistemaUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:adicionaSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:adicionaSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:adicionaSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:adicionaSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:adicionaSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaSistemaUsuarioVO listaSistemas(SistemaManterUsuarioVO sistemaManterUsuarioVO, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaManterUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisListaPar", xmlIN));

            String[] retTux = XmlManager.ParseXmlOut(xmlOUT);
            RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaSistemaUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaSistemaUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaSistemas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaSistemas", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaSistemas(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoException te) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaSistemas(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaSistemas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaSistemas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaSistemaUsuarioVO pesquisaSistema(SistemaManterUsuarioVO sistemaPesquisar, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaPesquisar.xmlText();
            try {
                xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisListaId", xmlIN));

            } catch (Exception ex) {
                xmlOUT = ControlXMLExceptionLookup.getXMLString(ex);
            }

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaSistemaUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaSistemaUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:pesquisaSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:pesquisaSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:pesquisaSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:pesquisaSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaSistemaUsuarioVO salvaSistemaEditado(SistemaManterUsuarioVO sistemaEditado, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaEditado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisEditar", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaSistemaUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaSistemaUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaSistemaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaSistemaEditado", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvaSistemaEditado(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaSistemaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaSistemaEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaSistemaUsuarioVO removeSistema(SistemaIDVO sistemaRemove, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaRemove.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisRemover", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaSistemaUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaSistemaUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removeSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removeSistema", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeSistema(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removeSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaSubSistemasPaginasVO listaSubSistemasPaginas(SistemaIDVO idSistema, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = idSistema.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisListaId", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaSubSistemasPaginasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaSubSistemasPaginasVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaSubSistemasPaginas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaSubSistemasPaginas", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaSubSistemasPaginas(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaSubSistemasPaginas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaSubSistemasPaginas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUnidadesPesquisaVO pesquisaUnidades(UnidadeSalvaVO unidadePesquisa, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:pesquisaUnidades(" + user + ")");
            xmlIN = unidadePesquisa.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "UniListaId", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaUnidadesPesquisaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaUnidadesPesquisaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:pesquisaUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:pesquisaUnidades", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:pesquisaUnidades(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:pesquisaUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:pesquisaUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUnidadesPesquisaVO removeUnidade(UnidadeSalvaVO unidadeRemover, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:removeUnidade(" + user + ")");
            xmlIN = unidadeRemover.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "UniRemover", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaUnidadesPesquisaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaUnidadesPesquisaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removeUnidade(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removeUnidade", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:removeUnidade(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeUnidade(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removeUnidade(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUnidadesPesquisaVO salvaUnidade(UnidadeSalvaVO salvaUnidade, String tipo, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:salvaUnidade(" + user + ")");
            xmlIN = salvaUnidade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (tipo.equals("I")) {
                xmlIN = XmlManager.MakeXmlIn(user, "UniInserir", xmlIN);
            } else {
                xmlIN = XmlManager.MakeXmlIn(user, "UniEditar", xmlIN);
            }

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaUnidadesPesquisaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaUnidadesPesquisaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaUnidade(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaUnidade", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvaUnidade(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaUnidade(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaUnidade(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaGrupamentosUsuarioVO listaTodosGrupamentos(GrupamentosExistentesUsuarioVO grupamentosExistentesUsuarioVO, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:listaTodosGrupamentos(" + user + ")");
            xmlIN = grupamentosExistentesUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GptLista", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaGrupamentosUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaGrupamentosUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaTodosGrupamentos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaTodosGrupamentos", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaTodosGrupamentos(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaTodosGrupamentos(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaTodosGrupamentos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaGrupamentosUsuarioVO removeGrupamento(GrupamentosExistentesUsuarioVO grupamentoRemove, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:removeGrupamento(" + user + ")");
            xmlIN = grupamentoRemove.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GptRemover", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaGrupamentosUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaGrupamentosUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removeGrupamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removeGrupamento", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeGrupamento(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeGrupamento(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removeGrupamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaGrupamentosUsuarioVO salvaGrupamento(GrupamentosExistentesUsuarioVO grupamentoSalvo, String tipo, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:salvaGrupamento(" + user + ")");

            String servico = ConstantesCRM.SVAZIO;
            xmlIN = grupamentoSalvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (tipo.equals("novo")) {
                servico = "GptInserir";
            } else if (tipo.equals("edicao")) {
                servico = "GptEditar";
            } else {
                log.error("ManterSistemaFacadeImpl:salvaGrupamento(" + user + ") - [Parametro tipo inválido]");
                throw new FOException("ManterSistemaFacadeImpl:salvaGrupamento - Parametro tipo inválido.");
            }

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, servico, xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaGrupamentosUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaGrupamentosUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaGrupamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaGrupamento", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaGrupamento(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaGrupamento(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaGrupamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaServidoresVO salvaServidor(SistemaServidorExistenteUsuarioVO salvaServidor, String tipo, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            String servico = ConstantesCRM.SVAZIO;
            log.debug("ManterSistemaFacadeImpl:salvaServidor(" + user + ")");

            xmlIN = salvaServidor.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (tipo.equals("novo")) {
                servico = "SrvInserir";
            } else if (tipo.equals("edicao")) {
                servico = "SrvEditar";
            } else {
                log.error("ManterSistemaFacadeImpl:salvaServidor(" + user + ") - [Parametro tipo inválido]");
                throw new FOException("ManterSistemaFacadeImpl:salvaServidor - Parametro tipo inválido.");
            }

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, servico, xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaServidoresVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaServidoresVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaServidor(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaServidor", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvaServidor(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaServidor(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaServidor(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaServidoresVO listaServidores(SistemaServidorExistenteUsuarioVO sistemaServidorExistenteUsuarioVO, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:listaServidores(" + user + ")");
            xmlIN = sistemaServidorExistenteUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SrvLista", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaServidoresVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaServidoresVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaServidores(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaServidores", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaServidores(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaServidores(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaServidores(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaServidoresVO removeServidor(SistemaServidorExistenteUsuarioVO servidorRemov, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:removeServidor(" + user + ")");
            xmlIN = servidorRemov.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SrvRemover", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaServidoresVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaServidoresVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removeServidor(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removeServidor", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:removeServidor(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeServidor(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removeServidor(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RelacionarSistemaServidorVO listaServidoresRelacionadosSistema(SistemaIDVO sistemaIdListar, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:listaServidoresRelacionadosSistema(" + user + ")");
            xmlIN = sistemaIdListar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SisSrvRelacao", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return RelacionarSistemaServidorVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRelacionarSistemaServidorVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaServidoresRelacionadosSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaServidoresRelacionadosSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaServidoresRelacionadosSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaServidoresRelacionadosSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaServidoresRelacionadosSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RelacionarSistemaServidorVO salvarServidoresRelacionadosSistema(RelacionarSistemaServidorVO servidoresRelacionados, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:salvarServidoresRelacionadosSistema(" + user + ")");
            xmlIN = servidoresRelacionados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SISSRVRELACIO", xmlIN));
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return RelacionarSistemaServidorVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRelacionarSistemaServidorVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvarServidoresRelacionadosSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvarServidoresRelacionadosSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvarServidoresRelacionadosSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvarServidoresRelacionadosSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvarServidoresRelacionadosSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void moverItemMenu(ItemMenuMoverVO itemMover, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:moverItemMenu(" + user + ")");
            xmlIN = itemMover.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ImnMover", xmlIN));

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:moverItemMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:moverItemMenu", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:moverItemMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:moverItemMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void inserirItemMenu(ItemMenuInserirVO itemInserir, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:inserirItemMenu(" + user + ")");
            xmlIN = itemInserir.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ImnInserir", xmlIN));

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:inserirItemMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:inserirItemMenu", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:inserirItemMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:inserirItemMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public MenuVO carregaMenu(String user) throws TuxedoException, FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:carregaMenu(" + user + ")");
            MenuVO menuVO = null;

            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ImnListaPar", ConstantesCRM.SVAZIO));

            menuVO = MenuVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getMenuVO();

            return menuVO;

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:carregaMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:carregaMenu", ex));

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:carregaMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public ItemMenuVO carregaArvoreMenu(SistemaIDArvoreVO idSistemaVO, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:carregaArvoreMenu(" + user + ")");
            xmlIN = idSistemaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ImnListaId", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ItemMenuVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getItemMenuVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:carregaArvoreMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:carregaArvoreMenu", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:carregaArvoreMenu(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:carregaArvoreMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:carregaArvoreMenu(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void removeItemArvore(ItemMenuRemoverVO itemRemover, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:removeItemArvore(" + user + ")");
            xmlIN = itemRemover.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ImnRemover", xmlIN));

            tratarWarningException(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgHdr());

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removeItemArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removeItemArvore", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:removeItemArvore(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeItemArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removeItemArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvarItemEditado(ItemMenuEditarVO itemEditado, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:salvarItemEditado(" + user + ")");
            xmlIN = itemEditado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ImnEditar", xmlIN));

            tratarWarningException(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgHdr());

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvarItemEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvarItemEditado", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvarItemEditado(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvarItemEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvarItemEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaItensMenuVO listaItensMenu(MenuParamPesquisaItensVO param, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:listaItensMenu(" + user + ")");
            xmlIN = param.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (param.getIdGrupo().equals("0")) {
                xmlIN = XmlManager.MakeXmlIn(user, "UsrImnRelacao", xmlIN);

            } else if (param.getIdUsuario().equals("0")) {
                xmlIN = XmlManager.MakeXmlIn(user, "GrpImnRelacao", xmlIN);
            }
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaItensMenuVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaItensMenuVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaItensMenu(" + user + ") - tipo:" + " - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaItensMenu", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaItensMenu(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaItensMenu(" + user + ") - tipo:" + " - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaItensMenu(" + user + ") - tipo:" + " - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaItensMenuVO salvaItensRelacionados(SalvarItensMenuRelacionadosVO param, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:salvaItensRelacionados(" + user + ")");
            xmlIN = param.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (param.getIdGrupo().equals("0")) {
                xmlIN = XmlManager.MakeXmlIn(user, "USRIMNRELACIO", xmlIN);

            } else if (param.getIdUsuario().equals("0")) {
                xmlIN = XmlManager.MakeXmlIn(user, "GRPIMNRELACIO", xmlIN);
            }
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaItensMenuVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaItensMenuVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaItensRelacionados(" + user + ") - tipo:" + " - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaItensRelacionados", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvaItensRelacionados(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaItensRelacionados(" + user + ") - tipo:" + " - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaItensRelacionados(" + user + ") - tipo:" + " - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaPaginasUsuarioVO salvaPagina(PaginaUsuarioVO paginaSalvar, String tipo, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            String servico = ConstantesCRM.SVAZIO;
            xmlIN = paginaSalvar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (tipo.equals("novo")) {
                servico = "PgnInserir";
            } else if (tipo.equals("edicao")) {
                servico = "PgnEditar";
            } else {
                log.error("ManterSistemaFacadeImpl:salvaPagina(" + user + ") - [Parametro tipo inválido]");
                throw new FOException("ManterSistemaFacadeImpl:salvaPagina - Parametro tipo inválido.");
            }

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, servico, xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaPaginasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaPaginasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaPagina(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaPagina", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvaPagina(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaPagina(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaPagina(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaPaginasUsuarioVO removePagina(PaginaUsuarioVO paginaRemover, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = paginaRemover.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PgnRemover", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaPaginasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaPaginasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removePagina(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removePagina", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:removePagina(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removePagina(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removePagina(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaPaginasUsuarioVO pesquisaPaginas(ListaPaginasUsuarioVO idSistSubSist, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            log.debug("ManterSistemaFacadeImpl:pesquisaPaginas(" + user + ")");
            xmlIN = idSistSubSist.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PgnListaPar", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListaPaginasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaPaginasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:pesquisaPaginas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:pesquisaPaginas", ex));

        } catch (TuxedoServiceCallerException ex) {
            String erro = ex.getCause().getMessage();
            // Não foi encontrada nenhuma pagina
            if (erro.startsWith("0000")) {
                ListaPaginasUsuarioVO listaPaginasUsuarioVO = ListaPaginasUsuarioVO.Factory.newInstance();
                return listaPaginasUsuarioVO;
            } else {
                log.error("TuxedoException - ManterSistemaFacadeImpl:pesquisaPaginas(" + user + ") - [" + ex.getMessage() + "]");
                throw new TuxedoException(ex);
            }

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:pesquisaPaginas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubSistemasUsuarioVO listaSubSistemas(SistemaUsuarioVO sistemaPerfil, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaPerfil.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SubLista", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return SubSistemasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSubSistemasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaSubSistemas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaSubSistemas", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaSubSistemas(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaSubSistemas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaSubSistemas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubSistemasUsuarioVO listaSubSistemasPorSistema(SistemaIDVO sistemaIdListar, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = sistemaIdListar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SubListaIdSis", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return SubSistemasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSubSistemasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaSubSistemasPorSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaSubSistemasPorSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaSubSistemasPorSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaSubSistemasPorSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaSubSistemasPorSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubSistemasUsuarioVO listaSubSistemasPorSistemaPorSubSistemaPar(SubSistemaUsuarioVO subSistemaUsuarioVO, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = subSistemaUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SubListaIdSis", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return SubSistemasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSubSistemasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaSubSistemasPorSistemaPorSubSistemaPar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaSubSistemasPorSistemaPorSubSistemaPar", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaSubSistemasPorSistemaPorSubSistemaPar(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaSubSistemasPorSistemaPorSubSistemaPar(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaSubSistemasPorSistemaPorSubSistemaPar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubSistemasUsuarioVO salvaSubSistema(SubSistemasUsuarioVO subSistemaSalvar, String tipo, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            String servico = ConstantesCRM.SVAZIO;
            xmlIN = subSistemaSalvar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            if (tipo.equals("novo")) {
                servico = "SubInserir";

            } else if (tipo.equals("edicao")) {
                servico = "SubEditar";

            } else {
                log.error("ManterSistemaFacadeImpl:salvaSubSistema(" + user + ") - [ Parametro tipo inexistente ou inválido ]");
                throw (new FOException("ManterSistemaFacadeImpl:salvaSubSistema(" + user + ") - [ Parametro tipo inexistente ou inválido ]"));
            }

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, servico, xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return SubSistemasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSubSistemasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaSubSistema(" + user + ") - tipo:" + tipo + " - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaSubSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaSubSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaSubSistema(" + user + ") - tipo:" + tipo + " - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaSubSistema(" + user + ") - tipo:" + tipo + " - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubSistemasUsuarioVO removeSubSistema(SubSistemasUsuarioVO subSistemaRemove, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = subSistemaRemove.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SubRemover", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return SubSistemasUsuarioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSubSistemasUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:removeSubSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:removeSubSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:removeSubSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:removeSubSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:removeSubSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaParamGrupamUnidVO listaParamRelGrupamUnidades(String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "GptSisLista", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaParamGrupamUnidVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaParamGrupamUnidVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaParamRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaParamRelGrupamUnidades", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaParamRelGrupamUnidades(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaParamRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaParamRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubSistemaPaginasVO listaPaginasPorIdSubSistema(SubSistemaUsuarioVO subSistema, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = subSistema.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "PgnListaIdSub", xmlIN));

            SubSistemaPaginasVO subSistemaPaginasVO = SubSistemaPaginasVO.Factory.newInstance();
            if (xmlOUT != null && !xmlOUT.trim().equals(ConstantesCRM.SVAZIO)) {
                MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

                tratarWarningException(msgDocRet.getMsg().getMsgHdr());

                subSistemaPaginasVO = SubSistemaPaginasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSubSistemaPaginasVO();
            }

            return subSistemaPaginasVO;

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaPaginasPorIdSubSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaPaginasPorIdSubSistema", ex));

        } catch (TuxedoWarningException twe) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:listaPaginasPorIdSubSistema(" + user + ") - [" + twe.getMessage() + "]");
            throw twe;

        } catch (TuxedoServiceCallerException ex) {
            if (ex.getMessage() != null) {
                String erro = ex.getMessage();
                // Não foi encontrada nenhuma pagina
                if (erro.startsWith("[PgnListaIdSub:1:08W0000]")) {
                    SubSistemaPaginasVO subSistemaPaginasVO = SubSistemaPaginasVO.Factory.newInstance();
                    return subSistemaPaginasVO;
                } else {
                    log.error("TuxedoException - ManterSistemaFacadeImpl:listaPaginasPorIdSubSistema(" + user + ") - [" + ex.getMessage() + "]");
                    throw new TuxedoException(ex);
                }
            } else {
                throw new TuxedoException(ex);
            }

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaPaginasPorIdSubSistema(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUnidadesGrupamentoVO listaRelGrupamUnidades(SalvaUnidadesGrupamentoVO filtroUnidades, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = filtroUnidades.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GptUniRelacao", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListaUnidadesGrupamentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaUnidadesGrupamentoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:listaRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:listaRelGrupamUnidades", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:listaRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:listaRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUnidadesGrupamentoVO salvaRelGrupamUnidades(SalvaUnidadesGrupamentoVO salvaUnidades, String user) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = salvaUnidades.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GPTUNIRELACIO", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

            return ListaUnidadesGrupamentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaUnidadesGrupamentoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterSistemaFacadeImpl:salvaRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterSistemaFacadeImpl:salvaRelGrupamUnidades", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterSistemaFacadeImpl:salvaRelGrupamUnidades(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterSistemaFacadeImpl:salvaRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterSistemaFacadeImpl:salvaRelGrupamUnidades(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    private void tratarWarningException(MsgHeaderVO msgHeaderVO) throws TuxedoWarningException {

        if (msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("W") > -1) {
            XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(), msgHeaderVO.getUser(), "fo", 'W', msgHeaderVO.getStatusCode().substring(0, 4), msgHeaderVO.getStatusText());
            throw new TuxedoWarningException(xmlHeader);
        } else if (msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("E") > -1) {
            XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(), msgHeaderVO.getUser(), "fo", 'E', msgHeaderVO.getStatusCode().substring(0, 4), msgHeaderVO.getStatusText());
            throw new TuxedoWarningException(xmlHeader);
        }

    }

}
