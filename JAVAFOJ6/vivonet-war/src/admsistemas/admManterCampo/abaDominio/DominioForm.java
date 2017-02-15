package admsistemas.admManterCampo.abaDominio;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominioVODocument.AdmTabelaDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTabelaDominiosVODocument.AdmTabelaDominiosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class DominioForm extends ActionForm {

	private static final long serialVersionUID = -5986890544864602113L;

	private String dominiosVOLength = ConstantesCRM.SVAZIO;
	private AdmTabelaDominiosVO dominiosVO;
	private AdmTabelaDominioVO dominioVO;

	public void setDominioVO(AdmTabelaDominioVO dominioVO) {
		this.dominioVO = dominioVO;
	}

	public AdmTabelaDominioVO getDominioVO() {
		if (this.dominioVO == null) {
			this.dominioVO = AdmTabelaDominioVO.Factory.newInstance();
		}

		return this.dominioVO;
	}

	public void setDominiosVO(AdmTabelaDominiosVO dominiosVO) {
		this.dominiosVO = dominiosVO;
	}

	public AdmTabelaDominiosVO getDominiosVO() {
		if (this.dominiosVO == null) {
			this.dominiosVO = AdmTabelaDominiosVO.Factory.newInstance();
		}

		return this.dominiosVO;
	}

	public void setDominiosVOLength(String dominiosVOLength) {
		this.dominiosVOLength = dominiosVOLength;
	}

	public String getDominiosVOLength() {
		return this.dominiosVOLength;
	}
}