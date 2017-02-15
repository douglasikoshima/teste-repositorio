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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "FORMAPAGTOCANALATNDPARAM_SQ", sequenceName = "CATALOGOPRS_OW.FORMAPAGTOCANALATNDPARAM_SQ", allocationSize = 1)
@Table(name="FORMAPAGTOCANALATNDPARAM", schema = "CATALOGOPRS_OW")
public class FormaPagtoCanalAtndParam implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FormaPagtoCanalAtndParam() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAPAGTOCANALATNDPARAM_SQ")
	@Column(name = "IDFORMAPAGTOCANALATNDPARAM")
	private Integer idFormaPagtoCanalAtndParam;

	@Column(name = "CDINSTITUICAOFINANCEIRA")
	private String cdInstituicaoFinanceira;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO", insertable = true, updatable = false)
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO", insertable = false, updatable = true)
	private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOCRIACAO", insertable = true, updatable = false)
	private String nmUsuarioCriacao;

    @Column(name = "NMUSUARIOULTIMAALTERACAO", insertable = false, updatable = true)
	private String nmUsuarioUltimaAlteracao;

    @Column(name = "VALORDESCONTO")
	private Float valorDesconto;

    @Column(name = "VLPARCELAMINIMA")
	private Float vlParcelaMinima;

	//bi-directional many-to-one association to Canalatendimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANALATENDIMENTO", nullable=false)
	private CanalAtendimento canalAtendimento;

	//bi-directional many-to-one association to Formapagtocanalparam
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDFORMAPAGTOCANALPARAM", nullable=false)
	private FormaPagtoCanalParam formaPagtoCanalParam;

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
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

	public FormaPagtoCanalParam getFormaPagtoCanalParam() {
		return formaPagtoCanalParam;
	}

	public void setFormaPagtoCanalParam(FormaPagtoCanalParam formaPagtoCanalParam) {
		this.formaPagtoCanalParam = formaPagtoCanalParam;
	}

	public Integer getIdFormaPagtoCanalAtndParam() {
		return idFormaPagtoCanalAtndParam;
	}

	public void setIdFormaPagtoCanalAtndParam(Integer idFormaPagtoCanalAtndParam) {
		this.idFormaPagtoCanalAtndParam = idFormaPagtoCanalAtndParam;
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

	public Float getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Float valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Float getVlParcelaMinima() {
		return vlParcelaMinima;
	}

	public void setVlParcelaMinima(Float vlParcelaMinima) {
		this.vlParcelaMinima = vlParcelaMinima;
	}

    
}