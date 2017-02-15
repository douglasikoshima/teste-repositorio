package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.DetalheServicoFixaTO;
import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSegmentoTO;
import com.indracompany.catalogo.to.ServicoServicoTO;
import com.indracompany.catalogo.to.SistemaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public interface ServicoFixaDAO {
    
    public List<ServicoFixaTO> search(ServicoFixaTO servicoFixaTO) throws DAOException;
    
	public DetalheServicoFixaTO findDetalheServicoFixaById(DetalheServicoFixaTO detalheServicoFixaTO) throws DAOException;
	
	public void updateDetalheServicoFixa(DetalheServicoFixaTO detalheServicoFixaTO) throws DAOException;
	
    public String changeStatus(Integer id) throws DAOException;

    public List<RelacionamentoServicoFixaTO> searchRelationship(RelacionamentoServicoFixaTO relacionamentoServicoFixaTO) throws DAOException;

    public List<TipoRelacionamentoTO> findAllTpRelacionamento() throws DAOException;

    public List<TipoServicoTO> findAllTpServico() throws DAOException;

    public RelacionamentoServicoFixaTO findRelationshipById(Integer idRelacionamento) throws DAOException;

    public String changeStatusRelationship(Integer idRelacionamento) throws DAOException;

    public Long countRelationshipByServiceAndType(Integer idServico1, Integer idTipoRelacionamento) throws DAOException;

    public void removeRelationship(Integer idRelacionamento) throws DAOException, BusinessException;
    
    public void gravarRelacionamento(ServicoServicoTO servicoServicoTO) throws DAOException;
    
    public SistemaTO findSistemaByIdServico(ServicoFixaTO servicoFixaTO) throws DAOException;

    public SistemaTO findSistemaByIdServico(int idServico) throws DAOException;
	
    public List<CategorizacaoAnaliseCreditoTO> searchServicoFixaAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
    
    public List<ServicoFixaTO> searchServicoFixaSegmento(ServicoFixaTO servicoFixaTO) throws DAOException;
    
    public void saveServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws DAOException;

    public void disassociateServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws DAOException;
}
