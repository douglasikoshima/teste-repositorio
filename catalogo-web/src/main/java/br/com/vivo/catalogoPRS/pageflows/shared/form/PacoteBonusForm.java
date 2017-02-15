package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.VivoNetVO;


public class PacoteBonusForm extends ValidatorActionForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3550328227514042009L;
	
	private Long paginaSolicitada;
	private String url;
	private Integer idPlataformaInt; 
	private Integer idPlataforma[]; 		// Plataforma
	private String ativo;
	private String nmServico;
	private Integer idCanalAtendimentoInt;
	private Integer idCanalAtendimento[];  	// Canal
	private String cdFuncionalidade;
	private Integer idServicoInteratividade;
	private Integer idTecnologiaInt;
	private Integer idTecnologia[];       	// Tecnologia
	private String sgTipoLinha;
	private Integer idTipoClienteInt;
	private Integer idTipoCliente[];      	// Tipo Cliente
	private Integer idParametroBase[];
	private String vlParametro[];
	private Integer idParametroBaseChecked[];
	
	private String numeroIP;
	List<VivoNetVO> listIP;
	
	
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public Integer[] getIdParametroBase() {
		return idParametroBase;
	}
	public void setIdParametroBase(Integer[] idParametroBase) {
		this.idParametroBase = idParametroBase;
	}
	public String[] getVlParametro() {
		return vlParametro;
	}
	public void setVlParametro(String[] vlParametro) {
		this.vlParametro = vlParametro;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCdFuncionalidade() {
		return cdFuncionalidade;
	}
	public void setCdFuncionalidade(String cdFuncionalidade) {
		this.cdFuncionalidade = cdFuncionalidade;
	}
			
	public Integer[] getIdCanalAtendimento() {
		return idCanalAtendimento;
	}
	public void setIdCanalAtendimento(Integer[] idCanalAtendimento) {
		this.idCanalAtendimento = idCanalAtendimento;
	}
	public Integer[] getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Integer[] idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Integer[] getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(Integer[] idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	
	public Integer getIdCanalAtendimentoInt() {
		return idCanalAtendimentoInt;
	}
	public void setIdCanalAtendimentoInt(Integer idCanalAtendimentoInt) {
		this.idCanalAtendimentoInt = idCanalAtendimentoInt;
	}
	public Integer getIdPlataformaInt() {
		return idPlataformaInt;
	}
	public void setIdPlataformaInt(Integer idPlataformaInt) {
		this.idPlataformaInt = idPlataformaInt;
	}
	public Integer getIdTecnologiaInt() {
		return idTecnologiaInt;
	}
	public void setIdTecnologiaInt(Integer idTecnologiaInt) {
		this.idTecnologiaInt = idTecnologiaInt;
	}
	public Integer getIdTipoClienteInt() {
		return idTipoClienteInt;
	}
	public void setIdTipoClienteInt(Integer idTipoClienteInt) {
		this.idTipoClienteInt = idTipoClienteInt;
	}
	public String getNmServico() {
		return nmServico;
	}
	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}
	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	public String getAtivo() {
		return ativo;
	}
	
	public Integer[] getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Integer[] idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Integer getIdServicoInteratividade() {
		return idServicoInteratividade;
	}

	public void setIdServicoInteratividade(Integer idServicoInteratividade) {
		this.idServicoInteratividade = idServicoInteratividade;
	}
	public String getSgTipoLinha() {
		return sgTipoLinha;
	}
	public void setSgTipoLinha(String sgTipoLinha) {
		this.sgTipoLinha = sgTipoLinha;
	}
	public Integer[] getIdParametroBaseChecked() {
		return idParametroBaseChecked;
	}
	public void setIdParametroBaseChecked(Integer[] idParametroBaseChecked) {
		this.idParametroBaseChecked = idParametroBaseChecked;
	}
	public String getNumeroIP() {
		return numeroIP;
	}
	public void setNumeroIP(String numeroIP) {
		this.numeroIP = numeroIP;
	}
	public List<VivoNetVO> getListIP() {
		return listIP;
	}
	public void setListIP(List<VivoNetVO> listIP) {
		this.listIP = listIP;
	}
}
