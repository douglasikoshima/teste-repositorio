package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQueries ({
	@NamedQuery(name = "TipoProduto.findAll", query = "SELECT tp FROM TipoProduto tp order by tp.nmTipoProduto"),
	@NamedQuery(name = "TipoProduto.findAllButSimCards", query = "SELECT tp FROM TipoProduto tp WHERE tp.idTipoProduto not in (7, 13, 17, 19) ORDER BY tp.nmTipoProduto")
})
@Table(name = "TIPOPRODUTO", schema = "CATALOGOPRS_OW")
public class TipoProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TipoProduto() {}

    public TipoProduto(Integer idTipoProduto) {
		super();
		this.idTipoProduto = idTipoProduto;
	}
	
	@Id
	@Column(name = "IDTIPOPRODUTO")
	private Integer idTipoProduto;

	@Column(name = "NMTIPOPRODUTO")
	private String nmTipoProduto;

	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

	@Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;
	
	@Column(name = "SGTIPOPRODUTO")
	private String sgTipoProduto;
	
	@Column(name = "UTILIZACHIP")
	private String utilizaChip;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDTIPOLINHA")
	private TipoLinha tipoLinha;
	
	@OneToMany(mappedBy="tipoProdutoRestricao")
	private List<AssociaRestricaoCompativel> associaRestricaoCompativeList;
	
	@ManyToMany
	@JoinTable(name = "FABRICANTETIPOPRODUTO", joinColumns = @JoinColumn(name = "IDTIPOPRODUTO"), inverseJoinColumns = @JoinColumn(name = "IDFABRICANTE"))
	private List<Fabricante> fabricanteList;
	
	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public List<AssociaRestricaoCompativel> getAssociaRestricaoCompativeList() {
		return associaRestricaoCompativeList;
	}

	public void setAssociaRestricaoCompativeList(
			List<AssociaRestricaoCompativel> associaRestricaoCompativeList) {
		this.associaRestricaoCompativeList = associaRestricaoCompativeList;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdTipoProduto() {
		return idTipoProduto;
	}

	public void setIdTipoProduto(Integer idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}

	public String getNmTipoProduto() {
		return nmTipoProduto;
	}

	public void setNmTipoProduto(String nmTipoProduto) {
		this.nmTipoProduto = nmTipoProduto;
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

	public String getSgTipoProduto() {
		return sgTipoProduto;
	}

	public void setSgTipoProduto(String sgTipoProduto) {
		this.sgTipoProduto = sgTipoProduto;
	}

	public TipoLinha getTipoLinha() {
		return tipoLinha;
	}

	public void setTipoLinha(TipoLinha tipoLinha) {
		this.tipoLinha = tipoLinha;
	}

	public String getUtilizaChip() {
		return utilizaChip;
	}

	public void setUtilizaChip(String utilizaChip) {
		this.utilizaChip = utilizaChip;
	}

	public List<Fabricante> getFabricanteList() {
		return fabricanteList;
	}

	public void setFabricanteList(List<Fabricante> fabricanteList) {
		this.fabricanteList = fabricanteList;
	}
	
}