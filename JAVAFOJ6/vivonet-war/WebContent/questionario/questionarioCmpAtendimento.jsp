<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
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
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/cronometro.js" charset="ISO-8859-1"></script>
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js" charset="ISO-8859-1"></script>                  
        <script language="javascript" src="/FrontOfficeWeb/resources/scripts/questionario.js" charset="ISO-8859-1"></script>    

<script language="javascript">
    function checaTextarea(obj, tamanho){
      obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
    }
</script>
        
        <%@ include file="includeJS.jspf"%> 
        
        <SCRIPT FOR=window EVENT=onload>
            top.frameApp.oculta_div();
            qstIniciaContador(<bean:write name="pageFlow" property="tempoTotal" />, <bean:write name="pageFlow" property="tempoCorrido" />);
        </script>    
    </head>
    <acesso:controlHiddenItem nomeIdentificador="que_qat_verpagina">
    <body> 
    <form action="factoryRespostaForward.do" method="post" name="factoryRespostaForward">

    <vivo:quadro id="qdPerg" width="100%" height="100%" label="Script">
	<table border="0" width="100%" height="100%">
        <tr valign="top" >
            <td >    
        <table border="0" cellpadding="0" cellspacing="0" width="100%" align="left">
            <tr>
                <td align="right">&nbsp;
                    <!--
                    *********************************************************************************
                    Inicio Cronometro
                    *********************************************************************************
                    -->
                        
                        <table width="280"  border="0" cellpadding="0" cellspacing="0" id="tblCronometro"  style="display:none;">
                            <tr>
                                <td id="" width="13" rowspan="3" background="/FrontOfficeWeb/resources/images/cronometro/esquerdo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                
                                <td id="tdC10" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC20" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC30" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC40" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC50" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC60" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC70" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC80" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC90" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdC100" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_cima.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                        
                                <td id="" width="13" height="27" rowspan="3" background="/FrontOfficeWeb/resources/images/cronometro/direito.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                        
                            </tr>
                            <tr>
                                <td  id="trM" colspan="10" background="/FrontOfficeWeb/resources/images/cronometro/meio.gif" align="center" height="15" style="font-size:10px;font-family:verdana"><div id="lyrTemp"></div></td>
                            </tr>
                            <tr>
                                <td id="tdB10" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB20" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB30" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB40" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB50" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB60" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB70" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB80" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB90" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                                <td id="tdB100" height="6" background="/FrontOfficeWeb/resources/images/cronometro/apagado_baixo.gif"><img src="/FrontOfficeWeb/resources/images/cronometro/_spacer.gif" width="5" height="6"></td>
                            </tr>
                        </table>

              
                    <!--
                    *********************************************************************************
                    Fim Cronometro
                    *********************************************************************************
                    -->                                                                            
                </td>
            </tr>
            <tr class="corpo"> 					
                <td class="tblEstatica_campoNome" align="left" width="600" valign="middle">
                    <netui:label value="{pageContext.Form.dsScriptPergunta}" />
                </td>
            </tr>
            <tr>
                <td >&nbsp;</td>
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
                    <tr align="left">
                        <td>
                            <html:textarea name="Form" property="textoResposta" rows="8" style="width:755px;height:200px;" />
                        </td>
                    </tr>
            </logic:equal>
        </table>
        
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
        </td>
        </tr>
        <tr>
        <td>
        <table border="0" cellpadding="0" cellspacing="0" width="755" align="left">
            <tr> 
                <td align="left">                    
                    <acesso:controlHiddenItem nomeIdentificador="que_qat_dataaniver">
                        <img hspace="10" src="/FrontOfficeWeb/resources/images/bt_agenda_niver_nrml.gif" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_agenda_niver_over.gif'" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_agenda_niver_nrml.gif'" onClick="agendaAniversario();" border="0" style="border:none;cursor:hand;"/>                                        
                    </acesso:controlHiddenItem>
                    
                    <acesso:controlHiddenItem nomeIdentificador="que_qat_navegar">
                        
                        <img class="button"
                         	 hspace="10"
                         	 action="showAndamento"
                         	 src="/FrontOfficeWeb/resources/images/bt_navegscript_nrml.gif"
                         	 onClick="top.frameApp.mostrar_div();"
                         	 style="border:none;" />
                    
                    </acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="que_qat_terminar">
                        <img hspace="10" src="/FrontOfficeWeb/resources/images/bt_terminar_nrml.gif" onMouseOut="this.src = '/FrontOfficeWeb/resources/images/bt_terminar_nrml.gif';" onMouseOver="this.src = '/FrontOfficeWeb/resources/images/bt_terminar_over.gif';" border="0" onClick="questionarioCmpAtendimento_terminarClick();" style="border:none;cursor:hand;"/>
                    </acesso:controlHiddenItem>
                </td>
                <td align="right">
                <%
                    if (!ConstantesCRM.SONE.equals(request.getAttribute("isPrimeiraPergunta"))){
                        %>
                        <img class="button"
                             hspace="10"
                             src="/FrontOfficeWeb/resources/images/bt_anterior_nrml.gif"
                             rolloverImage="/FrontOfficeWeb/resources/images/bt_anterior_over.gif"
                             border="0" style="border:none;"
                             onClick="top.frameApp.mostrar_div();window.location.href='voltarQuestionario.do?voltar=retornar';" />
                        <%
                    }
                %>
                    <acesso:controlHiddenItem nomeIdentificador="que_qat_proxima">
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
    </body>
    <!--Quadro Flutuante-->    
    <vivo:quadroFlutuante id="dvAgendaAniversario" idIframe="ifrmAgendaAniversario" height="100" width="300" spacesTop="50" spacesLeft="200" display="none" url="<%=request.getContextPath()%>" label="Agenda de Aniversario"/>
    </acesso:controlHiddenItem>
</html>
