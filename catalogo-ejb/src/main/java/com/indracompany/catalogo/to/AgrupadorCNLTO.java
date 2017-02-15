package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AgrupadorCNLTO implements Serializable, Comparable<AgrupadorCNLTO> {

    private static final long serialVersionUID = -2557989758839551992L;
    
    private String nmCidade;
    private String cdAreaRegistro;
    private String nmUf;
    private List<LocalidadeTO> localidadeTOList;
    
    public AgrupadorCNLTO(){}
    
    public AgrupadorCNLTO(String nmCidade, String cdAreaRegistro, String nmUf){
        this.nmCidade = nmCidade;
        this.cdAreaRegistro = cdAreaRegistro;
        this.nmUf = nmUf;
    }

    public Integer getIdCidade() {
    	if (this.localidadeTOListIsEmpty()) {
    		return null;
    	}
    	return this.localidadeTOList.iterator().next().getCidadeTO().getIdCidade();
    }
    public boolean localidadeTOListIsEmpty(){
    	if (this.localidadeTOList == null) {
    		return true;
    	}
    	return this.localidadeTOList.isEmpty();
    }
    public List<LocalidadeTO> getLocalidadeTOList() {
        return localidadeTOList;
    }
    
    public String getNmCidade() {
        return nmCidade;
    }
    
    public void setLocalidadeTOList(List<LocalidadeTO> localidadeTOList) {
        this.localidadeTOList = localidadeTOList;
    }
    
    public void setLocalidadeTOList(Collection<LocalidadeTO> localidadeTOCollection) {
        localidadeTOList = new ArrayList<LocalidadeTO>(localidadeTOCollection);
    }    
    
    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public String getCdAreaRegistro() {
        return cdAreaRegistro;
    }

    public void setCdAreaRegistro(String cdAreaRegistro) {
        this.cdAreaRegistro = cdAreaRegistro;
    }

    public String getNmUf() {
        return nmUf;
    }

    public void setNmUf(String nmUf) {
        this.nmUf = nmUf;
    }
    
    public String getCdLocalidade(){
        StringBuilder sb = new StringBuilder("");
        for (LocalidadeTO localidadeTO : this.localidadeTOList) {
            sb.append(localidadeTO.getCdLocalidade());
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((cdAreaRegistro == null) ? 0 : cdAreaRegistro.hashCode());
        result = PRIME * result + ((nmCidade == null) ? 0 : nmCidade.hashCode());
        result = PRIME * result + ((nmUf == null) ? 0 : nmUf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final AgrupadorCNLTO other = (AgrupadorCNLTO) obj;
        if (cdAreaRegistro == null) {
            if (other.cdAreaRegistro != null)
                return false;
        } else if (!cdAreaRegistro.equals(other.cdAreaRegistro))
            return false;
        if (nmCidade == null) {
            if (other.nmCidade != null)
                return false;
        } else if (!nmCidade.equals(other.nmCidade))
            return false;
        if (nmUf == null) {
            if (other.nmUf != null)
                return false;
        } else if (!nmUf.equals(other.nmUf))
            return false;
        return true;
    }

    public int compareTo(AgrupadorCNLTO other) {
        int last = this.nmUf.compareTo(other.nmUf);
        last = last == 0 ? this.cdAreaRegistro.compareTo(other.cdAreaRegistro) : last;
        last = last == 0 ? this.nmCidade.compareTo(other.nmCidade) : last;
        return last;
    }
}
