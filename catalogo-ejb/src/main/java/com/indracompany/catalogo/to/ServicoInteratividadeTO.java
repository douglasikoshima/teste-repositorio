package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * @author Luiz Pereira
 *
 */
public class ServicoInteratividadeTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ServicoInteratividadeTO() {}
	
	public ServicoInteratividadeTO(Integer idServicoInteratividade) {
		this.idServicoInteratividade = idServicoInteratividade;		
	}
	
	private Integer idServicoInteratividade;
	private String ativo;
	private FuncionalidadeTO funcionalidadeTO;
	private Date dtCriacao;
	private Date dtUltimaAlteracao;
	private String nmServico;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;
	private String url;
	private List<ServicoIntCanalTO> servicoIntCanalTOList;
	private List<ServicoIntPlataformaTO> servicoIntPlataformaTOList;	
	private List<ServicoIntTecnologiaTO> servicoIntTecnologiaTOList;
	private List<ServicoIntClienteTO> servicoIntClienteTOList;	
	
	private TipoLinhaTO tipoLinhaTO;
	private List<SrvInteratividadeParamTO> srvInteratividadeParametroTOList;
	
	public String getAtivo() {
		return ativo;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public TipoLinhaTO getTipoLinhaTO() {
		return tipoLinhaTO;
	}
	public void setTipoLinhaTO(TipoLinhaTO tipoLinhaTO) {
		this.tipoLinhaTO = tipoLinhaTO;
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
	public Integer getIdServicoInteratividade() {
		return idServicoInteratividade;
	}
	public void setIdServicoInteratividade(Integer idServicoInteratividade) {
		this.idServicoInteratividade = idServicoInteratividade;
	}
	public String getNmServico() {
		return nmServico;
	}
	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	public FuncionalidadeTO getFuncionalidadeTO() {
		return funcionalidadeTO;	
	}
	public void setFuncionalidadeTO(FuncionalidadeTO funcionalidadeTO) {
		this.funcionalidadeTO = funcionalidadeTO;
	}
	
	public List<ServicoIntCanalTO> getServicoIntCanalTOList() {
		return servicoIntCanalTOList;
	}

	public void setServicoIntCanalTOList(List<ServicoIntCanalTO> servicoIntCanalTOList) {
		this.servicoIntCanalTOList = servicoIntCanalTOList;
	}

	public List<ServicoIntClienteTO> getServicoIntClienteTOList() {
		return servicoIntClienteTOList;
	}

	public void setServicoIntClienteTOList(
			List<ServicoIntClienteTO> servicoIntClienteTOList) {
		this.servicoIntClienteTOList = servicoIntClienteTOList;
	}

	public List<ServicoIntPlataformaTO> getServicoIntPlataformaTOList() {
		return servicoIntPlataformaTOList;
	}

	public void setServicoIntPlataformaTOList(
			List<ServicoIntPlataformaTO> servicoIntPlataformaTOList) {
		this.servicoIntPlataformaTOList = servicoIntPlataformaTOList;
	}

	public List<ServicoIntTecnologiaTO> getServicoIntTecnologiaTOList() {
		return servicoIntTecnologiaTOList;
	}

	public void setServicoIntTecnologiaTOList(
			List<ServicoIntTecnologiaTO> servicoIntTecnologiaTOList) {
		this.servicoIntTecnologiaTOList = servicoIntTecnologiaTOList;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idServicoInteratividade: " + this.idServicoInteratividade, "nmServico: " + this.nmServico}, ", ");
	}
	public List<SrvInteratividadeParamTO> getSrvInteratividadeParametroTOList() {
		return srvInteratividadeParametroTOList;
	}
	public void setSrvInteratividadeParametroTOList(
			List<SrvInteratividadeParamTO> srvInteratividadeParametroTOList) {
		this.srvInteratividadeParametroTOList = srvInteratividadeParametroTOList;
	}	
}
