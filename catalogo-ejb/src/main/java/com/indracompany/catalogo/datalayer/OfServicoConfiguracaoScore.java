package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "OFSERVICOCONFIGURACAOSCORE_SQ", sequenceName = "CATALOGOPRS_OW.OFSERVICOCONFIGURACAOSCORE_SQ", allocationSize = 1)
@Table(name="OFSERVICOCONFIGURACAOSCORE", schema = "CATALOGOPRS_OW")
public class OfServicoConfiguracaoScore implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public OfServicoConfiguracaoScore() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFSERVICOCONFIGURACAOSCORE_SQ")
	@Column(name = "IDOFSERVICOCONFIGURACAOSCORE")
	private Integer idOfServicoConfiguracaoScore;

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

	//bi-directional many-to-one association to Ofertaservicocategoriascore
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="IDOFERTASERVICOCATEGORIASCORE", nullable=false)
	private OfertaServicoCategoriaScore ofertaServicoCategoriaScore;
	
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

	public Integer getIdOfServicoConfiguracaoScore() {
		return idOfServicoConfiguracaoScore;
	}

	public void setIdOfServicoConfiguracaoScore(Integer idOfServicoConfiguracaoScore) {
		this.idOfServicoConfiguracaoScore = idOfServicoConfiguracaoScore;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public OfertaServicoCategoriaScore getOfertaServicoCategoriaScore() {
		return ofertaServicoCategoriaScore;
	}

	public void setOfertaServicoCategoriaScore(
			OfertaServicoCategoriaScore ofertaServicoCategoriaScore) {
		this.ofertaServicoCategoriaScore = ofertaServicoCategoriaScore;
	}
}