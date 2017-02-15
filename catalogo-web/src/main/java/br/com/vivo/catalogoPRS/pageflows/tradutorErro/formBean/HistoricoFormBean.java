package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

public class HistoricoFormBean extends TradutorFormBean {

	private static final long serialVersionUID = 1L;

	private HistoricoVO historicoVO = new HistoricoVO();

	public HistoricoVO getHistoricoVO() {
		return historicoVO;
	}

	public void setHistoricoVO(HistoricoVO historicoVO) {
		this.historicoVO = historicoVO;
	}
	
}

