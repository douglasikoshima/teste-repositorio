package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "FORMAPAGAMENTO_SQ", sequenceName = "CATALOGOPRS_OW.FORMAPAGAMENTO_SQ", allocationSize = 1)
@NamedQuery(name = "FormaPagamento.findAll", query = "SELECT fp FROM FormaPagamento fp order by fp.nmFormaPagamento")
@Table(name="FORMAPAGAMENTO", schema = "CATALOGOPRS_OW")
public class FormaPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FormaPagamento() {}
	
	public FormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAPAGAMENTO_SQ")
	@Column(name = "IDFORMAPAGAMENTO")
	private Integer idFormaPagamento;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO", insertable = true, updatable = false)
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO", insertable = false, updatable = true)
	private Date dtUltimaAlteracao;

    @Column(name = "NMFORMAPAGAMENTO")
	private String nmFormaPagamento;

    @Column(name = "NMUSUARIOALTERACAO", insertable = false, updatable = true)
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO", insertable = true, updatable = false)
	private String nmUsuarioCriacao;
    
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SGFORMAPAGAMENTO", referencedColumnName = "SGMEIOPAGAMENTO")
	private MeioPagamento meioPagamento;
    
	//bi-directional many-to-one association to Condicaopagamento
	@OneToMany(mappedBy="formaPagamento", cascade = CascadeType.ALL)
	private List<CondicaoPagamento> condicaoPagamentoList;

	//bi-directional many-to-one association to FormaPagamentoBandeira
	@OneToMany(mappedBy="formaPagamento")
	private List<FormaPagamentoBandeira> formaPagamentoBandeiraList;

	//bi-directional many-to-one association to Formapagtoparcelaminima
	@OneToMany(mappedBy="formaPagamento")
	private List<FormaPagtoParcelaMinima> formaPagtoParcelaMinimaList;
	
	@OneToOne(mappedBy = "formaPagamento")
	private FormaPagtoCanalParam formaPagtoCanalParam;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            schema = "CATALOGOPRS_OW", 
            name = "FORMAPAGAMENTOPLATAFORMA", 
            joinColumns = @JoinColumn(name = "IDFORMAPAGAMENTO"),
            inverseJoinColumns = @JoinColumn(name = "IDPLATAFORMA")
    )
    private List<Plataforma> plataformaList;

    public List<CondicaoPagamento> getCondicaoPagamentoList() {
		return condicaoPagamentoList;
	}

	public void addToCondicaoPagamentoList(
			CondicaoPagamento condicaoPagamento) {
		condicaoPagamento.setFormaPagamento(this);
		this.condicaoPagamentoList.add(condicaoPagamento);
	}
	
	public void setCondicaoPagamentoList(
			List<CondicaoPagamento> condicaoPagamentoList) {
		this.condicaoPagamentoList = condicaoPagamentoList;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public List<FormaPagtoParcelaMinima> getFormaPagtoParcelaMinimaList() {
		return formaPagtoParcelaMinimaList;
	}

	public void setFormaPagtoParcelaMinimaList(
			List<FormaPagtoParcelaMinima> formaPagtoParcelaMinimaList) {
		this.formaPagtoParcelaMinimaList = formaPagtoParcelaMinimaList;
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

	public Integer getIdFormaPagamento() {
		return idFormaPagamento;
	}

	public void setIdFormaPagamento(Integer idFormaPagamento) {
		this.idFormaPagamento = idFormaPagamento;
	}

	public String getNmFormaPagamento() {
		return nmFormaPagamento;
	}

	public void setNmFormaPagamento(String nmFormaPagamento) {
		this.nmFormaPagamento = nmFormaPagamento;
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

	public List<FormaPagamentoBandeira> getFormaPagamentoBandeiraList() {
		return formaPagamentoBandeiraList;
	}

	public void setFormaPagamentoBandeiraList(
			List<FormaPagamentoBandeira> formaPagamentoBandeiraList) {
		this.formaPagamentoBandeiraList = formaPagamentoBandeiraList;
	}

	public MeioPagamento getMeioPagamento() {
		return meioPagamento;
	}

	public void setMeioPagamento(MeioPagamento meioPagamento) {
		this.meioPagamento = meioPagamento;
	}

	public FormaPagtoCanalParam getFormaPagtoCanalParam() {
		return formaPagtoCanalParam;
	}

	public void setFormaPagtoCanalParam(FormaPagtoCanalParam formaPagtoCanalParam) {
		this.formaPagtoCanalParam = formaPagtoCanalParam;
	}

    public List<Plataforma> getPlataformaList() {
        return plataformaList;
    }

    public void setPlataformaList(List<Plataforma> plataformaList) {
        this.plataformaList = plataformaList;
    }
}