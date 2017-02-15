package com.indracompany.catalogo.to;

import java.io.Serializable;

public class ServicoArqItemTO implements Serializable {

    private static final long serialVersionUID = 6923142595348162310L;

    private Integer idArquivo;
    private Integer idItem;
    private String cdServico;
    private String nmComercial;
    private String disponibilidade;
    private String erros;
    
    public String getErrosFormatado() {
        return this.getErros().replaceAll("\\|", "<br/>");
    }
    
    public String getCdServico() {
        return cdServico;
    }
    public String getDisponibilidade() {
        return disponibilidade;
    }
    public String getErros() {
        return erros;
    }
    public Integer getIdArquivo() {
        return idArquivo;
    }
    public Integer getIdItem() {
        return idItem;
    }
    public String getNmComercial() {
        return nmComercial;
    }
    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }
    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    public void setErros(String erros) {
        this.erros = erros;
    }
    public void setIdArquivo(Integer idArquivo) {
        this.idArquivo = idArquivo;
    }
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
    public void setNmComercial(String nmComercial) {
        this.nmComercial = nmComercial;
    }    
}
