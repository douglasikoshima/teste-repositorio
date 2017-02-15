package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="REGIONAL", schema = "CATALOGOPRS_OW")
@NamedQuery(name = "Regional.findAll", query = "SELECT r FROM Regional r ")
public class Regional implements Serializable {
	private static final long serialVersionUID = -654651L;
	
	public Regional() {}	
	
	public Regional(Integer idRegional, String nmRegional) {
		this.idRegional = idRegional;
		this.nmRegional = nmRegional;
	}
	
	@Id
	@Column(name = "IDREGIONAL")
	private Integer idRegional;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMREGIONAL")
	private String nmRegional;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	/*bi-directional many-to-one association to Plano
	@OneToMany(mappedBy="regional")
	private List<Plano> planoList;*/

	//bi-directional many-to-one association to Uf
	@OneToMany(mappedBy="regional")
	private List<Uf> ufList;

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

	public Integer getIdRegional() {
		return idRegional;
	}

	public void setIdRegional(Integer idRegional) {
		this.idRegional = idRegional;
	}

	public String getNmRegional() {
		return nmRegional;
	}

	public void setNmRegional(String nmRegional) {
		this.nmRegional = nmRegional;
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

	/*public List<Plano> getPlanoList() {
		return planoList;
	}

	public void setPlanoList(List<Plano> planoList) {
		this.planoList = planoList;
	}*/

	public List<Uf> getUfList() {
		return ufList;
	}

	public void setUfList(List<Uf> ufList) {
		this.ufList = ufList;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((idRegional == null) ? 0 : idRegional.hashCode());
		result = PRIME * result + ((nmRegional == null) ? 0 : nmRegional.hashCode());
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
		final Regional other = (Regional) obj;
		if (idRegional == null) {
			if (other.idRegional != null)
				return false;
		} else if (!idRegional.equals(other.idRegional))
			return false;
		if (nmRegional == null) {
			if (other.nmRegional != null)
				return false;
		} else if (!nmRegional.equals(other.nmRegional))
			return false;
		return true;
	}
	
}