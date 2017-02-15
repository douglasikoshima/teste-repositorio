package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroLegadoVO;

/**
 * Form Bean para ErroLegados
 */
public class ErroLegadoFormBean extends TradutorFormBean
{
	private static final long serialVersionUID = 1L;
	/**	
	 * VO de erroLegado
	 */
	private ErroLegadoVO erroLegadoVO = new ErroLegadoVO();


	/**
	 * @param String
	 */
	public void setId(String id)
	{
		erroLegadoVO.setId(id);
	}

	/**
	 * @return String
	 */
	public String getId()
	{
		return erroLegadoVO.getId();
	}

	/**
	 * @param String
	 */
	public void setFlagNew(boolean flag)
	{
		erroLegadoVO.setFlagNew(flag);
	}

	/**
	 * @return String
	 */
	public boolean getFlagNew()
	{
		return erroLegadoVO.getFlagNew();
	}

	/**
	 * Gets the erroLegadoVO
	 * 
	 * @return Returns a ErroLegadoVO
	 */
	public ErroLegadoVO getErroLegadoVO()
	{
		return erroLegadoVO;
	}

	/**
	 * Sets the erroLegadoVO
	 * 
	 * @param erroLegadoVO
	 *          The erroLegadoVO to set
	 */
	public void setErroLegadoVO(ErroLegadoVO erroLegadoVO)
	{
		this.erroLegadoVO = erroLegadoVO;
	}

}