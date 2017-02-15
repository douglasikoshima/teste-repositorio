package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.application;

import java.io.IOException;

import javax.ejb.SessionContext;

import netscape.ldap.LDAPAttribute;
import netscape.ldap.LDAPAttributeSet;
import netscape.ldap.LDAPException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.foundation.xml.XMLParserException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.indrasistemas.framework.service.application.ApplicationServiceException;
import br.com.indrasistemas.framework.service.application.BaseApplicationService;
import br.com.indrasistemas.framework.service.servicegateway.ServiceGatewayException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoBusinessException;
import br.com.indrasistemas.framework.service.servicegateway.tuxedo.exception.TuxedoException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RelacionaUsuarioTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.CadastroUsuarioIdmGateway;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.RelacionarUsuarioIdmGateway;
import br.com.indrasistemas.vivoservices.ldap.LDAPUtil;

public class CadastroIdmAS extends BaseApplicationService {

	private static final Log logger = LogFactory.getLog(CadastroIdmAS.class);
	
	public CadastroIdmAS(SessionContext sessionContext) {
		super(sessionContext);
	}

	public RespostaTO cadastrar(RequestInfoTO requestInfo, ParametrosTO parametros) throws ApplicationServiceException, BusinessException {
		RespostaTO respostaTO = new RespostaTO();
        if (logger.isDebugEnabled()) {
            logger.debug("CadastroIdmAS::cadastrar - Iniciando AS");
        }
        if(parametros==null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(parametros.getNome()==null || "".equals(parametros.getNome())){
                throw new ApplicationServiceException("Parametro nome é Obrigatório.");
            }
        }

        try{
        	CadastroUsuarioIdmGateway gateway = new CadastroUsuarioIdmGateway();
        	respostaTO = (RespostaTO) gateway.call(requestInfo, parametros);
        	
        	incluirUsuarioLDAP(respostaTO, parametros.getEmail());

        }catch(TuxedoBusinessException e){
            logger.error("CadastroIdmAS::cadastrar::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("CadastroIdmAS::cadastrar::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("CadastroIdmAS::cadastrar::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("CadastroIdmAS::cadastrar::XMLParserException", e);
            throw new ApplicationServiceException(e);
        
        } catch (LDAPException e) {
            logger.error("CadastroIdmAS::cadastrar::LDAPException", e);
            throw new ApplicationServiceException(e);

        } catch (IOException e) {
            logger.error("CadastroIdmAS::cadastrar::IOException", e);
            throw new ApplicationServiceException(e);
		}
		return respostaTO;
	}
	
	private void incluirUsuarioLDAP(RespostaTO respostaTO, String email) throws LDAPException, IOException {
        inseriLDAP(respostaTO, email);
        desbloqueiaUsuario(respostaTO.getLogin());
	}
	
    private void inseriLDAP(RespostaTO usuarioLDAPVO, String correio) throws LDAPException, IOException {
        LDAPUtil ldap = new LDAPUtil();
        
        LDAPAttribute attr1 = new LDAPAttribute("cn", usuarioLDAPVO.getNome());
        LDAPAttribute attr2 = new LDAPAttribute("sn", usuarioLDAPVO.getSobrenome());
        LDAPAttribute attr3 = new LDAPAttribute("objectclass", "top");
        LDAPAttribute attr4 = new LDAPAttribute("objectclass", "person");
        LDAPAttribute attr5 = new LDAPAttribute("objectclass", "organizationalPerson");
        LDAPAttribute attr6 = new LDAPAttribute("objectclass", "inetOrgPerson");
        LDAPAttribute attr7 = new LDAPAttribute("mail", correio);
        LDAPAttribute attr8 = new LDAPAttribute("userPassword", "654321");
        LDAPAttribute attr9 = new LDAPAttribute("title", "true");

        LDAPAttributeSet myAttrs = new LDAPAttributeSet();
        myAttrs.add(attr1);
        myAttrs.add(attr2);
        myAttrs.add(attr3);
        myAttrs.add(attr4);
        myAttrs.add(attr5);
        myAttrs.add(attr6);
        myAttrs.add(attr7);
        myAttrs.add(attr8);
        myAttrs.add(attr9);

        ldap.setAdicionaUsuario(usuarioLDAPVO.getLogin(), myAttrs);
        ldap.adicionaUsuarioGrupo("uniquemember", usuarioLDAPVO.getLogin());
    }
    
    private void desbloqueiaUsuario(String login) throws IOException, LDAPException {
        logger.debug("CadastroIdmAS:desbloqueiaUsuario() (LDAP)");
        LDAPUtil ldap = new LDAPUtil();
        ldap.modificaAtributo(login, "nsaccountlock", "false");
        ldap.modificaAtributo(login, "passwordRetryCount", "0");
    }

    public RespostaTO relacionarUsuarioIdm(RequestInfoTO requestInfo, RelacionaUsuarioTO parametros) throws ApplicationServiceException, BusinessException {
		RespostaTO respostaTO = new RespostaTO();
        if (logger.isDebugEnabled()) {
            logger.debug("CadastroIdmAS::relacionarUsuarioIdm - Iniciando AS");
        }
        if(parametros==null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(parametros.getIdUsuario()==null || "".equals(parametros.getIdUsuario())){
                throw new ApplicationServiceException("Parametro idUsuário é Obrigatório.");
            }
            if(parametros.getNmUnidade()==null || "".equals(parametros.getNmUnidade())){
                throw new ApplicationServiceException("Parametro NmUnidade é Obrigatório.");
            }else{
            	if( !"Grupo".equals(parametros.getNmUnidade()) &&
            		!"Operadora".equals(parametros.getNmUnidade()) &&
            		!"ItemMenu".equals(parametros.getNmUnidade()) &&
            		!"Perfil".equals(parametros.getNmUnidade())
            	){
            		throw new ApplicationServiceException("Valor inválido para o parametro NmUnidade. ["+parametros.getNmUnidade()+"]");
                }
            }
            
            if(parametros.getListaId()==null || (parametros.getListaId()!=null && parametros.getListaId().size()==0)){
                throw new ApplicationServiceException("Parametro ListaId é Obrigatório.");
            }
        }

        try{
        	RelacionarUsuarioIdmGateway gateway = new RelacionarUsuarioIdmGateway();
        	respostaTO = (RespostaTO) gateway.call(requestInfo, parametros);

        }catch(TuxedoBusinessException e){
            logger.error("CadastroIdmAS::cadastrar::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("CadastroIdmAS::cadastrar::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("CadastroIdmAS::cadastrar::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("CadastroIdmAS::cadastrar::XMLParserException", e);
            throw new ApplicationServiceException(e);
		}
		return respostaTO;
    }

	public RespostaTO ativarUsuarioIdm(RequestInfoTO requestInfo, ParametrosTO parametros) throws ApplicationServiceException, BusinessException {
		RespostaTO respostaTO = new RespostaTO();
        if (logger.isDebugEnabled()) {
            logger.debug("CadastroIdmAS::cadastrar - Iniciando AS");
        }
        if(parametros==null){
            throw new ApplicationServiceException("Parametros Obrigatórios não informados.");
        }else{
            if(parametros.getNome()==null || "".equals(parametros.getNome())){
                throw new ApplicationServiceException("Parametro nome é Obrigatório.");
            }
            if(parametros.getIdUsuario()==null || "".equals(parametros.getIdUsuario())){
                throw new ApplicationServiceException("Parametro IdUsuario é Obrigatório.");
            }
        }

        try{
        	CadastroUsuarioIdmGateway gateway = new CadastroUsuarioIdmGateway();
        	respostaTO = (RespostaTO) gateway.call(requestInfo, parametros);
        	
        }catch(TuxedoBusinessException e){
            logger.error("CadastroIdmAS::cadastrar::TuxedoBusinessException", e);
            throw new BusinessException(e);

        }catch(ServiceGatewayException e){
            logger.error("CadastroIdmAS::cadastrar::ServiceGatewayException", e);
            throw new ApplicationServiceException(e);
            
        }catch(TuxedoException e){
            logger.error("CadastroIdmAS::cadastrar::TuxedoException", e);
            throw new BusinessException(e);
            
        }catch(XMLParserException e){
            logger.error("CadastroIdmAS::cadastrar::XMLParserException", e);
            throw new ApplicationServiceException(e);
		}
		return respostaTO;
	}
}
