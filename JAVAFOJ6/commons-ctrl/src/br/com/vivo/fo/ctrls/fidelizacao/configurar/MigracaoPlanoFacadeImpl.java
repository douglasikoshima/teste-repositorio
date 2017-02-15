package br.com.vivo.fo.ctrls.fidelizacao.configurar;

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
import br.com.vivo.fo.fidelizacao.vo.BonusPesquisaVODocument.BonusPesquisaVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument.ListaMigracaoVO;
import br.com.vivo.fo.fidelizacao.vo.MigracaoInVODocument.MigracaoInVO;
import br.com.vivo.fo.fidelizacao.vo.MigracaoVODocument.MigracaoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "MigracaoPlanoFacade", mappedName = "MigracaoPlanoFacade")
@Local(MigracaoPlanoFacade.class)
@Session(ejbName = "MigracaoPlanoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MigracaoPlanoFacadeImpl implements MigracaoPlanoFacade {

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
            log.error("XmlException - MigracaoPlanoFacadeImpl:getRegionais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MigracaoPlanoFacadeImpl:getRegionais", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MigracaoPlanoFacadeImpl:getRegionais(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            // Monta mensagem de erro
            log.error("Exception - MigracaoPlanoFacadeImpl:getRegionais(" + user + ") - [" + ex.getMessage() + "]");
            // Lança exceção
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public ListaMigracaoVO getMigracaoPlano(String user, BonusPesquisaVO bonusPVO) throws TuxedoException, FacadeException {
        ListaMigracaoVO lista = null;

        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;

        try {
            // xmlIn = "<idUfOperadora>" + parametro[0]+
            // "</idUfOperadora><nmBonus>"+ parametro[1] + "</nmBonus>";
            xmlIn = bonusPVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "SELMIGRACAO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            lista = ListaMigracaoVODocument.Factory.parse(xmlOut).getListaMigracaoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - MigracaoPlanoFacadeImpl:getMigracaoPlano(" + user + ", " + bonusPVO.getIdUfOperadora() + " ," + bonusPVO.getNmBonus() + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {

            log.error("XmlException - MigracaoPlanoFacadeImpl:getMigracaoPlano(" + user + ", " + bonusPVO.getIdUfOperadora() + "," + bonusPVO.getNmBonus() + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MigracaoPlanoFacadeImpl:getMigracaoPlano", xex));

        } catch (Exception ex) {

            log.error("Exception- MigracaoPlanoFacadeImpl:getMigracaoPlano(" + user + ", " + bonusPVO.getIdUfOperadora() + "," + bonusPVO.getNmBonus() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

        return lista;
    }

    /**
     * @common:operation
     */
    public void delMigracaoPlano(String user, String id) throws FacadeException, TuxedoException {

        try {
            // Monta o log da operação se possível

            log.debug("BonusFacadeImpl:delMigracao(" + user + ", " + id + ")");

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;

            xmlIn = "<idMatrizBonus>" + id + "</idMatrizBonus>";
            xmlIn = XmlManager.MakeXmlIn(user, "EXCMIGRACAO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException tex) {

            log.error("TuxedoException - MigracaoPlanoFacadeImpl:delMigracao(" + user + ", " + id + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - MigracaoPlanoFacadeImpl:delMigracao(" + user + ", " + id + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MigracaoPlanoFacadeImpl:delMigracao", xex));

        } catch (Exception ex) {

            log.error("Exception - MigracaoPlanoFacadeImpl:delMigracao(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public String setMigracaoPlano(String user, MigracaoVO migracao) throws TuxedoException, FacadeException {
        xmlIn = ConstantesCRM.SVAZIO;
        xmlOut = ConstantesCRM.SVAZIO;

        try {

            xmlIn = migracao.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "UPDMIGRACAO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return (xmlOut);

        } catch (XmlException ex) {
            log.error("XmlException - MigracaoPlanoFacadeImpl:setMigracaoPlano(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MigracaoPlanoFacadeImpl:setMigracaoPlano", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MigracaoPlanoFacadeImpl:setMigracaoPlano(" + user + ") - [" + ex.getMessage() + "]");

            if (ex.getLocalizedMessage().lastIndexOf("Existe Migracao Cadastrada!") > 0) {
                return (ex.getLocalizedMessage());
            } else {
                // Lança exceção
                throw new TuxedoException(ex);
            }

        } catch (Exception ex) {
            // Monta mensagem de erro
            log.error("Exception - MigracaoPlanoFacadeImpl:setMigracaoPlano(" + user + ") - [" + ex.getMessage() + "]");
            // Lança exceção
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addMigracaoPlano(String user, MigracaoInVO migacaoInVO) throws FacadeException, XmlException, TuxedoException {
        /**
         * Foi criado um array como parametro para que ele receba a regional,
         * validade e descricao do bonus. Onde migracao[0] - Regional
         * migracao[1] - descricao migracao[2] - validade migracao[3] - valor
         **/
        try {

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;

            // xmlIn = "<idUfOperadora>"+
            // migracaoPlano[0]+"</idUfOperadora><descricao>"+
            // migracaoPlano[1]+"</descricao><vlBonus>" + migracaoPlano[2] +
            // "</vlBonus><qtDiasValidade>" + migracaoPlano[3] +
            // "</qtDiasValidade>";
            xmlIn = migacaoInVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "INSMIGRACAO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - MigracaoPlanoFacadeImpl:addMigracaoPlano(" + user + ", " + "[ " + migacaoInVO.getIdUfOperadora() + " , " + migacaoInVO.getDescricao() + " ," + migacaoInVO.getVlBonus() + " , "
                    + migacaoInVO.getQtDiasValidade() + "] ) - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {

            log.error("XmlException - MigracaoPlanoFacadeImpl:addMigracaoPlano(" + user + ", " + "[ " + migacaoInVO.getIdUfOperadora() + " , " + migacaoInVO.getDescricao() + " ," + migacaoInVO.getVlBonus() + " , " + migacaoInVO.getQtDiasValidade()
                    + "] ) - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MigracaoPlanoFacadeImpl:addMigracaoPlano", xex));

        } catch (Exception ex) {

            log.error("Exception - MigracaoPlanoFacadeImpl:addMigracaoPlano(" + user + ", " + "[ " + migacaoInVO.getIdUfOperadora() + " , " + migacaoInVO.getDescricao() + " ," + migacaoInVO.getVlBonus() + " , " + migacaoInVO.getQtDiasValidade()
                    + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }
}
