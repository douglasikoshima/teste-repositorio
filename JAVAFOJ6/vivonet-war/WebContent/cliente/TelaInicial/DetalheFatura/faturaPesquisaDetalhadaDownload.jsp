<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<html>
<head>
    <title>Lista de meses de vencimento disponíveis</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script> 
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" language="javascript">
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
        validarPesquisa = function() {
           if ($F('dtVencimento').blank()) {
                alert('Por favor, selecione o mês para pesquisa ou informe um período desejado.');
                return false;
            } else if ($F('dtVencimento') == 'TODAS' && (!validaData($F('dtInicio')) || !validaData($F('dtFim')))) {
                alert('Por favor, selecione o período para a pesquisa.');
                return false;
            } else {

                if ($F('dtVencimento') == 'TODAS') {
                    var objDateInicio = new Date($F('dtInicio').substring(6, 10), $F('dtInicio').substring(3, 5), $F('dtInicio').substring(0, 2));
                    var objDateFim = new Date($F('dtFim').substring(6, 10), $F('dtFim').substring(3, 5), $F('dtFim').substring(0, 2));
                    var qtDiasDecorridos = diasDecorridos(objDateInicio, objDateFim);
                    if (!validaDataFinal($F('dtInicio'), $F('dtFim'))) {
                        alert('A data final da pesquisa não pode ser superior à data atual.');
                        return false;
                    } else if (qtDiasDecorridos > 180) {
                        alert('A data inicial da pesquisa não pode ser superior a 6 meses do período fechado.');
                        return false;
                    }
                }
                if ($('inExportar').checked && $F('cdFormatoExportacao').blank()) {
                    alert('Por favor, selecione o tipo de arquivo para a exportação do relatório.');
                    return false;
                }
            }
            return true;
        }
        pesquisar = function() {
            if (validarPesquisa()) {

                pesquisa = new Ajax.Updater('resultadoPesquisa', 'pesquisaFaturaDetalhada.do', {
                    method: 'post',
                    evalScripts: true,
                    parameters: {
                        'filtrosFaturamentoForm.dtVencimento': $F('dtVencimento'),
                        'filtrosFaturamentoForm.dtInicio': $F('dtInicio'),
                        'filtrosFaturamentoForm.dtFim': $F('dtFim'),
                        'filtrosFaturamentoForm.nrTelefoneOrigem': $F('nrTelefoneOrigem'),
                        'filtrosFaturamentoForm.cdTipoServico': $F('cdTipoServico'),
                        'filtrosFaturamentoForm.nrTelefoneDestino': $F('nrTelefoneDestino'),
                        'filtrosFaturamentoForm.idTipoLigacao': $F('idTipoLigacao'),
                        'filtrosFaturamentoForm.idTipoArea': $F('idTipoArea'),
                        'filtrosFaturamentoForm.cdFormatoExportacao': $F('cdFormatoExportacao'),
                        'filtrosFaturamentoForm.idDetalheChamada': $F('idDetalheChamada')
                    }, onComplete: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, on503: function(e) {
                        createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });

                /*if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                top.frameApp.$('divFaturaDetalhadaImagem').show();
                var f = document.forms[0];
                f.action = "consultarImagemFatura.do?dataVencimento=" + $F('dtVencimento');
                f.submit();*/
            }
        }
    </script>
    <style type="text/css">
        body {
            background:#ededed;
        }
        select {
            width:150px;
            margin-left:3px;
        }
        #resultadoPesquisa {
            margin-top:6px;
            display:block;
            width:780px;
            height:160px;
            background:#f7f9fa;
            overflow:hidden;
        }
    </style>
