package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class RelatorioFixaForm extends ValidatorActionForm  implements java.io.Serializable {
	
	private static final long serialVersionUID = 5952016246619710639L;
	private String tipoPesquisa;

	public String getTipoPesquisa() {
		return tipoPesquisa;
	}

	public void setTipoPesquisa(String tipoPesquisa) {
		this.tipoPesquisa = tipoPesquisa;
	}	

}
