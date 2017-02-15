package br.com.indrasistemas.vivoservices.portabilidade.programapontos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.portabilidade.programapontos.webservice.to.RespostaStatusPortabilidadeTO;

public class ConsultarPortabilidadeDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(ConsultarPortabilidadeDAO.class);

	public RespostaStatusPortabilidadeTO consultarStatusPortabilidade(RequestInfoTO requestInfoTO, String nrLinha) throws DAOException {

		String ddd = nrLinha.substring(0, 2);
		String nrTelefone = nrLinha.substring(2);

		RespostaStatusPortabilidadeTO respostaPPTO = new RespostaStatusPortabilidadeTO();

		StringBuffer sql = new StringBuffer("");

		sql.append("select acao, ");
		sql.append("       estado ");
		sql.append("from ( ");
		sql.append("select sgtipoportabilidade acao, ");
		sql.append("       dsacaoportabilidade estado ");
		sql.append("from customer.pessoaportabilidadehist pph ");
		sql.append("where pph.cdarearegistro = ").append(ddd).append(" ");
		sql.append("and pph.nrlinha = ").append(nrTelefone).append(" ");
		sql.append("and  not exists (select 1 from customer.pessoaportabilidadehist nin ");
		sql.append("                where nin.cdarearegistro = pph.cdarearegistro ");
		sql.append("                and   nin.nrlinha = pph.nrlinha ");
		sql.append("                and nin.dsacaoportabilidade in ('Autorização Negada','Concluído','Cancelado')) ");
		sql.append("order by pph.dtultimaalteracao desc) a ");
		sql.append("where rownum<=1 ");
		
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				respostaPPTO.setInAcao(-1); //Apenas para que as regras fiquem na camada do AS indicando que possui dados
				respostaPPTO.setDsAcao(rs.getString("acao"));
				respostaPPTO.setDsEstado(rs.getString("estado"));
				respostaPPTO.setStatus("OK");
			}else{
				respostaPPTO.setInAcao(0);
				respostaPPTO.setDsAcao("");
				respostaPPTO.setDsEstado("");
				respostaPPTO.setStatus("OK");
				respostaPPTO.setReason("Nenhum registro encontrado.");
			}
		} catch (Exception ex) {
			logger.error("Exception: ", ex);
			throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
			ps = null;
			conn = null;
		}
		return respostaPPTO;
	}
}