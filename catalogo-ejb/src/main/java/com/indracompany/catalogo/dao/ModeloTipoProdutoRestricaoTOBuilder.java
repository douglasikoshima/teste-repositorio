package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ModeloTipoProdutoRestricao;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoRestricaoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ModeloTipoProdutoRestricaoTOBuilder {
	
	/**
	 * @param modeloTipoProdutoRestricaoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public ModeloTipoProdutoRestricao createModeloTipoProdutoRestricao(ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO) {
		
		ModeloTipoProdutoRestricao modeloTipoProdutoRestricao = null;
		
		if (modeloTipoProdutoRestricaoTO != null) {
			modeloTipoProdutoRestricao = new ModeloTipoProdutoRestricao();
			modeloTipoProdutoRestricao.setDtCriacao(modeloTipoProdutoRestricaoTO.getDtCriacao());
			modeloTipoProdutoRestricao.setDtUltimaAlteracao(modeloTipoProdutoRestricaoTO.getDtUltimaAlteracao());
			modeloTipoProdutoRestricao.setIdModeloTipoProdutoRestricao(modeloTipoProdutoRestricaoTO.getIdModeloTipoProdutoRestricao());
			modeloTipoProdutoRestricao.setNmUsuarioAlteracao(modeloTipoProdutoRestricaoTO.getNmUsuarioAlteracao());
			modeloTipoProdutoRestricao.setNmUsuarioCriacao(modeloTipoProdutoRestricaoTO.getNmUsuarioCriacao());
			
			GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
			modeloTipoProdutoRestricao.setGrupoProduto(grupoProdutoTOBuilder.createGrupoProduto(modeloTipoProdutoRestricaoTO.getGrupoProdutoTO()));
			
			TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
			modeloTipoProdutoRestricao.setTipoProduto(tipoProdutoTOBuilder.createTipoProduto(modeloTipoProdutoRestricaoTO.getTipoProdutoTO()));
		}
		
		return modeloTipoProdutoRestricao;
	}
	
	/**
	 * @param modeloTipoProdutoRestricao
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ModeloTipoProdutoRestricaoTO createModeloTipoProdutoRestricaoTO(ModeloTipoProdutoRestricao modeloTipoProdutoRestricao) {
		
		ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO = null;
		
		if (modeloTipoProdutoRestricao != null) {
			modeloTipoProdutoRestricaoTO = new ModeloTipoProdutoRestricaoTO();
			modeloTipoProdutoRestricaoTO.setDtCriacao(modeloTipoProdutoRestricao.getDtCriacao());
			modeloTipoProdutoRestricaoTO.setDtUltimaAlteracao(modeloTipoProdutoRestricao.getDtUltimaAlteracao());
			modeloTipoProdutoRestricaoTO.setIdModeloTipoProdutoRestricao(modeloTipoProdutoRestricao.getIdModeloTipoProdutoRestricao());
			modeloTipoProdutoRestricaoTO.setNmUsuarioAlteracao(modeloTipoProdutoRestricao.getNmUsuarioAlteracao());
			modeloTipoProdutoRestricaoTO.setNmUsuarioCriacao(modeloTipoProdutoRestricao.getNmUsuarioCriacao());
			
			if (modeloTipoProdutoRestricao.getGrupoProduto() != null) {
				modeloTipoProdutoRestricaoTO.setGrupoProdutoTO(new GrupoProdutoTO(modeloTipoProdutoRestricao.getGrupoProduto().getIdGrupoProduto()));
			}
			
			TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
			modeloTipoProdutoRestricaoTO.setTipoProdutoTO(tipoProdutoTOBuilder.createTipoProdutoTO(modeloTipoProdutoRestricao.getTipoProduto()));
			
			ModeloTipoProdutoCompativelTOBuilder modeloTipoProdutoCompativelTOBuilder = new ModeloTipoProdutoCompativelTOBuilder();
			modeloTipoProdutoRestricaoTO.setModeloTipoProdutoCompativelTOList(modeloTipoProdutoCompativelTOBuilder.createModeloTipoProdutoCompativelTOList(modeloTipoProdutoRestricao.getModeloTipoProdutoCompativelList()));
		}
		
		return modeloTipoProdutoRestricaoTO;
	}
	
	/**
	 * @param modeloTipoProdutoRestricaoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ModeloTipoProdutoRestricaoTO> createModeloTipoProdutoRestricaoTOList(List<ModeloTipoProdutoRestricao> modeloTipoProdutoRestricaoList) {
		
		List<ModeloTipoProdutoRestricaoTO> list = new ArrayList<ModeloTipoProdutoRestricaoTO>();
		
		if (modeloTipoProdutoRestricaoList != null && modeloTipoProdutoRestricaoList.size() > 0) {
			for (ModeloTipoProdutoRestricao modeloTipoProdutoRestricao : modeloTipoProdutoRestricaoList) {
				list.add(createModeloTipoProdutoRestricaoTO(modeloTipoProdutoRestricao));
			}
		}
		
		return list;
	}
}
