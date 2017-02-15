package extworkflw.RegistrarContato;

import org.apache.struts.action.ActionForm;

import extworkflw.RegistrarContato.RegistrarContatoController.ValorCampo;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.TipoEnderecoVODocument.TipoEnderecoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AtendimentoTipoComunicacaoVODocument.AtendimentoTipoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.ContaVODocument.ContaVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFListaContatosVODocument.WFListaContatosVO;
import br.com.vivo.fo.workflow.vo.impl.AtendimentoComunicacaoVODocumentImpl.AtendimentoComunicacaoVOImpl;
import br.com.vivo.fo.workflow.vo.impl.LinhaVODocumentImpl.LinhaVOImpl;

public class RegistrarContatoForm extends ActionForm {

	private static final long serialVersionUID = 7355652267311283679L;

	private String nmPais;
	private String sgUF;
	private AdmGrupoCamposVO[] admGrupoCamposVO;
	/*
	 * Variável responsável pela informação sobre existência ou ausência de forma
	 * de contato "Celular" presente na base. valor 1: existe forma de contato
	 * "Celular" na base valor 0: não existe forma de contato "Celular" na base
	 * Alteração referente à SM 517
	 */
	private String inCelular;
	private String idLinhaAtendimento;
	private String user;
	// selecionados
	private String tipoComunicacaoSelecionada;
	private String idTipoComunicacaoAbertura;
	private String contaSelecionada;
	private String linhaSelecionada;
	private String[] comunicacaoDisponivel;
	private String[] comunicacaoSelecionada;
	private String observacaoAtendimento;
	private String observacaoInsistencia;
	private String nomeContatoAlterado;
	private String scriptArvoreBaixa;
	private boolean flagAtendimentosCorrentes = false;
	private String idBaixa;
	private String idBaixaMensagem;
	private String observacaoFechamento;
	private String idCanal = ConstantesCRM.SVAZIO;
	private String idProcedencia;
	private String nmURL;
	private String scriptValidacao = ConstantesCRM.SVAZIO;
	private String scriptArvore = ConstantesCRM.SVAZIO;
	private String inResponsavelAbertura = null;
	private String inTipoPessoa = null;
	private String idUfOperadora = null;
	private String idSegmentacao = null;
	private String idTipoCarteira = null;
	private String idTipoLinha = null;
	private String idGrupoAbertura = null;
	private String idContato = null;
	private String nomeContato = null;
	private long idChamadaTelefonica = 0;
	private int abertura = 0;
	private String telContato = null;
	private String telContatoFrm = null;
	private String nrConta = null;
	private String nrLinha = null;
	private String idConta = null;
	private String idLinha = null;
	private String idClienteDePara = null;
	private String idPessoa = null;
	private String idUsuarioDePara = null;
	private String idDiscador = null;
	private String idAtendimento = null;
	private String nrProtocolo = null;
	private long idAtendimentoSituacao;
	private String nrProtocoloSituacao = ConstantesCRM.SVAZIO;
	private int idTipoReaberturaProcesso;
	private String nmTipo = null;
	private String qtDias = null;
	private String fila = ConstantesCRM.SVAZIO;
	private String abreProcessoReaRei = null;
	private String idTipoComunicacao = null;
	private String descricaoCompleta = null;
	private String textoPesquisa = null;
	private String idCampo = null;
	private String selecaoFormulario = null;
	private String scriptCarrega = null;
	private String carregaLinha = null;
	private String tipoOperacao = null;
	private String idFase = null;
	private String[] nrLinhasSelecionadas = null;

	// VO's
	private AtendimentoVO atendimentoVO;
	private AtendimentoVO atendimentoVOFacade;
	private WFListaContatosVO listaContatosVO = WFListaContatosVO.Factory.newInstance();

	// utilizado quando houver uma alteração no customer profile
	private AtendimentoVO atendimentoVOComunicacao = null;
	private FormularioVO formularioSalvarVO;
	private ContaVO contaSelecionadaVO;
	private AtendimentoTipoComunicacaoVO tipoComunicacaoSelecionadaVO;
	private AtendimentoVO atendimentoVOLista = null;
	private FormularioCampoVO formularioCampoVO;
	private EnderecoVO enderecoVO;
	private TipoEnderecoVO[] listaTipoEndereco;

	// idAtendimento do processo corrente a ser excluído
	private long idAtendimentoExclusao;
	// índice do array de processos correntes
	private int rowIndex;
	private int numeroCampos = 0;
	private ValorCampo[] valorCampo;

