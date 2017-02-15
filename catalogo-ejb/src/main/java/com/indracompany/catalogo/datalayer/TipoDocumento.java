package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPODOCUMENTO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "TIPODOCUMENTO_SQ", sequenceName = "CATALOGOPRS_OW.TIPODOCUMENTO_SQ", allocationSize = 1)
@NamedQueries(
	@NamedQuery(name = "TipoDocumento.findAll", query = "select td from TipoDocumento td order by td.nmTipoDocumento ")
)
public class TipoDocumento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPODOCUMENTO_SQ")
	private Long idTipoDocumento;
	
	@Column(name = "NMTIPODOCUMENTO")
	private String nmTipoDocumento;
	
	public TipoDocumento() {
		super();
	}

	public TipoDocumento(Long idTipoDocumento) {
		super();
		this.idTipoDocumento = idTipoDocumento;
	}

	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getNmTipoDocumento() {
		return nmTipoDocumento;
	}

	public void setNmTipoDocumento(String nmTipoDocumento) {
		this.nmTipoDocumento = nmTipoDocumento;
	}
}