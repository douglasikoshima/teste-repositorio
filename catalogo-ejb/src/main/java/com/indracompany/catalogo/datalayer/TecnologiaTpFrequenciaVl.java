package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="TECNOLOGIATPFREQUENCIAVL", schema = "CATALOGOPRS_OW")
public class TecnologiaTpFrequenciaVl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "IDTECNOLOGIATPFREQUENCIAVL")
	private Integer idTecnologiaTpFrequenciaVl;

	//bi-directional many-to-one association to Grupoprodutotecnfreqvl
	@OneToMany(mappedBy="tecnologiaTpFrequenciaVl")
	private List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVList;

	//bi-directional many-to-one association to Tecnologiatpfrequencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIATPFREQUENCIA", nullable=false)
	private TecnologiaTpFrequencia tecnologiaTpFrequencia;

	//bi-directional many-to-one association to Vlfrequencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDVLFREQUENCIA", nullable=false)
	private VlFrequencia vlFrequencia;

	public TecnologiaTpFrequenciaVl() {
	}
	
	public TecnologiaTpFrequenciaVl(Integer idTecnologiaTpFrequenciaVl) {
		this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
	}
	
	public List<GrupoProdutoTecnFreqVl> getGrupoProdutoTecnFreqVList() {
		return grupoProdutoTecnFreqVList;
	}

	public void setGrupoProdutoTecnFreqVList(
			List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVList) {
		this.grupoProdutoTecnFreqVList = grupoProdutoTecnFreqVList;
	}

	public Integer getIdTecnologiaTpFrequenciaVl() {
		return idTecnologiaTpFrequenciaVl;
	}

	public void setIdTecnologiaTpFrequenciaVl(Integer idTecnologiaTpFrequenciaVl) {
		this.idTecnologiaTpFrequenciaVl = idTecnologiaTpFrequenciaVl;
	}

	public TecnologiaTpFrequencia getTecnologiaTpFrequencia() {
		return tecnologiaTpFrequencia;
	}

	public void setTecnologiaTpFrequencia(
			TecnologiaTpFrequencia tecnologiaTpFrequencia) {
		this.tecnologiaTpFrequencia = tecnologiaTpFrequencia;
	}

	public VlFrequencia getVlFrequencia() {
		return vlFrequencia;
	}

	public void setVlFrequencia(VlFrequencia vlFrequencia) {
		this.vlFrequencia = vlFrequencia;
	}
	
}