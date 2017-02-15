package br.com.vivo.fo.ctrls.extworkflw.token;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;

import br.com.vivo.fo.ctrls.extworkflw.token.db.DadosTokenVivo360;
import br.com.vivo.fo.ctrls.extworkflw.token.db.TokenDB;
import br.com.vivo.fo.ctrls.workflow.parametros.Parametros;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "TokenFacade", mappedName = "TokenFacade")
@Local(TokenFacade.class)
@Session(ejbName = "TokenFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class TokenFacadeImpl implements TokenFacade {
	
	@EJB
	TokenDB tokenDB;
	
	private final static transient Logger log = new Logger("extworkflw");

	@Override
	public DadosTokenVivo360 getDadosToken(String id) throws FacadeException {
		log.debug("inicio getDadosToken id = " + id);
		DadosTokenVivo360 dadosTokenVivo360 = null;
		try {
			dadosTokenVivo360 = tokenDB.getDadosToken(id);
		} catch (SQLException e) {
			log.error("erro na query para obter token erro = [" + e.getMessage() + "]");			
			throw (new FacadeException(e));
		}
		log.debug("dados obtidos com sucesso");
		StringBuffer dados = new StringBuffer();
		dados.append("dtUltimaAtualizacao = [" + dadosTokenVivo360.getDtUltimaAtualizacao() + "]");
		dados.append(", idGrupo = [" + dadosTokenVivo360.getIdGrupo() + "]");
		dados.append(", idTokenVivo360 = [" + dadosTokenVivo360.getIdTokenVivo360() + "]");
		dados.append(", idUsuarioAlteracao = [" + dadosTokenVivo360.getIdUsuarioAlteracao() + "]");
		dados.append(", nrLinha = [" + dadosTokenVivo360.getNrLinha() + "]");
		dados.append(", nrProtocolo = [" + dadosTokenVivo360.getNrProtocolo() + "]");
		
		log.debug(dados.toString());

		return dadosTokenVivo360;
	}

}
