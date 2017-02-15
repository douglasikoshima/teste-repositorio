package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@SequenceGenerator(name = "VALORCARACTERISTICA_SQ", sequenceName = "CATALOGOPRS_OW.VALORCARACTERISTICA_SQ", allocationSize = 1)
@NamedQuery(name = "ValorCaracteristica.findAll", query = "SELECT vc FROM ValorCaracteristica vc ")
@Table(name = "VALORCARACTERISTICA", schema = "CATALOGOPRS_OW")
public class ValorCaracteristica implements Serializable {

	private static final long serialVersionUID = 1L;

	public ValorCaracteristica() {
	}
	
	public ValorCaracteristica(Integer idValorCaracteristica) {
		this.idValorCaracteristica = idValorCaracteristica;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VALORCARACTERISTICA_SQ")
	@Column(name = "IDVALORCARACTERISTICA")
	private Integer idValorCaracteristica;

	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "VALOR")
	private String valor;

	@ManyToOne
	@JoinColumn(name="IDCARACTERISTICA")
	private Caracteristica caracteristica;

	@OneToMany(mappedBy="valorCaracteristica", fetch = FetchType.LAZY)
	private List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList;

	public Caracteristica getCaracteristica() {
		return this.caracteristica;
	}

	public void setCaracteristica(Caracteristica caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtcriacao) {
		this.dtCriacao = dtcriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdValorCaracteristica() {
		return idValorCaracteristica;
	}

	public void setIdValorCaracteristica(Integer idValorCaracteristica) {
		this.idValorCaracteristica = idValorCaracteristica;
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

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public List<GrupoProdutoCaracteristica> getGrupoProdutoCaracteristicaList() {
		return grupoProdutoCaracteristicaList;
	}

	public void setGrupoProdutoCaracteristicaList(
			List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList) {
		this.grupoProdutoCaracteristicaList = grupoProdutoCaracteristicaList;
	}
	
}