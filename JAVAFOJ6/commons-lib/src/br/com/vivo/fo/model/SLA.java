package br.com.vivo.fo.model;

import java.io.Serializable;

public class SLA implements Serializable {

	private static final long serialVersionUID = 8234156420163089664L;
	private Integer id;
    private String nome;
    private Integer valor;

    public SLA() {
    }

    public SLA(Integer id) {
        this.id = id;
    }

    public SLA(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public SLA(Integer id, String nome, Integer valor) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public String toString() {
        return id + " - " + nome;
    }

    public void setValor(Integer valor) {
        this.id = valor;
    }

    public Integer getValor() {
        return this.valor;
    }
}
