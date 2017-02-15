package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AgrupadorDisponibilidadeByEps implements Serializable{

    private static final long serialVersionUID = 5969234158556921912L;
    
    private String nmEps;
    private List<DisponibilidadePromocaoFixaTO> disponibilidadePromocaoFixaTOList; 
    private List<CanalVendaTO> canalVendaTOList;
    
    public AgrupadorDisponibilidadeByEps(){}
    
    public AgrupadorDisponibilidadeByEps(String nmEps,  Collection<DisponibilidadePromocaoFixaTO> disponibilidadePromocaoFixaTOList) {
        this.nmEps = nmEps;
        this.disponibilidadePromocaoFixaTOList = new ArrayList<DisponibilidadePromocaoFixaTO>(disponibilidadePromocaoFixaTOList);
    }
   
    public String getNmEps() {
        return nmEps;
    }

    public void setNmEps(String nmEps) {
        this.nmEps = nmEps;
    }

    public List<DisponibilidadePromocaoFixaTO> getDisponibilidadePromocaoFixaTOList() {
        return disponibilidadePromocaoFixaTOList;
    }

    public void setDisponibilidadePromocaoFixaTOList(
            List<DisponibilidadePromocaoFixaTO> disponibilidadePromocaoFixaTOList) {
        this.disponibilidadePromocaoFixaTOList = disponibilidadePromocaoFixaTOList;
    } 

    public List<CanalVendaTO> getCanalVendaTOList() {
        return canalVendaTOList;
    }

    public void setCanalVendaTOList(List<CanalVendaTO> canalVendaTOList) {
        this.canalVendaTOList = canalVendaTOList;
    }
   
    public int getCanalVendaTOListSize() {
        return this.canalVendaTOList != null ? this.canalVendaTOList.size() : 0;
    }
    
    public int getDisponibilidadePromocaoFixaTOListSize() {
        return this.disponibilidadePromocaoFixaTOList != null ? this.disponibilidadePromocaoFixaTOList.size() : 0;
    }     
}
