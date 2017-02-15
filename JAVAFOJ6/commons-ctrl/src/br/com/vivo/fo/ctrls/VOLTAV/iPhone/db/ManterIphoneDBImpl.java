package br.com.vivo.fo.ctrls.VOLTAV.iPhone.db;

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

@Stateless(name = "ManterIphoneDB", mappedName = "ManterIphoneDB")
@Local(ManterIphoneDB.class)
@Session(ejbName = "ManterIphoneDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterIphoneDBImpl implements ManterIphoneDB {

	@EJB
	private DataBaseCall database;

    private Logger log = new Logger(ManterIphoneDBImpl.class.getName());

	@Override
	public LinhaIphone[] getLinhaIphoneGeral() throws SQLException {
		ArrayList<LinhaIphone> list = new ArrayList<LinhaIphone>();
		ResultSet rs = null;
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT NRLINHA linha, ");
		query.append("CDAREAREGISTRO ddd, ");
		query.append("1 idx ");
		query.append("FROM VOL.MAILINGIPHONE");

			st = database.getConnection().createStatement();
			
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			LinhaIphone linhaIphone = new LinhaIphone();
			linhaIphone.setDdd(rs.getString("ddd"));
			linhaIphone.setLinha(rs.getString("linha"));
			//linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
			list.add(linhaIphone);

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

		return list.toArray(new LinhaIphone[0]);

	}

	@Override
	public LinhaIphone[] getLinhaIphoneAll(Integer paginaInicial, Integer paginaFinal) throws SQLException {
		ArrayList<LinhaIphone> list = new ArrayList<LinhaIphone>();
		ResultSet rs = null;
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT NRLINHA linha, ");
		query.append("CDAREAREGISTRO ddd, ");
		query.append("LINHA idx ");
		query.append("FROM( SELECT nrlinha, ");
		query.append("cdarearegistro, ");
		query.append("ROWNUM AS linha FROM VOL.MAILINGIPHONE ) ");
		query.append("WHERE LINHA BETWEEN ").append(paginaInicial);
		query.append("AND ").append(paginaFinal);

			st = database.getConnection().createStatement();
			
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			LinhaIphone linhaIphone = new LinhaIphone();
			linhaIphone.setDdd(rs.getString("ddd"));
			linhaIphone.setLinha(rs.getString("linha"));
			//linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
			list.add(linhaIphone);

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

		return list.toArray(new LinhaIphone[0]);

	}

	@Override
	public LinhaIphone[] getLinhaIphoneByDdd(Integer ddd) throws SQLException {
		ArrayList<LinhaIphone> list = new ArrayList<LinhaIphone>();
		ResultSet rs = null;
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT NRLINHA linha, ");
		query.append("CDAREAREGISTRO ddd, ");
		query.append("LINHA idx FROM( SELECT nrlinha, ");
		query.append("cdarearegistro, ");
		query.append("ROWNUM AS linha FROM VOL.MAILINGIPHONE ");
		query.append("where cdarearegistro = ").append(ddd).append(")");

			st = database.getConnection().createStatement();
			
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			LinhaIphone linhaIphone = new LinhaIphone();
			linhaIphone.setDdd(rs.getString("ddd"));
			linhaIphone.setLinha(rs.getString("linha"));
			//linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
			list.add(linhaIphone);

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

		return list.toArray(new LinhaIphone[0]);

	}

	@Override
	public LinhaIphone[] getLinhaIphoneByLinha(Integer linha) throws SQLException {
		ArrayList<LinhaIphone> list = new ArrayList<LinhaIphone>();
		ResultSet rs = null;
		Statement st = null;

		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT NRLINHA linha, ");
		query.append("CDAREAREGISTRO ddd, ");
		query.append("LINHA idx FROM( SELECT nrlinha, ");
		query.append("cdarearegistro, ");
		query.append("ROWNUM AS linha FROM VOL.MAILINGIPHONE ");
		query.append("where nrlinha = ").append(linha).append(")");

			st = database.getConnection().createStatement();
			
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			LinhaIphone linhaIphone = new LinhaIphone();
			linhaIphone.setDdd(rs.getString("ddd"));
			linhaIphone.setLinha(rs.getString("linha"));
			//linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
			list.add(linhaIphone);
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

		return list.toArray(new LinhaIphone[0]);
	}

	@Override
	public void incluirLinha(Integer ddd, Integer linha) throws SQLException {
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO VOL.MAILINGIPHONE ");
		query.append("(CDAREAREGISTRO, ");
		query.append("NRLINHA) ");
		query.append("VALUES (").append(ddd).append(", ");
		query.append(" ").append(linha).append(")");

			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} finally {
			if (st != null) {
				st.close();
			}
			database.release();
		}
	}

	@Override
	public void excluirLinha(Integer ddd, Integer linha) throws SQLException {
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("delete from vol.MAILINGIPHONE ");
		query.append("where CDAREAREGISTRO = ").append(ddd);
		query.append("and NRLINHA = ").append(linha);

			st = database.getConnection().createStatement();
			st.executeUpdate(query.toString());
		} finally {
			if (st != null) {
				st.close();
			}
			database.release();
		}
	}

	@Override
	public LinhaIphone getTotalRegistroLinhaIphone(Integer ddd, Integer linha) throws SQLException {
		ResultSet rs = null;
		Statement st = null;
		LinhaIphone linhaIphone = new LinhaIphone();
		try {
		StringBuffer query = new StringBuffer();
/*		query.append("SELECT COUNT(*) ");
		query.append("totalRegistros FROM vol.MAILINGIPHONE ");
		query.append("where (cdarearegistro = ").append(ddd).append(") ");
		query.append("or ").append(ddd).append(" = -1) ");
		query.append("and (nrlinha = ").append(linha);
		query.append("or ").append(linha).append(" = -1) ");*/

		query.append(" select count(*) totalRegistros from vol.MAILINGIPHONE ");
		query.append(" where (cdarearegistro = " + ddd + " or " + ddd + " = -1) ");
		query.append(" and (nrlinha = " + linha + " or " + linha + " = -1) ");


			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			//linhaIphone.setDdd(rs.getString("ddd"));
			//linhaIphone.setLinha(rs.getString("linha"));
			linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			//linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
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

		return linhaIphone;
	}

	@Override
	public LinhaIphone getLinhaExiste(Integer ddd, Integer linha) throws SQLException {
		ResultSet rs = null;
		Statement st = null;
		LinhaIphone linhaIphone = new LinhaIphone();
		try {
		StringBuffer query = new StringBuffer();
		query.append("select count(*) ");
		query.append("totalRegistros from linha.linhabase lb, ");
		query.append("apoio.arearegistro ag ");
		query.append("where lb.nrlinha = ").append(linha);
		query.append("and ag.idarearegistro = lb.idarearegistro ");
		query.append("and ag.cdarearegistro = ").append(ddd);

			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			//linhaIphone.setDdd(rs.getString("ddd"));
			//linhaIphone.setLinha(rs.getString("linha"));
			linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			//linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
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

		return linhaIphone;

	}

	@Override
	public LinhaIphone isPremium(Integer ddd, Integer linha) throws SQLException {
		ResultSet rs = null;
		Statement st = null;
		LinhaIphone linhaIphone = new LinhaIphone();

		try {
		StringBuffer query = new StringBuffer();
		query.append("select count(*) totalRegistros ");
		query.append("from vol.MAILINGIPHONE ");
		query.append("where cdarearegistro = ").append(ddd);
		query.append("and nrlinha = ").append(linha);

			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			//linhaIphone.setDdd(rs.getString("ddd"));
			//linhaIphone.setLinha(rs.getString("linha"));
			linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			//linhaIphone.setIdx(rs.getString("idx"));
			//linhaIphone.setUf(rs.getString("uf"));
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

		return linhaIphone;

	}

	@Override
	public LinhaIphone getUfByIdPessoa(Integer ddd, Integer linha) throws SQLException {
		ResultSet rs = null;
		Statement st = null;
		LinhaIphone linhaIphone = new LinhaIphone();
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT UF.SGUF uf ");
		query.append("FROM APOIO.AREAREGISTRO AREAREGISTRO, ");
		query.append("APOIO.UF UF, CUSTOMER.UFOPERADORA UFOPERADORA ");
		query.append("WHERE AREAREGISTRO.CDAREAREGISTRO =  ").append(ddd);
		query.append("AND UFOPERADORA.IDUF = UF.IDUF ");
		query.append("AND AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA");

			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			//linhaIphone.setDdd(rs.getString("ddd"));
			//linhaIphone.setLinha(rs.getString("linha"));
			//linhaIphone.setTotalRegistros(rs.getString("totalRegistros"));
			//linhaIphone.setIdx(rs.getString("idx"));
			linhaIphone.setUf(rs.getString("uf"));
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

		return linhaIphone;

	}

	@Override
	public LinhaRelatorioIphone[] getRelatorioIphone(String cDtRelacionamentoInicio, String cDtRelacionamentoFinal, String inAtivadoEnvioMailSQL, String iNrLinhaSQL, String iCdAreaRegistroSQL, String iIdUFOperadoraSQL, String iIdGrupoOperadoraSQL) throws SQLException {
		ArrayList<LinhaRelatorioIphone> list = new ArrayList<LinhaRelatorioIphone>();
		ResultSet rs = null;
		Statement st = null;
		try {
		StringBuffer query = new StringBuffer();
		query.append("SELECT cdAreaRegistro cdAreaRegistro, ");
		query.append("linhabase.nrLinha nrLinha, ");
		query.append("TO_CHAR(dtCadastro, 'dd/mm/yyyy') dtCadastro, ");
		query.append("NVL(nmPessoa, ' ') nmPessoa, ");
		query.append("TO_CHAR(DECODE(nrLinhaFixo, NULL, 0, nrLinhaFixo))  nrLinhaFixo, ");
		query.append("NVL(dsEmail, ' ') dsEmail, ");
		query.append("NVL(dsEndereco, ' ') dsEndereco, ");
		query.append("NVL(cpf, ' ') cpf, ");
		query.append("DECODE(inativadoenviomail, 1, 'Sim', 2, 'Não') inAtivadoEnvioMail, ");
		query.append("DECODE(idtiporelacionamento, 1, 'U', 2, 'C') idTipoRelacionamento ");
		query.append("FROM VOL.CAMPANHAVIP CAMPANHAVIP, ");
		query.append("LINHA.LINHABASE LINHABASE, ");
		query.append("APOIO.AREAREGISTRO AREAREGISTRO, ");
		query.append("CUSTOMER.UFOPERADORA UFOPERADORA, ");
		query.append("CUSTOMER.OPERADORA OPERADORA ");
		query.append("WHERE CAMPANHAVIP.IDLINHABASE = LINHABASE.IDLINHABASE ");
		query.append("AND LINHABASE.IDAREAREGISTRO = AREAREGISTRO.IDAREAREGISTRO ");
		query.append("AND CAMPANHAVIP.DTCADASTRO BETWEEN ");
		query.append("TO_DATE(TO_CHAR('").append(cDtRelacionamentoInicio).append("') || '00:00:00','dd/mm/yyyy hh24:mi:ss') AND ");
		query.append("TO_DATE(TO_CHAR('").append(cDtRelacionamentoFinal).append("') || '23:59:59', 'dd/mm/yyyy hh24:mi:ss') ");
		query.append("AND AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA ");
		query.append("AND UFOPERADORA.IDPESSOADEPARAOPERADORA = OPERADORA.IDPESSOADEPARAOPERADORA ");
		query.append("AND (CAMPANHAVIP.INATIVADOENVIOMAIL = ").append(inAtivadoEnvioMailSQL).append(" OR ").append(inAtivadoEnvioMailSQL).append(" = 0) ");
		query.append("AND (LINHABASE.NRLINHA = ").append(iNrLinhaSQL).append(" OR ").append(iNrLinhaSQL).append(" = 0) ");
		query.append("AND (AREAREGISTRO.CDAREAREGISTRO = ").append(iCdAreaRegistroSQL).append(" OR ").append(iCdAreaRegistroSQL).append(" = 0) ");
		query.append("AND (AREAREGISTRO.IDUFOPERADORA = ").append(iIdUFOperadoraSQL).append(" OR ").append(iIdUFOperadoraSQL).append(" = 0) ");
		query.append("AND (OPERADORA.IDGRUPOOPERADORA = ").append(iIdGrupoOperadoraSQL).append(" OR ").append(iIdGrupoOperadoraSQL).append(" = 0) ");
		query.append("ORDER BY DTCADASTRO ");

			st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());

		while (rs.next()) {
			LinhaRelatorioIphone linhaRelatorioIphone = new LinhaRelatorioIphone();
			linhaRelatorioIphone.setCdAreaRegistro(rs.getString("cdAreaRegistro"));
			linhaRelatorioIphone.setNrLinha(rs.getString("nrLinha"));
			linhaRelatorioIphone.setDtCadastro(rs.getString("dtCadastro"));
			linhaRelatorioIphone.setNmPessoa(rs.getString("nmPessoa"));
			linhaRelatorioIphone.setNrLinhaFixo(rs.getString("nrLinhaFixo"));
			linhaRelatorioIphone.setDsEmail(rs.getString("dsEmail"));
			linhaRelatorioIphone.setCpf(rs.getString("cpf"));
			linhaRelatorioIphone.setInAtivadoEnvioMail(rs.getString("inAtivadoEnvioMail"));
			linhaRelatorioIphone.setDsEndereco(rs.getString("dsEndereco"));
			linhaRelatorioIphone.setIdTipoRelacionamento(rs.getString("IdTipoRelacionamento"));
			list.add(linhaRelatorioIphone);
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

		return list.toArray(new LinhaRelatorioIphone[0]);
	}

}
