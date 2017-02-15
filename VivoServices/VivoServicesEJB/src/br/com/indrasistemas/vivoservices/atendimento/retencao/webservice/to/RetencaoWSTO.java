package br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class RetencaoWSTO extends RespostaWSTO {

    private static final long   serialVersionUID = -1347032197801232386L;

    private String              codError;
    private String              msgError;
    private Long                nrRetencao;
    private RetencaoParamWSTO[] out;

    public RetencaoWSTO() {
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getCodiRet() {
        return codError;
    }

    public void setCodiRet(String codError) {
        this.codError = codError;
    }

    public Long getNrRetencao() {
        return nrRetencao;
    }

    public void setNrRetencao(Long nrRetencao) {
        this.nrRetencao = nrRetencao;
    }

    public RetencaoParamWSTO[] getOut() {
        return out;
    }

    public void setOut(RetencaoParamWSTO[] out) {
        this.out = out;
    }

}