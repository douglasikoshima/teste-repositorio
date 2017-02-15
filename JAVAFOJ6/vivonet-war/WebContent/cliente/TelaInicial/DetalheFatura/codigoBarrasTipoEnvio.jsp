<%@ page language="java" contentType="text/html;charset=UTF-8"%>
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
            for (var i = 0; i < $('bodyTipoEnvio').select('input["radio"]').length; i++) {
                if ($('bodyTipoEnvio').select('input')[i].checked) {
                    isSelected = true;
                }
            }
            if (!isSelected) {
                alert('Por favor, selecione o tipo de envio do código de barras.');
                return false;
            } else {
                //if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();

                var params = $H();
                params.set('inSMS', $('inSMS').checked ? 'true' : 'false');
                params.set('inListaEmails', !$('inSMS').checked ? 'true' : 'false');
                $('titleCodigoBarras').update('Lista de Emails');

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
<div id="bodyTipoEnvio">
    <table align="center" width="100%" height="100%">
        <tr>
            <td height="100%" width="100%" valign="middle" style="padding-left:100px">
                <ul class="ulListas">
                    <li>
                        <input name="tipoEnvio" type="radio" id="inSMS" value="" />
                        <label for="inSMS">SMS</label>
                    </li>
                    <li>
                        <input name="tipoEnvio" type="radio" id="inEmail" value="" />
                        <label for="inEmail">Email</label>
                    </li>
                </ul>
            </td>
        </tr>
        <tr>
            <td align="right">
                <p>
                    <img src="<%=request.getContextPath()%>/resources/images/bt_enviar_nrml.gif" border="0" onmouseup="manageCodigoBarras()" style="cursor:pointer;margin:0 20px 10px 0" />
                </p>
            </td>
        </tr>
    </table>
</div>
<style type="text/css">
    input {border:none;background:none;margin:0;padding:0}
    #bodyTipoEnvio {margin:0;padding:0;background:#ededed;overflow:hidden;width:348px;height:248px;}
    .ulListas {
        list-style-type:none;
        margin:0;
        padding:0
    }
    .ulListas li {
        margin:0 0 6px 0;
    }
</style>
</body>
</html>