package br.com.vivo.fo.ctrls.admsistemas.arquivos;

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
import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.ArquivosRS;
import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.RelatorioUpload;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "ArquivosDB", mappedName = "ArquivosDB")
@Local(ArquivosDB.class)
@Session(ejbName = "ArquivosDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArquivosDBImpl implements ArquivosDB {

	@EJB
	private DataBaseCall database;

	public ArquivosRS[] executeQuery(String query) {
		ArrayList<ArquivosRS> lista = new ArrayList<ArquivosRS>();
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				ArquivosRS arquivosRS = new ArquivosRS();
				arquivosRS.setDsErroProcessamento(rs.getString("dsErroProcessamento"));
				arquivosRS.setDtInclusao(rs.getString("dtInclusao"));
				arquivosRS.setDtProcessamento(rs.getString("dtProcessamento"));
				//arquivosRS.setIdAtendimentoAnatelArquivo(rs.getString("idAtendimentoAnatelArquivo"));
				arquivosRS.setNmArquivo(rs.getString("nmArquivo"));
				//arquivosRS.setNmArquivoOriginal(rs.getString("nmArquivoOriginal"));
				arquivosRS.setNmLoginUsuario(rs.getString("nmLoginUsuario"));
				arquivosRS.setQtRegistrosDescartados(rs.getInt("qtRegistrosDescartados"));
				arquivosRS.setQtRegistrosProcessados(rs.getInt("qtRegistrosProcessados"));
				lista.add(arquivosRS);
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
		return lista.toArray(new ArquivosRS[0]);
	}

	public int getInt(String query) {
		int retorno = 0;
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				retorno = rs.getInt(1);
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
		return retorno;
	}

	public void executeCommand(String query) {
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			st.executeUpdate(query);
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

	public RelatorioUpload buscarRelatorioUpload(String query) {
		RelatorioUpload relatorioUpload = new RelatorioUpload();
		ResultSet rs = null;
		Statement st = null;
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(query);
			if (rs.next()) {
				relatorioUpload.setNmArquivoOriginal(rs.getString("nmArquivoOriginal"));
				relatorioUpload.setNmArquivo(rs.getString("nmArquivo"));
				relatorioUpload.setDtEnvio(rs.getString("dtEnvio"));
				relatorioUpload.setDsArquivoLog(rs.getString("dsArquivoLog"));
				relatorioUpload.setIdAtendimentoAnatelArquivo(rs.getString("idAtendimentoAnatelArquivo"));
				relatorioUpload.setQtTotalRegistros(rs.getInt("qtTotalRegistros"));
				relatorioUpload.setQtProcessado(rs.getInt("qtProcessado"));
				relatorioUpload.setQtRejeitado(rs.getInt("qtRejeitado"));
				relatorioUpload.setQtProtocoloCliente(rs.getInt("qtProtocoloCliente"));
				relatorioUpload.setQtProtocoloNaoCliente(rs.getInt("qtProtocoloNaoCliente"));
				relatorioUpload.setQtContatoNovo(rs.getInt("qtContatoNovo"));
				relatorioUpload.setQtContatoGenerico(rs.getInt("qtContatoGenerico"));
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
		return relatorioUpload;
	}
}
