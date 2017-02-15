package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to;

import java.util.Collection;

public class RelacionaUsuarioWSTO {

	private String idUsuario = "";
	private String nmUnidade = "";

	private Collection listaId;

	public RelacionaUsuarioWSTO(){
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Collection getListaId() {
		return listaId;
	}

	public void setListaId(Collection listaId) {
		this.listaId = listaId;
	}

	public String getNmUnidade() {
		return nmUnidade;
	}

	public void setNmUnidade(String nmUnidade) {
		this.nmUnidade = nmUnidade;
	}

}
