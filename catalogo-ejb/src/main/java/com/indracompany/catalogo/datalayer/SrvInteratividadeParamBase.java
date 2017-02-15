package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@Table(name = "SRVINTERATIVIDADEPARAMBASE", catalog = "", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "SrvInteratividadeParamBase.findAll", query = "SELECT p FROM SrvInteratividadeParamBase p where p.indVisivel = 'S' ")})
public class SrvInteratividadeParamBase implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public SrvInteratividadeParamBase() {}
    
    public SrvInteratividadeParamBase(Integer idParametro) {
        this.idSrvInteratividadeParamBase = idParametro;
    }

    public SrvInteratividadeParamBase(Integer idParametro, String nmParametro, String tpParametro, String vlDefaultParametro, String dsParametro, String indVisivel) {
        this.idSrvInteratividadeParamBase = idParametro;
        this.nmSrvInteratividadeParamBase = nmParametro;
        this.tpSrvInteratividadeParamBase = tpParametro;
        this.vlDefaultParam = vlDefaultParametro;
        this.dsSrvInteratividadeParamBase = dsParametro;
        this.indVisivel = indVisivel;
    }
    
    @Id
    @Column(name = "IDSRVINTERATIVIDADEPARAMBASE", nullable = false, precision = 22)
    private Integer idSrvInteratividadeParamBase;
    
    @Column(name = "NMSRVINTERATIVIDADEPARAMBASE", nullable = false, length = 255)
    private String nmSrvInteratividadeParamBase;
    
    @Column(name = "TPSRVINTERATIVIDADEPARAMBASE", nullable = false, length = 255)
    private String tpSrvInteratividadeParamBase;
    
    @Column(name = "VLDEFAULTPARAM", nullable = false, length = 255)
    private String vlDefaultParam;
    
    @Column(name = "DSSRVINTERATIVIDADEPARAMBASE", nullable = false, length = 255)
    private String dsSrvInteratividadeParamBase;
    
    @Column(name = "INDVISIVEL", nullable = false, length = 1)
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
}
