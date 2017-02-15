package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "SERVICOCONFIGURACAOSCORE_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOCONFIGURACAOSCORE_SQ", allocationSize = 1)
@Table(name="SERVICOCONFIGURACAOSCORE", schema = "CATALOGOPRS_OW")
public class ServicoConfiguracaoScore implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoConfiguracaoScore() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOCONFIGURACAOSCORE_SQ")
	@Column(name = "IDSERVICOCONFIGURACAOSCORE")
	private Integer idServicoConfiguracaoScore;

    @Temporal( TemporalType.DATE)
	@Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Analisecredito
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDANALISECREDITO", nullable=false)
	private AnaliseCredito analiseCredito;

	//bi-directional many-to-one association to Cabecalhoanalisecredito
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCABECALHOANALISECREDITO", nullable=false)
	private CabecalhoAnaliseCredito cabecalhoAnaliseCredito;

	//bi-directional many-to-one association to Servicocategoriascore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDSERVICOCATEGORIASCORE", nullable=false)
	private ServicoCategoriaScore servicoCategoriaScore;
	
	//bi-directional many-to-one association to Regional
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDREGIONAL", nullable=true)
	private Regional regional;	

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
	}

	public AnaliseCredito getAnaliseCredito() {
		return analiseCredito;
	}
	
	public void setAnaliseCredito(AnaliseCredito analiseCredito) {
		this.analiseCredito = analiseCredito;
	}

	public CabecalhoAnaliseCredito getCabecalhoAnaliseCredito() {
		return cabecalhoAnaliseCredito;
	}

	public void setCabecalhoAnaliseCredito(
			CabecalhoAnaliseCredito cabecalhoAnaliseCredito) {
		this.cabecalhoAnaliseCredito = cabecalhoAnaliseCredito;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdServicoConfiguracaoScore() {
		return idServicoConfiguracaoScore;
	}

	public void setIdServicoConfiguracaoScore(Integer idServicoConfiguracaoScore) {
		this.idServicoConfiguracaoScore = idServicoConfiguracaoScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public ServicoCategoriaScore getServicoCategoriaScore() {
		return servicoCategoriaScore;
	}

	public void setServicoCategoriaScore(ServicoCategoriaScore servicoCategoriaScore) {
		this.servicoCategoriaScore = servicoCategoriaScore;
	}
}