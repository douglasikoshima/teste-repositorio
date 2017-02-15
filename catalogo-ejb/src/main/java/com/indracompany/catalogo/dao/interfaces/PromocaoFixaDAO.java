package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.ValorPoliticaPromocaoTO;

public interface PromocaoFixaDAO {

    List<PromocaoTO> search(PromocaoTO promocaoTO) throws DAOException;

    PromocaoTO merge(PromocaoTO promocaoTO) throws DAOException;

    List<ComposicaoPromocaoTO> findComposicaoByIdPromocao(Integer idPromocao) throws DAOException;

    List<TipoServicoTO> findTipoServicoTO() throws DAOException;

    List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico) throws DAOException;

    List<ServicoFixaTO> findServicoDescontoByIdServico(Integer idServico) throws DAOException;

    List<SolicitacaoComercialFixaTO> findSolicitacaoComercialByIdServico(Integer idServico) throws DAOException;

    void removeComposicaoById(Integer idComposicao) throws DAOException;

    boolean persist(ComposicaoPromocaoTO composicaoPromocaoTO) throws DAOException;

    List<DisponibilidadePromocaoFixaTO> obterCanalVendaPorPromocao(Integer idPromocao) throws DAOException;
    
    void removeDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws DAOException;
    
    void persistDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws DAOException;

    void remove(Integer idPromocao) throws DAOException;

    PromocaoTO findById(Integer idPromocao) throws DAOException;

    PromocaoTO findByCodigoExceptId(String codigo, Integer idPromocao) throws DAOException;

    PromocaoTO findByNameExceptId(String nome, Integer idPromocao) throws DAOException;

    List<PromocaoTO> reload(List<PromocaoTO> promocaoTOList) throws DAOException;
    
    List<EpsTO> listEpsTO() throws DAOException;
    
    List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws DAOException;
    
    CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws DAOException;
    
    List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda) throws DAOException;
    
    List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda, Integer idPromocao) throws DAOException;
}
