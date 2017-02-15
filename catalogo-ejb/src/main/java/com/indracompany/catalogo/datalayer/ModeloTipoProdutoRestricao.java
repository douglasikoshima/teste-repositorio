/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@SequenceGenerator(name = "MODELOTIPOPRODUTORESTRICAO_SQ", sequenceName = "CATALOGOPRS_OW.MODELOTIPOPRODUTORESTRICAO_SQ", allocationSize = 1)
@Table(name = "MODELOTIPOPRODUTORESTRICAO", catalog = "", schema = "CATALOGOPRS_OW", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDGRUPOPRODUTO", "IDTIPOPRODUTO"})})
public class ModeloTipoProdutoRestricao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public ModeloTipoProdutoRestricao() {}
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELOTIPOPRODUTORESTRICAO_SQ")
    @Column(name = "IDMODELOTIPOPRODUTORESTRICAO", nullable = false, precision = 22)
    private Integer idModeloTipoProdutoRestricao;
    
    @Basic(optional = false)
    @Column(name = "DTCRIACAO", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dtCriacao;

    @Basic(optional = false)
    @Column(name = "NMUSUARIOCRIACAO", nullable = false, length = 255)
    private String nmUsuarioCriacao;
    
    @Column(name = "DTULTIMAALTERACAO")
    @Temporal(TemporalType.DATE)
    private Date dtUltimaAlteracao;
    
    @Column(name = "NMUSUARIOALTERACAO", length = 255)
    private String nmUsuarioAlteracao;
    
    @JoinColumn(name = "IDGRUPOPRODUTO", referencedColumnName = "IDGRUPOPRODUTO", nullable = false)
    @ManyToOne(optional = false)
    private GrupoProduto grupoProduto;
    
    @JoinColumn(name = "IDTIPOPRODUTO", referencedColumnName = "IDTIPOPRODUTO", nullable = false)
    @ManyToOne(optional = false)
    private TipoProduto tipoProduto;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "modeloTipoProdutoRestricao")
    private List<ModeloTipoProdutoCompativel> modeloTipoProdutoCompativelList;

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

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public Integer getIdModeloTipoProdutoRestricao() {
		return idModeloTipoProdutoRestricao;
	}

	public void setIdModeloTipoProdutoRestricao(
			Integer idModeloTipoProdutoRestricao) {
		this.idModeloTipoProdutoRestricao = idModeloTipoProdutoRestricao;
	}

	public List<ModeloTipoProdutoCompativel> getModeloTipoProdutoCompativelList() {
		return modeloTipoProdutoCompativelList;
	}

	public void setModeloTipoProdutoCompativelList(
			List<ModeloTipoProdutoCompativel> modeloTipoProdutoCompativelList) {
		this.modeloTipoProdutoCompativelList = modeloTipoProdutoCompativelList;
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

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
