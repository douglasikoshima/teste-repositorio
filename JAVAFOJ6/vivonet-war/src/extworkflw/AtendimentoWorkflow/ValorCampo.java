package extworkflw.AtendimentoWorkflow;

import java.io.Serializable;

public class ValorCampo implements Serializable {

	private static final long serialVersionUID = 10932872837892L;
	private String valor;
	private String[] valorArray;

	public String getValor() {
		return valor;
	}

	public String[] getValorArray() {
		return valorArray;
	}

	public void setValor(String strings) {
		valor = strings;
	}

	public void setValorArray(String[] string) {
		valorArray = string;
	}
}