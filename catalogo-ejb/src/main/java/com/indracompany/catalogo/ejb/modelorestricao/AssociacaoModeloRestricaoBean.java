package com.indracompany.catalogo.ejb.modelorestricao;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoCompativelDAO;
import com.indracompany.catalogo.dao.interfaces.ModeloTipoProdutoRestricaoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoRestricaoTO;
import com.indracompany.catalogo.to.TipoProdutoTO;


/**
 * @author Luiz Pereira
 * 
 * EJB responsável em realizar as funções de Associacao Modelo Restricao
 */
@Stateless(name = "AssociacaoModeloRestricaoBean", mappedName = "AssociacaoModeloRestricaoBean")
@Session(ejbName = "AssociacaoModeloRestricaoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AssociacaoModeloRestricaoBean implements AssociacaoModeloRestricaoBeanLocal {
	
	private static Logger logger = Logger.getLogger(AssociacaoModeloRestricaoBean.class);
	
	@EJB
	private ModeloTipoProdutoCompativelDAO modeloTipoProdutoCompativelDAO;
	
	@EJB
	private ModeloTipoProdutoRestricaoDAO modeloTipoProdutoRestricaoDAO;


	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.ejb.modelorestricao.AssociacaoModeloRestricaoBeanLocal#createUpdateRestricoes(com.indracompany.catalogo.to.GrupoProdutoTO, java.util.Map)
	 */
	public void createUpdateRestricoes(GrupoProdutoTO grupoProdutoTO, Map<Integer, List<ModeloTipoProdutoCompativelTO>> map, String user) throws BusinessException {
		logger.debug("grupoProdutoTO: " + grupoProdutoTO);
		
		try {
			
			modeloTipoProdutoCompativelDAO.removeModeloTipoProdutoCompativel(grupoProdutoTO.getIdGrupoProduto());
			modeloTipoProdutoRestricaoDAO.removeModeloTipoProdutoRestricaoByGrupoProduto(grupoProdutoTO.getIdGrupoProduto());
			
			ModeloTipoProdutoRestricaoTO modeloTipoProdutoRestricaoTO = null;
			
			for (Integer id : map.keySet()) { 
				// SALVA A RESTRICAO
				modeloTipoProdutoRestricaoTO = modeloTipoProdutoRestricaoDAO.createUpdateModeloTipoProdutoRestricao(new ModeloTipoProdutoRestricaoTO(
						null, new Date(), user, null, null, grupoProdutoTO, new TipoProdutoTO(id)
				));
				
				// SALVA OS TIPOS DE PRODUTOS COMPATÍVEIS
				for (ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO : map.get(id)) {
					modeloTipoProdutoCompativelTO.setModeloTipoProdutoRestricaoTO(modeloTipoProdutoRestricaoTO);
					modeloTipoProdutoCompativelDAO.createUpdateModeloTipoProdutoCompativel(modeloTipoProdutoCompativelTO); 
				}
			}
			
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
}
