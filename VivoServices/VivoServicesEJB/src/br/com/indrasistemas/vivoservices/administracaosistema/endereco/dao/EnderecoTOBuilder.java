package br.com.indrasistemas.vivoservices.administracaosistema.endereco.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.EnderecoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.PaisTO;
import br.com.indrasistemas.vivoservices.administracaosistema.endereco.to.UfTO;

public class EnderecoTOBuilder {

	public static List buildEnderecoTO(List list) {
		List result = null;

		if (list != null && list.size() > 0) {
			result = new ArrayList();
			EnderecoTO to = null;
			UfTO ufTO = null;
			PaisTO paisTO = null;

			for (Iterator i = list.iterator(); i.hasNext();) {
				Object[] element = (Object[]) i.next();

				to = new EnderecoTO();
				to.setCep((String) element[0]);
				to.setLogradouro((String) element[1]);
				to.setTituloLogradouro((String) element[2]);
				to.setMunicipio((String) element[3]);
				to.setTipoLogradouro((String) element[4]);
				to.setBairro((String) element[5]);

				ufTO = new UfTO();
				ufTO.setId((Long) element[6]);
				ufTO.setNome((String) element[7]);
				ufTO.setSigla((String) element[8]);
				to.setUf(ufTO);

				paisTO = new PaisTO();
				paisTO.setId((Long) element[9]);
				paisTO.setSigla((String) element[10]);
				paisTO.setNome((String) element[11]);
				to.setPais(paisTO);

				Boolean ladoPar = (Boolean) element[12];
				to.setLado(ladoPar.booleanValue() ? EnderecoTO.LADO_PAR
						: EnderecoTO.LADO_IMPAR);

				result.add(to);
			}

		}

		return result;
	}
}
