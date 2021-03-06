package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANORESTRICOES", schema = "CATALOGOPRS_OW")
public class PlanoRestricoes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoRestricoes() {}
	
	@Id
	@Column(name = "IDPLANORESTRICOES")
	private Integer idPlanoRestricoes;

	@Column(name = "CODIGOAREA")
	private Integer codigoArea;

	@Column(name = "SGCARTEIRA")
	private String sgCarteira;

	@Column(name = "SGSEGMENTO")
	private String sgSegmento;

	@Column(name = "SGTIPOASSINATURA")
	private String sgTipoAssinatura;

	//bi-directional many-to-one association to Canalatendimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANALATENDIMENTO")
	private CanalAtendimento canalAtendimento;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA")
	private Tecnologia tecnologia;

	//bi-directional many-to-one association to Tipocliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOCLIENTE")
	private TipoCliente tipoCliente;

	//bi-directional many-to-one association to Uf
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUF")
	private Uf uf;

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public Integer getIdPlanoRestricoes() {
		return idPlanoRestricoes;
	}

	public void setIdPlanoRestricoes(Integer idPlanoRestricoes) {
		this.idPlanoRestricoes = idPlanoRestricoes;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public String getSgCarteira() {
		return sgCarteira;
	}

	public void setSgCarteira(String sgCarteira) {
		this.sgCarteira = sgCarteira;
	}

	public String getSgSegmento() {
		return sgSegmento;
	}

	public void setSgSegmento(String sgSegmento) {
		this.sgSegmento = sgSegmento;
	}

	public String getSgTipoAssinatura() {
		return sgTipoAssinatura;
	}

	public void setSgTipoAssinatura(String sgTipoAssinatura) {
		this.sgTipoAssinatura = sgTipoAssinatura;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
}