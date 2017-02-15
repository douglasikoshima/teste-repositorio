package extworkflw;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;

public class AuthenticationExtAction extends Action {

	public static final String USERID_PARAM  = "x-usuario";
	public static final String GROUPID_PARAM = "x-grupo";
	public static final String LINHA_PARAM   = "x-linha";
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setContentType("text/plain");
		PrintWriter w = response.getWriter();
		
		String usuario = request.getParameter(USERID_PARAM);
		String grupo   = request.getParameter(GROUPID_PARAM);
		String linha   = request.getParameter(LINHA_PARAM);
		String token   = request.getSession().getId().substring(0,20);

		if (usuario == null || usuario.isEmpty() 
		    || grupo == null || grupo.isEmpty() 
			|| linha == null || linha.isEmpty() 
			|| linha.length() != 10) {
			w.println("parametros invalidos");
			w.close();
			return null;
		} 				
		
		StringBuffer query = new StringBuffer();
		
		query.append("INSERT INTO ACESSO.TOKENVIVO360 (IDTOKENVIVO360, NRLINHA, IDGRUPO, IDUSUARIOALTERACAO, DTULTIMAATUALIZACAO) VALUES ( ");
		query.append("'" + token + "'," + linha + "," + grupo + "," + usuario + ", SYSDATE )");
		
		Pesquisar pesquisar = new Pesquisar();
		
		pesquisar.executar(query.toString());

		w.println("token=" + token);
		
		
		return super.execute(mapping, form, request, response);
	}

}
