package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "Tecnologia.findAll", query = "SELECT t FROM Tecnologia t order by t.nmTecnologia ")
@Table(name="TECNOLOGIA", schema = "CATALOGOPRS_OW")
public class Tecnologia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Tecnologia() {
	}
	
	public Tecnologia(Integer idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	
	@Id
	@Column(name = "IDTECNOLOGIA")
	private Integer idTecnologia;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMTECNOLOGIA")
	private String nmTecnologia;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGTECNOLOGIA")
	private String sgTecnologia;

	//bi-directional many-to-one association to Disponibilidadeservico
	@OneToMany(mappedBy="tecnologia")
	private List<DisponibilidadeServico> disponibilidadeServicoList;

	//bi-directional many-to-one association to Grupoprodutotecnologia
	@OneToMany(mappedBy="tecnologia")
	private List<GrupoProdutoTecnologia> grupoProdutoTecnologiaList;

	//bi-directional many-to-one association to Planorestricoe
	@OneToMany(mappedBy="tecnologia")
	private List<PlanoRestricoes> planoRestricoesList;

	//bi-directional many-to-one association to Planotecnologia
	@OneToMany(mappedBy="tecnologia")
	private List<PlanoTecnologia> planoTecnologiaList;

	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="tecnologia")
	private List<Produto> produtoList;

	//bi-directional many-to-one association to Servicorestricoe
	@OneToMany(mappedBy="tecnologia")
	private List<ServicoRestricoes> servicoRestricoesList;

	//bi-directional many-to-one association to Servicotecnologia
	@OneToMany(mappedBy="tecnologia")
	private List<ServicoTecnologia> servicoTecnologiaList;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIAPAI")
	private Tecnologia tecnologia;

	//bi-directional many-to-one association to Tecnologia
	@OneToMany(mappedBy="tecnologia")
	private List<Tecnologia> tecnologiaList;

	//bi-directional many-to-one association to Tecnologiatpfrequencia
	@OneToMany(mappedBy="tecnologia")
	private List<TecnologiaTpFrequencia> tecnologiaTpFrequenciaList;
	
	@ManyToMany
	@JoinTable(name = "SERVICOTECNOLOGIA", joinColumns = @JoinColumn(name = "IDTECNOLOGIA"), inverseJoinColumns = @JoinColumn(name = "IDSERVICO"))
	private List<Servico> servicoList;

	public List<DisponibilidadeServico> getDisponibilidadeServicoList() {
		return disponibilidadeServicoList;
	}

	public void setDisponibilidadeServicoList(
			List<DisponibilidadeServico> disponibilidadeServicoList) {
		this.disponibilidadeServicoList = disponibilidadeServicoList;
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

	public List<GrupoProdutoTecnologia> getGrupoProdutoTecnologiaList() {
		return grupoProdutoTecnologiaList;
	}

	public void setGrupoProdutoTecnologiaList(
			List<GrupoProdutoTecnologia> grupoProdutoTecnologiaList) {
		this.grupoProdutoTecnologiaList = grupoProdutoTecnologiaList;
	}

	public Integer getIdTecnologia() {
		return idTecnologia;
	}

	public void setIdTecnologia(Integer idTecnologia) {
		this.idTecnologia = idTecnologia;
	}

	public String getNmTecnologia() {
		return nmTecnologia;
	}

	public void setNmTecnologia(String nmTecnologia) {
		this.nmTecnologia = nmTecnologia;
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

	public List<PlanoRestricoes> getPlanoRestricoesList() {
		return planoRestricoesList;
	}

	public void setPlanoRestricoesList(List<PlanoRestricoes> planoRestricoesList) {
		this.planoRestricoesList = planoRestricoesList;
	}

	public List<PlanoTecnologia> getPlanoTecnologiaList() {
		return planoTecnologiaList;
	}

	public void setPlanoTecnologiaList(List<PlanoTecnologia> planoTecnologiaList) {
		this.planoTecnologiaList = planoTecnologiaList;
	}

	public List<Produto> getProdutoList() {
		return produtoList;
	}

	public void setProdutoList(List<Produto> produtoList) {
		this.produtoList = produtoList;
	}

	public List<ServicoRestricoes> getServicoRestricoesList() {
		return servicoRestricoesList;
	}

	public void setServicoRestricoesList(
			List<ServicoRestricoes> servicoRestricoesList) {
		this.servicoRestricoesList = servicoRestricoesList;
	}

	public List<ServicoTecnologia> getServicoTecnologiaList() {
		return servicoTecnologiaList;
	}

	public void setServicoTecnologiaList(
			List<ServicoTecnologia> servicoTecnologiaList) {
		this.servicoTecnologiaList = servicoTecnologiaList;
	}

	public String getSgTecnologia() {
		return sgTecnologia;
	}

	public void setSgTecnologia(String sgTecnologia) {
		this.sgTecnologia = sgTecnologia;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public List<Tecnologia> getTecnologiaList() {
		return tecnologiaList;
	}

	public void setTecnologiaList(List<Tecnologia> tecnologiaList) {
		this.tecnologiaList = tecnologiaList;
	}

	public List<TecnologiaTpFrequencia> getTecnologiaTpFrequenciaList() {
		return tecnologiaTpFrequenciaList;
	}

	public void setTecnologiaTpFrequenciaList(
			List<TecnologiaTpFrequencia> tecnologiaTpFrequenciaList) {
		this.tecnologiaTpFrequenciaList = tecnologiaTpFrequenciaList;
	}

	public List<Servico> getServicoList() {
		return servicoList;
	}

	public void setServicoList(List<Servico> servicoList) {
		this.servicoList = servicoList;
	}
}