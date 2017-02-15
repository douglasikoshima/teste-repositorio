package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name = "CONTRATOMATRIZOFERTA_SQ", sequenceName = "CATALOGOPRS_OW.CONTRATOMATRIZOFERTA_SQ", allocationSize = 180000)
@NamedQuery(name = "ContratoMatrizOferta.findAll", query = "SELECT cmo FROM ContratoMatrizOferta cmo ")
@Table(name = "CONTRATOMATRIZOFERTA", schema = "CATALOGOPRS_OW")
public class ContratoMatrizOferta implements Serializable {

	private static final long serialVersionUID = 1L;

	public ContratoMatrizOferta() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTRATOMATRIZOFERTA_SQ")
	@Column(name = "IDCONTRATOMATRIZ")
	private Integer idContratoMatriz;

	@Column(name = "CODIGOPLANO")
	private String codigoPlano;
	
	@Column(name = "CODIGOSERVICO")
	private String codigoServico;

	@Column(name = "SIGLACSA")
	private String siglaCsa;

	@Column(name = "VALORCONTRATO")
	private String valorContrato;
	
	@Column(name = "NUMEROLINHA")
	private Integer numeroLinha;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

	public String getCodigoPlano() {
		return codigoPlano;
	}

	public void setCodigoPlano(String codigoPlano) {
		this.codigoPlano = codigoPlano;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdContratoMatriz() {
		return idContratoMatriz;
	}

	public void setIdContratoMatriz(Integer idContratoMatriz) {
		this.idContratoMatriz = idContratoMatriz;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getSiglaCsa() {
		return siglaCsa;
	}

	public void setSiglaCsa(String siglaCsa) {
		this.siglaCsa = siglaCsa;
	}

	public String getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
	}

	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}
	
}