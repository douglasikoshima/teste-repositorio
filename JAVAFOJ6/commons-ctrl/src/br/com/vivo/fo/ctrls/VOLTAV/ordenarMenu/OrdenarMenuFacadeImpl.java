package br.com.vivo.fo.ctrls.VOLTAV.ordenarMenu;

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
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ItemMenuVODocument.ItemMenuVO;
import br.com.vivo.fo.voltav.vo.ListaMenuVODocument;
import br.com.vivo.fo.voltav.vo.ListaMenuVODocument.ListaMenuVO;
import br.com.vivo.fo.xml.XmlManager;


/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="OrdenarMenuFacade",mappedName="OrdenarMenuFacade")
@Local(OrdenarMenuFacade.class)
@Session(ejbName = "OrdenarMenuFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OrdenarMenuFacadeImpl implements OrdenarMenuFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

    private final static String _SERVICO = "DADMENU";
    static final long serialVersionUID = 1L;

    private Logger log = new Logger(ConstantesCRM.SVAZIO);

    /**
     * @common:operation
     */
    public ListaMenuVO obterComboSubSistema(String user, String idCanal) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>consultarMenuSuperior</operacao>");
        sb.append("<idCanal>").append(idCanal).append("</idCanal>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ListaMenuVODocument listaDoc = ListaMenuVODocument.Factory.parse(xmlOut);

            return listaDoc.getListaMenuVO();
        } catch (XmlException e) {
            log.error("XmlException - OrdenarMenuFacadeImpl:obterComboCanal(" + user + ") - [" + e.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de saida: OrdenarMenuFacadeImpl:obterComboCanal", e));
        } catch (TuxedoServiceCallerException e) {
            log.error("TuxedoException - OrdenarMenuFacadeImpl:obterComboSubSistema(" + user + ")", e);
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public ListaMenuVO pesquisarMenu(String user, String idCanal, String idItemMenu) throws FacadeException {
        if ("-1".equals(idItemMenu)) {
            return obterComboSubSistema(user, idCanal);
        } else {
            return pesquisarItensMenu(user, idCanal, idItemMenu);
        }
    }

    /**
     * @common:operation
     */
    public ListaMenuVO pesquisarItensMenu(String user, String idCanal, String idItemMenu) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>consultarMenuFilho</operacao>");
        sb.append("<idCanal>").append(idCanal).append("</idCanal>");
        sb.append("<idItemMenu>").append(idItemMenu).append("</idItemMenu>");

        try {
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();
            
            ListaMenuVODocument listaDoc = ListaMenuVODocument.Factory.parse(xmlOut);

            return listaDoc.getListaMenuVO();
        } catch (XmlException e) {
            log.error("XmlException - OrdenarMenuFacadeImpl:obterComboCanal(" + user + ") - [" + e.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de saida: OrdenarMenuFacadeImpl:obterComboCanal", e));
        } catch (TuxedoServiceCallerException e) {
            log.error("TuxedoException - OrdenarMenuFacadeImpl:pesquisarMenu(" + user + ")", e);
            throw new FacadeException(e.getMessage(), e);
        }
    }

    /**
     * @common:operation
     */
    public void gravar(String user, ListaMenuVO itens) throws FacadeException {
        StringBuffer sb = new StringBuffer();

        sb.append("<operacao>alterarOrdemMenu</operacao>");

        try {
            ItemMenuVO[] menus = itens.getItemMenuVOArray();
            for(int i=0; i<menus.length; i++) {
                sb.append("<ItemMenuVO>");
                sb.append("<idItemMenu>" + menus[i].getIdItemMenu() + "</idItemMenu>");
                sb.append("<sqSequencia>" + menus[i].getSqSequencia() + "</sqSequencia>");
                sb.append("</ItemMenuVO>");
            }

            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            
            return;
        } catch (XmlException e) {
            log.error("XmlException - OrdenarMenuFacadeImpl:gravar(" + user + ") - [" + e.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de saida: OrdenarMenuFacadeImpl:obterComboCanal", e));
        } catch (TuxedoServiceCallerException e) {
            log.error("TuxedoException - OrdenarMenuFacadeImpl:gravar(" + user + ")", e);
            throw new FacadeException(e.getMessage(), e);
        }
    }

}
