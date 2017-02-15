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

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.db.DataBaseCall;
import br.com.vivo.fo.dbclasses.AcessoBannerVOLE;
import br.com.vivo.fo.dbclasses.ApoioAreaBannerVOLE;
import br.com.vivo.fo.dbclasses.ApoioTipoBannerVOLE;
import br.com.vivo.fo.dbclasses.RelacionamentoBannerVOLE;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "BannerDB", mappedName = "BannerDB")
@Local(BannerDB.class)
@Session(ejbName = "BannerDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BannerDBImpl implements BannerDB {

	@EJB
	private DataBaseCall db;

	private final static transient Logger log = new Logger("vol");

	@Override
	public ApoioAreaBannerVOLE[] getListaAreasBanner() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM APOIO.AREABANNERVOLE ORDER BY DSAREABANNERVOLE");

		ArrayList<ApoioAreaBannerVOLE> list = new ArrayList<ApoioAreaBannerVOLE>();
		ResultSet rs = db.executeQuery(query.toString());

		while (rs.next()) {
			ApoioAreaBannerVOLE apoioAreaBannerVOLE = new ApoioAreaBannerVOLE();
			apoioAreaBannerVOLE.setIdAreaBannerVOLE(rs.getLong("idAreaBannerVOLE"));
			apoioAreaBannerVOLE.setDsAreaBannerVOLE(rs.getString("dsAreaBannerVOLE"));
			list.add(apoioAreaBannerVOLE);
		}
		rs.close();
		db.release();

		return list.toArray(new ApoioAreaBannerVOLE[0]);
	}

	@Override
	public ApoioTipoBannerVOLE[] getListaTiposBanner() throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM APOIO.TIPOBANNERVOLE");

		ArrayList<ApoioTipoBannerVOLE> list = new ArrayList<ApoioTipoBannerVOLE>();
		ResultSet rs = db.executeQuery(query.toString());

		while (rs.next()) {
			ApoioTipoBannerVOLE apoioTipoBannerVOLE = new ApoioTipoBannerVOLE();
			apoioTipoBannerVOLE.setIdTipoBannerVOLE(rs.getLong("idTipoBannerVOLE"));
			apoioTipoBannerVOLE.setDsTipoBannerVOLE(rs.getString("dsTipoBannerVOLE"));
			list.add(apoioTipoBannerVOLE);
		}
		rs.close();
		db.release();

		return list.toArray(new ApoioTipoBannerVOLE[0]);
	}

	@Override
	public AcessoBannerVOLE[] getListaBanners(String query) throws Exception {
		ArrayList<AcessoBannerVOLE> list = new ArrayList<AcessoBannerVOLE>();
		ResultSet rs = db.executeQuery(query);

		while (rs.next()) {
			AcessoBannerVOLE acessoBannerVOLE = new AcessoBannerVOLE();
			acessoBannerVOLE.setIdBannerVOLE(rs.getLong("idBannerVOLE"));
			acessoBannerVOLE.setRNum(rs.getLong("rNum"));
			acessoBannerVOLE.setNmBannerVOLE(rs.getString("nmBannerVOLE"));
			acessoBannerVOLE.setUrlBannerVOLE(rs.getString("urlBannerVOLE"));
			acessoBannerVOLE.setIdAreaBannerVOLE(rs.getLong("idAreaBannerVOLE"));
			acessoBannerVOLE.setIdTipoBannerVOLE(rs.getLong("idTipoBannerVOLE"));
			acessoBannerVOLE.setDsBannerVOLE(rs.getString("dsBannerVOLE"));
			acessoBannerVOLE.setIPBannerVOLE(rs.getString("IPBannerVOLE"));
			acessoBannerVOLE.setDtInicial(rs.getDate("dtInicial"));
			acessoBannerVOLE.setDtFinal(rs.getDate("dtFinal"));
			acessoBannerVOLE.setNrContador(rs.getLong("nrContador"));
			acessoBannerVOLE.setInContador(rs.getInt("inContador"));
			list.add(acessoBannerVOLE);
		}
		rs.close();
		db.release();

		return list.toArray(new AcessoBannerVOLE[0]);
	}

	@Override
	public RelacionamentoBannerVOLE[] getListaRelacionamentos(String query) throws Exception {
		ArrayList<RelacionamentoBannerVOLE> list = new ArrayList<RelacionamentoBannerVOLE>();
		ResultSet rs = db.executeQuery(query);

		while (rs.next()) {
			RelacionamentoBannerVOLE relacionamentoBannerVOLE = new RelacionamentoBannerVOLE();
			relacionamentoBannerVOLE.setIdRelacionamentoBannerVOLE(rs.getLong("idRelacionamentoBannerVOLE"));
			relacionamentoBannerVOLE.setSgUF(rs.getString("sgUF"));
			relacionamentoBannerVOLE.setIdBannerVOLE(rs.getLong("idBannerVOLE"));
			relacionamentoBannerVOLE.setNmBannerVOLE(rs.getString("nmBannerVOLE"));
			relacionamentoBannerVOLE.setDsBannerVOLE(rs.getString("dsBannerVOLE"));
			relacionamentoBannerVOLE.setUrlBannerVOLE(rs.getString("urlBannerVOLE"));
			relacionamentoBannerVOLE.setDsAreaBannerVOLE(rs.getString("dsAreaBannerVOLE"));
			relacionamentoBannerVOLE.setRNum(rs.getLong("rNum"));
			list.add(relacionamentoBannerVOLE);
		}
		rs.close();
		db.release();

		return list.toArray(new RelacionamentoBannerVOLE[0]);
	}

	@Override
	public int countListaBanner(String query) throws Exception {
		int retorno = 0;
		ResultSet rs = db.executeQuery(query);
		if (rs.next()) {
			retorno = rs.getInt(1);
		}
		return retorno;
	}

	@Override
	public AcessoBannerVOLE getBannerVOLE(long idBannerVOLE) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("SELECT IDBANNERVOLE, ROWNUM as rNum, NMBANNERVOLE, URLBANNERVOLE, IDAREABANNERVOLE, IDTIPOBANNERVOLE, ");
		query.append("DSBANNERVOLE, IPBANNERVOLE, DTINICIAL, DTFINAL, INCONTADOR, NRCONTADOR ");
		query.append("FROM ACESSO.BANNERVOLE ");
		query.append("WHERE IDBANNERVOLE = ").append(idBannerVOLE);

		AcessoBannerVOLE acessoBannerVOLE = new AcessoBannerVOLE();
		ResultSet rs = db.executeQuery(query.toString());

		if (rs.next()) {
			acessoBannerVOLE.setIdBannerVOLE(rs.getLong("idBannerVOLE"));
			acessoBannerVOLE.setRNum(rs.getLong("rNum"));
			acessoBannerVOLE.setNmBannerVOLE(rs.getString("nmBannerVOLE"));
			acessoBannerVOLE.setUrlBannerVOLE(rs.getString("urlBannerVOLE"));
			acessoBannerVOLE.setIdAreaBannerVOLE(rs.getLong("idAreaBannerVOLE"));
			acessoBannerVOLE.setIdTipoBannerVOLE(rs.getLong("idTipoBannerVOLE"));
			acessoBannerVOLE.setDsBannerVOLE(rs.getString("dsBannerVOLE"));
			acessoBannerVOLE.setIPBannerVOLE(rs.getString("IPBannerVOLE"));
			acessoBannerVOLE.setDtInicial(rs.getDate("dtInicial"));
			acessoBannerVOLE.setDtFinal(rs.getDate("dtFinal"));
			acessoBannerVOLE.setNrContador(rs.getLong("nrContador"));
			acessoBannerVOLE.setInContador(rs.getInt("inContador"));
		}
		rs.close();
		db.release();

		return acessoBannerVOLE;
	}

	@Override
	public void insertBannerVOLE(String nmBannerVOLE, String urlBannerVOLE, long idAreaBannerVOLE, long idTipoBannerVOLE, String dsBannerVOLE, String IPBannerVOLE, String dtInicial, String dtFinal, int inContador) throws SQLException {
		StringBuffer query = new StringBuffer();
		query.append("INSERT INTO ACESSO.BANNERVOLE ");
		query.append("(IDBANNERVOLE,NMBANNERVOLE,URLBANNERVOLE,IDAREABANNERVOLE,IDTIPOBANNERVOLE,DSBANNERVOLE,");
		query.append("IPBANNERVOLE,DTINICIAL,DTFINAL,INCONTADOR,NRCONTADOR) VALUES (");
		query.append("ACESSO.BANNERVOLESQ.NEXTVAL,'");
		query.append(nmBannerVOLE).append("','");
		query.append(urlBannerVOLE).append("', ");
		query.append(idAreaBannerVOLE).append(", ");
		query.append(idTipoBannerVOLE).append(",'");
		query.append(dsBannerVOLE).append("','");
		query.append(IPBannerVOLE).append("', ");
		query.append("TO_DATE('").append(dtInicial).append("', 'DD/MM/YYYY'), ");
		query.append("TO_DATE('").append(dtFinal).append("', 'DD/MM/YYYY'), ");
		query.append(inContador).append(", 0)");

		log.debug("BannerDBImpl::insertBannerVOLE::query:="+query.toString());

		db.executeUpdate(query.toString());
	}

	@Override
	public void updateBannerVOLE(long idBannerVOLE, String nmBannerVOLE, String urlBannerVOLE, long idAreaBannerVOLE, long idTipoBannerVOLE, String dsBannerVOLE, String IPBannerVOLE, String dtInicial, String dtFinal, int inContador) throws SQLException {
		
		log.debug("BannerDBImpl.updateBannerVOLE inicio");
		StringBuffer query = new StringBuffer();
		query.append("UPDATE ACESSO.BANNERVOLE ");
		query.append(" SET ");
		query.append("	NMBANNERVOLE = '").append(nmBannerVOLE).append("', ");
		query.append("	URLBANNERVOLE = '").append(urlBannerVOLE).append("', ");
		query.append("	IDAREABANNERVOLE = ").append(idAreaBannerVOLE).append(", ");
		query.append("	IDTIPOBANNERVOLE = ").append(idTipoBannerVOLE).append(", ");
		query.append("	DSBANNERVOLE = '").append(dsBannerVOLE).append("', ");
		query.append("	IPBANNERVOLE = '").append(IPBannerVOLE).append("', ");
		query.append("	DTINICIAL = TO_DATE('").append(dtInicial).append("', 'DD/MM/YYYY'),");
		query.append("	DTFINAL = TO_DATE('").append(dtFinal).append("', 'DD/MM/YYYY'), ");
		query.append("	INCONTADOR = ").append(inContador);
		query.append(" WHERE IDBANNERVOLE = ").append(idBannerVOLE);
		
		log.debug("query = " + query.toString());

		db.executeUpdate(query.toString());
	}

	@Override
	public void updateRelacionamentoBannerVOLE(String query) throws SQLException {
		db.executeUpdate(query);
	}
}