<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="servicosDeParaForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute value="Vivonet" name="title"/>
<netui-template:setAttribute value="Parametrização" name="modulo"/>
<netui-template:section name="headerSection">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        gravar = function() {
            var f = document.forms['0'];
            if ($F('cdPlano').blank()) {
                alert('Por favor, digite o código do Plano.');
            } else if ($F('dsPlano').blank()) {
                alert('Por favor, digite a descrição do Plano.');
            } else {
                if ($('flAtivo').checked) {
                    $('ativo').value = "true";
                } else {
                    $('ativo').value = "false";
                }
                f.action = 'salvarServico.do';
                f.submit();
            }
        }
    </script>
    <style type="text/css">
        #formServicos {
            background: none;
        }
        #formServicos input {

            width: 250px;
        }
        #formServicos input {

        }
    </style>
</netui-template:section>
<netui-template:section name="bodySection">
<div id="menuPrincipal" style="margin-bottom:4px;"><jsp:include page="/resources/menus/MenuPrincipal.jsp" /></div>
<vivo:sessao height="470" id="permissoes" width="790" scroll="no" label="Serviços de Para" operacoes="Parametrização">
    <html:form action="begin.do" method="post" styleId="formServicos">
    <html:hidden name="form" property="idPlano" styleId="idPlano" />
    <html:hidden name="form" property="ativo" styleId="ativo" />
    <table border="0" cellpadding="2" cellspacing="2">
        <tr>
            <td>Código do Plano</td>
            <td><html:text name="form" property="cdPlano" /></td>
        </tr>
        <tr>
            <td>Descrição do Plano</td>
            <td><html:text name="form" property="dsPlano" /></td>
        </tr>
        <tr>
            <td>Código do plano no Atlys</td>
            <td><html:text name="form" property="cdPlanoAtlys" /></td>
        </tr>
        <tr>
            <td>Ativo?</td>
            <td>
                <input style="border:none;background:none;width:20px;" type="checkbox" name="flAtivo" id="flAtivo" <logic:equal name="form" property="ativo" value="true">checked</logic:equal> />
            </td>
        </tr>
        <tr>
            <td width="50%"><img src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" style="cursor:pointer" onmouseup="this.document.forms[0].action='begin.do';this.document.forms[0].submit();" /></td>
            <td width="50%" align="right"><img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" style="cursor:pointer" onmouseup="gravar();" /></td>
        </tr>
    </table>
    </html:form>
</vivo:sessao>
</netui-template:section>
</netui-template:template>