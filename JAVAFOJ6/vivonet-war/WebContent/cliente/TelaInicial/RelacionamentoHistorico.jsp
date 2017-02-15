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
<bean:define id="aEstados"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.wfEstados.WFEstadoVOArray" />
<bean:define id="aSubEstados"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.atendimentoPesquisaVO.WFSubEstados.WFSubEstadoVOArray" />

<% ParametrosVO parametros = (ParametrosVO) session.getAttribute(ConstantesCRM.SPARAMETROS); %>

<%!
private boolean isClienteVivo(ParametrosVO parametros) {
    if (parametros.getNrLinha() != null && !ConstantesCRM.SVAZIO.equals(parametros.getNrLinha()) &&
        parametros.getIdConta() != null && !ConstantesCRM.SVAZIO.equals(parametros.getIdConta())) {
            return true;
    } else {
        return false;
    }
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/TelaInicialAbaRelacionamento.css"/>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoheader.js" ></script>
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
        
        
        obtemDetalhesHistorico  = function(inACS, idAtendimento, inPortout) {
	        var src = '';
			if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
            src = '/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin3.do?idAtendimento='+idAtendimento+'&inMenu=N&fila=';
            top.frameApp.$('divDetalheHistorico').show();
            top.frameApp.$('iframeDetalheHistorico').src = src;
        }

        arvoreContato = function() {
            if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
            desativar_combos();
            dvArvore.style.display = '';
            $('relacionamentoForm').method = "POST";
            $('relacionamentoForm').action = "obterArvoreContato.do";
            $('relacionamentoForm').target = "ifrmArvore";
            $('relacionamentoForm').submit();
        }

        chamaPesquisarRelacionamento = function() {
			<logic:notEqual property="idPessoaDePara" name="Form" value="26">
			if ($('radioTpPessoa').checked) {
				if (top.frameApp.$('tipoRelacionamento').disabled == false) {
                    alert("� necess�rio identifica��o da liga��o para a realiza��o de pesquisa por pessoa.");
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

			// Vari�vel para verifica�ao de carregamento da p�gina
            setaRadio = false;

			if ((dtInicioDe != '' && dtInicioAte == '')
				|| (dtInicioDe == '' && dtInicioAte != '')) {
				alert('Para pesquisa por per�odo de abertura � necess�rio informar a data inicial e a data final.');
				return;
			}

			if ((dtFimDe != '' && dtFimAte == '')
				|| (dtFimDe == '' && dtFimAte != '')) {
				alert('Para pesquisa por per�odo de fechamento � necess�rio informar a data inicial e a data final.');
				return;
			}

            if (!(validaDataFinal(dtInicioDe, dtInicioAte)) && dtInicioDe != '' && dtInicioAte != '') {
                $('dtAberturaInicio').focus();
                alert('Por favor, digite corretamente as datas de in�cio e fim de abertura.');
                return;
            }

			if (dtInicioDe != '' && dtInicioAte != '') {
				var dt1 = new Date();
				dt1.setFullYear(dtInicioDe.substring(6,10), parseInt(dtInicioDe.substring(3,5))-1,dtInicioDe.substring(0,2));
				var dt2 = new Date();
				dt2.setFullYear(dtInicioAte.substring(6,10), parseInt(dtInicioAte.substring(3,5))-1,dtInicioAte.substring(0,2));

				var qtDiasDecorridos = diasDecorridos(dt1, dt2);
				if (qtDiasDecorridos > 30) {
					alert('O intervalo de pesquisa n�o pode ser superior a 30 dias.');
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
                alert('Por favor, digite corretamente as datas de in�cio e fim de fechamento.');
                return;
            }

			if (dtFimDe != '' && dtFimAte != '') {
				var dt1 = new Date();
				dt1.setFullYear(dtFimDe.substring(6,10), parseInt(dtFimDe.substring(3,5))-1,dtFimDe.substring(0,2));
				var dt2 = new Date();
				dt2.setFullYear(dtFimAte.substring(6,10), parseInt(dtFimAte.substring(3,5))-1,dtFimAte.substring(0,2));

				var qtDiasDecorridos = diasDecorridos(dt1, dt2);
				if (qtDiasDecorridos > 30) {
					alert('O intervalo de pesquisa n�o pode ser superior a 30 dias.');
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
					alert('A data inicial n�o pode ser superior a 730 dias.');
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
					var bottomAccordion = new accordion('vertical_container', {});
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
			params.set('idContato', $F('idContato'));
			params.set('inPrimeiraChamada', $F('inPrimeiraChamada'));
			params.set('dtAberturaInicio', $F('dtAberturaInicio'));
			params.set('dtAberturaFim', $F('dtAberturaFim'));
			params.set('nrProtocolo', $F('nrProtocolo'));
			params.set('dtFechamentoInicio', $F('dtFechamentoInicio'));
			params.set('dtFechamentoFim', $F('dtFechamentoFim'));
            params.set('historicoRelacionamento', 'true');
			if ($('contato')) {
                params.set('contato', $F('contato'));
            }
			if ($('estadoSelecionado')) {
                params.set('estadoSelecionado', $F('estadoSelecionado'));
            }

            <% // Chamada via ScreenPop (Linha n�o cadastrada)
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
            if ($('contato')) {
                $('contato').value = '';
            }
            $('idContato').value          = '';
            if ($('estadoSelecionado')) {
                $('estadoSelecionado').value  = '';
            }
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
            if ($('contato')) {
                $('contato').value            = '';
            }
            $('idContato').value          = '';
            $('inPrimeiraChamada').value  = '';
            if ($('estadoSelecionado')) {
                $('estadoSelecionado').value  = '';
            }
            $('radioTpLinha').checked = true;
        }

        validaData = function(data) {
            if (data.value == '')
                return false;
            if (!validate_date(data.value)) {
                data.value = '';
                data.focus();
                alert("Data inv�lida");
            }
        }

        // Faz o radio de Linha ser selecionado pois � a pesquisa default
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
		        //Inicializa a pesquisa
		        setPesquisaLinha();
		    } else {
		        //Inicializa a pesquisa
		        setPesquisaPessoa();
		    }

			<% if ("26".equals(Form.getIdPessoaDePara()) && parametros.getNrLinha() == null) { %>

                $('radioTpPessoa').checked = true;

            <%  } else if (!"26".equals(Form.getIdPessoaDePara()) || parametros.getNrLinha() != null) { %>

                var params = $H();
                params.set('tpPesquisa', (linha && cliente) ? 4 : linha ? 2 : 3);
                params.set('inPrimeiraChamada', 1);
                params.set('historicoRelacionamento', true);

                <% // Chamada via ScreenPop (Linha n�o cadastrada)
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
							var bottomAccordion = new accordion('vertical_container', {});
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
            
            
            
            //if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }

		obtemDetalhes = function(inACS, idAtendimento, inPortout) {

	        var height = 570;
	        var src = '';
			if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();

            src = '/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin3.do?idAtendimento='+idAtendimento+'&inMenu=N&fila=';
	        /*if (inACS) {
	            src = '/FrontOfficeWeb/workflow/AtendimentoDetalheACS/begin.do?IDATENDIMENTO='+idAtendimento;
	        } else if (inPortout) {
	            height = 550;
	            src = '/FrontOfficeWeb/workflow/AtendimentoFila/Portabilidade/getDetalheProcesso.do?idAtendimento=' + idAtendimento;
	        } else {
	            src = '/FrontOfficeWeb/workflow/AtendimentoDetalhe/begin2.do?idAtendimento='+idAtendimento+'&inMenu=N&fila=';
			}*/
            top.frameApp.createNewPopUp('detalhesRelacionamento', 'Atendimento', 800, 600, null, src, true, null, true);
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

    </script>

</head>

<body id="bodyRelacionamento" style="width:800px;overflow-x:hidden;height:580px;">
<div style="width:795px;height:590px;overflow-x:hidden;overflow-y:auto">
<form action="pesquisarRelacionamento.do" id="relacionamentoForm" name="relacionamentoForm" method="post">

	<html:hidden name="Form" property="idPessoaDePara" styleId="idPessoaDePara" />
	<html:hidden name="Form" property="tpRelacionamento" styleId="tpRelacionamento" />
	<html:hidden name="Form" property="idPessoaLinhaHistorico" styleId="idPessoaLinhaHistorico" />
	<html:hidden name="Form" property="user" styleId="user" />
	<html:hidden name="Form" property="idContato" styleId="idContato" />
	<html:hidden name="Form" property="inPrimeiraChamada" styleId="inPrimeiraChamada" />
    <html:hidden name="Form" property="historicoRelacionamento" styleId="historicoRelacionamento" />

	<table id="tablePesquisa" width="760" border="0">
		<tr>
			<td width="130">
				<div id="divRadioPessoa">
					<html:radio styleClass="radio" name="Form" property="tpPesquisa" styleId="radioTpPessoa" value="3" />
					<label for="radioTpPessoa">
						<logic:equal property="idPessoaDePara" name="Form" value="26">
							N�o Identificado
						</logic:equal>
						<logic:notEqual property="idPessoaDePara" name="Form" value="26">
							Pessoa
						</logic:notEqual>
					</label>
				</div>
			</td>
			<td width="60">Protocolo:</td>
			<td width="250">
				<html:text name="Form" property="nrProtocolo" styleId="nrProtocolo" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/>
			</td>
			<td width="90">Abertura de:</td>
			<td width="115">
				<html:text name="Form" property="dtAberturaInicio" styleId="dtAberturaInicio" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calend�rio" onclick="return showCalendar('dtAberturaInicio', '%d/%m/%Y');">
			</td>
			<td width="115">
				At� <html:text name="Form" property="dtAberturaFim" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calend�rio" onclick="return showCalendar('dtAberturaFim', '%d/%m/%Y');">
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
			<% if (isClienteVivo(parametros)) { %>
            <td>Contato:</td>
			<td>
				<html:text name="Form" property="contato" styleId="contato" size="45" style="width:210px" readonly="true"/>
				<img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="arvoreContato()" border="0">
			</td>
            <% } else { %>
            <td colspan="2"></td>
            <% }%>
			<td>Fechamento de:</td>
			<td>
				<html:text name="Form" property="dtFechamentoInicio" styleId="dtFechamentoInicio" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calend�rio" onclick="return showCalendar('dtFechamentoInicio', '%d/%m/%Y');">
			</td>
			<td>
				At� <html:text name="Form" property="dtFechamentoFim" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calend�rio" onclick="return showCalendar('dtFechamentoFim', '%d/%m/%Y');">
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

			<% if (isClienteVivo(parametros)) { %>
			<td>
				Estado
			</td>
			<td colspan="4">
				<html:select name="Form" property="estadoSelecionado" styleId="estadoSelecionado" style="width:200px">
					<html:option value=''>-- Selecione --</html:option>
					<html:option value='Conclu�do'>Conclu�do</html:option>
					<html:option value='Encerrado'>Encerrado</html:option>
				</html:select>
			</td>
			<% } else { %>
			<td colspan="5"></td>
			<% } %>
		</tr>
		<tr>
			<td>
				<div id="divRadioConta">
					<logic:notEqual property="idPessoaDePara" name="Form" value="26">
						<html:radio styleClass="radio" name="Form" property="tpPesquisa" styleId="radioTpConta" value="5" />
						<label for="radioTpConta">Conta</label>
					</logic:notEqual>
				</div>
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
<vivo:quadroFlutuante id="dvArvore"      idIframe="ifrmArvore"     width="720" height="300" spacesTop="90" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="�rvore de Contato" scroll="auto" onclick="ativar_combos();" />
<vivo:quadroFlutuante id="divComentario" idIframe="ifrmComentario" src="" width="740" height="100" spacesTop="10" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Inser��o de Coment�rio" scroll="auto" />

</body>
</html>