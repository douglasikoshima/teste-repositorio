package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANOANALISECREDITO", schema = "CATALOGOPRS_OW")
public class PlanoAnaliseCredito implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoAnaliseCredito() {}
	
	@Id
	@Column(name = "IDPLANOANALISECREDITO")
	private Integer idPlanoAnaliseCredito;

	//bi-directional many-to-one association to Analisecredito
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDANALISECREDITO", nullable=false)
	private AnaliseCredito analiseCredito;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	public AnaliseCredito getAnaliseCredito() {
		return analiseCredito;
	}

	public void setAnaliseCredito(AnaliseCredito analiseCredito) {
		this.analiseCredito = analiseCredito;
	}

	public Integer getIdPlanoAnaliseCredito() {
		return idPlanoAnaliseCredito;
	}

	public void setIdPlanoAnaliseCredito(Integer idPlanoAnaliseCredito) {
		this.idPlanoAnaliseCredito = idPlanoAnaliseCredito;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}
}