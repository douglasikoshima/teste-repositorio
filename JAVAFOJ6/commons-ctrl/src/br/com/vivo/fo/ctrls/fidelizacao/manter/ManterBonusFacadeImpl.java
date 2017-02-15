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
import br.com.vivo.fo.fidelizacao.vo.BonusDelVODocument.BonusDelVO;
import br.com.vivo.fo.fidelizacao.vo.BonusPesquisaInVODocument.BonusPesquisaInVO;
import br.com.vivo.fo.fidelizacao.vo.BonusPesquisaVODocument.BonusPesquisaVO;
import br.com.vivo.fo.fidelizacao.vo.BonusVODocument.BonusVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument.ListaBonusVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ManterBonusFacade", mappedName = "ManterBonusFacade")
@Local(ManterBonusFacade.class)
@Session(ejbName = "ManterBonusFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterBonusFacadeImpl implements ManterBonusFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");
    private String            xmlIn;
    private String            xmlOut;

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getApoioBonus(String user) throws TuxedoException, FacadeException {
        FidelizacaoListaGeralVO regionais = null;
        xmlOut = ConstantesCRM.SVAZIO;
        xmlIn = ConstantesCRM.SVAZIO;
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
            log.error("XmlException - ManterBonusFacadeImpl:getApoioBonusMin(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:getApoioBonusMin", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterBonusFacadeImpl:getApoioBonusMin(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterBonusFacadeImpl:getApoioBonusMin(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaBonusVO getBonus(String user, BonusPesquisaVO bonuspVO) throws TuxedoException, FacadeException {
        ListaBonusVO lista = null;
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            xmlIn = bonuspVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            // xmlIn = "<idUfOperadora>" + parametro[0] +
            // "</idUfOperadora><nmBonus>"+ parametro[1] + "</nmBonus>";
            xmlIn = XmlManager.MakeXmlIn(user, "GETBONUS", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            lista = ListaBonusVODocument.Factory.parse(xmlOut).getListaBonusVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterBonusFacadeImpl:getBonus(" + user + bonuspVO.getIdUfOperadora() + "," + bonuspVO.getNmBonus() + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ManterBonusFacadeImpl:getBonus(" + user + ", " + bonuspVO.getIdUfOperadora() + bonuspVO.getNmBonus() + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:getBonus", xex));

        } catch (Exception ex) {
            log.error("Exception - ManterBonusFacadeImpl:getBonus(" + user + ", " + bonuspVO.getIdUfOperadora() + bonuspVO.getNmBonus() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return lista;
    }

    /**
     * @common:operation
     */
    /*
     * Alterado por PPaula 04/11/2004 Troca do tipo de retorno String para
     * RetornoVO
     */
    public RetornoVO delBonus(String user, BonusDelVO idBonus) throws FacadeException, TuxedoException {

        try {
            // Monta o log da operação se possível

            log.debug("ManterBonusFacadeImpl:dellBonus(" + user + ", " + idBonus.getIdMatrizBonus() + ")");

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;

            // xmlIn = "<idMatrizBonus>"+ id+"</idMatrizBonus>";
            xmlIn = idBonus.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlIn = XmlManager.MakeXmlIn(user, "DELBONUS", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {

            log.error("TuxedoException - ManterBonusFacadeImpl:delBonus(" + user + ", " + idBonus.getIdMatrizBonus() + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ManterBonusFacadeImpl:delBonus(" + user + ", " + idBonus.getIdMatrizBonus() + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:delBonus", xex));

        } catch (Exception ex) {
            log.error("Exception - ManterBonusFacadeImpl:delBonus(" + user + ", " + idBonus.getIdMatrizBonus() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    /*
     * Alterado por PPaula 04/11/2004 Troca do tipo de retorno String para
     * RetornoVO
     */
    public RetornoVO setBonus(String user, BonusVO bonus) throws TuxedoException, XmlException, FacadeException {
        try {

            xmlIn = bonus.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "UPDBONUS", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ManterBonusFacadeImpl:setBonus(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:setBonus", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterBonusFacadeImpl:setBonus(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterBonusFacadeImpl:setBonus(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    /*
     * Alterado por PPaula 04/11/2004 Troca do tipo de retorno String para
     * RetornoVO
     */
    public RetornoVO addBonus(String user, BonusPesquisaInVO bonusInVO) throws FacadeException, XmlException, TuxedoException {
        /**
         * Foi criado um array como parametro para que ele receba a regional,
         * validade e descricao do bonus. Onde bonus[0] - Regional bonus[1] -
         * descricao do bonus bonus[2] - validade do bonus
         **/
        // O valor bonus está com 0 porque este Façade é para manutenção de
        // bonus e neste caso não temos valor.
        try {
            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;
            // xmlIn = "<idufoperadora>"+bonus[0]+"</idufoperadora><nmbonus>"+
            // bonus[1]+"</nmbonus><qtdiasvalidade>" + bonus[2] +
            // "</qtdiasvalidade><vlbonus>0</vlbonus>";
            bonusInVO.setVlbonus(new String(ConstantesCRM.SZERO));
            xmlIn = bonusInVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "INSBONUS", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOut).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - ManterBonusFacadeImpl:addBonus(" + user + ", " + "[ " + bonusInVO.getIdufoperadora() + " , " + bonusInVO.getNmbonus() + " ," + bonusInVO.getQtdiasvalidade() + "] ) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - ManterBonusFacadeImpl:addBonus(" + user + ", " + "[ " + bonusInVO.getIdufoperadora() + " , " + bonusInVO.getNmbonus() + " ," + bonusInVO.getQtdiasvalidade() + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:addBonus", xex));

        } catch (Exception ex) {
            log.error("Exception - ManterBonusFacadeImpl:addBonus(" + user + ", " + "[ " + bonusInVO.getIdufoperadora() + " , " + bonusInVO.getNmbonus() + " ," + bonusInVO.getQtdiasvalidade() + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralDescricaoVO getTipoOfertaBonus(String user) throws TuxedoException, FacadeException {
        FidelizacaoListaGeralDescricaoVO listatipoOferta = null;
        xmlOut = ConstantesCRM.SVAZIO;
        xmlIn = ConstantesCRM.SVAZIO;
        try {
            xmlIn = "<getreg></getreg>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELTIPOOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            listatipoOferta = FidelizacaoListaGeralDescricaoVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralDescricaoVO();

            return listatipoOferta;

        } catch (XmlException ex) {
            log.error("XmlException - ManterBonusFacadeImpl:getTipoOfertaBonus(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterBonusFacadeImpl:getTipoOfertaBonus", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterBonusFacadeImpl:getTipoOfertaBonus(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterBonusFacadeImpl:getTipoOfertaBonus(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
