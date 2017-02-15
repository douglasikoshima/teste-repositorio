<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="consultarNumeroForm"
             type="extworkflw.NumeroDestino.ConsultarNumeroDestinoController.ConsultarNumeroForm" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript">
            pesquisar = function() {
                if ($F('nrTelefone').blank() || $F('nrTelefone').length != 13) {
                    alert("Favor incluir um DDD e um número de linha válido.");
                } else {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                    var params = $H();
                    params.set('nrTelefone', $F('nrTelefone'));
                    params.set('tipoConsulta', 'historico');
                    params.set('idContato', top.frameApp.idContato);
                    $('containerPesquisa').update('');
                    new Ajax.Updater('containerPesquisa', 'pesquisarNumero.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            //var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            //top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                            alert(e.statusText);
                        }
                    });
                }
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <vivo:quadro id="divConsultaNumero" height="247" width="690" label="Consulta de Número" scroll="no">
            <form name="consultarNumeroForm" id="consultarNumeroForm" action="/FrontOfficeWeb/extworkflw/NumeroDestino/pesquisarNumero.do" method="post">
            <table width="100%">
                <tr>
                    <td style="padding-right:30px;" nowrap>
                        Linha:
                        <html:text name="Form"
                                   property="nrTelefone"
                                   styleId="nrTelefone"
                                   onkeyup="formatTelNo(this)"
                                   onblur="formatTelNo(this)"
                                   style="width:80px;"
                                   maxlength="13" />
                    </td>
                    <td width="80%">
                        <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" onmouseup="pesquisar()" style="cursor:pointer" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div id="containerPesquisa" style="width:640px;height:200px;">

                        </div>
                    </td>
                </tr>
            </table>
            </form>
        </vivo:quadro>
    </netui-template:section>
</netui-template:template>