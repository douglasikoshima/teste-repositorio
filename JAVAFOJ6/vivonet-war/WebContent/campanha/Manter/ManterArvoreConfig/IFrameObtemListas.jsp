<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

   
<acesso:controlInitEnv/>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm" />
<bean:define id="aSubCampanha"	name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="scriptCampanhaActionForm.listaSubCampanha" />

<html>

    <head></head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cam_iolmanu_verpagina">
    
    <form action="ObtemListaAction" method="post">
			<html:select name="Form" property="subCampanhaSelecionada" size="1" style="width=200px;height=10px">
				<html:option value=""></html:option>
				<html:options collection="aSubCampanha" property="codigo" labelProperty="nmSubCampanha"/> 
			</html:select>	
	
		</form>
		
		
		 <script>
		 
        if(parent.document.forms["scriptCampanhaActionForm"]) {
            var aOptComponent = new Array(document.forms[0].elements["subCampanhaSelecionada"]);
            var aOptComponentsParent = new Array(parent.document.forms["scriptCampanhaActionForm"].elements["subCampanhaSelecionada"]);                                      
            for(i = 0; i < aOptComponentsParent.length; i++) 
            {
        
                while(aOptComponentsParent[i].options.length != 0)
                    aOptComponentsParent[i].options.remove(0);
        
                for( x = 0; x < aOptComponent[i].options.length; x++ ) {
                    aOptComponent[i].options[x].selected;
                    oOption = parent.document.createElement("OPTION");               
                    aOptComponentsParent[i].options.add(oOption);
                    oOption.innerText = aOptComponent[i].options(x).text;
                    oOption.value     = aOptComponent[i].options(x).value;            
                    oOption.selected     = aOptComponent[i].options(x).selected;
                }  
            }
            
        }                                   
    </script>  
    <SCRIPT FOR=window EVENT=onload>
        parent.oculta_div();
    </script> 
    </acesso:controlHiddenItem>    
    </body>
</html>