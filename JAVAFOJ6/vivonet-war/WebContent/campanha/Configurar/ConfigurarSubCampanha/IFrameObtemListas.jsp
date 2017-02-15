<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm" />
<bean:define id="aMotivoDisp"	   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm.motivoDisp" />
<bean:define id="aSubMotivoDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="configurarSubCampanhaActionForm.subMotivoDisp" />

<html>

    <head></head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cam_ifoblis_verpagina">
        <form action="ObtemCampanhaListaAction.do" name="configurarSubCampanhaActionForm" method="post">
			<html:select name="Form" property="motivoDispSelecionado" size="1" style="width=100px;height=10px" onchange="ConsultaIFrameVersao();">
				<html:option value=""></html:option>
				<html:options collection="aMotivoDisp" property="codigo" labelProperty="sigla"/> 
			</html:select>	
			
														
			<html:select name="Form" property="subMotivoDispSelecionado" size="1" style="width=100px;height=10px">
					<html:option value=""></html:option>
					<html:options collection="aSubMotivoDisp" property="codigo" labelProperty="sigla"/> 
			</html:select>	
	
		</form>
		
		
		 <SCRIPT FOR=window EVENT=onload>
		 
	 
		
				var aOptComponent = new Array(document.forms[0].elements["motivoDispSelecionado"],document.forms[0].elements["subMotivoDispSelecionado"]);
    					  
				var aOptComponentsParent = new Array(parent.document.forms["configurarSubCampanhaActionForm"].elements["motivoDispSelecionado"],
													 parent.document.forms["configurarSubCampanhaActionForm"].elements["subMotivoDispSelecionado"]);                                      
													 
        
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
                
                parent.parent.parent.oculta_div();                                    
	  
    </SCRIPT>   
    </acesso:controlHiddenItem>    
    </body>
</html>