package com.indracompany.catalogo.to;

import java.io.Serializable;

/**
 * @author Luiz Pereira
 *
 */
public class ModeloTipoProdutoCompativelTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ModeloTipoProdutoCompativelTO() {}
	
	public ModeloTipoProdutoCompativelTO(Integer idModeloTipoProdutoCompativel) {
		this.idModeloTipoProdutoCompativel = idModeloTipoProdutoCompativel;
	}
	
	public ModeloTipoProdutoCompativelTO(Integer idModeloTipoProdutoCompativel,  ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO, TipoProdutoTO tipoProdutoTO) {
		this.idModeloTipoProdutoCompativel = idModeloTipoProdutoCompativel;
		this.modeloTipoProdutoRestricaoTO = modeloTipoProdutoRestricaoTO;
		this.tipoProdutoTO = tipoProdutoTO;
	}
	
    private Integer idModeloTipoProdutoCompativel;
    private ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO;
    private TipoProdutoTO tipoProdutoTO;

	public Integer getIdModeloTipoProdutoCompativel() {
		return idModeloTipoProdutoCompativel;
	}

	public void setIdModeloTipoProdutoCompativel(
			Integer idModeloTipoProdutoCompativel) {
		this.idModeloTipoProdutoCompativel = idModeloTipoProdutoCompativel;
	}

	public ModeloTipoProdutoRestricaoTO getModeloTipoProdutoRestricaoTO() {
		return modeloTipoProdutoRestricaoTO;
	}

	public void setModeloTipoProdutoRestricaoTO(
			ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO) {
		this.modeloTipoProdutoRestricaoTO = modeloTipoProdutoRestricaoTO;
	}

	public TipoProdutoTO getTipoProdutoTO() {
		return tipoProdutoTO;
	}

	public void setTipoProdutoTO(TipoProdutoTO tipoProdutoTO) {
		this.tipoProdutoTO = tipoProdutoTO;
	}
    
}
