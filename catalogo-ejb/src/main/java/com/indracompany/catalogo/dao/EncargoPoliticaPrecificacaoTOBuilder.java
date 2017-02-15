package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Encargo;
import com.indracompany.catalogo.to.EncargoPoliticaPrecificacaoTO;
import com.indracompany.catalogo.to.PoliticaValorAtributoTO;

public class EncargoPoliticaPrecificacaoTOBuilder {
	
	public EncargoPoliticaPrecificacaoTO createTO(Encargo ent){
		EncargoPoliticaPrecificacaoTO to = new EncargoPoliticaPrecificacaoTO();
		TipoEncargoTOBuilder tipoEncargoTOBuilder = new TipoEncargoTOBuilder();
		PoliticaValorAtributoTOBuilder politicaValorAtributoTOBuilder = new PoliticaValorAtributoTOBuilder();
		
		if(ent != null){
			to.setIdEncargo(ent.getIdEncargo());
			to.setCdEncargo(ent.getCdEncargo());
			to.setDsEncargo(ent.getDsEncargo());
			to.setVlEncargo(ent.getVlEncargo());
			to.setCdMoeda(ent.getCdMoeda());
			to.setPzGratuidade(ent.getPzGratuidade());
			if(ent.getTipoEncargo() != null){
				to.setTipoEncargoTO(tipoEncargoTOBuilder.createTipoEncargoTO(ent.getTipoEncargo()));
			}
			if(ent.getValorPoliticaPrecificacao() != null){
				if(ent.getValorPoliticaPrecificacao().getEspServicoPacote() != null
						&& ent.getValorPoliticaPrecificacao().getEspServicoPacote().getServico() != null){
					to.setNmPacote(ent.getValorPoliticaPrecificacao().getEspServicoPacote().getServico().getNmComercial());
				}
				if(ent.getValorPoliticaPrecificacao().getEmpresaInstaladora() != null){
					to.setNmEmpresaInstaladora(ent.getValorPoliticaPrecificacao().getEmpresaInstaladora().getNmLocalidade());
				}
				if(ent.getValorPoliticaPrecificacao() != null
						&& ent.getValorPoliticaPrecificacao().getClasseServicoPrincipal() != null
						&& ent.getValorPoliticaPrecificacao().getClasseServicoPrincipal().getServico() != null){
					to.setNmClasseServicoPrincipal(ent.getValorPoliticaPrecificacao().getClasseServicoPrincipal().getServico().getNmComercial());
				}
				if(ent.getValorPoliticaPrecificacao().getPoliticaValorAtributoList() != null){
					to.setPoliticaValorAtributoTOList(politicaValorAtributoTOBuilder.createTOList(ent.getValorPoliticaPrecificacao().getPoliticaValorAtributoList()));
					if(to.getPoliticaValorAtributoTOList() != null && !to.getPoliticaValorAtributoTOList().isEmpty()){
						String atrDomConcat = "";
						for(PoliticaValorAtributoTO politicaValorAtributoTO : to.getPoliticaValorAtributoTOList()){
							if(politicaValorAtributoTO.getAtributoTO() != null){
								atrDomConcat = atrDomConcat + politicaValorAtributoTO.getAtributoTO().getSvcAttrNm() +": ";
							}
							if(politicaValorAtributoTO.getDominioAtributoFixaTO() != null){
								atrDomConcat = atrDomConcat + politicaValorAtributoTO.getDominioAtributoFixaTO().getTxValorDominioAtributoFixa()+" ; ";
							}
						}
						to.setPoliticaValorAtributoTOListConcat(atrDomConcat);
					}
				}
			}
		}
		return to;
	}
	
	public List<EncargoPoliticaPrecificacaoTO> createTOList(List<Encargo> entList){
		List<EncargoPoliticaPrecificacaoTO> toList = new ArrayList<EncargoPoliticaPrecificacaoTO>();
		for(Encargo ent : entList){
			toList.add(createTO(ent));
		}
		return toList;
	}
}
