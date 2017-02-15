package br.com.vivo.fo.ctrls.fidelizacao.configurar;

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
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@SuppressWarnings({"rawtypes","unchecked"})
@Stateless(name = "ParcelamentoFacade", mappedName = "ParcelamentoFacade")
@Local(ParcelamentoFacade.class)
@Session(ejbName = "ParcelamentoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParcelamentoFacadeImpl implements ParcelamentoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private transient Logger  log              = new Logger("ConfigParcela");

    /**
     * @common:operation
     */
    public String gravarParcelamento(String user, String idTipoPessoa, String valorMinimo, ArrayList parcelas) throws TuxedoException, FacadeException {

        try {
            log.debug("ParcelamentoFacedeImpl:gravarParcelamento(" + user + ", " + parcelas.toArray(new String[0]) + ")");
            StringBuffer xml = new StringBuffer("<cdOperacao>1</cdOperacao><idTipoPessoa>").append(idTipoPessoa).append("</idTipoPessoa>");
            xml.append("<valorMinimo>").append(valorMinimo).append("</valorMinimo>");
            xml.append("<Parcelas>");
            for (int i = 0; i < parcelas.size(); i++) {
                xml.append("<id>").append(parcelas.get(i)).append("</id>");
            }
            xml.append("</Parcelas>");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSAPARELHO", xml.toString());
            // String xmlOUT = (new ControlTuxedoCall()).execute(this,
            // fidelizaTux, "GETSERVICE", xmlIN);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return (xmlOUT);

        } catch (XmlException ex) {
            log.error("XmlException - ParcelamentoFacedeImpl:gravarParcelamento(" + user + ", " + parcelas.toArray(new String[0]) + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ParcelamentoFacedeImpl:addNovoAparelho", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ParcelamentoFacedeImpl:gravarParcelamento(" + user + ", " + parcelas.toArray(new String[0]) + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ParcelamentoFacedeImpl:gravarParcelamento(" + user + ", " + parcelas.toArray(new String[0]) + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

}
