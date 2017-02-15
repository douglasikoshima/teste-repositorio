package admsistemas.admArvoreContato.workflow.abaRetorno;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmCanalEntradaVODocument.AdmCanalEntradaVO;
import br.com.vivo.fo.admsistemas.vo.AdmCarteirizacaoVODocument.AdmCarteirizacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.admsistemas.vo.AdmPessoaVODocument.AdmPessoaVO;
import br.com.vivo.fo.admsistemas.vo.AdmProcedenciaVODocument.AdmProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSegmentacaoVODocument.AdmSegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoClienteVODocument.AdmTipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoVODocument.AdmTipoRetornoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoUfOperadoraVODocument.AdmTipoUfOperadoraVO;

public class FormRetorno extends ActionForm implements Serializable {

	private static final long serialVersionUID = 9149091436933205725L;

	private String indicativo;

	private String[] arrayAdmGrupoExistente;

	private String[] arrayAdmGrupoAssociado;

	private AdmGrupoVO[] admGrupoAssociadoVO;

	private AdmGrupoVO[] admGrupoExistenteVO;

	private AdmCanalEntradaVO[] admCanalEntradaAssociadoVO;

	private AdmCanalEntradaVO[] admCanalEntradaExistenteVO;

	private String[] arrayAdmCanalEntradaAssociado;

	private String[] arrayAdmCanalEntradaExistente;

	private String[] arrayAdmCarteirizacaoExistente;

	private String[] arrayAdmCarteirizacaoAssociada;

	private AdmCarteirizacaoVO[] admCarteirizacaoAssociadaVO;

	private AdmCarteirizacaoVO[] admCarteirizacaoExistenteVO;

	private String[] arraySegmentacaoAssociada;

	private String[] arraySegmentacaoExistente;

	private AdmSegmentacaoVO[] admSegmentacaoAssociadaVO;

	private AdmSegmentacaoVO[] admSegmentacaoExistenteVO;

	private String[] arrayAdmTipoClienteAssociado;

	private String[] arrayAdmTipoClienteExistente;

	private AdmTipoClienteVO[] admTipoClienteAssociadoVO;

	private AdmTipoClienteVO[] admTipoClienteExistenteVO;

	private String[] arrayAdmProcedenciaAssociada;

	private String[] arrayAdmProcedenciaExistente;

	private AdmProcedenciaVO[] admProcedenciaAssociadaVO;

	private AdmProcedenciaVO[] admProcedenciaExistenteVO;

	private String[] arrayAdmTipoLinhaAssociada;

	private String[] arrayAdmTipoLinhaExistente;

	private AdmTipoLinhaVO[] admTipoLinhaAssociadaVO;

	private AdmTipoLinhaVO[] admTipoLinhaExistenteVO;

	private String[] arrayAdmPessoaAssociada;

	private String[] arrayAdmPessoaExistente;

	private AdmPessoaVO[] admPessoaAssociadaVO;

	private AdmPessoaVO[] admPessoaExistenteVO;

	private String[] arrayAdmTipoUfOperadoraAssociada;

	private String[] arrayAdmTipoUfOperadoraExistente;

	private AdmTipoUfOperadoraVO[] admTipoUfOperadoraAssociadaVO;

	private AdmTipoUfOperadoraVO[] admTipoUfOperadoraExistenteVO;

	private String[] arrayAdmTipoRetorno;

	private AdmTipoRetornoVO[] admTipoRetornoVO;

	private String idTipoRetornoAtivo;

	private String idContato;

