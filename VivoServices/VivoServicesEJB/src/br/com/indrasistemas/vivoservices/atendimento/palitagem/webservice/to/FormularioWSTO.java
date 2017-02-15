package br.com.indrasistemas.vivoservices.atendimento.palitagem.webservice.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class FormularioWSTO extends BaseTransferObject {

	private static final long serialVersionUID = -4055214518937815L;

	private FormularioCampoWSTO[] formularioCampoWSTO;

	public FormularioCampoWSTO[] getFormularioCampoWSTO() {
		return formularioCampoWSTO;
	}

	public void setFormularioCampoWSTO(FormularioCampoWSTO[] formularioCampoWSTO) {
		this.formularioCampoWSTO = formularioCampoWSTO;
	}
}
