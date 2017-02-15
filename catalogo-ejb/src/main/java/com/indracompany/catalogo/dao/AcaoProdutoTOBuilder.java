package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Acao;
import com.indracompany.catalogo.datalayer.AcaoProduto;
import com.indracompany.catalogo.datalayer.Canal;
import com.indracompany.catalogo.datalayer.OrganizacaoVenda;
import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.datalayer.Produto;
import com.indracompany.catalogo.to.AcaoProdutoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class AcaoProdutoTOBuilder {
	
	/**
	 * @param acaoProdutoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public AcaoProduto createAcaoProduto(AcaoProdutoTO acaoProdutoTO) {
		
		AcaoProduto acaoProduto = null;
		
		if (acaoProdutoTO != null) {
			acaoProduto = new AcaoProduto();
			
			Acao acao = new Acao();
			acao.setIdAcao(acaoProdutoTO.getAcaoTO().getIdAcao());
			acaoProduto.setAcao(acao);
			
			acaoProduto.setDtCriacao(acaoProdutoTO.getDtCriacao());
			acaoProduto.setIdAcaoProduto(acaoProdutoTO.getIdAcaoProduto());
			acaoProduto.setNmUsuarioCriacao(acaoProdutoTO.getNmUsuarioCriacao());
			
			Produto produto = new Produto();
			produto.setIdProduto(acaoProdutoTO.getProdutoTO().getIdProduto());
			acaoProduto.setProduto(produto);
			
			Canal canal = new Canal();
			canal.setIdCanal(acaoProdutoTO.getCanalTO().getIdCanal());
			acaoProduto.setCanal(canal);
			
			Plataforma plataforma = new Plataforma();
			plataforma.setIdPlataforma(acaoProdutoTO.getPlataformaTO().getIdPlataforma());
			acaoProduto.setPlataforma(plataforma);
			
			OrganizacaoVenda organizacaoVenda = new OrganizacaoVenda();
			organizacaoVenda.setIdOrganizacaoVendas(acaoProdutoTO.getOrganizacaoVendaTO().getIdOrganizacaoVendas());
			acaoProduto.setOrganizacaoVenda(organizacaoVenda);
			
		}
		return acaoProduto;
	}
	
	/**
	 * @param acaoProduto
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public AcaoProdutoTO createAcaoProdutoTO(AcaoProduto acaoProduto) {
		
		AcaoProdutoTO acaoProdutoTO = null;
		
		if (acaoProduto != null) {
			acaoProdutoTO = new AcaoProdutoTO();
			
			AcaoTOBuilder acaoTOBuilder = new AcaoTOBuilder();
			acaoProdutoTO.setAcaoTO(acaoTOBuilder.createAcaoTO(acaoProduto.getAcao()));
			
			acaoProdutoTO.setDtCriacao(acaoProduto.getDtCriacao());
			acaoProdutoTO.setIdAcaoProduto(acaoProduto.getIdAcaoProduto());
			acaoProdutoTO.setNmUsuarioCriacao(acaoProduto.getNmUsuarioCriacao());
			
			ProdutoTOBuilder produtoTOBuilder = new ProdutoTOBuilder();
			acaoProdutoTO.setProdutoTO(produtoTOBuilder.createProdutoTO(acaoProduto.getProduto()));
			
		}
		
		return acaoProdutoTO;
	}
	
	/**
	 * @param acaoProdutoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<AcaoProdutoTO> createAcaoProdutoTOList(List<AcaoProduto> acaoProdutoList) {
		
		List<AcaoProdutoTO> list = new ArrayList<AcaoProdutoTO>();
		
		if (acaoProdutoList != null && acaoProdutoList.size() > 0) {
			for (AcaoProduto acaoProduto : acaoProdutoList) {
				list.add(createAcaoProdutoTO(acaoProduto));
			}
		}
		
		return list;
	}
}
