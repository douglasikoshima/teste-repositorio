/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@SequenceGenerator(name = "ACAOACESSOFORNECEDOR_SQ", sequenceName = "CATALOGOPRS_OW.ACAOACESSOFORNECEDOR_SQ", allocationSize = 1)
@Table(name = "ACAOACESSOFORNECEDOR", schema = "CATALOGOPRS_OW")
public class AcaoAcessoFornecedor implements Serializable {

	private static final long serialVersionUID = 1L;
    

    public AcaoAcessoFornecedor() {
    }

    public AcaoAcessoFornecedor(Integer idAcaoAcessoFornecedor) {
        this.idAcaoAcessoFornecedor = idAcaoAcessoFornecedor;
    }
	
    public AcaoAcessoFornecedor(Integer idAcaoAcessoFornecedor, Integer idPerfilSCA, Integer idFornecedor) {
        this.idAcaoAcessoFornecedor = idAcaoAcessoFornecedor;
        this.idPerfilSCA = idPerfilSCA;
        this.idFornecedor = idFornecedor;
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAOACESSOFORNECEDOR_SQ")
    @Column(name = "IDACAOACESSOFORNECEDOR")
    private Integer idAcaoAcessoFornecedor;
    
    @Column(name = "IDPERFILSCA")
    private Integer idPerfilSCA;
    
    @Column(name = "IDFORNECEDOR")
    private Integer idFornecedor;
    
    @Column(name = "IDACAO")
    private Integer idAcao; 
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO", updatable = false, insertable = true)
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO", updatable = false, insertable = true)
	private String nmUsuarioCriacao;
    
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public Integer getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public Integer getIdAcaoAcessoFornecedor() {
		return idAcaoAcessoFornecedor;
	}

	public void setIdAcaoAcessoFornecedor(Integer idAcaoAcessoFornecedor) {
		this.idAcaoAcessoFornecedor = idAcaoAcessoFornecedor;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdPerfilSCA() {
		return idPerfilSCA;
	}

	public void setIdPerfilSCA(Integer idPerfilSCA) {
		this.idPerfilSCA = idPerfilSCA;
	}
	
	
}