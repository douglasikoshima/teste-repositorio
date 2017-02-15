package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import java.util.Collection;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroComumVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroLegadoVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroPadraoVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.SistemaLegadoVO;

import com.framework.hibernate.PagedList;

/**
 * Form Bean para Servicos
 */
public class ErroCadastroFormBean extends TradutorFormBean {
	private static final long serialVersionUID = 1L;
	private ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO();
	private ServicoVO servicoVO = new ServicoVO();
	private Collection servicoList = new PagedList();
	private Collection servicoNegocioList = new PagedList();
	private SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO();
	private Collection sistemaLegadoList = new PagedList();
	private ErroPadraoVO erroPadraoVO = new ErroPadraoVO();
	private ErroLegadoVO erroLegadoVO = new ErroLegadoVO();
	private ErroComumVO erroComumVO = new ErroComumVO();
	
	

	public ErroPadraoVO getErroPadraoVO() {
		return erroPadraoVO;
	}

	public void setErroPadraoVO(ErroPadraoVO erroPadraoVO) {
		this.erroPadraoVO = erroPadraoVO;
	}

	public ServicoVO getServicoVO() {
		return servicoVO;
	}

	public void setServicoVO(ServicoVO servicoVO) {
		this.servicoVO = servicoVO;
	}

	public Collection getServicoList() {
		return servicoList;
	}

	public void setServicoList(Collection servicoList) {
		this.servicoList = servicoList;
	}

	public Collection getSistemaLegadoList() {
		return sistemaLegadoList;
	}

	public void setSistemaLegadoList(Collection sistemaLegadoList) {
		this.sistemaLegadoList = sistemaLegadoList;
	}

	public ServicoNegocioVO getServicoNegocioVO() {
		return servicoNegocioVO;
	}

	public void setServicoNegocioVO(ServicoNegocioVO servicoNegocioVO) {
		this.servicoNegocioVO = servicoNegocioVO;
	}

	public Collection getServicoNegocioList() {
		return servicoNegocioList;
	}

	public void setServicoNegocioList(Collection servicoNegocioList) {
		this.servicoNegocioList = servicoNegocioList;
	}

	public SistemaLegadoVO getSistemaLegadoVO() {
		return sistemaLegadoVO;
	}

	public void setSistemaLegadoVO(SistemaLegadoVO sistemaLegadoVO) {
		this.sistemaLegadoVO = sistemaLegadoVO;
	}

	public void setId(String id) {
		// erroLegadoVO.setId(id);
	}

	/**
	 * @return String
	 */
	public String getId() {
		// return erroLegadoVO.getId();
		return "";
	}

	/**
	 * @param String
	 */
	public void setFlagNew(boolean flag) {
		// erroLegadoVO.setFlagNew(flag);
	}

	/**
	 * @return String
	 */
	public boolean getFlagNew() {
		// return erroLegadoVO.getFlagNew();
		return true;
	}

	public ErroComumVO getErroComumVO() {
		return erroComumVO;
	}

	public void setErroComumVO(ErroComumVO erroComumVO) {
		this.erroComumVO = erroComumVO;
	}

	public ErroLegadoVO getErroLegadoVO() {
		return erroLegadoVO;
	}

	public void setErroLegadoVO(ErroLegadoVO erroLegadoVO) {
		this.erroLegadoVO = erroLegadoVO;
	}

}