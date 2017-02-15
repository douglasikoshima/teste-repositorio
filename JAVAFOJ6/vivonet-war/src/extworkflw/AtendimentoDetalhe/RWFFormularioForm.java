package extworkflw.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument.FormularioProcessoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;

public class RWFFormularioForm extends ActionForm {

	private static final long serialVersionUID = 17773939928362223L;
	private FormularioVO formularioVO;

	public void setFormularioVO(FormularioVO formularioVO) {
		this.formularioVO = formularioVO;
	}

	public FormularioVO getFormularioVO() {
		return this.formularioVO;
	}

	private FormularioProcessoVO formularioProcessoVO;

	public void setFormularioProcessoVO(FormularioProcessoVO formularioProcessoVO) {
		this.formularioProcessoVO = formularioProcessoVO;
	}

	public FormularioProcessoVO getFormularioProcessoVO() {
		return this.formularioProcessoVO;
	}
}