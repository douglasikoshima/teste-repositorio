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
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.SistemaLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.SistemaLegadoVO;

/**
 * Coordenador do ciclo de vida de SistemaLegado
 */
public class SistemaLegadoCoordinator extends Coordinator
{
	private static SistemaLegadoCoordinator instance = new SistemaLegadoCoordinator();

	private SistemaLegadoCoordinator()
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
	 * Retorna um VO para o sistemalegado com ID especificado <br>ou null caso essa
	 * nao exista
	 * 
	 * @param id -
	 *          O ID do sistemalegado a ser procurado
	 * @return um SistemaLegadoVO para o ID especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso o idioma nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public SistemaLegadoVO buscaSistemaLegado(String id) throws FacadeException, NotFoundException
	{
		try
		{
			return (SistemaLegadoVO) getVO(id);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro na busca da SistemaLegado.", ce, true);
		}
	}

	/**
	 * Retorna uma lista com os sistemalegados
	 * 
	 * @return um PagedList com todos os sistemalegados
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosSistemaLegados() throws CoordinatorException
	{
		return buscaTodosSistemaLegados("CD_SISTEMA_LEGADO", "ASC");
	}
	/**
	 * Retorna uma lista com os sistemalegados
	 * 
	 * @return um PagedList com todos os sistemalegados
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosSistemaLegados(String order, String direction)
		throws CoordinatorException
	{
        Map<String, String> params = new HashMap<String, String>();
        params.put("SQLORD", order);
        params.put("SQLDIR", direction);
        return buscaSistemaLegadoPorParams(params);
	}

	/**
	 * Realiza a persistencia de um VO para o sistemalegado <br>
	 * 
	 * @param SistemaLegadoVO -
	 *          O VO do sistemalegado a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteSistemaLegado(SistemaLegadoVO sistemalegadoVO) throws FacadeException
	{
		persisteSistemaLegado(sistemalegadoVO, null);
	}

	/**
	 * Realiza a persistencia de um VO para o sistemaLegado <br>
	 * 
	 * @param SistemaLegadoVO -
	 *          O VO do sistemaLegado a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteSistemaLegado(SistemaLegadoVO sistemaLegadoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = sistemaLegadoVO.getFlagNew();

		try
		{
			tx = joinTransaction(tx);
			
			persistVO(sistemaLegadoVO, tx);
			sistemaLegadoVO.setFlagNew(false);
			
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			sistemaLegadoVO.setFlagNew(flagNew);
			tx.rollBackTrans();

			throw new CoordinatorException("Erro salvando SistemaLegado: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um sistemaLegado
	 * 
	 * @param sistemaLegadoVO -
	 *          O VO do sistemaLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeSistemaLegado(SistemaLegadoVO sistemaLegadoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = sistemaLegadoVO.getFlagNew();
		try
		{
			tx = joinTransaction(tx);
					
			sistemaLegadoVO.setFlagNew(false);
			removeVO(sistemaLegadoVO, tx);
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			sistemaLegadoVO.setFlagNew(flagNew);
			tx.rollBackTrans();
			
			throw new CoordinatorException("Erro removendo SistemaLegado: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um sistemaLegado
	 * 
	 * @param sistemaLegadoVO -
	 *          O VO do sistemaLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeSistemaLegado(SistemaLegadoVO sistemaLegadoVO) throws FacadeException
	{
		try
		{
			removeSistemaLegado(sistemaLegadoVO, null);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro removendo SistemaLegado: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um sistemaLegado
	 * 
	 * @param id -
	 *          O id do sistemaLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeSistemaLegado(String id, Transaction tx) throws FacadeException
	{
		SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO(id);

		removeSistemaLegado(sistemaLegadoVO, tx);
	}

	/**
	 * Realiza remocao de um sistemaLegado
	 * 
	 * @param id -
	 *          O id do sistemaLegado a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeSistemaLegado(String id) throws FacadeException
	{
		SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO(id);

		removeSistemaLegado(sistemaLegadoVO, null);
	}
	
	/**
	 * Retorna um VO para o sistemaLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um sistemaLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaSistemaLegadoPorParams(Map params)
		throws CoordinatorException
	{
		try
		{
			return ((SistemaLegadoDAO) getDAO()).findByParams(params);
		}
		catch (DAOException ce)
		{
			throw new CoordinatorException("Erro na busca de SistemaLegado.", ce);
		}
	}

	
	/**
	 * Retorna um VO para o sistemaLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um sistemaLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaSistemaLegadoPorCdSistemaLegado(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_SISTEMA_LEGADO = ", param);
		return buscaSistemaLegadoPorParams(params);
	}
	/**
	 * Retorna um VO para o sistemaLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um sistemaLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaSistemaLegadoPorDsSistemaLegado(String param)
		throws CoordinatorException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put( "DS_SISTEMA_LEGADO = ", param);
		return buscaSistemaLegadoPorParams(params);
	}
	/**
	 * Retorna um VO para o sistemaLegado com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um sistemaLegado para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaSistemaLegadoPorNmAplicacaoHeader(String param)
		throws CoordinatorException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put( "NM_APLICACAO_HEADER = ", param);
		return buscaSistemaLegadoPorParams(params);
	}
}