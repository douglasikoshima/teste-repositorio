package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class FormaCondicaoPagamentoForm extends ValidatorActionForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private Integer idFormaPagamentoSearch;
	private Integer idBandeiraSearch;
	private String sgMeioPagamentoSearch;
    private Integer idPlataformaSearch; 
	
	private Integer idFormaPagamento;
	private String idFormaCondicaoPagamento;
	private String formaPagamento;
	private Integer idCanalAtendimento;
	private Integer idCanalDistribuicao;
	private String vlDesconto;
	private Integer idBandeira;
	private String vlParcelaMinima;
	private String sgMeioPagamento;
	private String codInstituicaoFinanceira;
	
	private Integer idCondicoesPagto[];
	private Integer condicoesPagto[];
	private String siglasSAP[];
	private String descricoes[];
	private Integer checkBandeira[];
    private Integer checkPlataforma[];
	
	public Integer getIdBandeiraSearch() {
		return idBandeiraSearch;
	}
	public void setIdBandeiraSearch(Integer idBandeiraSearch) {
		this.idBandeiraSearch = idBandeiraSearch;
	}
	public Boolean getCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getIdFormaCondicaoPagamento() {
		return idFormaCondicaoPagamento;
	}
	public void setIdFormaCondicaoPagamento(String idFormaCondicaoPagamento) {
		this.idFormaCondicaoPagamento = idFormaCondicaoPagamento;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getVlDesconto() {
		return vlDesconto;
	}
	public void setVlDesconto(String vlDesconto) {
		this.vlDesconto = vlDesconto;
	}
	public Integer getIdBandeira() {
		return idBandeira;
	}
	public void setIdBandeira(Integer idBandeira) {
		this.idBandeira = idBandeira;
	}
	public String getVlParcelaMinima() {
		return vlParcelaMinima;
	}
	public void setVlParcelaMinima(String vlParcelaMinima) {
		this.vlParcelaMinima = vlParcelaMinima;
	}
	public String getCodInstituicaoFinanceira() {
		return codInstituicaoFinanceira;
	}
	public void setCodInstituicaoFinanceira(String codInstituicaoFinanceira) {
		this.codInstituicaoFinanceira = codInstituicaoFinanceira;
	}
	public Integer getIdFormaPagamentoSearch() {
		return idFormaPagamentoSearch;
	}
	public void setIdFormaPagamentoSearch(Integer idFormaPagamentoSearch) {
		this.idFormaPagamentoSearch = idFormaPagamentoSearch;
	}
	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public Integer getIdCanalDistribuicao() {
		return idCanalDistribuicao;
	}
	public void setIdCanalDistribuicao(Integer idCanalDistribuicao) {
		this.idCanalDistribuicao = idCanalDistribuicao;
	}
	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}
	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	public String getSgMeioPagamento() {
		return sgMeioPagamento;
	}
	public void setSgMeioPagamento(String sgMeioPagamento) {
		this.sgMeioPagamento = sgMeioPagamento;
	}
	public String getSgMeioPagamentoSearch() {
		return sgMeioPagamentoSearch;
	}
	public void setSgMeioPagamentoSearch(String sgMeioPagamentoSearch) {
		this.sgMeioPagamentoSearch = sgMeioPagamentoSearch;
	}
	public Integer[] getCondicoesPagto() {
		return condicoesPagto;
	}
	public void setCondicoesPagto(Integer[] condicoesPagto) {
		this.condicoesPagto = condicoesPagto;
	}
	public String[] getDescricoes() {
		return descricoes;
	}
	public void setDescricoes(String[] descricoes) {
		this.descricoes = descricoes;
	}
	public Integer[] getIdCondicoesPagto() {
		return idCondicoesPagto;
	}
	public void setIdCondicoesPagto(Integer[] idCondicoesPagto) {
		this.idCondicoesPagto = idCondicoesPagto;
	}
	public String[] getSiglasSAP() {
		return siglasSAP;
	}
	public void setSiglasSAP(String[] siglasSAP) {
		this.siglasSAP = siglasSAP;
	}
	public Integer[] getCheckBandeira() {
		return checkBandeira;
	}
	public void setCheckBandeira(Integer[] checkBandeira) {
		this.checkBandeira = checkBandeira;
	}
    public Integer getIdPlataformaSearch() {
        return idPlataformaSearch;
    }
    public void setIdPlataformaSearch(Integer idPlataformaSearch) {
        this.idPlataformaSearch = idPlataformaSearch;
    }
    public Integer[] getCheckPlataforma() {
        return checkPlataforma;
    }
    public void setCheckPlataforma(Integer[] checkPlataforma) {
        this.checkPlataforma = checkPlataforma;
    }
}