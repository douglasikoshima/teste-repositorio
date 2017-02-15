package workflow.Relacionamento;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO.WFEstados;
import br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO.WFSubEstados;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentoVODocument.AtendimentoRelacionamentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument.AtendimentoRelacionamentosVO;
import br.com.vivo.fo.workflow.vo.impl.WFEstadoVODocumentImpl.WFEstadoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.WFSubEstadoVODocumentImpl.WFSubEstadoVOImpl;

public class RelacionamentoForm extends ActionForm {

	private static final long serialVersionUID = -3738552801360657012L;

	private int tipoPesquisa;

	// informações obtidas na URL
	private String idPessoaDePara;
	private String tpRelacionamento;
	private String idPessoaLinhaHistorico;
	private String idLinha;
	private String user;
	private String dtAberturaInicio;
	private String dtAberturaFim;
	private String dtFechamentoInicio;
	private String dtFechamentoFim;
	private String idAtendimento;
	private String nrProtocolo;
	private String contato;
	private String idContato;
	private String estadoSelecionado;
	private String subEstadoSelecionado;
	private String tpPesquisa;
	private boolean flagACS;
	// scriptArvore
	private String scriptArvore = ConstantesCRM.SVAZIO;
	private String inPrimeiraChamada;

	private boolean historicoRelacionamento = false;
	private boolean historicoRelacionamentoMG = false;

	// VOs
	private AtendimentoPesquisaVO atendimentoPesquisaVO;
	private WFEstados wfEstados;
	private WFSubEstados wfSubEstados;
	private AtendimentoRelacionamentosVO atendimentoRelacionamentosVO;

	private AtendimentoRelacionamentosVO processosDeProtocolo;

	public RelacionamentoForm() {
		atendimentoPesquisaVO = AtendimentoPesquisaVO.Factory.newInstance();
		atendimentoPesquisaVO.setWFEstados(WFEstados.Factory.newInstance());
		atendimentoPesquisaVO.getWFEstados().setWFEstadoVOArray(new WFEstadoVOImpl[0]);

		atendimentoPesquisaVO.setWFSubEstados(WFSubEstados.Factory.newInstance());
		atendimentoPesquisaVO.getWFSubEstados().setWFSubEstadoVOArray(new WFSubEstadoVOImpl[0]);

		wfEstados = WFEstados.Factory.newInstance();
		wfEstados.setWFEstadoVOArray(new WFEstadoVOImpl[0]);

		wfSubEstados = WFSubEstados.Factory.newInstance();
		wfSubEstados.setWFSubEstadoVOArray(new WFSubEstadoVOImpl[0]);

		AtendimentoRelacionamentoVO atendimentoRelacionamentoVO = AtendimentoRelacionamentoVO.Factory
				.newInstance();
		atendimentoRelacionamentosVO = AtendimentoRelacionamentosVO.Factory.newInstance();
		atendimentoRelacionamentosVO
				.setAtendimentoRelacionamentoVOArray(new AtendimentoRelacionamentoVO[] { atendimentoRelacionamentoVO });
		processosDeProtocolo = AtendimentoRelacionamentosVO.Factory.newInstance();
		processosDeProtocolo
				.setAtendimentoRelacionamentoVOArray(new AtendimentoRelacionamentoVO[] { atendimentoRelacionamentoVO });
	}

	public String getInPrimeiraChamada() {
		return this.inPrimeiraChamada;
	}

	public void setInPrimeiraChamada(String inPrimeiraChamada) {
		this.inPrimeiraChamada = inPrimeiraChamada;
	}

	public String getIdLinha() {
		return this.idLinha;
	}

	public void setIdLinha(String idLinha) {
		this.idLinha = idLinha;
	}

	public String getScriptArvore() {
		return this.scriptArvore;
	}

	public void setScriptArvore(String scriptArvore) {
		this.scriptArvore = scriptArvore;
	}

	public boolean getFlagACS() {
		return flagACS;
	}

	public String getTpPesquisa() {
		return tpPesquisa;
	}

	public AtendimentoRelacionamentosVO getAtendimentoRelacionamentosVO() {
		return atendimentoRelacionamentosVO;
	}

