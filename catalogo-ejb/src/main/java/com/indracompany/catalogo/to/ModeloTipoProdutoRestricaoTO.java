package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
public class ModeloTipoProdutoRestricaoTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ModeloTipoProdutoRestricaoTO() {}
	
	public ModeloTipoProdutoRestricaoTO(Integer idModeloTipoProdutoRestricao) {
		this.idModeloTipoProdutoRestricao = idModeloTipoProdutoRestricao;
	}
	
	public ModeloTipoProdutoRestricaoTO(
			 Integer idModeloTipoProdutoRestricao,
			 Date dtCriacao,
			 String nmUsuarioCriacao,
			 Date dtUltimaAlteracao,
			 String nmUsuarioAlteracao,
			 GrupoProdutoTO grupoProdutoTO,
			 TipoProdutoTO tipoProdutoTO
	) {
		this.idModeloTipoProdutoRestricao = idModeloTipoProdutoRestricao;
		this.dtCriacao = dtCriacao;
		this.nmUsuarioCriacao = nmUsuarioCriacao;
		this.dtUltimaAlteracao = dtUltimaAlteracao;
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
		this.grupoProdutoTO = grupoProdutoTO;
		this.tipoProdutoTO = tipoProdutoTO;
	}
	
    private Integer idModeloTipoProdutoRestricao;
    private Date dtCriacao;
    private String nmUsuarioCriacao;
    private Date dtUltimaAlteracao;
    private String nmUsuarioAlteracao;
    private GrupoProdutoTO grupoProdutoTO;
    private TipoProdutoTO tipoProdutoTO;
    private List<ModeloTipoProdutoCompativelTO> modeloTipoProdutoCompativelTOList;
    @SuppressWarnings("unused")
    private Integer[] idModeloTipoProdutoCompativelArray;
    
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

	public GrupoProdutoTO getGrupoProdutoTO() {
		return grupoProdutoTO;
	}

	public void setGrupoProdutoTO(GrupoProdutoTO grupoProdutoTO) {
		this.grupoProdutoTO = grupoProdutoTO;
	}

	public Integer getIdModeloTipoProdutoRestricao() {
		return idModeloTipoProdutoRestricao;
	}

	public void setIdModeloTipoProdutoRestricao(Integer idModeloTipoProdutoRestricao) {
		this.idModeloTipoProdutoRestricao = idModeloTipoProdutoRestricao;
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

	public TipoProdutoTO getTipoProdutoTO() {
		return tipoProdutoTO;
	}

	public void setTipoProdutoTO(TipoProdutoTO tipoProdutoTO) {
		this.tipoProdutoTO = tipoProdutoTO;
	}

	public List<ModeloTipoProdutoCompativelTO> getModeloTipoProdutoCompativelTOList() {
		return modeloTipoProdutoCompativelTOList;
	}

	public void setModeloTipoProdutoCompativelTOList(
			List<ModeloTipoProdutoCompativelTO> modeloTipoProdutoCompativelTOList) {
		this.modeloTipoProdutoCompativelTOList = modeloTipoProdutoCompativelTOList;
	}

	public Integer[] getIdModeloTipoProdutoCompativelArray() {
		
		Integer[] ids = null;
		int i = 0;
		
		if (modeloTipoProdutoCompativelTOList != null) {
			ids = new Integer[modeloTipoProdutoCompativelTOList.size()];
			for (ModeloTipoProdutoCompativelTO modeloTipoProdutoCompativelTO : modeloTipoProdutoCompativelTOList) {
				ids[i] = modeloTipoProdutoCompativelTO.getTipoProdutoTO().getIdTipoProduto();
				i++;
			}
		}
		
		return ids;
	}

	public void setIdModeloTipoProdutoCompativelArray(
			Integer[] idModeloTipoProdutoCompativelArray) {
		this.idModeloTipoProdutoCompativelArray = idModeloTipoProdutoCompativelArray;
	}
}
