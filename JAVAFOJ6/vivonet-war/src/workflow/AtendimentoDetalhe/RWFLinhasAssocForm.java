package workflow.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;

public class RWFLinhasAssocForm extends ActionForm {

	private static final long serialVersionUID = 909290392398827L;
	private ListaDadosVO listaDadosVO;

	public void setLinhasAssoc(ListaDadosVO listaDadosVO) {
		this.listaDadosVO = listaDadosVO;
	}

	public ListaDadosVO getLinhasAssoc() {
		return this.listaDadosVO;
	}
}