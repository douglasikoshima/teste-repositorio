<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld"  prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<netui-template:template templatePage='/resources/jsp/CRMTemplate.jsp'>
	<netui-template:setAttribute value="Sincronismo Pré Pago" name="title"/>
	<netui-template:setAttribute value="Sincronismo Pré Pago" name="modulo"/>
	<netui-template:section name="headerSection">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.3.2.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/jquery.chili-2.2.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/chili/recipes.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script type="text/javascript">
            jQuery.noConflict();
            
            onload = function() {
                $('nrLinha').focus();
                
                <%
                String errorMsg = request.getAttribute(ConstantesCRM.SERROR).toString();
                if(!ConstantesCRM.SVAZIO.equals(errorMsg)){%>
         			alert("<%=errorMsg%>");
         		<%}%>
                
                try { if (top.frameApp.dvAnimarAguarde) top.frameApp.stopAnimation(); } 
                catch(e){}
            };
            
            function sincronizar(){
            	if (!$F('nrLinha').blank())
                	$('sincronismoForm').submit();
            	else
            		alert("Digite um número de DDD e Telefone para executar a Pesquisa!");
            }
            
			function resetar(){
            	
            	if (!$F('id').blank()) {
            		$('sincronismoForm').action ="resetarTentativas.do";
                    $('sincronismoForm').submit();
                } 
            	
            }     
        </script>
        <style type="text/css">
            #table-parametros {
                border: 1px solid #c7daea;
                width: 100%;
                margin-top: 3px;
            }
            #div-pesquisa {
                border: 1px solid #c7daea;
                margin-top: 5px;
                height: 427px;
                padding: 5px;
            }
            #div-form {
                margin: 5px 0 5px 0;
            }
            #div-form form {
                margin: 0;
                padding: 0;
            }
        </style>

    </netui-template:section>

	<netui-template:section name="bodySection">
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
        <vivo:sessao id="qdMain" height="470" width="790" label="Sincronismo" operacoes="Pré Pago" scroll="no">
			<html:form action="/cliente/PrePago/sincronismo/sincronismo.do" method="post" styleId="sincronismoForm">
            <table id="table-parametros" cellpadding="0" cellspacing="0">
                <thead>
                      <tr>                       
                        <td>
                            Número do telefone:
                            <html:text property="nrLinha" maxlength="14" styleId="nrLinha" onkeyup="maskPhoneNumberObj(this)" />
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" onclick="sincronizar()" class="button" align="middle" />
                        </td>
                    </tr>
                </thead>
            </table>
          	<div id="div-pesquisa">
	             	<table>
	             		<tr>
	             			<td>cdErro:</td>
	             			<td><html:text property="cdErro" styleId="cdErro" readonly="readonly" size="7" /></td>
	             		</tr>
	             		<tr>
	             			<td>dsErro:</td>
	             			<td><html:text property="dsErro" styleId="dsErro" readonly="readonly" size="80" /></td>
	             		</tr>
	             		<tr>
	             			<td>Xml:</td>
	             			<td><html:textarea property="xml" styleId="xml" rows="20" cols="60" readonly="readonly"></html:textarea></td>
	             		</tr>
	             		<tr>
	             			<td><img src="<%=request.getContextPath()%>/resources/images/bt_atualizar_nrml.gif" onclick="resetar()" class="button" align="middle" /></td>
	             		</tr>
	             	</table>
	             	<html:hidden property="id" />
	             </div>
        	</html:form>
        </vivo:sessao>
    </netui-template:section>
</netui-template:template>