package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "Bandeira.findAll", query = "SELECT b FROM Bandeira b order by b.nmBandeira")
@SequenceGenerator(name = "BANDEIRA_SQ", sequenceName = "CATALOGOPRS_OW.BANDEIRA_SQ", allocationSize = 1)
@Table(name="BANDEIRA", schema = "CATALOGOPRS_OW")
public class Bandeira implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Bandeira() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANDEIRA_SQ")
	@Column(name = "IDBANDEIRA")
	private Integer idBandeira;

	@Column(name = "CDINSTITUICAOFINANCEIRA")
	private String cdInstituicaoFinanceira;

	@Column(name = "CDBANDEIRASAP")
	private String cdBandeiraSAP;

	@Column(name = "NMBANDEIRA")
	private String nmBandeira;

	@Column(name = "URLIMAGEM")
	private String urlImagem;
	
	@Column(name = "VLPARCELAMINIMA")
	private Float vlParcelaMinima;

	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOULTIMAALTERACAO")
	private String nmUsuarioUltimaAlteracao;
	
	@OneToMany(mappedBy="bandeira")
	private List<FormaPagamentoBandeira> formaPagamentoBandeiraList;
	
	public String getCdBandeiraSAP() {
		return cdBandeiraSAP;
	}

	public void setCdBandeiraSAP(String cdBandeiraSAP) {
		this.cdBandeiraSAP = cdBandeiraSAP;
	}

	public String getCdInstituicaoFinanceira() {
		return cdInstituicaoFinanceira;
	}

	public void setCdInstituicaoFinanceira(String cdInstituicaoFinanceira) {
		this.cdInstituicaoFinanceira = cdInstituicaoFinanceira;
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

	public Integer getIdBandeira() {
		return idBandeira;
	}

	public void setIdBandeira(Integer idBandeira) {
		this.idBandeira = idBandeira;
	}

	public String getNmBandeira() {
		return nmBandeira;
	}

	public void setNmBandeira(String nmBandeira) {
		this.nmBandeira = nmBandeira;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getNmUsuarioUltimaAlteracao() {
		return nmUsuarioUltimaAlteracao;
	}

	public void setNmUsuarioUltimaAlteracao(String nmUsuarioUltimaAlteracao) {
		this.nmUsuarioUltimaAlteracao = nmUsuarioUltimaAlteracao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public Float getVlParcelaMinima() {
		return vlParcelaMinima;
	}

	public void setVlParcelaMinima(Float vlParcelaMinima) {
		this.vlParcelaMinima = vlParcelaMinima;
	}

	public List<FormaPagamentoBandeira> getFormaPagamentoBandeiraList() {
		return formaPagamentoBandeiraList;
	}

	public void setFormaPagamentoBandeiraList(
			List<FormaPagamentoBandeira> formaPagamentoBandeiraList) {
		this.formaPagamentoBandeiraList = formaPagamentoBandeiraList;
	}
	
	
}