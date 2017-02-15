package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.ServicosAdicionaisTO;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.param.servicosadicionais.ServicosAdicionaisAction;

public class ServicosAdicionaisForm extends ValidatorActionForm implements Serializable{
	
    private static final long serialVersionUID = -9011412349069373787L;

    // Campos
    private static Logger log = Logger.getLogger(ServicosAdicionaisAction.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Integer id; // Var genérico para fazer pesquisas por ID
    private Integer idServicosAdicionais;
    private Integer idServico;
    private Integer idTipoServico;
    private String nomeServico;
    private Long idSolicitacaoComercial;
    private String cdSolicitacaoComercial;
    private String nomeSolicitacao;
    private String dtInicio;
    private String dtFim;
    private Long tempoVigencia;
    private String periodoVigencia;
    private String comboTipoServico;
    private String comboNomeServico;
    private String codSolicitacao;
    private String comboNomeSolicitacao;
    private String idTempoVigencia;
    private String dataVigenteInicio;
    private String dataVigenteFim;

	public ServicosAdicionaisTO buildTO() throws CatalogoPRSException {
        ServicosAdicionaisTO servicosAdicionaisTO = new ServicosAdicionaisTO();
        servicosAdicionaisTO.setId(this.id);
        servicosAdicionaisTO.setIdServico(this.idServico);
        servicosAdicionaisTO.setIdTipoServico(this.idTipoServico);
        servicosAdicionaisTO.setNomeServico(this.nomeServico);
        servicosAdicionaisTO.setIdSolicitacaoComercial(this.idSolicitacaoComercial);
        servicosAdicionaisTO.setCdSolicitacaoComercial(this.cdSolicitacaoComercial);
        servicosAdicionaisTO.setNomeSolicitacaoComercial(this.nomeSolicitacao);
        try {
        	if(this.dtInicio !=null && !this.dtInicio.equals("")){
                servicosAdicionaisTO.setDtInicio(sdf.parse(this.dtInicio + " 00:00:00"));
                log.debug("Conversão do input da data inicial de vigência anexando a hora, minuto e segundo: " + servicosAdicionaisTO.getDtInicio());
        	}
        } catch (ParseException e) {
            log.error("Erro ao converter data final", e);
        }
        try {
        	if(this.dtFim != null && !this.dtFim.equals("")){
                servicosAdicionaisTO.setDtFim(sdf.parse(this.dtFim + " 23:59:59"));
                log.debug("Conversão do input da data final de vigência anexando a hora, minuto e segundo: " + servicosAdicionaisTO.getDtFim());
        		
        	}
        } catch (ParseException e) {
            log.error("Erro ao converter data final", e);
        }
        servicosAdicionaisTO.setTempoVigencia(this.tempoVigencia);
        return servicosAdicionaisTO;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Long getIdSolicitacaoComercial() {
        return idSolicitacaoComercial;
    }

    public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
        this.idSolicitacaoComercial = idSolicitacaoComercial;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getNomeSolicitacao() {
        return nomeSolicitacao;
    }

    public void setNomeSolicitacao(String nomeSolicitacao) {
        this.nomeSolicitacao = nomeSolicitacao;
    }

    public Long getTempoVigencia() {
        return tempoVigencia;
    }

    public void setTempoVigencia(Long tempoVigencia) {
        this.tempoVigencia = tempoVigencia;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getCdSolicitacaoComercial() {
        return cdSolicitacaoComercial;
    }

    public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
        this.cdSolicitacaoComercial = cdSolicitacaoComercial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Integer getIdServicosAdicionais() {
        return idServicosAdicionais;
    }

    public void setIdServicosAdicionais(Integer idServicosAdicionais) {
        this.idServicosAdicionais = idServicosAdicionais;
    }

    public String getPeriodoVigencia() {
        return periodoVigencia;
    }

    public void setPeriodoVigencia(String periodoVigencia) {
        this.periodoVigencia = periodoVigencia;
    }

    public String getComboTipoServico() {
		return comboTipoServico;
	}

	public void setComboTipoServico(String comboTipoServico) {
		this.comboTipoServico = comboTipoServico;
	}

	public String getComboNomeServico() {
		return comboNomeServico;
	}

	public void setComboNomeServico(String comboNomeServico) {
		this.comboNomeServico = comboNomeServico;
	}

	public String getCodSolicitacao() {
		return codSolicitacao;
	}

	public void setCodSolicitacao(String codSolicitacao) {
		this.codSolicitacao = codSolicitacao;
	}

	public String getComboNomeSolicitacao() {
		return comboNomeSolicitacao;
	}

	public void setComboNomeSolicitacao(String comboNomeSolicitacao) {
		this.comboNomeSolicitacao = comboNomeSolicitacao;
	}

	public String getIdTempoVigencia() {
		return idTempoVigencia;
	}

	public void setIdTempoVigencia(String idTempoVigencia) {
		this.idTempoVigencia = idTempoVigencia;
	}

	public String getDataVigenteInicio() {
		return dataVigenteInicio;
	}

	public void setDataVigenteInicio(String dataVigenteInicio) {
		this.dataVigenteInicio = dataVigenteInicio;
	}

	public String getDataVigenteFim() {
		return dataVigenteFim;
	}

	public void setDataVigenteFim(String dataVigenteFim) {
		this.dataVigenteFim = dataVigenteFim;
	}
    
}
