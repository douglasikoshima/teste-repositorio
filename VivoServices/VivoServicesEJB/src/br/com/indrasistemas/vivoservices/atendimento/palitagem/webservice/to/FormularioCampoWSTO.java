package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class FormularioCampoWSTO extends BaseTransferObject {

	private static final long serialVersionUID = -408822215518937815L;

	private long idContatoFolhaCampo;
	private long idCampo;
	private FormularioCampoValorWSTO[] formularioCampoValorWSTO;

	public long getIdContatoFolhaCampo() {
		return idContatoFolhaCampo;
	}

	public void setIdContatoFolhaCampo(long idContatoFolhaCampo) {
		this.idContatoFolhaCampo = idContatoFolhaCampo;
	}

	public long getIdCampo() {
		return idCampo;
	}

	public void setIdCampo(long idCampo) {
		this.idCampo = idCampo;
	}

	public FormularioCampoValorWSTO[] getFormularioCampoValorWSTO() {
		return formularioCampoValorWSTO;
	}

	public void setFormularioCampoValorWSTO(
			FormularioCampoValorWSTO[] formularioCampoValorWSTO) {
		this.formularioCampoValorWSTO = formularioCampoValorWSTO;
	}

}
