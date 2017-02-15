package fidelizacao.formBeans;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

@SuppressWarnings({"rawtypes"})
public class ActionRealizarRetencaoFormBean extends ActionForm {

    private static final long serialVersionUID = -2872534092592987875L;
    private ArrayList         listaLinhas      = new ArrayList();
    private String            linha;

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getLinha() {
        return this.linha;
    }

    public void setListaLinhas(ArrayList listaLinhas) {
        this.listaLinhas = listaLinhas;
    }

    public ArrayList getListaLinhas() {
        return this.listaLinhas;
    }
}
