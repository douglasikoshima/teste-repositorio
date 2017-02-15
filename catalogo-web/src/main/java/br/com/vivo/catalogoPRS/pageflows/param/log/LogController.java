package br.com.vivo.catalogoPRS.pageflows.param.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.LogForm;
import br.com.vivo.catalogoPRS.util.PropertiesUtil;

public class LogController extends BaseMappingDispatchAction {

	private static final long serialVersionUID = 1L;
	private String diretorio = new PropertiesUtil().getString("log.diretorio");
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "/templates/logList.jsp")})
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		File file = new File(diretorio);
		
		List<String> logList = Arrays.asList(file.list());
		request.setAttribute("logList", logList);
		
		return mapping.findForward("success");
	}
	
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "/pages/acao/acaoList.jsp") })
	public ActionForward obterLog(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		LogForm logForm = (LogForm)form;
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(diretorio + logForm.getNmFile()));
			
			StringBuffer str = new StringBuffer();
			while (in.ready()) {                
				str.append(in.readLine() + "\n");                
			}
			exportFile(str.toString(), null, "", logForm.getNmFile(), response);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return mapping.findForward("success");
	}
}

	