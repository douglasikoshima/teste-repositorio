package br.com.vivo.fo.model; 

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO

public class Formulario implements Serializable { 

	private static final long serialVersionUID = -1663296818640663706L;
	private Integer id;
    private String nome;
    private SLA sla;
    private List listaClientesAssociados = new ArrayList();
    private List listaClientesDessassociados = new ArrayList();
    
    public List getListaClientesDessassociados() {
		return listaClientesDessassociados;
	}

	public void setListaClientesDessassociados(List listaClientesDessassociados) {
		this.listaClientesDessassociados = listaClientesDessassociados;
	}

	public Formulario() {
    }

    public Formulario(Integer id) {
        this.id = id;
    }
    
    public Formulario(Integer id, String nome) {
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
    
    public void setSLA(SLA sla) {
        this.sla = sla;
    }
    
    public SLA getSLA() {
        return sla;
    }
    
    public void setListaClientesAssociados(List lista) {
        this.listaClientesAssociados = lista;
    }
    
    public List getListaClientesAssociados() {
        return listaClientesAssociados;
    }
    
    public void addClienteAssociado(Cliente cliente) {
        listaClientesAssociados.add(cliente);
    }
    
    public void addClienteDessassociado(Cliente cliente) {
    	listaClientesDessassociados.add(cliente);
    }    
    
    
    public void removeClienteDessassociado(String NrCNPJ) {
        for (int i = 0; i < listaClientesDessassociados.size(); i++) {
            Cliente cliente = (Cliente)listaClientesDessassociados.get(i);
             
            if (cliente.getNrCNPJ().equals(NrCNPJ)) {
            	listaClientesDessassociados.remove(i);
            }
        }
    }
        
    
    public void removeClienteAssociado(String NrCNPJ) {
        for (int i = 0; i < listaClientesAssociados.size(); i++) {
            Cliente cliente = (Cliente)listaClientesAssociados.get(i);
             
            if (cliente.getNrCNPJ().equals(NrCNPJ)) {
                listaClientesAssociados.remove(i);
            }
        }
    }
    
    public void removeClienteDessassociado(Cliente cliente) {
    	listaClientesDessassociados.remove(cliente);
    }    
    
    public void removeClienteAssociado(Cliente cliente) {
        listaClientesAssociados.remove(cliente);
    }
    
    public Cliente findClienteDessassociado(String nrCnpj) {
        Cliente cliente = new Cliente(nrCnpj);
        int i = listaClientesDessassociados.indexOf(cliente);
        
        if (i >= 0) {
            return (Cliente)listaClientesDessassociados.get(i);
        } else {
            return null;
        }
    }    
    
    public Cliente findCliente(String nrCnpj) {
        Cliente cliente = new Cliente(nrCnpj);
        int i = listaClientesAssociados.indexOf(cliente);
        
        if (i >= 0) {
            return (Cliente)listaClientesAssociados.get(i);
        } else {
            return null;
        }
    }
        
    public String toString() {
        return  id + " - " + nome;
    }
    
     public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Formulario other = (Formulario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   
} 
