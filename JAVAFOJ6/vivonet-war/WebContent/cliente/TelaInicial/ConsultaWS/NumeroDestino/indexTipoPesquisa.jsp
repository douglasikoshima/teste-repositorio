<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="consultarNumeroForm"
             type="cliente.TelaInicial.ConsultaWS.NumeroDestino.ConsultarNumeroDestinoController.ConsultarNumeroForm" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioClean.jsp">
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script type="text/javascript" language="javascript">
            pesquisar = function() {
                if (!$('tipoConsultaStatusAtual').checked && !$('tipoConsultaHistorico').checked) {
                    alert('Por favor, selecione um tipo de pesquisa.');
                } else if ($F('nrTelefone').blank() || $F('nrTelefone').length != 13) {
                    alert("Por favor, digite um número de linha válido.");
                } else {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                    var params = $H();
                    params.set('tipoConsulta', $('tipoConsultaStatusAtual').checked ? "statusAtual" : "historico");
                    params.set('nrTelefone', $F('nrTelefone'));
                    params.set('idContato', top.frameApp.idContato);
                    new Ajax.Updater('containerPesquisa', 'pesquisarNumero.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            top.frameApp.createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                }
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <vivo:quadro id="divConsultaNumero" height="247" width="690" label="Consulta de Número" scroll="no">
            <html:form styleId="consultarNumeroForm" action="pesquisarNumero">
            <table width="100%">
                <tr>
                    <td nowrap>
                        Tipo de consulta:
                    </td>
                    <td nowrap>
                        <input align="top" type="radio" name="tipoConsulta" id="tipoConsultaStatusAtual" value="0" style="background:none;border:none;margin:0;padding:0;" />
                        <label style="vertical-align:33%;" for="tipoConsultaStatusAtual">Status atual</label>
                        <input align="top" type="radio" name="tipoConsulta" id="tipoConsultaHistorico" value="1" style="background:none;border:none;margin:0;padding:0;" />
                        <label style="vertical-align:33%;" for="tipoConsultaHistorico">Histórico</label>
                    </td>
                    <td width="50%" style="padding-left:30px;">
                        Linha: 
                        <html:text name="Form"
                                   property="nrTelefone"
                                   styleId="nrTelefone"
                                   onblur="formatPhoneNumberObj(this)"
                                   style="width:80px;"
                                   maxlength="13" />
                                   <!--onkeyup="formatTelNo(this)"-->
                    </td>
                    <td align="right">
                        <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" onmouseup="pesquisar()" style="cursor:pointer" />
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <div id="containerPesquisa" style="width:640px;height:200px;">
                            
                        </div>
                    </td>
                </tr>
            </table>
            </html:form>
        </vivo:quadro>
    </netui-template:section>
</netui-template:template>