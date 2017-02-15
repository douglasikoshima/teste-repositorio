package VOLTAV.quadroAviso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.QuadroAvisoFacade;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Segmento;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.UF;

import com.indracompany.actions.AbstractAction;

public class QuadroAvisoController extends AbstractAction {

	private static final long serialVersionUID = 8505490730828897190L;

	protected global.Global globalApp = new global.Global();
	private String user = null;
	private static transient NonCatalogLogger log = new NonCatalogLogger(QuadroAvisoController.class.getName());

	@EJB
	private QuadroAvisoFacade quadroAvisoFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisaConta".equals(mapping.getParameter())) {
			return pesquisaConta(mapping, form, request, response);
		} else if ("confirmarMensagem".equals(mapping.getParameter())) {
			return confirmarMensagem(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			getQuadroAvisoForm().setSegmentos(quadroAvisoFacade.getSegmentos());
			getQuadroAvisoForm().setUfs(quadroAvisoFacade.getUFs());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisaConta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuadroAvisoForm form = (QuadroAvisoForm) formParam;
		String msg = ConstantesCRM.SVAZIO;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		String query = "select * from customer.conta c, customer.pessoaconta pc, customer.pessoaconsultor pcns, customer.pessoadepara pdp, customer.pessoadocumento pd, " +
		" customer.documento d where pc.idconta = c.idconta and pdp.idpessoadepara = pc.idpessoadepara and pd.idpessoa = pdp.idpessoa and pcns.idpessoa = " + user +
		" and pd.iddocumento = d.iddocumento and d.nrdocumento = pcns.NRDOCUMENTO and c.cdconta = '" + form.getConta() + "'";

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query);

		if (rsVO.getLinhas().getLinhaArray().length > 0) {
			try {
				String query2 = "select distinct pe.iduf, nvl(psh.idsegmentacao,11) from customer.conta c, customer.pessoaconta pc, customer.contaendereco ce, " +
				" customer.pessoaendereco pe, customer.pessoadepara pdp, customer.pessoasegmentacao ps, customer.pessoasegmentacaohistorico psh where c.cdconta = '" + form.getConta() + "'" +
				" and pc.idconta = c.idconta and ce.idconta = c.idconta and ce.idpessoaendereco = pe.idpessoaendereco and pdp.idpessoa = pe.idpessoa and ps.idpessoadepara(+) = pdp.idpessoadepara " +
				" and psh.idpessoasegmentacao(+) = ps.idpessoasegmentacao ";

				Resultset rsVO2 = pesquisar.executar(query2);

				for (int j = 0; j < rsVO2.getLinhas().getLinhaArray().length; j++) {
					Resultset.Linhas.Linha linha = rsVO2.getLinhas().getLinhaArray(j);
					getQuadroAvisoForm().setConta(form.getConta());
					getQuadroAvisoForm().setSegmentacao(linha.getValorArray(1));
					getQuadroAvisoForm().setRegional(linha.getValorArray(0));
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			msg = "Operação não permitida. Você não está associado à conta desse CNPJ. Associe-se ao CNPJ ou selecione outra conta.";
			request.setAttribute("msgStatus", msg);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward confirmarMensagem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		QuadroAvisoForm form = (QuadroAvisoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dataInicial = df.parse(form.getValidadeInicial());
		Date dataFinal = df.parse(form.getValidadeFinal());
		String msg = ConstantesCRM.SVAZIO;

		try {
			if (form.getConta() != null && form.getConta().length() > 0) {
				String query = "select * from customer.conta c, customer.pessoaconta pc, customer.pessoaconsultor pcns, " +
				" customer.pessoadepara pdp, customer.pessoadocumento pd, customer.documento d where pc.idconta = c.idconta " +
				" and pdp.idpessoadepara = pc.idpessoadepara and pd.idpessoa = pdp.idpessoa and pcns.idpessoa  =  " + user +
				" and pd.iddocumento = d.iddocumento and d.nrdocumento = pcns.NRDOCUMENTO and c.cdconta = '" + form.getConta() + "'";

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query);

				if (rsVO.getLinhas().getLinhaArray().length == 0) {
					msg = "Operação não permitida. Você não está associado à conta desse CNPJ. Associe-se ao CNPJ ou selecione outra conta.";
					request.setAttribute("msgStatus", msg);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			}

			// REGIONAL, CONTA e SEGMENTAÇÃO PREENCHIDOS
			if ((form.getRegional() != null && form.getRegional().length() > 0) && (form.getConta() != null && form.getConta().length() > 0) && (form.getSegmentacao() != null && form.getSegmentacao().length() > 0)) {

				String query = "SELECT contatoadm.MENSAGEMSQ.nextval FROM DUAL";
				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query);

				String idMensagem = null;
				if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
					idMensagem = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);

				}
				log.info("QuadroAvisoController: idMensagem >>> " + idMensagem);
				log.info("QuadroAvisoController: Preparando para inserir mensagem >>> Regional/Conta/Segmentacao Preenchidos ");

				// CONTATOADM.MENSAGEM
				log.info("QuadroAvisoController: Inserindo mensagem >>> Data Inicial: " + dataInicial + " Data Final: " + dataFinal + " Conta: " + form.getConta() + " Usuário: " + user + " Mensagem: " + form.getMensagem());
				quadroAvisoFacade.inserirMensagem(new java.sql.Date(dataInicial.getTime()), new java.sql.Date(dataFinal.getTime()), form.getConta(), user, form.getMensagem(), idMensagem);
				// CONTATOADM.MSGSEGMENTACAO
				log.info("QuadroAvisoController: Inserindo mensagem segmentacao >>> Segmentacao: " + form.getSegmentacao() + " Usuario: " + user);
				quadroAvisoFacade.inserirMensagemSegmentacao(form.getSegmentacao(), user, idMensagem);

				// CONTATOADM.MSGUF
				log.info("QuadroAvisoController: Inserindo mensagem uf >>> Regional: " + form.getRegional() + " Usuario: " + user);
				quadroAvisoFacade.inserirMensagemUF(form.getRegional(), user, idMensagem);

				// CUSTOMER.MSGLOG
				log.info("QuadroAvisoController: Inserindo mensagem log >>> Conta: " + form.getConta());
				quadroAvisoFacade.inserirMensagemLog(form.getConta(), idMensagem);

			} else {
				Carteira[] carteiras = quadroAvisoFacade.carteirasGeral(user);
				for (int i = 0; i < carteiras.length; i++) {
					String query = "SELECT contatoadm.MENSAGEMSQ.nextval FROM DUAL";
					Pesquisar pesquisar = new Pesquisar();
					Resultset rsVO = pesquisar.executar(query);

					String idMensagem = null;
					if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
						idMensagem = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);

					}

					log.info("QuadroAvisoController: idMensagem[" + i + "] " + idMensagem);

					if (idMensagem != null) {
						quadroAvisoFacade.inserirMensagem(new java.sql.Date(dataInicial.getTime()), new java.sql.Date(dataFinal.getTime()), carteiras[i].getIdconta(), user, form.getMensagem(), idMensagem);
						quadroAvisoFacade.inserirMensagemLog(carteiras[i].getIdconta(), idMensagem);
					}
				}
			}

			log.info("QuadroAvisoController: A mensagem de aviso foi enviado com sucesso.");
			msg = "A mensagem de aviso foi enviada com sucesso";

		} catch (Exception e) {
			log.error("QuadroAvisoController: Erro ao Confirmar a mensagem ", e);
			e.printStackTrace();
			// msg = "Seleção realizada não atende ao perfil dos clientes atendidos por este Consultor de Relacionamento.";
			msg = "Erro ao confirmar a mensagem.";
		}

		// Limpeza do formulario
		getQuadroAvisoForm().setConta(ConstantesCRM.SVAZIO);
		getQuadroAvisoForm().setRegional(ConstantesCRM.SVAZIO);
		getQuadroAvisoForm().setSegmentacao(ConstantesCRM.SVAZIO);
		getQuadroAvisoForm().setValidadeInicial(ConstantesCRM.SVAZIO);
		getQuadroAvisoForm().setValidadeFinal(ConstantesCRM.SVAZIO);

		request.setAttribute("msgStatus", msg);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private QuadroAvisoForm quadroAvisoForm = null;

	public QuadroAvisoForm getQuadroAvisoForm() {
		if (this.quadroAvisoForm == null) {
			this.quadroAvisoForm = new QuadroAvisoForm();
		}
		return this.quadroAvisoForm;
	}

	public static class QuadroAvisoForm extends ActionForm {

		private static final long serialVersionUID = -7566153312022180263L;

        public String conta;
		public String regional;
		public String segmentacao;
		public String mensagem;
		public String validadeInicial;
		public String validadeFinal;
		public String idconsultor;
		public Segmento[] segmentos;
		public UF[] ufs;

		public UF[] getUfs() {
			if (ufs == null) {
				ufs = new UF[0];
			}
			return this.ufs;
		}

		public void setUfs(UF[] ufs) {
			this.ufs = ufs;
		}

		public Segmento[] getSegmentos() {
			if (segmentos == null) {
				segmentos = new Segmento[0];
			}

			return this.segmentos;
		}

		public void setSegmentos(Segmento[] segmentos) {
			this.segmentos = segmentos;
		}

		public String getConta() {
			return conta;
		}

		public void setConta(String conta) {
			this.conta = conta;
		}

		public String getRegional() {
			return regional;
		}

		public void setRegional(String regional) {
			this.regional = regional;
		}

		public String getSegmentacao() {
			return segmentacao;
		}

		public void setSegmentacao(String segmentacao) {
			this.segmentacao = segmentacao;
		}

		public String getMensagem() {
			return mensagem;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getValidadeInicial() {
			return validadeInicial;
		}

		public void setValidadeInicial(String validadeInicial) {
			this.validadeInicial = validadeInicial;
		}

		public String getValidadeFinal() {
			return validadeFinal;
		}

		public void setValidadeFinal(String validadeFinal) {
			this.validadeFinal = validadeFinal;
		}

		public String getIdconsultor() {
			return idconsultor;
		}

		public void setIdconsultor(String idconsultor) {
			this.idconsultor = idconsultor;
		}

	}
}