	public AtendimentoRelacionamentosVO getProcessosDeProtocolo() {
		return processosDeProtocolo;
	}

	public WFEstados getWfEstados() {
		return wfEstados;
	}

	public WFSubEstados getWfSubEstados() {
		return wfSubEstados;
	}

	public AtendimentoPesquisaVO getAtendimentoPesquisaVO() {
		return atendimentoPesquisaVO;
	}

	public String getIdPessoaDePara() {
		return idPessoaDePara;
	}

	public String getTpRelacionamento() {
		return tpRelacionamento;
	}

	public String getIdPessoaLinhaHistorico() {
		return idPessoaLinhaHistorico;
	}

	public String getUser() {
		return user;
	}

	public String getDtAberturaInicio() {
		return dtAberturaInicio;
	}

	public String getDtAberturaFim() {
		return dtAberturaFim;
	}

	public String getDtFechamentoInicio() {
		return dtFechamentoInicio;
	}

	public String getDtFechamentoFim() {
		return dtFechamentoFim;
	}

	public String getIdAtendimento() {
		return idAtendimento;
	}

	public String getNrProtocolo() {
		return nrProtocolo;
	}

	public String getIdContato() {
		return idContato;
	}

	public String getContato() {
		return contato;
	}

	public String getEstadoSelecionado() {
		return estadoSelecionado;
	}

	public String getSubEstadoSelecionado() {
		return subEstadoSelecionado;
	}

	public void setFlagACS(boolean flagACS) {
		this.flagACS = flagACS;
	}

	public void setTpPesquisa(String tpPesquisa) {
		this.tpPesquisa = tpPesquisa;
	}

	public void setWfSubEstados(WFSubEstados wfSubEstados) {
		this.wfSubEstados = wfSubEstados;
	}

	public void setAtendimentoRelacionamentosVO(AtendimentoRelacionamentosVO arg) {
		this.atendimentoRelacionamentosVO = arg;
	}

	public void setProcessosDeProtocolo(AtendimentoRelacionamentosVO arg) {
		this.processosDeProtocolo = arg;
	}

	public void setWfEstados(WFEstados wfEstados) {
		this.wfEstados = wfEstados;
	}

	public void setIdPessoaDePara(String idPessoaDePara) {
		this.idPessoaDePara = idPessoaDePara;
	}

	public void setTpRelacionamento(String tpRelacionamento) {
		this.tpRelacionamento = tpRelacionamento;
	}

	public void setIdPessoaLinhaHistorico(String idPessoaLinhaHistorico) {
		this.idPessoaLinhaHistorico = idPessoaLinhaHistorico;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setAtendimentoPesquisaVO(AtendimentoPesquisaVO atendimentoPesquisaVO) {
		this.atendimentoPesquisaVO = atendimentoPesquisaVO;
	}

	public void setDtAberturaInicio(String dtAberturaInicio) {
		this.dtAberturaInicio = dtAberturaInicio;
	}

	public void setDtAberturaFim(String dtAberturaFim) {
		this.dtAberturaFim = dtAberturaFim;
	}

	public void setDtFechamentoInicio(String dtFechamentoInicio) {
		this.dtFechamentoInicio = dtFechamentoInicio;
	}

	public void setDtFechamentoFim(String dtFechamentoFim) {
		this.dtFechamentoFim = dtFechamentoFim;
	}

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public void setNrProtocolo(String nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public void setEstadoSelecionado(String estadoSelecionado) {
		this.estadoSelecionado = estadoSelecionado;
	}

	public void setSubEstadoSelecionado(String subEstadoSelecionado) {
		this.subEstadoSelecionado = subEstadoSelecionado;
	}

	public void setTipoPesquisa(int tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}

	public int getTipoPesquisa() {
		return this.tipoPesquisa;
	}

	public void setHistoricoRelacionamento(boolean arg) {
		this.historicoRelacionamento = arg;
	}

	public boolean isHistoricoRelacionamento() {
		return this.historicoRelacionamento;
	}

	public void setHistoricoRelacionamentoMG(boolean arg) {
		this.historicoRelacionamentoMG = arg;
	}

	public boolean isHistoricoRelacionamentoMG() {
		return this.historicoRelacionamentoMG;
	}

}