package admsistemas.parametrizacoes.parametrosSistema;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.dbclasses.ApoioParametro;

public class ParametrosForm extends ActionForm {

	private static final long serialVersionUID = 8171474486791896839L;

	private ApoioParametro parametro;
	private ApoioParametro[] listaParametros;

	public ApoioParametro getParametro() {
		if (this.parametro == null) {
			this.parametro = new ApoioParametro();
		}
		return this.parametro;
	}

	public void setParametro(ApoioParametro arg) {
		this.parametro = arg;
	}

	public ApoioParametro[] getListaParametros() {
		if (this.listaParametros == null) {
			this.listaParametros = new ApoioParametro[0];
		}
		return this.listaParametros;
	}

	public void setListaParametros(ApoioParametro[] arg) {
		this.listaParametros = arg;
	}
}