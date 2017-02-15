package br.com.vivo.fo.ctrls.VOLTAV.mailing;

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
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.AreaBanner;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingBanner;
import br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingLinha;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "MailingFacade", mappedName = "MailingFacade")
@Local(MailingFacade.class)
@Session(ejbName = "MailingFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MailingFacadeImpl implements MailingFacade {

    @EJB
    private Mailing                       mailingDB;

    private final static transient Logger log              = new Logger("vol");

    static final long                     serialVersionUID = 1L;

    /**
     * @common:operation
     */
    public void incluirLinha(String ddd, String linha, String idMailingBanner) throws FacadeException {
        try {
            MailingLinha mailingLinha = mailingDB.getLinhaExiste(new Integer(ddd), new Integer(linha));
            if ("0".equalsIgnoreCase(mailingLinha.getTotalRegistros())) {
                throw (new Exception("00002 - Registro inexistente"));
            }
            mailingDB.incluirLinha(new Integer(idMailingBanner), new Integer(ddd), new Integer(linha));
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:incluirLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirLinha(String ddd, String linha, String idMailingBanner) throws FacadeException {
        try {
            mailingDB.excluirLinha(new Integer(idMailingBanner), new Integer(ddd), new Integer(linha));
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:excluirLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public MailingLinha[] pesquisarLinhasPorMailing(String idMailing) throws FacadeException {
        try {
            MailingLinha[] mailingLinha = mailingDB.listarLinhasPorMailing(new Integer(idMailing));
            return mailingLinha;
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:pesquisarPorMailing(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public MailingLinha[] pesquisarPorLinha(String idMailing, String linha) throws FacadeException {
        try {
            MailingLinha[] mailingLinha = mailingDB.pesquisarPorLinha(new Integer(idMailing), new Integer(linha));
            return mailingLinha;
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:pesquisarPorLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AreaBanner[] listarAreaBanner() throws FacadeException {
        try {
            AreaBanner[] areaBanner = mailingDB.listarAreaBanner();
            return areaBanner;
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:listarAreaBanner(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public MailingBanner[] listarMailing() throws FacadeException {
        try {
            MailingBanner[] mailingBanner = mailingDB.listarMailing();
            return mailingBanner;
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:listarMailing(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public MailingLinha[] pesquisarPorDDD(String idMailing, String ddd) throws FacadeException {
        try {
            MailingLinha[] mailingLinha = mailingDB.pesquisarPorDDD(new Integer(idMailing), new Integer(ddd));
            return mailingLinha;
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:pesquisarPorDDD(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public String incluirMailing(String nome, String dataInicio, String dataFim, String idAreaBanner) throws FacadeException {
        try {
            MailingBanner mailingBanner = mailingDB.getChavePrimariaMailing();
            String idMailingBanner = mailingBanner.getIdMailingBanner();
            mailingDB.incluirMailing(nome, dataInicio, dataFim, new Integer(idAreaBanner), new Integer(idMailingBanner), new Integer(ConstantesCRM.TUX_USER_DEFAULT));
            return idMailingBanner;
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:incluirMailing(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void alterarMailing(String idMailingBanner, String dataInicio, String dataFim) throws FacadeException {
        try {
            mailingDB.alterarMailing(dataInicio, dataFim, new Integer(idMailingBanner));
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:alterarMailing(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirMailing(String idMailingBanner) throws FacadeException {
        try {
            mailingDB.excluirLinhasMailing(new Integer(idMailingBanner));
            mailingDB.excluirMailing(new Integer(idMailingBanner));
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:excluirMailing(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void incluirArquivo(java.lang.String cdParametro, java.lang.String idMailingBanner, java.lang.String idUser) throws FacadeException {
        try {
            mailingDB.incluirArquivo(cdParametro, idMailingBanner, new Integer(idUser));
        } catch (Exception ex) {
            log.error("MailingFacadeImpl:incluirArquivo(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
