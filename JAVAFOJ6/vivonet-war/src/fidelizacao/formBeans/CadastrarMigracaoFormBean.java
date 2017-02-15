package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.MigracaoVODocument.MigracaoVO;

public class CadastrarMigracaoFormBean extends ActionForm {

    private static final long serialVersionUID      = -7918667697007533651L;
    private MigracaoVO[]      listaMigracao         = new MigracaoVO[0];
    private String            migracaoSelecionada   = ConstantesCRM.SVAZIO;

    private String            validade              = ConstantesCRM.SVAZIO;
    private String            valor                 = ConstantesCRM.SVAZIO;
    private String            disValor              = ConstantesCRM.SVAZIO;

    private String            idValidadeSelecionada = ConstantesCRM.SVAZIO;
    private String            idValorSelecionado    = ConstantesCRM.SVAZIO;

    private String            dtFim;
    private String            dtInicio;
    private String            inExcecao;

    private String            nrAtual               = ConstantesCRM.SVAZIO;
    private String            nrNovoPre             = ConstantesCRM.SVAZIO;

    private String            idTipoEncerramento    = ConstantesCRM.SVAZIO;
    private String            idRetencao            = ConstantesCRM.SVAZIO;

    public MigracaoVO[] getListaMigracao() {
        return this.listaMigracao;
    }

    public void setListaValidade(MigracaoVO[] lista) {
        this.listaMigracao = lista;
    }

    public String getMigracaoSelecionada() {
        return this.migracaoSelecionada;
    }

    public void setMigracaoSelecionada(String s) {
        this.migracaoSelecionada = s;
    }

    public String getIdValorSelecionado() {
        return this.idValorSelecionado;
    }

    public void setIdValorSelecionado(String s) {
        this.idValorSelecionado = s;
    }

    public void setInExcecao(String inExcecao) {
        this.inExcecao = inExcecao;
    }

    public String getInExcecao() {
        return this.inExcecao;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtInicio() {
        return this.dtInicio;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public String getDtFim() {
        return this.dtFim;
    }

    public void setIdValidadeSelecionada(String s) {
        this.idValidadeSelecionada = s;
    }

    public String getIdValidadeSelecionada() {
        return this.idValidadeSelecionada;
    }

    public void setValidade(String s) {
        this.validade = s;
    }

    public String getValidade() {
        return this.validade;
    }

    public void setValor(String s) {
        this.valor = s;
    }

    public String getValor() {
        return this.valor;
    }

    public void setNrAtual(String s) {
        this.nrAtual = s;
    }

    public String getNrAtual() {
        return this.nrAtual;
    }

    public void setNrNovoPre(String s) {
        this.nrNovoPre = s;
    }

    public String getNrNovoPre() {
        return this.nrNovoPre;
    }

    public void setIdTipoEncerramento(String s) {
        this.idTipoEncerramento = s;
    }

    public String getIdTipoEncerramento() {
        return this.idTipoEncerramento;
    }

    public void setIdRetencao(String s) {
        this.idRetencao = s;
    }

    public String getIdRetencao() {
        return this.idRetencao;
    }

    public void setDisValor(String s) {
        this.disValor = s;
    }

    public String getDisValor() {
        return this.disValor;
    }
}
