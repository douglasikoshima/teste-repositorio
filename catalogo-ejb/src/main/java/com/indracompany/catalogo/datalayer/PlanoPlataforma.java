package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANOPLATAFORMA", schema = "CATALOGOPRS_OW")
public class PlanoPlataforma implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoPlataforma() {}
	
	@Id
	@Column(name = "IDPLANOPLATAFORMA")
	private Integer idPlanoPlataforma;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA", nullable=false)
	private Plataforma plataforma;

	public Integer getIdPlanoPlataforma() {
		return idPlanoPlataforma;
	}

	public void setIdPlanoPlataforma(Integer idPlanoPlataforma) {
		this.idPlanoPlataforma = idPlanoPlataforma;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
}