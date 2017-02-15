package workflow.Monitoramento;

import java.io.Serializable;

public class RelatorioVO implements Serializable {

	private static final long serialVersionUID = 5692021430815521995L;

	private int ordem = 0;
	private int quantidade = 0;
	private int percentual = 0;

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

	public int getOrdem() {
		return this.ordem;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setPercentual(int percentual) {
		this.percentual = percentual;
	}

	public int getPercentual() {
		return this.percentual;
	}
}