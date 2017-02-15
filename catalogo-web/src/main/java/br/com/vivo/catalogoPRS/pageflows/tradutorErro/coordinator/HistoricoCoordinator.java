package br.com.vivo.catalogoPRS.pageflows.tradutorErro.coordinator;

import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.HistoricoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

import com.framework.coordinator.Coordinator;
import com.framework.coordinator.Transaction;
import com.framework.exception.CoordinatorException;
import com.framework.exception.DAOException;
import com.framework.exception.FacadeException;
import com.framework.exception.NotFoundException;

public class HistoricoCoordinator extends Coordinator {

	public Collection buscaHistoricoPorParams(Map params)
			throws CoordinatorException {
		try {
			return ((HistoricoDAO) getDAO()).findByParams(params);
		} catch (DAOException ce) {
			throw new CoordinatorException("Erro na busca de Historico.", ce);
		}
	}
	
	public HistoricoVO buscaServico(String id) throws FacadeException, NotFoundException
	{
		try
		{
			return (HistoricoVO) getVO(id);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro na busca da Servico.", ce, true);
		}
	}
	
	public void persisteServico(HistoricoVO historicoVO) throws FacadeException
	{
		persisteServico(historicoVO, null);
	}

	public void persisteServico(HistoricoVO historicoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = historicoVO.getFlagNew();

		try
		{
			tx = joinTransaction(tx);
			
			persistVO(historicoVO, tx);
			historicoVO.setFlagNew(false);
			
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			historicoVO.setFlagNew(flagNew);
			tx.rollBackTrans();

			throw new CoordinatorException("Erro salvando Servico: ", ce, true);
		}
	}

	public void removeServico(HistoricoVO historicoVO) throws FacadeException
	{
		try
		{
			removeServico(historicoVO, null);
		}
		catch (CoordinatorException ce)
		{
			throw new CoordinatorException("Erro removendo Servico: ", ce, true);
		}
	}
	 
	
	public void removeServico(HistoricoVO historicoVO, Transaction tx) throws FacadeException
	{
		boolean flagNew = historicoVO.getFlagNew();
		try
		{
			tx = joinTransaction(tx);
					
			historicoVO.setFlagNew(false);
			removeVO(historicoVO, tx);
			tx.commitTrans();
		}
		catch (Exception ce)
		{
			historicoVO.setFlagNew(flagNew);
			tx.rollBackTrans();
			
			throw new CoordinatorException("Erro removendo Servico: ", ce, true);
		}
	}
	
	
	
	
	
}
