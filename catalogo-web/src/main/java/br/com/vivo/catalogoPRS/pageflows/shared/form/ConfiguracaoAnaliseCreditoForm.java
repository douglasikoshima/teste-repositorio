package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

public class ConfiguracaoAnaliseCreditoForm extends ValidatorActionForm  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String idCabecalhoAnaliseCredito;
	private String idCanalAtendimento;
	private String idCategoriaScore;
	private String idCabecalhoAnaliseCreditoCopia;

	private String nome;
	private String status;
	private Date dtCriacao;
	private String usuario;
	private String inDisponivel;
	
	private boolean regional;
	private String regionaisJsonSearch;
	private String nomeSearch;
	private String precoDeSearch;
	private String precoAteSearch;
	private String tpClassificacao;
	private String idCategoriaSearch;
	private String[] configuracoes;
	private String[] configuracoesRemove;
	private String configuracoesJson;
	private String idAnaliseCreditoSearch; 
	private Integer[] itensPesquisados;
	
	private List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreList;
	private List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreList;
	private List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreList;	
	private boolean salvarConfigScore;
	private String canaisConfiguraveisPorRegionalJSON; 
	private Boolean canalConfiguravelPorRegional;
	
	public List<OfServicoConfiguracaoScoreTO> getOfServicoConfiguracaoScoreList() {
		return ofServicoConfiguracaoScoreList;
	}
	public void setOfServicoConfiguracaoScoreList(
			List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoScoreList) {
		this.ofServicoConfiguracaoScoreList = ofServicoConfiguracaoScoreList;
	}
	public List<PlanoConfiguracaoScoreTO> getPlanoConfiguracaoScoreList() {
		return planoConfiguracaoScoreList;
	}
	public void setPlanoConfiguracaoScoreList(
			List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreList) {
		this.planoConfiguracaoScoreList = planoConfiguracaoScoreList;
	}
	public List<ServicoConfiguracaoScoreTO> getServicoConfiguracaoScoreList() {
		return servicoConfiguracaoScoreList;
	}
	public void setServicoConfiguracaoScoreList(
			List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreList) {
		this.servicoConfiguracaoScoreList = servicoConfiguracaoScoreList;
	}
	public String getIdCategoriaSearch() {
		return idCategoriaSearch;
	}
	public void setIdCategoriaSearch(String idCategoriaSearch) {
		this.idCategoriaSearch = idCategoriaSearch;
	}
	public String getNomeSearch() {
		return nomeSearch;
	}
	public void setNomeSearch(String nomeSearch) {
		this.nomeSearch = nomeSearch;
	}
	public String[] getConfiguracoes() {
		return configuracoes;
	}
	public void setConfiguracoes(String[] configuracoes) {
		this.configuracoes = configuracoes;
	}
	public String[] getConfiguracoesRemove() {
		return configuracoesRemove;
	}
	public void setConfiguracoesRemove(String[] configuracoesRemove) {
		this.configuracoesRemove = configuracoesRemove;
	}		
	public String getPrecoAteSearch() {
		return precoAteSearch;
	}
	public void setPrecoAteSearch(String precoAteSearch) {
		this.precoAteSearch = precoAteSearch;
	}
	public String getPrecoDeSearch() {
		return precoDeSearch;
	}
	public void setPrecoDeSearch(String precoDeSearch) {
		this.precoDeSearch = precoDeSearch;
	}
	public String getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(String idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public boolean isRegional() {
		return regional;
	}
	public void isRegional(boolean regional) {
		this.regional = regional;
	}
	public Date getDtCriacao() {
		return dtCriacao;
	}
	public String getRegionaisJsonSearch() {
		return regionaisJsonSearch;
	}
	public void setRegionaisJsonSearch(String regionaisJsonSearch) {
		this.regionaisJsonSearch = regionaisJsonSearch;
	}
	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTpClassificacao() {
		return tpClassificacao;
	}
	public void setTpClassificacao(String tpClassificacao) {
		this.tpClassificacao = tpClassificacao;
	}
	public String getIdCabecalhoAnaliseCredito() {
		return idCabecalhoAnaliseCredito;
	}
	public void setIdCabecalhoAnaliseCredito(String idCabecalhoAnaliseCredito) {
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getIdCategoriaScore() {
		return idCategoriaScore;
	}
	public void setIdCategoriaScore(String idCategoriaScore) {
		this.idCategoriaScore = idCategoriaScore;
	}
	public String getConfiguracoesJson() {
		return configuracoesJson;
	}
	public void setConfiguracoesJson(String configuracoesJson) {
		this.configuracoesJson = configuracoesJson;
	}
	public String getIdAnaliseCreditoSearch() {
		return idAnaliseCreditoSearch;
	}
	public void setIdAnaliseCreditoSearch(String idAnaliseCreditoSearch) {
		this.idAnaliseCreditoSearch = idAnaliseCreditoSearch;
	}
	public Integer[] getItensPesquisados() {
		return itensPesquisados;
	}
	public void setItensPesquisados(Integer[] itensPesquisados) {
		this.itensPesquisados = itensPesquisados;
	}
	public String getIdCabecalhoAnaliseCreditoCopia() {
		return idCabecalhoAnaliseCreditoCopia;
	}
	public void setIdCabecalhoAnaliseCreditoCopia(String idCabecalhoAnaliseCreditoCopia) {
		this.idCabecalhoAnaliseCreditoCopia = idCabecalhoAnaliseCreditoCopia;
	}
	public boolean isSalvarConfigScore() {
		return salvarConfigScore;
	}
	public void setSalvarConfigScore(boolean salvarConfigScore) {
		this.salvarConfigScore = salvarConfigScore;
	}
	public String getCanaisConfiguraveisPorRegionalJSON() {
		return canaisConfiguraveisPorRegionalJSON;
	}
	public void setCanaisConfiguraveisPorRegionalJSON(String canaisConfiguraveisPorRegionalJSON) {
		this.canaisConfiguraveisPorRegionalJSON = canaisConfiguraveisPorRegionalJSON;
	}
	public Boolean getCanalConfiguravelPorRegional() {
		return canalConfiguravelPorRegional;
	}
	public void setCanalConfiguravelPorRegional(Boolean canalConfiguravelPorRegional) {
		this.canalConfiguravelPorRegional = canalConfiguravelPorRegional;
	}
}
