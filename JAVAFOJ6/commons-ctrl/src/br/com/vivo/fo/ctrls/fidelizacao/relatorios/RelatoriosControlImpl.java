package br.com.vivo.fo.ctrls.fidelizacao.relatorios;

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
import br.com.vivo.fo.dbclasses.RetencaoStatusSAP;

@Stateless(name = "RelatoriosControl", mappedName = "RelatoriosControl")
@Local(RelatoriosControl.class)
@Session(ejbName = "RelatoriosControl", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatoriosControlImpl implements RelatoriosControl {

	@EJB
	private DataBaseCall database;

	public RetencaoStatusSAP[] getStatusSAPByNrLinha(String nrLinha) throws SQLException {
	    StringBuffer query = new StringBuffer();
	    query.append("SELECT U.NMLOGINUSUARIO, ");
	    query.append("       SP.VLIDREFERENCIA NRPEDIDO, ");
	    query.append("       SP.DSOBSERVACAO, ");
	    query.append("       SP.DTULTIMAALTERACAO, ");
	    query.append("       SP.VLLOGXMLIN XML ");
	    query.append("  FROM RETENCAO.STATUSSAP SP, ");
	    query.append("       ACESSO.USUARIO U ");
	    query.append(" WHERE SP.IDUSUARIOALTERACAO = U.IDPESSOAUSUARIO ");
	    query.append("   AND  VLLOGXMLIN LIKE '%").append(nrLinha).append("%' ");
	    query.append(" ORDER BY DTULTIMAALTERACAO DESC ");
	    
        ArrayList<RetencaoStatusSAP> lista = new ArrayList<RetencaoStatusSAP>();
        ResultSet rs;
        try {
            rs = database.executeQuery(query.toString());
            while (rs.next()) {
                RetencaoStatusSAP retencaoStatusSAP = new RetencaoStatusSAP();
                retencaoStatusSAP.setNmLoginUsuario(rs.getString("NMLOGINUSUARIO"));
                retencaoStatusSAP.setNrPedido(rs.getLong("NRPEDIDO"));
                retencaoStatusSAP.setDsObservacao(rs.getString("DSOBSERVACAO"));
                retencaoStatusSAP.setDtUltimaAlteracao(rs.getDate("DTULTIMAALTERACAO"));
                retencaoStatusSAP.setXml(rs.getString("XML"));
                lista.add(retencaoStatusSAP);
            }
            rs.close();
            database.release();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista.toArray(new RetencaoStatusSAP[0]);
	}

	public RetencaoStatusSAP[] getStatusSAPArray(String sqlStatement) throws SQLException {
        ArrayList<RetencaoStatusSAP> lista = new ArrayList<RetencaoStatusSAP>();
        ResultSet rs;
        try {
            rs = database.executeQuery(sqlStatement);
            while (rs.next()) {
                RetencaoStatusSAP retencaoStatusSAP = new RetencaoStatusSAP();
                retencaoStatusSAP.setDsObservacao(rs.getString("DSOBSERVACAO"));
                retencaoStatusSAP.setDtUltimaAlteracao(rs.getDate("DTULTIMAALTERACAO"));
                retencaoStatusSAP.setNmLoginUsuario(rs.getString("NMLOGINUSUARIO"));
                retencaoStatusSAP.setNrPedido(rs.getLong("NRPEDIDO"));
                retencaoStatusSAP.setXml(rs.getString("XML"));
                lista.add(retencaoStatusSAP);
            }
            rs.close();
            database.release();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista.toArray(new RetencaoStatusSAP[0]);
	}

}
