package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class ImportacaoTipoMatrizContratoForm extends ValidatorActionForm  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Boolean cadastrar;
	private FormFile fileImport;
	private String trabalho;
	
	public FormFile getFileImport() {
		return fileImport;
	}
	public void setFileImport(FormFile fileImport) {
		this.fileImport = fileImport;
	}
	public Boolean getCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	public String getTrabalho() {
		return trabalho;
	}
	public void setTrabalho(String trabalho) {
		this.trabalho = trabalho;
	}	
}
