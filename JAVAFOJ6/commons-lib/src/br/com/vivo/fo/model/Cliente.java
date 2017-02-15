package br.com.vivo.fo.model; 

import java.io.Serializable;

public class Cliente implements Serializable {

	private static final long serialVersionUID = -4618713957613012995L;

	private String nrCNPJ;
    private String nome;
    private String nrDocumentoFormatado;
    
    public Cliente() {
    }
    
    public Cliente(String nrCNPJ) {
        this.nrCNPJ = nrCNPJ;
    }
    
    public Cliente(String nrCNPJ, String nome) {
        this.nrCNPJ = nrCNPJ;
        this.nome = nome;
    }
    
    public void setNrCNPJ(String nrCNPJ) {
        this.nrCNPJ = nrCNPJ;
    }
    
    public String getNrCNPJ() {
        return nrCNPJ;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String toString() {
        return nrCNPJ + " - " + nome;
    }
    
    public String getNrDocumentoFormatado() {
        return nrDocumentoFormatado;
    }

    public void setNrDocumentoFormatado(String nrDocumentoFormatado) {
        this.nrDocumentoFormatado = nrDocumentoFormatado;
    }
    
    public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nrCNPJ == null) ? 0 : nrCNPJ.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Cliente other = (Cliente) obj;
		if (nrCNPJ == null) {
			if (other.nrCNPJ != null)
				return false;
		} else if (!nrCNPJ.equals(other.nrCNPJ))
			return false;
		return true;
	}
    
    
} 
