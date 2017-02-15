package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PLANOTIPOASSINATURA", schema = "CATALOGOPRS_OW")
public class PlanoTipoAssinatura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PlanoTipoAssinatura() {}
	
	@Id
	@Column(name = "IDPLANOTIPOASSINATURA")
	private Integer idPlanoTipoAssinatura;

	@Column(name = "SGTPASSINATURA")
	private String sgTpAssinatura;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO", nullable=false)
	private Plano plano;

	public Integer getIdPlanoTipoAssinatura() {
		return idPlanoTipoAssinatura;
	}

	public void setIdPlanoTipoAssinatura(Integer idPlanoTipoAssinatura) {
		this.idPlanoTipoAssinatura = idPlanoTipoAssinatura;
	}

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public String getSgTpAssinatura() {
		return sgTpAssinatura;
	}

	public void setSgTpAssinatura(String sgTpAssinatura) {
		this.sgTpAssinatura = sgTpAssinatura;
	}
}