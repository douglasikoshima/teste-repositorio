package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.FornecedorSCA;
import com.indracompany.catalogo.to.FornecedorSCATO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FornecedorSCATOBuilder {
	
	/**
	 * @param fornecedorSCATO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public FornecedorSCA createFornecedorSCA(FornecedorSCATO fornecedorSCATO) {
		
		FornecedorSCA fornecedorSCA = null;
		
		if (fornecedorSCATO != null) {
			fornecedorSCA = new FornecedorSCA();
			fornecedorSCA.setIdFornecedorSCA(fornecedorSCATO.getIdFornecedorSCA());
			fornecedorSCA.setNmFornecedorSCA(fornecedorSCATO.getNmFornecedorSCA());
		}
		
		return fornecedorSCA;
	}
	
	/**
	 * @param fornecedorSCA
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FornecedorSCATO createFornecedorSCATO(FornecedorSCA fornecedorSCA) {
		
		FornecedorSCATO fornecedorSCATO = null;
		
		if (fornecedorSCA != null) {
			fornecedorSCATO = new FornecedorSCATO();
			fornecedorSCATO.setIdFornecedorSCA(fornecedorSCA.getIdFornecedorSCA());
			fornecedorSCATO.setNmFornecedorSCA(fornecedorSCA.getNmFornecedorSCA());
		}
		
		return fornecedorSCATO;
	}
	
	/**
	 * @param fornecedorSCAList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FornecedorSCATO> createFornecedorSCATOList(List<FornecedorSCA> fornecedorSCAList) {
		
		List<FornecedorSCATO> list = new ArrayList<FornecedorSCATO>();
		
		if (fornecedorSCAList != null && fornecedorSCAList.size() > 0) {
			for (FornecedorSCA fornecedorSCA : fornecedorSCAList) {
				list.add(createFornecedorSCATO(fornecedorSCA));
			}
		}
		
		return list;
	}
}
