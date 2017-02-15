package br.com.vivo.fo.ctrls.VOLTAV.fatura.db;

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
import br.com.vivo.fo.ctrls.VOLTAV.fatura.db.dbClasses.TipoComunicacao;
import br.com.vivo.fo.db.DataBaseCall;

@Stateless(name = "FaturaDB", mappedName = "FaturaDB")
@Local(FaturaDB.class)
@Session(ejbName = "FaturaDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FaturaDBImpl implements FaturaDB {
	
	@EJB
	private DataBaseCall database;

	@Override
	public int inHierarquia(String cdConta) throws SQLException {
		int retorno = 0;
		StringBuffer query = new StringBuffer();
        query.append("SELECT DECODE(COUNT(*),0,0,1) inhierarquia ");
        query.append("  FROM customer.contahierarquia ch, ");
        query.append("       customer.conta c, ");
        query.append("       apoio.sistemaorigem so ");
        query.append(" WHERE so.sgsistemaorigem = 'ATY' ");
        query.append("   AND so.idsistemaorigem = c.idsistemaorigem ");
        query.append("   AND c.idcontasistemaorigem = ").append(cdConta).append(" ");
        query.append("   AND (ch.idconta = c.idconta OR ch.idcontapai = c.idconta) ");
	
		ResultSet rs = database.executeQuery(query.toString());
        if (rs.next()) {
			retorno = rs.getInt(1);
		}
        
		return retorno;
	}

	@Override
	public int inClienteDadosZap(String cdDDD, String nrLinha) throws SQLException {
		int retorno = 0;
		StringBuffer query = new StringBuffer();
		query.append("SELECT COUNT(1) ");
        query.append("  FROM LINHA.LINHABASE LINHABASE, ");
        query.append("       LINHA.LINHATELEFONICA LINHATELEFONICA, ");
        query.append("       APOIO.AREAREGISTRO AREAREGISTRO, ");
        query.append("       LINHA.PLANOSERVICOLINHA PLANOSERVICOLINHA, ");
        query.append("       LINHA.PLANOSERVICO PLANOSERVICO ");
        query.append(" WHERE LINHABASE.NRLINHA = ").append(nrLinha).append(" ");
        query.append("   AND LINHABASE.IDAREAREGISTRO = AREAREGISTRO.IDAREAREGISTRO ");
        query.append("   AND AREAREGISTRO.CDAREAREGISTRO = ").append(cdDDD).append(" ");
        query.append("   AND LINHABASE.IDLINHABASE = LINHATELEFONICA.IDLINHABASE ");
        query.append("   AND LINHATELEFONICA.IDLINHATELEFONICA = PLANOSERVICOLINHA.IDLINHATELEFONICA ");
        query.append("   AND PLANOSERVICOLINHA.IDSERVICO = PLANOSERVICO.IDSERVICO ");
        query.append("   AND PLANOSERVICO.NMSERVICO LIKE '%BASICO CARTAO%' ");
        query.append("   AND (PLANOSERVICOLINHA.DTEXPIRACAO IS NULL OR PLANOSERVICOLINHA.DTEXPIRACAO  >= SYSDATE) ");
        query.append("   AND (PLANOSERVICOLINHA.DTVIGENCIAFINAL IS NULL OR PLANOSERVICOLINHA.DTVIGENCIAFINAL >= SYSDATE) ");
		
		ResultSet rs = database.executeQuery(query.toString());
        
        if (rs.next()) {
			retorno = rs.getInt(1);
		}
        
		return retorno;
	}

	@Override
	public TipoComunicacao[] getTipoComunicacaoBySgClassificacao(String sgClassificacao) throws SQLException {
		
		StringBuffer query = new StringBuffer();
		query.append("SELECT * FROM APOIO.TIPOCOMUNICACAO ");
        query.append(" WHERE SGCLASSIFICACAO = '").append(sgClassificacao).append("' ");
		
		ArrayList<TipoComunicacao> list = new ArrayList<TipoComunicacao>();
		ResultSet rs = database.executeQuery(query.toString());

		if (rs.next()) {
			TipoComunicacao tipoComunicacao = new TipoComunicacao();
			tipoComunicacao.setIdTipoComunicacao(rs.getLong("idTipoComunicacao"));
			tipoComunicacao.setSgTipoComunicacao(rs.getString("sgTipoComunicacao"));
			tipoComunicacao.setDsTipoComunicacao(rs.getString("dsTipoComunicacao"));
			tipoComunicacao.setIdUsuarioAlteracao(rs.getLong("idUsuarioAlteracao"));
			tipoComunicacao.setDtUltimaAlteracao(rs.getDate("dtUltimaAlteracao"));
			tipoComunicacao.setIdFormaRetorno(rs.getInt("idFormaRetorno"));
			tipoComunicacao.setSgClassificacao(rs.getString("sgClassificacao"));
			list.add(tipoComunicacao);
		}
        
		rs.close();
		database.release();
			
			return list.toArray(new TipoComunicacao[0]);
	}

}
