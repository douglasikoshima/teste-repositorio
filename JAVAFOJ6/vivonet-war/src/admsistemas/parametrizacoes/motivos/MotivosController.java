package admsistemas.parametrizacoes.motivos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class MotivosController extends AbstractAction {

    private static final long serialVersionUID = -2192451497955319236L;

    private SubmotivoForm     submotivoForm;
    private MotivoForm        motivoForm;

    public MotivoForm getMotivoForm() {
        if (this.motivoForm == null) {
            this.motivoForm = new MotivoForm();
        }
        return this.motivoForm;
    }

    public SubmotivoForm getSubmotivoForm() {
        if (this.submotivoForm == null) {
            this.submotivoForm = new SubmotivoForm();
        }
        return this.submotivoForm;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("alterar".equals(mapping.getParameter())) {
            return alterar(mapping, form, request, response);
        } else if ("alterarSubmotivo".equals(mapping.getParameter())) {
            return alterarSubmotivo(mapping, form, request, response);
        } else if ("gravar".equals(mapping.getParameter())) {
            return gravar(mapping, form, request, response);
        } else if ("gravarSubmotivo".equals(mapping.getParameter())) {
            return gravarSubmotivo(mapping, form, request, response);
        } else if ("submotivo".equals(mapping.getParameter())) {
            return submotivo(mapping, form, request, response);
        } else if ("atualizaStatus".equals(mapping.getParameter())) {
            return atualizaStatus(mapping, form, request, response);
        } else if ("atualizaStatusSubmotivo".equals(mapping.getParameter())) {
            return atualizaStatusSubmotivo(mapping, form, request, response);
        } else {
            return begin(mapping, form, request, response);
        }
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        listaMotivos();
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward alterar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String codigo = request.getParameter("id");
        Resultset rsVO = null;

        try {
            StringBuffer query = new StringBuffer("");
            query.append("SELECT IDMOTIVORECONTATO, NMMOTIVORECONTATO, INATIVO FROM CONTATOADM.MOTIVORECONTATO WHERE IDMOTIVORECONTATO = " + codigo);
            Pesquisar pesquisar = new Pesquisar();
            rsVO = pesquisar.executar(query.toString());

            if (rsVO != null && rsVO.getLinhas() != null) {
                getMotivoForm().setCodigo(Long.valueOf(rsVO.getLinhas().getLinhaArray(0).getValorArray(0)));
                getMotivoForm().setNome(rsVO.getLinhas().getLinhaArray(0).getValorArray(1));
            }
        } catch (Exception e) {

        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="submotivo.jsp"
     */
    protected ActionForward alterarSubmotivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String codigo = request.getParameter("id");
        Resultset rsVO = null;

        try {
            StringBuffer query = new StringBuffer("");
            query.append("SELECT IDSUBMOTIVORECONTATO, NMSUBMOTIVORECONTATO, INATIVO FROM CONTATOADM.SUBMOTIVORECONTATO WHERE IDSUBMOTIVORECONTATO = " + codigo);
            Pesquisar pesquisar = new Pesquisar();
            rsVO = pesquisar.executar(query.toString());

            if (rsVO != null && rsVO.getLinhas() != null) {
                getSubmotivoForm().setCodigo(Long.valueOf(rsVO.getLinhas().getLinhaArray(0).getValorArray(0)));
                getSubmotivoForm().setNome(rsVO.getLinhas().getLinhaArray(0).getValorArray(1));
            }
        } catch (Exception e) {

        }

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward gravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        MotivoForm form = (MotivoForm) formParam;
        Pesquisar pesquisar = new Pesquisar();

        if (form.getCodigo().intValue() > 0) {
            StringBuffer update = new StringBuffer(ConstantesCRM.SVAZIO);
            update.append("UPDATE CONTATOADM.MOTIVORECONTATO SET NMMOTIVORECONTATO = '").append(form.getNome()).append("' WHERE IDMOTIVORECONTATO = ").append(form.getCodigo());

            Resultset resultset = pesquisar.executar(update.toString());
            if(resultset.getMsg()!=null && resultset.getMsg().length()>0) {
                request.setAttribute("msgRetorno", resultset.getMsg());
            }else {
                request.setAttribute("msgRetorno", "Motivo atualizado com sucesso.");
            }

        } else {
        	
            if (numeroMaximoMotivo()) {
            	request.setAttribute("msgRetorno", "Somente é permitido o cadastro de no máximo 10 motivos.");                
            } else {             	
	            StringBuffer insert = new StringBuffer(ConstantesCRM.SVAZIO);
	            insert.append("INSERT INTO CONTATOADM.MOTIVORECONTATO (IDMOTIVORECONTATO, NMMOTIVORECONTATO, INATIVO) VALUES (CONTATOADM.MOTIVORECONTATOSQ.NEXTVAL,");
	            insert.append("'").append(form.getNome()).append("', 0)");
	
	            Resultset resultset = pesquisar.executar(insert.toString());
	            if(resultset.getMsg()!=null && resultset.getMsg().length()>0) {
	                request.setAttribute("msgRetorno", resultset.getMsg());
	            }else {
	                request.setAttribute("msgRetorno", "Motivo cadastrado com sucesso.");
	            }
            }
        }

        // Atualizo a lista
        getMotivoForm().setCodigo(null);
        getMotivoForm().setNome(ConstantesCRM.SVAZIO);
        listaMotivos();

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }
    
    
    private boolean numeroMaximoMotivo() {
        boolean retorno = false;

        Pesquisar pesquisar = new Pesquisar();
        StringBuffer query = new StringBuffer("");
        query.append("SELECT COUNT(1) FROM CONTATOADM.MOTIVORECONTATO");
        
        Resultset resultset = pesquisar.executar(query.toString());
        
        resultset = pesquisar.executar(query.toString());
        
        if (resultset != null && resultset.getLinhas() != null) {
            if ( Integer.parseInt(resultset.getLinhas().getLinhaArray(0).getValorArray(0)) >= 10) {
                retorno = true;                
            } else {
                retorno = false;
            }            
        }           
        return retorno;
    }
    
    
    
    private boolean numeroMaximoSubmotivo(String id) {
        boolean retorno = false;

        Pesquisar pesquisar = new Pesquisar();
        StringBuffer query = new StringBuffer("");
        query.append("SELECT COUNT(1) FROM CONTATOADM.MATRIZRECONTATO WHERE IDMOTIVORECONTATO = " + id );
        Resultset resultset = pesquisar.executar(query.toString());
        
        
        if (resultset != null && resultset.getLinhas() != null) {
            if ( Integer.parseInt(resultset.getLinhas().getLinhaArray(0).getValorArray(0)) >= 10) {
                retorno = true;                
            } else {
                retorno = false;
            }            
        }           
        return retorno;
    }        
    
    

    /**
     * @jpf:action
     * @jpf:forward name="success" path="submotivo.jsp"
     */
    protected ActionForward gravarSubmotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        SubmotivoForm form = (SubmotivoForm) formParam;
        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = null;
        String novoCodigo = ConstantesCRM.SVAZIO;

        if (form.getCodigo().intValue() > 0) {
            StringBuffer update = new StringBuffer("");
            update.append("UPDATE CONTATOADM.SUBMOTIVORECONTATO SET NMSUBMOTIVORECONTATO = '").append(form.getNome()).append("' WHERE IDSUBMOTIVORECONTATO = ").append(form.getCodigo());

            Resultset resultset = pesquisar.executar(update.toString());
            if(resultset.getMsg()!=null && resultset.getMsg().length()>0) {
                request.setAttribute("msgRetorno", resultset.getMsg());
            }else {
                request.setAttribute("msgRetorno", "Submotivo atualizado com sucesso.");
            }

        } else {
        	
            if (numeroMaximoSubmotivo(String.valueOf(form.getPai()))) {
                request.setAttribute("msgRetorno", "Somente é permitido o cadastro de no máximo 10 submotivos para 1 motivo.");        
            } else {        	
        	
	            rsVO = pesquisar.executar("SELECT CONTATOADM.SUBMOTIVORECONTATOSQ.NEXTVAL FROM DUAL");
	            if (rsVO != null && rsVO.getLinhas() != null) {
	                novoCodigo = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
	            }
	
	            StringBuffer insert = new StringBuffer(ConstantesCRM.SVAZIO);
	            insert.append("INSERT INTO CONTATOADM.SUBMOTIVORECONTATO ( IDSUBMOTIVORECONTATO, NMSUBMOTIVORECONTATO, INATIVO) VALUES (" + novoCodigo + ", '" + form.getNome() + "', 1)");
	            pesquisar.executar(insert.toString());
	
	            StringBuffer insertMatriz = new StringBuffer("INSERT INTO  CONTATOADM.MATRIZRECONTATO (IDMOTIVORECONTATO, IDSUBMOTIVORECONTATO) VALUES (" + form.getPai() + ", " + novoCodigo + ")");
	            Resultset resultset = pesquisar.executar(insertMatriz.toString());
	            if(resultset.getMsg()!=null && resultset.getMsg().length()>0) {
	                request.setAttribute("msgRetorno", resultset.getMsg());
	            }else {
	                request.setAttribute("msgRetorno", "Submotivo cadastrado com sucesso.");
	            }
            }

        }

        getSubmotivoForm().setCodigo(null);
        getSubmotivoForm().setNome(ConstantesCRM.SVAZIO);
        listaSubmotivos(form.getPai());

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="submotivo.jsp"
     */
    protected ActionForward submotivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = null;

        String codigo = request.getParameter("motivo");

        StringBuffer query = new StringBuffer("");
        query.append("SELECT IDMOTIVORECONTATO, NMMOTIVORECONTATO FROM CONTATOADM.MOTIVORECONTATO WHERE IDMOTIVORECONTATO = " + codigo);
        rsVO = pesquisar.executar(query.toString());

        if (rsVO != null && rsVO.getLinhas() != null) {
            getSubmotivoForm().setPai(Long.valueOf(rsVO.getLinhas().getLinhaArray(0).getValorArray(0)));
            getSubmotivoForm().setNomePai(rsVO.getLinhas().getLinhaArray(0).getValorArray(1));
        }

        getSubmotivoForm().setCodigo(null);
        getSubmotivoForm().setNome(ConstantesCRM.SVAZIO);
        getSubmotivoForm().setSubmotivos( listaSubmotivos(Long.valueOf(codigo)));

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    protected ActionForward atualizaStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Pesquisar pesquisar = new Pesquisar();

        String codigo = request.getParameter("id");
        String status = request.getParameter("status");

        StringBuffer update = new StringBuffer(ConstantesCRM.SVAZIO);
        update.append("UPDATE CONTATOADM.MOTIVORECONTATO SET INATIVO = " + status + " WHERE IDMOTIVORECONTATO = " + codigo);
        pesquisar.executar(update.toString());

        
        StringBuffer updateSubmotivo = new StringBuffer(ConstantesCRM.SVAZIO);
        updateSubmotivo.append("UPDATE CONTATOADM.SUBMOTIVORECONTATO SET INATIVO = " + status + " WHERE IDSUBMOTIVORECONTATO IN (SELECT IDSUBMOTIVORECONTATO FROM CONTATOADM.MATRIZRECONTATO where idmotivorecontato = " + codigo +")");
        pesquisar.executar(updateSubmotivo.toString());           
        
        
        listaMotivos();

        request.setAttribute("msgRetorno", "Status do motivo atualizado com sucesso.");

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="submotivo.jsp"
     */
    protected ActionForward atualizaStatusSubmotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        SubmotivoForm form = (SubmotivoForm) formParam;
        Pesquisar pesquisar = new Pesquisar();
        Resultset rsVO = null;
        
        String codigo = request.getParameter("id");
        String status = request.getParameter("status");

        if (status.equals(ConstantesCRM.SZERO)) {       
	        StringBuffer update = new StringBuffer(ConstantesCRM.SVAZIO);
	        update.append("UPDATE CONTATOADM.SUBMOTIVORECONTATO SET INATIVO = " + status + " WHERE IDSUBMOTIVORECONTATO = " + codigo);
	        pesquisar.executar(update.toString());
	        listaSubmotivos(form.getPai());
	        request.setAttribute("msgRetorno", "Status do submotivo atualizado com sucesso.");
        } else {
        	  StringBuffer validar = new StringBuffer(ConstantesCRM.SVAZIO);
              validar.append(" SELECT MOT.INATIVO                                        ");
              validar.append(" FROM CONTATOADM.SUBMOTIVORECONTATO SUB,                   ");
              validar.append(" CONTATOADM.MOTIVORECONTATO    MOT,                        ");
              validar.append(" CONTATOADM.MATRIZRECONTATO    MAT                         ");
              validar.append(" WHERE SUB.IDSUBMOTIVORECONTATO = MAT.IDSUBMOTIVORECONTATO ");
              validar.append(" AND MAT.IDMOTIVORECONTATO      = MOT.IDMOTIVORECONTATO    ");
              validar.append(" AND SUB.IDSUBMOTIVORECONTATO   =  " + codigo);
                  
              rsVO = pesquisar.executar(validar.toString());               
              
              if (rsVO != null && rsVO.getLinhas() != null) {
                  if (rsVO.getLinhas().getLinhaArray(0).getValorArray(0) != status && rsVO.getLinhas().getLinhaArray(0).getValorArray(0).equals("0")) {
                      request.setAttribute("msgRetorno","O motivo associado ao submotivo está inativo, favor ativá-lo para que a alteração possa ser realizada.");        
                  } else {    
                      StringBuffer update = new StringBuffer("");
                      update.append("UPDATE CONTATOADM.SUBMOTIVORECONTATO SET INATIVO = " + status + " WHERE IDSUBMOTIVORECONTATO = " + codigo );
                      pesquisar.executar(update.toString());   
                                  
                      request.setAttribute("msgRetorno","Status do submotivo atualizado com sucesso.");                   
                  }
              }                    
        }
        
        getSubmotivoForm().setSubmotivos( listaSubmotivos(form.getPai()));
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    private void listaMotivos() {
        Resultset rsVO = null;
        try {
            StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
            query.append("SELECT IDMOTIVORECONTATO, NMMOTIVORECONTATO, INATIVO FROM CONTATOADM.MOTIVORECONTATO ORDER BY IDMOTIVORECONTATO");
            Pesquisar pesquisar = new Pesquisar();
            rsVO = pesquisar.executar(query.toString());

            if (rsVO != null && rsVO.getLinhas() != null) {
                Motivo[] motivos = new Motivo[rsVO.getLinhas().getLinhaArray().length];
                for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
                    Motivo motivo = new Motivo();
                    motivo.setCodigo(Long.valueOf(rsVO.getLinhas().getLinhaArray(i).getValorArray(0)));
                    motivo.setNome(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
                    motivo.setInativo(rsVO.getLinhas().getLinhaArray(i).getValorArray(2));
                    motivos[i] = motivo;
                }
                getMotivoForm().setMotivos(motivos);
            }

        } catch (Exception e) {
        }
    }

    
    private Submotivo[] listaSubmotivos(Long id){
        Resultset rsVO = null;
        Submotivo[] submotivos = null;
        try{
            StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

            query.append("SELECT A.IDSUBMOTIVORECONTATO, A.NMSUBMOTIVORECONTATO, A.INATIVO, B.IDMOTIVORECONTATO ");
            query.append("  FROM CONTATOADM.SUBMOTIVORECONTATO A, ");
            query.append("       CONTATOADM.MATRIZRECONTATO    B  ");
            query.append(" WHERE A.IDSUBMOTIVORECONTATO = B.IDSUBMOTIVORECONTATO ");
            query.append("   AND B.IDMOTIVORECONTATO    =  " + id);
            
            
            Pesquisar pesquisar = new Pesquisar();
            rsVO = pesquisar.executar(query.toString());

            if (rsVO != null && rsVO.getLinhas() != null) {
                    
                submotivos = new Submotivo[rsVO.getLinhas().getLinhaArray().length];
                
                for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
                    
                    Submotivo submotivo = new Submotivo();
                    
                    submotivo.setCodigo(Long.valueOf(rsVO.getLinhas().getLinhaArray(i).getValorArray(0)));
                    submotivo.setNome(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
                    submotivo.setInativo(rsVO.getLinhas().getLinhaArray(i).getValorArray(2));
                    submotivo.setPai(Long.valueOf(rsVO.getLinhas().getLinhaArray(i).getValorArray(3)));
                    
                    submotivos[i] = submotivo;
                    
                }                
                
            }

        }catch(Exception e){}
        
        return submotivos;
    }          
  
    public static class SubmotivoForm extends ActionForm {

        private static final long serialVersionUID = -1627148964758891824L;

        private Long              codigo;
        private Long              pai;
        private String            nome;
        private String            inativo;
        private String            nomePai;

        private Submotivo[]       submotivos;

        public Submotivo[] getSubmotivos() {
            if (submotivos == null) {
                submotivos = new Submotivo[0];
            }
            return submotivos;
        }

        public void setSubmotivos(Submotivo[] submotivos) {
            this.submotivos = submotivos;
        }

        public Long getPai() {
            return pai;
        }

        public void setPai(Long pai) {
            this.pai = pai;
        }

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getNomePai() {
            return nomePai;
        }

        public void setNomePai(String nomePai) {
            this.nomePai = nomePai;
        }

        public String getInativo() {
            return inativo;
        }

        public void setInativo(String inativo) {
            this.inativo = inativo;
        }
    }

    public static class MotivoForm extends ActionForm {

        private static final long serialVersionUID = -7240728919456160102L;

        private Long              codigo;

        private String            nome;
        private String            inativo;

        private Motivo[]          motivos;

        public Motivo[] getMotivos() {
            if (motivos == null) {
                motivos = new Motivo[0];
            }
            return motivos;
        }

        public void setMotivos(Motivo[] motivos) {
            this.motivos = motivos;
        }

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getInativo() {
            return inativo;
        }

        public void setInativo(String inativo) {
            this.inativo = inativo;
        }
    }

    public static class Submotivo {
        private String nome;
        private String inativo;
        private Long   codigo;
        private Long   pai;

        public Long getPai() {
            return pai;
        }

        public void setPai(Long pai) {
            this.pai = pai;
        }

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getInativo() {
            return inativo;
        }

        public void setInativo(String inativo) {
            this.inativo = inativo;
        }
    }

    public static class Motivo {
        private String nome;
        private String inativo;
        private Long   codigo;

        public Long getCodigo() {
            return codigo;
        }

        public void setCodigo(Long codigo) {
            this.codigo = codigo;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getInativo() {
            return inativo;
        }

        public void setInativo(String inativo) {
            this.inativo = inativo;
        }
    }

}
