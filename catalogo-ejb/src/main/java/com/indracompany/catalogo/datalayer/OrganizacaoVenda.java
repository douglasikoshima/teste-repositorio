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
@Table(name="ORGANIZACAOVENDAS", schema = "CATALOGOPRS_OW")
@NamedQuery(name = "OrganizacaoVenda.findAll", query = "SELECT ov FROM OrganizacaoVenda ov order by ov.sgOrganizacaoVendas")
public class OrganizacaoVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OrganizacaoVenda() {}
	
	@Id
	@Column(name = "IDORGANIZACAOVENDAS")
	private Integer idOrganizacaoVendas;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMORGANIZACAOVENDAS")
	private String nmOrganizacaoVendas;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGORGANIZACAOVENDAS")
	private String sgOrganizacaoVendas;
    
    //  bi-directional many-to-one association to Operadora
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOPERADORA")
	private Operadora operadora;
    
	//bi-directional many-to-one association to Arearegistro
	@OneToMany(mappedBy="organizacaoVenda")
	private List<AreaRegistro> areaRegistroList;

	//bi-directional many-to-one association to Arearegistroorgvnd
	@OneToMany(mappedBy="organizacaoVenda")
	private List<AreaRegistroOrgVnd> areaRegistroOrgVndList;

	//bi-directional many-to-one association to Matrizofertaitempreco
	@OneToMany(mappedBy="organizacaoVenda")
	private List<MatrizOfertaItemPreco> matrizOfertaItemPrecoList;

	public List<AreaRegistro> getAreaRegistroList() {
		return areaRegistroList;
	}

	public void setAreaRegistroList(List<AreaRegistro> areaRegistroList) {
		this.areaRegistroList = areaRegistroList;
	}

	public List<AreaRegistroOrgVnd> getAreaRegistroOrgVndList() {
		return areaRegistroOrgVndList;
	}

	public void setAreaRegistroOrgVndList(
			List<AreaRegistroOrgVnd> areaRegistroOrgVndList) {
		this.areaRegistroOrgVndList = areaRegistroOrgVndList;
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

	public Integer getIdOrganizacaoVendas() {
		return idOrganizacaoVendas;
	}

	public void setIdOrganizacaoVendas(Integer idOrganizacaoVendas) {
		this.idOrganizacaoVendas = idOrganizacaoVendas;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public List<MatrizOfertaItemPreco> getMatrizOfertaItemPrecoList() {
		return matrizOfertaItemPrecoList;
	}

	public void setMatrizOfertaItemPrecoList(
			List<MatrizOfertaItemPreco> matrizOfertaItemPrecoList) {
		this.matrizOfertaItemPrecoList = matrizOfertaItemPrecoList;
	}

	public String getNmOrganizacaoVendas() {
		return nmOrganizacaoVendas;
	}

	public void setNmOrganizacaoVendas(String nmOrganizacaoVendas) {
		this.nmOrganizacaoVendas = nmOrganizacaoVendas;
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

	public String getSgOrganizacaoVendas() {
		return sgOrganizacaoVendas;
	}

	public void setSgOrganizacaoVendas(String sgOrganizacaoVendas) {
		this.sgOrganizacaoVendas = sgOrganizacaoVendas;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}
}