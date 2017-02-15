package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@Table(name = "SRVINTERATIVIDADEPARAM", catalog = "", schema = "CATALOGOPRS_OW", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDSERVICOINTERATIVIDADE", "IDSRVINTERATIVIDADEPARAMBASE"})})
@NamedQueries({
    @NamedQuery(name = "SrvInteratividadeParam.findAll", query = "SELECT s FROM SrvInteratividadeParam s")})
@SequenceGenerator(name = "SRVINTERATIVIDADEPARAM_SQ", sequenceName = "CATALOGOPRS_OW.SRVINTERATIVIDADEPARAM_SQ", allocationSize = 1)
public class SrvInteratividadeParam implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public SrvInteratividadeParam() {}
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SRVINTERATIVIDADEPARAM_SQ")
    @Column(name = "IDSRVINTERATIVIDADEPARAM", nullable = false, precision = 22)
    private Integer idSrvInteratividadeParam;
    
    @Column(name = "VLPARAMETRO", nullable = false, length = 255)
    private String vlParametro;
    
    @JoinColumn(name = "IDSRVINTERATIVIDADEPARAMBASE", referencedColumnName = "IDSRVINTERATIVIDADEPARAMBASE", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    private SrvInteratividadeParamBase srvInteratividadeParamBase;
    
    @JoinColumn(name = "IDSERVICOINTERATIVIDADE", referencedColumnName = "IDSERVICOINTERATIVIDADE", nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    private ServicoInteratividade servicoInteratividade;

	public SrvInteratividadeParamBase getSrvInteratividadeParamBase() {
		return srvInteratividadeParamBase;
	}

	public Integer getIdSrvInteratividadeParam() {
		return idSrvInteratividadeParam;
	}

	public void setIdSrvInteratividadeParam(Integer idSrvInteratividadeParam) {
		this.idSrvInteratividadeParam = idSrvInteratividadeParam;
	}

	public void setSrvInteratividadeParamBase(SrvInteratividadeParamBase parametro) {
		this.srvInteratividadeParamBase = parametro;
	}

	public ServicoInteratividade getServicoInteratividade() {
		return servicoInteratividade;
	}

	public void setServicoInteratividade(ServicoInteratividade servicoInteratividade) {
		this.servicoInteratividade = servicoInteratividade;
	}

	public String getVlParametro() {
		return vlParametro;
	}

	public void setVlParametro(String vlParametro) {
		this.vlParametro = vlParametro;
	}
    
}
