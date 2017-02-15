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
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoXMLINMatrizOfertasVODocument.RetencaoXMLINMatrizOfertasVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "MatrizOfertaFacade", mappedName = "MatrizOfertaFacade")
@Local(MatrizOfertaFacade.class)
@Session(ejbName = "MatrizOfertaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MatrizOfertaFacadeImpl implements MatrizOfertaFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log    = new Logger("fidelizacao");
    private String            xmlIn  = ConstantesCRM.SVAZIO;
    private String            xmlOut = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public ArrayListaGeralVO getDadosIniciais(String user) throws TuxedoException, FacadeException {

        log.debug("MatrizOfertaFacade:getDadosIniciais" + "( " + user + " )");

        try {

            ArrayListaGeralVO apoio = null;

            xmlIn = "<getreg>GETAPOIOMTOF</getreg>";
            xmlIn = XmlManager.MakeXmlIn(user, "GETAPMTOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);

            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            apoio = ArrayListaGeralVODocument.Factory.parse(xmlOut).getArrayListaGeralVO();

            return apoio;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:getDadosIniciais", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getDestino(String user, RetencaoXMLINMatrizOfertasVO dados) throws TuxedoException, FacadeException {
        log.debug("MatrizOfertaFacade:getDestino" + "( " + user + " )");
        try {
            // xmlIn = "<idUfOperadora>" + idRegional+
            // "</idUfOperadora><idIntencao>" + idIntencao +
            // "</idIntencao><idTipoPessoa>" + idTipoPessoa + "</idTipoPessoa>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELDSTPREV", dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);

            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:getDestino(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:getDestino", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:getDestino(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:getDestino(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addOferta(String user, RetencaoXMLINMatrizOfertasVO dados) throws TuxedoException, FacadeException {

        log.debug("MatrizOfertaFacade:addOferta" + "( " + user + " )");

        try {
            /*
             * //monta xml de entrada para o serviço tuxedo xmlIn =
             * "<idUfOperadora>" + idMatriz[0] +
             * "</idUfOperadora><idSegmentacao>" + idMatriz[1] +
             * "</idSegmentacao>"; xmlIn += "<idTipoPessoa>" + idMatriz[2] +
             * "</idTipoPessoa><idIntencao>" + idMatriz[3] + "</idIntencao>";
             * xmlIn += "<idDestino>" + idMatriz[4] + "</idDestino><oferta>";
             * 
             * for(int i = 0 ; i< idOfertas.length ; i++){ xmlIn += "<id>"+
             * idOfertas[i] + "</id>"; }
             * 
             * xmlIn += "</oferta>";
             */

            xmlIn = XmlManager.MakeXmlIn(user, "INSMTOFERTA", dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:addOferta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:addOferta", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:addOferta(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:addOferta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ArrayListaGeralVO getOfertas(String user, RetencaoXMLINMatrizOfertasVO dados) throws TuxedoException, FacadeException {

        log.debug("MatrizOfertaFacade:getOfertas" + "( " + user + " )");

        try {

            ArrayListaGeralVO ofertas = null;

            // xmlIn = "<idUfOperadora>" + dados[0] +
            // "</idUfOperadora><idSegmentacao>"+ dados[1]+" </idSegmentacao>";
            // xmlIn += "<idTipoPessoa>"+ dados[2] +
            // "</idTipoPessoa><idDestino>" + dados[3] +
            // "</idDestino><idIntencao>" + dados[4] + "</idIntencao>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELOFDISP", dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // writeFileXML(xmlIn, "getOfertas") ;

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ofertas = ArrayListaGeralVODocument.Factory.parse(xmlOut).getArrayListaGeralVO();

            return ofertas;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:getOfertas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:getOfertas", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:getOfertas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:getOfertas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setOferta(String user, RetencaoXMLINMatrizOfertasVO dados) throws TuxedoException, FacadeException {

        log.debug("MatrizOfertaFacade:setOferta" + "( " + user + " )");

        try {
            /*
             * //monta xml de entrada para o serviço tuxedo xmlIn =
             * "<operacao>1</operacao><oferta>"; xmlIn += "<idUfOperadora>" +
             * dados[0] + "</idUfOperadora><idSegmentacao>"+
             * dados[1]+" </idSegmentacao>"; xmlIn += "<idTipoPessoa>"+ dados[2]
             * + "</idTipoPessoa><idDestino>" + dados[3] +
             * "</idDestino><idIntencao>" + dados[4] + "</idIntencao>";
             * 
             * if (idOfertas!= null ) { for(int i = 0 ; i< idOfertas.length ;
             * i++) { xmlIn += "<id>"+ idOfertas[i] + "</id>"; } } xmlIn +=
             * "</oferta>";
             */
            xmlIn = XmlManager.MakeXmlIn(user, "INSMTOFERTA", dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:setOferta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:setOferta", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:setOferta(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:setOferta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ItemArvoreVO getArvore(String user) throws FacadeException, TuxedoException {
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            xmlIn = "<getreg>GETARVOFERTA</getreg>";

            xmlIn = XmlManager.MakeXmlIn(user, "GETARVOFERTA", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);

            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ItemArvoreVO arvore = ItemArvoreVODocument.Factory.parse(xmlOut).getItemArvoreVO();

            return arvore;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:getArvore", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ItemArvoreVO getArvoreMatriz(String user, String xmlIdPais) throws FacadeException, TuxedoException {
        xmlOut = ConstantesCRM.SVAZIO;
        try {
            xmlIn = "<getreg>GETARVOFERTA</getreg>" + xmlIdPais;
            xmlIn = XmlManager.MakeXmlIn(user, "GETARVOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            ItemArvoreVO arvore = ItemArvoreVODocument.Factory.parse(xmlOut).getItemArvoreVO();

            return arvore;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizOfertaFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizOfertaFacadeImpl:getArvore", ex));
        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizOfertaFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (Exception ex) {
            log.error("Exception - MatrizOfertaFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*
     * private void writeFileXML(String xmlOUT, String fileName) throws
     * Exception{
     * 
     * java.io.File outFile = new java.io.File("D:\\xmlsFO\\" + fileName +
     * ".xml"); //O cara q escreve java.io.FileWriter out = new
     * java.io.FileWriter(outFile); //Seu conteudo de saida out.write(xmlOUT);
     * //Nao pode esquecer de fechar o objeto de saida out.close();
     * 
     * }
     */

}
