package admsistemas.admArvoreContato.workflow.ConfigGruposTratamentoNiveis;

import java.io.Serializable;

public class NivelVO implements Serializable {

	private static final long serialVersionUID = -483413608167097244L;

	private int codigo;
	private String descricao;

	public int getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}