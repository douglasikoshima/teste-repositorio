package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Categoria;
import com.indracompany.catalogo.datalayer.Encargo;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.to.DetalheServicoFixaTO;
import com.indracompany.catalogo.to.SegmentoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;

public class ServicoFixaTOBuilder {

    public ServicoFixaTO getTO(Object[] entity) {
        ServicoFixaTO to = null;
        if (entity != null) {
            to = new ServicoFixaTO();
            to.setIdSistema((Integer)entity[0]);
            to.setNmSistema((String)entity[1]);
            to.setCdServico((String)entity[2]);
            to.setCdPS((String)entity[3]);
            to.setNmComercial((String)entity[4]);
            to.setInDisponivel(((String)entity[5]).equalsIgnoreCase("S"));
            to.setIdServico((Integer)entity[6]);
            to.setIdTipoServico((Integer) entity[7]);
            to.setNmTipoServico((String) entity[8]);
            to.setNomeDoServicoOrigem((String) entity[9]);
            if (entity.length > 10) {
                to.setCdCategoria((String)entity[10]);
                if (entity.length > 11) {
                    to.setCdTipoServico((String)entity[11]);
                }
            }
        }
        return to;
    }
    
    public List<ServicoFixaTO> getTOList(List<Object[]> entityList) {
        List<ServicoFixaTO> toList = new ArrayList<ServicoFixaTO>();
        for (Object[] entity : entityList) {
            toList.add(this.getTO(entity));
        }
        return toList;
    }
    
