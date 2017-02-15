package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public class ServicoSolicitacaoComercialTOBuilder {
	
	public ServicoSolicitacaoComercialTO createTO(SolicitacaoComercial ent){
		PoliticaDispSlctComercialTOBuilder politicaDispSlctComercialTOBuilder = new PoliticaDispSlctComercialTOBuilder();
		ServicoSolicitacaoComercialTO to = new ServicoSolicitacaoComercialTO();

		to.setIdSolicitacaoComercial(ent.getIdSolicitacaoComercial());
		to.setCdSolicitacaoComercial(ent.getCdSolicitacaoComercial());
		to.setNmSolicitacaoComercial(ent.getNmSolicitacaoComercial());
		if(ent.getTipoSolicitacaoComercial() != null){
			to.setIdTipoSolicitacaoComercial(ent.getTipoSolicitacaoComercial().getIdTipoSolicitacaoComercial());
			to.setCdTipoSolicitacaoComercial(ent.getTipoSolicitacaoComercial().getCdTipoSolicitacaoComercial());
			to.setNmTipoSolicitacaoComercial(ent.getTipoSolicitacaoComercial().getNmTipoSolicitacaoComercial());
		}
		to.setPzMaximoAtendimento(ent.getPzMaximoAtendimento());
		to.setPzMaximoVigencia(ent.getPzMaximoVigencia());
		to.setInDisponivel(ent.getInDisponivel());
		to.setInOfertaClienteInadimplente(ent.getInOfertaClienteInadimplente());
		if(ent.getPoliticaDispSlctComercial() != null){
			to.setPoliticaDispSlctComercialTO(politicaDispSlctComercialTOBuilder.createTO(ent.getPoliticaDispSlctComercial()));
		}
		to.setIdSistema(ent.getSistemaServico().getSistema().getIdSistema());
		
		return to;
	}
	
	public List<ServicoSolicitacaoComercialTO> createTOList(List<SolicitacaoComercial> entList){
		
		List<ServicoSolicitacaoComercialTO> toList = new ArrayList<ServicoSolicitacaoComercialTO>();
		
		for(SolicitacaoComercial ent: entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
}
