package admsistemas.admArvoreContato;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO.Palitagem;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorAnatelVODocument.AdmIndicadorAnatelVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmNomeContatoVODocument.AdmNomeContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmClassificacaoSMSVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFechamentoVODocument.AdmTipoFechamentoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoProcessoVODocument.AdmTipoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormArvoreContato extends ActionForm implements Serializable {

	private static final long serialVersionUID = 3855531192250050213L;
	private String[] arrayAdmGruposExistentes;
	private String[] arrayAdmGruposAssociados;
	private AdmGrupoVO[] admGruposExistentesVO;
	private AdmGrupoVO[] admGruposAssociadosVO;
	private br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoAnatelVO[] admPrazoAnatel;
	private String qtDiasPrazoAnatel;
	// private String nomeContato;
	private String descPesquisa;
	private String qtdCopia;
	private br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrioridadeVO[] admPrioridade;
	private br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoVO[] admPrazo;
	private AdmClassificacaoSMSVO[] listaClassificoesSMS;
	private String idContatoDestino;
	private String idContatoOrigem;
	private String msgError = "";
	private String mensagemNova;
	private String salvaedita;
	private String idPagina;
	private String dsPath;
	private String inPermitirParametrizacao;
	private AdmTipoProcessoVO[] listaProcesso;
	private AdmTipoRetornoContatoVO[] listaRetorno;
	private String idMensagemAtual;
	private String idTipoRetornoContatoAtual;
	private String inFolha;
	private String nomeTipo;
	private String msg;
	private AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO;
	private AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO;
	private String[] arrayAdmIndicadorAnatelExistente;
	private String[] arrayAdmIndicadorAnatelAssociado;
	private AdmTipoFechamentoVO[] listaFechamento;
	private AdmMensagemAvisoVO[] listaMensagem;
	private String vlPesoContato;
	private String qtDiasPrazoContato;
	private String idClassificacaoSMS;
	private String processoTec;
	private String mensagem;
	private String fechamento;
	private String inDisponibilidadeNovo;
	private String idNomeContatoEscolhido;
	private String[] arrayAdmNomeContato;
	private String nrNivel;
	private String inDisponibilidade;
	private String nmContato;
	private String idContatoPai;
	private String idNomeContato;
	private String sgRegraEncaminhamento;
	private String sgFluxoAtendimento;
	private AdmNomeContatoVO[] admNomeContatoVO;
	private String idContato;
	private AdmArvoreContainerVO arvoreContato;

	private String inProtocolo;
	private String inRelacionamento;
	private String dsContatoCanais;
	private String dsMsgExcecao;
	private String inSMS;
	private boolean inComprovanteCancelamento;

	private AdmContatoPalitagemVO admContatoPalitagemVO;
	private Palitagem dadosPalitagem;

	public void setInPermitirParametrizacao(String inPermitirParametrizacao) {
		this.inPermitirParametrizacao = inPermitirParametrizacao;
	}

	public String getInPermitirParametrizacao() {
		return this.inPermitirParametrizacao;
	}

	public void setInProtocolo(String inProtocolo) {
		this.inProtocolo = inProtocolo;
	}

	public String getInProtocolo() {
		return this.inProtocolo;
	}

	public void setInComprovanteCancelamento(boolean arg1) {
		this.inComprovanteCancelamento = arg1;
	}

	public boolean getInComprovanteCancelamento() {
		return this.inComprovanteCancelamento;
	}

	public void setInRelacionamento(String inRelacionamento) {
		this.inRelacionamento = inRelacionamento;
	}

	public String getInRelacionamento() {
		return this.inRelacionamento;
	}

	public void setDsContatoCanais(String dsContatoCanais) {
		this.dsContatoCanais = dsContatoCanais;
	}

	public String getDsContatoCanais() {
		return this.dsContatoCanais;
	}

	public void setDsMsgExcecao(String dsMsgExcecao) {
		this.dsMsgExcecao = dsMsgExcecao;
	}

	public String getDsMsgExcecao() {
		if (this.dsMsgExcecao == null) {
			this.dsMsgExcecao = ConstantesCRM.SVAZIO;
		}
		return this.dsMsgExcecao;
	}

	public void setInSMS(String inSMS) {
		this.inSMS = inSMS;
	}

	public String getInSMS() {
		return this.inSMS;
	}

	public FormArvoreContato() {
		arvoreContato = AdmArvoreContainerVO.Factory.newInstance();
	}

	public void setArvoreContato(AdmArvoreContainerVO arvoreContato) {
		this.arvoreContato = arvoreContato;
	}

	public AdmArvoreContainerVO getArvoreContato() {
		return this.arvoreContato;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setAdmNomeContatoVO(AdmNomeContatoVO[] admNomeContatoVO) {
		this.admNomeContatoVO = admNomeContatoVO;
	}

	public AdmNomeContatoVO[] getAdmNomeContatoVO() {
		return this.admNomeContatoVO;
	}

	public void setIdNomeContato(String idNomeContato) {
		this.idNomeContato = idNomeContato;
	}

	public String getIdNomeContato() {
		return this.idNomeContato;
	}

	public void setSgRegraEncaminhamento(String arg) {
		this.sgRegraEncaminhamento = arg;
	}

	public String getSgRegraEncaminhamento() {
		if (this.sgRegraEncaminhamento == null) {
			this.sgRegraEncaminhamento = ConstantesCRM.SVAZIO;
		}
		return this.sgRegraEncaminhamento;
	}

	public void setSgFluxoAtendimento(String arg) {
		this.sgFluxoAtendimento = arg;
	}

	public String getSgFluxoAtendimento() {
		if (this.sgFluxoAtendimento == null) {
			this.sgFluxoAtendimento = ConstantesCRM.SVAZIO;
		}
		return this.sgFluxoAtendimento;
	}

	public void setIdContatoPai(String idContatoPai) {
		this.idContatoPai = idContatoPai;
	}

	public String getIdContatoPai() {
		return this.idContatoPai;
	}

	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}

	public String getNmContato() {
		return this.nmContato;
	}

	public void setInDisponibilidade(String inDisponibilidade) {
		this.inDisponibilidade = inDisponibilidade;
	}

	public String getInDisponibilidade() {
		return this.inDisponibilidade;
	}

	public void setNrNivel(String nrNivel) {
		this.nrNivel = nrNivel;
	}

	public String getNrNivel() {
		return this.nrNivel;
	}

	public void setArrayAdmNomeContato(String[] arrayAdmNomeContato) {
		this.arrayAdmNomeContato = arrayAdmNomeContato;
	}

	public String[] getArrayAdmNomeContato() {
		if (this.arrayAdmNomeContato == null || this.arrayAdmNomeContato.length == 0) {
			this.arrayAdmNomeContato = new String[1];
		}
		return this.arrayAdmNomeContato;
	}

	public void setIdNomeContatoEscolhido(String idNomeContatoEscolhido) {
		this.idNomeContatoEscolhido = idNomeContatoEscolhido;
	}

	public String getIdNomeContatoEscolhido() {
		return this.idNomeContatoEscolhido;
	}

	public void setInDisponibilidadeNovo(String inDisponibilidadeNovo) {
		this.inDisponibilidadeNovo = inDisponibilidadeNovo;
	}

	public String getInDisponibilidadeNovo() {
		return this.inDisponibilidadeNovo;
	}

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}

	public String getFechamento() {
		return this.fechamento;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setProcessoTec(String processoTec) {
		this.processoTec = processoTec;
	}

	public String getProcessoTec() {
		return this.processoTec;
	}

	public void setQtDiasPrazoContato(String qtDiasPrazoContato) {
		this.qtDiasPrazoContato = qtDiasPrazoContato;
	}

	public String getQtDiasPrazoContato() {
		return this.qtDiasPrazoContato;
	}

	public void setIdClassificacaoSMS(String arg) {
		this.idClassificacaoSMS = arg;
	}

	public String getIdClassificacaoSMS() {
		if (this.idClassificacaoSMS == null) {
			this.idClassificacaoSMS = ConstantesCRM.SVAZIO;
		}
		return this.idClassificacaoSMS;
	}

	public void setVlPesoContato(String vlPesoContato) {
		this.vlPesoContato = vlPesoContato;
	}

	public String getVlPesoContato() {
		return this.vlPesoContato;
	}

	public void setListaMensagem(AdmMensagemAvisoVO[] listaMensagem) {
		this.listaMensagem = listaMensagem;
	}

	public AdmMensagemAvisoVO[] getListaMensagem() {
		return this.listaMensagem;
	}

	public void setListaFechamento(AdmTipoFechamentoVO[] listaFechamento) {
		this.listaFechamento = listaFechamento;
	}

	public AdmTipoFechamentoVO[] getListaFechamento() {
		return this.listaFechamento;
	}

	public void setArrayAdmIndicadorAnatelAssociado(String[] arrayAdmIndicadorAnatelAssociado) {
		this.arrayAdmIndicadorAnatelAssociado = arrayAdmIndicadorAnatelAssociado;
	}

	public String[] getArrayAdmIndicadorAnatelAssociado() {
		if (this.arrayAdmIndicadorAnatelAssociado == null
				|| this.arrayAdmIndicadorAnatelAssociado.length == 0) {
			this.arrayAdmIndicadorAnatelAssociado = new String[1];
		}
		return this.arrayAdmIndicadorAnatelAssociado;
	}

	public void setArrayAdmIndicadorAnatelExistente(String[] arrayAdmIndicadorAnatelExistente) {
		this.arrayAdmIndicadorAnatelExistente = arrayAdmIndicadorAnatelExistente;
	}

	public String[] getArrayAdmIndicadorAnatelExistente() {
		if (this.arrayAdmIndicadorAnatelExistente == null
				|| this.arrayAdmIndicadorAnatelExistente.length == 0) {
			this.arrayAdmIndicadorAnatelExistente = new String[1];
		}
		return this.arrayAdmIndicadorAnatelExistente;
	}

	public void setAdmIndicadoresAnatelAssociadosVO(
			AdmIndicadorAnatelVO[] admIndicadoresAnatelAssociadosVO) {
		this.admIndicadoresAnatelAssociadosVO = admIndicadoresAnatelAssociadosVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelAssociadosVO() {
		return this.admIndicadoresAnatelAssociadosVO;
	}

	public void setAdmIndicadoresAnatelExistentesVO(
			AdmIndicadorAnatelVO[] admIndicadoresAnatelExistentesVO) {
		this.admIndicadoresAnatelExistentesVO = admIndicadoresAnatelExistentesVO;
	}

	public AdmIndicadorAnatelVO[] getAdmIndicadoresAnatelExistentesVO() {
		return this.admIndicadoresAnatelExistentesVO;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		if (this.msg == null) {
			this.msg = ConstantesCRM.SVAZIO;
		}
		return this.msg;
	}

	public void setNomeTipo(String nomeTipo) {
		this.nomeTipo = nomeTipo;
	}

	public String getNomeTipo() {
		if (this.nomeTipo == null) {
			this.nomeTipo = ConstantesCRM.SVAZIO;
		}
		return this.nomeTipo;
	}

	public void setInFolha(String inFolha) {
		this.inFolha = inFolha;
	}

	public String getInFolha() {
		if (this.inFolha == null) {
			this.inFolha = ConstantesCRM.SVAZIO;
		}
		return this.inFolha;
	}

	public void setIdTipoRetornoContatoAtual(String idTipoRetornoContatoAtual) {
		this.idTipoRetornoContatoAtual = idTipoRetornoContatoAtual;
	}

	public String getIdTipoRetornoContatoAtual() {
		if (this.idTipoRetornoContatoAtual == null) {
			this.idTipoRetornoContatoAtual = ConstantesCRM.SVAZIO;
		}
		return this.idTipoRetornoContatoAtual;
	}

	public void setIdMensagemAtual(String idMensagemAtual) {
		this.idMensagemAtual = idMensagemAtual;
	}

	public String getIdMensagemAtual() {
		if (this.idMensagemAtual == null) {
			this.idMensagemAtual = ConstantesCRM.SVAZIO;
		}
		return this.idMensagemAtual;
	}

	public void setListaRetorno(AdmTipoRetornoContatoVO[] listaRetorno) {
		this.listaRetorno = listaRetorno;
	}

	public AdmTipoRetornoContatoVO[] getListaRetorno() {
		return this.listaRetorno;
	}

	public void setListaProcesso(AdmTipoProcessoVO[] listaProcesso) {
		this.listaProcesso = listaProcesso;
	}

	public AdmTipoProcessoVO[] getListaProcesso() {
		return this.listaProcesso;
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		if (this.dsPath == null) {
			this.dsPath = ConstantesCRM.SVAZIO;
		}
		return this.dsPath;
	}

	public void setIdPagina(String idPagina) {
		this.idPagina = idPagina;
	}

	public String getIdPagina() {
		if (this.idPagina == null) {
			this.idPagina = ConstantesCRM.SVAZIO;
		}
		return this.idPagina;
	}

	public void setSalvaedita(String salvaedita) {
		this.salvaedita = salvaedita;
	}

	public String getSalvaedita() {
		if (this.salvaedita == null) {
			this.salvaedita = ConstantesCRM.SVAZIO;
		}
		return this.salvaedita;
	}

	public void setMensagemNova(String mensagemNova) {
		this.mensagemNova = mensagemNova;
	}

	public String getMensagemNova() {
		if (this.mensagemNova == null) {
			this.mensagemNova = ConstantesCRM.SVAZIO;
		}
		return this.mensagemNova;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		if (this.msgError == null) {
			this.msgError = ConstantesCRM.SVAZIO;
		}
		return this.msgError;
	}

	public void setIdContatoOrigem(String idContatoOrigem) {
		this.idContatoOrigem = idContatoOrigem;
	}

	public String getIdContatoOrigem() {
		if (this.idContatoOrigem == null) {
			this.idContatoOrigem = ConstantesCRM.SVAZIO;
		}
		return this.idContatoOrigem;
	}

	public void setIdContatoDestino(String idContatoDestino) {
		this.idContatoDestino = idContatoDestino;
	}

	public String getIdContatoDestino() {
		if (this.idContatoDestino == null) {
			this.idContatoDestino = ConstantesCRM.SVAZIO;
		}
		return this.idContatoDestino;
	}

	public void setAdmPrazo(
			br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoVO[] admPrazo) {
		this.admPrazo = admPrazo;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoVO[] getAdmPrazo() {
		return this.admPrazo;
	}

	public void setAdmPrioridade(
			br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrioridadeVO[] admPrioridade) {
		this.admPrioridade = admPrioridade;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrioridadeVO[] getAdmPrioridade() {
		return this.admPrioridade;
	}

	public void setListaClassificoesSMS(AdmClassificacaoSMSVO[] arg) {
		this.listaClassificoesSMS = arg;
	}

	public AdmClassificacaoSMSVO[] getListaClassificoesSMS() {
		if (this.listaClassificoesSMS == null) {
			this.listaClassificoesSMS = new AdmClassificacaoSMSVO[0];
		}
		return this.listaClassificoesSMS;
	}

	public void setQtdCopia(String qtdCopia) {
		this.qtdCopia = qtdCopia;
	}

	public String getQtdCopia() {
		if (this.qtdCopia == null) {
			this.qtdCopia = ConstantesCRM.SVAZIO;
		}
		return this.qtdCopia;
	}

	public void setDescPesquisa(String descPesquisa) {
		this.descPesquisa = descPesquisa;
	}

	public String getDescPesquisa() {
		if (this.descPesquisa == null) {
			this.descPesquisa = "";
		}
		return this.descPesquisa;
	}

	public void setQtDiasPrazoAnatel(String qtDiasPrazoAnatel) {
		this.qtDiasPrazoAnatel = qtDiasPrazoAnatel;
	}

	public String getQtDiasPrazoAnatel() {
		return this.qtDiasPrazoAnatel;
	}

	public void setAdmPrazoAnatel(
			br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoAnatelVO[] admPrazoAnatel) {
		this.admPrazoAnatel = admPrazoAnatel;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO.AdmPrazoAnatelVO[] getAdmPrazoAnatel() {
		return this.admPrazoAnatel;
	}

	public void setAdmGruposAssociadosVO(AdmGrupoVO[] admGruposAssociadosVO) {
		this.admGruposAssociadosVO = admGruposAssociadosVO;
	}

	public AdmGrupoVO[] getAdmGruposAssociadosVO() {
		return this.admGruposAssociadosVO;
	}

	public void setAdmGruposExistentesVO(AdmGrupoVO[] admGruposExistentesVO) {
		this.admGruposExistentesVO = admGruposExistentesVO;
	}

	public AdmGrupoVO[] getAdmGruposExistentesVO() {
		return this.admGruposExistentesVO;
	}

	public void setArrayAdmGruposAssociados(String[] arrayAdmGruposAssociados) {
		this.arrayAdmGruposAssociados = arrayAdmGruposAssociados;
	}

	public String[] getArrayAdmGruposAssociados() {
		if (this.arrayAdmGruposAssociados == null || this.arrayAdmGruposAssociados.length == 0) {
			this.arrayAdmGruposAssociados = new String[1];
		}
		return this.arrayAdmGruposAssociados;
	}

	public void setArrayAdmGruposExistentes(String[] arrayAdmGruposExistentes) {
		this.arrayAdmGruposExistentes = arrayAdmGruposExistentes;
	}

	public String[] getArrayAdmGruposExistentes() {
		if (this.arrayAdmGruposExistentes == null || this.arrayAdmGruposExistentes.length == 0) {
			this.arrayAdmGruposExistentes = new String[1];
		}
		return this.arrayAdmGruposExistentes;
	}

	public AdmContatoPalitagemVO getAdmContatoPalitagemVO() {
		if (this.admContatoPalitagemVO == null) {
			this.admContatoPalitagemVO = AdmContatoPalitagemVO.Factory.newInstance();
		}
		return this.admContatoPalitagemVO;
	}

	public void setAdmContatoPalitagemVO(AdmContatoPalitagemVO arg1) {
		this.admContatoPalitagemVO = arg1;
	}

	public Palitagem getDadosPalitagem() {
		if (this.dadosPalitagem == null) {
			this.dadosPalitagem = Palitagem.Factory.newInstance();
			this.dadosPalitagem.addNewAdmProcedenciaVO();
			this.dadosPalitagem.addNewSistemaVO();
			this.dadosPalitagem.addNewServico();
		}
		return this.dadosPalitagem;
	}

	public void setDadosPalitagem(Palitagem arg1) {
		this.dadosPalitagem = arg1;
	}

}