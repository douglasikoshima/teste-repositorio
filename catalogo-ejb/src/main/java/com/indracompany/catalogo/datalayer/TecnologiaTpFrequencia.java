package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="TECNOLOGIATPFREQUENCIA", schema = "CATALOGOPRS_OW")
public class TecnologiaTpFrequencia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "IDTECNOLOGIATPFREQUENCIA")
	private Integer idTecnologiaTpFrequencia;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA", nullable=false)
	private Tecnologia tecnologia;

	//bi-directional many-to-one association to Tipofrequencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOFREQUENCIA", nullable=false)
	private TipoFrequencia tipoFrequencia;

	//bi-directional many-to-one association to Tecnologiatpfrequenciavl
	@OneToMany(mappedBy="tecnologiaTpFrequencia")
	private List<TecnologiaTpFrequenciaVl> tecnologiaTpFrequenciaVList;

	public TecnologiaTpFrequencia() {
	}
	
	public TecnologiaTpFrequencia(Integer idTecnologiaTpFrequencia) {
		this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
	}
	
	public Integer getIdTecnologiaTpFrequencia() {
		return idTecnologiaTpFrequencia;
	}

	public void setIdTecnologiaTpFrequencia(Integer idTecnologiaTpFrequencia) {
		this.idTecnologiaTpFrequencia = idTecnologiaTpFrequencia;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}

	public List<TecnologiaTpFrequenciaVl> getTecnologiaTpFrequenciaVList() {
		return tecnologiaTpFrequenciaVList;
	}

	public void setTecnologiaTpFrequenciaVList(
			List<TecnologiaTpFrequenciaVl> tecnologiaTpFrequenciaVList) {
		this.tecnologiaTpFrequenciaVList = tecnologiaTpFrequenciaVList;
	}

	public TipoFrequencia getTipoFrequencia() {
		return tipoFrequencia;
	}

	public void setTipoFrequencia(TipoFrequencia tipoFrequencia) {
		this.tipoFrequencia = tipoFrequencia;
	}
}