package com.indracompany.catalogo.to;

public class CanalVendaSolicitacaoComercialTO {
	
	private Long idCanalVendaSolicitacaoComercial;
	private CanalVendaTO canalVendaTO;
	private Long idSolicitacaoComercial;
	private String inDisponivel;
	private Boolean inPossuiAreaConcorrencia = Boolean.FALSE;
	private Boolean inPossuiPlanoMinuto = Boolean.FALSE;
	private String inCriacaoCatalogo;

	public CanalVendaSolicitacaoComercialTO(){}
	
	public CanalVendaSolicitacaoComercialTO(Long id){
		this.idCanalVendaSolicitacaoComercial = new Long(id);
	}

	public CanalVendaSolicitacaoComercialTO(
			CanalVendaTO canalVendaTO
			,Long idSolicitacaoComercial
			,String inDisponivel
	){
		this.canalVendaTO = canalVendaTO;
		this.idSolicitacaoComercial = idSolicitacaoComercial;
		this.inDisponivel = inDisponivel;
	}
	
	public CanalVendaSolicitacaoComercialTO(
			Long idCanalVendaSolicitacaoComercial
			,CanalVendaTO canalVendaTO
			,Long idSolicitacaoComercial
			,String inDisponivel
			,String inAreaConcorrenciaSolicitacaoComercial
			,String inPlanoMinutoSolicitacaoComercial
	){
		this.idCanalVendaSolicitacaoComercial = idCanalVendaSolicitacaoComercial;
		this.canalVendaTO = canalVendaTO;
		this.idSolicitacaoComercial = idSolicitacaoComercial;
		this.inDisponivel = inDisponivel;
	}

	public CanalVendaTO getCanalVendaTO() {
		return canalVendaTO;
	}

	public void setCanalVendaTO(CanalVendaTO canalVendaTO) {
		this.canalVendaTO = canalVendaTO;
	}

	public Long getIdCanalVendaSolicitacaoComercial() {
		return idCanalVendaSolicitacaoComercial;
	}

	public void setIdCanalVendaSolicitacaoComercial(
			Long idCanalVendaSolicitacaoComercial) {
		this.idCanalVendaSolicitacaoComercial = idCanalVendaSolicitacaoComercial;
	}

	public Long getIdSolicitacaoComercial() {
		return idSolicitacaoComercial;
	}

	public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
		this.idSolicitacaoComercial = idSolicitacaoComercial;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public Boolean getInPossuiAreaConcorrencia() {
		return inPossuiAreaConcorrencia;
	}

	public void setInPossuiAreaConcorrencia(Boolean inPossuiAreaConcorrencia) {
		this.inPossuiAreaConcorrencia = inPossuiAreaConcorrencia;
	}

	public Boolean getInPossuiPlanoMinuto() {
		return inPossuiPlanoMinuto;
	}

	public void setInPossuiPlanoMinuto(Boolean inPossuiPlanoMinuto) {
		this.inPossuiPlanoMinuto = inPossuiPlanoMinuto;
	}

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}

}
