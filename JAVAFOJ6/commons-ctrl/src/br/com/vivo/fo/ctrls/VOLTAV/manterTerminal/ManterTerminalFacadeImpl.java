package br.com.vivo.fo.ctrls.VOLTAV.manterTerminal;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import noNamespace.MsgHeaderVO;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.ListaLojaVODocument;
import br.com.vivo.fo.cliente.vo.ListaLojaVODocument.ListaLojaVO;
import br.com.vivo.fo.cliente.vo.ListaMunicipioVODocument;
import br.com.vivo.fo.cliente.vo.ListaMunicipioVODocument.ListaMunicipioVO;
import br.com.vivo.fo.cliente.vo.ListaUFVODocument;
import br.com.vivo.fo.cliente.vo.ListaUFVODocument.ListaUFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.ListaTerminaisVODocument;
import br.com.vivo.fo.voltav.vo.ListaTerminaisVODocument.ListaTerminaisVO;
import br.com.vivo.fo.voltav.vo.ListaUFOperadoraVODocument;
import br.com.vivo.fo.voltav.vo.ListaUFOperadoraVODocument.ListaUFOperadoraVO;
import br.com.vivo.fo.voltav.vo.ManterTerminalVODocument;
import br.com.vivo.fo.voltav.vo.ManterTerminalVODocument.ManterTerminalVO;
import br.com.vivo.fo.voltav.vo.VOLTAVUFOperadoraVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVUFOperadoraVODocument.VOLTAVUFOperadoraVO;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name="ManterTerminalFacade",mappedName="ManterTerminalFacade")
@Local(ManterTerminalFacade.class)
@Session(ejbName = "ManterTerminalFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS,
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
/**
 * @editor-info:code-gen control-interface="true"
 */
public class ManterTerminalFacadeImpl implements ManterTerminalFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

    private static String _SERVICO = "CADLOJATERM";
    private Logger log = new Logger(ConstantesCRM.SVAZIO);
    static final long serialVersionUID = 1L;

    /**
     * @common:operation
     */
    public void alterarTerminal(String user, ManterTerminalVO terminalVO) throws TuxedoException, FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>alterarTerminal</operacao>");

        sb.append("<idTerminal>").append(StringEscapeUtils.escapeXml(terminalVO.getIdTerminal())).append("</idTerminal>");
        sb.append("<nrTerminal>").append(StringEscapeUtils.escapeXml(terminalVO.getNrTerminal())).append("</nrTerminal>");
        sb.append("<nrIpTerminal>").append(StringEscapeUtils.escapeXml(terminalVO.getNrIpTerminal())).append("</nrIpTerminal>");
        sb.append("<cdLojaOperadoraCartao>").append(StringEscapeUtils.escapeXml(terminalVO.getCdLojaOperadoraCartao())).append("</cdLojaOperadoraCartao>");
        sb.append("<idCor>").append(StringEscapeUtils.escapeXml(terminalVO.getIdCor())).append("</idCor>");
        sb.append("<inLiberadoRecarga>").append(StringEscapeUtils.escapeXml(terminalVO.getInLiberadoRecarga())).append("</inLiberadoRecarga>");
        sb.append("<inLiberadoPagamento>").append(StringEscapeUtils.escapeXml(terminalVO.getInLiberadoPagamento())).append("</inLiberadoPagamento>");
        sb.append("<idPessoaDePara>").append(StringEscapeUtils.escapeXml(terminalVO.getIdPessoaDePara())).append("</idPessoaDePara>");
        sb.append("<nmPessoa>").append(StringEscapeUtils.escapeXml(terminalVO.getNmPessoa())).append("</nmPessoa>");
        sb.append("<idPessoaEndereco>").append(StringEscapeUtils.escapeXml(terminalVO.getIdPessoaEndereco())).append("</idPessoaEndereco>");
        sb.append("<nmMunicipio>").append(StringEscapeUtils.escapeXml(terminalVO.getNmMunicipio())).append("</nmMunicipio>");
        sb.append("<nmLocalidade>").append(StringEscapeUtils.escapeXml(terminalVO.getNmLocalidade())).append("</nmLocalidade>");
        sb.append("<nmBairro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmBairro())).append("</nmBairro>");
        sb.append("<nmTipoLogradouro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmTipoLogradouro())).append("</nmTipoLogradouro>");
        sb.append("<nmTituloLogradouro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmTituloLogradouro())).append("</nmTituloLogradouro>");
        sb.append("<nmLogradouro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmLogradouro())).append("</nmLogradouro>");
        sb.append("<nrEndereco>").append(StringEscapeUtils.escapeXml(terminalVO.getNrEndereco())).append("</nrEndereco>");
        sb.append("<dsEnderecoComplemento>").append(StringEscapeUtils.escapeXml(terminalVO.getDsEnderecoComplemento())).append("</dsEnderecoComplemento>");
        sb.append("<nrCep>").append(StringEscapeUtils.escapeXml(terminalVO.getNrCep())).append("</nrCep>");
        sb.append("<idUF>").append(StringEscapeUtils.escapeXml(terminalVO.getUFVO().getIdUF())).append("</idUF>");
        sb.append("<idUfOperadora>").append(StringEscapeUtils.escapeXml(terminalVO.getIdUfOperadora())).append("</idUfOperadora>");

        try {
            String xmlIN = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

        /*
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:alterarTerminal", e));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + te.getMessage() + "]");
            throw te;
         } catch (TuxedoServiceCallerException tcex) { 
        	TuxedoException ex = new TuxedoException(tcex);
        	log.error("TuxedoException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + ex.getMessage() + "]");
            String cod = ex.getXmlHeader().getStatusCode();
            if ("13E0001".equals(cod)) {
                throw new FacadeException("Número de IP já existente.", ex);
            } else if ("13E0002".equals(cod)) {
                throw new FacadeException("Número de Terminal já existente.", ex);
            } else if ("13E0003".equals(cod)) {
                throw new FacadeException("Já existe um terminal ativo com a funcionalidade de recarga e pagamento para esta loja.", ex);
            }
            throw new FacadeException(ex.getMessage(), ex);
        }*/
            
        } catch (XmlException ex) {
            log.error("XmlException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterTerminalFacadeImpl:alterarTerminal", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void incluirTerminal(String user, ManterTerminalVO terminalVO) throws TuxedoException, FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>incluirTerminal</operacao>");

        sb.append("<idTerminal>").append(StringEscapeUtils.escapeXml(terminalVO.getIdTerminal())).append("</idTerminal>");
        sb.append("<nrTerminal>").append(StringEscapeUtils.escapeXml(terminalVO.getNrTerminal())).append("</nrTerminal>");
        sb.append("<nrIpTerminal>").append(StringEscapeUtils.escapeXml(terminalVO.getNrIpTerminal())).append("</nrIpTerminal>");
        sb.append("<cdLojaOperadoraCartao>").append(StringEscapeUtils.escapeXml(terminalVO.getCdLojaOperadoraCartao())).append("</cdLojaOperadoraCartao>");
        sb.append("<cdSitefSenha>").append("0000").append("</cdSitefSenha>");
        sb.append("<idCor>").append(StringEscapeUtils.escapeXml(terminalVO.getIdCor())).append("</idCor>");
        sb.append("<inLiberadoRecarga>").append(StringEscapeUtils.escapeXml(terminalVO.getInLiberadoRecarga())).append("</inLiberadoRecarga>");
        sb.append("<inLiberadoPagamento>").append(StringEscapeUtils.escapeXml(terminalVO.getInLiberadoPagamento())).append("</inLiberadoPagamento>");
        sb.append("<idPessoaDePara>").append(StringEscapeUtils.escapeXml(terminalVO.getIdPessoaDePara())).append("</idPessoaDePara>");
        sb.append("<nmPessoa>").append(StringEscapeUtils.escapeXml(terminalVO.getNmPessoa())).append("</nmPessoa>");
        sb.append("<idPessoaEndereco>").append(StringEscapeUtils.escapeXml(terminalVO.getIdPessoaEndereco())).append("</idPessoaEndereco>");
        sb.append("<nmMunicipio>").append(StringEscapeUtils.escapeXml(terminalVO.getNmMunicipio())).append("</nmMunicipio>");
        sb.append("<nmLocalidade>").append(StringEscapeUtils.escapeXml(terminalVO.getNmLocalidade())).append("</nmLocalidade>");
        sb.append("<nmBairro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmBairro())).append("</nmBairro>");
        sb.append("<nmTipoLogradouro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmTipoLogradouro())).append("</nmTipoLogradouro>");
        sb.append("<nmTituloLogradouro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmTituloLogradouro())).append("</nmTituloLogradouro>");
        sb.append("<nmLogradouro>").append(StringEscapeUtils.escapeXml(terminalVO.getNmLogradouro())).append("</nmLogradouro>");
        sb.append("<nrEndereco>").append(StringEscapeUtils.escapeXml(terminalVO.getNrEndereco())).append("</nrEndereco>");
        sb.append("<dsEnderecoComplemento>").append(StringEscapeUtils.escapeXml(terminalVO.getDsEnderecoComplemento())).append("</dsEnderecoComplemento>");
        sb.append("<nrCep>").append(StringEscapeUtils.escapeXml(terminalVO.getNrCep())).append("</nrCep>");
        sb.append("<idUF>").append(StringEscapeUtils.escapeXml(terminalVO.getUFVO().getIdUF())).append("</idUF>");
        sb.append("<idUfOperadora>").append(StringEscapeUtils.escapeXml(terminalVO.getIdUfOperadora())).append("</idUfOperadora>");

        try {

            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();
            
            tratarWarningException(msgDocRet.getMsg().getMsgHdr());

        /*
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:incluirTerminal(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:incluirTerminal", e));

        } catch (TuxedoServiceCallerException tex) {
            TuxedoException ex = new TuxedoException(tex);
        	log.error("TuxedoException - ManterTerminalFacadeImpl:incluirTerminal(" + user + ") - [" + ex.getMessage() + "]");

            String cod = ex.getXmlHeader().getStatusCode();
            if ("13E0001".equals(cod)) {
                throw new FacadeException("Número de IP já existente.", ex);
            } else if ("13E0002".equals(cod)) {
                throw new FacadeException("Número de Terminal já existente.", ex);
            } else if ("13E0004".equals(cod)) {
                throw new FacadeException("Já existe um terminal ativo com a funcionalidade de recarga e pagamento para esta loja.", ex);
            }
            throw new FacadeException(ex.getMessage(), ex);
        }
        */
        } catch (XmlException ex) {
            log.error("XmlException - ManterTerminalFacadeImpl:incluirTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterTerminalFacadeImpl:incluirTerminal", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterTerminalFacadeImpl:incluirTerminal(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterTerminalFacadeImpl:incluirTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterTerminalFacadeImpl:incluirTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUFVO obterComboUF(String user) throws FacadeException {
        try {
        	String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, "<operacao>consultarListaUF</operacao>");
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
        	String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            ListaUFVODocument listaDoc = ListaUFVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText());

            return listaDoc.getListaUFVO();

        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:obterComboUF(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:obterComboUF", e));

        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ListaMunicipioVO obterComboMunicipio(String user, String uf) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>consultarMunicipios</operacao>");
        sb.append("<idUF>").append(uf).append("</idUF>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaMunicipioVODocument listaDoc = ListaMunicipioVODocument.Factory.parse(xmlOut);

            return listaDoc.getListaMunicipioVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:obterComboMunicipio(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:obterComboMunicipio", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ListaLojaVO obterComboLoja(String user, String idUF, String nmMunicipio) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>consultarLojas</operacao>");
        sb.append("<idUF>").append(idUF).append("</idUF>");
        sb.append("<nmMunicipio>").append(nmMunicipio).append("</nmMunicipio>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaLojaVODocument listaDoc = ListaLojaVODocument.Factory.parse(xmlOUT);

            return listaDoc.getListaLojaVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:obterComboLoja(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:obterComboLoja", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ListaTerminaisVO obterComboTerminal(String user, String loja) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>consultarTerminais</operacao>");
        sb.append("<idPessoaDePara>").append(loja).append("</idPessoaDePara>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTerminaisVODocument listaDoc = ListaTerminaisVODocument.Factory.parse(xmlOut);

            return listaDoc.getListaTerminaisVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:obterComboTerminal(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:obterComboTerminal", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ManterTerminalVO pesquisarTerminal(String user, String idTerminal, String idPessoaDePara) throws FacadeException {
        StringBuffer sb = new StringBuffer();
        sb.append("<operacao>listaDadosLojaTerminal</operacao>");
        sb.append("<idTerminal>").append(idTerminal).append("</idTerminal>");
        sb.append("<idPessoaDePara>").append(idPessoaDePara).append("</idPessoaDePara>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ManterTerminalVODocument listaDoc = ManterTerminalVODocument.Factory.parse(xmlOut);

            return listaDoc.getManterTerminalVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:pesquisarTerminal(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:pesquisarTerminal", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ListaTerminaisVO pesquisarTerminais(String user, String uf, String municipio, String loja, String terminal, String recarga, String pagamento, String nrPagina) throws FacadeException {
        StringBuffer sb = new StringBuffer();
        sb.append("<operacao>consultarListaTerminais</operacao>");

        sb.append("<idUF>").append(StringEscapeUtils.escapeXml(uf)).append("</idUF>");
        sb.append("<nmMunicipio>").append(StringEscapeUtils.escapeXml(municipio)).append("</nmMunicipio>");
        sb.append("<idPessoaDePara>").append(StringEscapeUtils.escapeXml(loja)).append("</idPessoaDePara>");
        sb.append("<idTerminal>").append(StringEscapeUtils.escapeXml(terminal)).append("</idTerminal>");
        sb.append("<inLiberadoRecarga>").append(StringEscapeUtils.escapeXml(recarga)).append("</inLiberadoRecarga>");
        sb.append("<inLiberadoPagamento>").append(StringEscapeUtils.escapeXml(pagamento)).append("</inLiberadoPagamento>");
        sb.append("<nrPagina>").append(StringEscapeUtils.escapeXml(nrPagina)).append("</nrPagina>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaTerminaisVODocument listaDoc = ListaTerminaisVODocument.Factory.parse(xmlOut);

            return listaDoc.getListaTerminaisVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:pesquisarTerminais(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:pesquisarTerminais", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public void reiniciarSenhaLojista(String user, String idTerminal) throws FacadeException {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>resetSenha</operacao>");
            sb.append("<idTerminal>").append(idTerminal).append("</idTerminal>");

            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:reiniciarSenhaLojista(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:reiniciarSenhaLojista", e));

        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ListaLojaVO pesquisarNomeLoja(String user, String nomeLoja) throws FacadeException {
        StringBuffer sb = new StringBuffer();
        sb.append("<operacao>consultarPesquisaLoja</operacao>");
        sb.append("<nmPessoa>").append(StringEscapeUtils.escapeXml(nomeLoja.toUpperCase())).append("</nmPessoa>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

            ListaLojaVODocument listaDoc = ListaLojaVODocument.Factory.parse(xmlOut);
            return listaDoc.getListaLojaVO();

        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:pesquisarNomeLoja(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:pesquisarNomeLoja", e));

        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public EnderecoVO selecionarEnderecoLoja(String user, String idPessoaDePara) throws FacadeException {
        StringBuffer sb = new StringBuffer();
        sb.append("<operacao>consultarEndereco</operacao>");
        sb.append("<idPessoaDePara>").append(idPessoaDePara).append("</idPessoaDePara>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

            EnderecoVODocument listaDoc = EnderecoVODocument.Factory.parse(xmlOut);
            return listaDoc.getEnderecoVO();

        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:selecionarEnderecoLoja(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:selecionarEnderecoLoja", e));

        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public void excluirTerminal(String user, String idTerminal, String idPessoaDePara, String nmMunicipio, String nmPessoa) throws TuxedoException, FacadeException {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>excluirTerminal</operacao>");
            sb.append("<idTerminal>").append(StringEscapeUtils.escapeXml(idTerminal)).append("</idTerminal>");
            sb.append("<idPessoaDePara>").append(StringEscapeUtils.escapeXml(idPessoaDePara)).append("</idPessoaDePara>");
            sb.append("<nmMunicipio>").append(StringEscapeUtils.escapeXml(nmMunicipio)).append("</nmMunicipio>");
            sb.append("<nmPessoa>").append(StringEscapeUtils.escapeXml(nmPessoa)).append("</nmPessoa>");

            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();
            
            tratarWarningException(msgDocRet.getMsg().getMsgHdr());
/*
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:excluirTerminal(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:excluirTerminal", e));

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterTerminalFacadeImpl:alterarTerminal(" + user + ") - [" + tex.getMessage() + "]");
            TuxedoException ex = new TuxedoException(tex);
            String cod = ex.getXmlHeader().getStatusCode();

            if ("13E0005".equals(cod)) {
                throw new FacadeException("Este terminal não pode ser excluído, pois está configurado para realizar recarga e pagamento! Para excluir deverá alterar a configuração, removendo a opção de recarga e pagamento.", ex);

            } else if ("13E0004".equals(cod)) {
                throw new FacadeException("Este terminal não pode ser excluído, pois existe histórico de recarga e pagamento.", ex);

            } else if ("13E0008".equals(cod)) {
                throw new FacadeException("Existe terminal não pode ser excluído, pois existe histórico de atendimento.");
            }
            throw new FacadeException(ex.getMessage(), ex);
        }
        */
            
        } catch (XmlException ex) {
            log.error("XmlException - ManterTerminalFacadeImpl:excluirTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterTerminalFacadeImpl:excluirTerminal", ex));

        } catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterTerminalFacadeImpl:excluirTerminal(" + user + ") - [" + te.getMessage() + "]");
            throw te;

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterTerminalFacadeImpl:excluirTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterTerminalFacadeImpl:excluirTerminal(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaUFOperadoraVO obterComboUfOperadora(String user) throws FacadeException {
        StringBuffer sb = new StringBuffer();
        sb.append("<operacao>consultarListaUFOperadora</operacao>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

            ListaUFOperadoraVODocument listaDoc = ListaUFOperadoraVODocument.Factory.parse(xmlOut);

            return listaDoc.getListaUFOperadoraVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:obterComboUfOperadora(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:obterComboUfOperadora", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public VOLTAVUFOperadoraVO obterUFOperadora(String user, String uf) throws FacadeException {
        StringBuffer sb = new StringBuffer();
        sb.append("<idUF>").append(uf).append("</idUF>");
        sb.append("<operacao>consultarOperadora</operacao>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

            VOLTAVUFOperadoraVODocument operadoraDoc = VOLTAVUFOperadoraVODocument.Factory.parse(xmlOut);

            return operadoraDoc.getVOLTAVUFOperadoraVO();
        } catch (XmlException e) {
            log.error("XmlException - ManterTerminalFacadeImpl:obterUFOperadora(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: ManterTerminalFacadeImpl:obterUFOperadora", e));
        } catch (TuxedoServiceCallerException e) {
            throw new FacadeException(e.getMessage(), e);
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
