package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="COMBOOFERTA", schema = "CATALOGOPRS_OW")
public class ComboOferta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ComboOferta() {}
	
	@Id
	@Column(name = "IDCOMBOOFERTA", unique=true, nullable=false, precision=22)
	private Integer idComboOferta;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMCOMBOOFERTA")
	private String nmComboOferta;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Comboofertaarearegistro
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaAreaRegistro> comboOfertaAreaRegistroList;

	//bi-directional many-to-one association to Comboofertacanal
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaCanal> comboOfertaCanaList;

	//bi-directional many-to-one association to Comboofertacarteira
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaCarteira> comboOfertaCarteiraList;

	//bi-directional many-to-one association to Comboofertacsa
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaCSA> comboOfertaCSAList;

	//bi-directional many-to-one association to Comboofertaplanoservico
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaPlanoServico> comboOfertaPlanoServicoList;

	//bi-directional many-to-one association to Comboofertaplataforma
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaPlataforma> comboOfertaPlataformaList;

	//bi-directional many-to-one association to Comboofertasegmento
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaSegmento> comboOfertaSegmentoList;

	//bi-directional many-to-one association to Comboofertatipocliente
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaTipoCliente> comboOfertaTipoClienteList;

	//bi-directional many-to-one association to Comboofertauf
	@OneToMany(mappedBy="comboOferta")
	private List<ComboOfertaUF> comboOfertaUFList;

	public List<ComboOfertaAreaRegistro> getComboOfertaAreaRegistroList() {
		return comboOfertaAreaRegistroList;
	}

	public void setComboOfertaAreaRegistroList(
			List<ComboOfertaAreaRegistro> comboOfertaAreaRegistroList) {
		this.comboOfertaAreaRegistroList = comboOfertaAreaRegistroList;
	}

	public List<ComboOfertaCanal> getComboOfertaCanaList() {
		return comboOfertaCanaList;
	}

	public void setComboOfertaCanaList(List<ComboOfertaCanal> comboOfertaCanaList) {
		this.comboOfertaCanaList = comboOfertaCanaList;
	}

	public List<ComboOfertaCarteira> getComboOfertaCarteiraList() {
		return comboOfertaCarteiraList;
	}

	public void setComboOfertaCarteiraList(
			List<ComboOfertaCarteira> comboOfertaCarteiraList) {
		this.comboOfertaCarteiraList = comboOfertaCarteiraList;
	}

	public List<ComboOfertaCSA> getComboOfertaCSAList() {
		return comboOfertaCSAList;
	}

	public void setComboOfertaCSAList(List<ComboOfertaCSA> comboOfertaCSAList) {
		this.comboOfertaCSAList = comboOfertaCSAList;
	}

	public List<ComboOfertaPlanoServico> getComboOfertaPlanoServicoList() {
		return comboOfertaPlanoServicoList;
	}

	public void setComboOfertaPlanoServicoList(
			List<ComboOfertaPlanoServico> comboOfertaPlanoServicoList) {
		this.comboOfertaPlanoServicoList = comboOfertaPlanoServicoList;
	}

	public List<ComboOfertaPlataforma> getComboOfertaPlataformaList() {
		return comboOfertaPlataformaList;
	}

	public void setComboOfertaPlataformaList(
			List<ComboOfertaPlataforma> comboOfertaPlataformaList) {
		this.comboOfertaPlataformaList = comboOfertaPlataformaList;
	}

	public List<ComboOfertaSegmento> getComboOfertaSegmentoList() {
		return comboOfertaSegmentoList;
	}

	public void setComboOfertaSegmentoList(
			List<ComboOfertaSegmento> comboOfertaSegmentoList) {
		this.comboOfertaSegmentoList = comboOfertaSegmentoList;
	}

	public List<ComboOfertaTipoCliente> getComboOfertaTipoClienteList() {
		return comboOfertaTipoClienteList;
	}

	public void setComboOfertaTipoClienteList(
			List<ComboOfertaTipoCliente> comboOfertaTipoClienteList) {
		this.comboOfertaTipoClienteList = comboOfertaTipoClienteList;
	}

	public List<ComboOfertaUF> getComboOfertaUFList() {
		return comboOfertaUFList;
	}

	public void setComboOfertaUFList(List<ComboOfertaUF> comboOfertaUFList) {
		this.comboOfertaUFList = comboOfertaUFList;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdComboOferta() {
		return idComboOferta;
	}

	public void setIdComboOferta(Integer idComboOferta) {
		this.idComboOferta = idComboOferta;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmComboOferta() {
		return nmComboOferta;
	}

	public void setNmComboOferta(String nmComboOferta) {
		this.nmComboOferta = nmComboOferta;
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
}