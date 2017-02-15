package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "CANALVENDASOLICITACAOCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "CANALVENDASLCTCOMERCIAL_SQ", sequenceName = "CATALOGOPRS_OW.CANALVENDASLCTCOMERCIAL_SQ", allocationSize = 1)	
public class CanalVendaSolicitacaoComercial implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "IDCANALVENDASOLICITACAOCMRL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CANALVENDASLCTCOMERCIAL_SQ")
	private Long idCanalVendaSolicitacaoCmrl;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@Column(name = "INCRIACAOCATALOGO")
	private String inCriacaoCatalogo;
	
	@ManyToOne
	@JoinColumn(name = "IDCANALVENDA")
	@OrderBy("nmCanalVenda")
	private CanalVenda canalVenda;
	
	@ManyToOne
	@JoinColumn(name = "IDSOLICITACAOCOMERCIAL")
	private SolicitacaoComercial solicitacaoComercial;
	
	@OneToMany(mappedBy="canalVendaSolicitacaoComercial", fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	private List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "DISPONIBILIDADESLCTCOMERCIAL", joinColumns = @JoinColumn(name = "IDCANALVENDASOLICITACAOCMRL"), inverseJoinColumns = @JoinColumn(name = "IDAREACONCORRENCIA"))
	private List<AreaConcorrencia> areaConcorrenciaList;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "DISPONIBILIDADESLCTCOMERCIAL", joinColumns = @JoinColumn(name = "IDCANALVENDASOLICITACAOCMRL"), inverseJoinColumns = @JoinColumn(name = "IDESPSERVICOPLANOMINUTO"))
	private List<EspServicoPlanoMinuto> espServicoPlanoMinutoList;
	
	public CanalVendaSolicitacaoComercial() {
		super();
	}

	public CanalVendaSolicitacaoComercial(Long idCanalVendaSolicitacaoCmrl) {
		super();
		this.idCanalVendaSolicitacaoCmrl = idCanalVendaSolicitacaoCmrl;
	}

	public List<AreaConcorrencia> getAreaConcorrenciaList() {
		return areaConcorrenciaList;
	}

	public void setAreaConcorrenciaList(List<AreaConcorrencia> areaConcorrenciaList) {
		this.areaConcorrenciaList = areaConcorrenciaList;
	}

	public List<DisponibilidadeSlctComercial> getDisponibilidadeSlctComercialList() {
		return disponibilidadeSlctComercialList;
	}

	public void setDisponibilidadeSlctComercialList(
			List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList) {
		this.disponibilidadeSlctComercialList = disponibilidadeSlctComercialList;
	}

	public SolicitacaoComercial getSolicitacaoComercial() {
		return solicitacaoComercial;
	}

	public void setSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
		this.solicitacaoComercial = solicitacaoComercial;
	}

	public CanalVenda getCanalVenda() {
		return canalVenda;
	}

	public void setCanalVenda(CanalVenda canalVenda) {
		this.canalVenda = canalVenda;
	}

	public Long getIdCanalVendaSolicitacaoCmrl() {
		return idCanalVendaSolicitacaoCmrl;
	}

	public void setIdCanalVendaSolicitacaoCmrl(Long idCanalVendaSolicitacaoCmrl) {
		this.idCanalVendaSolicitacaoCmrl = idCanalVendaSolicitacaoCmrl;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String indisponivel) {
		this.inDisponivel = indisponivel;
	}

	public List<EspServicoPlanoMinuto> getEspServicoPlanoMinutoList() {
		return espServicoPlanoMinutoList;
	}

	public void setEspServicoPlanoMinutoList(
			List<EspServicoPlanoMinuto> espServicoPlanoMinutoList) {
		this.espServicoPlanoMinutoList = espServicoPlanoMinutoList;
	}

	public String getInCriacaoCatalogo() {
		return inCriacaoCatalogo;
	}

	public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
		this.inCriacaoCatalogo = inCriacaoCatalogo;
	}
}
