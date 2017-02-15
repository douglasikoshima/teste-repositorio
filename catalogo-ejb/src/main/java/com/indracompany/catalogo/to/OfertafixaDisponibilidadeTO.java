package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OfertafixaDisponibilidadeTO implements Serializable {

    private static final long serialVersionUID = 594502388164035433L;

    private List<AgrupadorCNLTO> agrupadorCNLTOList;
    private List<CanalVendaTO> canalVendaTOList;
    private List<AreaRegistroTO> areaRegistroTOList;
    
    public OfertafixaDisponibilidadeTO(){
        this.canalVendaTOList = new ArrayList<CanalVendaTO>();
        this.areaRegistroTOList = new ArrayList<AreaRegistroTO>();
    }
    
    public List<LocalidadeTO> getLocalidadeTOListByIdCidade(Integer idCidade) {
        if (this.agrupadorCNLTOList == null) {
            return new ArrayList<LocalidadeTO>();
        }
        for (AgrupadorCNLTO agrupadorCNLTO : this.agrupadorCNLTOList) {
            if (agrupadorCNLTO.getIdCidade().equals(idCidade)) {
                return agrupadorCNLTO.getLocalidadeTOList();
            }
        }
        return new ArrayList<LocalidadeTO>();
    }
    
    public List<Integer> getIdAreaRegistroList() {
        List<Integer> idAreaRegistroList = new ArrayList<Integer>();
        for (AreaRegistroTO arTO : this.areaRegistroTOList) {
            idAreaRegistroList.add(arTO.getIdAreaRegistro());
        }
        return idAreaRegistroList;
    }
    
    public List<Long> getIdCanalVendaList(){
    	List<Long> idCanalVendaList = new ArrayList<Long>();
    	for (CanalVendaTO canalVendaTO : this.canalVendaTOList) {
            idCanalVendaList.add(canalVendaTO.getIdCanalVenda());
        }
    	return idCanalVendaList;
    }
    
    public boolean agrupadorCNLTOListRemove(AgrupadorCNLTO agrupadorCNLTO) {
    	if (this.agrupadorCNLTOList == null) {
    		return false;
    	}
    	return this.agrupadorCNLTOList.remove(agrupadorCNLTO);
    }
    
    public boolean agrupadorCNLTOListAdd(AgrupadorCNLTO agrupadorCNLTO) {
    	if (this.agrupadorCNLTOList == null) {
    		this.agrupadorCNLTOList = new ArrayList<AgrupadorCNLTO>();
    	}
    	if (!agrupadorCNLTO.localidadeTOListIsEmpty()) {
    		return this.agrupadorCNLTOList.add(agrupadorCNLTO);
    	} else {
    		return false;
    	}
    }
    
    public List<AgrupadorCNLTO> getAgrupadorCNLTOList() {
    	if (this.agrupadorCNLTOList == null) {
    		return new ArrayList<AgrupadorCNLTO>();
    	}
        return agrupadorCNLTOList;
    }
    
    public void setAgrupadorCNLTOList(Collection<AgrupadorCNLTO> agrupadorCNLTOList) {
    	if (agrupadorCNLTOList == null) {
    		agrupadorCNLTOList = new ArrayList<AgrupadorCNLTO>();
    	} 
    	this.agrupadorCNLTOList = new ArrayList<AgrupadorCNLTO>(agrupadorCNLTOList);
    }

    public List<CanalVendaTO> getCanalVendaTOList() {
        return canalVendaTOList;
    }

    public void setCanalVendaTOList(List<CanalVendaTO> canalVendaTOList) {
        this.canalVendaTOList = canalVendaTOList;
    }

    public List<AreaRegistroTO> getAreaRegistroTOList() {
        return areaRegistroTOList;
    }

    public void setAreaRegistroTOList(List<AreaRegistroTO> areaRegistroTOList) {
        this.areaRegistroTOList = areaRegistroTOList;
    }

    public int getAreaRegistroTOListSize() {
        return this.areaRegistroTOList != null ? this.areaRegistroTOList.size() : 0;
    }

    public int getCanalVendaTOListSize() {
        return this.canalVendaTOList != null ? this.canalVendaTOList.size() : 0;
    }

}
