package admsistemas.parametrizacoes.mensagemSms;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class MensagemSmsForm extends ActionForm {

	private static final long serialVersionUID = -4778887832212940398L;

	private String textArea;
	private String selecionado;

	public void setTextArea(String textArea) {
		if (textArea == null) {
			this.textArea = ConstantesCRM.SVAZIO;
		}
		this.textArea = textArea;
	}

	public String getTextArea() {
		return this.textArea;
	}

	public void setSelecionado(String selecionado) {
		if (selecionado == null) {
			this.selecionado = ConstantesCRM.SVAZIO;
		}
		this.selecionado = selecionado;
	}

	public String getSelecionado() {
		return this.selecionado;
	}

}