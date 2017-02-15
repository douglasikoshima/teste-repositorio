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
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ErroLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroLegadoVO;

/**
 * Coordenador do ciclo de vida de ErroLegado
 */
public class ErroLegadoCoordinator extends Coordinator
{
	private static ErroLegadoCoordinator instance = new ErroLegadoCoordinator();

	private ErroLegadoCoordinator()
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
	 * Retorna um VO para o errolegado com ID especificado <br>ou null caso essa
	 * nao exista
	 * 
	 * @param id -
	 *          O ID do errolegado a ser procurado
	 * @return um ErroLegadoVO para o ID especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso o idioma nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public ErroLegadoVO buscaErroLegado(String id) throws FacadeException, NotFoundException
	{
		try
		{
			return (ErroLegadoVO) getVO(id);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro na busca da ErroLegado.", ce, true);
		}
	}

	/**
	 * Retorna uma lista com os errolegados
	 * 
	 * @return um PagedList com todos os errolegados
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosErroLegados() throws CoordinatorException
	{
		return buscaTodosErroLegados("NONE", "ASC");
	}
	/**
	 * Retorna uma lista com os errolegados
	 * 
	 * @return um PagedList com todos os errolegados
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosErroLegados(String order, String direction)
		throws CoordinatorException
	{
        Map<String, String> params = new HashMap<String, String>();
        params.put("SQLORD", order);
        params.put("SQLDIR", direction);
        return buscaErroLegadoPorParams(params);
	}

	/**
	 * Realiza a persistencia de um VO para o errolegado <br>
	 * 
	 * @param ErroLegadoVO -
	 *          O VO do errolegado a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteErroLegado(ErroLegadoVO errolegadoVO) throws FacadeException
	{
		persisteErroLegado(errolegadoVO, null);
	}

	/**
	 * Realiza a persistencia de um VO para o erroLegado <br>
	 * 
	 * @param ErroLegadoVO -
	 *          O VO do erroLegado a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteErroLegado(ErroLegadoVO erroLegadoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = erroLegadoVO.getFlagNew();

		try
		{
			tx = joinTransaction(tx);
			
			persistVO(erroLegadoVO, tx);
			erroLegadoVO.setFlagNew(false);
			
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			erroLegadoVO.setFlagNew(flagNew);
			tx.rollBackTrans();

			throw new CoordinatorException("Erro salvando ErroLegado: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um erroLegado
	 * 
	 * @param erroLegadoVO -
	 *          O VO do erroLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroLegado(ErroLegadoVO erroLegadoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = erroLegadoVO.getFlagNew();
		try
		{
			tx = joinTransaction(tx);
					
			erroLegadoVO.setFlagNew(false);
			removeVO(erroLegadoVO, tx);
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			erroLegadoVO.setFlagNew(flagNew);
			tx.rollBackTrans();
			
			throw new CoordinatorException("Erro removendo ErroLegado: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um erroLegado
	 * 
	 * @param erroLegadoVO -
	 *          O VO do erroLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroLegado(ErroLegadoVO erroLegadoVO) throws FacadeException
	{
		try
		{
			removeErroLegado(erroLegadoVO, null);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro removendo ErroLegado: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um erroLegado
	 * 
	 * @param id -
	 *          O id do erroLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroLegado(String id, Transaction tx) throws FacadeException
	{
		ErroLegadoVO erroLegadoVO = new ErroLegadoVO(id);

		removeErroLegado(erroLegadoVO, tx);
	}

	/**
	 * Realiza remocao de um erroLegado
	 * 
	 * @param id -
	 *          O id do erroLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeErroLegado(String id) throws FacadeException
	{
		ErroLegadoVO erroLegadoVO = new ErroLegadoVO(id);

		removeErroLegado(erroLegadoVO, null);
	}
	
	/**
	 * Retorna um VO para o erroLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroLegadoPorParams(Map params)
		throws CoordinatorException
	{
		try
		{
			return ((ErroLegadoDAO) getDAO()).findByParams(params);
		}
		catch (DAOException ce)
		{
			throw new CoordinatorException("Erro na busca de ErroLegado.", ce);
		}
	}

	
	/**
	 * Retorna um VO para o erroLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroLegadoPorCdErroLegado(String param)
		throws CoordinatorException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put( "CD_ERRO_LEGADO = ", param);
		return buscaErroLegadoPorParams(params);
	}
	/**
	 * Retorna um VO para o erroLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroLegadoPorCdErroPadrao(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_ERRO_PADRAO = ", param);
		return buscaErroLegadoPorParams(params);
	}
	/**
	 * Retorna um VO para o erroLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroLegadoPorCdServico(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_SERVICO = ", param);
		return buscaErroLegadoPorParams(params);
	}
	/**
	 * Retorna um VO para o erroLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um erroLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaErroLegadoPorCdSistemaLegado(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_SISTEMA_LEGADO = ", param);
		return buscaErroLegadoPorParams(params);
	}
}