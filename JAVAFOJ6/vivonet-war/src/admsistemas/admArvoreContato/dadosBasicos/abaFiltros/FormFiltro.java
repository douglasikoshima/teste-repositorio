package admsistemas.admArvoreContato.dadosBasicos.abaFiltros;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmSegmentacaoVODocument.AdmSegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoCarteiraVODocument.AdmTipoCarteiraVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoPessoaVODocument.AdmTipoPessoaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class FormFiltro extends ActionForm implements Serializable {

	private static final long serialVersionUID = 4876867325198999970L;

	private String msgError = ConstantesCRM.SVAZIO;
	private String[] d;
	private String[] c;
	private String[] b;
	private String[] a;
	private AdmTipoPessoaVO[] tipoPessoaExistenteVO;
	private AdmTipoPessoaVO[] tipoPessoaAssociadaVO;
	private AdmTipoLinhaVO[] tipoLinhaExistenteVO;
	private AdmTipoLinhaVO[] tipoLinhaAssociadaVO;
	private AdmTipoCarteiraVO[] tipoCarteiraAssociadaVO;
	private AdmTipoCarteiraVO[] tipoCarteiraExistenteVO;
	private AdmSegmentacaoVO[] tipoSegmentacaoAssociadaVO;
	private AdmSegmentacaoVO[] tipoSegmentacaoExistenteVO;
	private String[] arrayTipoCarteiraAssociada;
	private String[] arraySegmentacaoAssociada;
	private String[] arrayTipoLinhaAssociada;
	private String[] arrayTipoPessoaAssociada;
	private String idContato;

	public FormFiltro() {

		idContato = ConstantesCRM.SVAZIO;
		arrayTipoCarteiraAssociada = new String[0];
		arraySegmentacaoAssociada = new String[0];
		arrayTipoLinhaAssociada = new String[0];
		arrayTipoPessoaAssociada = new String[0];

		tipoPessoaExistenteVO = new AdmTipoPessoaVO[0];
		tipoPessoaAssociadaVO = new AdmTipoPessoaVO[0];
		tipoLinhaExistenteVO = new AdmTipoLinhaVO[0];
		tipoLinhaAssociadaVO = new AdmTipoLinhaVO[0];
		tipoCarteiraAssociadaVO = new AdmTipoCarteiraVO[0];
		tipoCarteiraExistenteVO = new AdmTipoCarteiraVO[0];
		tipoSegmentacaoAssociadaVO = new AdmSegmentacaoVO[0];
		tipoSegmentacaoExistenteVO = new AdmSegmentacaoVO[0];

	}

	public void setIdContato(String idContato) {
		this.idContato = idContato;
	}

	public String getIdContato() {
		return this.idContato;
	}

	public void setArrayTipoPessoaAssociada(String[] arrayTipoPessoaAssociada) {
		this.arrayTipoPessoaAssociada = arrayTipoPessoaAssociada;
	}

	public String[] getArrayTipoPessoaAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayTipoPessoaAssociada == null || this.arrayTipoPessoaAssociada.length == 0) {
			this.arrayTipoPessoaAssociada = new String[1];
		}

		return this.arrayTipoPessoaAssociada;
	}

	public void setArrayTipoLinhaAssociada(String[] arrayTipoLinhaAssociada) {
		this.arrayTipoLinhaAssociada = arrayTipoLinhaAssociada;
	}

	public String[] getArrayTipoLinhaAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayTipoLinhaAssociada == null || this.arrayTipoLinhaAssociada.length == 0) {
			this.arrayTipoLinhaAssociada = new String[1];
		}

		return this.arrayTipoLinhaAssociada;
	}

	public void setArraySegmentacaoAssociada(String[] arraySegmentacaoAssociada) {
		this.arraySegmentacaoAssociada = arraySegmentacaoAssociada;
	}

	public String[] getArraySegmentacaoAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arraySegmentacaoAssociada == null || this.arraySegmentacaoAssociada.length == 0) {
			this.arraySegmentacaoAssociada = new String[1];
		}

		return this.arraySegmentacaoAssociada;
	}

	public void setArrayTipoCarteiraAssociada(String[] arrayTipoCarteiraAssociada) {
		this.arrayTipoCarteiraAssociada = arrayTipoCarteiraAssociada;
	}

	public String[] getArrayTipoCarteiraAssociada() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.arrayTipoCarteiraAssociada == null || this.arrayTipoCarteiraAssociada.length == 0) {
			this.arrayTipoCarteiraAssociada = new String[1];
		}

		return this.arrayTipoCarteiraAssociada;
	}

	public void setTipoSegmentacaoExistenteVO(AdmSegmentacaoVO[] tipoSegmentacaoExistenteVO) {
		this.tipoSegmentacaoExistenteVO = tipoSegmentacaoExistenteVO;
	}

	public AdmSegmentacaoVO[] getTipoSegmentacaoExistenteVO() {
		return this.tipoSegmentacaoExistenteVO;
	}

	public void setTipoSegmentacaoAssociadaVO(AdmSegmentacaoVO[] tipoSegmentacaoAssociadaVO) {
		this.tipoSegmentacaoAssociadaVO = tipoSegmentacaoAssociadaVO;
	}

	public AdmSegmentacaoVO[] getTipoSegmentacaoAssociadaVO() {
		return this.tipoSegmentacaoAssociadaVO;
	}

	public void setTipoCarteiraExistenteVO(AdmTipoCarteiraVO[] tipoCarteiraExistenteVO) {
		this.tipoCarteiraExistenteVO = tipoCarteiraExistenteVO;
	}

	public AdmTipoCarteiraVO[] getTipoCarteiraExistenteVO() {
		return this.tipoCarteiraExistenteVO;
	}

	public void setTipoCarteiraAssociadaVO(AdmTipoCarteiraVO[] tipoCarteiraAssociadaVO) {
		this.tipoCarteiraAssociadaVO = tipoCarteiraAssociadaVO;
	}

	public AdmTipoCarteiraVO[] getTipoCarteiraAssociadaVO() {
		return this.tipoCarteiraAssociadaVO;
	}

	public void setTipoLinhaAssociadaVO(AdmTipoLinhaVO[] tipoLinhaAssociadaVO) {
		this.tipoLinhaAssociadaVO = tipoLinhaAssociadaVO;
	}

	public AdmTipoLinhaVO[] getTipoLinhaAssociadaVO() {
		return this.tipoLinhaAssociadaVO;
	}

	public void setTipoLinhaExistenteVO(AdmTipoLinhaVO[] tipoLinhaExistenteVO) {
		this.tipoLinhaExistenteVO = tipoLinhaExistenteVO;
	}

	public AdmTipoLinhaVO[] getTipoLinhaExistenteVO() {
		return this.tipoLinhaExistenteVO;
	}

	public void setTipoPessoaAssociadaVO(AdmTipoPessoaVO[] tipoPessoaAssociadaVO) {
		this.tipoPessoaAssociadaVO = tipoPessoaAssociadaVO;
	}

	public AdmTipoPessoaVO[] getTipoPessoaAssociadaVO() {
		return this.tipoPessoaAssociadaVO;
	}

	public void setTipoPessoaExistenteVO(AdmTipoPessoaVO[] tipoPessoaExistenteVO) {
		this.tipoPessoaExistenteVO = tipoPessoaExistenteVO;
	}

	public AdmTipoPessoaVO[] getTipoPessoaExistenteVO() {
		return this.tipoPessoaExistenteVO;
	}

	public void setA(String[] a) {
		this.a = a;
	}

	public String[] getA() {
		if (this.a == null || this.a.length == 0) {
			this.a = new String[1];
		}
		return this.a;
	}

	public void setB(String[] b) {
		this.b = b;
	}

	public String[] getB() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.b == null || this.b.length == 0) {
			this.b = new String[1];
		}

		return this.b;
	}

	public void setC(String[] c) {
		this.c = c;
	}

	public String[] getC() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.c == null || this.c.length == 0) {
			this.c = new String[1];
		}

		return this.c;
	}

	public void setD(String[] d) {
		this.d = d;
	}

	public String[] getD() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.d == null || this.d.length == 0) {
			this.d = new String[1];
		}

		return this.d;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public String getMsgError() {
		return this.msgError;
	}
}