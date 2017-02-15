package com.indracompany.catalogo.to;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class EncargoPoliticaPrecificacaoTO {
	
	private Long idEncargo;
	private String cdEncargo;
	private String dsEncargo;
	private BigDecimal vlEncargo;
	private String cdMoeda;
	private Long pzGratuidade;
	private ServicoSolicitacaoComercialTO solicitacaoComercialTO;
	private Date dtInicial;
	private Date dtFinal;
	private String nmClasseServicoPrincipal;
	private String nmPacote;
	private String nmEmpresaInstaladora;
	private TipoEncargoTO tipoEncargoTO;
	private List<PoliticaValorAtributoTO> politicaValorAtributoTOList;
	private String politicaValorAtributoTOListConcat;
	
	public String getPoliticaValorAtributoTOListConcat() {
		return politicaValorAtributoTOListConcat;
	}
	public void setPoliticaValorAtributoTOListConcat(
			String politicaValorAtributoTOListConcat) {
		this.politicaValorAtributoTOListConcat = politicaValorAtributoTOListConcat;
	}
	public String getCdEncargo() {
		return cdEncargo;
	}
	public void setCdEncargo(String cdEncargo) {
		this.cdEncargo = cdEncargo;
	}
	public String getCdMoeda() {
		return cdMoeda;
	}
	public void setCdMoeda(String cdMoeda) {
		this.cdMoeda = cdMoeda;
	}
	public String getDsEncargo() {
		return dsEncargo;
	}
	public void setDsEncargo(String dsEncargo) {
		this.dsEncargo = dsEncargo;
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
	public Long getIdEncargo() {
		return idEncargo;
	}
	public void setIdEncargo(Long idEncargo) {
		this.idEncargo = idEncargo;
	}
	public String getNmClasseServicoPrincipal() {
		return nmClasseServicoPrincipal;
	}
	public void setNmClasseServicoPrincipal(String nmClasseServicoPrincipal) {
		this.nmClasseServicoPrincipal = nmClasseServicoPrincipal;
	}
	public String getNmEmpresaInstaladora() {
		return nmEmpresaInstaladora;
	}
	public void setNmEmpresaInstaladora(String nmEmpresaInstaladora) {
		this.nmEmpresaInstaladora = nmEmpresaInstaladora;
	}
	public String getNmPacote() {
		return nmPacote;
	}
	public void setNmPacote(String nmPacote) {
		this.nmPacote = nmPacote;
	}
	public Long getPzGratuidade() {
		return pzGratuidade;
	}
	public void setPzGratuidade(Long pzGratuidade) {
		this.pzGratuidade = pzGratuidade;
	}
	public ServicoSolicitacaoComercialTO getSolicitacaoComercialTO() {
		return solicitacaoComercialTO;
	}
	public void setSolicitacaoComercialTO(
			ServicoSolicitacaoComercialTO solicitacaoComercialTO) {
		this.solicitacaoComercialTO = solicitacaoComercialTO;
	}
	public TipoEncargoTO getTipoEncargoTO() {
		return tipoEncargoTO;
	}
	public void setTipoEncargoTO(TipoEncargoTO tipoEncargoTO) {
		this.tipoEncargoTO = tipoEncargoTO;
	}
	public BigDecimal getVlEncargo() {
		return vlEncargo;
	}
	public void setVlEncargo(BigDecimal vlEncargo) {
		this.vlEncargo = vlEncargo;
	}
	public List<PoliticaValorAtributoTO> getPoliticaValorAtributoTOList() {
		return politicaValorAtributoTOList;
	}
	public void setPoliticaValorAtributoTOList(
			List<PoliticaValorAtributoTO> politicaValorAtributoTOList) {
		this.politicaValorAtributoTOList = politicaValorAtributoTOList;
	}
}
