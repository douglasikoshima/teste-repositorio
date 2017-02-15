package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class AgrupadorAreaRegistroTO implements Serializable {

    private static final long serialVersionUID = 7241327049986404606L;
    
    private String nmUf;
    private List<AreaRegistroTO> areaRegistroTOList;

    public AgrupadorAreaRegistroTO() {}    
    
    public AgrupadorAreaRegistroTO(String nmUf, Collection<AreaRegistroTO> areaRegistroTOList) {
        this.nmUf = nmUf;
        this.areaRegistroTOList = new ArrayList<AreaRegistroTO>(areaRegistroTOList);
    }

    public String getNmUf() {
        return nmUf;
    }
    
    public void setNmUf(String nmUf) {
        this.nmUf = nmUf;
    }

    public List<AreaRegistroTO> getAreaRegistroTOList() {
        return areaRegistroTOList;
    }

    public void setAreaRegistroTOList(List<AreaRegistroTO> areaRegistroTOList) {
        this.areaRegistroTOList = areaRegistroTOList;
    }

    public String getAllChecked() {
        return this.areaRegistroTOList != null ? CollectionUtils.select(this.areaRegistroTOList, new Predicate() {
            public boolean evaluate(Object obj) {
                AreaRegistroTO to = (AreaRegistroTO) obj;
                return to.getChecked().equals("checked");
            }
        }).size() == this.areaRegistroTOList.size() ? "checked" : "" : "";
    }
    
    public boolean isSomeOneChecked() {
    	return this.areaRegistroTOList != null && CollectionUtils.exists(this.areaRegistroTOList, new Predicate() {
			public boolean evaluate(Object obj) {
				AreaRegistroTO to = (AreaRegistroTO) obj;
                return to.getChecked().equals("checked");
			}
		});
    }
    
    public String getGroupLabelStyle() {
    	return this.isSomeOneChecked() ? "font-weight: bold !important;" : "font-weight: normal !important;";  
    }
}
