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
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoNegocioDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;

/**
 * Coordenador do ciclo de vida de ServicoNegocio
 */
public class ServicoNegocioCoordinator extends Coordinator
{
	private static ServicoNegocioCoordinator instance = new ServicoNegocioCoordinator();

	private ServicoNegocioCoordinator()
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
	 * Retorna um VO para o serviconegocio com ID especificado <br>ou null caso essa
	 * nao exista
	 * 
	 * @param id -
	 *          O ID do serviconegocio a ser procurado
	 * @return um ServicoNegocioVO para o ID especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso o idioma nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public ServicoNegocioVO buscaServicoNegocio(String id) throws FacadeException, NotFoundException
	{
		try
		{
			return (ServicoNegocioVO) getVO(id);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro na busca da ServicoNegocio.", ce, true);
		}
	}

	/**
	 * Retorna uma lista com os serviconegocios
	 * 
	 * @return um PagedList com todos os serviconegocios
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosServicoNegocios() throws CoordinatorException
	{
		return buscaTodosServicoNegocios("CD_SERVICO_NEGOCIO", "ASC");
	}
	/**
	 * Retorna uma lista com os serviconegocios
	 * 
	 * @return um PagedList com todos os serviconegocios
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosServicoNegocios(String order, String direction)
		throws CoordinatorException
	{
        Map<String, String> params = new HashMap<String, String>();
        params.put("SQLORD", order);
        params.put("SQLDIR", direction);
        return buscaServicoNegocioPorParams(params);
	}

	/**
	 * Realiza a persistencia de um VO para o serviconegocio <br>
	 * 
	 * @param ServicoNegocioVO -
	 *          O VO do serviconegocio a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteServicoNegocio(ServicoNegocioVO serviconegocioVO) throws FacadeException
	{
		persisteServicoNegocio(serviconegocioVO, null);
	}

	/**
	 * Realiza a persistencia de um VO para o servicoNegocio <br>
	 * 
	 * @param ServicoNegocioVO -
	 *          O VO do servicoNegocio a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteServicoNegocio(ServicoNegocioVO servicoNegocioVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = servicoNegocioVO.getFlagNew();

		try
		{
			tx = joinTransaction(tx);
			
			persistVO(servicoNegocioVO, tx);
			servicoNegocioVO.setFlagNew(false);
			
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			servicoNegocioVO.setFlagNew(flagNew);
			tx.rollBackTrans();

			throw new CoordinatorException("Erro salvando ServicoNegocio: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um servicoNegocio
	 * 
	 * @param servicoNegocioVO -
	 *          O VO do servicoNegocio a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServicoNegocio(ServicoNegocioVO servicoNegocioVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = servicoNegocioVO.getFlagNew();
		try
		{
			tx = joinTransaction(tx);
					
			servicoNegocioVO.setFlagNew(false);
			removeVO(servicoNegocioVO, tx);
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			servicoNegocioVO.setFlagNew(flagNew);
			tx.rollBackTrans();
			
			throw new CoordinatorException("Erro removendo ServicoNegocio: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um servicoNegocio
	 * 
	 * @param servicoNegocioVO -
	 *          O VO do servicoNegocio a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServicoNegocio(ServicoNegocioVO servicoNegocioVO) throws FacadeException
	{
		try
		{
			removeServicoNegocio(servicoNegocioVO, null);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro removendo ServicoNegocio: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um servicoNegocio
	 * 
	 * @param id -
	 *          O id do servicoNegocio a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServicoNegocio(String id, Transaction tx) throws FacadeException
	{
		ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO(id);

		removeServicoNegocio(servicoNegocioVO, tx);
	}

	/**
	 * Realiza remocao de um servicoNegocio
	 * 
	 * @param id -
	 *          O id do servicoNegocio a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServicoNegocio(String id) throws FacadeException
	{
		ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO(id);

		removeServicoNegocio(servicoNegocioVO, null);
	}
	
	/**
	 * Retorna um VO para o servicoNegocio com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um servicoNegocio para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaServicoNegocioPorParams(Map params)
		throws CoordinatorException
	{
		try
		{
			return ((ServicoNegocioDAO) getDAO()).findByParams(params);
		}
		catch (DAOException ce)
		{
			throw new CoordinatorException("Erro na busca de ServicoNegocio.", ce);
		}
	}

	
	/**
	 * Retorna um VO para o servicoNegocio com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um servicoNegocio para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaServicoNegocioPorCdServicoNegocio(Integer param)
		throws CoordinatorException
	{
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put( "CD_SERVICO_NEGOCIO = ", param);
		return buscaServicoNegocioPorParams(params);
	}
	/**
	 * Retorna um VO para o servicoNegocio com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um servicoNegocio para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaServicoNegocioPorDsServicoNegocio(String param)
		throws CoordinatorException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put( "DS_SERVICO_NEGOCIO = ", param);
		return buscaServicoNegocioPorParams(params);
	}
}