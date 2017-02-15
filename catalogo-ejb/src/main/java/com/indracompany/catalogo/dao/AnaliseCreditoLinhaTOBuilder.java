package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

import edu.emory.mathcs.backport.java.util.LinkedList;

public class AnaliseCreditoLinhaTOBuilder {

    private static Logger log = Logger.getLogger(AnaliseCreditoLinhaTOBuilder.class);

    public List<AnaliseCreditoLinhaTO> buildTOServicoList(List<Servico> entityList) {
        List<AnaliseCreditoLinhaTO> toList = new ArrayList<AnaliseCreditoLinhaTO>();

        for (int i = 0; i < entityList.size(); i++) {
            AnaliseCreditoLinhaTO analiseCreditoLinhaTO = new AnaliseCreditoLinhaTO();
            analiseCreditoLinhaTO.setIdServico(entityList.get(i).getIdServico());
            analiseCreditoLinhaTO.setCdServico(entityList.get(i).getCdServico());
            analiseCreditoLinhaTO.setNomeComercialServico(entityList.get(i).getNmComercial());
            log.debug("Servico entity idServico: " + entityList.get(i).getIdServico());
            log.debug("Servico entity nomeComercialServico: " + entityList.get(i).getNmComercial());
            toList.add(analiseCreditoLinhaTO);
        }

        return toList;
    }
    
    @SuppressWarnings("unchecked")
    public List<AnaliseCreditoLinhaTO> createTOList(List<Object[]> resultList, List<AnaliseCreditoTO> analiseCreditoTOListScore) {
        Map<Integer, AnaliseCreditoLinhaTO> analiseCreditoLinhaTOMap = new HashMap<Integer, AnaliseCreditoLinhaTO>();
        for (Object[] result : resultList) {
            this.createTO(analiseCreditoLinhaTOMap, result, analiseCreditoTOListScore);
        }
        return new LinkedList(analiseCreditoLinhaTOMap.values());
    }

    public AnaliseCreditoLinhaTO createTO(Object[] result) {
        return createTO(new HashMap<Integer, AnaliseCreditoLinhaTO>(), result, null);
    }

    /* Utilizado para criar o TO que será renderizado na tela */
    public AnaliseCreditoLinhaTO createTO(Map<Integer, AnaliseCreditoLinhaTO> analiseCreditoLinhaTOMap, Object[] result,
            List<AnaliseCreditoTO> analiseCreditoTOListScore) {
        AnaliseCreditoLinhaTO to = null;
        if (analiseCreditoLinhaTOMap.containsKey((Integer) result[0])) {
            to = analiseCreditoLinhaTOMap.get((Integer) result[0]);
            to.addCdCorList((Integer) result[11]);
        } else if (result.length >= 14) {
            to = new AnaliseCreditoLinhaTO();
            to.setIdOferta((Integer) result[0]);
            to.setDsOfertafixa((String) result[1]);
            to.setInFWT((String) result[2]);
            to.setInConvergenteFibra((String) result[3]);
            to.setInConvergenteCobre((String) result[4]);
            to.setInSpeedySoloFibra((String) result[5]);
            to.setInSpeedySoloCobre((String) result[6]);
            to.setInPortab((String) result[7]);
            to.setDtInicial((Date) result[8]);
            to.setDtFinal((Date) result[9]);
            to.setIdOfertafixaCreditoScore((Integer) result[10]);
            to.setAnaliseCreditoTOList(analiseCreditoTOListScore, "search");
            to.addCdCorList((Integer) result[11]);
            to.setNmServicoLinha((String) result[12]);
            to.setNmServicoPreCad((String) result[13]);
            to.setNmServicoPlano((String) result[14]);
            to.setCdOfertafixa((String) result[15]);
            analiseCreditoLinhaTOMap.put(to.getIdOferta(), to);
        }
        log.debug("AnaliseCreditoLinhaTOBuilder - createTO: " + to.toString());
        return to;
    }
}
