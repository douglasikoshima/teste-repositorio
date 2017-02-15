package workflow.AtendimentoFechamentoMassa;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;

public class AtendimentoFechamentoMassaForm extends ActionForm {

	private static final long serialVersionUID = 31231235609373L;

	public AtendimentoFechamentoMassaForm() {

		// inicicializa objeto atendimentoInformacaoVO
		this.atendimentoInformacaoVO = AtendimentoInformacaoVO.Factory.newInstance();

		// inicicializa objeto usuarioVIVO
		this.usuarioVIVO = UsuarioVIVO.Factory.newInstance();

		// inicicializa objeto alertasVO
		this.alertasVO = new AlertaVO[0];

		// inicicializa objeto atdWFTecnicoDocsVO
		atdWFTecnicoDocsVO = new AtendimentoWorkflowTecnicoDocVO[0];

	}

	private String scriptArvoreBaixa;

	public void setScriptArvoreBaixa(String scriptArvoreBaixa) {
		this.scriptArvoreBaixa = scriptArvoreBaixa;
	}

	public String getScriptArvoreBaixa() {
		return this.scriptArvoreBaixa;
	}

	// idContato
	private String idContato = ConstantesCRM.SVAZIO;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	// idContato
	private String idContatoWF = ConstantesCRM.SVAZIO;

	public void setIdContatoWF(String idContatoWF) {
		this.idContatoWF = idContatoWF;
	}

	public String getIdContatoWF() {
		return this.idContatoWF;
	}

	// textoContato
	private String textoContato = ConstantesCRM.SVAZIO;

	public void setTextoContato(String textoContato) {
		this.textoContato = textoContato;
	}

	public String getTextoContato() {
		return this.textoContato;
	}

	// comentario
	private String comentario = ConstantesCRM.SVAZIO;

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getComentario() {
		return this.comentario;
	}

	// login
	private String login = ConstantesCRM.SVAZIO;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	// dtSuspeitoInicio
	private String dtSuspeitoInicio = ConstantesCRM.SVAZIO;

	public void setDtSuspeitoInicio(String dtSuspeitoInicio) {
		this.dtSuspeitoInicio = dtSuspeitoInicio;
	}

	public String getDtSuspeitoInicio() {
		return this.dtSuspeitoInicio;
	}

	// dtSuspeitoFim
	private String dtSuspeitoFim = ConstantesCRM.SVAZIO;

	public void setDtSuspeitoFim(String dtSuspeitoFim) {
		this.dtSuspeitoFim = dtSuspeitoFim;
	}

	public String getDtSuspeitoFim() {
		return this.dtSuspeitoFim;
	}

	// atendimentoInformacaoVO
	private AtendimentoInformacaoVO atendimentoInformacaoVO;

	public void setAtendimentoInformacaoVO(AtendimentoInformacaoVO atendimentoInformacaoVO) {
		this.atendimentoInformacaoVO = atendimentoInformacaoVO;
	}

	public AtendimentoInformacaoVO getAtendimentoInformacaoVO() {
		return this.atendimentoInformacaoVO;
	}

	// usuarioVIVO
	private UsuarioVIVO usuarioVIVO;

	public void setUsuarioVIVO(UsuarioVIVO usuarioVIVO) {
		this.usuarioVIVO = usuarioVIVO;
	}

	public UsuarioVIVO getUsuarioVIVO() {
		return this.usuarioVIVO;
	}

	// alertasVO
	private AlertaVO[] alertasVO;

	public void setAlertasVO(AlertaVO[] alertasVO) {
		this.alertasVO = alertasVO;
	}

	public AlertaVO[] getAlertasVO() {
		return this.alertasVO;
	}

	// atdWFTecnicoDocsVO
	private AtendimentoWorkflowTecnicoDocVO[] atdWFTecnicoDocsVO;

	public void setAtdWFTecnicoDocsVO(AtendimentoWorkflowTecnicoDocVO[] atdWFTecnicoDocsVO) {
		this.atdWFTecnicoDocsVO = atdWFTecnicoDocsVO;
	}

	public AtendimentoWorkflowTecnicoDocVO[] getAtdWFTecnicoDocsVO() {
		return this.atdWFTecnicoDocsVO;
	}

	// scriptArvore
	private String scriptArvore = ConstantesCRM.SVAZIO;

	public String getScriptArvore() {
		return this.scriptArvore;
	}

	public void setScriptArvore(String scriptArvore) {
		this.scriptArvore = scriptArvore;
	}

	private String idAtividade;

	public void setIdAtividade(String idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getIdAtividade() {
		return this.idAtividade;
	}

	private String idBaixa;

	public void setIdBaixa(String idBaixa) {
		this.idBaixa = idBaixa;
	}

	public String getIdBaixa() {
		return this.idBaixa;
	}

	private String documentoAssociado;

	public void setDocumentoAssociado(String documentoAssociado) {
		this.documentoAssociado = documentoAssociado;
	}

	public String getDocumentoAssociado() {
		return this.documentoAssociado;
	}

	// abaSelecionada
	private String fila = ConstantesCRM.SVAZIO;

	public String getFila() {
		return this.fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}
}