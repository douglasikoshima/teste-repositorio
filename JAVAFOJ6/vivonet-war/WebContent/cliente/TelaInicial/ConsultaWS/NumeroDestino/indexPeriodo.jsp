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
                if ($F('dtInicio').blank()) {
                    alert('Por favor, digite a data de in�cio para consulta.');
                } else if ($F('dtFim').blank()) {
                    alert('Por favor, digite a data final para consulta.');
                } else if (!validaDataFinal($F('dtInicio'), $F('dtFim'))) {
                    alert("Data final � menor que a data inicial.");
                } else if ($F('nrTelefone').blank() && $F('nrTelefone').length != 13) {
                    alert("Por favor, digite um n�mero de linha v�lido.");
                } else {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                    var params = $H();
                    params.set('dtInicio', $F('dtInicio'));
                    params.set('dtFim', $F('dtFim'));
                    params.set('nrTelefone', $F('nrTelefone'));
                    new Ajax.Updater('containerPesquisa', 'pesquisarNumero.do', {
                        method: 'post',
                        parameters: params,
                        evalScripts: true,
                        onSuccess: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onFailure: function(e) {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                            createErrorPopUp('erroConsultaNumero', e.statusText, stackTrace, false);
                        }
                    });
                }
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <vivo:quadro id="divConsultaNumero" height="247" width="660" label="Consulta de N�mero" scroll="no">
            <html:form styleId="consultarNumeroForm" action="pesquisarNumero">
            <table width="100%">
                <tr>
                    <td nowrap>
                        Data de in�cio: 
                        <html:text name="Form"
                               property="dtInicio"
                               styleId="dtInicio"
                               size="10"
                               maxlength="10"
                               onblur="validaDataInput(this)"
                               onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" />
                        <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calend�rio" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">
                    </td>
                    <td nowrap>
                        Data fim: 
                        <html:text name="Form"
                               property="dtFim"
                               styleId="dtFim"
                               size="10"
                               maxlength="10"
                               onblur="validaDataInput(this)"
                               onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" />
                        <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calend�rio" onclick="return showCalendar('dtFim', '%d/%m/%Y');">
                    </td>
                    <td width="100%" nowrap>
                        N�mero: 
                        <html:text name="Form"
                                   property="nrTelefone"
                                   styleId="nrTelefone"
                                   onblur="formatPhoneNumberObj(this)"
                                   style="width:80px;"
                                   maxlength="13" />
                                   <!--onkeyup="formatTelNo(this)"-->
                    </td>
                    <td align="right">
                        <a href="javacript:void(0)" onmouseup="pesquisar()">
                            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" />
                        </a>
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