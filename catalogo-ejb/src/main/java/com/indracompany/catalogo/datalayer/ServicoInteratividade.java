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
@SequenceGenerator(name = "SERVICOINTERATIVIDADE_SEQ", sequenceName = "CATALOGOPRS_OW.SERVICOINTERATIVIDADE_SEQ", allocationSize = 1)
@Table(name = "SERVICOINTERATIVIDADE", schema = "CATALOGOPRS_OW")
public class ServicoInteratividade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ServicoInteratividade() {}
	
	public ServicoInteratividade(Integer idServicoInteratividade) {
		this.idServicoInteratividade = idServicoInteratividade;		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOINTERATIVIDADE_SEQ")
	@Column(name = "IDSERVICOINTERATIVIDADE")
	private Integer idServicoInteratividade;

	@Column(name = "ATIVO")
	private String ativo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CDFUNCIONALIDADE")
	private Funcionalidade funcionalidade;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO", insertable = true, updatable = false)
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMSERVICO")
	private String nmServico;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO", insertable = true, updatable = false)
	private String nmUsuarioCriacao;

    @Column(name = "URL")
	private String url;
    
    @JoinColumn(name = "TPREDE", referencedColumnName = "SGTIPOLINHA")
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.REFRESH)
    private TipoLinha tipoLinha;
    
    @OneToMany(mappedBy = "servicoInteratividade", cascade = CascadeType.REFRESH)
    private List<SrvInteratividadeParam> srvInteratividadeParametroList;
    
    @OneToMany(mappedBy="servicoInteratividade", cascade = CascadeType.REFRESH)
    private List<ServicoIntCanal> servicoIntCanal;
    
    @OneToMany(mappedBy="servicoInteratividade", cascade = CascadeType.REFRESH)
    private List<ServicoIntPlataforma> servicoIntPlataforma;
    
    @OneToMany(mappedBy="servicoInteratividade", cascade = CascadeType.REFRESH)
    private List<ServicoIntCliente> servicoIntCliente;
    
    @OneToMany(mappedBy="servicoInteratividade", cascade = CascadeType.REFRESH)
    private List<ServicoIntTecnologia> servicoIntTecnologia;
    
	public TipoLinha getTipoLinha() {
		return tipoLinha;
	}

	public void setTipoLinha(TipoLinha tipoLinha) {
		this.tipoLinha = tipoLinha;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
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

	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}

	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}

	public Integer getIdServicoInteratividade() {
		return idServicoInteratividade;
	}

	public void setIdServicoInteratividade(Integer idServicoInteratividade) {
		this.idServicoInteratividade = idServicoInteratividade;
	}

	public String getNmServico() {
		return nmServico;
	}

	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
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

	public List<SrvInteratividadeParam> getSrvInteratividadeParametroList() {
		return srvInteratividadeParametroList;
	}

	public void setSrvInteratividadeParametroList(
			List<SrvInteratividadeParam> srvInteratividadeParametroList) {
		this.srvInteratividadeParametroList = srvInteratividadeParametroList;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<ServicoIntCanal> getServicoIntCanal() {
		return servicoIntCanal;
	}

	public void setServicoIntCanal(List<ServicoIntCanal> servicoIntCanal) {
		this.servicoIntCanal = servicoIntCanal;
	}

	public List<ServicoIntCliente> getServicoIntCliente() {
		return servicoIntCliente;
	}

	public void setServicoIntCliente(List<ServicoIntCliente> servicoIntCliente) {
		this.servicoIntCliente = servicoIntCliente;
	}

	public List<ServicoIntPlataforma> getServicoIntPlataforma() {
		return servicoIntPlataforma;
	}

	public void setServicoIntPlataforma(
			List<ServicoIntPlataforma> servicoIntPlataforma) {
		this.servicoIntPlataforma = servicoIntPlataforma;
	}

	public List<ServicoIntTecnologia> getServicoIntTecnologia() {
		return servicoIntTecnologia;
	}

	public void setServicoIntTecnologia(
			List<ServicoIntTecnologia> servicoIntTecnologia) {
		this.servicoIntTecnologia = servicoIntTecnologia;
	}	
}