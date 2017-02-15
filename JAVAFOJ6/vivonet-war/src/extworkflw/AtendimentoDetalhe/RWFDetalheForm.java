package extworkflw.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;

public class RWFDetalheForm extends ActionForm {

	private static final long serialVersionUID = 2398872387287366L;
	private String inRC;
	private boolean meuCliente;
	private String[] qtdProcessosPai = new String[0];
	private WFEstadosVO estadoVO;
	private RWFAtendimentoVO atendimentoVO;
	private WFAcaoVO[] acaoVO;

	public void setAcaoVO(WFAcaoVO[] arg) {
		this.acaoVO = arg;
	}

	public WFAcaoVO[] getAcaoVO() {
		return this.acaoVO;
	}

	public void setAtendimentoVO(RWFAtendimentoVO atendimentoVO) {
		this.atendimentoVO = atendimentoVO;
	}

	public RWFAtendimentoVO getAtendimentoVO() {
		return this.atendimentoVO;
	}

	public void setEstadoVO(WFEstadosVO estadoVO) {
		this.estadoVO = estadoVO;
	}

	public WFEstadosVO getEstadoVO() {
		return this.estadoVO;
	}

	// fila
	private String fila = ConstantesCRM.SVAZIO;

	public String getFila() {
		return this.fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	// CRI
	private String inCRI = ConstantesCRM.SVAZIO;

	public String getInCRI() {
		return this.inCRI;
	}

	public void setInCRI(String inCRI) {
		this.inCRI = inCRI;
	}

	public void setQtdProcessosPai(String qtd) {
		this.qtdProcessosPai = new String[Integer.parseInt(qtd)];
	}

	public String[] getQtdProcessosPai() {
		return this.qtdProcessosPai;
	}

	public void setInRC(String inRC) {
		this.inRC = inRC;
	}

	public String getInRC() {
		return this.inRC;
	}

	public void setMeuCliente(boolean arg) {
		this.meuCliente = arg;
	}

	public boolean isMeuCliente() {
		return this.meuCliente;
	}
}