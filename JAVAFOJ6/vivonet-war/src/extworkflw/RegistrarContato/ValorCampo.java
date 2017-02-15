package extworkflw.RegistrarContato;

import java.io.Serializable;

public class ValorCampo implements Serializable {

	private static final long serialVersionUID = -2258128582081567961L;

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