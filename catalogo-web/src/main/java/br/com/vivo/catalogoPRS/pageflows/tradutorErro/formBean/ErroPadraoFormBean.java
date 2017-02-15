package br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroPadraoVO;

/**
 * Form Bean para ErroPadraos
 */
public class ErroPadraoFormBean extends TradutorFormBean
{
	private static final long serialVersionUID = 1L;
	/**	
	 * VO de erroPadrao
	 */
	private ErroPadraoVO erroPadraoVO = new ErroPadraoVO();


	/**
	 * @param String
	 */
	public void setId(String id)
	{
		erroPadraoVO.setId(id);
	}

	/**
	 * @return String
	 */
	public String getId()
	{
		return erroPadraoVO.getId();
	}

	/**
	 * @param String
	 */
	public void setFlagNew(boolean flag)
	{
		erroPadraoVO.setFlagNew(flag);
	}

	/**
	 * @return String
	 */
	public boolean getFlagNew()
	{
		return erroPadraoVO.getFlagNew();
	}

	/**
	 * Gets the erroPadraoVO
	 * 
	 * @return Returns a ErroPadraoVO
	 */
	public ErroPadraoVO getErroPadraoVO()
	{
		return erroPadraoVO;
	}

	/**
	 * Sets the erroPadraoVO
	 * 
	 * @param erroPadraoVO
	 *          The erroPadraoVO to set
	 */
	public void setErroPadraoVO(ErroPadraoVO erroPadraoVO)
	{
		this.erroPadraoVO = erroPadraoVO;
	}

}