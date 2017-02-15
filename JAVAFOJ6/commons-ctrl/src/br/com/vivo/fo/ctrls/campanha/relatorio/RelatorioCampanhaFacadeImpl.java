package br.com.vivo.fo.ctrls.campanha.relatorio;

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
import br.com.vivo.fo.campanha.vo.AreasRegistroVODocument;
import br.com.vivo.fo.campanha.vo.AreasRegistroVODocument.AreasRegistroVO;
import br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO;
import br.com.vivo.fo.campanha.vo.RelArquivoRespostasVODocument;
import br.com.vivo.fo.campanha.vo.RelArquivoRespostasVODocument.RelArquivoRespostasVO;
import br.com.vivo.fo.campanha.vo.RelArquivoResultadoVODocument;
import br.com.vivo.fo.campanha.vo.RelArquivoResultadoVODocument.RelArquivoResultadoVO;
import br.com.vivo.fo.campanha.vo.RelEfetividadeVODocument;
import br.com.vivo.fo.campanha.vo.RelEfetividadeVODocument.RelEfetividadeVO;
import br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument;
import br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO;
import br.com.vivo.fo.campanha.vo.RelatorioPercentualVODocument;
import br.com.vivo.fo.campanha.vo.RelatorioPercentualVODocument.RelatorioPercentualVO;
import br.com.vivo.fo.campanha.vo.RelatorioRespostasVODocument;
import br.com.vivo.fo.campanha.vo.RelatorioRespostasVODocument.RelatorioRespostasVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RelatorioCampanhaFacade", mappedName = "RelatorioCampanhaFacade")
@Local(RelatorioCampanhaFacade.class)
@Session(ejbName = "RelatorioCampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatorioCampanhaFacadeImpl implements RelatorioCampanhaFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final transient static Logger log = new Logger("campanha");

    /**
     * @common:operation
     */
    public CampanhaRelatorioVO getRelatorioGere(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {

            log.debug("RelatorioCampanhaFacadeImpl:getRelatorioGere(" + user + ")");

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            CampanhaRelatorioVODocument doc = CampanhaRelatorioVODocument.Factory.parse(xmlOUT);
            CampanhaRelatorioVO vo = doc.getCampanhaRelatorioVO();

            return vo;

        } catch (Exception ex) {
            log.error("Exception - RelatorioCampanhaFacadeImpl:getRelatorioGere(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public CampanhaRelatorioOperadorVO getRelaOperador(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {
            log.debug("RelatorioCampanhaFacadeImpl:getRelaOperador(" + user + ")");

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            CampanhaRelatorioOperadorVODocument doc = CampanhaRelatorioOperadorVODocument.Factory.parse(xmlOUT);
            CampanhaRelatorioOperadorVO vo = doc.getCampanhaRelatorioOperadorVO();

            return vo;

        } catch (Exception ex) {
            log.error("Exception - RelatorioCampanhaFacadeImpl:getRelaOperador(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public RelatorioPercentualVO getRelaPercentual(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {
            log.debug("RelatorioCampanhaFacadeImpl:getRelaPercentual(" + user + ")");

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            RelatorioPercentualVODocument doc = RelatorioPercentualVODocument.Factory.parse(xmlOUT);
            RelatorioPercentualVO per = doc.getRelatorioPercentualVO();

            return per;

        } catch (Exception ex) {
            log.error("RelatorioCampanhaFacadeImpl:getRelaPercentual(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public RelatorioRespostasVO getRelaRespostas(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {

            log.debug("RelatorioCampanhaFacadeImpl:getRelaRespostas(" + user + ")");

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            RelatorioRespostasVODocument doc = RelatorioRespostasVODocument.Factory.parse(xmlOUT);
            RelatorioRespostasVO per = doc.getRelatorioRespostasVO();

            return per;

        } catch (Exception ex) {
            log.error("RelatorioCampanhaFacadeImpl:getRelaRespostas(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public RelEfetividadeVO getRelaEfetividade(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {
            // Monta o log da operação se possível
            log.debug("RelatorioCampanhaFacadeImpl:getRelaEfetividade(" + user + ")");

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            // String xmlOUT =
            // SimuladorTuxedo.Read("D:\\temp\\xml\\RefEfetividadeVO.xml");
            RelEfetividadeVODocument doc = RelEfetividadeVODocument.Factory.parse(xmlOUT);
            RelEfetividadeVO efe = doc.getRelEfetividadeVO();

            return efe;

        } catch (Exception ex) {
            log.error("RelatorioCampanhaFacadeImpl:getRelaEfetividade(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public RelaAgendaAniversarioVO getRelaAgendaAniv(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {
            log.debug("RelatorioCampanhaFacadeImpl:getRelaAgendaAniv(" + user + ")");

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            RelaAgendaAniversarioVODocument doc = RelaAgendaAniversarioVODocument.Factory.parse(xmlOUT);
            RelaAgendaAniversarioVO efe = doc.getRelaAgendaAniversarioVO();

            return efe;

        } catch (Exception ex) {
            log.error("RelatorioCampanhaFacadeImpl:getRelaAgendaAniv(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public UsuariosVO getUFOperadora(String user) throws Exception {

        try {
            log.debug("RelatorioCampanhaFacadeImpl:getUFOperadora(" + user + ")");

            String xmlIN = "<UF>" + 1 + "</UF>";
            xmlIN = XmlManager.MakeXmlIn(user, "UsrLista", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            UsuariosVODocument doc = UsuariosVODocument.Factory.parse(xmlOUT);
            UsuariosVO efe = doc.getUsuariosVO();

            return efe;

        } catch (Exception ex) {
            log.error("RelatorioCampanhaFacadeImpl:getUFOperadora(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;
        }
    }

    /**
     * @common:operation
     */
    public RelArquivoResultadoVO getArqResult(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,
                               // "RELGERADOR", xmlIN));
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RelArquivoResultadoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRelArquivoResultadoVO();

        } catch (Exception ex) {

            log.error("RelatorioCampanhaFacadeImpl:getArqResult(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;

        }

    }

    /**
     * @common:operation
     */
    public RelArquivoRespostasVO getArqResp(String user, CampanhaFiltroRelatorioVO filtroRelatorio) throws Exception {

        try {

            String xmlIN = filtroRelatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELGERADOR", xmlIN);

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,
                               // "RELGERADOR", xmlIN));

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RelArquivoRespostasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRelArquivoRespostasVO();

        } catch (Exception ex) {

            log.error("RelatorioCampanhaFacadeImpl:getArqResp(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;

        }

    }

    /**
     * @common:operation
     */
    public AreasRegistroVO getAreaRegistro(String user, String idUFOperadora) throws Exception {

        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idUFOperadora>");
            xmlINBuffer.append(idUFOperadora);
            xmlINBuffer.append("</idUFOperadora>");

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,
                               // "GETAREAREG", xmlINBuffer.toString()));

            xmlOUT = tuxedo.callService("TuxConnector", xmlINBuffer.toString());

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return AreasRegistroVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAreasRegistroVO();

        } catch (Exception ex) {

            log.error("RelatorioCampanhaFacadeImpl:getAreaRegistro(" + user + ") - [" + ex.getMessage() + "]");
            throw ex;

        }

    }

}
