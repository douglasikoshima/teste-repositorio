package admsistemas.admArvoreContato.dadosBasicos;

import org.apache.struts.action.ActionForm;

public class DadosBasicosForm extends ActionForm {

	private static final long serialVersionUID = 7001790221742409186L;

	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	private String nmContato;

	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}

	public String getNmContato() {
		return this.nmContato;
	}
}