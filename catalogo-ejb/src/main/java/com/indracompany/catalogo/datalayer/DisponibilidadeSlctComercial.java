package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DISPONIBILIDADESLCTCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "DISPONIBILIDADESLCTCMRC_SQ", sequenceName = "CATALOGOPRS_OW.DISPONIBILIDADESLCTCMRC_SQ", allocationSize = 1)
public class DisponibilidadeSlctComercial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7286504338039469275L;
	
	@Id
	@Column(name = "IDDISPONIBILIDADESLCTCOMERCIAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISPONIBILIDADESLCTCMRC_SQ")
	private Long idDisponibilidadeSlctComercial;
	
	@ManyToOne
	@JoinColumn(name = "IDCANALVENDASOLICITACAOCMRL")
	private CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial;
	
	@OneToOne
	@JoinColumn(name = "IDPOLITICADISPSLCTCOMERCIAL")
	private PoliticaDispSlctComercial politicaDispSlctComercial;
 
	@ManyToOne
	@JoinColumn(name = "IDESPSERVICOPLANOMINUTO")
	private EspServicoPlanoMinuto espServicoPlanoMinuto;

	@ManyToOne
	@JoinColumn(name = "IDAREACONCORRENCIA")
	private AreaConcorrencia areaConcorrencia;
	
	public DisponibilidadeSlctComercial() {
		super();
	}

	public DisponibilidadeSlctComercial(Long idDisponibilidadeSlctComercial) {
		super();
		this.idDisponibilidadeSlctComercial = idDisponibilidadeSlctComercial;
	}

	public DisponibilidadeSlctComercial(CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial, PoliticaDispSlctComercial politicaDispSlctComercial, EspServicoPlanoMinuto espServicoPlanoMinuto, AreaConcorrencia areaConcorrencia) {
		super();
		this.canalVendaSolicitacaoComercial = canalVendaSolicitacaoComercial;
		this.politicaDispSlctComercial = politicaDispSlctComercial;
		this.espServicoPlanoMinuto = espServicoPlanoMinuto;
		this.areaConcorrencia = areaConcorrencia;
	}

	public CanalVendaSolicitacaoComercial getCanalVendaSolicitacaoComercial() {
		return canalVendaSolicitacaoComercial;
	}

	public void setCanalVendaSolicitacaoComercial(
			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial) {
		this.canalVendaSolicitacaoComercial = canalVendaSolicitacaoComercial;
	}

	public Long getIdDisponibilidadeSlctComercial() {
		return idDisponibilidadeSlctComercial;
	}

	public void setIdDisponibilidadeSlctComercial(
			Long idDisponibilidadeSlctComercial) {
		this.idDisponibilidadeSlctComercial = idDisponibilidadeSlctComercial;
	}

	public PoliticaDispSlctComercial getPoliticaDispSlctComercial() {
		return politicaDispSlctComercial;
	}

	public void setPoliticaDispSlctComercial(
			PoliticaDispSlctComercial politicaDispSlctComercial) {
		this.politicaDispSlctComercial = politicaDispSlctComercial;
	}

	public EspServicoPlanoMinuto getEspServicoPlanoMinuto() {
		return espServicoPlanoMinuto;
	}

	public void setEspServicoPlanoMinuto(EspServicoPlanoMinuto espServicoPlanoMinuto) {
		this.espServicoPlanoMinuto = espServicoPlanoMinuto;
	}

	public AreaConcorrencia getAreaConcorrencia() {
		return areaConcorrencia;
	}

	public void setAreaConcorrencia(AreaConcorrencia areaConcorrencia) {
		this.areaConcorrencia = areaConcorrencia;
	}

}
