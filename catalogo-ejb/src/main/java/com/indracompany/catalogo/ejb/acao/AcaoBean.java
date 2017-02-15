package com.indracompany.catalogo.ejb.acao;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.AcaoAcessoFornecedorDAO;
import com.indracompany.catalogo.dao.interfaces.AcaoDAO;
import com.indracompany.catalogo.dao.interfaces.AcaoProdutoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoAcessoFornecedorTO;
import com.indracompany.catalogo.to.AcaoProdutoTO;
import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;
import com.indracompany.catalogo.to.PlataformaTO;
import com.indracompany.catalogo.to.ProdutoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Ação
 */
@Stateless(name = "AcaoBean", mappedName = "AcaoBean")
@Session(ejbName = "AcaoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AcaoBean implements AcaoBeanLocal {
	
	private static Logger logger = Logger.getLogger(AcaoBean.class);
	
	@EJB
	private AcaoDAO acaoDAO;
	
	@EJB
	private AcaoAcessoFornecedorDAO acaoAcessoFornecedorDAO;
	
	@EJB
	private AcaoProdutoDAO acaoProdutoDAO;
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.AcaoBeanLocal#searchAcao(com.indracompany.catalogo.to.AcaoTO)
	 */
	public List<AcaoTO> searchAcao(AcaoTO acaoTO) throws BusinessException {
		logger.debug("acaoTO: " + acaoTO);
		
		List<AcaoTO> list = null; 
		
		try {
			
			list = acaoDAO.searchAcao(acaoTO);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		
		return list;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.AcaoBeanLocal#createUpdateAcao(com.indracompany.catalogo.to.AcaoTO)
	 */
	public void createUpdateAcao(AcaoTO acaoTO) throws BusinessException {
		logger.debug("acaoTO: " + acaoTO);
		
		try {
			
			if (acaoTO.getIdAcao() == null ) {
				AcaoTO acaoSearchTO = new AcaoTO();
				acaoSearchTO.setSgAcao(acaoTO.getSgAcao());
				
				if (acaoDAO.findBySigla(acaoSearchTO) != null) {
					throw new BusinessException("J&aacute; existe A&ccedil;&atilde;o de Venda com esta sigla. Informe outro valor");
				}
			}
			
			AcaoTO acaoResultTO = acaoDAO.createUpdateAcao(acaoTO);
			
			if (acaoTO.getIdAcao() != null ) {
				acaoAcessoFornecedorDAO.removeAcaoAcessoFornecedor(new AcaoAcessoFornecedorTO(null, null, acaoTO.getIdAcao(), new Date(), acaoTO.getNmUsuarioCriacao()));
			}
			
			if (acaoTO.getPerfilList() != null) {
			
				for (Integer idPerfil : acaoTO.getPerfilList()) {
					for (Integer idFornecedor : acaoTO.getFornecedorList()) {
						acaoAcessoFornecedorDAO.createUpdateAcaoAcessoFornecedor(new AcaoAcessoFornecedorTO(idPerfil, idFornecedor, acaoResultTO.getIdAcao(), new Date(), acaoTO.getNmUsuarioCriacao()));
					}
				}
			}
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.AcaoBeanLocal#findById(com.indracompany.catalogo.to.AcaoTO)
	 */
	public AcaoTO findById(AcaoTO acaoTO) throws BusinessException {
		logger.debug("acaoTO: " + acaoTO);
		
		AcaoTO acaoResultTO = null;
		
		try {
			acaoResultTO = acaoDAO.findById(acaoTO);
			
			List<Integer> perfilList = acaoAcessoFornecedorDAO.findPerfilByAcao(new AcaoAcessoFornecedorTO(null, null, acaoTO.getIdAcao(), new Date(), acaoTO.getNmUsuarioCriacao()));
			acaoResultTO.setPerfilList(perfilList.toArray(new Integer[perfilList.size()]));
			
			List<Integer> fornecedorList = acaoAcessoFornecedorDAO.findFornecedorByAcao(new AcaoAcessoFornecedorTO(null, null, acaoTO.getIdAcao(), new Date(), acaoTO.getNmUsuarioCriacao()));
			acaoResultTO.setFornecedorList(fornecedorList.toArray(new Integer[fornecedorList.size()]));
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
		return acaoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.AcaoBeanLocal#removeAcao(com.indracompany.catalogo.to.AcaoTO)
	 */
	public void removeAcao(AcaoTO acaoTO) throws BusinessException {
		logger.debug("acaoTO: " + acaoTO);
		
		try {
	
			if (acaoDAO.existAssociation(acaoTO)) {
				throw new BusinessException("N&atilde;o &eacute; poss&iacute;vel efetuar a exclus&atilde;o pois existem ofertas precificadas para essa A&ccedil;&atilde;o de Vendas.");
			}
			
			if (acaoDAO.findById(acaoTO).getIsVigente().equals("S")) {
				throw new BusinessException("N&atilde;o &eacute; poss&iacute;vel efetuar a exclus&atilde;o pois a A&ccedil;&atilde;o de Vendas ainda est&aacute; vigente");
			}
			acaoProdutoDAO.removeAssociationAcaoProduto(new AcaoProdutoTO(null, acaoTO, null, null, null));
			acaoAcessoFornecedorDAO.removeAcaoAcessoFornecedor(new AcaoAcessoFornecedorTO(null, null, acaoTO.getIdAcao(), new Date(), acaoTO.getNmUsuarioCriacao()));
			acaoDAO.removeAcao(acaoTO);
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.AcaoBeanLocal#createAssociacaoAcaoProduto(java.lang.Integer, java.lang.Integer[])
	 */
	public void createAssociacaoAcaoProduto(AcaoTO acaoTO , String[] idsProduto) throws BusinessException {
		
		AcaoProdutoTO acaoProdutoTO = null;
		
		try {
			for (String idProduto : idsProduto) {
				acaoProdutoTO = new AcaoProdutoTO();
				acaoProdutoTO.setProdutoTO(new ProdutoTO(Integer.parseInt(idProduto.split("\\|")[0])));
				acaoProdutoTO.setPlataformaTO(new PlataformaTO(Integer.parseInt(idProduto.split("\\|")[1])));
				acaoProdutoTO.setOrganizacaoVendaTO(new OrganizacaoVendaTO(Integer.parseInt(idProduto.split("\\|")[2])));
				acaoProdutoTO.setAcaoTO(new AcaoTO(acaoTO.getIdAcao()));
				acaoProdutoTO.setCanalTO(new CanalTO(40));
				acaoProdutoTO.setNmUsuarioCriacao(acaoTO.getNmUsuarioCriacao());
				acaoProdutoTO.setDtCriacao(acaoTO.getDtCriacao());
				acaoProdutoDAO.createUpdateAcaoProduto(acaoProdutoTO);
			}
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.acao.AcaoBeanLocal#removeAssociacaoAcaoProduto(java.lang.Integer, java.lang.Integer[])
	 */
	public void removeAssociacaoAcaoProduto(AcaoTO acaoTO, String[] idsProduto) throws BusinessException {
		
		AcaoProdutoTO acaoProdutoTO = null;
		
		try {
			for (String idProduto : idsProduto) {
				acaoProdutoTO = new AcaoProdutoTO();
				acaoProdutoTO.setProdutoTO(new ProdutoTO(Integer.parseInt(idProduto.split("\\|")[0])));
				acaoProdutoTO.setPlataformaTO(new PlataformaTO(Integer.parseInt(idProduto.split("\\|")[1])));
				acaoProdutoTO.setOrganizacaoVendaTO(new OrganizacaoVendaTO(Integer.parseInt(idProduto.split("\\|")[2])));
				acaoProdutoTO.setAcaoTO(new AcaoTO(acaoTO.getIdAcao()));
				acaoProdutoTO.setCanalTO(new CanalTO(40));
				acaoProdutoDAO.removeAcaoProduto(acaoProdutoTO);
			}
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}