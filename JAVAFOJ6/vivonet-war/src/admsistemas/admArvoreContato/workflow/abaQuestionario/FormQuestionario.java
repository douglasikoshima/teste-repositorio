package admsistemas.admArvoreContato.workflow.abaQuestionario;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmListaVincularQuestionarioVODocument.AdmListaVincularQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoPessoaVODocument.AdmTipoPessoaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO;

public class FormQuestionario extends ActionForm implements Serializable {

	private static final long serialVersionUID = 1748843085015519708L;

	private String idTipoPessoaAtual;

	private AdmQuestionarioVO[] questionariosAssociados;

	// private String msgError = ConstantesCRM.SVAZIO;

	private String idQuestionarioAtual;

	private UFOperadoraUsuarioVO[] admUFOperadoraExistente;

	private UFOperadoraUsuarioVO[] admUFOperadoraRelacionado;

	private String[] arrayAdmTipoPessoa;

	private AdmTipoPessoaVO[] admTipoPessoaVO;

	private String[] arrayAdmUFOperadoraUsuario;

	// private UFOperadoraUsuarioVO[] admUFOperadoraUsuarioVO;

	// private String[] arrayAdmTipoLinha;

	// private AdmTipoLinhaVO[] admTipoLinhaVO;

	private String[] arrayAdmQuestionario;

	private String[] arrayAdmListaVincularQuestionario;

	private AdmListaVincularQuestionarioVO[] admListaVincularQuestionarioVO;

	private AdmQuestionarioVO[] admQuestionarioVO;

	private String idContato;

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setAdmQuestionarioVO(AdmQuestionarioVO[] admQuestionarioVO) {
		this.admQuestionarioVO = admQuestionarioVO;
	}

	public AdmQuestionarioVO[] getAdmQuestionarioVO() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize admQuestionarioVO if it is null or length == 0.
		// if(this.admQuestionarioVO == null || this.admQuestionarioVO.length == 0)
		// {
		// this.admQuestionarioVO = new AdmQuestionarioVO[1];
		// this.admQuestionarioVO[0] = new AdmQuestionarioVO(?);
		// }

