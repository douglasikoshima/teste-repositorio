package br.com.indrasistemas.vivoservices.atendimento.ordemvenda.webservice.to;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;

public class manterOrdemVenda {

    //private Long           nrProtocolo;
    //private String         cdServico;
    //private Long           idSistema;
    //private String         dsComentario;
    //private Long           idGrpAbertura;
    //private Long           idCanal;
    //private Long           idUsuario;
    //private Long           idContato;
    //private Boolean        consultor;
    //private Integer        tpOperacao;
    //private String         nrDocumento;
    //private RequestInfoTO  requestInfo;
    private String         cdOrdemVenda;
    private String         cdUsuarioOrdemVenda;
    private String         cdStatusRejeicao;
    private String         dsMotivoRejeicao;
    //private OutManterOrdemVenda formularioWSTO;
    
    public String getCdOrdemVenda() {
        return cdOrdemVenda;
    }
    public void setCdOrdemVenda(String cdOrdemVenda) {
        this.cdOrdemVenda = cdOrdemVenda;
    }
    public String getCdUsuarioOrdemVenda() {
        return cdUsuarioOrdemVenda;
    }
    public void setCdUsuarioOrdemVenda(String cdUsuarioOrdemVenda) {
        this.cdUsuarioOrdemVenda = cdUsuarioOrdemVenda;
    }
    public String getCdStatusRejeicao() {
        return cdStatusRejeicao;
    }
    public void setCdStatusRejeicao(String cdStatusRejeicao) {
        this.cdStatusRejeicao = cdStatusRejeicao;
    }
    public String getDsMotivoRejeicao() {
        return dsMotivoRejeicao;
    }
    public void setDsMotivoRejeicao(String dsMotivoRejeicao) {
        this.dsMotivoRejeicao = dsMotivoRejeicao;
    }
    //public RequestInfoTO getRequestInfo() {
    //    return requestInfo;
    //}
    //public void setRequestInfo(RequestInfoTO requestInfo) {
    //    this.requestInfo = requestInfo;
    //}

}
