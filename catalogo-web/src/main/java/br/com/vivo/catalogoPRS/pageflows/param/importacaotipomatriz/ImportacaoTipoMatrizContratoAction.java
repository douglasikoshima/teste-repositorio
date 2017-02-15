package br.com.vivo.catalogoPRS.pageflows.param.importacaotipomatriz;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.delegate.ContratoMatrizOfertaCriticaDelegate;
import br.com.vivo.catalogoPRS.delegate.ContratoMatrizOfertaLockDelegate;
import br.com.vivo.catalogoPRS.delegate.ImportacaoTipoMatrizContratoDelegate;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ImportacaoTipoMatrizContratoForm;
import br.com.vivo.catalogoPRS.util.PropertiesUtil;

import com.indracompany.catalogo.ejb.servicointeratividade.ServicoInteratividadeBean;
import com.indracompany.catalogo.to.ContratoMatrizOfertaLockTO;

/**
 * @author Luiz Pereira
 *
 */
public class ImportacaoTipoMatrizContratoAction  extends BaseMappingDispatchAction implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ServicoInteratividadeBean.class);
	
	private final static String CABECALHO_EXPORT = "CODIGOPLANO;CODIGOSERVICO;SIGLACSA;VALORCONTRATO";
	private final static String CABECALHO_CRITICA_EXPORT = "CODIGOPLANO;CODIGOSERVICO;SIGLACSA;VALORCONTRATO;CRITICA";
	private String SUCCESS = "success";
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		verificarEstadoArquivoCorrente(request);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward liberarExport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		ImportacaoTipoMatrizContratoForm importacaoTipoMatrizContratoForm = (ImportacaoTipoMatrizContratoForm)form;
		
		if (importacaoTipoMatrizContratoForm.getTrabalho() != null && importacaoTipoMatrizContratoForm.getTrabalho().equalsIgnoreCase("S")) {
			request.setAttribute("liberar", Boolean.TRUE);
		}
		
		request.setAttribute("exportar", Boolean.TRUE);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		ImportacaoTipoMatrizContratoForm importacaoTipoMatrizContratoForm = (ImportacaoTipoMatrizContratoForm)form;
		ImportacaoTipoMatrizContratoDelegate importacaoTipoMatrizContratoDelegate = new ImportacaoTipoMatrizContratoDelegate();
		
		
		try {
			
			List<String> list = importacaoTipoMatrizContratoDelegate.exportContratoMatrizOferta(importacaoTipoMatrizContratoForm.getTrabalho().equals("S"));
			logger.info("EXPORTANDO ARQUIVO DE TABELA ESPELHO");
			
			String nameFile = createNameFile(importacaoTipoMatrizContratoForm);
			exportFile(list, CABECALHO_EXPORT, "application/vnd.ms-excel", nameFile, response);
            
			if (importacaoTipoMatrizContratoForm.getTrabalho() != null && importacaoTipoMatrizContratoForm.getTrabalho().equals("S")) {
				// CRIAR LOCK NO ARQUIVO SE FOR PARA TRABALHO
				ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = new ContratoMatrizOfertaLockTO();
				contratoMatrizOfertaLockTO.setDtInclusao(new Date());
				contratoMatrizOfertaLockTO.setInStatus("S");
				contratoMatrizOfertaLockTO.setNmArquivo(nameFile);
				contratoMatrizOfertaLockTO.setNmUsuarioCriacao(this.getUserLogged(request).getUsername());
				ContratoMatrizOfertaLockDelegate contratoMatrizOfertaLockDelegate = new ContratoMatrizOfertaLockDelegate();
				contratoMatrizOfertaLockDelegate.createUpdateContratoMatrizOfertaLock(contratoMatrizOfertaLockTO);
				request.setAttribute("liberar", Boolean.TRUE);
				logger.info("ARQUIVO DE TRABALHO EXPORTADO COM ÊXITO: " + contratoMatrizOfertaLockTO);
				
				// LIMPAR TABELAS
				importacaoTipoMatrizContratoDelegate.removerDadosAntigos();
			}
		
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
			verificarEstadoArquivoCorrente(request);
		}
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward importar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		ImportacaoTipoMatrizContratoForm importacaoTipoMatrizContratoForm = (ImportacaoTipoMatrizContratoForm)form;
		
		ImportacaoTipoMatrizContratoDelegate importacaoTipoMatrizContratoDelegate = new ImportacaoTipoMatrizContratoDelegate();
		
		try {
			if (validarArquivoTecnicamente(importacaoTipoMatrizContratoForm.getFileImport(), request)) {
				logger.info("IMPORTANDO ARQUIVO DE TIPO MATRIZ OFERTA E CONTRATO");
				importacaoTipoMatrizContratoDelegate.importContratoMatrizOferta(
					importacaoTipoMatrizContratoForm.getFileImport().getInputStream(), this.getUserLogged(request).getUsername());
				
				resetForm(importacaoTipoMatrizContratoForm);
				request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
				//logger.info("ARQUIVO IMPORTADO COM ÊXITO: " + importacaoTipoMatrizContratoForm.getFileImport().getFileName());
			}
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		
		verificarEstadoArquivoCorrente(request);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward liberar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		
		// FUNCIONALIDADE DE LIBERAR UM ARQUIVO BLOQUEADO.
		ContratoMatrizOfertaLockDelegate contratoMatrizOfertaLockDelegate = new ContratoMatrizOfertaLockDelegate();
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = new ContratoMatrizOfertaLockTO();
		contratoMatrizOfertaLockTO.setDtAlteracao(new Date());
		contratoMatrizOfertaLockTO.setNmUsuarioAlteracao(this.getUserLogged(request).getUsername());
		contratoMatrizOfertaLockDelegate.updateStatusLiberar(contratoMatrizOfertaLockTO);
		
		verificarEstadoArquivoCorrente(request);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	public ActionForward validar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ImportacaoTipoMatrizContratoDelegate importacaoTipoMatrizContratoDelegate = new ImportacaoTipoMatrizContratoDelegate();
		try {
			importacaoTipoMatrizContratoDelegate.validarDados();
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		verificarEstadoArquivoCorrente(request);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward liberarProducao(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ImportacaoTipoMatrizContratoDelegate importacaoTipoMatrizContratoDelegate = new ImportacaoTipoMatrizContratoDelegate();
		try {
			importacaoTipoMatrizContratoDelegate.liberarProducao();
		} catch (BusinessException e) {
			request.setAttribute("labelError", e.getMessage());
		}
		
		verificarEstadoArquivoCorrente(request);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	public ActionForward exportarCriticas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  {
		
		ContratoMatrizOfertaLockDelegate contratoMatrizOfertaLockDelegate = new ContratoMatrizOfertaLockDelegate();
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = contratoMatrizOfertaLockDelegate.findFileLockedCurrent();
		
		ContratoMatrizOfertaCriticaDelegate contratoMatrizOfertaCriticaDelegate = new ContratoMatrizOfertaCriticaDelegate();
		List<String> list = contratoMatrizOfertaCriticaDelegate.exportContratoMatrizOfertaCritica();
		
		logger.info("EXPORTANDO ARQUIVO COM CRITICAS");
		logger.info("EXPORTANDO ARQUIVO: " + contratoMatrizOfertaLockTO.getNmArquivo());
		exportFile(list, CABECALHO_CRITICA_EXPORT, "application/vnd.ms-excel", contratoMatrizOfertaLockTO.getNmArquivo(), response);
		
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}
	
	private Boolean validarArquivoTecnicamente(FormFile formFile, HttpServletRequest request) throws BusinessException, FileNotFoundException, IOException {
		
		
		
		// VERIFICA SE É UM ARQUIVO CSV.
		if (!formFile.getFileName().endsWith(".csv")) {
			throw new BusinessException("O arquivo a ser processado não é CSV.");
		}
		
		// VERIFICA SE O ARQUIVO NÃO ESTÁ EM BRANCO
		if (formFile.getFileSize() == 0) {
			throw new BusinessException("Arquivo sem dados para importação. Favor verificar e fazer nova importação");
		}
		
		ContratoMatrizOfertaLockDelegate contratoMatrizOfertaLockDelegate = new ContratoMatrizOfertaLockDelegate();
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockTO = contratoMatrizOfertaLockDelegate.findFileLockedCurrent();
		
		if (contratoMatrizOfertaLockTO != null) {
			
			// VERIFICA SE O ARQUIVO IMPORTADO É O MESMO QUE FOI EXPORTADO
			if (!formFile.getFileName().endsWith(contratoMatrizOfertaLockTO.getNmArquivo())) {
				throw new BusinessException("O arquivo selecionado não tem o mesmo nome do arquivo exportado.");
			}
			
			// VERIFICA SE O USUÁRIO QUE ESTÁ TENTANDO IMPORTAR O ARQUIVO É O MESMO QUE O EXPORTOU.
			if (!contratoMatrizOfertaLockTO.getNmUsuarioCriacao().equals(this.getUserLogged(request).getUsername())) {
				throw new BusinessException("Você não tem permissão para importar esse arquivo.");
			}
		} else {
			throw new BusinessException("Antes da importação, o Arquivo deverá ser exportado como trabalho.");
		}
		
		return Boolean.TRUE;
	}
	
	private String createNameFile(ImportacaoTipoMatrizContratoForm importacaoTipoMatrizContratoForm) {

		SimpleDateFormat formato = new SimpleDateFormat("ddMMyyyyHHmmss");
		String nameFileStr = StringUtils.join(
				new String[]{
							"CONTRATO", 
							"_MATRIZ", 
							"_",
							new PropertiesUtil().getString("sigla.ambiente.corrente"), 
							importacaoTipoMatrizContratoForm.getTrabalho().equals("N") ? "_CONSULTA" : "", 
							"_",
							formato.format(new Date()).concat(".csv")
						});
		
		return nameFileStr;
	}

	private void verificarEstadoArquivoCorrente(HttpServletRequest request) {
		ContratoMatrizOfertaLockDelegate contratoMatrizOfertaLockDelegate = new ContratoMatrizOfertaLockDelegate();
		ContratoMatrizOfertaLockTO contratoMatrizOfertaLockResultTO = contratoMatrizOfertaLockDelegate.findFileLockedCurrent();

		if (contratoMatrizOfertaLockResultTO != null) {
			
			request.setAttribute("liberar", this.getUserLogged(request).getUsername().equals(contratoMatrizOfertaLockResultTO.getNmUsuarioCriacao()));
			
			if (contratoMatrizOfertaLockResultTO.getInImportacao() != null) {
				request.setAttribute("importado", (contratoMatrizOfertaLockResultTO.getInImportacao().equals("S"))&& this.getUserLogged(request).getUsername().equals(contratoMatrizOfertaLockResultTO.getNmUsuarioCriacao()));
				
				if (contratoMatrizOfertaLockResultTO.getInValidado() != null) {
					request.setAttribute("validado", (contratoMatrizOfertaLockResultTO.getInValidado().equals("S") && this.getUserLogged(request).getUsername().equals(contratoMatrizOfertaLockResultTO.getNmUsuarioCriacao())));
				}
			}
		}
		
		ContratoMatrizOfertaCriticaDelegate contratoMatrizOfertaCriticaDelegate = new ContratoMatrizOfertaCriticaDelegate();
		request.setAttribute("contratoMatrizOfertaCriticaList", contratoMatrizOfertaCriticaDelegate.findAllCriticas());
	}
	
	private void resetForm(ImportacaoTipoMatrizContratoForm importacaoTipoMatrizContratoForm) {
		importacaoTipoMatrizContratoForm.setFileImport(null);
		importacaoTipoMatrizContratoForm.setTrabalho(null);
	}
}

	