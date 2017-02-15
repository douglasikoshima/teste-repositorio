package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

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

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "DESCONTOCONDPAGTO_SQ", sequenceName = "CATALOGOPRS_OW.DESCONTOCONDPAGTO_SQ", allocationSize = 1)
@Table(name="DESCONTOCONDPAGTO", schema = "CATALOGOPRS_OW")
public class DescontoCondPagto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public DescontoCondPagto() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DESCONTOCONDPAGTO_SQ")
	@Column(name = "IDDESCONTOCONDPAGTO")
	private Integer idDescontoCondPagto;

	@Column(name = "FATORPRECO")
	private Float fatorPreco;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL", nullable=false)
	private Canal canal;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDCONDICAOPAGAMENTO", nullable=false)
	private CondicaoPagamento condicaoPagamento;
	
	public Canal getCanal() {
		return canal;
	}

	public CondicaoPagamento getCondicaoPagamento() {
		return condicaoPagamento;
	}

	public void setCondicaoPagamento(CondicaoPagamento condicaoPagamento) {
		this.condicaoPagamento = condicaoPagamento;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Float getFatorPreco() {
		return fatorPreco;
	}

	public void setFatorPreco(Float fatorPreco) {
		this.fatorPreco = fatorPreco;
	}

	public Integer getIdDescontoCondPagto() {
		return idDescontoCondPagto;
	}

	public void setIdDescontoCondPagto(Integer idDescontoCondPagto) {
		this.idDescontoCondPagto = idDescontoCondPagto;
	}
}