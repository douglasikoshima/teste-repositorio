package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class SrvInteratividadeParamBaseTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SrvInteratividadeParamBaseTO() {}
	
	public SrvInteratividadeParamBaseTO(Integer idParametro) {
		this.idSrvInteratividadeParamBase = idParametro;
	}
	
    private Integer idSrvInteratividadeParamBase;
    private String nmSrvInteratividadeParamBase;
    private String tpSrvInteratividadeParamBase;
    private String vlDefaultParam;
    private String dsSrvInteratividadeParamBase;
    private String indVisivel;

    public String getDsSrvInteratividadeParamBase() {
		return dsSrvInteratividadeParamBase;
	}

	public void setDsSrvInteratividadeParamBase(String dsParametro) {
		this.dsSrvInteratividadeParamBase = dsParametro;
	}

	public Integer getIdSrvInteratividadeParamBase() {
		return idSrvInteratividadeParamBase;
	}

	public void setIdSrvInteratividadeParamBase(Integer idParametro) {
		this.idSrvInteratividadeParamBase = idParametro;
	}

	public String getIndVisivel() {
		return indVisivel;
	}

	public void setIndVisivel(String indVisivel) {
		this.indVisivel = indVisivel;
	}

	public String getNmSrvInteratividadeParamBase() {
		return nmSrvInteratividadeParamBase;
	}

	public void setNmSrvInteratividadeParamBase(String nmParametro) {
		this.nmSrvInteratividadeParamBase = nmParametro;
	}

	public String getTpSrvInteratividadeParamBase() {
		return tpSrvInteratividadeParamBase;
	}

	public void setTpSrvInteratividadeParamBase(String tpParametro) {
		this.tpSrvInteratividadeParamBase = tpParametro;
	}

	public String getVlDefaultParam() {
		return vlDefaultParam;
	}

	public void setVlDefaultParam(String vlDefaultParametro) {
		this.vlDefaultParam = vlDefaultParametro;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idParametro: " + this.idSrvInteratividadeParamBase, "nmParametro: " + this.nmSrvInteratividadeParamBase}, ", ");
	}
}
