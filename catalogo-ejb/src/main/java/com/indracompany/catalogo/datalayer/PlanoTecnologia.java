package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANOTECNOLOGIA", schema = "CATALOGOPRS_OW")
public class PlanoTecnologia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoTecnologia() {}
	
	@Id
	@Column(name = "IDPLANOTECNOLOGIA")
	private Integer idPlanoTecnologia;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA", nullable=false)
	private Tecnologia tecnologia;

	public Integer getIdPlanoTecnologia() {
		return idPlanoTecnologia;
	}

	public void setIdPlanoTecnologia(Integer idPlanoTecnologia) {
		this.idPlanoTecnologia = idPlanoTecnologia;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}
}