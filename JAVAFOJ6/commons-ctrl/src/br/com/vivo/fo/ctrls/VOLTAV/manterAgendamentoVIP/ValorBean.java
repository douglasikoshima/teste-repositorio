package br.com.vivo.fo.ctrls.VOLTAV.manterAgendamentoVIP;

import java.io.Serializable;

public class ValorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigo;
    private String descricao;

    public ValorBean() {
    }

    public ValorBean(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String valor) {
        codigo = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String valor) {
        descricao = valor;
    }
}
