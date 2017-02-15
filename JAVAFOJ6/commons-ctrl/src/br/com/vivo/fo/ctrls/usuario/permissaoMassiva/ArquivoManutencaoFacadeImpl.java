package br.com.vivo.fo.ctrls.usuario.permissaoMassiva;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.db.DataBaseCall;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "ArquivoManutencaoFacade", mappedName = "ArquivoManutencaoFacade")
@Local(ArquivoManutencaoFacade.class)
@Session(ejbName = "ArquivoManutencaoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArquivoManutencaoFacadeImpl implements ArquivoManutencaoFacade{

	@EJB
	private DataBaseCall database;
	private static Logger log = new Logger("usuario");
	
    @Override
	public Integer initTableManut(String fileName, String login, Integer funcionalidade){
		
		log.debug("[ArquivoManutencaoFacadeImpl.initTableManut] begin");
		
		int sequence = -1;
		String sql = " SELECT ACESSO.MANUTENCAOLOGINACESSOSQ.NEXTVAL FROM DUAL ";
		ResultSet rs = null;
		Statement st = null;
		
		log.debug("[ArquivoManutencaoFacadeImpl.initTableManut] SQL1 = " + sql);
		try {
			st = database.getConnection().createStatement();
			rs = st.executeQuery(sql);
			
            if (rs.next()) {
				sequence = rs.getInt(1);
            }
		

		} catch (SQLException e) {
			e.printStackTrace();
			 log.error("[ArquivoManutencaoFacadeImpl.initTableManut] Exception", e);
			sequence = -1;
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
			
		
        sql = " INSERT INTO ACESSO.MANUTENCAOLOGINACESSO "
                + " ("
                + "	IDMANUTENCAOLA, "
                + "	DATA_ENVIO, "
                + "	NOME_ARQUIVO, "
                + "	LOGIN, "
                + "	STATUS_CARGA, "
                + "	FUNCIONALIDADE"
                + " ) "
                + " VALUES "
                + " ("
                + " 	" + String.valueOf(sequence) + ", "
                + " 	SYSDATE, "
                + "   '" + fileName.toUpperCase() + "', "
                + " 	'" + login.toUpperCase() + "', "
                + " 	1, "
                + " 	" + String.valueOf(funcionalidade) + " "
                + " ) ";
		
		log.debug("[ArquivoManutencaoFacadeImpl.initTableManut] SQL2 = " + sql);
		try{
			executeCommand(sql);

		}catch (Exception e){
			log.error("[ArquivoManutencaoFacadeImpl.initTableManut] Erro", e);
			sequence = -1;
		}
		
		log.debug("[ArquivoManutencaoFacadeImpl.initTableManut] end");
		return sequence;
	}
	
    @Override
	public ArquivoProcessamentoVO selectTableByFunc(String sgFuncionalidade){
		
		log.debug("[ArquivoManutencaoFacadeImpl.selectTableByFunc] begin");
		
		ArquivoProcessamentoVO vo = ArquivoProcessamentoVO.Factory.newInstance();
		int funcionalidade = 0;
		StringBuffer query = new StringBuffer();
		ResultSet rs = null;
		Statement st = null;
		try{
			
			if (sgFuncionalidade != null && !ConstantesCRM.SVAZIO.equals(sgFuncionalidade)) {
				
                if ("MANUTL".equals(sgFuncionalidade)) {
                    funcionalidade = 1;
                }
                if ("MANUTA".equals(sgFuncionalidade)) {
                    funcionalidade = 2;
                }
                if ("MANUTC".equals(sgFuncionalidade)) {
                    funcionalidade = 3;
                }
				
				query.append(" SELECT  TO_CHAR (MLA.DATA_ENVIO, 'DD/MM/YYYY HH24:MI:SS') AS DTENVIO, ");
				query.append("         MLA.NOME_ARQUIVO        NMARQUIVO, ");
				query.append("         MLA.LOGIN               LOGIN, ");
				query.append("         TO_CHAR (MLA.DATA_PROC, 'DD/MM/YYYY HH24:MI:SS') AS DTPROC, ");
				query.append("         SLA.DSSTATUSLOGINACESSO STCARGA, ");
				query.append("         MLA.QTDE_REGISTROS_ERRO QTREGERR, ");
				query.append("         MLA.QTDE_REGISTROS      QTREG, ");
				query.append("         MLA.ARQUIVO_ERR         ARQUIVOERR ");
				query.append(" FROM    ACESSO.MANUTENCAOLOGINACESSO MLA, ");
				query.append("         APOIO.STATUSLOGINACESSO SLA ");
				query.append(" WHERE   SLA.IDSTATUSLOGINACESSO = MLA.STATUS_CARGA ");
                query.append(" AND     MLA.FUNCIONALIDADE      = ").append(String.valueOf(funcionalidade));
                query.append(" ORDER BY MLA.DATA_ENVIO ");
				
				log.debug("[ArquivoManutencaoFacadeImpl.selectTableByFunc] SQL = " + query.toString());
				
				
				st = database.getConnection().createStatement();
				rs = st.executeQuery(query.toString());
				
				
				vo.setSgFuncionalidade(sgFuncionalidade);
				while(rs.next()){
					
					ArquivoProcessamentoVO.Item item = vo.addNewItem();
					
					item.setDtEnvio(rs.getString("DTENVIO"));
					
					item.setNmFileOriginal(rs.getString("NMARQUIVO"));
					
					item.setDsLoginUsuario(rs.getString("LOGIN"));
					
					item.setDtProcessamento(rs.getString("DTPROC"));
					
					item.setDsStatusCarga(rs.getString("STCARGA"));
					
					item.setQtRegRejeitados(rs.getString("QTREGERR"));
					
					item.setQtRegTotal(rs.getString("QTREG"));
					
					String fileErr = rs.getString("ARQUIVOERR");
					if(!"".equals(fileErr) && fileErr!=null){
						fileErr = fileErr.replaceAll("<", "");
						fileErr = fileErr.replaceAll(">", "");
					}
					item.setNmFileBad(fileErr);
					
					//log.debug("[ArquivoManutencaoFacadeImpl.selectTableByFunc] Preenchido ArquivoProcessamentoVO");
				}
				query = null;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("[ArquivoManutencaoFacadeImpl.selectTableByFunc] Erro", e);
			vo = null;
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
			
		log.debug("[ArquivoManutencaoFacadeImpl.selectTableByFunc] end");
		return vo;
	}
	
    @Override
	public Boolean eraseTableManut(Integer sq){
		
		log.debug("[ArquivoManutencaoFacadeImpl.eraseTableManut] begin");
		
		String sql = "DELETE FROM ACESSO.MANUTENCAOLOGINACESSO WHERE IDMANUTENCAOLA = " + String.valueOf(sq);
		
		log.debug("[ArquivoManutencaoFacadeImpl.eraseTableManut] SQL = " + sql);
		try {
			
			executeCommand(sql);
		} catch (Exception e) {
			log.debug("[ArquivoManutencaoFacadeImpl.eraseTableManut] Erro", e);
			return false;
		}
		
		log.debug("[ArquivoManutencaoFacadeImpl.eraseTableManut] end");
		return true;
	}
    
    @Override
    public String buscarParametroPath(String cdParametro) {
        StringBuilder query = new StringBuilder();
        String resultPATH = "";
        ResultSet rs = null;
		Statement st = null;
		
        log.debug("[ArquivoManutencaoFacadeImpl.buscarParametroPath] begin");

        query.append("SELECT DSVALORPARAMETRO dsValorParametro FROM APOIO.PARAMETRO WHERE CDPARAMETRO = '").append(cdParametro).append("'");

        log.debug("[ArquivoManutencaoFacadeImpl.buscarParametroPath] SQL = " + query.toString());
        try {

        	st = database.getConnection().createStatement();
			rs = st.executeQuery(query.toString());
            while (rs.next()) {
                resultPATH = rs.getString("dsValorParametro");
            }
            log.debug("[ArquivoManutencaoFacadeImpl.buscarParametroPath] end");

            return resultPATH;
        } catch (SQLException e) {
			e.printStackTrace();
			log.error("Erro SQL: " + e.getMessage());
            log.debug("[ArquivoManutencaoFacadeImpl.buscarParametroPath] Erro", e);
            return resultPATH;
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
}
