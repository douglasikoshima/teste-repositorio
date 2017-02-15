package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="MODELOVENDA", schema = "CATALOGOPRS_OW")
public class ModeloVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ModeloVenda() {}
	
	@Id
	@Column(name = "IDMODELOVENDA")
	private Integer idModeloVenda;

	@Column(name = "CDMODELOVENDA")
	private String cdModeloVenda;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMMODELOVENDA")
	private String nmModeloVenda;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Produtomodelovenda
	@OneToMany(mappedBy="modeloVenda")
	private List<ProdutoModeloVenda> produtoModeloVendaList;

	public String getCdModeloVenda() {
		return cdModeloVenda;
	}

	public void setCdModeloVenda(String cdModeloVenda) {
		this.cdModeloVenda = cdModeloVenda;
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

	public Integer getIdModeloVenda() {
		return idModeloVenda;
	}

	public void setIdModeloVenda(Integer idModeloVenda) {
		this.idModeloVenda = idModeloVenda;
	}

	public String getNmModeloVenda() {
		return nmModeloVenda;
	}

	public void setNmModeloVenda(String nmModeloVenda) {
		this.nmModeloVenda = nmModeloVenda;
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

	public List<ProdutoModeloVenda> getProdutoModeloVendaList() {
		return produtoModeloVendaList;
	}

	public void setProdutoModeloVendaList(
			List<ProdutoModeloVenda> produtoModeloVendaList) {
		this.produtoModeloVendaList = produtoModeloVendaList;
	}
}