package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.util.PerfilSCA;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoListaPlanoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao;

public class ConsultaAcessoPlanosServicosForm extends ValidatorActionForm  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private Long idPlataforma;
	private Long idTipoCliente;
	private Long idTecnologia;
	private Long idUf;
	private String nmComercialPlano;
	private String nmComercialServico;
	private String nmTecnico;
	private Long idCategoria;
	private Long idTipoServico;
	private Long idPerfil;
	private String idsPerfilCheck;
	private String idsPerfilRadio;
	private String idExporte;
	private String idPlano;
	private String idServico;
	private Long idTpPesquisa;
	private Long tpNmComercial;
	private String nmComercialPlanoTextBox;
	private String nmComercialServicoTextBox;
	private String nmComercialPlanoTextBox2;
	private String nmComercialServicoTextBox2;
	
	private PerfilSCA[] arrayPerfil;
	private ResultadoBuscarListaPlanoListaPlanoPlano[] arrayListaPlanos;
	private ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] arrayListaServicos;
	private ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] arrayListaAcessoPlano;
	private ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] arrayListaAcessoServico;
	private String perfilSCA;
	private String[] perfilSelecionados;
	private String[] nmComercialSelecionados;
	private String chkTodos;
	private String hidden_perfilSCACheck;
	private String hidden_perfilSCARadio;
	private String hidden_nmComercial_planoRadio;
	private String hidden_nmComercial_servicoRadio;
	private String hidden_nmComercial_planoCheck;
	private String hidden_nmComercial_servicoCheck;
	private String textfieldPerfilSCARadio;
	private String textfieldPerfilSCACheck;
	private String checkRestricaoConsulta;
	private String checkRestricaoAtivacao;
	private String checkRestricaoDesativacao;
	private String checkAcessoPlano;

	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma; 
	}
	public Long getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(Long idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public Long getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Long idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Long getIdUf() {
		return idUf;
	}
	public void setIdUf(Long idUf) {
		this.idUf = idUf;
	}
	public String getNmComercialPlano() {
		return nmComercialPlano;
	}
	public void setNmComercialPlano(String nmComercialPlano) {
		this.nmComercialPlano = nmComercialPlano;
	}
	public String getNmComercialServico() {
		return nmComercialServico;
	}
	public void setNmComercialServico(String nmComercialServico) {
		this.nmComercialServico = nmComercialServico;
	}
	public String getNmTecnico() {
		return nmTecnico;
	}
	public void setNmTecnico(String nmTecnico) {
		this.nmTecnico = nmTecnico;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public Long getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Long idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getIdsPerfilCheck() {
		return idsPerfilCheck;
	}
	public void setIdsPerfilCheck(String idsPerfilCheck) {
		this.idsPerfilCheck = idsPerfilCheck;
	}
	public String getIdsPerfilRadio() {
		return idsPerfilRadio;
	}
	public void setIdsPerfilRadio(String idsPerfilRadio) {
		this.idsPerfilRadio = idsPerfilRadio;
	}
	public String getIdExporte() {
		return idExporte;
	}
	public void setIdExporte(String idExporte) {
		this.idExporte = idExporte;
	}
	public String getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(String idPlano) {
		this.idPlano = idPlano;
	}
	public String getIdServico() {
		return idServico;
	}
	public void setIdServico(String idServico) {
		this.idServico = idServico;
	}
	public Long getIdTpPesquisa() {
		return idTpPesquisa;
	}
	public void setIdTpPesquisa(Long idTpPesquisa) {
		this.idTpPesquisa = idTpPesquisa;
	}
	public Long getTpNmComercial() {
		return tpNmComercial;
	}
	public void setTpNmComercial(Long tpNmComercial) {
		this.tpNmComercial = tpNmComercial;
	}
	public String getNmComercialPlanoTextBox() {
		return nmComercialPlanoTextBox;
	}
	public void setNmComercialPlanoTextBox(String nmComercialPlanoTextBox) {
		this.nmComercialPlanoTextBox = nmComercialPlanoTextBox;
	}
	public String getNmComercialServicoTextBox() {
		return nmComercialServicoTextBox;
	}
	public void setNmComercialServicoTextBox(String nmComercialServicoTextBox) {
		this.nmComercialServicoTextBox = nmComercialServicoTextBox;
	}
	public String getNmComercialPlanoTextBox2() {
		return nmComercialPlanoTextBox2;
	}
	public void setNmComercialPlanoTextBox2(String nmComercialPlanoTextBox2) {
		this.nmComercialPlanoTextBox2 = nmComercialPlanoTextBox2;
	}
	public String getNmComercialServicoTextBox2() {
		return nmComercialServicoTextBox2;
	}
	public void setNmComercialServicoTextBox2(String nmComercialServicoTextBox2) {
		this.nmComercialServicoTextBox2 = nmComercialServicoTextBox2;
	}
	public PerfilSCA[] getArrayPerfil() {
		return arrayPerfil;
	}
	public void setArrayPerfil(PerfilSCA[] arrayPerfil) {
		this.arrayPerfil = arrayPerfil;
	}
	public String getPerfilSCA() {
		return perfilSCA;
	}
	public void setPerfilSCA(String perfilSCA) {
		this.perfilSCA = perfilSCA;
	}
	public String[] getPerfilSelecionados() {
		return perfilSelecionados;
	}
	public void setPerfilSelecionados(String[] perfilSelecionados) {
		this.perfilSelecionados = perfilSelecionados;
	}
	public String getChkTodos() {
		return chkTodos;
	}
	public void setChkTodos(String chkTodos) {
		this.chkTodos = chkTodos;
	}
	public String getHidden_perfilSCACheck() {
		return hidden_perfilSCACheck;
	}
	public void setHidden_perfilSCACheck(String hidden_perfilSCACheck) {
		this.hidden_perfilSCACheck = hidden_perfilSCACheck;
	}
	public String getHidden_perfilSCARadio() {
		return hidden_perfilSCARadio;
	}
	public void setHidden_perfilSCARadio(String hidden_perfilSCARadio) {
		this.hidden_perfilSCARadio = hidden_perfilSCARadio;
	}
	public String getTextfieldPerfilSCARadio() {
		return textfieldPerfilSCARadio;
	}
	public void setTextfieldPerfilSCARadio(String textfieldPerfilSCARadio) {
		this.textfieldPerfilSCARadio = textfieldPerfilSCARadio;
	}
	public String getTextfieldPerfilSCACheck() {
		return textfieldPerfilSCACheck;
	}
	public void setTextfieldPerfilSCACheck(String textfieldPerfilSCACheck) {
		this.textfieldPerfilSCACheck = textfieldPerfilSCACheck;
	}
	public ResultadoBuscarListaPlanoListaPlanoPlano[] getArrayListaPlanos() {
		return arrayListaPlanos;
	}
	public void setArrayListaPlanos(ResultadoBuscarListaPlanoListaPlanoPlano[] arrayListaPlanos) {
		this.arrayListaPlanos = arrayListaPlanos;
	}	
	public String[] getNmComercialSelecionados() {
		return nmComercialSelecionados;
	}
	public void setNmComercialSelecionados(String[] nmComercialSelecionados) {
		this.nmComercialSelecionados = nmComercialSelecionados;
	}
	public String getHidden_nmComercial_planoRadio() {
		return hidden_nmComercial_planoRadio;
	}
	public void setHidden_nmComercial_planoRadio(String hidden_nmComercial_planoRadio) {
		this.hidden_nmComercial_planoRadio = hidden_nmComercial_planoRadio;
	}
	public String getHidden_nmComercial_servicoRadio() {
		return hidden_nmComercial_servicoRadio;
	}
	public void setHidden_nmComercial_servicoRadio(String hidden_nmComercial_servicoRadio) {
		this.hidden_nmComercial_servicoRadio = hidden_nmComercial_servicoRadio;
	}
	public String getHidden_nmComercial_planoCheck() {
		return hidden_nmComercial_planoCheck;
	}
	public void setHidden_nmComercial_planoCheck(String hidden_nmComercial_planoCheck) {
		this.hidden_nmComercial_planoCheck = hidden_nmComercial_planoCheck;
	}
	public String getHidden_nmComercial_servicoCheck() {
		return hidden_nmComercial_servicoCheck;
	}
	public void setHidden_nmComercial_servicoCheck(String hidden_nmComercial_servicoCheck) {
		this.hidden_nmComercial_servicoCheck = hidden_nmComercial_servicoCheck;
	}	
	public ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] getArrayListaServicos() {
		return arrayListaServicos;
	}
	public void setArrayListaServicos(
			ResultadoBuscarListaServicoParametrizacaoListaBuscarListaServicoParametrizacaoRetornoBuscarListaServicoParametrizacao[] arrayListaServicos) {
		this.arrayListaServicos = arrayListaServicos;
	}	
	public ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] getArrayListaAcessoPlano() {
		return arrayListaAcessoPlano;
	}
	public void setArrayListaAcessoPlano(
			ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] arrayListaAcessoPlano) {
		this.arrayListaAcessoPlano = arrayListaAcessoPlano;
	}	
	public ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] getArrayListaAcessoServico() {
		return arrayListaAcessoServico;
	}
	public void setArrayListaAcessoServico(
			ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] arrayListaAcessoServico) {
		this.arrayListaAcessoServico = arrayListaAcessoServico;
	}	
	public String getCheckRestricaoConsulta() {
		return checkRestricaoConsulta;
	}
	public void setCheckRestricaoConsulta(String checkRestricaoConsulta) {
		this.checkRestricaoConsulta = checkRestricaoConsulta;
	}
	public String getCheckRestricaoAtivacao() {
		return checkRestricaoAtivacao;
	}
	public void setCheckRestricaoAtivacao(String checkRestricaoAtivacao) {
		this.checkRestricaoAtivacao = checkRestricaoAtivacao;
	}
	public String getCheckRestricaoDesativacao() {
		return checkRestricaoDesativacao;
	}
	public void setCheckRestricaoDesativacao(String checkRestricaoDesativacao) {
		this.checkRestricaoDesativacao = checkRestricaoDesativacao;
	}	
	public String getCheckAcessoPlano() {
		return checkAcessoPlano;
	}
	public void setCheckAcessoPlano(String checkAcessoPlano) {
		this.checkAcessoPlano = checkAcessoPlano;
	}
}
