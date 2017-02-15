package br.com.vivo.fo.ctrls.extworkflw.token.db;

import java.sql.SQLException;

import javax.ejb.Local;


@Local
public interface TokenDB {
	public DadosTokenVivo360 getDadosToken(String id) throws SQLException;
}
