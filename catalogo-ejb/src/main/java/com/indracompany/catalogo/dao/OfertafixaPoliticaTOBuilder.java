package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.OfertafixaPolitica;
import com.indracompany.catalogo.to.OfertafixaPoliticaTO;

public class OfertafixaPoliticaTOBuilder {

    public OfertafixaPoliticaTO buildTO(OfertafixaPolitica entity) {
        OfertafixaPoliticaTO to = null;
        if (entity != null) {
            to = new OfertafixaPoliticaTO();
            to.setIdOfertafixaPolitica(entity.getIdOfertafixaPolitica());
            to.setInAreaRegistro(entity.getInAreaRegistro());
            to.setInCanalVenda(entity.getInCanalVenda());
            to.setInEPS(entity.getInEPS());
            to.setInLocalidade(entity.getInLocalidade());
        }
        return to;
    }

}