</head>
<body>
<form id="formFatura" name="formFatura" action="consultarImagemFatura.do" method="post" target="iframeFaturaDetalhadaImagem">
<table width="100%">
    <tr>
        <td colspan="4">
            <fieldset>
                <legend>Tipo de pesquisa</legend>
                <table width="100%">
                    <tr>
                        <td>Por fatura</td>
                        <td>
                            <html:select name="Form" property="filtrosFaturamentoForm.dtVencimento" styleId="dtVencimento" onchange="$('dtInicio').value='';$('dtFim').value=''">
                                <option value="">-- Selecione --</option>
                                <option value="TODAS">Todas</option>
                                <logic:iterate id="listaVencimentos" name="Form" property="faturaVencimentosDisponiveis" type="java.lang.String">
                                <option value="<bean:write name="listaVencimentos" />"><bean:write name="listaVencimentos" /></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Por período</td>
                        <td>
                            <html:text name="Form"
                                       property="filtrosFaturamentoForm.dtInicio"
                                       styleId="dtInicio"
                                       style="width:65px;"
                                       maxlength="10"
                                       onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('dtVencimento').selectedIndex=1" />
                            <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="$('dtVencimento').selectedIndex=1;return showCalendar('dtInicio', '%d/%m/%Y');"> 
                            a 
                            <html:text name="Form"
                                       property="filtrosFaturamentoForm.dtFim"
                                       styleId="dtFim"
                                       style="width:65px;margin-left:10px;"
                                       maxlength="10"
                                       onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('dtVencimento').selectedIndex=1" />
                            <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="$('dtVencimento').selectedIndex=1;return showCalendar('dtFim', '%d/%m/%Y');"> 
                        </td>
                    </tr>
                </table>
            </fieldset>
        </td>
    <tr>
        <td>Número de Celular</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.nrTelefoneOrigem" styleId="nrTelefoneOrigem">
                <option value="">-- Selecione --</option>
                <html:options name="Form" property="filtrosFaturamentoForm.listaNrTelefoneOrigem" />
            </html:select>
        </td>
        <td style="padding-left:5px;">Uso</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdTipoServico" styleId="cdTipoServico">
                <option value="">-- Selecione --</option>
                <option value="D">Dados</option>
                <option value="O">Outros</option>
                <option value="A">Todos</option>
            </html:select>
        </td>
    </tr>
    <tr>
        <td>Número Discado</td>
        <td>
            <html:text name="Form"
                       property="filtrosFaturamentoForm.nrTelefoneDestino"
                       styleId="nrTelefoneDestino" 
                       onblur="formatPhoneNumberObj(this)"
                       maxlength="14"
                       style="width:70px" />
                       <!--onkeyup="formatTelNo(this)"
                       onblur="formatTelNo(this)"-->
        </td>
        <td style="padding-left:5px;">Tipo de Ligação</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.idTipoLigacao" styleId="idTipoLigacao">
                <option value="">-- Selecione --</option>
                <option value="0">Todos</option>
                <option value="1">Fixo</option>
                <option value="2">Vivo</option>
                <option value="3">Outras Operadoras</option>
            </html:select>
        </td>
    </tr>
    <tr>
        <td>Tipo de Área</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.idTipoArea" styleId="idTipoArea">
                <option value="">-- Selecione --</option>
                <option value="0">Todos</option>
                <option value="1">Dentro da Área</option>
                <option value="2">Fora da Área</option>
            </html:select>
        </td>
        <td>
            <input type="checkbox" id="inExportar" style="background:none;border:none;margin:0;padding:0" />
            <label for="inExportar" style="text-align:left;line-height:-20px;">Exportar arquivo</label>
        </td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdFormatoExportacao" styleId="cdFormatoExportacao">
                <option value="">-- Selecione --</option>
                <option value="CSV">CSV</option>
            </html:select>
        </td>
    </tr>
    <tr>
        <td>Detalhe da Ligação</td>
        <td colspan="3">
            <html:select name="Form" property="filtrosFaturamentoForm.idDetalheChamada" styleId="idDetalheChamada">
                <option value="">-- Selecione --</option>
                <option value="0">Todos</option>
                <option value="1">Realizada</option>
                <option value="2">Local</option>
                <option value="3">Longa Distância Nacional</option>
                <option value="4">Longa Distância Internacional</option>
                <option value="5">A cobrar</option>
            </html:select>
        </td>
    </tr>
    <tr>
        <td colspan="4" align="right">
            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" onmouseup="pesquisar()" style="cursor:pointer" />
        </td>
    </tr>
    <tr>
        <td colspan="4" valign="top">
            <div id="resultadoPesquisa"></div>
        </td>
    </tr>
</table>
</form>
</body>
</html>