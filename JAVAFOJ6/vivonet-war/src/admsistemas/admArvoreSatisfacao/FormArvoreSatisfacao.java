package admsistemas.admArvoreSatisfacao;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmListaObjetosSatisfacaoVODocument.AdmListaObjetosSatisfacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmPerguntaVODocument.AdmPerguntaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument.AdmSatisfacaoContainerVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormArvoreSatisfacao extends ActionForm {

	private static final long serialVersionUID = 7294896131620666757L;

	private String idPerguntaSalto;
	private String msgError = ConstantesCRM.SVAZIO;
	private String indUpDown;
	private String indPerguntaResposta;
	private String[] arrayAdmPergunta;
	private AdmPerguntaVO[] admPerguntaVO;
	private String idAtivoPerguntaSaltoFuncao;
	private String dsTipoApresentacaoPerguntaFuncao;
	private String arrayListaObjetosArvoreSatisfacao;
	private AdmListaObjetosSatisfacaoVO[] admListaObjetosSatisfacaoVO;
	private String ativoSaltoFuncao;
	private String idPerguntaSaltoFuncao;
	private String inDisponibilidadeRespostaFuncao;
	private String inEncerramentoRespostaFuncao;
	private String sqApresentacaoRespostaFuncao;
	private String dsScriptRespostaFuncao;
	private String dsRespostaFuncao;
	private String idRespostaFuncao;
	private String idTipoPerguntaFuncao;
	private String inObrigatoriaFuncao;
	private String inDisponibilidadePerguntaFuncao;
	private String inEncerramentoPerguntaFuncao;
	private String sqApresentacaoPerguntaFuncao;
	private String dsScriptPerguntaFuncao;
	private String dsPerguntaFuncao;
	private String idTipoApresentacaoPerguntaFuncao;
	private String dsQuestionarioFuncao;
	private String idQuestionarioFuncao;
	private AdmSatisfacaoContainerVO admSatisfacaoContainerVO;
	private String idQuestionario;

	public void setIdQuestionario(String idQuestionario) {
		this.idQuestionario = idQuestionario;
	}

	public String getIdQuestionario() {
		return this.idQuestionario;
	}

	public void setAdmSatisfacaoContainerVO(AdmSatisfacaoContainerVO admSatisfacaoContainerVO) {
		this.admSatisfacaoContainerVO = admSatisfacaoContainerVO;
	}

	public AdmSatisfacaoContainerVO getAdmSatisfacaoContainerVO() {
		return this.admSatisfacaoContainerVO;
	}

	public void setIdQuestionarioFuncao(String idQuestionarioFuncao) {
		this.idQuestionarioFuncao = idQuestionarioFuncao;
	}

	public String getIdQuestionarioFuncao() {
		return this.idQuestionarioFuncao;
	}

	public void setDsQuestionarioFuncao(String dsQuestionarioFuncao) {
		this.dsQuestionarioFuncao = dsQuestionarioFuncao;
	}

	public String getDsQuestionarioFuncao() {
		return this.dsQuestionarioFuncao;
	}

	public void setIdTipoApresentacaoPerguntaFuncao(String idTipoApresentacaoPerguntaFuncao) {
		this.idTipoApresentacaoPerguntaFuncao = idTipoApresentacaoPerguntaFuncao;
	}

	public String getIdTipoApresentacaoPerguntaFuncao() {
		return this.idTipoApresentacaoPerguntaFuncao;
	}

	public void setDsPerguntaFuncao(String dsPerguntaFuncao) {
		this.dsPerguntaFuncao = dsPerguntaFuncao;
	}

	public String getDsPerguntaFuncao() {
		return this.dsPerguntaFuncao;
	}

	public void setDsScriptPerguntaFuncao(String dsScriptPerguntaFuncao) {
		this.dsScriptPerguntaFuncao = dsScriptPerguntaFuncao;
	}

	public String getDsScriptPerguntaFuncao() {
		return this.dsScriptPerguntaFuncao;
	}

	public void setSqApresentacaoPerguntaFuncao(String sqApresentacaoPerguntaFuncao) {
		this.sqApresentacaoPerguntaFuncao = sqApresentacaoPerguntaFuncao;
	}

	public String getSqApresentacaoPerguntaFuncao() {
		return this.sqApresentacaoPerguntaFuncao;
	}

	public void setInEncerramentoPerguntaFuncao(String inEncerramentoPerguntaFuncao) {
		this.inEncerramentoPerguntaFuncao = inEncerramentoPerguntaFuncao;
	}

	public String getInEncerramentoPerguntaFuncao() {
		return this.inEncerramentoPerguntaFuncao;
	}

	public void setInDisponibilidadePerguntaFuncao(String inDisponibilidadePerguntaFuncao) {
		this.inDisponibilidadePerguntaFuncao = inDisponibilidadePerguntaFuncao;
	}

	public String getInDisponibilidadePerguntaFuncao() {
		return this.inDisponibilidadePerguntaFuncao;
	}

	public void setInObrigatoriaFuncao(String inObrigatoriaFuncao) {
		this.inObrigatoriaFuncao = inObrigatoriaFuncao;
	}

	public String getInObrigatoriaFuncao() {
		return this.inObrigatoriaFuncao;
	}

	public void setIdTipoPerguntaFuncao(String idTipoPerguntaFuncao) {
		this.idTipoPerguntaFuncao = idTipoPerguntaFuncao;
	}

	public String getIdTipoPerguntaFuncao() {
		return this.idTipoPerguntaFuncao;
	}

	public void setIdRespostaFuncao(String idRespostaFuncao) {
		this.idRespostaFuncao = idRespostaFuncao;
	}

	public String getIdRespostaFuncao() {
		return this.idRespostaFuncao;
	}

	public void setDsRespostaFuncao(String dsRespostaFuncao) {
		this.dsRespostaFuncao = dsRespostaFuncao;
	}

	public String getDsRespostaFuncao() {
		return this.dsRespostaFuncao;
	}

	public void setDsScriptRespostaFuncao(String dsScriptRespostaFuncao) {
		this.dsScriptRespostaFuncao = dsScriptRespostaFuncao;
	}

	public String getDsScriptRespostaFuncao() {
		return this.dsScriptRespostaFuncao;
	}

	public void setSqApresentacaoRespostaFuncao(String sqApresentacaoRespostaFuncao) {
		this.sqApresentacaoRespostaFuncao = sqApresentacaoRespostaFuncao;
	}

	public String getSqApresentacaoRespostaFuncao() {
		return this.sqApresentacaoRespostaFuncao;
	}

	public void setInEncerramentoRespostaFuncao(String inEncerramentoRespostaFuncao) {
		this.inEncerramentoRespostaFuncao = inEncerramentoRespostaFuncao;
	}

	public String getInEncerramentoRespostaFuncao() {
		return this.inEncerramentoRespostaFuncao;
	}

	public void setInDisponibilidadeRespostaFuncao(String inDisponibilidadeRespostaFuncao) {
		this.inDisponibilidadeRespostaFuncao = inDisponibilidadeRespostaFuncao;
	}

	public String getInDisponibilidadeRespostaFuncao() {
		return this.inDisponibilidadeRespostaFuncao;
	}

	public void setIdPerguntaSaltoFuncao(String idPerguntaSaltoFuncao) {
		this.idPerguntaSaltoFuncao = idPerguntaSaltoFuncao;
	}

	public String getIdPerguntaSaltoFuncao() {
		return this.idPerguntaSaltoFuncao;
	}

	public void setAtivoSaltoFuncao(String ativoSaltoFuncao) {
		this.ativoSaltoFuncao = ativoSaltoFuncao;
	}

	public String getAtivoSaltoFuncao() {
		return this.ativoSaltoFuncao;
	}

	public void setAdmListaObjetosSatisfacaoVO(
			AdmListaObjetosSatisfacaoVO[] admListaObjetosSatisfacaoVO) {
		this.admListaObjetosSatisfacaoVO = admListaObjetosSatisfacaoVO;
	}

	public AdmListaObjetosSatisfacaoVO[] getAdmListaObjetosSatisfacaoVO() {
		return this.admListaObjetosSatisfacaoVO;
	}

	public void setArrayListaObjetosArvoreSatisfacao(String arrayListaObjetosArvoreSatisfacao) {
		this.arrayListaObjetosArvoreSatisfacao = arrayListaObjetosArvoreSatisfacao;
	}

	public String getArrayListaObjetosArvoreSatisfacao() {
		// if(this.arrayListaObjetosArvoreSatisfacao == null ||
		// this.arrayListaObjetosArvoreSatisfacao.length == 0)
		// {
		// this.arrayListaObjetosArvoreSatisfacao = new String[1];
		// }

		return this.arrayListaObjetosArvoreSatisfacao;
	}

	public void setDsTipoApresentacaoPerguntaFuncao(String dsTipoApresentacaoPerguntaFuncao) {
		this.dsTipoApresentacaoPerguntaFuncao = dsTipoApresentacaoPerguntaFuncao;
	}

	public String getDsTipoApresentacaoPerguntaFuncao() {
		return this.dsTipoApresentacaoPerguntaFuncao;
	}

	public void setIdAtivoPerguntaSaltoFuncao(String idAtivoPerguntaSaltoFuncao) {
		this.idAtivoPerguntaSaltoFuncao = idAtivoPerguntaSaltoFuncao;
	}

	public String getIdAtivoPerguntaSaltoFuncao() {
		return this.idAtivoPerguntaSaltoFuncao;
	}

	public void setAdmPerguntaVO(AdmPerguntaVO[] admPerguntaVO) {
		this.admPerguntaVO = admPerguntaVO;
	}

	public AdmPerguntaVO[] getAdmPerguntaVO() {
		return this.admPerguntaVO;
	}

	public void setArrayAdmPergunta(String[] arrayAdmPergunta) {
		this.arrayAdmPergunta = arrayAdmPergunta;
	}

	public String[] getArrayAdmPergunta() {
		if (this.arrayAdmPergunta == null || this.arrayAdmPergunta.length == 0) {
			this.arrayAdmPergunta = new String[1];
		}

		return this.arrayAdmPergunta;
	}

	public void setIndPerguntaResposta(String indPerguntaResposta) {
		this.indPerguntaResposta = indPerguntaResposta;
	}

	public String getIndPerguntaResposta() {
		return this.indPerguntaResposta;
	}

	public void setIndUpDown(String indUpDown) {
		this.indUpDown = indUpDown;
	}

	public String getIndUpDown() {
		return this.indUpDown;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setIdPerguntaSalto(String idPerguntaSalto) {
		this.idPerguntaSalto = idPerguntaSalto;
	}

	public String getIdPerguntaSalto() {
		return this.idPerguntaSalto;
	}
}