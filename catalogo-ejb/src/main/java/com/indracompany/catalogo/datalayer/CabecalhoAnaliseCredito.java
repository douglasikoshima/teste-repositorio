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
@SequenceGenerator(name = "CABECALHOANALISECREDITO_SQ", sequenceName = "CATALOGOPRS_OW.CABECALHOANALISECREDITO_SQ", allocationSize = 1)
@Table(name="CABECALHOANALISECREDITO", schema = "CATALOGOPRS_OW")
@NamedQuery(name = "CabecalhoAnaliseCredito.findAll", query = "SELECT cac FROM CabecalhoAnaliseCredito cac ")
public class CabecalhoAnaliseCredito implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CabecalhoAnaliseCredito() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CABECALHOANALISECREDITO_SQ")
	@Column(name = "IDCABECALHOANALISECREDITO")
	private Integer idCabecalhoAnaliseCredito;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMCABECALHOANALISECREDITO")
	private String nmCabecalhoAnaliseCredito;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Canalatendimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANALATENDIMENTO", nullable=false)
	private CanalAtendimento canalAtendimento;

	//bi-directional many-to-one association to Ofservicoconfiguracaoscore
	@OneToMany(mappedBy="cabecalhoAnaliseCredito")
	private List<OfServicoConfiguracaoScore> ofServicoConfiguracaoScoreList;

	//bi-directional many-to-one association to Planoconfiguracaoscore
	@OneToMany(mappedBy="cabecalhoAnaliseCredito")
	private List<PlanoConfiguracaoScore> planoConfiguracaoScoreList;

	//bi-directional many-to-one association to Servicoconfiguracaoscore
	@OneToMany(mappedBy="cabecalhoAnaliseCredito")
	private List<ServicoConfiguracaoScore> servicoConfiguracaoScoreList;

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
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

	public Integer getIdCabecalhoAnaliseCredito() {
		return idCabecalhoAnaliseCredito;
	}

	public void setIdCabecalhoAnaliseCredito(Integer idCabecalhoAnaliseCredito) {
		this.idCabecalhoAnaliseCredito = idCabecalhoAnaliseCredito;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmCabecalhoAnaliseCredito() {
		return nmCabecalhoAnaliseCredito;
	}

	public void setNmCabecalhoAnaliseCredito(String nmCabecalhoAnaliseCredito) {
		this.nmCabecalhoAnaliseCredito = nmCabecalhoAnaliseCredito;
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

	public List<OfServicoConfiguracaoScore> getOfServicoConfiguracaoScoreList() {
		return ofServicoConfiguracaoScoreList;
	}

	public void setOfServicoConfiguracaoScoreList(
			List<OfServicoConfiguracaoScore> ofServicoConfiguracaoScoreList) {
		this.ofServicoConfiguracaoScoreList = ofServicoConfiguracaoScoreList;
	}

	public List<PlanoConfiguracaoScore> getPlanoConfiguracaoScoreList() {
		return planoConfiguracaoScoreList;
	}

	public void setPlanoConfiguracaoScoreList(
			List<PlanoConfiguracaoScore> planoConfiguracaoScoreList) {
		this.planoConfiguracaoScoreList = planoConfiguracaoScoreList;
	}

	public List<ServicoConfiguracaoScore> getServicoConfiguracaoScoreList() {
		return servicoConfiguracaoScoreList;
	}

	public void setServicoConfiguracaoScoreList(
			List<ServicoConfiguracaoScore> servicoConfiguracaoScoreList) {
		this.servicoConfiguracaoScoreList = servicoConfiguracaoScoreList;
	}
}