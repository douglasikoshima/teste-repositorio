package admsistemas.PrazoCRI;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class PrazoCRIForm extends ActionForm {

	private static final long serialVersionUID = 4908446449459526993L;

	private String strMensagem = ConstantesCRM.SVAZIO;

	public void setMensagem(String strMensagem) {
		this.strMensagem = strMensagem;
	}

	public String getMensagem() {
		return this.strMensagem;
	}

	private String idRetorno = ConstantesCRM.SVAZIO;

	public void setIdRetorno(String idRetorno) {
		this.idRetorno = idRetorno;
	}

	public String getIdRetorno() {
		return this.idRetorno;
	}
}