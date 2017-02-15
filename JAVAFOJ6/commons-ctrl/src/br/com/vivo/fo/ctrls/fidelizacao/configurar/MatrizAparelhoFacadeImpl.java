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
import br.com.vivo.fo.fidelizacao.vo.AparelhoCorVODocument.AparelhoCorVO;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.DetalheAparelhoVODocument;
import br.com.vivo.fo.fidelizacao.vo.DetalheAparelhoVODocument.DetalheAparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.fidelizacao.vo.ListaDDDVODocument.ListaDDDVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "MatrizAparelhoFacade", mappedName = "MatrizAparelhoFacade")
@Local(MatrizAparelhoFacade.class)
@Session(ejbName = "MatrizAparelhoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MatrizAparelhoFacadeImpl implements MatrizAparelhoFacade {
    
    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");
    private String            xmlIn            = ConstantesCRM.SVAZIO;
    private String            xmlOut           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public ArrayListaGeralVO getDadosIniciais(String user) throws FacadeException, TuxedoException {

        ArrayListaGeralVO dados = null;

        try {
            xmlIn = "<getreg>GETAPOIOMTZAP</getreg>";
            xmlIn = XmlManager.MakeXmlIn(user, "GETAPOIOMTZAP", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            dados = ArrayListaGeralVODocument.Factory.parse(xmlOut).getArrayListaGeralVO();

            return dados;
        } catch (Exception ex) {
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public ArrayListaGeralVO getAparelhos(String user, String regional, String segmentacao, String tipoCliente, String idGrupo) throws FacadeException, TuxedoException {
        ArrayListaGeralVO aparelhos = null;

        try {
            xmlIn = "<idUfOperadora>" + regional + "</idUfOperadora><idTipoPessoa>" + tipoCliente + "</idTipoPessoa><idSegmentacao>" + segmentacao + "</idSegmentacao><idGrupo>" + idGrupo + "</idGrupo>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELAPDISPSEL", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            aparelhos = ArrayListaGeralVODocument.Factory.parse(xmlOut).getArrayListaGeralVO();
            return aparelhos;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizAparelhoFacadeImpl:getAparelhos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizAparelhoFacadeImpl:getAparelhos", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizAparelhoFacadeImpl:getAparelhos(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizAparelhoFacadeImpl:getAparelhos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getParcelas(String user, String idMatrizAparelho, String operacao) throws FacadeException, TuxedoException {
        FidelizacaoListaGeralVO parcelas = null;
        try {
            xmlIn = "<idOperacao>" + operacao + "</idOperacao><idMatrizAparelho>" + idMatrizAparelho + "</idMatrizAparelho>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELPARCELAS", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            parcelas = FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();
            return parcelas;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizAparelhoFacadeImpl:getParcelas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizAparelhoFacadeImpl:getParcelas", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizAparelhoFacadeImpl:getParcelas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizAparelhoFacadeImpl:getParcelas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setAparelhos(String user, String regional, String segmentacao, String tipoCliente, String[] aparelhos, String idGrupo) throws FacadeException, TuxedoException {
        try {
            xmlIn = "<idUfOperadora>" + regional + "</idUfOperadora><idTipoPessoa>" + tipoCliente + "</idTipoPessoa><idSegmentacao>" + segmentacao + "</idSegmentacao><idGrupo>" + idGrupo + "</idGrupo>";

            for (int i = 0; i < aparelhos.length; i++) {
                if (aparelhos[i] != null) {
                    xmlIn += "<id>" + aparelhos[i] + "</id>";
                }
            }

            xmlIn = XmlManager.MakeXmlIn(user, "INSMTAPARELHO", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException ex) {
            log.error("XmlException - MatrizAparelhoFacadeImpl:setAparelhos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizAparelhoFacadeImpl:setAparelhos", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizAparelhoFacadeImpl:setAparelhos(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizAparelhoFacadeImpl:setAparelhos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public ItemArvoreVO getArvoreMatriz(String user, String xmlIdPais) throws FacadeException, TuxedoException {

        ItemArvoreVO arvore = null;
        try {

            xmlIn = "<getreg>GETARVAPARELHO</getreg>" + xmlIdPais;
            xmlIn = XmlManager.MakeXmlIn(user, "GETARVAPARELHO", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            // writeFileXML(xmlOut, "getArvoreMatriz_xmlOUT");

            arvore = ItemArvoreVODocument.Factory.parse(xmlOut).getItemArvoreVO();

            return arvore;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizAparelhoFacadeImpl:getArvoreMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizAparelhoFacadeImpl:getArvoreMatriz", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizAparelhoFacadeImpl:getArvoreMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizAparelhoFacadeImpl:getArvoreMatriz(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public DetalheAparelhoVO getDetalheAparelho(String user, String id) throws FacadeException, TuxedoException {

        DetalheAparelhoVO detAparelho = null;
        try {

            xmlIn = "<idMatrizAparelho>" + id + "</idMatrizAparelho>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELDETAPARELHO", xmlIn);

            // writeFileXML(xmlIn, "getDetalheAparelho_xmlIN");

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            // writeFileXML(xmlOut, "getDetalheAparelho_xmlOUT");

            detAparelho = DetalheAparelhoVODocument.Factory.parse(xmlOut).getDetalheAparelhoVO();

            return detAparelho;

        } catch (XmlException ex) {
            log.error("XmlException - MatrizAparelhoFacadeImpl:getDetalheAparelho(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizAparelhoFacadeImpl:getDetalheAparelho", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizAparelhoFacadeImpl:getDetalheAparelho(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizAparelhoFacadeImpl:getDetalheAparelho(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void setDetAparelho(String user, String idMatriz, String vlMulta, AparelhoCorVO[] detalheAparelhos, ListaDDDVO[] listaDDD, String[] descontos, String[] parcelas, String regional, String inChipAvulso, String inChipPreProgramado)
            throws FacadeException, TuxedoException {

        try {
            /*************** Modificado por Decio Jr 03/11/2004 ***************************************************/
            vlMulta = vlMulta.replace(',', '.');
            /*************** Fim Modificacao **********************************************************************/

            // MONTAGEM DO XML DE ENTRADA
            xmlIn = "<idUfOperadora>" + regional + "</idUfOperadora><idMatrizAparelho>" + idMatriz + "</idMatrizAparelho><vlMulta>" + vlMulta + "</vlMulta><listaApCor>";

            for (int i = 0; i < detalheAparelhos.length; i++) {
                xmlIn += "<item><idEstoque>" + detalheAparelhos[i].getIdEstoque() + "</idEstoque><cdSapAparelho>" + detalheAparelhos[i].getCodigoSAP() + "</cdSapAparelho>";
                xmlIn += "<qtDisponivel>" + detalheAparelhos[i].getQtdeEstoque() + "</qtDisponivel><vlAparelho>" + detalheAparelhos[i].getPreco().replace(',', '.') + "</vlAparelho></item>";
                xmlIn += "<idAparelhoCor>" + detalheAparelhos[i].getIdAparelhoCor() + "</idAparelhoCor>";
            }

            xmlIn += "</listaApCor><listaDesconto>";

            for (int i = 0; i < descontos.length; i++) {
                xmlIn += "<vlPercentualDesconto>" + descontos[i].replaceAll(",", "") + "</vlPercentualDesconto>";
            }
            xmlIn += "</listaDesconto><listaParcelas>";

            for (int i = 0; i < parcelas.length; i++) {
                xmlIn += "<idParcela>" + parcelas[i] + "</idParcela>";
            }

            xmlIn += "</listaParcelas><listaDDD>";

            for (int i = 0; i < listaDDD.length; i++) {
                xmlIn += "<item><id>" + listaDDD[i].getId() + "</id>";
                xmlIn += "<nrDDD>" + listaDDD[i].getNrDDD() + "</nrDDD>";
                xmlIn += "<nrCodigoSAP>" + listaDDD[i].getNrCodigoSAP() + "</nrCodigoSAP>";
                xmlIn += "<vlUnitario>" + listaDDD[i].getVlUnitario() + "</vlUnitario>";
                xmlIn += "<qtEstoque>" + listaDDD[i].getQtEstoque() + "</qtEstoque></item>";
            }
            xmlIn += "</listaDDD>";
            xmlIn += "<inChipAvulso>" + inChipAvulso + "</inChipAvulso>";
            xmlIn += "<inChipPreProgramado>" + inChipPreProgramado + "</inChipPreProgramado>";

            // FIM DA MONTAGEM DO XML
            xmlIn = XmlManager.MakeXmlIn(user, "SETDETAPARELHO", xmlIn);
            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (XmlException ex) {
            log.error("XmlException - MatrizAparelhoFacadeImpl:setDetAparelho(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MatrizAparelhoFacadeImpl:setDetAparelho", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MatrizAparelhoFacadeImpl:setDetAparelho(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - MatrizAparelhoFacadeImpl:setDetAparelho(" + user + ") - [" + ex.getMessage() + "]");
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
