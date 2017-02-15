package br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db.Acao;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "AcaoIncentivoFacade", mappedName = "AcaoIncentivoFacade")
@Local(AcaoIncentivoFacade.class)
@Session(ejbName = "AcaoIncentivoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AcaoIncentivoFacadeImpl implements AcaoIncentivoFacade {

	private static final long serialVersionUID = 1L;

	private final static transient Logger log = new Logger("vol");

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.acaoIncentivo.db.AcaoIncentivoDB db;

	/**
	 * @common:operation
	 */
	public Acao[] getTodasAcoes() throws FacadeException {
		try {
			Acao[] acoes = db.getTodasAcoes();
			return acoes;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public Acao[] getPesquisaAcoes(String query) throws FacadeException {
		try {
			Acao[] acoes = db.getPesquisaAcoes(query);
			return acoes;
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public Acao buscaAcaoIncentivo(String id) throws FacadeException {
		try {
			return db.buscarAcaoIncentivo(id);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void excluirAcaoIncentivo(String id) throws FacadeException {
		try {
			db.excluirAcaoIncentivo(id);
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void manterAcaoIncentivo(String id, String descricao, String dataInicial, String dataFinal, String tipo, String msgInicial, String msgFinal, String inAtivo, String user) throws FacadeException {
		try {
			if (id == null || id.equals(ConstantesCRM.SVAZIO)) {
				db.inserirAcaoIncentivo(id, descricao, dataInicial, dataFinal, tipo, msgInicial, msgFinal, inAtivo, user);
			} else {
				db.atualizarAcaoIncentivo(id, descricao, dataInicial, dataFinal, tipo, msgInicial, msgFinal, inAtivo, user);
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			throw (new FacadeException(ex));
		}
	}

}
