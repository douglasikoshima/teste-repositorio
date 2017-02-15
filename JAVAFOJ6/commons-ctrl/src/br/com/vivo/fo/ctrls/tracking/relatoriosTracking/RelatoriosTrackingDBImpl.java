package br.com.vivo.fo.ctrls.tracking.relatoriosTracking;

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
import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses.TrackingRelatorioConsolidado;
import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses.TrackingRelatorioDetalhado;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name="RelatoriosTrackingDB",mappedName="RelatoriosTrackingDB")
@Local(RelatoriosTrackingDB.class)
@Session(ejbName = "RelatoriosTrackingDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatoriosTrackingDBImpl implements RelatoriosTrackingDB {

	@EJB
	private DataBaseCall database;
	
	@Override
	public TrackingRelatorioConsolidado[] getTrackingRelatorioConsolidado(String query) throws SQLException {
		
		ArrayList<TrackingRelatorioConsolidado> list = new ArrayList<TrackingRelatorioConsolidado>();
		ResultSet rs = database.executeQuery(query);
		
		while(rs.next()){
			TrackingRelatorioConsolidado track = new TrackingRelatorioConsolidado();
			track.setStatus(rs.getInt("status"));
			track.setDsStatus(rs.getString("dsStatus"));
			track.setVolume1(rs.getBigDecimal("volume1"));
			track.setVolume2(rs.getBigDecimal("volume2"));
			track.setVolume3(rs.getBigDecimal("volume3"));
			track.setVolume4(rs.getBigDecimal("volume4"));
			track.setVolume5(rs.getBigDecimal("volume5"));
			track.setVolume6(rs.getBigDecimal("volume6"));
			track.setVolume7(rs.getBigDecimal("volume7"));
			track.setVolume8(rs.getBigDecimal("volume8"));
			track.setAgingstatus(rs.getInt("agingstatus"));
			track.setAgingpedido(rs.getInt("agingpedido"));
			track.setSguf(rs.getString("sguf"));
			track.setDssegmento(rs.getString("dssegmento"));
			track.setIdcanalvenda(rs.getBigDecimal("idcanalvenda"));
			list.add(track);
		}
		rs.close();
		database.release();
		
		return list.toArray(new TrackingRelatorioConsolidado[0]);
	}

	@Override
	public TrackingRelatorioDetalhado[] getTrackingRelatorioDetalhado(String query) throws SQLException {
		ArrayList<TrackingRelatorioDetalhado> list = new ArrayList<TrackingRelatorioDetalhado>();
		ResultSet rs = database.executeQuery(query);
		
		while(rs.next()){
			TrackingRelatorioDetalhado track = new TrackingRelatorioDetalhado();
			track.setNrRegistro(rs.getLong("nrregistro"));
			track.setDtPedido(rs.getString("dtpedido"));
			track.setNrPedido(rs.getLong("nrpedido"));
			track.setNrOrdemVenda(rs.getLong("nrordemvenda"));
			track.setDtOrdemVenda(rs.getString("dtordemvenda"));
			track.setDsStatus(rs.getString("dsstatus"));
			track.setNmResponsavel(rs.getString("nmresponsavel"));
			track.setMotivoBloqueio(rs.getString("motivobloqueio"));
			track.setDtMotivoBloqueio(rs.getString("dtmotivobloqueio"));
			track.setSgUF(rs.getString("sguf"));
			track.setDsCanalVenda(rs.getString("dscanalvenda"));
			track.setIdCanalVenda(rs.getInt("idcanalvenda"));
			track.setQtdAparelho(rs.getInt("qtdaparelho"));
			track.setNrFornecimento(rs.getLong("nrfornecimento"));
			track.setDtFornecimento(rs.getString("dtfornecimento"));
			track.setNrPicking(rs.getLong("nrpicking"));
			track.setDtPicking(rs.getString("dtpicking"));
			track.setNrNotaFiscal(rs.getLong("nrnotafiscal"));
			track.setDtNotaFiscal(rs.getString("dtnotafiscal"));
			track.setTempoStatus(rs.getInt("tempostatus"));
			track.setTempoDecorrido(rs.getInt("tempodecorrido"));
			track.setNmResponsavelOV(rs.getString("nmresponsavelov"));
			track.setDsSegmento(rs.getString("dssegmento"));
			track.setDtRelatorio(rs.getString("dtrelatorio"));
			track.setInTransporte(rs.getInt("intransporte"));
			track.setDtTransporte(rs.getString("dttransporte"));
			list.add(track);
		}
		rs.close();
		database.release();
		
		return list.toArray(new TrackingRelatorioDetalhado[0]);
	}
	
	@Override
	public int getTrackingRelatorioDetalhadoCount(String query) throws SQLException {
		
		ResultSet rs = database.executeQuery(query);
		int total = 0;
		if(rs.next()){
			total = rs.getInt("total");
		}
		rs.close();
		database.release();
		
		return total;
	}

}
