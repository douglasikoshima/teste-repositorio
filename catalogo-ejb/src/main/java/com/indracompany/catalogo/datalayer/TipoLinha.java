package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "TipoLinha.findAll", query = "SELECT tl FROM TipoLinha tl order by tl.dscTipoLinha")
@Table(name = "TIPOLINHA", schema = "CATALOGOPRS_OW")
public class TipoLinha implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public TipoLinha() {}
    
    public TipoLinha(Integer idTipoLinha) {
    	this.idTipoLinha = idTipoLinha;
    }
    
    public TipoLinha(Integer idTipoLinha, String sgTipoLinha) {
		this.idTipoLinha = idTipoLinha;
		this.sgTipoLinha = sgTipoLinha;    	
    }
    
	@Id
	@Column(name = "IDTIPOLINHA")
	private Integer idTipoLinha;
	
	@Column(name = "DSCTIPOLINHA")
	private String dscTipoLinha;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Column(name = "SGTIPOLINHA")
	private String sgTipoLinha;

	public String getDscTipoLinha() {
		return dscTipoLinha;
	}

	public void setDscTipoLinha(String dscTipoLinha) {
		this.dscTipoLinha = dscTipoLinha;
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

	public Integer getIdTipoLinha() {
		return idTipoLinha;
	}

	public void setIdTipoLinha(Integer idTipoLinha) {
		this.idTipoLinha = idTipoLinha;
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

	public String getSgTipoLinha() {
		return sgTipoLinha;
	}

	public void setSgTipoLinha(String sgTipoLinha) {
		this.sgTipoLinha = sgTipoLinha;
	}
	
}