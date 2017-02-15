package br.com.vivo.fo.ctrls.workflow.RouterService;

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
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "Router", mappedName = "Router")
@Local(Router.class)
@Session(ejbName = "Router", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RouterImpl implements Router {

    @EJB
    private TuxedoServiceCall   tuxedo;

    private static Logger       log                    = new Logger("workflow");

    private static final String CT_SCRIPTTYPE_BODY     = "B";
    private static final String CT_SCRIPTTYPE_DATABASE = "D";

    /**
     * M�todo respons�vel por montar o xml de chamada do servi�o tuxedo SSKlunk -> RouterSvc por script body
     * @author Alexandre Nunes
     * @version 1.0
     * @param user
     *            O nome do usu�rio respons�vel pela execu��o do servi�o script O Script a ser executado xml O xml-body
     *            a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo servi�o tuxedo
     * @throws TuxedoException
     *             � um erro retornado pelo servi�o tuxedo ou montado dinamicamente pelo met�do TrataCodigoExecucao()
     * @see executar
     * @common:operation
     */
    public String executarScriptXMLBody(String user, String script, String xmlBody) throws TuxedoException, FacadeException {

        if (script == null) {
            throw new FacadeException("Para executar o script � necessario informar o seu conte�do [" + script + "], Opera��o Cancelada!");
        }

        return executar(user, CT_SCRIPTTYPE_BODY, 0, script, xmlBody);
    }

    /**
     * M�todo respons�vel por montar o xml de chamada do servi�o tuxedo SSKlunk -> RouterSvc pelo script disponivel no
     * banco de dados
     * @author Alexandre Nunes
     * @version 1.0
     * @param user
     *            O nome do usu�rio respons�vel pela execu��o do servi�o script O Script a ser executado xml O xml-body
     *            a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo servi�o tuxedo
     * @throws TuxedoException
     *             � um erro retornado pelo servi�o tuxedo ou montado dinamicamente pelo met�do TrataCodigoExecucao()
     * @see executar
     * @common:operation
     */
    public String executarScriptXMLDB(String user, int scriptDBID, String xmlBody) throws TuxedoException, FacadeException {

        if (scriptDBID < 0) {
            throw new FacadeException("Para executar o script pelo banco de dados � necess�rio informa o seu id [" + scriptDBID + "], Opera��o Cancelada!");
        }

        return executar(user, CT_SCRIPTTYPE_DATABASE, scriptDBID, null, xmlBody);
    }

    /**
     * M�todo respons�vel por montar o xml de chamada do servi�o tuxedo SSKlunk -> RouterSvc
     * @author Alexandre Nunes
     * @version 1.0
     * @param user
     *            O nome do usu�rio respons�vel pela execu��o do servi�o scriptType O tipo do script a ser executado <-
     *            Obrigatorio scriptDBID O codigo do script para ser buscado no banco de dados pelo SSKlunk script O
     *            Script a ser executado xml O xml-body a ser enviado para o script executar <- Obrigatorio
     * @return O string contendo o xmlOUT retornado pelo servi�o tuxedo
     * @throws TuxedoException
     *             � um erro retornado pelo servi�o tuxedo ou montado dinamicamente pelo met�do TrataCodigoExecucao()
     */
    private String executar(String user, String scriptType, int scriptDBID, String script, String xmlBody) throws TuxedoException, FacadeException {

        try {
            StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
            sb.append("RouterImpl:executarScriptXML(").append(user).append(", ").append(scriptType).append(", ").append(script).append(", ").append(xmlBody).append(") ");
            log.debug(sb.toString());

            if (scriptType == null) {
                throw new FacadeException("Para executar o servi�o script � necess�rio informar o tipo [" + scriptType + "], Opera��o Cancelada!");
            }

            StringBuffer xmlIN = new StringBuffer();
            xmlIN.append("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
            xmlIN.append("<msg>");
            xmlIN.append("	<msgHdr>");

            // Verifica se foi informado o usu�rio que rodar� o script
            if (user == null) {
                xmlIN.append("		<user/>");
            } else {
                xmlIN.append("		<user>");
                xmlIN.append(user);
                xmlIN.append("</user>");
            }

            xmlIN.append("		<service>SSKlunk10</service>");
            xmlIN.append("	</msgHdr>");
            xmlIN.append("	<msgBody>");

            // Verfica se existe o source do script )
            if (script != null) {
                xmlIN.append("		<script type=\"");
                xmlIN.append(scriptType);
                xmlIN.append("\">");
                xmlIN.append("			<![CDATA[");
                xmlIN.append(script);
                xmlIN.append("			]]>");
                xmlIN.append("		</script>");

            } else {
                xmlIN.append("		<script type=\"");
                xmlIN.append(scriptType);
                xmlIN.append("\" dbid=\"");
                xmlIN.append(scriptDBID);
                xmlIN.append("\"/>");
            }

            xmlIN.append("		<rsBody>");
            xmlIN.append((xmlBody != null ? xmlBody : ""));
            xmlIN.append("		</rsBody>");
            xmlIN.append("	</msgBody>");
            xmlIN.append("</msg>");

            return executar(xmlIN.toString());

        } catch (TuxedoException tx) {
            log.error("TuxedoException - RouterImpl:executar(" + user + ", " + scriptType + ", String script, String xmlBody) - [" + tx.getMessage() + "]");
            throw (tx);

        } catch (Exception ex) {
            log.error("Exception - RouterImpl:executar(" + user + ", " + scriptType + ", String script, String xmlBody) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * M�todo respons�vel por executar um script klunk armazenado em um arquivo xml
     * @author Alexandre Nunes
     * @version 1.0
     * @param xmlIN
     *            O String xmlIN a ser disparado para o servi�o de gerenciamento de script ssklunk
     * @return O string contendo o xmlOUT retornado pelo servi�o tuxedo
     * @throws TuxedoException
     *             � um erro retornado pelo servi�o tuxedo ou montado dinamicamente pelo met�do TrataCodigoExecucao()
     *             FacadeException � um erro interno do sistema vivoNET
     */
    private String executar(String xmlIN) throws TuxedoException, FacadeException {

        try {
            StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
            sb.append("RouterImpl:executar(").append(xmlIN).append(")");
            log.debug(sb.toString());

            return tuxedo.callService("TuxConnector", xmlIN);

        } catch (Exception ex) {
            log.error("Exception - RouterImpl:executar(String xmlIN) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
