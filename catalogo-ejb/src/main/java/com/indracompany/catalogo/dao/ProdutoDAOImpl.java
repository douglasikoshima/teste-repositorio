package com.indracompany.catalogo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ProdutoDAO;
import com.indracompany.catalogo.datalayer.Produto;
import com.indracompany.catalogo.datalayer.TipoProduto;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;
import com.indracompany.catalogo.to.ProdutoTO;
import com.indracompany.catalogo.to.TipoProdutoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Produtos.
 */
@Stateless
public class ProdutoDAOImpl implements ProdutoDAO {
	
	private static Logger logger = Logger.getLogger(ProdutoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ProdutoDAO#search(com.indracompany.catalogo.to.ProdutoTO)
	 */
	@SuppressWarnings("unchecked")
	public List<ProdutoTO> search(ProdutoTO produtoTO) throws DAOException {
		logger.debug("produtoTO: " + produtoTO);
		
		List<ProdutoTO> list = null;
		
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			
			queryStr.append("select p from Produto p ");
			
			if (produtoTO.getSistemaProdutoTO() != null && produtoTO.getSistemaProdutoTO().getCdCodigo() != null && !produtoTO.getSistemaProdutoTO().getCdCodigo().equals("")) {
				queryStr.append(", SistemaProduto sp ");
			}
			
			if (produtoTO.getProdutoGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto() != null) {
				queryStr.append(", ProdutoGrupoProdutos pgp ");
			}
			
			queryStr.append(" where p.inDisponivel = 'S' ");
			
			if (produtoTO.getFabricanteTO() != null && produtoTO.getFabricanteTO().getIdFabricante() != null) {
				queryStr.append(" and p.fabricante.idFabricante = :idFabricante ");
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getIdTipoProduto() != null) {
				queryStr.append(" and p.tipoProduto.idTipoProduto = :idTipoProduto ");
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO().getIdTipoLinha() != null) {
				queryStr.append(" and p.tipoProduto.tipoLinha.idTipoLinha = :idTipoLinha ");
			}
			
			if (produtoTO.getSistemaProdutoTO() != null && produtoTO.getSistemaProdutoTO().getCdCodigo() != null && !produtoTO.getSistemaProdutoTO().getCdCodigo().equals("")) {
				queryStr.append(" and p.idProduto = sp.produto.idProduto ");
				queryStr.append(" and sp.cdCodigo = :cdCodigo ");
			}
			if (produtoTO.getNmProduto() != null && !produtoTO.getNmProduto().equals("")){
				queryStr.append(" and upper(p.nmProduto) = :nmProduto ");
			}
			
			if (produtoTO.getProdutoGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto() != null) {
				queryStr.append(" and p.idProduto = pgp.produto.idProduto ");
				queryStr.append(" and pgp.grupoProduto.idGrupoProduto = :idGrupoProduto ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (produtoTO.getFabricanteTO() != null && produtoTO.getFabricanteTO().getIdFabricante() != null) {
				query.setParameter("idFabricante", produtoTO.getFabricanteTO().getIdFabricante());
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getIdTipoProduto() != null) {
				query.setParameter("idTipoProduto", produtoTO.getTipoProdutoTO().getIdTipoProduto());
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO().getIdTipoLinha() != null) {
				query.setParameter("idTipoLinha", produtoTO.getTipoProdutoTO().getTipoLinhaTO().getIdTipoLinha());
			}
			
			if (produtoTO.getSistemaProdutoTO() != null && produtoTO.getSistemaProdutoTO().getCdCodigo() != null && !produtoTO.getSistemaProdutoTO().getCdCodigo().equals("")) {
				query.setParameter("cdCodigo", produtoTO.getSistemaProdutoTO().getCdCodigo());
			}

			if (produtoTO.getNmProduto() != null && !produtoTO.getNmProduto().equals("")){
				query.setParameter("nmProduto", produtoTO.getNmProduto().toUpperCase());
			}
			
			if (produtoTO.getProdutoGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto() != null) {
				query.setParameter("idGrupoProduto", produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto());
			}
			
			ProdutoTOBuilder produtoTOBuilder = new ProdutoTOBuilder();
			
			list = produtoTOBuilder.createProdutoTOList(query.getResultList());
		
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<AssociacaoAcaoProdutoTO> searchSemAssociacaoAcao(ProdutoTO produtoTO, Integer idAcao) throws DAOException {
		logger.debug("produtoTO: " + produtoTO);
		
		List<AssociacaoAcaoProdutoTO> list = null;
		
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select new  com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO(  ");
			queryStr.append("	p.idProduto ");
			queryStr.append(",	p.sistemaProduto.cdCodigo ");
			queryStr.append(",	p.nmProduto ");
			queryStr.append(",	pgp.grupoProduto.nmGrupoProduto ");
			queryStr.append(",	p.fabricante.nmFabricante ");
			queryStr.append(",	p.tipoProduto.nmTipoProduto ");
			queryStr.append(",	p.tipoProduto.tipoLinha.dscTipoLinha ");
			queryStr.append(",	pp.plataforma.idPlataforma ");
			queryStr.append(",	pp.plataforma.nmPlataforma ");
			queryStr.append(",	pov.organizacaoVenda.idOrganizacaoVendas  ");
			queryStr.append(",	pov.organizacaoVenda.sgOrganizacaoVendas ");
			queryStr.append("	) from ");
			queryStr.append("	ProdutoPlataforma pp ");
			queryStr.append(",	ProdutoOrganizacaoVendas pov ");
			queryStr.append(", 	ProdutoGrupoProduto pgp ");
			queryStr.append(",	Produto p ");
			queryStr.append(", SistemaProduto sp ");
			queryStr.append(" where p.inDisponivel = 'S' ");
			queryStr.append(" and pp.produto.idProduto = pov.produto.idProduto ");
			queryStr.append(" and pp.produto.idProduto = p.idProduto ");
			queryStr.append(" and p.idProduto = pgp.produto.idProduto ");
			queryStr.append(" and p.idProduto = sp.produto.idProduto ");
			queryStr.append(" and p.tipoProduto.idTipoProduto not in (7, 13, 17, 19) ");
			queryStr.append(" and ( ");
			queryStr.append(" 	pp.produto.idProduto not in (select ap.produto.idProduto from AcaoProduto ap where ap.acao.idAcao = :idAcao) ");
			queryStr.append(" 	or pp.plataforma.idPlataforma not in (select ap.plataforma.idPlataforma from AcaoProduto ap where ap.acao.idAcao = :idAcao) ");
			queryStr.append(" 	or pov.organizacaoVenda.idOrganizacaoVendas not in (select ap.organizacaoVenda.idOrganizacaoVendas from AcaoProduto ap where ap.acao.idAcao = :idAcao) ");
			queryStr.append(" ) ");
			queryStr.append(" and exists ( ");
			queryStr.append(" 	select ");
			queryStr.append(" 		ppd.produto.idProduto ");
			queryStr.append(" 	from ");
			queryStr.append(" 		PrecoProduto ppd ");
			queryStr.append(" 	where ");
			queryStr.append(" 		ppd.produto.idProduto = p.idProduto ");
			queryStr.append(" 		and ppd.plataforma.idPlataforma = pp.plataforma.idPlataforma ");
			queryStr.append(" 		and ppd.organizacaoVenda.idOrganizacaoVendas = pov.organizacaoVenda.idOrganizacaoVendas ");
			queryStr.append(" 		and ppd.idEscritorioVenda is not null ");
			queryStr.append(" ) ");
			
			if (produtoTO.getFabricanteTO() != null && produtoTO.getFabricanteTO().getIdFabricante() > 0) {
				queryStr.append(" and p.fabricante.idFabricante = :idFabricante ");
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getIdTipoProduto() > 0) {
				queryStr.append(" and p.tipoProduto.idTipoProduto = :idTipoProduto ");
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO().getIdTipoLinha() > 0) {
				queryStr.append(" and p.tipoProduto.tipoLinha.idTipoLinha = :idTipoLinha ");
			}
			
			if (produtoTO.getSistemaProdutoTO() != null && produtoTO.getSistemaProdutoTO().getCdCodigo() != null && !produtoTO.getSistemaProdutoTO().getCdCodigo().equals("")) {
				queryStr.append(" and sp.cdCodigo = :cdCodigo ");
			}
			
			if (produtoTO.getProdutoGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto() > 0) {
				queryStr.append(" and pgp.grupoProduto.idGrupoProduto = :idGrupoProduto ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idAcao", idAcao);

			if (produtoTO.getFabricanteTO() != null && produtoTO.getFabricanteTO().getIdFabricante() != null && produtoTO.getFabricanteTO().getIdFabricante()  > 0 ) {
				query.setParameter("idFabricante", produtoTO.getFabricanteTO().getIdFabricante());
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getIdTipoProduto() != null && produtoTO.getTipoProdutoTO().getIdTipoProduto() > 0) {
				query.setParameter("idTipoProduto", produtoTO.getTipoProdutoTO().getIdTipoProduto());
			}
			
			if (produtoTO.getTipoProdutoTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO() != null && produtoTO.getTipoProdutoTO().getTipoLinhaTO().getIdTipoLinha() > 0) {
				query.setParameter("idTipoLinha", produtoTO.getTipoProdutoTO().getTipoLinhaTO().getIdTipoLinha());
			}
			
			if (produtoTO.getSistemaProdutoTO() != null && produtoTO.getSistemaProdutoTO().getCdCodigo() != null && !produtoTO.getSistemaProdutoTO().getCdCodigo().equals("")) {
				query.setParameter("cdCodigo", produtoTO.getSistemaProdutoTO().getCdCodigo());
			}
			
			if (produtoTO.getProdutoGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO() != null && produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto() > 0) {
				query.setParameter("idGrupoProduto", produtoTO.getProdutoGrupoProdutoTO().getGrupoProdutoTO().getIdGrupoProduto());
			}
			
			list = query.getResultList();
		
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<AssociacaoAcaoProdutoTO> searchComAssociacaoAcao(Integer idAcao) throws DAOException {
		logger.debug("idAcao: " + idAcao);
		
		List<AssociacaoAcaoProdutoTO> list = null;
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select new  com.indracompany.catalogo.to.AssociacaoAcaoProdutoTO(  ");
			queryStr.append("	p.idProduto ");
			queryStr.append(",	p.sistemaProduto.cdCodigo ");
			queryStr.append(",	p.nmProduto ");
			queryStr.append(",	pgp.grupoProduto.nmGrupoProduto ");
			queryStr.append(",	p.fabricante.nmFabricante ");
			queryStr.append(",	p.tipoProduto.nmTipoProduto ");
			queryStr.append(",	p.tipoProduto.tipoLinha.dscTipoLinha ");
			queryStr.append(",	pp.plataforma.idPlataforma ");
			queryStr.append(",	pp.plataforma.nmPlataforma ");
			queryStr.append(",	pov.organizacaoVenda.idOrganizacaoVendas  ");
			queryStr.append(",	pov.organizacaoVenda.sgOrganizacaoVendas ");
			queryStr.append("	) from ");
			queryStr.append("	ProdutoPlataforma pp ");
			queryStr.append(",	ProdutoOrganizacaoVendas pov ");
			queryStr.append(", 	ProdutoGrupoProduto pgp ");
			queryStr.append(",	Produto p ");
			queryStr.append(", 	SistemaProduto sp ");
			queryStr.append(",	AcaoProduto ap ");
			queryStr.append(" where pp.produto.idProduto = pov.produto.idProduto ");
			queryStr.append(" and pp.produto.idProduto = p.idProduto ");
			queryStr.append(" and p.idProduto = pgp.produto.idProduto ");
			queryStr.append(" and p.idProduto = sp.produto.idProduto ");
			queryStr.append(" and p.idProduto = ap.produto.idProduto ");
			queryStr.append(" and ap.plataforma.idPlataforma = pp.plataforma.idPlataforma ");
			queryStr.append(" and ap.organizacaoVenda.idOrganizacaoVendas = pov.organizacaoVenda.idOrganizacaoVendas ");
			queryStr.append(" and ap.acao.idAcao = :idAcao ");
			
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idAcao", idAcao);
			
			list = query.getResultList();
		
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	    return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<ParametrizacaoProdutosTO> pesquisarParametrizacaoProdutos(ParametrizacaoProdutosTO pp){

		StringBuilder query = new StringBuilder();
		StringBuilder where = new StringBuilder(" where ");
		String seletcParametros = new String();
		Map<String,Object> parametros = new HashMap<String,Object>();
		query.append(" select PARAMETROS ");
		seletcParametros = " p.idProduto, sp.cdCodigo, p.dsSAP, p.dsProduto, tp.nmTipoProduto, " +
				" t.nmTecnologia, f.nmFabricante, gp.nmGrupoProduto, c.nmCor, c.rgb, p.nmProduto, gp.idGrupoProduto ";
		query.append(" from Produto p ");
		query.append(" join p.tipoProduto tp ");
		query.append(" join p.fabricante f ");
		query.append(" left join p.tecnologia t ");
		query.append(" left join p.sistemaProduto sp ");
		query.append(" left join p.produtoGrupoProdutos pgp ");
		query.append(" left join pgp.grupoProduto gp ");
		query.append(" left join p.cor c ");
		where.append(" tp.idTipoProduto = :idTipoProduto ");
		parametros.put("idTipoProduto", pp.getIdTipoProduto());
		where.append(" and f.idFabricante = :idFabricante ");
		parametros.put("idFabricante", pp.getIdFabricante());
		if(pp.getIdTecnologia()!= null){

			where.append(" and t.idTecnologia = :idTecnologia ");
			parametros.put("idTecnologia", pp.getIdTecnologia());
		}
		if(pp.getFiltrarModelos()){
		
			if( pp.getIdModelo() != null ){

				where.append(" and gp.idGrupoProduto = :idGrupoProduto ");
				parametros.put("idGrupoProduto", pp.getIdModelo());
			}else{
				
				where.append(" and pgp is not null ");
			}
		}else{
			
			where.append(" and pgp is null ");
		}
		
		if(pp.getCodigoProduto() != null){

			where.append(" and sp.cdCodigo like :cdCodigo ");
			parametros.put("cdCodigo", "%"+pp.getCodigoProduto()+"%");
		}
		query.append(where.toString());
		Query queryCount = em.createQuery(query.toString().replace("PARAMETROS", " count( DISTINCT p.idProduto ) "));
		for(Entry<String,Object> entry:parametros.entrySet()){

			queryCount.setParameter(entry.getKey(), entry.getValue());
		}
		Long registros = (Long)queryCount.getResultList().get(0);
		if(pp.getNomeCampoOrdenacao() != null){

			query.append(" order by ");
			query.append(pp.getNomeCampoOrdenacao());
			query.append(pp.getOrdemCrescente()?" asc ":" desc ");
		}
		Query select = em.createQuery(query.toString().replace("PARAMETROS", seletcParametros.toString()))
						.setFirstResult(pp.getRegistroAtual())
						.setMaxResults(pp.getRegistrosPorPagina());
		for(Entry<String,Object> entry:parametros.entrySet()){

			select.setParameter(entry.getKey(), entry.getValue());
		}
		List<Object[]> results = (List<Object[]>) select.getResultList();
		List<ParametrizacaoProdutosTO> produtos = new LinkedList<ParametrizacaoProdutosTO>();
		for(Object[] objs:results){

			ParametrizacaoProdutosTO produto = new ParametrizacaoProdutosTO();
			produto.setIdProduto((Integer)objs[0]);
			produto.setCodigoProduto((String)(objs[1]!=null?objs[1]:""));
			produto.setDescricaoSAP((String)(objs[2]!=null?objs[2]:""));
			produto.setDescricaoProdutoCatalogo((String)(objs[3]!=null?objs[3]:""));
			produto.setNomeTipoProduto((String)(objs[4]!=null?objs[4]:""));
			produto.setNomeTecnologia((String)(objs[5]==null?"":objs[5]));
			produto.setNomeFabricante((String)(objs[6]==null?"":objs[6]));
			produto.setNomeGrupoProduto((String)(objs[7]==null?"":objs[7]));
			produto.setNomeCor((String)(objs[8]==null?"":objs[8]));
			produto.setRgbCor((String)(objs[9]==null?"":objs[9]));
			produto.setNomeProduto((String)(objs[10]==null?"":objs[10]));
			produto.setIdGrupoProduto((Integer)(objs[11]==null?0:objs[11]));
			if (produtos.isEmpty()){

				produto.setRegistroAtual(pp.getRegistroAtual());
				produto.setRegistrosPorPagina(pp.getRegistrosPorPagina());
				produto.setTotalRegistros(registros.intValue());
			}
			produtos.add(produto);
		}
		return produtos;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ParametrizacaoProdutosTO consultar(ProdutoTO produto){
		
		StringBuilder query = new StringBuilder();
		query.append("select p.idProduto, sp.cdCodigo, p.nmProduto, p.dsSAP, p.dsProduto, ");
		query.append(" tp.idTipoProduto, p.dsNota, c.nmCor, f.idFabricante ");
		query.append(" from Produto p ");
		query.append(" join p.sistemaProduto sp ");
		query.append(" join p.tipoProduto tp ");
		query.append(" left join p.cor c ");
		query.append(" join p.fabricante f ");
		query.append(" where p.idProduto = :idProduto ");
		Query select = em.createQuery(query.toString());
		select.setParameter("idProduto", produto.getIdProduto());
		Object[] result = (Object[])select.getSingleResult();
		ParametrizacaoProdutosTO pp = new ParametrizacaoProdutosTO();
		pp.setIdProduto((Integer)result[0]);
		pp.setCodigoProduto((String)(result[1]!=null?result[1]:""));
		pp.setNomeProduto((String)(result[2]!=null?result[2]:""));
		pp.setDescricaoSAP((String)(result[3]!=null?result[3]:""));
		pp.setDescricaoProdutoCatalogo((String)(result[4]!=null?result[4]:""));
		pp.setIdTipoProduto((Integer)(result[5]==null?0:result[5]));
		pp.setDescricaoNota((String)(result[6]==null?"":result[6]));
		pp.setNomeCor((String)(result[7]==null?"":result[7]));
		Integer idFabricante = (Integer)result[8];
		query = new StringBuilder();
		query.append(" select tp.idTipoProduto, tp.nmTipoProduto ");
		query.append(" from FabricanteTipoProduto ftp ");
		query.append(" join ftp.fabricante f ");
		query.append(" join ftp.tipoProduto tp ");
		query.append(" where f.idFabricante = :idFabricante ");
		List<Object[]> results = (List<Object[]>) em.createQuery(query.toString()).setParameter("idFabricante", idFabricante).getResultList();
		pp.setTipoProdutos(new LinkedList());
		for(Object[] obj : results){
			
			TipoProdutoTO tp = new TipoProdutoTO((Integer)obj[0]);
			tp.setNmTipoProduto((String)obj[1]);
			pp.getTipoProdutos().add(tp);
		}
		return pp;
	}
	public boolean atualizarProduto(ParametrizacaoProdutosTO pp){
		
		Produto produto = em.find(Produto.class, pp.getIdProduto());
		if(pp.getIdTipoProduto() > 0){

			TipoProduto tipoProduto = em.find(TipoProduto.class, pp.getIdTipoProduto());
			produto.setTipoProduto(tipoProduto);
		}else{
			
			produto.setTipoProduto(null);
		}
		produto.setDsNota(pp.getDescricaoNota());
		produto.setNmProduto(pp.getNomeProduto());
		produto.setDtUltimaAlteracao(new Date());
		produto.setNmUsuarioAlteracao(pp.getNomeUsuarioAltercao());
		em.persist(produto);
		return true;
	}
}
