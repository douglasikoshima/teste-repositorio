package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="COMBOOFERTACARTEIRA", schema = "CATALOGOPRS_OW")
public class ComboOfertaCarteira implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ComboOfertaCarteira() {}
	
	@Id
	@Column(name = "IDCOMBOOFERTACARTEIRA")
	private Integer idcomboofertacarteira;

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

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SGCARTEIRA")
	private String sgCarteira;

	//bi-directional many-to-one association to Combooferta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOMBOOFERTA", nullable=false)
	private ComboOferta comboOferta;

	public ComboOferta getComboOferta() {
		return comboOferta;
	}

	public void setComboOferta(ComboOferta comboOferta) {
		this.comboOferta = comboOferta;
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

	public Integer getIdcomboofertacarteira() {
		return idcomboofertacarteira;
	}

	public void setIdcomboofertacarteira(Integer idcomboofertacarteira) {
		this.idcomboofertacarteira = idcomboofertacarteira;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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

	public String getSgCarteira() {
		return sgCarteira;
	}

	public void setSgCarteira(String sgCarteira) {
		this.sgCarteira = sgCarteira;
	}
}