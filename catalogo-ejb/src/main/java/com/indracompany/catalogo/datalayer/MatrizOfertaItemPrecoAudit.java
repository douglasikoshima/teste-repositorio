package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="MATRIZOFERTAITEMPRECOAUDIT", schema = "CATALOGOPRS_OW")
public class MatrizOfertaItemPrecoAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MatrizOfertaItemPrecoAudit() {}
	 
	@Id
	@Column(name = "IDMATRIZOFERTAITEMPRECOAUDIT")
	private Integer idMatrizOfertaItemPrecoAudit;

	@Column(name = "CDADABAS")
	private String cdAdabas;

	@Column(name = "CODIGOAREA")
	private Integer codigoArea;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTALTERACAO")
	private Date dtAlteracao;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTINICIAL")
	private Date dtInicial;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioalteracao;

    @Column(name = "VALORANTIGO")
	private Float valorAntigo;

    @Column(name = "VALORNOVO")
	private Float valorNovo;

	//bi-directional many-to-one association to Acao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDACAO")
	private Acao acao;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANALDISTRIBUICAO", nullable=false)
	private Canal canal;

	//bi-directional many-to-one association to Matrizofertaitem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMATRIZOFERTAITEM", nullable=false)
	private MatrizOfertaItem matrizOfertaItem;

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS", nullable=false)
	private OrganizacaoVenda organizacaoVenda;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public String getCdAdabas() {
		return cdAdabas;
	}

	public void setCdAdabas(String cdAdabas) {
		this.cdAdabas = cdAdabas;
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
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

	public Integer getIdMatrizOfertaItemPrecoAudit() {
		return idMatrizOfertaItemPrecoAudit;
	}

	public void setIdMatrizOfertaItemPrecoAudit(Integer idMatrizOfertaItemPrecoAudit) {
		this.idMatrizOfertaItemPrecoAudit = idMatrizOfertaItemPrecoAudit;
	}

	public MatrizOfertaItem getMatrizOfertaItem() {
		return matrizOfertaItem;
	}

	public void setMatrizOfertaItem(MatrizOfertaItem matrizOfertaItem) {
		this.matrizOfertaItem = matrizOfertaItem;
	}

	public String getNmUsuarioalteracao() {
		return nmUsuarioalteracao;
	}

	public void setNmUsuarioalteracao(String nmUsuarioalteracao) {
		this.nmUsuarioalteracao = nmUsuarioalteracao;
	}

	public OrganizacaoVenda getOrganizacaoVenda() {
		return organizacaoVenda;
	}

	public void setOrganizacaoVenda(OrganizacaoVenda organizacaoVenda) {
		this.organizacaoVenda = organizacaoVenda;
	}

	public Float getValorAntigo() {
		return valorAntigo;
	}

	public void setValorAntigo(Float valorAntigo) {
		this.valorAntigo = valorAntigo;
	}

	public Float getValorNovo() {
		return valorNovo;
	}

	public void setValorNovo(Float valorNovo) {
		this.valorNovo = valorNovo;
	}
}