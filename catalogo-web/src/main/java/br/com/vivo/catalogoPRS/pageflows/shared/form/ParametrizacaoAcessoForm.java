
package br.com.vivo.catalogoPRS.pageflows.shared.form;


import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.util.PerfilSCA;
import br.com.vivo.catalogoPRS.util.ProfileSys;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoAcesso.sn.ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico;
import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarListaPlanoListaPlanoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoServico.sn.ResultadoBuscarListaServicoListaServicoRetornoServico;



public class ParametrizacaoAcessoForm extends ValidatorActionForm implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long paginaSolicitada;
	private Long idPlataforma;
	private Long idTipoCliente;
	private Long idTecnologia;
	private Long idUf;
	private Long idGrupoServico;
	private Long idPlano;
	private Long idServico;
	private Long idTipoServico;
	private Long idCategoria;
	private Long[] idAcessoPlano;
	private Long[] idAcessoServico;
	private String[] inRestricaoConsulta;
	private String[] inRestricaoAtivacao;
	private String[] inRestricaoDesativacao;
	private String nmComPlanoPerfilPop;
	private String nmComPlanoPerfilText;
	private String nmComServicoPerfilPop;
	private String nmComServicoPerfilText;
	private String nmComPerfilPlanoPop;
	private String nmComPerfilPlanoText;
	private String nmComPerfilServicoPop;
	private String nmComPerfilServicoText;
	private String idsPerfilPpSp;
	private String idsPerfilPpPs;
	private String tpPesquisa;
	private String tipoRestricao;
	private String consulta;
	private String ativacao;
	private String desativacao;
	private String nmTecnico;
	
	private String textfield_plano_perfil;
	private String hidden_lista_perfil_plano_perfil;
	private String hidden_lista_perfil_servico_perfil;
	private String hidden_nmComercial_plano_perfil;
	private String hidden_nmComercial_servico_perfil;
	private String hidden_nmComercial_perfil_plano;
	private String hidden_nmComercial_perfil_servico;
	
	private String nome_tecnico;
	private String textfield_servico_perfil;
	private String planoPerfil;
	private String servicoPerfil;
	private String perfilPlano;
	private String perfilServico;
	private String chkTodos;
	private String[] perfilSelecionados;
	private PerfilSCA[] arrayPerfil;
	private String perfil_cb;
    
    private String larguraPopup;
    private String alturaPopup;
    private String select_plataforma;
    private String select_tecnologia;
    private String select_ufs;
    private String nmPerfilSCA;
    private String txtnmComPlanoPerfilPop;
    private String txtnmComServicoPerfilPop;
    private String radioPlano;
    private String radioServico;
    private String ckb_consulta;
    private String ckb_desativacao;
    private String ckb_ativacao;
    private String botao_gravar_restricoes_plano_servico;
    private String botao_pesquisar_acesso_plano_servico;
    private String botao_pesquisar_nome_comercial_plano_popup;
    private PerfilSCA[] idPerfilOrigem;
    private PerfilSCA[] idPerfilDestino;
    
	private Map<Long, ProfileSys> perfilScaMap;
	private Map<Long, String> perfilSCA;
	private Map<Long, String> listaPerfilSCA;
    
    private String[] nmComercialSelecionados;
    private ResultadoBuscarListaPlanoListaPlanoPlano[] planoLista;									// resultadoPesquisaNmComercialNovaRestricaoPlano.jsp
    private ResultadoBuscarListaServicoListaServicoRetornoServico[] retornoServicoLista;			// resultadoPesquisaNmComercialNovaRestricaoServico.jsp
    private ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] acessoPlanoLista; 		    // resultadoPesquisaPlanoPerfil.jsp
    private ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] acessoServicoLista;  // resultadoPesquisaServicoPerfil.jsp
  
    
    private String ckb_inRestricaoConsulta;
    private String ckb_inRestricaoAtivacao;
    private String ckb_inRestricaoDesativacao;
    
	private Long idPerfilOrigemCopiarRestrAcesso;
	private Long idPerfilDestinoCopiarRestrAcesso;
	private Long idAcessoPlanoExcluirPlano;
	private Long idAcessoServicoExcluirServico;
	
	private String inRestricaoConsultaExcluir;
	private String inRestricaoAtivacaoExcluir;
	private String inRestricaoDesativacaoExcluir;
	
	
    private String modelo;
    
	public String getNmTecnico() {
		return nmTecnico;
	}
	public void setNmTecnico(String nmTecnico) {
		this.nmTecnico = nmTecnico;
	}
	public String getTipoRestricao() {
		return tipoRestricao;
	}
	public void setTipoRestricao(String tipoRestricao) {
		this.tipoRestricao = tipoRestricao;
	}
	public String getIdsPerfilPpPs() {
		return idsPerfilPpPs;
	}
	public void setIdsPerfilPpPs(String idsPerfilPpPs) {
		this.idsPerfilPpPs = idsPerfilPpPs;
	}
	public String getIdsPerfilPpSp() {
		return idsPerfilPpSp;
	}
	public void setIdsPerfilPpSp(String idsPerfilPpSp) {
		this.idsPerfilPpSp = idsPerfilPpSp;
	}
	public String getTpPesquisa() {
		return tpPesquisa;
	}
	public void setTpPesquisa(String tpPesquisa) {
		this.tpPesquisa = tpPesquisa;
	}
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
	public Long[] getIdAcessoPlano() {
		return idAcessoPlano;
	}
	public void setIdAcessoPlano(Long[] idAcessoPlano) {
		this.idAcessoPlano = idAcessoPlano;
	}
	public String[] getInRestricaoConsulta() {
		return inRestricaoConsulta;
	}
	public void setInRestricaoConsulta(String[] inRestricaoConsulta) {
		this.inRestricaoConsulta = inRestricaoConsulta;
	}
	public String getNmComPerfilPlanoPop() {
		return nmComPerfilPlanoPop;
	}
	public void setNmComPerfilPlanoPop(String nmComPerfilPlanoPop) {
		this.nmComPerfilPlanoPop = nmComPerfilPlanoPop;
	}
	public String getNmComPerfilServicoPop() {
		return nmComPerfilServicoPop;
	}
	public void setNmComPerfilServicoPop(String nmComPerfilServicoPop) {
		this.nmComPerfilServicoPop = nmComPerfilServicoPop;
	}
	public String getNmComPlanoPerfilPop() {
		return nmComPlanoPerfilPop;
	}
	public void setNmComPlanoPerfilPop(String nmComPlanoPerfilPop) {
		this.nmComPlanoPerfilPop = nmComPlanoPerfilPop;
	}
	public String getNmComServicoPerfilPop() {
		return nmComServicoPerfilPop;
	}
	public void setNmComServicoPerfilPop(String nmComServicoPerfilPop) {
		this.nmComServicoPerfilPop = nmComServicoPerfilPop;
	}
	
	public Long getIdTipoServico() {
		return idTipoServico;
	}
	public void setIdTipoServico(Long idTipoServico) {
		this.idTipoServico = idTipoServico;
	}
	public String getNmComPlanoPerfilText() {
		return nmComPlanoPerfilText;
	}
	public void setNmComPlanoPerfilText(String nmComPlanoPerfilText) {
		this.nmComPlanoPerfilText = nmComPlanoPerfilText;
	}
	public String getNmComServicoPerfilText() {
		return nmComServicoPerfilText;
	}
	public void setNmComServicoPerfilText(String nmComServicoPerfilText) {
		this.nmComServicoPerfilText = nmComServicoPerfilText;
	}
	public String getNmComPerfilPlanoText() {
		return nmComPerfilPlanoText;
	}
	public void setNmComPerfilPlanoText(String nmComPerfilPlanoText) {
		this.nmComPerfilPlanoText = nmComPerfilPlanoText;
	}
	public String getNmComPerfilServicoText() {
		return nmComPerfilServicoText;
	}
	public void setNmComPerfilServicoText(String nmComPerfilServicoText) {
		this.nmComPerfilServicoText = nmComPerfilServicoText;
	}
	public String[] getInRestricaoAtivacao() {
		return inRestricaoAtivacao;
	}
	public void setInRestricaoAtivacao(String[] inRestricaoAtivacao) {
		this.inRestricaoAtivacao = inRestricaoAtivacao;
	}
	public String[] getInRestricaoDesativacao() {
		return inRestricaoDesativacao;
	}
	public void setInRestricaoDesativacao(String[] inRestricaoDesativacao) {
		this.inRestricaoDesativacao = inRestricaoDesativacao;
	}
	public Long[] getIdAcessoServico() {
		return idAcessoServico;
	}
	public void setIdAcessoServico(Long[] idAcessoServico) {
		this.idAcessoServico = idAcessoServico;
	}
	public String getAtivacao() {
		return ativacao;
	}
	public void setAtivacao(String ativacao) {
		this.ativacao = ativacao;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	public String getDesativacao() {
		return desativacao;
	}
	public void setDesativacao(String desativacao) {
		this.desativacao = desativacao;
	}
	public Long getIdGrupoServico() {
		return idGrupoServico;
	}
	public void setIdGrupoServico(Long idGrupoServico) {
		this.idGrupoServico = idGrupoServico;
	}
	public Long getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}
	public Long getIdServico() {
		return idServico;
	}
	public void setIdServico(Long idServico) {
		this.idServico = idServico;
	}
	public String getTextfield_plano_perfil() {
		return textfield_plano_perfil;
	}
	public void setTextfield_plano_perfil(String textfield_plano_perfil) {
		this.textfield_plano_perfil = textfield_plano_perfil;
	}
	public String getHidden_lista_perfil_plano_perfil() {
		return hidden_lista_perfil_plano_perfil;
	}
	public void setHidden_lista_perfil_plano_perfil(String hidden_lista_perfil_plano_perfil) {
		this.hidden_lista_perfil_plano_perfil = hidden_lista_perfil_plano_perfil;
	}
	public String getNome_tecnico() {
		return nome_tecnico;
	}
	public void setNome_tecnico(String nome_tecnico) {
		this.nome_tecnico = nome_tecnico;
	}
	public String getHidden_lista_perfil_servico_perfil() {
		return hidden_lista_perfil_servico_perfil;
	}
	public void setHidden_lista_perfil_servico_perfil(String hidden_lista_perfil_servico_perfil) {
		this.hidden_lista_perfil_servico_perfil = hidden_lista_perfil_servico_perfil;
	}
	public String getTextfield_servico_perfil() {
		return textfield_servico_perfil;
	}
	public void setTextfield_servico_perfil(String textfield_servico_perfil) {
		this.textfield_servico_perfil = textfield_servico_perfil;
	}
	public String getPlanoPerfil() {
		return planoPerfil;
	}
	public void setPlanoPerfil(String planoPerfil) {
		this.planoPerfil = planoPerfil;
	}
	public String getServicoPerfil() {
		return servicoPerfil;
	}
	public void setServicoPerfil(String servicoPerfil) {
		this.servicoPerfil = servicoPerfil;
	}
	public String getPerfilPlano() {
		return perfilPlano;
	}
	public void setPerfilPlano(String perfilPlano) {
		this.perfilPlano = perfilPlano;
	}
	public String getPerfilServico() {
		return perfilServico;
	}
	public void setPerfilServico(String perfilServico) {
		this.perfilServico = perfilServico;
	}
	public String getHidden_nmComercial_plano_perfil() {
		return hidden_nmComercial_plano_perfil;
	}
	public void setHidden_nmComercial_plano_perfil(String hidden_nmComercial_plano_perfil) {
		this.hidden_nmComercial_plano_perfil = hidden_nmComercial_plano_perfil;
	}
	public String getHidden_nmComercial_servico_perfil() {
		return hidden_nmComercial_servico_perfil;
	}
	public void setHidden_nmComercial_servico_perfil(String hidden_nmComercial_servico_perfil) {
		this.hidden_nmComercial_servico_perfil = hidden_nmComercial_servico_perfil;
	}
	public String getHidden_nmComercial_perfil_plano() {
		return hidden_nmComercial_perfil_plano;
	}
	public void setHidden_nmComercial_perfil_plano(String hidden_nmComercial_perfil_plano) {
		this.hidden_nmComercial_perfil_plano = hidden_nmComercial_perfil_plano;
	}
	public String getHidden_nmComercial_perfil_servico() {
		return hidden_nmComercial_perfil_servico;
	}
	public void setHidden_nmComercial_perfil_servico(String hidden_nmComercial_perfil_servico) {
		this.hidden_nmComercial_perfil_servico = hidden_nmComercial_perfil_servico;
	}
	public String getChkTodos() {
		return chkTodos;
	}
	public void setChkTodos(String chkTodos) {
		this.chkTodos = chkTodos;
	}

	public String getPerfil_cb() {
		return perfil_cb;
	}
	public void setPerfil_cb(String perfil_cb) {
		this.perfil_cb = perfil_cb;
	}
	public String[] getPerfilSelecionados() {
		return perfilSelecionados;
	}
	public void setPerfilSelecionados(String[] perfilSelecionados) {
		this.perfilSelecionados = perfilSelecionados;
	}

	public PerfilSCA[] getArrayPerfil() {
		return arrayPerfil;
	}
	public void setArrayPerfil(PerfilSCA[] arrayPerfil) {
		this.arrayPerfil = arrayPerfil;
	}
	public String getLarguraPopup() {
		return larguraPopup;
	}
	public void setLarguraPopup(String larguraPopup) {
		this.larguraPopup = larguraPopup;
	}
	public String getAlturaPopup() {
		return alturaPopup;
	}
	public void setAlturaPopup(String alturaPopup) {
		this.alturaPopup = alturaPopup;
	}
	public String getSelect_plataforma() {
		return select_plataforma;
	}
	public void setSelect_plataforma(String select_plataforma) {
		this.select_plataforma = select_plataforma;
	}
	public String getSelect_tecnologia() {
		return select_tecnologia;
	}
	public void setSelect_tecnologia(String select_tecnologia) {
		this.select_tecnologia = select_tecnologia;
	}
	public String getSelect_ufs() {
		return select_ufs;
	}
	public void setSelect_ufs(String select_ufs) {
		this.select_ufs = select_ufs;
	}
	public String getNmPerfilSCA() {
		return nmPerfilSCA;
	}
	public void setNmPerfilSCA(String nmPerfilSCA) {
		this.nmPerfilSCA = nmPerfilSCA;
	}
	public String getTxtnmComPlanoPerfilPop() {
		return txtnmComPlanoPerfilPop;
	}
	public void setTxtnmComPlanoPerfilPop(String txtnmComPlanoPerfilPop) {
		this.txtnmComPlanoPerfilPop = txtnmComPlanoPerfilPop;
	}
	public String getRadioPlano() {
		return radioPlano;
	}
	public void setRadioPlano(String radioPlano) {
		this.radioPlano = radioPlano;
	}
	public String getRadioServico() {
		return radioServico;
	}
	public void setRadioServico(String radioServico) {
		this.radioServico = radioServico;
	}
	public String getCkb_desativacao() {
		return ckb_desativacao;
	}
	public void setCkb_desativacao(String ckb_desativacao) {
		this.ckb_desativacao = ckb_desativacao;
	}
	public String getCkb_ativacao() {
		return ckb_ativacao;
	}
	public void setCkb_ativacao(String ckb_ativacao) {
		this.ckb_ativacao = ckb_ativacao;
	}
	public String getCkb_consulta() {
		return ckb_consulta;
	}
	public void setCkb_consulta(String ckb_consulta) {
		this.ckb_consulta = ckb_consulta;
	}
	public String getTxtnmComServicoPerfilPop() {
		return txtnmComServicoPerfilPop;
	}
	public void setTxtnmComServicoPerfilPop(String txtnmComServicoPerfilPop) {
		this.txtnmComServicoPerfilPop = txtnmComServicoPerfilPop;
	}
	public String[] getNmComercialSelecionados() {
		return nmComercialSelecionados;
	}
	public void setNmComercialSelecionados(String[] nmComercialSelecionados) {
		this.nmComercialSelecionados = nmComercialSelecionados;
	}
	public ResultadoBuscarListaPlanoListaPlanoPlano[] getPlanoLista() {
		return planoLista;
	}
	public void setPlanoLista(ResultadoBuscarListaPlanoListaPlanoPlano[] planoLista) {
		this.planoLista = planoLista;
	}
	public String getBotao_gravar_restricoes_plano_servico() {
		return botao_gravar_restricoes_plano_servico;
	}
	public void setBotao_gravar_restricoes_plano_servico(String botao_gravar_restricoes_plano_servico) {
		this.botao_gravar_restricoes_plano_servico = botao_gravar_restricoes_plano_servico;
	}
	public Long getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}
	public PerfilSCA[] getIdPerfilOrigem() {
		return idPerfilOrigem;
	}
	public void setIdPerfilOrigem(PerfilSCA[] idPerfilOrigem) {
		this.idPerfilOrigem = idPerfilOrigem;
	}
	public PerfilSCA[] getIdPerfilDestino() {
		return idPerfilDestino;
	}
	public void setIdPerfilDestino(PerfilSCA[] idPerfilDestino) {
		this.idPerfilDestino = idPerfilDestino;
	}
	public String getBotao_pesquisar_acesso_plano_servico() {
		return botao_pesquisar_acesso_plano_servico;
	}
	public void setBotao_pesquisar_acesso_plano_servico(String botao_pesquisar_acesso_plano_servico) {
		this.botao_pesquisar_acesso_plano_servico = botao_pesquisar_acesso_plano_servico;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getCkb_inRestricaoConsulta() {
		return ckb_inRestricaoConsulta;
	}
	public void setCkb_inRestricaoConsulta(String ckb_inRestricaoConsulta) {
		this.ckb_inRestricaoConsulta = ckb_inRestricaoConsulta;
	}
	public String getCkb_inRestricaoAtivacao() {
		return ckb_inRestricaoAtivacao;
	}
	public void setCkb_inRestricaoAtivacao(String ckb_inRestricaoAtivacao) {
		this.ckb_inRestricaoAtivacao = ckb_inRestricaoAtivacao;
	}
	public String getCkb_inRestricaoDesativacao() {
		return ckb_inRestricaoDesativacao;
	}
	public void setCkb_inRestricaoDesativacao(String ckb_inRestricaoDesativacao) {
		this.ckb_inRestricaoDesativacao = ckb_inRestricaoDesativacao;
	}
	public ResultadoBuscarListaServicoListaServicoRetornoServico[] getRetornoServicoLista() {
		return retornoServicoLista;
	}
	public void setRetornoServicoLista(ResultadoBuscarListaServicoListaServicoRetornoServico[] retornoServicoLista) {
		this.retornoServicoLista = retornoServicoLista;
	}
	public ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] getAcessoPlanoLista() {
		return acessoPlanoLista;
	}
	public void setAcessoPlanoLista(ResultadoBuscarListaAcessoPlanoListaAcessoPlanoAcessoPlano[] acessoPlanoLista) {
		this.acessoPlanoLista = acessoPlanoLista;
	}

	public Map<Long, ProfileSys> getPerfilScaMap() {
		return perfilScaMap;
	}
	public void setPerfilScaMap(Map<Long, ProfileSys> perfilScaMap) {
		this.perfilScaMap = perfilScaMap;
	}
	public Map<Long, String> getPerfilSCA() {
		return perfilSCA;
	}
	public void setPerfilSCA(Map<Long, String> perfilSCA) {
		this.perfilSCA = perfilSCA;
	}
	public Map<Long, String> getListaPerfilSCA() {
		return listaPerfilSCA;
	}
	public void setListaPerfilSCA(Map<Long, String> listaPerfilSCA) {
		this.listaPerfilSCA = listaPerfilSCA;
	}
	public ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] getAcessoServicoLista() {
		return acessoServicoLista;
	}
	public void setAcessoServicoLista(
			ResultadoBuscarListaAcessoServicoListaAcessoServicoAcessoServico[] acessoServicoLista) {
		this.acessoServicoLista = acessoServicoLista;
	}
	public Long getIdPerfilOrigemCopiarRestrAcesso() {
		return idPerfilOrigemCopiarRestrAcesso;
	}
	public void setIdPerfilOrigemCopiarRestrAcesso(Long idPerfilOrigemCopiarRestrAcesso) {
		this.idPerfilOrigemCopiarRestrAcesso = idPerfilOrigemCopiarRestrAcesso;
	}
	public Long getIdPerfilDestinoCopiarRestrAcesso() {
		return idPerfilDestinoCopiarRestrAcesso;
	}
	public void setIdPerfilDestinoCopiarRestrAcesso(Long idPerfilDestinoCopiarRestrAcesso) {
		this.idPerfilDestinoCopiarRestrAcesso = idPerfilDestinoCopiarRestrAcesso;
	}
	public Long getIdAcessoPlanoExcluirPlano() {
		return idAcessoPlanoExcluirPlano;
	}
	public void setIdAcessoPlanoExcluirPlano(Long idAcessoPlanoExcluirPlano) {
		this.idAcessoPlanoExcluirPlano = idAcessoPlanoExcluirPlano;
	}
	public String getInRestricaoConsultaExcluir() {
		return inRestricaoConsultaExcluir;
	}
	public void setInRestricaoConsultaExcluir(String inRestricaoConsultaExcluir) {
		this.inRestricaoConsultaExcluir = inRestricaoConsultaExcluir;
	}
	public String getInRestricaoAtivacaoExcluir() {
		return inRestricaoAtivacaoExcluir;
	}
	public void setInRestricaoAtivacaoExcluir(String inRestricaoAtivacaoExcluir) {
		this.inRestricaoAtivacaoExcluir = inRestricaoAtivacaoExcluir;
	}
	public String getInRestricaoDesativacaoExcluir() {
		return inRestricaoDesativacaoExcluir;
	}
	public void setInRestricaoDesativacaoExcluir(String inRestricaoDesativacaoExcluir) {
		this.inRestricaoDesativacaoExcluir = inRestricaoDesativacaoExcluir;
	}
	public Long getIdAcessoServicoExcluirServico() {
		return idAcessoServicoExcluirServico;
	}
	public void setIdAcessoServicoExcluirServico(Long idAcessoServicoExcluirServico) {
		this.idAcessoServicoExcluirServico = idAcessoServicoExcluirServico;
	}
	public String getBotao_pesquisar_nome_comercial_plano_popup() {
		return botao_pesquisar_nome_comercial_plano_popup;
	}
	public void setBotao_pesquisar_nome_comercial_plano_popup(String botao_pesquisar_nome_comercial_plano_popup) {
		this.botao_pesquisar_nome_comercial_plano_popup = botao_pesquisar_nome_comercial_plano_popup;
	}
	
	
	
}