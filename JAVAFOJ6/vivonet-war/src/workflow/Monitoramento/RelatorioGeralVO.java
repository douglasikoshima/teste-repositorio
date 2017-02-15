package workflow.Monitoramento;

/**
 * Representação de 1 linha do relatorio geral
 */
public class RelatorioGeralVO extends RelatorioVO {

	private static final long serialVersionUID = -8822319628091259321L;

	private String grupo = null;

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupo() {
		return this.grupo;
	}
}