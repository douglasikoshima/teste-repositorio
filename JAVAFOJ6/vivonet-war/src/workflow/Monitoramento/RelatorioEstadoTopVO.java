package workflow.Monitoramento;

/**
 * Representação de 1 linha do relatorio estado do processo
 */
public class RelatorioEstadoTopVO extends RelatorioVO {

	private static final long serialVersionUID = 7209379201069615552L;

	private String estado = null;

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return this.estado;
	}
}