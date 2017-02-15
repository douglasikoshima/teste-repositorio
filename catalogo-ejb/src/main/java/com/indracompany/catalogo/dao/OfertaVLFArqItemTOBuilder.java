package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.to.OfertaVLFArqItemTO;

public class OfertaVLFArqItemTOBuilder {
	
    public List<OfertaVLFArqItemTO> createTOList(List<Object[]> entityList) {
        List<OfertaVLFArqItemTO> toList = new ArrayList<OfertaVLFArqItemTO>();
        for (Object[] entity : entityList) {
            toList.add(this.createTO(entity));
        }
        return toList;
    }

    public OfertaVLFArqItemTO createTO(Object[] entity) {
        /*vfiwoc.idArquivo, vfiwoc.idItem, vfiwoc.cdOferta, vfiwoc.cdPsServico, vfiwoc.cdOpComServico, vfiwoc.pzVigencia, vfiwoc.erros*/
    	OfertaVLFArqItemTO to = null;
        if (entity != null) {
            to = new OfertaVLFArqItemTO();
            to.setIdArquivo((Integer) entity[0]);
            to.setIdItem((Integer) entity[1]);
            to.setCdOferta((String) entity[2]);
            to.setCdPsServico((String) entity[3]);
            to.setCdOpComServico((String) entity[4]);
            to.setPzVigencia((String) entity[5].toString());
            to.setErros((String) entity[6]);
        }
        return to;
    }	

}
