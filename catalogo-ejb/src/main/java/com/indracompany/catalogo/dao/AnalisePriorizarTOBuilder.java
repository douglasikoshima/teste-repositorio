package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.indracompany.catalogo.to.AnalisePriorizarTO;

public class AnalisePriorizarTOBuilder {

    public List<AnalisePriorizarTO> createTOList(List<Object[]> resultList, boolean cdPropriedadeRemove) {
        List<AnalisePriorizarTO> analisePriorizarTOList = new ArrayList<AnalisePriorizarTO>();
        for (Object[] result : resultList) {
            analisePriorizarTOList.add(this.createTO(result,cdPropriedadeRemove));
        }
        return analisePriorizarTOList;
    }

    public AnalisePriorizarTO createTO(Object[] result, boolean cdPropriedadeRemove) {
        AnalisePriorizarTO to = null;
        if (result.length >= 14 ) {
            to = new AnalisePriorizarTO();
            to.setDsOfertafixa((String) result[0]);
            to.setInFWT((String)result[1]);
            to.setInConvergenteFibra((String)result[2]);
            to.setInConvergenteCobre((String) result[3]);
            to.setInSpeedySoloFibra((String) result[4]);
            to.setInSpeedySoloCobre((String) result[5]);
            to.setInPortab((String) result[6]);
            to.setDtInicial((Date) result[7]);
            to.setDtFinal((Date) result[8]);
            to.setIdOfertafixaCreditoScore((Integer) result[9]);            
            if(cdPropriedadeRemove){
            	to.setCdPrioridade(null);
            }else{
            	to.setCdPrioridade((Integer)result[10]);
            }
            to.setNmServicoLinha((String)result[11]);
            to.setNmServicoPreCad((String)result[12]);
            to.setNmServicoPlano((String)result[13]);
            to.setCdOfertafixa((String)result[14]);
        }
        return to;
    }

}
