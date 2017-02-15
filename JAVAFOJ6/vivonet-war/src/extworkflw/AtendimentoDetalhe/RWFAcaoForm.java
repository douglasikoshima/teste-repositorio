package extworkflw.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

public class RWFAcaoForm extends ActionForm {

	private static final long serialVersionUID = 19886327678612376L;
	private br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO;

	public void setWFAcaoVO(br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO) {
		this.WFAcaoVO = WFAcaoVO;
	}

	public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVO() {
		return this.WFAcaoVO;
	}
}