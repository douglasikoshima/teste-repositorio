package workflow.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;

public class RWFHistoricoForm extends ActionForm {

	private static final long serialVersionUID = 48872987357926L;
	private AtendimentoHistoricoVO[] historicoVO;
	private String altura = ConstantesCRM.SVAZIO;;
	private WFEstadosVO estadoVO;

	public void setEstadoVO(WFEstadosVO estadoVO) {
		this.estadoVO = estadoVO;
	}

	public WFEstadosVO getEstadoVO() {
		return this.estadoVO;
	}

	public String getAltura() {
		return this.altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public void setHistoricoVO(AtendimentoHistoricoVO[] historicoVO) {
		this.historicoVO = historicoVO;
	}

	public AtendimentoHistoricoVO[] getHistoricoVO() {
		return this.historicoVO;
	}
}