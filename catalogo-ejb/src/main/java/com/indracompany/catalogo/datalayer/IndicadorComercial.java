package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "INDICADORCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "INDICADORCOMERCIAL_SQ", sequenceName = "CATALOGOPRS_OW.INDICADORCOMERCIAL_SQ", allocationSize = 1)
@NamedQueries(
	@NamedQuery(name = "IndicadorComercial.findAll", query="select ic from IndicadorComercial ic order by ic.nmIndicadorComercial ")
)
public class IndicadorComercial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -864912436953199663L;
	
	@Id
	@Column(name = "IDINDICADORCOMERCIAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "INDICADORCOMERCIAL_SQ")
	private Long idIndicadorComercial;
	
	@Column(name = "NMINDICADORCOMERCIAL")
	private String nmIndicadorComercial;
	
	@Column(name = "INTIPOFILTRO")
	private Long inTipoFiltro;
	
	@OneToMany(mappedBy = "indicadorComercial")
	@JoinColumn(name = "IDINDICADORCOMERCIAL")
	private List<FiltroDocumento> filtroDocumentoList;
	
	@ManyToMany
	@JoinTable(name = "FILTRODOCUMENTO", joinColumns = @JoinColumn(name = "IDINDICADORCOMERCIAL"), inverseJoinColumns = @JoinColumn(name = "IDTIPODOCUMENTO"))
	private List<TipoDocumento> tipoDocumentoList;
	
	public IndicadorComercial() {
		super();
	}

	public IndicadorComercial(Long idIndicadorComercial) {
		super();
		this.idIndicadorComercial = idIndicadorComercial;
	}

	public List<FiltroDocumento> getFiltroDocumentoList() {
		return filtroDocumentoList;
	}

	public void setFiltroDocumentoList(List<FiltroDocumento> filtroDocumentoList) {
		this.filtroDocumentoList = filtroDocumentoList;
	}

	public Long getIdIndicadorComercial() {
		return idIndicadorComercial;
	}

	public void setIdIndicadorComercial(Long idIndicadorComercial) {
		this.idIndicadorComercial = idIndicadorComercial;
	}

	public Long getInTipoFiltro() {
		return inTipoFiltro;
	}

	public void setInTipoFiltro(Long inTipoFiltro) {
		this.inTipoFiltro = inTipoFiltro;
	}

	public String getNmIndicadorComercial() {
		return nmIndicadorComercial;
	}

	public void setNmIndicadorComercial(String nmIndicadorComercial) {
		this.nmIndicadorComercial = nmIndicadorComercial;
	}

	public List<TipoDocumento> getTipoDocumentoList() {
		return tipoDocumentoList;
	}

	public void setTipoDocumentoList(List<TipoDocumento> tipoDocumentoList) {
		this.tipoDocumentoList = tipoDocumentoList;
	}
}


	
