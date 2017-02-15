package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "GRUPOPRODUTOTECNFREQVL_SQ" ,sequenceName = "CATALOGOPRS_OW.GRUPOPRODUTOTECNFREQVL_SQ", allocationSize = 1)
@Table(name="GRUPOPRODUTOTECNFREQVL", schema = "CATALOGOPRS_OW")
public class GrupoProdutoTecnFreqVl implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public GrupoProdutoTecnFreqVl() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOPRODUTOTECNFREQVL_SQ")
	@Column(name = "IDGRUPOPRODUTOTECNFREQVL")
	private Integer idGrupoProdutoTecnfreqVl;

	//bi-directional many-to-one association to Grupoprodutotecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOPRODUTOTECN", nullable=false)
	private GrupoProdutoTecnologia grupoProdutoTecnologia;

	//bi-directional many-to-one association to Tecnologiatpfrequenciavl
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIAFREQUENCIAVL", nullable=false)
	private TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl;

	public GrupoProdutoTecnologia getGrupoProdutoTecnologia() {
		return grupoProdutoTecnologia;
	}

	public void setGrupoProdutoTecnologia(
			GrupoProdutoTecnologia grupoProdutoTecnologia) {
		this.grupoProdutoTecnologia = grupoProdutoTecnologia;
	}

	public Integer getIdGrupoProdutoTecnfreqVl() {
		return idGrupoProdutoTecnfreqVl;
	}

	public void setIdGrupoProdutoTecnfreqVl(Integer idGrupoProdutoTecnfreqVl) {
		this.idGrupoProdutoTecnfreqVl = idGrupoProdutoTecnfreqVl;
	}

	public TecnologiaTpFrequenciaVl getTecnologiaTpFrequenciaVl() {
		return tecnologiaTpFrequenciaVl;
	}

	public void setTecnologiaTpFrequenciaVl(
			TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl) {
		this.tecnologiaTpFrequenciaVl = tecnologiaTpFrequenciaVl;
	}
}