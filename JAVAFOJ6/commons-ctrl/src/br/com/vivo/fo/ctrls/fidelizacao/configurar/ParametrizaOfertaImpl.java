package br.com.vivo.fo.ctrls.fidelizacao.configurar;

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
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ParametrizaOferta", mappedName = "ParametrizaOferta")
@Local(ParametrizaOferta.class)
@Session(ejbName = "ParametrizaOferta", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ParametrizaOfertaImpl implements ParametrizaOferta {

    @EJB
    private TuxedoServiceCall tuxedo;

    private String            xmlIn            = ConstantesCRM.SVAZIO;
    private String            xmlOut           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public FidelizacaoVO getListas(String user, FidelizacaoVO xml) throws FacadeException, TuxedoException {
        try {
            xmlIn = xml.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "GETLISTAS", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux ,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return FidelizacaoVODocument.Factory.parse(xmlOut).getFidelizacaoVO();

        } catch (Exception ex) {
            throw (new FacadeException(ex));
        }
    }

}
