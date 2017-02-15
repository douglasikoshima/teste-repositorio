package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Caracteristica;
import com.indracompany.catalogo.datalayer.Fabricante;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.ModeloTipoProdutoRestricao;
import com.indracompany.catalogo.datalayer.ProdutoGrupoProduto;
import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.to.GrupoProdutoTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class GrupoProdutoTOBuilder {
	
	private boolean criarCor;
	private boolean criarProdutoGrupoProduto;
	private boolean criarTipoProduto;
	private boolean criarFabricante;
	private boolean criarTecnologia;
	private boolean criarModeloTipoProdutoRestricao;
	private boolean criarCaracteristica;
	
	private CorTOBuilder corTOBuilder = new CorTOBuilder();
	private TipoProdutoTOBuilder tipoProdutoTOBuilder = new TipoProdutoTOBuilder();
	private FabricanteTOBuilder fabricanteTOBuilder = new FabricanteTOBuilder();
	private TecnologiaTOBuilder tecnologiaTOBuilder = new TecnologiaTOBuilder();
	private ModeloTipoProdutoRestricaoTOBuilder modeloTipoProdutoRestricaoTOBuilder = new ModeloTipoProdutoRestricaoTOBuilder();
	private CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
	
	public GrupoProdutoTOBuilder() {
		this(true);
	}
	
	public GrupoProdutoTOBuilder(boolean criar) {
		criarCor = criar;
		criarProdutoGrupoProduto = criar;
		criarTipoProduto = criar;
		criarFabricante = criar;
		criarTecnologia = criar;
		criarModeloTipoProdutoRestricao = criar;
		criarCaracteristica = criar;
	}
	
	/**
	 * @param grupoProdutoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public GrupoProduto createGrupoProduto(GrupoProdutoTO grupoProdutoTO) {
		
		GrupoProduto grupoProduto = null;
		
		if (grupoProdutoTO != null) {
			grupoProduto = new GrupoProduto();
			
			CorTOBuilder corTOBuilder = new CorTOBuilder();
			grupoProduto.setCor(corTOBuilder.createCor(grupoProdutoTO.getCorTO()));
			grupoProduto.setDsNota(grupoProdutoTO.getDsNota());
			grupoProduto.setDtCriacao(grupoProdutoTO.getDtCriacao());
			grupoProduto.setDtUltimaAlteracao(grupoProdutoTO.getDtUltimaAlteracao());
			grupoProduto.setIdGrupoProduto(grupoProdutoTO.getIdGrupoProduto());
			grupoProduto.setInDisponivel(grupoProdutoTO.getInDisponivel());
			grupoProduto.setInFimVida(grupoProdutoTO.getInFimVida());
			grupoProduto.setNmGrupoProduto(grupoProdutoTO.getNmGrupoProduto());
			grupoProduto.setNmUsuarioAlteracao(grupoProdutoTO.getNmUsuarioAlteracao());
			grupoProduto.setNmUsuarioCriacao(grupoProdutoTO.getNmUsuarioCriacao());
			
			if (grupoProdutoTO.getFabricanteTO() != null) {
				grupoProduto.setFabricante(new Fabricante(grupoProdutoTO.getFabricanteTO().getIdFabricante()));
			}
			
			if (grupoProdutoTO.getTipoProdutoTO() != null) {
				grupoProduto.setTipoProduto(new TipoProduto(grupoProdutoTO.getTipoProdutoTO().getIdTipoProduto()));
			}
		}
		
		return grupoProduto;
	}
	
	/**
	 * @param grupoProduto
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public GrupoProdutoTO createGrupoProdutoTO(GrupoProduto grupoProduto) {
		
		GrupoProdutoTO grupoProdutoTO = null;
		
		if (grupoProduto != null) {
			grupoProdutoTO = new GrupoProdutoTO();
			grupoProdutoTO.setDsNota(grupoProduto.getDsNota());
			grupoProdutoTO.setDtCriacao(grupoProduto.getDtCriacao());
			grupoProdutoTO.setDtUltimaAlteracao(grupoProduto.getDtUltimaAlteracao());
			grupoProdutoTO.setIdGrupoProduto(grupoProduto.getIdGrupoProduto());
			grupoProdutoTO.setInDisponivel(grupoProduto.getInDisponivel());
			grupoProdutoTO.setInFimVida(grupoProduto.getInFimVida());
			grupoProdutoTO.setNmGrupoProduto(grupoProduto.getNmGrupoProduto());
			grupoProdutoTO.setNmUsuarioAlteracao(grupoProduto.getNmUsuarioAlteracao());
			grupoProdutoTO.setNmUsuarioCriacao(grupoProduto.getNmUsuarioCriacao());
			
			if (criarCor) {
				grupoProdutoTO.setCorTO(corTOBuilder.createCorTO(grupoProduto.getCor()));
			}
			
			if (criarProdutoGrupoProduto) {
				List<ProdutoGrupoProduto> produtoGrupoProdutoList = grupoProduto.getProdutoGrupoProdutoList();
				grupoProdutoTO.setQtProdutoGrupoProduto(produtoGrupoProdutoList != null ? produtoGrupoProdutoList.size() : 0);
			}
			
			if (criarTipoProduto) {
				grupoProdutoTO.setTipoProdutoTO(tipoProdutoTOBuilder.createTipoProdutoTO(grupoProduto.getTipoProduto()));
			}
			
			if (criarFabricante) {
				grupoProdutoTO.setFabricanteTO(fabricanteTOBuilder.createFabricanteTO(grupoProduto.getFabricante()));
			}
			
			if (criarTecnologia) {
				grupoProdutoTO.setTecnologiaTOList(tecnologiaTOBuilder.createTecnologiaTOList(grupoProduto.getTecnologiaList()));
			}
			
			if (criarModeloTipoProdutoRestricao) {
				List<ModeloTipoProdutoRestricao> modeloTipoProdutoRestricaoList = grupoProduto.getModeloTipoProdutoRestricaoList();
				if (modeloTipoProdutoRestricaoList != null && !modeloTipoProdutoRestricaoList.isEmpty()) {
					grupoProdutoTO.setModeloTipoProdutoRestricaoTOList(modeloTipoProdutoRestricaoTOBuilder.createModeloTipoProdutoRestricaoTOList(modeloTipoProdutoRestricaoList));
				}
			}
			
			if (criarCaracteristica) {
				List<Caracteristica> caracteristicaList = grupoProduto.getCaracteristicaList();
				if (caracteristicaList != null && !caracteristicaList.isEmpty()){
					grupoProdutoTO.setCaracteristicaTOList(caracteristicaTOBuilder.createCaracteristicaTOList(caracteristicaList));
				}
			}
			
		}
		
		return grupoProdutoTO;
	}
	
	/**
	 * @param grupoProdutoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<GrupoProdutoTO> createGrupoProdutoTOList(List<GrupoProduto> grupoProdutoList) {
		
		List<GrupoProdutoTO> list = new ArrayList<GrupoProdutoTO>();
		
		if (grupoProdutoList != null && !grupoProdutoList.isEmpty()) {
			for (GrupoProduto grupoProduto : grupoProdutoList) {
				list.add(createGrupoProdutoTO(grupoProduto));
			}
		}
		
		return list;
	}
	
	public boolean isCriarCaracteristica() {
		return criarCaracteristica;
	}

	public void setCriarCaracteristica(boolean criarCaracteristica) {
		this.criarCaracteristica = criarCaracteristica;
	}
	
	public void setCriarCaracteristica(CaracteristicaTOBuilder caracteristicaTOBuilder) {
		this.criarCaracteristica = true;
		this.caracteristicaTOBuilder = caracteristicaTOBuilder;
	}

	public boolean isCriarCor() {
		return criarCor;
	}

	public void setCriarCor(boolean criarCor) {
		this.criarCor = criarCor;
	}
	
	public void setCriarCor(CorTOBuilder corTOBuilder) {
		this.criarCor = true;
		this.corTOBuilder = corTOBuilder;
	}

	public boolean isCriarFabricante() {
		return criarFabricante;
	}

	public void setCriarFabricante(boolean criarFabricante) {
		this.criarFabricante = criarFabricante;
	}
	
	public void setCriarFabricante(FabricanteTOBuilder fabricanteTOBuilder) {
		this.criarFabricante = true;
		this.fabricanteTOBuilder = fabricanteTOBuilder;
	}

	public boolean isCriarModeloTipoProdutoRestricao() {
		return criarModeloTipoProdutoRestricao;
	}

	public void setCriarModeloTipoProdutoRestricao(boolean criarModeloTipoProdutoRestricao) {
		this.criarModeloTipoProdutoRestricao = criarModeloTipoProdutoRestricao;
	}
	
	public void setCriarModeloTipoProdutoRestricao(ModeloTipoProdutoRestricaoTOBuilder modeloTipoProdutoRestricaoTOBuilder) {
		this.criarModeloTipoProdutoRestricao = true;
		this.modeloTipoProdutoRestricaoTOBuilder = modeloTipoProdutoRestricaoTOBuilder;
	}

	public boolean isCriarProdutoGrupoProduto() {
		return criarProdutoGrupoProduto;
	}

	public void setCriarProdutoGrupoProduto(boolean criarProdutoGrupoProduto) {
		this.criarProdutoGrupoProduto = criarProdutoGrupoProduto;
	}

	public boolean isCriarTecnologia() {
		return criarTecnologia;
	}

	public void setCriarTecnologia(boolean criarTecnologia) {
		this.criarTecnologia = criarTecnologia;
	}
	
	public void setCriarTecnologia(TecnologiaTOBuilder tecnologiaTOBuilder) {
		this.criarTecnologia = true;
		this.tecnologiaTOBuilder = tecnologiaTOBuilder;
	}

	public boolean isCriarTipoProduto() {
		return criarTipoProduto;
	}

	public void setCriarTipoProduto(boolean criarTipoProduto) {
		this.criarTipoProduto = criarTipoProduto;
	}
	
	public void setCriarTipoProduto(TipoProdutoTOBuilder tipoProdutoTOBuilder) {
		this.criarTipoProduto = true;
		this.tipoProdutoTOBuilder = tipoProdutoTOBuilder;
	}
	
}