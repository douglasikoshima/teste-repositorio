package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

public class MatrizOfertaArquivoItemTO implements Serializable {

    private static final long serialVersionUID = -3109584758513254494L;

    private Integer idMatrizOfertaArquivoItem;
    private String acao;
    private String canalDistribuicao;
    private String cdAdabas;
    private String cdErro;
    private String cdOfertaSap;
    private String cdProduto;
    private String codigoArea;
    private Date dtFinal;
    private Date dtInicial;
    private String sgEscritorioVendas;
    private String sgOrganizacaoVendas;
    private String valor;
    public String getAcao() {
        return acao;
    }
    public void setAcao(String acao) {
        this.acao = acao;
    }
    public String getCanalDistribuicao() {
        return canalDistribuicao;
    }
    public void setCanalDistribuicao(String canalDistribuicao) {
        this.canalDistribuicao = canalDistribuicao;
    }
    public String getCdAdabas() {
        return cdAdabas;
    }
    public void setCdAdabas(String cdAdabas) {
        this.cdAdabas = cdAdabas;
    }
    public String getCdErro() {
        return cdErro;
    }
    public void setCdErro(String cdErro) {
        this.cdErro = cdErro;
    }
    public String getCdOfertaSap() {
        return cdOfertaSap;
    }
    public void setCdOfertaSap(String cdOfertaSap) {
        this.cdOfertaSap = cdOfertaSap;
    }
    public String getCdProduto() {
        return cdProduto;
    }
    public void setCdProduto(String cdProduto) {
        this.cdProduto = cdProduto;
    }
    public String getCodigoArea() {
        return codigoArea;
    }
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    public Date getDtFinal() {
        return dtFinal;
    }
    public void setDtFinal(Date dtFinal) {
        this.dtFinal = dtFinal;
    }
    public Date getDtInicial() {
        return dtInicial;
    }
    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }
    public Integer getIdMatrizOfertaArquivoItem() {
        return idMatrizOfertaArquivoItem;
    }
    public void setIdMatrizOfertaArquivoItem(Integer idMatrizOfertaArquivoItem) {
        this.idMatrizOfertaArquivoItem = idMatrizOfertaArquivoItem;
    }
    public String getSgEscritorioVendas() {
        return sgEscritorioVendas;
    }
    public void setSgEscritorioVendas(String sgEscritorioVendas) {
        this.sgEscritorioVendas = sgEscritorioVendas;
    }
    public String getSgOrganizacaoVendas() {
        return sgOrganizacaoVendas;
    }
    public void setSgOrganizacaoVendas(String sgOrganizacaoVendas) {
        this.sgOrganizacaoVendas = sgOrganizacaoVendas;
    }
    public String getValor() {
        return valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }

    
}
