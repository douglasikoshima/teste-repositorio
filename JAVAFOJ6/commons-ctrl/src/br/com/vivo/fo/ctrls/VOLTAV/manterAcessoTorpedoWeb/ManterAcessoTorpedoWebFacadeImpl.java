package br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb;

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
import br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.db.ManterAcessoTorpedoDB;
import br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.db.ManterAcessoTorpedoDB.ItemAcessoTorpedo;

@Stateless(name = "ManterAcessoTorpedoWebFacade", mappedName = "ManterAcessoTorpedoWebFacade")
@Local(ManterAcessoTorpedoWebFacade.class)
@Session(ejbName = "ManterAcessoTorpedoWebFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterAcessoTorpedoWebFacadeImpl implements ManterAcessoTorpedoWebFacade {

    @EJB
    private ManterAcessoTorpedoDB manterAcessoTorpedoDB;

    /**
     * @common:operation
     */
    public ItemAcessoTorpedo[] consultarAcessoTorpedoWeb() throws Exception {
        return manterAcessoTorpedoDB.consultarAcessoTorpedoWeb();
    }

    /**
     * @common:operation
     */
    public void ativarAcessoTorpedoWeb(String idAcessoTorpedo) throws Exception {
        manterAcessoTorpedoDB.ativarAcessoTorpedoWeb(idAcessoTorpedo);
    }

    /**
     * @common:operation
     */
    public void desativarAcessoTorpedoWeb(String idAcessoTorpedo) throws Exception {
        manterAcessoTorpedoDB.desativarAcessoTorpedoWeb(idAcessoTorpedo);
    }
}
