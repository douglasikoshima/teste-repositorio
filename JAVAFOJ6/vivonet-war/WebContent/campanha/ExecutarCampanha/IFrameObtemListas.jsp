<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   

<acesso:controlInitEnv/>
<bean:define id="Form"             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm" />
<bean:define id="aMotivoDisp"	   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm.motivoDisp" />
<bean:define id="aSubMotivoDisp"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="obtemCampanhaListaActionForm.subMotivoDisp" />


<html>

    <head>
    <script language="javascript" src="/FrontOfficeWeb/resources/scripts/executarCampanha.js"></script>                
    </head>
    <body>
    <acesso:controlHiddenItem nomeIdentificador="cam_ifroblist_verpagina">
        <form action="obtemCampanhaListaAction.do" method="post" name="obtemCampanhaListaActionForm">
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
		
        iFrameObtemListas_copiarCombo();
        top.frameApp.oculta_div();
    </script>
    </acesso:controlHiddenItem>      
    </body>
</html>
