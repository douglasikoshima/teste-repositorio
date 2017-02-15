package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import org.apache.struts.validator.ValidatorActionForm;

public class CadastroCorForm extends ValidatorActionForm implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private String url;
	private Integer idCor;
	private String rgb;
	private String nmCor;
	private String rgbDisplay;
	
	public String getRgbDisplay() {
		return rgbDisplay;
	}
	public void setRgbDisplay(String rgbDisplay) {
		this.rgbDisplay = rgbDisplay;
	}
	public Integer getIdCor() {
		return idCor;
	}
	public void setIdCor(Integer idCor) {
		this.idCor = idCor;
	}
	public String getNmCor() {
		return nmCor;
	}
	public void setNmCor(String nmCor) {
		this.nmCor = nmCor;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getRgb() {
		return rgb;
	}
	public void setRgb(String rgb) {
		this.rgb = rgb;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}