package VOLE.relatorioMVE;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import com.indracompany.actions.AbstractAction;

public class RelatorioMVEController extends AbstractAction {
	
	private static final long serialVersionUID = 1L;
	
	private RelatorioForm relatorioForm = new RelatorioForm();
	
	public RelatorioForm getRelatorioForm() {
        if (this.relatorioForm == null) 
            this.relatorioForm = new RelatorioForm();
		return relatorioForm;
	}	
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("clean".equals(mapping.getParameter())) {
			return clean(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("download".equals(mapping.getParameter())) {
			return download(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}	
	
	private String getPathRelatorio() {
        StringBuffer query = new StringBuffer();
        
        query.append(" SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'PATH_DOWNLOAD_MEUVIVO' ");

        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = pesquisar.executar(query.toString());
        
        Resultset.Linhas.Linha linha = rsVO.getLinhas().getLinhaArray(0);
        return linha.getValorArray(0);
	}
	
    private void buscar() {
        try{
            StringBuffer query = new StringBuffer();
            
            query.append(" SELECT NMARQUIVO,                                                                                 ");
            query.append("       DECODE(INSTATUS,'S','Solicitado','A','Aguardando Processamento','P','Processado') INSTATUS, ");
            query.append("       TPRELATORIO,                                                                                ");
            query.append("       TO_CHAR(DTINICIALREL, 'DD/MM/YYYY') DTINICIALREL,                                           ");
            query.append("       TO_CHAR(DTFINALREL, 'DD/MM/YYYY') DTFINALREL,                                               "); 
            query.append("       TO_CHAR(DTSOLICITACAO, 'DD/MM/YYYY HH24:MI') DTSOLICITACAO                                  ");
            query.append("  FROM VOLE.STATUSRELMVE                                               ");
            query.append("  ORDER BY IDSTATUSRELMVE DESC                                             ");
    
            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar(query.toString());
            
            if(rsVO==null || rsVO.getLinhas()==null){
                rsVO = Resultset.Factory.newInstance();
                rsVO.addNewLinhas();
            }
            
            getRelatorioForm().setResultset(rsVO); 
            
            if (rsVO.getLinhas() == null ) {
        	 request.setAttribute("msgStatus", "Não existe informações a serem exibidas.");
            }    
            
        }catch(Exception e){
            //log.error("RelatorioQuadroAvisoController::pesquisar",e);
        }        
    }	
    
   	
	
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		buscar();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	public ActionForward download(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		
		String nmArquivo = request.getParameter("arquivo");
		
		String path = getPathRelatorio();
		
		try {
	        File arquivo = new File(path + nmArquivo);
	        if (arquivo!=null && arquivo.isFile()) {
	            response.addHeader("Content-Disposition","attachment; filename="+nmArquivo);
	            response.addHeader("Content-Type","application/force-download");
	            response.addHeader("Content-Transfer-Encoding","binary");
	            response.addHeader("Pragma","no-cache");
	            response.addHeader("Expires","0");
	
	            FileInputStream fis = new FileInputStream(arquivo);
	            PrintWriter pw = response.getWriter();
	            int c = -1;
	            while ((c = fis.read()) != -1){
	                pw.print((char)c);
	            }
	            fis.close();
	            pw.flush();
	            pw = null;
	            return null;
	
	        } else {
	            request.setAttribute("msgStatus", "Arquivo não encontrado.");
	        }  	
		}catch (Exception e) {
			
		}
		
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		
	String user = ConstantesCRM.getCurrentUser(request.getSession());
        String dataInicio  = request.getParameter("dataInicio");
        String datatermino = request.getParameter("dataTermino");
        String tipo        = request.getParameter("tipo");
        String relatorio   = request.getParameter("relatorio");
        
        try{
            StringBuffer query = new StringBuffer();
            
            query.append(" INSERT INTO VOLE.STATUSRELMVE (IDSTATUSRELMVE,NMARQUIVO,INSTATUS,TPRELATORIO,DTINICIALREL,DTFINALREL,DTSOLICITACAO,IDUSUARIO)                                                                                  ");
            query.append("                        VALUES (VOLE.STATUSRELMVESQ.NEXTVAL, ");
            query.append("                                '" +relatorio + "',");
            query.append("                                'S',");
            query.append("                                '" +relatorio + "',");            
            query.append(" to_date('").append(dataInicio).append("','dd/mm/yyyy'), ");
            query.append(" to_date('").append(datatermino).append("','dd/mm/yyyy'), ");
            query.append("                                SYSDATE,");
            query.append("                                '" +user + "')");

            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar(query.toString());
                        
        }catch(Exception e){
            //log.error("RelatorioQuadroAvisoController::pesquisar",e);
        }          
        
        buscar();		
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	
	public ActionForward clean(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {
		
		Resultset rsVO = Resultset.Factory.newInstance();
		rsVO.addNewLinhas();

		getRelatorioForm().setDataInicio(ConstantesCRM.SVAZIO);
		getRelatorioForm().setDataTermino(ConstantesCRM.SVAZIO);
		getRelatorioForm().setResultset(rsVO);		
		
        buscar();			
		
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	
}
