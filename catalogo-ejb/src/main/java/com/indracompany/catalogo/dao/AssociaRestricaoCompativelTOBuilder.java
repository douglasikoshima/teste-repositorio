package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AssociaRestricaoCompativel;
import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.to.AssociaRestricaoCompativelTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class AssociaRestricaoCompativelTOBuilder {
	
	/**
	 * @param associaRestricaoCompativelTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public AssociaRestricaoCompativel createAssociaRestricaoCompativel(AssociaRestricaoCompativelTO associaRestricaoCompativelTO) {
		
		AssociaRestricaoCompativel associaRestricaoCompativel = null;
		
		if (associaRestricaoCompativelTO != null) {
			associaRestricaoCompativel = new AssociaRestricaoCompativel();
			associaRestricaoCompativel.setDtCriacao(associaRestricaoCompativelTO.getDtCriacao());
			associaRestricaoCompativel.setIdAssociaRestricaoCompativel(associaRestricaoCompativelTO.getIdAssociaRestricaoCompativel());
			associaRestricaoCompativel.setNmUsuarioCriacao(associaRestricaoCompativelTO.getNmUsuarioCriacao());
			
			TipoProduto tipoProdutoCompativel = new TipoProduto();
			tipoProdutoCompativel.setIdTipoProduto(associaRestricaoCompativelTO.getTipoProdutoCompativelTO().getIdTipoProduto());
			tipoProdutoCompativel.setNmTipoProduto(associaRestricaoCompativelTO.getTipoProdutoCompativelTO().getNmTipoProduto());
			associaRestricaoCompativel.setTipoProdutoCompativel(tipoProdutoCompativel);
			
			TipoProduto tipoProdutoRestricao = new TipoProduto();
			tipoProdutoRestricao.setIdTipoProduto(associaRestricaoCompativelTO.getTipoProdutoRestricaoTO().getIdTipoProduto());
			tipoProdutoRestricao.setNmTipoProduto(associaRestricaoCompativelTO.getTipoProdutoRestricaoTO().getNmTipoProduto());
			associaRestricaoCompativel.setTipoProdutoRestricao(tipoProdutoRestricao);
		}
		return associaRestricaoCompativel;
	}
	
	/**
	 * @param tipoFrequencia
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public AssociaRestricaoCompativelTO createAssociaRestricaoCompativelTO(AssociaRestricaoCompativel associaRestricaoCompativel) {
		
		AssociaRestricaoCompativelTO associaRestricaoCompativelTO = null;
		
		if (associaRestricaoCompativel != null) {
			associaRestricaoCompativelTO = new AssociaRestricaoCompativelTO();
			associaRestricaoCompativelTO.setDtCriacao(associaRestricaoCompativel.getDtCriacao());
			associaRestricaoCompativelTO.setIdAssociaRestricaoCompativel(associaRestricaoCompativel.getIdAssociaRestricaoCompativel());
			associaRestricaoCompativelTO.setNmUsuarioCriacao(associaRestricaoCompativel.getNmUsuarioCriacao());
			
			TipoProdutoTO tipoProdutoCompativelTO = new TipoProdutoTO();
			tipoProdutoCompativelTO.setIdTipoProduto(associaRestricaoCompativel.getTipoProdutoCompativel().getIdTipoProduto());
			tipoProdutoCompativelTO.setNmTipoProduto(associaRestricaoCompativel.getTipoProdutoCompativel().getNmTipoProduto());
			associaRestricaoCompativelTO.setTipoProdutoCompativelTO(tipoProdutoCompativelTO);
			
			TipoProdutoTO tipoProdutoRestricaoTO = new TipoProdutoTO();
			tipoProdutoRestricaoTO.setIdTipoProduto(associaRestricaoCompativel.getTipoProdutoRestricao().getIdTipoProduto());
			tipoProdutoRestricaoTO.setNmTipoProduto(associaRestricaoCompativel.getTipoProdutoRestricao().getNmTipoProduto());
			associaRestricaoCompativelTO.setTipoProdutoRestricaoTO(tipoProdutoRestricaoTO);
		}
		
		return associaRestricaoCompativelTO;
	}
	
	/**
	 * @param tipoFrequenciaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<AssociaRestricaoCompativelTO> createAssociaRestricaoCompativelTOList(List<AssociaRestricaoCompativel> associaRestricaoCompativeList) {
		
		List<AssociaRestricaoCompativelTO> list = new ArrayList<AssociaRestricaoCompativelTO>();
		
		if (associaRestricaoCompativeList != null && associaRestricaoCompativeList.size() > 0) {
			for (AssociaRestricaoCompativel associaRestricaoCompativel : associaRestricaoCompativeList) {
				list.add(createAssociaRestricaoCompativelTO(associaRestricaoCompativel));
			}
		}
		
		return list;
	}
}
