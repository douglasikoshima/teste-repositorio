package admsistemas.admArvoreConsulta;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais;

public class FormArvoreConsulta extends ActionForm implements Serializable {

	private static final long serialVersionUID = -8765549686502073069L;

	private String inProximo;
	private String paginaSeleciona;
	private String totalPagina;
	private String numTotalRegistro;
	private String nmContato;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosRetornoVO[] dadosRetorno;
	private String inFolha;
	private String inDisponibilidade;
	private String dsPath;
	private String idContatoPai;
	private AdmArvoreParametroCombosVO containerCombosAssoc;
	private AdmArvoreParametroCombosVO containerCombosExist;
	private AdmArvoreParametroCombosVO containerCombos;
	private String limpaForm;
	private String[] idContatoArray;
	private String idContato;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc;
	private String[] idTipoCarteiraExistArray;
	private String[] idTipoCarteiraAssocArray;
	private String[] idSegmentacaoExistArray;
	private String[] idSegmentacaoAssocArray;
	private String[] idProcedenciaExistArray;
	private String[] idProcedenciaAssocArray;
	private String msgError;

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setIdProcedenciaAssocArray(String[] idProcedenciaAssocArray) {
		this.idProcedenciaAssocArray = idProcedenciaAssocArray;
	}

	public String[] getIdProcedenciaAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idProcedenciaAssocArray == null || this.idProcedenciaAssocArray.length == 0) {
			this.idProcedenciaAssocArray = new String[1];
		}

		return this.idProcedenciaAssocArray;
	}

	public void setIdProcedenciaExistArray(String[] idProcedenciaExistArray) {
		this.idProcedenciaExistArray = idProcedenciaExistArray;
	}

	public String[] getIdProcedenciaExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idProcedenciaExistArray == null || this.idProcedenciaExistArray.length == 0) {
			this.idProcedenciaExistArray = new String[1];
		}

		return this.idProcedenciaExistArray;
	}

	public void setIdSegmentacaoAssocArray(String[] idSegmentacaoAssocArray) {
		this.idSegmentacaoAssocArray = idSegmentacaoAssocArray;
	}

	public String[] getIdSegmentacaoAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idSegmentacaoAssocArray == null || this.idSegmentacaoAssocArray.length == 0) {
			this.idSegmentacaoAssocArray = new String[1];
		}

		return this.idSegmentacaoAssocArray;
	}

	public void setIdSegmentacaoExistArray(String[] idSegmentacaoExistArray) {
		this.idSegmentacaoExistArray = idSegmentacaoExistArray;
	}

	public String[] getIdSegmentacaoExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idSegmentacaoExistArray == null || this.idSegmentacaoExistArray.length == 0) {
			this.idSegmentacaoExistArray = new String[1];
		}

		return this.idSegmentacaoExistArray;
	}

	public void setIdTipoCarteiraAssocArray(String[] idTipoCarteiraAssocArray) {
		this.idTipoCarteiraAssocArray = idTipoCarteiraAssocArray;
	}

	public String[] getIdTipoCarteiraAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoCarteiraAssocArray == null || this.idTipoCarteiraAssocArray.length == 0) {
			this.idTipoCarteiraAssocArray = new String[1];
		}

		return this.idTipoCarteiraAssocArray;
	}

	public void setIdTipoCarteiraExistArray(String[] idTipoCarteiraExistArray) {
		this.idTipoCarteiraExistArray = idTipoCarteiraExistArray;
	}

	public String[] getIdTipoCarteiraExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoCarteiraExistArray == null || this.idTipoCarteiraExistArray.length == 0) {
			this.idTipoCarteiraExistArray = new String[1];
		}

		return this.idTipoCarteiraExistArray;
	}

	public void setDadosAtuaisAssoc(
			br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc) {
		this.dadosAtuaisAssoc = dadosAtuaisAssoc;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais getDadosAtuaisAssoc() {
		if (this.dadosAtuaisAssoc == null) {
			this.dadosAtuaisAssoc = DadosAtuais.Factory.newInstance();
		}

		return this.dadosAtuaisAssoc;
	}

	public void setDadosAtuaisExist(
			br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist) {
		this.dadosAtuaisExist = dadosAtuaisExist;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais getDadosAtuaisExist() {
		if (this.dadosAtuaisExist == null) {
			this.dadosAtuaisExist = DadosAtuais.Factory.newInstance();
		}

		return this.dadosAtuaisExist;
	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setIdContatoArray(String[] idContatoArray) {
		this.idContatoArray = idContatoArray;
	}

	public String[] getIdContatoArray() {
		if (this.idContatoArray == null || this.idContatoArray.length == 0) {
			this.idContatoArray = new String[1];
		}

		return this.idContatoArray;
	}

	public void setLimpaForm(String limpaForm) {
		this.limpaForm = limpaForm;
	}

	public String getLimpaForm() {
		return this.limpaForm;
	}

	public void setContainerCombos(AdmArvoreParametroCombosVO containerCombos) {
		this.containerCombos = containerCombos;
	}

	public AdmArvoreParametroCombosVO getContainerCombos() {
		return this.containerCombos;
	}

	public void setContainerCombosExist(AdmArvoreParametroCombosVO containerCombosExist) {
		this.containerCombosExist = containerCombosExist;
	}

	public AdmArvoreParametroCombosVO getContainerCombosExist() {

		if (this.containerCombosExist == null) {
			this.containerCombosExist = AdmArvoreParametroCombosVO.Factory.newInstance();
		}

		return this.containerCombosExist;
	}

	public void setContainerCombosAssoc(AdmArvoreParametroCombosVO containerCombosAssoc) {
		this.containerCombosAssoc = containerCombosAssoc;
	}

	public AdmArvoreParametroCombosVO getContainerCombosAssoc() {

		if (this.containerCombosAssoc == null) {
			this.containerCombosAssoc = AdmArvoreParametroCombosVO.Factory.newInstance();
		}

		return this.containerCombosAssoc;
	}

	public void setIdContatoPai(String idContatoPai) {
		this.idContatoPai = idContatoPai;
	}

	public String getIdContatoPai() {
		return this.idContatoPai;
	}

	public void setDsPath(String dsPath) {
		this.dsPath = dsPath;
	}

	public String getDsPath() {
		return this.dsPath;
	}

	public void setInDisponibilidade(String inDisponibilidade) {
		this.inDisponibilidade = inDisponibilidade;
	}

	public String getInDisponibilidade() {
		return this.inDisponibilidade;
	}

	public void setInFolha(String inFolha) {
		this.inFolha = inFolha;
	}

	public String getInFolha() {
		return this.inFolha;
	}

	public void setDadosRetorno(
			br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosRetornoVO[] dadosRetorno) {
		this.dadosRetorno = dadosRetorno;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosRetornoVO[] getDadosRetorno() {
		return this.dadosRetorno;
	}

	public void setNmContato(String nmContato) {
		this.nmContato = nmContato;
	}

	public String getNmContato() {
		return this.nmContato;
	}

	public void setNumTotalRegistro(String numTotalRegistro) {
		this.numTotalRegistro = numTotalRegistro;
	}

	public String getNumTotalRegistro() {
		return this.numTotalRegistro;
	}

	public void setTotalPagina(String totalPagina) {
		this.totalPagina = totalPagina;
	}

	public String getTotalPagina() {
		return this.totalPagina;
	}

	public void setPaginaSeleciona(String paginaSeleciona) {
		this.paginaSeleciona = paginaSeleciona;
	}

	public String getPaginaSeleciona() {
		return this.paginaSeleciona;
	}

	public void setInProximo(String inProximo) {
		this.inProximo = inProximo;
	}

	public String getInProximo() {
		return this.inProximo;
	}
}