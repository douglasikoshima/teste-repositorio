package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="GRUPOPRODUTORESTRICOES", schema = "CATALOGOPRS_OW")
public class GrupoProdutoRestricoes implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public GrupoProdutoRestricoes() {}
	
	@Id
	@Column(name = "IDGRUPOPRODUTORESTRICOES")
	private Integer idGrupoProdutoRestricoes;

	@Column(name = "SGCARTEIRA")
	private String sgCarteira;

	@Column(name = "SGSEGMENTO")
	private String sgSegmento;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL")
	private Canal canal;

	//bi-directional many-to-one association to Grupoproduto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOPRODUTO", nullable=false)
	private GrupoProduto grupoProduto;

	//bi-directional many-to-one association to Tipocliente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOCLIENTE")
	private TipoCliente tipoCliente;

	//bi-directional many-to-one association to Uf
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUF")
	private Uf uf;

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public Integer getIdGrupoProdutoRestricoes() {
		return idGrupoProdutoRestricoes;
	}

	public void setIdGrupoProdutoRestricoes(Integer idGrupoProdutoRestricoes) {
		this.idGrupoProdutoRestricoes = idGrupoProdutoRestricoes;
	}

	public String getSgCarteira() {
		return sgCarteira;
	}

	public void setSgCarteira(String sgCarteira) {
		this.sgCarteira = sgCarteira;
	}

	public String getSgSegmento() {
		return sgSegmento;
	}

	public void setSgSegmento(String sgSegmento) {
		this.sgSegmento = sgSegmento;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}
}