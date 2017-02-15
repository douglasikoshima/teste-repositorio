package com.indracompany.catalogo.ejb.importacaotipomatriz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaCriticaDAO;
import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaDAO;
import com.indracompany.catalogo.dao.interfaces.ContratoMatrizOfertaLockDAO;
import com.indracompany.catalogo.datalayer.ContratoMatrizOferta;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;


/**
 * @author Luiz Pereira
 *
 */
@Stateless(name = "ImportacaoTipoMatrizContratoBean", mappedName = "ImportacaoTipoMatrizContratoBean")
@Session(ejbName = "ImportacaoTipoMatrizContratoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.SUPPORTS)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ImportacaoTipoMatrizContratoBean implements ImportacaoTipoMatrizContratoBeanLocal {
	 
	private static Logger logger = Logger.getLogger(ImportacaoTipoMatrizContratoBean.class);
	
	private final static String CABECALHO_EXPORT = "CODIGOPLANO;CODIGOSERVICO;SIGLACSA;VALORCONTRATO";
	private final static String CABECALHO_CRITICA_EXPORT = "CODIGOPLANO;CODIGOSERVICO;SIGLACSA;VALORCONTRATO;CRITICA";
	
	@EJB
	private ContratoMatrizOfertaDAO contratoMatrizOfertaDAO;
	
	@EJB
	private ContratoMatrizOfertaLockDAO contratoMatrizOfertaLockDAO;
	
	@EJB
	private ContratoMatrizOfertaCriticaDAO contratoMatrizOfertaCriticaDAO; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.importacaotipomatriz.ImportacaoTipoMatrizContratoBeanLocal#importar(java.io.InputStream)
	 */
	public Boolean importContratoMatrizOferta(InputStream inputStream, String usuario) throws BusinessException {

		Boolean retorno = Boolean.TRUE;
		
		try {
			
			logger.info("FAZENDO LEITURA DO ARQUIVO");
			BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream ) );
			
			// REMOVER CRITICAS ATUAIS
			contratoMatrizOfertaCriticaDAO.removeAllCriticas();
			
			// REMOVER DADOS DA BASE
			contratoMatrizOfertaDAO.deleteAll();
			
			logger.info("PARSEANDO ARQUIVO");
			
			String line = "";
			Integer linhaCorrente = 0; 
			
			List<ContratoMatrizOferta> list = new ArrayList<ContratoMatrizOferta>();
			
			while ((line = reader.readLine()) != null) {
				linhaCorrente++;
				if (!line.equals(CABECALHO_EXPORT) && !line.equals(CABECALHO_CRITICA_EXPORT)) {
					// INSERIR NOVOS DADOS NA BASE 
					list.add(createTO(line, usuario, linhaCorrente));
				}
			}
			
			contratoMatrizOfertaDAO.createUpdateContratoMatrizOferta(list);
			
			// ALTERANDO ARQUIVO DE LOCK PARA IMPORTADO.
			ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = new ContratoMatrizOfertaLockTO();
			contratoMatrizOfertaLockTO.setNmUsuarioAlteracao(usuario);
			contratoMatrizOfertaLockTO.setDtAlteracao(new Date());
			contratoMatrizOfertaLockDAO.updateStatusImportar(contratoMatrizOfertaLockTO);
			
		} catch (FileNotFoundException e) {
			throw new BusinessException(e);
		} catch (IOException e) {
			throw new BusinessException(e);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.importacaotipomatriz.ImportacaoTipoMatrizContratoBeanLocal#exportContratoMatrizOferta(java.lang.Boolean)
	 */
	public List<String> exportContratoMatrizOferta(Boolean trabalho) throws BusinessException {
		
		List<String> list = null;
		
		try {
			
			if (trabalho) {
				ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = contratoMatrizOfertaLockDAO.findFileLockedCurrent();
				if (contratoMatrizOfertaLockTO != null) {
					throw new BusinessException(StringUtils.join(new String[]{"Arquivo de contrato em utiliza&ccedil;&atilde;o:", contratoMatrizOfertaLockTO.getNmUsuarioCriacao(), " - ", contratoMatrizOfertaLockTO.getNmArquivo()}, " "));
				}
			}
			
			list = contratoMatrizOfertaDAO.exportContratoMatrizOferta();
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.importacaotipomatriz.ImportacaoTipoMatrizContratoBeanLocal#validarDados()
	 */
	public Boolean validarDados() throws BusinessException {
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			retorno = contratoMatrizOfertaDAO.validarDados();
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		 
		return retorno;
	}
	

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.importacaotipomatriz.ImportacaoTipoMatrizContratoBeanLocal#liberarProducao()
	 */
	public Boolean liberarProducao() throws BusinessException {
		Boolean retorno = Boolean.TRUE;
		
		try {
			retorno = contratoMatrizOfertaDAO.liberarProducao();
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
		 
		return retorno;
	}
	
	public void removerDadosAntigos() throws BusinessException {
		
		try {
			// REMOVER CRITICAS ATUAIS
			contratoMatrizOfertaCriticaDAO.removeAllCriticas();
			
			// REMOVER DADOS DA BASE
			contratoMatrizOfertaDAO.deleteAll();
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
	
	/**
	 * @param line 
	 * @return 
	 * 
	 * Método responsável em parsear uma linha em um TO.
	 */
	private ContratoMatrizOferta createTO(String line, String usuario, Integer numeroLinha) {
		
		ContratoMatrizOferta contratoMatrizOferta = new ContratoMatrizOferta();
		
		try {
			String[] lineArray = line.split(";");
			contratoMatrizOferta.setCodigoPlano(lineArray[0]);
			contratoMatrizOferta.setCodigoServico(lineArray[1]);
			contratoMatrizOferta.setSiglaCsa(lineArray[2]);
			try {
				contratoMatrizOferta.setValorContrato(lineArray[3]);
			} catch (ArrayIndexOutOfBoundsException e) {
				contratoMatrizOferta.setValorContrato(null);
			}
			contratoMatrizOferta.setDtCriacao(new Date());
			contratoMatrizOferta.setNmUsuarioCriacao(usuario);
			contratoMatrizOferta.setNumeroLinha(numeroLinha);
		} catch (ArrayIndexOutOfBoundsException e) {
			contratoMatrizOferta = null;
		}
		
		return contratoMatrizOferta;
	}
}
