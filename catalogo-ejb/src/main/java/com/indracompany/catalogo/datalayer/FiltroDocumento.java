package com.indracompany.catalogo.datalayer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FILTRODOCUMENTO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "FILTRODOCUMENTO_SQ", sequenceName = "CATALOGOPRS_OW.FILTRODOCUMENTO_SQ", allocationSize = 1)
public class FiltroDocumento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILTRODOCUMENTO_SQ")
	private Long idFiltroDocumento;
	
	@ManyToOne
	@JoinColumn(name = "IDTIPODOCUMENTO")
	private TipoDocumento tipoDocumento;
	
	@ManyToOne
	@JoinColumn(name = "IDINDICADORCOMERCIAL")
	private IndicadorComercial indicadorComercial;

	public Long getIdFiltroDocumento() {
		return idFiltroDocumento;
	}

	public void setIdFiltroDocumento(Long idFiltroDocumento) {
		this.idFiltroDocumento = idFiltroDocumento;
	}

	public IndicadorComercial getIndicadorComercial() {
		return indicadorComercial;
	}

	public void setIndicadorComercial(IndicadorComercial indicadorComercial) {
		this.indicadorComercial = indicadorComercial;
	}

	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
}
