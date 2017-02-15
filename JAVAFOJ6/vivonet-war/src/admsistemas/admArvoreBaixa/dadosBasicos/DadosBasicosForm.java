package admsistemas.admArvoreBaixa.dadosBasicos;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

public class DadosBasicosForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 9005130610656029245L;
	private String nmBaixa;
	private String idBaixa;

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setNmBaixa(String nmBaixa) {
		this.nmBaixa = nmBaixa;
	}

	public String getNmBaixa() {
		return this.nmBaixa;
	}
}