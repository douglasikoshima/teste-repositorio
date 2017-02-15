package com.indracompany.catalogo.ejb.importacaoservicofixa;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.OfertaVLArqItemTO;
import com.indracompany.catalogo.to.OfertaVLFArqItemTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;
import com.indracompany.catalogo.to.ServicoArqItemTO;
import com.indracompany.catalogo.to.ServicoServicoArqItemTO;
import com.indracompany.catalogo.to.SolComGrupoComArqItemTO;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;



/**
 * @author equipe Catalogo
 */
public interface ImportacaoServicoFixaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ImportacaoServicoFixaBean";
	
	/**
	 * @param 
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em importar o arquivo de ServicoServicoArq.
	 */
	public Boolean importarServicoServicoArq(String usuario, String nomeArquivo) throws BusinessException;
	
	/**
	 * @param 
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em importar o arquivo de SolComGrupoComArq.
	 */
	public Boolean importarSolComGrupoComArq(String usuario, String nomeArquivo) throws BusinessException;
	
	/**
	 * @param 
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em importar o arquivo de SCEncargoGruComArq.
	 */
	public Boolean importarSCEncargoGruComArq(String usuario, String nomeArquivo) throws BusinessException;
	
	/**
	 * @param 
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em importar o arquivo de SCEncargoGCPMACArq.
	 */
	public Boolean importarSCEncargoGCPMACArq(String usuario, String nomeArquivo) throws BusinessException;
	
    public Boolean importarServicoArq(String usuario, String nomeArquivo) throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Metodo responsavel em pesquisar os arquivos importados de OFERTA VENDA LINHA.
	 */
    
    public Boolean importarOfertaVendaLinha(String usuario, String nomeArquivo) throws BusinessException;    
    
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Metodo responsavel em pesquisar os arquivos importados de OFERTA COMPLEMENTAR.
	 */
    
    public Boolean importarOfertaComplementar(String usuario, String nomeArquivo) throws BusinessException;      
    
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os arquivos importados de ServicoServicoArq.
	 */
	public List<ImportacaoServicoFixaTO> pesquisarServicoServicoArq(ImportacaoServicoFixaTO to) throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os arquivos importados de SolComGrupoComArq.
	 */
	public List<ImportacaoServicoFixaTO> pesquisarSolComGrupoComArq(ImportacaoServicoFixaTO to) throws BusinessException;

	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os arquivos importados de SCEncargoGruComArq.
	 */
	public List<ImportacaoServicoFixaTO> pesquisarSCEncargoGruComArq(ImportacaoServicoFixaTO to) throws BusinessException;

	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os arquivos importados de SCEncargoGCPMACArq.
	 */
	public List<ImportacaoServicoFixaTO> pesquisarSCEncargoGCPMACArq(ImportacaoServicoFixaTO to) throws BusinessException;

	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em carregar o combo dos status do arquivo.
	 */
	public List<StatusArquivoImportacaoTO> carregarComboStatusArq() throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os itens criticos de ServicoServicoArq.
	 */
	public List<ServicoServicoArqItemTO> pesquisarCriticaServicoServicoArq(Integer idArquivo) throws BusinessException;

	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os itens criticos de SolComGrupoComArq.
	 */
	public List<SolComGrupoComArqItemTO> pesquisarCriticaSolComGrupoComArq(Integer idArquivo) throws BusinessException;
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os itens criticos de SCEncargoGruComArq.
	 */
	
	public List<SCEncargoGruComArqItemTO> pesquisarCriticaSCEncargoGruComArq(Integer idArquivo) throws BusinessException;
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar os itens criticos de SCEncargoGCPMACArq.
	 */
	public List<SCEncargoGruComArqItemTO> pesquisarCriticaSCEncargoGCPMACArq(Integer idArquivo) throws BusinessException;

    List<ImportacaoServicoFixaTO> pesquisarServicoArq(ImportacaoServicoFixaTO to) throws BusinessException;

    List<ServicoArqItemTO> pesquisarCriticaServicoArq(Integer idArquivo) throws BusinessException;
    
    public List<ImportacaoServicoFixaTO> pesquisarOfertaVLArq(ImportacaoServicoFixaTO to, String tpImportacao) throws BusinessException;   
    
    public List<OfertaVLArqItemTO> pesquisarCriticaOfertaVLArq(Integer idArquivo) throws BusinessException;
    
    public List<OfertaVLFArqItemTO> pesquisarCriticaOfertaVLFArq(Integer idArquivo) throws BusinessException;
    
}
