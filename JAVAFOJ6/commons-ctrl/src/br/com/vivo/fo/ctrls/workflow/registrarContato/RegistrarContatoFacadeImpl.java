package br.com.vivo.fo.ctrls.workflow.registrarContato;

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
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.cliente.vo.TipoComunicacaoVODocument.TipoComunicacaoVO;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoComunicacaoVODocument.AtendimentoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoTipoComunicacaoVODocument.AtendimentoTipoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO;
import br.com.vivo.fo.workflow.vo.ContaVODocument;
import br.com.vivo.fo.workflow.vo.ContaVODocument.ContaVO;
import br.com.vivo.fo.workflow.vo.FechamentoAtendimentoVODocument.FechamentoAtendimentoVO;
import br.com.vivo.fo.workflow.vo.InformeBuscaVODocument.InformeBuscaVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.WFListaContatosVODocument;
import br.com.vivo.fo.workflow.vo.WFListaContatosVODocument.WFListaContatosVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RegistrarContatoFacade", mappedName = "RegistrarContatoFacade")
@Local(RegistrarContatoFacade.class)
@Session(ejbName = "RegistrarContatoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RegistrarContatoFacadeImpl implements RegistrarContatoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.RouterService.Router routerTuxControl;

	private static Logger log = new Logger("workflow");

	/**
	 * @common:operation
	 */
	public AdmContatoFolhaVO carregaArvoreContato(String user, AtendimentoArvoreFiltrosVO atendimentoArvoreFiltrosVO) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:carregaArvoreContato("+user+", "+atendimentoArvoreFiltrosVO+")");
			
			if (atendimentoArvoreFiltrosVO != null) {
			    xmlIN = atendimentoArvoreFiltrosVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			} else {
			    xmlIN = ConstantesCRM.SVAZIO;
			}

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "WFOBTARVOCONT", xmlIN.toString()));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			return AdmContatoFolhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAdmContatoFolhaVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:carregaArvoreContato(" + user + ", " + atendimentoArvoreFiltrosVO + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:carregaArvoreContato(" + user + ", " + atendimentoArvoreFiltrosVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoVO verificaProcessosCorrentes(String user, long idChamadaTelefonica) throws FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:verificaProcessosCorrentes(" + user + "," + idChamadaTelefonica + ")");
			xmlIN = "<idChamadaTelefonica>" + idChamadaTelefonica + "</idChamadaTelefonica>";

			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDPQCHAATDUS", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO();

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:verificaProcessosCorrentes(" + user + ", " + idChamadaTelefonica + ") - [" + ex.getMessage() + "]");
			return AtendimentoVO.Factory.newInstance();
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoVO consultarContato(String user, int idContato, long idPessoaDePara, int idFaseProcesso, String idTipoLinha, String idLinha, String idUFOperadora, String nrTelefone, int indAbertura, String idGrupo, String inTipoPessoa, String idTipoCarteira, String idSegmentacao, String idTipoRelacionamento, String idPessoa, String idConta, boolean inPreview) throws TuxedoException, FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:consultarContato(" + user + ", " + idContato + ", " + idPessoaDePara + ", " + idFaseProcesso + ", " + idTipoLinha + ", " + idUFOperadora + ")");

			StringBuffer xmlIn = new StringBuffer();
			xmlIn.append("<idContato>").append(idContato).append("</idContato>");
			xmlIn.append("<inPreview>").append(inPreview ? "1" : "0").append("</inPreview>");
			xmlIn.append("<idLinha>").append(idLinha).append("</idLinha>");
			xmlIn.append("<idPessoaDePara>").append(idPessoaDePara).append("</idPessoaDePara>");
			xmlIn.append("<idPessoa>").append(idPessoa).append("</idPessoa>");
			xmlIn.append("<idUFOperadora>").append(idUFOperadora).append("</idUFOperadora>");
			xmlIn.append("<idTipoLinha>").append(idTipoLinha).append("</idTipoLinha>");
			xmlIn.append("<idFaseProcesso>").append(idFaseProcesso).append("</idFaseProcesso>");
			xmlIn.append("<nrTelefone>").append(nrTelefone).append("</nrTelefone>");
			xmlIn.append("<indAbertura>").append(indAbertura).append("</indAbertura>");
			xmlIn.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
			xmlIn.append("<inTipoPessoa>").append(inTipoPessoa).append("</inTipoPessoa>");
			xmlIn.append("<idTipoCarteira>").append(idTipoCarteira).append("</idTipoCarteira>");
			xmlIn.append("<idSegmentacao>").append(idSegmentacao).append("</idSegmentacao>");
			xmlIn.append("<idTipoRelacionamento>").append(idTipoRelacionamento).append("</idTipoRelacionamento>");
			xmlIn.append("<idConta>").append(idConta).append("</idConta>");
			
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "ATDCLKFOLHA", xmlIn.toString()));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			AtendimentoVO atendVO = AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO();

			// caso seja uma insistência
			if (atendVO.getAtendimentoSituacaoVO() != null && ConstantesCRM.SVAZIO.equals(atendVO.getAtendimentoSituacaoVO().getQtDias())) {
				return atendVO;
			}
			
			return atendVO;

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:consultarContato(" + user + ", " + idContato + ", " + idPessoaDePara + ", " + idFaseProcesso + ", " + idTipoLinha + ", " + idUFOperadora + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:consultarContato(" + user + ", " + idContato + ", " + idPessoaDePara + ", " + idFaseProcesso + ", " + idTipoLinha + ", " + idUFOperadora + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public FormularioCampoVO obtemPesquisaFormulario(String user, String textoPesquisa, String idCampo) throws FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:obtemPesquisaFormulario(" + user + ", " + textoPesquisa + ", " + idCampo + ")");
			StringBuffer xmlIN = new StringBuffer("<idCampo>" + idCampo + "</idCampo>").append("<textoPesquisa>%" + textoPesquisa.toUpperCase() + "%</textoPesquisa>");
			
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFOBTCAMPODIN", xmlIN.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			return FormularioCampoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getFormularioCampoVO();
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemPesquisaFormulario(" + user + ", " + textoPesquisa + ", " + textoPesquisa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoVO obtemComunicacao(String idPessoa, String idTipoComunicacao, String user) throws FacadeException {
		// método que verifica se há processos correntes quando a pessoa mudar de aba na tela inicial
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:obtemComunicacao(" + user + "," + idTipoComunicacao + "," + idPessoa + ")");
			
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idTipoComunicacao>" + idTipoComunicacao + "</idTipoComunicacao>");
			xmlIN.append("<idPessoa>" + idPessoa + "</idPessoa>");
			
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFPSQPESTPCOM",xmlIN.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			return AtendimentoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoVO();
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemComunicacao(" + user + ", " + idTipoComunicacao + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			return AtendimentoVO.Factory.newInstance();
		}
	}

	/**
	 * @common:operation
	 */
	public LupaClienteVO obtemComunicacaoPessoa(String idPessoa, String user) throws FacadeException {
		// método que verifica se há processos correntes quando a pessoa mudar de aba na tela inicial
        String xmlOUT = "";
		try {
		    StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idTipoComunicacao>0</idTipoComunicacao>");
			xmlIN.append("<idPessoa>" + idPessoa + "</idPessoa>");
			
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFPSQPESTPCOM", xmlIN.toString()));

            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
            return LupaClienteVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getLupaClienteVO();

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemComunicacaoPessoa(" + user + ", " + idPessoa + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public TipoComunicacaoVO getIdTipoComunicacaoBySG(String user, String sgTipoComunicacao) throws FacadeException {
        String xmlIN  = "";
        String xmlOUT = "";
		try {
			xmlIN = XmlManager.MakeXmlIn(user, "WFPSQPESTPCOM", "<sgTipoComunicacao>" + sgTipoComunicacao + "</sgTipoComunicacao>");

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			ListaTipoComunicacaoVODocument msg = ListaTipoComunicacaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText());

			return msg.getListaTipoComunicacaoVO().getTipoComunicacaoVOArray(0);
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:getIdTipoComunicacaoBySG(" + user + ", String sgTipoComunicacao) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ListaTipoComunicacaoVO obtemTipoComunicacaoAtendimento(String user) throws FacadeException {
		// método que verifica se há processos correntes quando a pessoa mudar de aba na tela inicial
        String xmlOUT = "";
		try {
		    StringBuffer xmlIN = new StringBuffer("<idTipoComunicacao>0</idTipoComunicacao>").append("<idPessoa>0</idPessoa>");
			
		    xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFPSQPESTPCOM",xmlIN.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			
			return ListaTipoComunicacaoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getListaTipoComunicacaoVO();
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemTipoComunicacaoAtendimento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ContaVO obterLinhas(String user, String idConta, String idLinha) throws FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:obterLinhas(" + user + ", " + idConta + ")");
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idConta>").append(idConta).append("</idConta>");
			xmlIN.append("<idLinha>").append(idLinha).append("</idLinha>");
			
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"WFPSQLINHAS",xmlIN.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);

			return ContaVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getContaVO();
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obterLinhas(" + user + ", " + idConta + ") - [" + ex.getMessage() + "]");
			return ContaVO.Factory.newInstance();
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoVO obtemTipoComunicacao(String user, String idPessoaDePara) throws FacadeException {
		// esse método é chamado quando a pessoa altera o cutomer profileda aba contato
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:obtemTipoComunicacao(" + user + ", " + Long.parseLong(idPessoaDePara) + ")");
			StringBuffer xmlIN = new StringBuffer("<idPessoaDePara>").append(Long.parseLong(idPessoaDePara)).append("</idPessoaDePara>");
			
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDBUSCACOMCL",xmlIN.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			AtendimentoVO atendVO = AtendimentoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getAtendimentoVO();
			PessoaVO pessoaVO = atendVO.getPessoaVO();
			for (int i = 0, lengthTC = pessoaVO.getAtendimentoTipoComunicacaoVOArray().length; i < lengthTC; i++) {
			    AtendimentoTipoComunicacaoVO atendimentoTipoComunicacaoVO = pessoaVO.getAtendimentoTipoComunicacaoVOArray(i);
				for (int j = 0, lengthC = atendimentoTipoComunicacaoVO.getAtendimentoComunicacaoVOArray().length; j < lengthC; j++) {
				    AtendimentoComunicacaoVO atendimentoComunicacaoVO = atendimentoTipoComunicacaoVO.getAtendimentoComunicacaoVOArray(j);
				    StringBuffer dsAuxiliar = new StringBuffer();
					dsAuxiliar.append(atendimentoTipoComunicacaoVO.getIdTipoComunicacao()).append(",");
					dsAuxiliar.append(atendimentoComunicacaoVO.getIdComunicacao()).append(",");
					dsAuxiliar.append(atendimentoComunicacaoVO.getDescricao());
					atendimentoComunicacaoVO.setDsAuxiliar(dsAuxiliar.toString());
				}
			}
			
			return atendVO;
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemTipoComunicacao(" + user + ", " + Long.parseLong(idPessoaDePara) + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoVO registrarAtendimento(String user, AtendimentoVO atendimentoVO) throws TuxedoException, FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:registrarAtendimento(" + user + ", " + atendimentoVO + ")");
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<!-- Dados para registro do Atendimento -->");
			xmlIN.append(atendimentoVO.xmlText().replaceAll("vo:", ""));

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "REGCONTATO", xmlIN.toString()));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO();

		} catch (TuxedoServiceCallerException tex) {
			TuxedoException ex = new TuxedoException(tex);
			if (ex.getXmlHeader().getStatusCode().equals("86E0001")) {
				return AtendimentoVO.Factory.newInstance();
			
			} else {
				log.error("TuxedoException - RegistrarContatoFacadeImpl:registrarAtendimento(" + user + ", " + atendimentoVO + ") - [" + ex.getMessage() + "]");
				throw new TuxedoException(ex);
			}

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:registrarAtendimento(" + user + ", " + atendimentoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void registrarInsistencia(String user, AtendimentoVO atendimentoVO) throws TuxedoException, FacadeException {
		try {
			log.debug("RegistrarContatoFacadeImpl:registrarInsistencia(" + user + ", " + atendimentoVO + ")");
			
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idContato>1</idContato>");
			xmlIN.append("<qtInsistencia>0</qtInsistencia>");
			xmlIN.append("<inAlarme>0</inAlarme>");
			xmlIN.append("<idCanal>1</idCanal>");
			xmlIN.append("<idProcedencia>1</idProcedencia>");
			xmlIN.append("<idTipoCarteira>1</idTipoCarteira>");
			xmlIN.append("<idSegmentacao>1</idSegmentacao>");
			xmlIN.append("<idDiscador>0</idDiscador>");
			xmlIN.append("<idUsuarioAlteracao>0</idUsuarioAlteracao>");
			xmlIN.append("<iPrazoLimite>15</iPrazoLimite>");
			xmlIN.append(atendimentoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_ABERTURA_GRAVAR_INSISTENCIA, xmlIN.toString());

		} catch (TuxedoException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:registrarInsistencia(" + user + ", " + atendimentoVO + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:registrarInsistencia(" + user + ", " + atendimentoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void enviarProcessosCorrentes(String user, String idChamadaTelefonica, String idGrauSatisfacao) throws FacadeException {
		// envia os processos correntes para o pooling quando a ligação é finalizada
	    String xmlIN = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:enviarProcessosCorrentes(" + user + ", " + idChamadaTelefonica + ")");
			xmlIN = TuxedoServiceBridge.getXMLRequest(user, "INCLCHAMTEL", "<idChamadaTelefonica>" + idChamadaTelefonica + "</idChamadaTelefonica><idGrauSatisfacao>" + idGrauSatisfacao + "</idGrauSatisfacao>");

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:enviarProcessosCorrentes(" + user + ", " + idChamadaTelefonica + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmFolhaBaixaVO obtemArvoreBaixa(String user, int idContato, int idTipoComunicacao) throws TuxedoException, FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:obtemArvoreBaixa(" + user + ", " + idContato + ")");

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "BxaListar", ConstantesCRM.SVAZIO));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			return AdmFolhaBaixaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAdmFolhaBaixaVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:obtemArvoreBaixa(" + user + ", " + idContato + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemArvoreBaixa(" + user + ", " + idContato + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ChamadaTelefonicaVO obtemChamadaTelefonica(String user, int idGrauSatisfacao) throws TuxedoException, FacadeException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:obtemChamadaTelefonica(" + user + ", " + idGrauSatisfacao + ")");
			StringBuffer xmlIN = new StringBuffer("<idGrauSatisfacao>").append(idGrauSatisfacao).append("</idGrauSatisfacao>");
			
			xmlOUT = routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_ABERTURA_CHAMADA_TELEFONICA, xmlIN.toString());

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);
			return ChamadaTelefonicaVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getChamadaTelefonicaVO();

		} catch (TuxedoException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:obtemChamadaTelefonica(" + user + ", " + idGrauSatisfacao + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:obtemChamadaTelefonica(" + user + ", " + idGrauSatisfacao + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void registrarContatoSenhaIncrgContato(String user, String nrLinha, String cdAreaRegistro, String idTipoRelacionamento, String idCanal, String cdContato) {
		try {
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<nrLinha>").append(nrLinha).append("</nrLinha>");
			xmlIN.append("<cdAreaRegistro>").append(cdAreaRegistro).append("</cdAreaRegistro>");
			xmlIN.append("<idTipoRelacionamento>").append(idTipoRelacionamento).append("</idTipoRelacionamento>");
			xmlIN.append("<cdContato>").append(cdContato).append("</cdContato>");
			xmlIN.append("<idCanal>").append(idCanal).append("</idCanal>");
			xmlIN.append("<inRegistrarContato>1</inRegistrarContato>");

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "INCRGCONTATO", xmlIN.toString()));

		} catch (Exception e) {
			log.error("registrarContatoSenhaIncrgContato::Erro ao tentar registrar contato.", e);
		}
	}

	/**
	 * @common:operation
	 */
	public void registrarSolicitacaoSenha(String user, String idGrupoAbertura, String inResponsavelAbertura, String ddd, String nrLinha, String tpOperacao, String idProcedencia, String idCanal) throws TuxedoException, FacadeException {
		try {
			if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
				nrLinha = nrLinha.substring(2);
			}
			
			StringBuffer xmlIN = new StringBuffer();
			xmlIN.append("<idGrupoAbertura>").append(idGrupoAbertura).append("</idGrupoAbertura>");
			xmlIN.append("<inResponsavelAbertura>").append(inResponsavelAbertura).append("</inResponsavelAbertura>");
			xmlIN.append("<ddd>").append(ddd).append("</ddd>");
			xmlIN.append("<nrLinha>").append(nrLinha).append("</nrLinha>");
			xmlIN.append("<tpOperacao>").append(tpOperacao).append("</tpOperacao>");
			xmlIN.append("<ProcedenciaVO>");
			xmlIN.append("<idProcedencia>").append(idProcedencia).append("</idProcedencia>");
			xmlIN.append("</ProcedenciaVO>");
			xmlIN.append("<CanalVO>");
			xmlIN.append("<idCanal>").append(idCanal).append("</idCanal>");
			xmlIN.append("</CanalVO>");
			xmlIN.append("<ArvoreAtendimentoVO>");
			xmlIN.append("<cdContato>ContatoSolicitarSenha</cdContato>");
			xmlIN.append("</ArvoreAtendimentoVO>");

			log.debug("RegistrarContatoFacadeImpl:registrarSolicitacaoSenha(" + user + ")");

			tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "REGCONTATOFO", xmlIN.toString()));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:registrarSolicitacaoSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		
		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:registrarSolicitacaoSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AtendimentoVO fechamentoProcesso(String user, FechamentoAtendimentoVO fechamentoAtendimentoVO) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:fechamentoProcesso(" + user + "," + fechamentoAtendimentoVO + ")");
			xmlIN = fechamentoAtendimentoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("vo1:", ConstantesCRM.SVAZIO);
			
			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "REGCONTATO", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AtendimentoVODocument.Factory.parse(xmlOUT).getAtendimentoVO();
		
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:fechamentoProcesso(" + user + "," + fechamentoAtendimentoVO + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:fechamentoProcesso(" + user + "," + fechamentoAtendimentoVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void excluirProcessoCorrente(String user, String idAtendimento) throws TuxedoException, FacadeException {
		try {
			log.debug("RegistrarContatoFacadeImpl:excluirProcessoCorrente(" + user + ", " + idAtendimento + ")");
			StringBuffer xmlIN = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

			routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_ABERTURA_EXCLUIR_PROCESSO, xmlIN.toString());

		} catch (TuxedoException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:excluirProcessoCorrente(" + user + ", " + idAtendimento + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:excluirProcessoCorrente(" + user + ", " + idAtendimento + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFListaContatosVO getListaPesquisaContatos(String user, InformeBuscaVO informe) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:fechamentoProcesso(" + user + "," + informe + ")");
			xmlIN = informe.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "WFATDPSQNMCTO", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFListaContatosVODocument.Factory.parse(xmlOUT).getWFListaContatosVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:fechamentoProcesso(" + user + "," + informe + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:fechamentoProcesso(" + user + "," + informe + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ListaDadosVO getConsultaConta(String user, String tpOperacao, String idPessoa, String nrValor) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:getConsultaConta(" + user + ")");
			xmlIN = "<tpOperacao>" + tpOperacao + "</tpOperacao>";
			// Consulta das Contas
			xmlIN += ConstantesCRM.SZERO.equals(tpOperacao) ? "<idPessoa>" + idPessoa + "</idPessoa>" : ConstantesCRM.SVAZIO;
			// Consulta das Linhas
			xmlIN += ConstantesCRM.SONE.equals(tpOperacao) ? "<cdConta>" + nrValor + "</cdConta>" : ConstantesCRM.SVAZIO;
			// Consulta da Conta pela Linha
			xmlIN += ConstantesCRM.STWO.equals(tpOperacao) ? "<idPessoa>" + idPessoa + "</idPessoa><nrLinha>" + nrValor + "</nrLinha>" : ConstantesCRM.SVAZIO;

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "CONSULTACONTA", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return ListaDadosVODocument.Factory.parse(xmlOUT).getListaDadosVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:getConsultaConta(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:getConsultaConta(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ListaDadosVO getConsultaContaEx(String user, String tpOperacao, String idPessoa, String nrValor) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			log.debug("RegistrarContatoFacadeImpl:getConsultaContaEx(" + user + ")");
			xmlIN = "<tpOperacao>" + tpOperacao + "</tpOperacao>";
			// Consulta das Contas
			xmlIN += ConstantesCRM.SZERO.equals(tpOperacao) ? "<idPessoa>" + idPessoa + "</idPessoa>" : ConstantesCRM.SVAZIO;
			// Consulta das Linhas
			xmlIN += ConstantesCRM.SONE.equals(tpOperacao) ? "<cdConta>" + nrValor + "</cdConta>" : ConstantesCRM.SVAZIO;
			// Consulta da Conta pela Linha
			xmlIN += ConstantesCRM.STWO.equals(tpOperacao) ? "<idPessoa>" + idPessoa + "</idPessoa><nrLinha>" + nrValor + "</nrLinha>" : ConstantesCRM.SVAZIO;

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "CONSULTACTAEX", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			return ListaDadosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaDadosVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RegistrarContatoFacadeImpl:getConsultaContaEx(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RegistrarContatoFacadeImpl:getConsultaContaEx(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

}