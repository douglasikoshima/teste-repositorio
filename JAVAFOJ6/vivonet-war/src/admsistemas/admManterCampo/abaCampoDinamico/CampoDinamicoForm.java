package admsistemas.admManterCampo.abaCampoDinamico;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.AdmCampoCombosVODocument.AdmCampoCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoContatoVODocument.AdmCampoContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoDominioVODocument.AdmCampoDominioVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposContatoVODocument.AdmCamposContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmDominioComboVODocument.AdmDominioComboVO;
import br.com.vivo.fo.admsistemas.vo.AdmLayoutApresentacaoCampoVODocument.AdmLayoutApresentacaoCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmMascaraApresentacaoVODocument.AdmMascaraApresentacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoDadoCampoVODocument.AdmTipoDadoCampoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class CampoDinamicoForm extends ActionForm {

	private static final long serialVersionUID = -3349747288672519448L;

	private AdmCampoDominioVO campoDominioVO;
	private AdmDominioComboVO dominioComboVO;
	private AdmCampoCombosVO campoComboVO;
	private AdmCamposContatoVO camposVO;
	private String campoVOArrayLength = ConstantesCRM.SVAZIO;
	private AdmCampoContatoVO campoVO;

	public void setCampoVO(AdmCampoContatoVO campoVO) {
		this.campoVO = campoVO;
	}

	public AdmCampoContatoVO getCampoVO() {
		if (this.campoVO == null) {
			this.campoVO = AdmCampoContatoVO.Factory.newInstance();
			this.campoVO.setAdmClassificadorCampoVO(AdmClassificadorCampoVO.Factory.newInstance());
			this.campoVO.setAdmMascaraApresentacaoVO(AdmMascaraApresentacaoVO.Factory.newInstance());
			this.campoVO.setAdmLayoutApresentacaoCampoVO(AdmLayoutApresentacaoCampoVO.Factory
					.newInstance());
			this.campoVO.setAdmTipoDadoCampoVO(AdmTipoDadoCampoVO.Factory.newInstance());
		}
		return this.campoVO;
	}

	public void setCampoVOArrayLength(String campoVOArrayLength) {
		this.campoVOArrayLength = campoVOArrayLength;
	}

	public String getCampoVOArrayLength() {
		return this.campoVOArrayLength;
	}

	public void setCamposVO(AdmCamposContatoVO camposVO) {
		this.camposVO = camposVO;
	}

	public AdmCamposContatoVO getCamposVO() {
		if (this.camposVO == null) {
			this.camposVO = AdmCamposContatoVO.Factory.newInstance();
		}
		return this.camposVO;
	}

	public void setCampoComboVO(AdmCampoCombosVO campoComboVO) {
		this.campoComboVO = campoComboVO;
	}

	public AdmCampoCombosVO getCampoComboVO() {
		if (this.campoComboVO == null) {
			this.campoComboVO = AdmCampoCombosVO.Factory.newInstance();
		}
		return this.campoComboVO;
	}

	public void setDominioComboVO(AdmDominioComboVO dominioComboVO) {
		this.dominioComboVO = dominioComboVO;
	}

	public AdmDominioComboVO getDominioComboVO() {
		if (this.dominioComboVO == null) {
			this.dominioComboVO = AdmDominioComboVO.Factory.newInstance();
		}
		return this.dominioComboVO;
	}

	public void setCampoDominioVO(AdmCampoDominioVO campoDominioVO) {
		this.campoDominioVO = campoDominioVO;
	}

	public AdmCampoDominioVO getCampoDominioVO() {
		if (this.campoDominioVO == null) {
			this.campoDominioVO = AdmCampoDominioVO.Factory.newInstance();
		}
		return this.campoDominioVO;
	}
}