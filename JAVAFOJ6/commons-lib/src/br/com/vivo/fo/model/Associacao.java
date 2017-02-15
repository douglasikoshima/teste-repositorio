package br.com.vivo.fo.model; 

import br.com.vivo.fo.model.Contato;
import br.com.vivo.fo.model.Formulario;
import br.com.vivo.fo.model.Funcionalidade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Associacao implements Serializable { 

	private static final long serialVersionUID = 7893421395755942551L;
	private Funcionalidade funcionalidade;
    private Contato contato;
    private List listaFormulario = new ArrayList();
    
    public void setFuncionalidade(Funcionalidade funcionalidade) {
        this.funcionalidade = funcionalidade;
    }
    
    public Funcionalidade getFuncionalidade() {
        return funcionalidade;
    }
    
    public void setContato(Contato contato) {
        this.contato = contato;
    }
    
    public Contato getContato() {
        return contato;
    }
    
    public void setListaFormulario(List lista) {
        this.listaFormulario = lista;
    }
    
    public List getListaFormulario() {
        return listaFormulario;
    }
    
    public void addFormulario(Formulario formulario) {
        listaFormulario.add(formulario);
    }
    
    public void removeFormulario(Integer idFormulario) {
        for (int i = 0; i < listaFormulario.size(); i++) {
            Formulario formulario = (Formulario)listaFormulario.get(i);
            
            if (formulario.getId().equals(idFormulario)) {
                listaFormulario.remove(i);
            }
        }
    }
    
    public void removeFormulario(Formulario formulario) {
        listaFormulario.remove(formulario);
    }
    
    public Formulario findFormulario(Integer id) {
        Formulario formulario = new Formulario(id);
        int i = listaFormulario.indexOf(formulario);
       
        if (i >= 0) {
            return (Formulario)listaFormulario.get(i);
        } else {
            return null;
        }
    }
} 
