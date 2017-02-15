package br.com.vivo.fo.ctrls.campanha.lista;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.campanha.vo.ListaCampanhaApoioVODocument;
import br.com.vivo.fo.campanha.vo.ListaCampanhaApoioVODocument.ListaCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.ListaStatusCampanhaVODocument;
import br.com.vivo.fo.campanha.vo.ListaStatusCampanhaVODocument.ListaStatusCampanhaVO;
import br.com.vivo.fo.campanha.vo.ListasCampanhaVODocument;
import br.com.vivo.fo.campanha.vo.ListasCampanhaVODocument.ListasCampanhaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ListaCampanhaFacade", mappedName = "ListaCampanhaFacade")
@Local(ListaCampanhaFacade.class)
@Session(ejbName = "ListaCampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ListaCampanhaFacadeImpl implements ListaCampanhaFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final transient static Logger log              = new Logger("campanha");

    private transient String[]            retTux           = new String[0];

    /********************************************** Lista Externas **********************************************************************************************************************/
    /**
     * @common:operation
     */
    public ListaCampanhaApoioVO getListasAssociadasCampanhaVO(String user, int idCanalCampanha, int inNegative) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlBuffer = new StringBuffer();
            xmlBuffer.append("<idCanalCampanha>").append(idCanalCampanha).append("</idCanalCampanha>");
            xmlBuffer.append("<inNegative>").append(inNegative).append("</inNegative>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETLISTAASSOC", xmlBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return ListaCampanhaApoioVODocument.Factory.parse(xmlOUT).getListaCampanhaApoioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:getListasAssociadasCampanhaVO(" + user + "," + idCanalCampanha + "," + inNegative + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:getListasAssociadasCampanhaVO", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoServiceCallerException - ListaCampanhaFacadeImpl:getListasAssociadasCampanhaVO(" + user + "," + idCanalCampanha + "," + inNegative + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:getListasAssociadasCampanhaVO(" + user + "," + idCanalCampanha + "," + inNegative + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO addUploadLista(String user, String idLista) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlBuffer = new StringBuffer();
            xmlBuffer.append("<valor>").append(idLista).append("</valor>");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSLISTACAMP", xmlBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:addUploadLista(" + user + ", " + idLista + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:addUploadLista", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoServiceCallerException - ListaCampanhaFacadeImpl:addUploadLista(" + user + ", " + idLista + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:addUploadLista(" + user + ", " + idLista + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO checaUploadLista(String user, String nmLista, String nmArquivoLista) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlBuffer = new StringBuffer();

            xmlBuffer.append("<nmLista>");
            xmlBuffer.append(StringEscapeUtils.escapeXml(nmLista));
            xmlBuffer.append("</nmLista>");
            xmlBuffer.append("<nmArquivoLista>");
            xmlBuffer.append(nmArquivoLista);
            xmlBuffer.append("</nmArquivoLista>");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSLISTACAMP", xmlBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:checaUploadLista(" + user + ", " + nmLista + ", " + nmArquivoLista + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:checaUploadLista", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoServiceCallerException - ListaCampanhaFacadeImpl:checaUploadLista(" + user + ", " + nmLista + ", " + nmArquivoLista + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:checaUploadLista(" + user + ", " + nmLista + ", " + nmArquivoLista + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setListasAssociadasCampanhaVO(java.lang.String user, String idLista[], int idCanalCampanha, int inNegative) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlBuffer = new StringBuffer();

            if (idLista.length == 0) {
                xmlBuffer.append("<idCanalCampanha>");
                xmlBuffer.append(idCanalCampanha);
                xmlBuffer.append("</idCanalCampanha>");
            } else {
                for (int i = 0; i < idLista.length; i++) {
                    xmlBuffer.append("<codigo>");
                    xmlBuffer.append(idLista[i]);
                    xmlBuffer.append("</codigo><idCanalCampanha>");
                    xmlBuffer.append(idCanalCampanha);
                    xmlBuffer.append("</idCanalCampanha><inAtivo>");
                    xmlBuffer.append(inNegative);
                    xmlBuffer.append("</inAtivo>");
                }
            }

            String xmlIN = XmlManager.MakeXmlIn(user, "SETLISTAASSOC", xmlBuffer.toString());
            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE(xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            retTux = XmlManager.ParseXmlOut(xmlOUT);
            RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:setListasAssociadasCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:setListasAssociadasCampanhaVO", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoServiceCallerException - ListaCampanhaFacadeImpl:setListasAssociadasCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:setListasAssociadasCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaStatusCampanhaVO obterStatusLista(String user) throws TuxedoException, FacadeException {
        try {
            String xmlIN = "<operacao>2</operacao>";
            xmlIN = XmlManager.MakeXmlIn(user, "SELLISTACAMP", xmlIN);

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE( xmlIN );
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListaStatusCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaStatusCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:obterStatusLista(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:obterStatusLista", ex));
        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:obterStatusLista(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListasCampanhaVO pesquisarListas(String user, String nmLista, String inStatusCarga) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlBuffer = new StringBuffer();
            xmlBuffer.append("<nmLista>");
            xmlBuffer.append(StringEscapeUtils.escapeXml(nmLista));
            xmlBuffer.append("</nmLista>");
            xmlBuffer.append("<inStatusCarga>");
            xmlBuffer.append(inStatusCarga);
            xmlBuffer.append("</inStatusCarga>");
            xmlBuffer.append("<operacao>1</operacao>");

            String xmlIN = XmlManager.MakeXmlIn(user, "SELLISTACAMP", xmlBuffer.toString());

            String xmlOUT = ConstantesCRM.SVAZIO;// campanhaTux.GETSERVICE( xmlIN );
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return ListasCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListasCampanhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:pesquisarListas(" + user + ", " + nmLista + ", " + inStatusCarga + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:pesquisarListas", ex));
        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:pesquisarListas(" + user + ", " + nmLista + ", " + inStatusCarga + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void delListaCamp(String user, String idLista) throws TuxedoException, FacadeException {
        try {
            StringBuffer xmlBuffer = new StringBuffer();
            xmlBuffer.append("<idLista>");
            xmlBuffer.append(idLista);
            xmlBuffer.append("</idLista>");

            String xmlIN = XmlManager.MakeXmlIn(user, "DELLISTACAMP", xmlBuffer.toString());

            tuxedo.callService("TuxConnector", xmlIN);

        } catch (XmlException ex) {
            log.error("XmlException - ListaCampanhaFacadeImpl:delListaCamp(" + user + ", " + idLista + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ListaCampanhaFacadeImpl:delListaCamp", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoServiceCallerException - ListaCampanhaFacadeImpl:delListaCamp(" + user + ", " + idLista + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ListaCampanhaFacadeImpl:delListaCamp(" + user + ", " + idLista + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
