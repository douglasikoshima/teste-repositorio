package com.indracompany.catalogo.ejb.formapagamentobandeira;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FormaPagamentoBandeiraTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Forma Pagamento Bandeira com o EJB.  
 */
@Local
public interface FormaPagamentoBandeiraBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/FormaPagamentoBandeiraBean";
	
	/**
	 * @param formaPagamentoBandeiraTOList
	 * @throws BusinessException
	 */
	public void createUpdateFormaPagamentoBandeira(List<FormaPagamentoBandeiraTO> formaPagamentoBandeiraTOList) throws BusinessException;
	
}
