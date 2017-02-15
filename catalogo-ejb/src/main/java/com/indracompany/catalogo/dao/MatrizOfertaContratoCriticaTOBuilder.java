package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.MatrizOfertaContratoCritica;
import com.indracompany.catalogo.to.MatrizOfertaContratoCriticaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class MatrizOfertaContratoCriticaTOBuilder {
	
	/**
	 * @param matrizOfertaContratoCriticaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public MatrizOfertaContratoCritica createMatrizOfertaContratoCritica(MatrizOfertaContratoCriticaTO matrizOfertaContratoCriticaTO) {
		
		MatrizOfertaContratoCritica matrizOfertaContratoCritica = null;
		
		if (matrizOfertaContratoCriticaTO != null) {
			matrizOfertaContratoCritica = new MatrizOfertaContratoCritica();
			matrizOfertaContratoCritica.setDscCriticaimportacao(matrizOfertaContratoCriticaTO.getDscCriticaimportacao());
			matrizOfertaContratoCritica.setIdMatrizOfertaArquivoCritica(matrizOfertaContratoCriticaTO.getIdMatrizOfertaArquivoCritica());
		}
		return matrizOfertaContratoCritica;
	}
	
	/**
	 * @param matrizOfertaContratoCritica
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public MatrizOfertaContratoCriticaTO createMatrizOfertaContratoCriticaTO(MatrizOfertaContratoCritica matrizOfertaContratoCritica) {
		
		MatrizOfertaContratoCriticaTO matrizOfertaContratoCriticaTO = null;
		
		if (matrizOfertaContratoCritica != null) {
			matrizOfertaContratoCriticaTO = new MatrizOfertaContratoCriticaTO();
			matrizOfertaContratoCriticaTO.setDscCriticaimportacao(matrizOfertaContratoCritica.getDscCriticaimportacao());
			matrizOfertaContratoCriticaTO.setIdMatrizOfertaArquivoCritica(matrizOfertaContratoCritica.getIdMatrizOfertaArquivoCritica());
		}
		
		return matrizOfertaContratoCriticaTO;
	}
	
	/**
	 * @param matrizOfertaContratoCriticaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<MatrizOfertaContratoCriticaTO> createMatrizOfertaContratoCriticaTOList(List<MatrizOfertaContratoCritica> matrizOfertaContratoCriticaList) {
		
		List<MatrizOfertaContratoCriticaTO> list = new ArrayList<MatrizOfertaContratoCriticaTO>();
		
		if (matrizOfertaContratoCriticaList != null && matrizOfertaContratoCriticaList.size() > 0) {
			for (MatrizOfertaContratoCritica matrizOfertaContratoCritica : matrizOfertaContratoCriticaList) {
				list.add(createMatrizOfertaContratoCriticaTO(matrizOfertaContratoCritica));
			}
		}
		
		return list;
	}
}
