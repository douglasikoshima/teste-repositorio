package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class OfertafixaTO implements Serializable {

    private static final long serialVersionUID = -1797289167920448149L;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public static final String VIGENTE = "vigente";
    public static final String NAO_INICIADO = "naoiniciado";
    public static final String FINALIZADO = "finalizado";
    public static final String VISUALIZAR = "visualizar";
    public static final String ALTERAR = "alterar";

    private Integer idOfertafixa;
    private String cdOfertafixa;
    private String statusOfertaFixa = "copiar";
    private String dsOfertafixa;
    private Date dtCriacao;
    private Date dtFinal;
    private Date dtInicial;
    private Date dtUltimaAlteracao;
    private String inConvergenteCobre = "N";
    private String inConvergenteFibra = "N";
    private String inFWT = "N";
    private String inPortab = "N";
    private String inSpeedySoloCobre = "N";
    private String inSpeedySoloFibra = "N";
    private String inBasePontual = "N";
    private String inOfertaClienteInadimplente = "N";
    private String inFATB = "N";
    private String nmUsuarioAlteracao;
    private String nmUsuarioCriacao;
    private String inStatusConfiguracao;
    private String opSolicitacaoComercialPreCadastro;
    private String opSolicitacaoComercialNormal;
    private String opSolicitacaoComercialPlano;
    private String psServicoPlano;
    private String psServivoLinha;
    private OfertafixaPoliticaTO ofertafixaPoliticaTO;
    private ServicoOfertafixaTO servicoOfertaFixaTOLinha;
    private SolicitacaoComercialFixaTO solicitacaoComercialFixaTOLinha;
    private SolicitacaoComercialFixaTO solicitacaoComercialFixaTOPreCad;
    private ServicoOfertafixaTO servicoOfertaFixaTOPlano;
    private SolicitacaoComercialFixaTO solicitacaoComercialFixaTOPlano;
    private List<OfertafixaComplementarTO> ofertafixaComplementarTOList;

    private String status = "";

    public OfertafixaTO() {
    }
    
    public OfertafixaTO(Integer idOfertafixa) {
        this.setIdOfertafixa(idOfertafixa);
    }    

    private String formatDate(Date date) {
        if (date != null) {
            return sdf.format(date);
        } else {
            return "";
        }
    }

    public boolean isExcluivel() {
        return this.getStatus().equals(NAO_INICIADO) || this.getStatus().equals("");
    }
    
    public boolean isAlteravel() {
        return !this.getStatus().equals(FINALIZADO);
    }
    
    public String getPeriodoVigencia() {
        return String.format("%s - %s", this.getDtInicioFormatado(), this.getDtFimFormatado());
    }

    public String getCdOfertafixaDisabled() {
        return  this.getCdOfertafixa() != null 
        	&& !this.getCdOfertafixa().equals("") 
        	&& (this.getStatusOfertaFixa().equals(VISUALIZAR) || this.getStatusOfertaFixa().equals(ALTERAR)) ? "disabled" : "";
    }
    
    public String getDsOfertafixaDisabled() {
        return this.isPadraoAlteravel() ? "" : "disabled";
    }

    public String getDtFinalDisabled() {
        return getStatus().equals(FINALIZADO) ? "disabled" : "";
    }
    
    public String getDtInicialDisabled() {
        return this.isPadraoAlteravel() ? "" : "disabled";
    }

    public String getDtInicioFormatado() {
        return this.formatDate(this.dtInicial);
    }

    public String getInConvergenteCobreDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public String getInConvergenteFibraDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }
    
    public String getInFWTDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }
    
    public String getInPortabDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public String getInSpeedySoloCobreDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public String getInSpeedySoloFibraDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }
    
    public String getInBasePontualDisabled() {
    	return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }
    public String getInOfertaClienteInadimplenteDisabled() {
    	return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }
    public String getInFATBDisabled() {
    	return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public String getServicoOfertaFixaTOLinhaDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public Integer getIdServicoLinha() {
        return this.getServicoOfertaFixaTOLinha() != null ? this.getServicoOfertaFixaTOLinha().getId() != null ? this.getServicoOfertaFixaTOLinha().getId() : null : null;
    }

    public String getServicoOfertaFixaTOPlanoDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public Integer getIdServicoPlano() {
        return this.getServicoOfertaFixaTOPlano() != null ? this.getServicoOfertaFixaTOPlano().getId() != null ? this.getServicoOfertaFixaTOPlano().getId() : null : null;
    }

    public String getSolicitacaoComercialFixaTOLinhaDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public Long getIdSolicitacaoComercialLinha() {
        return this.getSolicitacaoComercialFixaTOLinha() != null && this.getSolicitacaoComercialFixaTOLinha().getIdSolicitacaoComercial() != null
            ? this.getSolicitacaoComercialFixaTOLinha().getIdSolicitacaoComercial() : null;
    }

    public String getSolicitacaoComercialFixaTOPlanoDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public Long getIdSolicitacaoComercialPlano() {
        return this.getSolicitacaoComercialFixaTOPlano() != null && this.getSolicitacaoComercialFixaTOPlano().getIdSolicitacaoComercial() != null
            ? this.getSolicitacaoComercialFixaTOPlano().getIdSolicitacaoComercial() : null;
    }

    public String getSolicitacaoComercialFixaTOPreCadDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }

    public Long getIdSolicitacaoComercialPreCad() {
        return this.getSolicitacaoComercialFixaTOPreCad() != null && this.getSolicitacaoComercialFixaTOPreCad().getIdSolicitacaoComercial() != null
            ? this.getSolicitacaoComercialFixaTOPreCad().getIdSolicitacaoComercial() : null;
    }

    public String getDisponibilidadeDisabled() {
        return this.isPendenteConfiguracao() || this.isPadraoAlteravel() ? "" : "disabled";
    }
    
    public boolean isPendenteConfiguracao() {
        return "D".equals(this.getInStatusConfiguracao());
    }
    
    public boolean isOfertaComplementarAlteravel() {
    	return this.isPendenteConfiguracao() || this.isPadraoAlteravel(); 
    }
    
    private boolean isPadraoAlteravel() {
    	return this.getStatus().equals(NAO_INICIADO) || this.getStatus().equals("");
    }
    
    /* 
     * default getters and setters
     */

    public String getDtFimFormatado() {
        return this.formatDate(this.dtFinal);
    }
    
    public String getDsOfertafixa() {
        return dsOfertafixa;
    }
    
    public Date getDtCriacao() {
        return dtCriacao;
    }

    public Date getDtFinal() {
        return dtFinal;
    }
    
    public Date getDtInicial() {
        return dtInicial;
    }

    public String getInConvergenteFibra() {
        return inConvergenteFibra;
    }    

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public Integer getIdOfertafixa() {
        return idOfertafixa;
    }

    public String getInConvergenteCobre() {
        return inConvergenteCobre;
    }    

    public String getInFWT() {
        return inFWT;
    }

    public String getInPortab() {
        return inPortab;
    }

    public String getInSpeedySoloCobre() {
        return inSpeedySoloCobre;
    }
    
    public String getInSpeedySoloFibra() {
        return inSpeedySoloFibra;
    }

    public String getCdOfertafixa() {
        return cdOfertafixa;
    }
    
    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }    
   
	public String getStatusOfertaFixa() {
		return statusOfertaFixa;
	}

	public void setStatusOfertaFixa(String statusOfertaFixa) {
		this.statusOfertaFixa = statusOfertaFixa;
	}

	public String getNmUsuarioCriacao() {
        return nmUsuarioCriacao;
    }

    public OfertafixaPoliticaTO getOfertafixaPoliticaTO() {
        return ofertafixaPoliticaTO;
    }

    public ServicoOfertafixaTO getServicoOfertaFixaTOLinha() {
        return servicoOfertaFixaTOLinha;
    }
    
    public ServicoOfertafixaTO getServicoOfertaFixaTOPlano() {
        return servicoOfertaFixaTOPlano;
    }
    
    public SolicitacaoComercialFixaTO getSolicitacaoComercialFixaTOLinha() {
        return solicitacaoComercialFixaTOLinha;
    }
    
    public SolicitacaoComercialFixaTO getSolicitacaoComercialFixaTOPlano() {
        return solicitacaoComercialFixaTOPlano;
    }

    public SolicitacaoComercialFixaTO getSolicitacaoComercialFixaTOPreCad() {
        return solicitacaoComercialFixaTOPreCad;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setCdOfertafixa(String cdOfertafixa) {
        this.cdOfertafixa = cdOfertafixa;
    }
    
    public void setDsOfertafixa(String dsOfertafixa) {
        this.dsOfertafixa = dsOfertafixa;
    }
    
    public void setDtCriacao(Date dtCriacao) {
        this.dtCriacao = dtCriacao;
    }
    
    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }
    
    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }
    
    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }
    
    public void setIdOfertafixa(Integer idOfertafixa) {
        this.idOfertafixa = idOfertafixa;
    }
    
    public void setInConvergenteCobre(String inConvergenteCobre) {
        this.inConvergenteCobre = inConvergenteCobre;
    }
    
    public void setInConvergenteFibra(String inConvergenteFibra) {
        this.inConvergenteFibra = inConvergenteFibra;
    }
    
    public void setInFWT(String inFWT) {
        this.inFWT = inFWT;
    }
    
    public void setInPortab(String inPortab) {
        this.inPortab = inPortab;
    }
    
    public void setInSpeedySoloCobre(String inSpeedySoloCobre) {
        this.inSpeedySoloCobre = inSpeedySoloCobre;
    }
    
    public void setInSpeedySoloFibra(String inSpeedySoloFibra) {
        this.inSpeedySoloFibra = inSpeedySoloFibra;
    }
    
    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }
    
    public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
        this.nmUsuarioCriacao = nmUsuarioCriacao;
    }
    
    public void setOfertafixaPoliticaTO(OfertafixaPoliticaTO ofertafixaPoliticaTO) {
        this.ofertafixaPoliticaTO = ofertafixaPoliticaTO;
    }
    
    public void setServicoOfertaFixaTOLinha(ServicoOfertafixaTO servicoOfertaFixaTOLinha) {
        this.servicoOfertaFixaTOLinha = servicoOfertaFixaTOLinha;
    }
    
    public void setServicoOfertaFixaTOPlano(ServicoOfertafixaTO servicoOfertaFixaTOPlano) {
        this.servicoOfertaFixaTOPlano = servicoOfertaFixaTOPlano;
    }
    
    public void setSolicitacaoComercialFixaTOLinha(SolicitacaoComercialFixaTO solicitacaoComercialFixaTOLinha) {
        this.solicitacaoComercialFixaTOLinha = solicitacaoComercialFixaTOLinha;
    }
    
    public void setSolicitacaoComercialFixaTOPlano(SolicitacaoComercialFixaTO solicitacaoComercialFixaTOPlano) {
        this.solicitacaoComercialFixaTOPlano = solicitacaoComercialFixaTOPlano;
    }
    
    public void setSolicitacaoComercialFixaTOPreCad(SolicitacaoComercialFixaTO solicitacaoComercialFixaTOPreCad) {
        this.solicitacaoComercialFixaTOPreCad = solicitacaoComercialFixaTOPreCad;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public List<OfertafixaComplementarTO> getOfertafixaComplementarTOList() {
        return ofertafixaComplementarTOList;
    }
    
    public void setOfertafixaComplementarTOList(Collection<OfertafixaComplementarTO> ofertafixaComplementarTOCollection) {
        this.ofertafixaComplementarTOList = new ArrayList<OfertafixaComplementarTO>(ofertafixaComplementarTOCollection);
    }
    
    public void addAllOfertafixaComplementarTOList(List<OfertafixaComplementarTO> ofertafixaComplementarTOList) {
    	if (this.ofertafixaComplementarTOList == null) {
    		this.ofertafixaComplementarTOList = new ArrayList<OfertafixaComplementarTO>();
    	}
        this.ofertafixaComplementarTOList.addAll(ofertafixaComplementarTOList);
    }
    
    public String getInStatusConfiguracao() {
        return inStatusConfiguracao;
    }
    
    public void setInStatusConfiguracao(String inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao;
    }

	public String getInBasePontual() {
		return inBasePontual;
	}

	public void setInBasePontual(String inBasePontual) {
		this.inBasePontual = inBasePontual;
	}

	public String getInFATB() {
		return inFATB;
	}

	public void setInFATB(String inFATB) {
		this.inFATB = inFATB;
	}

	public String getInOfertaClienteInadimplente() {
		return inOfertaClienteInadimplente;
	}

	public void setInOfertaClienteInadimplente(String inOfertaClienteInadimplente) {
		this.inOfertaClienteInadimplente = inOfertaClienteInadimplente;
	}

	public String getOpSolicitacaoComercialNormal() {
		return opSolicitacaoComercialNormal;
	}

	public void setOpSolicitacaoComercialNormal(String opSolicitacaoComercialNormal) {
		this.opSolicitacaoComercialNormal = opSolicitacaoComercialNormal;
	}

	public String getOpSolicitacaoComercialPlano() {
		return opSolicitacaoComercialPlano;
	}

	public void setOpSolicitacaoComercialPlano(String opSolicitacaoComercialPlano) {
		this.opSolicitacaoComercialPlano = opSolicitacaoComercialPlano;
	}

	public String getOpSolicitacaoComercialPreCadastro() {
		return opSolicitacaoComercialPreCadastro;
	}

	public void setOpSolicitacaoComercialPreCadastro(
			String opSolicitacaoComercialPreCadastro) {
		this.opSolicitacaoComercialPreCadastro = opSolicitacaoComercialPreCadastro;
	}

	public String getPsServicoPlano() {
		return psServicoPlano;
	}

	public void setPsServicoPlano(String psServicoPlano) {
		this.psServicoPlano = psServicoPlano;
	}

	public String getPsServivoLinha() {
		return psServivoLinha;
	}

	public void setPsServivoLinha(String psServivoLinha) {
		this.psServivoLinha = psServivoLinha;
	}
	
}
