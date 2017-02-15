package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

public class CategoriaTO implements Serializable {

    private static final long serialVersionUID = 6432865663901935009L;
    
    private Integer idCategoria;
    private String cdCategoria;
    private Date dtCriacao;
    private Date dtUltimaAlteracao;
    private Boolean indCatalogo;
    private Boolean inDisponivel;
    private String nmCategoria;
    private String nmUsuarioAlteracao;
    private String nmUsuarioCriacao;
    private Boolean inFixa;
    private String dsCategoria;
    private FamiliaTO familiaTO;
    private String inAlteracaoCatalogo;

    public CategoriaTO(){}
    public CategoriaTO(Integer idCategoria) {
		super();
		this.idCategoria = idCategoria;
	}
    public CategoriaTO(FamiliaTO familiaTO){
        this.familiaTO = familiaTO;
    }
	public int hashCode() {
		return ((idCategoria == null) ? 0 : idCategoria.hashCode());
	}
	public boolean equals(Object obj) {
		final CategoriaTO other = (CategoriaTO) obj;
		if (idCategoria == null && other.idCategoria == null) {
			return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		return true;
	}
	public String getCdCategoria() {
		return cdCategoria;
	}
	public void setCdCategoria(String cdCategoria) {
		this.cdCategoria = cdCategoria;
	}
	public String getDsCategoria() {
        return dsCategoria;
    }
    public void setDsCategoria(String dsCategoria) {
        this.dsCategoria = dsCategoria;
    }
    public Date getDtCriacao() {
        return dtCriacao;
    }
    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }
    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }
    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }
    public FamiliaTO getFamiliaTO() {
        return familiaTO;
    }
    public void setFamiliaTO(FamiliaTO familiaTO) {
        this.familiaTO = familiaTO;
    }
    public Integer getIdCategoria() {
        return idCategoria;
    }
    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }
    public Boolean getIndCatalogo() {
        return indCatalogo;
    }
    public void setIndCatalogo(Boolean indCatalogo) {
        this.indCatalogo = indCatalogo;
    }
    public Boolean getInDisponivel() {
        return inDisponivel;
    }
    public void setInDisponivel(Boolean inDisponivel) {
        this.inDisponivel = inDisponivel;
    }
    public Boolean getInFixa() {
        return inFixa;
    }
    public void setInFixa(Boolean inFixa) {
        this.inFixa = inFixa;
    }
    public String getNmCategoria() {
        return nmCategoria;
    }
    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }
    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }
    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }
    public String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }
    public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }
    
    public String getInAlteracaoCatalogo() {
		return inAlteracaoCatalogo;
	}
	public void setInAlteracaoCatalogo(String inAlteracaoCatalogo) {
		this.inAlteracaoCatalogo = inAlteracaoCatalogo;
	}
	@Override
    public String toString() {
        return String
                .format("CategoriaTO [idCategoria=%s, dtCriacao=%s, dtUltimaAlteracao=%s, indCatalogo=%s, inDisponivel=%s, nmCategoria=%s, nmUsuarioAlteracao=%s, nmUsuarioCriacao=%s, inFixa=%s, dsCategoria=%s, familiaTO=%s]",
                        idCategoria, dtCriacao, dtUltimaAlteracao, indCatalogo,
                        inDisponivel, nmCategoria, nmUsuarioAlteracao,
                        nmUsuarioCriacao, inFixa, dsCategoria, familiaTO);
    }    
}
