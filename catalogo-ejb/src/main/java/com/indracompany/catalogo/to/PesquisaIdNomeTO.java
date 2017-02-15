package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gustavo
 *
 */
public class PesquisaIdNomeTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private PaginacaoDataTableTO paginacaoDataTableTO;
	private List<IdNomeTO> resultado = new ArrayList<IdNomeTO>();
	
	public PaginacaoDataTableTO getPaginacaoDataTableTO() {
		return paginacaoDataTableTO;
	}
	public void setPaginacaoDataTableTO(PaginacaoDataTableTO paginacaoDataTableTO) {
		this.paginacaoDataTableTO = paginacaoDataTableTO;
	}
	public List<IdNomeTO> getResultado() {
		return resultado;
	}
	public void setResultado(List<IdNomeTO> resultado) {
		this.resultado = resultado;
	}
	
}