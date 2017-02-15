<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<html>
<head>
    <title>Lista de meses de vencimento disponíveis</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <link rel="stylesheet" media="all" type="text/css" href="<%=request.getContextPath()%>/resources/css/autocomplete.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/messages.js" ></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/date.format.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/autocomplete.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
    <script type="text/javascript" language="javascript">
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
			window.top.frameApp.iframePopupTI.ifrmCtDetalhe.scrolling = 'no';
            manageTipoPesquisa();
            /*if ($('nrTelefoneOrigem').length > 2) {
                $('span_pesquisa_numero').show();
                new Autocomplete('query', {
                    serviceUrl : 'buscaNumeroOrigem.do',
                    minChars:2,
                    maxHeight:80,
                    width:80,
                    onSelect: function(value, data){
                        $('nrTelefoneOrigem').selectedIndex = data + 1;
                        $('query').value = '';
                    }
                });
            }*/;

        }
        validarPesquisa = function() {
            if (!$('pesquisa_periodo').checked && !$('pesquisa_fatura').checked) {
                alert('Por favor, informe um período para pesquisa.');
                return false;
            }
			if ($('pesquisa_fatura').checked && $('dtVencimento').selectedIndex == 0) {
				alert('Por favor, selecione uma data de vencimento da fatura para pesquisa.');
				return false;
			}
            if ($('pesquisa_periodo').checked) {
                if ($F('dtInicio').blank() && $F('dtFim').blank()) {
                    alert('Por favor, informe um período para pesquisa.');
                    return false;
                } else if (!$F('dtInicio').blank() && $F('dtFim').blank()) {
                    alert('Por favor, informe a data final da pesquisa.');
                    return false;
                } else if ($F('dtInicio').blank() && !$F('dtFim').blank()) {
                    alert('Por favor, informe a data inicial da pesquisa.');
                    return false;
                } else if (!validaData($F('dtInicio'))) {
                    alert('Por favor, informe corretamente a data inicial da pesquisa.');
                    return false;
                } else if (!validaData($F('dtFim'))) {
                    alert('Por favor, informe corretamente a data final da pesquisa.');
                    return false;
                } else {
                    var dtFatAntiga = $('dtVencimento').options[$('dtVencimento').length-1].value;
					var mesFatAntiga = parseInt(dtFatAntiga.substring(3, 5), 10);
					var anoFatAntiga = parseInt(dtFatAntiga.substring(6, 10));
					if (mesFatAntiga == 1) {
						mesFatAntiga = 12;
						anoFatAntiga--;
					} else {
						mesFatAntiga--;
					}
					dtFatAntiga = dtFatAntiga.substring(0,3) + mesFatAntiga.toPaddedString(2) +'/' + anoFatAntiga;
                    var objDateFaturaMaisAntiga = new Date(dtFatAntiga.substring(6, 10), dtFatAntiga.substring(3, 5), dtFatAntiga.substring(0, 2));
                    var objDateInicio = new Date($F('dtInicio').substring(6, 10), $F('dtInicio').substring(3, 5), $F('dtInicio').substring(0, 2));
                    var objDateFim = new Date($F('dtFim').substring(6, 10), $F('dtFim').substring(3, 5), $F('dtFim').substring(0, 2));
                    var objDateAtual = new Date();
                    var qtDiasDecorridos = diasDecorridos(objDateInicio, objDateFim);
                    if (!validaDataFinal($F('dtInicio'), $F('dtFim'))) {
                        alert('A data inicial deve ser menor que a data final da pesquisa.');
                        return false;
                    } else if (!validaDataFinal($F('dtInicio'), objDateAtual.format("dd/mm/yyyy"))) {
                        alert('A data inicial da pesquisa não pode ser maior que a data atual.');
                        return false;
                    } else if (!validaDataFinal(dtFatAntiga, $F('dtInicio'))) {
						alert('A data inicial deve ser maior ou igual que ' + dtFatAntiga);
						return false;
                    } else if (qtDiasDecorridos > 180) {
                        alert('O período selecionado deve ser igual ou menor que 6 (seis) meses.');
                        return false;
                    }
                }
            }
            return true;
        }

        pesquisar = function() {
            if (validarPesquisa()) {
                var action = '';
                action = 'pesquisaFaturaDetalhada.do?pesquisa=true';                
                pesquisa = new Ajax.Updater('resultadoPesquisa', action, {
                    method: 'post',
                    evalScripts: true,
                    parameters: $('formFatura').serialize(),
                    onComplete: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }, onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    }, onFailure: function(e) {
						var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                        var msgErro = e.getHeader("msgErro") ? e.getHeader("msgErro") : "";
                        top.frameApp.createErrorPopUp('erroPortabilidade', (msgErro == '') ? Messages.ERRO_PADRAO : msgErro, stackTrace, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
					}
                });
            }
        }

        manageTipoPesquisa = function() {
            if ($('pesquisa_fatura').checked) {
                $('dtInicio').value = '';
                $('dtFim').value = '';
                $('dtInicio').readOnly = true;
                $('dtFim').readOnly = true;
                $('dtVencimento').disabled = false;
                showCalendarios(false);
            } else if ($('pesquisa_periodo').checked) {
                $('dtVencimento').selectedIndex = 0;
                $('dtVencimento').disabled = true;
                $('dtInicio').readOnly = false;
                $('dtFim').readOnly = false;
                showCalendarios(true);
            } else {
                $('dtInicio').value = '';
                $('dtFim').value = '';
                $('dtInicio').readOnly = true;
                $('dtFim').readOnly = true;
                $('dtVencimento').selectedIndex = 0;
                $('dtVencimento').disabled = true;
            }
        }

        showCalendarios = function(arg) {
            var calendarios = $('tdPeriodo').select('[class=calendario]');
            if (arg) {
                for (var i = 0; i < calendarios.length; i++) {
                    calendarios[i].show();
                }
            } else {
                for (var i = 0; i < calendarios.length; i++) {
                    calendarios[i].hide();
                }
            }
        }

        limpar = function() {
            $('pesquisa_fatura').checked = true;
            manageTipoPesquisa();
            $('nrTelefoneOrigem').selectedIndex = 0;
            $('nrTelefoneDestino').value = '';
            $('cdTipoChamada').selectedIndex = $('cdTipoChamada').length-1;
            $('cdAreaRegistro').selectedIndex = $('cdAreaRegistro').length-1;
            $('cdTipoDestino').selectedIndex = $('cdTipoDestino').length-1;
            $('cdDetalheChamada').selectedIndex = $('cdDetalheChamada').length-1;
            $('cdTipoServico').selectedIndex = 0;
            $('pesquisa_fatura').checked = false;
            $('dtVencimento').selectedIndex = 0;
            $('dtVencimento').disabled = true;
			if ($('query') && $('span_pesquisa_numero').visible()) {
				$('query').value = '';
			}
        }

		/*checaTelefoneAst = function(obj) {
			var s = obj.value;
			obj.value = s.replace(/[^0-9,*]/, '');
			if (s.lastIndexOf('*') > 0) {
				obj.value = s.substring(0, s.length-1);
			}
		}*/

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
            height:185px;
            background:#f7f9fa;
            overflow:auto;
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
                        <td>
                            <input type="radio" name="tipo_pesquisa" id="pesquisa_fatura" class="radio" onclick="manageTipoPesquisa()" />
                            <label for="pesquisa_fatura">Por fatura:</label>
                        </td>
                        <td>
                            <html:select name="Form" property="filtrosFaturamentoForm.dtVencimento" styleId="dtVencimento" onchange="$('pesquisa_fatura').checked=true;manageTipoPesquisa();">
                                <option value="" selected>-- Selecione --</option>
								<logic:iterate id="listaVencimentos" name="Form" property="faturaVencimentosDisponiveis" type="java.lang.String">
                                <option value="<bean:write name="listaVencimentos" />"><%=listaVencimentos %></option>
                                </logic:iterate>
                            </html:select>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%">
                            <input type="radio" name="tipo_pesquisa" id="pesquisa_periodo" class="radio" onclick="manageTipoPesquisa();$('dtInicio').focus();" />
                            <label for="pesquisa_periodo">Por período:</label>
                        </td>
                        <td width="80%" id="tdPeriodo">
                            <html:text name="Form"
                                       property="filtrosFaturamentoForm.dtInicio"
                                       styleId="dtInicio"
                                       style="width:65px;"
                                       maxlength="10"
                                       onkeyup="if(this.value.length==10){$('dtFim').focus()};Formatar(this.value, this.form.name, this.name, 'data');$('pesquisa_periodo').checked=true;manageTipoPesquisa()" />
                            <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;display:none" title="Calendário" onclick="$('dtVencimento').selectedIndex=$('dtVencimento').length-1;return showCalendar('dtInicio', '%d/%m/%Y');">
                            at&eacute;
                            <html:text name="Form"
                                       property="filtrosFaturamentoForm.dtFim"
                                       styleId="dtFim"
                                       style="width:65px;margin-left:10px;"
                                       maxlength="10"
                                       onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('pesquisa_periodo').checked=true;manageTipoPesquisa()" />
                            <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;display:none" title="Calendário" onclick="$('dtVencimento').selectedIndex=$('dtVencimento').length-1;return showCalendar('dtFim', '%d/%m/%Y');">
                        </td>
                    </tr>
                </table>
            </fieldset>
        </td>
    <tr>
        <td>Número de origem:</td>
        <td colspan="3">
            <html:select name="Form" property="filtrosFaturamentoForm.nrTelefoneOrigem" styleId="nrTelefoneOrigem">
                <option value="" selected>Todos</option>
                <html:options name="Form" property="filtrosFaturamentoForm.listaNrTelefoneOrigem" />
            </html:select>
        </td>
    </tr>
    <tr>
        <td>Número Discado:</td>
        <td>
            <html:text name="Form"
                       property="filtrosFaturamentoForm.nrTelefoneDestino"
                       styleId="nrTelefoneDestino"
                       onblur="formatPhoneNumberObj(this)"
                       maxlength="14"
                       style="width:75px" />
                       <!-- onkeyup="checaTelefoneAst(this)"
                       onfocus="checaTelefoneAst(this)"
					   onblur="checaTelefoneAst(this)"-->
        </td>
        <td style="padding-left:5px;">Tipo de Liga&ccedil;&atilde;o:</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdTipoChamada" styleId="cdTipoChamada">
                <option value="O">Realizada</option>
                <option value="R">Recebida</option>
                <option value="T" selected>Todas</option>
            </html:select>
        </td>
    </tr>
    <tr>
        <td>Localização:</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdAreaRegistro" styleId="cdAreaRegistro">
                <option value="D">Dentro do seu DDD</option>
                <option value="F">Em Roaming</option>
                <option value="T" selected>Todas</option>
            </html:select>
        </td>
		<td style="padding-left:5px;">Operadora:</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdTipoDestino" styleId="cdTipoDestino">
                <option value="F">Para Fixo</option>
                <option value="V">Para Vivo</option>
                <option value="O">Para Outra Operadora</option>
				<option value="" selected>Todos</option>
            </html:select>
        </td>
    </tr>
    <tr>
        <td>Detalhe da Ligação:</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdDetalheChamada" styleId="cdDetalheChamada">
                <option value="L">Local</option>
				<option value="N">Longa Distância Nacional</option>
				<option value="I">Longa Distância Internacional</option>
				<option value="T" selected>Todas</option>
            </html:select>
        </td>
		<td style="padding-left:5px;">Uso:</td>
        <td>
            <html:select name="Form" property="filtrosFaturamentoForm.cdTipoServico" styleId="cdTipoServico">
                <option value="T" selected>Todos</option>
                <option value="V">Voz</option>
				<option value="A">Dados</option>
                <option value="O">Outros</option>
            </html:select>
			<span align="right">
				<img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" border="0" onmouseup="limpar()" style="cursor:pointer" />
				<img src="<%=request.getContextPath()%>/resources/images/bt_gerar_nrml.gif" border="0" onmouseup="pesquisar()" style="cursor:pointer" />
			</span>
        </td>
    </tr>
    <tr>
        <td colspan="4" valign="top">
            <div id="resultadoPesquisa"></div>
        </td>
    </tr>
</table>
</form>
<iframe name="iframeDownload" id="iframeDownload" src="about:blank" width="0" height="0" frameborder="0"></iframe>
<vivo:alert atributo="msgErro" scope="request" />
</body>
</html>