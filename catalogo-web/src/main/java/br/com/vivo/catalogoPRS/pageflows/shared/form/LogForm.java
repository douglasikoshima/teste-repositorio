package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class LogForm extends ValidatorActionForm  implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private String nmFile;

	public Boolean getCadastrar() {
		return cadastrar;
	}

	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}

	public String getNmFile() {
		return nmFile;
	}

	public void setNmFile(String nmFile) {
		this.nmFile = nmFile;
	}

	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}

	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}	

}
