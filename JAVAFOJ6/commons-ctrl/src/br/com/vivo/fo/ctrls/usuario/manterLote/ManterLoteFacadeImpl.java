package br.com.vivo.fo.ctrls.usuario.manterLote;

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
import br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument;
import br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ManterLoteFacade",mappedName="ManterLoteFacade")
@Local(ManterLoteFacade.class)
@Session(ejbName = "ManterLoteFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterLoteFacadeImpl implements ManterLoteFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

    static final long serialVersionUID = 1L;
    private String xmlIN;
    private String xmlOUT;

    private static Logger log = new Logger("usuario");

    private ListaIdDescricaoVO listaIdDescricaoVO;

    /**
     * @common:operation
     */
    public ListaIdDescricaoVO getListaIdDescricaoVO(String user) throws TuxedoException, FacadeException {

        try {

            log.debug("ManterLoteFacadeImpl:getListaIdDescricaoVO(" + user + ")" );
            xmlIN = XmlManager.MakeXmlIn(user, "GETSTATUSUSU", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            listaIdDescricaoVO = ListaIdDescricaoVODocument.Factory.parse(xmlOUT).getListaIdDescricaoVO();

    		return listaIdDescricaoVO;

        } catch(XmlException ex) {
            log.error("XmlException - ManterLoteFacadeImpl:getListaIdDescricaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: ManterLoteFacadeImpl:getListaIdDescricaoVO", ex));
        
        } catch(TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterLoteFacadeImpl:getListaIdDescricaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        
        } catch(Exception ex) {
            log.error("Exception - ManterLoteFacadeImpl:getListaIdDescricaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));
        }
    }
}