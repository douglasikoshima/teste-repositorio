package br.com.vivo.fo.commons.utils.presentation.menu; 

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import javax.servlet.jsp.JspTagException;
import br.com.vivo.fo.usuario.vo.ItemMenuVODocument.ItemMenuVO;
import br.com.vivo.fo.usuario.vo.MenuVODocument.MenuVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

/**
 * taglib JMenuTag
 * criada em 22/10/2004
 * by Andrei kurak
 */
public class JMenuTag extends TagSupport { 
    
	private static final long serialVersionUID = -7075297529051304321L;
	private String width;
    private String height;
    private String id;
    //private String user;
    
    public JMenuTag(){
        
    }
    
    public int doStartTag() throws JspException {
        try{
            super.pageContext.getOut().write(this.montaMenu());
        
        }catch(IOException _ex){
            throw new JspTagException("Falha ao montar menu. causa: "+_ex.getMessage());
        
        }catch(Exception ex){
            throw new JspTagException("Falha ao montar menu. causa: "+ex.getMessage());
        }
        return (EVAL_PAGE);
    }   

    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
    
    public void setWidth(String width){
        this.width = width;   
    }
    
    public void setHeight(String height){
        this.height = height;   
    }
    
    public void setId(String id){
        this.id =id;   
    }

    public String getWidth(){
        return this.width;
    }
    
    public String getHeight(){
        return this.height;
    }
    
    public String getId(){
        return this.id;
    }
    
    public String montaMenu() throws Exception{                
        
    	MenuVO menuVO = (MenuVO) this.pageContext.getSession().getAttribute(ConstantesCRM.USUARIO_MENU);
    	
    	if(menuVO==null){
    		menuVO = MenuVO.Factory.newInstance();
    		//throw new Exception("Nenhum Menu do Usuário encontrado.");
    	}
    	
    	StringBuffer menu = new StringBuffer();        
        menu.append(montaHeader());      
        
        for(int i=0;i<menuVO.sizeOfItemMenuVOArray();i++){            
            ItemMenuVO item = menuVO.getItemMenuVOArray(i);
            menu.append(startMenu(item));
            listItensMenu(item,menu);
            
            if((i+1)<menuVO.sizeOfItemMenuVOArray()){
                menu.append(endMenu(true));
            
            }else{
                menu.append(endMenu(false));
            } 
        }
        
        menu.append(montaFooter());
        
        return menu.toString();      
    }
    
    private String startMenu(ItemMenuVO itemMenuVO){
        StringBuffer sb = new StringBuffer();
        sb.append("\n<td width=\"130px\" class=\"menuItem\" style=\"cursor:hand;\" title=\"");
        sb.append(itemMenuVO.getNmMenu());
        sb.append("\"");
        sb.append(" onclick=\"showMenu('menuRaiz");
        sb.append(itemMenuVO.getIdItemMenu());
        sb.append("', 'menuItemHolder');ifrMenu(true,'menuRaiz");
        sb.append(itemMenuVO.getIdItemMenu()).append("');");
        sb.append("\" background='"+pageContext.getServletContext().getContextPath()+"/resources/images/menu_bg.gif'");
        sb.append(" style='cursor:hand;'>");
        sb.append(itemMenuVO.getNmMenu());
        return sb.toString();
    }

    private String endMenu(boolean next){
        if(next){
            return "<td><img src='"+pageContext.getServletContext().getContextPath()+"/resources/images/menu_left.gif'></td></td>";
        }else{
            return "</td>";
        }
    }
    
