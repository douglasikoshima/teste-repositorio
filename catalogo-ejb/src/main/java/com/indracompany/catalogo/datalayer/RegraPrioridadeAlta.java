package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "REGRAPRIORIDADEALTA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "REGRAPRIORIDADEALTA_SQ", sequenceName = "CATALOGOPRS_OW.REGRAPRIORIDADEALTA_SQ", allocationSize = 1)
@NamedQuery(name = "RegraPrioridadeAlta.buscar", query = "select rpa from RegraPrioridadeAlta rpa order by rpa.dsRegraAlta ")
public class RegraPrioridadeAlta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2886535166601841894L;

	@Id
	@Column(name = "IDREGRAPRIORIDADEALTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REGRAPRIORIDADEALTA_SQ")
	private Long idRegraPrioridadeAlta;
	
	@Column(name = "CDPRIORIDADE")
	private Long cdPrioridade;
	
	@Column(name = "DSREGRAALTA")
	private String dsRegraAlta;
	
	@ManyToOne
	@JoinColumn(name = "IDTIPOALTA")
	private TipoAlta tipoAlta;

	public Long getCdPrioridade() {
		return cdPrioridade;
	}

	public void setCdPrioridade(Long cdPrioridade) {
		this.cdPrioridade = cdPrioridade;
	}

	public String getDsRegraAlta() {
		return dsRegraAlta;
	}

	public void setDsRegraAlta(String dsRegraAlta) {
		this.dsRegraAlta = dsRegraAlta;
	}

	public Long getIdRegraPrioridadeAlta() {
		return idRegraPrioridadeAlta;
	}

	public void setIdRegraPrioridadeAlta(Long idRegraPrioridadeAlta) {
		this.idRegraPrioridadeAlta = idRegraPrioridadeAlta;
	}

	public TipoAlta getTipoAlta() {
		return tipoAlta;
	}

	public void setTipoAlta(TipoAlta tipoAlta) {
		this.tipoAlta = tipoAlta;
	}
}
