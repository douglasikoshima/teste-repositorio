package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.OperacaoComercial;
import com.indracompany.catalogo.to.OperacaoComercialTO;

public class OperacaoComercialTOBuilder {

    public List<OperacaoComercialTO> buildBasicTOList(List<OperacaoComercial> entityList) {
        List<OperacaoComercialTO> toList = new ArrayList<OperacaoComercialTO>();
        for(OperacaoComercial entity : entityList) {
            toList.add(this.buildBasicTO(entity));
        }
        return toList;
    }	
    
    public OperacaoComercialTO buildBasicTO(OperacaoComercial entity) {
    	OperacaoComercialTO to = null;
        if (entity != null) {
            to = new OperacaoComercialTO();
            to.setIdOperacaoComercial(entity.getIdOperacaoComercial());
            to.setCdOperacaoComercial(entity.getCdOperacaoComercial());
            to.setDsOperacaoComercial(entity.getDsOperacaoComercial());
            to.setNmOperacaoComercial(entity.getNmOperacaoComercial());
        }
        return to;
    }    
	
}
