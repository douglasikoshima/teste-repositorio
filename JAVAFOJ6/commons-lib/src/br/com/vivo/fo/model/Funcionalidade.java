package br.com.vivo.fo.model; 

import java.io.Serializable;

public class Funcionalidade implements Serializable { 

	private static final long serialVersionUID = -953129463522699846L;
	private Integer id;
    private String nome;
    
    public Funcionalidade() {
    }
    
    public Funcionalidade(Integer id) {
        this.id = id;
    }
    
    public Funcionalidade(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
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
} 
