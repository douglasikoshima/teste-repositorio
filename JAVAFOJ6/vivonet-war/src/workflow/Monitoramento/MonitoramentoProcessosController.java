package workflow.Monitoramento;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoResultadoVODocument.MonitoramentoResultadoVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoVODocument.MonitoramentoVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked"})
public class MonitoramentoProcessosController extends AbstractAction {

	private static final long serialVersionUID = 8468321692121472923L;

	private static Logger log = new Logger("workflow");

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade monitoramentoFacade;

	protected global.Global globalApp = new global.Global();

	private MonitoramentoForm monitoramentoForm = null;

	public MonitoramentoForm getMonitoramentoForm() {
		return this.monitoramentoForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {

		} else if ("inicioGeral".equals(mapping.getParameter())) {
			return inicioGeral(mapping, form, request, response);

		} else if ("inicioEstado".equals(mapping.getParameter())) {
			return inicioEstado(mapping, form, request, response);

		} else if ("inicioAtendente".equals(mapping.getParameter())) {
			return inicioAtendente(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * Metodo responsável pela obtenção dos dados para a geração de todos os relatórios, este metódo só deve ser
	 * executado uma única vez, para a geração consistênte de todos os relatórios, garantindo a integridade da
	 * informação a ser apresentada para o usuário da aplicação.
	 */
	private void pesquisar(MonitoramentoForm form, HttpServletRequest request) throws Exception {

		log.debug("MonitoramentoProcessosController:pesquisar.do - Inicio do Metodo]");
		log.debug(new StringBuffer("MonitoramentoProcessosController:pesquisar(").append(form).append(")").toString());

		// Definições
		WFGrupoVO wfGrupoVO = WFGrupoVO.Factory.newInstance();
		MonitoramentoPesquisaVO monitoramentoPesquisaVO = MonitoramentoPesquisaVO.Factory.newInstance();
		MonitoramentoResultadoVO monitoramentoResultadoVO = null;

		// Monta os elementos para pesquisa
		if ((form.getDtInicio() != null) && (form.getDtInicio().length() > 0)) {
			monitoramentoPesquisaVO.setDtInicio(form.getDtInicio());
		}

		if ((form.getDtFim() != null) && (form.getDtFim().length() > 0)) {
			monitoramentoPesquisaVO.setDtFim(form.getDtFim());
		}

		// Monta o grupo se necessário
		if (Integer.parseInt(form.getIdGrupo()) > 0) {
			wfGrupoVO.setIdGrupo(form.getIdGrupo());
			monitoramentoPesquisaVO.setWFGrupoVOArray(new WFGrupoVO[] { wfGrupoVO });
		}

		// Obtem idPessoaUsuario da Sessão
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// Este procedimento deve ser executado 1 unica vez,
		// para a geração dos relatorios consistentes e com uma única chamada do containner ejb
		monitoramentoResultadoVO = monitoramentoFacade.executaPesquisa(user, monitoramentoPesquisaVO);

		// Quarda o processamento para os outros relatórios
		this.monitoramentoForm.setMonitoramentoResultadoVO(monitoramentoResultadoVO);
		log.debug("MonitoramentoProcessosController:pesquisar.do - Fim do Metodo]");
	}

	/**
	 * Action inicial da tela de monitoramento, obtendo os grupos ao qual o usuário tem acesso.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="MonitorarProcessos.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MonitoramentoForm form = (MonitoramentoForm) formParam;

		log.debug("MonitoramentoProcessosController:begin.do - Inicio do Metodo]");

		// Obtem idPessoaUsuario da Sessão
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// Obtem a instancia inicial
		this.monitoramentoForm = form;

		// Monta o procedimento inicial
		this.monitoramentoForm.setMonitoramentoPesquisaVO(monitoramentoFacade.obtemGruposParaMonitoramento(user));

		log.debug("MonitoramentoProcessosController:begin.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Action com a montagem da aba de estatísticas gerais sobre processos do usuário
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="MonitoramentoGeral.jsp"
	 */
	protected ActionForward inicioGeral(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MonitoramentoForm form = (MonitoramentoForm) formParam;

		log.debug("MonitoramentoProcessosController:inicioGeral.do - Inicio do Metodo]");
		log.debug(new StringBuffer("MonitoramentoProcessosController:inicioGeral(").append(form).append(")").toString());

		// LinkedHashMap lhmRelatorioGeral = null;

		RelatorioGeralVO relatorioGeralVO = null;
		MonitoramentoResultadoVO monitoramentoResultadoVO = null;
		MonitoramentoVO monitoramentoVO = null;

		// Efetua a pesquisa caso o cliente solicite
		if (form.getPesquisar() != null && "S".equalsIgnoreCase(form.getPesquisar())) {
			pesquisar(form, request);
		}

		// Obtém o resultado da pesquisa
		monitoramentoResultadoVO = this.monitoramentoForm.getMonitoramentoResultadoVO();

		// Limpa para processamento
		this.monitoramentoForm.setRelatorioGeralVO(null);

		/*******************************************************************************************************************/
		/* Monta a apresentação do relatorio */
		/*******************************************************************************************************************/
		if ((monitoramentoResultadoVO.getMonitoramentoVOArray() != null) && (monitoramentoResultadoVO.getMonitoramentoVOArray().length > 0)) {
			// Zera o acumulador
			int qtdeTotalGeral = 0;

			/**************************************************************************/
			/* 1o - Obtém os grupos + quantidades + total */
			/**************************************************************************/
			LinkedHashMap lhmRelatorioGeral = new LinkedHashMap();
			for (int i = 0; i < monitoramentoResultadoVO.getMonitoramentoVOArray().length; i++) {
				// Obtém o elemento
				monitoramentoVO = monitoramentoResultadoVO.getMonitoramentoVOArray(i);

				// Verifica se existe o registro corrente no hash
				if (!lhmRelatorioGeral.containsKey(monitoramentoVO.getDescricaoGrupo())) {
					relatorioGeralVO = new RelatorioGeralVO();
					relatorioGeralVO.setGrupo(monitoramentoVO.getDescricaoGrupo());

				} else {
					relatorioGeralVO = (RelatorioGeralVO) lhmRelatorioGeral.get(monitoramentoVO.getDescricaoGrupo());
				}

				// Monta a quantidade
				relatorioGeralVO.setQuantidade(relatorioGeralVO.getQuantidade() + monitoramentoVO.getQuantidade());

				// Cálcula o total
				qtdeTotalGeral += monitoramentoVO.getQuantidade();

				// Verifica se existe o registro corrente no hash
				if (!lhmRelatorioGeral.containsKey(monitoramentoVO.getDescricaoGrupo())) {
					lhmRelatorioGeral.put(monitoramentoVO.getDescricaoGrupo(), relatorioGeralVO);
				}
			}

			/**************************************************************************/
			/* 2o - Calcula os percentuais */
			/**************************************************************************/
			int qtdePercentual = 0;
			int contLinha = 0;
			for (Iterator iRelatorioEstadoTopVO = lhmRelatorioGeral.values().iterator(); iRelatorioEstadoTopVO.hasNext();) {
				relatorioGeralVO = (RelatorioGeralVO) iRelatorioEstadoTopVO.next();
				qtdePercentual = (int) Math.round((relatorioGeralVO.getQuantidade() * 100.0) / qtdeTotalGeral);

				relatorioGeralVO.setOrdem(++contLinha); // Monta a ordem da apresentação
				relatorioGeralVO.setPercentual(qtdePercentual); // Monta o percentual do elemento corrente
			}

			/**************************************************************************/
			/* 3o - Monta um registro com o total */
			/**************************************************************************/
			relatorioGeralVO = new RelatorioGeralVO();
			relatorioGeralVO.setOrdem(contLinha + 1);
			relatorioGeralVO.setGrupo("Total Geral");
			relatorioGeralVO.setQuantidade(qtdeTotalGeral);
			relatorioGeralVO.setPercentual(100);

			// Adiciona a linha de total geral
			lhmRelatorioGeral.put("Total Geral", relatorioGeralVO);

			/**************************************************************************/
			/* 4o - Monta dados no formulário para impressão */
			/**************************************************************************/
			this.monitoramentoForm.setRelatorioGeralVO((RelatorioGeralVO[]) lhmRelatorioGeral.values().toArray(new RelatorioGeralVO[] { new RelatorioGeralVO() }));
		}

		log.debug("MonitoramentoProcessosController:inicioGeral.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Action com a montagem da aba de estado dos processos do usuário
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="MonitoramentoEstado.jsp"
	 */
	protected ActionForward inicioEstado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MonitoramentoForm form = (MonitoramentoForm) formParam;

		log.debug("MonitoramentoProcessosController:inicioEstado.do - Inicio do Metodo]");
		// Definições
		int qtdeTotalGeral;

		MonitoramentoResultadoVO monitoramentoResultadoVO = null;
		MonitoramentoVO monitoramentoVO = null;
		RelatorioEstadoTopVO relatorioEstadoTopVO = null;
		RelatorioEstadoBottonVO relatorioEstadoBottonVO = null;

		// Efetua a pesquisa caso o cliente solicite
		if ((form.pesquisar != null) && form.pesquisar.equalsIgnoreCase("S")) {
			pesquisar(form, request);
		}

		// Limpa para processamento
		this.monitoramentoForm.setRelatorioEstadoTopVO(null);
		this.monitoramentoForm.setRelatorioEstadoBottonVO(null);

		/*******************************************************************************************************************/
		/* Monta a apresentação do relatorio */
		/*******************************************************************************************************************/
		// Obtém os dados para montagem do relatório
		monitoramentoResultadoVO = this.monitoramentoForm.getMonitoramentoResultadoVO();

		if ((monitoramentoResultadoVO.getMonitoramentoVOArray() != null) && (monitoramentoResultadoVO.getMonitoramentoVOArray().length > 0)) {
			// Zera o acumulador
			qtdeTotalGeral = 0;

			/*******************************************************************************************************************/
			/* TOP */
			/*******************************************************************************************************************/
			/**************************************************************************/
			/* 1o - Obtém os estado + quantidades + total */
			/**************************************************************************/
			LinkedHashMap lhmRelatorioTop = new LinkedHashMap();
			for (int i = 0; i < monitoramentoResultadoVO.getMonitoramentoVOArray().length; i++) {
				// Obtém o elemento
				monitoramentoVO = monitoramentoResultadoVO.getMonitoramentoVOArray(i);

				// Verifica se existe o registro corrente no hash
				if (!lhmRelatorioTop.containsKey(monitoramentoVO.getDescricaoEstado())) {
					relatorioEstadoTopVO = new RelatorioEstadoTopVO();
					relatorioEstadoTopVO.setEstado(monitoramentoVO.getDescricaoEstado());

				} else {
					relatorioEstadoTopVO = (RelatorioEstadoTopVO) lhmRelatorioTop.get(monitoramentoVO.getDescricaoEstado());
				}

				// Monta a quantidade
				relatorioEstadoTopVO.setQuantidade(relatorioEstadoTopVO.getQuantidade() + monitoramentoVO.getQuantidade());

				// Cálcula o total
				qtdeTotalGeral += monitoramentoVO.getQuantidade();

				// Verifica se existe o registro corrente no hash
				if (!lhmRelatorioTop.containsKey(monitoramentoVO.getDescricaoEstado())) {
					lhmRelatorioTop.put(monitoramentoVO.getDescricaoEstado(), relatorioEstadoTopVO);
				}
			}

			/**************************************************************************/
			/* 2o - Calcula os percentuais */
			/**************************************************************************/
			int qtdePercentual = 0;
			int iteracaoOrdemTop = 0;
			// contLinha = 0;
			for (Iterator iRelatorioEstadoTopVO = lhmRelatorioTop.values().iterator(); iRelatorioEstadoTopVO.hasNext();) {
				relatorioEstadoTopVO = (RelatorioEstadoTopVO) iRelatorioEstadoTopVO.next();
				qtdePercentual = (int) Math.round((relatorioEstadoTopVO.getQuantidade() * 100.0) / qtdeTotalGeral);
				iteracaoOrdemTop++;
				relatorioEstadoTopVO.setOrdem(iteracaoOrdemTop); // Monta a ordem da apresentação
				// relatorioEstadoTopVO.setOrdem( ++contLinha ); //Monta a ordem da apresentação
				relatorioEstadoTopVO.setPercentual(qtdePercentual); // Monta o percentual do elemento corrente
			}

			/**************************************************************************/
			/* 3o - Monta um registro com o total */
			/**************************************************************************/
			relatorioEstadoTopVO = new RelatorioEstadoTopVO();
			relatorioEstadoTopVO.setOrdem(iteracaoOrdemTop + 1);
			// relatorioEstadoTopVO.setOrdem( contLinha + 1 );
			relatorioEstadoTopVO.setEstado("Total Geral");
			relatorioEstadoTopVO.setQuantidade(qtdeTotalGeral);
			relatorioEstadoTopVO.setPercentual(100);

			// Adiciona a linha de total geral
			lhmRelatorioTop.put("Total Geral", relatorioEstadoTopVO);

			/*******************************************************************************************************************/
			/* BOTTON */
			/*******************************************************************************************************************/
			/**********************************************************************/
			/* 4o - Dado o grupo obtém os seus estado + quantidades + total */
			/**********************************************************************/
			LinkedHashMap lhmRelatorioBotton = new LinkedHashMap();
			for (int i = 0; i < monitoramentoResultadoVO.getMonitoramentoVOArray().length; i++) {
				// Obtém os grupos correntes
				monitoramentoVO = monitoramentoResultadoVO.getMonitoramentoVOArray(i);

				// Zera a quantidade para cálcular o grupo + estado
				qtdeTotalGeral = 0;

				// Monta um grupo para a analise dos estados do mesmo
				relatorioEstadoBottonVO = new RelatorioEstadoBottonVO();
				relatorioEstadoBottonVO.setOrdem(i/* +1 */);
				relatorioEstadoBottonVO.setGrupo(monitoramentoVO.getDescricaoGrupo());

				// Obtem os dados para montagem do estado dado o grupo
				monitoramentoResultadoVO = this.monitoramentoForm.getMonitoramentoResultadoVO();
				LinkedHashMap lhmRelatorioEstadoTop = new LinkedHashMap(); // Inicializa o hash de armazenamento que ira
				// prover o resultado final
				relatorioEstadoTopVO = new RelatorioEstadoTopVO(); // Inicializa o elemento de analise do grupo
				for (int j = 0; j < monitoramentoResultadoVO.getMonitoramentoVOArray().length; j++) {
					// Obtém o grupo + estado + quantidade
					monitoramentoVO = monitoramentoResultadoVO.getMonitoramentoVOArray(j);

					// Verifica se o grupo em contLinha é referente ao grupo em contColuna
					if (monitoramentoVO.getDescricaoGrupo().equals(relatorioEstadoBottonVO.getGrupo())) {
						// Verifica se já contém um estado para o grupo corrente
						if (!lhmRelatorioEstadoTop.containsKey(monitoramentoVO.getDescricaoEstado())) {
							relatorioEstadoTopVO = new RelatorioEstadoTopVO();
							relatorioEstadoTopVO.setEstado(monitoramentoVO.getDescricaoEstado());
							// Monta a quantidade
							relatorioEstadoTopVO.setQuantidade(monitoramentoVO.getQuantidade());
						} else {
							relatorioEstadoTopVO = (RelatorioEstadoTopVO) lhmRelatorioEstadoTop.get(monitoramentoVO.getDescricaoEstado());
							// Monta a quantidade
							relatorioEstadoTopVO.setQuantidade(monitoramentoVO.getQuantidade() + relatorioEstadoTopVO.getQuantidade());
						}

						// Cálcula o total
						qtdeTotalGeral += monitoramentoVO.getQuantidade();

						// Verifica se já contém um estado para o grupo corrente
						if (!lhmRelatorioEstadoTop.containsKey(monitoramentoVO.getDescricaoEstado())) {
							lhmRelatorioEstadoTop.put(monitoramentoVO.getDescricaoEstado(), relatorioEstadoTopVO);
						}
					}
				}

				/**************************************************************************/
				/* 5o - Calcula os percentuais do grupo + estado */
				/**************************************************************************/
				qtdePercentual = 0;
				// contLinha = 0;
				int iteracaoOrdemEstadoTop = 0;
				for (Iterator iRelatorioEstadoBottonVO = lhmRelatorioEstadoTop.values().iterator(); iRelatorioEstadoBottonVO.hasNext();) {
					relatorioEstadoTopVO = (RelatorioEstadoTopVO) iRelatorioEstadoBottonVO.next();
					qtdePercentual = (int) Math.round((relatorioEstadoTopVO.getQuantidade() * 100.0) / qtdeTotalGeral);
					iteracaoOrdemEstadoTop++;
					relatorioEstadoTopVO.setOrdem(iteracaoOrdemEstadoTop); // Monta a ordem da apresentação
					// relatorioEstadoTopVO.setOrdem( ++contLinha ); //Monta a ordem da apresentação
					relatorioEstadoTopVO.setPercentual(qtdePercentual); // Monta o percentual do elemento corrente
				}

				/**************************************************************************/
				/* 6o - Monta um registro com o total */
				/**************************************************************************/
				relatorioEstadoTopVO = new RelatorioEstadoTopVO();
				relatorioEstadoTopVO.setOrdem(iteracaoOrdemEstadoTop + 1);
				relatorioEstadoTopVO.setEstado("Total Geral");
				relatorioEstadoTopVO.setQuantidade(qtdeTotalGeral);
				relatorioEstadoTopVO.setPercentual(100);

				// Adiciona a linha de total geral
				lhmRelatorioEstadoTop.put("Total Geral", relatorioEstadoTopVO);

				/**************************************************************************/
				/* 7o - Adiciona a analise no grupo corrente */
				/**************************************************************************/
				relatorioEstadoBottonVO.setRelatorioEstadoTopVO((RelatorioEstadoTopVO[]) lhmRelatorioEstadoTop.values().toArray(new RelatorioEstadoTopVO[] { new RelatorioEstadoTopVO() }));

				// Monta o grupo com os seus estados
				lhmRelatorioBotton.put(relatorioEstadoBottonVO.getGrupo(), relatorioEstadoBottonVO);
			}

			/**************************************************************************/
			/* 8o - Monta dados no formulário para impressão */
			/**************************************************************************/
			this.monitoramentoForm.setRelatorioEstadoTopVO((RelatorioEstadoTopVO[]) lhmRelatorioTop.values().toArray(new RelatorioEstadoTopVO[] { new RelatorioEstadoTopVO() }));
			this.monitoramentoForm.setRelatorioEstadoBottonVO((RelatorioEstadoBottonVO[]) lhmRelatorioBotton.values().toArray(new RelatorioEstadoBottonVO[] { new RelatorioEstadoBottonVO() }));
		}

		log.debug("MonitoramentoProcessosController:inicioGeral.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Action com a montagem da aba de estado dos processos por atendente
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="MonitoramentoAtendente.jsp"
	 */
	protected ActionForward inicioAtendente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MonitoramentoForm form = (MonitoramentoForm) formParam;

		log.debug("MonitoramentoProcessosController:inicioAtendente.do - Inicio do Metodo]");

		// Definições
		int qtdeTotalGeral;

		MonitoramentoResultadoVO monitoramentoResultadoVO = null;
		// MonitoramentoVO monitoramentoVO = null;
		// RelatorioEstadoTopVO relatorioEstadoTopVO = null;
		// RelatorioAtendenteVO relatorioAtendenteVO = null;

		// Efetua a pesquisa caso o cliente solicite
		if ((form.pesquisar != null) && form.pesquisar.equalsIgnoreCase("S")) {
			pesquisar(form, request);
		}

		// Limpa para processamento
		this.monitoramentoForm.setRelatorioAtendenteVO(null);

		/*******************************************************************************************************************/
		/* Monta a apresentação do relatorio */
		/*******************************************************************************************************************/
		// Obtém os dados para montagem do relatório
		monitoramentoResultadoVO = this.monitoramentoForm.getMonitoramentoResultadoVO();

		if ((monitoramentoResultadoVO.getMonitoramentoVOArray() != null) && (monitoramentoResultadoVO.getMonitoramentoVOArray().length > 0)) {
			// Zera o acumulador
			qtdeTotalGeral = 0;

			/************************************************************************/
			/* 1o - Dado o grupo + login obtém os seus estado + quantidades + total */
			/************************************************************************/
			LinkedHashMap lhmRelatorioBotton = new LinkedHashMap();
			MonitoramentoVO monitoramentoVO = null;
			LinkedHashMap lhmRelatorioEstadoTop = null;
			RelatorioEstadoTopVO relatorioEstadoTopVO = null;

			for (int i = 0; i < (monitoramentoResultadoVO.getMonitoramentoVOArray().length); i++) {
				// Obtém os grupos correntes
				monitoramentoVO = monitoramentoResultadoVO.getMonitoramentoVOArray(i);

				// Zera a quantidade para cálcular o grupo + estado
				qtdeTotalGeral = 0;

				// Monta um grupo para a analise dos estados do mesmo
				RelatorioAtendenteVO relatorioAtendenteVO = new RelatorioAtendenteVO();
				relatorioAtendenteVO.setOrdem(i + 1);
				relatorioAtendenteVO.setGrupo(monitoramentoVO.getDescricaoGrupo());
				relatorioAtendenteVO.setLogin(monitoramentoVO.getDescricaoLogin());

				// Obtem os dados para montagem do estado dado o grupo
				monitoramentoResultadoVO = this.monitoramentoForm.getMonitoramentoResultadoVO();
				lhmRelatorioEstadoTop = new LinkedHashMap(); // Inicializa o hash de armazenamento que ira prover o
				// resultado final
				relatorioEstadoTopVO = new RelatorioEstadoTopVO(); // Inicializa o elemento de analise do grupo
				for (int j = 0; j < monitoramentoResultadoVO.getMonitoramentoVOArray().length; j++) {
					// Obtém o grupo + estado + quantidade
					monitoramentoVO = monitoramentoResultadoVO.getMonitoramentoVOArray(j);

					// Verifica se o grupo em contLinha é referente ao grupo em contColuna
					if (monitoramentoVO.getDescricaoGrupo().equals(relatorioAtendenteVO.getGrupo()) && monitoramentoVO.getDescricaoLogin().equals(relatorioAtendenteVO.getLogin())) {
						// Verifica se já contém um estado para o grupo corrente
						if (!lhmRelatorioEstadoTop.containsKey(monitoramentoVO.getDescricaoEstado())) {
							relatorioEstadoTopVO = new RelatorioEstadoTopVO();
							relatorioEstadoTopVO.setEstado(monitoramentoVO.getDescricaoEstado());
						} else {
							relatorioEstadoTopVO = (RelatorioEstadoTopVO) lhmRelatorioBotton.get(monitoramentoVO.getDescricaoEstado());
						}

						// Monta a quantidade
						relatorioEstadoTopVO.setQuantidade(monitoramentoVO.getQuantidade());

						// Cálcula o total
						qtdeTotalGeral += monitoramentoVO.getQuantidade();

						// Verifica se já contém um estado para o grupo corrente
						if (!lhmRelatorioEstadoTop.containsKey(monitoramentoVO.getDescricaoEstado())) {
							lhmRelatorioEstadoTop.put(monitoramentoVO.getDescricaoEstado(), relatorioEstadoTopVO);
						}
					}
				}

				/**************************************************************************/
				/* 2o - Calcula os percentuais do grupo + estado */
				/**************************************************************************/
				int qtdePercentual = 0;
				// contLinha = 0;
				int iteracaoOrdem = 0;
				for (Iterator iRelatorioAtendenteVO = lhmRelatorioEstadoTop.values().iterator(); iRelatorioAtendenteVO.hasNext();) {
					relatorioEstadoTopVO = (RelatorioEstadoTopVO) iRelatorioAtendenteVO.next();
					qtdePercentual = (int) Math.round((relatorioEstadoTopVO.getQuantidade() * 100.0) / qtdeTotalGeral);

					iteracaoOrdem++;
					relatorioEstadoTopVO.setOrdem(iteracaoOrdem); // Monta a ordem da apresentação
					// relatorioEstadoTopVO.setOrdem( ++contLinha ); //Monta a ordem da apresentação
					relatorioEstadoTopVO.setPercentual(qtdePercentual); // Monta o percentual do elemento corrente
				}

				/**************************************************************************/
				/* 3o - Monta um registro com o total */
				/**************************************************************************/
				relatorioEstadoTopVO = new RelatorioEstadoTopVO();
				relatorioEstadoTopVO.setOrdem(iteracaoOrdem + 1);
				// relatorioEstadoTopVO.setOrdem( contLinha + 1 );
				relatorioEstadoTopVO.setEstado("Total Geral");
				relatorioEstadoTopVO.setQuantidade(qtdeTotalGeral);
				relatorioEstadoTopVO.setPercentual(100);

				// Adiciona a linha de total geral
				lhmRelatorioEstadoTop.put("Total Geral", relatorioEstadoTopVO);

				/**************************************************************************/
				/* 4o - Adiciona a analise no grupo corrente */
				/**************************************************************************/
				relatorioAtendenteVO.setRelatorioEstadoTopVO((RelatorioEstadoTopVO[]) lhmRelatorioEstadoTop.values().toArray(new RelatorioEstadoTopVO[] { new RelatorioEstadoTopVO() }));

				// Monta o grupo + atendente com os seus estados
				lhmRelatorioBotton.put(new StringBuffer(relatorioAtendenteVO.getGrupo()).append("-").append(relatorioAtendenteVO.getLogin()).toString(), relatorioAtendenteVO);
			}

			/**************************************************************************/
			/* 5o - Monta dados no formulário para impressão */
			/**************************************************************************/
			this.monitoramentoForm.setRelatorioAtendenteVO((RelatorioAtendenteVO[]) lhmRelatorioBotton.values().toArray(new RelatorioAtendenteVO[] { new RelatorioAtendenteVO() }));
		}
		log.debug("MonitoramentoProcessosController:inicioAtendente.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Interface para a montagem dos relatórios
	 */
	public class RelatorioVO implements Serializable {

		private static final long serialVersionUID = 5692021430815521995L;

		private int ordem = 0;
		private int quantidade = 0;
		private int percentual = 0;

		public void setOrdem(int ordem) {
			this.ordem = ordem;
		}

		public int getOrdem() {
			return this.ordem;
		}

		public void setQuantidade(int quantidade) {
			this.quantidade = quantidade;
		}

		public int getQuantidade() {
			return this.quantidade;
		}

		public void setPercentual(int percentual) {
			this.percentual = percentual;
		}

		public int getPercentual() {
			return this.percentual;
		}
	}

	/**
	 * Representação de 1 linha do relatorio geral
	 */
	public class RelatorioGeralVO extends RelatorioVO {

		private static final long serialVersionUID = -8822319628091259321L;

		private String grupo = null;

		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}

		public String getGrupo() {
			return this.grupo;
		}
	}

	/**
	 * Representação de 1 linha do relatorio estado do processo
	 */
	public class RelatorioEstadoTopVO extends RelatorioVO {

		private static final long serialVersionUID = 7209379201069615552L;

		private String estado = null;

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getEstado() {
			return this.estado;
		}
	}

	/**
	 * Representação de 2 linha do relatorio estado do processo
	 */
	public class RelatorioEstadoBottonVO extends RelatorioVO {

		private static final long serialVersionUID = 1473677450858529L;

		private String grupo = null;
		private RelatorioEstadoTopVO[] relatorioEstadoTopVO = null;

		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}

		public String getGrupo() {
			return this.grupo;
		}

		public void setRelatorioEstadoTopVO(RelatorioEstadoTopVO[] relatorioEstadoTopVO) {
			this.relatorioEstadoTopVO = relatorioEstadoTopVO;
		}

		public RelatorioEstadoTopVO[] getRelatorioEstadoTopVO() {
			return this.relatorioEstadoTopVO;
		}
	}

	public class RelatorioAtendenteVO extends RelatorioVO {

		private static final long serialVersionUID = -5929163101265311902L;

		private String grupo = null;
		private String login = null;
		private RelatorioEstadoTopVO[] relatorioEstadoTopVO = null;

		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}

		public String getGrupo() {
			return this.grupo;
		}

		public void setLogin(String login) {
			this.login = login;
		}

		public String getLogin() {
			return this.login;
		}

		public void setRelatorioEstadoTopVO(RelatorioEstadoTopVO[] relatorioEstadoTopVO) {
			this.relatorioEstadoTopVO = relatorioEstadoTopVO;
		}

		public RelatorioEstadoTopVO[] getRelatorioEstadoTopVO() {
			return this.relatorioEstadoTopVO;
		}
	}

	public static class MonitoramentoForm extends ActionForm {

		private static final long serialVersionUID = -5248105141622802301L;

		private String pesquisar = null;
		private String idGrupo = null;
		private String dtInicio = null;
		private String dtFim = null;
		private MonitoramentoPesquisaVO monitoramentoPesquisaVO = null;
		private MonitoramentoResultadoVO monitoramentoResultadoVO = null;

		private RelatorioVO[] relatorioGeralVO = null;
		private RelatorioVO[] relatorioEstadoTopVO = null;
		private RelatorioVO[] relatorioEstadoBottonVO = null;
		private RelatorioVO[] relatorioAtendenteVO = null;

		public void setPesquisar(String pesquisar) {
			this.pesquisar = pesquisar;
		}

		public String getPesquisar() {
			return this.pesquisar;
		}

		public void setMonitoramentoPesquisaVO(MonitoramentoPesquisaVO monitoramentoPesquisaVO) {
			this.monitoramentoPesquisaVO = monitoramentoPesquisaVO;
		}

		public MonitoramentoPesquisaVO getMonitoramentoPesquisaVO() {
			return this.monitoramentoPesquisaVO;
		}

		public void setMonitoramentoResultadoVO(MonitoramentoResultadoVO monitoramentoResultadoVO) {
			this.monitoramentoResultadoVO = monitoramentoResultadoVO;
		}

		public MonitoramentoResultadoVO getMonitoramentoResultadoVO() {
			return this.monitoramentoResultadoVO;
		}

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		public void setdtInicio(String dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {
			return this.dtInicio;
		}

		public void setDtFim(String dtFim) {
			this.dtFim = dtFim;
		}

		public String getDtFim() {
			return this.dtFim;
		}

		public void setRelatorioGeralVO(RelatorioVO[] relatorioGeralVO) {
			this.relatorioGeralVO = relatorioGeralVO;
		}

		public RelatorioVO[] getRelatorioGeralVO() {
			return this.relatorioGeralVO;
		}

		public void setRelatorioEstadoTopVO(RelatorioVO[] relatorioEstadoTopVO) {
			this.relatorioEstadoTopVO = relatorioEstadoTopVO;
		}

		public RelatorioVO[] getRelatorioEstadoTopVO() {
			return this.relatorioEstadoTopVO;
		}

		public void setRelatorioEstadoBottonVO(RelatorioVO[] relatorioEstadoBottonVO) {
			this.relatorioEstadoBottonVO = relatorioEstadoBottonVO;
		}

		public RelatorioVO[] getRelatorioEstadoBottonVO() {
			return this.relatorioEstadoBottonVO;
		}

		public void setRelatorioAtendenteVO(RelatorioVO[] relatorioAtendenteVO) {
			this.relatorioAtendenteVO = relatorioAtendenteVO;
		}

		public RelatorioVO[] getRelatorioAtendenteVO() {
			return this.relatorioAtendenteVO;
		}
	}
}