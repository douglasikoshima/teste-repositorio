package admsistemas.admArvoreContato.workflow.abaDadosBasicosW;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;

public class FormDadosBasicosW extends ActionForm implements Serializable {

	private static final long serialVersionUID = -8402158396236331760L;

	private AdmTipoRetornoContatoVO[] tipoRetornoContatoVO;
	private String[] arraytipoRetornoContato;
	private String vlPesoContato;
	private String qtDiasPrazoContato;
	private String idContato;

	public FormDadosBasicosW() {

	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setQtDiasPrazoContato(String qtDiasPrazoContato) {
		this.qtDiasPrazoContato = qtDiasPrazoContato;
	}

	public String getQtDiasPrazoContato() {
		return this.qtDiasPrazoContato;
	}

	public void setVlPesoContato(String vlPesoContato) {
		this.vlPesoContato = vlPesoContato;
	}

	public String getVlPesoContato() {
		return this.vlPesoContato;
	}

	public void setArraytipoRetornoContato(String[] arraytipoRetornoContato) {
		this.arraytipoRetornoContato = arraytipoRetornoContato;
	}

	public String[] getArraytipoRetornoContato() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arraytipoRetornoContato == null || this.arraytipoRetornoContato.length == 0) {
			this.arraytipoRetornoContato = new String[1];
		}

		return this.arraytipoRetornoContato;
	}

	public void setTipoRetornoContatoVO(AdmTipoRetornoContatoVO[] tipoRetornoContatoVO) {
		this.tipoRetornoContatoVO = tipoRetornoContatoVO;
	}

	public AdmTipoRetornoContatoVO[] getTipoRetornoContatoVO() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize tipoRetornoContatoVO if it is null.
		// if(this.tipoRetornoContatoVO == null)
		// {
		// this.tipoRetornoContatoVO = new AdmTipoRetornoContatoVO(?);
		// }

		return this.tipoRetornoContatoVO;
	}
}