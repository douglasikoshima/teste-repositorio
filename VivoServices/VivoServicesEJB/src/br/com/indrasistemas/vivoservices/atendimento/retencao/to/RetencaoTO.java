package br.com.indrasistemas.vivoservices.atendimento.retencao.to;

import java.util.Date;
import br.com.indrasistemas.framework.service.BaseTransferObject;

public class RetencaoTO extends BaseTransferObject {

    private static final long serialVersionUID = -7931367366798000781L;

    // Dados de entrada
    private String            nrOperacao;
    private String            nrTelefone = "";
    private String            nrTipo = "";
    private String            cdOferta = "";

    // Dados de retorno
    private String            nmGrupo = "";
    private Date              dtValidade;
    private String            cdBonus = "";
    private String            vlTarifa = "";

    private Long              nrRetencao;
    private String            cdRetorno = "";
    private String            msgRetorno = "";

    private RetencaoParamTO[] out;

    public String getNrOperacao() {
        return nrOperacao;
    }

    public void setNrOperacao(String nrOperacao) {
        this.nrOperacao = nrOperacao;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getNrTipo() {
        return nrTipo;
    }

    public void setNrTipo(String nrTipo) {
        this.nrTipo = nrTipo;
    }

    public String getCdOferta() {
        return cdOferta;
    }

    public void setCdOferta(String cdOferta) {
        this.cdOferta = cdOferta;
    }

    public String getNmGrupo() {
        return nmGrupo;
    }

    public void setNmGrupo(String nmGrupo) {
        this.nmGrupo = nmGrupo;
    }

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
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

    public Long getNrRetencao() {
        return nrRetencao;
    }

    public void setNrRetencao(Long nrRetencao) {
        this.nrRetencao = nrRetencao;
    }

    public RetencaoParamTO[] getOut() {
        return out;
    }

    public void setOut(RetencaoParamTO[] out) {
        this.out = out;
    }

    public String getCdRetorno() {
        return cdRetorno;
    }

    public void setCdRetorno(String cdRetorno) {
        this.cdRetorno = cdRetorno;
    }

    public String getMsgRetorno() {
        return msgRetorno;
    }

    public void setMsgRetorno(String msgRetorno) {
        this.msgRetorno = msgRetorno;
    }
}
