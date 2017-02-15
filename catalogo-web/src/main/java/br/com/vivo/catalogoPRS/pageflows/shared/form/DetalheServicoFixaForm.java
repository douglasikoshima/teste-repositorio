package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.math.BigDecimal;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.AtributoTO;

public class DetalheServicoFixaForm extends ValidatorActionForm  implements java.io.Serializable {

	private static final long serialVersionUID = -6626273960639564656L;
	
	private Boolean edit;
	private String alterarServicoSearch; 
	private Integer idServicoSearch;
	private String nmSistema;
	private String cdCodigo;
	private String nmComercialOrigem;
	private String nmComercialCatalogo;
	private String descricaoOrigem;
	private String descricaoCatalogo;
	private String cdTipoServico;
	private String nmTipoServicoDetalheServico;
	private Long qtMinutoLivreFMLocal;
	private Long qtMinutoLivreFFLocal;
	private BigDecimal vlMinutoAdicionalFFLocal;
	private String cdPacote;
	private String cdTipoPrecoPacote;
	private Boolean inVendaIsolada;
	private String nmTecnologia;
	private String cdClasseServicoPrincipal;
	private String cdClasseServicoAdicional;
	private String cdTipoEquipamento;
	private Boolean inPreFactibilidade;
	private Boolean inPosFactibilidade;
	private String cdSiscom;
	private BigDecimal qtPercentualAuditoria;
	private Long nuOrdemAtendimento;
	private String sgTipoAplicacaoDesconto;
	private BigDecimal vlDescontoAbsoluto;
	private Boolean inEspServicoPacote;
	private Boolean inClasseServicoPrincipal;
	private Boolean inEmpresaInstaladora;
	private Boolean inAreaConcorrenciaDesconto;
	private Boolean inPlanoMinutoDesconto;
	private Boolean inPossuiVarDispDesconto;
	private Boolean inAreaConcorrenciaFinanciamento;
	private Boolean inPlanoMinutoFinanciamento;
	private Boolean inPossuiVarDispFinanciamento;
    private String idFamiliaForm;
    private String idCategoriaForm;
    private String cdServicoOrigem;
    private String alterarPoliticas = "";
	private List<AtributoTO> atributoTOList;
	private List<AtributoTO> atributoPoliticaPrecificacaoTOList;
	private Boolean inDisponivelDetalheServico;
	private String idFamilia;
	private String idCategoria;
	
	public List<AtributoTO> getAtributoPoliticaPrecificacaoTOList() {
		return atributoPoliticaPrecificacaoTOList;
	}

	public void setAtributoPoliticaPrecificacaoTOList(
			List<AtributoTO> atributoPoliticaPrecificacaoTOList) {
		this.atributoPoliticaPrecificacaoTOList = atributoPoliticaPrecificacaoTOList;
	}

	public String getAlterarServicoSearch() {
		return alterarServicoSearch;
	}

	public void setAlterarServicoSearch(String alterarServicoSearch) {
		this.alterarServicoSearch = alterarServicoSearch;
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

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

	public Integer getIdServicoSearch() {
		return idServicoSearch;
	}

	public void setIdServicoSearch(Integer idServicoSearch) {
		this.idServicoSearch = idServicoSearch;
	}

	public Boolean getInAreaConcorrenciaDesconto() {
		return inAreaConcorrenciaDesconto;
	}

	public void setInAreaConcorrenciaDesconto(Boolean inAreaConcorrenciaDesconto) {
		this.inAreaConcorrenciaDesconto = inAreaConcorrenciaDesconto;
	}

	public Boolean getInAreaConcorrenciaFinanciamento() {
		return inAreaConcorrenciaFinanciamento;
	}

	public void setInAreaConcorrenciaFinanciamento(
			Boolean inAreaConcorrenciaFinanciamento) {
		this.inAreaConcorrenciaFinanciamento = inAreaConcorrenciaFinanciamento;
	}

	public Boolean getInClasseServicoPrincipal() {
		return inClasseServicoPrincipal;
	}

	public void setInClasseServicoPrincipal(Boolean inClasseServicoPrincipal) {
		this.inClasseServicoPrincipal = inClasseServicoPrincipal;
	}

	public Boolean getInEmpresaInstaladora() {
		return inEmpresaInstaladora;
	}

	public void setInEmpresaInstaladora(Boolean inEmpresaInstaladora) {
		this.inEmpresaInstaladora = inEmpresaInstaladora;
	}

	public Boolean getInEspServicoPacote() {
		return inEspServicoPacote;
	}

	public void setInEspServicoPacote(Boolean inEspServicoPacote) {
		this.inEspServicoPacote = inEspServicoPacote;
	}

	public Boolean getInPlanoMinutoDesconto() {
		return inPlanoMinutoDesconto;
	}

	public void setInPlanoMinutoDesconto(Boolean inPlanoMinutoDesconto) {
		this.inPlanoMinutoDesconto = inPlanoMinutoDesconto;
	}

	public Boolean getInPlanoMinutoFinanciamento() {
		return inPlanoMinutoFinanciamento;
	}

	public void setInPlanoMinutoFinanciamento(Boolean inPlanoMinutoFinanciamento) {
		this.inPlanoMinutoFinanciamento = inPlanoMinutoFinanciamento;
	}

	public Boolean getInPosFactibilidade() {
		return inPosFactibilidade;
	}

	public void setInPosFactibilidade(Boolean inPosFactibilidade) {
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

	public Boolean getInPreFactibilidade() {
		return inPreFactibilidade;
	}

	public void setInPreFactibilidade(Boolean inPreFactibilidade) {
		this.inPreFactibilidade = inPreFactibilidade;
	}

	public Boolean getInVendaIsolada() {
		return inVendaIsolada;
	}

	public void setInVendaIsolada(Boolean inVendaIsolada) {
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

    public String getIdCategoriaForm() {
        return idCategoriaForm;
    }

    public void setIdCategoriaForm(String idCategoriaForm) {
        this.idCategoriaForm = idCategoriaForm;
    }

    public String getIdFamiliaForm() {
        return idFamiliaForm;
    }

    public void setIdFamiliaForm(String idFamiliaForm) {
        this.idFamiliaForm = idFamiliaForm;
    }

    public String getCdServicoOrigem() {
        return cdServicoOrigem;
    }

    public void setCdServicoOrigem(String cdServicoOrigem) {
        this.cdServicoOrigem = cdServicoOrigem;
    }

    public String getAlterarPoliticas() {
        return alterarPoliticas;
    }

    public void setAlterarPoliticas(String alterarPoliticas) {
        this.alterarPoliticas = alterarPoliticas;
    }

	public String getNmTipoServicoDetalheServico() {
		return nmTipoServicoDetalheServico;
	}

	public void setNmTipoServicoDetalheServico(String nmTipoServicoDetalheServico) {
		this.nmTipoServicoDetalheServico = nmTipoServicoDetalheServico;
	}

	public Boolean getInDisponivelDetalheServico() {
		return inDisponivelDetalheServico;
	}

	public void setInDisponivelDetalheServico(Boolean inDisponivelDetalheServico) {
		this.inDisponivelDetalheServico = inDisponivelDetalheServico;
	}

	public String getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
}
