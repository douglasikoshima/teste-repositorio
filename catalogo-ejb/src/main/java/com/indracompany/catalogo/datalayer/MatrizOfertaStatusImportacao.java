package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



/**
 * @author equipe Catalogo
 *
 */
@Entity
@NamedQuery(name = "MatrizOfertaStatusImportacao.findAll", query = "SELECT mosi FROM MatrizOfertaStatusImportacao mosi WHERE mosi.idMatrizOfertaStatusImportacao not in (3, 7)")
@Table(name = "MATRIZOFERTASTATUSIMPORTACAO", schema = "CATALOGOPRS_OW")
public class MatrizOfertaStatusImportacao implements Serializable {

	private static final long serialVersionUID = 1L;

	public MatrizOfertaStatusImportacao() {	}
	
	public MatrizOfertaStatusImportacao(Integer idMatrizOfertaStatusImportacao) {
		setIdMatrizOfertaStatusImportacao(idMatrizOfertaStatusImportacao);
	}
	
	@Id
	@Column(name = "IDMATRIZOFERTASTATUSIMPORTACAO")
	private Integer idMatrizOfertaStatusImportacao;

	@Column(name = "DSCSTATUSIMPORTACAO")
	private String dscStatusImportacao;

	
	public String getDscStatusImportacao() {
		return dscStatusImportacao;
	}

	public void setDscStatusImportacao(String dscStatusImportacao) {
		this.dscStatusImportacao = dscStatusImportacao;
	}

	public Integer getIdMatrizOfertaStatusImportacao() {
		return idMatrizOfertaStatusImportacao;
	}

	public void setIdMatrizOfertaStatusImportacao(
			Integer idMatrizOfertaStatusImportacao) {
		this.idMatrizOfertaStatusImportacao = idMatrizOfertaStatusImportacao;
	}
	
}