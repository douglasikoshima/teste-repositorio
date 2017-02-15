package br.com.vivo.fo.ctrls.cliente.prePago.manutencaoPrePago;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.ARGDocument.ARG;
import noNamespace.MsgDocument;
import noNamespace.ServicoEmailBeanDocument.ServicoEmailBean;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.assembler.ServiceRouter;
import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument;
import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO;
import br.com.vivo.fo.cliente.vo.CNAEVODocument;
import br.com.vivo.fo.cliente.vo.CNAEVODocument.CNAEVO;
import br.com.vivo.fo.cliente.vo.ManterPrePagoVODocument;
import br.com.vivo.fo.cliente.vo.ManterPrePagoVODocument.ManterPrePagoVO;
import br.com.vivo.fo.cliente.vo.PesquisaGrupoOpcoesVODocument;
import br.com.vivo.fo.cliente.vo.PesquisaGrupoOpcoesVODocument.PesquisaGrupoOpcoesVO;
import br.com.vivo.fo.cliente.vo.PesquisaTIVODocument;
import br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO;
import br.com.vivo.fo.cliente.vo.PrePagoPesquisaVODocument.PrePagoPesquisaVO;
import br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument;
import br.com.vivo.fo.cliente.vo.PrePagoRetornoValidaLinhaDocument.PrePagoRetornoValidaLinha;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.vol.menu.vo.ARVOREDocument.ARVORE;

