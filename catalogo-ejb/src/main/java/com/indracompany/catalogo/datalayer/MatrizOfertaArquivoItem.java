package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MATRIZOFERTAARQUIVOITEM", schema = "CATALOGOPRS_OW")
public class MatrizOfertaArquivoItem implements Serializable {

    private static final long serialVersionUID = -3192653871282865791L;

    @Id
    @Column(name = "IDMATRIZOFERTAARQUIVOITEM")
    private Integer idMatrizOfertaArquivoItem;
    
    @Column(name = "ACAO")
    private String acao;
    
    @Column(name = "CANALDISTRIBUICAO")
    private String canalDistribuicao;
    
    @Column(name = "CDADABAS")
    private String cdAdabas;
    
    @Column(name = "CDERRO")
    private String cdErro;
    
    @Column(name = "CDOFERTASAP")
    private String cdOfertaSap;
    
    
    @Column(name = "CDPRODUTO")
    private String cdProduto;
    
    
    @Column(name = "CODIGOAREA")
    private String codigoArea;
    
    @Column(name = "DTFINAL")
    private Date dtFinal;
    
    @Column(name = "DTINICIAL")
    private Date dtInicial;
    
    @Column(name = "SGESCRITORIOVENDA")
    private String sgEscritorioVendas;
    
    @Column(name = "SGORGANIZACAOVENDAS")
    private String sgOrganizacaoVendas;
    
    @Column(name = "VALOR")
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
