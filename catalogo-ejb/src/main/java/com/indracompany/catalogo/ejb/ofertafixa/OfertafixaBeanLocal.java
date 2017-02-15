package com.indracompany.catalogo.ejb.ofertafixa;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaDisponibilidadeTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Local
public interface OfertafixaBeanLocal {

    public static final String JNDI_NAME = "java:comp/env/OfertafixaBean";
    
    List<ServicoFixaTO> searchServicoLinha() throws BusinessException;

    List<SolicitacaoComercialFixaTO> carregarSolicitacaoComercialPorServicoList(Integer idServicoFixa) throws BusinessException;

    List<ServicoFixaTO> carregarServicoFixaTOPlanoList(Integer idServicoFixa) throws BusinessException;

    List<OfertafixaTO> search(OfertafixaTO ofertafixaTO) throws BusinessException;

    OfertafixaTO gravar(OfertafixaTO ofertafixaTO, OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO) throws BusinessException;

    OfertafixaTO findByCodigoExceptId(String cdOfertafixa, Integer idOfertafixa) throws BusinessException;

    OfertafixaTO findByNameExceptId(String nmOfertafixa, Integer idOfertafixa) throws BusinessException;

    void excluir(Integer idOfertafixa) throws BusinessException;

    OfertafixaTO findByIdWithComplementar(Integer idOfertafixa) throws BusinessException;

    List<TipoServicoTO> carregarTipoServicoTOList() throws BusinessException;

    List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico, OfertafixaTO ofertafixaTO) throws BusinessException;

    void excluirOfertaComplementar(Integer idOfertafixaComplementar) throws BusinessException;

    OfertafixaDisponibilidadeTO carregarDisponibilidadeTO(Integer idOfertafixa) throws BusinessException;

    List<OfertafixaTO> reload(List<OfertafixaTO> ofertafixaTOList) throws BusinessException;

	SolicitacaoComercialFixaTO findSolicitacaoComercialById(Long idSolicitacaoComercial) throws BusinessException;

	CidadeTO findCidadeById(Integer idCidade) throws BusinessException;

    CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws BusinessException;

    AreaRegistroTO findAreaRegistroTOById(Integer idAreaRegistro) throws BusinessException;

    List<OfertafixaComplementarTO> findOfertafixaComplementarTOWithDependentes(Long idSolicitacaoComercial, Integer pzMaximoVigencia) throws BusinessException;

	List<Long> carregarIdCanalVendaListCompativel(Long idSolicitacaoComercial) throws BusinessException;
}