@SuppressWarnings({"rawtypes","unchecked"})
@Stateless(name = "ManutencaoPrePagoFacade", mappedName = "ManutencaoPrePagoFacade")
@Local(ManutencaoPrePagoFacade.class)
@Session(ejbName = "ManutencaoPrePagoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManutencaoPrePagoFacadeImpl implements ManutencaoPrePagoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log    = new Logger("clientes");

    /**
     * @common:operation
     */
    public ManterPrePagoVO getPessoaPrePago(String user, ManterPrePagoVO manterPrePagoVO) throws TuxedoException, FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPPESPESSOA", manterPrePagoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO)));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ManterPrePagoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getManterPrePagoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:getPessoaPrePago(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:getPessoaPrePago", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:getPessoaPrePago(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:getPessoaPrePago(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void gravaPrePago(String user, ManterPrePagoVO manterPrePagoVO) throws TuxedoException, FacadeException {
        try {
            long timeIni = System.currentTimeMillis();
            tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPINSPESSOA", manterPrePagoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO)));
            long timeFim = System.currentTimeMillis() - timeIni;

            log.debug((new StringBuffer("PPINSPESSOA > Tempo de execucao em ")).append(timeFim).append(" ms").toString());

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:gravaPrePago(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:gravaPrePago", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:gravaPrePago(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:gravaPrePago(" + user + ") - [" + ex.getMessage() + "]");
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
        try {
            xmlIN = "<nrLinha>" + nrLinha + "</nrLinha>";

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPVALLIN", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return PrePagoRetornoValidaLinhaDocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPrePagoRetornoValidaLinha();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:validarLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:validarLinha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:validarLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:validarLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PesquisaTIVO getPesquisaPessoas(String user, String pesquisa, String valor, String nmMeio, String nmSobreNome, String inPrePago) throws TuxedoException, FacadeException {
        PesquisaTIVO clientesPesquisados = PesquisaTIVODocument.PesquisaTIVO.Factory.newInstance();
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            if (inPrePago.equals("PF") || inPrePago.equals("PJ")) {
                xmlIN = "<pesquisa>" + pesquisa + "</pesquisa><valor>" + valor + "</valor><nmMeio>" + nmMeio + "</nmMeio><nmSobreNome>" + nmSobreNome + "</nmSobreNome><inPrePago>1</inPrePago><inTipoPessoa>" + inPrePago + "</inTipoPessoa>";
            } else {
                xmlIN = "<pesquisa>" + pesquisa + "</pesquisa><valor>" + valor + "</valor><nmMeio>" + nmMeio + "</nmMeio><nmSobreNome>" + nmSobreNome + "</nmSobreNome><inPrePago>1</inPrePago>";
            }

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPLISPESSOA", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return PesquisaTIVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPesquisaTIVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:getPesquisaPessoas(" + user + ", " + clientesPesquisados.toString() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:getPesquisaPessoas", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:getPesquisaPessoas(" + user + ", " + clientesPesquisados.toString() + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:getPesquisaPessoas(" + user + ", " + clientesPesquisados.toString() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public CNAEVO validaCNAE(String user, String nrCNAE) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<nrCNAE>" + nrCNAE + "</nrCNAE>";

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPVALCNAE", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return CNAEVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getCNAEVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:validaCNAE(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:validaCNAE", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:validaCNAE(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:validaCNAE(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PesquisaGrupoOpcoesVO getIdPessoaGrupo(String user, String nrGrupo) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<nrGrupo>" + nrGrupo + "</nrGrupo>";

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPPESGRUPO", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return PesquisaGrupoOpcoesVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPesquisaGrupoOpcoesVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:getIdPessoaGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:getIdPessoaGrupo", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:getIdPessoaGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:getIdPessoaGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * Valida os dados retornados pelo ServiceRouter
     */
    private void validaRetorno(ARG retorno) throws TuxedoException {
        String erro = null;

        // O ServiceRouter retornou um xml com mensagem de erro
        if (retorno.getERRO() != null) {
            erro = retorno.getERRO().getMSG();
        }

        // O ServiceRouter retornou nada
        if (retorno == null || retorno.equals(ConstantesCRM.SVAZIO)) {
            erro = "Resposta do legado diferente do esperado";
        }

        if (erro != null) {
            XmlHeader header = new XmlHeader("validaRetorno()", ConstantesCRM.TUX_USER_DEFAULT, "90", 'E', "0000", erro);
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:validaRetorno() - [" + header.getMessage() + "]");
            throw new TuxedoException(header);
        }
    }

    /**
     * @common:operation
     */
    public java.util.ArrayList pesquisaEmailLegado(String user, String dsEmail, ARVORE[] arvore, String serviceRouter) throws TuxedoException {
        try {
            java.util.ArrayList telEncontrados = new java.util.ArrayList();

            if (arvore.length > 0) {
                // Verifica se o e-mail alterado está registrado no legado
                ServiceRouter service = new ServiceRouter(serviceRouter);
                for (int i = 0; i < arvore.length; i++) {
                    String tarefa;

                    if (ConstantesCRM.TIPO_LINHA_PRE_PAGO.equalsIgnoreCase(arvore[i].getIdTipoLinha())) {
                        tarefa = ConstantesCRM.SERVICE_ROUTER_PRE;
                    } else {
                        tarefa = ConstantesCRM.SERVICE_ROUTER_POS;
                    }

                    ARG retorno = service.consulta(getXmlCadastro(arvore[i].getCdAreaRegistro(), arvore[i].getNrLinha(), tarefa));
                    validaRetorno(retorno);

                    ServicoEmailBean cadastro = retorno.getDADOS().getServicoEmailBean();
                    if (cadastro.getCadastroAtivo() && dsEmail.equalsIgnoreCase(cadastro.getEmail())) {
                        telEncontrados.add(arvore[i]);
                    }
                }
            }

            return telEncontrados;

        } catch (Exception e) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:pesquisaEmailLegado(): Erro ao montar o XML de saida");
            XmlHeader header = new XmlHeader("pesquisaEmailLegado()", ConstantesCRM.TUX_USER_DEFAULT, "90", 'E', "0000", "Erro ao conectar com Sistema Legado-SERVICE HOUTER");
            throw new TuxedoException(header, e);
        }
    }

    /**
     * @common:operation
     */
    public ApoioParametroVO getApoioParametro(String user, ApoioParametroVO inParametro) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "GETURLROUTER", inParametro.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ApoioParametroVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getApoioParametroVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:getApoioParametro(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:getApoioParametro", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:getApoioParametro(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:getApoioParametro(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * Método Responsável por enviar novo email ao legado
     * @common:operation
     */
    public void persisteContaEmail(String user, String foneArea, String foneNumero, String idTipoLinha, String email, String serviceRouter) throws TuxedoException {
        try {
            String tarefa;
            if (ConstantesCRM.TIPO_LINHA_PRE_PAGO.equals(idTipoLinha)) {
                tarefa = ConstantesCRM.SERVICE_ROUTER_PRE;
            } else {
                tarefa = ConstantesCRM.SERVICE_ROUTER_POS;
            }

            ServiceRouter service = new ServiceRouter(serviceRouter);
            ARG retorno = service.consulta(getXmlAtiva(foneArea, foneNumero, email, tarefa, ConstantesCRM.SVAZIO));

            validaRetorno(retorno);

        } catch (Exception e) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:persisteContaEmail(): Erro ao invocar o serviço de Ativação de Email");
            XmlHeader header = new XmlHeader("persisteContaEmail()", ConstantesCRM.TUX_USER_DEFAULT, "90", 'E', "0000", "Erro ao conectar com Sistema Legado");
            throw new TuxedoException(header, e);
        }
    }

    private String getXmlCadastro(String foneArea, String foneNumero, String tarefa) {
        StringBuffer xmlIN = new StringBuffer();

        xmlIN.append(ConstantesCRM.SVAZIO);
        xmlIN.append("<?xml version='1.0' encoding='ISO-8859-1'?> ");
        xmlIN.append("<ARG>");
        xmlIN.append("<CONTROLE>");
        xmlIN.append("<BYPASS>false</BYPASS>");
        xmlIN.append("</CONTROLE>");
        xmlIN.append("<REG>");
        xmlIN.append("<COD_AREA>").append(foneArea).append("</COD_AREA>");
        xmlIN.append("<NUM_LINE>").append(foneNumero).append("</NUM_LINE>");
        xmlIN.append("<PLATAFORMA>O</PLATAFORMA>");
        xmlIN.append("<SERVICO>INFCLIENTE</SERVICO>");
        xmlIN.append("<OPERACAO>CADSRVMAIL</OPERACAO>");
        xmlIN.append("<CANAL>VOL</CANAL>");
        xmlIN.append("<USUARIO>VOL</USUARIO>");
        xmlIN.append("<SENHA>123456</SENHA>");
        xmlIN.append("<ORIGEM>VOL</ORIGEM>");
        xmlIN.append("<CON_NUMBER>1456</CON_NUMBER>");
        xmlIN.append("<TRANS_NUMBER>0</TRANS_NUMBER>");
        xmlIN.append("</REG>");
        xmlIN.append("<DADOS>");
        xmlIN.append("<OPCAO>CONSULTAR</OPCAO>");
        xmlIN.append("<TAREFA>").append(tarefa).append("</TAREFA>");
        xmlIN.append("</DADOS>");
        xmlIN.append("</ARG>");
        xmlIN.append(ConstantesCRM.SVAZIO);

        return xmlIN.toString();
    }

    private String getXmlAtiva(String foneArea, String foneNumero, String email, String tarefa, String dataTarefa) {
        StringBuffer xmlIN = new StringBuffer();

        xmlIN.append("<?xml version='1.0' encoding='ISO-8859-1'?> ");
        xmlIN.append("<ARG>");
        xmlIN.append("<CONTROLE>");
        xmlIN.append("<BYPASS>false</BYPASS>");
        xmlIN.append("</CONTROLE>");
        xmlIN.append("<REG>");
        xmlIN.append("<COD_AREA>").append(foneArea).append("</COD_AREA>");
        xmlIN.append("<NUM_LINE>").append(foneNumero).append("</NUM_LINE>");
        xmlIN.append("<PLATAFORMA>O</PLATAFORMA>");
        xmlIN.append("<SERVICO>INFCLIENTE</SERVICO>");
        xmlIN.append("<OPERACAO>CADSRVMAIL</OPERACAO>");
        xmlIN.append("<CANAL>VOL</CANAL>");
        xmlIN.append("<USUARIO>VOL</USUARIO>");
        xmlIN.append("<SENHA>123456</SENHA>");
        xmlIN.append("<ORIGEM>VOL</ORIGEM>");
        xmlIN.append("<CON_NUMBER>1456</CON_NUMBER>");
        xmlIN.append("<TRANS_NUMBER>1</TRANS_NUMBER>");
        xmlIN.append("</REG>");
        xmlIN.append("<DADOS>");
        xmlIN.append("<DATA_TAREFA>").append(dataTarefa).append("</DATA_TAREFA>");
        xmlIN.append("<EMAIL>").append(email).append("</EMAIL>");
        xmlIN.append("<TAREFA>").append(tarefa).append("</TAREFA>");
        xmlIN.append("<TELEFONE_CONTATO>12345567</TELEFONE_CONTATO>");
        xmlIN.append("</DADOS>");
        xmlIN.append("</ARG>");
        xmlIN.append(ConstantesCRM.SVAZIO);
        return xmlIN.toString();
    }

    /**
     * @common:operation
     */
    public ManterPrePagoVO pesquisaNumeroLinha(String user, PrePagoPesquisaVO manterPrePagoVO) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = manterPrePagoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "PPPESPESADC", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ManterPrePagoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getManterPrePagoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManutencaoPrePagoFacadeImpl:pesquisaNumeroLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManutencaoPrePagoFacadeImpl:pesquisaNumeroLinha", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManutencaoPrePagoFacadeImpl:pesquisaNumeroLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManutencaoPrePagoFacadeImpl:pesquisaNumeroLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
