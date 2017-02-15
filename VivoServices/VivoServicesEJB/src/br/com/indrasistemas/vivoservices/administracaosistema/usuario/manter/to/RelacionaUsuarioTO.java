package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import br.com.indrasistemas.framework.service.BaseTransferObject;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.RelacionaUsuarioWSTO;

public class RelacionaUsuarioTO extends BaseTransferObject {

	private static final long serialVersionUID = 9119103665318332019L;

	private String idUsuario = "";
	private String nmUnidade = "";

	private Collection listaId;

	public RelacionaUsuarioTO(){
	}

	public RelacionaUsuarioTO(RelacionaUsuarioWSTO value){
		this.idUsuario = value.getIdUsuario();
		this.nmUnidade = value.getNmUnidade();
		if(value.getListaId().size()>0){
			this.listaId = new ArrayList();
			for (Iterator iterator = value.getListaId().iterator(); iterator.hasNext();) {
				br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.Id item = (br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.Id) iterator.next();
				Id id = new Id();
				id.setId(item.getId());
				id.setDs(item.getDs());
				this.listaId.add(id);
			}
		}
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
