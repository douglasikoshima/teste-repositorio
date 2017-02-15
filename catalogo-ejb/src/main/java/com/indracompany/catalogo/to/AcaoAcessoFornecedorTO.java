package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class AcaoAcessoFornecedorTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public AcaoAcessoFornecedorTO() {}
	
	public AcaoAcessoFornecedorTO(Integer idPerfilSCA, Integer idFornecedor, Integer idAcao, Date dtCriacao, String nmUsuarioCriacao) {
		this.idPerfilSCA = idPerfilSCA;
		this.idFornecedor = idFornecedor;
		this.idAcao = idAcao;
		this.dtCriacao = dtCriacao;
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	   
    private Integer idAcaoAcessoFornecedor;
    private Integer idPerfilSCA;
    private Integer idFornecedor;
    private Integer idAcao;
    private Date dtCriacao;
    private String nmUsuarioCriacao;
    
	public Integer getIdAcao() {
		return idAcao;
	}

	public void setIdAcao(Integer idAcao) {
		this.idAcao = idAcao;
	}

	public Integer getIdAcaoAcessoFornecedor() {
		return idAcaoAcessoFornecedor;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public void setIdAcaoAcessoFornecedor(Integer idAcaoAcessoFornecedor) {
		this.idAcaoAcessoFornecedor = idAcaoAcessoFornecedor;
	}

	public Integer getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public Integer getIdPerfilSCA() {
		return idPerfilSCA;
	}

	public void setIdPerfilSCA(Integer idPerfilSCA) {
		this.idPerfilSCA = idPerfilSCA;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idAcao: " + this.idAcao, "idPerfilSCA: " + this.idPerfilSCA, "idFornecedor: " + this.idFornecedor}, ", ");
	}

}
