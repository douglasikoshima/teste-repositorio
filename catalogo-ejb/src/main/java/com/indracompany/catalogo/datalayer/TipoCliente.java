package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@NamedQuery(name = "TipoCliente.findAll", query = "SELECT tc FROM TipoCliente tc ")
@Table(name="TIPOCLIENTE", schema = "CATALOGOPRS_OW")
public class TipoCliente implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public TipoCliente() {}
	
	public TipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	
	@Id
	@Column(name = "IDTIPOCLIENTE")
	private Integer idTipoCliente;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMTIPOCLIENTE")
	private String nmTipoCliente;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Comboofertatipocliente
	@OneToMany(mappedBy="tipoCliente")
	private List<ComboOfertaTipoCliente> comboOfertaTipoClienteList;

	//bi-directional many-to-one association to Planorestricoe
	@OneToMany(mappedBy="tipoCliente")
	private List<PlanoRestricoes> planoRestricoesList;

	//bi-directional many-to-one association to Servicorestricoe
	@OneToMany(mappedBy="tipoCliente")
	private List<ServicoRestricoes> servicoRestricoesList;

	public List<ComboOfertaTipoCliente> getComboOfertaTipoClienteList() {
		return comboOfertaTipoClienteList;
	}

	public void setComboOfertaTipoClienteList(
			List<ComboOfertaTipoCliente> comboOfertaTipoClienteList) {
		this.comboOfertaTipoClienteList = comboOfertaTipoClienteList;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getNmTipoCliente() {
		return nmTipoCliente;
	}

	public void setNmTipoCliente(String nmTipoCliente) {
		this.nmTipoCliente = nmTipoCliente;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public List<PlanoRestricoes> getPlanoRestricoesList() {
		return planoRestricoesList;
	}

	public void setPlanoRestricoesList(List<PlanoRestricoes> planoRestricoesList) {
		this.planoRestricoesList = planoRestricoesList;
	}

	public List<ServicoRestricoes> getServicoRestricoesList() {
		return servicoRestricoesList;
	}

	public void setServicoRestricoesList(
			List<ServicoRestricoes> servicoRestricoesList) {
		this.servicoRestricoesList = servicoRestricoesList;
	}
}