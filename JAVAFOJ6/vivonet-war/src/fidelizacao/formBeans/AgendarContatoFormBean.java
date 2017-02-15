package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

public class AgendarContatoFormBean extends ActionForm {

    private static final long serialVersionUID = 6443205186043612747L;

    private String            idUfOperadora;
    private String            idRetencao;
    private String            comentario;
    private String            minutos;
    private String            horas;
    private String            data;
    private String            telefoneContato;
    private String            nomeContato;

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getNomeContato() {
        return this.nomeContato;
    }

    public void setTelefoneContato(String telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public String getTelefoneContato() {
        return this.telefoneContato;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getHoras() {
        return this.horas;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getMinutos() {
        return this.minutos;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return this.comentario;
    }

    public void setIdRetencao(String idRetencao) {
        this.idRetencao = idRetencao;
    }

    public String getIdRetencao() {
        return this.idRetencao;
    }

    public void setIdUfOperadora(String idUfOperadora) {
        this.idUfOperadora = idUfOperadora;
    }

    public String getIdUfOperadora() {
        return this.idUfOperadora;
    }
}
