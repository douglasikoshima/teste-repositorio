package br.com.vivo.fo.ctrls.admsistemas.crud.apoioparametro;

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
import br.com.vivo.fo.dbclasses.ApoioParametro;

@Stateless(name = "ApoioParametroControl", mappedName = "ApoioParametroControl")
@Local(ApoioParametroControl.class)
@Session(ejbName = "ApoioParametroControl", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ApoioParametroControlImpl implements ApoioParametroControl {

	@EJB
	private DataBaseCall database;

	public ApoioParametro getParametroById(long idParametro) throws SQLException {
		ApoioParametro apoioParametro = new ApoioParametro();
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDPARAMETRO, CDPARAMETRO, DSPARAMETRO, DSVALORPARAMETRO, IDUSUARIOALTERACAO, DTULTIMAALTERACAO FROM APOIO.PARAMETRO WHERE IDPARAMETRO = ").append(idParametro);
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
			if(rs.next()){
				apoioParametro.setIdParametro(rs.getLong("idParametro"));
				apoioParametro.setCdParametro(rs.getString("cdParametro"));
				apoioParametro.setDsParametro(rs.getString("dsParametro"));
				apoioParametro.setDsValorParametro(rs.getString("dsValorParametro"));
				apoioParametro.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
				apoioParametro.setIdUsuarioAlteracao(rs.getLong("idUsuarioAlteracao"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			database.release();
		}
		return apoioParametro;
	}

	public ApoioParametro[] getParametrosByInicialLetter(String initialLetter) throws SQLException {
		ArrayList<ApoioParametro> lista = new ArrayList<ApoioParametro>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDPARAMETRO,CDPARAMETRO,DSPARAMETRO,DSVALORPARAMETRO,IDUSUARIOALTERACAO,DTULTIMAALTERACAO FROM APOIO.PARAMETRO WHERE UPPER(CDPARAMETRO) LIKE '%").append(initialLetter).append("%'");
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
			while(rs.next()){
				ApoioParametro apoioParametro = new ApoioParametro();
				apoioParametro.setIdParametro(rs.getLong("idParametro"));
				apoioParametro.setCdParametro(rs.getString("cdParametro"));
				apoioParametro.setDsParametro(rs.getString("dsParametro"));
				apoioParametro.setDsValorParametro(rs.getString("dsValorParametro"));
				apoioParametro.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
				apoioParametro.setIdUsuarioAlteracao(rs.getLong("idUsuarioAlteracao"));
				lista.add(apoioParametro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			database.release();
		}
		return lista.toArray(new ApoioParametro[0]);
	}

	public ApoioParametro[] getApoioParametroArray(String sqlStatement) throws SQLException {
		ArrayList<ApoioParametro> lista = new ArrayList<ApoioParametro>();
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(sqlStatement);
			while(rs.next()){
				ApoioParametro apoioParametro = new ApoioParametro();
				apoioParametro.setIdParametro(rs.getLong("idParametro"));
				apoioParametro.setCdParametro(rs.getString("cdParametro"));
				apoioParametro.setDsParametro(rs.getString("dsParametro"));
				apoioParametro.setDsValorParametro(rs.getString("dsValorParametro"));
				apoioParametro.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
				apoioParametro.setIdUsuarioAlteracao(rs.getLong("idUsuarioAlteracao"));
				lista.add(apoioParametro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			database.release();
		}
		return lista.toArray(new ApoioParametro[0]);
	}

	public ApoioParametro[] getParametrosBySpecialCharacters() throws SQLException {
		ArrayList<ApoioParametro> lista = new ArrayList<ApoioParametro>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDPARAMETRO,CDPARAMETRO,DSPARAMETRO,DSVALORPARAMETRO,IDUSUARIOALTERACAO,DTULTIMAALTERACAO FROM APOIO.PARAMETRO WHERE ASCII(UPPER(SUBSTR(CDPARAMETRO,1,1))) < 65 OR ASCII(UPPER(SUBSTR(CDPARAMETRO,1,1))) > 90");
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
			while(rs.next()){
				ApoioParametro apoioParametro = new ApoioParametro();
				apoioParametro.setIdParametro(rs.getLong("idParametro"));
				apoioParametro.setCdParametro(rs.getString("cdParametro"));
				apoioParametro.setDsParametro(rs.getString("dsParametro"));
				apoioParametro.setDsValorParametro(rs.getString("dsValorParametro"));
				apoioParametro.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
				apoioParametro.setIdUsuarioAlteracao(rs.getLong("idUsuarioAlteracao"));
				lista.add(apoioParametro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			}
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			database.release();
		}
		return lista.toArray(new ApoioParametro[0]);
	}

	public void addParametro(String cdParametro, String dsParametro, String dsValorParametro, long idUsuario) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO APOIO.PARAMETRO (IDPARAMETRO,CDPARAMETRO,DSPARAMETRO,DSVALORPARAMETRO,IDUSUARIOALTERACAO,DTULTIMAALTERACAO) VALUES (APOIO.PARAMETROSQ.NEXTVAL,");
		query.append(cdParametro).append(",").append(dsParametro).append(",").append(dsValorParametro).append(",").append(idUsuario).append(",SYSDATE)");
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
		try {
					st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
			database.release();
		}
	}

	public void updateParametro(long idParametro, String cdParametro, String dsParametro, String dsValorParametro, long idUsuario) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE APOIO.PARAMETRO ");
		query.append(" SET CDPARAMETRO = ").append(cdParametro).append(", ");
		query.append("     DSPARAMETRO = ").append(dsParametro).append(", ");
		query.append("     DSVALORPARAMETRO = ").append(dsValorParametro).append(", ");
		query.append("     IDUSUARIOALTERACAO = ").append(idUsuario).append(", ");
		query.append("     DTULTIMAALTERACAO = SYSDATE ");
		query.append("WHERE IDPARAMETRO = ").append(idParametro);
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
			database.release();
		}
	}

	public void deleteParametro(long idParametro) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM APOIO.PARAMETRO WHERE IDPARAMETRO = ").append(idParametro);
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (st != null) {
				try {
					st.close();
		} catch (SQLException e) {
			e.printStackTrace();
				}
			}
			database.release();
		}
	}
}
