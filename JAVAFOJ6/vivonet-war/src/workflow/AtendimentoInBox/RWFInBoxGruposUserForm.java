package workflow.AtendimentoInBox;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;

public class RWFInBoxGruposUserForm extends ActionForm {

	private static final long serialVersionUID = -5569498251907789665L;

	private String idGrupo;

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdGrupo() {
		return this.idGrupo;
	}

	private WFGrupoVO[] gruposVO;

	public void setGruposVO(WFGrupoVO[] gruposVO) {
		this.gruposVO = gruposVO;
	}

	public WFGrupoVO[] getGruposVO() {
		return this.gruposVO;
	}
}
