package workflow.AtendimentoInBox;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;

public class RWFAlertaForm extends ActionForm {

	private static final long serialVersionUID = -5746588623424452315L;

	private AlertaVO[] alertasVO;

	public void setAlertasVO(AlertaVO[] alertasVO) {
		this.alertasVO = alertasVO;
	}

	public AlertaVO[] getAlertasVO() {
		if (this.alertasVO == null || this.alertasVO.length == 0) {
			this.alertasVO[0] = AlertaVO.Factory.newInstance();
		}
		return this.alertasVO;
	}
}