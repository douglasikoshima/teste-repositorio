package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;

public class MatrizOfertaProdutoPrecoForm extends ValidatorActionForm implements Serializable {
	private static final long serialVersionUID = -58171129687197644L;
	
	private String idMatrizOfertaItemPreco;
	private String cdProduto;
	private String nmProduto;
	private Integer[] idOrganizacaoVendasList;
	private Integer idCanalDistribuicao;
	private Integer idCanalAtendimento;
	private String sgEscritorioVenda;
	private Integer[] idOfertaSAPList;
	private Integer idAcao;
	private String cdAdabas;
	private Date dtPeriodoVigencia;
	
	private String idMatrizOfertaItemPrecoCheckList[];

	public String getCdAdabas() {
		return cdAdabas;
	}

	public void setCdAdabas(String cdAdabas) {
		this.cdAdabas = cdAdabas;
	}

	public String getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
	}

	public Date getDtPeriodoVigencia() {
		return dtPeriodoVigencia;
	}

	public void setDtPeriodoVigencia(Date dtPeriodoVigencia) {
		this.dtPeriodoVigencia = dtPeriodoVigencia;
	}

	public Integer getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public Integer getIdCanalAtendimento() {
		return idCanalAtendimento;
	}

	public void setIdCanalAtendimento(Integer idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}

	public Integer getIdCanalDistribuicao() {
		return idCanalDistribuicao;
	}

	public void setIdCanalDistribuicao(Integer idCanalDistribuicao) {
		this.idCanalDistribuicao = idCanalDistribuicao;
	}

	public String getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}

	public void setIdMatrizOfertaItemPreco(String idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
	}

	public String[] getIdMatrizOfertaItemPrecoCheckList() {
		return idMatrizOfertaItemPrecoCheckList;
	}

	public void setIdMatrizOfertaItemPrecoCheckList(
			String[] idMatrizOfertaItemPrecoCheckList) {
		this.idMatrizOfertaItemPrecoCheckList = idMatrizOfertaItemPrecoCheckList;
	}

	public Integer[] getIdOfertaSAPList() {
		return idOfertaSAPList;
	}

	public void setIdOfertaSAPList(Integer[] idOfertaSAPList) {
		this.idOfertaSAPList = idOfertaSAPList;
	}

	public Integer[] getIdOrganizacaoVendasList() {
		return idOrganizacaoVendasList;
	}

	public void setIdOrganizacaoVendasList(Integer[] idOrganizacaoVendasList) {
		this.idOrganizacaoVendasList = idOrganizacaoVendasList;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
	}

	public String getSgEscritorioVenda() {
		return sgEscritorioVenda;
	}

	public void setSgEscritorioVenda(String sgEscritorioVenda) {
		this.sgEscritorioVenda = sgEscritorioVenda;
	}
}
