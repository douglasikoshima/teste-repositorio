package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class AcaoProdutoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AcaoProdutoTO() {}
	
	public AcaoProdutoTO(Integer idAcaoProduto, AcaoTO acaoTO, ProdutoTO produtoTO, Date dtCriacao, String nmUsuarioCriacao) {
		this.idAcaoProduto = idAcaoProduto;
		this.acaoTO = acaoTO;
		this.produtoTO = produtoTO;
		this.dtCriacao = dtCriacao;
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	
	private Integer idAcaoProduto;
	private AcaoTO acaoTO;
	private ProdutoTO produtoTO;
	private CanalTO canalTO;
	private OrganizacaoVendaTO organizacaoVendaTO;
	private PlataformaTO plataformaTO;
	private Date dtCriacao;
	private String nmUsuarioCriacao;

	public AcaoTO getAcaoTO() {
		return acaoTO;
	}

	public void setAcaoTO(AcaoTO acaoTO) {
		this.acaoTO = acaoTO;
	}

	public CanalTO getCanalTO() {
		return canalTO;
	}

	public void setCanalTO(CanalTO canalTO) {
		this.canalTO = canalTO;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdAcaoProduto() {
		return idAcaoProduto;
	}

	public void setIdAcaoProduto(Integer idAcaoProduto) {
		this.idAcaoProduto = idAcaoProduto;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public OrganizacaoVendaTO getOrganizacaoVendaTO() {
		return organizacaoVendaTO;
	}

	public void setOrganizacaoVendaTO(OrganizacaoVendaTO organizacaoVendaTO) {
		this.organizacaoVendaTO = organizacaoVendaTO;
	}

	public PlataformaTO getPlataformaTO() {
		return plataformaTO;
	}

	public void setPlataformaTO(PlataformaTO plataformaTO) {
		this.plataformaTO = plataformaTO;
	}

	public ProdutoTO getProdutoTO() {
		return produtoTO;
	}

	public void setProdutoTO(ProdutoTO produtoTO) {
		this.produtoTO = produtoTO;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idAcaoProduto: " + this.idAcaoProduto}, ", ");
	}
}
