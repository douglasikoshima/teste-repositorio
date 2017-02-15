package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class AgrupadorEpsTO implements Serializable {

    private static final long serialVersionUID = -3017355454935149217L;
    
    public AgrupadorEpsTO(){}
    public AgrupadorEpsTO(String nmGrupoEps, Collection<CanalVendaTO> canalVendaTOList) {
        this.nmGrupoEps = nmGrupoEps;
        this.canalVendaTOList = new ArrayList<CanalVendaTO>(canalVendaTOList);
    }
    
    private String nmGrupoEps;
    private List<CanalVendaTO> canalVendaTOList;
    
    public List<CanalVendaTO> getCanalVendaTOList() {
        return canalVendaTOList;
    }
     
    public String getNmGrupoEps() {
        return nmGrupoEps != null && !nmGrupoEps.equals("") ? nmGrupoEps : "N&atilde;o Classificado";
    }
    
    public void setCanalVendaTOList(List<CanalVendaTO> canalVendaTOList) {
        this.canalVendaTOList = canalVendaTOList;
    }
    
    public void setNmGrupoEps(String nmGrupoEps) {
        this.nmGrupoEps = nmGrupoEps;
    }
    
    public String getAllChecked() {
        return this.canalVendaTOList != null ? CollectionUtils.select(this.canalVendaTOList, new Predicate() {
            public boolean evaluate(Object obj) {
                CanalVendaTO to = (CanalVendaTO) obj;
                return to.getChecked().equals("checked");
            }
        }).size() == this.canalVendaTOList.size() ? "checked" : "" : "";
    }
    
    public String getSomeOneChecked() {
    	return this.canalVendaTOList != null ? CollectionUtils.exists(this.canalVendaTOList, new Predicate() {
			public boolean evaluate(Object obj) {
                CanalVendaTO to = (CanalVendaTO) obj;
                return to.getChecked().equals("checked");
			}
		}) ? "font-weight: bold !important;" : "font-weight: normal !important;" : "font-weight: normal !important;";
    }
}
