package br.com.indrasistemas.vivoservices.atendimento.retencao.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class RetencaoParamTO extends BaseTransferObject {

    private static final long serialVersionUID = -3276957361140651457L;

    private String nmGrupo = "";
    private String dtValidade = "";
    private String cdBonus = "";
    private String vlTarifa = "";

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public String getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(String dtValidade) {
        this.dtValidade = dtValidade;
    }

    public String getCdBonus() {
        return cdBonus;
    }

    public void setCdBonus(String cdBonus) {
        this.cdBonus = cdBonus;
    }

    public String getVlTarifa() {
        return vlTarifa;
    }

    public void setVlTarifa(String vlTarifa) {
        this.vlTarifa = vlTarifa;
    }

}
