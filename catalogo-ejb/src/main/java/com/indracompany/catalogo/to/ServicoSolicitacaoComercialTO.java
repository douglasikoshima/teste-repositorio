package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.List;

public class ServicoSolicitacaoComercialTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 61815442842474004L;
	
	private Integer idServico;
	private Integer idSistema;
	private List<Integer> idSistemaList;
	
	private Long idSolicitacaoComercial;
	private String cdSolicitacaoComercial;
	private String nmSolicitacaoComercial;
	private Long idTipoSolicitacaoComercial;
	private String cdTipoSolicitacaoComercial;
	private String nmTipoSolicitacaoComercial;
	private Long pzMaximoVigencia;
	private Long pzMaximoAtendimento;
	private String inDisponivel;
	private String cdCorEquipamento;
	private String cdPropriedadeEquipamento;
	private String cdNaturezaPedido;
	private String inOfertaClienteInadimplente;
	
	private PoliticaDispSlctComercialTO politicaDispSlctComercialTO;
	
	private List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList;
	private List<TipoEncargoTO> tipoEncargoTOList;
	private List<CanalVendaTO> canalVendaFullTOList;
	private List<CondicaoPagamentoTO> condicaoPagamentoFullTOList;
	
	private Long idCanalVendaSolicitacaoComercial;
	private List<CanalVendaTO> canalVendaSelecionavelTOList;
	private List<CanalVendaSolicitacaoComercialTO> canalVendaSolicitacaoComercialTOList;
	
	private List<DisponibilidadeSlctComercialTO> disponibilidadeSlctComercialTOList;
	private List<AreaConcorrenciaTO> areaConcorrenciaSlctSelecionavelTOList;
	private List<AreaConcorrenciaTO> areaConcorrenciaSlctSelecionadaTOList;
	private List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoSlctSelecionavelTOList;
	private List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoSlctSelecionadoTOList;
	
	private Long idEncargo;
	private String dsEncargo;
	private Long pzGratuidade;
	private String nmPacote;
	private Long idTipoEncargo;
	private List<EncargoPoliticaPrecificacaoTO> encargoPoliticaPrecificacaoTOList;

	private Long idCanalVendaEncargo;
	private Long idCondicaoPagamentoEncargo;
	private PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO;
	private List<CanalVendaTO> canalVendaCndcPgtoEncargoTOList;
	private List<CondicaoPagamentoEncargoTO> condicaoPagamentoEncargoTOList;
	
	private CondicaoPagamentoEncargoTO condicaoPagamentoEncargoTO; 
	
	private List<DisponibilidadeCndcPagamentoTO> disponibilidadeCndcPagamentoTOList;
	private List<AreaConcorrenciaTO> areaConcorrenciaCndcSelecionavelTOList;
	private List<AreaConcorrenciaTO> areaConcorrenciaCndcSelecionadaTOList;
	private List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoCndcSelecionavelTOList;
	private List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoCndcSelecionadoTOList;
	
	private String mensagemRetorno;
	
	public ServicoSolicitacaoComercialTO() {
		super();
	}
	public ServicoSolicitacaoComercialTO(Long idSolicitacaoComercial, String inOfertaClienteInadimplente) {
		super();
		this.idSolicitacaoComercial = idSolicitacaoComercial;
		this.inOfertaClienteInadimplente = inOfertaClienteInadimplente;
	}
	public List<AreaConcorrenciaTO> getAreaConcorrenciaCndcSelecionadaTOList() {
		return areaConcorrenciaCndcSelecionadaTOList;
	}
	public void setAreaConcorrenciaCndcSelecionadaTOList(
			List<AreaConcorrenciaTO> areaConcorrenciaCndcSelecionadaTOList) {
		this.areaConcorrenciaCndcSelecionadaTOList = areaConcorrenciaCndcSelecionadaTOList;
	}
	public List<AreaConcorrenciaTO> getAreaConcorrenciaCndcSelecionavelTOList() {
		return areaConcorrenciaCndcSelecionavelTOList;
	}
	public void setAreaConcorrenciaCndcSelecionavelTOList(
			List<AreaConcorrenciaTO> areaConcorrenciaCndcSelecionavelTOList) {
		this.areaConcorrenciaCndcSelecionavelTOList = areaConcorrenciaCndcSelecionavelTOList;
	}
	public List<AreaConcorrenciaTO> getAreaConcorrenciaSlctSelecionadaTOList() {
		return areaConcorrenciaSlctSelecionadaTOList;
	}
	public void setAreaConcorrenciaSlctSelecionadaTOList(
			List<AreaConcorrenciaTO> areaConcorrenciaSlctSelecionadaTOList) {
		this.areaConcorrenciaSlctSelecionadaTOList = areaConcorrenciaSlctSelecionadaTOList;
	}
	public List<AreaConcorrenciaTO> getAreaConcorrenciaSlctSelecionavelTOList() {
		return areaConcorrenciaSlctSelecionavelTOList;
	}
	public void setAreaConcorrenciaSlctSelecionavelTOList(
			List<AreaConcorrenciaTO> areaConcorrenciaSlctSelecionavelTOList) {
		this.areaConcorrenciaSlctSelecionavelTOList = areaConcorrenciaSlctSelecionavelTOList;
	}
	public List<CanalVendaTO> getCanalVendaSelecionavelTOList() {
		return canalVendaSelecionavelTOList;
	}
	public void setCanalVendaSelecionavelTOList(
			List<CanalVendaTO> canalVendaSelecionavelTOList) {
		this.canalVendaSelecionavelTOList = canalVendaSelecionavelTOList;
	}
	public List<CanalVendaSolicitacaoComercialTO> getCanalVendaSolicitacaoComercialTOList() {
		return canalVendaSolicitacaoComercialTOList;
	}
	public void setCanalVendaSolicitacaoComercialTOList(
			List<CanalVendaSolicitacaoComercialTO> canalVendaSolicitacaoComercialTOList) {
		this.canalVendaSolicitacaoComercialTOList = canalVendaSolicitacaoComercialTOList;
	}
	public String getCdCorEquipamento() {
		return cdCorEquipamento;
	}
	public void setCdCorEquipamento(String cdCorEquipamento) {
		this.cdCorEquipamento = cdCorEquipamento;
	}
	public String getCdNaturezaPedido() {
		return cdNaturezaPedido;
	}
	public void setCdNaturezaPedido(String cdNaturezaPedido) {
		this.cdNaturezaPedido = cdNaturezaPedido;
	}
	public String getCdPropriedadeEquipamento() {
		return cdPropriedadeEquipamento;
	}
	public void setCdPropriedadeEquipamento(String cdPropriedadeEquipamento) {
		this.cdPropriedadeEquipamento = cdPropriedadeEquipamento;
	}
	public String getCdSolicitacaoComercial() {
		return cdSolicitacaoComercial;
	}
	public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
		this.cdSolicitacaoComercial = cdSolicitacaoComercial;
	}
	public List<CondicaoPagamentoEncargoTO> getCondicaoPagamentoEncargoTOList() {
		return condicaoPagamentoEncargoTOList;
	}
	public void setCondicaoPagamentoEncargoTOList(
			List<CondicaoPagamentoEncargoTO> condicaoPagamentoEncargoTOList) {
		this.condicaoPagamentoEncargoTOList = condicaoPagamentoEncargoTOList;
	}
	public List<DisponibilidadeCndcPagamentoTO> getDisponibilidadeCndcPagamentoTOList() {
		return disponibilidadeCndcPagamentoTOList;
	}
	public void setDisponibilidadeCndcPagamentoTOList(
			List<DisponibilidadeCndcPagamentoTO> disponibilidadeCndcPagamentoTOList) {
		this.disponibilidadeCndcPagamentoTOList = disponibilidadeCndcPagamentoTOList;
	}
	public List<DisponibilidadeSlctComercialTO> getDisponibilidadeSlctComercialTOList() {
		return disponibilidadeSlctComercialTOList;
	}
	public void setDisponibilidadeSlctComercialTOList(
			List<DisponibilidadeSlctComercialTO> disponibilidadeSlctComercialTOList) {
		this.disponibilidadeSlctComercialTOList = disponibilidadeSlctComercialTOList;
	}
	public List<EncargoPoliticaPrecificacaoTO> getEncargoPoliticaPrecificacaoTOList() {
		return encargoPoliticaPrecificacaoTOList;
	}
	public void setEncargoPoliticaPrecificacaoTOList(
			List<EncargoPoliticaPrecificacaoTO> encargoPoliticaPrecificacaoTOList) {
		this.encargoPoliticaPrecificacaoTOList = encargoPoliticaPrecificacaoTOList;
	}
	public List<EspServicoPlanoMinutoTO> getEspServicoPlanoMinutoCndcSelecionadoTOList() {
		return espServicoPlanoMinutoCndcSelecionadoTOList;
	}
	public void setEspServicoPlanoMinutoCndcSelecionadoTOList(
			List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoCndcSelecionadoTOList) {
		this.espServicoPlanoMinutoCndcSelecionadoTOList = espServicoPlanoMinutoCndcSelecionadoTOList;
	}
	public List<EspServicoPlanoMinutoTO> getEspServicoPlanoMinutoCndcSelecionavelTOList() {
		return espServicoPlanoMinutoCndcSelecionavelTOList;
	}
	public void setEspServicoPlanoMinutoCndcSelecionavelTOList(
			List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoCndcSelecionavelTOList) {
		this.espServicoPlanoMinutoCndcSelecionavelTOList = espServicoPlanoMinutoCndcSelecionavelTOList;
	}
	public List<EspServicoPlanoMinutoTO> getEspServicoPlanoMinutoSlctSelecionadoTOList() {
		return espServicoPlanoMinutoSlctSelecionadoTOList;
	}
	public void setEspServicoPlanoMinutoSlctSelecionadoTOList(
			List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoSlctSelecionadoTOList) {
		this.espServicoPlanoMinutoSlctSelecionadoTOList = espServicoPlanoMinutoSlctSelecionadoTOList;
	}
	public List<EspServicoPlanoMinutoTO> getEspServicoPlanoMinutoSlctSelecionavelTOList() {
		return espServicoPlanoMinutoSlctSelecionavelTOList;
	}
	public void setEspServicoPlanoMinutoSlctSelecionavelTOList(
			List<EspServicoPlanoMinutoTO> espServicoPlanoMinutoSlctSelecionavelTOList) {
		this.espServicoPlanoMinutoSlctSelecionavelTOList = espServicoPlanoMinutoSlctSelecionavelTOList;
	}
	public Long getIdCanalVendaEncargo() {
		return idCanalVendaEncargo;
	}
	public void setIdCanalVendaEncargo(Long idCanalVendaEncargo) {
		this.idCanalVendaEncargo = idCanalVendaEncargo;
	}
	public Long getIdCanalVendaSolicitacaoComercial() {
		return idCanalVendaSolicitacaoComercial;
	}
	public void setIdCanalVendaSolicitacaoComercial(
			Long idCanalVendaSolicitacaoComercial) {
		this.idCanalVendaSolicitacaoComercial = idCanalVendaSolicitacaoComercial;
	}
	public Long getIdCondicaoPagamentoEncargo() {
		return idCondicaoPagamentoEncargo;
	}
	public void setIdCondicaoPagamentoEncargo(Long idCondicaoPagamentoEncargo) {
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}
	public Long getIdEncargo() {
		return idEncargo;
	}
	public void setIdEncargo(Long idEncargo) {
		this.idEncargo = idEncargo;
	}
	public Integer getIdServico() {
		return idServico;
	}
	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}
	public Integer getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Integer idSistema) {
		this.idSistema = idSistema;
	}
	public List<Integer> getIdSistemaList() {
		return idSistemaList;
	}
	public void setIdSistemaList(List<Integer> idSistemaList) {
		this.idSistemaList = idSistemaList;
	}
	public Long getIdSolicitacaoComercial() {
		return idSolicitacaoComercial;
	}
	public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
		this.idSolicitacaoComercial = idSolicitacaoComercial;
	}
	public Long getIdTipoEncargo() {
		return idTipoEncargo;
	}
	public void setIdTipoEncargo(Long idTipoEncargo) {
		this.idTipoEncargo = idTipoEncargo;
	}
	public Long getIdTipoSolicitacaoComercial() {
		return idTipoSolicitacaoComercial;
	}
	public void setIdTipoSolicitacaoComercial(Long idTipoSolicitacaoComercial) {
		this.idTipoSolicitacaoComercial = idTipoSolicitacaoComercial;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getNmPacote() {
		return nmPacote;
	}
	public void setNmPacote(String nmPacote) {
		this.nmPacote = nmPacote;
	}
	public String getNmSolicitacaoComercial() {
		return nmSolicitacaoComercial;
	}
	public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
		this.nmSolicitacaoComercial = nmSolicitacaoComercial;
	}
	public String getNmTipoSolicitacaoComercial() {
		return nmTipoSolicitacaoComercial;
	}
	public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
		this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
	}
	public Long getPzGratuidade() {
		return pzGratuidade;
	}
	public void setPzGratuidade(Long pzGratuidade) {
		this.pzGratuidade = pzGratuidade;
	}
	public Long getPzMaximoAtendimento() {
		return pzMaximoAtendimento;
	}
	public void setPzMaximoAtendimento(Long pzMaximoAtendimento) {
		this.pzMaximoAtendimento = pzMaximoAtendimento;
	}
	public Long getPzMaximoVigencia() {
		return pzMaximoVigencia;
	}
	public void setPzMaximoVigencia(Long pzMaximoVigencia) {
		this.pzMaximoVigencia = pzMaximoVigencia;
	}
	public List<TipoEncargoTO> getTipoEncargoTOList() {
		return tipoEncargoTOList;
	}
	public void setTipoEncargoTOList(List<TipoEncargoTO> tipoEncargoTOList) {
		this.tipoEncargoTOList = tipoEncargoTOList;
	}
	public List<TipoSolicitacaoComercialTO> getTipoSolicitacaoComercialTOList() {
		return tipoSolicitacaoComercialTOList;
	}
	public void setTipoSolicitacaoComercialTOList(
			List<TipoSolicitacaoComercialTO> tipoSolicitacaoComercialTOList) {
		this.tipoSolicitacaoComercialTOList = tipoSolicitacaoComercialTOList;
	}
	public List<CanalVendaTO> getCanalVendaCndcPgtoEncargoTOList() {
		return canalVendaCndcPgtoEncargoTOList;
	}
	public void setCanalVendaCndcPgtoEncargoTOList(
			List<CanalVendaTO> canalVendaCndcPgtoEncargoTO) {
		this.canalVendaCndcPgtoEncargoTOList = canalVendaCndcPgtoEncargoTO;
	}
	public List<CanalVendaTO> getCanalVendaFullTOList() {
		return canalVendaFullTOList;
	}
	public void setCanalVendaFullTOList(List<CanalVendaTO> canalVendaTodosTOList) {
		this.canalVendaFullTOList = canalVendaTodosTOList;
	}
	public String getMensagemRetorno() {
		return mensagemRetorno;
	}
	public void setMensagemRetorno(String mensagemRetorno) {
		this.mensagemRetorno = mensagemRetorno;
	}
	public List<CondicaoPagamentoTO> getCondicaoPagamentoFullTOList() {
		return condicaoPagamentoFullTOList;
	}
	public void setCondicaoPagamentoFullTOList(
			List<CondicaoPagamentoTO> condicaoPagamentoFullTOList) {
		this.condicaoPagamentoFullTOList = condicaoPagamentoFullTOList;
	}
	public CondicaoPagamentoEncargoTO getCondicaoPagamentoEncargoTO() {
		return condicaoPagamentoEncargoTO;
	}
	public void setCondicaoPagamentoEncargoTO(
			CondicaoPagamentoEncargoTO condicaoPagamentoEncargoTO) {
		this.condicaoPagamentoEncargoTO = condicaoPagamentoEncargoTO;
	}
	public PoliticaDispSlctComercialTO getPoliticaDispSlctComercialTO() {
		return politicaDispSlctComercialTO;
	}
	public void setPoliticaDispSlctComercialTO(
			PoliticaDispSlctComercialTO politicaDispSlctComercialTO) {
		this.politicaDispSlctComercialTO = politicaDispSlctComercialTO;
	}
	public PoliticaDispCndcPagamentoTO getPoliticaDispCndcPagamentoTO() {
		return politicaDispCndcPagamentoTO;
	}
	public void setPoliticaDispCndcPagamentoTO(
			PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO) {
		this.politicaDispCndcPagamentoTO = politicaDispCndcPagamentoTO;
	}
    public String getDsEncargo() {
        return dsEncargo;
    }
    public void setDsEncargo(String dsEncargo) {
        this.dsEncargo = dsEncargo;
    }
	public String getInOfertaClienteInadimplente() {
		return inOfertaClienteInadimplente;
	}
	public void setInOfertaClienteInadimplente(String inOfertaClienteInadimplente) {
		this.inOfertaClienteInadimplente = inOfertaClienteInadimplente;
	}
	public String getCdTipoSolicitacaoComercial() {
		return cdTipoSolicitacaoComercial;
	}
	public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
		this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
	}
}
