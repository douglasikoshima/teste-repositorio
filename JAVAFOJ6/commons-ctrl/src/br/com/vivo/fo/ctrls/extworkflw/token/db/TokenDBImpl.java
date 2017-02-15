package br.com.vivo.fo.ctrls.extworkflw.token.db;

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
import br.com.vivo.fo.db.DataBaseCall;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "TokenDB", mappedName = "TokenDB")
@Local(TokenDB.class)
@Session(ejbName = "TokenDB", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TokenDBImpl implements TokenDB {

	@EJB
	private DataBaseCall database;
	
	private final static transient Logger log = new Logger("extworkflw");
	
	@Override
	public DadosTokenVivo360 getDadosToken(String id) throws SQLException {		
		log.debug("inicio getDadosToken id = " + id);
		
		ResultSet rs = null;
		Statement st = null;
		DadosTokenVivo360 dadosTokenVivo360 = null;
		
		StringBuffer query = new StringBuffer();
	try {
		
		query.append("SELECT T.IDGRUPO, T.IDTOKENVIVO360, T.IDUSUARIOALTERACAO, T.NRLINHA, T.NRPROTOCOLO,");
		query.append(" TO_CHAR(T.DTULTIMAATUALIZACAO, 'DD/MM/YYYY') DTULTIMAATUALIZACAO, U.NMLOGINUSUARIO ");
		query.append(" FROM ACESSO.TOKENVIVO360 T, ACESSO.USUARIO U WHERE T.IDTOKENVIVO360 = '").append(id).append("'");
		query.append(" AND U.IDPESSOAUSUARIO = T.IDUSUARIOALTERACAO");
			
		log.debug("query = " + query.toString());
		
		st = database.getConnection().createStatement();

		rs = st.executeQuery(query.toString());

		dadosTokenVivo360 = new DadosTokenVivo360();
		if (rs.next()) {			
			dadosTokenVivo360.setDtUltimaAtualizacao(rs.getString("DTULTIMAATUALIZACAO"));
			dadosTokenVivo360.setIdGrupo(rs.getString("IDGRUPO"));
			dadosTokenVivo360.setIdTokenVivo360(rs.getString("IDTOKENVIVO360"));
			dadosTokenVivo360.setIdUsuarioAlteracao(rs.getString("IDUSUARIOALTERACAO"));
			dadosTokenVivo360.setNrLinha(rs.getString("NRLINHA"));
			dadosTokenVivo360.setNrProtocolo(rs.getString("NRPROTOCOLO"));
			dadosTokenVivo360.setNmLoginUsuario(rs.getString("NMLOGINUSUARIO"));
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
	
		
		return dadosTokenVivo360;
	}

}
