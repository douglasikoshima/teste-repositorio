package workflow.AtendimentoDetalheACS;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.AtendimentoDetalheACSMigracaoVODocument.AtendimentoDetalheACSMigracaoVO;

public class AtendimentoDetalheACSForm extends ActionForm {

	private static final long serialVersionUID = -4722098997787114470L;

	private AtendimentoDetalheACSMigracaoVO atendimentoDetalheACS;

	public void setAtendimentoDetalheACS(AtendimentoDetalheACSMigracaoVO atendimentoDetalheACS) {
		this.atendimentoDetalheACS = atendimentoDetalheACS;
	}

	public AtendimentoDetalheACSMigracaoVO getAtendimentoDetalheACS() {
		return this.atendimentoDetalheACS;
	}
}