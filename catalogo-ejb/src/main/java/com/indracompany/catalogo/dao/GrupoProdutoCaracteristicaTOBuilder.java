package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Caracteristica;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.GrupoProdutoCaracteristica;
import com.indracompany.catalogo.datalayer.ValorCaracteristica;
import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoCaracteristicaTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ValorCaracteristicaTO;

/**
 * @author gmuniz
 * 
 */
public class GrupoProdutoCaracteristicaTOBuilder {
	
	private boolean criarGrupoProduto;
	private boolean criarCaracteristica;
	private boolean criarValorCaracteristica;
	
	public GrupoProdutoCaracteristicaTOBuilder() {
		this(true);
	}
	
	public GrupoProdutoCaracteristicaTOBuilder(boolean criar) {
		criarGrupoProduto = criar;
		criarCaracteristica = criar;
		criarValorCaracteristica = criar;
	}
	
	/**
	 * @param grupoProdutoCaracteristicaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public GrupoProdutoCaracteristica createGrupoProdutoCaracteristica(GrupoProdutoCaracteristicaTO grupoProdutoCaracteristicaTO) {
		GrupoProdutoCaracteristica grupoProdutoCaracteristica = null;
		if (grupoProdutoCaracteristicaTO != null) {
			
			grupoProdutoCaracteristica = new GrupoProdutoCaracteristica();
			grupoProdutoCaracteristica.setNmUsuarioCriacao(grupoProdutoCaracteristicaTO.getNmUsuarioCriacao());
			grupoProdutoCaracteristica.setDtCriacao(grupoProdutoCaracteristicaTO.getDtCriacao());
			grupoProdutoCaracteristica.setNmUsuarioAlteracao(grupoProdutoCaracteristicaTO.getNmUsuarioAlteracao());
			grupoProdutoCaracteristica.setDtUltimaAlteracao(grupoProdutoCaracteristicaTO.getDtUltimaAlteracao());
			
			GrupoProdutoTO grupoProdutoTO = grupoProdutoCaracteristicaTO.getGrupoProdutoTO();
			if (grupoProdutoTO != null) {
				grupoProdutoCaracteristica.setGrupoProduto(new GrupoProduto(grupoProdutoTO.getIdGrupoProduto()));
			}
			
			CaracteristicaTO caracteristicaTO = grupoProdutoCaracteristicaTO.getCaracteristicaTO();
			if (caracteristicaTO != null) {
				grupoProdutoCaracteristica.setCaracteristica(new Caracteristica(caracteristicaTO.getIdCaracteristica()));
			}
			
			ValorCaracteristicaTO valorCaracteristicaTO = grupoProdutoCaracteristicaTO.getValorCaracteristicaTO();
			if (valorCaracteristicaTO != null) {
				grupoProdutoCaracteristica.setValorCaracteristica(new ValorCaracteristica(valorCaracteristicaTO.getIdValorCaracteristica()));
			}
		}
		return grupoProdutoCaracteristica;
	}
	
	/**
	 * @param grupoProdutoCaracteristica
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public GrupoProdutoCaracteristicaTO createGrupoProdutoCaracteristicaTO(GrupoProdutoCaracteristica grupoProdutoCaracteristica) {
		GrupoProdutoCaracteristicaTO grupoProdutoCaracteristicaTO = null;
		
		if (grupoProdutoCaracteristica != null) {
			grupoProdutoCaracteristicaTO = new GrupoProdutoCaracteristicaTO();
			grupoProdutoCaracteristicaTO.setIdGrupoProdutoCaracteristica(grupoProdutoCaracteristica.getIdGrupoProdutoCaracteristica());
			grupoProdutoCaracteristicaTO.setNmUsuarioCriacao(grupoProdutoCaracteristica.getNmUsuarioCriacao());
			grupoProdutoCaracteristicaTO.setDtCriacao(grupoProdutoCaracteristica.getDtCriacao());
			grupoProdutoCaracteristicaTO.setNmUsuarioAlteracao(grupoProdutoCaracteristica.getNmUsuarioAlteracao());
			grupoProdutoCaracteristicaTO.setDtUltimaAlteracao(grupoProdutoCaracteristica.getDtUltimaAlteracao());
			
			if (criarGrupoProduto) {
				GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder(false);
				GrupoProduto grupoProduto = grupoProdutoCaracteristica.getGrupoProduto();
				GrupoProdutoTO grupoProdutoTO = grupoProdutoTOBuilder.createGrupoProdutoTO(grupoProduto);
				grupoProdutoCaracteristicaTO.setGrupoProdutoTO(grupoProdutoTO);
			}
			
			if (criarCaracteristica) {
				CaracteristicaTOBuilder caracteristicaTOBuilder = new CaracteristicaTOBuilder();
				Caracteristica caracteristica = grupoProdutoCaracteristica.getCaracteristica();
				CaracteristicaTO caracteristicaTO = caracteristicaTOBuilder.createCaracteristicaTO(caracteristica);
				grupoProdutoCaracteristicaTO.setCaracteristicaTO(caracteristicaTO);
			}
			
			if (criarValorCaracteristica) {
				ValorCaracteristicaTOBuilder valorCaracteristicaTOBuilder = new ValorCaracteristicaTOBuilder();
				ValorCaracteristica valorCaracteristica = grupoProdutoCaracteristica.getValorCaracteristica();
				ValorCaracteristicaTO valorCaracteristicaTO = valorCaracteristicaTOBuilder.createValorCaracteristicaTO(valorCaracteristica);
				grupoProdutoCaracteristicaTO.setValorCaracteristicaTO(valorCaracteristicaTO);
				grupoProdutoCaracteristicaTO.setCaracteristicaTO(valorCaracteristicaTO.getCaracteristicaTO());
			}
		}
		
		return grupoProdutoCaracteristicaTO;
	}
	
	/**
	 * @param grupoProdutoCaracteristicaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<GrupoProdutoCaracteristicaTO> createGrupoProdutoCaracteristicaTOList(List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList) {
		List<GrupoProdutoCaracteristicaTO> list = new ArrayList<GrupoProdutoCaracteristicaTO>();
		
		if (grupoProdutoCaracteristicaList != null && !grupoProdutoCaracteristicaList.isEmpty()) {
			for (GrupoProdutoCaracteristica grupoProdutoCaracteristica : grupoProdutoCaracteristicaList) {
				list.add(createGrupoProdutoCaracteristicaTO(grupoProdutoCaracteristica));
			}
		}
		
		return list;
	}
	
	public boolean isCriarGrupoProduto() {
		return criarGrupoProduto;
	}
	
	public void setCriarGrupoProduto(boolean criarGrupoProduto) {
		this.criarGrupoProduto = criarGrupoProduto;
	}
	
	public boolean isCriarCaracteristica() {
		return criarCaracteristica;
	}
	
	public void setCriarCaracteristica(boolean criarCaracteristica) {
		this.criarCaracteristica = criarCaracteristica;
	}
	
	public boolean isCriarValorCaracteristica() {
		return criarValorCaracteristica;
	}
	
	public void setCriarValorCaracteristica(boolean criarValorCaracteristica) {
		this.criarValorCaracteristica = criarValorCaracteristica;
	}
	
}