package workflow.AtendimentoInBox;

import org.apache.struts.action.ActionForm;

public class ExpandeArvoreContatoForm extends ActionForm {

	private static final long serialVersionUID = -5594535355727612627L;

	private String scriptArvore;
	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setScriptArvore(String scriptArvore) {
		this.scriptArvore = scriptArvore;
	}

	public String getScriptArvore() {
		return this.scriptArvore;
	}
}