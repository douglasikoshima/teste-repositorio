package br.com.vivo.fo.ctrls.cliente.dadosComportamentais;

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
import br.com.vivo.fo.cliente.vo.AssuntoVODocument.AssuntoVO;
import br.com.vivo.fo.cliente.vo.AssuntosDocument.Assuntos;
import br.com.vivo.fo.cliente.vo.ConteudosDocument;
import br.com.vivo.fo.cliente.vo.ConteudosDocument.Conteudos;
import br.com.vivo.fo.cliente.vo.DadoComportamentalDocument.DadoComportamental;
import br.com.vivo.fo.cliente.vo.DadosComportamentaisDocument;
import br.com.vivo.fo.cliente.vo.DadosComportamentaisDocument.DadosComportamentais;
import br.com.vivo.fo.cliente.vo.ListaConteudoVODocument;
import br.com.vivo.fo.cliente.vo.ListaConteudoVODocument.ListaConteudoVO;
import br.com.vivo.fo.cliente.vo.ManterDadosComportamentaisVODocument;
import br.com.vivo.fo.cliente.vo.ManterDadosComportamentaisVODocument.ManterDadosComportamentaisVO;
import br.com.vivo.fo.cliente.vo.SubAssuntoGravacaoVODocument.SubAssuntoGravacaoVO;
import br.com.vivo.fo.cliente.vo.SubAssuntosDocument;
import br.com.vivo.fo.cliente.vo.SubAssuntosDocument.SubAssuntos;
import br.com.vivo.fo.cliente.vo.ValorPossivelVODocument.ValorPossivelVO;
import br.com.vivo.fo.cliente.vo.ValoresPossiveisDocument;
import br.com.vivo.fo.cliente.vo.ValoresPossiveisDocument.ValoresPossiveis;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "DadosComportamentaisFacade", mappedName = "DadosComportamentaisFacade")
@Local(DadosComportamentaisFacade.class)
@Session(ejbName = "DadosComportamentaisFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DadosComportamentaisFacadeImpl implements DadosComportamentaisFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("clientes");

    static final long         serialVersionUID = 1L;
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public ListaConteudoVO getListas(String user, String inFiltro) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:getListas(" + user + ")");
            ListaConteudoVO listaConteudoVO = null;

            xmlIN = "<inFiltro>" + inFiltro + "</inFiltro>";
            xmlIN = XmlManager.MakeXmlIn(user, "LContInicial", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            listaConteudoVO = ListaConteudoVODocument.Factory.parse(xmlOUT).getListaConteudoVO();

            return listaConteudoVO;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:getListas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:getListas", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:getListas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public SubAssuntos getListaSubAssuntos(String user, int codigoAssunto, String inFiltro) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:getListaSubAssuntos(" + user + ", " + codigoAssunto + ")");

            SubAssuntos subAssuntos = null;

            xmlIN = "<idAssunto>" + codigoAssunto + "</idAssunto><inFiltro>" + inFiltro + "</inFiltro>";
            xmlIN = XmlManager.MakeXmlIn(user, "LSubAssunto", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            subAssuntos = SubAssuntosDocument.Factory.parse(xmlOUT).getSubAssuntos();

            return subAssuntos;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:getListaSubAssuntos(" + user + ", " + codigoAssunto + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:getListaSubAssuntos", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:getListaSubAssuntos(" + user + ", " + codigoAssunto + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public DadosComportamentais findDadosComportamentais(String user, int codigoSubAssunto) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:findDadosComportamentais(" + user + ",  " + codigoSubAssunto + ")");

            DadosComportamentais dadosComportamentais = null;

            xmlIN = "<idSubAssunto>" + codigoSubAssunto + "</idSubAssunto>";
            xmlIN = XmlManager.MakeXmlIn(user, "LConteudo", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            dadosComportamentais = DadosComportamentaisDocument.Factory.parse(xmlOUT).getDadosComportamentais();

            return dadosComportamentais;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:findDadosComportamentais(" + user + ", " + codigoSubAssunto + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:findDadosComportamentais", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:findDadosComportamentais(" + user + ", " + codigoSubAssunto + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String setAlteraDadoComportamental(String user, DadoComportamental dc) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:setAlteraDadoComportamental(" + user + ", " + dc + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "AAtributo", dc.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:setAlteraDadoComportamental(" + user + ", " + dc + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setAlteraDadoComportamental", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:setAlteraDadoComportamental(" + user + ", " + dc + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String setGravaDadoComportamental(String user, DadoComportamental dc) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:setGravaDadoComportamental(" + user + ", " + dc + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "IAtributo", dc.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:setGravaDadoComportamental(" + user + ", " + dc + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setGravaDadoComportamental", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:setGravaDadoComportamental(" + user + ", " + dc + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Assuntos getListaAssuntos(String user, String inFiltro) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:getListaAssuntos(" + user + ")");

            Assuntos assuntos = null;

            xmlIN = "<inFiltro>" + inFiltro + "</inFiltro>";
            xmlIN = XmlManager.MakeXmlIn(user, "LContInicial", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaConteudoVO lc = null;
            lc = ListaConteudoVODocument.Factory.parse(xmlOUT).getListaConteudoVO();
            assuntos = lc.getAssuntos();

            return assuntos;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:getListaAssuntos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:getListaAssuntos", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:getListaAssuntos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String setAssunto(String user, AssuntoVO assuntoVO) throws TuxedoException, FacadeException {

        try {

            log.debug("DadosComportamentaisFacadeImpl:setAssunto(" + user + ", " + assuntoVO + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "IAssunto", assuntoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {

            log.error("XmlException - DadosComportamentaisFacadeImpl:setAssunto(" + user + ", " + assuntoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setAssunto", ex));

        } catch (Exception ex) {

            log.error("Exception - DadosComportamentaisFacadeImpl:setAssunto(" + user + ", " + assuntoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /**
     * @common:operation
     */
    public String setAlterarAssunto(String user, AssuntoVO assuntoVO) throws TuxedoException, FacadeException {

        try {

            log.debug("DadosComportamentaisFacadeImpl:setAlterarAssunto(" + user + ", " + assuntoVO + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "AAssunto", assuntoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {

            log.error("XmlException - DadosComportamentaisFacadeImpl:setAlterarAssunto(" + user + ", " + assuntoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setAlterarAssunto", ex));

        } catch (Exception ex) {

            log.error("Exception - DadosComportamentaisFacadeImpl:setAlterarAssunto(" + user + ", " + assuntoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /**
     * @common:operation
     */
    public String setAlteraSubAssunto(String user, SubAssuntoGravacaoVO subAssuntoGravacaoVO) throws TuxedoException, FacadeException {

        try {

            log.debug("DadosComportamentaisFacadeImpl:setAlteraSubAssunto(" + user + ", " + subAssuntoGravacaoVO + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "ASubAssunto", subAssuntoGravacaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {

            log.error("XmlException - DadosComportamentaisFacadeImpl:setAlteraSubAssunto(" + user + ", " + subAssuntoGravacaoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setAlteraSubAssunto", ex));

        } catch (Exception ex) {

            log.error("Exception - DadosComportamentaisFacadeImpl:setAlteraSubAssunto(" + user + ", " + subAssuntoGravacaoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /**
     * @common:operation
     */
    public String setGravaSubAssunto(String user, SubAssuntoGravacaoVO subAssuntoGravacaoVO) throws TuxedoException, FacadeException {

        try {

            log.debug("DadosComportamentaisFacadeImpl:setGravaSubAssunto(" + user + ", " + subAssuntoGravacaoVO + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "ISubAssunto", subAssuntoGravacaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {

            log.error("XmlException - DadosComportamentaisFacadeImpl:setGravaSubAssunto(" + user + ", " + subAssuntoGravacaoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setGravaSubAssunto", ex));

        } catch (Exception ex) {

            log.error("Exception - DadosComportamentaisFacadeImpl:setGravaSubAssunto(" + user + ", " + subAssuntoGravacaoVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /**
     * @common:operation
     */
    public Conteudos getListaConteudos(String user, int codigoSubAssunto, String inFiltro) throws TuxedoException, FacadeException {

        try {

            log.debug("DadosComportamentaisFacadeImpl:getListaConteudos(" + user + ", " + codigoSubAssunto + ")");

            Conteudos conteudos = null;

            xmlIN = "<idSubAssunto>" + codigoSubAssunto + "</idSubAssunto><inFiltro>" + inFiltro + "</inFiltro>";
            xmlIN = XmlManager.MakeXmlIn(user, "ListAtributoId", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            conteudos = ConteudosDocument.Factory.parse(xmlOUT).getConteudos();

            return conteudos;

        } catch (XmlException ex) {

            log.error("XmlException - DadosComportamentaisFacadeImpl:getListaConteudos(" + user + ", " + codigoSubAssunto + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:getListaConteudos", ex));

        } catch (Exception ex) {

            log.error("Exception - DadosComportamentaisFacadeImpl:getListaConteudos(" + user + ", " + codigoSubAssunto + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));

        }

    }

    /**
     * @common:operation
     */
    public ValoresPossiveis findListaValoresPossiveis(String user, int codigoConteudo) throws TuxedoException, FacadeException {

        try {

            log.debug("DadosComportamentaisFacadeImpl:findListaValoresPossiveis(" + user + ", " + codigoConteudo + ")");

            ValoresPossiveis valoresPossiveis = null;

            xmlIN = "<idConteudo>" + codigoConteudo + "</idConteudo>";
            xmlIN = XmlManager.MakeXmlIn(user, "LISTVALORPOSS", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            valoresPossiveis = ValoresPossiveisDocument.Factory.parse(xmlOUT).getValoresPossiveis();

            return valoresPossiveis;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:findListaValoresPossiveis(" + user + ", " + codigoConteudo + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:findListaValoresPossiveis", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:findListaValoresPossiveis(" + user + ", " + codigoConteudo + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public String setAlterarValorPossivel(String user, ValorPossivelVO valorPossivelVO) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:setAlterarValorPossivel(" + user + ", " + valorPossivelVO + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "AValorPossivel", valorPossivelVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:setAlterarValorPossivel(" + user + ", " + valorPossivelVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setAlterarValorPossivel", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:setAlterarValorPossivel(" + user + ", " + valorPossivelVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String setGravarValorPossivel(String user, ValorPossivelVO valorPossivelVO) throws TuxedoException, FacadeException {

        try {
            log.debug("DadosComportamentaisFacadeImpl:setGravarValorPossivel(" + user + ", " + valorPossivelVO + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "IValorPossivel", valorPossivelVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:setGravarValorPossivel(" + user + ", " + valorPossivelVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setGravarValorPossivel", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:setGravarValorPossivel(" + user + ", " + valorPossivelVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ManterDadosComportamentaisVO getManterDadosComportamentais(String user, String idPessoa, String idGrupo) throws TuxedoException, FacadeException {

        ManterDadosComportamentaisVO manterDadosComportamentais = ManterDadosComportamentaisVO.Factory.newInstance();
        try {
            xmlIN = "<idPessoa>" + idPessoa + "</idPessoa><idGrupo>" + idGrupo + "</idGrupo>";
            xmlIN = XmlManager.MakeXmlIn(user, "CadCompCliIni", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            manterDadosComportamentais = ManterDadosComportamentaisVODocument.Factory.parse(xmlOUT).getManterDadosComportamentaisVO();

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:getManterDadosComportamentais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:getManterDadosComportamentais", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:getManterDadosComportamentais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return manterDadosComportamentais;
    }

    /**
     * @common:operation
     */
    public void setSalvarManterDadosComportamentais(String user, ManterDadosComportamentaisVO dadosComportamentais) throws TuxedoException, FacadeException {
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "CadCompCliIns", dadosComportamentais.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:setSalvarManterDadosComportamentais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:setSalvarManterDadosComportamentais", ex));

        } catch (Exception ex) {
            log.error("Exception - DadosComportamentaisFacadeImpl:setSalvarManterDadosComportamentais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ManterDadosComportamentaisVO getAtributosDadosComportamentais(String user, String idPessoa, String idSubAssunto, String idGrupo) throws TuxedoException, FacadeException {

        ManterDadosComportamentaisVO manterDadosComportamentaisAssunto = ManterDadosComportamentaisVO.Factory.newInstance();
        try {
            xmlIN = "<idPessoa>" + idPessoa + "</idPessoa><idSubAssunto>" + idSubAssunto + "</idSubAssunto><idGrupo>" + idGrupo + "</idGrupo>";
            xmlIN = XmlManager.MakeXmlIn(user, "CadCompCliAtt", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            manterDadosComportamentaisAssunto = ManterDadosComportamentaisVODocument.Factory.parse(xmlOUT).getManterDadosComportamentaisVO();

        } catch (XmlException ex) {
            log.error("XmlException - DadosComportamentaisFacadeImpl:getAtributosDadosComportamentais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: DadosComportamentaisFacadeImpl:getAtributosDadosComportamentais", ex));

        } catch (Exception ex) {
            log.error("DadosComportamentaisFacadeImpl:getAtributosDadosComportamentais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return manterDadosComportamentaisAssunto;
    }
}