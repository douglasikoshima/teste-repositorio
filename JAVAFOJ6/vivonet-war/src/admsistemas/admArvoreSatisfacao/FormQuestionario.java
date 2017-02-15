package admsistemas.admArvoreSatisfacao;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioContainerVODocument.AdmQuestionarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormQuestionario extends ActionForm {

	private static final long serialVersionUID = 165754225516565861L;

	private String msgError = ConstantesCRM.SVAZIO;
	private String listaQuestionarioLength;
	private String dsQuestionario;
	private String idQuestionario;
	private AdmQuestionarioContainerVO admQuestionarioContainerVO;
	private AdmQuestionarioVO[] admQuestionarioVO;
	private String dsTipoApresentacaoPergunta;
	private String idTipoApresentacaoPergunta;

	public void setIdTipoApresentacaoPergunta(String idTipoApresentacaoPergunta) {
		this.idTipoApresentacaoPergunta = idTipoApresentacaoPergunta;
	}

	public String getIdTipoApresentacaoPergunta() {
		return this.idTipoApresentacaoPergunta;
	}

	public void setDsTipoApresentacaoPergunta(String dsTipoApresentacaoPergunta) {
		this.dsTipoApresentacaoPergunta = dsTipoApresentacaoPergunta;
	}

	public String getDsTipoApresentacaoPergunta() {
		return this.dsTipoApresentacaoPergunta;
	}

	public void setAdmQuestionarioVO(AdmQuestionarioVO[] admQuestionarioVO) {
		this.admQuestionarioVO = admQuestionarioVO;
	}

	public AdmQuestionarioVO[] getAdmQuestionarioVO() {
		return this.admQuestionarioVO;
	}

	public void setAdmQuestionarioContainerVO(AdmQuestionarioContainerVO admQuestionarioContainerVO) {
		this.admQuestionarioContainerVO = admQuestionarioContainerVO;
	}

	public AdmQuestionarioContainerVO getAdmQuestionarioContainerVO() {
		return this.admQuestionarioContainerVO;
	}

	public void setIdQuestionario(String idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public String getIdQuestionario() {
		return this.idQuestionario;
	}

	public void setDsQuestionario(String dsQuestionario) {
		this.dsQuestionario = dsQuestionario;
	}

	public String getDsQuestionario() {
		return this.dsQuestionario;
	}

	public void setListaQuestionarioLength(String listaQuestionarioLength) {
		this.listaQuestionarioLength = listaQuestionarioLength;
	}

	public String getListaQuestionarioLength() {
		return this.listaQuestionarioLength;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}
}