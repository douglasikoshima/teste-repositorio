package br.com.vivo.fo.ctrls.workflow.atendimentoDetalheACS;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheACSMigracaoVODocument;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheACSMigracaoVODocument.AtendimentoDetalheACSMigracaoVO;

/**
 * Responsável pela exibição dos detalhes do processo ACS.
 *
 * @modulo  Gestão de Processos
 * @usecase Atendimento Detalhe ACS
 * @author  Eric Senne
 * @version $Revision: 1.3 $
 * @CVS     $Author: a5110705 $ - $Date: 2011/05/16 13:37:15 $
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="AtendimentoDetalheACSFacade",mappedName="AtendimentoDetalheACSFacade")
@Local(AtendimentoDetalheACSFacade.class)
@Session(ejbName = "AtendimentoDetalheACSFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AtendimentoDetalheACSFacadeImpl implements AtendimentoDetalheACSFacade
{
	@EJB
	private TuxedoServiceCall tuxedo;

    static final long serialVersionUID = 1L;

    //private static transient NonCatalogLogger log = new NonCatalogLogger(AtendimentoDetalheACSFacadeImpl.class.getName());
    private static Logger log = new Logger("workflow");

    /**
     * @common:operation
     */
    public AtendimentoDetalheACSMigracaoVO obtemDetalheACS(String user, String idAtendimento) throws TuxedoException, FacadeException
    {
        try {

            //Monta o log da operação se possível
              {
                log.debug("AtendimentoDetalheACSFacadeImpl:obtemDetalheACS("
                    + user
                    + ", " + idAtendimento
                    + ")");
            }

            // Monta xml de entrada do serviço
            String xmlIN = "<idAtendimento>"+idAtendimento+"</idAtendimento>";

            // Executa chamada ao servico Tuxedo
            String  inService = TuxedoServiceBridge.getXMLRequest(user,"ATDDETALHE",xmlIN);
            String xmlOut= "";//workflowTux.GETSERVICE(inService);
            xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet=MsgDocument.Factory.parse(xmlOut);
            return AtendimentoDetalheACSMigracaoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoDetalheACSMigracaoVO();

        }
        catch(Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - AtendimentoDetalheACSFacadeImpl:obtemDetalheACS(" + user + "," + idAtendimento + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw(new FacadeException("Erro na montagem do XML de entrada: AtendimentoDetalheACSFacadeImpl:obtemDetalheACS", ex));
        }
    }
}
