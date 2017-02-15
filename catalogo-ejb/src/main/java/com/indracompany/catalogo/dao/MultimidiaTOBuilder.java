package com.indracompany.catalogo.dao;

import java.util.LinkedList;
import java.util.List;

import com.indracompany.catalogo.datalayer.ClassificacaoMultimidia;
import com.indracompany.catalogo.datalayer.Cor;
import com.indracompany.catalogo.datalayer.GrupoProduto;
import com.indracompany.catalogo.datalayer.Multimidia;
import com.indracompany.catalogo.datalayer.TipoMultimidia;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;
import com.indracompany.catalogo.to.CorTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.TipoMultimidiaTO;

public class MultimidiaTOBuilder {
	
	private boolean criarTipo;
	private boolean criarCor;
	private boolean criarGrupoProduto;
	private boolean criarClassificacao;
	
	public MultimidiaTOBuilder() {
		this(true);
	}
	
	public MultimidiaTOBuilder(boolean criar) {
		criarTipo = criar;
		criarCor = criar;
		criarGrupoProduto = criar;
		criarClassificacao = criar;
	}
	
	public Multimidia createMultimidia(MultimidiaTO multimidiaTO) {
		Multimidia multimidia = null;
		if (multimidiaTO != null) {
			
			multimidia = new Multimidia();
			multimidia.setNomeMultimidia(multimidiaTO.getNomeMultimidia());
			multimidia.setDataCriacao(multimidiaTO.getDataCriacao());
			multimidia.setDataUltimaAlteracao(multimidiaTO.getDataUltimaAlteracao());
			multimidia.setNomeUsuarioCriacao(multimidiaTO.getNomeUsuarioCriacao());
			multimidia.setNomeUsuarioUltimaAlteracao(multimidiaTO.getNomeUsuarioUltimaAlteracao());
			
			TipoMultimidiaTO tipoMultimidiaTO = multimidiaTO.getTipoMultimidiaTO();
			if (tipoMultimidiaTO != null) {
				multimidia.setTipoMultimidia(new TipoMultimidia(tipoMultimidiaTO.getIdTipoMultimidia()));
			}
			
			ClassificacaoMultimidiaTO classificacaoTO = multimidiaTO.getClassificacaoTO();
			if (classificacaoTO != null) {
				multimidia.setClassificacao(new ClassificacaoMultimidia(classificacaoTO.getIdClassificacao()));
			}
			
			CorTO corTO = multimidiaTO.getCorTO();
			if (corTO != null) {
				multimidia.setCor(new Cor(corTO.getIdCor()));
			}
			
			GrupoProdutoTO grupoProdutoTO = multimidiaTO.getGrupoProdutoTO();
			if (grupoProdutoTO != null) {
				multimidia.setGrupoProduto(new GrupoProduto(grupoProdutoTO.getIdGrupoProduto()));
			}
		}
		return multimidia;
	}
	
	public MultimidiaTO createMultimidiaTO(Multimidia multimidia) {
		MultimidiaTO multimidiaTO = new MultimidiaTO();
		
		multimidiaTO.setIdMultimidia(multimidia.getIdMultimidia());
		multimidiaTO.setNomeMultimidia(multimidia.getNomeMultimidia());
		multimidiaTO.setDataCriacao(multimidia.getDataCriacao());
		multimidiaTO.setDataUltimaAlteracao(multimidia.getDataUltimaAlteracao());
		multimidiaTO.setNomeUsuarioCriacao(multimidia.getNomeUsuarioCriacao());
		multimidiaTO.setNomeUsuarioUltimaAlteracao(multimidia.getNomeUsuarioUltimaAlteracao());
		
		if (criarTipo && multimidia.getTipoMultimidia() != null) {
			TipoMultimidiaTOBuilder tipoMultimidiaTOBuilder = new TipoMultimidiaTOBuilder();
			multimidiaTO.setTipoMultimidiaTO(tipoMultimidiaTOBuilder.createTipoMultimidiaTO(multimidia.getTipoMultimidia()));
		}
		
		if (criarClassificacao && multimidia.getClassificacao() != null) {
			ClassificacaoMultimidiaTOBuilder classificacaoTOBuilder = new ClassificacaoMultimidiaTOBuilder();
			multimidiaTO.setClassificacaoTO(classificacaoTOBuilder.createClassificacaoMultimidiaTO(multimidia.getClassificacao()));
		}
		
		if (criarCor && multimidia.getCor() != null) {
			CorTOBuilder corTOBuilder = new CorTOBuilder();
			multimidiaTO.setCorTO(corTOBuilder.createCorTO(multimidia.getCor()));
		}
		
		if (criarGrupoProduto && multimidia.getGrupoProduto() != null) {
			GrupoProdutoTOBuilder grupoProdutoTOBuilder = new GrupoProdutoTOBuilder();
			multimidiaTO.setGrupoProdutoTO(grupoProdutoTOBuilder.createGrupoProdutoTO(multimidia.getGrupoProduto()));
		}
		
		return multimidiaTO;
	}
	
	public List<MultimidiaTO> createMultimidiaTOList(List<Multimidia> multimidiaList){
		List<MultimidiaTO> multimidiaTOList = new LinkedList<MultimidiaTO>();
		for (Multimidia multimidia : multimidiaList) {
			multimidiaTOList.add(createMultimidiaTO(multimidia));
		}
		return multimidiaTOList;
	}
	
	public boolean isCriarTipo() {
		return criarTipo;
	}
	
	public void setCriarTipo(boolean criarTipo) {
		this.criarTipo = criarTipo;
	}
	
	public boolean isCriarCor() {
		return criarCor;
	}
	
	public void setCriarCor(boolean criarCor) {
		this.criarCor = criarCor;
	}
	
	public boolean isCriarGrupoProduto() {
		return criarGrupoProduto;
	}
	
	public void setCriarGrupoProduto(boolean criarGrupoProduto) {
		this.criarGrupoProduto = criarGrupoProduto;
	}
	
	public boolean isCriarClassificacao() {
		return criarClassificacao;
	}
	
	public void setCriarClassificacao(boolean criarClassificacao) {
		this.criarClassificacao = criarClassificacao;
	}
	
}