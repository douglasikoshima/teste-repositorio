package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;
import questionario.QuestionarioController.IniciaQuestionarioForm;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;

public class PesquisaDestinoPrevistoFormBean extends ActionForm {

    private static final long      serialVersionUID       = 6811985601140085981L;

    private ItemListaVO[]          intCancelamento        = new ItemListaVO[0];
    private IniciaQuestionarioForm iniciaQuestionarioForm = new IniciaQuestionarioForm();
    private String                 intencaoSelecionada;
    private String                 idUfOperadora;
    private String                 idTipoEncerramento;

    public void setIdTipoEncerramento(String idTipoEncerramento) {
        this.idTipoEncerramento = idTipoEncerramento;
    }

    public String getIdTipoEncerramento() {
        return this.idTipoEncerramento;
    }

    public void setIntencaoSelecionada(String intencaoSelecionada) {
        this.intencaoSelecionada = intencaoSelecionada;
    }

    public String getIntencaoSelecionada() {
        return this.intencaoSelecionada;
    }

    public void setIdUfOperadora(String idUfOperadora) {
        this.idUfOperadora = idUfOperadora;
    }

    public String getIdUfOperadora() {
        return this.idUfOperadora;
    }

    public void setIniciaQuestionarioForm(IniciaQuestionarioForm iniciaQuestionarioForm) {
        this.iniciaQuestionarioForm = iniciaQuestionarioForm;
    }

    public IniciaQuestionarioForm getIniciaQuestionarioForm() {
        if (this.iniciaQuestionarioForm == null) {
            this.iniciaQuestionarioForm = new IniciaQuestionarioForm();
        }

        return this.iniciaQuestionarioForm;
    }

    public void setIntCancelamento(ItemListaVO[] intCancelamento) {
        this.intCancelamento = intCancelamento;
    }

    public ItemListaVO[] getIntCancelamento() {

        return this.intCancelamento;
    }

}
