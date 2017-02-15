package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="AREAREGISTROORGVND", schema = "CATALOGOPRS_OW")
public class AreaRegistroOrgVnd implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AreaRegistroOrgVnd() {}
	
	@Id
	@Column(name = "IDAREAREGISTROORGVND")
	private Integer idAreaRegistroOrgVnd;

	//bi-directional many-to-one association to Arearegistro
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDAREAREGISTRO", nullable=false)
	private AreaRegistro areaRegistro;

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS", nullable=false)
	private OrganizacaoVenda organizacaoVenda;

	public AreaRegistro getAreaRegistro() {
		return areaRegistro;
	}

	public void setAreaRegistro(AreaRegistro areaRegistro) {
		this.areaRegistro = areaRegistro;
	}

	public Integer getIdAreaRegistroOrgVnd() {
		return idAreaRegistroOrgVnd;
	}

	public void setIdAreaRegistroOrgVnd(Integer idAreaRegistroOrgVnd) {
		this.idAreaRegistroOrgVnd = idAreaRegistroOrgVnd;
	}

	public OrganizacaoVenda getOrganizacaoVenda() {
		return organizacaoVenda;
	}

	public void setOrganizacaoVenda(OrganizacaoVenda organizacaoVenda) {
		this.organizacaoVenda = organizacaoVenda;
	}
}