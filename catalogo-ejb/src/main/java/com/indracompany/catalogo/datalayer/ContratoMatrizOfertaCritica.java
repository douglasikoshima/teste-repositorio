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
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "CONTRATOMATRIZOFERTACRITICA_SQ", sequenceName = "CATALOGOPRS_OW.CONTRATOMATRIZOFERTACRITICA_SQ", allocationSize = 40)
@NamedQuery(name = "ContratoMatrizOfertaCritica.findAll", query = "SELECT cmoc FROM ContratoMatrizOfertaCritica cmoc ORDER BY cmoc.numeroLinhaErro")
@Table(name = "CONTRATOMATRIZOFERTACRITICA", schema = "CATALOGOPRS_OW")
public class ContratoMatrizOfertaCritica implements Serializable {

	private static final long serialVersionUID = 1L;

	public ContratoMatrizOfertaCritica() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTRATOMATRIZOFERTACRITICA_SQ")
	@Column(name = "IDCONTRATOMATRIZOFERTACRITICA")
	private Integer idContratoMatrizOfertaCritica;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCONTRATOMATRIZ")
	private ContratoMatrizOferta contratoMatrizOferta;
	
	@Column(name = "NUMEROLINHAERRO")
	private Integer numeroLinhaErro;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMATRIZOFERTAARQUIVOCRITICA")
	private MatrizOfertaContratoCritica matrizOfertaContratoCritica;

	public ContratoMatrizOferta getContratoMatrizOferta() {
		return contratoMatrizOferta;
	}

	public void setContratoMatrizOferta(ContratoMatrizOferta contratoMatrizOferta) {
		this.contratoMatrizOferta = contratoMatrizOferta;
	}

	public Integer getIdContratoMatrizOfertaCritica() {
		return idContratoMatrizOfertaCritica;
	}

	public void setIdContratoMatrizOfertaCritica(
			Integer idContratoMatrizOfertaCritica) {
		this.idContratoMatrizOfertaCritica = idContratoMatrizOfertaCritica;
	}

	public MatrizOfertaContratoCritica getMatrizOfertaContratoCritica() {
		return matrizOfertaContratoCritica;
	}

	public void setMatrizOfertaContratoCritica(
			MatrizOfertaContratoCritica matrizOfertaContratoCritica) {
		this.matrizOfertaContratoCritica = matrizOfertaContratoCritica;
	}

	public Integer getNumeroLinhaErro() {
		return numeroLinhaErro;
	}

	public void setNumeroLinhaErro(Integer numeroLinhaErro) {
		this.numeroLinhaErro = numeroLinhaErro;
	}
}