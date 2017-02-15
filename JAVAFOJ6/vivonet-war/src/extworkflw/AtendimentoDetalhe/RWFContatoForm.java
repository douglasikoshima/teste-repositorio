package extworkflw.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO;

public class RWFContatoForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private RDContatoVO rDContato;

	public void setrDContato(RDContatoVO rDContato) {
		this.rDContato = rDContato;
	}

	public RDContatoVO getrDContato() {
		return this.rDContato;
	}
}