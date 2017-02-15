<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<html>
<head>
    <title>Lista de meses de vencimento disponíveis</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript">
        manageCodigoBarras = function() {
            var isSelected = false;
            var indexEmail;
            for (var i = 0; i < $('bodyListaEmails').select('input["radio"]').length; i++) {
                if ($('bodyListaEmails').select('input')[i].checked) {
                    isSelected = true;
                    indexEmail = i;
                }
            }
            if (!isSelected) {
                alert('Por favor, selecione o endereço de email para envio.');
                return false;
            } else {

                var params = $H();
                params.set('inEmail', 'true');
                params.set('indexEmail', indexEmail);

                new Ajax.Updater('containerCodigoBarras', '/FrontOfficeWeb/cliente/TelaInicial/DetalheFatura/manageCodigoBarras.do', {
                    method: 'get',
                    parameters: params,
                    evalScripts: true,
                    onComplete: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    },
                    onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, on406: function(e) {
                        createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }
    </script>
</head>
<body>
<div id="bodyListaEmails">
    <table width="100%" height="100%">
        <tr>
            <td height="100%" width="100%" valign="middle">
                <%
                List listaEmails = Form.getListaEmails();
                if (listaEmails.size() == 0) {
                %>
                    <span style="text-align:center;width:100%">
                        Não existe nenhum endereço de email cadastrado para o cliente.
                    </span>
                <% } else { %>
                    <ul class="ulListas">
                        <%
                        int contador = 0;
                        for (int i = 0; i < listaEmails.size(); i++) {
                        %>
                        <li>
                            <input name="dsEmail" type="radio" id="rdEmail_<%=i%>" value="<%=i%>" />
                            <label for="rdEmail_<%=i%>"><%=(String)listaEmails.get(i)%></label>
                        </li>
                        <% contador++;
                        }
                        %>
                    </ul>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <p>
                        <img src="<%=request.getContextPath()%>/resources/images/bt_enviar_nrml.gif" border="0" onmouseup="manageCodigoBarras()" style="cursor:pointer;margin:10px" />
                    </p>
                <% } %>
            </td>
        </tr>
    </table>
</div>
<style type="text/css">
    input {border:none;background:none;margin:0;padding:0}
    #bodyListaEmails {margin:0;padding:0;background:#ededed;overflow:hidden;width:348px;height:248px;}
    .ulListas {
        list-style-type:none;
        margin:0;
        padding:0 0 0 50px
    }
    .ulListas li {
        margin:0 0 6px 0;
    }
</style>
</body>
</html>