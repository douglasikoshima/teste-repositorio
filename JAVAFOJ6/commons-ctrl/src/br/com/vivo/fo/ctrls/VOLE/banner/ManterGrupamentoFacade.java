package br.com.vivo.fo.ctrls.VOLE.banner;

import java.sql.SQLException;

import javax.ejb.Local;

import br.com.vivo.fo.dbclasses.GrupamentoAcesso;

@Local
public interface ManterGrupamentoFacade {
	
	void incluirGrupamento(GrupamentoAcesso grupamentoAcesso, String user) throws SQLException;
	
	void alterarGrupamento(GrupamentoAcesso grupamentoAcesso, String user) throws SQLException;
	
	void removerGrupamento(GrupamentoAcesso grupamentoAcesso) throws SQLException;
	
	int existeSiglaGrupamento(String siglaGrupamento) throws SQLException;
	
	GrupamentoAcesso[] listarGrupamento(GrupamentoAcesso grupamentoAcesso) throws SQLException;
}
