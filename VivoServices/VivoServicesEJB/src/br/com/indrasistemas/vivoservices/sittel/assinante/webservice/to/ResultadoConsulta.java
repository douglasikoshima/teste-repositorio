package br.com.indrasistemas.vivoservices.sittel.assinante.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoConsulta extends RespostaWSTO {

    private static final long serialVersionUID = -1822557320824195365L;

    private Long              codigoErro;
    private String            descricaoErro;

    public ResultadoConsulta() {

    }

    public ResultadoConsulta(Long codigoErro, String descricaoErro, String status, String reason) {
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
