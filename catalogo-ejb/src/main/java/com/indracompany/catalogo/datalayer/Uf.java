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
@Table(name="UF", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "Uf.findAll", query = "select u from Uf u order by u.nmUf"),
    @NamedQuery(name = "Uf.findByIdOfertafixa", query = "select distinct offar.areaRegistro.uf from OfertafixaFatorAreaRegistro offar where offar.ofertafixa.idOfertafixa = :idOfertafixa order by offar.areaRegistro.uf.nmUf")
})
public class Uf implements Serializable {
	private static final long serialVersionUID = -656881L;
	
	public Uf() {}
	
	public Uf(Integer idUf) {
		this.idUf = idUf;
	}
	
	@Id
	@Column(name = "IDUF")
	private Integer idUf;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMUF")
	private String nmUf;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Arearegistro
	@OneToMany(mappedBy="uf")
	private List<AreaRegistro> areaRegistroList;

	//bi-directional many-to-one association to Centro
	@OneToMany(mappedBy="uf")
	private List<Centro> centroList;

	//bi-directional many-to-one association to Comboofertauf
	@OneToMany(mappedBy="uf")
	private List<ComboOfertaUF> comboOfertaUFList;

	//bi-directional many-to-one association to Grupoprodutorestricoe
	@OneToMany(mappedBy="uf")
	private List<GrupoProdutoRestricoes> grupoProdutoRestricoesList;

	//bi-directional many-to-one association to Planorestricoe
	@OneToMany(mappedBy="uf")
	private List<PlanoRestricoes> planoRestricoesList;

	//bi-directional many-to-one association to Planorestricoesprd
	@OneToMany(mappedBy="uf")
	private List<PlanoRestricoesPRD> planoRestricoesPRDList;

	//bi-directional many-to-one association to Planovariaveisprd
	@OneToMany(mappedBy="uf")
	private List<PlanoVariaveisPRD> planoVariaveisPRDList;

	//bi-directional many-to-one association to Servicorestricoe
	@OneToMany(mappedBy="uf")
	private List<ServicoRestricoes> servicoRestricoesList;

	//bi-directional many-to-one association to Servicorestricoesprd
	@OneToMany(mappedBy="uf")
	private List<ServicoRestricoesPRD> servicoRestricoesPRDList;

	//bi-directional many-to-one association to Regional
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDREGIONAL", nullable=false)
	private Regional regional;

	public List<AreaRegistro> getAreaRegistroList() {
		return areaRegistroList;
	}

	public void setAreaRegistroList(List<AreaRegistro> areaRegistroList) {
		this.areaRegistroList = areaRegistroList;
	}

	public List<Centro> getCentroList() {
		return centroList;
	}

	public void setCentroList(List<Centro> centroList) {
		this.centroList = centroList;
	}

	public List<ComboOfertaUF> getComboOfertaUFList() {
		return comboOfertaUFList;
	}

	public void setComboOfertaUFList(List<ComboOfertaUF> comboOfertaUFList) {
		this.comboOfertaUFList = comboOfertaUFList;
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

	public List<GrupoProdutoRestricoes> getGrupoProdutoRestricoesList() {
		return grupoProdutoRestricoesList;
	}

	public void setGrupoProdutoRestricoesList(
			List<GrupoProdutoRestricoes> grupoProdutoRestricoesList) {
		this.grupoProdutoRestricoesList = grupoProdutoRestricoesList;
	}

	public Integer getIdUf() {
		return idUf;
	}

	public void setIdUf(Integer idUf) {
		this.idUf = idUf;
	}

	public String getNmUf() {
		return nmUf;
	}

	public void setNmUf(String nmUf) {
		this.nmUf = nmUf;
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

	public List<PlanoRestricoesPRD> getPlanoRestricoesPRDList() {
		return planoRestricoesPRDList;
	}

	public void setPlanoRestricoesPRDList(
			List<PlanoRestricoesPRD> planoRestricoesPRDList) {
		this.planoRestricoesPRDList = planoRestricoesPRDList;
	}

	public List<PlanoVariaveisPRD> getPlanoVariaveisPRDList() {
		return planoVariaveisPRDList;
	}

	public void setPlanoVariaveisPRDList(
			List<PlanoVariaveisPRD> planoVariaveisPRDList) {
		this.planoVariaveisPRDList = planoVariaveisPRDList;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public List<ServicoRestricoes> getServicoRestricoesList() {
		return servicoRestricoesList;
	}

	public void setServicoRestricoesList(
			List<ServicoRestricoes> servicoRestricoesList) {
		this.servicoRestricoesList = servicoRestricoesList;
	}

	public List<ServicoRestricoesPRD> getServicoRestricoesPRDList() {
		return servicoRestricoesPRDList;
	}

	public void setServicoRestricoesPRDList(
			List<ServicoRestricoesPRD> servicoRestricoesPRDList) {
		this.servicoRestricoesPRDList = servicoRestricoesPRDList;
	}
}