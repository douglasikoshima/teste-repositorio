package admsistemas.admArvoreContato.workflow.abaEntrevista;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaContainerVODocument.AdmArvoreBaixaContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreBaixaPesquisaVODocument.AdmArvoreBaixaPesquisaVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AbaEntrevistaController extends AbstractAction {

	@EJB
    private br.com.vivo.fo.ctrls.admsistemas.entrevista.Entrevista controlArvoreBaixaAssociada;

	@EJB
    private br.com.vivo.fo.ctrls.admsistemas.configArvoreBaixa.ArvoreBaixa controlArvoreBaixa;

    protected global.Global globalApp = new global.Global();

    private FormArvoreBaixa formArvoreBaixa;
    private FormArvoreBaixaAssociada formArvoreBaixaAssociada;

    private String fonteArvoreBaixa;
    private String fonteArvoreBaixaAssociada;
    private static final String EXCLUIR 		= "0";
	private static final String INCLUIR 		= "1";
    private static final String FOLHA    		= "1";
    private static final String DISPONIVEL 		= "1";

    private static  transient Logger log = new Logger("admsistemas");

    @Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("excluirNo".equals(mapping.getParameter())) {
			return excluirNo(mapping, form, request, response);
		} else if ("inserirNo".equals(mapping.getParameter())) {
			return inserirNo(mapping, form, request, response);
		} else if ("montaArvoreParte".equals(mapping.getParameter())) {
			return montaArvoreParte(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="vincularEntrevista.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
    {
        log.debug("AbaEntrevistaController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreBaixa form = (FormArvoreBaixa) formParam;
        try
        {
            formArvoreBaixa = new FormArvoreBaixa();
            formArvoreBaixaAssociada = new FormArvoreBaixaAssociada();
            /////formArvoreBaixa.setIdContato(request.getParameter("idContato"));    
            /////formArvoreBaixaAssociada.setIdContato(request.getParameter("idContato"));
            
            if (request.getParameter("idContato")!=null){
                
                log.debug("AbaEntrevistaController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) idContato --> " + request.getParameter("idContato"));
                formArvoreBaixa.setIdContato(request.getParameter("idContato"));
            }
            else
                formArvoreBaixa.setIdContato(form.getIdContato());
                        
            formArvoreBaixaAssociada.setIdContato(formArvoreBaixa.getIdContato());
            
            fonteArvoreBaixa = carregaArvoreBaixa(request);
            fonteArvoreBaixaAssociada = carregaArvoreBaixaAssociada(request.getParameter("idContato"),request);
            request.setAttribute("arvoreBaixa",fonteArvoreBaixa);
            if (request.getSession().getAttribute("arvoreBaixaAssociada")!=null)
            request.getSession().removeAttribute("arvoreBaixaAssociada");
            request.getSession().setAttribute("arvoreBaixaAssociada",fonteArvoreBaixaAssociada);

        } catch(TuxedoWarningException twe) 
        {
            log.warn("AbaEntrevistaController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
            request.setAttribute("msgError",twe.getXmlHeader().getStatusText());
        
        }catch(Exception e)
        {
            log.error("AbaEntrevistaController:begin"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
            
            // Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
            request.setAttribute(ConstantesCRM.SFORMERROR,formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
        }        

        
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
        
    }
    
    protected String carregaArvoreBaixa(HttpServletRequest request)throws Exception
    {

        log.debug("AbaEntrevistaController:carregaArvoreBaixa"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

    /*********************************************
     * MONTA ARVORE BAIXA
     ********************************************/

           request.setCharacterEncoding(ConstantesCRM.SISO);
           
           //AdmIdBaixaVO admIdBaixaVO = AdmIdBaixaVO.Factory.newInstance();

           formArvoreBaixa.setArvoreBaixa(chamaServicoArvoreBaixa(request));
           formArvoreBaixa.setAdmFolhaBaixaVO(formArvoreBaixa.getAdmFolhaBaixaVO());

     
           StringBuffer script = new StringBuffer();
           StringBuffer node  = new StringBuffer();
           String folder= ConstantesCRM.SVAZIO;
    
           script.append("if (document.getElementById) { var tree = new WebFXTree('")
                .append(StringUtilStatic.escapaComillasJS(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getNmBaixa()))
                .append("',\"Javascript:capturaParametrosArvoreBaixa('")
                .append(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getIdBaixa()).append("','")
                .append(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getInFolha())
                .append("');")
                .append("\",'','');");
                            
            for( int i = 0; i < formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray().length;i++ ) 
            {
                boolean inFolha = false;
                
                    if(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals(FOLHA))
                    {
                            inFolha = true;
                            folder = "',\"Javascript:capturaParametrosArvoreBaixa('";
                    }else
                            folder = "',\"Javascript:expandirNoArvoreBaixa('";

                // Se Folha.                
                if (inFolha) 
                {
                    node.append("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa()))
                        .append(folder)
                        .append(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','")
                        .append(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha());
                    
                    if(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals(DISPONIVEL))
                        node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");                        
                    else
                        node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
    
                // Se PASTA.
                //**************************************
                } else 
                {
                    node.append("tmpArvore = new WebFXTreeItem('"+StringUtilStatic.escapaComillasJS(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa()))
                        .append(folder)
                        .append(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','")
                        .append(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha());
                    
                    if(formArvoreBaixa.getArvoreBaixa().getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals(DISPONIVEL))
                    {
                        node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");                        
                    }else
                       node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
    
                }                
                
                script.append(node)
                .append("tree.add(tmpArvore);\n\n");
    
            }
            script.append("\ndocument.write(tree);}\n\n");
                                                                 
            return String.valueOf(script);
        
    }
    
    protected AdmArvoreBaixaContainerVO chamaServicoArvoreBaixa(HttpServletRequest request)throws Exception 
    {
        log.debug("AbaEntrevistaController:chamaServicoArvoreBaixa"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
        
        AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = AdmArvoreBaixaContainerVO.Factory.newInstance();

        AdmIdBaixaVO admIdBaixaVO = AdmIdBaixaVO.Factory.newInstance();
        admIdBaixaVO.setIdBaixa(ConstantesCRM.SVAZIO);
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        return admArvoreBaixaContainerVO = controlArvoreBaixa.carregaArvoreBaixa(admIdBaixaVO,user);
    }
    
    protected String montaArvoreBaixa(AdmFolhaBaixaVO itens, String tree, String funcao, String funcao2, HttpServletRequest request){
        
        log.debug("AbaEntrevistaController:montaArvoreBaixa"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");

        String node=ConstantesCRM.SVAZIO;
        if( itens.getAdmFolhaBaixaVOArray().length == 0 ){
            return ConstantesCRM.SVAZIO;
        }else
        {   
            for( int x = 0; x < itens.getAdmFolhaBaixaVOArray().length;x++ )
            {
                node = node + "var " + tree + String.valueOf(x)
                            + " = new WebFXTreeItem('" 
                            + StringUtilStatic.escapaComillasJS(itens.getAdmFolhaBaixaVOArray(x).getNmBaixa());
                            
                if(itens.getAdmFolhaBaixaVOArray(x).getInFolha().trim().equals("1"))
                    node = node + "',\"Javascript:document.getElementById('btnIncluir').style.style = 'block';" + funcao2 + "('";
                else
                    node = node + "',\"Javascript:document.getElementById('btnIncluir').style.style = 'none';" + funcao + "('";
                
                node = node + itens.getAdmFolhaBaixaVOArray(x).getIdBaixa() + "','" + itens.getAdmFolhaBaixaVOArray(x).getInFolha();

                    if(itens.getAdmFolhaBaixaVOArray(x).getInFolha().trim().equals(ConstantesCRM.SONE))
                        if(itens.getAdmFolhaBaixaVOArray(x).getInDisponibilidade().trim().equals("1"))
                            node = node  + "');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');";                        
                        else
                            node = node  + "');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');";                        
                    else
                        if(itens.getAdmFolhaBaixaVOArray(x).getInDisponibilidade().trim().equals(ConstantesCRM.SONE))
                            node = node + "');\",'','/FrontOfficeWeb/resources/images/folder.gif','/FrontOfficeWeb/resources/images/folder.gif');";                        
                        else
                           node = node + "');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');";
                                    
                node = node + montaArvoreBaixa( itens.getAdmFolhaBaixaVOArray(x),tree+ String.valueOf(x),funcao,funcao2 , request);
                node = node + tree + ".add(" + tree+ String.valueOf(x) + ");\n";
            }
        }
        return node;   
    }
    
    protected String carregaArvoreBaixaAssociada(String idContato, HttpServletRequest request)throws Exception{
        
        formArvoreBaixaAssociada.setArvoreBaixaAssociada(chamaServicoArvoreBaixaAssociada(idContato, request));
        formArvoreBaixaAssociada.setAdmFolhaBaixaVO(formArvoreBaixa.getAdmFolhaBaixaVO());
            
        //String script ="if (document.getElementById) {var tree = new WebFXTree('Menu');tree.setBehavior('classic');";
        String script ="if (document.getElementById) {var tree = new WebFXTree('" 
                        + StringUtilStatic.escapaComillasJS(formArvoreBaixaAssociada.getArvoreBaixaAssociada().getAdmFolhaBaixaVO().getNmBaixa())
                        + "',\"Javascript:document.getElementById('btnIncluir').style.visibility = 'hidden';capturaParametrosArvoreBaixaAssociada('"
                        + formArvoreBaixaAssociada.getArvoreBaixaAssociada().getAdmFolhaBaixaVO().getIdBaixa() + "','"
                        + formArvoreBaixaAssociada.getArvoreBaixaAssociada().getAdmFolhaBaixaVO().getInFolha()
                        + "');\",'classic');";
        
        // ############# ALTERADO!
        script = script + montaArvoreBaixa(formArvoreBaixaAssociada.getArvoreBaixaAssociada().getAdmFolhaBaixaVO(),"tree","expandirNoArvoreBaixaAssociada","capturaParametrosArvoreBaixaAssociada", request) + "document.write(tree);}";  
        
        return script;
        
    }
    
    protected AdmArvoreBaixaContainerVO chamaServicoArvoreBaixaAssociada(String idContato, HttpServletRequest request)throws Exception {
        
        AdmArvoreBaixaContainerVO admArvoreBaixaContainerVO = AdmArvoreBaixaContainerVO.Factory.newInstance();
        AdmArvoreBaixaPesquisaVO admArvoreBaixaPesquisaVO = AdmArvoreBaixaPesquisaVO.Factory.newInstance();
        admArvoreBaixaPesquisaVO.setIdContato(idContato);
        admArvoreBaixaPesquisaVO.setIdBaixa(ConstantesCRM.SVAZIO);
        
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        return admArvoreBaixaContainerVO = controlArvoreBaixaAssociada.carregaArvoreBaixaAssociada(admArvoreBaixaPesquisaVO,user);
    }
    
    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward excluirNo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
    {
        log.debug("AbaEntrevistaController:excluirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
        FormArvoreBaixaAssociada form = (FormArvoreBaixaAssociada) formParam;
        try
        {
            AdmArvoreBaixaPesquisaVO admArvoreBaixaPesquisaVO = AdmArvoreBaixaPesquisaVO.Factory.newInstance();
            admArvoreBaixaPesquisaVO.setIdBaixa(form.getIdBaixa());
            admArvoreBaixaPesquisaVO.setIdContato(formArvoreBaixa.getIdContato());
            admArvoreBaixaPesquisaVO.setInInclui(EXCLUIR);

            formArvoreBaixa.setIdBaixa(form.getIdBaixa());
            log.debug("AbaEntrevistaController:excluirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) idBaixa--> "+form.getIdBaixa());

            formArvoreBaixa.setIdContato(formArvoreBaixa.getIdContato());
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            controlArvoreBaixaAssociada.removerItemArvoreBaixa(admArvoreBaixaPesquisaVO,user);

            request.setAttribute("msgError",ConstantesCRM.SSucesso);

        } catch(TuxedoWarningException twe) 
        {        
            log.warn("AbaEntrevistaController:excluirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
            request.setAttribute("msgError",twe.getXmlHeader().getStatusText());
        
        }catch(Exception e)
        {
            log.error("AbaEntrevistaController:excluirNo"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
            
            // Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request);
            formError.setTarget(ConstantesCRM.FRAMEAPP);            
            request.setAttribute(ConstantesCRM.SFORMERROR,formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
        }        


        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward inserirNo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
    {
        log.debug("AbaEntrevistaController:inserirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormArvoreBaixa form = (FormArvoreBaixa) formParam;
        try
        {
            AdmArvoreBaixaPesquisaVO admArvoreBaixaPesquisaVO = AdmArvoreBaixaPesquisaVO.Factory.newInstance();

            // LOG
            log.debug("AbaEntrevistaController:inserirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> idBaixa = " + form.getIdBaixa());
            log.debug("AbaEntrevistaController:inserirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> idContato = " + formArvoreBaixa.getIdContato());

            admArvoreBaixaPesquisaVO.setIdBaixa(form.getIdBaixa());
            admArvoreBaixaPesquisaVO.setIdContato(formArvoreBaixa.getIdContato());
            admArvoreBaixaPesquisaVO.setInInclui(INCLUIR);
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            controlArvoreBaixaAssociada.adicionarItemArvoreBaixa(admArvoreBaixaPesquisaVO,user);

            request.setAttribute("msgError",ConstantesCRM.SSucesso);

        } catch(TuxedoWarningException twe) 
        {
        
            log.warn("AbaEntrevistaController:inserirNo"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
            request.setAttribute("msgError",twe.getXmlHeader().getStatusText());
            
        }catch(Exception e)
        {
            log.error("AbaEntrevistaController:inserirNo"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

            // Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request);
            request.setAttribute(ConstantesCRM.SFORMERROR,formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
        }        

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="iframeEntrevistaBaixa.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    public ActionForward montaArvoreParte(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) 
    {
        log.debug("AbaEntrevistaController:montaArvoreParte"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
        FormArvoreBaixa form = (FormArvoreBaixa) formParam;
        try
        {
            request.setCharacterEncoding(ConstantesCRM.SISO);

            AdmIdBaixaVO admIdBaixaVO = AdmIdBaixaVO.Factory.newInstance();
            admIdBaixaVO.setIdBaixa(form.getIdBaixa());
            
            AdmArvoreBaixaPesquisaVO admArvoreBaixaPesquisaVO = AdmArvoreBaixaPesquisaVO.Factory.newInstance();
            admArvoreBaixaPesquisaVO.setIdBaixa(form.getIdBaixa());
            admArvoreBaixaPesquisaVO.setIdContato(form.getIdContato());
    
            AdmArvoreBaixaContainerVO admArvoreBaixaVO = AdmArvoreBaixaContainerVO.Factory.newInstance();
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            
            String isAssociada = (request.getParameter("isAssociada") != null)?request.getParameter("isAssociada"):"";
            
            if(isAssociada.equals("true"))
            {
                admArvoreBaixaVO = controlArvoreBaixaAssociada.carregaArvoreBaixaAssociada(admArvoreBaixaPesquisaVO,user);
            }
            else
            {
                admArvoreBaixaVO = controlArvoreBaixa.carregaArvoreBaixa(admIdBaixaVO,user);        
            }
            
            StringBuffer script = new StringBuffer();
            StringBuffer node = new StringBuffer();
            String folder = ConstantesCRM.SVAZIO;
                            
            for( int i = 0; i < admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray().length;i++ ) {
        
                boolean inFolha = false;
                
                    if(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals(FOLHA))
                    {
                            inFolha = true;
                            if(isAssociada.equals("true"))
                                folder = "',\"Javascript:capturaParametrosArvoreBaixaAssociada('";
                            else
                                folder = "',\"Javascript:capturaParametrosArvoreBaixa('";
                    }else
                    {
                            if(isAssociada.equals("true"))
                                folder = "',\"Javascript:expandirNoArvoreBaixaAssociada('";
                            else
                                folder = "',\"Javascript:expandirNoArvoreBaixa('";
                    }
                    
                
                if(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha().trim().equals(FOLHA))
                    inFolha = true;
                
                if (inFolha) 
                {
                    node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa()))
                        .append(folder)
                        .append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','")
                        .append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha());
                    
                    if(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals(DISPONIVEL))
                        node.append( "');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");                        
                    else
                        node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');"); 
    
                } else 
                {
                    node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getNmBaixa()))
                        .append(folder)
                        .append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getIdBaixa()).append("','")
                        .append(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInFolha());
    
                    if(admArvoreBaixaVO.getAdmFolhaBaixaVO().getAdmFolhaBaixaVOArray(i).getInDisponibilidade().trim().equals("1"))
                        node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
                    else
                       node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');"); 
    
                }                
                script.append(node)
                .append("parent.tree.getSelected().add(parent.tmpArvore);\n\n");
    
            }
            
            script.append("parent.tree.getSelected().setAddEnabled(false);\n\n")
                  .append("parent.tree.getSelected().expand()\n\n");
                                                                 
            request.setAttribute("arvoreBaixa",String.valueOf(script));

        } catch(TuxedoWarningException twe) 
        {
            log.warn("AbaEntrevistaController:montaArvoreParte"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
            request.setAttribute("msgError",twe.getXmlHeader().getStatusText());
        
        }catch(Exception e)
        {
            log.error("AbaEntrevistaController:montaArvoreParte"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

            // Monta URL de retorno e desvia para o erro
            FormError formError = globalApp.buildFormError(e, request);            
            request.setAttribute(ConstantesCRM.SFORMERROR,formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SERROR);
        }        
        
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
    }


    public static class FormArvoreBaixa extends ActionForm
    {
        private String idBaixa;

        private AdmFolhaBaixaVO[] admFolhaBaixaVO;

        private AdmArvoreBaixaContainerVO arvoreBaixa;

        private String idContato;

        public void setIdContato(String idContato)
        {
            this.idContato = idContato;
        }

        public String getIdContato()
        {
            return this.idContato;
        }

        public void setArvoreBaixa(AdmArvoreBaixaContainerVO arvoreBaixa)
        {
            this.arvoreBaixa = arvoreBaixa;
        }

        public AdmArvoreBaixaContainerVO getArvoreBaixa()
        {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null. This type doesn't have
            // a default constructor, so Workshop cannot initialize it for you.

            // TODO: Initialize arvoreBaixa if it is null.
            //if(this.arvoreBaixa == null)
            //{
            //    this.arvoreBaixa = new AdmArvoreBaixaContainerVO(?);
            //}

            return this.arvoreBaixa;
        }

        public void setAdmFolhaBaixaVO(AdmFolhaBaixaVO[] admFolhaBaixaVO)
        {
            this.admFolhaBaixaVO = admFolhaBaixaVO;
        }

        public AdmFolhaBaixaVO[] getAdmFolhaBaixaVO()
        {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null. This type doesn't have
            // a default constructor, so Workshop cannot initialize it for you.

            // TODO: Initialize admFolhaBaixaVO if it is null or length == 0.
            //if(this.admFolhaBaixaVO == null || this.admFolhaBaixaVO.length == 0)
            //{
            //    this.admFolhaBaixaVO = new AdmFolhaBaixaVO[1];
            //    this.admFolhaBaixaVO[0] = new AdmFolhaBaixaVO(?);
            //}

            return this.admFolhaBaixaVO;
        }

        public void setIdBaixa(String idBaixa)
        {
            this.idBaixa = idBaixa;
        }

        public String getIdBaixa()
        {
            return this.idBaixa;
        }
    }

    /**
     * FormData get and set methods may be overwritten by the Form Bean editor.
     */
    public static class FormArvoreBaixaAssociada extends ActionForm
    {
        private String fonte;

        private String idBaixa;

        private AdmFolhaBaixaVO[] admFolhaBaixaVO;

        private AdmArvoreBaixaContainerVO arvoreBaixaAssociada;

        private String idContato;

        public void setIdContato(String idContato)
        {
            this.idContato = idContato;
        }

        public String getIdContato()
        {
            return this.idContato;
        }

        public void setArvoreBaixaAssociada(AdmArvoreBaixaContainerVO arvoreBaixaAssociada)
        {
            this.arvoreBaixaAssociada = arvoreBaixaAssociada;
        }

        public AdmArvoreBaixaContainerVO getArvoreBaixaAssociada()
        {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null. This type doesn't have
            // a default constructor, so Workshop cannot initialize it for you.

            // TODO: Initialize arvoreBaixaAssociada if it is null.
            //if(this.arvoreBaixaAssociada == null)
            //{
            //    this.arvoreBaixaAssociada = new AdmArvoreBaixaContainerVO(?);
            //}

            return this.arvoreBaixaAssociada;
        }

        public void setAdmFolhaBaixaVO(AdmFolhaBaixaVO[] admFolhaBaixaVO)
        {
            this.admFolhaBaixaVO = admFolhaBaixaVO;
        }

        public AdmFolhaBaixaVO[] getAdmFolhaBaixaVO()
        {
            // For data binding to be able to post data back, complex types and
            // arrays must be initialized to be non-null. This type doesn't have
            // a default constructor, so Workshop cannot initialize it for you.

            // TODO: Initialize admFolhaBaixaVO if it is null or length == 0.
            //if(this.admFolhaBaixaVO == null || this.admFolhaBaixaVO.length == 0)
            //{
            //    this.admFolhaBaixaVO = new AdmFolhaBaixaVO[1];
            //    this.admFolhaBaixaVO[0] = new AdmFolhaBaixaVO(?);
            //}

            return this.admFolhaBaixaVO;
        }

        public void setIdBaixa(String idBaixa)
        {
            this.idBaixa = idBaixa;
        }

        public String getIdBaixa()
        {
            return this.idBaixa;
        }

        public void setFonte(String fonte)
        {
            this.fonte = fonte;
        }

        public String getFonte()
        {
            return this.fonte;
        }
    }
    public FormArvoreBaixa getFormArvoreBaixa() {
        return this.formArvoreBaixa;
    }
    public FormArvoreBaixaAssociada getFormArvoreBaixaAssociada() {
        return this.formArvoreBaixaAssociada;
    }
    
    protected boolean alwaysTrackPreviousPage()
    {
        return true;
    }
    
    protected boolean alwaysTrackPreviousAction()
    {
        return true;
    }
    
}
