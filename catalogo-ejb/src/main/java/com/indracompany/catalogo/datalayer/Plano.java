package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANO", schema = "CATALOGOPRS_OW")
public class Plano implements Serializable {
	private static final long serialVersionUID = 1L;
	
    public Plano() {}
    
    public Plano(Integer idPlano) {
    	this.idPlano = idPlano;
    }
	
	@Id
	@Column(name = "IDPLANO", unique=true, nullable=false)
	private Integer idPlano;

	@Column(name = "ATIVAWIFI")
	private String ativaWifi;

	@Column(name = "CDANATEL")
	private String cdAnatel;

	@Column(name = "CDPERCONTRATO")
	private String cdPerContrato;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "DIASEXPIRACAO")
	private Integer diasExpiracao;

	@Column(name = "DSCOMERCIALAUXILIAR")
	private String dsComercialAuxiliar;

	@Column(name = "DSDADOS")
	private String dsDados;

	@Column(name = "DSMINUTAGEM")
	private String dsMinutagem;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

	@Column(name = "IDCLASSIFICACAOPLANO")
	private Integer idClassificacaoPlano;

	@Column(name = "IDCOMBINACAOIMAGEMPLANO")
	private Integer idCombinacaoImagemPlano;

	@Column(name = "INDALTERACAOLEGADO")
	private String indAlteracaoLegado;

	@Column(name = "INDAPROVISIONA")
	private String indAprovisiona;

	@Column(name = "INDEXPIRACAOAUTOMATICA")
	private String indExpiracaoAutomatica;

	@Column(name = "INDISPONIVEL")
	private String inDisponivel;

	@Column(name = "INDISPONIVELLEGADO", nullable = false)
	private String inDisponivelLegado;

	@Column(name = "INDRESTRICAOEQUIP")
	private String indRestricaoEquip;

	@Column(name = "INDTITDEP")
	private String indTitDep;

	@Column(name = "NMCOMERCIAL")
	private String nmComercial;

	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	@Column(name = "QTDEMAXDEPENDCATALOGO")
	private Integer qtdeMaxDependCatalogo;

	@Column(name = "QTDEMAXDEPENDLEGADO")
	private Integer qtdeMaxDependLegado;

	@Column(name = "QTDEMAXIMADEPENDENTES")
	private Integer qtdeMaximaDependentes;

	@Column(name = "QTDEMINIMADEPENDENTES")
	private Integer qtdeMinimaDependentes;

	@Column(name = "QTMINFRANQUIA")
	private Integer qtMinFranquia;

	@Column(name = "QTPERCONTRATO")
	private Integer qtPerContrato;

	@Column(name = "QTPERMINIMOCONTR")
	private Integer qtPerMinimoContr;

	@Column(name = "VALORASSINATURAMENSAL")
	private Integer valorAssinaturaMensal;
	
	@Column(name = "IN4G")
	private String in4g;

	//bi-directional many-to-one association to Acessoplano
	@OneToMany(mappedBy="plano")
	private List<AcessoPlano> acessoPlanoList;
	
	//bi-directional many-to-one association to Comboofertaplanoservico
	@OneToMany(mappedBy="plano")
	private List<ComboOfertaPlanoServico> comboOfertaPlanoServicoList;

	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCATEGORIALEGADO")
	private Categoria categoria;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA")
	private Plataforma plataforma;

	/*bi-directional many-to-one association to Regional
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDREGIONAL")
	private Regional regional;*/

	//bi-directional many-to-one association to Tipoplano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOPLANO")
	private TipoPlano tipoPlano;

	//bi-directional many-to-one association to Planoanalisecredito
	@OneToMany(mappedBy="plano")
	private List<PlanoAnaliseCredito> planoAnaliseCreditoList;

	//bi-directional many-to-one association to Planocsa
	@OneToMany(mappedBy="plano")
	@OrderBy("valorAssinaturaMensal DESC")
	private List<PlanoCSA> planoCSAList;

	//bi-directional many-to-one association to Planoplataforma
	@OneToMany(mappedBy="plano")
	private List<PlanoPlataforma> planoPlataformaList;

	//bi-directional many-to-one association to Planorestricoe
	@OneToMany(mappedBy="plano")
	private List<PlanoRestricoes> planoRestricoesList;

	//bi-directional many-to-one association to Planoservico
	@OneToMany(mappedBy="plano")
	private List<PlanoServico> planoServicosList;

	//bi-directional many-to-one association to Planotecnologia
	@OneToMany(mappedBy="plano")
	private List<PlanoTecnologia> planoTecnologiaList;

	//bi-directional many-to-one association to Planotipoassinatura
	@OneToMany(mappedBy="plano")
	private List<PlanoTipoAssinatura> planoTipoAssinaturaList;

	//bi-directional many-to-one association to Produtoplano
	@OneToMany(mappedBy="plano")
	private List<ProdutoPlano> produtoPlanos;

	//bi-directional many-to-one association to Restricao
	@OneToMany(mappedBy="plano")
	private List<Restricao> restricaoList;

	//bi-directional many-to-one association to Restricaoitem
	@OneToMany(mappedBy="plano")
	private List<RestricaoItem> restricaoItemList;

	//bi-directional many-to-one association to Restricaoitem
	@OneToOne(mappedBy="plano")
	private PlanoCategoriaScore planoCategoriaScore;

	//bi-directional many-to-one association to Restricaoitem
	@OneToOne(mappedBy="plano")
	private SistemaPlano sistemaPlano;
	
	@OneToMany(mappedBy = "plano", fetch = FetchType.LAZY)
	private List<PlanoUfRestricao> planoUfRestricaoList;
	
	@ManyToMany
	@JoinTable(schema = "CATALOGOPRS_OW", name = "PLANOSUBSEGMENTO", joinColumns = @JoinColumn(name = "IDPLANO"), inverseJoinColumns = @JoinColumn(name = "IDSUBSEGMENTO"))
	private List<Segmento> segmentoList;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "plano")
	private PlanoSegmento planoSegmento;
	
	@ManyToMany
	@JoinTable(name = "PLANOPLATAFORMA", joinColumns = @JoinColumn(name = "IDPLANO"), inverseJoinColumns = @JoinColumn(name = "IDPLATAFORMA"))
	private List<Plataforma> plataformaList;
	
	public List<AcessoPlano> getAcessoPlanoList() {
		return acessoPlanoList;
	}

	public void setAcessoPlanoList(List<AcessoPlano> acessoPlanoList) {
		this.acessoPlanoList = acessoPlanoList;
	}

	public String getAtivaWifi() {
		return ativaWifi;
	}

	public void setAtivaWifi(String ativaWifi) {
		this.ativaWifi = ativaWifi;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getCdAnatel() {
		return cdAnatel;
	}

	public void setCdAnatel(String cdAnatel) {
		this.cdAnatel = cdAnatel;
	}

	public String getCdPerContrato() {
		return cdPerContrato;
	}

	public void setCdPerContrato(String cdPerContrato) {
		this.cdPerContrato = cdPerContrato;
	}

	public List<ComboOfertaPlanoServico> getComboOfertaPlanoServicoList() {
		return comboOfertaPlanoServicoList;
	}

	public void setComboOfertaPlanoServicoList(
			List<ComboOfertaPlanoServico> comboOfertaPlanoServicoList) {
		this.comboOfertaPlanoServicoList = comboOfertaPlanoServicoList;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDiasExpiracao() {
		return diasExpiracao;
	}

	public void setDiasExpiracao(Integer diasExpiracao) {
		this.diasExpiracao = diasExpiracao;
	}

	public String getDsComercialAuxiliar() {
		return dsComercialAuxiliar;
	}

	public void setDsComercialAuxiliar(String dsComercialAuxiliar) {
		this.dsComercialAuxiliar = dsComercialAuxiliar;
	}

	public String getDsDados() {
		return dsDados;
	}

	public void setDsDados(String dsDados) {
		this.dsDados = dsDados;
	}

	public String getDsMinutagem() {
		return dsMinutagem;
	}

	public void setDsMinutagem(String dsMinutagem) {
		this.dsMinutagem = dsMinutagem;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdClassificacaoPlano() {
		return idClassificacaoPlano;
	}

	public void setIdClassificacaoPlano(Integer idClassificacaoPlano) {
		this.idClassificacaoPlano = idClassificacaoPlano;
	}

	public Integer getIdCombinacaoImagemPlano() {
		return idCombinacaoImagemPlano;
	}

	public void setIdCombinacaoImagemPlano(Integer idCombinacaoImagemPlano) {
		this.idCombinacaoImagemPlano = idCombinacaoImagemPlano;
	}

	public Integer getIdPlano() {
		return idPlano;
	}

	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}

	public String getIndAlteracaoLegado() {
		return indAlteracaoLegado;
	}

	public void setIndAlteracaoLegado(String indAlteracaoLegado) {
		this.indAlteracaoLegado = indAlteracaoLegado;
	}

	public String getIndAprovisiona() {
		return indAprovisiona;
	}

	public void setIndAprovisiona(String indAprovisiona) {
		this.indAprovisiona = indAprovisiona;
	}

	public String getIndExpiracaoAutomatica() {
		return indExpiracaoAutomatica;
	}

	public void setIndExpiracaoAutomatica(String indExpiracaoAutomatica) {
		this.indExpiracaoAutomatica = indExpiracaoAutomatica;
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

	public String getIndRestricaoEquip() {
		return indRestricaoEquip;
	}

	public void setIndRestricaoEquip(String indRestricaoEquip) {
		this.indRestricaoEquip = indRestricaoEquip;
	}

	public String getIndTitDep() {
		return indTitDep;
	}

	public void setIndTitDep(String indTitDep) {
		this.indTitDep = indTitDep;
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

	public List<PlanoAnaliseCredito> getPlanoAnaliseCreditoList() {
		return planoAnaliseCreditoList;
	}

	public void setPlanoAnaliseCreditoList(
			List<PlanoAnaliseCredito> planoAnaliseCreditoList) {
		this.planoAnaliseCreditoList = planoAnaliseCreditoList;
	}

	public List<PlanoCSA> getPlanoCSAList() {
		return planoCSAList;
	}

	public void setPlanoCSAList(List<PlanoCSA> planoCSAList) {
		this.planoCSAList = planoCSAList;
	}

	public List<PlanoPlataforma> getPlanoPlataformaList() {
		return planoPlataformaList;
	}

	public void setPlanoPlataformaList(List<PlanoPlataforma> planoPlataformaList) {
		this.planoPlataformaList = planoPlataformaList;
	}

	public List<PlanoRestricoes> getPlanoRestricoesList() {
		return planoRestricoesList;
	}

	public void setPlanoRestricoesList(List<PlanoRestricoes> planoRestricoesList) {
		this.planoRestricoesList = planoRestricoesList;
	}

	public List<PlanoServico> getPlanoServicosList() {
		return planoServicosList;
	}

	public void setPlanoServicosList(List<PlanoServico> planoServicosList) {
		this.planoServicosList = planoServicosList;
	}

	public List<PlanoTecnologia> getPlanoTecnologiaList() {
		return planoTecnologiaList;
	}

	public void setPlanoTecnologiaList(List<PlanoTecnologia> planoTecnologiaList) {
		this.planoTecnologiaList = planoTecnologiaList;
	}

	public List<PlanoTipoAssinatura> getPlanoTipoAssinaturaList() {
		return planoTipoAssinaturaList;
	}

	public void setPlanoTipoAssinaturaList(
			List<PlanoTipoAssinatura> planoTipoAssinaturaList) {
		this.planoTipoAssinaturaList = planoTipoAssinaturaList;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public List<ProdutoPlano> getProdutoPlanos() {
		return produtoPlanos;
	}

	public void setProdutoPlanos(List<ProdutoPlano> produtoPlanos) {
		this.produtoPlanos = produtoPlanos;
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

	public Integer getQtdeMaximaDependentes() {
		return qtdeMaximaDependentes;
	}

	public void setQtdeMaximaDependentes(Integer qtdeMaximaDependentes) {
		this.qtdeMaximaDependentes = qtdeMaximaDependentes;
	}

	public Integer getQtdeMinimaDependentes() {
		return qtdeMinimaDependentes;
	}

	public void setQtdeMinimaDependentes(Integer qtdeMinimaDependentes) {
		this.qtdeMinimaDependentes = qtdeMinimaDependentes;
	}

	public Integer getQtMinFranquia() {
		return qtMinFranquia;
	}

	public void setQtMinFranquia(Integer qtMinFranquia) {
		this.qtMinFranquia = qtMinFranquia;
	}

	public Integer getQtPerContrato() {
		return qtPerContrato;
	}

	public void setQtPerContrato(Integer qtPerContrato) {
		this.qtPerContrato = qtPerContrato;
	}

	public Integer getQtPerMinimoContr() {
		return qtPerMinimoContr;
	}

	public void setQtPerMinimoContr(Integer qtPerMinimoContr) {
		this.qtPerMinimoContr = qtPerMinimoContr;
	}

	/*public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}*/

	public List<RestricaoItem> getRestricaoItemList() {
		return restricaoItemList;
	}

	public void setRestricaoItemList(List<RestricaoItem> restricaoItemList) {
		this.restricaoItemList = restricaoItemList;
	}

	public List<Restricao> getRestricaoList() {
		return restricaoList;
	}

	public void setRestricaoList(List<Restricao> restricaoList) {
		this.restricaoList = restricaoList;
	}

	public TipoPlano getTipoPlano() {
		return tipoPlano;
	}

	public void setTipoPlano(TipoPlano tipoPlano) {
		this.tipoPlano = tipoPlano;
	}

	public Integer getValorAssinaturaMensal() {
		return valorAssinaturaMensal;
	}

	public void setValorAssinaturaMensal(Integer valorAssinaturaMensal) {
		this.valorAssinaturaMensal = valorAssinaturaMensal;
	}

	public PlanoCategoriaScore getPlanoCategoriaScore() {
		return planoCategoriaScore;
	}

	public void setPlanoCategoriaScore(PlanoCategoriaScore planoCategoriaScore) {
		this.planoCategoriaScore = planoCategoriaScore;
	}

	public SistemaPlano getSistemaPlano() {
		return sistemaPlano;
	}

	public void setSistemaPlano(SistemaPlano sistemaPlano) {
		this.sistemaPlano = sistemaPlano;
	}

	public List<PlanoUfRestricao> getPlanoUfRestricaoList() {
		return planoUfRestricaoList;
	}

	public void setPlanoUfRestricaoList(List<PlanoUfRestricao> planoUfRestricaoList) {
		this.planoUfRestricaoList = planoUfRestricaoList;
	}

	public String getIn4g() {
		return in4g;
	}

	public void setIn4g(String in4g) {
		this.in4g = in4g;
	}

	public List<Segmento> getSegmentoList() {
		return segmentoList;
	}

	public void setSegmentoList(List<Segmento> segmentoList) {
		this.segmentoList = segmentoList;
	}

	public List<Plataforma> getPlataformaList() {
		return plataformaList;
	}

	public void setPlataformaList(List<Plataforma> plataformaList) {
		this.plataformaList = plataformaList;
	}
	
	public PlanoSegmento getPlanoSegmento() {
		return planoSegmento;
	}

	public void setPlanoSegmento(PlanoSegmento planoSegmento) {
		this.planoSegmento = planoSegmento;
	}
}