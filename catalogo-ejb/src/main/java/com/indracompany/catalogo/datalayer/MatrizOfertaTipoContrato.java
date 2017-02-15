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
@Table(name="MATRIZOFERTATIPOCONTRATO", schema = "CATALOGOPRS_OW")
public class MatrizOfertaTipoContrato implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MatrizOfertaTipoContrato() {}
	
	@Id
	@Column(name = "IDMATRIZOFERTATIPOCONTRATO")
	private Integer idMatrizOfertaTipoContrato;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "VALOR")
	private Integer valor;

	//bi-directional many-to-one association to Csa
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCSA", nullable=false)
	private CSA csa;

	//bi-directional many-to-one association to Matrizofertatipo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMATRIZOFERTATIPO", nullable=false)
	private MatrizOfertaTipo matrizOfertaTipo;

	//bi-directional many-to-one association to Sistemaplano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMAPLANO", nullable=false)
	private SistemaPlano sistemaPlano;

	//bi-directional many-to-one association to Sistemaservico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMASERVICO", nullable=false)
	private SistemaServico sistemaServico;

	public CSA getCsa() {
		return csa;
	}

	public void setCsa(CSA csa) {
		this.csa = csa;
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

	public Integer getIdMatrizOfertaTipoContrato() {
		return idMatrizOfertaTipoContrato;
	}

	public void setIdMatrizOfertaTipoContrato(Integer idMatrizOfertaTipoContrato) {
		this.idMatrizOfertaTipoContrato = idMatrizOfertaTipoContrato;
	}
	
	public MatrizOfertaTipo getMatrizOfertaTipo() {
		return matrizOfertaTipo;
	}

	public void setMatrizOfertaTipo(MatrizOfertaTipo matrizOfertaTipo) {
		this.matrizOfertaTipo = matrizOfertaTipo;
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

	public SistemaPlano getSistemaPlano() {
		return sistemaPlano;
	}

	public void setSistemaPlano(SistemaPlano sistemaPlano) {
		this.sistemaPlano = sistemaPlano;
	}

	public SistemaServico getSistemaServico() {
		return sistemaServico;
	}

	public void setSistemaServico(SistemaServico sistemaServico) {
		this.sistemaServico = sistemaServico;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
}