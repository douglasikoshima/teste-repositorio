package com.indracompany.catalogo.to;

import java.io.Serializable;

public class ServicoOfertafixaTO implements Serializable {

    private static final long serialVersionUID = 4199201626256794795L;
    
    private Integer id;
    private String nome;
    private String cdServico;
    private ComplementoLegadoTO complementoLegado;
    
    public ComplementoLegadoTO getComplementoLegado() {
		return complementoLegado;
	}

	public void setComplementoLegado(ComplementoLegadoTO complementoLegado) {
		this.complementoLegado = complementoLegado;
	}

	public ServicoOfertafixaTO() {}
    
    public ServicoOfertafixaTO(Integer id) {
    	this();
        this.id = id;
    }
    
    public ServicoOfertafixaTO(Integer id, String nome) {
    	this(id);
        this.nome = nome;
    }
    
    public ServicoOfertafixaTO(Integer id, String nome, String cdServico) {
    	this(id, nome);
    	this.cdServico = cdServico;
    }    
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCdServico() {
        return cdServico;
    }
    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }
}
