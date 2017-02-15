package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.SistemaProduto;
import com.indracompany.catalogo.to.SistemaProdutoTO;

public class SistemaProdutoTOBuilder {
	
	public SistemaProdutoTO buildTO(SistemaProduto entity) {
		SistemaProdutoTO to = null;
		if (entity != null) {
			to = new SistemaProdutoTO();
			to.setIdSistemaProduto(entity.getIdSistemaProduto());
			to.setCdCodigo(entity.getCdCodigo());
			to.setNmUsuarioCriacao(entity.getNmUsuarioCriacao());
			to.setDtCriacao(entity.getDtCriacao());
			to.setNmUsuarioAlteracao(entity.getNmUsuarioAlteracao());
			to.setDtUltimaAlteracao(entity.getDtUltimaAlteracao());
		}
		return to;
	}
	
	public List<SistemaProdutoTO> buildTOList(List<SistemaProduto> entityList) {
		List<SistemaProdutoTO> toList = new ArrayList<SistemaProdutoTO>();
		for (SistemaProduto entity : entityList) {
			toList.add(this.buildTO(entity));
		}
		return toList;
	}
	
}