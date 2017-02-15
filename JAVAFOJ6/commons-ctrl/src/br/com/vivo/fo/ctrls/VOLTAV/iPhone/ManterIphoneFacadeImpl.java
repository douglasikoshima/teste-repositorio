package br.com.vivo.fo.ctrls.VOLTAV.iPhone;

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
import br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaIphone;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ManterIphoneFacade",mappedName="ManterIphoneFacade")
@Local(ManterIphoneFacade.class)
@Session(ejbName = "ManterIphoneFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterIphoneFacadeImpl implements ManterIphoneFacade {

    static final long serialVersionUID = 1L;
    private final static transient Logger log = new Logger("vol");

	@EJB
    private br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB manterIphoneDB;

    /**
     * @common:operation
     */
    public LinhaIphone[] getLinhasIphone(String paginaInicial, String paginaFinal, String ddd, String linha) throws FacadeException {
        try {
            Integer pagInicial = null;
            Integer pagFinal = null;
            LinhaIphone[] linhas = null;

            if (paginaInicial != null) {
                pagInicial = new Integer(paginaInicial);
            } else {
                pagInicial = new Integer(ConstantesCRM.SONE);
            }

            if (paginaFinal != null) {
                pagFinal = new Integer(paginaFinal);
            } else {
                pagFinal = new Integer("50");
            }

            if (ddd == null && linha == null) {
                //linhas = manterAgendamentoDB.getLinhaPremmiunAll(pagInicial, pagFinal);
                linhas = new LinhaIphone[0];
            }

            if (ddd != null) {
                Integer intDdd = new Integer(ddd);
                //linhas = manterIphoneDB.getLinhaIphoneByDdd(pagInicial, pagFinal, intDdd);
            }

            if (linha != null) {
                Integer intLinha = new Integer(linha);
                //linhas = manterIphoneDB.getLinhaIphoneByLinha(pagInicial, pagFinal, intLinha);
            }

            return linhas;
        } catch (Exception ex) {
            log.error("ManterIphoneFacadeImpl:registraAgendamento(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public LinhaIphone getTotalLinhasIphone(String ddd, String linha) throws FacadeException {
        try {
            LinhaIphone linhas = null;
            Integer intDdd = null;
            Integer intLinha = null;

            if (ddd != null) {
                intDdd = new Integer(ddd);
            } else {
                intDdd = new Integer(-1);
            }

            if (linha != null) {
                intLinha = new Integer(linha);
            } else {
                intLinha = new Integer(-1);
            }
            linhas = manterIphoneDB.getTotalRegistroLinhaIphone(intDdd, intLinha);
            return linhas;
        } catch (Exception ex) {
            log.error("ManterIphoneFacadeImpl:getTotalLinhasPremiun(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public LinhaIphone[] getTodasLinhas() throws FacadeException {
        try {
            LinhaIphone[] linhas = manterIphoneDB.getLinhaIphoneGeral();
            return linhas;
        } catch (Exception ex) {
            log.error("ManterIphoneFacadeImpl:getTodasLinhas(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public LinhaIphone[] getPesquisarLinhas(String ddd, String linha) throws FacadeException {
        try {
            LinhaIphone[] linhas;
            if (ddd != null) {
                linhas = manterIphoneDB.getLinhaIphoneByDdd(new Integer(ddd));
            } else {
                linhas = manterIphoneDB.getLinhaIphoneByLinha(new Integer(linha));
            }
            return linhas;
        } catch (Exception ex) {
            log.error("ManterIphoneFacadeImpl:getTodasLinhas(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void incluirLinha(String ddd, String linha) throws FacadeException {
        try {
            LinhaIphone linhaIphone = manterIphoneDB.getLinhaExiste(new Integer(ddd), new Integer(linha));
            if ("0".equalsIgnoreCase(linhaIphone.getTotalRegistros())) {
                throw (new Exception("00002 - Registro inexistente"));
            }
            manterIphoneDB.incluirLinha(new Integer(ddd), new Integer(linha));
        } catch (Exception ex) {
            log.error("ManterIphoneFacadeImpl:incluirLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirLinha(String ddd, String linha) throws FacadeException {
        try {
            manterIphoneDB.excluirLinha(new Integer(ddd), new Integer(linha));
        } catch (Exception ex) {
            log.error("ManterIphoneFacadeImpl:excluirLinha(" + ConstantesCRM.TUX_USER_DEFAULT + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
