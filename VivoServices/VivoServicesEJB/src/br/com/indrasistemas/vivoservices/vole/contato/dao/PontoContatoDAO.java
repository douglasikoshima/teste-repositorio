package br.com.indrasistemas.vivoservices.vole.contato.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.vole.contato.to.PontoContatoTO;

public class PontoContatoDAO extends HibernateBaseDAO {

	public List buscarDDD(PontoContatoTO filtro) throws DAOException {

		List retorno = null;

		String sql = "SELECT DISTINCT \r\n"
				+ "   arearegistro.cdarearegistro \r\n"
				+ "FROM \r\n"
				+ "   customer.conta         conta, \r\n"
				+ "   customer.linhaconta    linhaconta, \r\n"
				+ "   linha.linhatelefonica  linhatelefonica, \r\n"
				+ "   linha.linhabase        linhabase, \r\n"
				+ "   apoio.arearegistro     arearegistro \r\n"
				+ "WHERE \r\n"
				+ "   conta.idconta = linhaconta.idconta \r\n"
				+ "AND \r\n"
				+ "   linhatelefonica.idlinhatelefonica = linhaconta.idlinhatelefonica \r\n"
				+ "AND \r\n"
				+ "   linhabase.idlinhabase = linhatelefonica.idlinhabase \r\n"
				+ "AND \r\n"
				+ "   arearegistro.idarearegistro = linhabase.idarearegistro \r\n"
				+ "AND \r\n" + "   conta.cdconta = ? \r\n";

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, filtro.getCdConta());

