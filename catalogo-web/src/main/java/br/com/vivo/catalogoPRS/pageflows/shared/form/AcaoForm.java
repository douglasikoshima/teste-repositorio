package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.AcaoTO;

public class AcaoForm extends ValidatorActionForm implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private Integer idAcao;
	private String nmAcao;
	private String sgAcao;
	private String dsAcao;
	private String inDisponivel;
	private String dtInicial;
	private String dtFinal;
	private String indAnaliseFraude;
	private Integer idCanalAtendimento;
	
	private Integer idFornecedores[];
	private Integer idPerfis[];
	
	private Integer idAcaoSearch;
	private String nmAcaoSearch;
	private String sgAcaoSearch;
	private String inDisponivelSearch;
	private String dtInicialSearch;
	private String dtFinalSearch;
	private Integer idCanalAtendimentoSearch;
	
	private String isPesquisa;
	
	
	private List<AcaoTO> acaoList;

	public String getIsPesquisa() {
		return isPesquisa;
	}
	public void setIsPesquisa(String isPesquisa) {
		this.isPesquisa = isPesquisa;
	}
	public Boolean getCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	public String getDsAcao() {
		return dsAcao;
	}
	public void setDsAcao(String dsAcao) {
		this.dsAcao = dsAcao;
	}
	public Integer getIdAcao() {
		return idAcao;
	}
	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}
	public Integer getIdAcaoSearch() {
		return idAcaoSearch;
	}
	public void setIdAcaoSearch(Integer idAcaoSearch) {
		this.idAcaoSearch = idAcaoSearch;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getInDisponivelSearch() {
		return inDisponivelSearch;
	}
	public void setInDisponivelSearch(String inDisponivelSearch) {
		this.inDisponivelSearch = inDisponivelSearch;
	}
	public String getNmAcao() {
		return nmAcao;
	}
	public void setNmAcao(String nmAcao) {
		this.nmAcao = nmAcao;
	}
	public String getNmAcaoSearch() {
		return nmAcaoSearch;
	}
	public void setNmAcaoSearch(String nmAcaoSearch) {
		this.nmAcaoSearch = nmAcaoSearch;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getSgAcao() {
		return sgAcao;
	}
	public void setSgAcao(String sgAcao) {
		this.sgAcao = sgAcao;
	}
	public String getSgAcaoSearch() {
		return sgAcaoSearch;
	}
	public void setSgAcaoSearch(String sgAcaoSearch) {
		this.sgAcaoSearch = sgAcaoSearch;
	}
	public List<AcaoTO> getAcaoList() {
		return acaoList;
	}
	public void setAcaoList(List<AcaoTO> acaoList) {
		this.acaoList = acaoList;
	}
	public String getIndAnaliseFraude() {
		return indAnaliseFraude;
	}
	public void setIndAnaliseFraude(String indAnaliseFraude) {
		this.indAnaliseFraude = indAnaliseFraude;
	}
	public Integer[] getIdFornecedores() {
		return idFornecedores;
	}
	public void setIdFornecedores(Integer[] idFornecedores) {
		this.idFornecedores = idFornecedores;
	}
	public Integer[] getIdPerfis() {
		return idPerfis;
	}
	public void setIdPerfis(Integer[] idPerfis) {
		this.idPerfis = idPerfis;
	}
	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public String getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}
	public String getDtFinalSearch() {
		return dtFinalSearch;
	}
	public void setDtFinalSearch(String dtFinalSearch) {
		this.dtFinalSearch = dtFinalSearch;
	}
	public Integer getIdCanalAtendimentoSearch(){
		return this.idCanalAtendimentoSearch;
	}
	public void setIdCanalAtendimentoSearch(Integer idCanalAtendimentoSearch){
		this.idCanalAtendimentoSearch = idCanalAtendimentoSearch;
	}
	public String getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}
	public String getDtInicialSearch() {
		return dtInicialSearch;
	}
	public void setDtInicialSearch(String dtInicialSearch) {
		this.dtInicialSearch = dtInicialSearch;
	}
}