<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="Form"		 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="questionario" />
<bean:define id="Respostas"	 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="questionario.respostas"/>
<bean:define id="pageFlow"	 name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"/>

<html>
   <head>
        <title>
        </title>
        <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/questionario.js" charset="ISO-8859-1"></script>    
        
        <%@ include file="includeJS.jspf"%>	
        
		 <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
			<!--
			parent.parent.oculta_div();
			-->
		</SCRIPT>	
    </head>
    <acesso:controlHiddenItem nomeIdentificador="que_qfa_verpagina">
    <form action="factoryRespostaForward.do" name="factoryRespostaForward" method="factoryRespostaForward">

    <vivo:quadro id="qdPerg" width="760" height="320" label="Script">
	<table height="100%">
        <tr valign="top" >
            <td >    
        <table border="0" cellpadding="0" cellspacing="0" width="740" align="left">
            <tr>
                <td colspan="3">&nbsp;</td>
            </tr>
            <tr class="corpo"> 					
                <td class="tblEstatica_campoNome" align="left" width="100%" colspan="3" valign="middle">
                    <netui:label value="{pageContext.Form.dsScriptPergunta}" />
                </td>
            </tr>
            <tr>
                <td colspan="3">&nbsp;</td>
            </tr>
            <html:hidden name="Form" property="inObrigatoria" />
            <logic:equal name="Form" property="sgTipoApresentacaoPergunta" value="RAD" >												 
                 <tr>		
                    <td align="left" colspan="2">
                        <logic:iterate name="Form" property="respostas" id="Respostas" indexId="index" >
                            <bean:define name="Respostas" id="idResposta" property="idResposta"/>
                             <tr>		
                                <td align="left" colspan="2">
                                    <html:radio name="Form"  style="border:none;background-color:#E3ECF4;" property="indiceResposta" value='<%="" + idResposta + ""%>'/>
                                    
                                    <bean:write name="Respostas" property="dsScriptResposta"/>
                                </td>
                            </tr>
                        </logic:iterate>                                                                        												
                    </td>
                </tr>														
            </logic:equal>
            
            <logic:equal name="Form" property="sgTipoApresentacaoPergunta" value="CHK" >												 
                 <tr>		
                    <td align="left" colspan="2">
                        <logic:iterate name="Form" property="respostas" id="Respostas" indexId="index" >
                            <bean:define name="Respostas" id="idResposta" property="idResposta"/>
                             <tr>		
                                <td align="left" colspan="2">
                                    <html:checkbox name="Form"  style="border:none;background-color:#E3ECF4;" property="indiceResposta" value='<%="" + idResposta + ""%>'/>
                                    
                                    <bean:write name="Respostas" property="dsScriptResposta"/>
                                </td>
                            </tr>
                        </logic:iterate>                                                                        												
                    </td>
                </tr>														
            </logic:equal>
                        
            <logic:equal name="Form" property="sgTipoApresentacaoPergunta" value="TXT" >
                    <tr align="center">
                        <td>
                            <html:textarea name="Form" property="textoResposta" rows="8" style="width:620px;height:200px;" />
                        </td>
                    </tr>
            </logic:equal>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        </td>
        </tr>
        <tr>
        <td>
        <table border="0" cellpadding="0" cellspacing="0" width="740" align="left">
            <tr> 
                <td align="left">
                    <img class="button"
                    	 src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                    	 style="border:none;"
                    	 onClick="parent.parent.mostrar_div();document.forms[0].action='voltarQuestionario.do?voltar=retornar';document.forms[0].submit();">
                </td>
                <td align="center">
                    <acesso:controlHiddenItem nomeIdentificador="que_qfa_navegar">
                    	<img class="button"
                    		 hspace="10"
                    		 src="/FrontOfficeWeb/resources/images/bt_navegscript_nrml.gif"
                    		 style="border:none;"
                    		 onClick="parent.parent.mostrar_div();document.forms[0].action='showAndamento.do';document.forms[0].submit();">
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="que_qfa_terminar">
                    	<img class="button" 
                    		 src="/FrontOfficeWeb/resources/images/bt_terminar_nrml.gif"
                    		 border="0"
                    		 style="border:none;"
                    		 onClick="parent.parent.mostrar_div();document.forms[0].action='finalizarQuestionario.do';document.forms[0].submit();">
                    </acesso:controlHiddenItem>
                </td>
                <td align="right">
                    <acesso:controlHiddenItem nomeIdentificador="que_qfa_proxima">
                    <img hspace="10" src="/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif" onMouseOut=" this.src = '/FrontOfficeWeb/resources/images/bt_proxima_nrml.gif'; " onMouseOver=" this.src = '/FrontOfficeWeb/resources/images/bt_proxima_over.gif';" onClick="questionario_proxima();"  border="0" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                </td>
            </tr>
        </table>
        </td>
        </tr>
        </table>
    </vivo:quadro>
    
    </form>
    
    <!--Quadro Flutuante-->    
    <!--vivo:quadroFlutuante id="dvNrScript" idIframe="ifrmNrScript" height="300" width="600" spacesTop="25" spacesLeft="50" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/-->
</acesso:controlHiddenItem>
</html>
