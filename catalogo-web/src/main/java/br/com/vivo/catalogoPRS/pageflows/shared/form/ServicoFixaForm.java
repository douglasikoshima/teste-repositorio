package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.AtributoTO;
import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoTO;

public class ServicoFixaForm extends ValidatorActionForm  implements Serializable{       
    private static final long serialVersionUID = 8867762386315402484L;

    private Integer idSistema;
    private String cdServico;
    private String nmTipoServico;
    private String cdPS;
    private String nomeDoServicoOrigem;
    private String nmComercial;
    private String inDisponivel;
    private String idServico;
    private String alterar;
    private String cdServicoSelecionado;
    
    //Propriedades do form DetalheServicoFixaForm
	private Boolean edit;
	private String alterarServicoSearch; 
	private String idServicoSearch;
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

	//Propriedades do form RelacionamentoServicoFixaForm
    private Integer idSistemaSearch;
    private String idRelacionamento;
    private String idTipoRelacionamento;
    private Integer idTipoServico;
    private Integer[] idServicoSelecionadoList;
    
    public Integer getIdSistema() {
        if (this.idSistema == null) {
            this.idSistema = 0;
        }
        return this.idSistema;
    }
    
    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getCdServico() {
        return this.cdServico;
    }

    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }

    public String getCdPS() {
        return this.cdPS;
    }

    public void setCdPS(String cdPS) {
        this.cdPS = cdPS;
    }

    public String getNomeDoServicoOrigem() {
        return this.nomeDoServicoOrigem;
    }

    public void setNomeDoServicoOrigem(String nomeDoServicoOrigem) {
        this.nomeDoServicoOrigem = nomeDoServicoOrigem;
    }

    public String getNmComercial() {
        return this.nmComercial;
    }

    public void setNmComercial(String nmComercial) {
        this.nmComercial = nmComercial;
    }

    public String getInDisponivel() {
        if (this.inDisponivel == null) {
            this.inDisponivel = "sim";
        }
        return this.inDisponivel;
    }

    public void setInDisponivel(String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }
    
    public String getIdServico() {
        return this.idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }
    
    public String getAlterar() {
        return this.alterar;
    }

    public void setAlterar(String alterar) {
        this.alterar = alterar;
    }
    
    public String getCdServicoSelecionado() {
        return this.cdServicoSelecionado;
    }

    public void setCdServicoSelecionado(String cdServicoSelecionado) {
        this.cdServicoSelecionado = cdServicoSelecionado;
    }        

    public String getNmTipoServico() {
		return nmTipoServico;
	}

	public void setNmTipoServico(String nmTipoServico) {
		this.nmTipoServico = nmTipoServico;
	}

	public Boolean getEdit() {
		return edit;
	}

	public void setEdit(Boolean edit) {
		this.edit = edit;
	}

    public String getAlterarPoliticas() {
        return alterarPoliticas;
    }

    public void setAlterarPoliticas(String alterarPoliticas) {
        this.alterarPoliticas = alterarPoliticas;
    }

	public Boolean getInDisponivelDetalheServico() {
		return inDisponivelDetalheServico;
	}

	public void setInDisponivelDetalheServico(Boolean inDisponivelDetalheServico) {
		this.inDisponivelDetalheServico = inDisponivelDetalheServico;
	}

	public String getIdServicoSearch() {
		return idServicoSearch;
	}

	public void setIdServicoSearch(String idServicoSearch) {
		this.idServicoSearch = idServicoSearch;
	}

	public String getNmSistema() {
		return nmSistema;
	}

	public void setNmSistema(String nmSistema) {
		this.nmSistema = nmSistema;
	}

	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public String getNmComercialOrigem() {
		return nmComercialOrigem;
	}

	public void setNmComercialOrigem(String nmComercialOrigem) {
		this.nmComercialOrigem = nmComercialOrigem;
	}

	public String getNmComercialCatalogo() {
		return nmComercialCatalogo;
	}

	public void setNmComercialCatalogo(String nmComercialCatalogo) {
		this.nmComercialCatalogo = nmComercialCatalogo;
	}

	public String getDescricaoOrigem() {
		return descricaoOrigem;
	}

	public void setDescricaoOrigem(String descricaoOrigem) {
		this.descricaoOrigem = descricaoOrigem;
	}

	public String getDescricaoCatalogo() {
		return descricaoCatalogo;
	}

	public void setDescricaoCatalogo(String descricaoCatalogo) {
		this.descricaoCatalogo = descricaoCatalogo;
	}

	public String getCdTipoServico() {
		return cdTipoServico;
	}

	public void setCdTipoServico(String cdTipoServico) {
		this.cdTipoServico = cdTipoServico;
	}

	public String getNmTipoServicoDetalheServico() {
		return nmTipoServicoDetalheServico;
	}

	public void setNmTipoServicoDetalheServico(String nmTipoServicoDetalheServico) {
		this.nmTipoServicoDetalheServico = nmTipoServicoDetalheServico;
	}

	public Long getQtMinutoLivreFMLocal() {
		return qtMinutoLivreFMLocal;
	}

	public void setQtMinutoLivreFMLocal(Long qtMinutoLivreFMLocal) {
		this.qtMinutoLivreFMLocal = qtMinutoLivreFMLocal;
	}

	public Long getQtMinutoLivreFFLocal() {
		return qtMinutoLivreFFLocal;
	}

	public void setQtMinutoLivreFFLocal(Long qtMinutoLivreFFLocal) {
		this.qtMinutoLivreFFLocal = qtMinutoLivreFFLocal;
	}

	public BigDecimal getVlMinutoAdicionalFFLocal() {
		return vlMinutoAdicionalFFLocal;
	}

	public void setVlMinutoAdicionalFFLocal(BigDecimal vlMinutoAdicionalFFLocal) {
		this.vlMinutoAdicionalFFLocal = vlMinutoAdicionalFFLocal;
	}

	public String getCdPacote() {
		return cdPacote;
	}

	public void setCdPacote(String cdPacote) {
		this.cdPacote = cdPacote;
	}

	public String getCdTipoPrecoPacote() {
		return cdTipoPrecoPacote;
	}

	public void setCdTipoPrecoPacote(String cdTipoPrecoPacote) {
		this.cdTipoPrecoPacote = cdTipoPrecoPacote;
	}

	public Boolean getInVendaIsolada() {
		return inVendaIsolada;
	}

	public void setInVendaIsolada(Boolean inVendaIsolada) {
		this.inVendaIsolada = inVendaIsolada;
	}

	public String getNmTecnologia() {
		return nmTecnologia;
	}

	public void setNmTecnologia(String nmTecnologia) {
		this.nmTecnologia = nmTecnologia;
	}

	public String getCdClasseServicoPrincipal() {
		return cdClasseServicoPrincipal;
	}

	public void setCdClasseServicoPrincipal(String cdClasseServicoPrincipal) {
		this.cdClasseServicoPrincipal = cdClasseServicoPrincipal;
	}

	public String getCdClasseServicoAdicional() {
		return cdClasseServicoAdicional;
	}

	public void setCdClasseServicoAdicional(String cdClasseServicoAdicional) {
		this.cdClasseServicoAdicional = cdClasseServicoAdicional;
	}

	public String getCdTipoEquipamento() {
		return cdTipoEquipamento;
	}

	public void setCdTipoEquipamento(String cdTipoEquipamento) {
		this.cdTipoEquipamento = cdTipoEquipamento;
	}

	public Boolean getInPreFactibilidade() {
		return inPreFactibilidade;
	}

	public void setInPreFactibilidade(Boolean inPreFactibilidade) {
		this.inPreFactibilidade = inPreFactibilidade;
	}

	public Boolean getInPosFactibilidade() {
		return inPosFactibilidade;
	}

	public void setInPosFactibilidade(Boolean inPosFactibilidade) {
		this.inPosFactibilidade = inPosFactibilidade;
	}

	public String getCdSiscom() {
		return cdSiscom;
	}

	public void setCdSiscom(String cdSiscom) {
		this.cdSiscom = cdSiscom;
	}

	public BigDecimal getQtPercentualAuditoria() {
		return qtPercentualAuditoria;
	}

	public void setQtPercentualAuditoria(BigDecimal qtPercentualAuditoria) {
		this.qtPercentualAuditoria = qtPercentualAuditoria;
	}

	public Long getNuOrdemAtendimento() {
		return nuOrdemAtendimento;
	}

	public void setNuOrdemAtendimento(Long nuOrdemAtendimento) {
		this.nuOrdemAtendimento = nuOrdemAtendimento;
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

	public Boolean getInEspServicoPacote() {
		return inEspServicoPacote;
	}

	public void setInEspServicoPacote(Boolean inEspServicoPacote) {
		this.inEspServicoPacote = inEspServicoPacote;
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

	public Boolean getInAreaConcorrenciaDesconto() {
		return inAreaConcorrenciaDesconto;
	}

	public void setInAreaConcorrenciaDesconto(Boolean inAreaConcorrenciaDesconto) {
		this.inAreaConcorrenciaDesconto = inAreaConcorrenciaDesconto;
	}

	public Boolean getInPlanoMinutoDesconto() {
		return inPlanoMinutoDesconto;
	}

	public void setInPlanoMinutoDesconto(Boolean inPlanoMinutoDesconto) {
		this.inPlanoMinutoDesconto = inPlanoMinutoDesconto;
	}

	public Boolean getInPossuiVarDispDesconto() {
		return inPossuiVarDispDesconto;
	}

	public void setInPossuiVarDispDesconto(Boolean inPossuiVarDispDesconto) {
		this.inPossuiVarDispDesconto = inPossuiVarDispDesconto;
	}

	public Boolean getInAreaConcorrenciaFinanciamento() {
		return inAreaConcorrenciaFinanciamento;
	}

	public void setInAreaConcorrenciaFinanciamento(Boolean inAreaConcorrenciaFinanciamento) {
		this.inAreaConcorrenciaFinanciamento = inAreaConcorrenciaFinanciamento;
	}

	public Boolean getInPlanoMinutoFinanciamento() {
		return inPlanoMinutoFinanciamento;
	}

	public void setInPlanoMinutoFinanciamento(Boolean inPlanoMinutoFinanciamento) {
		this.inPlanoMinutoFinanciamento = inPlanoMinutoFinanciamento;
	}

	public Boolean getInPossuiVarDispFinanciamento() {
		return inPossuiVarDispFinanciamento;
	}

	public void setInPossuiVarDispFinanciamento(Boolean inPossuiVarDispFinanciamento) {
		this.inPossuiVarDispFinanciamento = inPossuiVarDispFinanciamento;
	}

	public String getIdFamiliaForm() {
		return idFamiliaForm;
	}

	public void setIdFamiliaForm(String idFamiliaForm) {
		this.idFamiliaForm = idFamiliaForm;
	}

	public String getIdCategoriaForm() {
		return idCategoriaForm;
	}

	public void setIdCategoriaForm(String idCategoriaForm) {
		this.idCategoriaForm = idCategoriaForm;
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

	public String getCdServicoOrigem() {
		return cdServicoOrigem;
	}

	public void setCdServicoOrigem(String cdServicoOrigem) {
		this.cdServicoOrigem = cdServicoOrigem;
	}

	public Integer getIdSistemaSearch() {
		return idSistemaSearch;
	}

	public void setIdSistemaSearch(Integer idSistemaSearch) {
		this.idSistemaSearch = idSistemaSearch;
	}

	public String getIdRelacionamento() {
		return idRelacionamento;
	}

	public void setIdRelacionamento(String idRelacionamento) {
		this.idRelacionamento = idRelacionamento;
	}

	public String getIdTipoRelacionamento() {
		return idTipoRelacionamento;
	}

	public void setIdTipoRelacionamento(String idTipoRelacionamento) {
		this.idTipoRelacionamento = idTipoRelacionamento;
	}

	public Integer getIdTipoServico() {
		return idTipoServico;
	}

	public void setIdTipoServico(Integer idTipoServico) {
		this.idTipoServico = idTipoServico;
	}

	public Integer[] getIdServicoSelecionadoList() {
		return idServicoSelecionadoList;
	}

	public void setIdServicoSelecionadoList(Integer[] idServicoSelecionadoList) {
		this.idServicoSelecionadoList = idServicoSelecionadoList;
	}

	public ServicoFixaTO buildTO() {
        ServicoFixaTO to = new ServicoFixaTO();
        to.setIdSistema(this.idSistema);
        to.setCdServico(this.getCdServico());
        to.setCdPS(this.cdPS);
        to.setNmComercial(this.nmComercial);
        to.setNomeDoServicoOrigem(this.nomeDoServicoOrigem);
        to.setInDisponivel(StringUtils.isBlank(this.inDisponivel) ? null : this.inDisponivel.equalsIgnoreCase("sim") );
        return to;
    }
	
    public RelacionamentoServicoFixaTO buildRelacionamentoServicoFixaTO() {
        RelacionamentoServicoFixaTO to = new RelacionamentoServicoFixaTO();
        if (!StringUtils.isBlank(this.idServicoSearch)) {
            to.setIdServicoSearch(Integer.valueOf(this.idServicoSearch));
        }
        return to;
    }

    public List<ServicoServicoTO> buildTOListReccord() {
        List<ServicoServicoTO> toList = new ArrayList<ServicoServicoTO>();
        for (Integer idServico2 : idServicoSelecionadoList) {
            ServicoServicoTO to = this.getBasicServicoServicoTO();
            to.setIdServico2(idServico2);
            toList.add(to);
        }
        return toList;
    }

    private ServicoServicoTO getBasicServicoServicoTO() {
        ServicoServicoTO to = new ServicoServicoTO();
        to.setDtCriacao(new Date());
        to.setDtFinal(null);
        to.setDtInicial(null);
        to.setDtUltimaAlteracao(new Date());
        to.setIdServico1(Integer.valueOf(this.idServicoSearch));
        to.setInCriacaoCatalogo("S");
        to.setInDisponivel("S");
        to.setNmUsuarioAlteracao("");
        to.setNmUsuarioCriacao("");
        to.setTipoRelacionamento(Integer.valueOf(this.idTipoRelacionamento));
        return to;
    }

    public ServicoFixaTO buildTOServicoFixa(){
        ServicoFixaTO to = new ServicoFixaTO();
        to.setCdServico(this.cdServico);
        to.setCdPS(this.cdPS);
        to.setNomeDoServicoOrigem(this.nomeDoServicoOrigem);
        to.setNmComercial(this.nmComercial);
        to.setIdTipoServico(this.idTipoServico);
        to.setIdSistema(this.idSistemaSearch);
        return to;
    }
	
    public void clearSearchServicoData() {
        this.idTipoRelacionamento = null;
        this.cdPS = null;
        this.cdServico = null;
        this.nomeDoServicoOrigem = null;
        this.nmComercial = null;
        this.idTipoServico = null;
    }	
}
