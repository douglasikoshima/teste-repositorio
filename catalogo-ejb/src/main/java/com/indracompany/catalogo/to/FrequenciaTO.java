package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class FrequenciaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public FrequenciaTO() {}
	
	public FrequenciaTO(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}
	
	private Integer idVlFrequencia;
	private Integer vlFrequencia;
	
	public Integer getIdVlFrequencia() {
		return idVlFrequencia;
	}

	public void setIdVlFrequencia(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}

	public Integer getVlFrequencia() {
		return vlFrequencia;
	}

	public void setVlFrequencia(Integer vlFrequencia) {
		this.vlFrequencia = vlFrequencia;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idVlFrequencia: " + this.idVlFrequencia, "vlFrequencia: " + this.vlFrequencia}, ", ");
	}
}