	/**
	 * Construtor Default
	 */
	public FormRetorno() {
		admCanalEntradaAssociadoVO = new AdmCanalEntradaVO[] { AdmCanalEntradaVO.Factory.newInstance() };
		admCarteirizacaoAssociadaVO = new AdmCarteirizacaoVO[] { AdmCarteirizacaoVO.Factory
				.newInstance() };
		admGrupoAssociadoVO = new AdmGrupoVO[] { AdmGrupoVO.Factory.newInstance() };
		admPessoaAssociadaVO = new AdmPessoaVO[] { AdmPessoaVO.Factory.newInstance() };
		admProcedenciaAssociadaVO = new AdmProcedenciaVO[] { AdmProcedenciaVO.Factory.newInstance() };
		admSegmentacaoAssociadaVO = new AdmSegmentacaoVO[] { AdmSegmentacaoVO.Factory.newInstance() };
		admTipoClienteAssociadoVO = new AdmTipoClienteVO[] { AdmTipoClienteVO.Factory.newInstance() };
		admTipoLinhaAssociadaVO = new AdmTipoLinhaVO[] { AdmTipoLinhaVO.Factory.newInstance() };
		admTipoUfOperadoraAssociadaVO = new AdmTipoUfOperadoraVO[] { AdmTipoUfOperadoraVO.Factory
				.newInstance() };

		admCanalEntradaExistenteVO = new AdmCanalEntradaVO[] { AdmCanalEntradaVO.Factory.newInstance() };
		admCarteirizacaoExistenteVO = new AdmCarteirizacaoVO[] { AdmCarteirizacaoVO.Factory
				.newInstance() };
		admGrupoExistenteVO = new AdmGrupoVO[] { AdmGrupoVO.Factory.newInstance() };
		admPessoaExistenteVO = new AdmPessoaVO[] { AdmPessoaVO.Factory.newInstance() };
		admProcedenciaExistenteVO = new AdmProcedenciaVO[] { AdmProcedenciaVO.Factory.newInstance() };
		admSegmentacaoExistenteVO = new AdmSegmentacaoVO[] { AdmSegmentacaoVO.Factory.newInstance() };
		admTipoClienteExistenteVO = new AdmTipoClienteVO[] { AdmTipoClienteVO.Factory.newInstance() };
		admTipoLinhaExistenteVO = new AdmTipoLinhaVO[] { AdmTipoLinhaVO.Factory.newInstance() };
		// formRetorno.setIdTipoRetornoAtivo(admRetornoContainerVO.getIdTipoRetornoAtivo());
		admTipoUfOperadoraExistenteVO = new AdmTipoUfOperadoraVO[] { AdmTipoUfOperadoraVO.Factory
				.newInstance() };

	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setIdTipoRetornoAtivo(String idTipoRetornoAtivo) {
		this.idTipoRetornoAtivo = idTipoRetornoAtivo;
	}

	public String getIdTipoRetornoAtivo() {
		return this.idTipoRetornoAtivo;
	}

	public void setAdmTipoRetornoVO(AdmTipoRetornoVO[] admTipoRetornoVO) {
		this.admTipoRetornoVO = admTipoRetornoVO;
	}

	public AdmTipoRetornoVO[] getAdmTipoRetornoVO() {
		return this.admTipoRetornoVO;
	}

	public void setArrayAdmTipoRetorno(String[] arrayAdmTipoRetorno) {
		this.arrayAdmTipoRetorno = arrayAdmTipoRetorno;
	}

	public String[] getArrayAdmTipoRetorno() {
		if (this.arrayAdmTipoRetorno == null || this.arrayAdmTipoRetorno.length == 0) {
			this.arrayAdmTipoRetorno = new String[1];
		}
		return this.arrayAdmTipoRetorno;
	}

	public void setArrayAdmTipoUfOperadoraExistente(String[] arrayAdmTipoUfOperadoraExistente) {
		this.arrayAdmTipoUfOperadoraExistente = arrayAdmTipoUfOperadoraExistente;
	}

	public String[] getArrayAdmTipoUfOperadoraExistente() {
		if (this.arrayAdmTipoUfOperadoraExistente == null
				|| this.arrayAdmTipoUfOperadoraExistente.length == 0) {
			this.arrayAdmTipoUfOperadoraExistente = new String[1];
		}
		return this.arrayAdmTipoUfOperadoraExistente;
	}

	public void setArrayAdmTipoUfOperadoraAssociada(String[] arrayAdmTipoUfOperadoraAssociada) {
		this.arrayAdmTipoUfOperadoraAssociada = arrayAdmTipoUfOperadoraAssociada;
	}

	public String[] getArrayAdmTipoUfOperadoraAssociada() {
		if (this.arrayAdmTipoUfOperadoraAssociada == null
				|| this.arrayAdmTipoUfOperadoraAssociada.length == 0) {
			this.arrayAdmTipoUfOperadoraAssociada = new String[1];
		}
		return this.arrayAdmTipoUfOperadoraAssociada;
	}

	public void setAdmPessoaExistenteVO(AdmPessoaVO[] admPessoaExistenteVO) {
		this.admPessoaExistenteVO = admPessoaExistenteVO;
	}

	public AdmPessoaVO[] getAdmPessoaExistenteVO() {
		return this.admPessoaExistenteVO;
	}

	public void setAdmPessoaAssociadaVO(AdmPessoaVO[] admPessoaAssociadaVO) {
		this.admPessoaAssociadaVO = admPessoaAssociadaVO;
	}

	public AdmPessoaVO[] getAdmPessoaAssociadaVO() {
		return this.admPessoaAssociadaVO;
	}

	public void setArrayAdmPessoaExistente(String[] arrayAdmPessoaExistente) {
		this.arrayAdmPessoaExistente = arrayAdmPessoaExistente;
	}

	public String[] getArrayAdmPessoaExistente() {
		if (this.arrayAdmPessoaExistente == null || this.arrayAdmPessoaExistente.length == 0) {
			this.arrayAdmPessoaExistente = new String[1];
		}
		return this.arrayAdmPessoaExistente;
	}

	public void setArrayAdmPessoaAssociada(String[] arrayAdmPessoaAssociada) {
		this.arrayAdmPessoaAssociada = arrayAdmPessoaAssociada;
	}

	public String[] getArrayAdmPessoaAssociada() {
		if (this.arrayAdmPessoaAssociada == null || this.arrayAdmPessoaAssociada.length == 0) {
			this.arrayAdmPessoaAssociada = new String[1];
		}
		return this.arrayAdmPessoaAssociada;
	}

	public void setAdmTipoUfOperadoraExistenteVO(AdmTipoUfOperadoraVO[] admTipoUfOperadoraExistenteVO) {
		this.admTipoUfOperadoraExistenteVO = admTipoUfOperadoraExistenteVO;
	}

	public AdmTipoUfOperadoraVO[] getAdmTipoUfOperadoraExistenteVO() {
		return this.admTipoUfOperadoraExistenteVO;
	}

	public void setAdmTipoUfOperadoraAssociadaVO(AdmTipoUfOperadoraVO[] admTipoUfOperadoraAssociadaVO) {
		this.admTipoUfOperadoraAssociadaVO = admTipoUfOperadoraAssociadaVO;
	}

	public AdmTipoUfOperadoraVO[] getAdmTipoUfOperadoraAssociadaVO() {
		return this.admTipoUfOperadoraAssociadaVO;
	}

	public void setAdmTipoLinhaExistenteVO(AdmTipoLinhaVO[] admTipoLinhaExistenteVO) {
		this.admTipoLinhaExistenteVO = admTipoLinhaExistenteVO;
	}

	public AdmTipoLinhaVO[] getAdmTipoLinhaExistenteVO() {
		return this.admTipoLinhaExistenteVO;
	}

	public void setAdmTipoLinhaAssociadaVO(AdmTipoLinhaVO[] admTipoLinhaAssociadaVO) {
		this.admTipoLinhaAssociadaVO = admTipoLinhaAssociadaVO;
	}

	public AdmTipoLinhaVO[] getAdmTipoLinhaAssociadaVO() {
		return this.admTipoLinhaAssociadaVO;
	}

	public void setArrayAdmTipoLinhaExistente(String[] arrayAdmTipoLinhaExistente) {
		this.arrayAdmTipoLinhaExistente = arrayAdmTipoLinhaExistente;
	}

	public String[] getArrayAdmTipoLinhaExistente() {
		if (this.arrayAdmTipoLinhaExistente == null || this.arrayAdmTipoLinhaExistente.length == 0) {
			this.arrayAdmTipoLinhaExistente = new String[1];
		}

		return this.arrayAdmTipoLinhaExistente;
	}

	public void setArrayAdmTipoLinhaAssociada(String[] arrayAdmTipoLinhaAssociada) {
		this.arrayAdmTipoLinhaAssociada = arrayAdmTipoLinhaAssociada;
	}

	public String[] getArrayAdmTipoLinhaAssociada() {
		if (this.arrayAdmTipoLinhaAssociada == null || this.arrayAdmTipoLinhaAssociada.length == 0) {
			this.arrayAdmTipoLinhaAssociada = new String[1];
		}

		return this.arrayAdmTipoLinhaAssociada;
	}

	public void setAdmProcedenciaExistenteVO(AdmProcedenciaVO[] admProcedenciaExistenteVO) {
		this.admProcedenciaExistenteVO = admProcedenciaExistenteVO;
	}

	public AdmProcedenciaVO[] getAdmProcedenciaExistenteVO() {
		return this.admProcedenciaExistenteVO;
	}

	public void setAdmProcedenciaAssociadaVO(AdmProcedenciaVO[] admProcedenciaAssociadaVO) {
		this.admProcedenciaAssociadaVO = admProcedenciaAssociadaVO;
	}

	public AdmProcedenciaVO[] getAdmProcedenciaAssociadaVO() {
		return this.admProcedenciaAssociadaVO;
	}

	public void setArrayAdmProcedenciaExistente(String[] arrayAdmProcedenciaExistente) {
		this.arrayAdmProcedenciaExistente = arrayAdmProcedenciaExistente;
	}

	public String[] getArrayAdmProcedenciaExistente() {
		if (this.arrayAdmProcedenciaExistente == null || this.arrayAdmProcedenciaExistente.length == 0) {
			this.arrayAdmProcedenciaExistente = new String[1];
		}

		return this.arrayAdmProcedenciaExistente;
	}

	public void setArrayAdmProcedenciaAssociada(String[] arrayAdmProcedenciaAssociada) {
		this.arrayAdmProcedenciaAssociada = arrayAdmProcedenciaAssociada;
	}

	public String[] getArrayAdmProcedenciaAssociada() {
		if (this.arrayAdmProcedenciaAssociada == null || this.arrayAdmProcedenciaAssociada.length == 0) {
			this.arrayAdmProcedenciaAssociada = new String[1];
		}

		return this.arrayAdmProcedenciaAssociada;
	}

	public void setAdmTipoClienteExistenteVO(AdmTipoClienteVO[] admTipoClienteExistenteVO) {
		this.admTipoClienteExistenteVO = admTipoClienteExistenteVO;
	}

	public AdmTipoClienteVO[] getAdmTipoClienteExistenteVO() {
		return this.admTipoClienteExistenteVO;
	}

	public void setAdmTipoClienteAssociadoVO(AdmTipoClienteVO[] admTipoClienteAssociadoVO) {
		this.admTipoClienteAssociadoVO = admTipoClienteAssociadoVO;
	}

	public AdmTipoClienteVO[] getAdmTipoClienteAssociadoVO() {
		return this.admTipoClienteAssociadoVO;
	}

	public void setArrayAdmTipoClienteExistente(String[] arrayAdmTipoClienteExistente) {
		this.arrayAdmTipoClienteExistente = arrayAdmTipoClienteExistente;
	}

	public String[] getArrayAdmTipoClienteExistente() {
		if (this.arrayAdmTipoClienteExistente == null || this.arrayAdmTipoClienteExistente.length == 0) {
			this.arrayAdmTipoClienteExistente = new String[1];
		}
		return this.arrayAdmTipoClienteExistente;
	}

	public void setArrayAdmTipoClienteAssociado(String[] arrayAdmTipoClienteAssociado) {
		this.arrayAdmTipoClienteAssociado = arrayAdmTipoClienteAssociado;
	}

	public String[] getArrayAdmTipoClienteAssociado() {
		if (this.arrayAdmTipoClienteAssociado == null || this.arrayAdmTipoClienteAssociado.length == 0) {
			this.arrayAdmTipoClienteAssociado = new String[1];
		}
		return this.arrayAdmTipoClienteAssociado;
	}

	public void setAdmSegmentacaoExistenteVO(AdmSegmentacaoVO[] admSegmentacaoExistenteVO) {
		this.admSegmentacaoExistenteVO = admSegmentacaoExistenteVO;
	}

	public AdmSegmentacaoVO[] getAdmSegmentacaoExistenteVO() {
		return this.admSegmentacaoExistenteVO;
	}

	public void setAdmSegmentacaoAssociadaVO(AdmSegmentacaoVO[] admSegmentacaoAssociadaVO) {
		this.admSegmentacaoAssociadaVO = admSegmentacaoAssociadaVO;
	}

	public AdmSegmentacaoVO[] getAdmSegmentacaoAssociadaVO() {
		return this.admSegmentacaoAssociadaVO;
	}

	public void setArraySegmentacaoExistente(String[] arraySegmentacaoExistente) {
		this.arraySegmentacaoExistente = arraySegmentacaoExistente;
	}

	public String[] getArraySegmentacaoExistente() {
		if (this.arraySegmentacaoExistente == null || this.arraySegmentacaoExistente.length == 0) {
			this.arraySegmentacaoExistente = new String[1];
		}

		return this.arraySegmentacaoExistente;
	}

	public void setArraySegmentacaoAssociada(String[] arraySegmentacaoAssociada) {
		this.arraySegmentacaoAssociada = arraySegmentacaoAssociada;
	}

	public String[] getArraySegmentacaoAssociada() {
		if (this.arraySegmentacaoAssociada == null || this.arraySegmentacaoAssociada.length == 0) {
			this.arraySegmentacaoAssociada = new String[1];
		}

		return this.arraySegmentacaoAssociada;
	}

	public void setAdmCarteirizacaoExistenteVO(AdmCarteirizacaoVO[] admCarteirizacaoExistenteVO) {
		this.admCarteirizacaoExistenteVO = admCarteirizacaoExistenteVO;
	}

	public AdmCarteirizacaoVO[] getAdmCarteirizacaoExistenteVO() {
		return this.admCarteirizacaoExistenteVO;
	}

	public void setAdmCarteirizacaoAssociadaVO(AdmCarteirizacaoVO[] admCarteirizacaoAssociadaVO) {
		this.admCarteirizacaoAssociadaVO = admCarteirizacaoAssociadaVO;
	}

	public AdmCarteirizacaoVO[] getAdmCarteirizacaoAssociadaVO() {
		return this.admCarteirizacaoAssociadaVO;
	}

	public void setArrayAdmCarteirizacaoAssociada(String[] arrayAdmCarteirizacaoAssociada) {
		this.arrayAdmCarteirizacaoAssociada = arrayAdmCarteirizacaoAssociada;
	}

	public String[] getArrayAdmCarteirizacaoAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmCarteirizacaoAssociada == null
				|| this.arrayAdmCarteirizacaoAssociada.length == 0) {
			this.arrayAdmCarteirizacaoAssociada = new String[1];
		}

