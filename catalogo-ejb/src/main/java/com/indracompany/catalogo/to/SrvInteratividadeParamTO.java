package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class SrvInteratividadeParamTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SrvInteratividadeParamTO() {}
	
	public SrvInteratividadeParamTO(Integer idSrvInteratividadeParametro) {
		this.idSrvInteratividadeParam = idSrvInteratividadeParametro;
	}
	
	private Integer idSrvInteratividadeParam;
    private String vlParametro;
    private SrvInteratividadeParamBaseTO parametroTO;
    private ServicoInteratividadeTO servicoInteratividadeTO;

	public Integer getIdSrvInteratividadeParam() {
		return idSrvInteratividadeParam;
	}

	public void setIdSrvInteratividadeParam(Integer idSrvInteratividadeParametro) {
		this.idSrvInteratividadeParam = idSrvInteratividadeParametro;
	}

	public SrvInteratividadeParamBaseTO getParametroTO() {
		return parametroTO;
	}

	public void setParametroTO(SrvInteratividadeParamBaseTO parametroTO) {
		this.parametroTO = parametroTO;
	}

	public ServicoInteratividadeTO getServicoInteratividadeTO() {
		return servicoInteratividadeTO;
	}

	public void setServicoInteratividadeTO(
			ServicoInteratividadeTO servicoInteratividadeTO) {
		this.servicoInteratividadeTO = servicoInteratividadeTO;
	}

	public String getVlParametro() {
		return vlParametro;
	}

	public void setVlParametro(String vlParametro) {
		this.vlParametro = vlParametro;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idSrvInteratividadeParametro: " + this.idSrvInteratividadeParam, "vlParametro: " + this.vlParametro}, ", ");
	}
}
