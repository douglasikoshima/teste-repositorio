package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="COMBOOFERTAPLANOSERVICO", schema = "CATALOGOPRS_OW")
public class ComboOfertaPlanoServico implements Serializable {
	private static final long serialVersionUID = 1L;
	
	 public ComboOfertaPlanoServico() {}
	
	@Id
	@Column(name = "IDCOMBOOFERTAPLANOSERVICO")
	private Integer idComboOfertaPlanoServico;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "QTMINIMA")
	private Float qtMinima;

    @Column(name = "VALOR")
	private Float valor;

	//bi-directional many-to-one association to Combooferta
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOMBOOFERTA", nullable=false)
	private ComboOferta comboOferta;

	//bi-directional many-to-one association to Plano
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLANO")
	private Plano plano;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO")
	private Servico servico;

	//bi-directional many-to-one association to Comboofertaplanoservicoitem
	@OneToMany(mappedBy="comboOfertaPlanoServico")
	private List<ComboOfertaPlanoServicoItem> comboOfertaPlanoServicoItemList;

	public ComboOferta getComboOferta() {
		return comboOferta;
	}

	public void setComboOferta(ComboOferta comboOferta) {
		this.comboOferta = comboOferta;
	}

	public List<ComboOfertaPlanoServicoItem> getComboOfertaPlanoServicoItemList() {
		return comboOfertaPlanoServicoItemList;
	}

	public void setComboOfertaPlanoServicoItemList(
			List<ComboOfertaPlanoServicoItem> comboOfertaPlanoServicoItemList) {
		this.comboOfertaPlanoServicoItemList = comboOfertaPlanoServicoItemList;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
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

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdComboOfertaPlanoServico() {
		return idComboOfertaPlanoServico;
	}

	public void setIdComboOfertaPlanoServico(Integer idComboOfertaPlanoServico) {
		this.idComboOfertaPlanoServico = idComboOfertaPlanoServico;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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

	public Plano getPlano() {
		return plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

	public Float getQtMinima() {
		return qtMinima;
	}

	public void setQtMinima(Float qtMinima) {
		this.qtMinima = qtMinima;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
}