		return this.arrayAdmCarteirizacaoAssociada;
	}

	public void setArrayAdmCarteirizacaoExistente(String[] arrayAdmCarteirizacaoExistente) {
		this.arrayAdmCarteirizacaoExistente = arrayAdmCarteirizacaoExistente;
	}

	public String[] getArrayAdmCarteirizacaoExistente() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmCarteirizacaoExistente == null
				|| this.arrayAdmCarteirizacaoExistente.length == 0) {
			this.arrayAdmCarteirizacaoExistente = new String[1];
		}

		return this.arrayAdmCarteirizacaoExistente;
	}

	public void setArrayAdmCanalEntradaExistente(String[] arrayAdmCanalEntradaExistente) {
		this.arrayAdmCanalEntradaExistente = arrayAdmCanalEntradaExistente;
	}

	public String[] getArrayAdmCanalEntradaExistente() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmCanalEntradaExistente == null
				|| this.arrayAdmCanalEntradaExistente.length == 0) {
			this.arrayAdmCanalEntradaExistente = new String[1];
		}

		return this.arrayAdmCanalEntradaExistente;
	}

	public void setArrayAdmCanalEntradaAssociado(String[] arrayAdmCanalEntradaAssociado) {
		this.arrayAdmCanalEntradaAssociado = arrayAdmCanalEntradaAssociado;
	}

	public String[] getArrayAdmCanalEntradaAssociado() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmCanalEntradaAssociado == null
				|| this.arrayAdmCanalEntradaAssociado.length == 0) {
			this.arrayAdmCanalEntradaAssociado = new String[1];
		}

		return this.arrayAdmCanalEntradaAssociado;
	}

	public void setAdmCanalEntradaExistenteVO(AdmCanalEntradaVO[] admCanalEntradaExistenteVO) {
		this.admCanalEntradaExistenteVO = admCanalEntradaExistenteVO;
	}

	public AdmCanalEntradaVO[] getAdmCanalEntradaExistenteVO() {
		return this.admCanalEntradaExistenteVO;
	}

	public void setAdmCanalEntradaAssociadoVO(AdmCanalEntradaVO[] admCanalEntradaAssociadoVO) {
		this.admCanalEntradaAssociadoVO = admCanalEntradaAssociadoVO;
	}

	public AdmCanalEntradaVO[] getAdmCanalEntradaAssociadoVO() {
		return this.admCanalEntradaAssociadoVO;
	}

	public void setAdmGrupoExistenteVO(AdmGrupoVO[] admGrupoExistenteVO) {
		this.admGrupoExistenteVO = admGrupoExistenteVO;
	}

	public AdmGrupoVO[] getAdmGrupoExistenteVO() {
		return this.admGrupoExistenteVO;
	}

	public void setAdmGrupoAssociadoVO(AdmGrupoVO[] admGrupoAssociadoVO) {
		this.admGrupoAssociadoVO = admGrupoAssociadoVO;
	}

	public AdmGrupoVO[] getAdmGrupoAssociadoVO() {
		return this.admGrupoAssociadoVO;
	}

	public void setArrayAdmGrupoAssociado(String[] arrayAdmGrupoAssociado) {
		this.arrayAdmGrupoAssociado = arrayAdmGrupoAssociado;
	}

	public String[] getArrayAdmGrupoAssociado() {
		if (this.arrayAdmGrupoAssociado == null || this.arrayAdmGrupoAssociado.length == 0) {
			this.arrayAdmGrupoAssociado = new String[1];
		}

		return this.arrayAdmGrupoAssociado;
	}

	public void setArrayAdmGrupoExistente(String[] arrayAdmGrupoExistente) {
		this.arrayAdmGrupoExistente = arrayAdmGrupoExistente;
	}

	public String[] getArrayAdmGrupoExistente() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayAdmGrupoExistente == null || this.arrayAdmGrupoExistente.length == 0) {
			this.arrayAdmGrupoExistente = new String[1];
		}

		return this.arrayAdmGrupoExistente;
	}

	public void setIndicativo(String indicativo) {
		this.indicativo = indicativo;
	}

	public String getIndicativo() {
		return this.indicativo;
	}
}
