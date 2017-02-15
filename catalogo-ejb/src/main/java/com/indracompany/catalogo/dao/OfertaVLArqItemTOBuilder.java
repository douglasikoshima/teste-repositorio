package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.indracompany.catalogo.to.OfertaVLArqItemTO;

public class OfertaVLArqItemTOBuilder {

    public List<OfertaVLArqItemTO> createTOList(List<Object[]> entityList) {
        List<OfertaVLArqItemTO> toList = new ArrayList<OfertaVLArqItemTO>();
        for (Object[] entity : entityList) {
            toList.add(this.createTO(entity));
        }
        return toList;
    }

    public OfertaVLArqItemTO createTO(Object[] entity) {
        /*vfiwo.idArquivo, vfiwo.idItem, vfiwo.cdOferta, vfiwo.nmOferta, vfiwo.dtInicioVigencia, vfiwo.dtFimVigencia, vfiwo.cdPsLinha, 
    	vfiwo.cdOpcomLinha, vfiwo.cdOpComPreCadastro, vfiwo.cdPsPlano, vfiwo.cdOpComPlano, vfiwo.portabilidade, vfiwo.fwt, vfiwo.basePontual, 
    	vfiwo.inadimplente, vfiwo.fatb, vfiwo.produtoObrigatorio, vfiwo.speedySolo, vfiwo.erros*/
    	OfertaVLArqItemTO to = null;
        if (entity != null) {
            to = new OfertaVLArqItemTO();
            to.setIdArquivo((Integer) entity[0]);
            to.setIdItem((Integer) entity[1]);
            to.setCdOferta((String) entity[2]);
            to.setNmOferta((String) entity[3]);
            to.setDtInicioVigencia((Calendar) entity[4]);
            to.setDtFimVigencia((Calendar) entity[5]);
            to.setCdPsLinha((String) entity[6]);
            to.setCdOpcomLinha((String) entity[7]);
            to.setCdOpComPreCadastro((String) entity[8]);
            to.setCdPsPlano((String) entity[9]);
            to.setCdOpComPlano((String) entity[10]);
            to.setInPortabilidade((String) entity[11]);
            to.setInFwt((String) entity[12]);
            to.setInBasePontual((String) entity[13]);
            to.setInInadimplente((String) entity[14]);
            to.setInFatb((String) entity[15]);
            to.setInProdutoObrigatorio((String) entity[16]);
            to.setInSpeedySolo((String) entity[17]);
            to.setErros((String) entity[18]);
        }
        return to;
    }
}
