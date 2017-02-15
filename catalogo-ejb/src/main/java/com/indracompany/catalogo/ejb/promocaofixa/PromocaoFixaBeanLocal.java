package com.indracompany.catalogo.ejb.promocaofixa;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.ValorPoliticaPromocaoTO;

@Local
public interface PromocaoFixaBeanLocal {

    public static final String JNDI_NAME = "java:comp/env/PromocaoFixaBean";

    List<PromocaoTO> search(PromocaoTO promocaoTO) throws BusinessException;

    PromocaoTO reccord(PromocaoTO promocaoTO) throws BusinessException;

    List<ComposicaoPromocaoTO> findComposicaoByIdPromocao(Integer idPromocao) throws BusinessException;

    List<TipoServicoTO> findTipoServicoTO() throws BusinessException;

    List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico) throws BusinessException;

    List<ServicoFixaTO> findServicoDescontoByIdServico(Integer idServico) throws BusinessException;

    List<SolicitacaoComercialFixaTO> findSolicitacaoComercialByIdServico(Integer idServico) throws BusinessException;

    boolean removeComposicaoById(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) throws BusinessException;

    boolean persist(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) throws BusinessException;

    List<DisponibilidadePromocaoFixaTO> obterCanalVendaPorPromocao(Integer idPromocao) throws BusinessException;
    
    void removeDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws BusinessException;
    
    void persistDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws BusinessException;

    void remove(Integer idPromocao) throws BusinessException;

    PromocaoTO findById(Integer idPromocao) throws BusinessException;

    PromocaoTO findByCodigoExceptId(String codigo, Integer idPromocao) throws BusinessException;

    PromocaoTO findByNameExceptId(String nome, Integer idPromocao) throws BusinessException;

    List<PromocaoTO> reload(List<PromocaoTO> promocaoTOList) throws BusinessException;
    
    List<EpsTO> listEpsTO() throws BusinessException;
    
    List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws BusinessException;
    
    CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws BusinessException;
    
    List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda) throws BusinessException;
    
    List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda, Integer idPromocao) throws BusinessException;
}
