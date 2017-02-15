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
@Table(name="PLANOVARIAVEISPRD", schema = "CATALOGOPRS_OW")
public class PlanoVariaveisPRD implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public PlanoVariaveisPRD() {}
	
	@Id
	@Column(name = "IDPLANOVARIAVEISPRD")
	private Integer idPlanoVariaveisPRD;

	@Column(name = "CODIGOAREA")
	private Integer codigoArea;

	@Column(name = "INDISPONIVEL")
	private String inDisponivel;

	@Column(name = "SGCARTEIRA")
	private String sgCarteira;

	@Column(name = "SGSEGMENTO")
	private String sgSegmento;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA", nullable=false)
	private Tecnologia tecnologia;

	//bi-directional many-to-one association to Tipocliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOCLIENTE", nullable=false)
	private TipoCliente tipoCliente;

	//bi-directional many-to-one association to Uf
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUF", nullable=false)
	private Uf uf;

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
		this.codigoArea = codigoArea;
	}

	public Integer getIdPlanoVariaveisPRD() {
		return idPlanoVariaveisPRD;
	}

	public void setIdPlanoVariaveisPRD(Integer idPlanoVariaveisPRD) {
		this.idPlanoVariaveisPRD = idPlanoVariaveisPRD;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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