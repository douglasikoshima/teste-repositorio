package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.SistemaLegadoVO;

public class SistemaLegadoFormBean extends TradutorFormBean{
	private static final long serialVersionUID = 1L;

	private SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO();

	public SistemaLegadoVO getSistemaLegadoVO() {
		return sistemaLegadoVO;
	}

	public void setSistemaLegadoVO(SistemaLegadoVO sistemaLegadoVO) {
		this.sistemaLegadoVO = sistemaLegadoVO;
	}

	
}