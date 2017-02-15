package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name="GrupoProduto.findAll", query="SELECT gp FROM GrupoProduto gp order by gp.nmGrupoProduto")
@SequenceGenerator(name="GRUPOPRODUTO_SQ", sequenceName="CATALOGOPRS_OW.GRUPOPRODUTO_SQ", allocationSize=1)
@Table(name="GRUPOPRODUTO", schema="CATALOGOPRS_OW")
public class GrupoProduto implements Serializable {
	private static final long serialVersionUID = 1L;

    public GrupoProduto() {}
    
	public GrupoProduto(Integer idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}
    
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRUPOPRODUTO_SQ")
	@Column(name = "IDGRUPOPRODUTO")
	private Integer idGrupoProduto;

	@Column(name = "NMGRUPOPRODUTO")
	private String nmGrupoProduto;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFABRICANTE")
	private Fabricante fabricante;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOPRODUTO")
	private TipoProduto tipoProduto;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Column(name = "INFIMVIDA")
	private String inFimVida;

	@Column(name = "DSNOTA")
	private String dsNota;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCORPADRAO")
	private Cor cor;
	
	@OneToMany(mappedBy="grupoProduto", fetch=FetchType.LAZY)
	private List<ProdutoGrupoProduto> produtoGrupoProdutoList; 
	
	@OneToMany(mappedBy="grupoProduto", fetch=FetchType.LAZY)
	private List<ModeloTipoProdutoRestricao> modeloTipoProdutoRestricaoList; 
	
	@OneToMany(mappedBy="grupoProduto", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	private List<Multimidia> multimidiaList;
	
	@OneToMany(mappedBy="grupoProduto", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	private List<GrupoProdutoTecnologia> grupoProdutoTecnologiaList;
	
	@OneToMany(mappedBy="grupoProduto", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	private List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "GRUPOPRODUTOTECNOLOGIA", joinColumns=@JoinColumn(name="IDGRUPOPRODUTO"), inverseJoinColumns=@JoinColumn(name="IDTECNOLOGIA"))
	private List<Tecnologia> tecnologiaList;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "GRUPOPRODUTOCARACTERISTICA", joinColumns=@JoinColumn(name="IDGRUPOPRODUTO"), inverseJoinColumns=@JoinColumn(name="IDCARACTERISTICA"))
	private List<Caracteristica> caracteristicaList;
	
	public String getDsNota() {
		return dsNota;
	}

	public void setDsNota(String dsNota) {
		this.dsNota = dsNota;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdGrupoProduto() {
		return idGrupoProduto;
	}

	public void setIdGrupoProduto(Integer idGrupoProduto) {
		this.idGrupoProduto = idGrupoProduto;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInFimVida() {
		return inFimVida;
	}

	public void setInFimVida(String inFimVida) {
		this.inFimVida = inFimVida;
	}

	public String getNmGrupoProduto() {
		return nmGrupoProduto;
	}

	public void setNmGrupoProduto(String nmGrupoProduto) {
		this.nmGrupoProduto = nmGrupoProduto;
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

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public List<ProdutoGrupoProduto> getProdutoGrupoProdutoList() {
		return produtoGrupoProdutoList;
	}

	public void setProdutoGrupoProdutoList(
			List<ProdutoGrupoProduto> produtoGrupoProdutoList) {
		this.produtoGrupoProdutoList = produtoGrupoProdutoList;
	}

	public List<ModeloTipoProdutoRestricao> getModeloTipoProdutoRestricaoList() {
		return modeloTipoProdutoRestricaoList;
	}

	public void setModeloTipoProdutoRestricaoList(
			List<ModeloTipoProdutoRestricao> modeloTipoProdutoRestricaoList) {
		this.modeloTipoProdutoRestricaoList = modeloTipoProdutoRestricaoList;
	}
	
	public List<Multimidia> getMultimidiaList() {
		return multimidiaList;
	}

	public void setMultimidiaList(List<Multimidia> multimidiaList) {
		this.multimidiaList = multimidiaList;
	}

	public List<GrupoProdutoCaracteristica> getGrupoProdutoCaracteristicaList() {
		return grupoProdutoCaracteristicaList;
	}

	public void setGrupoProdutoCaracteristicaList(
			List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList) {
		this.grupoProdutoCaracteristicaList = grupoProdutoCaracteristicaList;
	}

	public List<GrupoProdutoTecnologia> getGrupoProdutoTecnologiaList() {
		return grupoProdutoTecnologiaList;
	}

	public void setGrupoProdutoTecnologiaList(
			List<GrupoProdutoTecnologia> grupoProdutoTecnologiaList) {
		this.grupoProdutoTecnologiaList = grupoProdutoTecnologiaList;
	}

	public List<Tecnologia> getTecnologiaList() {
		return tecnologiaList;
	}

	public void setTecnologiaList(List<Tecnologia> tecnologiaList) {
		this.tecnologiaList = tecnologiaList;
	}

	public List<Caracteristica> getCaracteristicaList() {
		return caracteristicaList;
	}

	public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
		this.caracteristicaList = caracteristicaList;
	}

}