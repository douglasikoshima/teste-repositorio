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
@Table(name="OFERTASAPPLANOOFERTA", schema = "CATALOGOPRS_OW")
public class OfertaSAPPlanoOferta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OfertaSAPPlanoOferta() {}
	
    @Temporal(TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
    
    @Id
    @Column(name = "IDMATRIZOFERTA")
	private Integer idMatrizOferta;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "SQORDEMOFERTASERVICO")
	private Integer sqOrdemOfertaServico;

    @Column(name = "SQORDEMPLANO")
	private Integer sqOrdemPlano;

	//bi-directional many-to-one association to Ofertasap
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOFERTASAP")
	private OfertaSAP ofertaSAP;

	//bi-directional many-to-one association to Ofertaservico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDOFERTASERVICO")
	private OfertaServico ofertaServico;

	//bi-directional many-to-one association to Sistemaplano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMAPLANO")
	private SistemaPlano sistemaPlano;

    

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdMatrizOferta() {
		return idMatrizOferta;
	}

	public void setIdMatrizOferta(Integer idMatrizOferta) {
		this.idMatrizOferta = idMatrizOferta;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public OfertaSAP getOfertaSAP() {
		return ofertaSAP;
	}

	public void setOfertaSAP(OfertaSAP ofertaSAP) {
		this.ofertaSAP = ofertaSAP;
	}

	public OfertaServico getOfertaServico() {
		return ofertaServico;
	}

	public void setOfertaServico(OfertaServico ofertaServico) {
		this.ofertaServico = ofertaServico;
	}

	public SistemaPlano getSistemaPlano() {
		return sistemaPlano;
	}

	public void setSistemaPlano(SistemaPlano sistemaPlano) {
		this.sistemaPlano = sistemaPlano;
	}

	public Integer getSqOrdemOfertaServico() {
		return sqOrdemOfertaServico;
	}

	public void setSqOrdemOfertaServico(Integer sqOrdemOfertaServico) {
		this.sqOrdemOfertaServico = sqOrdemOfertaServico;
	}

	public Integer getSqOrdemPlano() {
		return sqOrdemPlano;
	}

	public void setSqOrdemPlano(Integer sqOrdemPlano) {
		this.sqOrdemPlano = sqOrdemPlano;
	}
}