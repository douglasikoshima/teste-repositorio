package workflow.AtendimentoInBox;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;

public class RWFInBoxPesquisaForm extends ActionForm {

	private static final long serialVersionUID = 255321109817028915L;

	private WFEstadosVO estadosVO;
	private String tabPausa;
	private String textoContato;
	private String idContato;
	private String dtFechamentoFim;
	private String dtFechamentoInicio;
	private String dtAberturaFim;
	private String dtAberturaInicio;
	private String nrLinha;
	private String idAtendimento;
	private String nrProtocolo;
	private String idSubEstado;
	private String idEstado;
	private String idGrupo;
	private int radioButton;

	public RWFInBoxPesquisaForm() {
	}

	public void setRadioButton(int radio) {
		this.radioButton = radio;
	}

	public int getRadioButton() {
		return this.radioButton;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getIdEstado() {
		return this.idEstado;
	}

	public void setIdSubEstado(String idSubEstado) {
		this.idSubEstado = idSubEstado;
	}

	public String getIdSubEstado() {
		return this.idSubEstado;
	}

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getIdAtendimento() {
		return this.idAtendimento;
	}

	public void setNrProtocolo(String nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

	public String getNrProtocolo() {
		return this.nrProtocolo;
	}

	public void setNrLinha(String nrLinha) {
		this.nrLinha = nrLinha;
	}

	public String getNrLinha() {
		return this.nrLinha;
	}

	public void setDtAberturaInicio(String dtAberturaInicio) {
		this.dtAberturaInicio = dtAberturaInicio;
	}

	public String getDtAberturaInicio() {
		return this.dtAberturaInicio;
	}

	public void setDtAberturaFim(String dtAberturaFim) {
		this.dtAberturaFim = dtAberturaFim;
	}

	public String getDtAberturaFim() {
		return this.dtAberturaFim;
	}

	public void setDtFechamentoInicio(String dtFechamentoInicio) {
		this.dtFechamentoInicio = dtFechamentoInicio;
	}

	public String getDtFechamentoInicio() {
		return this.dtFechamentoInicio;
	}

	public void setDtFechamentoFim(String dtFechamentoFim) {
		this.dtFechamentoFim = dtFechamentoFim;
	}

	public String getDtFechamentoFim() {
		return this.dtFechamentoFim;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setTextoContato(String textoContato) {
		this.textoContato = textoContato;
	}

	public String getTextoContato() {
		return this.textoContato;
	}

	public void setTabPausa(String tabPausa) {
		this.tabPausa = tabPausa;
	}

	public String getTabPausa() {
		return this.tabPausa;
	}

	public void setEstadosVO(WFEstadosVO estadosVO) {
		this.estadosVO = estadosVO;
	}

	public WFEstadosVO getEstadosVO() {
		return this.estadosVO;
	}

}