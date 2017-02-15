<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm" type="workflow.Relacionamento.RelacionamentoController.RelacionamentoForm" />

<% ParametrosVO parametros = (ParametrosVO) session.getAttribute(ConstantesCRM.SPARAMETROS); %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/TelaInicialAbaRelacionamento.css"/>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/effects.js"></script>
	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/accordion.js"></script>
	<script type="text/javascript" language="javascript">
		var setaRadio = true;
        var altura = 0;
        var div = null;
        var inVerPagina = null;
		var frmsState;
		var msgErro = 'Houve um problema durante o carregamento dos protocolos.';
        var relacionamentoHistorico = true;
        var protocoloAtivo = '';

        chamaPesquisarRelacionamento = function() {
			<logic:notEqual property="idPessoaDePara" name="Form" value="26">
			if ($('radioTpPessoa').checked) {
				if (top.frameApp.$('tipoRelacionamento').disabled == false) {
                    alert("É necessário identificação da ligação para a realização de pesquisa por pessoa.");
                    return;
                }
            }
			</logic:notEqual>
            pesquisarRelacionamento();
        }

        pesquisarRelacionamento = function() {

            dtInicioDe  = $F('dtAberturaInicio');
            dtInicioAte = $F('dtAberturaFim');
            dtFimDe     = $F('dtFechamentoInicio');
            dtFimAte    = $F('dtFechamentoFim');

			// Variável para verificaçao de carregamento da página
            setaRadio = false;

			if ((dtInicioDe != '' && dtInicioAte == '')
				|| (dtInicioDe == '' && dtInicioAte != '')) {
				alert('Para pesquisa por período de abertura é necessário informar a data inicial e a data final.');
				return;
			}

			if ((dtFimDe != '' && dtFimAte == '')
				|| (dtFimDe == '' && dtFimAte != '')) {
				alert('Para pesquisa por período de fechamento é necessário informar a data inicial e a data final.');
				return;
			}

            if (!(validaDataFinal(dtInicioDe, dtInicioAte)) && dtInicioDe != '' && dtInicioAte != '') {
                $('dtAberturaInicio').focus();
                alert('Por favor, digite corretamente as datas de início e fim de abertura.');
                return;
            }

			if (dtInicioDe != '' && dtInicioAte != '') {
				var dt1 = new Date();
				dt1.setFullYear(dtInicioDe.substring(6,10), parseInt(dtInicioDe.substring(3,5))-1,dtInicioDe.substring(0,2));
				var dt2 = new Date();
				dt2.setFullYear(dtInicioAte.substring(6,10), parseInt(dtInicioAte.substring(3,5))-1,dtInicioAte.substring(0,2));

				var qtDiasDecorridos = diasDecorridos(dt1, dt2);
				if (qtDiasDecorridos > 30) {
					alert('O intervalo de pesquisa não pode ser superior a 30 dias.');
					return;
				}

				var hoje = new Date();
				qtDiasDecorridos = diasDecorridos(dt1, hoje);
				if (qtDiasDecorridos <= 180) {
					alert('A data inicial deve ser inferior a 180 dias.');
					return;
				}

			}

            if (!(validaDataFinal(dtFimDe,dtFimAte)) && dtFimDe != '' && dtFimAte != '') {
                $('dtFechamentoInicio').focus();
                alert('Por favor, digite corretamente as datas de início e fim de fechamento.');
                return;
            }

			if (dtFimDe != '' && dtFimAte != '') {
				var dt1 = new Date();
				dt1.setFullYear(dtFimDe.substring(6,10), parseInt(dtFimDe.substring(3,5))-1,dtFimDe.substring(0,2));
				var dt2 = new Date();
				dt2.setFullYear(dtFimAte.substring(6,10), parseInt(dtFimAte.substring(3,5))-1,dtFimAte.substring(0,2));

				var qtDiasDecorridos = diasDecorridos(dt1, dt2);
				if (qtDiasDecorridos > 30) {
					alert('O intervalo de pesquisa não pode ser superior a 30 dias.');
					return;
				}

                var hoje = new Date();
				qtDiasDecorridos = diasDecorridos(dt1, hoje);
				if (qtDiasDecorridos <= 180) {
					alert('A data inicial deve ser inferior a 180 dias.');
					return;
				}

				hoje = new Date();
				qtDiasDecorridos = diasDecorridos(hoje, dt1);
				if (qtDiasDecorridos > 730) {
					alert('A data inicial não pode ser superior a 730 dias.');
					return;
				}


			}

            var params = getFormParameters();

			new Ajax.Updater('divRelacionamento', 'pesquisarRelacionamento.do', {
				method: 'post',
				parameters: params,
				evalScripts: true,
				onSuccess: function() {
					if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
				}, onComplete: function() {
					var bottomAccordion = new accordion('vertical_container', {onActivate : getProcessosByProtocolo});
					if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
				}, onCreate: function() {
					if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
				}, onException: function(e) {
                    var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
					var statusText = e.statusText ? e.statusText : msgErro;
					top.frameApp.createErrorPopUp('erroAbaRelacionamento', statusText, stackTrace, false);
					if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
				}, onFailure: function(e) {
					var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
					top.frameApp.createErrorPopUp('erroAbaRelacionamento', e.statusText, stackTrace, false);
					if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
				}
			});
        }

		getFormParameters = function() {

			var params = $H();

			params.set('idPessoaDePara', $F('idPessoaDePara'));
			params.set('tpRelacionamento', $F('tpRelacionamento'));
			params.set('idPessoaLinhaHistorico', $F('idPessoaLinhaHistorico'));
			params.set('user', $F('user'));
			params.set('inPrimeiraChamada', $F('inPrimeiraChamada'));
			params.set('dtAberturaInicio', $F('dtAberturaInicio'));
			params.set('dtAberturaFim', $F('dtAberturaFim'));
			params.set('nrProtocolo', $F('nrProtocolo'));
			params.set('dtFechamentoInicio', $F('dtFechamentoInicio'));
			params.set('dtFechamentoFim', $F('dtFechamentoFim'));
            params.set('historicoRelacionamento', false);
            params.set('historicoRelacionamentoMG', true);

            <% // Chamada via ScreenPop (Linha não cadastrada)
               if ("26".equals(Form.getIdPessoaDePara())
                    && parametros.getNrLinha() != null
                    && !ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())) { %>
            params.set('tpPesquisa', 2);
            <% } else { %>
            var idTipoPesquisa;
            if ($('radioTpPessoa').checked) idTipoPesquisa = $F('radioTpPessoa');
			else if ($('radioTpLinha') && $('radioTpLinha').checked) idTipoPesquisa = $F('radioTpLinha');
			else if ($('radioTpLinhaCliente') && $('radioTpLinhaCliente').checked) idTipoPesquisa = $F('radioTpLinhaCliente');
			else if ($('radioTpConta') && $('radioTpConta').checked) idTipoPesquisa = $F('radioTpConta');
			params.set('tpPesquisa', idTipoPesquisa);
            <% } %>

			return params;
		}

        limpar = function() {

			$('dtAberturaInicio').value   = '';
            $('dtAberturaFim').value      = '';
            $('dtFechamentoInicio').value = '';
            $('dtFechamentoFim').value    = '';
            $('nrProtocolo').value        = '';
            $('dtAberturaInicio').focus();

            var idTipoRelacionamento = $F('tpRelacionamento');
            if ((idTipoRelacionamento == 6) || (idTipoRelacionamento == 7)) {
                $('radioTpPessoa').checked = true;
            } else {
                $('radioTpLinha').checked = true;
            }
            $('divRelacionamento').update('');
        }

        limparSemFoco = function() {
            $('dtAberturaInicio').value   = '';
            $('dtAberturaFim').value      = '';
            $('dtFechamentoInicio').value = '';
            $('dtFechamentoFim').value    = '';
            $('nrProtocolo').value        = '';
            $('inPrimeiraChamada').value  = '';
            $('radioTpLinha').checked = true;
        }

        validaData = function(data) {
            if (data.value == '')
                return false;
            if (!validate_date(data.value)) {
                data.value = '';
                data.focus();
                alert("Data inválida");
            }
        }

        // Faz o radio de Linha ser selecionado pois é a pesquisa default
        setPesquisaLinha = function() {
            if (setaRadio) {
				if ($('divRadioPessoa')) $('divRadioPessoa').show();
				if ($('divRadioLinha')) $('divRadioLinha').show();
				if ($('radioTpLinhaCliente')) $('radioTpLinhaCliente').checked = true;
            }
        }

        // Faz o radio de Pessoa ser selecionado
        setPesquisaPessoa = function() {
            if (setaRadio) {
				if ($('divRadioPessoa')) $('divRadioPessoa').show();
				if ($('divRadioLinha')) $('divRadioLinha').hide();
				if ($('divRadioLinhaCliente')) $('divRadioLinhaCliente').hide();
				if ($('divRadioConta')) $('divRadioConta').hide();
				if ($('radioTpPessoa')) $('radioTpPessoa').checked = true;
            }
        }

        desativar_combos = function() {
            if (frmsState == null) {
                forms=document.forms;
                frmsState = new Array(forms.length);
                for (i=0;i<forms.length;i++) {
                    el=forms[i].elements;elState=new Array(el.length);frmsState[i]=elState;
                    for (j=0;j<el.length;j++) {
                        elState[j]=el[j].disabled;el[j].disabled=true;
                    }
                }
            }
            return;
        }

        ativar_combos = function() {
            if (frmsState!=null) {
                forms=document.forms;
                for (i=0;i<forms.length;i++) {
                    el=forms[i].elements;elState=frmsState[i];frmsState[i]=elState;
                    for(j=0;j<el.length;j++){ el[j].disabled=elState[j]; }
                }
            }
            frmsState = null;
			return;
        }

        inserirComentario = function() {
            $('divComentario').show();
            ifrmComentario.location.href = 'inserirComentarioTracking.jsp';
        }

        onload = function() {

            var linha = false;
            var cliente = false;
		    <%
		        if (parametros.getIdTipoRelacionamento() ==  null ||
						parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SVAZIO) ||
						parametros.getIdTipoRelacionamento().equals(ConstantesCRM.SZERO) ) {
		            if (!ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())) { %>
		                linha = true;
		            <% } else { %>
		                linha = false;
		            <% }
                    if ("26".equals(parametros.getIdPessoaCliente())) { %>
		                cliente = false;
		            <% } else { %>
                        cliente = true;
		            <% }
		        } else {
		            if (parametros.getIdTipoRelacionamento().matches("[12]")) {%>
		                linha = true;
                        cliente = true;
		            <%} else if (parametros.getIdTipoRelacionamento().matches("[67]")) {%>
		                linha = false;
		            <%}
		        }%>
		    if (linha) {
		        // Inicializa a pesquisa
		        setPesquisaLinha();
		    } else {
		        // Inicializa a pesquisa
		        setPesquisaPessoa();
		    }

			<% if ("26".equals(Form.getIdPessoaDePara()) && parametros.getNrLinha() == null) { %>

                $('radioTpPessoa').checked = true;

            <%  } else if (!"26".equals(Form.getIdPessoaDePara()) || parametros.getNrLinha() != null) { %>

                var params = $H();
                params.set('tpPesquisa', (linha && cliente) ? 4 : linha ? 2 : 3);
                params.set('inPrimeiraChamada', 1);
                params.set('historicoRelacionamento', false);
                params.set('historicoRelacionamentoMG', true);

                <% // Chamada via ScreenPop (Linha não cadastrada)
                   if ("26".equals(Form.getIdPessoaDePara())
                        && parametros.getNrLinha() != null
                        && !ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())) { %>
                params.set('tpPesquisa', 2);
                <% } %>

				if (top.frameApp.$('divContentContainer').visible()) {
	                new Ajax.Updater('divRelacionamento', 'pesquisarRelacionamento.do', {
			            method: 'post',
						parameters: params,
			            evalScripts: true,
			            onComplete: function() {
							$('inPrimeiraChamada').value  = '';
							var bottomAccordion = new accordion('vertical_container', {onActivate : getProcessosByProtocolo});
							if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
			            }, onException: function(e) {
	                        var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
	                        var statusText = e.statusText ? e.statusText : msgErro;
			                top.frameApp.createErrorPopUp('erroAbaRelacionamento', statusText, stackTrace, false);
			                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
	                    }, onFailure: function(e) {
			                var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
			                top.frameApp.createErrorPopUp('erroAbaRelacionamento', e.statusText, stackTrace, false);
			                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
			            }
			        });
				} else {
	                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
	            }
            <% } %>
        }

        obtemDetalhes  = function(inACS, idAtendimento, inPortout) {
	        var src = '';
			if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
            src = '/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin3.do?idAtendimento='+idAtendimento+'&inMenu=N&fila=';
            top.frameApp.$('divDetalheHistorico').show();
            top.frameApp.$('iframeDetalheHistorico').src = src;
        }

        getAtendimentoProtocolo = function(nrProt) {
            top.frameApp.$('ti_comboPesquisa').selectedIndex = 13;      // Protocolo
            top.frameApp.$('ti_inputValorPesquisa').value = nrProt;
            top.frameApp.isScreenPop = false;
            top.frameApp.inIdPosAutomatico = true;
            top.frameApp.mostraIdPos();
            top.frameApp.nrProtocoloScreenPop = '';
            top.frameApp.validaPesquisa();
        }

        function getProcessosByProtocolo(elem) {

            var myElement = $(elem);
            var elemid = myElement.id;
            var estado = myElement.estado;
            var nrProtocolo = elemid.substring(12, elemid.length);
            protocoloAtivo = nrProtocolo;
            var inAtendimendoOuPendente = (estado.toLowerCase() == 'em atendimento' || estado.toLowerCase() == 'pendente') ? true : false;

            if (inAtendimendoOuPendente || myElement.innerHTML == '') {

                elem.innerHTML = '&nbsp;Carregando...';
                var ajax = new Ajax.Request('getProcessosByProtocolo.do' , {
                        onSuccess :  ajax_text,
                        parameters : {
                            nrProtocolo: nrProtocolo,
                            inHistoricoMG: true
                        }
                    }
                );

            }
        }

        function ajax_text(originalRequest) {
            var textdata = originalRequest.responseText;
            $('divProcessos' + protocoloAtivo).innerHTML = textdata;
        }

    </script>

