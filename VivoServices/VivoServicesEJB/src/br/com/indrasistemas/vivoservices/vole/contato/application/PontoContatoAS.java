package br.com.indrasistemas.vivoservices.vole.contato.application;

import java.util.List;
import javax.ejb.SessionContext;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.vole.contato.dao.PontoContatoDAO;
import br.com.indrasistemas.vivoservices.vole.contato.to.PontoContatoTO;

public class PontoContatoAS extends BaseApplicationService {

	public PontoContatoAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public List buscarPontoContato(PontoContatoTO filtro, Integer pagina,
			Integer qtdeRegistros) throws ApplicationServiceException,
			BusinessException {

		String nrCNPJ;
		/*
		 String sgUF;
		 String cdDDD;
		 */
		List resultDAO = null;
		List ListAux = null;
		List ListDDD = null;
		List ListUF = null;
		long idTipoEmpresa;
		int first = 0;
		int max = PontoContatoTO.RESULTADO_MAXIMO;

		if (pagina != null) {
			first = ((pagina.intValue() - 1) * PontoContatoTO.RESULTADO_MAXIMO);
		}
		if (qtdeRegistros != null) {
			max = first + qtdeRegistros.intValue();
		}

		if (max - first > PontoContatoTO.RESULTADO_MAXIMO) {
			max = first + PontoContatoTO.RESULTADO_MAXIMO;
		}

		try {

			PontoContatoDAO pontoContatoDAO = new PontoContatoDAO();

			ListDDD = pontoContatoDAO.buscarDDD(filtro);
			if (ListDDD != null && ListDDD.size() > 0) {
				ListUF = pontoContatoDAO.buscarUF(ListDDD);
				if (ListUF != null && ListUF.size() > 0) {

					// Identifica o Tipo de Empresa, para chamar o metodo correto
					// 0 - GCN
					// 1 - PME
					ListAux = pontoContatoDAO.buscarPontoContato(ListUF,filtro);
					if (ListAux != null && ListAux.size() > 0) {
						idTipoEmpresa = ((PontoContatoTO) ListAux.get(0))
								.getIdTipoEmpresa();

						nrCNPJ = filtro.getNrCnpj();
						/* RaizCnpj = nrCNPJ.substring(0, 8); */
						filtro.setNrCnpj(nrCNPJ);
						if (idTipoEmpresa == 0) {
							resultDAO = pontoContatoDAO.buscarGCNPontoContato(
									ListUF, filtro, new Integer(first),
									new Integer(max));
						}
						if (idTipoEmpresa == 1) {
							resultDAO = pontoContatoDAO.buscarPMEPontoContato(
									ListUF, filtro, new Integer(first),
									new Integer(max));
						}
					}

				}

			}

		} catch (DAOException ex) {
			getSessionContext().setRollbackOnly();
			throw new ApplicationServiceException(ex);
		}
		return resultDAO;
	}

}
