package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

public class SegmentoPlanoServicoForm extends ValidatorActionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String searchType;
	private Integer idPlano;
	private String cdPlano;
	private String nmPlano;
	private Integer idPlataforma;
	private Integer idCategoria;
	private String inTitular;
	private Long idSegmento;
	private String inInfancia;
	private Integer[] idPlanoCheckedList;
	private Long idSegmentoNew;
	private Boolean inInfanciaNew;
	private Boolean enabledCfgSegmento = Boolean.FALSE;
	
	private Integer idServico;
	private String cdServico;
	private String nmServico;
	private Integer idTipoServico;
	private Integer idFamilia;
	private String cdCategoria;
	private Integer idTecnologia;
	private Integer[] idServicoCheckedList;

	
	public SegmentoPlanoServicoForm(){
		this.searchType = "movel";
	}
	
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public Integer getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Integer idPlano) {
		this.idPlano = idPlano;
	}
	public String getCdPlano() {
		return cdPlano;
	}
	public void setCdPlano(String cdPlano) {
		this.cdPlano = cdPlano;
	}
	public String getNmPlano() {
		return nmPlano;
	}
	public void setNmPlano(String nmPlano) {
		this.nmPlano = nmPlano;
	}
	public Integer getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Integer getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getInTitular() {
		return inTitular;
	}
	public void setInTitular(String inTitular) {
		this.inTitular = inTitular;
	}
	public Long getIdSegmento() {
		return idSegmento;
	}
	public void setIdSegmento(Long idSegmento) {
		this.idSegmento = idSegmento;
	}
	public String getInInfancia() {
		return inInfancia;
	}
	public void setInInfancia(String inInfancia) {
		this.inInfancia = inInfancia;
	}
	public Integer[] getIdPlanoCheckedList() {
		return idPlanoCheckedList;
	}
	public void setIdPlanoCheckedList(Integer[] idPlanoCheckedList) {
		this.idPlanoCheckedList = idPlanoCheckedList;
	}
	public Long getIdSegmentoNew() {
		return idSegmentoNew;
	}
	public void setIdSegmentoNew(Long idSegmentoNew) {
		this.idSegmentoNew = idSegmentoNew;
	}
	public Boolean getInInfanciaNew() {
		return inInfanciaNew;
	}
	public void setInInfanciaNew(Boolean inInfanciaNew) {
		this.inInfanciaNew = inInfanciaNew;
	}
	public Boolean getEnabledCfgSegmento() {
		return enabledCfgSegmento;
	}
	public void setEnabledCfgSegmento(Boolean enabledCfgSegmento) {
		this.enabledCfgSegmento = enabledCfgSegmento;
	}
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}
	public String getCdServico() {
		return cdServico;
	}
	public void setCdServico(String cdServico) {
		this.cdServico = cdServico;
	}
	public String getNmServico() {
		return nmServico;
	}
	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}
	public Integer getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Integer idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public Integer getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(Integer idFamilia) {
		this.idFamilia = idFamilia;
	}
	public String getCdCategoria() {
		return cdCategoria;
	}
	public void setCdCategoria(String cdCategoria) {
		this.cdCategoria = cdCategoria;
	}
	public Integer getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Integer idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Integer[] getIdServicoCheckedList() {
		return idServicoCheckedList;
	}
	public void setIdServicoCheckedList(Integer[] idServicoCheckedList) {
		this.idServicoCheckedList = idServicoCheckedList;
	}
}
