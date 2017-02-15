package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import java.util.Collection;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoVO;

import com.framework.util.PagedList;

/**
 * Form Bean para Servicos
 */
public class ServicoFormBean extends TradutorFormBean {
	private static final long serialVersionUID = 1L;
	/**
	 * VO de servico
	 */
	private ServicoVO servicoVO = new ServicoVO();
	private Collection<?> sistemaLegadoList = new PagedList();
	
	private Long pagina_solicitada;
	
	private Collection<?> collectionPesquisarEN;
	

	public Collection<?> getSistemaLegadoList() {
		return sistemaLegadoList;
	}

	public void setSistemaLegadoList(Collection<?> sistemaLegadoList) {
		this.sistemaLegadoList = sistemaLegadoList;
	}

	/**
	 * @param String
	 */
	public void setId(String id) {
		servicoVO.setId(id);
	}

	/**
	 * @return String
	 */
	public String getId() {
		return servicoVO.getId();
	}

	/**
	 * @param String
	 */
	public void setFlagNew(boolean flag) {
		servicoVO.setFlagNew(flag);
	}

	/**
	 * @return String
	 */
	public boolean getFlagNew() {
		return servicoVO.getFlagNew();
	}

	/**
	 * Gets the servicoVO
	 * 
	 * @return Returns a ServicoVO
	 */
	public ServicoVO getServicoVO() {
		return servicoVO;
	}

	/**
	 * Sets the servicoVO
	 * 
	 * @param servicoVO
	 *            The servicoVO to set
	 */
	public void setServicoVO(ServicoVO servicoVO) {
		this.servicoVO = servicoVO;
	}

	public Long getPagina_solicitada() {
		return pagina_solicitada;
	}

	public void setPagina_solicitada(Long pagina_solicitada) {
		this.pagina_solicitada = pagina_solicitada;
	}

	public Collection<?> getCollectionPesquisarEN() {
		return collectionPesquisarEN;
	}

	public void setCollectionPesquisarEN(Collection<?> collectionPesquisarEN) {
		this.collectionPesquisarEN = collectionPesquisarEN;
	}

	
	
}