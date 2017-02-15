package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CampoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String nome;
    private String tipo;
    private String valorSelecionado;
    private List valores;
    private boolean continuar;
    private boolean lido;
    private boolean nulo;

    public void setLido(boolean lido) {
        this.lido = lido;
    }

    public boolean getLido() {
        return this.lido;
    }

    public CampoBean() {
        valores = new ArrayList();
        this.lido = false;
        this.nulo = false;
        //this.continuar = continuar;
    }

    public void setNulo(boolean nulo) {
        this.nulo = nulo;
    }

    public boolean getNulo() {
        return this.nulo;
    }

    public boolean isNulo() {
        boolean n = this.nulo;
        this.nulo = false;
        return n;
    }

    public boolean getContinuar() {
        return this.continuar;
    }

    public void setContinuar(boolean continuar) {
        this.continuar = continuar;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String valor) {
        codigo = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String valor) {
        nome = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String valor) {
        tipo = valor;
    }

    public String getValorSelecionado() {
        return valorSelecionado;
    }

    public void setValorSelecionado(String valor) {
        valorSelecionado = valor;
    }

    public List getValores() {
        return valores;
    }

    public void setValores(List valores) {
        this.valores = valores;
    }

    public void addValor(ValorBean valor) {
        valores.add(valor);
    }
}
