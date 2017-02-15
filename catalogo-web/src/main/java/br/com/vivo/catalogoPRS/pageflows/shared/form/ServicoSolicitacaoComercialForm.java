package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class ServicoSolicitacaoComercialForm extends ValidatorActionForm  implements java.io.Serializable  {

	private static final long serialVersionUID = -8059231483750963851L;
	
	private Integer idServico;
	private Integer idSistema;
	private String nmTipoServico;
	
	private Long idSolicitacaoComercial;
	private Long idPoliticaDispSlctComercial;
	private String cdSolicitacaoComercial;
	private String nmSolicitacaoComercial;
	private Long idTipoSolicitacaoComercial;
	private String pzMaximoVigencia;
	private String pzMaximoAtendimento;
	private String inDisponivel;
	private String inDisponivelNew;
	private String inOfertaClienteInadimplente;
	private String inOfertaClienteInadimplenteNew; 
	
	private Long[] idCanalVendaSelecionavelList;
	private Long[] idCanalVendaSelecionadoList;
	
	private Long idCanalVendaSolicitacaoComercial;
	private Long[] idAreaConcorrenciaSlctSelecionavelList;
	private Long[] idAreaConcorrenciaSlctSelecionadoList;
	private Long[] idPlanoDeMinutosSlctSelecionavelList;
	private Long[] idPlanoDeMinutosSlctSelecionadoList;
	private Long[] idsOfertaClienteInadimplenteSelecionados;
	
	private Long idEncargo;
	private String pzGratuidade;
	private String nmPacote;
	private Long idTipoEncargo;
	
	private Integer idCondicaoPagamento;
	private Integer idCondicaoPagamentoNew;
	private Integer idCondicaoPagamentoEdit;
	
	private Long idCanalVendaNew;
	private Long idCanalVendaEdit;
	
	private Long idCanalVendaEncargo;
	private Long idCondicaoPagamentoEncargo;
	private Long idCondicaoPagamentoEncargoNew;
	private Long[] idAreaConcorrenciaCndcSelecionavelList;
	private Long[] idAreaConcorrenciaCndcSelecionadoList;
	private Long[] idPlanoDeMinutosCndcSelecionavelList;
	private Long[] idPlanoDeMinutosCndcSelecionadoList;
    private String dsEncargo;
	
	public String getCdSolicitacaoComercial() {
		return cdSolicitacaoComercial;
	}
	public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
		this.cdSolicitacaoComercial = cdSolicitacaoComercial;
	}
	public Long[] getIdAreaConcorrenciaCndcSelecionadoList() {
		return idAreaConcorrenciaCndcSelecionadoList;
	}
	public void setIdAreaConcorrenciaCndcSelecionadoList(
			Long[] idAreaConcorrenciaCndcSelecionadoList) {
		this.idAreaConcorrenciaCndcSelecionadoList = idAreaConcorrenciaCndcSelecionadoList;
	}
	public Long[] getIdAreaConcorrenciaCndcSelecionavelList() {
		return idAreaConcorrenciaCndcSelecionavelList;
	}
	public void setIdAreaConcorrenciaCndcSelecionavelList(
			Long[] idAreaConcorrenciaCndcSelecionavelList) {
		this.idAreaConcorrenciaCndcSelecionavelList = idAreaConcorrenciaCndcSelecionavelList;
	}
	public Long[] getIdAreaConcorrenciaSlctSelecionadoList() {
		return idAreaConcorrenciaSlctSelecionadoList;
	}
	public void setIdAreaConcorrenciaSlctSelecionadoList(
			Long[] idAreaConcorrenciaSlctSelecionadoList) {
		this.idAreaConcorrenciaSlctSelecionadoList = idAreaConcorrenciaSlctSelecionadoList;
	}
	public Long[] getIdAreaConcorrenciaSlctSelecionavelList() {
		return idAreaConcorrenciaSlctSelecionavelList;
	}
	public void setIdAreaConcorrenciaSlctSelecionavelList(
			Long[] idAreaConcorrenciaSlctSelecionavelList) {
		this.idAreaConcorrenciaSlctSelecionavelList = idAreaConcorrenciaSlctSelecionavelList;
	}
	public Long getIdCanalVendaEncargo() {
		return idCanalVendaEncargo;
	}
	public void setIdCanalVendaEncargo(Long idCanalVendaEncargo) {
		this.idCanalVendaEncargo = idCanalVendaEncargo;
	}
	public Long[] getIdCanalVendaSelecionadoList() {
		return idCanalVendaSelecionadoList;
	}
	public void setIdCanalVendaSelecionadoList(Long[] idCanalVendaSelecionadoList) {
		this.idCanalVendaSelecionadoList = idCanalVendaSelecionadoList;
	}
	public Long[] getIdCanalVendaSelecionavelList() {
		return idCanalVendaSelecionavelList;
	}
	public void setIdCanalVendaSelecionavelList(Long[] idCanalVendaSelecionavelList) {
		this.idCanalVendaSelecionavelList = idCanalVendaSelecionavelList;
	}
	public Long getIdCanalVendaSolicitacaoComercial() {
		return idCanalVendaSolicitacaoComercial;
	}
	public void setIdCanalVendaSolicitacaoComercial(
			Long idCanalVendaSolicitacaoComercial) {
		this.idCanalVendaSolicitacaoComercial = idCanalVendaSolicitacaoComercial;
	}
	public Long getIdCondicaoPagamentoEncargo() {
		return idCondicaoPagamentoEncargo;
	}
	public void setIdCondicaoPagamentoEncargo(Long idCondicaoPagamentoEncargo) {
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}
	public Long getIdEncargo() {
		return idEncargo;
	}
	public void setIdEncargo(Long idEncargo) {
		this.idEncargo = idEncargo;
	}
	public Long[] getIdPlanoDeMinutosCndcSelecionadoList() {
		return idPlanoDeMinutosCndcSelecionadoList;
	}
	public void setIdPlanoDeMinutosCndcSelecionadoList(
			Long[] idPlanoDeMinutosCndcSelecionadoList) {
		this.idPlanoDeMinutosCndcSelecionadoList = idPlanoDeMinutosCndcSelecionadoList;
	}
	public Long[] getIdPlanoDeMinutosCndcSelecionavelList() {
		return idPlanoDeMinutosCndcSelecionavelList;
	}
	public void setIdPlanoDeMinutosCndcSelecionavelList(
			Long[] idPlanoDeMinutosCndcSelecionavelList) {
		this.idPlanoDeMinutosCndcSelecionavelList = idPlanoDeMinutosCndcSelecionavelList;
	}
	public Long[] getIdPlanoDeMinutosSlctSelecionadoList() {
		return idPlanoDeMinutosSlctSelecionadoList;
	}
	public void setIdPlanoDeMinutosSlctSelecionadoList(
			Long[] idPlanoDeMinutosSlctSelecionadoList) {
		this.idPlanoDeMinutosSlctSelecionadoList = idPlanoDeMinutosSlctSelecionadoList;
	}
	public Long[] getIdPlanoDeMinutosSlctSelecionavelList() {
		return idPlanoDeMinutosSlctSelecionavelList;
	}
	public void setIdPlanoDeMinutosSlctSelecionavelList(
			Long[] idPlanoDeMinutosSlctSelecionavelList) {
		this.idPlanoDeMinutosSlctSelecionavelList = idPlanoDeMinutosSlctSelecionavelList;
	}
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}
	public Integer getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}
	public Long getIdSolicitacaoComercial() {
		return idSolicitacaoComercial;
	}
	public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
		this.idSolicitacaoComercial = idSolicitacaoComercial;
	}
	public Long getIdTipoEncargo() {
		return idTipoEncargo;
	}
	public void setIdTipoEncargo(Long idTipoEncargo) {
		this.idTipoEncargo = idTipoEncargo;
	}
	public Long getIdTipoSolicitacaoComercial() {
		return idTipoSolicitacaoComercial;
	}
	public void setIdTipoSolicitacaoComercial(Long idTipoSolicitacaoComercial) {
		this.idTipoSolicitacaoComercial = idTipoSolicitacaoComercial;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getNmPacote() {
		return nmPacote;
	}
	public void setNmPacote(String nmPacote) {
		this.nmPacote = nmPacote;
	}
	public String getNmSolicitacaoComercial() {
		return nmSolicitacaoComercial;
	}
	public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
		this.nmSolicitacaoComercial = nmSolicitacaoComercial;
	}
	public String getPzGratuidade() {
		return pzGratuidade;
	}
	public void setPzGratuidade(String pzGratuidade) {
		this.pzGratuidade = pzGratuidade;
	}
	public String getPzMaximoAtendimento() {
		return pzMaximoAtendimento;
	}
	public void setPzMaximoAtendimento(String pzMaximoAtendimento) {
		this.pzMaximoAtendimento = pzMaximoAtendimento;
	}
	public String getPzMaximoVigencia() {
		return pzMaximoVigencia;
	}
	public void setPzMaximoVigencia(String pzMaximoVigencia) {
		this.pzMaximoVigencia = pzMaximoVigencia;
	}
	public Integer getIdCondicaoPagamento() {
		return idCondicaoPagamento;
	}
	public void setIdCondicaoPagamento(Integer idCondicaoPagamento) {
		this.idCondicaoPagamento = idCondicaoPagamento;
	}
	public String getInDisponivelNew() {
		return inDisponivelNew;
	}
	public void setInDisponivelNew(String inDisponivelNew) {
		this.inDisponivelNew = inDisponivelNew;
	}
	public Long getIdCondicaoPagamentoEncargoNew() {
		return idCondicaoPagamentoEncargoNew;
	}
	public void setIdCondicaoPagamentoEncargoNew(Long idCondicaoPagamentoEncargoNew) {
		this.idCondicaoPagamentoEncargoNew = idCondicaoPagamentoEncargoNew;
	}
	public Long getIdCanalVendaNew() {
		return idCanalVendaNew;
	}
	public void setIdCanalVendaNew(Long idCanalVendaNew) {
		this.idCanalVendaNew = idCanalVendaNew;
	}
	public Integer getIdCondicaoPagamentoNew() {
		return idCondicaoPagamentoNew;
	}
	public void setIdCondicaoPagamentoNew(Integer idCondicaoPagamentoNew) {
		this.idCondicaoPagamentoNew = idCondicaoPagamentoNew;
	}
	public Long getIdCanalVendaEdit() {
		return idCanalVendaEdit;
	}
	public void setIdCanalVendaEdit(Long idCanalVendaEdit) {
		this.idCanalVendaEdit = idCanalVendaEdit;
	}
	public Integer getIdCondicaoPagamentoEdit() {
		return idCondicaoPagamentoEdit;
	}
	public void setIdCondicaoPagamentoEdit(Integer idCondicaoPagamentoEdit) {
		this.idCondicaoPagamentoEdit = idCondicaoPagamentoEdit;
	}
	public Long getIdPoliticaDispSlctComercial() {
		return idPoliticaDispSlctComercial;
	}
	public void setIdPoliticaDispSlctComercial(Long idPoliticaDispSlctComercial) {
		this.idPoliticaDispSlctComercial = idPoliticaDispSlctComercial;
	}
    public String getDsEncargo() {
        return dsEncargo;
    }
    public void setDsEncargo(String dsEncargo) {
        this.dsEncargo = dsEncargo;
    }
	public String getInOfertaClienteInadimplente() {
		return inOfertaClienteInadimplente;
	}
	public void setInOfertaClienteInadimplente(String inOfertaClienteInadimplente) {
		this.inOfertaClienteInadimplente = inOfertaClienteInadimplente;
	}
	public Long[] getIdsOfertaClienteInadimplenteSelecionados() {
		return idsOfertaClienteInadimplenteSelecionados;
	}
	public void setIdsOfertaClienteInadimplenteSelecionados(
			Long[] idsOfertaClienteInadimplenteSelecionados) {
		this.idsOfertaClienteInadimplenteSelecionados = idsOfertaClienteInadimplenteSelecionados;
	}
	public String getInOfertaClienteInadimplenteNew() {
		return inOfertaClienteInadimplenteNew;
	}
	public void setInOfertaClienteInadimplenteNew(
			String inOfertaClienteInadimplenteNew) {
		this.inOfertaClienteInadimplenteNew = inOfertaClienteInadimplenteNew;
	}
	public String getNmTipoServico() {
		return nmTipoServico;
	}
	public void setNmTipoServico(String nmTipoServico) {
		this.nmTipoServico = nmTipoServico;
	}
}
