package br.com.vivo.catalogoPRS.bo;

import java.io.Serializable;

public class DadosCookie implements Serializable {

	private static final long serialVersionUID = 1L;

	private String ipCliente;
	private String hostnameCliente;
	private String idSessaoCliente;
	public String getHostnameCliente() {
		return hostnameCliente;
	}
	public void setHostnameCliente(String hostnameCliente) {
		this.hostnameCliente = hostnameCliente;
	}
	public String getIdSessaoCliente() {
		return idSessaoCliente;
	}
	public void setIdSessaoCliente(String idSessaoCliente) {
		this.idSessaoCliente = idSessaoCliente;
	}
	public String getIpCliente() {
		return ipCliente;
	}
	public void setIpCliente(String ipCliente) {
		this.ipCliente = ipCliente;
	}

}
