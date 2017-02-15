package com.indracompany.catalogo.to;

import java.util.Calendar;

public class PlanoTO {
	
	private Integer idPlano;
	private Boolean ativaWifi;
	private CategoriaTO categoriaTO;
	private String cdPlano;
	private String descricao;
	private Calendar dtCriacao;
	private Calendar dtFinal;
	private Calendar dtInicial;
	private Calendar dtUltimaAlteracao;
	private Boolean in4g;
	private String inDisponivel;
	private String inDisponivelLegado;
	private String sgTitularidade;
	private String nmComercial;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private PlataformaTO plataformaTO;
	private Integer qtdeMaxDependCatalogo;
	private Integer qtdeMaxDependLegado;
	private SegmentoTO segmentoTO;
	private SistemaTO sistemaTO;
	
	public PlanoTO() {
		super();
	}
	public PlanoTO(Integer idPlano) {
		super();
		this.idPlano = idPlano;
	}
	public Boolean getAtivaWifi() {
		return ativaWifi;
	}
	public void setAtivaWifi(Boolean ativaWifi) {
		this.ativaWifi = ativaWifi;
	}
	public CategoriaTO getCategoriaTO() {
		return categoriaTO;
	}
	public void setCategoriaTO(CategoriaTO categoriaTO) {
		this.categoriaTO = categoriaTO;
	}
	public String getCdPlano() {
		return cdPlano;
	}
	public void setCdPlano(String cdPlano) {
		this.cdPlano = cdPlano;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Calendar getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Calendar getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(Calendar dtFinal) {
		this.dtFinal = dtFinal;
	}
	public Calendar getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(Calendar dtInicial) {
		this.dtInicial = dtInicial;
	}
	public Calendar getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}
	public void setDtUltimaAlteracao(Calendar dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}
	public Integer getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}
	public Boolean getIn4g() {
		return in4g;
	}
	public void setIn4g(Boolean in4g) {
		this.in4g = in4g;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getInDisponivelLegado() {
		return inDisponivelLegado;
	}
	public void setInDisponivelLegado(String inDisponivelLegado) {
		this.inDisponivelLegado = inDisponivelLegado;
	}
	public String getNmComercial() {
		return nmComercial;
	}
	public void setNmComercial(String nmComercial) {
		this.nmComercial = nmComercial;
	}
	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}
	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}
	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}
	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	public PlataformaTO getPlataformaTO() {
		return plataformaTO;
	}
	public void setPlataformaTO(PlataformaTO plataformaTO) {
		this.plataformaTO = plataformaTO;
	}
	public Integer getQtdeMaxDependCatalogo() {
		return qtdeMaxDependCatalogo;
	}
	public void setQtdeMaxDependCatalogo(Integer qtdeMaxDependCatalogo) {
		this.qtdeMaxDependCatalogo = qtdeMaxDependCatalogo;
	}
	public Integer getQtdeMaxDependLegado() {
		return qtdeMaxDependLegado;
	}
	public void setQtdeMaxDependLegado(Integer qtdeMaxDependLegado) {
		this.qtdeMaxDependLegado = qtdeMaxDependLegado;
	}
	public SegmentoTO getSegmentoTO() {
		return segmentoTO;
	}
	public void setSegmentoTO(SegmentoTO segmentoTO) {
		this.segmentoTO = segmentoTO;
	}
	public String getSgTitularidade() {
		return sgTitularidade;
	}
	public void setSgTitularidade(String sgTitularidade) {
		this.sgTitularidade = sgTitularidade;
	}
	public SistemaTO getSistemaTO() {
		return sistemaTO;
	}
	public void setSistemaTO(SistemaTO sistemaTO) {
		this.sistemaTO = sistemaTO;
	}
}
