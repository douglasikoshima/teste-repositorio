package usuario.permissaoMassiva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import br.com.vivo.fo.admsistemas.vo.ArquivoProcessamentoVODocument.ArquivoProcessamentoVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import com.indracompany.actions.AbstractAction;

public class PermissaoController extends AbstractAction {

    private static final long serialVersionUID = -5792414425543724516L;

    @EJB
    private br.com.vivo.fo.ctrls.usuario.permissaoMassiva.ArquivoManutencaoFacade arquivoManutencaoFacade;

    private static transient Logger log = new Logger("usuario");

    private UsuarioUploadArquivoForm uploadForm = new UsuarioUploadArquivoForm();
    private String PATH_TO_; //Carrega o PATH através da tabela APOIO.PARAMETRO ("PATH_PROCESSO_MASSIVO")
    private String _DS_ = "/";
    private String DATA = "data" + _DS_;
    private String ERROR = "error" + _DS_;

    private int funcAtual = 0;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);

        }else if ("uploadArquivo".equals(mapping.getParameter())) {
            return uploadArquivo(mapping, form, request, response);

        }else if ("consultaManutencao".equals(mapping.getParameter())) {
            return consultaManutencao(mapping, form, request, response);

        }else if ("downloadLogErro".equals(mapping.getParameter())) {
            return downloadLogErro(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    private ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        log.debug("[PermissaoController.begin] Inicio");
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public ActionForward uploadArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {

        log.info("[PermissaoController.uploadArquivo] Iniciando Upload de arquivo.");
        //LoadProperties properties = new LoadProperties(request);
        PATH_TO_ = arquivoManutencaoFacade.buscarParametroPath("PATH_PERMISSAO_MASSIVA");
        log.info("Caminho do uploado de arquivo encontrada: " + PATH_TO_);
        String user = (String) request.getSession().getAttribute("usuarioLogin");
        int funcionalidade = 0;
        int sq = -1;

        UsuarioUploadArquivoForm form = (UsuarioUploadArquivoForm) formParam;
        log.debug("[PermissaoController.uploadArquivo] begin");
        log.info("[PermissaoController.uploadArquivo] Usuário: " + user);

        try{
            InputStream stream = form.getFileUpload().getInputStream();
            String strNomeAtual = form.getFileUpload().getFileName().toUpperCase();
            String strNomeFunc = form.getSgFuncionalidade();

            log.info("[PermissaoController.uploadArquivo] Nome do arquivo: " + strNomeAtual);
            strNomeAtual = StringUtilStatic.removeAccents(strNomeAtual);
            log.info("[PermissaoController.uploadArquivo] Nome do arquivo limpo: " + strNomeAtual);
            
            log.info("[PermissaoController.uploadArquivo] Funcionalidade selecionada: " + strNomeFunc);

            int size = form.getFileUpload().getFileSize();
            if (size == 0) {
                log.debug("PermissaoController::uploadArquivo - size == 0");
                request.setAttribute("msgStatus", "Arquivo inválido");

                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(ConstantesCRM.SUCCESS);

            } else if (size > (20 * 1024 * 1024)) {
                log.debug("PermissaoController::uploadArquivo - size > 20 Mb");
                request.setAttribute("msgStatus", "Arquivo muito grande. Maior que 20 MB.");

                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                return mapping.findForward(ConstantesCRM.SUCCESS);
            }

            String path = PATH_TO_;

            if("MANUTL".equals(strNomeFunc)){
                path += "manutencaoLogin";
                funcionalidade = 1;

            }else if("MANUTA".equals(strNomeFunc)){
                path += "manutencaoAcesso";
                funcionalidade = 2;
            } else if ("MANUTC".equals(strNomeFunc)) {
                path += "CadUsuarioMassivo";
                funcionalidade = 3;
            }

            path += _DS_ + DATA;
            log.info("[PermissaoController.uploadArquivo] Caminho completo gerada para o uploado do arquivo: " + path);

            sq = startDB(strNomeAtual, user, funcionalidade);
            log.debug("[PermissaoController.uploadArquivo] Sequencia Retornada " + String.valueOf(sq));

            if(sq != -1){
                File dir = new File(path);

                log.debug("[PermissaoController.uploadArquivo] Path " + path);
                log.debug("[PermissaoController.uploadArquivo] Path/File " + path + strNomeAtual);
                log.info("[PermissaoController.uploadArquivo] Diretorio existe? " + dir.exists());

                if (dir.exists()) {

                    log.info("[PermissaoController.uploadArquivo] Diretório encontrado.");

                    String strNomeTmp = strNomeAtual.substring(0, strNomeAtual.length() - 4).concat(".tmp");
                    File fileTmp = new File(path + strNomeTmp);

                    OutputStream bos = new FileOutputStream(fileTmp);

                    byte[] buffer = new byte[256 * 1024];
                    int bytesRead = 0;
                    while ((bytesRead = stream.read(buffer, 0, 256 * 1024)) != -1) {
                        bos.write(buffer, 0, bytesRead);
                    }
                    buffer = null;
                    bos.close();
                    stream.close();

                    /**
                     * ****************************************************************************************
                     */
                    File fileTxt = new File(path + strNomeAtual);
                    FileWriter fw = new FileWriter(fileTxt);

                    BufferedReader br = new BufferedReader(new FileReader(fileTmp));
                    String line = "";

                    while((line = br.readLine()) != null){
                        fw.write(line + "\n");
                    }
                    fw.flush();
                    fw.close();
                    br.close();

                    request.setAttribute("msgStatus", "Arquivo enviado com sucesso!");
                    log.info("[PermissaoController.uploadArquivo] Arquivo enviado com sucesso!");

                    // INICIO GET IP
                    String IP = request.getHeader("X-Forwarded-For");
                    if (IP == null) {
                        IP = request.getRemoteAddr();
                        log.debug("IP from RemoteAddr = " + IP);

                    } else {
                        log.debug("HEADER_IP from X-Forwarded-For = " + IP);
                    }
                    // FIM GET IP
                }else{
                    request.setAttribute("msgStatus", "Problemas de acesso ao local do arquivo.");
                    log.info("[PermissaoController.uploadArquivo] Problemas de acesso ao local do arquivo.");
                    eraseDB(sq);
                }
            }else{
                throw new Exception("Arquivo já existente na base ou ocorreu um erro de conexão com o banco de dados!");
            }
        }catch(Exception e){
            if (sq != -1) {
                eraseDB(sq);
            }
            request.setAttribute("msgStatus", e.getMessage());
            log.error("ERROR: ", e);
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private ActionForward consultaManutencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        String user = (String) request.getSession().getAttribute("usuarioLogin");
        log.debug("[PermissaoController.consultaProcessamento] Inicio: " + user);

        try {
        	UsuarioUploadArquivoForm form = (UsuarioUploadArquivoForm) formParam;
            ArquivoProcessamentoVO arquivoProcessamentoVO = null;

            log.debug("[PermissaoController.consultaProcessamento] SgFuncionalidade: " + form.getSgFuncionalidade());

            if ("MANUTL".equals(form.getSgFuncionalidade())) {
                funcAtual = 1;
            }
            if ("MANUTA".equals(form.getSgFuncionalidade())) {
                funcAtual = 2;
            }
            if ("MANUTC".equals(form.getSgFuncionalidade())) {
                funcAtual = 3;
            }

            if (form != null && form.getSgFuncionalidade() != null && !ConstantesCRM.SVAZIO.equals(form.getSgFuncionalidade().trim())) {
                arquivoProcessamentoVO = ArquivoProcessamentoVO.Factory.newInstance();
                arquivoProcessamentoVO = arquivoManutencaoFacade.selectTableByFunc(form.getSgFuncionalidade().trim());
            }

            setUploadForm(form);
            form.setArquivoProcessamentoVO(arquivoProcessamentoVO);

        } catch (Exception e) {
            log.error("PermissaoController::consultaProcessamento", e);
            request.setAttribute("msgStatus", "Houve um problema durante a consulta de processamento.");
        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private ActionForward downloadLogErro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        PATH_TO_ = arquivoManutencaoFacade.buscarParametroPath("PATH_PERMISSAO_MASSIVA");

        log.debug("[PermissaoController.downloadLogErro] begin");

        try {
            String strPos = request.getParameter("nrPos");
            int nrPos = Integer.parseInt(strPos);

            log.debug("[PermissaoController.downloadLogErro] funcionalidade = " + funcAtual);
            log.debug("[PermissaoController.downloadLogErro] nrPos = " + nrPos);

            String fileErr = getUploadForm().getArquivoProcessamentoVO().getItemArray(nrPos).getNmFileBad();
            log.debug("[PermissaoController.downloadLogErro] caminho = " + fileErr);

            String nmArquivo = fileErr.substring(fileErr.lastIndexOf("/") + 1);
            log.info("[PermissaoController.downloadLogErro] nome do arquivo de erro = " + nmArquivo);
            
            String path = PATH_TO_;
            if(funcAtual == 1){
                path += "manutencaoLogin";
            } else if (funcAtual == 2) {
                path += "manutencaoAcesso";
            } else if (funcAtual == 3) {
                path += "CadUsuarioMassivo";
            }

            path += _DS_ + ERROR + nmArquivo;

            log.debug("[PermissaoController.downloadLogErro] caminho Final = " + path);
            File arquivo = new File(path);

            if (arquivo != null && arquivo.isFile()) {
                setHeader(nmArquivo, response);
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

        } catch (Exception e) {
            request.setAttribute("msgStatus", "Arquivo inválido");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        }

        log.debug("[PermissaoController.downloadLogErro] end");
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }
    

    private int startDB(String fileName, String login, int funcionalidade){
        return arquivoManutencaoFacade.initTableManut(fileName, login,
                funcionalidade);
    }

    private void eraseDB(int sq){
        arquivoManutencaoFacade.eraseTableManut(sq);
    }

    private void setHeader(String fileName, HttpServletResponse response) {
        response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.addHeader("Content-Type", "application/force-download");
        response.addHeader("Content-Transfer-Encoding", "binary");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
    }

    public UsuarioUploadArquivoForm getUploadForm() {
        if(uploadForm==null) {
            uploadForm = new UsuarioUploadArquivoForm();
        }
        return uploadForm;
    }

    public void setUploadForm(UsuarioUploadArquivoForm form) {
        uploadForm = form;
    }

    public static class UsuarioUploadArquivoForm extends ActionForm {

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
