package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Caracteristica;
import com.indracompany.catalogo.datalayer.ValorCaracteristica;
import com.indracompany.catalogo.to.CaracteristicaTO;

/**
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class CaracteristicaTOBuilder {
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Caracteristica createCaracteristica(CaracteristicaTO caracteristicaTO) {
		
		GrupoCaracteristicaTOBuilder grupoCaracteristicaTOBuilder = new GrupoCaracteristicaTOBuilder();
		
		Caracteristica caracteristica = null;
		
		if (caracteristicaTO != null) {
			caracteristica = new Caracteristica();
			caracteristica.setDescricao(caracteristicaTO.getDescricao());
			caracteristica.setDtCriacao(caracteristicaTO.getDtCriacao());
			caracteristica.setDtUltimaAlteracao(caracteristicaTO.getDtUltimaAlteracao());
			caracteristica.setGrupoCaracteristica(grupoCaracteristicaTOBuilder.createGrupoCaracteristica(caracteristicaTO.getGrupoCaracteristicaTO()));
			caracteristica.setIdCaracteristica(caracteristicaTO.getIdCaracteristica());
			caracteristica.setIndComparavel(caracteristicaTO.getIndComparavel());
			caracteristica.setIndExibivel(caracteristicaTO.getIndExibivel());
			caracteristica.setInDisponivel(caracteristicaTO.getInDisponivel());
			caracteristica.setInSimulador(caracteristicaTO.getInSimulador());
			caracteristica.setNmCaracteristica(caracteristicaTO.getNmCaracteristica());
			caracteristica.setNmUsuarioAlteracao(caracteristicaTO.getNmUsuarioAlteracao());
			caracteristica.setNmUsuarioCriacao(caracteristicaTO.getNmUsuarioCriacao());
		}
		return caracteristica;
	}
	
	/**
	 * @param acao
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public CaracteristicaTO createCaracteristicaTO(Caracteristica caracteristica) {
		
		CaracteristicaTO caracteristicaTO = null;
		GrupoCaracteristicaTOBuilder grupoCaracteristicaTOBuilder = new GrupoCaracteristicaTOBuilder();
		
		if (caracteristica != null) {
			caracteristicaTO = new CaracteristicaTO();
			caracteristicaTO.setDescricao(caracteristica.getDescricao());
			caracteristicaTO.setDtCriacao(caracteristica.getDtCriacao());
			caracteristicaTO.setDtUltimaAlteracao(caracteristica.getDtUltimaAlteracao());
			caracteristicaTO.setGrupoCaracteristicaTO(grupoCaracteristicaTOBuilder.createGrupoCaracteristicaTO(caracteristica.getGrupoCaracteristica()));
			caracteristicaTO.setIdCaracteristica(caracteristica.getIdCaracteristica());
			caracteristicaTO.setIndComparavel(caracteristica.getIndComparavel());
			caracteristicaTO.setIndExibivel(caracteristica.getIndExibivel());
			caracteristicaTO.setInDisponivel(caracteristica.getInDisponivel());
			caracteristicaTO.setInSimulador(caracteristica.getInSimulador());
			caracteristicaTO.setNmCaracteristica(caracteristica.getNmCaracteristica());
			caracteristicaTO.setNmUsuarioAlteracao(caracteristica.getNmUsuarioAlteracao());
			caracteristicaTO.setNmUsuarioCriacao(caracteristica.getNmUsuarioCriacao());
			caracteristicaTO.setExistGrupoProdutoCaracteristica((caracteristica.getGrupoProdutoCaracteristicaList() != null && caracteristica.getGrupoProdutoCaracteristicaList().size() > 0));
			List<ValorCaracteristica> valorCaracteristicaList = caracteristica.getValorCaracteristicaList();
			if(valorCaracteristicaList != null && !valorCaracteristicaList.isEmpty()){
				caracteristicaTO.setDominioValoresCaracteristica(new ValorCaracteristicaTOBuilder().createBasicValorCaracteristicaTOList(valorCaracteristicaList));
			}
		}
		
		return caracteristicaTO;
	}
	
	/**
	 * @param caracteristicaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<CaracteristicaTO> createCaracteristicaTOList(List<Caracteristica> caracteristicaList) {
		
		List<CaracteristicaTO> list = new ArrayList<CaracteristicaTO>();
		
		if (caracteristicaList != null && caracteristicaList.size() > 0) {
			for (Caracteristica caracteristica : caracteristicaList) {
				list.add(createCaracteristicaTO(caracteristica));
			}
		}
		
		return list;
	}
	
	/**
	 * @param caracteristicaTOList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de TO em uma lista de Entity.
	 */
	public List<Caracteristica> createCaracteristicaList(List<CaracteristicaTO> caracteristicaTOList) {
		
		List<Caracteristica> list = new ArrayList<Caracteristica>();
		
		if (caracteristicaTOList != null && caracteristicaTOList.size() > 0) {
			for (CaracteristicaTO caracteristicaTO : caracteristicaTOList) {
				list.add(createCaracteristica(caracteristicaTO));
			}
		}
		
		return list;
	}

	public List<Integer> getIdList(List<CaracteristicaTO> caracteristicaTOList) {
		
		List<Integer> idList = new ArrayList<Integer>();
		
		if (caracteristicaTOList != null && !caracteristicaTOList.isEmpty()) {
			for (CaracteristicaTO caracteristicaTO : caracteristicaTOList) {
				idList.add(caracteristicaTO.getIdCaracteristica());
			}
		}
		
		return idList;
	}
}
