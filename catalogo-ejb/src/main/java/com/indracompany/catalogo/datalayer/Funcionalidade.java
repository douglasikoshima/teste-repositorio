package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "Funcionalidade.findAll", query = "SELECT f FROM Funcionalidade f order by f.nmFuncionalidade")
@Table(name = "NFOREF_FUNCIONALIDADE", schema = "NFOREF_OW")
public class Funcionalidade implements Serializable {
	private static final long serialVersionUID = 1L;

    public Funcionalidade() {}
    
    public Funcionalidade(String cdFuncionalidade) {
    	this.cdFuncionalidade = cdFuncionalidade;    	
    }
    
	@Id
	@Column(name = "CD_FUNCIONALIDADE")
	private String cdFuncionalidade;
	
	@OrderBy
	@Column(name = "DS_FUNCIONALIDADE")
	private String nmFuncionalidade;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "DT_CRIACAO")
	private Date dtCriacao;

	public String getCdFuncionalidade() {
		return cdFuncionalidade;
	}

	public void setCdFuncionalidade(String cdFuncionalidade) {
		this.cdFuncionalidade = cdFuncionalidade;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmFuncionalidade() {
		return nmFuncionalidade;
	}

	public void setNmFuncionalidade(String nmFuncionalidade) {
		this.nmFuncionalidade = nmFuncionalidade;
	}
}