package fidelizacao.formBeans;

import java.util.ArrayList;
import org.apache.struts.action.ActionForm;

@SuppressWarnings({"rawtypes"})
public class ActionOferAparelhoFormBean extends ActionForm {

    private static final long serialVersionUID = 2601375023118966134L;
    private ArrayList         aparelhos;
    private String            tipoCliente;
    private String            carterizacao;
    private String            segmentacao;
    private String            nomeCliente;

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setSegmentacao(String segmentacao) {
        this.segmentacao = segmentacao;
    }

    public String getSegmentacao() {
        return this.segmentacao;
    }

    public void setCarterizacao(String carterizacao) {
        this.carterizacao = carterizacao;
    }

    public String getCarterizacao() {
        return this.carterizacao;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return this.tipoCliente;
    }

    public void setAparelhos(ArrayList aparelhos) {

        this.aparelhos = aparelhos;
    }

    public ArrayList getAparelhos() {
        return this.aparelhos;
    }
}
