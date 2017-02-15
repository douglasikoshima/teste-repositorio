package br.com.indrasistemas.vivoservices.atendimento.palitagem.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class FormularioTO extends BaseTransferObject {

	private static final long serialVersionUID = -4055214518937815L;

	private FormularioCampoTO[] formularioCampoTO;

	public FormularioCampoTO[] getFormularioCampoTO() {
		return formularioCampoTO;
	}

	public void setFormularioCampoTO(FormularioCampoTO[] formularioCampoTO) {
		this.formularioCampoTO = formularioCampoTO;
	}
}
