package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorActionForm;

public class GrupoCaracteristicaForm extends ValidatorActionForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String caracteristica;
	private String nomeIcone;
	private FormFile icone;
	private Integer idGrupoCaracteristica;
	
	private String caracteristicaAssociada;
	
	public String getNomeIcone() {
		return nomeIcone;
	}
	public void setNomeIcone(String nomeIcone) {
		this.nomeIcone = nomeIcone;
	}
	public Integer getIdGrupoCaracteristica() {
		return idGrupoCaracteristica;
	}
	public void setIdGrupoCaracteristica(Integer idGrupoCaracteristica) {
		this.idGrupoCaracteristica = idGrupoCaracteristica;
	}
	public FormFile getIcone() {
		return icone;
	}
	public void setIcone(FormFile icone) {
		this.icone = icone;
	}
	public String getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = caracteristica;
	}
	public String getCaracteristicaAssociada() {
		return caracteristicaAssociada;
	}
	public void setCaracteristicaAssociada(String caracteristicaAssociada) {
		this.caracteristicaAssociada = caracteristicaAssociada;
	}
	

}
