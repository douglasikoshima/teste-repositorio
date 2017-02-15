package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="VLFREQUENCIA", schema = "CATALOGOPRS_OW")
public class VlFrequencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "IDVLFREQUENCIA")
	private Integer idVlFrequencia;

	@Column(name = "VLFREQUENCIA")
	private String vlFrequencia;

	//bi-directional many-to-one association to Tecnologiatpfrequenciavl
	@OneToMany(mappedBy="vlFrequencia")
	private List<TecnologiaTpFrequenciaVl> tecnologiaTpFrequenciaVList;

	public VlFrequencia() {
	}
	
	public VlFrequencia(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}
	
	public Integer getIdVlFrequencia() {
		return idVlFrequencia;
	}

	public void setIdVlFrequencia(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}

	public List<TecnologiaTpFrequenciaVl> getTecnologiaTpFrequenciaVList() {
		return tecnologiaTpFrequenciaVList;
	}

	public void setTecnologiaTpFrequenciaVList(
			List<TecnologiaTpFrequenciaVl> tecnologiaTpFrequenciaVList) {
		this.tecnologiaTpFrequenciaVList = tecnologiaTpFrequenciaVList;
	}

	public String getVlFrequencia() {
		return vlFrequencia;
	}

	public void setVlFrequencia(String vlFrequencia) {
		this.vlFrequencia = vlFrequencia;
	}
}