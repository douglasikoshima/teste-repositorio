package br.com.indrasistemas.vivoservices.atendimento.palitagem.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class FormularioCampoTO extends BaseTransferObject {

	private static final long serialVersionUID = -408822215518937815L;

	private long idContatoFolhaCampo;
	private long idCampo;

	private FormularioCampoValorTO[] formularioCampoValorTO;

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

	public FormularioCampoValorTO[] getFormularioCampoValorTO() {
		return formularioCampoValorTO;
	}

	public void setFormularioCampoValorTO(
			FormularioCampoValorTO[] formularioCampoValorTO) {
		this.formularioCampoValorTO = formularioCampoValorTO;
	}

}
