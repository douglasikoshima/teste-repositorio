package VOLTAV.manterBanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.vivo.fo.log.Logger;


public class BannerServlet extends HttpServlet {
	private static  transient Logger log = new Logger("vol");


	private static final long serialVersionUID = -7695397144117489055L;

	public void init(ServletConfig config) throws ServletException {
        log.info("BannerServlet:inicio: init");

		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        log.info("BannerServlet:inicio: doGet");
    
		processRequest(request, response);

        log.info("BannerServlet:final: doGet");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
        log.info("BannerServlet:inicio: doPost");

		processRequest(request, response);

        log.info("BannerServlet:final: doPost");
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("BannerServlet:: processRequest");
		
		try{
			
			String bannerName = (String)request.getParameter("bannerName");
			String bannerPath = (String)request.getSession().getAttribute("bannerPath");
			log.info("BannerServlet:: processRequest: bannerPath " + bannerPath);
			log.info("BannerServlet:: processRequest: bannerName " + bannerName);
			
			response.setContentType("image/gif");
	        int i = 0;
	        InputStream is = new FileInputStream(bannerPath +bannerName);	        
	        PrintWriter pw = response.getWriter();
	        while ((i = is.read()) != -1) {
	            pw.write(i);
	        }	        
	        if (pw != null)  {            
	            pw.flush();
	            pw.close();
	        }
	        if (is != null) {
	            is.close();
	        }	    		
		}catch(Exception ex){
        	log.error("BannerServlet:Não foi possível Banner "+ex);
        }
      
        log.info("BannerServlet:final: processRequest");
	}
	
}
