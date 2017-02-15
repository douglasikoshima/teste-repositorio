package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Gama;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.Produto;
import com.indracompany.catalogo.datalayer.ProdutoGrupoProduto;
import com.indracompany.catalogo.datalayer.SistemaProduto;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ProdutoGrupoProdutoTO;
import com.indracompany.catalogo.to.ProdutoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class ProdutoTOBuilder {
	
	private boolean criarCor;
	private boolean criarFabricante;
	private boolean criarTipoProduto;
	private boolean criarSistemaProduto;
	private boolean criarGama;
	private boolean criarProdutoGrupoProduto;
	
	public ProdutoTOBuilder() {
		this(true);
	}
	
	public ProdutoTOBuilder(boolean criar) {
		criarCor = criar;
		criarFabricante = criar;
		criarTipoProduto = criar;
		criarSistemaProduto = criar;
		criarGama = criar;
		criarProdutoGrupoProduto = criar;
	}
	
	/**
	 * @param produtoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Produto createProduto(ProdutoTO produtoTO) {
		Produto produto = null;
		
		if (produtoTO != null) {
			produto = new Produto();
			
			CorTOBuilder corTOBuilder = new CorTOBuilder();
			produto.setCor(corTOBuilder.createCor(produtoTO.getCorTO()));
			
			produto.setDsNota(produtoTO.getDsNota());
			produto.setDsProduto(produtoTO.getDsProduto());
			produto.setDsSAP(produtoTO.getDsSAP());
			produto.setDtCriacao(produtoTO.getDtCriacao());
			produto.setDtInicial(produtoTO.getDtInicial());
			produto.setDtFinal(produtoTO.getDtFinal());
			produto.setDtUltimaAlteracao(produtoTO.getDtUltimaAlteracao());
			
			FabricanteTOBuilder fabricanteTOBuilder = new FabricanteTOBuilder();
			produto.setFabricante(fabricanteTOBuilder.createFabricante(produtoTO.getFabricanteTO()));
			
			produto.setIdProduto(produtoTO.getIdProduto());
			produto.setInDisponivel(produtoTO.getInDisponivel());
			produto.setNmProduto(produtoTO.getNmProduto());
			produto.setNmUsuarioAlteracao(produtoTO.getNmUsuarioAlteracao());
			produto.setNmUsuarioCriacao(produtoTO.getNmUsuarioCriacao());
			produto.setSgClasseAvaliacao(produtoTO.getSgClasseAvaliacao());
			produto.setSgGrupoContabil(produtoTO.getSgGrupoContabil());
			produto.setSgSetorAtividade(produtoTO.getSgSetorAtividade());
			
			TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
			produto.setTipoProduto(tipoProdutoTOBuilder.createTipoProduto(produtoTO.getTipoProdutoTO()));
		}
		
		return produto;
	}
	
	/**
	 * @param produto
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public ProdutoTO createProdutoTO(Produto produto) {
		ProdutoTO produtoTO = null;
		
		if (produto != null) {
			produtoTO = new ProdutoTO();
			
			produtoTO.setIdProduto(produto.getIdProduto());
			produtoTO.setInDisponivel(produto.getInDisponivel());
			produtoTO.setNmProduto(produto.getNmProduto());
			produtoTO.setNmUsuarioAlteracao(produto.getNmUsuarioAlteracao());
			produtoTO.setNmUsuarioCriacao(produto.getNmUsuarioCriacao());
			produtoTO.setSgClasseAvaliacao(produto.getSgClasseAvaliacao());
			produtoTO.setSgGrupoContabil(produto.getSgGrupoContabil());
			produtoTO.setSgSetorAtividade(produto.getSgSetorAtividade());
			produtoTO.setDsNota(produto.getDsNota());
			produtoTO.setDsProduto(produto.getDsProduto());
			produtoTO.setDsSAP(produto.getDsSAP());
			produtoTO.setDtCriacao(produto.getDtCriacao());
			produtoTO.setDtInicial(produto.getDtInicial());
			produtoTO.setDtFinal(produto.getDtFinal());
			produtoTO.setDtUltimaAlteracao(produto.getDtUltimaAlteracao());
			
			if (criarCor) {
				CorTOBuilder corTOBuilder = new CorTOBuilder();
				produtoTO.setCorTO(corTOBuilder.createCorTO(produto.getCor()));
			}
			
			if (criarFabricante) {
				FabricanteTOBuilder fabricanteTOBuilder = new FabricanteTOBuilder();
				produtoTO.setFabricanteTO(fabricanteTOBuilder.createFabricanteTO(produto.getFabricante()));
			}
			
			if (criarTipoProduto) {
				TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
				produtoTO.setTipoProdutoTO(tipoProdutoTOBuilder.createTipoProdutoTO(produto.getTipoProduto()));
			}
			
			if (criarSistemaProduto) {
				SistemaProduto sistemaProduto = produto.getSistemaProduto();
				if (sistemaProduto != null) {
					SistemaProdutoTOBuilder sistemaProdutoTOBuilder = new SistemaProdutoTOBuilder();
					produtoTO.setSistemaProdutoTO(sistemaProdutoTOBuilder.buildTO(sistemaProduto));
				}
			}
			
			if (criarGama) {
				Gama gama = produto.getGama();
				if (gama != null) {
					GamaTOBuilder gamaTOBuilder = new GamaTOBuilder();
					produtoTO.setGamaTO(gamaTOBuilder.buildTO(gama));
				}
			}
			
			if (criarProdutoGrupoProduto) {
				List<ProdutoGrupoProduto> produtoGrupoProdutoList = produto.getProdutoGrupoProdutos();
				if (produtoGrupoProdutoList != null) {
					
					ProdutoGrupoProdutoTO produtoGrupoProdutoTO = new ProdutoGrupoProdutoTO();
					GrupoProdutoTO grupoProdutoTO = new GrupoProdutoTO();
					if (!produtoGrupoProdutoList.isEmpty()) {
						GrupoProduto grupoProduto = produtoGrupoProdutoList.get(0).getGrupoProduto();
						grupoProdutoTO.setIdGrupoProduto(grupoProduto.getIdGrupoProduto());
						grupoProdutoTO.setNmGrupoProduto(grupoProduto.getNmGrupoProduto());
					}
					produtoGrupoProdutoTO.setGrupoProdutoTO(grupoProdutoTO);
					produtoTO.setProdutoGrupoProdutoTO(produtoGrupoProdutoTO);
					
					produtoTO.setProdutoGrupoProdutosTO(new LinkedList<ProdutoGrupoProdutoTO>());
					for (ProdutoGrupoProduto produtoGrupoProduto : produto.getProdutoGrupoProdutos()) {
						produtoGrupoProdutoTO = new ProdutoGrupoProdutoTO();
						grupoProdutoTO = new GrupoProdutoTO();
						GrupoProduto grupoProduto = produtoGrupoProduto.getGrupoProduto();
						grupoProdutoTO.setIdGrupoProduto(grupoProduto.getIdGrupoProduto());
						grupoProdutoTO.setNmGrupoProduto(grupoProduto.getNmGrupoProduto());
						produtoGrupoProdutoTO.setGrupoProdutoTO(grupoProdutoTO);
						produtoTO.getProdutoGrupoProdutosTO().add(produtoGrupoProdutoTO);
					}
				}
			}
		}
		
		return produtoTO;
	}
	
	/**
	 * @param produtoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<ProdutoTO> createProdutoTOList(List<Produto> produtoList) {
		List<ProdutoTO> list = new ArrayList<ProdutoTO>();
		if (produtoList != null && produtoList.size() > 0) {
			for (Produto produto : produtoList) {
				list.add(createProdutoTO(produto));
			}
		}
		return list;
	}

	public boolean isCriarCor() {
		return criarCor;
	}

	public void setCriarCor(boolean criarCor) {
		this.criarCor = criarCor;
	}

	public boolean isCriarFabricante() {
		return criarFabricante;
	}

	public void setCriarFabricante(boolean criarFabricante) {
		this.criarFabricante = criarFabricante;
	}

	public boolean isCriarGama() {
		return criarGama;
	}

	public void setCriarGama(boolean criarGama) {
		this.criarGama = criarGama;
	}

	public boolean isCriarProdutoGrupoProduto() {
		return criarProdutoGrupoProduto;
	}

	public void setCriarProdutoGrupoProduto(boolean criarProdutoGrupoProduto) {
		this.criarProdutoGrupoProduto = criarProdutoGrupoProduto;
	}

	public boolean isCriarSistemaProduto() {
		return criarSistemaProduto;
	}

	public void setCriarSistemaProduto(boolean criarSistemaProduto) {
		this.criarSistemaProduto = criarSistemaProduto;
	}

	public boolean isCriarTipoProduto() {
		return criarTipoProduto;
	}

	public void setCriarTipoProduto(boolean criarTipoProduto) {
		this.criarTipoProduto = criarTipoProduto;
	}
	
}