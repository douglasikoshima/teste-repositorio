package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import noNamespace.MsgDocument.Msg;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db.ManterAgendamentoDB;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.db.ManterAgendamentoDB.LinhaPremmiun;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendaLojaImpl.ConsultaAgendaLojaPortTypeProxy;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaAgendamentosPorLinhaImpl.ConsultaAgendamentosPorLinhaPortTypeProxy;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaArvoreAssuntosImpl.ConsultaArvoreAssuntosPortTypeProxy;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaLojasImpl.ConsultaLojasPortTypeProxy;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.consultaMunicipiosImpl.ConsultaMunicipiosPortTypeProxy;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xmlns.registraAgendamentoImpl.RegistraAgendamentoPortTypeProxy;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendaLoja.input.ConsultaAgendaLojaInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendaLoja.output.ConsultaAgendaLojaOutputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.input.ConsultaAgendamentoPorLinhaInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.AgendamentoType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaAgendamentoPorLinha.output.ConsultaAgendamentoPorLinhaOutputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.input.ConsultaArvoreAssuntosInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output.AssuntoType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaArvoreAssuntos.output.ConsultaArvoreAssuntosOutputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.input.ConsultaLojasInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.output.ConsultaLojasOutputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaLojas.output.LojaType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipios.input.ConsultaMunicipiosInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.consultaMunicipios.output.ConsultaMunicipiosOutputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.input.RegistraAgendamentoInputType;
import br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP.ws.xsd.registraAgendamento.output.RegistraAgendamentoOutputType;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.vol.dados.vo.ConsultarEmailsVODocument;
import br.com.vivo.vol.dados.vo.ConsultarEmailsVODocument.ConsultarEmailsVO;
import br.com.vivo.vol.dados.vo.ConsultarTiposEmailVODocument;
import br.com.vivo.vol.dados.vo.ConsultarTiposEmailVODocument.ConsultarTiposEmailVO;
import br.com.vivo.vol.dados.vo.ListaAlertaAgendamentoVIPDocument;
import br.com.vivo.vol.dados.vo.ListaAlertaAgendamentoVIPDocument.ListaAlertaAgendamentoVIP;
import br.com.vivo.vol.dados.vo.ListaAlertaDocument.ListaAlerta;
import br.com.vivo.vol.dados.vo.VALPRIACESSOVIPDocument;
import br.com.vivo.vol.dados.vo.VALPRIACESSOVIPDocument.VALPRIACESSOVIP;

