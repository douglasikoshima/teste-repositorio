package admsistemas.admArvoreContato.workflow.abaAssocGrupoContato;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class AssocGrupoContatoForm extends ActionForm implements Serializable {

	private static final long serialVersionUID = 4998071789671083806L;

	private String currentUser;
	private String[] aGruposDisponiveis;
	private String[] aGruposAssociados;
	private AdmGrupoVO[] gruposAssociados;
	private AdmGrupoVO[] gruposDisponiveis;
	private String msgError;
	private String idContato;

	public AssocGrupoContatoForm() {
		aGruposDisponiveis = new String[0];
		aGruposAssociados = new String[0];
		gruposAssociados = new AdmGrupoVO[0];
		gruposDisponiveis = new AdmGrupoVO[0];
		msgError = ConstantesCRM.SVAZIO;
		idContato = ConstantesCRM.SVAZIO;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setGruposDisponiveis(AdmGrupoVO[] gruposDisponiveis) {
		this.gruposDisponiveis = gruposDisponiveis;
	}

	public AdmGrupoVO[] getGruposDisponiveis() {
		return this.gruposDisponiveis;
	}

	public void setGruposAssociados(AdmGrupoVO[] gruposAssociados) {
		this.gruposAssociados = gruposAssociados;
	}

	public AdmGrupoVO[] getGruposAssociados() {
		return this.gruposAssociados;
	}

	public void setaGruposAssociados(String[] aGruposAssociados) {
		this.aGruposAssociados = aGruposAssociados;
	}

	public String[] getaGruposAssociados() {
		if (this.aGruposAssociados == null || this.aGruposAssociados.length == 0) {
			this.aGruposAssociados = new String[1];
		}
		return this.aGruposAssociados;
	}

	public void setaGruposDisponiveis(String[] aGruposDisponiveis) {
		this.aGruposDisponiveis = aGruposDisponiveis;
	}

	public String[] getaGruposDisponiveis() {
		if (this.aGruposDisponiveis == null || this.aGruposDisponiveis.length == 0) {
			this.aGruposDisponiveis = new String[1];
		}
		return this.aGruposDisponiveis;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public String getCurrentUser() {
		return this.currentUser;
	}

}