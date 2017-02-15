package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Bandeira;
import com.indracompany.catalogo.to.BandeiraTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class BandeiraTOBuilder {
	
	/**
	 * @param bandeiraTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Bandeira createBandeira(BandeiraTO bandeiraTO) {
		
		Bandeira bandeira = null;
		
		if (bandeiraTO != null) {
			bandeira = new Bandeira();
			bandeira.setCdBandeiraSAP(bandeiraTO.getCdBandeiraSAP());
			bandeira.setCdInstituicaoFinanceira(bandeiraTO.getCdInstituicaoFinanceira());
			bandeira.setDtCriacao(bandeiraTO.getDtCriacao());
			bandeira.setDtUltimaAlteracao(bandeiraTO.getDtUltimaAlteracao());
			bandeira.setNmBandeira(bandeiraTO.getNmBandeira());
			bandeira.setNmUsuarioCriacao(bandeiraTO.getNmUsuarioCriacao());
			bandeira.setNmUsuarioUltimaAlteracao(bandeiraTO.getNmUsuarioUltimaAlteracao());
			bandeira.setUrlImagem(bandeiraTO.getUrlImagem());
			bandeira.setVlParcelaMinima(bandeiraTO.getVlParcelaMinima());
			bandeira.setIdBandeira(bandeiraTO.getIdBandeira());
			
		}
		return bandeira;
	}
	
	/**
	 * @param bandeira
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public BandeiraTO createBandeiraTO(Bandeira bandeira) {
		
		BandeiraTO bandeiraTO = null;
		
		if (bandeira != null) {
			bandeiraTO = new BandeiraTO();
			bandeiraTO.setCdBandeiraSAP(bandeira.getCdBandeiraSAP());
			bandeiraTO.setCdInstituicaoFinanceira(bandeira.getCdInstituicaoFinanceira());
			bandeiraTO.setDtCriacao(bandeira.getDtCriacao());
			bandeiraTO.setDtUltimaAlteracao(bandeira.getDtUltimaAlteracao());
			bandeiraTO.setIdBandeira(bandeira.getIdBandeira());
			bandeiraTO.setNmBandeira(bandeira.getNmBandeira());
			bandeiraTO.setNmUsuarioCriacao(bandeira.getNmUsuarioCriacao());
			bandeiraTO.setNmUsuarioUltimaAlteracao(bandeira.getNmUsuarioUltimaAlteracao());
			bandeiraTO.setUrlImagem(bandeira.getUrlImagem());
			bandeiraTO.setVlParcelaMinima(bandeira.getVlParcelaMinima());
			
			FormaPagamentoBandeiraTOBuilder formaPagamentoBandeiraTOBuilder = new FormaPagamentoBandeiraTOBuilder();
			bandeiraTO.setFormaPagamentoBandeiraTOList(formaPagamentoBandeiraTOBuilder.createFormaPagamentoBandeiraTOList(bandeira.getFormaPagamentoBandeiraList()));
		}
		
		return bandeiraTO;
	}
	
	/**
	 * @param bandeiraList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<BandeiraTO> createBandeiraTOList(List<Bandeira> bandeiraList) {
		
		List<BandeiraTO> list = new ArrayList<BandeiraTO>();
		
		if (bandeiraList != null && bandeiraList.size() > 0) {
			for (Bandeira bandeira : bandeiraList) {
				list.add(createBandeiraTO(bandeira));
			}
		}
		
		return list;
	}
}
