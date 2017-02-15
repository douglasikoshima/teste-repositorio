package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "MATRIZOFERTACONTRATOCRITICA_SQ", sequenceName = "CATALOGOPRS_OW.MATRIZOFERTACONTRATOCRITICA_SQ", allocationSize = 1)
@NamedQuery(name = "MatrizOfertaContratoCritica.findAll", query = "SELECT mocc FROM MatrizOfertaContratoCritica mocc ")
@Table(name = "MATRIZOFERTACONTRATOCRITICA", schema = "CATALOGOPRS_OW")
public class MatrizOfertaContratoCritica implements Serializable {

	private static final long serialVersionUID = 1L;

	public MatrizOfertaContratoCritica() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATRIZOFERTACONTRATOCRITICA_SQ")
	@Column(name = "IDMATRIZOFERTAARQUIVOCRITICA")
	private Integer idMatrizOfertaArquivoCritica;

	@Column(name = "DSCCRITICAIMPORTACAO")
	private String dscCriticaimportacao;

	public String getDscCriticaimportacao() {
		return dscCriticaimportacao;
	}

	public void setDscCriticaimportacao(String dscCriticaimportacao) {
		this.dscCriticaimportacao = dscCriticaimportacao;
	}

	public Integer getIdMatrizOfertaArquivoCritica() {
		return idMatrizOfertaArquivoCritica;
	}

	public void setIdMatrizOfertaArquivoCritica(Integer idMatrizOfertaArquivoCritica) {
		this.idMatrizOfertaArquivoCritica = idMatrizOfertaArquivoCritica;
	}
	
}