	public DetalheServicoFixaTO createDetalheServicoFixaTO(Servico ent){
		AtributoTOBuilder atributoTOBuilder = new AtributoTOBuilder();
		DetalheServicoFixaTO to = new DetalheServicoFixaTO();
		
		to.setIdServico(ent.getIdServico());
		to.setNmComercialCatalogo(ent.getNmComercial());
		to.setNmComercialOrigem(ent.getDescricao());
		to.setDescricaoCatalogo(ent.getDescricao());
		to.setDescricaoOrigem(ent.getDescricao());
		to.setInVendaIsolada(ent.getInVendaIsolada());
		to.setInPreFactibilidade(ent.getInPreFactibilidade());
		to.setInPosFactibilidade(ent.getInPosFactibilidade());
		to.setCdSiscom(ent.getCdSiscom());
		to.setQtPercentualAuditoria(ent.getQtPercentualAuditoria());
		to.setNuOrdemAtendimento(ent.getNuOrdemAtendimento());
		to.setInDisponivel(ent.getInDisponivel());
		if(ent.getSistemaServico() != null){
            to.setCdServicoOrigem(ent.getSistemaServico().getComplementoLegado() == null ? null : ent.getSistemaServico().getComplementoLegado().getCdPS());
			to.setCdCodigo(ent.getSistemaServico().getCdCodigo());
			if(ent.getSistemaServico().getSistema() != null)
				to.setNmSistema(ent.getSistemaServico().getSistema().getNmSistema());
			if(ent.getSistemaServico().getComplementoLegado() != null){
				to.setCdClasseServicoAdicional(ent.getSistemaServico().getComplementoLegado().getCdClasseServicoAdicional());
				to.setCdClasseServicoPrincipal(ent.getSistemaServico().getComplementoLegado().getCdClasseServicoPrincipal());
				to.setCdTipoEquipamento(ent.getSistemaServico().getComplementoLegado().getCdTipoEquipamento());
			}
			if(ent.getSistemaServico().getServicoAtributoList() != null && !ent.getSistemaServico().getServicoAtributoList().isEmpty()){
				to.setAtributoTOList(atributoTOBuilder.createTOList(ent.getSistemaServico().getAtributoList()));
			}
			if(ent.getSistemaServico().getSolicitacaoComercialList() != null){
				if(ent.getSistemaServico().getSolicitacaoComercialList().isEmpty()){
					to.setInPossuiVarDispDesconto(Boolean.FALSE);
					to.setInPossuiVarDispFinanciamento(Boolean.FALSE);
				} else {
					to.setInPossuiVarDispDesconto(Boolean.TRUE);
					if(ent.getSistemaServico().getSolicitacaoComercialList().get(0).getPoliticaDispSlctComercial() == null){
						to.setInAreaConcorrenciaDesconto("N");
						to.setInPlanoMinutoDesconto("N");
					} else {
						to.setInAreaConcorrenciaDesconto(ent.getSistemaServico().getSolicitacaoComercialList().get(0).getPoliticaDispSlctComercial().getInAreaConcorrencia());
						to.setInPlanoMinutoDesconto(ent.getSistemaServico().getSolicitacaoComercialList().get(0).getPoliticaDispSlctComercial().getInPlanoMinuto());
					}
					searchVarDispFinanciamento:
					for(SolicitacaoComercial solicitacaoComercial : ent.getSistemaServico().getSolicitacaoComercialList()){
						for(Encargo encargo : solicitacaoComercial.getEncargoList()){
							if(!encargo.getCondicaoPagamentoEncargoList().isEmpty()){
								to.setInPossuiVarDispFinanciamento(Boolean.TRUE);
								if(encargo.getCondicaoPagamentoEncargoList().get(0).getPoliticaDispCndcPagamento() == null){
									to.setInAreaConcorrenciaFinanciamento("N");
									to.setInPlanoMinutoFinanciamento("N");
								} else {
									to.setInAreaConcorrenciaFinanciamento(encargo.getCondicaoPagamentoEncargoList().get(0).getPoliticaDispCndcPagamento().getInAreaConcorrencia());
									to.setInPlanoMinutoFinanciamento(encargo.getCondicaoPagamentoEncargoList().get(0).getPoliticaDispCndcPagamento().getInPlanoMinuto());
								}
								break searchVarDispFinanciamento;
							}
						}
					}
				}
			}
			if(ent.getSistemaServico().getAtributoList() != null && !ent.getSistemaServico().getAtributoList().isEmpty()){
				to.setAtributoTOList(atributoTOBuilder.createTOList(ent.getSistemaServico().getAtributoList()));
			}
		}
		if(ent.getTipoServico() != null){
			to.setCdTipoServico(ent.getTipoServico().getCdTipoServico());
			to.setNmTipoServico(ent.getTipoServico().getNmTipoServico());
		}
		if(ent.getEspServicoPlanoMinuto() != null){
			to.setQtMinutoLivreFMLocal(ent.getEspServicoPlanoMinuto().getQtMinutoLivreFMLocal());
			to.setQtMinutoLivreFFLocal(ent.getEspServicoPlanoMinuto().getQtMinutoLivreFFLocal());
			to.setVlMinutoAdicionalFFLocal(ent.getEspServicoPlanoMinuto().getVlMinutoAdicionalFFlocal());
		}
		if(ent.getEspServicoPacote() != null){
			to.setCdPacote(ent.getEspServicoPacote().getCdPacote());
			if(ent.getEspServicoPacote().getTipoPrecoPacote() != null)
				to.setCdTipoPrecoPacote(ent.getEspServicoPacote().getTipoPrecoPacote().getCdTipoPrecoPacote());
		}
		if(ent.getServicoTecnologiaList() != null 
				&& !ent.getServicoTecnologiaList().isEmpty() 
				&& ent.getServicoTecnologiaList().get(0).getTecnologia() != null)
			to.setNmTecnologia(ent.getServicoTecnologiaList().get(0).getTecnologia().getNmTecnologia());
		if(ent.getEspServicoDesconto() != null){
			to.setSgTipoAplicacaoDesconto(ent.getEspServicoDesconto().getSgTipoAplicacaoDesconto());
			to.setVlDescontoAbsoluto(ent.getEspServicoDesconto().getVlDescontoAbsoluto());
		}
		if(ent.getPoliticaPrecificacao() != null
				&& !ent.getPoliticaPrecificacao().getAtributoList().isEmpty()){
			to.setAtributoPoliticaPrecificacaoTOList(atributoTOBuilder.createTOList(ent.getPoliticaPrecificacao().getAtributoList()));
		}
        to.setIdCategoria(ent.getCategoria().getIdCategoria());
        to.setIdFamilia(ent.getCategoria().getFamilia().getIdFamilia());
		
		return to;
	}
	
	public Servico updateEntity(Servico ent, DetalheServicoFixaTO to){

		if(ent != null && to != null){
            if (!ent.getNmComercial().equals(to.getNmComercialCatalogo())) {
                ent.setNmComercial(to.getNmComercialCatalogo());
                ent.setInAlteracaoCatalogo("S");
            }
			ent.setInPreFactibilidade(to.getInPreFactibilidade());
			ent.setInPosFactibilidade(to.getInPosFactibilidade());
			ent.setCdSiscom(to.getCdSiscom());
			ent.setInDisponivel(to.getInDisponivel());
            ent.setCategoria(new Categoria(to.getIdCategoria()));
		}
		
		return ent;
	}
    
