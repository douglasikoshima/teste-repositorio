<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="admsistemas.admArvoreSatisfacao.admArvoreSatisfacaoController.FormQuestionario"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<bean:define id="FormQuestionario"  name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="formQuestionario"/>

<html>

<head>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
    <script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
    <script language="Javascript">
        function salvarItemEditado(){
            if(trim(document.forms[0].dsQuestionario.value) == ''){
                alert('Nome do questionario é um campo obrigatorio.');
                return;
            }else{
                document.forms[0].action='salvarQuestionarioEditado.do';
                document.forms[0].target='_parent';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }        
        function inserirItem(){
            if(trim(document.forms[0].dsQuestionario.value) == ''){
                alert('Nome do questionario é um campo obrigatorio.');
                return;
            }else{
                document.forms[0].action='adicionarQuestionario.do';
                document.forms[0].target='_parent';
                parent.mostrar_div();
                document.forms[0].submit();
            }
        }
    </script>    
</head>

<body>

<acesso:controlHiddenItem nomeIdentificador="adm_ialq_verpagina">

    <% if( request.getParameter("operacao") != null && request.getParameter("operacao").equals("incluir")){%>
        <form action="adicionarQuestionario.do" method="post" name="adicionarQuestionario" id="adicionarQuestionario" onSubmit="return false;">
        <input type="hidden" name="idQuestionario" value="<%=request.getParameter("idQuestionario")%>"/>
            <table>
                <tr><td height="6"></td></tr>
                <tr>
                    <td>&nbsp;<netui:label value="Nome do Questionário:" styleClass="tblEstatica_campoNome"/></td>
                    <td><input type="text" name="dsQuestionario" style="width:320px;" maxlength="50"></td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_ialq_salvarnov">
                        <img class="button"
                        	 vspace="6"
                        	 onmouseup="inserirItem();"
                        	 src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                        	 style="border:none;" />
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            
            <script>
                document.body.style.backgroundColor = '#ededed';
            </script>
        </form>
    
         <script language="javascript" for="window" event="onload">
        <!--   
             parent.oculta_div();
        -->
        </script> 


    
    <%}else{%>
        <form action="salvarQuestionarioEditado.do" name="salvarQuestionarioEditado" method="post" >
            <table>
                
                <input type="hidden" name="idQuestionario" value="<%=request.getParameter("idQuestionario")%>"/>
                <tr><td height="6"></td></tr>
                <tr>
                    <td>&nbsp;<netui:label value="Nome do Questionário:" styleClass="tblEstatica_campoNome"/></td>
                    <td><input type="text" name="dsQuestionario" style="width:320px;" value="<bean:write name="FormQuestionario" property="dsQuestionario"/>" maxlength="50"></td>
                </tr>
                <tr>
                    <td colspan="2" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_ialq_salvaralt">
                        <img class="button"
                        	 vspace="6"
                        	 onmouseup="salvarItemEditado()"
                        	 src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                        	 style="border:none;" />
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            
            <script>
                document.body.style.backgroundColor = '#ededed';
            </script>
        </form>

        <script language="javascript" for="window" event="onload">
        <!--   
             parent.oculta_div();
        -->
        </script> 
    
    
    <%}%>
</acesso:controlHiddenItem>    
</body>
</html>