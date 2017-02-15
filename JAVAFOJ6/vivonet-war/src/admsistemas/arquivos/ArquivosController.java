package admsistemas.arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses.RelatorioUpload;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class ArquivosController extends AbstractAction {

	private static final long serialVersionUID = 7673884734204438585L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.arquivos.Arquivos arquivosFacade;

	private transient Logger log = new Logger("admsistemas");
	private UploadArquivoForm uploadForm = new UploadArquivoForm();
	private static final String ADMSIS_KEY_PATH_UPLOAD = "ADMSIS_PATH_UPLOAD_ARQUIVOS";
	private static final String ADMSIS_KEY_PATH_SOLAP = "PATH_CARGA_SOLICITAR_APARELHOS";
	private static final String ADMSIS_KEY_PATH_CNSLT = "PATH_CARGA_CONSULTORES"; // Path para Carga de Consultores
	private static final String ADMSIS_KEY_PATH_GSTPJ = "PATH_CARGA_GESTORESPJ";
	private static final String ADMSIS_KEY_PATH_GSTPJLOG = "PATH_CARGA_GESTORESPJ_LOG";
	private static final String ADMSIS_KEY_PATH_ANTFOCUS = "PATH_CARGA_ANATEL_FOCUS";
	private static final String ADMSIS_KEY_PATH_PIMEI    = "PATH_CARGA_ANATEL_PIMEI";
	private static final String ADMSIS_KEY_PATH_BLKLSTCRED = "PATH_CARGA_BLKLSTCRED";
	private static final String ADMSIS_KEY_PATH_WHITELIST = "PATH_CARGA_WHITELIST";
    private static final String ADMSIS_KEY_PATH_MSGSBLKLSTCRED = "PATH_CARGA_MSG_BLKLSTCRED"; //PATH_CARGA_BLKLSTDADOS
    private static final String ADMSIS_KEY_PATH_BLKLSTDADOS = "PATH_CARGA_BLKLSTDADOS";
    private static final String ADMSIS_KEY_PATH_CRGCLTR = "PATH_CARGA_CLTR";
	private static final String CARGA_CONSULTORES = "CNSLT";
	private static final String CARGA_SOL_APARELHO = "SOLAP";
	private static final String CARGA_GESTORES_PJ = "GSTPJ";
	private static final String CARGA_ANATEL_FOCUS = "ANTFO";
	private static final String CARGA_PROCESSO_PIMEI     = "PIMEI";
    private static final String CARGA_BLACKLIST_CREDITO = "BLCRD";
    private static final String CARGA_MSGS_BLACKLIST_CREDITO = "MSBLK";
    private static final String CARGA_BLACKLIST_DADOS = "BLDDS";
    private static final String CARGA_CLUSTER = "CCLTR";
    private static final String CARGA_WHITELIST = "WHITL";
    
    


	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("uploadArquivo".equals(mapping.getParameter())) {
			return uploadArquivo(mapping, form, request, response);
		} else if ("consultaProcessamento".equals(mapping.getParameter())) {
			return consultaProcessamento(mapping, form, request, response);
		} else if ("download".equals(mapping.getParameter())) {
			return download(mapping, form, request, response);
		} else if ("downloadAnatel".equals(mapping.getParameter())) {
			return downloadAnatel(mapping, form, request, response);
		} else if ("downloadLogErro".equals(mapping.getParameter())) {
			return downloadLogErro(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public UploadArquivoForm getUploadForm() {
		if(uploadForm==null) {
			uploadForm = new UploadArquivoForm();
		}
		return uploadForm;
	}

	public void setUploadForm(UploadArquivoForm form) {
		uploadForm = form;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward uploadArquivo(ActionMapping mapping
									 , ActionForm formParam
									 , HttpServletRequest request
									 , HttpServletResponse response) {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		UploadArquivoForm form = (UploadArquivoForm) formParam;
		log.debug("[ArquivosController.uploadArquivo] Inicio");
		try {
			InputStream stream = form.getFileUpload().getInputStream();
			String strNomeAtual = form.getFileUpload().getFileName();
            log.info("[ArquivosController.uploadArquivo] Nome do arquivo: " + strNomeAtual);
            strNomeAtual = StringUtilStatic.removeAccents(strNomeAtual);
            log.info("[ArquivosController.uploadArquivo] Nome do arquivo limpo: " + strNomeAtual);
			String strNomeFunc = form.getSgFuncionalidade();
			String strNomeOriginal = form.getFileUpload().getFileName();
            log.info("[ArquivosController.uploadArquivo] Nome do arquivo: " + strNomeOriginal);
            strNomeOriginal = StringUtilStatic.removeAccents(strNomeOriginal);
            log.info("[ArquivosController.uploadArquivo] Nome do arquivo limpo: " + strNomeOriginal);

			int size = form.getFileUpload().getFileSize();
			if (size == 0) {
				log.debug("ArquivosController::uploadArquivo - size == 0");
				request.setAttribute("msgStatus", "Arquivo inválido");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} else if (size > (40 * 1024 * 1024)) { // Tamanho superior a 40Mb
				log.debug("ArquivosController::uploadArquivo - size > 40 Mb");
				request.setAttribute("msgStatus", "Arquivo inválido");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			boolean temArquivo = false;

			try {
				Pesquisar pesquisar = new Pesquisar();
                Resultset rs = pesquisar.executar("SELECT count(1) qtd FROM infra.arquivofuncionalidade WHERE UPPER(nmArquivo) = UPPER('" + strNomeAtual + "') ");
				if (rs.getLinhas() != null && rs.getLinhas().getLinhaArray() != null && rs.getLinhas().getLinhaArray().length > 0 && rs.getLinhas().getLinhaArray(0) != null && rs.getLinhas().getLinhaArray(0).getValorArray().length > 0) {
					if (Integer.parseInt(rs.getLinhas().getLinhaArray(0).getValorArray(0)) > 0) {
						temArquivo = true;
					}
				}
			} catch (Exception e) {
				log.error("[ArquivosController.uploadArquivo] Erro ao realizar a pesquisa se o arquivo já existe na tabela", e);
			}

			// Não existe validação de nome de arquivo para carga de gestores PJ
			// porque o arquivo sempre será renomeado pelo sistema.
			if (CARGA_GESTORES_PJ.equals(strNomeFunc)) {
				temArquivo = false;
			}

			if (!temArquivo) {
				GetParametro parametro = GetParametro.getInstace();
				ParametroVO apoioParam = null;

				log.debug("ArquivosController::uploadArquivo - Nome Func.: "+strNomeFunc);
				
				if (CARGA_SOL_APARELHO.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_SOLAP);
                } else if(CARGA_PROCESSO_PIMEI.equals(strNomeFunc)) {
                    apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_PIMEI);    					

				} else if (CARGA_CONSULTORES.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_CNSLT);

				} else if (CARGA_ANATEL_FOCUS.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_ANTFOCUS);
					strNomeAtual = gerarNomeArquivo(user, strNomeFunc, ".zip");
					log.debug("[x.uploadArquivo] strNomeAtual " + strNomeAtual);

				} else if (CARGA_GESTORES_PJ.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_GSTPJ);
					strNomeAtual = gerarNomeArquivo(user, strNomeFunc, ".txt");
				} else if (CARGA_BLACKLIST_CREDITO.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_BLKLSTCRED);
					strNomeAtual = gerarNomeArquivo(user, strNomeFunc, ".txt");
                } else if (CARGA_MSGS_BLACKLIST_CREDITO.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_MSGSBLKLSTCRED);
					strNomeAtual = gerarNomeArquivo(user, strNomeFunc, ".txt");
                } else if (CARGA_BLACKLIST_DADOS.equals(strNomeFunc)) {
                    apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_BLKLSTDADOS);
                    strNomeAtual = "blacklistDados" + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".txt";
                } else if (CARGA_WHITELIST.equals(strNomeFunc)) {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_WHITELIST);					
					strNomeAtual = "whitelist_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
                }else if (CARGA_CLUSTER.equals(strNomeFunc)){   
                	apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_CRGCLTR);
                    strNomeAtual = gerarNomeArquivo(user, strNomeFunc, ".txt");
				} else {
					apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_UPLOAD);
				}

				String path = apoioParam.getDsValorParametro();
				if (path.charAt(path.length() - 1) != '/') {
					path += "/";
				}

                log.info("Path de upload: " + path);

				File dir = new File(path);
				File file = new File(path + strNomeAtual);

                log.info("[ArquivosController.uploadArquivo] Path " + path + strNomeAtual);
				if (dir.exists()) {
					OutputStream bos = new FileOutputStream(file);

					byte[] buffer = new byte[256 * 1024];
					int bytesRead = 0;
					while ((bytesRead = stream.read(buffer, 0, 256 * 1024)) != -1) {
						bos.write(buffer, 0, bytesRead);
					}
					buffer = null;
					bos.close();
					stream.close();

					request.setAttribute("msgStatus", "Arquivo enviado com sucesso");
					if (CARGA_ANATEL_FOCUS.equals(strNomeFunc)) {
						arquivosFacade.incluiProcessamentoArquivoAnatel(user, strNomeAtual, strNomeOriginal);
                    } else 	if (CARGA_CLUSTER.equals(strNomeFunc)){
							arquivosFacade.incluiProcessamentoArquivo(user, strNomeFunc, strNomeAtual);                        
					} else {
						arquivosFacade.incluiProcessamentoArquivo(user, strNomeFunc, strNomeAtual);
					}

					// INICIO GET IP
					String IP = request.getHeader("X-Forwarded-For");
					if (IP == null) {
						IP = request.getRemoteAddr();
						log.debug("IP from RemoteAddr = " + IP);

					} else {
						log.debug("HEADER_IP from X-Forwarded-For = " + IP);
					}
					// FIM GET IP
				} else {
					log.debug("ArquivosController::uploadArquivo - O Diretório não existe");
					request.setAttribute("msgStatus", "Problemas na tentativa de gravar o arquivo.");
				}
			} else {
				log.debug("ArquivosController::uploadArquivo - Arquivo selecionado já Processado.");
				request.setAttribute("msgStatus", "Arquivo selecionado já Processado.");
			}
		} catch (Exception e) {
			log.error("Erro::", e);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaProcessamento.jsp"
	 */
	public ActionForward consultaProcessamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("[ArquivosController.consultaProcessamento] Inicio: " + user);
		try {
			UploadArquivoForm form = (UploadArquivoForm) formParam;
			ArquivoProcessamentoVO arquivoProcessamentoVO = null;
			
			log.debug("[ArquivosController.consultaProcessamento] SgFuncionalidade: " + form.getSgFuncionalidade());

			if (form!=null && form.getSgFuncionalidade() != null && !ConstantesCRM.SVAZIO.equals(form.getSgFuncionalidade().trim()) && !"GSTPJ".equals(form.getSgFuncionalidade().trim())) {
				arquivoProcessamentoVO = ArquivoProcessamentoVO.Factory.newInstance();
				/* BUSCA POR CARGAS DA ANATEL FOCUS EXIGEM PERÍODO */
				if (CARGA_ANATEL_FOCUS.equalsIgnoreCase(form.getSgFuncionalidade()) && !ConstantesCRM.SVAZIO.equals(form.getDtInicial().trim()) && !ConstantesCRM.SVAZIO.equals(form.getDtFinal().trim())) {
					arquivoProcessamentoVO = arquivosFacade.getDadosArquivosProcessadosAnatel(form.getDtInicial().trim(), form.getDtFinal().trim());

				} else {
					arquivoProcessamentoVO = arquivosFacade.getDadosArquivosProcessados(user, form.getSgFuncionalidade().trim());
				}

			} else if (form!=null && CARGA_GESTORES_PJ.equals(form.getSgFuncionalidade().trim())) {
				arquivoProcessamentoVO = ArquivoProcessamentoVO.Factory.newInstance();
				String pathArquivosLog = ConstantesCRM.SVAZIO;

				try {
					pathArquivosLog = GetParametro.getInstace().getParametroVO(user, ADMSIS_KEY_PATH_GSTPJLOG).getDsValorParametro();
				} catch (Exception e) {
					log.debug("ArquivosController:consultaProcessamento( " + user + " )", e);
				}

				File diretorio = new File(pathArquivosLog);
				if (diretorio.exists()) {
					File fList[] = diretorio.listFiles();
					for (int i = 0; i < fList.length; i++) {
						if (fList[i].isFile()) {
							arquivoProcessamentoVO.addNewItem().setNmFileBad(fList[i].getPath());
						}
					}
				}
			}
			setUploadForm(form);
			form.setArquivoProcessamentoVO(arquivoProcessamentoVO);

		} catch (Exception e) {
			log.error("ArquivosController::consultaProcessamento", e);
			request.setAttribute("msgStatus", "Houve um problema durante a consulta de processamento.");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaProcessamento.jsp"
	 */
	public ActionForward download(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			UploadArquivoForm form = (UploadArquivoForm) formParam;
			String strPos = request.getParameter("nrPos");
			if (strPos != null && !ConstantesCRM.SVAZIO.equals(strPos)) {
				try {
					int nrPos = Integer.parseInt(strPos);
					GetParametro parametro = GetParametro.getInstace();
					ParametroVO apoioParam = null;
					if (CARGA_SOL_APARELHO.equals(form.getSgFuncionalidade())) {
						apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_SOLAP);

                    } else if(CARGA_PROCESSO_PIMEI.equals(form.getSgFuncionalidade())){
                        apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_PIMEI);                    						
						
					} else if (CARGA_CONSULTORES.equals(form.getSgFuncionalidade())) {
						apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_CNSLT);

					} else if (CARGA_GESTORES_PJ.equals(form.getSgFuncionalidade())) {
						apoioParam = ParametroVO.Factory.newInstance();
						apoioParam.setDsValorParametro(ConstantesCRM.SVAZIO);

					} else {
						apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_UPLOAD);
					}

					String nmArquivo = getUploadForm().getArquivoProcessamentoVO().getItemArray(nrPos).getNmFileBad();
					String path = apoioParam.getDsValorParametro();
					if (!CARGA_GESTORES_PJ.equals(form.getSgFuncionalidade())) {
						if (path.charAt(path.length() - 1) != '/') {
							path += "/";
						}
					}

					File arquivo = new File(path + nmArquivo);
					if (arquivo != null && arquivo.isFile()) {
						response.addHeader("Content-Disposition", "attachment; filename=" + nmArquivo);
						response.addHeader("Content-Type", "application/force-download");
						response.addHeader("Content-Transfer-Encoding", "binary");
						response.addHeader("Pragma", "no-cache");
						response.addHeader("Expires", "0");

						// int fSize = (int)arquivo.length();

						FileInputStream fis = new FileInputStream(arquivo);
						PrintWriter pw = response.getWriter();
						int c = -1;
						while ((c = fis.read()) != -1) {
							pw.print((char) c);
						}
						fis.close();
						pw.flush();
						pw = null;
						return null;

					} else {
						request.setAttribute("msgStatus", "Arquivo não encontrado.");
					}

				} catch (Exception e) {
					log.error("ArquivosController:downloadArquivoPalitagens( " + user + " )", e);
				}
			}

		} catch (Exception e) {
			request.setAttribute("msgStatus", "Arquivo inválido");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaProcessamento.jsp"
	 */
	public ActionForward downloadAnatel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		// GetParametro parametro = GetParametro.getInstace();
		try {
			String strPos = request.getParameter("nrPos");
			int nrPos = Integer.parseInt(strPos);
			// ParametroVO apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_ANTFOCUS);
			String idAtendimento = getUploadForm().getArquivoProcessamentoVO().getItemArray(nrPos).getIdAtendimentoAnatelArquivo();

			RelatorioUpload relatorioUpload = arquivosFacade.getRelatorioUpload(idAtendimento);

			if (relatorioUpload != null) {
				String fileName = getCsvFileName(relatorioUpload.getNmArquivo());
				setHeader(fileName, response);
				PrintWriter out = response.getWriter();

				StringBuffer fileCSV = new StringBuffer();
				fileCSV.append("Nome Arquivo|Qtde de registros|Qtde de registros processados com sucesso|Qtde de registros rejeitados com erro|Qtde de protocolos abertos para clientes|Qtde de protocolos abertos para não clientes|Qtde de contatos novos|Qtde de contatos genéricos|\n");
				fileCSV.append(relatorioUpload.getNmArquivoOriginal()).append("|");
				fileCSV.append(relatorioUpload.getQtTotalRegistros()).append("|");
				fileCSV.append(relatorioUpload.getQtProcessado()).append("|");
				fileCSV.append(relatorioUpload.getQtRejeitado()).append("|");
				fileCSV.append(relatorioUpload.getQtProtocoloCliente()).append("|");
				fileCSV.append(relatorioUpload.getQtProtocoloNaoCliente()).append("|");
				fileCSV.append(relatorioUpload.getQtContatoNovo()).append("|");
				fileCSV.append(relatorioUpload.getQtContatoGenerico()).append("|");

				out.println(fileCSV.toString());
				out.close();

				return null;

			} else {
				request.setAttribute("msgStatus", "Arquivo inválido");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {
			request.setAttribute("msgStatus", "Arquivo inválido");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaProcessamento.jsp"
	 */
	public ActionForward downloadLogErro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		// UploadArquivoForm form = (UploadArquivoForm) formParam;
		// GetParametro parametro = GetParametro.getInstace();
		try {
			String strPos = request.getParameter("nrPos");
			int nrPos = Integer.parseInt(strPos);
			// ParametroVO apoioParam = parametro.getParametroVO(user, ADMSIS_KEY_PATH_ANTFOCUS);
			String idAtendimento = getUploadForm().getArquivoProcessamentoVO().getItemArray(nrPos).getIdAtendimentoAnatelArquivo();
			RelatorioUpload relatorioUpload = arquivosFacade.getRelatorioUpload(idAtendimento);

			if (relatorioUpload != null) {
				String fileName = "LogErro_" + getCsvFileName(relatorioUpload.getNmArquivo());
				String nmArquivo = relatorioUpload.getDsArquivoLog();
				File arquivo = new File(nmArquivo);

				if (arquivo != null && arquivo.isFile()) {
					setHeader(fileName, response);
					FileInputStream fis = new FileInputStream(arquivo);
					PrintWriter pw = response.getWriter();
					int c = -1;
					while ((c = fis.read()) != -1) {
						pw.print((char) c);
					}
					fis.close();
					pw.flush();
					pw = null;
					return null;

				} else {
					request.setAttribute("msgStatus", "Arquivo não encontrado.");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}

			} else {
				throw new Exception("[ArquivosController]Erro busca Relatório Upload");
			}

		} catch (Exception e) {
			request.setAttribute("msgStatus", "Arquivo inválido");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

	}

	private String gerarNomeArquivo(String user, String sgFuncionalidade, String ext) {
		String strNomeAtual = ConstantesCRM.SVAZIO;
		try {
			int proximoID = arquivosFacade.getQuantidadeArquivos(user, sgFuncionalidade);
			String paddedID = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
			strNomeAtual = proximoID + "_" + paddedID + ext;
		} catch (Exception e) {
			log.error("[ArquivosController:gerarNomeArquivo] Erro ", e);
		}
		return strNomeAtual;
	}

	private String getCsvFileName(String fileName) {
		String csvFileName = fileName.substring(0, fileName.indexOf("."));
		return csvFileName + ".csv";
	}

	private void setHeader(String fileName, HttpServletResponse response) {
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.addHeader("Content-Type", "application/force-download");
		response.addHeader("Content-Transfer-Encoding", "binary");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", "0");
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class UploadArquivoForm extends ActionForm {

		private static final long serialVersionUID = -8712755583018053996L;

		private String sgFuncionalidade = ConstantesCRM.SVAZIO;
		private String nmFileBad = ConstantesCRM.SVAZIO;
		private FormFile fileUpload;
		private ArquivoProcessamentoVO arquivoProcessamentoVO = ArquivoProcessamentoVO.Factory.newInstance();
		private String dtInicial = ConstantesCRM.SVAZIO;
		private String dtFinal = ConstantesCRM.SVAZIO;

		public String getSgFuncionalidade() {
			return sgFuncionalidade;
		}

		public void setSgFuncionalidade(String sgFuncionalidade) {
			this.sgFuncionalidade = sgFuncionalidade;
		}

		public String getNmFileBad() {
			return nmFileBad;
		}

		public void setNmFileBad(String nmFileBad) {
			this.nmFileBad = nmFileBad;
		}

		public void setFileUpload(FormFile fileUpload) {
			this.fileUpload = fileUpload;
		}

		public FormFile getFileUpload() {
			return fileUpload;
		}

		public ArquivoProcessamentoVO getArquivoProcessamentoVO() {
			return arquivoProcessamentoVO;
		}

		public void setArquivoProcessamentoVO(ArquivoProcessamentoVO arquivoProcessamentoVO) {
			this.arquivoProcessamentoVO = arquivoProcessamentoVO;
		}

		public void setDtFinal(String dtFinal) {
			this.dtFinal = dtFinal;
		}

		public String getDtFinal() {
			return dtFinal;
		}

		public void setDtInicial(String dtInicial) {
			this.dtInicial = dtInicial;
		}

		public String getDtInicial() {
			return dtInicial;
		}
	}
}
