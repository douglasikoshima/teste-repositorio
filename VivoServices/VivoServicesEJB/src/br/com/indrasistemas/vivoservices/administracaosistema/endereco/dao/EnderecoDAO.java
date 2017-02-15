package br.com.indrasistemas.vivoservices.administracaosistema.endereco.dao;

import java.util.List;

import org.hibernate.Query;

import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PesquisaEnderecoTO;

public class EnderecoDAO extends HibernateBaseDAO {

	public List buscarEndereco(PesquisaEnderecoTO filtro, Integer first, Integer total) throws DAOException {
		String hql = "select cep.numCep, logradouro.nomLogradouro,"
				+ "       titulo.dscTituloLograd,"
				+ "       municipio.dscLocalidade,"
				+ "       tipoLogradouro.dscTipoLograd,"
				+ "       bairro.nomBairro, uf.iduf, uf.nmuf, uf.sguf, "
				+ "       pais.idpais, pais.sgpais, pais.nmpais,"
				+ "       relCep.indLadoNumeracao"
				+ "  from RelCepLogradBairro relCep,"
				+ "  Logradouro logradouro left outer join"
				+ "  logradouro.tituloLogradouro as titulo,"
				+ "  TipoLogradouro tipoLogradouro,"
				+ "  Localidade municipio, Cep cep, Bairro bairro,"
				+ "  UnidadeFederacao unifed,"
				+ "  br.com.indrasistemas.vivonet.datalayer.endereco.Pais p,"
				+ "  Uf uf,"
				+ "  br.com.indrasistemas.vivonet.datalayer.apoio.Pais pais"
				+ " where relCep.logradouro = logradouro "
				+ "   and tipoLogradouro = logradouro.tipoLogradouro"
				+ "   and municipio = logradouro.localidade"
				+ "   and cep = relCep.cep and bairro = relCep.bairro"
				+ "   and municipio.unidadeFederacao = unifed"
				+ "   and p = unifed.pais and unifed.sglUf = uf.sguf"
				//+ "   and p = unifed.codPais and unifed.sglUf = uf.sguf"
				+ "   and p.sglIsoPais = pais.sgpais";

		if (filtro.getCep() != null && !"".equals(filtro.getCep().trim())) {
			hql += "  and cep.numCep = :cep";
		}

		if (filtro.getLogradouro() != null
				&& !"".equals(filtro.getLogradouro().trim())) {
			hql += "  and logradouro.nomLogradouro like upper(:rua)";
		}

		if (filtro.getMunicipio() != null
				&& !"".equals(filtro.getMunicipio().trim())) {
			hql += "  and municipio.dscLocalidade like upper(:cidade)";
		}

		if (filtro.getBairro() != null && !"".equals(filtro.getBairro().trim())) {
			hql += "  and bairro.nomBairro like upper(:bairro)";
		}

		Query q = this.getQuery(hql, first, total);

		if (filtro.getCep() != null && !"".equals(filtro.getCep().trim())) {
			q.setString("cep", filtro.getCep());
		}

		if (filtro.getLogradouro() != null
				&& !"".equals(filtro.getLogradouro().trim())) {
			q.setString("rua", filtro.getLogradouro());
		}

		if (filtro.getMunicipio() != null
				&& !"".equals(filtro.getMunicipio().trim())) {
			q.setString("cidade", filtro.getMunicipio());
		}

		if (filtro.getBairro() != null && !"".equals(filtro.getBairro().trim())) {
			q.setString("bairro", filtro.getBairro());
		}

		return EnderecoTOBuilder.buildEnderecoTO(q.list());
	}

}