    private void listItensMenu(ItemMenuVO item,StringBuffer buffer){   
        ItemMenuVO[] itens = item.getItemMenuVOArray();
        if(itens.length == 0){
                return;   
        }
        buffer.append("\n<span id=\"menuRaiz");
        buffer.append(item.getIdItemMenu());
        buffer.append("\" class=\"menuItemHolder\" style=\"z-index:99;display:none;\" menuPai=\"none\"><table>");
        
        for(int i=0;i < itens.length;i++){
            StringBuffer sbNome = new StringBuffer(itens[i].getNmMenu());
            //String nome = itens[i].getNmMenu();
            
            StringBuffer sbSubmenu = new StringBuffer("menuRaiz");
            sbSubmenu.append(itens[i].getIdItemMenu());
            //String idSubmenu = "menuRaiz"+itens[i].getIdItemMenu();
            
            StringBuffer sbIdHide = new StringBuffer("menuRaiz");
            sbIdHide.append(item.getIdItemMenu());
            //String idHide = "menuRaiz"+(item.getIdItemMenu());
            
            if(itens[i].getItemMenuVOArray().length > 0){
                sbNome.append(" >");
                //nome += " >"; 
                buffer.append("\n<tr><td nowrap menuAssociado=\"");
                buffer.append(sbSubmenu.toString());
                buffer.append("\" title=\"");
                buffer.append(itens[i].getDsHint());
                buffer.append("\"");
                buffer.append(" onclick=\"doShowSubMenu('");
                buffer.append(sbSubmenu.toString());
                buffer.append("','menuHover');ifrSubMenu(true,'").append(sbSubmenu.toString()).append("');");
                buffer.append("\" onmouseover=\"setStyle('menuItem');\"");
                buffer.append(" onmouseout=\"setStyle('menu');\" class=\"menuItem\" title=\"");
                buffer.append(itens[i].getDsHint());
                buffer.append("\">");
                buffer.append(sbNome.toString());
                buffer.append("</td></tr>");
            }else{
			    String url = "";
                if(itens[i].getSistemaPaginaVO() != null){
                    url = itens[i].getSistemaPaginaVO().getNmURL();
                }
                buffer.append("\n<tr><td nowrap class=\"menuItem\" title=\"");
                buffer.append(itens[i].getDsHint());
                buffer.append("\"><a href=\"");
                buffer.append(url);
                buffer.append("\"");
                buffer.append(" class=\"menuItem\" onclick=\"doHideAllSubMenu('");
                buffer.append(sbIdHide.toString());
                buffer.append("');ifrSubMenu(false,'").append(sbIdHide.toString());
                buffer.append("');ifrMenu(false,'");
                buffer.append(sbSubmenu.toString()).append("')");
                buffer.append(";\">");
                buffer.append(sbNome.toString());
                buffer.append("</a></td></tr>");
            }
        }
        buffer.append("\n</table></span>");  
        for(int i=0;i<itens.length;i++){
            listItensMenu(itens[i],buffer);
        }
    }
  
    private String montaHeader(){
        String header = new String("\n<table border='0' cellpadding='0' cellspacing='0' width='790px'><tr><td><table width='790' id='mnMin' border='0' cellpadding='0' cellspacing='0' style='display:none;'><tr><td width='17'><img id='menu_button_open' style='cursor:hand;' hspace='4' src='"+pageContext.getServletContext().getContextPath()+"/resources/images/menu_small_bt.gif' onclick='mudarMenu(mnMin, mnMax);'></td><td bgcolor='006699' onmouseover='mudarMenuMin(mnMin, mnMax);'><img style='cursor:hand;' src='"+pageContext.getServletContext().getContextPath()+"/resources/images/menu_small_txt.gif'></td></tr></table><table id='mnMax' border='0' cellpadding='0' cellspacing='0' style='display:;' onmouseout='mudarMenuMax(mnMin, mnMax);'><tr><td><img id='menu_button_close' style='cursor:hand;' onclick='mudarMenu(mnMin, mnMax);' src='"+pageContext.getServletContext().getContextPath()+"/resources/images/menu_bt.gif'></td>");
        return header;
    }
    private String montaFooter(){
        String footer = new String("\n<td><img src='"+pageContext.getServletContext().getContextPath()+"/resources/images/menu_right.gif'></td></tr></table></td></tr></table>");   
        return footer;
    }
}