		return this.admQuestionarioVO;
	}

	public void setAdmListaVincularQuestionarioVO(
			AdmListaVincularQuestionarioVO[] admListaVincularQuestionarioVO) {
		this.admListaVincularQuestionarioVO = admListaVincularQuestionarioVO;
	}

	public AdmListaVincularQuestionarioVO[] getAdmListaVincularQuestionarioVO() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize admListaVincularQuestionarioVO if it is null or length
		// == 0.
		// if(this.admListaVincularQuestionarioVO == null ||
		// this.admListaVincularQuestionarioVO.length == 0)
		// {
		// this.admListaVincularQuestionarioVO = new
		// AdmListaVincularQuestionarioVO[1];
		// this.admListaVincularQuestionarioVO[0] = new
		// AdmListaVincularQuestionarioVO(?);
		// }

		return this.admListaVincularQuestionarioVO;
	}

	public void setArrayAdmListaVincularQuestionario(String[] arrayAdmListaVincularQuestionario) {
		this.arrayAdmListaVincularQuestionario = arrayAdmListaVincularQuestionario;
	}

	public String[] getArrayAdmListaVincularQuestionario() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmListaVincularQuestionario == null
				|| this.arrayAdmListaVincularQuestionario.length == 0) {
			this.arrayAdmListaVincularQuestionario = new String[1];
		}

		return this.arrayAdmListaVincularQuestionario;
	}

	public void setArrayAdmQuestionario(String[] arrayAdmQuestionario) {
		this.arrayAdmQuestionario = arrayAdmQuestionario;
	}

	public String[] getArrayAdmQuestionario() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmQuestionario == null || this.arrayAdmQuestionario.length == 0) {
			this.arrayAdmQuestionario = new String[1];
		}

		return this.arrayAdmQuestionario;
	}

	public void setArrayAdmUFOperadoraUsuario(String[] arrayAdmUFOperadoraUsuario) {
		this.arrayAdmUFOperadoraUsuario = arrayAdmUFOperadoraUsuario;
	}

	public String[] getArrayAdmUFOperadoraUsuario() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmUFOperadoraUsuario == null || this.arrayAdmUFOperadoraUsuario.length == 0) {
			this.arrayAdmUFOperadoraUsuario = new String[1];
		}

		return this.arrayAdmUFOperadoraUsuario;
	}

	public void setArrayAdmTipoPessoa(String[] arrayAdmTipoPessoa) {
		this.arrayAdmTipoPessoa = arrayAdmTipoPessoa;
	}

	public String[] getArrayAdmTipoPessoa() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmTipoPessoa == null || this.arrayAdmTipoPessoa.length == 0) {
			this.arrayAdmTipoPessoa = new String[1];
		}

		return this.arrayAdmTipoPessoa;
	}

	public void setAdmUFOperadoraRelacionado(UFOperadoraUsuarioVO[] admUFOperadoraRelacionado) {
		this.admUFOperadoraRelacionado = admUFOperadoraRelacionado;
	}

	public UFOperadoraUsuarioVO[] getAdmUFOperadoraRelacionado() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize admUFOperadoraRelacionado if it is null or length == 0.
		// if(this.admUFOperadoraRelacionado == null ||
		// this.admUFOperadoraRelacionado.length == 0)
		// {
		// this.admUFOperadoraRelacionado = new UFOperadoraUsuarioVO[1];
		// this.admUFOperadoraRelacionado[0] = new UFOperadoraUsuarioVO(?);
		// }

		return this.admUFOperadoraRelacionado;
	}

	public void setAdmUFOperadoraExistente(UFOperadoraUsuarioVO[] admUFOperadoraExistente) {
		this.admUFOperadoraExistente = admUFOperadoraExistente;
	}

	public UFOperadoraUsuarioVO[] getAdmUFOperadoraExistente() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize admUFOperadoraExistente if it is null or length == 0.
		// if(this.admUFOperadoraExistente == null ||
		// this.admUFOperadoraExistente.length == 0)
		// {
		// this.admUFOperadoraExistente = new UFOperadoraUsuarioVO[1];
		// this.admUFOperadoraExistente[0] = new UFOperadoraUsuarioVO(?);
		// }

		return this.admUFOperadoraExistente;
	}

	public void setIdQuestionarioAtual(String idQuestionarioAtual) {
		this.idQuestionarioAtual = idQuestionarioAtual;
	}

	public String getIdQuestionarioAtual() {
		return this.idQuestionarioAtual;
	}

	public void setQuestionariosAssociados(AdmQuestionarioVO[] questionariosAssociados) {
		this.questionariosAssociados = questionariosAssociados;
	}

	public AdmQuestionarioVO[] getQuestionariosAssociados() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize questionariosAssociados if it is null or length == 0.
		// if(this.questionariosAssociados == null ||
		// this.questionariosAssociados.length == 0)
		// {
		// this.questionariosAssociados = new AdmQuestionarioVO[1];
		// this.questionariosAssociados[0] = new AdmQuestionarioVO(?);
		// }

		return this.questionariosAssociados;
	}

	public void setIdTipoPessoaAtual(String idTipoPessoaAtual) {
		this.idTipoPessoaAtual = idTipoPessoaAtual;
	}

	public String getIdTipoPessoaAtual() {
		return this.idTipoPessoaAtual;
	}

	public void setAdmTipoPessoaVO(AdmTipoPessoaVO[] admTipoPessoaVO) {
		this.admTipoPessoaVO = admTipoPessoaVO;
	}

	public AdmTipoPessoaVO[] getAdmTipoPessoaVO() {

		if (this.admTipoPessoaVO == null) {
			this.admTipoPessoaVO = new AdmTipoPessoaVO[0];
		}

		return this.admTipoPessoaVO;
	}
}