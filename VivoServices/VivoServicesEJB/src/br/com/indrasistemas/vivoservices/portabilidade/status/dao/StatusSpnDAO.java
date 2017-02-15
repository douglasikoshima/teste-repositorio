package br.com.indrasistemas.vivoservices.portabilidade.status.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.status.to.RespostaTO;

public class StatusSpnDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(StatusSpnDAO.class);

	public RespostaTO validaStatusSpn(RequestInfoTO requestInfo, String nrLinha) throws DAOException {

		RespostaTO respostaTO = new RespostaTO();

		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT decode(el.inlinhacancelada,0,'ATIVO',1,'DESATIVO') dsestadolinha, ");
		sql.append("       tdoc.sgclassificacao, ");
		sql.append("       doc.nrdocumento, ");
		sql.append("       tl.idclassificacaotipolinha ");
		sql.append("FROM apoio.arearegistro ar, ");
		sql.append("     linha.linhabase lb, ");
		sql.append("     linha.linhatelefonica lt, ");
		sql.append("     customer.tiporelacionamento tr, ");
		sql.append("     customer.pessoalinha pl, ");
		sql.append("     customer.pessoadepara pdp, ");
		sql.append("     customer.pessoadocumento pd, ");
		sql.append("     customer.documento doc, ");
		sql.append("     apoio.tipodocumento tdoc, ");
		sql.append("     apoio.estadolinha el, ");
		sql.append("     apoio.tipolinha tl ");
		sql.append("WHERE lb.idarearegistro       = ar.idarearegistro ");
		sql.append("  AND lb.idestadolinha        = el.idestadolinha ");
		sql.append("  AND lt.idlinhabase          = lb.idlinhabase ");
		sql.append("  AND lt.idtipolinha          = tl.idtipolinha ");
		sql.append("  AND pl.idlinhatelefonica    = lt.idlinhatelefonica ");
		sql.append("  AND pl.idtiporelacionamento = tr.idtiporelacionamento ");
		sql.append("  AND pdp.idpessoadepara      = pl.idpessoadepara ");
		sql.append("  AND pd.idpessoa             = pdp.idpessoa ");
		sql.append("  AND doc.iddocumento         = pd.iddocumento ");
		sql.append("  AND doc.idtipodocumento     = tdoc.idtipodocumento ");
		sql.append("  AND ar.cdarearegistro       = ? ");
		sql.append("  AND lb.nrlinha              = ? ");
		sql.append("  AND tr.sgtiporelacionamento = 'C' ");
		sql.append("  AND tdoc.sgclassificacao IN ('CNPJ','CPF','RG') ");
		
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setLong(1, new Long(nrLinha.substring(0, 2)).longValue());
			ps.setLong(2, new Long(nrLinha.substring(2)).longValue());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				respostaTO.setTpLinha(rs.getString("idclassificacaotipolinha"));
				respostaTO.setStatusLinha(rs.getString("dsestadolinha"));
				respostaTO.setTpDocumento(rs.getString("sgclassificacao"));
				respostaTO.setNrDocumento(rs.getString("nrdocumento"));
			}else{
				logger.error("StatusSpnDAO::validaStatusSpn::Nenhum registro encontrado");
				throw new DAOException("Nenhum registro encontrado.");
			}
		} catch (Exception ex) {
			logger.error("StatusSpnDAO::validaStatusSpn::Exception", ex);
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
			ps = null;
			conn = null;
		}
		return respostaTO;
	}
}