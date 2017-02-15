package admsistemas.admArvoreParametro;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroRetornoVODocument.AdmArvoreParametroRetornoVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoParametroVODocument.AdmGrupoParametroVO;
import br.com.vivo.fo.admsistemas.vo.AdmVariavelRetornoVODocument.AdmVariavelRetornoVO;
import br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormArvoreParametro extends ActionForm {

	private static final long serialVersionUID = -3103820864316521382L;

	private String dsPathArquivo;
	private String idArquivo;
	private br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO.ArquivoGerado[] arquivosGerados;
	private VariaveisArvoreContatoVO variaveisArvoreContatoVO;
	private String[] idProcedenciaAssocArray;
	private String[] idProcedenciaExistArray;
	private String listaArquivosLength;
	private String arquivoSelecionado;
	private String[] listaArquivos;
	private String idContatoGrupoRadio;
	private String limpaForm;
	private ArrayList disponivelAssoc;
	private ArrayList disponivelExist;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosExist;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosAssoc;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc;
	private String[] idTipoRelacionamentoAssocArray;
	private String[] idTipoPessoaAssocArray;
	private String[] idTipoLinhaAssocArray;
	private String[] idTipoCarteiraAssocArray;
	private String[] idSegmentacaoAssocArray;
	private String[] idOperadoraAssocArray;
	private String[] idGrupoTratamentoAssocArray;
	private String[] idGrupoRetornoAssocArray;
	private String[] idGrupoAberturaAssocArray;
	private String[] idFechamentoAssocArray;
	private String[] idDisponivelAssocArray;
	private String[] idCanalAssocArray;
	private String[] idNaturezaAssocArray;
	private String[] idNaturezaExistArray;
	private String[] idCanalExistArray;
	private String[] idTipoRelacionamentoExistArray;
	private String[] idTipoPessoaExistArray;
	private String[] idTipoLinhaExistArray;
	private String[] idTipoCarteiraExistArray;
	private String[] idSegmentacaoExistArray;
	private String[] idOperadoraExistArray;
	private String[] idGrupoTratamentoExistArray;
	private String[] idGrupoRetornoExistArray;
	private String[] idGrupoAberturaExistArray;
	private String[] idFechamentoExistArray;
	private String[] idDisponivelExistArray;
	private AdmVariavelRetornoVO[] admVariavelRetorno;
	private AdmGrupoParametroVO[] admArvoreGrupoRetorno;
	private AdmArvoreParametroRetornoVO admArvoreRetorno;
	private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombos;
	private String msgError = ConstantesCRM.SVAZIO;

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}

	public void setContainerCombos(
			br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombos) {
		this.containerCombos = containerCombos;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO getContainerCombos() {
		return this.containerCombos;
	}

	public void setAdmArvoreGrupoRetorno(AdmGrupoParametroVO[] admArvoreGrupoRetorno) {
		this.admArvoreGrupoRetorno = admArvoreGrupoRetorno;
	}

	public AdmGrupoParametroVO[] getAdmArvoreGrupoRetorno() {
		return this.admArvoreGrupoRetorno;
	}

	public void setAdmVariavelRetorno(AdmVariavelRetornoVO[] admVariavelRetorno) {
		this.admVariavelRetorno = admVariavelRetorno;
	}

	public AdmVariavelRetornoVO[] getAdmVariavelRetorno() {
		return this.admVariavelRetorno;
	}

	public void setAdmArvoreRetorno(AdmArvoreParametroRetornoVO admArvoreRetorno) {
		this.admArvoreRetorno = admArvoreRetorno;
	}

	public AdmArvoreParametroRetornoVO getAdmArvoreRetorno() {
		return this.admArvoreRetorno;
	}

	public void setIdDisponivelExistArray(String[] idDisponivelExistArray) {
		this.idDisponivelExistArray = idDisponivelExistArray;
	}

	public String[] getIdDisponivelExistArray() {
		if (this.idDisponivelExistArray == null || this.idDisponivelExistArray.length == 0) {
			this.idDisponivelExistArray = new String[1];
		}
		return this.idDisponivelExistArray;
	}

	public void setIdFechamentoExistArray(String[] idFechamentoExistArray) {
		this.idFechamentoExistArray = idFechamentoExistArray;
	}

	public String[] getIdFechamentoExistArray() {
		if (this.idFechamentoExistArray == null || this.idFechamentoExistArray.length == 0) {
			this.idFechamentoExistArray = new String[1];
		}
		return this.idFechamentoExistArray;
	}

	public void setIdGrupoAberturaExistArray(String[] idGrupoAberturaExistArray) {
		this.idGrupoAberturaExistArray = idGrupoAberturaExistArray;
	}

	public String[] getIdGrupoAberturaExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idGrupoAberturaExistArray == null || this.idGrupoAberturaExistArray.length == 0) {
			this.idGrupoAberturaExistArray = new String[1];
		}

		return this.idGrupoAberturaExistArray;
	}

	public void setIdGrupoRetornoExistArray(String[] idGrupoRetornoExistArray) {
		this.idGrupoRetornoExistArray = idGrupoRetornoExistArray;
	}

	public String[] getIdGrupoRetornoExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idGrupoRetornoExistArray == null || this.idGrupoRetornoExistArray.length == 0) {
			this.idGrupoRetornoExistArray = new String[1];
		}

		return this.idGrupoRetornoExistArray;
	}

	public void setIdGrupoTratamentoExistArray(String[] idGrupoTratamentoExistArray) {
		this.idGrupoTratamentoExistArray = idGrupoTratamentoExistArray;
	}

	public String[] getIdGrupoTratamentoExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idGrupoTratamentoExistArray == null || this.idGrupoTratamentoExistArray.length == 0) {
			this.idGrupoTratamentoExistArray = new String[1];
		}

		return this.idGrupoTratamentoExistArray;
	}

	public void setIdOperadoraExistArray(String[] idOperadoraExistArray) {
		this.idOperadoraExistArray = idOperadoraExistArray;
	}

	public String[] getIdOperadoraExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idOperadoraExistArray == null || this.idOperadoraExistArray.length == 0) {
			this.idOperadoraExistArray = new String[1];
		}

		return this.idOperadoraExistArray;
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

	public void setIdTipoLinhaExistArray(String[] idTipoLinhaExistArray) {
		this.idTipoLinhaExistArray = idTipoLinhaExistArray;
	}

	public String[] getIdTipoLinhaExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoLinhaExistArray == null || this.idTipoLinhaExistArray.length == 0) {
			this.idTipoLinhaExistArray = new String[1];
		}

		return this.idTipoLinhaExistArray;
	}

	public void setIdTipoPessoaExistArray(String[] idTipoPessoaExistArray) {
		this.idTipoPessoaExistArray = idTipoPessoaExistArray;
	}

	public String[] getIdTipoPessoaExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoPessoaExistArray == null || this.idTipoPessoaExistArray.length == 0) {
			this.idTipoPessoaExistArray = new String[1];
		}

		return this.idTipoPessoaExistArray;
	}

	public void setIdTipoRelacionamentoExistArray(String[] idTipoRelacionamentoExistArray) {
		this.idTipoRelacionamentoExistArray = idTipoRelacionamentoExistArray;
	}

	public String[] getIdTipoRelacionamentoExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoRelacionamentoExistArray == null
				|| this.idTipoRelacionamentoExistArray.length == 0) {
			this.idTipoRelacionamentoExistArray = new String[1];
		}

		return this.idTipoRelacionamentoExistArray;
	}

	public void setIdCanalExistArray(String[] idCanalExistArray) {
		this.idCanalExistArray = idCanalExistArray;
	}

	public String[] getIdCanalExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idCanalExistArray == null || this.idCanalExistArray.length == 0) {
			this.idCanalExistArray = new String[1];
		}

		return this.idCanalExistArray;
	}

	public void setIdNaturezaExistArray(String[] idNaturezaExistArray) {
		this.idNaturezaExistArray = idNaturezaExistArray;
	}

	public String[] getIdNaturezaExistArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idNaturezaExistArray == null || this.idNaturezaExistArray.length == 0) {
			this.idNaturezaExistArray = new String[1];
		}

		return this.idNaturezaExistArray;
	}

	public void setIdNaturezaAssocArray(String[] idNaturezaAssocArray) {
		this.idNaturezaAssocArray = idNaturezaAssocArray;
	}

	public String[] getIdNaturezaAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idNaturezaAssocArray == null || this.idNaturezaAssocArray.length == 0) {
			this.idNaturezaAssocArray = new String[1];
		}

		return this.idNaturezaAssocArray;
	}

	public void setIdCanalAssocArray(String[] idCanalAssocArray) {
		this.idCanalAssocArray = idCanalAssocArray;
	}

	public String[] getIdCanalAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idCanalAssocArray == null || this.idCanalAssocArray.length == 0) {
			this.idCanalAssocArray = new String[1];
		}

		return this.idCanalAssocArray;
	}

	public void setIdDisponivelAssocArray(String[] idDisponivelAssocArray) {
		this.idDisponivelAssocArray = idDisponivelAssocArray;
	}

	public String[] getIdDisponivelAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idDisponivelAssocArray == null || this.idDisponivelAssocArray.length == 0) {
			this.idDisponivelAssocArray = new String[1];
		}

		return this.idDisponivelAssocArray;
	}

	public void setIdFechamentoAssocArray(String[] idFechamentoAssocArray) {
		this.idFechamentoAssocArray = idFechamentoAssocArray;
	}

	public String[] getIdFechamentoAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idFechamentoAssocArray == null || this.idFechamentoAssocArray.length == 0) {
			this.idFechamentoAssocArray = new String[1];
		}

		return this.idFechamentoAssocArray;
	}

	public void setIdGrupoAberturaAssocArray(String[] idGrupoAberturaAssocArray) {
		this.idGrupoAberturaAssocArray = idGrupoAberturaAssocArray;
	}

	public String[] getIdGrupoAberturaAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idGrupoAberturaAssocArray == null || this.idGrupoAberturaAssocArray.length == 0) {
			this.idGrupoAberturaAssocArray = new String[1];
		}

		return this.idGrupoAberturaAssocArray;
	}

	public void setIdGrupoRetornoAssocArray(String[] idGrupoRetornoAssocArray) {
		this.idGrupoRetornoAssocArray = idGrupoRetornoAssocArray;
	}

	public String[] getIdGrupoRetornoAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idGrupoRetornoAssocArray == null || this.idGrupoRetornoAssocArray.length == 0) {
			this.idGrupoRetornoAssocArray = new String[1];
		}

		return this.idGrupoRetornoAssocArray;
	}

	public void setIdGrupoTratamentoAssocArray(String[] idGrupoTratamentoAssocArray) {
		this.idGrupoTratamentoAssocArray = idGrupoTratamentoAssocArray;
	}

	public String[] getIdGrupoTratamentoAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idGrupoTratamentoAssocArray == null || this.idGrupoTratamentoAssocArray.length == 0) {
			this.idGrupoTratamentoAssocArray = new String[1];
		}

		return this.idGrupoTratamentoAssocArray;
	}

	public void setIdOperadoraAssocArray(String[] idOperadoraAssocArray) {
		this.idOperadoraAssocArray = idOperadoraAssocArray;
	}

	public String[] getIdOperadoraAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idOperadoraAssocArray == null || this.idOperadoraAssocArray.length == 0) {
			this.idOperadoraAssocArray = new String[1];
		}

		return this.idOperadoraAssocArray;
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

	public void setIdTipoLinhaAssocArray(String[] idTipoLinhaAssocArray) {
		this.idTipoLinhaAssocArray = idTipoLinhaAssocArray;
	}

	public String[] getIdTipoLinhaAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoLinhaAssocArray == null || this.idTipoLinhaAssocArray.length == 0) {
			this.idTipoLinhaAssocArray = new String[1];
		}

		return this.idTipoLinhaAssocArray;
	}

	public void setIdTipoPessoaAssocArray(String[] idTipoPessoaAssocArray) {
		this.idTipoPessoaAssocArray = idTipoPessoaAssocArray;
	}

	public String[] getIdTipoPessoaAssocArray() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.idTipoPessoaAssocArray == null || this.idTipoPessoaAssocArray.length == 0) {
			this.idTipoPessoaAssocArray = new String[1];
		}

		return this.idTipoPessoaAssocArray;
	}

	public void setIdTipoRelacionamentoAssocArray(String[] idTipoRelacionamentoAssocArray) {
		this.idTipoRelacionamentoAssocArray = idTipoRelacionamentoAssocArray;
	}

	public String[] getIdTipoRelacionamentoAssocArray() {
		if (this.idTipoRelacionamentoAssocArray == null
				|| this.idTipoRelacionamentoAssocArray.length == 0) {
			this.idTipoRelacionamentoAssocArray = new String[1];
		}

		return this.idTipoRelacionamentoAssocArray;
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

	public void setContainerCombosAssoc(
			br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosAssoc) {
		this.containerCombosAssoc = containerCombosAssoc;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO getContainerCombosAssoc() {
		if (this.containerCombosAssoc == null) {
			this.containerCombosAssoc = AdmArvoreParametroCombosVO.Factory.newInstance();
		}
		return this.containerCombosAssoc;
	}

	public void setContainerCombosExist(
			br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosExist) {
		this.containerCombosExist = containerCombosExist;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO getContainerCombosExist() {
		if (this.containerCombosExist == null) {
			this.containerCombosExist = AdmArvoreParametroCombosVO.Factory.newInstance();
		}
		return this.containerCombosExist;
	}

	public void setDisponivelExist(ArrayList disponivelExist) {
		this.disponivelExist = disponivelExist;
	}

	public ArrayList getDisponivelExist() {
		if (this.disponivelExist == null) {
			this.disponivelExist = new ArrayList();
		}
		return this.disponivelExist;
	}

	public void setDisponivelAssoc(ArrayList disponivelAssoc) {
		this.disponivelAssoc = disponivelAssoc;
	}

	public ArrayList getDisponivelAssoc() {
		if (this.disponivelAssoc == null) {
			this.disponivelAssoc = new ArrayList();
		}
		return this.disponivelAssoc;
	}

	public void setLimpaForm(String limpaForm) {
		this.limpaForm = limpaForm;
	}

	public String getLimpaForm() {
		return this.limpaForm;
	}

	public void setIdContatoGrupoRadio(String idContatoGrupoRadio) {
		this.idContatoGrupoRadio = idContatoGrupoRadio;
	}

	public String getIdContatoGrupoRadio() {
		if (this.idContatoGrupoRadio == null) {
			this.idContatoGrupoRadio = ConstantesCRM.SVAZIO;
		}
		return this.idContatoGrupoRadio;
	}

	public void setListaArquivos(String[] listaArquivos) {
		this.listaArquivos = listaArquivos;
	}

	public String[] getListaArquivos() {
		if (this.listaArquivos == null || this.listaArquivos.length == 0) {
			this.listaArquivos = new String[1];
		}
		return this.listaArquivos;
	}

	public void setArquivoSelecionado(String arquivoSelecionado) {
		this.arquivoSelecionado = arquivoSelecionado;
	}

	public String getArquivoSelecionado() {
		return this.arquivoSelecionado;
	}

	public void setListaArquivosLength(String listaArquivosLength) {
		this.listaArquivosLength = listaArquivosLength;
	}

	public String getListaArquivosLength() {
		return this.listaArquivosLength;
	}

	public void setIdProcedenciaExistArray(String[] idProcedenciaExistArray) {
		this.idProcedenciaExistArray = idProcedenciaExistArray;
	}

	public String[] getIdProcedenciaExistArray() {
		if (this.idProcedenciaExistArray == null || this.idProcedenciaExistArray.length == 0) {
			this.idProcedenciaExistArray = new String[1];
		}
		return this.idProcedenciaExistArray;
	}

	public void setIdProcedenciaAssocArray(String[] idProcedenciaAssocArray) {
		this.idProcedenciaAssocArray = idProcedenciaAssocArray;
	}

	public String[] getIdProcedenciaAssocArray() {
		if (this.idProcedenciaAssocArray == null || this.idProcedenciaAssocArray.length == 0) {
			this.idProcedenciaAssocArray = new String[1];
		}
		return this.idProcedenciaAssocArray;
	}

	public void setVariaveisArvoreContatoVO(VariaveisArvoreContatoVO variaveisArvoreContatoVO) {
		this.variaveisArvoreContatoVO = variaveisArvoreContatoVO;
	}

	public VariaveisArvoreContatoVO getVariaveisArvoreContatoVO() {
		if (this.variaveisArvoreContatoVO == null) {
			this.variaveisArvoreContatoVO = VariaveisArvoreContatoVO.Factory.newInstance();
		}
		return this.variaveisArvoreContatoVO;
	}

	public void setArquivosGerados(
			br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO.ArquivoGerado[] arquivosGerados) {
		this.arquivosGerados = arquivosGerados;
	}

	public br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO.ArquivoGerado[] getArquivosGerados() {
		return this.arquivosGerados;
	}

	public void setIdArquivo(String idArquivo) {
		this.idArquivo = idArquivo;
	}

	public String getIdArquivo() {
		return this.idArquivo;
	}

	public void setDsPathArquivo(String dsPathArquivo) {
		this.dsPathArquivo = dsPathArquivo;
	}

	public String getDsPathArquivo() {
		return this.dsPathArquivo;
	}
}