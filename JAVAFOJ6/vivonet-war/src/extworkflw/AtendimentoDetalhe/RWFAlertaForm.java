package extworkflw.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;

public class RWFAlertaForm extends ActionForm {

	private static final long serialVersionUID = 59927827748928763L;
	private AlertaVO[] alertaVO;

	public void setAlertaVO(AlertaVO[] alertaVO) {
		this.alertaVO = alertaVO;
	}

	public AlertaVO[] getAlertaVO() {
		if (this.alertaVO == null) {
			AlertaVO avo = AlertaVO.Factory.newInstance();
			avo.setDsMensagem("-- --");
			avo.setIdAlerta(-1);
			AlertaVO[] array = new AlertaVO[1];
			array[0] = avo;
			this.alertaVO = array;
		}
		return this.alertaVO;
	}
}