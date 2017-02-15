<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

   

<acesso:controlInitEnv/>
<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configuraPrioridadeActionForm" />
<bean:define id="aCanal"		name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configuraPrioridadeActionForm.listaCanal" />

<html>

    <head></head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cam_iolis_verpagina">
        <form action="ObtemListaAction.do" name="configuraPrioridadeActionForm" method="post">
			<html:select name="Form" property="listaCanalSelecionado" size="1" style="width=400px;height=10px">
				<html:option value=""></html:option>
				<html:options collection="aCanal" property="codigo" labelProperty="descricao"/> 
			</html:select>		
	
		</form>
		
		
		 <script>
		 
	 
		
				var aOptComponent = new Array(document.forms[0].elements["listaCanalSelecionado"]);
				var aOptComponentsParent = new Array(parent.document.forms["configuraPrioridadeActionForm"].elements["listaCanalSelecionado"]);                                      
						
				 
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
                
                parent.parent.oculta_div();          
	  
    </script> 
       
    </acesso:controlHiddenItem>      
    </body>
</html>
