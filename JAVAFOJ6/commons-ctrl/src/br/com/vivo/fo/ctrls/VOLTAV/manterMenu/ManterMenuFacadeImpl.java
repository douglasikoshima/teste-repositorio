package br.com.vivo.fo.ctrls.VOLTAV.manterMenu;


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
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.VOLTAVManterMenuVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVManterMenuVODocument.VOLTAVManterMenuVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ManterMenuFacade",mappedName="ManterMenuFacade")
@Local(ManterMenuFacade.class)
@Session(ejbName = "ManterMenuFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterMenuFacadeImpl implements ManterMenuFacade {

	@EJB
	private TuxedoServiceCall tuxedo;
	
    static final long serialVersionUID = 1L;
    private String xmlIN;
    private String xmlOUT;
    private Logger log = new Logger(ConstantesCRM.SVAZIO);

    /**
     * @common:operation
     */
    public VOLTAVManterMenuVO getManterMenu(String xml, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ManterMenuFacadeImpl:getManterMenu(" + user + ")");

            xmlIN = xml;//"<operacao>consultarFiltros</operacao>";

            xmlIN = XmlManager.MakeXmlIn(user, "DADMENU", xmlIN);

            // Executa chamada ao servico Tuxedo
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            //xmlOUT = SimuladorTuxedo.Read("D:\\tmp\\TAVManterMenuVO.xml");
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            VOLTAVManterMenuVO tavManterMenuVO = VOLTAVManterMenuVODocument.Factory.parse(xmlOUT).getVOLTAVManterMenuVO();

            return tavManterMenuVO;

        } catch (XmlException ex) {
            //Monta mensagem de erro
            log.error("XmlException - ManterMenuFacadeImpl:getManterMenu(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:getManterMenu", ex));

        } catch (TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - ManterMenuFacadeImpl:getManterMenu(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - ManterMenuFacadeImpl:getManterMenu(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException(ex));
        }
    }
}
