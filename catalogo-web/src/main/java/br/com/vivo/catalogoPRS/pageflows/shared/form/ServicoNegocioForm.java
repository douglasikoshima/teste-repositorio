package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean.ServicoNegocioFormBean;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;

public class ServicoNegocioForm extends ValidatorActionForm implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long cdServicoNegocio;
	private String dsServicoNegocio;
	private String pageNumber;
	private List <ServicoNegocioVO> listaServicoNegocio;
	
	private ServicoNegocioFormBean servicoNegocioFormBean;

	public void setCdServicoNegocio(Long cdServicoNegocio) {
		this.cdServicoNegocio = cdServicoNegocio;
	}
	public Long getCdServicoNegocio() {
		return cdServicoNegocio;
	}
	public void setDsServicoNegocio(String dsServicoNegocio) {
		this.dsServicoNegocio = dsServicoNegocio;
	}
	public String getDsServicoNegocio() {
		return dsServicoNegocio;
	}
	public String getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	public ServicoNegocioFormBean getServicoNegocioFormBean() {
		return servicoNegocioFormBean;
	}
	public void setServicoNegocioFormBean(ServicoNegocioFormBean servicoNegocioFormBean) {
		this.servicoNegocioFormBean = servicoNegocioFormBean;
	}
	public List<ServicoNegocioVO> getListaServicoNegocio() {
		return listaServicoNegocio;
	}
	public void setListaServicoNegocio(List<ServicoNegocioVO> listaServicoNegocio) {
		this.listaServicoNegocio = listaServicoNegocio;
	}
	
}