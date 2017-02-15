package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import java.io.Serializable;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;

/**
 * Form Bean para ServicoNegocios
 */
public class ServicoNegocioFormBean extends TradutorFormBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO();
	
	public ServicoNegocioVO getServicoNegocioVO() {
		return servicoNegocioVO;
	}
	public void setServicoNegocioVO(ServicoNegocioVO servicoNegocioVO) {
		this.servicoNegocioVO = servicoNegocioVO;
	}
	
		
}