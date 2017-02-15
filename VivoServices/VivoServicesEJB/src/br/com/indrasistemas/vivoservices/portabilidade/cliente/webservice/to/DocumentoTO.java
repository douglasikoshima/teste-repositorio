package br.com.indrasistemas.vivoservices.portabilidade.cliente.webservice.to;

public class DocumentoTO {

	public DocumentoTO() {
	}

	private static final long serialVersionUID = -25487197893232238L;

	private String dsTipoDocumento;
	private String nrDocumento;
	private String dtExpedicao;
	private String dsOrgaoEmissor;
	private String sgUFDocumento;

	public String getDsTipoDocumento() {
		return dsTipoDocumento;
	}

	public void setDsTipoDocumento(String dsTipoDocumento) {
		this.dsTipoDocumento = dsTipoDocumento;
	}

	public String getNrDocumento() {
		return nrDocumento;
	}

	public void setNrDocumento(String nrDocumento) {
		this.nrDocumento = nrDocumento;
	}

	public String getDtExpedicao() {
		return dtExpedicao;
	}

	public void setDtExpedicao(String dtExpedicao) {
		this.dtExpedicao = dtExpedicao;
	}

	public String getDsOrgaoEmissor() {
		return dsOrgaoEmissor;
	}

	public void setDsOrgaoEmissor(String dsOrgaoEmissor) {
		this.dsOrgaoEmissor = dsOrgaoEmissor;
	}

	public String getSgUFDocumento() {
		return sgUFDocumento;
	}

	public void setSgUFDocumento(String sgUFDocumento) {
		this.sgUFDocumento = sgUFDocumento;
	}
}
