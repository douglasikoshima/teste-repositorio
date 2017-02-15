package admsistemas.acaoIncentivo;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.AcaoIncentivoFacade;
import br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db.Acao;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class AcaoIncentivoController extends AbstractAction {

	private static final long serialVersionUID = -326685315663305108L;

	@EJB
	private AcaoIncentivoFacade acaoIncentivoFacade;

	private AcaoIncentivoForm acaoIncentivoForm = null;
	
	private final static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("alteraAcaoIncentivo".equals(mapping.getParameter())) {
			return alteraAcaoIncentivo(mapping, form, request, response);
		} else if ("excluirAcaoIncentivo".equals(mapping.getParameter())) {
			return excluirAcaoIncentivo(mapping, form, request, response);
		} else if ("incluirAcaoIncentivo".equals(mapping.getParameter())) {
			return incluirAcaoIncentivo(mapping, form, request, response);
		} else if ("manterAcaoIncentivo".equals(mapping.getParameter())) {
			return manterAcaoIncentivo(mapping, form, request, response);
		} else if ("pesquisarAcaoIncentivo".equals(mapping.getParameter())) {
			return pesquisarAcaoIncentivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer statement = new StringBuffer();
		statement.append("SELECT CDACAOINCENTIVO, ");
		statement.append("       DSACAOINCENTIVO, ");
		statement.append("       TO_CHAR(DTHORAINICIO, 'DD/MM/YYYY HH24:MI:SS')  DTHORAINICIO, ");
		statement.append("       TO_CHAR(DTHORATERMINO, 'DD/MM/YYYY HH24:MI:SS') DTHORATERMINO, ");
		statement.append("       INTIPOACAOINCENTIVO, ");
		statement.append("       MSGANTESINICIOACAO, ");
		statement.append("       MSGAPOSINICIOACAO, ");
		statement.append("       INATIVO  ");
		statement.append("  FROM CUSTOMER.ACAO_INCENTIVO ");
		statement.append(" WHERE INATIVO = 1");
		try {
			getAcaoIncentivoForm().setAcoes(acaoIncentivoFacade.getPesquisaAcoes(statement.toString()));
			getAcaoIncentivoForm().setPesquisaAtivo("1");
		} catch (Exception e) {
		    log.error("AcaoIncentivoController::begin", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAcaoIncentivo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraAcaoIncentivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idAcao = request.getParameter("id");
		Acao acao = acaoIncentivoFacade.buscaAcaoIncentivo(idAcao);

		getAcaoIncentivoForm().setCdacaoincentivo(acao.getCdacaoincentivo());
		getAcaoIncentivoForm().setDsacaoincentivo(acao.getDsacaoincentivo());

		// Separa Data Hora INICIO
		if (acao.getDthorainicio() != null) {
			String dataHoraInicio = acao.getDthorainicio();
			getAcaoIncentivoForm().setDataInicio(dataHoraInicio.substring(0, 10));
			getAcaoIncentivoForm().setHoraInicio(dataHoraInicio.substring(11, dataHoraInicio.length()));
		}

		// Separa Data Hora TERMINO
		if (acao.getDthoratermino() != null) {
			String dataHoraTermino = acao.getDthoratermino();
			getAcaoIncentivoForm().setDataTermino(dataHoraTermino.substring(0, 10));
			getAcaoIncentivoForm().setHoraTermino(dataHoraTermino.substring(11, dataHoraTermino.length()));
		}

		getAcaoIncentivoForm().setMsgantesinicioacao(acao.getMsgantesinicioacao());
		getAcaoIncentivoForm().setMsgaposinicioacao(acao.getMsgaposinicioacao());
		getAcaoIncentivoForm().setInativo(acao.getInativo());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluirAcaoIncentivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String idAcao = request.getParameter("id");

		acaoIncentivoFacade.excluirAcaoIncentivo(idAcao);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAcaoIncentivo.jsp"
	 */
	public ActionForward incluirAcaoIncentivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		acaoIncentivoForm = null;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward manterAcaoIncentivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AcaoIncentivoForm form = (AcaoIncentivoForm) formParam;

		String dataInicial = form.getDataInicio() + " " + form.getHoraInicio() + ":00";
		String dataTermino = form.getDataTermino() + " " + form.getHoraTermino() + ":59";
		String tipo = ConstantesCRM.SVAZIO;
		String inativo = ConstantesCRM.SVAZIO;

		if (form.getInativo() == null) {
			inativo = ConstantesCRM.SZERO;
		} else {
			inativo = ConstantesCRM.SONE;
		}

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		acaoIncentivoFacade.manterAcaoIncentivo(form.getCdacaoincentivo(), form.getDsacaoincentivo(), dataInicial, dataTermino, tipo, form.getMsgantesinicioacao(), form.getMsgaposinicioacao(), inativo,  user);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward pesquisarAcaoIncentivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		AcaoIncentivoForm form = (AcaoIncentivoForm) formParam;

		String inativo = form.getPesquisaAtivo();
		String codigo = form.getPesquisaCodigoAcao();
		String descricao = form.getPesquisaDescricao();
		String situacao = form.getPesquisaSituacao();

		StringBuffer statement = new StringBuffer();
		statement.append("SELECT CDACAOINCENTIVO, ");
		statement.append("       DSACAOINCENTIVO, ");
		statement.append("       TO_CHAR(DTHORAINICIO, 'DD/MM/YYYY HH24:MI:SS')  DTHORAINICIO, ");
		statement.append("       TO_CHAR(DTHORATERMINO, 'DD/MM/YYYY HH24:MI:SS') DTHORATERMINO, ");
		statement.append("       INTIPOACAOINCENTIVO, ");
		statement.append("       MSGANTESINICIOACAO, ");
		statement.append("       MSGAPOSINICIOACAO, ");
		statement.append("       INATIVO  ");
		statement.append("  FROM CUSTOMER.ACAO_INCENTIVO ");

		if (ConstantesCRM.STWO.equals(inativo)) {
			statement.append(" WHERE INATIVO IN (0,1)");
		} else {
			statement.append(" WHERE INATIVO =").append(inativo);
		}

		if (ConstantesCRM.SONE.equals(situacao)) {
			statement.append(" AND SYSDATE BETWEEN DTHORAINICIO AND DTHORATERMINO  ");
		} else if (situacao.equals(ConstantesCRM.STWO)) {
			statement.append(" AND DTHORATERMINO < SYSDATE  ");
		} else if (situacao.equals("3")) {
			statement.append(" AND DTHORAINICIO > SYSDATE  ");
		}

		if (codigo.length() > 0) {
			statement.append(" AND CDACAOINCENTIVO = ").append(codigo);
		}

		if (descricao.length() > 0) {
			statement.append(" AND UPPER(DSACAOINCENTIVO) LIKE  '%").append(descricao.toUpperCase()).append("%'");
		}

		getAcaoIncentivoForm().setAcoes(acaoIncentivoFacade.getPesquisaAcoes(statement.toString()));

		getAcaoIncentivoForm().setPesquisaCodigoAcao(form.getPesquisaCodigoAcao());
		getAcaoIncentivoForm().setPesquisaDescricao(form.getPesquisaDescricao());
		getAcaoIncentivoForm().setPesquisaSituacao(form.getPesquisaSituacao());
		getAcaoIncentivoForm().setPesquisaAtivo(form.getPesquisaAtivo());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public AcaoIncentivoForm getAcaoIncentivoForm() {
		if (this.acaoIncentivoForm == null) {
			this.acaoIncentivoForm = new AcaoIncentivoForm();
		}
		return this.acaoIncentivoForm;
	}

	public static class AcaoIncentivoForm extends ActionForm {

		private static final long serialVersionUID = 393195462462950478L;

		private String cdacaoincentivo;
		private String dsacaoincentivo;
		private String dthorainicio;
		private String dthoratermino;
		private String intipoacaoincentivo;
		private String msgantesinicioacao;
		private String msgaposinicioacao;
		private String inativo;

		private String dataInicio;
		private String horaInicio;
		private String dataTermino;
		private String horaTermino;

		public Acao[] acoes;

		private String pesquisaCodigoAcao;
		private String pesquisaDescricao;
		private String pesquisaSituacao;
		private String pesquisaAtivo;

		public String getInativo() {
			return inativo;
		}

		public void setInativo(String inativo) {
			this.inativo = inativo;
		}

		public String getDataInicio() {
			return dataInicio;
		}

		public void setDataInicio(String dataInicio) {
			this.dataInicio = dataInicio;
		}

		public String getHoraInicio() {
			return horaInicio;
		}

		public void setHoraInicio(String horaInicio) {
			this.horaInicio = horaInicio;
		}

		public String getDataTermino() {
			return dataTermino;
		}

		public void setDataTermino(String dataTermino) {
			this.dataTermino = dataTermino;
		}

		public String getHoraTermino() {
			return horaTermino;
		}

		public void setHoraTermino(String horaTermino) {
			this.horaTermino = horaTermino;
		}

		public String getCdacaoincentivo() {
			return cdacaoincentivo;
		}

		public void setCdacaoincentivo(String cdacaoincentivo) {
			this.cdacaoincentivo = cdacaoincentivo;
		}

		public String getDsacaoincentivo() {
			return dsacaoincentivo;
		}

		public void setDsacaoincentivo(String dsacaoincentivo) {
			this.dsacaoincentivo = dsacaoincentivo;
		}

		public String getDthorainicio() {
			return dthorainicio;
		}

		public void setDthorainicio(String dthorainicio) {
			this.dthorainicio = dthorainicio;
		}

		public String getDthoratermino() {
			return dthoratermino;
		}

		public void setDthoratermino(String dthoratermino) {
			this.dthoratermino = dthoratermino;
		}

		public String getIntipoacaoincentivo() {
			return intipoacaoincentivo;
		}

		public void setIntipoacaoincentivo(String intipoacaoincentivo) {
			this.intipoacaoincentivo = intipoacaoincentivo;
		}

		public String getMsgantesinicioacao() {
			return msgantesinicioacao;
		}

		public void setMsgantesinicioacao(String msgantesinicioacao) {
			this.msgantesinicioacao = msgantesinicioacao;
		}

		public String getMsgaposinicioacao() {
			return msgaposinicioacao;
		}

		public void setMsgaposinicioacao(String msgaposinicioacao) {
			this.msgaposinicioacao = msgaposinicioacao;
		}

		public Acao[] getAcoes() {
			if (this.acoes == null) {
				this.acoes = new Acao[0];
			}
			return this.acoes;
		}

		public void setAcoes(Acao[] acoes) {
			this.acoes = acoes;
		}

		public String getPesquisaCodigoAcao() {
			return pesquisaCodigoAcao;
		}

		public void setPesquisaCodigoAcao(String pesquisaCodigoAcao) {
			this.pesquisaCodigoAcao = pesquisaCodigoAcao;
		}

		public String getPesquisaDescricao() {
			return pesquisaDescricao;
		}

		public void setPesquisaDescricao(String pesquisaDescricao) {
			this.pesquisaDescricao = pesquisaDescricao;
		}

		public String getPesquisaSituacao() {
			return pesquisaSituacao;
		}

		public void setPesquisaSituacao(String pesquisaSituacao) {
			this.pesquisaSituacao = pesquisaSituacao;
		}

		public String getPesquisaAtivo() {
			return pesquisaAtivo;
		}

		public void setPesquisaAtivo(String pesquisaAtivo) {
			this.pesquisaAtivo = pesquisaAtivo;
		}
	}
}
