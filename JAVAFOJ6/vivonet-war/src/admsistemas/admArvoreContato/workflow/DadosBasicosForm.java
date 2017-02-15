package admsistemas.admArvoreContato.workflow;

import org.apache.struts.action.ActionForm;

public class DadosBasicosForm extends ActionForm {

	private static final long serialVersionUID = 6778440978672719637L;
	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}
}