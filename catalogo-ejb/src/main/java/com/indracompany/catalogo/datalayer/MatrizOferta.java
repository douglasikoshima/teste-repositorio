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
@Table(name="MATRIZOFERTA", schema = "CATALOGOPRS_OW")
public class MatrizOferta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public MatrizOferta() {}
	
	@Id
	@Column(name = "IDMATRIZOFERTA")
	private Integer idMatrizOferta;

    @Temporal( TemporalType.DATE)
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

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMMATRIZOFERTA")
	private String nmMatrizOferta;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGTIPOPEDIDO")
	private String sgTipoPedido;

	//bi-directional many-to-one association to Matrizofertatipo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMATRIZOFERTATIPO")
	private MatrizOfertaTipo matrizOfertaTipo;

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

	public Integer getIdMatrizOferta() {
		return idMatrizOferta;
	}

	public void setIdMatrizOferta(Integer idMatrizOferta) {
		this.idMatrizOferta = idMatrizOferta;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public MatrizOfertaTipo getMatrizOfertaTipo() {
		return matrizOfertaTipo;
	}

	public void setMatrizOfertaTipo(MatrizOfertaTipo matrizOfertaTipo) {
		this.matrizOfertaTipo = matrizOfertaTipo;
	}

	public String getNmMatrizOferta() {
		return nmMatrizOferta;
	}

	public void setNmMatrizOferta(String nmMatrizOferta) {
		this.nmMatrizOferta = nmMatrizOferta;
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

	public String getSgTipoPedido() {
		return sgTipoPedido;
	}

	public void setSgTipoPedido(String sgTipoPedido) {
		this.sgTipoPedido = sgTipoPedido;
	}
}