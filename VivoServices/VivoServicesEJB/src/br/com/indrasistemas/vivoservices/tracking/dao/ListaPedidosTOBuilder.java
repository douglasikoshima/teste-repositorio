package br.com.indrasistemas.vivoservices.tracking.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;

public class ListaPedidosTOBuilder {

	public static List buildListaPedidosTO(List list) {
		List result = null;

		if (list != null && list.size() > 0) {
			result = new ArrayList();
			PedidoTO to = null;

			for (Iterator i = list.iterator(); i.hasNext();) {
				Object[] element = (Object[]) i.next();

				to = new PedidoTO();
				
				to.setDtAberturaPedido((Date) element[0]);
				to.setSgUF((String) element[1]);
				to.setIdPedido((Long) element[2]);
				to.setNrNotaFiscal((Long) element[3]);
				to.setStatus((String) element[4]);

				result.add(to);
			}

		}

		return result;
	}
}
