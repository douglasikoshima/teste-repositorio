package br.com.vivo.fo.ctrls.usuario.acesso;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ControleAcessoFacade", mappedName = "ControleAcessoFacade")
@Local(ControleAcessoFacade.class)
@Session(ejbName = "ControleAcessoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ControleAcessoFacadeImpl implements ControleAcessoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("usuario");

    public String obterValorSenha(String user, String cdAreaRegistro, String nrLinha, String idCanal, String idTipoRelacionamento) throws TuxedoException, FacadeException {
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;

        StringBuffer sb = new StringBuffer();
        sb.append("<cdAreaRegistro>").append(cdAreaRegistro).append("</cdAreaRegistro>");
        sb.append("<nrLinha>").append(nrLinha).append("</nrLinha>");
        sb.append("<idCanal>").append(idCanal).append("</idCanal>");
        sb.append("<idTipoRelacionamento>").append(idTipoRelacionamento).append("</idTipoRelacionamento>");
        sb.append("<operacao>obterSenha</operacao>");

       try {
    	   xmlIN = XmlManager.MakeXmlIn(ConstantesCRM.SONE, "SOLICITARSENHA", sb.toString());
    	   xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

           return MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().getDomNode().getNodeValue();

       } catch (XmlException ex) {
           log.error("XmlException - ControleAcessoFacadeImpl:obterValorSenha(" + xmlIN + ") - [" + ex.getMessage() + "]");
           throw (new FacadeException("Erro na montagem do XML de entrada: ControleAcessoFacadeImpl:obterValorSenha", ex));
       
       } catch (TuxedoServiceCallerException ex) {
           log.error("TuxedoException - ControleAcessoFacadeImpl:obterValorSenha(" + xmlIN + ") - [" + ex.getMessage() + "]");
           throw new TuxedoException(ex);
       
       } catch (Exception ex) {
           log.error("Exception - ControleAcessoFacadeImpl:obterValorSenha(" + xmlIN + ") - [" + ex.getMessage() + "]");
           throw (new FacadeException(ex));
       }
  }    	
	
	
    public UsuarioVO carregaDadosUsuarioSessao(String xmlUsuario) throws TuxedoException, FacadeException {
        try {
            log.debug("ControleAcessoFacadeImpl:carregaDadosUsuarioSessao(" + xmlUsuario + ")");
            String xmlIN = XmlManager.MakeXmlIn(ConstantesCRM.SONE, "USRLOGINID", xmlUsuario);
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return UsuarioVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getUsuarioVO();

        } catch (XmlException ex) {
            log.error("XmlException - ControleAcessoFacadeImpl:carregaDadosUsuarioSessao(" + xmlUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ControleAcessoFacadeImpl:carregaDadosUsuarioSessao", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ControleAcessoFacadeImpl:carregaDadosUsuarioSessao(" + xmlUsuario + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ControleAcessoFacadeImpl:carregaDadosUsuarioSessao(" + xmlUsuario + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
