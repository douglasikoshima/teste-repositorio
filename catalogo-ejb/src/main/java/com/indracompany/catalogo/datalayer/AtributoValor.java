package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="ATRIBUTOVALOR", schema = "CATALOGOPRS_OW")
public class AtributoValor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AtributoValor() {}
	
	@Id
	@Column(name = "IDVALORATRIBUTO")
	private Integer idvaloratributo;

	@Column(name = "DSCVALOR")
	private String dscvalor;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtcriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtultimaalteracao;

    @Column(name = "INDEFAULT")
	private String indefault;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmusuarioalteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmusuariocriacao;

    @Column(name = "SVCATTRSEQ")
	private String svcattrseq;

    @Column(name = "VALOR")
	private String valor;

	//bi-directional many-to-one association to Atributo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDATRIBUTO", nullable=false)
	private Atributo atributo;

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	public String getDscvalor() {
		return dscvalor;
	}

	public void setDscvalor(String dscvalor) {
		this.dscvalor = dscvalor;
	}

	public Date getDtcriacao() {
		return dtcriacao;
	}

	public void setDtcriacao(Date dtcriacao) {
		this.dtcriacao = dtcriacao;
	}

	public Date getDtultimaalteracao() {
		return dtultimaalteracao;
	}

	public void setDtultimaalteracao(Date dtultimaalteracao) {
		this.dtultimaalteracao = dtultimaalteracao;
	}

	public Integer getIdvaloratributo() {
		return idvaloratributo;
	}

	public void setIdvaloratributo(Integer idvaloratributo) {
		this.idvaloratributo = idvaloratributo;
	}

	public String getIndefault() {
		return indefault;
	}

	public void setIndefault(String indefault) {
		this.indefault = indefault;
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

	public String getSvcattrseq() {
		return svcattrseq;
	}

	public void setSvcattrseq(String svcattrseq) {
		this.svcattrseq = svcattrseq;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}