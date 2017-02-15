package workflow.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument.EncerramentoVO;

public class RWFEncerramentoForm extends ActionForm {

	private static final long serialVersionUID = 1909982398766622L;
	private EncerramentoVO encerramentoVO;

	public void setEncerramentoVO(EncerramentoVO encerramentoVO) {
		this.encerramentoVO = encerramentoVO;
	}

	public EncerramentoVO getEncerramentoVO() {
		return this.encerramentoVO;
	}
}