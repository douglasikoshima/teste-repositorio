package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="COMBOOFERTAPLANOSERVICOITEM", schema = "CATALOGOPRS_OW")
public class ComboOfertaPlanoServicoItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ComboOfertaPlanoServicoItem() {}
	
	@Id
	@Column(name = "IDCOMBOOFERTAPLANOSERVICOITEM")
	private Integer idComboOfertaPlanoServicoItem;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "INOBRIGATORIO")
	private String inObrigatorio;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Comboofertaplanoservico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCOMBOOFERTAPLANOSERVICO", nullable=false)
	private ComboOfertaPlanoServico comboOfertaPlanoServico;

	//bi-directional many-to-one association to Servico
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSERVICO", nullable=false)
	private Servico servico;

	public ComboOfertaPlanoServico getComboOfertaPlanoServico() {
		return comboOfertaPlanoServico;
	}

	public void setComboOfertaPlanoServico(
			ComboOfertaPlanoServico comboOfertaPlanoServico) {
		this.comboOfertaPlanoServico = comboOfertaPlanoServico;
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

	public Integer getIdComboOfertaPlanoServicoItem() {
		return idComboOfertaPlanoServicoItem;
	}

	public void setIdComboOfertaPlanoServicoItem(
			Integer idComboOfertaPlanoServicoItem) {
		this.idComboOfertaPlanoServicoItem = idComboOfertaPlanoServicoItem;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInObrigatorio() {
		return inObrigatorio;
	}

	public void setInObrigatorio(String inObrigatorio) {
		this.inObrigatorio = inObrigatorio;
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

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}
}