    public ServicoFixaTO buildBasicTO(Servico entity) {
        ServicoFixaTO to = null;
        if(entity != null) {
            to = new ServicoFixaTO();
            to.setIdServico(entity.getIdServico());
            to.setCdServico(entity.getCdServico());
            to.setNmComercial(entity.getNmComercial());
            to.setInDisponivel(entity.getInDisponivel().equalsIgnoreCase("S"));
            to.setIdTipoServico(entity.getTipoServico().getIdTipoServico());
            to.setNmTipoServico(entity.getTipoServico().getNmTipoServico());
            to.setCdPS(entity.getSistemaServico().getComplementoLegado().getCdPS());
            if(entity.getSegmentoList() != null && !entity.getSegmentoList().isEmpty()){
            	to.setSegmentoTO(new SegmentoTOBuilder().createSegmentoTO(entity.getSegmentoList().get(0)));
            }
        }
        return to;
    }

    public ServicoFixaTO buildServicoSegmentoTO(Servico entity) {
        ServicoFixaTO to = null;
        if(entity != null) {
            to = new ServicoFixaTO();
            to.setIdServico(entity.getIdServico());
            to.setCdServico(entity.getCdServico());
            to.setNmComercial(entity.getNmComercial());
            to.setInDisponivel(entity.getInDisponivel().equalsIgnoreCase("S"));
            to.setIdTipoServico(entity.getTipoServico().getIdTipoServico());
            to.setNmTipoServico(entity.getTipoServico().getNmTipoServico());
            if(entity.getServicoSegmento() != null && entity.getServicoSegmento().getSegmento() != null){
            	SegmentoTO segmentoTO = new SegmentoTOBuilder().createSegmentoTO(entity.getServicoSegmento().getSegmento());
            	segmentoTO.setInInfancia(entity.getServicoSegmento().getInInfancia());
            	to.setSegmentoTO(segmentoTO);
            }
        }
        return to;
    }
    
    public ServicoFixaTO buildServicoFixaSegmentoTO(Servico entity){
        ServicoFixaTO to = null;
        if(entity != null) {
            to = new ServicoFixaTO();
            to.setIdServico(entity.getIdServico());
            to.setCdServico(entity.getCdServico());
            to.setNmComercial(entity.getNmComercial());
            to.setInDisponivel(entity.getInDisponivel().equalsIgnoreCase("S"));
            to.setIdTipoServico(entity.getTipoServico().getIdTipoServico());
            to.setNmTipoServico(entity.getTipoServico().getNmTipoServico());
            if(entity.getSegmentoList() != null && !entity.getSegmentoList().isEmpty()){
            	to.setSegmentoTO(new SegmentoTOBuilder().createSegmentoTO(entity.getSegmentoList().get(0)));
            }
        }
        return to;
    	
    }
    
    public List<ServicoFixaTO> buildServicoSegmentoTOList(List<Servico> entityList) {
        List<ServicoFixaTO> servicoFixaTOList = new ArrayList<ServicoFixaTO>();
        for(Servico entity : entityList) {
            servicoFixaTOList.add(this.buildServicoSegmentoTO(entity));
        }
        return servicoFixaTOList;
    }

    public List<ServicoFixaTO> buildBasicTOList(List<Servico> entityList) {
        List<ServicoFixaTO> servicoFixaTOList = new ArrayList<ServicoFixaTO>();
        for(Servico entity : entityList) {
            servicoFixaTOList.add(this.buildBasicTO(entity));
        }
        return servicoFixaTOList;
    }
    
    public List<ServicoFixaTO> getBasicTOList(List<Object[]> entityList) {
        List<ServicoFixaTO> toList = new ArrayList<ServicoFixaTO>();
        for (Object[] entity : entityList) {
            toList.add(this.getBasicTO(entity));
        }
        return toList;
    }

    public ServicoFixaTO getBasicTO(Object[] entity) {
        ServicoFixaTO to = null;
        if (entity != null) {
            to = new ServicoFixaTO();
            to.setIdServico((Integer) entity[0]);
            to.setNmComercial((String) entity[1]);
            to.setCdPS((String) entity[2]);
        }
        return to;
    }
	
}
