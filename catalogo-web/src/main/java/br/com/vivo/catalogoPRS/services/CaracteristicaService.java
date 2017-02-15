package br.com.vivo.catalogoPRS.services;

/*import br.com.vivo.catalogoPRS.controls.CaracteristicaSoapServiceControl;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaValorCaracteristicaRequestDocument;
import br.com.vivo.sn.catalogoCaracteristica.BuscarListaValorCaracteristicaResponseDocument;
import com.bea.control.ServiceControlException;
*/

public class CaracteristicaService {

	private static CaracteristicaService instance;

	private CaracteristicaService() {
		// singleton
	}

	public static CaracteristicaService getInstance() {
		if (instance == null) {
			instance = new CaracteristicaService();
		}
		return instance;
	}

/*	public BuscarListaValorCaracteristicaResponseDocument buscarListaValorCaracteristica(CaracteristicaSoapServiceControl caracteristicaSoapServiceControl,
			BuscarListaValorCaracteristicaRequestDocument paramListarValorCaracteristica, boolean vazioAutorizado) {
		try {
			return caracteristicaSoapServiceControl.buscarListaValorCaracteristica(paramListarValorCaracteristica);
		} catch (ServiceControlException e) {
			if (vazioAutorizado && e.hasSoapFault()) {
				return null;
			} else {
				throw e;
			}
		}
	}*/

}
