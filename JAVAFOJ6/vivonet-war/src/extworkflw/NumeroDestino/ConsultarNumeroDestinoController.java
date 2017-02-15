package extworkflw.NumeroDestino;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;

import com.indracompany.actions.AbstractAction;
import com.indracompany.ws.consultanumeroservice.ConsultarNumeroDestinoSoapProxy;
import com.indracompany.ws.consultanumeroservice.to.output.MensagemCorpoHistoricoCdStatus;

import commons.errors.AjaxError;

@SuppressWarnings({"deprecation"})
public class ConsultarNumeroDestinoController extends AbstractAction {

	private static final long serialVersionUID = -2302585257506931290L;


	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	private ConsultarNumeroForm consultarNumeroForm;
	
	public final String  PARAM_WSEP_CONSULTA_NUMERO  = "WSEP_CONSULTA_NUMERO";
	private String user = null;

	public ConsultarNumeroForm getConsultarNumeroForm() {
		if (this.consultarNumeroForm == null) {
			this.consultarNumeroForm = new ConsultarNumeroForm();
		}
		return this.consultarNumeroForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 */
	public ActionForward pesquisarNumero(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			ConsultarNumeroForm form = (ConsultarNumeroForm) formParam;
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			ConsultarNumeroForm.HistoricoLinha[] listaHistorico = new ConsultarNumeroForm.HistoricoLinha[0];

			com.indracompany.ws.consultanumeroservice.to.input.MensagemCabecalho cabecalhoIn = new com.indracompany.ws.consultanumeroservice.to.input.MensagemCabecalho();
			cabecalhoIn.setCdTranst("URCN");
			cabecalhoIn.setCdUsuar(new BigInteger("11"));

			com.indracompany.ws.consultanumeroservice.to.input.MensagemCorpo corpoIn = new com.indracompany.ws.consultanumeroservice.to.input.MensagemCorpo();
			corpoIn.setCdDDD(form.getNrTelefone().replaceAll("[()-]", ConstantesCRM.SVAZIO).substring(0,2));
			corpoIn.setNumTelf(form.getNrTelefone().replaceAll("[()-]", ConstantesCRM.SVAZIO).substring(2));
			corpoIn.setRetHist(com.indracompany.ws.consultanumeroservice.to.input.MensagemCorpoRetHist.S);

			com.indracompany.ws.consultanumeroservice.to.input.Mensagem mensagemIn = new com.indracompany.ws.consultanumeroservice.to.input.Mensagem();
			mensagemIn.setCabecalho(cabecalhoIn);
			mensagemIn.setCorpo(corpoIn);

			ParametroVO parametro;
			try {
				parametro = GetParametro.getInstace().getParametroVO(user, PARAM_WSEP_CONSULTA_NUMERO);
			} catch (Exception e) {
				parametro = ParametroVO.Factory.newInstance();
			}

			String  endPoint = parametro.getDsValorParametro();
			ConsultarNumeroDestinoSoapProxy proxy = new ConsultarNumeroDestinoSoapProxy(); 
			proxy.setEndpoint(endPoint);
			
			com.indracompany.ws.consultanumeroservice.to.output.Mensagem pesquisaNumero = 	proxy.consultarNumeroDestinoEntrada(mensagemIn);

			// Verificação de retorno de acesso a Web Service
			switch (pesquisaNumero.getCorpo().getStatCom().intValue()) {
			case 1:
				getConsultarNumeroForm().setInErro(false);
				break;
			case 2:
				getConsultarNumeroForm().setInErro(true);
				getConsultarNumeroForm().setMsgRetorno("Ocorreu um erro no acesso às informações: " +
						getMensagemRetorno(pesquisaNumero.getCorpo().getCodiRet().intValue()));
				break;
			case 3:
				getConsultarNumeroForm().setInErro(true);
				getConsultarNumeroForm().setMsgRetorno("Sistema indisponível no momento. Por favor, tente mais tarde. \\n " +
						getMensagemRetorno(pesquisaNumero.getCorpo().getCodiRet().intValue()));
				break;
			}

			if (!getConsultarNumeroForm().getInErro()) {

				// Pesquisa por data (não utilizado atualmente (funcionalidade removida da documentação)
				// Arquivo: indexPeriodo.jsp)
				if (ConstantesCRM.SVAZIO.equals(form.getTipoConsulta())) {

					Date dataInicio = new Date();
					Date dataFim = new Date();
					Date data = new Date();
					dataInicio = formatter.parse(form.getDtInicio());
					dataFim = formatter.parse(form.getDtFim());

					// Verificação de itens de histórico que estejam no período solicitado
					int contador = 0;
					for (int i = 0; i < pesquisaNumero.getCorpo().getHistorico().length; i++) {
						data = formatter.parse(pesquisaNumero.getCorpo().getHistorico()[i].getData());
						if (data.after(dataInicio) && data.before(dataFim)) {
							contador++;
						}
					}

					// Formatação de dados para apresentação em tela
					listaHistorico = new ConsultarNumeroForm.HistoricoLinha[contador];
					for (int i = 0; i < listaHistorico.length; i++) {
						data = formatter.parse(pesquisaNumero.getCorpo().getHistorico()[i].getData());
						if (data.after(dataInicio) && data.before(dataFim)) {
							ConsultarNumeroForm cnf = getConsultarNumeroForm();
							listaHistorico[i] = cnf.new HistoricoLinha();
							listaHistorico[i].setData(pesquisaNumero.getCorpo().getHistorico()[i].getData());
							
							MensagemCorpoHistoricoCdStatus cdStatus = pesquisaNumero.getCorpo().getHistorico()[i].getCdStatus();
							
							listaHistorico[i].setDsStatus(cdStatus.getValue().intValue() == 1 ? "VIVO" : "NÃO VIVO");
						}
					}
					getConsultarNumeroForm().setHistoricoLinha(listaHistorico);

				} else {

					ConsultarNumeroForm cnf = getConsultarNumeroForm();
					int arrayLength = (pesquisaNumero.getCorpo().getHistorico() != null) ?
							pesquisaNumero.getCorpo().getHistorico().length + 1 :
								1;
							listaHistorico = new ConsultarNumeroForm.HistoricoLinha[arrayLength];

							Date dataAtual = new Date();
							listaHistorico[0] = cnf.new HistoricoLinha();
							listaHistorico[0].setNrLinha(form.getNrTelefone());
							listaHistorico[0].setData(formatter.format(dataAtual));
							
							listaHistorico[0].setDsStatus(
									pesquisaNumero.getCorpo().getCdOper().intValue() == 320 ? "VIVO" : "NÃO VIVO"
							);

							// Formatação de dados para apresentação em tela
							if (pesquisaNumero.getCorpo().getHistorico() != null) {

								for (int i = 1; i <= pesquisaNumero.getCorpo().getHistorico().length; i++) {
									listaHistorico[i] = cnf.new HistoricoLinha();
									listaHistorico[i].setNrLinha(form.getNrTelefone());
									listaHistorico[i].setData(formatarStringData(pesquisaNumero.getCorpo().getHistorico()[i-1].getData()));
									
									MensagemCorpoHistoricoCdStatus cdStatus = pesquisaNumero.getCorpo().getHistorico()[i-1].getCdStatus();
									
									listaHistorico[i].setDsStatus(
											pesquisaNumero.getCorpo().getHistorico()[i-1].getCdStatus().getValue().intValue() == 1 ? "VIVO" : "NÃO VIVO"
									);
								}
							}

							getConsultarNumeroForm().setHistoricoLinha(listaHistorico);

				}
				registrarPalitagem(null, form.getNrTelefone().replaceAll("[()-]", ConstantesCRM.SVAZIO), form.getIdContato(), ConstantesCRM.SVAZIO, request);
			}

		} catch(Exception e) {
			response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
			response.setHeader("stackTrace", AjaxError.getStackTrace(e));
			return null;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String formatarStringData(String data) {
		String[] strData = data.split("\\/");
		if (strData[0].length() == 1) {
			strData[0] = "0" + strData[0];
		}
		if (strData[1].length() == 1) {
			strData[1] = "0" + strData[1];
		}
		return strData[0] + "/" + strData[1] + "/" + strData[2];
	}

	private String registrarPalitagem(String idLinha, String nrLinha, int idContato, String dsObservacao, HttpServletRequest request){

		String user                     = ConstantesCRM.getCurrentUser(request.getSession());

		AtendimentoVO atendimentoVO     = AtendimentoVO.Factory.newInstance();
		AtendimentoVO retornoRegContato = AtendimentoVO.Factory.newInstance();
		ParametrosVO parametros         = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);

		atendimentoVO.setIdLinhaAtendimento((parametros.getIdLinhaAtendimento()!=null&&!parametros.getIdLinhaAtendimento().equals(ConstantesCRM.SZERO))?parametros.getIdLinhaAtendimento():parametros.getIdLinha());
		atendimentoVO.setIdUfOperadora(parametros.getIdUfOperadora() == null || parametros.getIdUfOperadora().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdUfOperadora());
		atendimentoVO.setIdTipoLinha(parametros.getIdTipoLinha() == null || parametros.getIdTipoLinha().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdTipoLinha());
		atendimentoVO.setIdChamadaTelefonica(Long.parseLong(parametros.getIdChamadaTelefonica() == null || parametros.getIdChamadaTelefonica().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdChamadaTelefonica()));
		atendimentoVO.setIdGrupoAbertura(Long.parseLong(parametros.getIdGrupo() == null?ConstantesCRM.SONE:parametros.getIdGrupo()));
		atendimentoVO.setInResponsavelAbertura(parametros.getIdTipoRelacionamento()== null?ConstantesCRM.SONE:parametros.getIdTipoRelacionamento());
		atendimentoVO.setInTipoPessoa(parametros.getInTipoPessoa() == null || parametros.getInTipoPessoa().equals(ConstantesCRM.SVAZIO)?"PF":parametros.getInTipoPessoa());

		if (nrLinha != null && !ConstantesCRM.SVAZIO.equals(nrLinha)) {
			atendimentoVO.setNrTelefone(nrLinha);
		} else {
			atendimentoVO.setNrTelefone(parametros.getNrLinha() == null ? "1100000000" : parametros.getNrLinha());
		}

		atendimentoVO.setTpOperacao(ConstantesCRM.SONE); /* tpOperacao: 1=fechar, 2=encaminhar */
		atendimentoVO.addNewProcedenciaVO().setIdProcedencia(1); /* Procedência: TELEFONE - ID 1 */
		atendimentoVO.addNewCanalVO().setIdCanal(1); /* Canal: CRC - ID 1 */

		if (idLinha != null && !ConstantesCRM.SVAZIO.equals(idLinha)) {
			atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(idLinha));
		} else if (parametros.getIdLinha() != null) {
			if (!ConstantesCRM.SVAZIO.equals(parametros.getIdLinha())) {
				atendimentoVO.addNewContas().addNewContaVO().addNewLinhaVO().setIdPessoaLinhaHistorico(Long.parseLong(parametros.getIdLinha()));
			}
		}
		atendimentoVO.addNewPessoaVO().setIdPessoa(Long.parseLong(parametros.getIdClienteDePara() == null ? "21" : parametros.getIdClienteDePara()));
		atendimentoVO.addNewUsuarioLinhaVO().setIdPessoa(parametros.getIdUsuarioDePara() == null ? "21" : parametros.getIdUsuarioDePara());
		atendimentoVO.addNewArvoreAtendimentoVO().setIdContato(idContato);
		atendimentoVO.getArvoreAtendimentoVO().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(parametros.getIdTipoCarteira() == null || parametros.getIdTipoCarteira().equals(ConstantesCRM.SVAZIO)?ConstantesCRM.SONE:parametros.getIdTipoCarteira()));
		atendimentoVO.getArvoreAtendimentoVO().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt((parametros.getIdSegmentacao() == null || parametros.getIdSegmentacao().equals(ConstantesCRM.SVAZIO))?ConstantesCRM.SONE:parametros.getIdSegmentacao()));
		atendimentoVO.setNrProtocolo(ConstantesCRM.SVAZIO);
		atendimentoVO.setObservacao(dsObservacao);

		try {
			retornoRegContato = registrarContatoFacade.registrarAtendimento(user, atendimentoVO);
		} catch(Exception e) {
			return (e.getMessage());
		}
		return retornoRegContato.getNrProtocolo();
	}

	private String getMensagemRetorno(int codigoRetorno) {

		String msgRetorno = ConstantesCRM.SVAZIO;

		switch (codigoRetorno) {
		case 1:
			msgRetorno = "Número de linha não localizado.";
			break;
		case 2:
			msgRetorno = "Parâmetros de entrada inválidos.";
			break;
		case 3:
			msgRetorno = "Erro no acesso às subrotinas (integrador).";
			break;
		case 4:
			msgRetorno = "Falha na comunicação com integrador.";
			break;
		case 6:
			msgRetorno = "Erro na consulta ao banco de dados.";
			break;
		case 7:
			msgRetorno = "Não existe registro para a consulta solicitada.";
			break;
		case 8:
			msgRetorno = "Erro inesperado.";
			break;
		}
		return msgRetorno;
	}

	public static class ConsultarNumeroForm extends ActionForm {

		private static final long serialVersionUID = -582217238794456283L;

		private String tipoConsulta;
		private String dtInicio;
		private String dtFim;
		private String nrTelefone;
		private int idContato;
		private HistoricoLinha[] historicoLinha;
		private boolean inVivo;
		private boolean inErro;
		private String msgRetorno;

		public class HistoricoLinha {

			public HistoricoLinha(){
				this.data = ConstantesCRM.SVAZIO;
				this.dsStatus = ConstantesCRM.SVAZIO;
				this.nrLinha = ConstantesCRM.SVAZIO;
			}

			private String data;
			private String dsStatus;
			private String nrLinha;

			public String getData() {
				if (this.data == null) {
					this.data = ConstantesCRM.SVAZIO;
				}
				return this.data;
			}

			public void setData(String arg1) {
				this.data = arg1;
			}

			public String getDsStatus() {
				if (this.dsStatus == null) {
					this.dsStatus = ConstantesCRM.SVAZIO;
				}
				return this.dsStatus;
			}

			public void setDsStatus(String arg1) {
				this.dsStatus = arg1;
			}

			public String getNrLinha() {
				if (this.nrLinha == null) {
					this.nrLinha = ConstantesCRM.SVAZIO;
				}
				return this.nrLinha;
			}

			public void setNrLinha(String arg1) {
				this.nrLinha = arg1;
			}

		}

		public String getDtInicio() {
			if (this.dtInicio == null) {
				this.dtInicio = ConstantesCRM.SVAZIO;
			}
			return this.dtInicio;
		}

		public void setDtInicio(String arg1) {
			this.dtInicio = arg1;
		}

		public String getDtFim() {
			if (this.dtFim == null) {
				this.dtFim = ConstantesCRM.SVAZIO;
			}
			return this.dtFim;
		}

		public void setDtFim(String arg1) {
			this.dtFim = arg1;
		}

		public String getNrTelefone() {
			if (this.nrTelefone == null) {
				this.nrTelefone = ConstantesCRM.SVAZIO;
			}
			return this.nrTelefone;
		}

		public void setNrTelefone(String arg1) {
			this.nrTelefone = arg1;
		}

		public boolean getInVivo() {
			return this.inVivo;
		}

		public void setInVivo(boolean arg1) {
			this.inVivo = arg1;
		}

		public boolean getInErro() {
			return this.inErro;
		}

		public void setInErro(boolean arg1) {
			this.inErro = arg1;
		}

		public String getMsgRetorno() {
			if (this.msgRetorno == null) {
				this.msgRetorno = ConstantesCRM.SVAZIO;
			}
			return this.msgRetorno;
		}

		public void setMsgRetorno(String arg1) {
			this.msgRetorno = arg1;
		}

		public HistoricoLinha[] getHistoricoLinha() {
			if (this.historicoLinha == null) {
				this.historicoLinha = new HistoricoLinha[0];
			}
			return this.historicoLinha;
		}

		public void setHistoricoLinha(HistoricoLinha[] arg1) {
			this.historicoLinha = arg1;
		}

		public String getTipoConsulta() {
			if (this.tipoConsulta == null) {
				this.tipoConsulta = ConstantesCRM.SVAZIO;
			}
			return this.tipoConsulta;
		}

		public void setTipoConsulta(String arg1) {
			this.tipoConsulta = arg1;
		}

		public int getIdContato() {
			return this.idContato;
		}

		public void setIdContato(int arg1) {
			this.idContato = arg1;
		}

	}

}