	private ListaDadosVO listaDadosVO = null;

	// default Constructor
	public RegistrarContatoForm() {
		contaSelecionadaVO = ContaVO.Factory.newInstance();
		contaSelecionadaVO.setLinhaVOArray(new LinhaVOImpl[0]);
		listaTipoEndereco = new TipoEnderecoVO[0];
		enderecoVO = EnderecoVO.Factory.newInstance();
		tipoComunicacaoSelecionadaVO = AtendimentoTipoComunicacaoVO.Factory.newInstance();
		tipoComunicacaoSelecionadaVO
				.setAtendimentoComunicacaoVOArray(new AtendimentoComunicacaoVOImpl[0]);
		comunicacaoSelecionada = new String[0];
		atendimentoVO = AtendimentoVO.Factory.newInstance();
		atendimentoVO.setWFAcaoVOArray(new WFAcaoVO[] { WFAcaoVO.Factory.newInstance() });
		listaDadosVO = ListaDadosVO.Factory.newInstance();
	}

	public void setEnderecoVO(EnderecoVO enderecoVO) {
		this.enderecoVO = enderecoVO;
	}

	public EnderecoVO getEnderecoVO() {
		if (this.enderecoVO == null) {
			this.enderecoVO = EnderecoVO.Factory.newInstance();
		}
		return this.enderecoVO;
	}

	public void setListaDadosVO(ListaDadosVO listaDadosVO) {
		this.listaDadosVO = listaDadosVO;
	}

	public ListaDadosVO getListaDadosVO() {
		return this.listaDadosVO;
	}

	public void setNrLinhasSelecionadas(String[] nrLinhasSelecionadas) {
		this.nrLinhasSelecionadas = nrLinhasSelecionadas;
	}

	public String[] getNrLinhasSelecionadas() {
		return this.nrLinhasSelecionadas;
	}

	public void setListaTipoEndereco(TipoEnderecoVO[] listaTipoEndereco) {
		this.listaTipoEndereco = listaTipoEndereco;
	}

	public TipoEnderecoVO[] getListaTipoEndereco() {
		return this.listaTipoEndereco;
	}

	public void setSelecaoFormulario(String selecaoFormulario) {
		this.selecaoFormulario = selecaoFormulario;
	}

	public String getSelecaoFormulario() {
		return this.selecaoFormulario;
	}

	public void setInTipoPessoa(String inTipoPessoa) {
		this.inTipoPessoa = inTipoPessoa;
	}

	public String getInTipoPessoa() {
		return this.inTipoPessoa;
	}

