package com.indracompany.catalogo.to;

import java.util.Date;
import java.util.List;
/**
 * @author Luciano
 *
 */
public class PlanoParametroTO extends PaginacaoDataTableTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3934363683769005671L;

	private Integer tipoPesquisa;
	private Integer idPlataforma;
	private String classificacao;
	private Integer idPlano;
	private String nomeTecnico;
	private String nomeComercial;
	private List<UfTO> UFs;
	private List<TipoPlanoTO> tipoPlanos;
	private Boolean WifiAtivo;
	private Boolean disponivel;
	private Boolean disponivelLegado;
	private Date dataUltimaAlteracao;
	private String nomeUsuarioAlteracao;
	private Integer maximoDependenteLegado;
	private Integer tipoPlano;
	public String getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}
	public Boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
	public Integer getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}
	public Integer getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public String getNomeComercial() {
		return nomeComercial;
	}
	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}
	public String getNomeTecnico() {
		return nomeTecnico;
	}
	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}
	public Integer getTipoPesquisa() {
		return tipoPesquisa;
	}
	public void setTipoPesquisa(Integer tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}
	public List<UfTO> getUFs() {
		return UFs;
	}
	public void setUFs(List<UfTO> fs) {
		UFs = fs;
	}
	public Boolean getWifiAtivo() {
		return WifiAtivo;
	}
	public void setWifiAtivo(Boolean wifiAtivo) {
		WifiAtivo = wifiAtivo;
	}
	public List<TipoPlanoTO> getTipoPlanos() {
		return tipoPlanos;
	}
	public void setTipoPlanos(List<TipoPlanoTO> tipoPlanos) {
		this.tipoPlanos = tipoPlanos;
	}
	public Date getDataUltimaAlteracao() {
		return dataUltimaAlteracao;
	}
	public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
		this.dataUltimaAlteracao = dataUltimaAlteracao;
	}
	public String getNomeUsuarioAlteracao() {
		return nomeUsuarioAlteracao;
	}
	public void setNomeUsuarioAlteracao(String nomeUsuarioAlteracao) {
		this.nomeUsuarioAlteracao = nomeUsuarioAlteracao;
	}
	public Integer getMaximoDependenteLegado() {
		return maximoDependenteLegado;
	}
	public void setMaximoDependenteLegado(Integer maximoDependenteLegado) {
		this.maximoDependenteLegado = maximoDependenteLegado;
	}
	public Boolean getDisponivelLegado() {
		return disponivelLegado;
	}
	public void setDisponivelLegado(Boolean disponivelLegado) {
		this.disponivelLegado = disponivelLegado;
	}
	public Integer getTipoPlano() {
		return tipoPlano;
	}
	public void setTipoPlano(Integer tipoPlano) {
		this.tipoPlano = tipoPlano;
	}
	
}
