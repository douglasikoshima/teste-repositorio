package br.com.vivo.catalogoPRS.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class IconeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(IconeServlet.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("IconeServlet.processRequest inicio");
		String icone = (String)request.getParameter("icone");
		log.debug("nome do ícone = " + icone);
		String path = ApplicationConfiguration.getCaminhoSalvarImagensCaracteristicas();
		try {
			File file = new File(path + icone);
			InputStream is = new FileInputStream(file);
			response.setContentType("image/gif");
			response.setHeader("Cache-Control", "no-cache");
			byte[] buffer = new byte[1];
			while(is.read(buffer) != -1) {
				response.getOutputStream().write(buffer);
			}	
			is.close();
		} catch (Exception ex){
			log.error("Erro ao carregar arquivo de imagem " + ex.getMessage());
		}
	}
}