			ResultSet rs = ps.executeQuery();
			retorno = PontoContatoTOBuilder.buildPontoContatoDDDTO(rs);
		} catch (Exception ex) {
			//throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return retorno;
	}

	/*public List buscarUF(PontoContatoTO filtro) throws DAOException {*/
	public List buscarUF(List cdAreaRegistro) throws DAOException {

		List retorno = null;

		/*
		 String sql = "SELECT \r\n"
		 + "   UF.SGUF \r\n"
		 + "FROM \r\n"
		 + "	APOIO.UF              UF , \r\n"
		 + "	APOIO.AREAREGISTRO    AREAREGISTRO , \r\n"
		 + "	CUSTOMER.UFOPERADORA  UFOPERADORA \r\n"
		 + "WHERE \r\n"
		 + "    AREAREGISTRO.CDAREAREGISTRO = ? \r\n"
		 + "AND \r\n"
		 + "    AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA \r\n"
		 + "AND \r\n" + "    UFOPERADORA.IDUF = UF.IDUF \r\n";
		 */

		String sql = "SELECT DISTINCT\r\n"
				+ "   UF.SGUF \r\n"
				+ "FROM \r\n"
				+ "	APOIO.UF              UF , \r\n"
				+ "	APOIO.AREAREGISTRO    AREAREGISTRO , \r\n"
				+ "	CUSTOMER.UFOPERADORA  UFOPERADORA \r\n"
				+ "WHERE \r\n"
				+ " AREAREGISTRO.IDUFOPERADORA = UFOPERADORA.IDUFOPERADORA \r\n"
				+ " AND UFOPERADORA.IDUF = UF.IDUF \r\n"
				+ " AND AREAREGISTRO.CDAREAREGISTRO IN ( ";
		for (int i = 0; i < cdAreaRegistro.size(); i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += cdAreaRegistro.get(i).toString();
		}
		sql += ") \r\n";

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			/* ps.setString(1, filtro.getNrDDD()); */

			ResultSet rs = ps.executeQuery();
			retorno = PontoContatoTOBuilder.buildPontoContatoUFTO(rs);
		} catch (Exception ex) {
			//throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return retorno;
	}

	public List buscarPontoContato(List sgUF, PontoContatoTO filtro)
			throws DAOException {

		List retorno = null;

		String sql = "SELECT \r\n" + "   IDTIPOEMPRESA \r\n" + "FROM \r\n"
				+ "   VOL.PONTOCONTATO \r\n" + "WHERE \r\n"
				+ "      CDCNPJEMPRESA = ? \r\n" + "   AND \r\n"
				+ "      SGUF IN ( ";

		for (int i = 0; i < sgUF.size(); i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += "'";
			sql += sgUF.get(i).toString();
			sql += "'";
		}
		sql += " ) \r\n";

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, filtro.getNrCnpj());

			ResultSet rs = ps.executeQuery();
			retorno = PontoContatoTOBuilder.buildPontoContatoTO(rs);
		} catch (Exception ex) {
			//throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return retorno;
	}

	/*
	 public List buscarGCNPontoContato(PontoContatoTO filtro, Integer first,
	 Integer total) throws DAOException {
	 */
	public List buscarGCNPontoContato(List sgUF, PontoContatoTO filtro,
			Integer first, Integer total) throws DAOException {

		List retorno = null;

		/*
		 String sql = "SELECT  IDTIPOEMPRESA ,\r\n"
		 + "   DSCONSULTORRELACIONAMENTO ,\r\n"
		 + "   NRTELEFONECONSULTOR , \r\n"
		 + "   DSEMAILCONSULTOR , \r\n" + "   NMTECNICORESIDENTE , \r\n"
		 + "   NRTELEFONETECNICO , \r\n" + "   DSEMAILTECNICO , \r\n"
		 + "   NMGERENTECONTAS , \r\n" + "   NRTELEFONEGERCONTAS , \r\n"
		 + "   DSEMAILGERCONTAS , \r\n" + "   DSGAM , \r\n"
		 + "   NRTELEFONEGAM , \r\n" + "   DSEMAILGAM , \r\n"
		 + "   DSGERENTESECAO , \r\n" + "   NRTELEFONEGERSECAO , \r\n"
		 + "   DSEMAILGERSECAO , \r\n" + "   DSGERENTEDIVISAO , \r\n"
		 + "   NRTELEFONEGERDIVISAO , \r\n" + "   SGUF , \r\n"
		 + "   DSEMAILGERDIVISAO \r\n" + "FROM \r\n" + "   ( \r\n"
		 + "   SELECT \r\n" + "      ROWNUM AS NLIN, \r\n"
		 + "      IDTIPOEMPRESA , DSCONSULTORRELACIONAMENTO , \r\n"
		 + "      NRTELEFONECONSULTOR , \r\n"
		 + "      DSEMAILCONSULTOR , \r\n"
		 + "      NMTECNICORESIDENTE , \r\n"
		 + "      NRTELEFONETECNICO , \r\n"
		 + "      DSEMAILTECNICO , \r\n"
		 + "      NMGERENTECONTAS , \r\n"
		 + "      NRTELEFONEGERCONTAS , \r\n"
		 + "      DSEMAILGERCONTAS , \r\n" + "      DSGAM , \r\n"
		 + "      NRTELEFONEGAM , \r\n" + "      DSEMAILGAM , \r\n"
		 + "      DSGERENTESECAO , \r\n"
		 + "      NRTELEFONEGERSECAO , \r\n"
		 + "      DSEMAILGERSECAO , \r\n"
		 + "      DSGERENTEDIVISAO , \r\n"
		 + "      NRTELEFONEGERDIVISAO , \r\n" + "      SGUF , \r\n"
		 + "      DSEMAILGERDIVISAO \r\n" + "   FROM \r\n"
		 + "      VOL.PONTOCONTATO \r\n" + "   WHERE \r\n"
		 + "      SUBSTR(CDCNPJEMPRESA, 1, 8) = ? \r\n" + "   AND \r\n"
		 + "      SGUF = ? \r\n" + "   ) \r\n" + "   WHERE \r\n"
		 + "      NLIN >= ? AND NLIN <= ? \r\n";
		 */

		String sql = "SELECT  IDTIPOEMPRESA ,\r\n"
				+ "   DSCONSULTORRELACIONAMENTO ,\r\n"
				+ "   NRTELEFONECONSULTOR , \r\n"
				+ "   DSEMAILCONSULTOR , \r\n" + "   NMTECNICORESIDENTE , \r\n"
				+ "   NRTELEFONETECNICO , \r\n" + "   DSEMAILTECNICO , \r\n"
				+ "   NMGERENTECONTAS , \r\n" + "   NRTELEFONEGERCONTAS , \r\n"
				+ "   DSEMAILGERCONTAS , \r\n" + "   DSGAM , \r\n"
				+ "   NRTELEFONEGAM , \r\n" + "   DSEMAILGAM , \r\n"
				+ "   DSGERENTESECAO , \r\n" + "   NRTELEFONEGERSECAO , \r\n"
				+ "   DSEMAILGERSECAO , \r\n" + "   DSGERENTEDIVISAO , \r\n"
				+ "   NRTELEFONEGERDIVISAO , \r\n" + "   SGUF , \r\n"
				+ "   DSEMAILGERDIVISAO \r\n" + "FROM \r\n" + "   ( \r\n"
				+ "   SELECT \r\n" + "      ROWNUM AS NLIN, \r\n"
				+ "      IDTIPOEMPRESA , DSCONSULTORRELACIONAMENTO , \r\n"
				+ "      NRTELEFONECONSULTOR , \r\n"
				+ "      DSEMAILCONSULTOR , \r\n"
				+ "      NMTECNICORESIDENTE , \r\n"
				+ "      NRTELEFONETECNICO , \r\n"
				+ "      DSEMAILTECNICO , \r\n"
				+ "      NMGERENTECONTAS , \r\n"
				+ "      NRTELEFONEGERCONTAS , \r\n"
				+ "      DSEMAILGERCONTAS , \r\n" + "      DSGAM , \r\n"
				+ "      NRTELEFONEGAM , \r\n" + "      DSEMAILGAM , \r\n"
				+ "      DSGERENTESECAO , \r\n"
				+ "      NRTELEFONEGERSECAO , \r\n"
				+ "      DSEMAILGERSECAO , \r\n"
				+ "      DSGERENTEDIVISAO , \r\n"
				+ "      NRTELEFONEGERDIVISAO , \r\n" + "      SGUF , \r\n"
				+ "      DSEMAILGERDIVISAO \r\n" + "   FROM \r\n"
				+ "      VOL.PONTOCONTATO \r\n" + "   WHERE \r\n"
				+ "      CDCNPJEMPRESA = ? \r\n" + "   AND \r\n"
				+ "      SGUF IN ( ";

		for (int i = 0; i < sgUF.size(); i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += "'";
			sql += sgUF.get(i).toString();
			sql += "'";
		}
		sql += " )) WHERE NLIN >= ? AND NLIN <= ? \r\n";

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, filtro.getNrCnpj());
			/* ps.setString(2, filtro.getsgUF()); */
			ps.setInt(2, first.intValue());
			ps.setInt(3, total.intValue());

			ResultSet rs = ps.executeQuery();
			retorno = PontoContatoTOBuilder.buildPontoContatoGCNTO(rs);
		} catch (Exception ex) {
			//throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return retorno;
	}

	/*
	 public List buscarPMEPontoContato(PontoContatoTO filtro, Integer first,
	 Integer total) throws DAOException {
	 */
	public List buscarPMEPontoContato(List sgUF, PontoContatoTO filtro,
			Integer first, Integer total) throws DAOException {

		List retorno = null;

		/*
		 String sql = "SELECT   IDTIPOEMPRESA , \r\n"
		 + "   DSRAZAOSOCIALDEALER , \r\n" + "   NRTELEF1 , \r\n"
		 + "   NRTELEF2 , \r\n" + "   DSEMAILDEALER , \r\n"
		 + "   DSCIDADEDEALER , \r\n" + "   NMGERENTECONTAS , \r\n"
		 + "   NRTELEFONEGERCONTAS , \r\n"
		 + "   DSEMAILGERCONTAS , \r\n" + "   SGUF  \r\n"
		 + "   FROM \r\n" + "( \r\n" + "   SELECT \r\n"
		 + "      ROWNUM AS NLIN , IDTIPOEMPRESA , \r\n"
		 + "      DSRAZAOSOCIALDEALER , \r\n" + "      NRTELEF1 , \r\n"
		 + "      NRTELEF2 , \r\n" + "      DSEMAILDEALER , \r\n"
		 + "      DSCIDADEDEALER , \r\n"
		 + "      NMGERENTECONTAS , \r\n"
		 + "      NRTELEFONEGERCONTAS , \r\n"
		 + "      DSEMAILGERCONTAS , \r\n" + "      SGUF  \r\n"
		 + "   FROM  \r\n" + "      VOL.PONTOCONTATO \r\n"
		 + "   WHERE \r\n"
		 + "      SUBSTR(CDCNPJEMPRESA, 1, 8) = ? \r\n" + "   AND \r\n"
		 + "      SGUF = ? \r\n" + ") \r\n" + "WHERE \r\n"
		 + "   NLIN >= ? AND NLIN <= ? \r\n";
		 */

		String sql = "SELECT   IDTIPOEMPRESA , \r\n"
				+ "   DSRAZAOSOCIALDEALER , \r\n" + "   NRTELEF1 , \r\n"
				+ "   NRTELEF2 , \r\n" + "   DSEMAILDEALER , \r\n"
				+ "   DSCIDADEDEALER , \r\n" + "   NMGERENTECONTAS , \r\n"
				+ "   NRTELEFONEGERCONTAS , \r\n"
				+ "   DSEMAILGERCONTAS , \r\n" + "   SGUF  \r\n"
				+ "   FROM \r\n" + "( \r\n" + "   SELECT \r\n"
				+ "      ROWNUM AS NLIN , IDTIPOEMPRESA , \r\n"
				+ "      DSRAZAOSOCIALDEALER , \r\n" + "      NRTELEF1 , \r\n"
				+ "      NRTELEF2 , \r\n" + "      DSEMAILDEALER , \r\n"
				+ "      DSCIDADEDEALER , \r\n"
				+ "      NMGERENTECONTAS , \r\n"
				+ "      NRTELEFONEGERCONTAS , \r\n"
				+ "      DSEMAILGERCONTAS , \r\n" + "      SGUF  \r\n"
				+ "   FROM  \r\n" + "      VOL.PONTOCONTATO \r\n"
				+ "   WHERE \r\n" + "      CDCNPJEMPRESA = ? \r\n"
				+ "   AND \r\n" + "      SGUF IN ( ";

		for (int i = 0; i < sgUF.size(); i++) {
			if (i > 0) {
				sql += ",";
			}
			sql += "'";
			sql += sgUF.get(i).toString();
			sql += "'";
		}
		sql += " )) WHERE NLIN >= ? AND NLIN <= ? \r\n";

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		// Query q = this.getQuery(hql, first, total);

		try {
			ps = conn.prepareStatement(sql);

			ps.setString(1, filtro.getNrCnpj());
			/* ps.setString(2, filtro.getsgUF()); */
			ps.setInt(2, first.intValue());
			ps.setInt(3, total.intValue());

			ResultSet rs = ps.executeQuery();
			retorno = PontoContatoTOBuilder.buildPontoContatoPMETO(rs);
		} catch (Exception ex) {
			//throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return retorno;
	}

}
