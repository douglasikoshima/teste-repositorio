package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
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
@Table(name = "PLATAFORMA", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "Plataforma.findAll", query = "SELECT p FROM Plataforma p ORDER BY p.nmPlataforma"),
    @NamedQuery(name = "Plataforma.findByIdCanalAtendimento", query = "select p from Plataforma p inner join p.canalAtendimentoList ca where ca.idCanalAtendimento = :idCanalAtendimento order by p.nmPlataforma")
})
public class Plataforma implements Serializable {
	private static final long serialVersionUID = 1L;

    public Plataforma() {}
    
	public Plataforma(Integer idPlataforma) {
	    this.idPlataforma = idPlataforma;
    }

    @Id
	@Column(name = "IDPLATAFORMA")
	private Integer idPlataforma;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @OrderBy
    @Column(name = "NMPLATAFORMA")
	private String nmPlataforma;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "CATALOGOPRS_OW", 
            name = "CANALATENDIMENTOPLATAFORMA", 
            joinColumns = @JoinColumn(name = "IDPLATAFORMA"),
            inverseJoinColumns = @JoinColumn(name = "IDCANALATENDIMENTO")
    )
    private List<CanalAtendimento> canalAtendimentoList;

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

	public Integer getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNmPlataforma() {
		return nmPlataforma;
	}

	public void setNmPlataforma(String nmPlataforma) {
		this.nmPlataforma = nmPlataforma;
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

    public List<CanalAtendimento> getCanalAtendimentoList() {
        return canalAtendimentoList;
    }

    public void setCanalAtendimentoList(List<CanalAtendimento> canalAtendimentoList) {
        this.canalAtendimentoList = canalAtendimentoList;
    }
}