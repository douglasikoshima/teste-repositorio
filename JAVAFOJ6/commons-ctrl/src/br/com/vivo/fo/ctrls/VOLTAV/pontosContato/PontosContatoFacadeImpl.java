package br.com.vivo.fo.ctrls.VOLTAV.pontosContato;

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
import br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db.RelatorioContatoDB.PontoContato;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.PontosContatoVODocument;
import br.com.vivo.fo.voltav.vo.PontosContatoVODocument.PontosContatoVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="PontosContatoFacade",mappedName="PontosContatoFacade")
@Local(PontosContatoFacade.class)
@Session(ejbName = "PontosContatoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PontosContatoFacadeImpl implements PontosContatoFacade {

    private static final long serialVersionUID = 1L;
    /**
     * @common:control
     */
    private br.com.vivo.fo.ctrls.VOLTAV.pontosContato.db.RelatorioContatoDB relatorioContatoDB;
   
    @EJB
    private TuxedoServiceCall tuxedo;
    
    private String xmlIN = ConstantesCRM.SVAZIO;
    private String xmlOUT = ConstantesCRM.SVAZIO;
    private final static transient Logger log = new Logger("vol");

    /**
     * @common:operation
     */
    public PontosContatoVO consultarProcessamento(String user) throws TuxedoException, FacadeException {

        try {

            PontosContatoVO pontosContatoVO = PontosContatoVO.Factory.newInstance();
            log.debug("PontosContatoFacadeImpl:consultarProcessamento(" + user + ")");

            xmlIN = ConstantesCRM.SVAZIO;
            xmlIN = XmlManager.MakeXmlIn(user, "STATUSPTOCNT", xmlIN);
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            pontosContatoVO = PontosContatoVODocument.Factory.parse(xmlOUT).getPontosContatoVO();

            return pontosContatoVO;

        } catch (XmlException ex) {
            log.error("XmlException - PontosContatoFacadeImpl:consultarProcessamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PontosContatoFacadeImpl:consultarProcessamento", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - PontosContatoFacadeImpl:consultarProcessamento(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - PontosContatoFacadeImpl:consultarProcessamento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PontoContato[] consultarPontosContato(String cdCnpjEmpresa) throws Exception {
        return relatorioContatoDB.getRelatorioContato(cdCnpjEmpresa);
    }

    /**
     * @common:operation
     */
    public PontoContato[] consultarPontosContatoPorCnpj(String cdCnpjEmpresa) throws Exception {
        return relatorioContatoDB.getRelatorioContatoPorCnpj(cdCnpjEmpresa);
    }

    /**
     * @common:operation
     */
    public PontoContato[] consultarPontosContatoCompleto(int pageInicial, int pageFinal) throws Exception {
        return relatorioContatoDB.getRelatorioContatoCompleto(pageInicial, pageFinal);
    }
}
