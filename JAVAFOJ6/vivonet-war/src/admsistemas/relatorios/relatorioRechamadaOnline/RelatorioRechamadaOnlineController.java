package admsistemas.relatorios.relatorioRechamadaOnline;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.RelatorioBlindagemVODocument.RelatorioBlindagemVO;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class RelatorioRechamadaOnlineController extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private RelatorioForm relatorioForm = new RelatorioForm();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("clean".equals(mapping.getParameter())) {
			return clean(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);				
		} else if ("manterLogado".equals(mapping.getParameter())) {
			return manterLogado(mapping, form, request, response);
		} 
		return begin(mapping, form, request, response);
	}	
	
	
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
        // Tempo de atualização do relatorio
        int time = autoRefreshTime();
        request.getSession().setAttribute("TIME_REFRESH", String.valueOf(time));        
        
        buscar("-1","-1");    

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}	
	

	public ActionForward manterLogado(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
       try {
            Pesquisar pesquisar = new Pesquisar();
            pesquisar.executar("SELECT SYSDATE FROM DUAL");                    
       } catch (Exception e){}
		return mapping.findForward("done");
	}	

	public ActionForward clean(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
        // Tempo de atualização do relatorio
        int time = autoRefreshTime();
        request.getSession().setAttribute("TIME_REFRESH", String.valueOf(time));        
        
        Resultset rsVO = Resultset.Factory.newInstance();
        rsVO.addNewLinhas();
        
        getRelatorioForm().setDataInicio("-1");
        getRelatorioForm().setDataTermino("-1");
        getRelatorioForm().setResultset(rsVO);    
        
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){

        String dataInicio  = request.getParameter("dataInicio");
        String datatermino = request.getParameter("dataTermino");
            
        buscar(dataInicio, datatermino);     
        
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}		
	
	
	private void buscar (String dataInicio, String dataTermino) {
        try{
            StringBuffer query = new StringBuffer();    

            query.append(" select to_char(dad.dtultimaalteracao, 'hh24') || 'h às ' ||                                         "); 
            query.append("        (to_number(to_char(dad.dtultimaalteracao, 'hh24'))) || 'h59min' as intervalo,                ");
            query.append("        mot.nmmotivorecontato as motivo,                                                             ");
            query.append("        sub.nmsubmotivorecontato as submotivo,                                                       ");
            query.append("        count(1) as qtde,                                                                            ");
            query.append("        round(((count(1) / subtotal.t) * 100), 1) as qtde_perc,                                      ");
            query.append("        total.a as qtde_total,                                                                       "); 
            query.append("        round(total.a /                                                                              "); 
            query.append("              (select count(1)                                                                       "); 
            query.append("                 from atendimento.dadosrecontato                                                     "); 
            query.append("                where to_char(dtultimaalteracao, 'yyyymmddhh24') between                             "); 
            query.append("                      to_char(sysdate, 'yyyymmdd') || '00' and                                       "); 
            query.append("                      to_char(sysdate, 'yyyymmdd') ||                                                "); 
            query.append("                      lpad(to_number(to_char(sysdate, 'hh24') - 1), 2, 0)) * 100,                    "); 
            query.append("              1)                                                                                     "); 
            query.append("                                                                                                     "); 
            query.append("   from atendimento.dadosrecontato    dad,                                                           "); 
            query.append("        contatoadm.motivorecontato    mot,                                                           "); 
            query.append("        contatoadm.submotivorecontato sub,                                                           "); 
            query.append("                                                                                                     "); 
            query.append("        (select count(1) t, to_char(dtultimaalteracao, 'hh24') d                                     "); 
            query.append("           from atendimento.dadosrecontato                                                           "); 
            query.append("          where to_char(dtultimaalteracao, 'yyyymmddhh24') between                                   "); 
            query.append("                to_char(sysdate, 'yyyymmdd') || '00' and                                             "); 
            query.append("                to_char(sysdate, 'yyyymmdd') ||                                                      "); 
            query.append("                lpad(to_number(to_char(sysdate, 'hh24') - 1), 2, 0)                                  "); 
            query.append("          group by to_char(dtultimaalteracao, 'hh24')) subtotal,                                     "); 
            query.append("                                                                                                     ");         
            query.append("        (select count(1) a, idsubmotivorecontato                                                     "); 
            query.append("           from atendimento.dadosrecontato                                                           "); 
            query.append("          where to_char(dtultimaalteracao, 'yyyymmddhh24') between                                   "); 
            query.append("                to_char(sysdate, 'yyyymmdd') || '00' and                                             "); 
            query.append("                to_char(sysdate, 'yyyymmdd') ||                                                      "); 
            query.append("                lpad(to_number(to_char(sysdate, 'hh24') - 1), 2, 0)                                  "); 
            query.append("          group by idsubmotivorecontato) total                                                       "); 
            query.append("  where dad.idmotivorecontato = mot.idmotivorecontato                                                "); 
            query.append("    and dad.idsubmotivorecontato = sub.idsubmotivorecontato                                          "); 
            query.append("    and to_char(dad.dtultimaalteracao, 'hh24') = subtotal.d                                          "); 
            query.append("    and to_char(dad.dtultimaalteracao, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')                    "); 
            query.append("    and total.idsubmotivorecontato = sub.idsubmotivorecontato                                        "); 
            
            if (!dataInicio.equals("-1") && !dataTermino.equals("-1")) {                                
                if (dataInicio.equals(dataTermino)) {
                    query.append(" and to_char(dad.DTULTIMAALTERACAO, 'HH24') = ").append(Integer.parseInt(dataInicio));
                } else {        
                    query.append(" and to_char(dad.DTULTIMAALTERACAO, 'HH24') >= ").append(Integer.parseInt(dataInicio));
                    query.append(" and to_char(dad.DTULTIMAALTERACAO, 'HH24') <= ").append(Integer.parseInt(dataTermino));
                }
            }            
            
            query.append("  group by to_char(dad.dtultimaalteracao, 'hh24'),                    "); 
            query.append("           mot.nmmotivorecontato,                                     "); 
            query.append("           sub.nmsubmotivorecontato,                                  "); 
            query.append("           subtotal.t,                                                "); 
            query.append("           total.a                                                    "); 
            query.append("                                                                      "); 
            query.append("  order by to_char(dad.dtultimaalteracao, 'hh24') desc, count(1) desc "); 
 
            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar(query.toString());
            
            if(rsVO==null || rsVO.getLinhas()==null){
                rsVO = Resultset.Factory.newInstance();
                rsVO.addNewLinhas();

            }

            /*******************************************************************************/
            /** Os calculos realizados na query original foram alterados e estão sendo 
             *  feitos manualmente percorrendo os registros retornados.
             *******************************************************************************/
            
            String agrupador = ConstantesCRM.SVAZIO;            
            int contador = 1;
            
            for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {
                if (j == 0) {
                    agrupador = rsVO.getLinhas().getLinhaArray(j).getValorArray(0);
                 } else {
                     if (!rsVO.getLinhas().getLinhaArray(j).getValorArray(0).equals(agrupador)) {
                       agrupador = rsVO.getLinhas().getLinhaArray(j).getValorArray(0);                         
                       contador = 1;            		 
                     } else if (rsVO.getLinhas().getLinhaArray(j).getValorArray(0).equals(agrupador)) {
                         if (contador < 3) {           		 
                             contador ++;
                         } else {
                             rsVO.getLinhas().removeLinha(j);
                             j--;
                         }
                     }
                 }
            }            
            
            
            DecimalFormat aproximador = new DecimalFormat( "0.00" );  
            
            float n = totalPeriodo(dataInicio, dataTermino);
            float b = 0;            
            float p1 = 0;
            float p2 = 0;
            for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {                
                b = Float.parseFloat(rsVO.getLinhas().getLinhaArray(j).getValorArray(5));                
                
                // Calculo do Percentual
                p1 = Float.parseFloat(rsVO.getLinhas().getLinhaArray(j).getValorArray(3));    
                p2 = percentual(rsVO, rsVO.getLinhas().getLinhaArray(j).getValorArray(0));
                
                rsVO.getLinhas().getLinhaArray(j).setValorArray(6, String.valueOf( aproximador.format((b/n) * 100)) );                
                rsVO.getLinhas().getLinhaArray(j).setValorArray(4, String.valueOf( aproximador.format((p1/p2) * 100)) );                        
            }
            
            getRelatorioForm().setDataInicio(dataInicio);
            getRelatorioForm().setDataTermino(dataTermino);
            getRelatorioForm().setResultset(rsVO); 
            
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        }catch(Exception e){
            //log.error("RelatorioQuadroAvisoController::pesquisar",e);
        }          
                        
    }	
	
	
    
    private int autoRefreshTime() {
        int time = 3600000; // 60000 = 1s
        try {
            
            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar("SELECT DSVALORPARAMETRO FROM APOIO.PARAMETRO WHERE CDPARAMETRO = 'RELATORIO_RECHAMADA_ONLINE_TIME_TO_REFRESH'");            
            if(rsVO!=null || rsVO.getLinhas()!=null){
                time = Integer.parseInt(rsVO.getLinhas().getLinhaArray(0).getValorArray(0));
            }
        } catch (Exception e) {
            //log.error("RelatorioQuadroAvisoController::autoRefreshTime",e);
        }
        return time;
    }
    
    private int totalPeriodo(String dataInicio, String dataTermino) {
        int total = 0; 
        try {
            
            StringBuffer query = new StringBuffer();    
            
            query.append(" select count(1)                                                                           ");
            query.append("   from atendimento.dadosrecontato                                                         ");
            query.append("  where to_char(dtultimaalteracao, 'yyyymmddhh24') between                                 ");
            query.append("        to_char(sysdate, 'yyyymmdd') || '00' and                                           ");
            query.append("        to_char(sysdate, 'yyyymmdd') ||lpad(to_number(to_char(sysdate, 'hh24') - 1), 2, 0) ");

            Pesquisar pesquisar = new Pesquisar();
            Resultset rsVO = pesquisar.executar(query.toString());            
            
            if(rsVO!=null || rsVO.getLinhas()!=null){
                total = Integer.parseInt(rsVO.getLinhas().getLinhaArray(0).getValorArray(0));
            }
        } catch (Exception e) {
            //log.error("RelatorioQuadroAvisoController::autoRefreshTime",e);
        }
        return total;
    }  
    
    private int percentual (Resultset rsVO, String agrupador) {
        int acumulado = 0;
        for (int j = 0; j < rsVO.getLinhas().getLinhaArray().length; j++) {                
            if (rsVO.getLinhas().getLinhaArray(j).getValorArray(0).equals(agrupador)) {
                acumulado = acumulado + Integer.parseInt(rsVO.getLinhas().getLinhaArray(j).getValorArray(3));
            }
        }        
        return acumulado;
    }    
    	
	
	
	public RelatorioForm getRelatorioForm(){
		return relatorioForm;
	}

	public static class RelatorioForm extends ActionForm{
	        private Resultset  resultset;
	        
	        private String     relatorio    = ConstantesCRM.SVAZIO;
	        private String     tipo         = ConstantesCRM.SVAZIO;
	        private String     dataInicio   = ConstantesCRM.SVAZIO;
	        private String     dataTermino  = ConstantesCRM.SVAZIO;
	 
	        
	        public String getTipo() {
	            return tipo;
	        }
	        public void setTipo(String tipo) {
	            this.tipo = tipo;
	        }
	                
	        
	        public String getRelatorio() {
	            return relatorio;
	        }
	        public void setRelatorio(String relatorio) {
	            this.relatorio = relatorio;
	        }
	        
	        public String getDataInicio() {
	            return dataInicio;
	        }
	        public void setDataInicio(String dataInicio) {
	            this.dataInicio = dataInicio;
	        }
	        public String getDataTermino() {
	            return dataTermino;
	        }
	        public void setDataTermino(String dataTermino) {
	            this.dataTermino = dataTermino;
	        }
	        
	        
	        public void setResultset(Resultset resultset){
	            this.resultset = resultset;
	        }

	        public Resultset getResultset(){
	            return this.resultset;
	        }        
	    }    	
	
	
	
	
}
