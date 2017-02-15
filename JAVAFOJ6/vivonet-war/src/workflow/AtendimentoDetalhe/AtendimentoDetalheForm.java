package workflow.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoDetalheVODocument.AtendimentoDetalheVO;
import br.com.vivo.fo.workflow.vo.AtendimentoTipoComunicacaoVODocument.AtendimentoTipoComunicacaoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO.Contas;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowAgendamentoVODocument.AtendimentoWorkflowAgendamentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowEncerramentoVODocument.AtendimentoWorkflowEncerramentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowInsistenciaVODocument.AtendimentoWorkflowInsistenciaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowPesquisaVODocument.AtendimentoWorkflowPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument.AtendimentoWorkflowTecnicoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowVODocument.AtendimentoWorkflowVO;
import br.com.vivo.fo.workflow.vo.ContaVODocument.ContaVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;

public class AtendimentoDetalheForm extends ActionForm {

	private static final long serialVersionUID = 3235999742436903790L;

	// private int docTec;

	/**
	 * Antiguo formulario => eliminar cuando todo este funcionando.
	 */
	public AtendimentoDetalheForm() {

		// inicicializa objeto atendimentoVO
		this.atendimentoDetalheVO = AtendimentoDetalheVO.Factory.newInstance();
		this.atendimentoDetalheVO.setAtendimentoVO(AtendimentoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().setAtendimentoWorkflowVO(
				AtendimentoWorkflowVO.Factory.newInstance());
		this.atendimentoDetalheVO
				.getAtendimentoVO()
				.getAtendimentoWorkflowVO()
				.setAtendimentoWorkflowAgendamentoVO(AtendimentoWorkflowAgendamentoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO()
				.setAtendimentoWorkflowComumVO(AtendimentoWorkflowComumVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO()
				.getAtendimentoWorkflowComumVO().setWFGrupoVOArray(new WFGrupoVO[0]);
		this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO()
				.getAtendimentoWorkflowComumVO().setWFMotivoVOArray(new WFMotivoVO[0]);
		this.atendimentoDetalheVO
				.getAtendimentoVO()
				.getAtendimentoWorkflowVO()
				.setAtendimentoWorkflowEncerramentoVO(
						AtendimentoWorkflowEncerramentoVO.Factory.newInstance());
		this.atendimentoDetalheVO
				.getAtendimentoVO()
				.getAtendimentoWorkflowVO()
				.setAtendimentoWorkflowInsistenciaVO(AtendimentoWorkflowInsistenciaVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO()
				.setAtendimentoWorkflowPesquisaVO(AtendimentoWorkflowPesquisaVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getAtendimentoWorkflowVO()
				.setAtendimentoWorkflowTecnicoVO(AtendimentoWorkflowTecnicoVO.Factory.newInstance());

		this.atendimentoDetalheVO.getAtendimentoVO().setPessoaVO(PessoaVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getPessoaVO()
				.setCarterizacaoVO(CarterizacaoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getPessoaVO()
				.setSegmentacaoVO(SegmentacaoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().setWFEstadoVO(WFEstadoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().setWFSubEstadoVO(
				WFSubEstadoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().setAlertaVOArray(new AlertaVO[0]);

		this.atendimentoDetalheVO.getAtendimentoVO().setContas(Contas.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getContas().setContaVOArray(new ContaVO[0]);

		this.atendimentoDetalheVO.getAtendimentoVO().getPessoaVO()
				.setAtendimentoTipoComunicacaoVOArray(new AtendimentoTipoComunicacaoVO[0]);

		this.atendimentoDetalheVO.getAtendimentoVO().setArvoreAtendimentoVO(
				ArvoreAtendimentoVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getArvoreAtendimentoVO()
				.setFormularioVO(FormularioVO.Factory.newInstance());
		this.atendimentoDetalheVO.getAtendimentoVO().getArvoreAtendimentoVO().getFormularioVO()
				.setFormularioCampoVOArray(new FormularioCampoVO[0]);

		this.atendimentoDetalheVO.setWFEstadoVOArray(new WFEstadoVO[0]);
		this.atendimentoDetalheVO.setWFSubEstadoVOArray(new WFSubEstadoVO[0]);

	}

	private GestorGerenteContaForm[] gestorGerenteContaForm;

	public GestorGerenteContaForm[] getGestorGerenteContaForm() {
		if (this.gestorGerenteContaForm == null) {
			this.gestorGerenteContaForm = new GestorGerenteContaForm[2];
		}
		return this.gestorGerenteContaForm;
	}

	public void setGestorGerenteContaForm(GestorGerenteContaForm[] arg) {
		this.gestorGerenteContaForm = arg;
	}

	// fila
	private String fila = ConstantesCRM.SVAZIO;

	public String getFila() {
		return this.fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	// inMenu - indica se a tela de Detalhe exibirá ou não o menu e o botão
	// voltar
	private String inMenu = null;

	public void setInMenu(String inMenu) {
		this.inMenu = inMenu;
	}

	public String getInMenu() {
		return this.inMenu;
	}

	// idAtendimento
	private String idAtendimento = ConstantesCRM.SONE;

	public void setIdAtendimento(String idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getIdAtendimento() {
		return this.idAtendimento;
	}

	// idAtendimento
	private String inResponsavel = ConstantesCRM.SONE;

	public void setInResponsavel(String inResponsavel) {
		this.inResponsavel = inResponsavel;
	}

	public String getInResponsavel() {
		return this.inResponsavel;
	}

	// motivoSel
	private String motivoSel = "-1"; // -1 é o item em branco default

	public String getMotivoSel() {
		return this.motivoSel;
	}

	public void setMotivoSel(String motivoSel) {
		this.motivoSel = motivoSel;
	}

	// tipoRetornoSel
	private String tipoRetornoSel = "-1"; // -1 é o item em branco default

	public String getTipoRetornoSel() {
		return this.tipoRetornoSel;
	}

	public void setTipoRetornoSel(String tipoRetornoSel) {
		this.tipoRetornoSel = tipoRetornoSel;
	}

	// retornoSel
	private String retornoSel = "-1"; // -1 é o item em branco default

	public String getRetornoSel() {
		return this.retornoSel;
	}

	public void setRetornoSel(String retornoSel) {
		this.retornoSel = retornoSel;
	}

	// estadoSel
	private String estadoSel = ConstantesCRM.SVAZIO;

	public void setEstadoSel(String estadoSel) {
		this.estadoSel = estadoSel;
	}

	public String getEstadoSel() {
		return this.estadoSel;
	}

	// subEstadoSel
	private String subEstadoSel = ConstantesCRM.SVAZIO;

	public void setSubEstadoSel(String subEstadoSel) {
		this.subEstadoSel = subEstadoSel;
	}

	public String getSubEstadoSel() {
		return this.subEstadoSel;
	}

	// contaFormatada
	private String contaFormatada = ConstantesCRM.SVAZIO;

	public void setContaFormatada(String contaFormatada) {
		this.contaFormatada = contaFormatada;
	}

	public String getContaFormatada() {
		return this.contaFormatada;
	}

	// atendimentoDetalheVO
	private AtendimentoDetalheVO atendimentoDetalheVO;

	public void setAtendimentoDetalheVO(AtendimentoDetalheVO atendimentoDetalheVO) {
		this.atendimentoDetalheVO = atendimentoDetalheVO;
	}

	public AtendimentoDetalheVO getAtendimentoDetalheVO() {
		return this.atendimentoDetalheVO;
	}

	// comentarioHistorico
	private String comentarioHistorico;

	public void setComentarioHistorico(String comentarioHistorico) {
		this.comentarioHistorico = comentarioHistorico;
	}

	public String getComentarioHistorico() {
		return this.comentarioHistorico;
	}

	// linhaFormatada
	private String linhaFormatada;

	public void setLinhaFormatada(String linhaFormatada) {
		this.linhaFormatada = linhaFormatada;
	}

	public String getLinhaFormatada() {
		return this.linhaFormatada;
	}

	// scriptPalitagem
	private String scriptPalitagem = ConstantesCRM.SVAZIO;

	public void setScriptPalitagem(String scriptPalitagem) {
		this.scriptPalitagem = scriptPalitagem;
	}

	public String getScriptPalitagem() {
		return this.scriptPalitagem;
	}

	// scriptPalitagemEncerramento
	private String scriptPalitagemEncerramento = ConstantesCRM.SVAZIO;

	public void setScriptPalitagemEncerramento(String scriptPalitagemEncerramento) {
		this.scriptPalitagemEncerramento = scriptPalitagemEncerramento;
	}

	public String getScriptPalitagemEncerramento() {
		return this.scriptPalitagemEncerramento;
	}

	// acao
	private String acaoSel;

	public void setAcaoSel(String acaoSel) {
		this.acaoSel = acaoSel;
	}

	public String getAcaoSel() {
		return this.acaoSel;
	}
}