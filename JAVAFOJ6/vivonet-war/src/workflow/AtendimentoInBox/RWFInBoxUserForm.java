package workflow.AtendimentoInBox;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;

public class RWFInBoxUserForm extends ActionForm {

	private static final long serialVersionUID = 3625132944246348196L;

	private RetornoWFCTIVO[] retorno;
	private String inDisponivelWF;
	private String nmLoginUsuario;
	private String inDisponivelProcessosInboxWF;

	public void setNmLoginUsuario(String nmLoginUsuario) {
		this.nmLoginUsuario = nmLoginUsuario;
	}

	public String getNmLoginUsuario() {
		return this.nmLoginUsuario;
	}

	public void setInDisponivelProcessosInboxWF(String inDisponivelProcessosInboxWF) {
		this.inDisponivelProcessosInboxWF = inDisponivelProcessosInboxWF;
	}

	public String getInDisponivelProcessosInboxWF() {
		return this.inDisponivelProcessosInboxWF;
	}

	public void setInDisponivelWF(String inDisponivelWF) {
		this.inDisponivelWF = inDisponivelWF;
	}

	public String getInDisponivelWF() {
		return this.inDisponivelWF;
	}

	public void setRetorno(RetornoWFCTIVO[] retorno) {
		this.retorno = retorno;
	}

	public RetornoWFCTIVO[] getRetorno() {
		return this.retorno;
	}
}