package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="COMBOOFERTASEGMENTO", schema = "CATALOGOPRS_OW")
public class ComboOfertaSegmento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ComboOfertaSegmento() {}
	
	@Id
	@Column(name = "IDCOMBOOFERTASEGMENTO")
	private Integer idcomboofertasegmento;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtcriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtfinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtinicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtultimaalteracao;

    @Column(name = "INDISPONIVEL")
	private String indisponivel;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmusuarioalteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmusuariocriacao;

    @Column(name = "SGSEGMENTO")
	private String sgsegmento;

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

	public Date getDtcriacao() {
		return dtcriacao;
	}

	public void setDtcriacao(Date dtcriacao) {
		this.dtcriacao = dtcriacao;
	}

	public Date getDtfinal() {
		return dtfinal;
	}

	public void setDtfinal(Date dtfinal) {
		this.dtfinal = dtfinal;
	}

	public Date getDtinicial() {
		return dtinicial;
	}

	public void setDtinicial(Date dtinicial) {
		this.dtinicial = dtinicial;
	}

	public Date getDtultimaalteracao() {
		return dtultimaalteracao;
	}

	public void setDtultimaalteracao(Date dtultimaalteracao) {
		this.dtultimaalteracao = dtultimaalteracao;
	}

	public Integer getIdcomboofertasegmento() {
		return idcomboofertasegmento;
	}

	public void setIdcomboofertasegmento(Integer idcomboofertasegmento) {
		this.idcomboofertasegmento = idcomboofertasegmento;
	}

	public String getIndisponivel() {
		return indisponivel;
	}

	public void setIndisponivel(String indisponivel) {
		this.indisponivel = indisponivel;
	}

	public String getNmusuarioalteracao() {
		return nmusuarioalteracao;
	}

	public void setNmusuarioalteracao(String nmusuarioalteracao) {
		this.nmusuarioalteracao = nmusuarioalteracao;
	}

	public String getNmusuariocriacao() {
		return nmusuariocriacao;
	}

	public void setNmusuariocriacao(String nmusuariocriacao) {
		this.nmusuariocriacao = nmusuariocriacao;
	}

	public String getSgsegmento() {
		return sgsegmento;
	}

	public void setSgsegmento(String sgsegmento) {
		this.sgsegmento = sgsegmento;
	}
 }