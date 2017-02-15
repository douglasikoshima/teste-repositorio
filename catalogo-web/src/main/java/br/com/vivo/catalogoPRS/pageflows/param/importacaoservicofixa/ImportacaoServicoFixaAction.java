package br.com.vivo.catalogoPRS.pageflows.param.importacaoservicofixa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.controller.ImportacaoServicoFixaConstants;
import br.com.vivo.catalogoPRS.delegate.ImportacaoServicoFixaDelegate;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ImportacaoServicoFixaForm;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;


public class ImportacaoServicoFixaAction extends BaseMappingDispatchAction {

    private static final String SUCCESS = "success";
    
    private static Logger logger = Logger.getLogger(ImportacaoServicoFixaAction.class);
	
	
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}

	public ActionForward importar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {			
			ImportacaoServicoFixaForm importacaoServicoFixaForm = (ImportacaoServicoFixaForm)form;
            this.validarArquivoTecnicamente(importacaoServicoFixaForm.getFileImport());

            HttpSession session = (HttpSession) request.getSession();
            UserCatalogo usuario = (UserCatalogo) session.getAttribute("logged_user");
            
			String nomeArquivo = "";
			if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.RELACIONAMENTO_SERVICO) {
				logger.info("IMPORTANDO ARQUIVO DE RELACIONAMENTO DE SERVICO");
                new ImportacaoServicoFixaDelegate().importarServicoServicoArq(usuario.getUser().getName(), this.generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoRelacionamento()));
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
			}
			else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_GC_PM_AC){
				logger.info("IMPORTANDO ARQUIVO DE SC x GC x PM x AC");
				nomeArquivo = generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoSCGCPMAC());
                new ImportacaoServicoFixaDelegate().importarSolComGrupoComArq(usuario.getUser().getName(), nomeArquivo);
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
			}
  			else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC){
				logger.info("IMPORTANDO ARQUIVO DE SC x ENC x GC");
				nomeArquivo = generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoSCENCGC());
                new ImportacaoServicoFixaDelegate().importarSCEncargoGruComArq(usuario.getUser().getName(), nomeArquivo);
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
  			}
            else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC_PM_AC){
				logger.info("IMPORTANDO ARQUIVO DE SC x ENC x GC x PM x AC");
				nomeArquivo = generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoSCENCGCPMAC());
                new ImportacaoServicoFixaDelegate().importarSCEncargoGCPMACArq(usuario.getUser().getName(), nomeArquivo);
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
                
			} else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SERVICO) {
                logger.info("IMPORTANDO ARQUIVO DE SERVICO");
                nomeArquivo = generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoServico());
                new ImportacaoServicoFixaDelegate().importarServicoArq(usuario.getUser().getName(), nomeArquivo);
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
                                
                // importa��o arquivo Oferta Venda Linha
            } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_VENDA_LINHA) {
                logger.info("IMPORTANDO ARQUIVO DE OFERTA VENDA LINHA");
                nomeArquivo = generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoOferta());
                new ImportacaoServicoFixaDelegate().importarServicoOfertaVendaLinha(usuario.getUser().getName(), nomeArquivo);
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
                
                // importa��o arquivo Oferta Venda Linha
            } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_COMPLEMENTAR) {
                logger.info("IMPORTANDO ARQUIVO DE OFERTA VENDA COMPLEMENTAR");
                nomeArquivo = generateFile(importacaoServicoFixaForm, ApplicationConfiguration.getCaminhoImportacaoOfertaComplementar());
                new ImportacaoServicoFixaDelegate().importarServicoOfertaComplementar(usuario.getUser().getName(), nomeArquivo);
                request.setAttribute("labelSucess", "Arquivo importado com sucesso!");
            } else {
            	request.setAttribute("erro", "Tipo de importa&ccedil;&atilde;o n&atilde;o suportada.");
            }
            importacaoServicoFixaForm.clean();
		} catch (BusinessException e) {
			request.setAttribute("erro", e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return mapping.findForward(SUCCESS);
	}

    public ActionForward obterModelo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	ImportacaoServicoFixaForm importacaoServicoFixaForm = (ImportacaoServicoFixaForm)form;
    	
        if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.RELACIONAMENTO_SERVICO) {
            this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_RELACIONAMENTO_EXPORT, "RELACIONAMENTO_SERVICO", response);
        } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_GC_PM_AC) {
            this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_SC_GC_PM_AC_EXPORT, "SC_GC_PM_AC", response);
        
        } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC) {
            this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_SC_ENC_GC_EXPORT, "SC_ENC_PF_GC", response);
        
        } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SC_ENC_GC_PM_AC) {
            this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_SC_ENC_GC_PM_AC_EXPORT, "SC_ENC_PF_GC_PM_AC", response);
        } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.SERVICO) {
            this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_SERVICO_EXPORT, "SERVICO", response);
        } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_VENDA_LINHA) {
        	this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_SERVICO_EXPORT_OFERTA_VENDA_LINHA, ImportacaoServicoFixaConstants.NOME_ARQUIVO_OFERTA_VENDA_LINHA, response);
        } else if (importacaoServicoFixaForm.getIdTipoImportacao() == ImportacaoServicoFixaConstants.OFERTA_COMPLEMENTAR) {        	
        	this.gerarModelo(ImportacaoServicoFixaConstants.CABECALHO_SERVICO_EXPORT_OFERTA_COMPLEMENTAR, ImportacaoServicoFixaConstants.NOME_ARQUIVO_OFERTA_COMPLEMENTAR, response);
        } else {
        	request.setAttribute("labelError", "Favor selecionar tipo de importa&ccedil;&atilde;o");
        }
        return mapping.findForward(SUCCESS);
    }
    
    private void gerarModelo(String cabecalho, String nomeArquivo, HttpServletResponse response){
    	response.setContentType("application/vnd.ms-excel");
    	response.setHeader("Content-Disposition", String.format("attachment; filename=%s.csv", nomeArquivo));
    	response.setContentLength(cabecalho.getBytes().length);
        try {
            OutputStream out = response.getOutputStream();
            out.write(cabecalho.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	private void validarArquivoTecnicamente(FormFile formFile) throws BusinessException{
		
		// VERIFICA SE EXISTE ARQUIVO.
		if (formFile == null) {
			throw new BusinessException("Favor preencher os campos obrigat&oacute;rios!");
		}
		
		// VERIFICA SE É UM ARQUIVO CSV.
		if (!formFile.getFileName().toUpperCase().endsWith(".CSV")) {
			throw new BusinessException("Tipo de Arquivo Inv&aacute;lido!");
		}
		
		// VERIFICA SE O ARQUIVO NÃO EST�? EM BRANCO
		if (formFile.getFileSize() == 0) {
			throw new BusinessException("Arquivo sem dados para importa&ccedil;&atilde;o. Favor verificar e fazer nova importa&ccedil;&atilde;o");
		}
	}

	private String generateFile(final ImportacaoServicoFixaForm importacaoServicoFixaForm, final String path) throws CatalogoPRSException {
        final File file = prepareFile(importacaoServicoFixaForm.getFileImport().getFileName(), path);
		String msg = "Erro ao recuperar arquivo do formulario";
        try {
            this.reccordFile(file, importacaoServicoFixaForm.getFileImport().getInputStream());
            return file.getName();
        } catch (FileNotFoundException e) {
            logger.error(msg, e);
            throw new CatalogoPRSException(msg, e);
        } catch (IOException e) {
            logger.error(msg, e);
            throw new CatalogoPRSException(msg, e);
        }
	}

    private File prepareFile(final String fileName, final String path) throws CatalogoPRSException {
        try {
            final File dir = new File(path);
            dir.mkdirs();
    		String string = fileName.split("\\.[cC][sS][vV]")[0].replaceAll(" ", "");
    		final File file = new File(dir, String.format("%s%s%s%s", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()), "_", string, ".csv"));
            return file;
        } catch (RuntimeException e) {
            String msg = "Erro ao preparar arquivo para gravacao";
            logger.error(msg, e);
            throw new CatalogoPRSException(msg, e);
        }
    }
    
	private void reccordFile(final File file, final InputStream inputStream) throws CatalogoPRSException {
        try {
            final OutputStream out = new FileOutputStream(file);
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (Throwable t) {
            String msg = "Erro ao gravar arquivo";
            logger.error(msg, t);
            throw new CatalogoPRSException(msg);
        }
        logger.info(String.format("Arquivo gravado em disco com sucesso: %s", file.getAbsolutePath()));
    }
}