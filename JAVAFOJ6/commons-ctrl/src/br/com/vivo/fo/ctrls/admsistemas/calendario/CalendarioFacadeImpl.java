package br.com.vivo.fo.ctrls.admsistemas.calendario;

import java.util.ArrayList;
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
import br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@SuppressWarnings({"rawtypes","unchecked"})
@Stateless(name = "CalendarioFacade", mappedName = "CalendarioFacade")
@Local(CalendarioFacade.class)
@Session(ejbName = "CalendarioFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CalendarioFacadeImpl implements CalendarioFacade {

    @EJB
    private TuxedoServiceCall   tuxedo;

    private static Logger       log               = new Logger("admsistemas");
    private String              xmlIN             = ConstantesCRM.SVAZIO;
    private String              xmlOUT            = ConstantesCRM.SVAZIO;
    private final static String REGISTROS_PPAGINA = "200";

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO carregaTelaInsere(String user) throws TuxedoException, FacadeException {
        try {
            // Substituir idContatoFolha por AdmIdContatoVO
            log.debug("CalendarioFacadeImpl:carregaTelaInsere(" + user + ")");

            xmlIN = "<oi></oi>";
            xmlIN = XmlManager.MakeXmlIn(user, "FrdListarId", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            if (msgDocRet.getMsg().getMsgHdr().getStatusCode().indexOf("99E") >= 0) {
                throw new Exception(msgDocRet.getMsg().getMsgHdr().getStatusText());
            }

            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);

            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:carregaTelaInsere(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:carregaTelaInsere", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:carregaTelaInsere(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO salvaFeriado(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:salvaFeriado(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdIncluir", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:salvaFeriado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:salvaFeriado", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:salvaFeriado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO salvaFeriadoEditado(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:salvaFeriadoEditado(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdEditar", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:salvaFeriadoEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:salvaFeriadoEditado", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:salvaFeriadoEditado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void removeFeriado(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:removeFeriado(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdRemove", xmlIN);

            // new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:removeFeriado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:removeFeriado", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:removeFeriado(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ArrayList listaFeriados(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:listaFeriados(" + user + ")");

            feriado.setRegistrosPPagina(REGISTROS_PPAGINA);
            feriado.setPaginaAtual(ConstantesCRM.SONE);
            boolean proximaPagina = true;
            AdmCalendarioContainerVO selectCalendario = null;
            ArrayList list = new ArrayList();

            while (proximaPagina) {
                xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
                xmlIN = XmlManager.MakeXmlIn(user, "FrdListar", xmlIN);

                // xmlOUT = (new ControlTuxedoCall()).execute(this,
                // admSistemasTux, "GETSERVICE", xmlIN);
                xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
                MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
                xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

                AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
                selectCalendario = doc.getAdmCalendarioContainerVO();

                list.add(selectCalendario);

                if (selectCalendario.getRegistrosPPagina().equals(REGISTROS_PPAGINA)) {
                    int auxPagina = Integer.parseInt(feriado.getPaginaAtual()) + 1;
                    feriado.setPaginaAtual(String.valueOf(auxPagina));
                } else {
                    proximaPagina = false;
                }
            }
            return list;

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:listaFeriados(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:listaFeriados", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:listaFeriados(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO salvaFeriadosMoveis(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:salvaFeriadosMoveis(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdNomIncluir", xmlIN);
            xmlIN = xmlIN.replaceAll("vo1", "ns1");

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:salvaFeriadosMoveis(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:salvaFeriadosMoveis", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:salvaFeriadosMoveis(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO montaListaUF(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:montaListaUF(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdMncListarId", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;

            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:montaListaUF(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:montaListaUF(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO carregaMunicipiosPorID(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:carregaMunicipiosPorID(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdMncListar", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:carregaMunicipiosPorID(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:carregaMunicipiosPorID", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:carregaMunicipiosPorID(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO carregaUFsPorID(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:carregaUFsPorID(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdUFListar", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:carregaUFsPorID(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:carregaUFsPorID", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:carregaUFsPorID(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO salvaRelacionamentoPorUF(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:salvaRelacionamentoPorUF(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdUFIncluir", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:salvaRelacionamentoPorUF(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:salvaRelacionamentoPorUF", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:salvaRelacionamentoPorUF(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO salvaRelacionamentoPorMunicipio(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:salvaRelacionamentoPorMunicipio(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdMncIncluir", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:salvaRelacionamentoPorMunicipio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:salvaRelacionamentoPorMunicipio", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:salvaRelacionamentoPorMunicipio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO copiarCalendario(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:copiarCalendario(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FRDCOPIA", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;
            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:copiarCalendario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:copiarCalendario", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:copiarCalendario(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO carregarPonte(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:carregarPonte(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdTipListar", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;

            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:carregarPonte(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:carregarPonte", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:carregarPonte(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmCalendarioContainerVO salvarPonte(AdmCalendarioContainerVO feriado, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("CalendarioFacadeImpl:salvarPonte(" + user + ")");

            xmlIN = feriado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "FrdTipIncluir", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmCalendarioContainerVODocument doc = AdmCalendarioContainerVODocument.Factory.parse(xmlOUT);;

            return doc.getAdmCalendarioContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - CalendarioFacadeImpl:salvarPonte(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CalendarioFacadeImpl:salvarPonte", ex));

        } catch (Exception ex) {
            log.error("Exception - CalendarioFacadeImpl:salvarPonte(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
