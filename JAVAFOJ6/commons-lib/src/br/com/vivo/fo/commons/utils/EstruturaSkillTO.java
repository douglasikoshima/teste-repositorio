package br.com.vivo.fo.commons.utils;

import br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO;
import java.util.ArrayList;

public class EstruturaSkillTO {
	private AdmSkillUsuarioVO admSkillUsuarioVO = null;
	private ArrayList listaUsuariosExistentes = new ArrayList();
	private ArrayList listaUsuarioSkillAssociado = new ArrayList();
	private ArrayList listaContatosExistentes = new ArrayList();
	private ArrayList listaContatosAssociados = new ArrayList();

	public void setAdmSkillUsuarioVO(AdmSkillUsuarioVO admSkill) {
		admSkillUsuarioVO = admSkill;
	}

	public void setListaUsuariosExistentes(ArrayList lista) {
		listaUsuariosExistentes = lista;
	}

	public AdmSkillUsuarioVO getAdmSkillUsuarioVO() {
		return admSkillUsuarioVO;
	}

	public ArrayList getListaUsuariosExistentes() {
		return listaUsuariosExistentes;
	}

	public void setListaUsuarioSkillAssociado(ArrayList lista) {
		listaUsuarioSkillAssociado = lista;
	}

	public ArrayList getListaUsuarioSkillAssociado() {
		if (this.listaUsuarioSkillAssociado == null) {
			this.listaUsuarioSkillAssociado = new ArrayList();
		}
		return this.listaUsuarioSkillAssociado;
	}

	public void setListaContatosExistentes(ArrayList lista) {
		listaContatosExistentes = lista;
	}

	public void setListaContatosAssociados(ArrayList lista) {
		listaContatosAssociados = lista;
	}

	public ArrayList getListaContatosExistentes() {
		return listaContatosExistentes;
	}

	public ArrayList getListaContatosAssociados() {
		return listaContatosAssociados;
	}
}
