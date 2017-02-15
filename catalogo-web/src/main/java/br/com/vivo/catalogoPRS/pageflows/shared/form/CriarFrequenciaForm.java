package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class CriarFrequenciaForm extends ValidatorActionForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String tipoFrequencia;

	private Long qtdeFrequencia;
	
	private Long idEntidade;
	
	private String justificativa;
	

    public Long getIdEntidade() {
		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

	public String getJustificativa() {
		return justificativa;
	}

	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}

	public Long getQtdeFrequencia() {
		return qtdeFrequencia;
	}

	public void setQtdeFrequencia(Long qtdeFrequencia) {
		this.qtdeFrequencia = qtdeFrequencia;
	}

	public String getTipoFrequencia() {
		return tipoFrequencia;
	}

	public void setTipoFrequencia(String tipoFrequencia) {
		this.tipoFrequencia = tipoFrequencia==null?tipoFrequencia:tipoFrequencia.toUpperCase();
	
	}
}