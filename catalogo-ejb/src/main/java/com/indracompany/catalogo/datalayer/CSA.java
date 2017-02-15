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
@Table(name="CSA", schema = "CATALOGOPRS_OW")
public class CSA implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CSA() {}
	
	@Id
	@Column(name = "IDCSA")
	private Integer idCSA;

	@Column(name = "CDSIGAN")
	private String cdSigan;

	@Column(name = "DSCCSA")
	private String dsCCSA;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMCSA")
	private String nmCSA;

    @Column(name = "NMCSAAVO")
	private String nmCsaAvo;

    @Column(name = "NMCSAMASTER")
	private String nmCsaMaster;

    @Column(name = "NMCSAPAI")
	private String nmCsaPai;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Comboofertacsa
	@OneToMany(mappedBy="csa")
	private List<ComboOfertaCSA> comboOfertaCSAList;

	//bi-directional many-to-one association to Arearegistro
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDAREAREGISTRO")
	private AreaRegistro areaRegistro;

	//bi-directional many-to-one association to Disponibilidadeservico
	@OneToMany(mappedBy="csa")
	private List<DisponibilidadeServico> disponibilidadeServicoList;

	//bi-directional many-to-one association to Matrizofertatipocontrato
	@OneToMany(mappedBy="csa")
	private List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList;

	//bi-directional many-to-one association to Planocsa
	@OneToMany(mappedBy="csa")
	private List<PlanoCSA> planoCSAList;

	//bi-directional many-to-one association to Servicocsa
	@OneToMany(mappedBy="csa")
	private List<ServicoCSA> servicoCSAList;

	//bi-directional many-to-one association to Servicotarifa
	@OneToMany(mappedBy="csa")
	private List<ServicoTarifa> servicoTarifaList;

	public AreaRegistro getAreaRegistro() {
		return areaRegistro;
	}

	public void setAreaRegistro(AreaRegistro areaRegistro) {
		this.areaRegistro = areaRegistro;
	}

	public String getCdSigan() {
		return cdSigan;
	}

	public void setCdSigan(String cdSigan) {
		this.cdSigan = cdSigan;
	}

	public List<ComboOfertaCSA> getComboOfertaCSAList() {
		return comboOfertaCSAList;
	}

	public void setComboOfertaCSAList(List<ComboOfertaCSA> comboOfertaCSAList) {
		this.comboOfertaCSAList = comboOfertaCSAList;
	}

	public List<DisponibilidadeServico> getDisponibilidadeServicoList() {
		return disponibilidadeServicoList;
	}

	public void setDisponibilidadeServicoList(
			List<DisponibilidadeServico> disponibilidadeServicoList) {
		this.disponibilidadeServicoList = disponibilidadeServicoList;
	}

	public String getDsCCSA() {
		return dsCCSA;
	}

	public void setDsCCSA(String dsCCSA) {
		this.dsCCSA = dsCCSA;
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

	public Integer getIdCSA() {
		return idCSA;
	}

	public void setIdCSA(Integer idCSA) {
		this.idCSA = idCSA;
	}

	public List<MatrizOfertaTipoContrato> getMatrizOfertaTipoContratoList() {
		return matrizOfertaTipoContratoList;
	}

	public void setMatrizOfertaTipoContratoList(
			List<MatrizOfertaTipoContrato> matrizOfertaTipoContratoList) {
		this.matrizOfertaTipoContratoList = matrizOfertaTipoContratoList;
	}

	public String getNmCSA() {
		return nmCSA;
	}

	public void setNmCSA(String nmCSA) {
		this.nmCSA = nmCSA;
	}

	public String getNmCsaAvo() {
		return nmCsaAvo;
	}

	public void setNmCsaAvo(String nmCsaAvo) {
		this.nmCsaAvo = nmCsaAvo;
	}

	public String getNmCsaMaster() {
		return nmCsaMaster;
	}

	public void setNmCsaMaster(String nmCsaMaster) {
		this.nmCsaMaster = nmCsaMaster;
	}

	public String getNmCsaPai() {
		return nmCsaPai;
	}

	public void setNmCsaPai(String nmCsaPai) {
		this.nmCsaPai = nmCsaPai;
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

	public List<PlanoCSA> getPlanoCSAList() {
		return planoCSAList;
	}

	public void setPlanoCSAList(List<PlanoCSA> planoCSAList) {
		this.planoCSAList = planoCSAList;
	}

	public List<ServicoCSA> getServicoCSAList() {
		return servicoCSAList;
	}

	public void setServicoCSAList(List<ServicoCSA> servicoCSAList) {
		this.servicoCSAList = servicoCSAList;
	}

	public List<ServicoTarifa> getServicoTarifaList() {
		return servicoTarifaList;
	}

	public void setServicoTarifaList(List<ServicoTarifa> servicoTarifaList) {
		this.servicoTarifaList = servicoTarifaList;
	}
}