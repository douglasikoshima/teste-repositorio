package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class ImportacaoServicoFixaForm extends ValidatorActionForm implements Serializable {
    private static final long serialVersionUID = -7073697592854621205L;
    
    private FormFile fileImport;
	private int idTipoImportacao;
    
    public void clean(){
        this.fileImport = null;
        this.idTipoImportacao = 0;
    }
	
	public FormFile getFileImport() {
		return fileImport;
	}
	public void setFileImport(FormFile fileImport) {
		this.fileImport = fileImport;
	}
	public int getIdTipoImportacao() {
		return idTipoImportacao;
	}
	public void setIdTipoImportacao(int idTipoImportacao) {
		this.idTipoImportacao = idTipoImportacao;
	}

}