</head>

<body id="bodyRelacionamento" style="width:800px;overflow-x:hidden;height:580px;">
<div style="width:795px;height:590px;overflow-x:hidden;overflow-y:auto">
<form action="pesquisarRelacionamento.do" id="relacionamentoForm" method="post" name="relacionamentoForm">

	<html:hidden name="Form" property="idPessoaDePara" styleId="idPessoaDePara" />
	<html:hidden name="Form" property="tpRelacionamento" styleId="tpRelacionamento" />
	<html:hidden name="Form" property="idPessoaLinhaHistorico" styleId="idPessoaLinhaHistorico" />
	<html:hidden name="Form" property="user" styleId="user" />
	<html:hidden name="Form" property="inPrimeiraChamada" styleId="inPrimeiraChamada" />
    <html:hidden name="Form" property="historicoRelacionamento" styleId="historicoRelacionamento" />

	<table id="tablePesquisa" width="760" border="0">
		<tr>
			<td width="130">
				<div id="divRadioPessoa">
					<html:radio styleClass="radio" name="Form" property="tpPesquisa" styleId="radioTpPessoa" value="3" />
					<label for="radioTpPessoa">
                        Pessoa
					</label>
				</div>
			</td>
			<td colspan="3" nowrap>
                Protocolo
			    <html:text name="Form" property="nrProtocolo" styleId="nrProtocolo" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/>
			</td>
		</tr>
		<tr>
			<td>
				<div id="divRadioLinha">
					<logic:notEqual property="idPessoaDePara" name="Form" value="26">
						<html:radio styleClass="radio" name="Form" property="tpPesquisa" styleId="radioTpLinha" value="2" />
						<label for="radioTpLinha">Linha</label>
					</logic:notEqual>
				</div>
			</td>
            <td width="90">Abertura de:</td>
			<td>
				<html:text name="Form" property="dtAberturaInicio" styleId="dtAberturaInicio" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAberturaInicio', '%d/%m/%Y');">
				Até <html:text name="Form" property="dtAberturaFim" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAberturaFim', '%d/%m/%Y');">
			</td>

		</tr>
		<tr>
            <td>
				<div id="divRadioLinhaCliente">
					<logic:notEqual property="idPessoaDePara" name="Form" value="26">
						<html:radio styleClass="radio" name="Form" property="tpPesquisa" styleId="radioTpLinhaCliente" value="4" />
						<label for="radioTpLinhaCliente">Linha do Cliente</label>
					</logic:notEqual>
				</div>
			</td>
            <td>Fechamento de:</td>
			<td>
				<html:text name="Form" property="dtFechamentoInicio" styleId="dtFechamentoInicio" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFechamentoInicio', '%d/%m/%Y');">
				Até <html:text name="Form" property="dtFechamentoFim" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFechamentoFim', '%d/%m/%Y');">
			</td>
		</tr>
		<tr>
			<td>

			</td>
			<td colspan="5" align="right">
                <img src="<%=request.getContextPath()%>\resources\images\bt_pesquisar_nrml.gif" onmouseup="$('inPrimeiraChamada').value='0';chamaPesquisarRelacionamento()" />
				<img src="<%=request.getContextPath()%>\resources\images\bt_limpar_nrml.gif" style="margin-right:3px;" onmouseup="limpar()" />
			</td>
		</tr>
	</table>

	<div id="divRelacionamento"></div>

</form>
</div>
<vivo:quadroFlutuante id="divComentario" idIframe="ifrmComentario" src="" width="740" height="100" spacesTop="10" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Inserção de Comentário" scroll="auto" />
</body>
</html>