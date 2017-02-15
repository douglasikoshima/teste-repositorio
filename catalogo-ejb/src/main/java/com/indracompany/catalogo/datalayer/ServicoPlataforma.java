package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SERVICOPLATAFORMA", schema = "CATALOGOPRS_OW")
public class ServicoPlataforma implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoPlataforma() {}
	
	@Id
	@Column(name = "IDSERVICOPLATAFORMA")
	private Integer idServicoPlataforma;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA", nullable=false)
	private Plataforma plataforma;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	public Integer getIdServicoPlataforma() {
		return idServicoPlataforma;
	}

	public void setIdServicoPlataforma(Integer idServicoPlataforma) {
		this.idServicoPlataforma = idServicoPlataforma;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}