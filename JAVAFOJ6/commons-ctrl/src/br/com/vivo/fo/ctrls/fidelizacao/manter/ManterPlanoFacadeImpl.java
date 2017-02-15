package br.com.vivo.fo.ctrls.fidelizacao.manter;

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
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument.ListaPlanoVO;
import br.com.vivo.fo.fidelizacao.vo.PlanoPesquisaVODocument.PlanoPesquisaVO;
import br.com.vivo.fo.fidelizacao.vo.PlanoVODocument.PlanoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ManterPlanoFacade", mappedName = "ManterPlanoFacade")
@Local(ManterPlanoFacade.class)
@Session(ejbName = "ManterPlanoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterPlanoFacadeImpl implements ManterPlanoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");
    private String            xmlIn;
    private String            xmlOut;

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getRegionais(String user) throws TuxedoException, FacadeException {
        FidelizacaoListaGeralVO regionais = null;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            xmlIn = "<getreg>GETREGIONAL</getreg>";
            xmlIn = XmlManager.MakeXmlIn(user, "GETREGIONAL", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            regionais = FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

            return regionais;

        } catch (XmlException ex) {
            log.error("XmlException - ManterPlanoFacadeImpl:getRegionais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterPlanoFacadeImpl:getRegionais", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterPlanoFacadeImpl:getRegionais(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterPlanoFacadeImpl:getRegionais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaPlanoVO getPlano(String user, PlanoPesquisaVO planoPVO) throws TuxedoException, FacadeException {
        ListaPlanoVO planos;
        try {
            xmlIn = planoPVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            // xmlIn = "<idufop>" + dados[0]+ "</idufop><texto>"+ dados[1]
            // +"</texto>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELPLANO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            planos = ListaPlanoVODocument.Factory.parse(xmlOut).getListaPlanoVO();

            return planos;

        } catch (XmlException ex) {
            log.error("XmlException - ManterPlanoFacadeImpl:getPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterPlanoFacadeImpl:getPlano", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterPlanoFacadeImpl:getPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - ManterPlanoFacadeImpl:getPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO addPlano(String user, PlanoVO planoPesVO) throws TuxedoException, FacadeException {
        try {
            // xmlIn = "<idReg>"+plano[0]+"</idReg><descricao>"+ plano[1]
            // +"</descricao>";
            xmlIn = planoPesVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "INSPLANO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterPlanoFacadeImpl:addPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterPlanoFacadeImpl:addPlano", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterPlanoFacadeImpl:addPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterPlanoFacadeImpl:addPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO setPlano(String user, PlanoVO plano) throws TuxedoException, FacadeException {
        try {
            xmlIn = plano.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "UPDPLANO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterPlanoFacadeImpl:setPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterPlanoFacadeImpl:setPlano", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterPlanoFacadeImpl:setPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterPlanoFacadeImpl:setPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO delPlano(String user, String id) throws TuxedoException, FacadeException {
        try {
            xmlIn = "<id>" + id + "</id>";
            xmlIn = XmlManager.MakeXmlIn(user, "EXCPLANO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterPlanoFacadeImpl:delPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterPlanoFacadeImpl:delPlano", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterPlanoFacadeImpl:delPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterPlanoFacadeImpl:delPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
