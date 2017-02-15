package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ListaServicoServico;
import br.com.vivo.catalogoPRS.ws.catalogoGrupoServico.sn.ResultadoListarGrupoServicoCategoria;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao;
import br.com.vivo.catalogoPRS.ws.catalogoRegional.sn.ListaRegionalUf;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ListaServicoTarifaServicoTarifa;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaAtributoPorIdServicoAtributo;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoListaServicoRetornoServico;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao;

public class ServicoForm extends ValidatorActionForm implements Serializable
	{
		private static final long serialVersionUID = 1L;
		
		//PesquisaPlanosFormBean
		//PesquisaServiçoFormBean
		private Long paginaSolicitada;
		private Long idPlataforma;
		private Long idCategoria;
		private String regionais;
		private String nmServico;
		private String cdServico;
		private String disponibilidade;
		private Boolean indTitular;
		private String tpPesquisa;
		
		
		//AlterarServiçoFormBean
		private Long idServico;
		private Long idTipoServico;
		private Long idCategoriaCatalogo;
		private Long qtdMinAtivacaoCatalogo;
		private Long qtdMaxAtivacaoCatalogo;
		private Long qtdMinAtivLegado;
		private Long qtdMaxAtivLegado;
		private String nmComercial;
		private String nmCategoriaCatalogo;
		private String nmCategoriaLegado;
		private String nmUsuarioAlteracao;
		private String dscTipoServico;
		private String inDisponibilidadeCatalogo;
		private String inDisponibilidadeLegado;
		private Date dtAlteracao;
		private String dtAlteracaoFormat;
		private String opAtivaWiFi;
		
		//AssociarCategoriaAtivaWifiFormBean
		private Long idSistema;
		private Long[] idsServicos;
		private String indisponivel;
		private String nmCategoria;
		
		//ValidarServicosFormBean
		private Long[] idsServico;
		private double qtdeMinAtivacaoCatalogo;
		private double qtdeMaxAtivacaoCatalogo;
		
		//ValidarPlanosFormBean
		private Long idPlano;
		private double qtMaximaDependentes;
		
		//AlterarPlanosFormBean
		private Long idTpPlano;
		private String cdCodigo;
		private String indTitDep;
		private Long qtdMaxDependenteCatalogo;
		private Long qtdMaxDependenteLegado;
		private Date dtUltimaAlteracao;
		private String dtUltimaAlteracaoFormat;
		
		//AtivaWifiPlanosFormBean
		private Long[] idsPlanos;
		
		private Long idGrupoServico;

		private String nomeServico;
		private String codigoServico;
		private String nomePlano;
	
		private ResultadoBuscarListaServicoListaServicoRetornoServico[] arrayListaServicos;
		private ListaServicoTarifaServicoTarifa[] listaServicoTarifa;
		private List<ResultadoBuscarListaAtributoPorIdServicoAtributo> atributoList;
		private List<ListaServicoServico> detalhesServicoList;
		private String[] idServicoSelecionados;	

		private Map<String, String> mapaDataIncial; 
		private Map<String, String> mapaDataFinal; 
		
	
		private List<ResultadoListarGrupoServicoCategoria> categorias;
		
		private String[] regionaisSelecionadas;
		private String[] ufsSelecionados;
		private String[] valoresRegionaisSelecionadas;
		
		private List<ListaRegionalUf> listaRegionalUf;
		
		private Long idRegional;
		
		private List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> listaServicoParametrizacao;
		private ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao [] listaPlanoParametrizacao;
		
		public ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao [] getListaPlanoParametrizacao() {
			return listaPlanoParametrizacao;
		}

		public void setListaPlanoParametrizacao(
				ResultadoBuscarListaPlanoParametrizacaoListaBuscarListaPlanoParametrizacaoRetornoBuscarListaPlanoParametrizacao [] listaPlanoParametrizacao) {
			this.listaPlanoParametrizacao = listaPlanoParametrizacao;
		}

		public ServicoForm()
		{
			this.tpPesquisa = "Por Servi&ccedil;o";
		}
		
		public Long[] getIdsPlanos() {
			return idsPlanos;
		}
		public void setIdsPlanos(Long[] idsPlanos) {
			this.idsPlanos = idsPlanos;
		}
		
		public String getCdCodigo() {
			return cdCodigo;
		}
		public void setCdCodigo(String cdCodigo) {
			this.cdCodigo = cdCodigo;
		}
		public Date getDtUltimaAlteracao() {
			return dtUltimaAlteracao;
		}
		public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
			this.dtUltimaAlteracao = dtUltimaAlteracao;
		}
		
		public String getDtUltimaAlteracaoFormat() {
			return dtUltimaAlteracaoFormat;
		}

		public void setDtUltimaAlteracaoFormat(String dtUltimaAlteracaoFormat) {
			this.dtUltimaAlteracaoFormat = dtUltimaAlteracaoFormat;
		}

		public String getIndTitDep() {
			return indTitDep;
		}
		public void setIndTitDep(String indTitDep) {
			this.indTitDep = indTitDep;
		}
		
		public Long getQtdMaxDependenteCatalogo() {
			return qtdMaxDependenteCatalogo;
		}
		public void setQtdMaxDependenteCatalogo(Long qtdMaxDependenteCatalogo) {
			this.qtdMaxDependenteCatalogo = qtdMaxDependenteCatalogo;
		}
		public Long getQtdMaxDependenteLegado() {
			return qtdMaxDependenteLegado;
		}
		public void setQtdMaxDependenteLegado(Long qtdMaxDependenteLegado) {
			this.qtdMaxDependenteLegado = qtdMaxDependenteLegado;
		}
		
		public Long getIdTpPlano() {
			return idTpPlano;
		}
		public void setIdTpPlano(Long idTpPlano) {
			this.idTpPlano = idTpPlano;
		}
		
		public Long getIdPlano() {
			return idPlano;
		}
		public void setIdPlano(Long idPlano) {
			this.idPlano = idPlano;
		}
		
		public double getQtMaximaDependentes() {
			return qtMaximaDependentes;
		}
		public void setQtMaximaDependentes(double qtMaximaDependentes) {
			this.qtMaximaDependentes = qtMaximaDependentes;
		}
		
		public Long[] getIdsServico() {
			return idsServico;
		}
		public void setIdsServico(Long[] idsServico) {
			this.idsServico = idsServico;
		}

		public double getQtdeMaxAtivacaoCatalogo() {
			return qtdeMaxAtivacaoCatalogo;
		}
		public void setQtdeMaxAtivacaoCatalogo(double qtdeMaxAtivacaoCatalogo) {
			this.qtdeMaxAtivacaoCatalogo = qtdeMaxAtivacaoCatalogo;
		}
		
		public double getQtdeMinAtivacaoCatalogo() {
			return qtdeMinAtivacaoCatalogo;
		}
		public void setQtdeMinAtivacaoCatalogo(double qtdeMinAtivacaoCatalogo) {
			this.qtdeMinAtivacaoCatalogo = qtdeMinAtivacaoCatalogo;
		}
		
		public String getOpAtivaWiFi() {
			return opAtivaWiFi;
		}
		public void setOpAtivaWiFi(String opAtivaWiFi) {
			this.opAtivaWiFi = opAtivaWiFi;
		}
		public String getNmCategoria() {
			return nmCategoria;
		}
		public void setNmCategoria(String nmCategoria) {
			this.nmCategoria = nmCategoria;
		}

		public String getIndisponivel() {
			return indisponivel;
		}
		public void setIndisponivel(String indisponivel) {
			this.indisponivel = indisponivel;
		}
		
		public Long getIdSistema() {
			return idSistema;
		}
		public void setIdSistema(Long idSistema) {
			this.idSistema = idSistema;
		}
		public Long[] getIdsServicos() {
			return idsServicos;
		}
		public void setIdsServicos(Long[] idsServicos) {
			this.idsServicos = idsServicos;
		}
		
		public Long getIdServico() {
			return idServico;
		}
		public void setIdServico(Long idServico) {
			this.idServico = idServico;
		}
		public Long getQtdMaxAtivacaoCatalogo() {
			return qtdMaxAtivacaoCatalogo;
		}
		public void setQtdMaxAtivacaoCatalogo(Long qtdMaxAtivacaoCatalogo) {
			this.qtdMaxAtivacaoCatalogo = qtdMaxAtivacaoCatalogo;
		}
		public Long getQtdMinAtivacaoCatalogo() {
			return qtdMinAtivacaoCatalogo;
		}
		public void setQtdMinAtivacaoCatalogo(Long qtdMinAtivacaoCatalogo) {
			this.qtdMinAtivacaoCatalogo = qtdMinAtivacaoCatalogo;
		}
		public Long getQtdMaxAtivLegado() {
			return qtdMaxAtivLegado;
		}
		public void setQtdMaxAtivLegado(Long qtdMaxAtivLegado) {
			this.qtdMaxAtivLegado = qtdMaxAtivLegado;
		}
		public Long getQtdMinAtivLegado() {
			return qtdMinAtivLegado;
		}
		public void setQtdMinAtivLegado(Long qtdMinAtivLegado) {
			this.qtdMinAtivLegado = qtdMinAtivLegado;
		}
		
		public Date getDtAlteracao() {
			return dtAlteracao;
		}
		public void setDtAlteracao(Date dtAlteracao) {
			this.dtAlteracao = dtAlteracao;
		}
		public String getNmUsuarioAlteracao() {
			return nmUsuarioAlteracao;
		}
		public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
			this.nmUsuarioAlteracao = nmUsuarioAlteracao;
		}
		public String getNmCategoriaCatalogo() {
			return nmCategoriaCatalogo;
		}
		public void setNmCategoriaCatalogo(String nmCategoriaCatalogo) {
			this.nmCategoriaCatalogo = nmCategoriaCatalogo;
		}
		public String getNmCategoriaLegado() {
			return nmCategoriaLegado;
		}
		public void setNmCategoriaLegado(String nmCategoriaLegado) {
			this.nmCategoriaLegado = nmCategoriaLegado;
		}
		public String getNmComercial() {
			return nmComercial;
		}
		public void setNmComercial(String nmComercial) {
			this.nmComercial = nmComercial;
		}
		public String getDscTipoServico() {
			return dscTipoServico;
		}
		public void setDscTipoServico(String dscTipoServico) {
			this.dscTipoServico = dscTipoServico;
		}
		public Long getIdTipoServico() {
			return idTipoServico;
		}
		public void setIdTipoServico(Long idTipoServico) {
			this.idTipoServico = idTipoServico;
		}
		public Long getIdCategoriaCatalogo() {
			return idCategoriaCatalogo;
		}
		public void setIdCategoriaCatalogo(Long idCategoriaCatalogo) {
			this.idCategoriaCatalogo = idCategoriaCatalogo;
		}
		
		public String getInDisponibilidadeCatalogo() {
			return inDisponibilidadeCatalogo;
		}
		public void setInDisponibilidadeCatalogo(String inDisponibilidadeCatalogo) {
			this.inDisponibilidadeCatalogo = inDisponibilidadeCatalogo;
		}
		public String getInDisponibilidadeLegado() {
			return inDisponibilidadeLegado;
		}
		public void setInDisponibilidadeLegado(String inDisponibilidadeLegado) {
			this.inDisponibilidadeLegado = inDisponibilidadeLegado;
		}

		public Boolean getIndTitular() {
			return indTitular;
		}

		public void setIndTitular(Boolean indTitular) {
			this.indTitular = indTitular;
		}

		public String getNmServico() {
			return nmServico;
		}
		
		public void setNmServico(String nmServico) {
			this.nmServico = nmServico;
		}
		
		public String getCdServico() {
			return cdServico;
		}

		public void setCdServico(String cdServico) {
			this.cdServico = cdServico;
		}

		public String getDisponibilidade() {
			return disponibilidade;
		}

		public void setDisponibilidade(String disponibilidade) {
			this.disponibilidade = disponibilidade;
		}
		
		public Long getIdPlataforma() {
			return idPlataforma;
		}

		public void setIdPlataforma(Long idPlataforma) {
			this.idPlataforma = idPlataforma;
		}

		public Long getIdCategoria() {
			return idCategoria;
		}

		public void setIdCategoria(Long idCategoria) {
			this.idCategoria = idCategoria;
		}

		public Long getPaginaSolicitada() {
			return paginaSolicitada;
		}

		public void setPaginaSolicitada(Long paginaSolicitada) {
			this.paginaSolicitada = paginaSolicitada;
		}

		public String getTpPesquisa() {
			return tpPesquisa;
		}

		public void setTpPesquisa(String tpPesquisa) {
			this.tpPesquisa = tpPesquisa;
		}

		public String getRegionais() {
			return regionais;
		}

		public void setRegionais(String regionais) {
			this.regionais = regionais;
		}

		public Long getIdGrupoServico() {
			return idGrupoServico;
		}

		public void setIdGrupoServico(Long idGrupoServico) {
			this.idGrupoServico = idGrupoServico;
		}

		public String getNomeServico() {
			return nomeServico;
		}

		public void setNomeServico(String nomeServico) {
			this.nomeServico = nomeServico;
		}

		public String getCodigoServico() {
			return codigoServico;
		}

		public void setCodigoServico(String codigoServico) {
			this.codigoServico = codigoServico;
		}

		public String getNomePlano() {
			return nomePlano;
		}

		public void setNomePlano(String nomePlano) {
			this.nomePlano = nomePlano;
		}

		public ResultadoBuscarListaServicoListaServicoRetornoServico[] getArrayListaServicos() {
			return arrayListaServicos;
		}

		public void setArrayListaServicos(ResultadoBuscarListaServicoListaServicoRetornoServico[] arrayListaServicos) {
			this.arrayListaServicos = arrayListaServicos;
		}

		public ListaServicoTarifaServicoTarifa[] getListaServicoTarifa() {
			return listaServicoTarifa;
		}

		public void setListaServicoTarifa(ListaServicoTarifaServicoTarifa[] listaServicoTarifa) {
			this.listaServicoTarifa = listaServicoTarifa;
		}

		public List<ResultadoBuscarListaAtributoPorIdServicoAtributo> getAtributoList() {
			return atributoList;
		}

		public void setAtributoList(List<ResultadoBuscarListaAtributoPorIdServicoAtributo> atributoList) {
			this.atributoList = atributoList;
		}

		public List<ListaServicoServico> getDetalhesServicoList() {
			return detalhesServicoList;
		}

		public void setDetalhesServicoList(List<ListaServicoServico> detalhesServicoList) {
			this.detalhesServicoList = detalhesServicoList;
		}

		public String[] getIdServicoSelecionados() {
			return idServicoSelecionados;
		}

		public void setIdServicoSelecionados(String[] idServicoSelecionados) {
			this.idServicoSelecionados = idServicoSelecionados;
		}

		public Map<String, String> getMapaDataIncial() {
			return mapaDataIncial;
		}

		public void setMapaDataIncial(Map<String, String> mapaDataIncial) {
			this.mapaDataIncial = mapaDataIncial;
		}

		public Map<String, String> getMapaDataFinal() {
			return mapaDataFinal;
		}

		public void setMapaDataFinal(Map<String, String> mapaDataFinal) {
			this.mapaDataFinal = mapaDataFinal;
		}

		public List<ResultadoListarGrupoServicoCategoria> getCategorias() {
			return categorias;
		}

		public void setCategorias(List<ResultadoListarGrupoServicoCategoria> categorias) {
			this.categorias = categorias;
		}

		public String[] getRegionaisSelecionadas() {
			return regionaisSelecionadas;
		}

		public void setRegionaisSelecionadas(String[] regionaisSelecionadas) {
			this.regionaisSelecionadas = regionaisSelecionadas;
		}

		public String[] getUfsSelecionados() {
			return ufsSelecionados;
		}

		public void setUfsSelecionados(String[] ufsSelecionados) {
			this.ufsSelecionados = ufsSelecionados;
		}

		public String[] getValoresRegionaisSelecionadas() {
			return valoresRegionaisSelecionadas;
		}

		public void setValoresRegionaisSelecionadas(String[] valoresRegionaisSelecionadas) {
			this.valoresRegionaisSelecionadas = valoresRegionaisSelecionadas;
		}

		public List<ListaRegionalUf> getListaRegionalUf() {
			return listaRegionalUf;
		}

		public void setListaRegionalUf(List<ListaRegionalUf> listaRegionalUf) {
			this.listaRegionalUf = listaRegionalUf;
		}

		public Long getIdRegional() {
			return idRegional;
		}

		public void setIdRegional(Long idRegional) {
			this.idRegional = idRegional;
		}

		public List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> getListaServicoParametrizacao() {
			return listaServicoParametrizacao;
		}

		public void setListaServicoParametrizacao(
				List<ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao> listaServicoParametrizacao) {
			this.listaServicoParametrizacao = listaServicoParametrizacao;
		}

		public String getDtAlteracaoFormat() {
			return dtAlteracaoFormat;
		}

		public void setDtAlteracaoFormat(String dtAlteracaoFormat) {
			this.dtAlteracaoFormat = dtAlteracaoFormat;
		}
		
		
		
	}
	
	
