package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class TipoProdutoTOBuilder {
	
	private boolean criarTipoLinha;
	private boolean criarAssociaRestricaoCompativel;
	
	public TipoProdutoTOBuilder() {
		this(true);
	}
	
	public TipoProdutoTOBuilder(boolean criar) {
		criarTipoLinha = criar;
		criarAssociaRestricaoCompativel = criar;
	}
	
	/**
	 * @param tipoProdutoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public TipoProduto createTipoProduto(TipoProdutoTO tipoProdutoTO) {
		
		TipoProduto tipoProduto = null;
		
		if (tipoProdutoTO != null) {
			tipoProduto = new TipoProduto();
			tipoProduto.setDtCriacao(tipoProdutoTO.getDtCriacao());
			tipoProduto.setDtUltimaAlteracao(tipoProdutoTO.getDtUltimaAlteracao());
			tipoProduto.setIdTipoProduto(tipoProdutoTO.getIdTipoProduto());
			tipoProduto.setNmTipoProduto(tipoProdutoTO.getNmTipoProduto());
			tipoProduto.setNmUsuarioAlteracao(tipoProdutoTO.getNmUsuarioAlteracao());
			tipoProduto.setNmUsuarioCriacao(tipoProdutoTO.getNmUsuarioCriacao());
			tipoProduto.setSgTipoProduto(tipoProdutoTO.getSgTipoProduto());
			tipoProduto.setUtilizaChip(tipoProdutoTO.getUtilizaChip());
		}
		
		return tipoProduto;
	}
	
	/**
	 * @param tipoProduto
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TipoProdutoTO createTipoProdutoTO(TipoProduto tipoProduto) {
		
		TipoProdutoTO tipoProdutoTO = null;
		
		if (tipoProduto != null) {
			tipoProdutoTO = new TipoProdutoTO();
			tipoProdutoTO.setDtCriacao(tipoProduto.getDtCriacao());
			tipoProdutoTO.setDtUltimaAlteracao(tipoProduto.getDtUltimaAlteracao());
			tipoProdutoTO.setIdTipoProduto(tipoProduto.getIdTipoProduto());
			tipoProdutoTO.setNmTipoProduto(tipoProduto.getNmTipoProduto());
			tipoProdutoTO.setNmUsuarioAlteracao(tipoProduto.getNmUsuarioAlteracao());
			tipoProdutoTO.setNmUsuarioCriacao(tipoProduto.getNmUsuarioCriacao());
			tipoProdutoTO.setSgTipoProduto(tipoProduto.getSgTipoProduto());
			tipoProdutoTO.setUtilizaChip(tipoProduto.getUtilizaChip());
			
			if (criarTipoLinha) {
				TipoLinhaTOBuilder tipoLinhaTOBuilder = new TipoLinhaTOBuilder();
				tipoProdutoTO.setTipoLinhaTO(tipoLinhaTOBuilder.createTipoLinhaTO(tipoProduto.getTipoLinha()));
			}
			
			if (criarAssociaRestricaoCompativel) {
				AssociaRestricaoCompativelTOBuilder associaRestricaoCompativelTOBuilder = new AssociaRestricaoCompativelTOBuilder();
				tipoProdutoTO.setAssociaRestricaoCompativelTOList(associaRestricaoCompativelTOBuilder
						.createAssociaRestricaoCompativelTOList(tipoProduto.getAssociaRestricaoCompativeList()));
			}
		}
		
		return tipoProdutoTO;
	}
	
	/**
	 * @param tipoProdutoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TipoProdutoTO> createTipoProdutoTOList(List<TipoProduto> tipoProdutoList) {
		
		List<TipoProdutoTO> list = new ArrayList<TipoProdutoTO>();
		
		if (tipoProdutoList != null && tipoProdutoList.size() > 0) {
			for (TipoProduto tipoProduto : tipoProdutoList) {
				list.add(createTipoProdutoTO(tipoProduto));
			}
		}
		
		return list;
	}
	
	public boolean isCriarAssociaRestricaoCompativel() {
		return criarAssociaRestricaoCompativel;
	}
	
	public void setCriarAssociaRestricaoCompativel(
			boolean criarAssociaRestricaoCompativel) {
		this.criarAssociaRestricaoCompativel = criarAssociaRestricaoCompativel;
	}
	
	public boolean isCriarTipoLinha() {
		return criarTipoLinha;
	}
	
	public void setCriarTipoLinha(boolean criarTipoLinha) {
		this.criarTipoLinha = criarTipoLinha;
	}
	
}