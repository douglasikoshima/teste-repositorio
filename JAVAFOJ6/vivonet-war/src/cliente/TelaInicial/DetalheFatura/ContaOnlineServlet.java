package cliente.TelaInicial.DetalheFatura;

import java.io.BufferedOutputStream;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ContaOnlineServlet extends HttpServlet {

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {    
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	    response.setContentType("image/gif");
	    String conta = (String) request.getSession().getAttribute("bufferContaOnline");

        BufferedOutputStream bos = new BufferedOutputStream((ServletOutputStream)response.getOutputStream());            
	    bos.write(Base64.decode(conta));
        bos.flush();
	    bos.close();

	    request.getSession().removeAttribute("bufferContaOnline");
	}
}