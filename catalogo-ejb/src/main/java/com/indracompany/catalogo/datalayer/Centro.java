package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="CENTRO", schema = "CATALOGOPRS_OW")
public class Centro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Centro() {}
	
	@Id
	@Column(name = "IDCENTRO")
	private Integer idCentro;

	@Column(name = "CODIGOAREA")
	private Integer codigoArea;

	@Column(name = "DSLOGRADOURO")
	private String dsLogradouro;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMBAIRRO")
	private String nmBairro;

    @Column(name = "NMCENTRO")
	private String nmCentro;

    @Column(name = "NMCIDADE")
	private String nmCidade;

    @Column(name = "NMDEPOSITO")
	private String nmDeposito;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "NRCENTRO")
	private String nrCentro;

    @Column(name = "NRCEP")
	private String nrCEP;

    @Column(name = "NRCNPJ")
	private String nrCNPJ;

    @Column(name = "NRDEPOSITO")
	private String nrDeposito;

    @Column(name = "NRTELEFONE")
	private String nrTelefone;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL", nullable=false)
	private Canal canal;

	//bi-directional many-to-one association to Operadora
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOPERADORA")
	private Operadora operadora;

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS", nullable=false)
	private OrganizacaoVenda organizacaoVenda;

	//bi-directional many-to-one association to Uf
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUF", nullable=false)
	private Uf uf;

	//bi-directional many-to-one association to Estoqueprodutocentro
	@OneToMany(mappedBy="centro")
	private List<EstoqueProdutoCentro> estoqueProdutoCentroList;

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public String getDsLogradouro() {
		return dsLogradouro;
	}

	public void setDsLogradouro(String dsLogradouro) {
		this.dsLogradouro = dsLogradouro;
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

	public List<EstoqueProdutoCentro> getEstoqueProdutoCentroList() {
		return estoqueProdutoCentroList;
	}

	public void setEstoqueProdutoCentroList(
			List<EstoqueProdutoCentro> estoqueProdutoCentroList) {
		this.estoqueProdutoCentroList = estoqueProdutoCentroList;
	}

	public Integer getIdCentro() {
		return idCentro;
	}

	public void setIdCentro(Integer idCentro) {
		this.idCentro = idCentro;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmBairro() {
		return nmBairro;
	}

	public void setNmBairro(String nmBairro) {
		this.nmBairro = nmBairro;
	}

	public String getNmCentro() {
		return nmCentro;
	}

	public void setNmCentro(String nmCentro) {
		this.nmCentro = nmCentro;
	}

	public String getNmCidade() {
		return nmCidade;
	}

	public void setNmCidade(String nmCidade) {
		this.nmCidade = nmCidade;
	}

	public String getNmDeposito() {
		return nmDeposito;
	}

	public void setNmDeposito(String nmDeposito) {
		this.nmDeposito = nmDeposito;
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

	public String getNrCentro() {
		return nrCentro;
	}

	public void setNrCentro(String nrCentro) {
		this.nrCentro = nrCentro;
	}

	public String getNrCEP() {
		return nrCEP;
	}

	public void setNrCEP(String nrCEP) {
		this.nrCEP = nrCEP;
	}

	public String getNrCNPJ() {
		return nrCNPJ;
	}

	public void setNrCNPJ(String nrCNPJ) {
		this.nrCNPJ = nrCNPJ;
	}

	public String getNrDeposito() {
		return nrDeposito;
	}

	public void setNrDeposito(String nrDeposito) {
		this.nrDeposito = nrDeposito;
	}

	public String getNrTelefone() {
		return nrTelefone;
	}

	public void setNrTelefone(String nrTelefone) {
		this.nrTelefone = nrTelefone;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public OrganizacaoVenda getOrganizacaoVenda() {
		return organizacaoVenda;
	}

	public void setOrganizacaoVenda(OrganizacaoVenda organizacaoVenda) {
		this.organizacaoVenda = organizacaoVenda;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
}