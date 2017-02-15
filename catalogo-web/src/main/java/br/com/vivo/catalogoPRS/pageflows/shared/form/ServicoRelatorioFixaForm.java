package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;

public class ServicoRelatorioFixaForm extends ValidatorActionForm implements Serializable{

    private static final long serialVersionUID = -3852968452567375480L;
    
    private String cdServicoCatalogo;
    private String cdServicoOrigem;
    private String cdTipoServico;
    private String nmServicoCatalogo;
    private String nmServicoOrigem;
    private String nmSistemaOrigem;
    private String nmCategoria;
    private String disponibilidade;
    
    public ServicoRelatorioFixaTO buildTO() {
        ServicoRelatorioFixaTO to = new ServicoRelatorioFixaTO();
        to.setCdServicoCatalogo(this.cdServicoCatalogo);
        to.setCdServicoOrigem(this.cdServicoOrigem);
        to.setNmServicoCatalogo(this.nmServicoCatalogo);
        to.setNmServicoOrigem(this.nmServicoOrigem);
        to.setNmSistemaOrigem(this.nmSistemaOrigem);
        to.setCdTipoServico(this.cdTipoServico);
        to.setNmCategoria(this.nmCategoria);
        to.setDisponibilidade(this.disponibilidade);
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
    
    public String getCdTipoServico() {
        return cdTipoServico;
    }
    
    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
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

    public String getNmSistemaOrigem() {
        return nmSistemaOrigem;
    }

    public void setNmSistemaOrigem(String nmSistemaOrigem) {
        this.nmSistemaOrigem = nmSistemaOrigem;
    }

    public String getNmCategoria() {
        return nmCategoria;
    }

    public void setNmCategoria(String nmCategoria) {
        this.nmCategoria = nmCategoria;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }	
}
