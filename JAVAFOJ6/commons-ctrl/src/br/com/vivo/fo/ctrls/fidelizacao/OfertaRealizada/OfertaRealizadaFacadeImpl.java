package br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada;

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
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument.ListaBonusVO;
import br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument.ListaMigracaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument.ListaPlanoVO;
import br.com.vivo.fo.fidelizacao.vo.MensajeEncerraVODocument.MensajeEncerraVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "OfertaRealizadaFacade", mappedName = "OfertaRealizadaFacade")
@Local(OfertaRealizadaFacade.class)
@Session(ejbName = "OfertaRealizadaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OfertaRealizadaFacadeImpl implements OfertaRealizadaFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("fidelizacao");

    /**
     * @common:operation
     */
    public RetornoVO finalizaRetencao(String user, FinalizaRetencaoVO finalizaRetencaoVO) throws TuxedoException, FacadeException {
        log.debug("OfertaRealizadaFacadeImpl:finalizaRetencao" + "( " + user + " )");
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = finalizaRetencaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"fidelizacao.fo.vivo.com.br/vo\"", "");

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FINALRETENCAO", xmlIN));

            return RetornoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:finalizaRetencao(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaRealizadaFacadeImpl:finalizaRetencao", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:finalizaRetencao(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaPlanoVO getPlano(String user, String idUfOperadora, String sgTipoPessoa, String idTipoLinha) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(
                    user, 
                    "SELPLANO", 
                    new StringBuffer("<idufop>").append(idUfOperadora).append("</idufop>")
                             .append("<sgTipoPessoa>").append(sgTipoPessoa).append("</sgTipoPessoa>")
                             .append("<idTipoLinha>").append(idTipoLinha).append("</idTipoLinha>")
                             .append("<texto></texto>").toString());

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return ListaPlanoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getListaPlanoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaRealizadaImpl:getPlano", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("\tOfertaRealizadaImpl:getPlano(" + user + ") - [" + ex.getMessage() + "]", ex);
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getMsgEncerramento(String user, String idUfOperadora, String idTpEncerramento) throws FacadeException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(
                    user, 
                    "GETMSGENCERRAR", 
                    new StringBuffer("<idUfOperadora>").append(idUfOperadora).append("</idUfOperadora>")
                             .append("<idTpEncerramento>").append(idTpEncerramento).append("</idTpEncerramento>").toString());

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();
            
        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getLista(" + user + "," + idUfOperadora + "," + idTpEncerramento + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AdequacaoPlanoFacadeImpl:getLista", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("AdequacaoPlanoFacadeImpl:getLista(" + user + "," + idUfOperadora + "," + idTpEncerramento + ") - [" + ex.getMessage() + "]", ex);
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getLista(" + user + "," + idUfOperadora + "," + idTpEncerramento + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaBonusVO getBonus(String user, String idUfOperadora, String idMatrizOferta, String sgTipoPessoa, String idSegmentacao, String idTipoLinha) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(
                    user, 
                    "GETBONUS", 
                    new StringBuffer("<idUfOperadora>").append(idUfOperadora).append("</idUfOperadora>")
                             .append("<sgTipoPessoa>").append(sgTipoPessoa).append("</sgTipoPessoa>")
                             .append("<idMatrizOferta>").append(idMatrizOferta).append("</idMatrizOferta>")
                             .append("<idSegmentacao>").append(idSegmentacao).append("</idSegmentacao>")
                             .append("<idTipoLinha>").append(idTipoLinha).append("</idTipoLinha>")
                             .append("<nmBonus>*</nmBonus>")
                             .append("<idUnidadeOferta>0</idUnidadeOferta>").toString());

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return ListaBonusVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getListaBonusVO();
            
        } catch (XmlException xex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getBonus(" + user + ", " + idUfOperadora + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:getBonus", xex));

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getBonus(" + user + ", " + idUfOperadora + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaMigracaoVO getListaMigracao(String user, String idUfOperadora, String sgTipoPessoa, String idTipoLinha) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(
                    user, 
                    "SELMIGRACAO", 
                    new StringBuffer("<idUfOperadora>").append(idUfOperadora).append("</idUfOperadora>")
                             .append("<sgTipoPessoa>").append(sgTipoPessoa).append("</sgTipoPessoa>")
                             .append("<idTipoLinha>").append(idTipoLinha).append("</idTipoLinha>")
                             .append("<nmBonus></nmBonus>").toString());

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return ListaMigracaoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getListaMigracaoVO();
            
        } catch (TuxedoServiceCallerException tex) {
            log.error("/ManterBonusFacadeImpl:getListaMigracao(" + user + ", " + idUfOperadora + ") - [" + tex.getMessage() + "]", tex);
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getListaMigracao(" + user + ", " + idUfOperadora + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:getListaMigracao", xex));

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getListaMigracao(" + user + ", " + idUfOperadora + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralDescricaoVO getOfertasDisponiveis(String user, String[] dados, String idTipoLinha) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(
                    user,
                    "GETOFDISP",
                    new StringBuffer("<idUfOperadora>").append(dados[0]).append("</idUfOperadora>")
                            .append("<idSegmentacao>").append(dados[1]).append("</idSegmentacao>")
                            .append("<idTipoPessoa>").append(dados[2]).append("</idTipoPessoa>")
                            .append("<idRespostaIntencao>").append(dados[3]).append("</idRespostaIntencao>")
                            .append("<idRespostaDestino>").append(dados[4]).append("</idRespostaDestino>")
                            .append("<idGrupo>").append(dados[5]).append("</idGrupo>")
                            .append("<idTipoLinha>").append(idTipoLinha).append("</idTipoLinha>").toString());

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return FidelizacaoListaGeralDescricaoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralDescricaoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getOfertasDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaDisponivelFacadeImpl:getOfertasDisponiveis", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("\tOfertaDisponivelFacadeImpl:getOfertasDisponiveis(" + user + ") - [" + ex.getMessage() + "]", ex);
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getOfertasDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getBuscaUrl(String user, String[] dados) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<idOferta>" + dados[0] + "</idOferta>";

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETURL", xmlIN));

            return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getBuscaUrl(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaDisponivelFacadeImpl:getBuscaUrl", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaRealizadaFacadeImpl:getBuscaUrl(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getBuscaUrl(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setObservacao(String user, MensajeEncerraVO msjEncerraVO) throws TuxedoException, XmlException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        try{
            xmlIN = XmlManager.MakeXmlIn(user,"UPDOBSRETENCAO",msjEncerraVO.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO));
            
            tuxedo.callService("TuxConnector", xmlIN);
        
        } catch(XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:setObservacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: OfertaRealizadaFacadeImpl:setObservacao", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaRealizadaFacadeImpl:getBuscaUrl(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch(Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:setObservacao(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addVaiPensarCancelar(String user, String[] dados, char tipoEncerramento, String observacao) throws FacadeException, TuxedoException {
        log.debug("OfertaRealizadaFacadeImpl:addVaiPensarCancelar" + "( " + user + " )");
        String xmlIN  = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<idPessoaDePara>" + dados[0] + "</idPessoaDePara><idRespostaIntencao>" + dados[1] + "</idRespostaIntencao>";
            xmlIN += "<idRespostaDestino>" + dados[2] + "</idRespostaDestino>";
            xmlIN += "<dsObservacao>" + observacao + "</dsObservacao><idLinhaTelefonica>" + dados[4] + "</idLinhaTelefonica>";
            xmlIN += "<nrLinha>" + dados[5] + "</nrLinha>";
            xmlIN += "<nomeContato>null</nomeContato>";
            xmlIN += "<telefone>null</telefone>";
            xmlIN += "<dtAgendamento>null</dtAgendamento>";
            xmlIN += "<comentario>null</comentario>";
            xmlIN += "<idTipoEncerramento>" + tipoEncerramento + "</idTipoEncerramento>";

            tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FINALRETENCAO", xmlIN));

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:addVaiPensarCancelar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaRealizadaFacadeImpl:addVaiPensarCancelar", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:addVaiPensarCancelar(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO getDiasSuspTemp(String user) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "SELSUSPTEMP", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return RetornoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getDiasSuspTemp(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getDiasSuspTemp", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("\tOfertaAparelhoFacadeImpl:getDiasSuspTemp(" + user + ") - [" + ex.getMessage() + "]", ex);
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getDiasSuspTemp(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmContatoFolhaVO getListaPalitagens(String user, AtendimentoPesquisaVO atendimentoPesquisaVO) throws TuxedoException, FacadeException {
        log.debug("OfertaRealizadaFacadeImpl:getListaPalitagens" + "( " + user + " )");
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = atendimentoPesquisaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = xmlIN.replaceAll(" xmlns:vo=\"workflow.fo.vivo.com.br/vo\"", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SELMOTALTEND", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AdmContatoFolhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAdmContatoFolhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaRealizadaFacadeImpl:getListaPalitagens(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaRealizadaFacadeImpl:getListaPalitagens", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaRealizadaFacadeImpl:getListaPalitagens(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
