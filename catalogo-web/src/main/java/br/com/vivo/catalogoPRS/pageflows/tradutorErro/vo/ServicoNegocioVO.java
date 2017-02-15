package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

/**
 * VO de ServicoNegocio
 */
public class ServicoNegocioVO extends ValueObject {
	private static final long serialVersionUID = 1L;

	/**
	 * CdServicoNegocio
	 */
	private Long cdServicoNegocio;

	/**
	 * DsServicoNegocio
	 */
	private String dsServicoNegocio;

	/**
	 * @param id
	 */
	public ServicoNegocioVO(String id) {
		super(id);
	}

	public ServicoNegocioVO() {
		super();
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;

		if ((id != null) && !id.trim().equals("")) {
			setCdServicoNegocio(new Long(Integer.parseInt(id)));
		}
	}

	public void setId() {
		this.id = getCdServicoNegocio() + "";
	}

	/**
	 * Sets the value for CdServicoNegocio
	 */
	public void setCdServicoNegocio(Long cdServicoNegocio) {
		this.cdServicoNegocio = cdServicoNegocio;
	}

	/**
	 * Gets the value for CdServicoNegocio
	 */
	public Long getCdServicoNegocio() {
		return cdServicoNegocio;
	}

	/**
	 * Sets the value for DsServicoNegocio
	 */
	public void setDsServicoNegocio(String dsServicoNegocio) {
		this.dsServicoNegocio = dsServicoNegocio;
	}

	/**
	 * Gets the value for DsServicoNegocio
	 */
	public String getDsServicoNegocio() {
		return dsServicoNegocio;
	}

}
