package br.com.vivo.fo.ctrls.admsistemas.crud.servicosdepara;

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
import br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "ServicosDeParaControl", mappedName = "ServicosDeParaControl")
@Local(ServicosDeParaControl.class)
@Session(ejbName = "ServicosDeParaControl", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ServicosDeParaControlImpl implements ServicosDeParaControl {

	@EJB
	private DataBaseCall database;

	public ServicosDePara getServicosDeParaByIdPlano(long idPlano) throws SQLException {
		ServicosDePara servicosDePara = new ServicosDePara();
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDPLANO, CDPLANO, DSPLANO, CDPLANOATLYS, INATIVO FROM LINHA.SERVICOSDEPARA WHERE IDPLANO = ").append(idPlano);
		ResultSet rs;
		try {
			rs = database.executeQuery(query.toString());
			if(rs.next()){
				servicosDePara.setIdPlano(rs.getLong("idPlano"));
				servicosDePara.setCdPlano(rs.getString("cdPlano"));
				servicosDePara.setDsPlano(rs.getString("dsPlano"));
				servicosDePara.setCdPlanoAtlys(rs.getString("cdPlanoAtlys"));
				servicosDePara.setInAtivo(rs.getInt("inAtivo"));
			}
			rs.close();
			database.release();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicosDePara;
	}

	public ServicosDePara[] getServicosDePara() throws SQLException {
		ArrayList<ServicosDePara> lista = new ArrayList<ServicosDePara>();
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDPLANO, CDPLANO, DSPLANO, CDPLANOATLYS, INATIVO FROM LINHA.SERVICOSDEPARA ORDER BY CDPLANO");
		ResultSet rs;
		try {
			rs = database.executeQuery(query.toString());
			while(rs.next()){
				ServicosDePara servicosDePara = new ServicosDePara();
				servicosDePara.setIdPlano(rs.getLong("idPlano"));
				servicosDePara.setCdPlano(rs.getString("cdPlano"));
				servicosDePara.setDsPlano(rs.getString("dsPlano"));
				servicosDePara.setCdPlanoAtlys(rs.getString("cdPlanoAtlys"));
				servicosDePara.setInAtivo(rs.getInt("inAtivo"));
				lista.add(servicosDePara);
			}
			rs.close();
			database.release();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista.toArray(new ServicosDePara[0]);
	}

	public ServicosDePara getServicosDeParaByCdPlano(String cdPlano) throws SQLException {
		ServicosDePara servicosDePara = new ServicosDePara();
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDPLANO, CDPLANO, DSPLANO, CDPLANOATLYS, INATIVO FROM LINHA.SERVICOSDEPARA WHERE CDPLANO = ").append(cdPlano);
		ResultSet rs;
		try {
			rs = database.executeQuery(query.toString());
			if(rs.next()){
				servicosDePara.setIdPlano(rs.getLong("idPlano"));
				servicosDePara.setCdPlano(rs.getString("cdPlano"));
				servicosDePara.setDsPlano(rs.getString("dsPlano"));
				servicosDePara.setCdPlanoAtlys(rs.getString("cdPlanoAtlys"));
				servicosDePara.setInAtivo(rs.getInt("inAtivo"));
			}
			rs.close();
			database.release();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicosDePara;
	}

	public void addServicosDePara(String cdPlano, String dsPlano, String cdPlanoAtlys, int inAtivo) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO LINHA.SERVICOSDEPARA (IDPLANO,CDPLANO,DSPLANO,CDPLANOATLYS,INATIVO) VALUES (LINHA.SERVICOSDEPARASQ.NEXTVAL, '");
		query.append(cdPlano).append("','").append(dsPlano).append("','").append(cdPlanoAtlys).append("',").append(inAtivo).append(")");
		try {
			database.executeUpdate(query.toString());
			database.release();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateServicosDePara(long idPlano, String cdPlano, String dsPlano, String cdPlanoAtlys, int inAtivo) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("UPDATE LINHA.SERVICOSDEPARA ");
		query.append("SET CDPLANO = '").append(cdPlano).append("',");
		query.append("DSPLANO = '").append(dsPlano).append("',");
		query.append("CDPLANOATLYS = '").append(cdPlanoAtlys).append("',");
		query.append("INATIVO = ").append(inAtivo).append(" ");
		query.append(" WHERE IDPLANO = ").append(idPlano);
		try {
			database.executeUpdate(query.toString());
			database.release();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteServicosDePara(long idPlano) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("DELETE FROM LINHA.SERVICOSDEPARA WHERE IDPLANO = ").append(idPlano);
		try {
			database.executeUpdate(query.toString());
			database.release();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
