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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "SOLICITACAOCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "SOLICITACAOCOMERCIAL_SQ", sequenceName = "CATALOGOPRS_OW.SOLICITACAOCOMERCIAL_SQ", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "SolicitacaoComercial.carregarSolicitacaoComercialPorServicoList", query = "select sc from SolicitacaoComercial sc inner join sc.operacaoComercial opCom where sc.sistemaServico.servico.idServico = :idServicoFixa and sc.inDisponivel = 'S' and sc.tipoSolicitacaoComercial.cdTipoSolicitacaoComercial = '1' order by sc.nmSolicitacaoComercial")
})
public class SolicitacaoComercial implements Serializable {

	@Id
	@Column(name = "IDSOLICITACAOCOMERCIAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SOLICITACAOCOMERCIAL_SQ")
	private Long idSolicitacaoComercial;
	
	@Column(name = "CDSOLICITACAOCOMERCIAL")
	private String cdSolicitacaoComercial;
	
	@Column(name = "NMSOLICITACAOCOMERCIAL")
	private String nmSolicitacaoComercial;

	@Column(name = "PZMAXIMOVIGENCIA")
	private Long pzMaximoVigencia;
	
	@Column(name = "PZMAXIMOATENDIMENTO")
	private Long pzMaximoAtendimento;
	
	@Column(name = "INDISPONIVEL")
	private String inDisponivel;
	
	@Column(name = "INOFERTACLIENTEINADIMPLENTE")
	private String inOfertaClienteInadimplente;
	
	@ManyToOne
	@JoinColumn(name = "IDOPERACAOCOMERCIAL")
	private OperacaoComercial operacaoComercial;
	
	@ManyToOne
	@JoinColumn(name = "IDSISTEMASERVICO")
	private SistemaServico sistemaServico;
	
	@ManyToOne
	@JoinColumn(name = "IDTIPOSOLICITACAOCOMERCIAL")
	private TipoSolicitacaoComercial tipoSolicitacaoComercial;
	
	@OneToOne
	@JoinColumn(name = "IDPOLITICADISPSLCTCOMERCIAL")
	private PoliticaDispSlctComercial politicaDispSlctComercial;

	@OneToMany(mappedBy="solicitacaoComercial")
	@OrderBy("canalVenda")
	private List<CanalVendaSolicitacaoComercial> canalVendaSolicitacaoComercialList;
	
	@OneToMany(mappedBy="solicitacaoComercial")
	private List<Encargo> encargoList;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "CANALVENDASOLICITACAOCOMERCIAL", joinColumns = @JoinColumn(name = "IDSOLICITACAOCOMERCIAL"), inverseJoinColumns = @JoinColumn(name = "IDCANALVENDA"))
	private List<CanalVenda> canalVendaList;
	
	public SolicitacaoComercial(){};
	
	public SolicitacaoComercial(Long id){
		this.idSolicitacaoComercial = id;
	}
	
	public List<CanalVenda> getCanalVendaList() {
		return canalVendaList;
	}

	public void setCanalVendaList(List<CanalVenda> canalVendaList) {
		this.canalVendaList = canalVendaList;
	}

	public List<CanalVendaSolicitacaoComercial> getCanalVendaSolicitacaoComercialList() {
		return canalVendaSolicitacaoComercialList;
	}

	public void setCanalVendaSolicitacaoComercialList(
			List<CanalVendaSolicitacaoComercial> canalVendaSolicitacaoComercialList) {
		this.canalVendaSolicitacaoComercialList = canalVendaSolicitacaoComercialList;
	}

	public String getCdSolicitacaoComercial() {
		return cdSolicitacaoComercial;
	}

	public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
		this.cdSolicitacaoComercial = cdSolicitacaoComercial;
	}

	public List<Encargo> getEncargoList() {
		return encargoList;
	}

	public void setEncargoList(List<Encargo> encargoList) {
		this.encargoList = encargoList;
	}

	public Long getIdSolicitacaoComercial() {
		return idSolicitacaoComercial;
	}

	public void setIdSolicitacaoComercial(Long idSolicitacaoComercial) {
		this.idSolicitacaoComercial = idSolicitacaoComercial;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}

	public String getNmSolicitacaoComercial() {
		return nmSolicitacaoComercial;
	}

	public void setNmSolicitacaoComercial(String nmSolicitacaoComercial) {
		this.nmSolicitacaoComercial = nmSolicitacaoComercial;
	}

	public OperacaoComercial getOperacaoComercial() {
		return operacaoComercial;
	}

	public void setOperacaoComercial(OperacaoComercial operacaoComercial) {
		this.operacaoComercial = operacaoComercial;
	}

	public PoliticaDispSlctComercial getPoliticaDispSlctComercial() {
		return politicaDispSlctComercial;
	}

	public void setPoliticaDispSlctComercial(
			PoliticaDispSlctComercial politicaDispSlctComercial) {
		this.politicaDispSlctComercial = politicaDispSlctComercial;
	}

	public Long getPzMaximoAtendimento() {
		return pzMaximoAtendimento;
	}

	public void setPzMaximoAtendimento(Long pzMaximoAtendimento) {
		this.pzMaximoAtendimento = pzMaximoAtendimento;
	}

	public Long getPzMaximoVigencia() {
		return pzMaximoVigencia;
	}

	public void setPzMaximoVigencia(Long pzMaximoVigencia) {
		this.pzMaximoVigencia = pzMaximoVigencia;
	}

	public SistemaServico getSistemaServico() {
		return sistemaServico;
	}

	public void setSistemaServico(SistemaServico sistemaServico) {
		this.sistemaServico = sistemaServico;
	}

	public TipoSolicitacaoComercial getTipoSolicitacaoComercial() {
		return tipoSolicitacaoComercial;
	}

	public void setTipoSolicitacaoComercial(
			TipoSolicitacaoComercial tipoSolicitacaoComercial) {
		this.tipoSolicitacaoComercial = tipoSolicitacaoComercial;
	}

	public String getInOfertaClienteInadimplente() {
		return inOfertaClienteInadimplente;
	}

	public void setInOfertaClienteInadimplente(String inOfertaClienteInadimplente) {
		this.inOfertaClienteInadimplente = inOfertaClienteInadimplente;
	}
}
