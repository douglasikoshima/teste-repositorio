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
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoVO;

/**
 * Coordenador do ciclo de vida de Servico
 */
public class ServicoCoordinator extends Coordinator
{
	private static ServicoCoordinator instance = new ServicoCoordinator();

	private ServicoCoordinator()
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
	 * Retorna um VO para o servico com ID especificado <br>ou null caso essa
	 * nao exista
	 * 
	 * @param id -
	 *          O ID do servico a ser procurado
	 * @return um ServicoVO para o ID especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso o idioma nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public ServicoVO buscaServico(String id) throws FacadeException, NotFoundException
	{
		try
		{
			return (ServicoVO) getVO(id);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro na busca da Servico.", ce, true);
		}
	}

	/**
	 * Retorna uma lista com os servicos
	 * 
	 * @return um PagedList com todos os servicos
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosServicos() throws CoordinatorException
	{
		return buscaTodosServicos("NONE", "ASC");
	}
	/**
	 * Retorna uma lista com os servicos
	 * 
	 * @return um PagedList com todos os servicos
	 * @throws com.framework.exception.CoordinatorException
	 *           para qualquer erro
	 */
	public Collection buscaTodosServicos(String order, String direction)
		throws CoordinatorException
	{
        Map<String, String> params = new HashMap<String, String>();
        params.put("SQLORD", order);
        params.put("SQLDIR", direction);
        return buscaServicoPorParams(params);
	}

	/**
	 * Realiza a persistencia de um VO para o servico <br>
	 * 
	 * @param ServicoVO -
	 *          O VO do servico a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteServico(ServicoVO servicoVO) throws FacadeException
	{
		persisteServico(servicoVO, null);
	}

	/**
	 * Realiza a persistencia de um VO para o servico <br>
	 * 
	 * @param ServicoVO -
	 *          O VO do servico a ser persistido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void persisteServico(ServicoVO servicoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = servicoVO.getFlagNew();

		try
		{
			tx = joinTransaction(tx);
			
			persistVO(servicoVO, tx);
			servicoVO.setFlagNew(false);
			
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			servicoVO.setFlagNew(flagNew);
			tx.rollBackTrans();

			throw new CoordinatorException("Erro salvando Servico: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um servico
	 * 
	 * @param servicoVO -
	 *          O VO do servico a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServico(ServicoVO servicoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = servicoVO.getFlagNew();
		try
		{
			tx = joinTransaction(tx);
					
			servicoVO.setFlagNew(false);
			removeVO(servicoVO, tx);
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			servicoVO.setFlagNew(flagNew);
			tx.rollBackTrans();
			
			throw new CoordinatorException("Erro removendo Servico: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um servico
	 * 
	 * @param servicoVO -
	 *          O VO do servico a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServico(ServicoVO servicoVO) throws FacadeException
	{
		try
		{
			removeServico(servicoVO, null);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro removendo Servico: ", ce, true);
		}
	}

	/**
	 * Realiza remocao de um servico
	 * 
	 * @param id -
	 *          O id do servico a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServico(String id, Transaction tx) throws FacadeException
	{
		ServicoVO servicoVO = new ServicoVO(id);

		removeServico(servicoVO, tx);
	}

	/**
	 * Realiza remocao de um servico
	 * 
	 * @param id -
	 *          O id do servico a ser removido
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer erro ocorrido
	 */
	public void removeServico(String id) throws FacadeException
	{
		ServicoVO servicoVO = new ServicoVO(id);

		removeServico(servicoVO, null);
	}
	
	/**
	 * Retorna um VO para o servico com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um servico para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaServicoPorParams(Map params) throws CoordinatorException {
		try
		{
			return ((ServicoDAO) getDAO()).findByParams(params);
		}
		catch (DAOException ce)
		{
			throw new CoordinatorException("Erro na busca de Servico.", ce);
		}
	}

	
	/**
	 * Retorna um VO para o servico com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um servico para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaServicoPorCdServico(Double param)
		throws CoordinatorException
	{
		Map<String, Double> params = new HashMap<String, Double>();
		params.put( "CD_SERVICO = ", param);
		return buscaServicoPorParams(params);
	}
	/**
	 * Retorna um VO para o servico com param especificado <br>ou null caso
	 * essa nao exista
	 * 
	 * @param param -
	 *          Parametro de Busca
	 * @return um servico para o param especificado, caso exista
	 * @throws com.framework.exception.NotFoundException
	 *           caso VO nao seja encontrada
	 * @throws com.framework.exception.FacadeException
	 *           para qualquer outro erro
	 */
	public Collection buscaServicoPorDsServico(String param)
		throws CoordinatorException
	{
		Map<String, String> params = new HashMap<String, String>();
		params.put( "DS_SERVICO = ", param);
		return buscaServicoPorParams(params);
	}
}