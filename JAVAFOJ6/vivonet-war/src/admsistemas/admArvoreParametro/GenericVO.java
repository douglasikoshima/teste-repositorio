package admsistemas.admArvoreParametro;

import java.io.Serializable;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class GenericVO implements Serializable {

	private static final long serialVersionUID = 8048541816913923052L;

	private String codigo = ConstantesCRM.SVAZIO;
	private String descricao = ConstantesCRM.SVAZIO;

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return this.codigo;
	}

}