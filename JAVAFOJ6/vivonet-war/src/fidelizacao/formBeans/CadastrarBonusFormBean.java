package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.BonusVODocument;
import br.com.vivo.fo.fidelizacao.vo.BonusVODocument.BonusVO;

public class CadastrarBonusFormBean extends ActionForm {

    private static final long serialVersionUID = -2048855040961740003L;
    private BonusVO[]         bonusArray;
    private BonusVODocument   bonus;
    private String            validade;
    private String            dtFim;
    private String            dtInicio;
    private String            bonusSelecionado;
    private String            vlPropriedade;
    private String            nmPropriedade;
    private String            inExcecao;

    /************************** Modificado por Décio Jr 15/10/2004 **********************************/
    private BonusVO[]         listaValidade    = new BonusVO[0];

    public BonusVO[] getListaValidade() {
        return this.listaValidade;
    }

    public void setListaValidade(BonusVO[] lista) {
        this.listaValidade = lista;
    }

    private String validadeSelecionada = ConstantesCRM.SVAZIO;

    public String getValidadeSelecionada() {
        return this.validadeSelecionada;
    }

    public void setValidadeSelecionada(String s) {
        this.validadeSelecionada = s;
    }

    /***********************************************************************************************/
    public void setInExcecao(String inExcecao) {
        this.inExcecao = inExcecao;
    }

    public String getInExcecao() {
        return this.inExcecao;
    }

    public void setNmPropriedade(String nmPropriedade) {
        this.nmPropriedade = nmPropriedade;
    }

    public String getNmPropriedade() {
        return this.nmPropriedade;
    }

    public void setVlPropriedade(String vlPropriedade) {
        this.vlPropriedade = vlPropriedade;
    }

    public String getVlPropriedade() {
        return this.vlPropriedade;
    }

    public void setBonusSelecionado(String bonusSelecionado) {
        this.bonusSelecionado = bonusSelecionado;
    }

    public String getBonusSelecionado() {
        return this.bonusSelecionado;
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

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getValidade() {
        return this.validade;
    }

    public void setBonus(BonusVODocument bonus) {
        this.bonus = bonus;
    }

    public BonusVODocument getBonus() {
        return this.bonus;
    }

    public void setBonusArray(br.com.vivo.fo.fidelizacao.vo.BonusVODocument.BonusVO[] bonusArray) {
        this.bonusArray = bonusArray;
    }

    public br.com.vivo.fo.fidelizacao.vo.BonusVODocument.BonusVO[] getBonusArray() {
        return this.bonusArray;
    }
}
