package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaComposicaoListaComposicaoComposicao;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaSap.sn.ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF;

public class OfertaSapForm extends ValidatorActionForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private Long idOfertaSap;
	private Long idMatrizOferta;
	private String cdOfertaSap;
	private String descOfertaSap;
	private String sgUtilizacao;
	private String inDisponivel;
	private Long idPlano;
	private Long idPlataforma;
	private Long idTecnologia;
	private Long idOfertaServico;
	private Long idServico;
	private String[] idsPlanos;
	private String idsUf;
	private String nmPlano;
	private String cdPlano;
	private Long idUf;
	private ResultadoBuscarListaComposicaoListaComposicaoComposicao[] composicaoLista;
	private ResultadoBuscarListaUFUF[] listUF;
	private ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta[] planoLista;
	private ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] ofertaServicoLista;
	private String[] ufsSelecionados;
	private String[] dddsSelecionados;
	 
	private String idSistemaPlano;
	
	public ResultadoBuscarListaUFUF[] getListUF() {
		return listUF;
	}
	public void setListUF(ResultadoBuscarListaUFUF[] listUF) {
		this.listUF = listUF;
	}
	private List<ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap> ofertaSapLista;
	
	public List<ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap> getOfertaSapLista() {
		return ofertaSapLista;
	}
	public void setOfertaSapLista(List<ResultadoBuscarListaOfertaSapListaOfertaSapOfertaSap> ofertaSapLista) {
		this.ofertaSapLista = ofertaSapLista;
	}
	public String getDescOfertaSap() {
		return descOfertaSap;
	}
	public void setDescOfertaSap(String descOfertaSap) {
		this.descOfertaSap = descOfertaSap;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	public String getCdOfertaSap() {
		return cdOfertaSap;
	}
	public void setCdOfertaSap(String cdOfertaSap) {
		this.cdOfertaSap = cdOfertaSap;
	}
	public Long getIdOfertaSap() {
		return idOfertaSap;
	}
	public void setIdOfertaSap(Long idOfertaSap) {
		this.idOfertaSap = idOfertaSap;
	}
	public String getSgUtilizacao() {
		return sgUtilizacao;
	}
	public void setSgUtilizacao(String sgUtilizacao) {
		this.sgUtilizacao = sgUtilizacao;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public Long getIdMatrizOferta() {
		return idMatrizOferta;
	}
	public void setIdMatrizOferta(Long idMatrizOferta) {
		this.idMatrizOferta = idMatrizOferta;
	}
	public String getCdPlano() {
		return cdPlano;
	}
	public void setCdPlano(String cdPlano) {
		this.cdPlano = cdPlano;
	}
	public Long getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public String getIdsUf() {
		return idsUf;
	}
	public void setIdsUf(String idsUf) {
		this.idsUf = idsUf;
	}
	public Long getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Long idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public String getNmPlano() {
		return nmPlano;
	}
	public void setNmPlano(String nmPlano) {
		this.nmPlano = nmPlano;
	}
	public ResultadoBuscarListaComposicaoListaComposicaoComposicao[] getComposicaoLista() {
		return composicaoLista;
	}
	public void setComposicaoLista(ResultadoBuscarListaComposicaoListaComposicaoComposicao[] composicaoLista) {
		this.composicaoLista = composicaoLista;
	}
	public Long getIdOfertaServico() {
		return idOfertaServico;
	}
	public void setIdOfertaServico(Long idOfertaServico) {
		this.idOfertaServico = idOfertaServico;
	}
	public String[] getIdsPlanos() {
		return idsPlanos;
	}
	public void setIdsPlanos(String[] idsPlanos) {
		this.idsPlanos = idsPlanos;
	}
	public Long getIdServico() {
		return idServico;
	}
	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	
	public Long getIdUf() {
		return idUf;
	}
	public void setIdUf(Long idUf) {
		this.idUf = idUf;
	}
	public String[] getUfsSelecionados() {
		return ufsSelecionados;
	}
	public void setUfsSelecionados(String[] ufsSelecionados) {
		this.ufsSelecionados = ufsSelecionados;
	}
	public String[] getDddsSelecionados() {
		return dddsSelecionados;
	}
	public void setDddsSelecionados(String[] dddsSelecionados) {
		this.dddsSelecionados = dddsSelecionados;
	}
	public ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta[] getPlanoLista() {
		return planoLista;
	}
	public void setPlanoLista(ResultadoBuscarListaPlanosSemOfertaListaPlanosSemOfertaPlanosSemOferta[] planoLista) {
		this.planoLista = planoLista;
	}
	public ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] getOfertaServicoLista() {
		return ofertaServicoLista;
	}
	public void setOfertaServicoLista(
			ResultadoBuscarListaNmOfertaServicoListaNomeOfertaServicoOfertaServico[] ofertaServicoLista) {
		this.ofertaServicoLista = ofertaServicoLista;
	}
	public String getIdSistemaPlano() {
		return idSistemaPlano;
	}
	public void setIdSistemaPlano(String idSistemaPlano) {
		this.idSistemaPlano = idSistemaPlano;
	}
	
	
}

