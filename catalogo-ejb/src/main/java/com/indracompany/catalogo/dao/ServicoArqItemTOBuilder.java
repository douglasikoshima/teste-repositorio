package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.to.ServicoArqItemTO;

public class ServicoArqItemTOBuilder {

    public List<ServicoArqItemTO> createTOList(List<Object[]> entityList) {
        List<ServicoArqItemTO> toList = new ArrayList<ServicoArqItemTO>();
        for (Object[] entity : entityList) {
            toList.add(this.createTO(entity));
        }
        return toList;
    }

    public ServicoArqItemTO createTO(Object[] entity) {
        /*vfiws.idArquivo, vfiws.idItem, vfiws.cdServico, vfiws.nmComercial, vfiws.disponiblidade, vfiws.erros*/
        ServicoArqItemTO to = null;
        if (entity != null) {
            to = new ServicoArqItemTO();
            to.setIdArquivo((Integer) entity[0]);
            to.setIdItem((Integer) entity[1]);
            to.setCdServico((String) entity[2]);
            to.setNmComercial((String) entity[3]);
            to.setDisponibilidade((String) entity[4]);
            to.setErros((String) entity[5]);
        }
        return to;
    }

}
