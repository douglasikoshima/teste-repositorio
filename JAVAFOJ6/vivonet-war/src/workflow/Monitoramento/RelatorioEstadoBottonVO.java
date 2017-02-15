package workflow.Monitoramento;

/**
 * Representação de 2 linha do relatorio estado do processo
 */
public class RelatorioEstadoBottonVO extends RelatorioVO {

	private static final long serialVersionUID = 1473677450858529L;

	private String grupo = null;
	private RelatorioEstadoTopVO[] relatorioEstadoTopVO = null;

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setRelatorioEstadoTopVO(RelatorioEstadoTopVO[] relatorioEstadoTopVO) {
		this.relatorioEstadoTopVO = relatorioEstadoTopVO;
	}

	public RelatorioEstadoTopVO[] getRelatorioEstadoTopVO() {
		return this.relatorioEstadoTopVO;
	}
}