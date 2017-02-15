package admsistemas.relatorio;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.admsistemas.vo.RelatorioBlindagemVODocument.RelatorioBlindagemVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

public class RelatorioForm extends ActionForm {

	private static final long serialVersionUID = -826184522150274390L;

	private String dtFimBlind = ConstantesCRM.SVAZIO;
	private String dtIniBlind = ConstantesCRM.SVAZIO;
	private RelatorioBlindagemVO relatorioBlindagemVO = RelatorioBlindagemVO.Factory.newInstance();

	public void setDtIniBlind(String dtIniBlind) {
		this.dtIniBlind = dtIniBlind;
	}

	public String getDtIniBlind() {
		return this.dtIniBlind;
	}

	public void setDtFimBlind(String dtFimBlind) {
		this.dtFimBlind = dtFimBlind;
	}

	public String getDtFimBlind() {
		return this.dtFimBlind;
	}

	public RelatorioBlindagemVO getRelatorioBlindagemVO() {
		return this.relatorioBlindagemVO;
	}

	public void setRelatorioBlindagemVO(RelatorioBlindagemVO relatorioBlindagemVO) {
		this.relatorioBlindagemVO = relatorioBlindagemVO;
	}
}