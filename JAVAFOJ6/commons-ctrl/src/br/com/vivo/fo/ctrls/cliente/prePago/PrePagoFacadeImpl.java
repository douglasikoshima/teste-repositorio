package br.com.vivo.fo.ctrls.cliente.prePago;

import java.sql.ResultSet;
import java.sql.SQLException;

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
import br.com.vivo.fo.atendimento.vo.SETPPTMAVODocument;
import br.com.vivo.fo.atendimento.vo.SETPPTMAVODocument.SETPPTMAVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.CNAEVODocument;
import br.com.vivo.fo.cliente.vo.CNAEVODocument.CNAEVO;
import br.com.vivo.fo.cliente.vo.ListasVODocument;
import br.com.vivo.fo.cliente.vo.ListasVODocument.ListasVO;
import br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument;
import br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument.PrePagoRetornoValidaLinha;
import br.com.vivo.fo.cliente.vo.PrePagoVODocument;
import br.com.vivo.fo.cliente.vo.PrePagoVODocument.PrePagoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.db.DataBaseCall;
import br.com.vivo.fo.dbclasses.ApoioParametro;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "PrePagoFacade", mappedName = "PrePagoFacade")
@Local(PrePagoFacade.class)
@Session(ejbName = "PrePagoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PrePagoFacadeImpl implements PrePagoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    @EJB
    private DataBaseCall      database;

    private static Logger     log              = new Logger("clientes");

    /**
     * Recupera dados de Lista para a Tela
     * @common:operation
     */
    public ListasVO getListasVO(String user) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "GETPPTMA", ConstantesCRM.SVAZIO);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            return ListasVODocument.Factory.parse(xmlOUT).getListasVO();

        } catch (XmlException ex) {
            log.error("PrePagoFacadeImpl:getListasVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de retorno: ", ex));

        } catch (Exception ex) {
            log.error("PrePagoFacadeImpl:getListasVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * Verifica se a linha é válida
     * @common:operation
     */
    public PrePagoRetornoValidaLinha validarLinha(String user, String nrLinha) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        PrePagoRetornoValidaLinha validaLinha = PrePagoRetornoValidaLinha.Factory.newInstance();
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "VALPPTMA", "<nrLinha>" + nrLinha + "</nrLinha>");
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            validaLinha = PrePagoRetornoValidaLinhaDocument.Factory.parse(xmlOUT).getPrePagoRetornoValidaLinha();

        } catch (XmlException ex) {
            log.error("XmlException - PrePagoFacadeImpl:validarLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de retorno: PrePagoFacadeImpl:validarLinha", ex));

        } catch (Exception ex) {
            log.error("Exception - PrePagoFacadeImpl:validarLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return validaLinha;
    }

    /**
     * @common:operation
     */
    public PrePagoVO getPessoaByDocumento(String user, String idTipoPessoa, String idTipoDocumento, String nrDocumento, boolean inPesquisaCliente, int nrPagina) throws TuxedoException,
            FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;

        try {
        	xmlIN = "PF".equals(idTipoPessoa) ? "<idTipoPessoa>1</idTipoPessoa><idTipoDocumento>" + idTipoDocumento + "</idTipoDocumento><nrDocumento>" + nrDocumento + "</nrDocumento>" : "";
            xmlIN = "PJ".equals(idTipoPessoa) ? "<idTipoPessoa>2</idTipoPessoa><nrDocumento>" + nrDocumento + "</nrDocumento>" : xmlIN;

            if (inPesquisaCliente) {
                xmlIN += "<inPesquisaCliente>1</inPesquisaCliente>";
                xmlIN += "<nrPagina>" + nrPagina + "</nrPagina>";
            } else {
                xmlIN += "<inPesquisaCliente>0</inPesquisaCliente>";
            }

            xmlIN = XmlManager.MakeXmlIn(user, "GETPPDOCTMA", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            return PrePagoVODocument.Factory.parse(xmlOUT).getPrePagoVO();

        } catch (XmlException ex) {
            log.error("PrePagoFacadeImpl:getPessoaByDocumento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("PrePagoFacadeImpl:getPessoaByDocumento::Erro na montagem do XML de retorno: ", ex));

        } catch (Exception ex) {
            log.error("PrePagoFacadeImpl:getPessoaByDocumento(" + user + ") - [" + ex.getMessage() + "]");
          
            if(ex instanceof TuxedoServiceCallerException){
            	throw (new FacadeException("XML: " + ex.getMessage(), ex));
            }else{
            	throw (new FacadeException(ex));
            }
        }
    }

    /**
     * @common:operation
     */
    public PrePagoVO getPessoaByDocumentoFiltro(String user, String idTipoPessoa, String idTipoDocumento, String nrDocumento, boolean inPesquisaCliente, int nrPagina, String nrLinha, String nrConta)
            throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;

        try {
            xmlIN = "PF".equals(idTipoPessoa) ? "<idTipoPessoa>1</idTipoPessoa><idTipoDocumento>" + idTipoDocumento + "</idTipoDocumento><nrDocumento>" + nrDocumento + "</nrDocumento>" : "";
            xmlIN = "PJ".equals(idTipoPessoa) ? "<idTipoPessoa>2</idTipoPessoa><nrDocumento>" + nrDocumento + "</nrDocumento>" : xmlIN;

            if (inPesquisaCliente) {
                xmlIN += "<inPesquisaCliente>1</inPesquisaCliente>";
                xmlIN += "<nrPagina>" + nrPagina + "</nrPagina>";
            } else {
                xmlIN += "<inPesquisaCliente>0</inPesquisaCliente>";
            }
            if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
                xmlIN += "<nrLinha>" + nrLinha + "</nrLinha>";
            }
            if (nrConta != null && !ConstantesCRM.SVAZIO.equals(nrConta)) {
                xmlIN += "<nrConta>" + nrConta + "</nrConta>";
            }

            xmlIN = XmlManager.MakeXmlIn(user, "GETPPDOCTMA", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            return PrePagoVODocument.Factory.parse(xmlOUT).getPrePagoVO();

        } catch (XmlException ex) {
            log.error("PrePagoFacadeImpl:getPessoaByDocumento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("PrePagoFacadeImpl:getPessoaByDocumento::Erro na montagem do XML de retorno: ", ex));

        } catch (Exception ex) {
            log.error("PrePagoFacadeImpl:getPessoaByDocumento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * Busca os dados de Pessoa PrePago atraves do numero do documento
     * @common:operation
     */
    public PrePagoVO getPrePagoVOById(String user, String idTipoPessoa, String idLinhaTelefonica, String idPessoa) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "PF".equals(idTipoPessoa) ? "<idTipoPessoa>1</idTipoPessoa>" : "PJ".equals(idTipoPessoa) ? "<idTipoPessoa>2</idTipoPessoa>" : "";
            xmlIN += "<idLinhaTelefonica>" + idLinhaTelefonica + "</idLinhaTelefonica><idPessoa>" + idPessoa + "</idPessoa>";
            xmlIN = XmlManager.MakeXmlIn(user, "GETPPDOCTMA", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            return PrePagoVODocument.Factory.parse(xmlOUT).getPrePagoVO();

        } catch (XmlException ex) {
            log.error("XmlException - PrePagoFacadeImpl:getPrePagoVOById(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("PrePagoFacadeImpl:getPrePagoVOById::Erro na montagem do XML de retorno: ", ex));

        } catch (Exception ex) {
            log.error("PrePagoFacadeImpl:getPrePagoVOById(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * Busca os dados de Pessoa PrePago atraves do numero do documento
     * @common:operation
     */
    public SETPPTMAVO setPrePagoVO(String user, PrePagoVO prePagoVO) throws FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "SETPPTMA", prePagoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            return SETPPTMAVODocument.Factory.parse(xmlOUT).getSETPPTMAVO();

        } catch (Exception ex) {
            log.error("PrePagoFacadeImpl:setPrePagoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public CNAEVO validaCNAE(String user, String nrCNAE) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        CNAEVODocument cNAEVODocument = CNAEVODocument.Factory.newInstance();
        try {
            xmlIN = "<nrCNAE>" + nrCNAE + "</nrCNAE>";
            xmlIN = XmlManager.MakeXmlIn(user, "PPVALCNAE", xmlIN);
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            cNAEVODocument = CNAEVODocument.Factory.parse(xmlOUT);

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:validaCNAE(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:validaCNAE", ex));

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:validaCNAE(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return cNAEVODocument.getCNAEVO();
    }

    /**
     * @common:operation
     */
    public boolean inSincronismo(String user) throws FacadeException {
        // Por padrão o Sincronismo está ligado. Logo, caso ocorra algum erro, garante-se o fluxo de negócio padrão.
        try {
            boolean retorno = false;
            ApoioParametro pesquisa = isSincronismo();
            retorno = ConstantesCRM.SONE.equals(pesquisa.getDsValorParametro()) ? true : false;

            return retorno;

        } catch (Exception ex) {
            log.error("PrePagoFacadeImpl:inSincronismo(" + user + ") - [" + ex.getMessage() + "]");
            return true;
        }
    }

    public ApoioParametro isSincronismo() throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append("SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'PP_INSINCRONISMO'");

        ApoioParametro apoioParametro = new ApoioParametro();
        ResultSet rs = database.executeQuery(query.toString());

        if (rs.next()) {
            apoioParametro.setIdParametro(rs.getLong("idParametro"));
            apoioParametro.setCdParametro(rs.getString("cdParametro"));
            apoioParametro.setDsParametro(rs.getString("dsParametro"));
            apoioParametro.setDsValorParametro(rs.getString("dsValorParametro"));
            apoioParametro.setIdUsuarioAlteracao(rs.getLong("idUsuarioAlteracao"));
            apoioParametro.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
        }
        rs.close();
        database.release();

        return apoioParametro;
    }
}