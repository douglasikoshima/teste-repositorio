package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ModeloTipoProdutoCompativel;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoRestricaoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ModeloTipoProdutoCompativelTOBuilder {
	
	/**
	 * @param modeloTipoProdutoCompativelTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ModeloTipoProdutoCompativel createModeloTipoProdutoCompativel(ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO) {
		
		ModeloTipoProdutoCompativel modeloTipoProdutoCompativel = null;
		
		if (modeloTipoProdutoCompativelTO != null) {
			modeloTipoProdutoCompativel = new ModeloTipoProdutoCompativel();
			modeloTipoProdutoCompativel.setIdModeloTipoProdutoCompativel(modeloTipoProdutoCompativelTO.getIdModeloTipoProdutoCompativel());
			
			ModeloTipoProdutoRestricaoTOBuilder tipoProdutoRestricaoTOBuilder = new ModeloTipoProdutoRestricaoTOBuilder();
			modeloTipoProdutoCompativel.setModeloTipoProdutoRestricao(tipoProdutoRestricaoTOBuilder.createModeloTipoProdutoRestricao(modeloTipoProdutoCompativelTO.getModeloTipoProdutoRestricaoTO()));
			
			TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
			modeloTipoProdutoCompativel.setTipoProduto(tipoProdutoTOBuilder.createTipoProduto(modeloTipoProdutoCompativelTO.getTipoProdutoTO()));
			
		}
		return modeloTipoProdutoCompativel;
	}
	
	/**
	 * @param modeloTipoProdutoCompativel
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ModeloTipoProdutoCompativelTO createModeloTipoProdutoCompativelTO(ModeloTipoProdutoCompativel modeloTipoProdutoCompativel) {
		
		ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO = null;
		
		if (modeloTipoProdutoCompativel != null) {
			modeloTipoProdutoCompativelTO = new ModeloTipoProdutoCompativelTO();
			modeloTipoProdutoCompativelTO.setIdModeloTipoProdutoCompativel(modeloTipoProdutoCompativel.getIdModeloTipoProdutoCompativel());
			
			if (modeloTipoProdutoCompativel.getModeloTipoProdutoRestricao() != null) {
				ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO = new ModeloTipoProdutoRestricaoTO();
				modeloTipoProdutoRestricaoTO.setIdModeloTipoProdutoRestricao(modeloTipoProdutoCompativel.getModeloTipoProdutoRestricao().getIdModeloTipoProdutoRestricao());
				modeloTipoProdutoCompativelTO.setModeloTipoProdutoRestricaoTO(modeloTipoProdutoRestricaoTO);
			}
			TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
			modeloTipoProdutoCompativelTO.setTipoProdutoTO(tipoProdutoTOBuilder.createTipoProdutoTO(modeloTipoProdutoCompativel.getTipoProduto()));
		}
		
		return modeloTipoProdutoCompativelTO;
	}
	
	/**
	 * @param modeloTipoProdutoCompativeList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ModeloTipoProdutoCompativelTO> createModeloTipoProdutoCompativelTOList(List<ModeloTipoProdutoCompativel> modeloTipoProdutoCompativeList) {
		
		List<ModeloTipoProdutoCompativelTO> list = new ArrayList<ModeloTipoProdutoCompativelTO>();
		
		if (modeloTipoProdutoCompativeList != null && modeloTipoProdutoCompativeList.size() > 0) {
			for (ModeloTipoProdutoCompativel modeloTipoProdutoCompativel : modeloTipoProdutoCompativeList) {
				list.add(createModeloTipoProdutoCompativelTO(modeloTipoProdutoCompativel));
			}
		}
		
		return list;
	}
}
