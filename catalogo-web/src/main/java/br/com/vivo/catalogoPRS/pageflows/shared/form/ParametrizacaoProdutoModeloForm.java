package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class ParametrizacaoProdutoModeloForm extends ValidatorActionForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1485768055435754166L;
	
	private String tipoProdutoPesquisa;
	
	private String fabricantePesquisa;
	
	private String modeloPesquisa;
	
	private String tecnologiaPesquisa;
	
	private String tipoProdutoCadastro;
	
	private String fabricanteCadastro;
	
	private String inFimVidaCadastro;
	
	private String urlCadastro;
	
	private String nmGrupoProdutoCadastro;
	
	private String inDisponivelCadastro;
	
	private FormFile  fileMultimidia;
	
	

	public String getTipoProdutoPesquisa() {
		return tipoProdutoPesquisa;
	}

	public void setTipoProdutoPesquisa(String tipoProdutoPesquisa) {
		this.tipoProdutoPesquisa = tipoProdutoPesquisa;
	}

	public String getFabricantePesquisa() {
		return fabricantePesquisa;
	}

	public void setFabricantePesquisa(String fabricantePesquisa) {
		this.fabricantePesquisa = fabricantePesquisa;
	}

	public String getModeloPesquisa() {
		return modeloPesquisa;
	}

	public void setModeloPesquisa(String modeloPesquisa) {
		this.modeloPesquisa = modeloPesquisa;
	}

	public String getTecnologiaPesquisa() {
		return tecnologiaPesquisa;
	}

	public void setTecnologiaPesquisa(String tecnologiaPesquisa) {
		this.tecnologiaPesquisa = tecnologiaPesquisa;
	}

	public String getTipoProdutoCadastro() {
		return tipoProdutoCadastro;
	}

	public void setTipoProdutoCadastro(String tipoProdutoCadastro) {
		this.tipoProdutoCadastro = tipoProdutoCadastro;
	}

	public String getFabricanteCadastro() {
		return fabricanteCadastro;
	}

	public void setFabricanteCadastro(String fabricanteCadastro) {
		this.fabricanteCadastro = fabricanteCadastro;
	}

	public String getInFimVidaCadastro() {
		return inFimVidaCadastro;
	}

	public void setInFimVidaCadastro(String inFimVidaCadastro) {
		this.inFimVidaCadastro = inFimVidaCadastro;
	}

	public String getUrlCadastro() {
		return urlCadastro;
	}

	public void setUrlCadastro(String urlCadastro) {
		this.urlCadastro = urlCadastro;
	}

	public String getNmGrupoProdutoCadastro() {
		return nmGrupoProdutoCadastro;
	}

	public void setNmGrupoProdutoCadastro(String nmGrupoProdutoCadastro) {
		this.nmGrupoProdutoCadastro = nmGrupoProdutoCadastro;
	}

	public String getInDisponivelCadastro() {
		return inDisponivelCadastro;
	}

	public void setInDisponivelCadastro(String inDisponivelCadastro) {
		this.inDisponivelCadastro = inDisponivelCadastro;
	}

	public FormFile getFileMultimidia() {
		return fileMultimidia;
	}

	public void setFileMultimidia(FormFile fileMultimidia) {
		this.fileMultimidia = fileMultimidia;
	}

	
	
	
}
