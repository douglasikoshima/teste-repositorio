package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean.SistemaLegadoFormBean;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.SistemaLegadoVO;

public class SistemaLegadoForm extends ValidatorActionForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long cdSistemaLegado;
	
	private String dsSistemaLegado;

	private String nmAplicacaoHeader;
	
	private String pageNumber;
	
	private SistemaLegadoFormBean sistemaLegadoFormBean;

	private List<SistemaLegadoVO> listaSistemaLegado;
	
	public List<SistemaLegadoVO> getListaSistemaLegado() {
		return listaSistemaLegado;
	}

	public void setListaSistemaLegado(List<SistemaLegadoVO> listaSistemaLegado) {
		this.listaSistemaLegado = listaSistemaLegado;
	}
	
	public Long getCdSistemaLegado() {
		return cdSistemaLegado;
	}

	public void setCdSistemaLegado(Long cdSistemaLegado) {
		this.cdSistemaLegado = cdSistemaLegado;
	}

	public String getDsSistemaLegado() {
		return dsSistemaLegado;
	}

	public void setDsSistemaLegado(String dsSistemaLegado) {
		this.dsSistemaLegado = dsSistemaLegado;
	}

	public String getNmAplicacaoHeader() {
		return nmAplicacaoHeader;
	}

	public void setNmAplicacaoHeader(String nmAplicacaoHeader) {
		this.nmAplicacaoHeader = nmAplicacaoHeader;
	}

	public SistemaLegadoFormBean getSistemaLegadoFormBean() {
		return sistemaLegadoFormBean;
	}

	public void setSistemaLegadoFormBean(SistemaLegadoFormBean sisteLegadoFormBean) {
		this.sistemaLegadoFormBean = sisteLegadoFormBean;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	
}
