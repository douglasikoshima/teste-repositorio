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
@SequenceGenerator(name = "CARACTERISTICA_SQ", sequenceName = "CATALOGOPRS_OW.CARACTERISTICA_SQ", allocationSize = 1)
@NamedQuery(name = "Caracteristica.findAll", query = "SELECT c FROM Caracteristica c ORDER BY c.nmCaracteristica ")
@Table(name = "CARACTERISTICA", schema = "CATALOGOPRS_OW")
public class Caracteristica implements Serializable {
	private static final long serialVersionUID = 1L;

    public Caracteristica() {
    }
    
    public Caracteristica(Integer idCaracteristica) {
    	this.idCaracteristica = idCaracteristica;
    }
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CARACTERISTICA_SQ")
	@Column(name = "IDCARACTERISTICA")
	private Integer idCaracteristica;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO", insertable = true, updatable = false)
	private Date dtCriacao;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTULTIMAALTERACAO", insertable = false, updatable = true)
	private Date dtUltimaAlteracao;
	
	@Column(name = "INDCOMPARAVEL")
	private String indComparavel;
	
	@Column(name = "INDEXIBIVEL")
	private String indExibivel;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@Column(name = "INSIMULADOR")
	private String inSimulador;
	
	@Column(name = "NMCARACTERISTICA")
	private String nmCaracteristica;
	
	@Column(name = "NMUSUARIOALTERACAO", insertable = false, updatable = true)
	private String nmUsuarioAlteracao;
	
	@Column(name = "NMUSUARIOCRIACAO", insertable = true, updatable = false)
	private String nmUsuarioCriacao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDGRUPOCARACTERISTICA")
	private GrupoCaracteristica grupoCaracteristica;

	@OneToMany(mappedBy="caracteristica", fetch = FetchType.LAZY)
	private List<ValorCaracteristica> valorCaracteristicaList;
	
	@OneToMany(mappedBy="caracteristica", fetch = FetchType.LAZY)
	private List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList;
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

	public GrupoCaracteristica getGrupoCaracteristica() {
		return grupoCaracteristica;
	}

	public void setGrupoCaracteristica(GrupoCaracteristica grupoCaracteristica) {
		this.grupoCaracteristica = grupoCaracteristica;
	}

	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}

	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}

	public String getIndComparavel() {
		return indComparavel;
	}

	public void setIndComparavel(String indComparavel) {
		this.indComparavel = indComparavel;
	}

	public String getIndExibivel() {
		return indExibivel;
	}

	public void setIndExibivel(String indExibivel) {
		this.indExibivel = indExibivel;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInSimulador() {
		return inSimulador;
	}

	public void setInSimulador(String inSimulador) {
		this.inSimulador = inSimulador;
	}

	public String getNmCaracteristica() {
		return nmCaracteristica;
	}

	public void setNmCaracteristica(String nmCaracteristica) {
		this.nmCaracteristica = nmCaracteristica;
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

	public List<ValorCaracteristica> getValorCaracteristicaList() {
		return valorCaracteristicaList;
	}

	public void setValorCaracteristicaList(
			List<ValorCaracteristica> valorCaracteristicaList) {
		this.valorCaracteristicaList = valorCaracteristicaList;
	}

	public List<GrupoProdutoCaracteristica> getGrupoProdutoCaracteristicaList() {
		return grupoProdutoCaracteristicaList;
	}

	public void setGrupoProdutoCaracteristicaList(
			List<GrupoProdutoCaracteristica> grupoProdutoCaracteristicaList) {
		this.grupoProdutoCaracteristicaList = grupoProdutoCaracteristicaList;
	}
}