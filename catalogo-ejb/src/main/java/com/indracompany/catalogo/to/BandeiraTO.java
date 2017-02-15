package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Luiz
 *
 */
public class BandeiraTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public BandeiraTO() {}
	
	public BandeiraTO(Integer idBandeira) {
		this.idBandeira = idBandeira;
	}
	
	public BandeiraTO(Integer idBandeira,
				String nmBandeira,	
				String cdBandeiraSAP,
				String urlImagem,
				Float vlParcelaMinima,
				String cdInstituicaoFinanceira
			) {
		this.idBandeira = idBandeira;
		this.nmBandeira = nmBandeira;
		this.cdBandeiraSAP = cdBandeiraSAP;
		this.urlImagem = urlImagem;
		this.vlParcelaMinima = vlParcelaMinima;
		this. cdInstituicaoFinanceira = cdInstituicaoFinanceira;
	}
	
	private Integer idBandeira;
	private String cdInstituicaoFinanceira;
	private String cdBandeiraSAP;
	private String nmBandeira;
	private String urlImagem;
	private Float vlParcelaMinima;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioUltimaAlteracao;
	private List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList;
	
	public String getCdBandeiraSAP() {
		return cdBandeiraSAP;
	}

	public void setCdBandeiraSAP(String cdBandeiraSAP) {
		this.cdBandeiraSAP = cdBandeiraSAP;
	}

	public String getCdInstituicaoFinanceira() {
		return cdInstituicaoFinanceira;
	}

	public void setCdInstituicaoFinanceira(String cdInstituicaoFinanceira) {
		this.cdInstituicaoFinanceira = cdInstituicaoFinanceira;
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

	public Integer getIdBandeira() {
		return idBandeira;
	}

	public void setIdBandeira(Integer idBandeira) {
		this.idBandeira = idBandeira;
	}

	public String getNmBandeira() {
		return nmBandeira;
	}

	public void setNmBandeira(String nmBandeira) {
		this.nmBandeira = nmBandeira;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getNmUsuarioUltimaAlteracao() {
		return nmUsuarioUltimaAlteracao;
	}

	public void setNmUsuarioUltimaAlteracao(String nmUsuarioUltimaAlteracao) {
		this.nmUsuarioUltimaAlteracao = nmUsuarioUltimaAlteracao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public Float getVlParcelaMinima() {
		return vlParcelaMinima;
	}

	public void setVlParcelaMinima(Float vlParcelaMinima) {
		this.vlParcelaMinima = vlParcelaMinima;
	}

	public List<FormaPagamentoBandeiraTO> getFormaPagamentoBandeiraTOList() {
		return formaPagamentoBandeiraTOList;
	}

	public void setFormaPagamentoBandeiraTOList(
			List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList) {
		this.formaPagamentoBandeiraTOList = formaPagamentoBandeiraTOList;
	}
}
