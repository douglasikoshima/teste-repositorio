<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
 
<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm" />
<bean:define id="aSubCampanha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relEfetividadeActionForm.listaSubCampanha" />

<html>

    <head></head>
    <body>
        <form>
            <html:select name="Form" property="subcampanhaSelecionada">
                <option value=""></option>
                <html:options collection="aSubCampanha" property="codigo" labelProperty="nmSubCampanha"/> 
            </html:select>	
		</form>
		
		
    <SCRIPT FOR=window EVENT=onload>    
        if(parent.document.forms["relEfetividadeActionForm"])
        {    
            copiarCombo(document.forms[0].elements["subcampanhaSelecionada"], parent.document.forms["relEfetividadeActionForm"].elements["subcampanhaSelecionada"]);            
        }      
        top.frameApp.oculta_div();
        parent.redrawTable(this);      
    </script>  
    
    <script>  
    function copiarCombo(comboOrigem, comboDestino)
    {   
        var aComboOrigem  = new Array(comboOrigem);
        
        var aComboDestino = new Array(comboDestino);    
        
        
        for(i = 0; i < aComboDestino.length; i++) 
        {
            
            while(aComboDestino[i].options.length != 0)
            {
                aComboDestino[i].options.remove(0);
            }
                
            for( x = 0; x < aComboOrigem[i].options.length; x++ ) 
            {
                aComboOrigem[i].options[x].selected;
                oOption = document.createElement("OPTION");               
                aComboDestino[i].options.add(oOption);
                oOption.innerText    = aComboOrigem[i].options(x).text;
                oOption.value        = aComboOrigem[i].options(x).value;            
                oOption.selected     = aComboOrigem[i].options(x).selected;
            }  
        }
    }      
                     
    </script>  
    </body>
</html>
