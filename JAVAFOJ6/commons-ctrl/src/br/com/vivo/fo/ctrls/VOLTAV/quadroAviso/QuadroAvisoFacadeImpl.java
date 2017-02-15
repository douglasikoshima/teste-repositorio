package br.com.vivo.fo.ctrls.VOLTAV.quadroAviso;

import java.sql.Date;
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
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Associado;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Segmento;
import br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.UF;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "QuadroAvisoFacade", mappedName = "QuadroAvisoFacade")
@Local(QuadroAvisoFacade.class)
@Session(ejbName = "QuadroAvisoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class QuadroAvisoFacadeImpl implements QuadroAvisoFacade {

    private final static transient Logger log              = new Logger("vol");

    @EJB
    private QuadroAvisoDB                 db;

    /**
     * @common:operation
     */
    public Segmento[] getSegmentos() throws FacadeException {
        try {
            return db.getSegmentos();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public UF[] getUFs() throws FacadeException {
        try {
            return db.getUFs();
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Associado associadoUfSegmentacao(String conta) throws FacadeException {
        try {
            return db.associadoUfSegmentacao(conta);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void inserirMensagem(Date dtinicio, Date dtfim, String cdconta, String idconsultor, String dsmensagem, String idMensagem) throws FacadeException {
        try {
            db.insertMensagem(dtinicio, dtfim, cdconta, idconsultor, dsmensagem, idMensagem);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void inserirMensagemLog(String cdconta, String idMensagem) throws FacadeException {
        try {
            db.insertMensagemLog(cdconta, idMensagem);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void inserirMensagemSegmentacao(String segmentacao, String idconsultor, String idMensagem) throws FacadeException {
        try {
            db.insertMensagemSegmentacao(segmentacao, idconsultor, idMensagem);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void inserirMensagemUF(String uf, String idconsultor, String idMensagem) throws FacadeException {
        try {
            db.insertMensagemUF(uf, idconsultor, idMensagem);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public boolean validaAssociacao(String conta, String consultor) throws FacadeException {
        try {
            // boolean isAssociado = false;

            Associado associado = db.validaAssociacao(conta, consultor);

            if (associado.getIduf().equals("0")) {
                return false;
            } else {
                return true;
            }

            // return isAssociado = (db.validaAssociacao(conta, consultor) > 0) ? true : false;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirMensagem(String id) throws FacadeException {
        try {
            db.excluirMensagem(id);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Carteira[] carteirasTodas(String user) throws FacadeException {
        try {
            return db.carteirasTodas(user);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Carteira[] carteirasSegmentacao(String segmentacao, String user) throws FacadeException {
        try {
            return db.carteirasPorSegmentacao(segmentacao, user);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Carteira[] carteirasSegmentacaoRegional(String uf, String segmentacao, String user) throws FacadeException {
        try {
            return db.carteirasPorRegionalSegmentacao(uf, segmentacao, user);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Carteira[] carteirasRegional(String regional, String user) throws FacadeException {
        try {
            return db.carteirasPorRegional(regional, user);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Carteira[] carteirasGeral(String user) throws FacadeException {
        try {
            return db.carteirasGeral(user);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

}
