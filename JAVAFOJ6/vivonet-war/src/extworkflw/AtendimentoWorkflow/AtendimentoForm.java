package extworkflw.AtendimentoWorkflow;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO;
import br.com.vivo.fo.admsistemas.vo.AdmPerguntaVODocument.AdmPerguntaVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreEncerramentoVODocument.ArvoreEncerramentoVO;
import br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument.EncerramentoVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.TipoEnderecoVODocument.TipoEnderecoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowAgendamentoVODocument.AtendimentoWorkflowAgendamentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowEncerramentoVODocument.AtendimentoWorkflowEncerramentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowInsistenciaVODocument.AtendimentoWorkflowInsistenciaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowPesquisaVODocument.AtendimentoWorkflowPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument.AtendimentoWorkflowTecnicoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesHistVODocument.AtendimentoWorkflowTestesHistVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesQuestVODocument.AtendimentoWorkflowTestesQuestVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesVODocument.AtendimentoWorkflowTestesVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument.AtendimentoWorkflowVO;
import br.com.vivo.fo.workflow.vo.WFAcaoOrdemVendaVODocument.WFAcaoOrdemVendaVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFDocumentoTecnicoPesquisaVODocument.WFDocumentoTecnicoPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

public class AtendimentoForm extends ActionForm {

	private static final long serialVersionUID = 190192381237L;
	private String idAgrupamentoEstadoTpfuturo;
	private String dsMotivo;
	private int docTec;
	private String dsTipoComunicacao;
	private String idBaixaMensagem;
	private String scriptValidacao;
	private String idTipoComunicacao;
	private String idContato;
	private String hora;
	private boolean fechamentoMassa;
	private AdmGrupoCamposVO[] admGrupoCamposVO;

	// atributos que vem na URL -> idUsuario=x&idAtividade=y
	// idiaz 18.11.2004
	private String idAtendimento;
	private String idAtividade = ConstantesCRM.SVAZIO;
	private String inCRI = ConstantesCRM.SZERO;
	private String inRC = ConstantesCRM.SZERO;
	private String idTabelaMotivo = ConstantesCRM.SVAZIO;
	private String idProcedencia = ConstantesCRM.SVAZIO;
	private String qtInsistencia = ConstantesCRM.SVAZIO;
	private String numGruposAbertura = ConstantesCRM.SVAZIO;

	private String grupoSel = ConstantesCRM.SVAZIO;
	private String usuarioSel = ConstantesCRM.SVAZIO;
	private String motivoSel = ConstantesCRM.SVAZIO;
	private String comentario = ConstantesCRM.SVAZIO;
	private String scriptArvoreBaixa;
	private String observacaoTestes = ConstantesCRM.SVAZIO;

	private AtendimentoVO[] atendimentosVO;
	private WFGrupoVO[] wFGruposVO;
	private UsuarioVIVO[] usuarioVIVO;
	private WFMotivoVO[] wFMotivosVO;
	private WFAcaoRetornoVO wFAcaoRetornoVO;

	private WFDocumentoTecnicoPesquisaVO wFDocumentoTecnicoPesquisaVO;
	private AtendimentoWorkflowTecnicoDocVO atendimentoWorkflowTecnicoDocVO;
	private AtendimentoWorkflowTecnicoDocVO[] documentosAssociados;
	private WFDocumentoTecnicoPesquisaVO filtroAssociados;
	private String[] documentosId;

	private ArvoreEncerramentoVO arvoreEncerramentoVO;
	private AtendimentoWorkflowVO atendimentoWorkflowVO;
	private AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO;

	private EnderecoVO enderecoVO;
	private TipoEnderecoVO[] listaTipoEndereco;
	private String sgUF;
	private String nmPais;

	private int numeroCampos = 0;
	private int tamanho;
	private ValorCampo[] valorCampo;

	// armazena o indice do array do questionário da pesquisa de satisfação
	private int arrayIndex;
	private int numPergunta;
	private AdmPerguntaVO admPerguntaVO;
	private String[] respostaPesqSatisfa;
	private String[] perguntas;

	public String[] getPerguntas() {
		return perguntas;
	}

	public void setPerguntas(String[] perguntas) {
		this.perguntas = perguntas;
	}

	private String notaPesqSatisfa;
	private String observacaoPesqSatisfa;

	private String idBaixa;
	private String documentoAssociado;

	private String fila = ConstantesCRM.SVAZIO;

	private String textoPesquisa = null;
	private String idCampo = null;
	private String selecaoFormulario = null;

