/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@SequenceGenerator(name = "MODELOTIPOPRODUTOCOMPATIVEL_SQ", sequenceName = "CATALOGOPRS_OW.MODELOTIPOPRODUTOCOMPATIVEL_SQ", allocationSize = 1)
@Table(name = "MODELOTIPOPRODUTOCOMPATIVEL", catalog = "", schema = "CATALOGOPRS_OW", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDMODELOTIPOPRODUTORESTRICAO", "IDTIPOPRODUTO"})})
public class ModeloTipoProdutoCompativel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public ModeloTipoProdutoCompativel() {}

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELOTIPOPRODUTOCOMPATIVEL_SQ")
    @Column(name = "IDMODELOTIPOPRODUTOCOMPATIVEL", nullable = false, precision = 22)
    private Integer idModeloTipoProdutoCompativel;
    
    @JoinColumn(name = "IDMODELOTIPOPRODUTORESTRICAO", referencedColumnName = "IDMODELOTIPOPRODUTORESTRICAO", nullable = false)
    @ManyToOne(optional = false)
    private ModeloTipoProdutoRestricao modeloTipoProdutoRestricao;
    
    @JoinColumn(name = "IDTIPOPRODUTO", referencedColumnName = "IDTIPOPRODUTO", nullable = false)
    @ManyToOne(optional = false)
    private TipoProduto tipoProduto;

	public Integer getIdModeloTipoProdutoCompativel() {
		return idModeloTipoProdutoCompativel;
	}

	public void setIdModeloTipoProdutoCompativel(
			Integer idModeloTipoProdutoCompativel) {
		this.idModeloTipoProdutoCompativel = idModeloTipoProdutoCompativel;
	}

	public ModeloTipoProdutoRestricao getModeloTipoProdutoRestricao() {
		return modeloTipoProdutoRestricao;
	}

	public void setModeloTipoProdutoRestricao(
			ModeloTipoProdutoRestricao modeloTipoProdutoRestricao) {
		this.modeloTipoProdutoRestricao = modeloTipoProdutoRestricao;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
