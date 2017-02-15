package br.com.indrasistemas.vivoservices.administracaosistema.endereco.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.UfTO;

public class UfTOBuilder {

	public static List buildUfTO(List list) {
		List result = null;

		if (list != null && list.size() > 0) {
			result = new ArrayList();
			UfTO uf = null;

			for (Iterator i = list.iterator(); i.hasNext();) {
				Object[] element = (Object[]) i.next();

				uf = new UfTO();
				//TODO: Alterar esse código assim que o Rubens
				// corrigir o HBM da UnidadeFederacao
				uf.setId(new Long(((Short) element[0]).longValue()));
				uf.setNome((String) element[2]);
				uf.setSigla((String) element[1]);

				result.add(uf);
			}
		}

		return result;
	}
}
