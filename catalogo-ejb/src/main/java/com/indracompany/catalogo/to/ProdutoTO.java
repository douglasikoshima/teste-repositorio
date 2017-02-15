package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Luiz Pereira
 *
 */
public class ProdutoTO implements Serializable, Jsonable {

	private static final long serialVersionUID = 1L;
	
	public ProdutoTO() {}
	
	public ProdutoTO(Integer idProduto) {
		this.idProduto = idProduto;
	}
	
	private Integer idProduto;
	private String dsNota;
	private String dsProduto;
	private String dsSAP;
	private Date dtCriacao;
	private Date dtFinal;
	private Date dtInicial;
	private Date dtUltimaAlteracao;
	private String inDisponivel;
	private String nmProduto;
	private String cdProduto;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String sgClasseAvaliacao;
	private String sgGrupoContabil;
	private String sgSetorAtividade;
	private CorTO corTO;
	private FabricanteTO fabricanteTO;
	private TipoProdutoTO tipoProdutoTO;
	private SistemaProdutoTO sistemaProdutoTO;
	private GamaTO gamaTO;
	
	/**
	 * O Mapeamento original foi feito errado, esse atributo erra para ser um Collection
	 * não matei esse atributo pq varias telas o utilizam mas com o tempo ele será descontinuado 
	 * e deletato!
	 */
	private ProdutoGrupoProdutoTO produtoGrupoProdutoTO;
	
	private List<ProdutoGrupoProdutoTO> produtoGrupoProdutosTO;
	
	public String getCdProduto() {
		return cdProduto;
	}

	public void setCdProduto(String cdProduto) {
		this.cdProduto = cdProduto;
	}

	public CorTO getCorTO() {
		return corTO;
	}

	public ProdutoGrupoProdutoTO getProdutoGrupoProdutoTO() {
		return produtoGrupoProdutoTO;
	}

	public void setProdutoGrupoProdutoTO(ProdutoGrupoProdutoTO produtoGrupoProdutoTO) {
		this.produtoGrupoProdutoTO = produtoGrupoProdutoTO;
	}

	public void setCorTO(CorTO corTO) {
		this.corTO = corTO;
	}

	public String getDsNota() {
		return dsNota;
	}

	public void setDsNota(String dsNota) {
		this.dsNota = dsNota;
	}

	public String getDsProduto() {
		return dsProduto;
	}

	public void setDsProduto(String dsProduto) {
		this.dsProduto = dsProduto;
	}

	public String getDsSAP() {
		return dsSAP;
	}

	public void setDsSAP(String dsSAP) {
		this.dsSAP = dsSAP;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public FabricanteTO getFabricanteTO() {
		return fabricanteTO;
	}

	public void setFabricanteTO(FabricanteTO fabricanteTO) {
		this.fabricanteTO = fabricanteTO;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmProduto() {
		return nmProduto;
	}

	public void setNmProduto(String nmProduto) {
		this.nmProduto = nmProduto;
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

	public String getSgClasseAvaliacao() {
		return sgClasseAvaliacao;
	}

	public void setSgClasseAvaliacao(String sgClasseAvaliacao) {
		this.sgClasseAvaliacao = sgClasseAvaliacao;
	}

	public String getSgGrupoContabil() {
		return sgGrupoContabil;
	}

	public void setSgGrupoContabil(String sgGrupoContabil) {
		this.sgGrupoContabil = sgGrupoContabil;
	}

	public String getSgSetorAtividade() {
		return sgSetorAtividade;
	}

	public void setSgSetorAtividade(String sgSetorAtividade) {
		this.sgSetorAtividade = sgSetorAtividade;
	}

	public TipoProdutoTO getTipoProdutoTO() {
		return tipoProdutoTO;
	}

	public void setTipoProdutoTO(TipoProdutoTO tipoProdutoTO) {
		this.tipoProdutoTO = tipoProdutoTO;
	}

	public SistemaProdutoTO getSistemaProdutoTO() {
		return sistemaProdutoTO;
	}

	public void setSistemaProdutoTO(SistemaProdutoTO sistemaProdutoTO) {
		this.sistemaProdutoTO = sistemaProdutoTO;
	}

	public GamaTO getGamaTO() {
		return gamaTO;
	}

	public void setGamaTO(GamaTO gamaTO) {
		this.gamaTO = gamaTO;
	}

	public List<ProdutoGrupoProdutoTO> getProdutoGrupoProdutosTO() {
		return produtoGrupoProdutosTO;
	}

	public void setProdutoGrupoProdutosTO(
			List<ProdutoGrupoProdutoTO> produtoGrupoProdutosTO) {
		this.produtoGrupoProdutosTO = produtoGrupoProdutosTO;
	}
	
	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idProduto: " + this.idProduto, "nmProduto: " + this.nmProduto}, ", ");
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jsonProduto = new JSONObject();
		jsonProduto.put("idProduto", this.getIdProduto());
		jsonProduto.put("nmProduto", this.getNmProduto());
		
		SistemaProdutoTO sistemaProdutoTO = this.getSistemaProdutoTO();
		if (sistemaProdutoTO != null) {
			JSONObject jsonSistemaProduto = sistemaProdutoTO.toJSONObject();
			jsonProduto.put("sistemaProduto", jsonSistemaProduto);
		}
		
		GamaTO gamaTO = this.getGamaTO();
		if (gamaTO != null) {
			JSONObject jsonGama = gamaTO.toJSONObject();
			jsonProduto.put("gama", jsonGama);
		}
		
		return jsonProduto;
	}
	
}