package br.com.indrasistemas.vivoservices.atendimento.palitagem.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class PalitagemTO extends BaseTransferObject {

	private static final long serialVersionUID = -4088435138518937815L;

	private String nrProtocolo = "";
	private String cdServico = "";
	private String idSistema = "";
	private String dsComentario = "";
	private String idGrupoAbertura = "";
	private String idCanal = "";
	private String idUsuario = "";
	private String idAtendimento = "";

	private Integer tpOperacao;
	private Long idContato;
	private Boolean inConsultor;
	private String nrDocumento;

	private FormularioTO formularioTO;

	private String statusConsulta = "";
	private String errorCode = "";
	private String errorDescription = "";

	public String getNrProtocolo() {
		return nrProtocolo;
	}

	public void setNrProtocolo(String nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

	public String getCdServico() {
		return cdServico;
	}

	public void setCdServico(String cdServico) {
	    if ( cdServico != null )
	        this.cdServico = cdServico;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
        if ( idSistema != null )
            this.idSistema = idSistema;
	}

	public String getDsComentario() {
		return dsComentario;
	}

	public void setDsComentario(String dsComentario) {
        if ( dsComentario != null )
            this.dsComentario = dsComentario;
	}

	public String getIdGrupoAbertura() {
		return idGrupoAbertura;
	}

	public void setIdGrupoAbertura(String idGrupoAbertura) {
        if ( idGrupoAbertura != null )
            this.idGrupoAbertura = idGrupoAbertura;
	}

	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
        if ( idCanal != null )
            this.idCanal = idCanal;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
        if ( idUsuario != null )
            this.idUsuario = idUsuario;
	}

	public String getStatusConsulta() {
		return statusConsulta;
	}

	public void setStatusConsulta(String statusConsulta) {
        if ( statusConsulta != null )
            this.statusConsulta = statusConsulta;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
        if ( errorCode != null )
            this.errorCode = errorCode;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
        if ( errorDescription != null )
            this.errorDescription = errorDescription;
	}

	public Long getIdContato() {
		return idContato;
	}

	public void setIdContato(Long idContato) {
        if ( idContato != null )
            this.idContato = idContato;
        else
            this.idContato = new Long(0);
	}

	public Boolean isInConsultor() {
		return inConsultor;
	}

	public void setInConsultor(Boolean inConsultor) {
        if ( inConsultor != null )
            this.inConsultor = inConsultor;
        else
            this.inConsultor = new Boolean(false);
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
        if ( nrDocumento != null )
            this.nrDocumento = nrDocumento;
	}

	public FormularioTO getFormularioTO() {
		return formularioTO;
	}

	public void setFormularioTO(FormularioTO formularioTO) {
		this.formularioTO = formularioTO;
	}

	public Integer getTpOperacao() {
		return tpOperacao;
	}

	public void setTpOperacao(Integer tpOperacao) {
		this.tpOperacao = tpOperacao;
	}

    public Boolean getInConsultor() {
        return inConsultor;
    }

    public String getIdAtendimento() {
        return idAtendimento;
    }

    public void setIdAtendimento(String idAtendimento) {
        if ( idAtendimento != null )
            this.idAtendimento = idAtendimento;
    }
}
