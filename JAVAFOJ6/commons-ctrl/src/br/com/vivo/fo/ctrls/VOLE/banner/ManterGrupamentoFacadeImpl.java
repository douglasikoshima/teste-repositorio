package br.com.vivo.fo.ctrls.VOLE.banner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang.StringUtils;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

import br.com.vivo.fo.db.DataBaseCall;
import br.com.vivo.fo.dbclasses.ApoioAreaBannerVOLE;
import br.com.vivo.fo.dbclasses.GrupamentoAcesso;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "ManterGrupamentoFacade", mappedName = "ManterGrupamentoFacade")
@Local(ManterGrupamentoFacade.class)
@Session(ejbName = "ManterGrupamentoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterGrupamentoFacadeImpl implements ManterGrupamentoFacade {
	
	@EJB
	private DataBaseCall db;
	
	private final static transient Logger log = new Logger("vol");

	@Override
	public void incluirGrupamento(GrupamentoAcesso grupamentoAcesso, String user)
			throws SQLException {
		log.debug("ManterGrupamentoFacadeImpl.incluirGrupamento");
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO VOLE.AGRUPAMENTOACESSO ");
		query.append("(IDAGRUPAMENTOACESSO, SGAGRUPAMENTOACESSO, NMAGRUPAMENTOACESSO,");
		query.append(" DSAGRUPAMENTOACESSO, DTULTIMAALTERACAO, IDUSUARIOALTERACAO)");
		query.append(" VALUES (");
		query.append(" VOLE.AGRUPAMENTOACESSOSQ.NEXTVAL,'");
		query.append(grupamentoAcesso.getSiglaGrupamento()).append("','");
		query.append(grupamentoAcesso.getNomeGrupamento()).append("','");
		query.append(grupamentoAcesso.getDescricaoGrupamento()).append("',sysdate, ");
		query.append(user).append(")");

		log.debug("ManterGrupamentoFacadeImpl::incluirGrupamento::query:="+query.toString());

		db.executeUpdate(query.toString());		
	}

	@Override
	public void alterarGrupamento(GrupamentoAcesso grupamentoAcesso, String user)
			throws SQLException {
		log.debug("ManterGrupamentoFacadeImpl.alterarGrupamento inicio");
		StringBuffer query = new StringBuffer();
		query.append("UPDATE VOLE.AGRUPAMENTOACESSO ");
		query.append(" SET ");
		query.append("	SGAGRUPAMENTOACESSO = '").append(grupamentoAcesso.getSiglaGrupamento()).append("', ");
		query.append("	NMAGRUPAMENTOACESSO = '").append(grupamentoAcesso.getNomeGrupamento()).append("', ");
		query.append("	DSAGRUPAMENTOACESSO = '").append(grupamentoAcesso.getDescricaoGrupamento()).append("', ");
		query.append("	DTULTIMAALTERACAO = sysdate").append(", ");
		query.append("	IDUSUARIOALTERACAO = ").append(user);
		query.append(" WHERE IDAGRUPAMENTOACESSO = ").append(grupamentoAcesso.getIdGrupamento());
		
		log.debug("query = " + query.toString());

		db.executeUpdate(query.toString());

	}

	@Override
	public void removerGrupamento(GrupamentoAcesso grupamentoAcesso)
			throws SQLException {
		log.debug("ManterGrupamentoFacadeImpl.removerGrupamento inicio");
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM VOLE.AGRUPAMENTOACESSO ");
		query.append(" WHERE IDAGRUPAMENTOACESSO = ").append(grupamentoAcesso.getIdGrupamento());
		
		log.debug("query = " + query.toString());

		db.executeUpdate(query.toString());
	}
	
	@Override
	public int existeSiglaGrupamento(String siglaGrupamento)
			throws SQLException {
		log.debug("ManterGrupamentoFacadeImpl.existeSiglaGrupamento inicio");
		int resultado = 0;
		StringBuffer query = new StringBuffer();
		query.append("SELECT COUNT(1) as total from VOLE.AGRUPAMENTOACESSO ");
		query.append(" WHERE SGAGRUPAMENTOACESSO = '").append(siglaGrupamento).append("'");
		
		log.debug("query = " + query.toString());

		ResultSet rs = db.executeQuery(query.toString());
		if(rs.next()) {
			resultado = rs.getInt("total");
		}
		
		return resultado;
	}

	@Override
	public GrupamentoAcesso[] listarGrupamento(GrupamentoAcesso grupamentoAcesso) throws SQLException {
		log.debug("ManterGrupamentoFacadeImpl.listarGrupamento");
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDAGRUPAMENTOACESSO, SGAGRUPAMENTOACESSO, NMAGRUPAMENTOACESSO, " +
				"DSAGRUPAMENTOACESSO FROM VOLE.AGRUPAMENTOACESSO ");
		query.append(" WHERE 1 = 1");
		if (StringUtils.isNotEmpty(grupamentoAcesso.getSiglaGrupamento())) {
			query.append(" AND UPPER(SGAGRUPAMENTOACESSO) like UPPER('" + grupamentoAcesso.getSiglaGrupamento().trim() + "%')");
		}
		if (StringUtils.isNotEmpty(grupamentoAcesso.getNomeGrupamento())) {
			query.append(" AND NMAGRUPAMENTOACESSO like '" + grupamentoAcesso.getNomeGrupamento().trim() + "%'");
		}
		if (StringUtils.isNotEmpty(grupamentoAcesso.getDescricaoGrupamento())) {
			query.append(" AND DSAGRUPAMENTOACESSO like '" + grupamentoAcesso.getDescricaoGrupamento().trim() + "%'");
		}
		query.append(" ORDER BY DTULTIMAALTERACAO");
		
		log.debug("query = " + query.toString());

		ArrayList<GrupamentoAcesso> list = new ArrayList<GrupamentoAcesso>();
		ResultSet rs = db.executeQuery(query.toString());

		while (rs.next()) {
			GrupamentoAcesso grupamentoAcessoRow = new GrupamentoAcesso();
			grupamentoAcessoRow.setIdGrupamento(rs.getString("IDAGRUPAMENTOACESSO"));
			grupamentoAcessoRow.setSiglaGrupamento(rs.getString("SGAGRUPAMENTOACESSO"));
			grupamentoAcessoRow.setNomeGrupamento(rs.getString("NMAGRUPAMENTOACESSO"));
			grupamentoAcessoRow.setDescricaoGrupamento(rs.getString("DSAGRUPAMENTOACESSO"));
			list.add(grupamentoAcessoRow);
		}
		rs.close();
		db.release();

		return list.toArray(new GrupamentoAcesso[0]);
	}

}
