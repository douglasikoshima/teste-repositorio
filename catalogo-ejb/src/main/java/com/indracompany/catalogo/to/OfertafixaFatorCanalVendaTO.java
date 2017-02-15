package com.indracompany.catalogo.to;

import java.io.Serializable;

public class OfertafixaFatorCanalVendaTO implements Serializable {
    
    private static final long serialVersionUID = 1280101814287758319L;
    private Integer idOfertafixaFatorCanalVenda;
    private CanalVendaTO canalVendaTO;
    private OfertafixaTO ofertafixaTO;

    public OfertafixaFatorCanalVendaTO() {
    }
    public OfertafixaFatorCanalVendaTO(Integer idOfertafixa, Long idCanalVenda) {
        this.ofertafixaTO = new OfertafixaTO(idOfertafixa);
        this.canalVendaTO = new CanalVendaTO(idCanalVenda);
    }
    public CanalVendaTO getCanalVendaTO() {
        return canalVendaTO;
    }
    public Integer getIdOfertafixaFatorCanalVenda() {
        return idOfertafixaFatorCanalVenda;
    }
    public OfertafixaTO getOfertafixaTO() {
        return ofertafixaTO;
    }
    public void setCanalVendaTO(CanalVendaTO canalVendaTO) {
        this.canalVendaTO = canalVendaTO;
    }
    public void setIdOfertafixaFatorCanalVenda(Integer idOfertafixaFatorCanalVenda) {
        this.idOfertafixaFatorCanalVenda = idOfertafixaFatorCanalVenda;
    }
    public void setOfertafixaTO(OfertafixaTO ofertafixaTO) {
        this.ofertafixaTO = ofertafixaTO;
    }
    
}