	private String[] idCampoTestes;

	// Ordem de Venda - NFe

	private WFAcaoOrdemVendaVO wFAcaoOrdemVendaVO;

	public void setWFAcaoOrdemVendaVO(WFAcaoOrdemVendaVO wFAcaoOrdemVendaVO) {
		this.wFAcaoOrdemVendaVO = wFAcaoOrdemVendaVO;
	}

	public WFAcaoOrdemVendaVO getWFAcaoOrdemVendaVO() {
		return this.wFAcaoOrdemVendaVO;
	}

	public void setIdCampoTestes(String[] idCampoTestes) {
		this.idCampoTestes = idCampoTestes;
	}

	public String[] getIdCampoTestes() {
		return this.idCampoTestes;
	}

	private String[] dsCampoTestes;

	public void setDsCampoTestes(String[] dsCampoTestes) {
		this.dsCampoTestes = dsCampoTestes;
	}

	public String[] getDsCampoTestes() {
		return this.dsCampoTestes;
	}

	public boolean getFechamentoMassa() {
		return this.fechamentoMassa;
	}

	public void setFechamentoMassa(boolean fechamentoMassa) {
		this.fechamentoMassa = fechamentoMassa;
	}

	public AtendimentoForm() {
		// inicicializa objeto atendimentosVO
		this.atendimentosVO = new AtendimentoVO[0];

		// inicicializa objeto arvoreEncerramentoVO
		this.arvoreEncerramentoVO = ArvoreEncerramentoVO.Factory.newInstance();
		this.arvoreEncerramentoVO.setEncerramentoVO(EncerramentoVO.Factory.newInstance());

		WFAcaoOrdemVendaVO wf = WFAcaoOrdemVendaVO.Factory.newInstance();
		wf.addNewDadosOrdemVendaVO();
		wf.getDadosOrdemVendaVO().addNewCLIENTE();
		// wf.getDadosOrdemVendaVO().addNewORDEMCAB();
		// wf.getDadosOrdemVendaVO().addNewORDEMITEM();
		this.wFAcaoOrdemVendaVO = wf;

		// inicializa VO para acoes com Documentos Associados
		this.wFDocumentoTecnicoPesquisaVO = WFDocumentoTecnicoPesquisaVO.Factory.newInstance();
		this.atendimentoWorkflowTecnicoDocVO = AtendimentoWorkflowTecnicoDocVO.Factory.newInstance();
		this.documentosAssociados = new AtendimentoWorkflowTecnicoDocVO[0];
		this.filtroAssociados = WFDocumentoTecnicoPesquisaVO.Factory.newInstance();
		this.filtroAssociados.setIdDocTecEstado(ConstantesCRM.SVAZIO);
		this.filtroAssociados.setComentario(ConstantesCRM.SVAZIO);
		this.filtroAssociados.setDtPeriodo1(ConstantesCRM.SVAZIO);
		this.filtroAssociados.setDtPeriodo2(ConstantesCRM.SVAZIO);
		this.filtroAssociados.setIdDocTecTipo(ConstantesCRM.SVAZIO);
		this.filtroAssociados.setNrDocumentoTecnico(ConstantesCRM.SVAZIO);
		this.filtroAssociados.setAssociados(ConstantesCRM.SVAZIO);

		// inicicializa objeto wFGruposVO
		this.wFGruposVO = new WFGrupoVO[0];

		// inicializa objeto wFMotivosVO;
		this.wFMotivosVO = new WFMotivoVO[0];

		// inicializa objeto wFAcaoRetornoVO
		this.wFAcaoRetornoVO = WFAcaoRetornoVO.Factory.newInstance();

		// inicicializa objeto usuarioVIVO
		this.usuarioVIVO = new UsuarioVIVO[0];

		this.listaTipoEndereco = new TipoEnderecoVO[0];
		this.enderecoVO = EnderecoVO.Factory.newInstance();

		// inicicializa objeto atendimentoWorkflowVO
		this.atendimentoWorkflowVO = AtendimentoWorkflowVO.Factory.newInstance();
		this.atendimentoWorkflowVO
				.setAtendimentoWorkflowAgendamentoVO(AtendimentoWorkflowAgendamentoVO.Factory.newInstance());
		this.atendimentoWorkflowVO.setAtendimentoWorkflowComumVO(AtendimentoWorkflowComumVO.Factory
				.newInstance());
		this.atendimentoWorkflowVO
				.setAtendimentoWorkflowEncerramentoVO(AtendimentoWorkflowEncerramentoVO.Factory
						.newInstance());
		this.atendimentoWorkflowVO
				.setAtendimentoWorkflowInsistenciaVO(AtendimentoWorkflowInsistenciaVO.Factory.newInstance());
		this.atendimentoWorkflowVO
				.setAtendimentoWorkflowPesquisaVO(AtendimentoWorkflowPesquisaVO.Factory.newInstance());
		this.atendimentoWorkflowVO.setAtendimentoWorkflowTecnicoVO(AtendimentoWorkflowTecnicoVO.Factory
				.newInstance());

		this.atendimentoWorkflowTestesVO = AtendimentoWorkflowTestesVO.Factory.newInstance();
		atendimentoWorkflowTestesVO
				.setAtendimentoWorkflowTestesHistVO(AtendimentoWorkflowTestesHistVO.Factory.newInstance());
		atendimentoWorkflowTestesVO
				.setAtendimentoWorkflowTestesQuestVO(AtendimentoWorkflowTestesQuestVO.Factory.newInstance());
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

	public void setListaTipoEndereco(TipoEnderecoVO[] listaTipoEndereco) {
		this.listaTipoEndereco = listaTipoEndereco;
	}

	public TipoEnderecoVO[] getListaTipoEndereco() {
		return this.listaTipoEndereco;
	}

	public void setSgUF(String sgUF) {
		this.sgUF = sgUF;
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

	public String getSgUF() {
		if (this.sgUF == null) {
			this.sgUF = ConstantesCRM.SVAZIO;
		}
		return this.sgUF;
	}

	public void setAdmGrupoCamposVO(AdmGrupoCamposVO[] admGrupoCamposVO) {
		this.admGrupoCamposVO = admGrupoCamposVO;
	}

	public AdmGrupoCamposVO[] getAdmGrupoCamposVO() {
		if (this.admGrupoCamposVO == null) {
			this.admGrupoCamposVO = new AdmGrupoCamposVO[0];
		}
		return this.admGrupoCamposVO;
	}

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getIdAtendimento() {
		return this.idAtendimento;
	}

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	private String inResponsavel = ConstantesCRM.SONE;

	public void setInResponsavel(String inResponsavel) {
		this.inResponsavel = inResponsavel;
	}

	public String getInResponsavel() {
		return this.inResponsavel;
	}

	public void setTextoPesquisa(String textoPesquisa) {
		this.textoPesquisa = textoPesquisa;
	}

	public String getTextoPesquisa() {
		return this.textoPesquisa;
	}

	public void setIdCampo(String idCampo) {
		this.idCampo = idCampo;
	}

	public String getIdCampo() {
		return this.idCampo;
	}

	public void setSelecaoFormulario(String selecaoFormulario) {
		this.selecaoFormulario = selecaoFormulario;
	}

	public String getSelecaoFormulario() {
		return this.selecaoFormulario;
	}

	public void setNumGruposAbertura(String numGruposAbertura) {
		this.numGruposAbertura = numGruposAbertura;
	}

	public String getNumGruposAbertura() {
		return this.numGruposAbertura;
	}

	private String inTratamentoUsuario = ConstantesCRM.SVAZIO;

	public void setInTratamentoUsuario(String inTratamentoUsuario) {
		this.inTratamentoUsuario = inTratamentoUsuario;
	}

	public String getInTratamentoUsuario() {
		return this.inTratamentoUsuario;
	}

	private String abaSolicitante = ConstantesCRM.SVAZIO;

	public void setAbaSolicitante(String abaSolicitante) {
		this.abaSolicitante = abaSolicitante;
	}

	public String getAbaSolicitante() {
		return this.abaSolicitante;
	}

	public String getIdAtividade() {
		return this.idAtividade;
	}

	public String getInCRI() {
		return this.inCRI;
	}

	public void setInCRI(String inCRI) {
		this.inCRI = inCRI;
	}

	public String getInRC() {
		return this.inRC;
	}

	public void setInRC(String inRC) {
		this.inRC = inRC;
	}

	public void setIdTabelaMotivo(String idTabelaMotivo) {
		this.idTabelaMotivo = idTabelaMotivo;
	}

	public String getIdTabelaMotivo() {
		return this.idTabelaMotivo;
	}

	public void setIdProcedencia(String idProcedencia) {
		this.idProcedencia = idProcedencia;
	}

	public String getIdProcedencia() {
		return this.idProcedencia;
	}

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	public void setDocumentoAssociado(String documentoAssociado) {
		this.documentoAssociado = documentoAssociado;
	}

	public String getDocumentoAssociado() {
		return this.documentoAssociado;
	}

	public void setQtInsistencia(String qtInsistencia) {
		this.qtInsistencia = qtInsistencia;
	}

	public String getQtInsistencia() {
		return this.qtInsistencia;
	}

	public void setObservacaoTestes(String observacaoTestes) {
		this.observacaoTestes = observacaoTestes;
	}

	public String getObservacaoTestes() {
		return this.observacaoTestes;
	}

	public void setWFDocumentoTecnicoPesquisaVO(
			WFDocumentoTecnicoPesquisaVO wFDocumentoTecnicoPesquisaVO) {
		this.wFDocumentoTecnicoPesquisaVO = wFDocumentoTecnicoPesquisaVO;
	}

	public WFDocumentoTecnicoPesquisaVO getWFDocumentoTecnicoPesquisaVO() {
		return this.wFDocumentoTecnicoPesquisaVO;
	}

	public void setAtendimentoWorkflowTecnicoDocVO(
			AtendimentoWorkflowTecnicoDocVO atendimentoWorkflowTecnicoDocVO) {
		this.atendimentoWorkflowTecnicoDocVO = atendimentoWorkflowTecnicoDocVO;
	}

	public AtendimentoWorkflowTecnicoDocVO getAtendimentoWorkflowTecnicoDocVO() {
		return this.atendimentoWorkflowTecnicoDocVO;
	}

	public void setDocumentosAssociados(AtendimentoWorkflowTecnicoDocVO[] documentosAssociados) {
		this.documentosAssociados = documentosAssociados;
	}

	public AtendimentoWorkflowTecnicoDocVO[] getDocumentosAssociados() {
		return this.documentosAssociados;
	}

	public void setFiltroAssociados(WFDocumentoTecnicoPesquisaVO filtroAssociados) {
		this.filtroAssociados = filtroAssociados;
	}

	public WFDocumentoTecnicoPesquisaVO getFiltroAssociados() {
		return this.filtroAssociados;
	}

	public void setDocumentosId(String[] documentosId) {
		this.documentosId = documentosId;
	}

	public String[] getDocumentosId() {
		return this.documentosId;
	}

	public void setAtendimentoWorkflowTestesVO(AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO) {
		this.atendimentoWorkflowTestesVO = atendimentoWorkflowTestesVO;
	}

	public AtendimentoWorkflowTestesVO getAtendimentoWorkflowTestesVO() {
		return this.atendimentoWorkflowTestesVO;
	}

	public int getNumeroCampos() {
		return this.numeroCampos;
	}

	public void setNumeroCampos(int numeroCampos) {
		this.numeroCampos = numeroCampos;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public int getArrayIndex() {
		return this.arrayIndex;
	}

	public void setArrayIndex(int arrayIndex) {
		this.arrayIndex = arrayIndex;
	}

	public int getNumPergunta() {
		return this.numPergunta;
	}

	public void setNumPergunta(int numPergunta) {
		this.numPergunta = numPergunta;
	}

	public ValorCampo[] getValorCampo() {
		if (valorCampo == null) {
			int numeroCampos = this.getNumeroCampos();
			if (numeroCampos == 0) {
				numeroCampos = 100;
			}
			valorCampo = new ValorCampo[numeroCampos];
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

	public void setGrupoSel(String grupoSel) {
		this.grupoSel = grupoSel;
	}

	public String getGrupoSel() {
		return this.grupoSel;
	}

	public void setUsuarioSel(String usuarioSel) {
		this.usuarioSel = usuarioSel;
	}

	public String getUsuarioSel() {
		return this.usuarioSel;
	}

	public void setMotivoSel(String motivoSel) {
		this.motivoSel = motivoSel;
	}

	public String getMotivoSel() {
		return this.motivoSel;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public void setWFAcaoRetornoVO(WFAcaoRetornoVO wFAcaoRetornoVO) {
		this.wFAcaoRetornoVO = wFAcaoRetornoVO;
	}

	public WFAcaoRetornoVO getWFAcaoRetornoVO() {
		return this.wFAcaoRetornoVO;
	}

	public void setAtendimentosVO(AtendimentoVO[] atendimentosVO) {
		this.atendimentosVO = atendimentosVO;
	}

	public AtendimentoVO[] getAtendimentosVO() {
		return this.atendimentosVO;
	}

	public void setArvoreEncerramentoVO(ArvoreEncerramentoVO arvoreEncerramentoVO) {
		this.arvoreEncerramentoVO = arvoreEncerramentoVO;
	}

	public ArvoreEncerramentoVO getArvoreEncerramentoVO() {
		return this.arvoreEncerramentoVO;
	}

	public void setWFGruposVO(WFGrupoVO[] wFGruposVO) {
		this.wFGruposVO = wFGruposVO;
	}

	public WFGrupoVO[] getWFGruposVO() {
		return this.wFGruposVO;
	}

	public void setScriptArvoreBaixa(String scriptArvoreBaixa) {
		this.scriptArvoreBaixa = scriptArvoreBaixa;
	}

	public String getScriptArvoreBaixa() {
		return this.scriptArvoreBaixa;
	}

	public void setWFMotivosVO(WFMotivoVO[] wFMotivosVO) {
		this.wFMotivosVO = wFMotivosVO;
	}

	public WFMotivoVO[] getWFMotivosVO() {
		return this.wFMotivosVO;
	}

	public void setUsuarioVIVO(UsuarioVIVO[] usuarioVIVO) {
		this.usuarioVIVO = usuarioVIVO;
	}

	public UsuarioVIVO[] getUsuarioVIVO() {
		return this.usuarioVIVO;
	}

	public void setAtendimentoWorkflowVO(AtendimentoWorkflowVO atendimentoWorkflowVO) {
		this.atendimentoWorkflowVO = atendimentoWorkflowVO;
	}

	public AtendimentoWorkflowVO getAtendimentoWorkflowVO() {
		return this.atendimentoWorkflowVO;
	}

	public void setRespostaPesqSatisfa(String[] respostaPesqSatisfa) {
		this.respostaPesqSatisfa = respostaPesqSatisfa;
	}

	public String[] getRespostaPesqSatisfa() {
		return this.respostaPesqSatisfa;
	}

	public void setAdmPerguntaVO(AdmPerguntaVO admPerguntaVO) {
		this.admPerguntaVO = admPerguntaVO;
	}

	public AdmPerguntaVO getAdmPerguntaVO() {
		return this.admPerguntaVO;
	}

	public void setNotaPesqSatisfa(String notaPesqSatisfa) {
		this.notaPesqSatisfa = notaPesqSatisfa;
	}

	public String getNotaPesqSatisfa() {
		return this.notaPesqSatisfa;
	}

	public void setObservacaoPesqSatisfa(String observacaoPesqSatisfa) {
		this.observacaoPesqSatisfa = observacaoPesqSatisfa;
	}

	public String getObservacaoPesqSatisfa() {
		return this.observacaoPesqSatisfa;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getHora() {
		return this.hora;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public String getFila() {
		return this.fila;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setIdTipoComunicacao(String idTipoComunicacao) {
		this.idTipoComunicacao = idTipoComunicacao;
	}

	public String getIdTipoComunicacao() {
		return this.idTipoComunicacao;
	}

	public void setScriptValidacao(String scriptValidacao) {
		this.scriptValidacao = scriptValidacao;
	}

	public String getScriptValidacao() {
		return this.scriptValidacao;
	}

	public void setIdBaixaMensagem(String idBaixaMensagem) {
		this.idBaixaMensagem = idBaixaMensagem;
	}

	public String getIdBaixaMensagem() {
		return this.idBaixaMensagem;
	}

	public void setDsTipoComunicacao(String dsTipoComunicacao) {
		this.dsTipoComunicacao = dsTipoComunicacao;
	}

	public String getDsTipoComunicacao() {
		return this.dsTipoComunicacao;
	}

	public void setDocTec(int docTec) {
		this.docTec = docTec;
	}

	public int getDocTec() {
		return this.docTec;
	}

	public void setDsMotivo(String dsMotivo) {
		this.dsMotivo = dsMotivo;
	}

	public String getDsMotivo() {
		if (dsMotivo == null) {
			dsMotivo = ConstantesCRM.SVAZIO;
		}
		return this.dsMotivo;
	}

	public void setIdAgrupamentoEstadoTpfuturo(String idAgrupamentoEstadoTpfuturo) {
		this.idAgrupamentoEstadoTpfuturo = idAgrupamentoEstadoTpfuturo;
	}

	public String getIdAgrupamentoEstadoTpfuturo() {
		if (this.idAgrupamentoEstadoTpfuturo == null) {
			this.idAgrupamentoEstadoTpfuturo = ConstantesCRM.SVAZIO;
		}
		return this.idAgrupamentoEstadoTpfuturo;
	}
}