	public void setIdPessoa(String idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getIdPessoa() {
		return this.idPessoa;
	}

	public void setFormularioCampoVO(FormularioCampoVO formularioCampoVO) {
		this.formularioCampoVO = formularioCampoVO;
	}

	public FormularioCampoVO getFormularioCampoVO() {
		return this.formularioCampoVO;
	}

	public void setIdCampo(String idCampo) {
		this.idCampo = idCampo;
	}

	public String getIdCampo() {
		return this.idCampo;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	public String getTextoPesquisa() {
		return this.textoPesquisa;
	}

	public void setDescricaoCompleta(String descricaoCompleta) {
		this.descricaoCompleta = descricaoCompleta;
	}

	public String getDescricaoCompleta() {
		return this.descricaoCompleta;
	}

	public void setIdTipoComunicacao(String idTipoComunicacao) {
		this.idTipoComunicacao = idTipoComunicacao;
	}

	public String getIdTipoComunicacao() {
		return this.idTipoComunicacao;
	}

	public void setIdTipoComunicacaoAbertura(String idTipoComunicacaoAbertura) {
		this.idTipoComunicacaoAbertura = idTipoComunicacaoAbertura;
	}

	public String getIdTipoComunicacaoAbertura() {
		return this.idTipoComunicacaoAbertura;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getFila() {
		return this.fila;
	}

	public void setAbreProcessoReaRei(String abreProcessoReaRei) {
		this.abreProcessoReaRei = abreProcessoReaRei;
	}

	public String getAbreProcessoReaRei() {
		return this.abreProcessoReaRei;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getRowIndex() {
		return this.rowIndex;
	}

	public void setAtendimentoVOLista(AtendimentoVO atendimentoVOLista) {
		this.atendimentoVOLista = atendimentoVOLista;
	}

	public AtendimentoVO getAtendimentoVOLista() {
		return this.atendimentoVOLista;
	}

	public void setQtDias(String qtDias) {
		this.qtDias = qtDias;
	}

	public String getQtDias() {
		return this.qtDias;
	}

	public void setNmTipo(String nmTipo) {
		this.nmTipo = nmTipo;
	}

	public String getNmTipo() {
		return this.nmTipo;
	}

	public void setIdTipoReaberturaProcesso(int idTipoReaberturaProcesso) {
		this.idTipoReaberturaProcesso = idTipoReaberturaProcesso;
	}

	public int getIdTipoReaberturaProcesso() {
		return this.idTipoReaberturaProcesso;
	}

	public void setIdAtendimentoSituacao(long idAtendimentoSituacao) {
		this.idAtendimentoSituacao = idAtendimentoSituacao;
	}

	public long getIdAtendimentoSituacao() {
		return this.idAtendimentoSituacao;
	}

	public void setNrProtocoloSituacao(String nrProtocoloSituacao) {
		this.nrProtocoloSituacao = nrProtocoloSituacao;
	}

	public String getNrProtocoloSituacao() {
		return this.nrProtocoloSituacao;
	}

	public void setAbertura(int abertura) {
		this.abertura = abertura;
	}

	public int getAbertura() {
		return this.abertura;
	}

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getIdAtendimento() {
		return this.idAtendimento;
	}

	public void setNrProtocolo(String nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}

	public String getNrProtocolo() {
		if (this.nrProtocolo == null) {
			this.nrProtocolo = ConstantesCRM.SVAZIO;
		}
		return this.nrProtocolo;
	}

	public void setIdDiscador(String idDiscador) {
		this.idDiscador = idDiscador;
	}

	public String getIdDiscador() {
		return this.idDiscador;
	}

	public void setIdUsuarioDePara(String idUsuarioDePara) {
		this.idUsuarioDePara = idUsuarioDePara;
	}

	public String getIdUsuarioDePara() {
		return this.idUsuarioDePara;
	}

	public void setIdClienteDePara(String idClienteDePara) {
		this.idClienteDePara = idClienteDePara;
	}

	public String getIdClienteDePara() {
		return this.idClienteDePara;
	}

	public void setIdLinha(String idLinha) {
		this.idLinha = idLinha;
	}

	public String getIdLinha() {
		return this.idLinha;
	}

	public void setIdConta(String idConta) {
		this.idConta = idConta;
	}

	public String getIdConta() {
		return this.idConta;
	}

	public void setTipoOperacao(String tipoOperacao) {
		this.tipoOperacao = tipoOperacao;
	}

	public String getTipoOperacao() {
		return this.tipoOperacao;
	}

	public void setCarregaLinha(String carregaLinha) {
		this.carregaLinha = carregaLinha;
	}

	public String getCarregaLinha() {
		return this.carregaLinha;
	}

	public void setNrLinha(String nrLinha) {
		this.nrLinha = nrLinha;
	}

	public String getNrLinha() {
		return this.nrLinha;
	}

	public void setNrConta(String nrConta) {
		this.nrConta = nrConta;
	}

	public String getNrConta() {
		return this.nrConta;
	}

	public void setTelContato(String telContato) {
		this.telContato = telContato;
	}

	public String getTelContato() {
		return this.telContato;
	}

	public void setTelContatoFrm(String telContatoFrm) {
		this.telContatoFrm = telContatoFrm;
	}

	public String getTelContatoFrm() {
		return this.telContatoFrm;
	}

	public void setIdChamadaTelefonica(long idChamadaTelefonica) {
		this.idChamadaTelefonica = idChamadaTelefonica;
	}

	public long getIdChamadaTelefonica() {
		return this.idChamadaTelefonica;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public String getNomeContato() {
		return this.nomeContato;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setIdUfOperadora(String idUfOperadora) {
		this.idUfOperadora = idUfOperadora;
	}

	public String getIdUfOperadora() {
		return this.idUfOperadora;
	}

	public void setIdGrupoAbertura(String idGrupoAbertura) {
		this.idGrupoAbertura = idGrupoAbertura;
	}

	public String getIdGrupoAbertura() {
		return this.idGrupoAbertura;
	}

	public void setIdTipoLinha(String idTipoLinha) {
		this.idTipoLinha = idTipoLinha;
	}

	public String getIdTipoLinha() {
		return this.idTipoLinha;
	}

	public void setIdTipoCarteira(String idTipoCarteira) {
		this.idTipoCarteira = idTipoCarteira;
	}

	public String getIdTipoCarteira() {
		return this.idTipoCarteira;
	}

	public void setIdSegmentacao(String idSegmentacao) {
		this.idSegmentacao = idSegmentacao;
	}

	public String getIdSegmentacao() {
		return this.idSegmentacao;
	}

	public void setInResponsavelAbertura(String inResponsavelAbertura) {
		this.inResponsavelAbertura = inResponsavelAbertura;
	}

	public String getInResponsavelAbertura() {
		return this.inResponsavelAbertura;
	}

	public void setScriptArvore(String scriptArvore) {
		this.scriptArvore = scriptArvore;
	}

	public String getScriptArvore() {
		return this.scriptArvore;
	}

	public void setScriptCarrega(String scriptCarrega) {
		this.scriptCarrega = scriptCarrega;
	}

	public String getScriptCarrega() {
		return this.scriptCarrega;
	}

	public void setScriptValidacao(String scriptValidacao) {
		this.scriptValidacao = scriptValidacao;
	}

	public String getScriptValidacao() {
		return this.scriptValidacao;
	}

	public void setNmURL(String nmURL) {
		this.nmURL = nmURL;
	}

	public String getNmURL() {
		return this.nmURL;
	}

	public void setIdProcedencia(String idProcedencia) {
		this.idProcedencia = idProcedencia;
	}

	public String getIdProcedencia() {
		return this.idProcedencia;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
	}

	public String getIdCanal() {
		return this.idCanal;
	}

	public AtendimentoVO getAtendimentoVOComunicacao() {
		return this.atendimentoVOComunicacao;
	}

	public void setAtendimentoVOComunicacao(AtendimentoVO atendimentoVOComunicacao) {
		this.atendimentoVOComunicacao = atendimentoVOComunicacao;
	}

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setIdBaixaMensagem(String idBaixaMensagem) {
		this.idBaixaMensagem = idBaixaMensagem;
	}

	public String getIdBaixaMensagem() {
		return this.idBaixaMensagem;
	}

	public void setObservacaoFechamento(String observacaoFechamento) {
		this.observacaoFechamento = observacaoFechamento;
	}

	public String getObservacaoFechamento() {
		return this.observacaoFechamento;
	}

	public void setFlagAtendimentosCorrentes(boolean flagAtendimentosCorrentes) {
		this.flagAtendimentosCorrentes = flagAtendimentosCorrentes;
	}

	public boolean getFlagAtendimentosCorrentes() {
		return this.flagAtendimentosCorrentes;
	}

	public void setScriptArvoreBaixa(String scriptArvoreBaixa) {
		this.scriptArvoreBaixa = scriptArvoreBaixa;
	}

	public String getScriptArvoreBaixa() {
		return this.scriptArvoreBaixa;
	}

	public void setNomeContatoAlterado(String nomeContatoAlterado) {
		this.nomeContatoAlterado = nomeContatoAlterado;
	}

	public String getNomeContatoAlterado() {
		return this.nomeContatoAlterado;
	}

	public void setObservacaoInsistencia(String observacaoInsistencia) {
		this.observacaoInsistencia = observacaoInsistencia;
	}

	public String getObservacaoInsistencia() {
		return this.observacaoInsistencia;
	}

	public void setIdAtendimentoExclusao(long idAtendimentoExclusao) {
		this.idAtendimentoExclusao = idAtendimentoExclusao;
	}

	public long getIdAtendimentoExclusao() {
		return this.idAtendimentoExclusao;
	}

	public String getObservacaoAtendimento() {
		return this.observacaoAtendimento;
	}

	public void setObservacaoAtendimento(String x) {
		this.observacaoAtendimento = x;
	}

	public AtendimentoVO getAtendimentoVO() {
		return this.atendimentoVO;
	}

	public void setAtendimentoVO(AtendimentoVO atendimentoVO) {
		this.atendimentoVO = atendimentoVO;
	}

	public AtendimentoVO getAtendimentoVOFacade() {
		return this.atendimentoVOFacade;
	}

	public void setAtendimentoVOFacade(AtendimentoVO atendimentoVOFacade) {
		this.atendimentoVOFacade = atendimentoVOFacade;
	}

	public AtendimentoTipoComunicacaoVO getTipoComunicacaoSelecionadaVO() {
		return this.tipoComunicacaoSelecionadaVO;
	}

	public void setTipoComunicacaoSelecionadaVO(AtendimentoTipoComunicacaoVO tipoComunicacaoVO) {
		this.tipoComunicacaoSelecionadaVO = tipoComunicacaoVO;
	}

	public String[] getComunicacaoDisponivel() {
		return this.comunicacaoDisponivel;
	}

	public void setComunicacaoDisponivel(String[] comunicacaoDisponivel) {
		this.comunicacaoDisponivel = comunicacaoDisponivel;
	}

	public String[] getComunicacaoSelecionada() {
		return this.comunicacaoSelecionada;
	}

	public void setComunicacaoSelecionada(String[] comunicacaoSelecionada) {
		this.comunicacaoSelecionada = comunicacaoSelecionada;
	}

	public String getTipoComunicacaoSelecionada() {
		return this.tipoComunicacaoSelecionada;
	}

	public void setTipoComunicacaoSelecionada(String tipoComunicacaoSelecionada) {
		this.tipoComunicacaoSelecionada = tipoComunicacaoSelecionada;
	}

	public String getContaSelecionada() {
		return this.contaSelecionada;
	}

	public void setContaSelecionada(String contaSelecionada) {
		this.contaSelecionada = contaSelecionada;
	}

	public ContaVO getContaSelecionadaVO() {
		return this.contaSelecionadaVO;
	}

	public void setContaSelecionadaVO(ContaVO contaSelecionadaVO) {
		this.contaSelecionadaVO = contaSelecionadaVO;
	}

	public FormularioVO getFormularioSalvarVO() {
		return this.formularioSalvarVO;
	}

	public void setFormularioSalvarVO(FormularioVO formularioSalvarVO) {
		this.formularioSalvarVO = formularioSalvarVO;
	}

	public String getLinhaSelecionada() {
		return this.linhaSelecionada;
	}

	public void setLinhaSelecionada(String linhaSelecionada) {
		this.linhaSelecionada = linhaSelecionada;
	}

	public String getIdFase() {
		return this.idFase;
	}

	public void setIdFase(String idFase) {
		this.idFase = idFase;
	}

	public int getNumeroCampos() {
		return this.numeroCampos;
	}

	public void setNumeroCampos(int numeroCampos) {
		this.numeroCampos = numeroCampos;
	}

	public ValorCampo[] getValorCampo() {
		int numCampos = this.getNumeroCampos();
		if (valorCampo == null) {
			valorCampo = new ValorCampo[numCampos];
			for (int i = 0; i < valorCampo.length; i++) {
				valorCampo[i] = new ValorCampo();
				valorCampo[i].setValorArray(new String[0]);
			}
		}
		return (this.valorCampo);
	}

	public void setValorCampo(ValorCampo[] valorCampo) {
		this.valorCampo = valorCampo;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	public void setIdLinhaAtendimento(String idLinhaAtendimento) {
		this.idLinhaAtendimento = idLinhaAtendimento;
	}

	public String getIdLinhaAtendimento() {
		return this.idLinhaAtendimento;
	}

	public void setInCelular(String inCelular) {
		this.inCelular = inCelular;
	}

	public String getInCelular() {
		if (this.inCelular == null) {
			return "true";
		}
		return this.inCelular;
	}

	public void setListaContatosVO(WFListaContatosVO value) {
		this.listaContatosVO = value;
	}

	public WFListaContatosVO getListaContatosVO() {
		return this.listaContatosVO;
	}

	public void setAdmGrupoCamposVO(AdmGrupoCamposVO[] admGrupoCamposVO) {
		this.admGrupoCamposVO = admGrupoCamposVO;
	}

	public AdmGrupoCamposVO[] getAdmGrupoCamposVO() {
		if (this.admGrupoCamposVO == null) {
			this.admGrupoCamposVO = new AdmGrupoCamposVO[this.getNumeroCampos()];
		}
		return this.admGrupoCamposVO;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
	}

	public String getSgUF() {
		if (this.sgUF == null) {
			this.sgUF = ConstantesCRM.SVAZIO;
		}
		return this.sgUF;
	}

	public void setNmPais(String nmPais) {
		this.nmPais = nmPais;
	}

	public String getNmPais() {
		if (this.nmPais == null) {
			this.nmPais = ConstantesCRM.SVAZIO;
		}
		return this.nmPais;
	}
}