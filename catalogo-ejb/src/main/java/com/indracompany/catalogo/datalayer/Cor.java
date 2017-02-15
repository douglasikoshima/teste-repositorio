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

@Entity
@SequenceGenerator(name = "COR_SEQ", sequenceName = "CATALOGOPRS_OW.COR_SEQ", allocationSize = 1)
@Table(name = "COR", schema = "CATALOGOPRS_OW")
public class Cor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COR_SEQ")   
	@Column(name = "IDCOR")
	private Integer idCor;

	@Column(name = "RGB")
	private String rgb;
	
	@Column(name = "NMCOR", insertable = false, updatable = false)
	private String nmCor;

    @Temporal(TemporalType.DATE)
    @Column(name = "DTCRIACAO", insertable = true, updatable = false)
	private Date dtCriacao;
    
    @Column(name = "NMUSUARIOCRIACAO", insertable = true, updatable = false)
    private String nmUsuarioCriacao;
    
    @Temporal( TemporalType.DATE)
    @Column(name = "DTALTERACAO")
	private Date dtAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    public Cor() {
    }
    
    public Cor(Integer idCor) {
    	this.idCor = idCor;
    }
    
	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdCor() {
		return idCor;
	}

	public void setIdCor(Integer idCor) {
		this.idCor = idCor;
	}

	public String getNmCor() {
		return nmCor;
	}

	public void setNmCor(String nmCor) {
		this.nmCor = nmCor;
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

	public String getRgb() {
		return rgb;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}    
    
}
