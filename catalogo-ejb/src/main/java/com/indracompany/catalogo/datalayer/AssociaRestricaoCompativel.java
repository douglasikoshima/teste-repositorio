package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@Table(name = "ASSOCIARESTRICAOCOMPATIVEL", catalog = "", schema = "CATALOGOPRS_OW")
public class AssociaRestricaoCompativel implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public AssociaRestricaoCompativel() {}
    
    @Id
    @Column(name = "IDASSOCIARESTRICAOCOMPATIVEL")
    private Integer idAssociaRestricaoCompativel;
    
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDTIPOPRODUTORESTRICAO", referencedColumnName = "IDTIPOPRODUTO")
    private TipoProduto tipoProdutoRestricao;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDTIPOPRODUTOCOMPATIVEL", referencedColumnName = "IDTIPOPRODUTO")
    private TipoProduto tipoProdutoCompativel;
    
    @Column(name = "DTCRIACAO")
    @Temporal(TemporalType.DATE)
    private Date dtCriacao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdAssociaRestricaoCompativel() {
		return idAssociaRestricaoCompativel;
	}

	public void setIdAssociaRestricaoCompativel(Integer idAssociaRestricaoCompativel) {
		this.idAssociaRestricaoCompativel = idAssociaRestricaoCompativel;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public TipoProduto getTipoProdutoCompativel() {
		return tipoProdutoCompativel;
	}

	public void setTipoProdutoCompativel(TipoProduto tipoProdutoCompativel) {
		this.tipoProdutoCompativel = tipoProdutoCompativel;
	}

	public TipoProduto getTipoProdutoRestricao() {
		return tipoProdutoRestricao;
	}

	public void setTipoProdutoRestricao(TipoProduto tipoProdutoRestricao) {
		this.tipoProdutoRestricao = tipoProdutoRestricao;
	}
}
