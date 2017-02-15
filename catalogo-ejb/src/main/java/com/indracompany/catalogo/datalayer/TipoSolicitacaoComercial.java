package com.indracompany.catalogo.datalayer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOSOLICITACAOCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "TIPOSOLICITACAOCOMERCIAL_SQ", sequenceName = "CATALOGOPRS_OW.TIPOSOLICITACAOCOMERCIAL_SQ", allocationSize = 1)
@NamedQuery(name = "TipoSolicitacaoComercial.findAll", query = "select tsc from TipoSolicitacaoComercial tsc")
public class TipoSolicitacaoComercial {
	
	@Id
	@Column(name = "IDTIPOSOLICITACAOCOMERCIAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOSOLICITACAOCOMERCIAL_SQ")
	private Long idTipoSolicitacaoComercial;
	
	@Column(name = "CDTIPOSOLICITACAOCOMERCIAL")
	private String cdTipoSolicitacaoComercial;

	@Column(name = "NMTIPOSOLICITACAOCOMERCIAL")
	private String nmTipoSolicitacaoComercial;

	public String getCdTipoSolicitacaoComercial() {
		return cdTipoSolicitacaoComercial;
	}

	public void setCdTipoSolicitacaoComercial(String cdTipoSolicitacaoComercial) {
		this.cdTipoSolicitacaoComercial = cdTipoSolicitacaoComercial;
	}

	public Long getIdTipoSolicitacaoComercial() {
		return idTipoSolicitacaoComercial;
	}

	public void setIdTipoSolicitacaoComercial(Long idTipoSolicitacaoComercial) {
		this.idTipoSolicitacaoComercial = idTipoSolicitacaoComercial;
	}

	public String getNmTipoSolicitacaoComercial() {
		return nmTipoSolicitacaoComercial;
	}

	public void setNmTipoSolicitacaoComercial(String nmTipoSolicitacaoComercial) {
		this.nmTipoSolicitacaoComercial = nmTipoSolicitacaoComercial;
	}


}
