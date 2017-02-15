package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "ACAO_SQ", sequenceName = "CATALOGOPRS_OW.ACAO_SQ", allocationSize = 1)
@NamedQuery(name = "Acao.findAll", query = "SELECT a FROM Acao a ")
@Table(name = "ACAO", schema = "CATALOGOPRS_OW")
public class Acao implements Serializable {
	private static final long serialVersionUID = 1L;

    public Acao() {}
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAO_SQ")
	@Column(name = "IDACAO")
	private Integer idAcao;
	
	@Column(name = "NMACAO")
	private String nmAcao;
	
	@Column(name = "SGACAO")
	private String sgAcao;
	
	@Column(name = "DSACAO")
	private String dsAcao;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO", updatable = false, insertable = true)
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO", updatable = false, insertable = true)
	private String nmUsuarioCriacao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTALTERACAO")
	private Date dtAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANALATENDIMENTO")
	private CanalAtendimento canalAtendimento;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTINICIAL")
	private Date dtInicial;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTFINAL")
	private Date dtFinal;
	
	@Column(name = "INDANALISEFRAUDE")
	private String indAnaliseFraude;
	
	public String getDsAcao() {
		return dsAcao;
	}

	public void setDsAcao(String dsAcao) {
		this.dsAcao = dsAcao;
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

	public Integer getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmAcao() {
		return nmAcao;
	}

	public void setNmAcao(String nmAcao) {
		this.nmAcao = nmAcao;
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

	public String getSgAcao() {
		return sgAcao;
	}

	public void setSgAcao(String sgAcao) {
		this.sgAcao = sgAcao;
	}

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getIndAnaliseFraude() {
		return indAnaliseFraude;
	}

	public void setIndAnaliseFraude(String indAnaliseFraude) {
		this.indAnaliseFraude = indAnaliseFraude;
	}
}