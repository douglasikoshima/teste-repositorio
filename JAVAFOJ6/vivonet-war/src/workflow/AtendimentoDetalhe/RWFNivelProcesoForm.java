package workflow.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.FaseVODocument.FaseVO;

public class RWFNivelProcesoForm extends ActionForm {

	private static final long serialVersionUID = 36652362738728L;
	private FaseVO[] faseVO;

	public void setFaseVO(FaseVO[] faseVO) {
		this.faseVO = faseVO;
	}

	public FaseVO[] getFaseVO() {
		return this.faseVO;
	}
}