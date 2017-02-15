package br.com.indrasistemas.vivoservices.sittel.assinante.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoConsultaTO extends RespostaWSTO {

    private static final long serialVersionUID = -1324938960205322605L;

    private Long              codigoErro;
    private String            descricaoErro;

    public ResultadoConsultaTO() {

    }

    public ResultadoConsultaTO(Long codigoErro, String descricaoErro, String status, String reason) {
        this.setCodigoErro(codigoErro);
        this.setDescricaoErro(descricaoErro);
        this.setStatus(status);
        this.setReason(reason);
    }

    public Long getCodigoErro() {
        return codigoErro;
    }

    public void setCodigoErro(Long codigoErro) {
        this.codigoErro = codigoErro;
    }

    public String getDescricaoErro() {
        return descricaoErro;
    }

    public void setDescricaoErro(String descricaoErro) {
        this.descricaoErro = descricaoErro;
    }

}
