package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico;
import br.com.vivo.catalogoPRS.ws.catalogoOfertaServico.sn.ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao;

public class OfertaServicosMatrizOfertaForm extends ValidatorActionForm  implements java.io.Serializable  {

	private static final long serialVersionUID = 1L;

	private Long idOfertaServico;
	private Long idServicoOfertaServico;
	private Long idPlataforma;
	private Long idTecnologia;
	private Long idCategoria;
	private Long idTipoServico;
	private String cdOfertaServico;
	private String nmOfertaServico;
	private String cdCodigo;
	private String nmComercial;
	private Long paginaSolicitada;
	private String nmServico;
	private String cdServico;
	private Long[] idsServico;
	private String listaIdServico;
	private String dsNota;
	
	private List<ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico> listaOfertaServicos;
	private List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> servicos;
	private List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> listaServicosAssociadosOfertaServicos;
	private List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> servicosParaAssociar;

	public String getDsNota() {
		return dsNota;
	}
	public void setDsNota(String dsNota) {
		this.dsNota = dsNota;
	}
	public String getListaIdServico() {
		return listaIdServico;
	}
	public void setListaIdServico(String listaIdServico) {
		this.listaIdServico = listaIdServico;
	}
	public String getCdCodigo() {
		return cdCodigo;
	}
	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}
	public String getCdOfertaServico() {
		return cdOfertaServico;
	}
	public void setCdOfertaServico(String cdOfertaServico) {
		this.cdOfertaServico = cdOfertaServico;
	}
	public String getCdServico() {
		return cdServico;
	}
	public void setCdServico(String cdServico) {
		this.cdServico = cdServico;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Long getIdOfertaServico() {
		return idOfertaServico;
	}
	public void setIdOfertaServico(Long idOfertaServico) {
		this.idOfertaServico = idOfertaServico;
	}
	public Long[] getIdsServico() {
		return idsServico;
	}
	public void setIdsServico(Long[] idsServico) {
		this.idsServico = idsServico;
	}	
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Long getIdServicoOfertaServico() {
		return idServicoOfertaServico;
	}
	public void setIdServicoOfertaServico(Long idServicoOfertaServico) {
		this.idServicoOfertaServico = idServicoOfertaServico;
	}
	public Long getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Long idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Long getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Long idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public String getNmComercial() {
		return nmComercial;
	}
	public void setNmComercial(String nmComercial) {
		this.nmComercial = nmComercial;
	}
	public String getNmOfertaServico() {
		return nmOfertaServico;
	}
	public void setNmOfertaServico(String nmOfertaServico) {
		this.nmOfertaServico = nmOfertaServico;
	}
	public String getNmServico() {
		return nmServico;
	}
	public void setNmServico(String nmServico) {
		this.nmServico = nmServico;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public List<ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico> getListaOfertaServicos() {
		return listaOfertaServicos;
	}
	public void setListaOfertaServicos(
			List<ResultadoBuscarListaOfertaServicoListaOfertaServicoOfertaServico> listaOfertaServicos) {
		this.listaOfertaServicos = listaOfertaServicos;
	}
	public List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> getServicos() {
		return servicos;
	}
	public void setServicos(List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> servicos) {
		this.servicos = servicos;
	}
	public List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> getListaServicosAssociadosOfertaServicos() {
		return listaServicosAssociadosOfertaServicos;
	}
	public void setListaServicosAssociadosOfertaServicos(
			List<ResultadoBuscarListaServicoComOfertaListaServicoComOfertaServicoComOferta> listaServicosAssociadosOfertaServicos) {
		this.listaServicosAssociadosOfertaServicos = listaServicosAssociadosOfertaServicos;
	}
	public List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> getServicosParaAssociar() {
		return servicosParaAssociar;
	}
	public void setServicosParaAssociar(
			List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> servicosParaAssociar) {
		this.servicosParaAssociar = servicosParaAssociar;
	}
}
