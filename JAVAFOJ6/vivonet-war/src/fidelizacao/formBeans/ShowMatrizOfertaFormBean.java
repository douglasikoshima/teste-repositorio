package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;
import fidelizacao.FidelizacaoController.PesquisaDestinoPrevistoForm;

public class ShowMatrizOfertaFormBean extends ActionForm {

    private static final long           serialVersionUID            = -4936246794162630092L;

    private PesquisaDestinoPrevistoForm pesquisaDestinoPrevistoForm = new PesquisaDestinoPrevistoForm();
    private String                      idDestinoPrevisto;
    private String                      idTipoEncerramento;

    public void setIdTipoEncerramento(String idTipoEncerramento) {
        this.idTipoEncerramento = idTipoEncerramento;
    }

    public String getIdTipoEncerramento() {
        return this.idTipoEncerramento;
    }

    public void setIdDestinoPrevisto(String idDestinoPrevisto) {
        this.idDestinoPrevisto = idDestinoPrevisto;
    }

    public String getIdDestinoPrevisto() {
        return this.idDestinoPrevisto;
    }

    public void setPesquisaDestinoPrevistoForm(PesquisaDestinoPrevistoForm pesquisaDestinoPrevistoForm) {
        this.pesquisaDestinoPrevistoForm = pesquisaDestinoPrevistoForm;
    }

    public PesquisaDestinoPrevistoForm getPesquisaDestinoPrevistoForm() {
        if (this.pesquisaDestinoPrevistoForm == null) {
            this.pesquisaDestinoPrevistoForm = new PesquisaDestinoPrevistoForm();
        }

        return this.pesquisaDestinoPrevistoForm;
    }

}
