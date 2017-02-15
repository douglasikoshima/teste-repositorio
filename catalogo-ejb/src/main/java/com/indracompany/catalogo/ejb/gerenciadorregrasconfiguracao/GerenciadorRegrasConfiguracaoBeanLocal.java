package com.indracompany.catalogo.ejb.gerenciadorregrasconfiguracao;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO;
import com.indracompany.catalogo.to.IndicadorComercialDocumentoTO;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;
import com.indracompany.catalogo.to.TipoDocumentoTO;

public interface GerenciadorRegrasConfiguracaoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/GerenciadorRegrasConfiguracaoBean";

	public List<String> buscarNmIndicadorComercialPorTipoFiltro(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO) throws BusinessException;
	
	public List<RegraPrioridadeAltaTO> buscarRegraPrioridadeAltaTO() throws BusinessException;
	
	public List<IndicadorComercialDocumentoTO> buscarTodosIndicadoresDocumento() throws BusinessException;

	public List<TipoDocumentoTO> buscarTodosTiposDocumento() throws BusinessException;
	
	public IndicadorComercialDocumentoTO buscarTipoDocumentoPorIndicador(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO) throws BusinessException;
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisar(GerenciadorRegrasConfiguracaoTO gerenciadorRegraConfiguracaoTO) throws BusinessException;
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisarPorIdCanalAtendimento(GerenciadorRegrasConfiguracaoTO gerenciadorRegraConfiguracaoTO) throws BusinessException;
	
	public void salvar(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO) throws BusinessException;
	
	public void salvar(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO) throws BusinessException;
}
