package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Canal;
import com.indracompany.catalogo.datalayer.MatrizOfertaItem;
import com.indracompany.catalogo.datalayer.MatrizOfertaItemPreco;
import com.indracompany.catalogo.datalayer.OrganizacaoVenda;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;
import com.indracompany.catalogo.to.ProdutoTO;

public class MatrizOfertaProdutoPrecoTOBuilder {

	public MatrizOfertaProdutoPrecoTO createTO(MatrizOfertaItemPreco ent){
		MatrizOfertaProdutoPrecoTO to = new MatrizOfertaProdutoPrecoTO();
		
		if(ent != null){
			to.setIdMatrizOfertaItemPreco(new Long(ent.getIdMatrizOfertaItemPreco()));
			to.setDtCriacao(ent.getDtCriacao());
			to.setDtUltimaAlteracao(ent.getDtUltimaAlteracao());
			to.setDtInicial(ent.getDtInicial());
			to.setDtFinal(ent.getDtFinal());
			to.setNmUsuarioCriacao(ent.getNmUsuarioCriacao());
			to.setNmUsuarioAlteracao(ent.getNmUsuarioAlteracao());
			to.setCdAdabas(ent.getCdAdabas());
			to.setValor(ent.getValor());
			MatrizOfertaItem matrizOfertaItem = ent.getMatrizOfertaItem();
			if(matrizOfertaItem != null){
				if(matrizOfertaItem.getOfertaSAP() != null){
					to.setOfertaSAPTO(new OfertaSAPTOBuilder().createTO(matrizOfertaItem.getOfertaSAP()));
				}
				if(matrizOfertaItem.getProduto() != null){
					ProdutoTO produtoTO = new ProdutoTO();
					produtoTO.setIdProduto(matrizOfertaItem.getProduto().getIdProduto());
					produtoTO.setCdProduto(matrizOfertaItem.getProduto().getSistemaProduto().getCdCodigo());
					produtoTO.setNmProduto(matrizOfertaItem.getProduto().getNmProduto());
					to.setProdutoTO(produtoTO);
				}
			}
			OrganizacaoVenda organizacaoVenda = ent.getOrganizacaoVenda();
			if(organizacaoVenda != null){
				OrganizacaoVendaTO organizacaoVendaTO = new OrganizacaoVendaTO();
				organizacaoVendaTO.setIdOrganizacaoVendas(organizacaoVenda.getIdOrganizacaoVendas());
				organizacaoVendaTO.setSgOrganizacaoVendas(organizacaoVenda.getSgOrganizacaoVendas());
				to.setOrganizacaoVendaTO(organizacaoVendaTO);
			}
			Canal canal = ent.getCanal();
			if(canal != null){
				CanalTO canalTO = new CanalTO();
				canalTO.setIdCanal(canal.getIdCanal());
				canalTO.setSgCanal(canal.getSgCanal());
				to.setCanalTO(canalTO);
/*				CanalAtendimento canalAtendimento = ent.getCanal().getCanalAtendimentoCanalList().get(0).getCanalAtendimento();
				if(canalAtendimento != null){
					CanalAtendimentoTO canalAtendimentoTO = new CanalAtendimentoTO();
					canalAtendimentoTO.setIdCanalAtendimento(canalAtendimento.getIdCanalAtendimento());
					canalAtendimentoTO.setSgVivoNet(canalAtendimento.getSgVivoNet());
					canalAtendimentoTO.setNmCanal(canalAtendimento.getNmCanal());
					to.setCanalAtendimentoTO(canalAtendimentoTO);
				}

*/
			}
/*
			EscritorioVenda escritorioVenda = ent.getEscritorioVenda();
			if(escritorioVenda != null){
				EscritorioVendaTO escritorioVendaTO = new EscritorioVendaTO();
				escritorioVendaTO.setIdEscritorioVenda(escritorioVenda.getIdEscritorioVenda());
				escritorioVendaTO.setSgEscritorioVenda(escritorioVenda.getSgEscritorioVenda());
				to.setEscritorioVendaTO(escritorioVendaTO);
			}
			Acao acao = ent.getAcao();
			if(acao != null){
				AcaoTO acaoTO = new AcaoTO();
				acaoTO.setIdAcao(acao.getIdAcao());
				acaoTO.setSgAcao(acao.getSgAcao());
				to.setAcaoTO(acaoTO);
			}
*/
		}
		
		return to;
	}
	
	public List<MatrizOfertaProdutoPrecoTO> createTOList(List<MatrizOfertaItemPreco> entList){
		List<MatrizOfertaProdutoPrecoTO> toList = new ArrayList<MatrizOfertaProdutoPrecoTO>(); 
		
		for(MatrizOfertaItemPreco ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<Long> getIdList(List<MatrizOfertaProdutoPrecoTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(MatrizOfertaProdutoPrecoTO to : toList){
			idList.add(to.getIdMatrizOfertaItemPreco());
		}
		return idList;
	}	
}