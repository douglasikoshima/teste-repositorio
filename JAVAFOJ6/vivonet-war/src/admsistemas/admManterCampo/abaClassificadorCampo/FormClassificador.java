package admsistemas.admManterCampo.abaClassificadorCampo;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument.AdmClassificadorCamposVO;

public class FormClassificador extends ActionForm {

	private static final long serialVersionUID = 2904944620723335598L;

	private String arrayClassificadorLength;
	private AdmClassificadorCampoVO admClassificardorVO;
	private AdmClassificadorCamposVO admClassificadorVOArray;

	public void setAdmClassificadorVOArray(AdmClassificadorCamposVO admClassificadorVOArray) {
		this.admClassificadorVOArray = admClassificadorVOArray;
	}

	public AdmClassificadorCamposVO getAdmClassificadorVOArray() {
		if (this.admClassificadorVOArray == null) {
			this.admClassificadorVOArray = AdmClassificadorCamposVO.Factory.newInstance();
		}
		return this.admClassificadorVOArray;
	}

	public void setAdmClassificardorVO(AdmClassificadorCampoVO admClassificardorVO) {
		this.admClassificardorVO = admClassificardorVO;
	}

	public AdmClassificadorCampoVO getAdmClassificardorVO() {
		if (this.admClassificardorVO == null) {
			this.admClassificardorVO = AdmClassificadorCampoVO.Factory.newInstance();
		}
		return this.admClassificardorVO;
	}

	public void setArrayClassificadorLength(String arrayClassificadorLength) {
		this.arrayClassificadorLength = arrayClassificadorLength;
	}

	public String getArrayClassificadorLength() {
		return this.arrayClassificadorLength;
	}
}