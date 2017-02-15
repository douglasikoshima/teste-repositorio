package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.VwFixaExportScEnc;
import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;

public class SCxENCxPFxGCxPMxACRelatorioFixaTOBuilder {
	
	
	public SCxENCxPFxGCxPMxACRelatorioFixaTO createTO(VwFixaExportScEnc ent){
		SCxENCxPFxGCxPMxACRelatorioFixaTO to = new SCxENCxPFxGCxPMxACRelatorioFixaTO();
		
		if(ent != null){
			to.setNumeroRegistro(ent.getNumeroRegistro());
			to.setCdServico(ent.getCdServico());
			to.setCdAreaConcorrencia(ent.getCdAreaConcorrencia());
			to.setCdEncargo(ent.getCdEncargo());
			to.setCdGrupoComercial(ent.getCdGrupoComercial());
			to.setCdPlanoFinanciamento(ent.getCdPlanoFinanciamento());
			to.setCdPlanoMinutos(ent.getCdPlanoMinutos());
			to.setCdSolicitacaoComercial(ent.getCdSolicitacaoComercial());
			to.setCdTipoSolicitacaoComercial(ent.getCdTipoSolicitacaoComercial());
			to.setDsEncargo(ent.getDsEncargo());
			to.setDescricao(ent.getDescricao());
			to.setNmAreaConcorrencia(ent.getNmAreaConcorrencia());
			to.setNmGrupoComercial(ent.getNmGrupoComercial());
			to.setNmPlanoFinanciamento(ent.getNmPlanoFinanciamento());
			to.setNmPlanoMinutos(ent.getNmPlanoMinutos());
			to.setNmServico(ent.getNmServico());
			to.setNmSolicitacaoComercial(ent.getNmSolicitacaoComercial());
			to.setNmTipoSolicitacaoComercial(ent.getNmSolicitacaoComercial());
		}
		
		return to;
	}
	
	public List<SCxENCxPFxGCxPMxACRelatorioFixaTO> createTOList(List<VwFixaExportScEnc> entList){
		List<SCxENCxPFxGCxPMxACRelatorioFixaTO> toList = new ArrayList<SCxENCxPFxGCxPMxACRelatorioFixaTO>();
		
		if(entList != null){
			for(VwFixaExportScEnc ent : entList){
				toList.add(createTO(ent));
			}
		}
		
		return toList;
	}
}