@Stateless(name = "ManterAgendamentoVIPFacade", mappedName = "ManterAgendamentoVIPFacade")
@Local(ManterAgendamentoVIPFacade.class)
@Session(ejbName = "ManterAgendamentoVIPFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterAgendamentoVIPFacadeImpl implements ManterAgendamentoVIPFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    @EJB
    private ManterAgendamentoDB           manterAgendamentoDB;

    @EJB
    private TelaInicialFacade             telaInicialFacadeControl;

    private String                        URL_GSS                   = null;
    static final long                     serialVersionUID          = 1L;
    private final static String           URL_LISTA_AGENDAMENTO     = "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaAgendamentosPorLinha/Conector_sp_Process/SoapProcess";
    private final static String           URL_CONSULTAR_ASSUNTO     = "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaArvoreAssuntos/Conector_sp_Process/SoapProcess";
    private final static String           URL_CONSULTAR_HORARIO     = "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaAgendaLoja/Conector_sp_Process/SoapProcess";
    private final static String           URL_CONSULTAR_LOJAS       = "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaLojas/Conector_sp_Process/SoapProcess";
    private final static String           URL_CONSULTAR_MUNICIPIOS  = "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaMunicipiosPorDDD/Conector_sp_Process/SoapProcess";
    private final static String           URL_REGISTRAR_AGENDAMENTO = "/Main_sp_FRONTOFFICE/Services_sp_EAI/RegistraAgendamento/Conector_sp_Process/SoapProcess";
    private static final String           SERVICE_ROUTER_PRE        = "COMPSERV_M";
    private static final String           SERVICE_ROUTER_POS        = "EXTRATO_M";
    private String                        xmlIN;
    private String                        xmlOUT;
    private final static transient Logger log                       = new Logger("vol");

    public String getUrlGss(String user) throws Exception {
        ParametroVO parametro = null;
        if (this.URL_GSS == null) {
            parametro = telaInicialFacadeControl.getParametro(user, "URL_GSS_AGENDAMENTO");
        }
        loga("URL_GSS -> ", URL_GSS);
        return parametro.getDsValorParametro();
    }

    /**
     * @common:operation
     */
    public RegistraAgendamentoOutputType incluirAgendamento(RegistraAgendamentoInputType agendamento, String observacao, String user) throws Exception {
        return manterAgendamento(agendamento, "I", observacao, user);
    }

    /**
     * @common:operation
     */
    public RegistraAgendamentoOutputType alterarAgendamento(RegistraAgendamentoInputType agendamento, String observacao, String user) throws Exception {
        return manterAgendamento(agendamento, "A", observacao, user);
    }

    /**
     * @common:operation
     */
    public RegistraAgendamentoOutputType cancelarAgendamento(RegistraAgendamentoInputType agendamento, String observacao, String user) throws Exception {
        return manterAgendamento(agendamento, "C", observacao, user);
    }

    /**
     * Inclui / Altera / Cancela um agendamento
     */
    private RegistraAgendamentoOutputType manterAgendamento(RegistraAgendamentoInputType agendamento, String operacao, String observacao, String user) throws Exception {
        agendamento.setOperacao(operacao);
        RegistraAgendamentoPortTypeProxy proxy = new RegistraAgendamentoPortTypeProxy();
        proxy.setEndpoint(getUrlGss(user) + "/Main_sp_FRONTOFFICE/Services_sp_EAI/RegistraAgendamento/Conector_sp_Process/SoapProcess");
        // SETANDO URL GSS REGISTRAR CONTATO.
        System.out.println("%%%%%% URL-: " + new java.net.URL(getUrlGss(user) + URL_REGISTRAR_AGENDAMENTO));
        RegistraAgendamentoOutputType out = proxy.registraAgendamento(agendamento);
        if (!"00".equals(out.getErrorCode())) {
            throw new FacadeException(out.getErrorDesc());
        }
        return out;
    }

    /**
     * @common:operation
     */
    public AgendamentoType[] listarAgendamentos(String ddd, String telefone, String dataInicio, String dataFim, String user) throws Exception {
        try {
            String ip = null;
            String login = null;
            String password = null;

            ConsultaAgendamentosPorLinhaPortTypeProxy proxy = new ConsultaAgendamentosPorLinhaPortTypeProxy();
            proxy.setEndpoint(getUrlGss(user) + "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaAgendamentosPorLinha/Conector_sp_Process/SoapProcess");

            ConsultaAgendamentoPorLinhaInputType in = new ConsultaAgendamentoPorLinhaInputType();

            in.setIpOrigem(ip);
            in.setLogin(login);
            in.setPassword(password);

            loga("DDD --> ", ddd);
            loga("TELEFONE -->", telefone);
            loga("DATA INICIO --> ", dataInicio);
            loga("DATA FINAL -->", dataFim);

            in.setDdd(ddd);
            in.setNumeroTelefone(telefone);
            in.setDataInicio(dataInicio);
            in.setDataFim(dataFim);

            ConsultaAgendamentoPorLinhaOutputType out = proxy.consultaAgendamentosPorLinha(in);
            if (!"00".equals(out.getErrorCode())) {
                throw new FacadeException(out.getErrorDesc());
            }
            return out.getAgendamentos();
        } catch (Exception e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public AssuntoType[] consultarAssuntos(String user) throws Exception {
        try {
            String ip = null;
            String login = null;
            String password = null;

            ConsultaArvoreAssuntosPortTypeProxy proxy = new ConsultaArvoreAssuntosPortTypeProxy();
            proxy.setEndpoint(getUrlGss(user) + "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaArvoreAssuntos/Conector_sp_Process/SoapProcess");

            ConsultaArvoreAssuntosInputType in = new ConsultaArvoreAssuntosInputType();

            in.setIpOrigem(ip);
            in.setLogin(login);
            in.setPassword(password);

            ConsultaArvoreAssuntosOutputType out = proxy.consultaArvoreAssuntos(in);
            if (!"00".equals(out.getErrorCode())) {
                throw new FacadeException(out.getErrorDesc());
            }
            return out.getAssuntos();
        } catch (Exception e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.consultarAssuntos", e);
        }
    }

    private void loga(String descricao, String param) {
        log.info(descricao + param);
        log.warn(descricao + param);
        log.error(descricao + param);
    }

    /**
     * @common:operation
     */
    public String[] consultarHorarios(String loja, String data, String user) throws Exception {
        try {
            String ip = null;
            String login = null;
            String password = null;

            ConsultaAgendaLojaPortTypeProxy proxy = new ConsultaAgendaLojaPortTypeProxy();
            proxy.setEndpoint(getUrlGss(user) + "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaAgendaLoja/Conector_sp_Process/SoapProcess");

            ConsultaAgendaLojaInputType in = new ConsultaAgendaLojaInputType();

            in.setIpOrigem(ip);
            in.setLogin(login);
            in.setPassword(password);
            in.setIdLoja(loja);
            in.setData(data);

            ConsultaAgendaLojaOutputType out = proxy.consultaAgendaLoja(in);
            if (!"00".equals(out.getErrorCode())) {
                throw new FacadeException(out.getErrorDesc());
            }
            return out.getHoras();
        } catch (Exception e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.consultarHorarios", e);
        }
    }

    /**
     * @common:operation
     */
    public LojaType[] consultarLojas(String uf, String municipio, String user) throws Exception {
        try {
            String ip = null;
            String login = null;
            String password = null;

            ConsultaLojasPortTypeProxy proxy = new ConsultaLojasPortTypeProxy();
            proxy.setEndpoint(getUrlGss(user) + "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaLojas/Conector_sp_Process/SoapProcess");

            ConsultaLojasInputType in = new ConsultaLojasInputType();

            in.setIpOrigem(ip);
            in.setLogin(login);
            in.setPassword(password);
            in.setUf(uf);
            in.setMunicipio(municipio);

            ConsultaLojasOutputType out = proxy.consultaLojas(in);
            if (!"00".equals(out.getErrorCode())) {
                throw new FacadeException(out.getErrorDesc());
            }
            return out.getLojas();
        } catch (Exception e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.consultarLojas", e);
        }
    }

    /**
     * @common:operation
     */
    public String[] consultarMunicipios(String uf, String user) throws Exception {
        try {
            ConsultaMunicipiosPortTypeProxy proxy = new ConsultaMunicipiosPortTypeProxy();
            proxy.setEndpoint(getUrlGss(user) + "/Main_sp_FRONTOFFICE/Services_sp_EAI/ConsultaMunicipios/Conector_sp_Process/SoapProcess");
            String ip = null;
            String login = null;
            String password = null;

            ConsultaMunicipiosInputType in = new ConsultaMunicipiosInputType();

            in.setIpOrigem(ip);
            in.setLogin(login);
            in.setPassword(password);
            in.setUf(uf);

            ConsultaMunicipiosOutputType out = proxy.consultaMunicipios(in);
            if (!"00".equals(out.getErrorCode())) {
                throw new FacadeException(out.getErrorDesc());
            }
            return out.getMunicipios();
        } catch (Exception e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.consultarHorarios", e);
        }
    }

    /**
     * @common:operation
     */
    public boolean primeiroAgendamento(String cdAreaRegistro, String nrLinha, String user) throws FacadeException {
        try {
            log.debug("ManterAgendamentoVIPFacadeImpl:primeiroAgendamento(" + user + ")");

            StringBuffer sb = new StringBuffer();
            sb.append("<cdAreaRegistro>" + cdAreaRegistro + "</cdAreaRegistro>");
            sb.append("<nrLinha>" + nrLinha + "</nrLinha>");

            xmlIN = sb.toString();
            xmlIN = XmlManager.MakeXmlIn(user, "VALPRIACESSVIP", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            VALPRIACESSOVIP vALPRIACESSOVIP = VALPRIACESSOVIPDocument.Factory.parse(xmlOUT).getVALPRIACESSOVIP();

            return ConstantesCRM.SONE.equals(vALPRIACESSOVIP.getInPrimeiroAcessoVIP());

        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.primeiroAgendamento(): Erro ao invocar o serviço Tuxedo.", e);
        } catch (XmlException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.primeiroAgendamento(): Erro ao montar o XML de Saída.", e);
        }
    }

    /**
     * @common:operation
     */
    public Msg incluiEmail(String user, String idPessoa, String dsEmail, String idTipoemail, String foneArea, String foneNumero, String tarefa) throws FacadeException {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>incluir</operacao>");
            sb.append("<idPessoa>" + idPessoa + "</idPessoa>");
            sb.append("<dsEmail>" + dsEmail + "</dsEmail>");
            sb.append("<idTipoEmail>" + idTipoemail + "</idTipoEmail>");

            // sb.append(ConstantesCRM.getXmlContato(this.session, ConstantesCRM.MENU_INCLUIR_EMAIL));
            // sb.append(ConstantesCRM.getXmlSMS("ComunicarInclusaoEmail"));

            xmlIN = sb.toString();
            xmlIN = XmlManager.MakeXmlIn(user, "MODEMAIL", xmlIN);

            // Executa chamada ao servico Tuxedo
            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            // Msg msg = MsgDocument.Factory.parse(xmlOUT).getMsg();
            return null;
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException("Erro ao invocar o serviço Tuxedo de Alteração de E-mail.", e);
        } catch (XmlException e) {
            throw new FacadeException("Erro ao montar o XML de Saída.", e);
        }
    }

    /**
     * @common:operation
     */
    public ListaAlerta[] consultarAlerta(String cdAreaRegistro, String nrLinha, String user) throws FacadeException {
        try {
            log.debug("ManterAgendamentoVIPFacadeImpl:consultarAlerta(" + user + ")");
            StringBuffer sb = new StringBuffer();
            sb.append("<cdAreaRegistro>" + cdAreaRegistro + "</cdAreaRegistro>");
            sb.append("<nrLinha>" + nrLinha + "</nrLinha>");

            xmlIN = sb.toString();
            xmlIN = XmlManager.MakeXmlIn(user, "LSTALERTAVIP", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaAlertaAgendamentoVIP listaAlertaAgendamentoVIP = ListaAlertaAgendamentoVIPDocument.Factory.parse(xmlOUT).getListaAlertaAgendamentoVIP();

            return listaAlertaAgendamentoVIP.getListaAlertaArray();
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.consultarAlerta(): Erro ao invocar o serviço Tuxedo.", e);
        } catch (XmlException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.consultarAlerta(): Erro ao montar o XML de Saída.", e);
        }
    }

    /**
     * @common:operation
     */
    public void incluirAlerta(ListaAlerta[] alertas, String user) throws FacadeException {
        modAlerta(alertas, user);
    }

    /**
     * @common:operation
     */
    public void alterarAlerta(ListaAlerta[] alertas, String user) throws FacadeException {
        modAlerta(alertas, user);
    }

    /**
     * @common:operation
     */
    public void cancelarAlerta(ListaAlerta[] alertas, String user) throws FacadeException {
        modAlerta(alertas, user);
    }

    /**
     * inclui / altera ou cancela alerta
     */
    private void modAlerta(ListaAlerta[] alertas, String user) throws FacadeException {
        try {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < alertas.length; i++) {
                if (alertas[i].getSgTipoComunicacao() != null && alertas[i].getSgTipoComunicacao().length() > 0) {
                    sb.append("<listaAlerta>");
                    sb.append("    <cdAreaRegistro>" + alertas[i].getCdAreaRegistro() + "</cdAreaRegistro>");
                    sb.append("    <nrLinha>" + alertas[i].getNrLinha() + "</nrLinha>");
                    sb.append("    <dsContato>" + alertas[i].getDsContato() + "</dsContato>");
                    sb.append("    <dtVisitaLoja>" + alertas[i].getDtVisitaLoja() + "</dtVisitaLoja>");
                    sb.append("    <dtAlerta>" + alertas[i].getDtAlerta() + "</dtAlerta>");
                    sb.append("    <sgTipoComunicacao>" + alertas[i].getSgTipoComunicacao() + "</sgTipoComunicacao>");
                    sb.append("    <dsLoja>" + alertas[i].getDsLoja() + "</dsLoja>");
                    sb.append("    <operacao>" + alertas[i].getOperacao() + "</operacao>");
                    sb.append("</listaAlerta>");
                }
            }
            xmlIN = sb.toString();
            xmlIN = XmlManager.MakeXmlIn(user, "MODALERTAVIP", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.modAlerta(): Erro ao invocar o serviço Tuxedo.", e);
        } catch (XmlException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.modAlerta(): Erro ao montar o XML de Saída.", e);
        }
    }

    /**
     * @common:operation
     */
    public void registrarContato(String user, String nrLinha, String cdAreaRegistro, String idTipoRelacionamento, String idCanal, String cdContato) {
        try {
            StringBuffer xmlEntrada = new StringBuffer();
            xmlEntrada.append("<nrLinha>").append(nrLinha).append("</nrLinha>").append("<cdAreaRegistro>").append(cdAreaRegistro).append("</cdAreaRegistro>").append("<idTipoRelacionamento>")
                    .append(idTipoRelacionamento).append("</idTipoRelacionamento>").append("<cdContato>").append(cdContato).append("</cdContato>").append("<idCanal>").append(idCanal)
                    .append("</idCanal>").append("<idSistemaOrigem>").append(ConstantesCRM.ID_SISTEMA_ORIGEM_VIVONET).append("</idSistemaOrigem>");

            String xmlIn = new String();
            xmlIn = XmlManager.MakeXmlIn(user, "INCRGCONTATO", xmlEntrada.toString());

            // xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
        } catch (Exception e) {
            log.error("Erro ao tentar registrar contato.", e);
        }
    }

    /**
     * @common:operation
     */
    public LinhaPremmiun getTotalLinhasPremiun(String ddd, String linha) throws FacadeException {
        try {
            LinhaPremmiun linhas = null;
            Integer intDdd = null;
            Integer intLinha = null;

            if (ddd != null) {
                intDdd = new Integer(ddd);
            } else {
                intDdd = new Integer(-1);
            }

            if (linha != null) {
                intLinha = new Integer(linha);
            } else {
                intLinha = new Integer(-1);
            }
            linhas = manterAgendamentoDB.getTotalRegistroLinhaPremiun(intDdd, intLinha);
            return linhas;
        } catch (Exception ex) {
            log.error("ManterAgendamentoVIPFacadeImpl:getTotalLinhasPremiun(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public boolean isPremium(String ddd, String linha) throws FacadeException {
        try {
            LinhaPremmiun linhaPremium = manterAgendamentoDB.isPremium(new Integer(ddd), new Integer(linha));
            // Linha não premium
            if (ConstantesCRM.SZERO.equalsIgnoreCase(linhaPremium.getTotalRegistros())) {
                return false;
            } else {
                // Linha Premium
                return true;
            }
        } catch (Exception ex) {
            log.error("ManterAgendamentoVIPFacadeImpl:isPremium(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void incluirLinha(String ddd, String linha) throws FacadeException {
        try {
            LinhaPremmiun linhaPremium = manterAgendamentoDB.getLinhaExiste(new Integer(ddd), new Integer(linha));
            if (ConstantesCRM.SZERO.equalsIgnoreCase(linhaPremium.getTotalRegistros())) {
                throw (new Exception("00002 - Registro inexistente"));
            }
            manterAgendamentoDB.incluirLinha(new Integer(ddd), new Integer(linha));
        } catch (Exception ex) {
            log.error("ManterAgendamentoVIPFacadeImpl:incluirLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirLinha(String ddd, String linha) throws FacadeException {
        try {
            manterAgendamentoDB.excluirLinha(new Integer(ddd), new Integer(linha));
        } catch (Exception ex) {
            log.error("ManterAgendamentoVIPFacadeImpl:excluirLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String getUfByDddLinha(String ddd, String linha) throws FacadeException {
        try {
            LinhaPremmiun linhaPremium = manterAgendamentoDB.getUfByIdPessoa(new Integer(ddd), new Integer(linha));
            return linhaPremium.getUf();
        } catch (Exception ex) {
            log.error("ManterAgendamentoVIPFacadeImpl:getUfByIdPessoa(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public LinhaPremmiun[] getLinhasPremmiun(String paginaInicial, String paginaFinal, String ddd, String linha) throws FacadeException {
        try {
            Integer pagInicial = null;
            Integer pagFinal = null;
            LinhaPremmiun[] linhas = null;

            if (paginaInicial != null) {
                pagInicial = new Integer(paginaInicial);
            } else {
                pagInicial = new Integer(ConstantesCRM.SONE);
            }

            if (paginaFinal != null) {
                pagFinal = new Integer(paginaFinal);
            } else {
                pagFinal = new Integer("50");
            }

            if (ddd == null && linha == null) {
                // linhas = manterAgendamentoDB.getLinhaPremmiunAll(pagInicial, pagFinal);
                linhas = new LinhaPremmiun[0];
            }

            if (ddd != null) {
                Integer intDdd = new Integer(ddd);
                linhas = manterAgendamentoDB.getLinhaPremmiunByDdd(pagInicial, pagFinal, intDdd);
            }

            if (linha != null) {
                Integer intLinha = new Integer(linha);
                linhas = manterAgendamentoDB.getLinhaPremmiunByLinha(pagInicial, pagFinal, intLinha);
            }

            return linhas;
        } catch (Exception ex) {
            log.error("ManterAgendamentoVIPFacadeImpl:registraAgendamento(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ConsultarTiposEmailVO consultaTiposEmail(String user) throws Exception {
        try {
            log.debug("ManterAgendamentoVIPFacadeImpl:consultaTiposEmail(" + user + ")");
            String in = "<operacao>consultarTiposEmail</operacao>";

            xmlIN = in;
            xmlIN = XmlManager.MakeXmlIn(user, "LSTEMAIL", xmlIN);

            // Executa chamada ao servico Tuxedo
            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ConsultarTiposEmailVO consultarTiposEmailVO = ConsultarTiposEmailVODocument.Factory.parse(xmlOUT).getConsultarTiposEmailVO();

            return consultarTiposEmailVO;

        } catch (XmlException ex) {
            log.error("XmlException - ManterAgendamentoVIPFacadeImpl:consultaTiposEmail(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterAgendamentoVIPFacadeImpl:consultaTiposEmail", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterAgendamentoVIPFacadeImpl:consultaTiposEmail(" + user + ") - [" + ex.getMessage() + "]");
            throw (ex);

        } catch (Exception ex) {
            log.error("Exception - ManterAgendamentoVIPFacadeImpl:consultaTiposEmail(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaAlertaAgendamentoVIP consultaListaAlerta(String cdAreaRegistro, String nrLinha, String user) throws Exception {
        try {
            log.debug("ManterAgendamentoVIPFacadeImpl:consultaListaAlerta(" + user + ")");

            StringBuffer in = new StringBuffer();
            in.append("<cdAreaRegistro>").append(cdAreaRegistro).append("</cdAreaRegistro>").append("<nrLinha>").append(nrLinha).append("</nrLinha>");

            xmlIN = in.toString();
            xmlIN = XmlManager.MakeXmlIn(user, "LSTALERTAVIP", xmlIN);

            // Executa chamada ao servico Tuxedo
            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaAlertaAgendamentoVIP listaAlertaAgendamentoVIP = ListaAlertaAgendamentoVIPDocument.Factory.parse(xmlOUT).getListaAlertaAgendamentoVIP();

            return listaAlertaAgendamentoVIP;
        } catch (XmlException ex) {
            log.error("XmlException - ManterAgendamentoVIPFacadeImpl:consultaListaAlerta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterAgendamentoVIPFacadeImpl:consultaListaAlerta", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterAgendamentoVIPFacadeImpl:consultaListaAlerta(" + user + ") - [" + ex.getMessage() + "]");
            throw (ex);

        } catch (Exception ex) {
            log.error("Exception - ManterAgendamentoVIPFacadeImpl:consultaListaAlerta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void enviarSMS(String ddd, String telefone, String dataHora, String loja, String operacao, String user) throws FacadeException {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<cdAreaRegistro>" + ddd + "</cdAreaRegistro>");
            sb.append("<nrLinha>" + telefone + "</nrLinha>");
            sb.append("<dtVisitaLoja>" + dataHora + "</dtVisitaLoja>");
            sb.append("<dsLoja>" + loja + "</dsLoja>");
            sb.append("<operacao>" + operacao + "</operacao>");

            xmlIN = sb.toString();

            xmlIN = XmlManager.MakeXmlIn(user, "ENVALERTAVIP", xmlIN);

            // Executa chamada ao servico Tuxedo
            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.enviarSMS(): Erro ao invocar o serviço Tuxedo.", e);
        } catch (XmlException e) {
            throw new FacadeException("AgendamentoVIPFacadeImpl.enviarSMS(): Erro ao montar o XML de Saída.", e);
        }
    }

    /**
     * @common:operation
     */
    public ConsultarEmailsVO consultaEmail(String idPessoa, String user) throws Exception {
        try {
            log.debug("ManterAgendamentoVIPFacadeImpl:consultaEmail(" + user + ")");

            xmlIN = "<idPessoa>" + idPessoa + "</idPessoa>" + "<operacao>consultarEmails</operacao>";
            xmlIN = XmlManager.MakeXmlIn(user, "LSTEMAIL", xmlIN);

            // Executa chamada ao servico Tuxedo
            // xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ConsultarEmailsVO consultarEmailsVO = ConsultarEmailsVODocument.Factory.parse(xmlOUT).getConsultarEmailsVO();

            return consultarEmailsVO;
        } catch (XmlException ex) {
            log.error("XmlException - ManterAgendamentoVIPFacadeImpl:consultaEmail(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterAgendamentoVIPFacadeImpl:consultaEmail", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterAgendamentoVIPFacadeImpl:consultaEmail(" + user + ") - [" + ex.getMessage() + "]");
            throw (ex);

        } catch (Exception ex) {
            log.error("Exception - ManterAgendamentoVIPFacadeImpl:consultaEmail(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
