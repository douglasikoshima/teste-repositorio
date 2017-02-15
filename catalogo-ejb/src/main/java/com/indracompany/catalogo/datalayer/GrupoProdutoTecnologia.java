package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "GRUPOPRODUTOTECNOLOGIA_SQ" ,sequenceName = "CATALOGOPRS_OW.GRUPOPRODUTOTECNOLOGIA_SQ", allocationSize = 1)
@Table(name="GRUPOPRODUTOTECNOLOGIA", schema = "CATALOGOPRS_OW")
public class GrupoProdutoTecnologia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public GrupoProdutoTecnologia() {
	}
	
	public GrupoProdutoTecnologia(Integer idGrupoProdutoTecn) {
		this.idGrupoProdutoTecn = idGrupoProdutoTecn;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRUPOPRODUTOTECNOLOGIA_SQ")
	@Column(name = "IDGRUPOPRODUTOTECN")
	private Integer idGrupoProdutoTecn;

	//bi-directional many-to-one association to Grupoprodutotecnfreqvl
	@OneToMany(mappedBy="grupoProdutoTecnologia", fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	private List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVlList;

	//bi-directional many-to-one association to Grupoproduto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOPRODUTO", nullable=false)
	private GrupoProduto grupoProduto;

	//bi-directional many-to-one association to Tecnologia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTECNOLOGIA", nullable=false)
	private Tecnologia tecnologia;

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public List<GrupoProdutoTecnFreqVl> getGrupoProdutoTecnFreqVlList() {
		return grupoProdutoTecnFreqVlList;
	}

	public void setGrupoProdutoTecnFreqVlList(
			List<GrupoProdutoTecnFreqVl> grupoProdutoTecnFreqVlList) {
		this.grupoProdutoTecnFreqVlList = grupoProdutoTecnFreqVlList;
	}

	public Integer getIdGrupoProdutoTecn() {
		return idGrupoProdutoTecn;
	}

	public void setIdGrupoProdutoTecn(Integer idGrupoProdutoTecn) {
		this.idGrupoProdutoTecn = idGrupoProdutoTecn;
	}

	public Tecnologia getTecnologia() {
		return tecnologia;
	}

	public void setTecnologia(Tecnologia tecnologia) {
		this.tecnologia = tecnologia;
	}
}