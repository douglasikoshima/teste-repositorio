package workflow.AtendimentoInBox;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO;

public class RWFAtendimentoForm extends ActionForm {

	private static final long serialVersionUID = -3303480780708600971L;

	private String flLimpar;
	private String tabPausa;
	private int totalRegistros;
	private int nrRegistros;
	private int atualizacao;
	private RWFAtendimentoVO[] rwfAtendimentoVO;

	public void setRwfAtendimentoVO(RWFAtendimentoVO[] rwfAtendimentoVO) {
		this.rwfAtendimentoVO = rwfAtendimentoVO;
	}

	public RWFAtendimentoVO[] getRwfAtendimentoVO() {
		if (this.rwfAtendimentoVO == null || this.rwfAtendimentoVO.length == 0) {
			this.rwfAtendimentoVO[0] = RWFAtendimentoVO.Factory.newInstance();
		}

		return this.rwfAtendimentoVO;
	}

	public void setAtualizacao(int atualizacao) {
		this.atualizacao = atualizacao;
	}

	public int getAtualizacao() {
		return this.atualizacao;
	}

	public void setNrRegistros(int nrRegistros) {
		this.nrRegistros = nrRegistros;
	}

	public int getNrRegistros() {
		return this.nrRegistros;
	}

	public void setTotalRegistros(int totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public int getTotalRegistros() {
		return this.totalRegistros;
	}

	public void setTabPausa(String tabPausa) {
		this.tabPausa = tabPausa;
	}

	public String getTabPausa() {
		return this.tabPausa;
	}

	public void setFlLimpar(String flLimpar) {
		this.flLimpar = flLimpar;
	}

	public String getFlLimpar() {
		if (this.flLimpar == null) {
			this.flLimpar = ConstantesCRM.SZERO;
		}
		return this.flLimpar;
	}
}