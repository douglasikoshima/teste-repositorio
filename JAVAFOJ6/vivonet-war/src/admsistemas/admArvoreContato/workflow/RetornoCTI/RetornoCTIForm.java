package admsistemas.admArvoreContato.workflow.RetornoCTI;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument.RetornoWFCTIResultadoVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;

public class RetornoCTIForm extends ActionForm {

	private static final long serialVersionUID = -8566912202357754916L;

	private String errorMessage = null;
	private String user = null;
	private int method = -1;
	private String idRetorno = null;
	private RetornoWFCTIVO retornoWFCTIVO = RetornoWFCTIVO.Factory.newInstance();
	private RetornoWFCTIVO retornoWFCTIPesquisaVO = RetornoWFCTIVO.Factory.newInstance();
	private RetornoWFCTIResultadoVO retornoWFCTIResultado = null;
	private String[] idGruposDisponiveis = null;
	private String[] idGruposSelecionados = null;

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser() {
		return this.user;
	}

	public void setMethod(int method) {
		this.method = method;
	}

	public int getMethod() {
		return this.method;
	}

	public void setIdRetorno(String idRetorno) {
		this.idRetorno = idRetorno;
	}

	public String getIdRetorno() {
		return this.idRetorno;
	}

	public void setRetornoWFCTIVO(RetornoWFCTIVO retornoWFCTIVO) {
		this.retornoWFCTIVO = retornoWFCTIVO;
	}

	public RetornoWFCTIVO getRetornoWFCTIVO() {
		return this.retornoWFCTIVO;
	}

	public void setRetornoWFCTIPesquisaVO(RetornoWFCTIVO retornoWFCTIPesquisaVO) {
		this.retornoWFCTIPesquisaVO = retornoWFCTIPesquisaVO;
	}

	public RetornoWFCTIVO getRetornoWFCTIPesquisaVO() {
		return this.retornoWFCTIPesquisaVO;
	}

	public void setRetornoWFCTIResultado(RetornoWFCTIResultadoVO retornoWFCTIResultado) {
		this.retornoWFCTIResultado = retornoWFCTIResultado;
	}

	public RetornoWFCTIResultadoVO getRetornoWFCTIResultado() {
		return this.retornoWFCTIResultado;
	}

	public void setIdGruposDisponiveis(String[] idGruposDisponiveis) {
		this.idGruposDisponiveis = idGruposDisponiveis;
	}

	public String[] getIdGruposDisponiveis() {
		return this.idGruposDisponiveis;
	}

	public void setIdGruposSelecionados(String[] idGruposSelecionados) {
		this.idGruposSelecionados = idGruposSelecionados;
	}

	public String[] getIdGruposSelecionados() {
		return this.idGruposSelecionados;
	}
}