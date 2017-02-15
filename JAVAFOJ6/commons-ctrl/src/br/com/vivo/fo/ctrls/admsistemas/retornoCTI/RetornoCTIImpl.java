package br.com.vivo.fo.ctrls.admsistemas.retornoCTI;

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
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument.RetornoWFCTIResultadoVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name="RetornoCTI",mappedName="RetornoCTI")
@Local(RetornoCTI.class)
@Session(ejbName = "RetornoCTI", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RetornoCTIImpl implements RetornoCTI {

	@EJB
	private TuxedoServiceCall tuxedo;

    private static Logger log = new Logger("admsistemas");

    /**
     * Método responsável por iniciar a montagem da tela de retorno cti com a pesquisa informada
     *
     * @author  Alexandre Nunes
     * @version 1.0
     * @param  user String com o código do usuário logado na aplicação
     * @return RetornoWFCTIResultadoVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     *
     * @common:operation
     */
    public RetornoWFCTIResultadoVO pesquisar(String user, RetornoWFCTIVO retornoWFCTIVO) throws TuxedoException, FacadeException {
        try {
            log.debug("RetornoCTIImpl:pesquisar(" + user + ", " + retornoWFCTIVO + ")");

            StringBuffer xmlIN = new StringBuffer();

            if( retornoWFCTIVO.getIdRetornoWFCTI().trim().length() > 0 ) {
                xmlIN.append("<idRetornoWFCTI>").append( retornoWFCTIVO.getIdRetornoWFCTI() ).append("</idRetornoWFCTI>");
            }

            if( retornoWFCTIVO.getDsRetornoWFCTI().trim().length() > 0  ) {
                xmlIN.append("<dsRetornoWFCTI>").append( retornoWFCTIVO.getDsRetornoWFCTI() ).append("</dsRetornoWFCTI>");
            }

            if( ! retornoWFCTIVO.getSgStatus().equalsIgnoreCase("T") ) {
                xmlIN.append("<sgStatus>").append( retornoWFCTIVO.getSgStatus() ).append("</sgStatus>");
            }

            String xmlIn = XmlManager.MakeXmlIn(user, "WFPSQINBOXRET", xmlIN.toString());
            // String xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            RetornoWFCTIResultadoVODocument doc = RetornoWFCTIResultadoVODocument.Factory.parse(xmlOUT);

            return doc.getRetornoWFCTIResultadoVO();

        } catch(Exception ex) {
            log.error("Exception - RetornoCTIImpl:pesquisar(" + user + ", " + retornoWFCTIVO + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: RetornoCTIImpl:pesquisar", ex));
        }
    }

    /**
     * Método responsável por iniciar a consulta/edição de um registro do retorno cti
     *
     * @author  Alexandre Nunes
     * @version 1.0
     * @param  user String com o código do usuário logado na aplicação
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     *
     * @common:operation
     */
    public RetornoWFCTIVO consultarRetornoWFCTIVO(String user, RetornoWFCTIVO retornoWFCTIVO) throws TuxedoException, FacadeException {
        try {
            log.debug("RetornoCTIImpl:consultarRetornoWFCTIVO(" + user + ", " + retornoWFCTIVO + ")");

            StringBuffer xmlIN = new StringBuffer();
            xmlIN.append("<idRetornoWFCTI>").append( retornoWFCTIVO.getIdRetornoWFCTI()).append("</idRetornoWFCTI>");

            String xmlIn = XmlManager.MakeXmlIn(user, "WFCNSINBOXRET", xmlIN.toString());
            // String xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            RetornoWFCTIVODocument doc = RetornoWFCTIVODocument.Factory.parse(xmlOUT);

            return doc.getRetornoWFCTIVO();

        } catch(Exception ex) {
            log.error("Exception - RetornoCTIImpl:consultarRetornoWFCTIVO(" + user + ", " + retornoWFCTIVO + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: RetornoCTIImpl:consultarRetornoWFCTIVO", ex));
        }
    }

     /**
     * Método responsável por iniciar a consulta/edição de um registro do retorno cti
     *
     * @author  Denys Sene
     * @version 1.0
     * @param  user String com o código do usuário logado na aplicação
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     *
     * @common:operation
     */
    public RetornoWFCTIVO carregarGrupos(String user, RetornoWFCTIVO retornoWFCTIVO) throws TuxedoException, FacadeException {
        try {
            log.debug("RetornoCTIImpl:carregarGrupos(" + user + ", " + retornoWFCTIVO + ")");

            StringBuffer xmlIN = new StringBuffer();

            xmlIN.append("<idRetornoWFCTI>");
            if (retornoWFCTIVO.getIdRetornoWFCTI() != null) {
                xmlIN.append( retornoWFCTIVO.getIdRetornoWFCTI() );
            }

            xmlIN.append("</idRetornoWFCTI>");
            xmlIN.append("<inPadrao>");
            xmlIN.append( retornoWFCTIVO.getInPadrao() );
            xmlIN.append("</inPadrao>");

            String xmlIn = XmlManager.MakeXmlIn(user, "WFPSQGRPCTI", xmlIN.toString());
            // String xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            RetornoWFCTIVODocument doc  = RetornoWFCTIVODocument.Factory.parse(xmlOUT);

            return doc.getRetornoWFCTIVO();

        } catch(Exception ex) {
            log.error("Exception - RetornoCTIImpl:carregarGrupos(" + user + ", " + retornoWFCTIVO + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: RetornoCTIImpl:carregarGrupos", ex));
        }
    }

    /**
     * Método responsável por gerneciar os processos de: inclusão/alteração/exclusão conforme o metodo informado
     *
     * @author  Alexandre Nunes
     * @version 1.0
     * @param  user String com o código do usuário logado na aplicação
     * @param  operacao int com o codigo da operaçao a realizar 0=Inclusão; 1=Alteração; 2=Exclusao
     * @param  retornoWFCTIVO a classe com o conteudo a ser sensibilizado no banco de dados
     * @return RetornoWFCTIVO Classe com o retorno obtido do parse xml-in
     * @throws TuxedoException É um erro retornado pelo serviço tuxedo
     * @throws AssemblerException É um erro retornado pelo mecânismo de assembler
     * @throws FacadeException É um erro retornado no caso de erros desconhecidos
     *
     * @common:operation
     */
    public RetornoWFCTIVO gravarRetornoWFCTIVO(String user, int operacao, RetornoWFCTIVO retornoWFCTIVO) throws TuxedoException, FacadeException {

        try {
            log.debug("RetornoCTIImpl:gravarRetortnoWFCTIVO(" + user + ", " + operacao + ", " + retornoWFCTIVO + ")");

            String xml = retornoWFCTIVO.toString().replaceAll("vo:", ConstantesCRM.SVAZIO);
            
            
            StringBuffer xmlIN = new StringBuffer();
            xmlIN.append("<operacao>").append( operacao ).append("</operacao>");
            xmlIN.append(xml);

            String xmlIn = XmlManager.MakeXmlIn(user, "WFGRVCTIRET", xmlIN.toString());
            
            log.debug("RetornoCTIImpl:gravarRetortnoWFCTIVO(xmlIn: " + xmlIn + ")");
            
            // String xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIn);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            //Retorna informação se necessário
            if( (xmlOUT != null) && (xmlOUT.length() > 0) && !"<xml-fragment/>".equals(xmlOUT.trim()) ) {
                RetornoWFCTIVODocument doc = RetornoWFCTIVODocument.Factory.parse(xmlOUT);
                return doc.getRetornoWFCTIVO();
            } else {
                return null;
            }

        } catch(Exception ex) {
            log.error("Exception - RetornoCTIImpl:gravarRetornoWFCTIVO(" + user + ", " + operacao + ", " + retornoWFCTIVO + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: RetornoCTIImpl:gravarRetornoWFCTIVO", ex));
        }
    }
}