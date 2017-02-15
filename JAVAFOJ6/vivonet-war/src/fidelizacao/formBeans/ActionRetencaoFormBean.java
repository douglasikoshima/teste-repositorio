package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.DetalheLinhaVODocument.DetalheLinhaVO;

public class ActionRetencaoFormBean extends ActionForm {

    private static final long serialVersionUID   = -2579371548257095463L;

    private String            strLinhaSel        = ConstantesCRM.SVAZIO;
    private String            totalLinhas        = ConstantesCRM.SVAZIO;
    private String            linhaSelecionada   = ConstantesCRM.SVAZIO;
    private String            linhaPesquisa      = ConstantesCRM.SVAZIO;
    private String            tamanhoLista       = ConstantesCRM.SVAZIO;
    private DetalheLinhaVO[]  linhas             = null;                  // new DetalheLinhaVO[0];
    private String            idTipoEncerramento = ConstantesCRM.SVAZIO;

    public void setIdTipoEncerramento(String idTipoEncerramento) {
        this.idTipoEncerramento = idTipoEncerramento;
    }

    public String getIdTipoEncerramento() {
        return this.idTipoEncerramento;
    }

    public void setTamanhoLista(String tamanhoLista) {
        this.tamanhoLista = tamanhoLista;
    }

    public String getTamanhoLista() {
        this.tamanhoLista = String.valueOf(this.linhas.length);
        return this.tamanhoLista;
    }

    public void setLinhaPesquisa(String linhaPesquisa) {
        this.linhaPesquisa = linhaPesquisa;
    }

    public String getLinhaPesquisa() {
        return this.linhaPesquisa;
    }

    public void setLinhaSelecionada(String linhaSelecionada) {
        this.linhaSelecionada = linhaSelecionada;
    }

    public String getLinhaSelecionada() {
        return this.linhaSelecionada;
    }

    public void setTotalLinhas(String totalLinhas) {
        this.totalLinhas = totalLinhas;
    }

    public String getTotalLinhas() {
        return this.totalLinhas;
    }

    public void setLinhas(DetalheLinhaVO[] _linhas) {
        if (this.linhas == null) {
            this.linhas = new DetalheLinhaVO[0];
        }
        this.linhas = _linhas;
    }

    public DetalheLinhaVO[] getLinhas() {
        return this.linhas;
    }

    public void setStrLinhaSel(String strLinhaSel) {
        this.strLinhaSel = strLinhaSel;
    }

    public String getStrLinhaSel() {
        return this.strLinhaSel;
    }

}
