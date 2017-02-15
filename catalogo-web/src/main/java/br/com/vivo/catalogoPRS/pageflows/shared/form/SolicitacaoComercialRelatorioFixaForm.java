package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;

public class SolicitacaoComercialRelatorioFixaForm extends ValidatorActionForm implements Serializable {

    private static final long serialVersionUID = -6333779889486578104L;
    
    private String cdServicoCatalogo;
    private String cdServicoOrigem;
    private String cdSolicitacaoComercial;
    private String cdTipoServico;
    private String cdTipoSolicitacaoComercial;
    private String nmServicoCatalogo;
    private String nmServicoOrigem;
    private String nmSolicitacaoComercial;
    private Integer prazoMaximoAtendimento;
    private Integer prazoMaximoVigencia;
    
    public SolicitacaoComercialFixaTO buildTO() {
        SolicitacaoComercialFixaTO to = new SolicitacaoComercialFixaTO();
        to.setCdServicoCatalogo(this.cdServicoCatalogo);
        to.setCdServicoOrigem(this.cdServicoOrigem);
        to.setCdSolicitacaoComercial(this.cdSolicitacaoComercial);
        to.setCdTipoServico(this.cdTipoServico);
        to.setCdTipoSolicitacaoComercial(this.cdTipoSolicitacaoComercial);
        to.setNmServicoCatalogo(this.nmServicoCatalogo);
        to.setNmServicoOrigem(this.nmServicoOrigem);
        to.setNmSolicitacaoComercial(this.nmSolicitacaoComercial);
        to.setPrazoMaximoAtendimento(this.prazoMaximoAtendimento);
        to.setPrazoMaximoVigencia(this.prazoMaximoVigencia);
        return to;
    }

    public String getCdServicoCatalogo() {
        return cdServicoCatalogo;
    }

    public void setCdServicoCatalogo(String cdServicoCatalogo) {
        this.cdServicoCatalogo = cdServicoCatalogo;
    }

    public String getCdServicoOrigem() {
        return cdServicoOrigem;
    }

    public void setCdServicoOrigem(String cdServicoOrigem) {
        this.cdServicoOrigem = cdServicoOrigem;
    }

    public String getCdSolicitacaoComercial() {
        return cdSolicitacaoComercial;
    }

    public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
        this.cdSolicitacaoComercial = cdSolicitacaoComercial;
    }

    public String getCdTipoServico() {
        return cdTipoServico;
    }

    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }

    public String getCdTipoSolicitacaoComercial() {
        return cdTipoSolicitacaoComercial;
    }

    public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
        this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
    }

    public String getNmServicoCatalogo() {
        return nmServicoCatalogo;
    }

    public void setNmServicoCatalogo(String nmServicoCatalogo) {
        this.nmServicoCatalogo = nmServicoCatalogo;
    }

    public String getNmServicoOrigem() {
        return nmServicoOrigem;
    }

    public void setNmServicoOrigem(String nmServicoOrigem) {
        this.nmServicoOrigem = nmServicoOrigem;
    }

    public String getNmSolicitacaoComercial() {
        return nmSolicitacaoComercial;
    }

    public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
        this.nmSolicitacaoComercial = nmSolicitacaoComercial;
    }

    public Integer getPrazoMaximoAtendimento() {
        return prazoMaximoAtendimento;
    }

    public void setPrazoMaximoAtendimento(Integer prazoMaximoAtendimento) {
        this.prazoMaximoAtendimento = prazoMaximoAtendimento;
    }

    public Integer getPrazoMaximoVigencia() {
        return prazoMaximoVigencia;
    }

    public void setPrazoMaximoVigencia(Integer prazoMaximoVigencia) {
        this.prazoMaximoVigencia = prazoMaximoVigencia;
    }	
}
