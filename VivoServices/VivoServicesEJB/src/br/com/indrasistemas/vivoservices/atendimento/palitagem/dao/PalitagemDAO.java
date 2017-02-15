package br.com.indrasistemas.vivoservices.atendimento.palitagem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.atendimento.palitagem.to.ParametrosLinhaTO;

public class PalitagemDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(PalitagemDAO.class);

	public ParametrosLinhaTO buscarParametrosLinha(ParametrosLinhaTO to) throws DAOException {

		ParametrosLinhaTO parametrosLinha = null;

		StringBuffer sql = new StringBuffer("SELECT  idpessoa, ")
		        .append("      idpessoadepara, ")
		        .append("      idlinhatelefonica, ")
		        .append("      idtipolinha, ")
				.append("      nrlinha, ")
				.append("      cdarearegistro, ")
				.append("      idconta, ")
				.append("      cdconta, ")
				.append("      idlinhasistemaorigem, ")
				.append("      idcontasistemaorigem, ")
				.append("      nmpessoa, ")
				.append("      idtipopessoa, ")
				.append("      sgtipocarteira, ")
				.append("      dstipocarteira, ")
				.append("      incorporativo, ")
				.append("      sgtipopessoa, ")
				.append("      dstipopessoa, ")
				.append("      idsegmentacao, ")
				.append("      dssegmentacao, ")
				.append("      idtipocarteira, ")
				.append("      sgtiporelacionamento, ")
				.append("      nmtiporelacionamento, ")
				.append("      idufoperadora ")
				.append(" FROM customer.parametrosessaov01 ")
				.append(" WHERE  nrlinha = ? ")
				.append("     AND cdarearegistro =  ? ")
				.append("     AND sgtiporelacionamento = 'C' ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, to.getNrLinha().intValue());
			ps.setInt(2, to.getCdAreaRegistro().intValue());

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next() && i == 0) {
				parametrosLinha = new ParametrosLinhaTO();
				parametrosLinha.setIdPessoa(new Integer(rs.getInt(1)));
				parametrosLinha.setIdPessoaDePara(new Integer(rs.getInt(2)));
				parametrosLinha.setIdLinhaTelefonica(new Integer(rs.getInt(3)));
				parametrosLinha.setIdTipoLinha(new Integer(rs.getInt(4)));
				parametrosLinha.setNrLinha(new Integer(rs.getInt(5)));
				parametrosLinha.setCdAreaRegistro(new Integer(rs.getInt(6)));
				parametrosLinha.setIdConta(new Integer(rs.getInt(7)));
				parametrosLinha.setCdConta(new Integer(rs.getInt(8)));
				parametrosLinha.setIdLinhaSistemaOrigem(rs.getString(9));
				parametrosLinha.setIdContaSistemaOrigem(rs.getString(10));
				parametrosLinha.setNmPessoa(rs.getString(11));
				parametrosLinha.setIdTipoPessoa(new Integer(rs.getInt(12)));
				parametrosLinha.setSgTipoCarteira(rs.getString(13));
				parametrosLinha.setDsTipoCarteira(rs.getString(14));
				parametrosLinha.setInCorporativo("1".equals(rs.getString(15)) ? true : false);
				parametrosLinha.setSgTipoPessoa(rs.getString(16));
				parametrosLinha.setDsTipoPessoa(rs.getString(17));
				parametrosLinha.setIdSegmentacao(new Integer(rs.getInt(18)));
				parametrosLinha.setDsSegmentacao(rs.getString(19));
				parametrosLinha.setIdTipoCarteira(new Integer(rs.getInt(20)));
				parametrosLinha.setSgTipoRelacionamento(rs.getString(21));
				parametrosLinha.setNmTipoRelacionamento(rs.getString(22));
				parametrosLinha.setIdUFOperadora(new Integer(rs.getString(23)));
				i++;
			}
			parametrosLinha.setIdCanal(to.getIdCanal());
			parametrosLinha.setIdProcedencia(to.getIdProcedencia());
			parametrosLinha.setIdUser(to.getIdUser());
			
            if (logger.isDebugEnabled()) {
                logger.debug("Parametros consultados. ("+parametrosLinha.getNmPessoa()+")");
            }
		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return parametrosLinha;
	}

	public String consultarSistemaOrigem(Long nrTelefone) throws DAOException {

		String nmSistemaOrigem = "";
		StringBuffer sql = new StringBuffer("SELECT sis.nmsistemaorigem, lin.nrmin ")
		        .append("  FROM linha.linhabase lin, ")
		        .append("       linha.linhatelefonica lte, ")
		        .append("       apoio.sistemaorigem sis, ")
		        .append("       apoio.arearegistro ar ")
		        .append(" WHERE lin.idlinhabase = lte.idlinhabase ")
		        .append("   AND sis.idsistemaorigem = lte.idsistemaorigem ")
		        .append("   AND lin.idarearegistro = ar.idarearegistro ")
		        .append("   AND lin.nrlinha = SUBSTR ('").append(nrTelefone.toString()).append("', 3) ")
				.append("   AND ar.cdarearegistro = SUBSTR ('").append(nrTelefone.toString()).append("', 1, 2) ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			// ps.setString(1, nrTelefone.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nmSistemaOrigem = rs.getString(1).toUpperCase();
				if (nmSistemaOrigem.indexOf("NGIN") >= 0) {
					nmSistemaOrigem = "NGIN";
				} else if (nmSistemaOrigem.indexOf("ATLYS") >= 0) {
					nmSistemaOrigem = "ATLYS";
				}
			}
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível consulta a sistema origem. " + ex.getMessage());
			}
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return nmSistemaOrigem;
	}
}