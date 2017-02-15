package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@Table(name = "FABRICANTETIPOPRODUTO", catalog = "", schema = "CATALOGOPRS_OW", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDFABRICANTE", "IDTIPOPRODUTO"})})
public class FabricanteTipoProduto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public FabricanteTipoProduto() {
    }

    public FabricanteTipoProduto(BigDecimal idFabricanteTipoProduto) {
        this.idFabricanteTipoProduto = idFabricanteTipoProduto;
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "IDFABRICANTETIPOPRODUTO", nullable = false, precision = 22)
    private BigDecimal idFabricanteTipoProduto;
    
    @JoinColumn(name = "IDFABRICANTE", referencedColumnName = "IDFABRICANTE", nullable = false)
    @ManyToOne(optional = false)
    private Fabricante fabricante;
    
    @JoinColumn(name = "IDTIPOPRODUTO", referencedColumnName = "IDTIPOPRODUTO", nullable = false)
    @ManyToOne(optional = false)
    private TipoProduto tipoProduto;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public BigDecimal getIdFabricanteTipoProduto() {
		return idFabricanteTipoProduto;
	}

	public void setIdFabricanteTipoProduto(BigDecimal idFabricanteTipoProduto) {
		this.idFabricanteTipoProduto = idFabricanteTipoProduto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}
}
