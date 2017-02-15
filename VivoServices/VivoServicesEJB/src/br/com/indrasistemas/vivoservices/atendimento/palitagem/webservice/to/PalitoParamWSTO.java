package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to;

public class PalitoParamWSTO {

    private Long           nrProtocolo;
    private String         cdServico;
    private Long           idSistema;
    private String         dsComentario;
    private Long           idGrpAbertura;
    private Long           idCanal;
    private Long           idUsuario;
    private Long           idContato;
    private Boolean        consultor;
    private Integer        tpOperacao;
    private String         nrDocumento;
    private FormularioWSTO formularioWSTO;

    public Long getNrProtocolo() {
        return nrProtocolo;
    }

    public void setNrProtocolo(Long nrProtocolo) {
        if ( nrProtocolo != null )
            this.nrProtocolo = nrProtocolo;
    }

    public String getCdServico() {
        return cdServico;
    }

    public void setCdServico(String cdServico) {
        if ( cdServico != null )
            this.cdServico = cdServico;
    }

    public Long getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
    }

    public String getDsComentario() {
        return dsComentario;
    }

    public void setDsComentario(String dsComentario) {
        if ( dsComentario != null )
            this.dsComentario = dsComentario;
    }

    public Long getIdGrpAbertura() {
        return idGrpAbertura;
    }

    public void setIdGrpAbertura(Long idGrpAbertura) {
        if ( idGrpAbertura != null )
            this.idGrpAbertura = idGrpAbertura;
    }

    public Long getIdCanal() {
        return idCanal;
    }

    public void setIdCanal(Long idCanal) {
        if ( idCanal != null )
            this.idCanal = idCanal;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        if ( idUsuario != null )
            this.idUsuario = idUsuario;
    }

    public Long getIdContato() {
        return idContato;
    }

    public void setIdContato(Long idContato) {
        if ( idContato != null )
            this.idContato = idContato;
    }

    public String getNrDocumento() {
        return nrDocumento;
    }

    public void setNrDocumento(String nrDocumento) {
        if ( nrDocumento != null )
            this.nrDocumento = nrDocumento;
    }

    public FormularioWSTO getFormularioWSTO() {
        return formularioWSTO;
    }

    public void setFormularioWSTO(FormularioWSTO formularioWSTO) {
        this.formularioWSTO = formularioWSTO;
    }

    public Boolean isConsultor() {
        return consultor;
    }

    public void setConsultor(Boolean consultor) {
        if ( consultor != null )
            this.consultor = consultor;
        else
            this.consultor = new Boolean(false);
    }

    public Integer getTpOperacao() {
        return tpOperacao;
    }

    public void setTpOperacao(Integer tpOperacao) {
        if ( tpOperacao != null )
            this.tpOperacao = tpOperacao;
    }

}
