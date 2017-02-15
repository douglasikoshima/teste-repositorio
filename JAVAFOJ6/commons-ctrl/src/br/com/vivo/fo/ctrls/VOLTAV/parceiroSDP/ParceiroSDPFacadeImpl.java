package br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB;
import br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.FolhaContato;
import br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.Parceiro;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ParceiroSDPFacade", mappedName = "ParceiroSDPFacade")
@Local(ParceiroSDPFacade.class)
@Session(ejbName = "ParceiroSDPFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParceiroSDPFacadeImpl implements ParceiroSDPFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final static transient Logger log              = new Logger("vol");

    @EJB
    private ParceiroSDPDB                 parceiroSDPDB;

    /**
     * @common:operation
     */
    public Parceiro[] getParceiros(String nome) throws FacadeException {
        try {
            Parceiro[] parceiros = null;

            parceiros = parceiroSDPDB.getParceiros(nome.toUpperCase());
            return parceiros;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Parceiro[] getTodosParceiros() throws FacadeException {
        try {
            Parceiro[] parceiros = null;

            parceiros = parceiroSDPDB.getTodosParceiros();
            return parceiros;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public Parceiro carregaParceiro(String id) throws FacadeException {
        try {
            Parceiro parceiro = null;

            parceiro = parceiroSDPDB.carregaParceiro(id);

            return parceiro;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FolhaContato[] getFolhasContatoDisponiveis(String id) throws FacadeException {
        try {
            FolhaContato[] folhas = null;

            if (id == null) {
                folhas = parceiroSDPDB.getFolhasDisponiveis();
            } else {
                folhas = parceiroSDPDB.getFolhasDisponiveisAlteracao(id);
            }

            return folhas;

        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public boolean verificaDuplicidade(String nome) throws FacadeException {
        try {
            //boolean isDuplicado = false;
            return (parceiroSDPDB.duplicidade(nome) > 0) ? true : false;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void manterParceiroSDP(String operacao, String user, String nome, String menu, String contato, String ip, String url) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>" + operacao + "</operacao>");
        sb.append("<nmParceiro>" + nome + "</nmParceiro>");
        sb.append("<dsUrlParceiro>" + url + "</dsUrlParceiro>");
        sb.append("<dsIpParceiro>" + ip + "</dsIpParceiro>");
        sb.append("<idContato>" + contato + "</idContato>");

        if (operacao.equals("insereMenuParceiro")) {
            sb.append("<idItemPai>" + menu + "</idItemPai>");
        } else {
            sb.append("<idItemMenu>" + menu + "</idItemMenu>"); // No caso de
                                                                // alteração
        }

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, "DADMENU", sb.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

        } catch (XmlException e) {
            log.error("XmlException - OrdenarMenuFacadeImpl:gravar(" + user + ") - [" + e.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de saida: OrdenarMenuFacadeImpl:obterComboCanal", e));

        } catch (TuxedoServiceCallerException e) {
            log.error("TuxedoException - OrdenarMenuFacadeImpl:gravar(" + user + ")", e);
            throw new FacadeException(e.getMessage(), e);
        }
    }
}