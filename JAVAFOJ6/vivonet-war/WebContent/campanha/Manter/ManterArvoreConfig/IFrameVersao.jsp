<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="aVersao" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm.listaVersao" />
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm" />

<html>

    <head>
    <SCRIPT FOR=window EVENT=onload>
    
    if(parent.document.forms["scriptCampanhaActionForm"])
    {    
        var aOptComponent = new Array(document.forms[0].elements["versaoSelecionada"]);
        
        var aOptComponentsParent = new Array(parent.document.forms["scriptCampanhaActionForm"].elements["versaoSelecionada"]);                                      
                
        
        for(i = 0; i < aOptComponentsParent.length; i++) 
        {
        
            while(aOptComponentsParent[i].options.length != 0)
            {
                aOptComponentsParent[i].options.remove(0);
            }
            
            for( x = 0; x < aOptComponent[i].options.length; x++ ) 
            {
                aOptComponent[i].options[x].selected;
                oOption = parent.document.createElement("OPTION");               
                aOptComponentsParent[i].options.add(oOption);
                oOption.innerText = aOptComponent[i].options(x).text;
                oOption.value     = aOptComponent[i].options(x).value;            
                oOption.selected     = aOptComponent[i].options(x).selected;
            }  
        }    
    }   
    parent.oculta_div();                                
    </script>      
    </head>
    <body>
        <form action="obterVersao">
            <html:select name="form" property="versaoSelecionada" size="1" style="display:none">
                <html:options collection="aVersao" property="codigo" labelProperty="descricao"/> 
            </html:select>
			</form>		    
    </body>
</html>