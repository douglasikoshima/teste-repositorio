package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DetalheServicoFixaTO implements Serializable{

	private static final long serialVersionUID = 2571444278913612911L;
	
	private Integer idServico;
	private String nmSistema;
	private String cdCodigo;
	private String nmComercialOrigem;
	private String nmComercialCatalogo;
	private String descricaoOrigem;
	private String descricaoCatalogo;
	private String cdTipoServico;
	private String nmTipoServico;
	private Long qtMinutoLivreFMLocal;
	private Long qtMinutoLivreFFLocal;
	private BigDecimal vlMinutoAdicionalFFLocal;
	private String cdPacote;
	private String cdTipoPrecoPacote;
	private String inVendaIsolada;
	private String nmTecnologia;
	private String cdClasseServicoPrincipal;
	private String cdClasseServicoAdicional;
	private String cdTipoEquipamento;
	private String inPreFactibilidade;
	private String inPosFactibilidade;
	private String cdSiscom;
	private BigDecimal qtPercentualAuditoria;
	private Long nuOrdemAtendimento;
	private String inDisponivel;
	private String sgTipoAplicacaoDesconto;
	private BigDecimal vlDescontoAbsoluto;
	
	private String inEspServicoPacote;
	private String inClasseServicoPrincipal;
	private String inEmpresaInstaladora;
	private String inAreaConcorrenciaDesconto;
	private String inPlanoMinutoDesconto;
	private Boolean inPossuiVarDispDesconto = Boolean.FALSE;
	private String inAreaConcorrenciaFinanciamento;
	private String inPlanoMinutoFinanciamento;
	private Boolean inPossuiVarDispFinanciamento = Boolean.FALSE;
 	
	private List<AtributoTO> atributoTOList;
	
	private List<AtributoTO> atributoPoliticaPrecificacaoTOList;

    private Integer idFamilia;
    private Integer idCategoria;
    private String cdServicoOrigem;
    
    private Boolean alterarPoliticas;
	
	public DetalheServicoFixaTO(){}
	
	public DetalheServicoFixaTO(
			Integer idServico
		){
			this.idServico = idServico;
	}

	public List<AtributoTO> getAtributoTOList() {
		return atributoTOList;
	}

	public void setAtributoTOList(List<AtributoTO> atributoTOList) {
		this.atributoTOList = atributoTOList;
	}

	public String getCdClasseServicoAdicional() {
		return cdClasseServicoAdicional;
	}

	public void setCdClasseServicoAdicional(String cdClasseServicoAdicional) {
		this.cdClasseServicoAdicional = cdClasseServicoAdicional;
	}

	public String getCdClasseServicoPrincipal() {
		return cdClasseServicoPrincipal;
	}

	public void setCdClasseServicoPrincipal(String cdClasseServicoPrincipal) {
		this.cdClasseServicoPrincipal = cdClasseServicoPrincipal;
	}

	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public String getCdPacote() {
		return cdPacote;
	}

	public void setCdPacote(String cdPacote) {
		this.cdPacote = cdPacote;
	}

	public String getCdSiscom() {
		return cdSiscom;
	}

	public void setCdSiscom(String cdSiscom) {
		this.cdSiscom = cdSiscom;
	}

	public String getCdTipoEquipamento() {
		return cdTipoEquipamento;
	}

	public void setCdTipoEquipamento(String cdTipoEquipamento) {
		this.cdTipoEquipamento = cdTipoEquipamento;
	}

	public String getCdTipoPrecoPacote() {
		return cdTipoPrecoPacote;
	}

	public void setCdTipoPrecoPacote(String cdTipoPrecoPacote) {
		this.cdTipoPrecoPacote = cdTipoPrecoPacote;
	}

	public String getCdTipoServico() {
		return cdTipoServico;
	}

	public void setCdTipoServico(String cdTipoServico) {
		this.cdTipoServico = cdTipoServico;
	}

	public String getDescricaoCatalogo() {
		return descricaoCatalogo;
	}

	public void setDescricaoCatalogo(String descricaoCatalogo) {
		this.descricaoCatalogo = descricaoCatalogo;
	}

	public String getDescricaoOrigem() {
		return descricaoOrigem;
	}

	public void setDescricaoOrigem(String descricaoOrigem) {
		this.descricaoOrigem = descricaoOrigem;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public String getInAreaConcorrenciaDesconto() {
		return inAreaConcorrenciaDesconto;
	}

	public void setInAreaConcorrenciaDesconto(String inAreaConcorrenciaDesconto) {
		this.inAreaConcorrenciaDesconto = inAreaConcorrenciaDesconto;
	}

	public String getInAreaConcorrenciaFinanciamento() {
		return inAreaConcorrenciaFinanciamento;
	}

	public void setInAreaConcorrenciaFinanciamento(
			String inAreaConcorrenciaFinanciamento) {
		this.inAreaConcorrenciaFinanciamento = inAreaConcorrenciaFinanciamento;
	}

	public String getInClasseServicoPrincipal() {
		return inClasseServicoPrincipal;
	}

	public void setInClasseServicoPrincipal(String inClasseServicoPrincipal) {
		this.inClasseServicoPrincipal = inClasseServicoPrincipal;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getInEmpresaInstaladora() {
		return inEmpresaInstaladora;
	}

	public void setInEmpresaInstaladora(String inEmpresaInstaladora) {
		this.inEmpresaInstaladora = inEmpresaInstaladora;
	}

	public String getInEspServicoPacote() {
		return inEspServicoPacote;
	}

	public void setInEspServicoPacote(String inEspServicoPacote) {
		this.inEspServicoPacote = inEspServicoPacote;
	}

	public String getInPlanoMinutoDesconto() {
		return inPlanoMinutoDesconto;
	}

	public void setInPlanoMinutoDesconto(String inPlanoMinutoDesconto) {
		this.inPlanoMinutoDesconto = inPlanoMinutoDesconto;
	}

	public String getInPlanoMinutoFinanciamento() {
		return inPlanoMinutoFinanciamento;
	}

	public void setInPlanoMinutoFinanciamento(String inPlanoMinutoFinanciamento) {
		this.inPlanoMinutoFinanciamento = inPlanoMinutoFinanciamento;
	}

	public String getInPosFactibilidade() {
		return inPosFactibilidade;
	}

	public void setInPosFactibilidade(String inPosFactibilidade) {
		this.inPosFactibilidade = inPosFactibilidade;
	}

	public Boolean getInPossuiVarDispDesconto() {
		return inPossuiVarDispDesconto;
	}

	public void setInPossuiVarDispDesconto(Boolean inPossuiVarDispDesconto) {
		this.inPossuiVarDispDesconto = inPossuiVarDispDesconto;
	}

	public Boolean getInPossuiVarDispFinanciamento() {
		return inPossuiVarDispFinanciamento;
	}

	public void setInPossuiVarDispFinanciamento(Boolean inPossuiVarDispFinanciamento) {
		this.inPossuiVarDispFinanciamento = inPossuiVarDispFinanciamento;
	}

	public String getInPreFactibilidade() {
		return inPreFactibilidade;
	}

	public void setInPreFactibilidade(String inPreFactibilidade) {
		this.inPreFactibilidade = inPreFactibilidade;
	}

	public String getInVendaIsolada() {
		return inVendaIsolada;
	}

	public void setInVendaIsolada(String inVendaIsolada) {
		this.inVendaIsolada = inVendaIsolada;
	}

	public String getNmComercialCatalogo() {
		return nmComercialCatalogo;
	}

	public void setNmComercialCatalogo(String nmComercialCatalogo) {
		this.nmComercialCatalogo = nmComercialCatalogo;
	}

	public String getNmComercialOrigem() {
		return nmComercialOrigem;
	}

	public void setNmComercialOrigem(String nmComercialOrigem) {
		this.nmComercialOrigem = nmComercialOrigem;
	}

	public String getNmSistema() {
		return nmSistema;
	}

	public void setNmSistema(String nmSistema) {
		this.nmSistema = nmSistema;
	}

	public String getNmTecnologia() {
		return nmTecnologia;
	}

	public void setNmTecnologia(String nmTecnologia) {
		this.nmTecnologia = nmTecnologia;
	}

	public String getNmTipoServico() {
		return nmTipoServico;
	}

	public void setNmTipoServico(String nmTipoServico) {
		this.nmTipoServico = nmTipoServico;
	}

	public Long getNuOrdemAtendimento() {
		return nuOrdemAtendimento;
	}

	public void setNuOrdemAtendimento(Long nuOrdemAtendimento) {
		this.nuOrdemAtendimento = nuOrdemAtendimento;
	}

	public Long getQtMinutoLivreFFLocal() {
		return qtMinutoLivreFFLocal;
	}

	public void setQtMinutoLivreFFLocal(Long qtMinutoLivreFFLocal) {
		this.qtMinutoLivreFFLocal = qtMinutoLivreFFLocal;
	}

	public Long getQtMinutoLivreFMLocal() {
		return qtMinutoLivreFMLocal;
	}

	public void setQtMinutoLivreFMLocal(Long qtMinutoLivreFMLocal) {
		this.qtMinutoLivreFMLocal = qtMinutoLivreFMLocal;
	}

	public BigDecimal getQtPercentualAuditoria() {
		return qtPercentualAuditoria;
	}

	public void setQtPercentualAuditoria(BigDecimal qtPercentualAuditoria) {
		this.qtPercentualAuditoria = qtPercentualAuditoria;
	}

	public String getSgTipoAplicacaoDesconto() {
		return sgTipoAplicacaoDesconto;
	}

	public void setSgTipoAplicacaoDesconto(String sgTipoAplicacaoDesconto) {
		this.sgTipoAplicacaoDesconto = sgTipoAplicacaoDesconto;
	}

	public BigDecimal getVlDescontoAbsoluto() {
		return vlDescontoAbsoluto;
	}

	public void setVlDescontoAbsoluto(BigDecimal vlDescontoAbsoluto) {
		this.vlDescontoAbsoluto = vlDescontoAbsoluto;
	}

	public BigDecimal getVlMinutoAdicionalFFLocal() {
		return vlMinutoAdicionalFFLocal;
	}

	public void setVlMinutoAdicionalFFLocal(BigDecimal vlMinutoAdicionalFFLocal) {
		this.vlMinutoAdicionalFFLocal = vlMinutoAdicionalFFLocal;
	}

	public List<AtributoTO> getAtributoPoliticaPrecificacaoTOList() {
		return atributoPoliticaPrecificacaoTOList;
	}

	public void setAtributoPoliticaPrecificacaoTOList(
			List<AtributoTO> atributoPoliticaPrecificacaoTOList) {
		this.atributoPoliticaPrecificacaoTOList = atributoPoliticaPrecificacaoTOList;
	}

    public Integer getIdFamilia() {
        return this.idFamilia;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getCdServicoOrigem() {
        return cdServicoOrigem;
    }

    public void setCdServicoOrigem(String cdServicoOrigem) {
        this.cdServicoOrigem = cdServicoOrigem;
    }

    public Boolean getAlterarPoliticas() {
        return alterarPoliticas;
    }

    public void setAlterarPoliticas(Boolean alterarPoliticas) {
        this.alterarPoliticas = alterarPoliticas;
    }
}
