package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

/**
 * VO de Servico
 */
public class ServicoVO extends ValueObject {
	private static final long serialVersionUID = 1L;

	/**
	 * CdServico
	 */
	private Long cdServico;

	private Long sistemaLegado;
	
	/**
	 * DsServico
	 */
	private String dsServico;

	/**
	 * @param id
	 */
	public ServicoVO(String id) {
		super(id);
	}
	
	public ServicoVO() {
		super();
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;

		if ((id != null) && !id.trim().equals("")) {
			setCdServico(new Long(Integer.parseInt(id)));
		}
	}

	public void setId() {
		this.id = getCdServico() + "";
	}

	/**
	 * Sets the value for CdServico
	 */
	public void setCdServico(Long cdServico) {
		this.cdServico = cdServico;
	}

	/**
	 * Gets the value for CdServico
	 */
	public Long getCdServico() {
		return cdServico;
	}
	
	/**
	 * Sets the value for DsServico
	 */
	public void setDsServico(String dsServico) {
		this.dsServico = dsServico;
	}
	
	/**
	 * Gets the value for DsServico
	 */
	public String getDsServico() {
		return dsServico;
	}

	public Long getSistemaLegado() {
		return sistemaLegado;
	}

	public void setSistemaLegado(Long sistemaLegado) {
		this.sistemaLegado = sistemaLegado;
	}
	
}
	
	
