package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;

public class RelacionamentoRelatorioFixaForm extends ValidatorActionForm  implements java.io.Serializable{
	
    private static final long serialVersionUID = 1908286797593657226L;
    
    private String cdServicoCatalogoMestre;
    private String cdServicoCatalogoSubordinado;
    private String cdServicoOrigemMestre;
    private String cdServicoOrigemSubordinado;
    private String cdTipoServicoMestre;
    private String cdTipoServicoSubordinado;
    private String dscTipoRelacionamento;
    private String nmServicoCatalogoMestre;
    private String nmServicoCatalogoSubordinado;
    private String nmServicoOrigemMestre;
    private String nmServicoOrigemSubordinado;
    private String sgTipoRelacionamento;
    
    public RelacionamentoRelatorioFixaTO buildTO() {
        RelacionamentoRelatorioFixaTO to = new RelacionamentoRelatorioFixaTO();
        to.setCdTipoServicoMestre(this.cdTipoServicoMestre);
        to.setCdServicoOrigemMestre(this.cdServicoOrigemMestre);
        to.setCdServicoCatalogoMestre(this.cdServicoCatalogoMestre);
        to.setNmServicoOrigemMestre(this.nmServicoOrigemMestre);
        to.setNmServicoCatalogoMestre(this.nmServicoCatalogoMestre);

        to.setSgTipoRelacionamento(this.sgTipoRelacionamento);
        to.setDscTipoRelacionamento(this.dscTipoRelacionamento);
        
        to.setCdTipoServicoSubordinado(this.cdTipoServicoSubordinado);
        to.setCdServicoOrigemSubordinado(this.cdServicoOrigemSubordinado);
        to.setCdServicoCatalogoSubordinado(this.cdServicoCatalogoSubordinado);
        to.setNmServicoOrigemSubordinado(this.nmServicoOrigemSubordinado);
        to.setNmServicoCatalogoSubordinado(this.nmServicoCatalogoSubordinado);            
        return to;
    }
    
    public String getCdServicoCatalogoMestre() {
        return cdServicoCatalogoMestre;
    }
    
    public void setCdServicoCatalogoMestre(String cdServicoCatalogoMestre) {
        this.cdServicoCatalogoMestre = cdServicoCatalogoMestre;
    }
    
    public String getCdServicoCatalogoSubordinado() {
        return cdServicoCatalogoSubordinado;
    }
    
    public void setCdServicoCatalogoSubordinado(String cdServicoCatalogoSubordinado) {
        this.cdServicoCatalogoSubordinado = cdServicoCatalogoSubordinado;
    }
    
    public String getCdServicoOrigemMestre() {
        return cdServicoOrigemMestre;
    }
    
    public void setCdServicoOrigemMestre(String cdServicoOrigemMestre) {
        this.cdServicoOrigemMestre = cdServicoOrigemMestre;
    }
    
    public String getCdServicoOrigemSubordinado() {
        return cdServicoOrigemSubordinado;
    }
    
    public void setCdServicoOrigemSubordinado(String cdServicoOrigemSubordinado) {
        this.cdServicoOrigemSubordinado = cdServicoOrigemSubordinado;
    }
    
    public String getCdTipoServicoMestre() {
        return cdTipoServicoMestre;
    }
    
    public void setCdTipoServicoMestre(String cdTipoServicoMestre) {
        this.cdTipoServicoMestre = cdTipoServicoMestre;
    }
    
    public String getCdTipoServicoSubordinado() {
        return cdTipoServicoSubordinado;
    }
    
    public void setCdTipoServicoSubordinado(String cdTipoServicoSubordinado) {
        this.cdTipoServicoSubordinado = cdTipoServicoSubordinado;
    }
    
    public String getDscTipoRelacionamento() {
        return dscTipoRelacionamento;
    }
    
    public void setDscTipoRelacionamento(String dscTipoRelacionamento) {
        this.dscTipoRelacionamento = dscTipoRelacionamento;
    }
    
    public String getNmServicoOrigemMestre() {
        return nmServicoOrigemMestre;
    }
    
    public void setNmServicoOrigemMestre(String nmServicoOrigemMestre) {
        this.nmServicoOrigemMestre = nmServicoOrigemMestre;
    }
    
    public String getNmServicoOrigemSubordinado() {
        return nmServicoOrigemSubordinado;
    }
    
    public void setNmServicoOrigemSubordinado(String nmServicoOrigemSubordinado) {
        this.nmServicoOrigemSubordinado = nmServicoOrigemSubordinado;
    }
    
    public String getSgTipoRelacionamento() {
        return sgTipoRelacionamento;
    }
    
    public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
        this.sgTipoRelacionamento = sgTipoRelacionamento;
    }

    public String getNmServicoCatalogoMestre() {
        return nmServicoCatalogoMestre;
    }

    public void setNmServicoCatalogoMestre(String nmServicoCatalogoMestre) {
        this.nmServicoCatalogoMestre = nmServicoCatalogoMestre;
    }

    public String getNmServicoCatalogoSubordinado() {
        return nmServicoCatalogoSubordinado;
    }

    public void setNmServicoCatalogoSubordinado(String nmServicoCatalogoSubordinado) {
        this.nmServicoCatalogoSubordinado = nmServicoCatalogoSubordinado;
    }	

}
