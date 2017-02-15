package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AcaoAcessoFornecedor;
import com.indracompany.catalogo.to.AcaoAcessoFornecedorTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class AcaoAcessoFornecedorTOBuilder {
	
	/**
	 * @param acaoAcessoFornecedorTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public AcaoAcessoFornecedor createAcaoAcessoFornecedor(AcaoAcessoFornecedorTO acaoAcessoFornecedorTO) {
		
		AcaoAcessoFornecedor acaoAcessoFornecedor = null;
		
		if (acaoAcessoFornecedorTO != null) {
			acaoAcessoFornecedor = new AcaoAcessoFornecedor();
			acaoAcessoFornecedor.setIdAcao(acaoAcessoFornecedorTO.getIdAcao());
			acaoAcessoFornecedor.setIdFornecedor(acaoAcessoFornecedorTO.getIdFornecedor());
			acaoAcessoFornecedor.setIdPerfilSCA(acaoAcessoFornecedorTO.getIdPerfilSCA());
			acaoAcessoFornecedor.setDtCriacao(acaoAcessoFornecedorTO.getDtCriacao());
			acaoAcessoFornecedor.setNmUsuarioCriacao(acaoAcessoFornecedorTO.getNmUsuarioCriacao());
		}
		return acaoAcessoFornecedor;
	}
	
	/**
	 * @param acaoAcessoFornecedor
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public AcaoAcessoFornecedorTO createAcaoAcessoFornecedorTO(AcaoAcessoFornecedor acaoAcessoFornecedor) {
		
		AcaoAcessoFornecedorTO acaoAcessoFornecedorTO = null;
		
		if (acaoAcessoFornecedor != null) {
			acaoAcessoFornecedorTO = new AcaoAcessoFornecedorTO();
			acaoAcessoFornecedorTO.setIdAcao(acaoAcessoFornecedor.getIdAcao());
			acaoAcessoFornecedorTO.setIdFornecedor(acaoAcessoFornecedor.getIdFornecedor());
			acaoAcessoFornecedorTO.setIdPerfilSCA(acaoAcessoFornecedor.getIdPerfilSCA());
			acaoAcessoFornecedorTO.setDtCriacao(acaoAcessoFornecedor.getDtCriacao());
			acaoAcessoFornecedorTO.setNmUsuarioCriacao(acaoAcessoFornecedor.getNmUsuarioCriacao());
		}
		
		return acaoAcessoFornecedorTO;
	}
	
	/**
	 * @param acaoAcessoFornecedorList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<AcaoAcessoFornecedorTO> createAcaoAcessoFornecedorTOList(List<AcaoAcessoFornecedor> acaoAcessoFornecedorList) {
		
		List<AcaoAcessoFornecedorTO> list = new ArrayList<AcaoAcessoFornecedorTO>();
		
		if (acaoAcessoFornecedorList != null && acaoAcessoFornecedorList.size() > 0) {
			for (AcaoAcessoFornecedor acaoAcessoFornecedor : acaoAcessoFornecedorList) {
				list.add(createAcaoAcessoFornecedorTO(acaoAcessoFornecedor));
			}
		}
		
		return list;
	}
}
