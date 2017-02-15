package br.com.vivo.fo.ctrls.VOLTAV.fatura.db;

import java.sql.Date;
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
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "ContaDB", mappedName = "ContaDB")
@Local(ContaDB.class)
@Session(ejbName = "ContaDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ContaDBImpl implements ContaDB {

	@EJB
	private DataBaseCall database;

	@Override
	public TarefaCliente consultaContaEmail(Integer idPlataforma, String nrTelefone, String flStatus) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("SELECT ID, ");
        query.append("       NR_TELEFONE, ");
        query.append("       FL_STATUS, ");
        query.append("       DIA_ENVIO, ");
        query.append("       NM_EMAIL, ");
        query.append("       ID_OPERADORA, ");
        query.append("       NR_CONTRATO ");
        query.append("  FROM VOLE.TS_TAREFA_CLIENTE ");
        query.append(" WHERE ID_PLATAFORMA = ").append(idPlataforma).append(" ");
        query.append("   AND NR_TELEFONE = '").append(nrTelefone).append("' ");
        query.append("   AND FL_STATUS = '").append(flStatus).append("' ");
        query.append("   AND ROWNUM = 1 ");

		TarefaCliente tarefaCliente = new TarefaCliente();
		ResultSet rs = database.executeQuery(query.toString());

        if(rs.next()){
			tarefaCliente.setId(rs.getInt("id"));
			tarefaCliente.setNr_telefone(rs.getString("nr_telefone"));
			tarefaCliente.setFl_status(rs.getString("fl_status"));
			tarefaCliente.setDia_envio(rs.getInt("dia_envio"));
			tarefaCliente.setNm_email(rs.getString("nm_email"));
			tarefaCliente.setId_operadora(rs.getInt("id_operadora"));
            tarefaCliente.setNr_contrato(rs.getString("nr_contrato"));
		}
		rs.close();
		database.release();

		return tarefaCliente;
	}

	@Override
	public void insertTarefaCliente(String nrTelefone, Integer idPlataforma, String flStatus, String diaEnvio, String nrContrato, String nmEmail, Integer idOperadora) throws SQLException {

		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO VOLE.TS_TAREFA_CLIENTE ");
        query.append("(ID, NR_TELEFONE, ID_PLATAFORMA, FL_STATUS, DIA_ENVIO, NR_CONTRATO, NM_EMAIL, ID_OPERADORA, DT_ULTIMAEXECUCAO) ");
        query.append("VALUES (SQ_TS_TAREFA_CLIENTE.NEXTVAL, '").append(nrTelefone).append("',").append(idPlataforma).append(",'").append(flStatus).append("',").append(diaEnvio).append(",");
        query.append("'").append(nrContrato).append("','").append(nmEmail).append("',").append(idOperadora).append(", SYSDATE)");

		database.executeUpdate(query.toString());

	}

	@Override
	public void updateTarefaCliente(String nrTelefone, Integer idPlataforma, String flStatus, String diaEnvio, String nrContrato, String nmEmail, Integer idOperadora) throws SQLException {

		StringBuffer query = new StringBuffer();
        query.append("UPDATE VOLE.TS_TAREFA_CLIENTE ");
        query.append("   SET NR_TELEFONE = '").append(nrTelefone).append("', ");
        query.append("       ID_PLATAFORMA = ").append(idPlataforma).append(" , ");
        query.append("       FL_STATUS = '").append(flStatus).append("', ");
        query.append("       DIA_ENVIO = ").append(diaEnvio).append(", ");
        query.append("       NR_CONTRATO = '").append(nrContrato).append("', ");
        query.append("       NM_EMAIL = '").append(nmEmail).append("', ");
        query.append("       ID_OPERADORA = ").append(idOperadora).append(", ");
        query.append("       DT_ULTIMAEXECUCAO = sysdate ");
        query.append(" WHERE FL_STATUS IN ('A','D')");
        query.append("   AND NR_TELEFONE = '").append(nrTelefone).append("' ");
		
		database.executeUpdate(query.toString());
	}

	@Override
	public void desativaEnvioEmail(String nrTelefone, Integer idPlataforma, String flStatus) throws SQLException {
	     
		StringBuffer query = new StringBuffer();
        query.append("UPDATE VOLE.TS_TAREFA_CLIENTE ");
        query.append("   SET FL_STATUS = '").append(flStatus).append("' ");
        query.append(" WHERE ID_PLATAFORMA = ").append(idPlataforma);
        query.append("   AND NR_TELEFONE = '").append(nrTelefone).append("' ");
        query.append("   AND FL_STATUS IN ('A','D') ");

		database.executeUpdate(query.toString());

	}

	@Override
	public void atualizaEmail(String nrTelefone, Integer idPlataforma, String dsEmail) throws SQLException {
				
		StringBuffer query = new StringBuffer();
        query.append("UPDATE VOLE.TS_TAREFA_CLIENTE ");
        query.append("   SET NM_EMAIL = '").append(dsEmail).append("', ");
        query.append(" WHERE ID_PLATAFORMA = ").append(idPlataforma).append(", ");
        query.append("   AND NR_TELEFONE = '").append(nrTelefone).append("', ");
        query.append("   AND FL_STATUS IN ('A','D')");

		database.executeUpdate(query.toString());
		
	}

	@Override
	public TarefaCliente[] consultaHistoricoEmail(String nrTelefone) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT CLIENTE.ID_ROW ID, ");
        query.append("       TO_CHAR (CLIENTE.dt_hist, 'DD/MM/YYYY HH24:MI:SS') DATA, ");
        query.append("       ST.SITUACAO, ");
        query.append("       ST.DESCRICAO, ");
        query.append("       ST.ID STATUSID ");
        query.append("  FROM (SELECT ROWNUM 'ID_ROW', hc.* ");
        query.append("          FROM TS_HISTORICO_CLIENTE HC, ");
        query.append("               VOLE.TS_TAREFA_CLIENTE TC ");
        query.append("         WHERE TS_TAREFA_CLIENTE_ID = TC.ID ");
        query.append("           AND TC.NR_TELEFONE = '").append(nrTelefone).append("', ");
        query.append("           AND HC.TS_STATUS_ID NOT IN (10, 11, 12, 13) ");
        query.append("       ) CLIENTE, ");
        query.append("       TS_STATUS ST ");
        query.append(" WHERE CLIENTE.ID_ROW BETWEEN 1 AND 10 ");
        query.append("   AND TS_STATUS_ID = ST.ID ");
        query.append(" ORDER BY CLIENTE.DT_HIST DESC ");
						
		ArrayList<TarefaCliente> list = new ArrayList<TarefaCliente>();
		ResultSet rs = database.executeQuery(query.toString());
		
		while (rs.next()) {
			TarefaCliente tarefaCliente = new TarefaCliente();
			tarefaCliente.setId(rs.getInt("id"));
			tarefaCliente.setNr_telefone(rs.getString("nr_telefone"));
			tarefaCliente.setId_plataforma(rs.getInt("id_plataforma"));
			tarefaCliente.setFl_status(rs.getString("fl_status"));
			tarefaCliente.setDia_envio(rs.getInt("dia_envio"));
			tarefaCliente.setNr_contrato(rs.getString("nr_contrato"));
			tarefaCliente.setNm_email(rs.getString("nm_email"));
			tarefaCliente.setId_operadora(rs.getInt("id_operadora"));
			tarefaCliente.setDt_ultimaexecucao(rs.getString("dt_ultimaexecucao"));
			tarefaCliente.setId_canal(rs.getInt("id_canal"));
			tarefaCliente.setData(rs.getString("data"));
			tarefaCliente.setSituacao(rs.getString("situacao"));
			tarefaCliente.setDescricao(rs.getString("descricao"));
			tarefaCliente.setStatusId(rs.getInt("statusID"));
			tarefaCliente.setSbscrp_id(rs.getInt("sbscrp_id"));
			list.add(tarefaCliente);
		}

		rs.close();
		database.release();
		
		return list.toArray(new TarefaCliente[0]);
	}

	@Override
	public TarefaCliente getStatusIDByDescricao(String descricao) throws SQLException {
	
		StringBuffer query = new StringBuffer();
        query.append("SELECT ID FROM TS_STATUS WHERE DESCRICAO = '").append(descricao).append("' ");
		
		ResultSet rs = database.executeQuery(query.toString());
		TarefaCliente tarefaCliente = new TarefaCliente();
		
		if (rs.next()) {
			tarefaCliente.setId(rs.getInt("id"));
			}
		
		rs.close();
		database.release();
				
		return tarefaCliente;
	}

	@Override
	public TarefaCliente getTarefaClienteByEmail(String dsEmail, Integer idPlataforma) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT ID, ");
        query.append("       NR_TELEFONE, ");
        query.append("       FL_STATUS, ");
        query.append("       DIA_ENVIO ");
        query.append("  FROM VOLE.TS_TAREFA_CLIENTE ");
        query.append(" WHERE NM_EMAIL = '").append(dsEmail).append("' ");
        query.append("   AND ID_PLATAFORMA = ").append(idPlataforma);
        query.append("   AND FL_STATUS = 'A' ");
		
		ResultSet rs = database.executeQuery(query.toString());
		TarefaCliente tarefaCliente = new TarefaCliente();
		
        if (rs.next()) {
			tarefaCliente.setId(rs.getInt("id"));
			tarefaCliente.setNr_telefone(rs.getString("nr_telefone"));
			tarefaCliente.setFl_status(rs.getString("fl_status"));
			tarefaCliente.setDia_envio(rs.getInt("dia_envio"));
		}
		rs.close();
		database.release();

		return tarefaCliente;
	}

	@Override
	public void insertHistoricoCliente(Integer tarefaClienteId, Integer statusId) throws SQLException {
		
		StringBuffer query = new StringBuffer();
        query.append("INSERT INTO TS_HISTORICO_CLIENTE ");
        query.append(" VALUES (SQ_TS_HISTORICO_CLIENTE.NEXTVAL, ").append(tarefaClienteId).append(",").append(statusId).append(", SYSDATE)");
		
		database.executeUpdate(query.toString());

	}

	@Override
	public void insertHistoricoClienteFull(String nrTelefone, Integer idPlataforma, String dsEmail, String dsOperacao) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ts_historico_cliente ");
		query.append("VALUES (sq_ts_historico_cliente.NEXTVAL, ");
        query.append(" (SELECT DECODE ((SELECT ROWNUM ");
        query.append("    FROM VOLE.TS_TAREFA_CLIENTE ");
        query.append("   WHERE nm_email = '").append(dsEmail).append("' ");
        query.append("     AND id_plataforma = ").append(idPlataforma);
        query.append("     AND fl_status = 'A' AND ROWNUM = 1), ");
		query.append("1, (SELECT ID ");
        query.append("      FROM VOLE.TS_TAREFA_CLIENTE ");
        query.append("     WHERE nm_email = ").append(dsEmail).append(" , ");
        query.append("       AND id_plataforma = ").append(idPlataforma).append(" , ");
        query.append("       AND fl_status = 'A' AND ROWNUM = 1), ");
        query.append("   (SELECT ID ");
        query.append("      FROM VOLE.TS_TAREFA_CLIENTE ");
        query.append("     WHERE id_plataforma = ").append(idPlataforma).append(" , ");
        query.append("       AND nr_telefone = ").append(nrTelefone).append(" , ");
        query.append("       AND fl_status = 'A' AND ROWNUM = 1) ");
        query.append("     ) DEC ");
        query.append("  FROM VOLE.TS_TAREFA_CLIENTE ");
        query.append(" WHERE ROWNUM = 1), ");
        query.append("  (SELECT ID ");
        query.append("     FROM ts_status ");
        query.append("    WHERE descricao = '").append(dsOperacao).append("') , SYSDATE) ");
		
		database.executeUpdate(query.toString());

	}

	@Override
	public TarefaCliente consultaNumeroContrato(String nrTelefone) throws SQLException {
		StringBuffer query = new StringBuffer();
        query.append("SELECT SBSCRP_ID FROM TMP_CLIENTE WHERE ACCESS_NBR = ").append(nrTelefone);
		
		ResultSet rs = database.executeQuery(query.toString());
		TarefaCliente tarefaCliente = new TarefaCliente();
		
		if (rs.next()) {
			tarefaCliente.setSbscrp_id(rs.getInt("sbscrp_id"));
		}
		rs.close();
		database.release();
		
		return tarefaCliente;
	}

	@Override
	public void updateFaturaEmailPJ(String nrConta, String flStatus) throws SQLException {
		StringBuffer query = new StringBuffer();
        query.append("UPDATE VOLE.TS_TAREFA_CLIENTE_PJ ");
        query.append("   SET FL_STATUS = '").append(flStatus).append("', ");
        query.append(" WHERE FL_STATUS IN ('A','D') ");
        query.append("   AND NR_CONTRATO = '").append(nrConta).append("' ");
		
		database.executeUpdate(query.toString());

	}

	@Override
	public void insertTarefaClientePJ(String nrConta, String flStatus, String dsEmail, Date dtCadastro) throws SQLException {
		StringBuffer query = new StringBuffer();
        query.append("INSERT INTO VOLE.TS_TAREFA_CLIENTE_PJ (ID, NR_CONTRATO, FL_STATUS, NM_EMAIL, DT_CADASTRO) ");
        query.append("VALUES (SQ_TS_TAREFA_CLIENTE_PJ.NEXTVAL,'").append(nrConta).append("','").append(flStatus).append("','").append(dsEmail).append("', to_date('").append(dtCadastro).append("','dd/mm/yyyy hh24:mi:ss')");
		
		database.executeUpdate(query.toString());
												
	}

}
