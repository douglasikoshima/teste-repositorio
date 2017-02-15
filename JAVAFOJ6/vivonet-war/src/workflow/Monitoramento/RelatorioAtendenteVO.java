package workflow.Monitoramento;

public class RelatorioAtendenteVO extends RelatorioVO {

	private static final long serialVersionUID = -5929163101265311902L;

	private String grupo = null;
	private String login = null;
	private RelatorioEstadoTopVO[] relatorioEstadoTopVO = null;

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return this.login;
	}

	public void setRelatorioEstadoTopVO(RelatorioEstadoTopVO[] relatorioEstadoTopVO) {
		this.relatorioEstadoTopVO = relatorioEstadoTopVO;
	}

	public RelatorioEstadoTopVO[] getRelatorioEstadoTopVO() {
		return this.relatorioEstadoTopVO;
	}
}