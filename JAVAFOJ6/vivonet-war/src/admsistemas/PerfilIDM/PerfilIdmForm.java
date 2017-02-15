package admsistemas.PerfilIDM;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class PerfilIdmForm extends ActionForm {

	private static final long serialVersionUID = -6016624864087357768L;

	private String codError;
	private String msgError = ConstantesCRM.SVAZIO;
	private String isFirst = ConstantesCRM.SVAZIO;
	private String nmTela = ConstantesCRM.SVAZIO;

	private String action = ConstantesCRM.SVAZIO;
	private String idPerfil = ConstantesCRM.SVAZIO;
	private String nmPerfil = ConstantesCRM.SVAZIO;
	private String[] arrSelecionados;
	private String[] arrSeleGrupos;
	private String[] arrDisponiveis;

	private Disponivel lstPerfisIdm;

	private Disponivel lstDispGrupo;
	private Selecionado lstSeleGrupo;

	private Disponivel lstDisponiveis;
	private Selecionado lstSelecionados;

	public Disponivel getLstPerfisIdm() {
		return this.lstPerfisIdm;
	}

	public void setLstPerfisIdm(Disponivel lstPerfisIdm) {
		this.lstPerfisIdm = lstPerfisIdm;
	}

	public Disponivel getLstDispGrupo() {
		return this.lstDispGrupo;
	}

	public void setLstDispGrupo(Disponivel lstDispGrupo) {
		this.lstDispGrupo = lstDispGrupo;
	}

	public Selecionado getLstSeleGrupo() {
		return this.lstSeleGrupo;
	}

	public void setLstSeleGrupo(Selecionado lstSeleGrupo) {
		this.lstSeleGrupo = lstSeleGrupo;
	}

	public Disponivel getLstDisponiveis() {
		return this.lstDisponiveis;
	}

	public void setLstDisponiveis(Disponivel lstDisponiveis) {
		this.lstDisponiveis = lstDisponiveis;
	}

	public Selecionado getLstSelecionados() {
		return this.lstSelecionados;
	}

	public void setLstSelecionados(Selecionado lstSelecionados) {
		this.lstSelecionados = lstSelecionados;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return this.action;
	}

	public void setArrDisponiveis(String[] arrDisponiveis) {
		this.arrDisponiveis = arrDisponiveis;
	}

	public String[] getArrDisponiveis() {
		if (this.arrDisponiveis == null) {
			this.arrDisponiveis = new String[0];
		}
		return this.arrDisponiveis;
	}

	public void setArrSelecionados(String[] arrSelecionados) {
		this.arrSelecionados = arrSelecionados;
	}

	public String[] getArrSelecionados() {
		if (this.arrSelecionados == null) {
			this.arrSelecionados = new String[0];
		}
		return this.arrSelecionados;
	}

	public void setArrSeleGrupos(String[] arrSeleGrupos) {
		this.arrSeleGrupos = arrSeleGrupos;
	}

	public String[] getArrSeleGrupos() {
		if (this.arrSeleGrupos == null) {
			this.arrSeleGrupos = new String[0];
		}
		return this.arrSeleGrupos;
	}

	public void setNmPerfil(String nmPerfil) {
		this.nmPerfil = nmPerfil;
	}

	public String getNmPerfil() {
		return this.nmPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getIdPerfil() {
		return this.idPerfil;
	}

	public void setNmTela(String nmTela) {
		this.nmTela = nmTela;
	}

	public String getNmTela() {
		return this.nmTela;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getIsFirst() {
		return this.isFirst;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setCodError(String codError) {
		this.codError = codError;
	}

	public String getCodError() {
		return this.codError;
	}
}