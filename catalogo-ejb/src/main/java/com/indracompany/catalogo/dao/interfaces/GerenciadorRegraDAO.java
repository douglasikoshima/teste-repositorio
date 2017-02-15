package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO;

public interface GerenciadorRegraDAO {
	
	public List<String> buscarNmIndicadorComercial(Long inTipoFiltro) throws DAOException;
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisar(GerenciadorRegrasConfiguracaoTO to) throws DAOException;
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisarPorIdCanalAtendimento(GerenciadorRegrasConfiguracaoTO to) throws DAOException;
	
	public void salvar(GerenciadorRegrasConfiguracaoTO to) throws DAOException;
}
