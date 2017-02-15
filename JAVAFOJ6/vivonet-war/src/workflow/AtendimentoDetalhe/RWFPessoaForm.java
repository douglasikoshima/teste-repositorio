package workflow.AtendimentoDetalhe;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;

public class RWFPessoaForm extends ActionForm {

	private static final long serialVersionUID = 16726387668922L;
	private PessoaVO pessoaVO;

	public void setPessoaVO(PessoaVO pessoaVO) {
		this.pessoaVO = pessoaVO;
	}

	public PessoaVO getPessoaVO() {
		return this.pessoaVO;
	}
}