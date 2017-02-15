package br.com.vivo.fo.ctrls.VOLTAV.manterInSid;


import java.util.StringTokenizer;

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
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.ListaFuncionalidadeInSidVODocument;
import br.com.vivo.fo.voltav.vo.ListaFuncionalidadeInSidVODocument.ListaFuncionalidadeInSidVO;
import br.com.vivo.fo.voltav.vo.ListaPesquisaInSidVODocument;
import br.com.vivo.fo.voltav.vo.ListaPesquisaInSidVODocument.ListaPesquisaInSidVO;
import br.com.vivo.fo.voltav.vo.VOLTAVListaRegionalVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVListaRegionalVODocument.VOLTAVListaRegionalVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ConfigurarInSidFacade",mappedName="ConfigurarInSidFacade")
@Local(ConfigurarInSidFacade.class)
@Session(ejbName = "ConfigurarInSidFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConfigurarInSidFacadeImpl implements ConfigurarInSidFacade {

	@EJB
	private TuxedoServiceCall tuxedo;
	
    private final static String _SERVICO = "CADASTRAINSID";
    static final long serialVersionUID = 1L;
    private Logger log = new Logger(ConstantesCRM.SVAZIO);

    /**
     * @common:operation
     */
    public VOLTAVListaRegionalVO obterRegional(String user) throws FacadeException {
        log.debug("ManterInSidFacadeImpl:obterRegional(" + user + ")");
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>consultarRegional</operacao>");

            // Executa chamada ao servico Tuxedo
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            VOLTAVListaRegionalVODocument operadoraDoc = VOLTAVListaRegionalVODocument.Factory.parse(xmlOUT);
            return operadoraDoc.getVOLTAVListaRegionalVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterInSidFacadeImpl:obterRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:incluirBanner", ex));

        } catch (Exception ex) {
            log.error("Exception - ManterInSidFacadeImpl:obterRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaFuncionalidadeInSidVO obterFuncionalidades(String user) throws FacadeException {
        log.debug("ManterInSidFacadeImpl:obterFuncionalidades(" + user + ")");
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>consultarFuncionalidade</operacao>");

            // Executa chamada ao servico Tuxedo
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            ListaFuncionalidadeInSidVODocument operadoraDoc = ListaFuncionalidadeInSidVODocument.Factory.parse(xmlOUT);
            return operadoraDoc.getListaFuncionalidadeInSidVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterInSidFacadeImpl:obterFuncionalidades(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:incluirBanner", ex);
        } catch (Exception ex) {
            log.error("Exception - ManterInSidFacadeImpl:obterFuncionalidades(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public ListaPesquisaInSidVO pesquisarInSid(String user, String idCanal, String idOperadora, String idApi) throws FacadeException {

        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>consultarPesquisaInSid</operacao>");
            sb.append("<idCanal>" + idCanal + "</idCanal>");
            sb.append("<idGrupoOperadora>" + idOperadora + "</idGrupoOperadora>");
            sb.append("<idApi>" + idApi + "</idApi>");

            log.debug("ManterInSidFacadeImpl:pesquisarInSid(" + user + ")");

            // Executa chamada ao servico Tuxedo
            String xmlIn = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();
                        
            ListaPesquisaInSidVODocument doc = ListaPesquisaInSidVODocument.Factory.parse(xmlOut);
            return doc.getListaPesquisaInSidVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterInSidFacadeImpl:pesquisarInSid(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:incluirBanner", ex);
        } catch (Exception ex) {
            log.error("Exception - ManterInSidFacadeImpl:pesquisarInSid(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }

    }

    /**
     * @common:operation
     */
    public void alterarSid(String user, String[] selecionados, String nrNewSid) throws FacadeException {

        try {
            StringBuffer sb = new StringBuffer();
            sb.append("<operacao>alterarInSid</operacao>");

            for (int i = 0; i < selecionados.length; i++) {
                StringTokenizer st = new StringTokenizer(selecionados[i], "|");
                String idCanal = st.nextToken();
                String idGrupoOperadora = st.nextToken();
                st.nextToken();  //nrInSid 
                String idApi = st.nextToken();

                sb.append("<PesquisaInSidVO>");
                sb.append("<idCanal>" + idCanal + "</idCanal>");
                sb.append("<idGrupoOperadora>" + idGrupoOperadora + "</idGrupoOperadora>");
                sb.append("<nrInSid>" + nrNewSid + "</nrInSid>");
                sb.append("<idApi>" + idApi + "</idApi>");
                sb.append("</PesquisaInSidVO>");
            }

            log.debug("ManterInSidFacadeImpl:alterarSid(" + user + ")");

            // Executa chamada ao servico Tuxedo
            String xmlIN = XmlManager.MakeXmlIn(user, _SERVICO, sb.toString());
            //(new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;
        } catch (XmlException ex) {
            log.error("XmlException - ManterInSidFacadeImpl:alterarSid(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:incluirBanner", ex);
        } catch (Exception ex) {
            log.error("Exception - ManterInSidFacadeImpl:alterarSid(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }

    }
}
