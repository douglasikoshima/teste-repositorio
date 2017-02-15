package workflow.NotasURA;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotaHistVODocument.WFAtdNotaHistVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.workflow.vo.WFAtdTipoNotaVODocument.WFAtdTipoNotaVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

public class NotasForm extends ActionForm {

	private String comentarioNovo;

	private WFAtdTipoNotaVO[] tipoNotasVO;

	private String acaoLabel;

	private String dsObservacao;

	private String motivoSel;

	private String motivoAcao;

	private WFMotivoVO[] motivoVO;

	private String idAtendimentoNota;

	private String acaoSel;
	private WFAtdNotaVO notaVO;
	private WFAtdNotasVO notasVO;
	private WFAtdNotaHistVO[] notaHistVO;
	private WFAcaoVO[] acaoVO;
	private WFAcaoRetornoVO acaoRetornoVO;

	private int idNota;

	public void setNotaVO(WFAtdNotaVO notaVO) {
		this.notaVO = notaVO;
	}

	public WFAtdNotaHistVO[] getNotaHistVO() {
		return this.notaHistVO;
	}

	public WFAcaoVO[] getAcaoVO() {
		return this.acaoVO;
	}

	public void setAcaoVO(WFAcaoVO[] acaoVO) {
		this.acaoVO = acaoVO;
	}

	public WFAcaoRetornoVO getAcaoRetornoVO() {
		return this.acaoRetornoVO;
	}

	public void setAcaoRetornoVO(WFAcaoRetornoVO acaoRetornoVO) {
		this.acaoRetornoVO = acaoRetornoVO;
	}

	public void setNotaHistVO(WFAtdNotaHistVO[] notaHistVO) {
		this.notaHistVO = notaHistVO;
	}

	public WFAtdNotaVO getNotaVO() {
		if (this.notaVO == null) {
			this.notaVO = WFAtdNotaVO.Factory.newInstance();
		}
		return this.notaVO;
	}

	public void setNotasVO(WFAtdNotasVO notasVO) {
		this.notasVO = notasVO;
	}

	public WFAtdNotasVO getNotasVO() {
		if (this.notasVO == null) {
			this.notasVO = WFAtdNotasVO.Factory.newInstance();
		}
		return this.notasVO;
	}

	public void setIdNota(int idNota) {
		this.idNota = idNota;
	}

	public int getIdNota() {
		return this.idNota;
	}

	public void setAcaoSel(String acaoSel) {
		this.acaoSel = acaoSel;
	}

	public String getAcaoSel() {
		return this.acaoSel;
	}

	public void setIdAtendimentoNota(String idAtendimentoNota) {
		this.idAtendimentoNota = idAtendimentoNota;
	}

	public String getIdAtendimentoNota() {
		return this.idAtendimentoNota;
	}

	public void setMotivoVO(WFMotivoVO[] motivoVO) {
		this.motivoVO = motivoVO;
	}

	public WFMotivoVO[] getMotivoVO() {
		// For data binding to be able to post data back, complex types and
		// arrays must be initialized to be non-null. This type doesn't have
		// a default constructor, so Workshop cannot initialize it for you.

		// TODO: Initialize motivoVO if it is null or length == 0.
		// if(this.motivoVO == null || this.motivoVO.length == 0)
		// {
		// this.motivoVO = new WFMotivoVO[1];
		// this.motivoVO[0] = new WFMotivoVO(?);
		// }

		return this.motivoVO;
	}

	public void setMotivoSel(String motivoSel) {
		this.motivoSel = motivoSel;
	}

	public String getMotivoSel() {
		return this.motivoSel;
	}

	public void setDsObservacao(String dsObservacao) {
		this.dsObservacao = dsObservacao;
	}

	public String getDsObservacao() {
		return this.dsObservacao;
	}

	public void setAcaoLabel(String acaoLabel) {
		this.acaoLabel = acaoLabel;
	}

	public String getAcaoLabel() {
		return this.acaoLabel;
	}

	public void setTipoNotasVO(WFAtdTipoNotaVO[] tipoNotasVO) {
		this.tipoNotasVO = tipoNotasVO;
	}

	public WFAtdTipoNotaVO[] getTipoNotasVO() {
		return this.tipoNotasVO;
	}

	public void setComentarioNovo(String comentarioNovo) {
		this.comentarioNovo = comentarioNovo;
	}

	public String getComentarioNovo() {
		return this.comentarioNovo;
	}
}