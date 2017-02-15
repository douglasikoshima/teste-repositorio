package br.com.vivo.catalogoPRS.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DefaultFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.vivo.catalogoPRS.delegate.ClassificacaoMultimidiaDelegate;
import br.com.vivo.catalogoPRS.delegate.CorDelegate;
import br.com.vivo.catalogoPRS.util.ApplicationConfiguration;
import br.com.vivo.catalogoPRS.util.ImagemUtil;

import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;
import com.indracompany.catalogo.to.CorTO;
import com.indracompany.catalogo.util.JSONUtil;

/**
 * Servlet implementation class for Servlet: UploadFileServlet
 *
 */
@SuppressWarnings("serial")
public class UploadFileMultimidiaServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonResult = new JSONObject();
		try {
			FileItem fileItem = getFileItem(request);
			String fileName = ImagemUtil.salvarImagem(fileItem);
			jsonResult.put("nome", fileName);
			jsonResult.put("caminho_link_imagens_modelo", ApplicationConfiguration.getCaminhoLinkImagensModelo());
			
			List<ClassificacaoMultimidiaTO> classificacoes = (new ClassificacaoMultimidiaDelegate()).findAll();
			jsonResult.put("classificacoes", JSONUtil.toJSONArray(classificacoes));
			
			List<CorTO> cores = (new CorDelegate()).findAll();
			jsonResult.put("cores", JSONUtil.toJSONArray(cores));
		} catch (Exception e) {
			e.printStackTrace();
			try {
				jsonResult.put("erro", obterErro(e, "Erro ao carregar o arquivo."));
			} catch (JSONException je) {}
		}
		
		response.setContentType("text/html");
		response.setCharacterEncoding("iso-8859-1");
		response.setBufferSize(1024);
		
		PrintWriter writer = response.getWriter();
		writer.write(jsonResult.toString());
		writer.flush();
		writer.close();
	}
	
	@SuppressWarnings("unchecked")
	private FileItem getFileItem(HttpServletRequest request) throws FileUploadException {
		if (FileUpload.isMultipartContent(request)) {
			DefaultFileItemFactory factory = new DefaultFileItemFactory();
			FileUpload upload = new FileUpload(factory);
			List<FileItem> fileItemList = upload.parseRequest(request);
			for (FileItem fileItem : fileItemList) {
				if (!fileItem.isFormField()) {
					return fileItem;
				}
			}
		}
		return null;
	}
	
	private String obterErro(Exception e, String erroDefault) {
		String erro = null;
		Throwable cause = e.getCause();
		if (cause != null) {
			erro = cause.getMessage();
		}
		if (erro == null || erro.length() == 0) {
			erro = e.getMessage();
		}
		if (erro == null || erro.length() == 0) {
			erro = erroDefault;
		}
		return erro;
	}
	
}