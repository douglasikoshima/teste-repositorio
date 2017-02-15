package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

/**
 * VO de SistemaLegado
 */
public class SistemaLegadoVO extends ValueObject {
	private static final long serialVersionUID = 1L;

	/**
	 * CdSistemaLegado
	 */
	private Long cdSistemaLegado;
	
	/**
	 * DsSistemaLegado
	 */
	private String dsSistemaLegado;

	private String nmAplicacaoHeader;
	
	
	public String getNmAplicacaoHeader() {
		return nmAplicacaoHeader;
	}

	public void setNmAplicacaoHeader(String nmAplicacaoHeader) {
		this.nmAplicacaoHeader = nmAplicacaoHeader;
	}

	/**
	 * @param id
	 */
	public SistemaLegadoVO(String id) {
		super(id);
	}

	public SistemaLegadoVO() {
		super();
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;

		if ((id != null) && !id.trim().equals("")) {
			setCdSistemaLegado(new Long(Integer.parseInt(id)));
		}
	}

	public void setId() {
		this.id = getCdSistemaLegado() + "";
	}
	
	/**
	 * Sets the value for CdServicoNegocio
	 */
	public void setCdSistemaLegado(Long cdSistemaLegado) {
		this.cdSistemaLegado = cdSistemaLegado;
	}
	
	/**
	 * Gets the value for CdServicoNegocio
	 */
	public Long getCdSistemaLegado() {
		return cdSistemaLegado;
	}
	
	/**
	 * Sets the value for DsServicoNegocio
	 */
	public void setDsSistemaLegado(String dsSistemaLegado) {
		this.dsSistemaLegado = dsSistemaLegado;
	}

	/**
	 * Gets the value for DsServicoNegocio
	 */
	public String getDsSistemaLegado() {
		return dsSistemaLegado;
	}

}

	
	
	
	
	