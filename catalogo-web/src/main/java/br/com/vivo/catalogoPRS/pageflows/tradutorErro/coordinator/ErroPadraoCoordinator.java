package br.com.vivo.catalogoPRS.pageflows.tradutorErro.coordinator;

import com.framework.coordinator.Coordinator;
import com.framework.coordinator.Transaction;
import com.framework.exception.CoordinatorException;
import com.framework.exception.DAOException;
import com.framework.exception.FacadeException;
import com.framework.exception.NotFoundException;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ErroPadraoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroPadraoVO;

/**
 * Coordenador do ciclo de vida de ErroPadrao
 */
public class ErroPadraoCoordinator extends Coordinator
{
	private static ErroPadraoCoordinator instance = new ErroPadraoCoordinator();

	private ErroPadraoCoordinator()
	{
	}

	/**
	 * M�todo getInstance() do padr�o Singleton. <br>
	 * 
	 * @return com.framework.coordinator.Coordinator
	 * @throws com.framework.exception.CoordinatorException
	 */
	public static Coordinator getInstance() throws CoordinatorException
	{		
		return instance;
	}

	/**
	 * Retorna um VO para o erropadrao com ID especificado <br>ou null caso essa
	 * nao exista
	 * 
	 * @param id -
	 *          O ID do erropadrao a ser procurado
	 * @return um ErroPadraoVO para o ID especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso o idioma nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public ErroPadraoVO buscaErroPadrao(String id) throws FacadeException, NotFoundException
	{
		try
		{
			return (ErroPadraoVO) getVO(id);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro na busca da ErroPadrao.", ce, true);
		}
	}

	/**
	 * Retorna uma lista com os erropadraos
	 * 
	 * @return um PagedList com todos os erropadraos
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosErroPadraos() throws CoordinatorException
	{
		return buscaTodosErroPadraos("NONE", "ASC");
	}
	/**
	 * Retorna uma lista com os erropadraos
	 * 
	 * @return um PagedList com todos os erropadraos
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosErroPadraos(String order, String direction)
		throws CoordinatorException
	{
        Map<String, String> params = new HashMap<String, String>();
        params.put("SQLORD", order);
        params.put("SQLDIR", direction);
        return buscaErroPadraoPorParams(params);
	}

	/**
	 * Realiza a persistencia de um VO para o erropadrao <br>
	 * 
	 * @param ErroPadraoVO -
	 *          O VO do erropadrao a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteErroPadrao(ErroPadraoVO erropadraoVO) throws FacadeException
	{
		persisteErroPadrao(erropadraoVO, null);
	}

	/**
	 * Realiza a persistencia de um VO para o erroPadrao <br>
	 * 
	 * @param ErroPadraoVO -
	 *          O VO do erroPadrao a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteErroPadrao(ErroPadraoVO erroPadraoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = erroPadraoVO.getFlagNew();

		try
		{
			tx = joinTransaction(tx);
			
			persistVO(erroPadraoVO, tx);
			erroPadraoVO.setFlagNew(false);
			
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			erroPadraoVO.setFlagNew(flagNew);
			tx.rollBackTrans();

			throw new CoordinatorException("Erro salvando ErroPadrao: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um erroPadrao
	 * 
	 * @param erroPadraoVO -
	 *          O VO do erroPadrao a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroPadrao(ErroPadraoVO erroPadraoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = erroPadraoVO.getFlagNew();
		try
		{
			tx = joinTransaction(tx);
					
			erroPadraoVO.setFlagNew(false);
			removeVO(erroPadraoVO, tx);
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			erroPadraoVO.setFlagNew(flagNew);
			tx.rollBackTrans();
			
			throw new CoordinatorException("Erro removendo ErroPadrao: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um erroPadrao
	 * 
	 * @param erroPadraoVO -
	 *          O VO do erroPadrao a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroPadrao(ErroPadraoVO erroPadraoVO) throws FacadeException
	{
		try
		{
			removeErroPadrao(erroPadraoVO, null);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro removendo ErroPadrao: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um erroPadrao
	 * 
	 * @param id -
	 *          O id do erroPadrao a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroPadrao(String id, Transaction tx) throws FacadeException
	{
		ErroPadraoVO erroPadraoVO = new ErroPadraoVO(id);

		removeErroPadrao(erroPadraoVO, tx);
	}

	/**
	 * Realiza remocao de um erroPadrao
	 * 
	 * @param id -
	 *          O id do erroPadrao a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroPadrao(String id) throws FacadeException
	{
		ErroPadraoVO erroPadraoVO = new ErroPadraoVO(id);

		removeErroPadrao(erroPadraoVO, null);
	}
	
	/**
	 * Retorna um VO para o erroPadrao com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroPadrao para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroPadraoPorParams(Map params)
		throws CoordinatorException
	{
		try
		{
			return ((ErroPadraoDAO) getDAO()).findByParams(params);
		}
		catch (DAOException ce)
		{
			throw new CoordinatorException("Erro na busca de ErroPadrao.", ce);
		}
	}

	
	/**
	 * Retorna um VO para o erroPadrao com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroPadrao para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroPadraoPorCdClassificacao(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_CLASSIFICACAO = ", param);
		return buscaErroPadraoPorParams(params);
	}
	/**
	 * Retorna um VO para o erroPadrao com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroPadrao para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroPadraoPorCdErroPadrao(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_ERRO_PADRAO = ", param);
		return buscaErroPadraoPorParams(params);
	}
	/**
	 * Retorna um VO para o erroPadrao com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroPadrao para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroPadraoPorTxMensagemErroPadrao(String param)
		throws CoordinatorException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put( "TX_MENSAGEM_ERRO_PADRAO = ", param);
		return buscaErroPadraoPorParams(params);
	}
}