package br.com.vivo.fo.ctrls.workflow.indicadores;

import java.util.Hashtable;

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
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFIndicadoresPesquisaVODocument;
import br.com.vivo.fo.workflow.vo.WFIndicadoresPesquisaVODocument.WFIndicadoresPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument;
import br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="IndicadoresFacade",mappedName="IndicadoresFacade")
@Local(IndicadoresFacade.class)
@Session(ejbName = "IndicadoresFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class IndicadoresFacadeImpl implements IndicadoresFacade {
	
	@EJB
	private TuxedoServiceCall tuxedo;

	private static final long serialVersionUID = 5279324697081042319L;

    private static Logger log = new Logger("workflow");

    private static Hashtable htTiposRelatoriosIndicadores;

    /**
     * @common:operation
     */
    public WFIndicadoresPesquisaVO obtemWFIndicadoresPesquisaVO(String user) throws TuxedoException, FacadeException {
        try {

            //Monta o log da operação se possível
            log.debug("IndicadoresFacadeImpl:obtemWFIndicadoresPesquisaVO("
                + user
                + ")");

            // chamada ao simulador tuxedo
            //String xmlOut = SimuladorTuxedo.Read(ConstantesCRM.pathXML + "obtemWFIndicadoresPesquisaVO.xml");
            //String xmlOut = routerTuxControl.executarScriptXMLDB(user, ConstantesCRM.CT_WF_SCRIPTDB_INDICADORES_GRUPOS, xmlIn);

            String xmlOut = ConstantesCRM.SVAZIO;//workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDPESQGRPREG",""));
            xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDPESQGRPREG",""));

            // monta o objeto com a classe assembler
            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
            
            //WFIndicadoresPesquisaVO wFIndicadoresPesquisaVO = new IndicadoresAssembler().montarWFIndicadoresPesquisaVO(xmlOut);
            return WFIndicadoresPesquisaVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFIndicadoresPesquisaVO();

        } catch(Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - IndicadoresFacadeImpl:obtemWFIndicadoresPesquisaVO(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw(new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public WFIndicadoresVO obtemWFIndicadoresVO(String user, String tipo, WFIndicadoresPesquisaVO filtro) throws TuxedoException, FacadeException {
        try {

            //Monta o log da operação se possível
              {
                log.debug("IndicadoresFacadeImpl:obtemWFIndicadoresVO("
                    + user
                    + ")");
            }

            if (htTiposRelatoriosIndicadores == null) {
                htTiposRelatoriosIndicadores = new Hashtable();
                htTiposRelatoriosIndicadores.put("1","WFATDRELINDTPR");
                htTiposRelatoriosIndicadores.put("2","WFATDRELINDNCL");
                htTiposRelatoriosIndicadores.put("3","WFATDRELINDSCA");
                htTiposRelatoriosIndicadores.put("4","WFATDRELINDESL");
            }

            String nmServico = (String)htTiposRelatoriosIndicadores.get(tipo);

            // Monta XML com os parâmetros de pesquisa
            String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:",ConstantesCRM.SVAZIO);

            // chamada ao simulador tuxedo
            //String xmlOut = SimuladorTuxedo.Read(ConstantesCRM.pathXML + "obtemWFIndicadoresVO-"+indicadoresService+".xml");
            //String xmlOut = routerTuxControl.executarScriptXMLDB(user, indicadoresService, xmlIn);

            //Monta XML IN de acordo com o serviço a ser executado
            xmlIn = XmlManager.MakeXmlIn(user, nmServico, xmlIn);
            //String xmlOut = (new ControlTuxedoCall()).execute(this, workflowTux, "GETSERVICE", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();
            
            // monta o objeto com a classe assembler
            return WFIndicadoresVODocument.Factory.parse(xmlOut).getWFIndicadoresVO();

            //WFIndicadoresVO wFIndicadoresVO = new IndicadoresAssembler().montarWFIndicadoresVO(xmlOut);

            //return wFIndicadoresVO;

        } catch(TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - IndicadoresFacadeImpl:obtemWFIndicadoresVO(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);
        }catch(Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - IndicadoresFacadeImpl:obtemWFIndicadoresVO(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw(new FacadeException("Erro na montagem do XML de entrada: IndicadoresFacadeImpl:obtemWFIndicadoresVO", ex));
        }

    }


    /**
     * @common:operation
     */
    public WFIndicadoresVO gerarResumoAcompanhamento(String user) throws TuxedoException, FacadeException {
        try {

            log.debug("IndicadoresFacadeImpl:gerarResumoAcompanhamento("
            + user
            + ")");

            String  inService = TuxedoServiceBridge.getXMLRequest(user,"WFATDRELINDRAC",ConstantesCRM.SVAZIO);
            String xmlOut = ConstantesCRM.SVAZIO;//workflowTux.GETSERVICE(inService);
            xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet=MsgDocument.Factory.parse(xmlOut);

            return WFIndicadoresVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFIndicadoresVO();

        } catch(Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - IndicadoresFacadeImpl:gerarResumoAcompanhamento(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw(new FacadeException(ex));
        }

    }

}
