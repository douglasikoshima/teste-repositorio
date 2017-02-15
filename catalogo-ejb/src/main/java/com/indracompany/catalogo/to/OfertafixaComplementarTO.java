package com.indracompany.catalogo.to;

import java.io.Serializable;

public class OfertafixaComplementarTO implements Serializable {

    private static final long serialVersionUID = 4033511190477589278L;
    
    private Integer idOfertafixaComplementar;
    private SolicitacaoComercialFixaTO solicitacaoComercialFixaTO;
    private ServicoOfertafixaTO servicoOfertaFixaTO;
    private Integer pzMaximoVigencia;
    private OfertafixaTO ofertafixaTO;
    private boolean dependente = false;
    private boolean paraExcluir = false;
    private Long idSolicitacaoComercialPai;

    public OfertafixaComplementarTO(){
    }
    
    public OfertafixaComplementarTO(SolicitacaoComercialFixaTO solicitacaoComercialFixaTO, Integer pzMaximoVigencia){
        this();
        this.pzMaximoVigencia = pzMaximoVigencia;
        this.solicitacaoComercialFixaTO = solicitacaoComercialFixaTO;
        this.idSolicitacaoComercialPai = solicitacaoComercialFixaTO.getIdSolicitacaoComercial();
        this.servicoOfertaFixaTO = new ServicoOfertafixaTO(null, solicitacaoComercialFixaTO.getNmServicoCatalogo(), solicitacaoComercialFixaTO.getCdServicoCatalogo());
    }
    
    public OfertafixaComplementarTO(SolicitacaoComercialFixaTO solicitacaoComercialFixaTO, Integer pzMaximoVigencia, boolean dependente, Long idSolicitacaoComercialPai){
        this(solicitacaoComercialFixaTO, pzMaximoVigencia);
        this.idSolicitacaoComercialPai = idSolicitacaoComercialPai;
        this.dependente = dependente;
    }
    
    public Integer getIdOfertafixaComplementar() {
        return idOfertafixaComplementar;
    }

    public void setIdOfertafixaComplementar(Integer idOfertafixaComplementar) {
        this.idOfertafixaComplementar = idOfertafixaComplementar;
    }

    public SolicitacaoComercialFixaTO getSolicitacaoComercialFixaTO() {
        return solicitacaoComercialFixaTO;
    }

    public void setSolicitacaoComercialFixaTO(SolicitacaoComercialFixaTO solicitacaoComercialFixaTO) {
        this.solicitacaoComercialFixaTO = solicitacaoComercialFixaTO;
    }

    public Integer getPzMaximoVigencia() {
        return pzMaximoVigencia;
    }

    public void setPzMaximoVigencia(Integer pzMaximoVigencia) {
        this.pzMaximoVigencia = pzMaximoVigencia;
    }

    public ServicoOfertafixaTO getServicoOfertaFixaTO() {
        return servicoOfertaFixaTO;
    }

    public void setServicoOfertaFixaTO(ServicoOfertafixaTO servicoOfertaFixaTO) {
        this.servicoOfertaFixaTO = servicoOfertaFixaTO;
    }

	public OfertafixaTO getOfertafixaTO() {
		return ofertafixaTO;
	}

	public void setOfertafixaTO(OfertafixaTO ofertafixaTO) {
		this.ofertafixaTO = ofertafixaTO;
	}

    public boolean isDependente() {
        return dependente;
    }

    public void setDependente(boolean dependente) {
        this.dependente = dependente;
    }

    public boolean isParaExcluir() {
        return paraExcluir;
    }

    public void setParaExcluir(boolean paraExcluir) {
        this.paraExcluir = paraExcluir;
    }

    public Long getIdSolicitacaoComercialPai() {
        return idSolicitacaoComercialPai;
    }

    public void setIdSolicitacaoComercialPai(Long idSolicitacaoComercialPai) {
        this.idSolicitacaoComercialPai = idSolicitacaoComercialPai;
    }
}
