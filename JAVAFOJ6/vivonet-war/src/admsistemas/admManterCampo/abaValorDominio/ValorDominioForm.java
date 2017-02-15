package admsistemas.admManterCampo.abaValorDominio;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioVODocument.AdmDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioValorIncluiVODocument.AdmDominioValorIncluiVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominiosVODocument.AdmDominiosVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioCopiaVODocument.AdmTabelaDominioCopiaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaSimplVODocument.AdmTipoLinhaSimplVO;
import br.com.vivo.fo.admsistemas.vo.AdmUFOperadoraSimplVODocument.AdmUFOperadoraSimplVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class ValorDominioForm extends ActionForm {

	private static final long serialVersionUID = -4412882907411334257L;

	private String[] tipoLinhaArrayAssoc;
	private String[] operadoraArrayAssoc;
	private AdmTabelaDominioCopiaVO admCopiaVO;
	private String[] tipoLinhaArray;
	private String[] operadoraArray;
	private AdmDominioValorIncluiVO dominioValorIncluiVO;
	private AdmDominiosVO dominiosVO;
	private AdmDominioVO dominioVO;
	private AdmDominioComboVO valorDominioCombo;
	private String arrayValorDominioLength = ConstantesCRM.SVAZIO;

	public void setArrayValorDominioLength(String arrayValorDominioLength) {
		this.arrayValorDominioLength = arrayValorDominioLength;
	}

	public String getArrayValorDominioLength() {
		return this.arrayValorDominioLength;
	}

	public void setValorDominioCombo(AdmDominioComboVO valorDominioCombo) {
		this.valorDominioCombo = valorDominioCombo;
	}

	public AdmDominioComboVO getValorDominioCombo() {
		if (this.valorDominioCombo == null) {
			this.valorDominioCombo = AdmDominioComboVO.Factory.newInstance();
		}

		return this.valorDominioCombo;
	}

	public void setDominioVO(AdmDominioVO dominioVO) {
		this.dominioVO = dominioVO;
	}

	public AdmDominioVO getDominioVO() {
		if (this.dominioVO == null) {
			this.dominioVO = AdmDominioVO.Factory.newInstance();
		}

		if (this.dominioVO.getAdmTabelaDominioVO() == null) {
			this.dominioVO.setAdmTabelaDominioVO(AdmTabelaDominioVO.Factory.newInstance());
		}

		if (this.dominioVO.getAdmTipoLinhaSimplVO() == null) {
			this.dominioVO.setAdmTipoLinhaSimplVO(AdmTipoLinhaSimplVO.Factory.newInstance());
		}

		if (this.dominioVO.getAdmUFOperadoraSimplVO() == null) {
			this.dominioVO.setAdmUFOperadoraSimplVO(AdmUFOperadoraSimplVO.Factory.newInstance());
		}

		return this.dominioVO;
	}

	public void setDominiosVO(AdmDominiosVO dominiosVO) {
		this.dominiosVO = dominiosVO;
	}

	public AdmDominiosVO getDominiosVO() {
		if (this.dominiosVO == null) {
			this.dominiosVO = AdmDominiosVO.Factory.newInstance();
		}

		return this.dominiosVO;
	}

	public void setDominioValorIncluiVO(AdmDominioValorIncluiVO dominioValorIncluiVO) {
		this.dominioValorIncluiVO = dominioValorIncluiVO;
	}

	public AdmDominioValorIncluiVO getDominioValorIncluiVO() {
		if (this.dominioValorIncluiVO == null) {
			this.dominioValorIncluiVO = AdmDominioValorIncluiVO.Factory.newInstance();
		}

		if (this.dominioValorIncluiVO.getOperadoraArray1Array() == null) {
			this.dominioValorIncluiVO.setOperadoraArray1Array(new String[0]);
		}

		if (this.dominioValorIncluiVO.getTipoLinhaArray1Array() == null) {
			this.dominioValorIncluiVO.setTipoLinhaArray1Array(new String[0]);
		}

		if (this.dominioValorIncluiVO.getNmDominio() == null) {
			this.dominioValorIncluiVO.setNmDominio(ConstantesCRM.SVAZIO);
		}

		if (this.dominioValorIncluiVO.getInDisponibilidade() == null) {
			this.dominioValorIncluiVO.setInDisponibilidade(ConstantesCRM.SVAZIO);
		}

		if (this.dominioValorIncluiVO.getIdDominio() == null) {
			this.dominioValorIncluiVO.setIdDominio(ConstantesCRM.SVAZIO);
		}

		if (this.dominioValorIncluiVO.getIdTabelaDominio() == null) {
			this.dominioValorIncluiVO.setIdTabelaDominio(ConstantesCRM.SVAZIO);
		}

		return this.dominioValorIncluiVO;
	}

	public void setOperadoraArray(String[] operadoraArray) {
		this.operadoraArray = operadoraArray;
	}

	public String[] getOperadoraArray() {
		if (this.operadoraArray == null) {
			this.operadoraArray = new String[0];
		}

		return this.operadoraArray;
	}

	public void setTipoLinhaArray(String[] tipoLinhaArray) {
		this.tipoLinhaArray = tipoLinhaArray;
	}

	public String[] getTipoLinhaArray() {
		if (this.tipoLinhaArray == null) {
			this.tipoLinhaArray = new String[0];
		}

		return this.tipoLinhaArray;
	}

	public void setAdmCopiaVO(AdmTabelaDominioCopiaVO admCopiaVO) {
		this.admCopiaVO = admCopiaVO;
	}

	public AdmTabelaDominioCopiaVO getAdmCopiaVO() {
		if (this.admCopiaVO == null) {
			this.admCopiaVO = AdmTabelaDominioCopiaVO.Factory.newInstance();
			this.admCopiaVO.addNewAdmDominioVO();
			this.admCopiaVO.addNewAdmTabelaComDominioVO();
			this.admCopiaVO.addNewAdmTabelaSemDominioVO();
			this.admCopiaVO.addNewDadosAtuais();
		}

		return this.admCopiaVO;
	}

	public void setOperadoraArrayAssoc(String[] operadoraArrayAssoc) {
		this.operadoraArrayAssoc = operadoraArrayAssoc;
	}

	public String[] getOperadoraArrayAssoc() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.operadoraArrayAssoc == null) {
			this.operadoraArrayAssoc = new String[0];
		}

		return this.operadoraArrayAssoc;
	}

	public void setTipoLinhaArrayAssoc(String[] tipoLinhaArrayAssoc) {
		this.tipoLinhaArrayAssoc = tipoLinhaArrayAssoc;
	}

	public String[] getTipoLinhaArrayAssoc() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null.
		if (this.tipoLinhaArrayAssoc == null) {
			this.tipoLinhaArrayAssoc = new String[0];
		}

		return this.tipoLinhaArrayAssoc;
	}
}