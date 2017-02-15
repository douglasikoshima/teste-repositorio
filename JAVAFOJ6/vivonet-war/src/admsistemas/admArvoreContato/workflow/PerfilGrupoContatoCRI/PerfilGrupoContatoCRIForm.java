package admsistemas.admArvoreContato.workflow.PerfilGrupoContatoCRI;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class PerfilGrupoContatoCRIForm extends ActionForm {

	private static final long serialVersionUID = 6223523942709298437L;

	private String perfilIdx;

	private String inMover;

	private String strMensagem = ConstantesCRM.SVAZIO;

	public void setMensagem(String strMensagem) {
		this.strMensagem = strMensagem;
	}

	public String getMensagem() {
		return this.strMensagem;
	}

	private String idRetorno = ConstantesCRM.SVAZIO;

	public void setIdRetorno(String idRetorno) {
		this.idRetorno = idRetorno;
	}

	public String getIdRetorno() {
		return this.idRetorno;
	}

	private String inGrupoIguais = ConstantesCRM.SZERO;

	public void setInGrupoIguais(String inGrupoIguais) {
		this.inGrupoIguais = inGrupoIguais;
	}

	public String getInGrupoIguais() {
		return this.inGrupoIguais;
	}

	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	private String[] idGrupoPerfil;

	public void setIdGrupoPerfil(String[] idGrupoPerfil) {
		this.idGrupoPerfil = idGrupoPerfil;
	}

	public String[] getIdGrupoPerfil() {
		return this.idGrupoPerfil;
	}

	private String grupo;

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getGrupo() {
		return this.grupo;
	}

	private String grupoDes;

	public void setGrupoDes(String grupo) {
		this.grupoDes = grupo;
	}

	public String getGrupoDes() {
		return this.grupoDes;
	}

	private String perfil;

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getPerfil() {
		return this.perfil;
	}

	private String perfilDes;

	public void setPerfilDes(String perfil) {
		this.perfilDes = perfil;
	}

	public String getPerfilDes() {
		return this.perfilDes;
	}

	/*
	 * private br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil[] perfilVO;
	 * public void
	 * setPerfilVO(br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil[] perfilVO)
	 * { this.perfilVO = perfilVO; } public
	 * br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil[] getPerfilVO() {
	 * return this.perfilVO; }
	 * 
	 * 
	 * private br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO[] grupoVO;
	 * public void
	 * setGrupoVO(br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO[] grupoVO)
	 * { this.grupoVO = grupoVO; } public
	 * br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO[] getGrupoVO() {
	 * return this.grupoVO; }
	 */

	// Perfil Variaveis
	private PerfilVariaveisVO perfilVariaveisVO;

	public PerfilVariaveisVO getPerfilVariaveisVO() {
		return this.perfilVariaveisVO;
	}

	public void setPerfilVariaveisVO(PerfilVariaveisVO perfilVariaveisVO) {
		this.perfilVariaveisVO = perfilVariaveisVO;
	}

	public PerfilGrupoContatoCRIForm() {

	}

	public void setInMover(String inMover) {
		this.inMover = inMover;
	}

	public String getInMover() {
		if (this.inMover == null) {
			this.inMover = ConstantesCRM.SVAZIO;
		}

		return this.inMover;
	}

	public void setPerfilIdx(String perfilIdx) {
		this.perfilIdx = perfilIdx;
	}

	public String getPerfilIdx() {
		if (this.perfilIdx == null) {
			this.perfilIdx = ConstantesCRM.SVAZIO;
		}

		return this.perfilIdx;
	}

}