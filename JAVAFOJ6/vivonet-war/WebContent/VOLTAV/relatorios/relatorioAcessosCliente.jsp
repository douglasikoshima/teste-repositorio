<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioAcessosForm"/>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Front-Office >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
        <script language="javascript">

            function submitGerar() {
                erros = "";
                var codigoArea = document["formFiltro"]["codigoArea"];
                var numeroLinha = document["formFiltro"]["numeroLinha"];

                var dtInicio = document["formFiltro"]["dataInicio"];
                var dtFim = document["formFiltro"]["dataFim"];

                if (codigoArea.value == "") {
                    erros += "Informe o DDD da Linha do Cliente!\n";
                }

                if (numeroLinha.value == "") {
                    erros += "Informe o Número da Linha do Cliente!\n";
                }

                if (dtInicio.value == "") {
                    erros += "Selecione a Data de Início!\n";
                }
                if (dtFim.value == "") {
                    erros += "Selecione a Data Final!\n";
                }

                if (!validaDataFinal(dtInicio.value,dtFim.value)) {
                    erros += "Data Inicial maior que Data Final!\n";
                }

                if (!valida1mes(dtInicio,dtFim)) {
                    erros += "Intervalo de Datas maior que 30 dias!\n";
                }

                if (erros == "") {
                    document["formFiltro"].method = "POST";
                    document["formFiltro"].action = "gerarRelatorioAcessosCliente.do";
                    document["formFiltro"].target = "";
                    document["formFiltro"].submit();
                } else {
                    alert(erros);
                    return false;
                }
            }

            function valida1mes(objInicial,objFinal){
                objSomaDias = document.forms[0].somaDias;
                somaDiasData(objInicial,objSomaDias,30);
                resposta = validaDataFinal(objFinal.value,objSomaDias.value);
                return resposta;
            }

            function validaData(data){
                if(data.value == '')
                    return false;
                if(!validate_date(data.value)){
                    data.value = '';
                    data.focus();
                    alert("Data inválida");
                }
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>

        <!--APLICACAO-->
            <vivo:sessao id="qdMain" height="475" width="790" label="Acessos por Cliente" operacoes="Gerar Relatório">

                <form name="formFiltro" action="/VOLTAV/relatorios/gerarRelatorioAcessosCliente.do" method="post" onsubmit="return false;">
                    <table cellpadding="0" cellspacing="0" border="0" width="100%">
                        <tr>
                            <td colspan="2"><br><b>Filtros para Geração do Relatório</b></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td align="left"><b>Número da Linha:</b></td>
                            <td align="left">
                                <html:text name="form" property="codigoArea" styleId="codigoArea" size="2" maxlength="2"/> - <html:text name="form" property="numeroLinha" styleId="numeroLinha" size="9" maxlength="9"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td><b>Data de Início do Período:</b></td>
                            <td>
								<html:text name="form" property="dataInicio" styleId="dataInicio" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onChange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataInicio', '%d/%m/%Y');">&nbsp;
                                <B>até&nbsp;</b>
                                <html:text name="form" property="dataFim" styleId="dataFim" size="10" maxlength="10" onblur="validaData(this);" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onChange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dataFim', '%d/%m/%Y');">&nbsp;
                                <b>(Máximo 30 dias)</b></td>
                        </tr>
                    </table>
                    <br>
                    <table width="100%">
                        <tr>
                            <td align="center">
                                <vivo:botao id="btGerar" width="60px" height="10px" value="Gerar" styleClass="btTemplate" onclick="submitGerar();"/>
                            </td>
                        </tr>
                    </table>
                <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
                <input type="hidden" name="somaDias">
                </form>

            </vivo:sessao>
    </netui-template:section>
</netui-template:template>