package br.com.vivo.fo.model; 

import java.io.Serializable;

public class Contato implements Serializable {

	private static final long serialVersionUID = -687584187314521847L;
	private Integer id;
    private String nome;
    
    public Contato() {
    }

    public Contato(Integer id) {
         this.id = id;   
    }
    
    public Contato(Integer id, String nome) {
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
