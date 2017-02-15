package br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.db.DataBaseCall;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "AcaoIncentivoDB", mappedName = "AcaoIncentivoDB")
@Local(AcaoIncentivoDB.class)
@Session(ejbName = "AcaoIncentivoDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AcaoIncentivoDBImpl implements AcaoIncentivoDB {

	@EJB
	private DataBaseCall database;
	
	
	
	public Acao[] getTodasAcoes() throws SQLException {
		ArrayList<Acao> list = new ArrayList<Acao>();
		Statement st = null;
		ResultSet rs = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT CDACAOINCENTIVO, DSACAOINCENTIVO, ");
		query.append("TO_CHAR(DTHORAINICIO, 'DD/MM/YYYY HH24:MI:SS') DTHORAINICIO, ");
		query.append("TO_CHAR(DTHORATERMINO, 'DD/MM/YYYY HH24:MI:SS') DTHORATERMINO, ");
		query.append("INTIPOACAOINCENTIVO, MSGANTESINICIOACAO, MSGAPOSINICIOACAO, ");
		query.append("INATIVO FROM CUSTOMER.ACAO_INCENTIVO");
		
			Connection conn = database.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			Acao acao = new Acao();
			acao.setCdacaoincentivo(rs.getString("CDACAOINCENTIVO"));
			acao.setDsacaoincentivo(rs.getString("DSACAOINCENTIVO"));
			acao.setDthorainicio(rs.getString("DTHORAINICIO"));
			acao.setDthoratermino(rs.getString("DTHORATERMINO"));
			acao.setIntipoacaoincentivo(rs.getString("INTIPOACAOINCENTIVO"));
			acao.setMsgantesinicioacao(rs.getString("MSGANTESINICIOACAO"));
			acao.setMsgaposinicioacao(rs.getString("MSGAPOSINICIOACAO"));
			acao.setInativo(rs.getString("INATIVO"));
			list.add(acao);
		}
		} finally {
			if (rs != null) {
		rs.close();
			}
			if (st != null) {
				st.close();
			}
		database.release();
		}

		return list.toArray(new Acao[0]);
	}

	public Acao[] getPesquisaAcoes(String sqlStatement) throws SQLException {
		ArrayList<Acao> list = new ArrayList<Acao>();
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection conn = database.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sqlStatement);

		while (rs.next()) {
			Acao acao = new Acao();
			acao.setCdacaoincentivo(rs.getString("CDACAOINCENTIVO"));
			acao.setDsacaoincentivo(rs.getString("DSACAOINCENTIVO"));
			acao.setDthorainicio(rs.getString("DTHORAINICIO"));
			acao.setDthoratermino(rs.getString("DTHORATERMINO"));
			acao.setIntipoacaoincentivo(rs.getString("INTIPOACAOINCENTIVO"));
			acao.setMsgantesinicioacao(rs.getString("MSGANTESINICIOACAO"));
			acao.setMsgaposinicioacao(rs.getString("MSGAPOSINICIOACAO"));
			acao.setInativo(rs.getString("INATIVO"));
			list.add(acao);
		}
		} finally {
			if (rs != null) {
		rs.close();
			}
			if (st != null) {
				st.close();
			}
		database.release();
		}

		return list.toArray(new Acao[0]);
	}

	public Acao buscarAcaoIncentivo(String id) throws SQLException {
		Statement st = null;
		ResultSet rs = null;
		Acao acao = new Acao();
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT CDACAOINCENTIVO,DSACAOINCENTIVO, ");
		query.append("TO_CHAR(DTHORAINICIO, 'DD/MM/YYYY HH24:MI') DTHORAINICIO, ");
		query.append("TO_CHAR(DTHORATERMINO, 'DD/MM/YYYY HH24:MI') DTHORATERMINO, ");
		query.append("INTIPOACAOINCENTIVO, MSGANTESINICIOACAO, MSGAPOSINICIOACAO, ");
		query.append("INATIVO FROM CUSTOMER.ACAO_INCENTIVO WHERE CDACAOINCENTIVO = ").append(id);

			Connection conn = database.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(query.toString());

		if (rs.next()) {
			acao.setCdacaoincentivo(rs.getString("CDACAOINCENTIVO"));
			acao.setDsacaoincentivo(rs.getString("DSACAOINCENTIVO"));
			acao.setDthorainicio(rs.getString("DTHORAINICIO"));
			acao.setDthoratermino(rs.getString("DTHORATERMINO"));
			acao.setIntipoacaoincentivo(rs.getString("INTIPOACAOINCENTIVO"));
			acao.setMsgantesinicioacao(rs.getString("MSGANTESINICIOACAO"));
			acao.setMsgaposinicioacao(rs.getString("MSGAPOSINICIOACAO"));
			acao.setInativo(rs.getString("INATIVO"));
		}
		} finally {
			if (rs != null) {
		rs.close();
			}
			if (st != null) {
				st.close();
			}
		database.release();
		}

		return acao;
	}

	public void excluirAcaoIncentivo(String id) throws SQLException {
		Statement st = null;
		try {
		String query = "DELETE FROM CUSTOMER.ACAO_INCENTIVO WHERE CDACAOINCENTIVO = " + id;
			st = database.getConnection().createStatement();
			st.executeUpdate(query);
		} finally {
			if (st != null) {
				st.close();
			}
		database.release();
	}
	}

	public void inserirAcaoIncentivo(String id, String descricao, String dataInicial, String dataFinal, String tipo, String msgInicial, String msgFinal, String inAtivo, String user) throws SQLException {
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO CUSTOMER.ACAO_INCENTIVO ");
		query.append("(CDACAOINCENTIVO,DSACAOINCENTIVO,DTHORAINICIO,DTHORATERMINO,INTIPOACAOINCENTIVO,MSGANTESINICIOACAO,MSGAPOSINICIOACAO, IDUSUARIOALTERACAO, DTULTIMAALTERACAO, DTCADASTRO, IDUSUARIOCADASTRO, INATIVO) ");
		query.append("VALUES (CUSTOMER.ACAO_INCENTIVOSQ.NEXTVAL, '");
		query.append(descricao).append("' , ");
		query.append("TO_DATE('").append(dataInicial).append("', 'dd/mm/yyyy hh24:mi:ss'), ");
		query.append("TO_DATE('").append(dataFinal).append("', 'dd/mm/yyyy hh24:mi:ss'), '");
		query.append(tipo).append("' , '");
		query.append(msgInicial).append("' , '");
		query.append(msgFinal).append("' , ");
		query.append(user).append(" , ");
		query.append("SYSDATE").append(" , ");
		query.append("SYSDATE").append(" , ");
		query.append(user).append(" , ");
		query.append(inAtivo).append(")");
		
			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} finally {
			if (st != null) {
				st.close();
			}
		database.release();
	}
	}

	public void atualizarAcaoIncentivo(String id, String descricao, String dataInicial, String dataFinal, String tipo, String msgInicial, String msgFinal, String inAtivo, String user) throws SQLException {
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE CUSTOMER.ACAO_INCENTIVO ");
		query.append("SET DSACAOINCENTIVO = '").append(descricao).append("' , ");
		query.append("DTHORAINICIO = TO_DATE('").append(dataInicial).append("', 'dd/mm/yyyy hh24:mi:ss'), ");
		query.append("DTHORATERMINO = TO_DATE('").append(dataFinal).append("', 'dd/mm/yyyy hh24:mi:ss'), ");
		query.append("INTIPOACAOINCENTIVO = '").append(tipo).append("' , ");
		query.append("MSGANTESINICIOACAO = '").append(msgInicial).append("' , ");
		query.append("MSGAPOSINICIOACAO = '").append(msgFinal).append("' , ");
		query.append("IDUSUARIOALTERACAO = ").append(user).append(" , ");
		query.append("DTULTIMAALTERACAO = ").append("SYSDATE").append(" , ");
		query.append("INATIVO = ").append(inAtivo).append(" ");
		query.append("WHERE CDACAOINCENTIVO = ").append(id);
		
			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} finally {
			if (st != null) {
				st.close();
			}
		database.release();
		}
	}

}
