package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.LocalidadeTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaDisponibilidadeTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.UfTO;

public interface OfertaFixaDAO {

    List<ServicoFixaTO> searchServicoLinha() throws DAOException;

    List<SolicitacaoComercialFixaTO> carregarSolicitacaoComercialPorServicoList(Integer idServicoFixa) throws DAOException;

    List<ServicoFixaTO> carregarServicoFixaTOPlanoList(Integer idServicoFixa) throws DAOException;

    List<OfertafixaTO> search(OfertafixaTO ofertafixaTO) throws DAOException;

    OfertafixaTO gravar(OfertafixaTO ofertafixaTO, OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO) throws DAOException;

    OfertafixaTO findByCodigoExceptId(String cdOfertafixa, Integer idOfertafixa) throws DAOException;

    OfertafixaTO findByNameExceptId(String nmOfertafixa, Integer idOfertafixa) throws DAOException;

    void excluir(Integer idOfertafixa) throws DAOException;

    OfertafixaTO findByIdWithComplementar(Integer idOfertafixa) throws DAOException;

    List<TipoServicoTO> carregarTipoServicoTOList() throws DAOException;

    List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico, OfertafixaTO ofertafixaTO) throws DAOException;

    void excluirOfertaComplementar(Integer idOfertafixaComplementar) throws DAOException;
    
    OfertafixaDisponibilidadeTO carregarDisponibilidadeTO(Integer idOfertafixa) throws DAOException;

    List<UfTO> obterUfTOList() throws DAOException;

    List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws DAOException;

    List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws DAOException;

    List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws DAOException;

    List<OfertafixaTO> reload(List<OfertafixaTO> ofertafixaTOList) throws DAOException;

	SolicitacaoComercialFixaTO findSolicitacaoComercialById(Long idSolicitacaoComercial) throws DAOException;

	CidadeTO findCidadeById(Integer idCidade) throws DAOException;

    List<EpsTO> listEpsTO() throws DAOException;

    List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws DAOException;

    CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws DAOException;

    AreaRegistroTO findAreaRegistroTOById(Integer idAreaRegistro) throws DAOException;

    List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws DAOException;

    List<OfertafixaComplementarTO> findOfertafixaComplementarTOWithDependentes(Long idSolicitacaoComercial, Integer pzMaximoVigencia) throws DAOException;

	List<Long> carregarIdCanalVendaListCompativel(Long idSolicitacaoComercial) throws DAOException;
}
