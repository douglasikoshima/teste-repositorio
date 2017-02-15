package br.com.indrasistemas.vivoservices.administracaosistema.endereco.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;

public class UnidadeFederativaDAO extends HibernateBaseDAO {

	public List buscarTodas() throws DAOException {

		String hql = "select uf.codUf, uf.sglUf, uf.nomUf "
				+ "	from UnidadeFederacao uf";
		Query query = this.getQuery(hql);
		List list = query.list();
		return UfTOBuilder.buildUfTO(list);
	}
}
