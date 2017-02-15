<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"          name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm" type="workflow.Relacionamento.RelacionamentoController.RelacionamentoForm" />
<bean:define id="aEstados"      name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.wfEstados.WFEstadoVOArray" />
<bean:define id="aSubEstados"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.atendimentoPesquisaVO.WFSubEstados.WFSubEstadoVOArray" />
<%!
private boolean isClienteVivo(ParametrosVO parametros) {
    if (parametros.getNrLinha() != null && !ConstantesCRM.SVAZIO.equals(parametros.getNrLinha()) &&
        parametros.getIdConta() != null && !ConstantesCRM.SVAZIO.equals(parametros.getIdConta())) {
            return true;
    } else {
        return false;
    }
}
public String getRegional(String foneArea) {
	if(foneArea!=null && !ConstantesCRM.SVAZIO.equals(foneArea)){
	    int ddd = Integer.parseInt(foneArea);
	    if (ddd > 10 && ddd < 20)         { return "SP";
	    } else if (ddd > 20 && ddd <  29) { return "RJ_ES";
	    } else if (ddd > 30 && ddd <  39) { return "MG";
	    } else if (ddd > 40 && ddd <  50) { return "PR_SC";
	    } else if (ddd > 50 && ddd <  60) { return "RS";
	    } else if (ddd > 60 && ddd <  70) { return "CON";
	    } else if (ddd > 70 && ddd <  90) { return "BA_SE";
	    } else if (ddd > 90 && ddd < 100) { return "CON";
	    }
	}
    return ConstantesCRM.SVAZIO;
}
%>
<% ParametrosVO parametros = (ParametrosVO) session.getAttribute(ConstantesCRM.SPARAMETROS); %>
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
        var relacionamentoHistorico = false;
        var protocoloAtivo = '';

        if (parent.inVerAbaRelacionamento)
            inVerPagina = 1;

        arvoreContato = function() {
            if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
            desativar_combos();
            dvArvore.style.display = '';
            $('relacionamentoForm').method = "POST";
            $('relacionamentoForm').action = "obterArvoreContato.do";
            $('relacionamentoForm').target = "ifrmArvore";
            $('relacionamentoForm').submit();
        };

        pesquisarRelacionamento1 = function() {
			var params = getFormParameters();
			new Ajax.Updater('divRelacionamento', '<%=request.getContextPath()%>/cliente/TelaInicial/pesquisarRelacionamento1.do', {
				method: 'post',
				parameters: params,
				evalScripts: true,
				onCreate: function() {
					if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();
				}, onSuccess: function() {
					if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
				}, onComplete: function() {
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
        };

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
        };

        chamaPesquisaHistorico = function(inMG) {
            window.top.frameApp.showPopupTI('Pesquisa Histórico', 800, 600, null, '<%=request.getContextPath()%>/cliente/TelaInicial/getPesquisaHistorico.do?' + getFormParameters().toQueryString() + '&inHistorico=true&inHistoricoMG=' + inMG, null);
        };

        pesquisarRelacionamento = function() {

            dtInicioDe  = $F('dtAberturaInicio');
            dtInicioAte = $F('dtAberturaFim');
            dtFimDe     = $F('dtFechamentoInicio');
            dtFimAte    = $F('dtFechamentoFim');

			// Variável para verificaçao de carregamento da página
            setaRadio = false;

			if ((dtInicioDe != '' && dtInicioAte == '') || (dtInicioDe == '' && dtInicioAte != '')) {
				alert('Para pesquisa por período de abertura é necessário informar a data inicial e a data final.');
				return;
			}

			if ((dtFimDe != '' && dtFimAte == '') || (dtFimDe == '' && dtFimAte != '')) {
				alert('Para pesquisa por período de fechamento é necessário informar a data inicial e a data final.');
				return;
			}

            if (!(validaDataFinal(dtInicioDe, dtInicioAte)) && dtInicioDe != '' && dtInicioAte != '') {
                $('dtAberturaInicio').focus();
                alert('Por favor, digite corretamente as datas de início e fim de abertura.');
                return;
            }

			if (dtInicioDe != '' && dtInicioAte != '') {
                arD1 = dtInicioDe.split("/");

				var dt1 = new Date();
                dt1.setFullYear(arD1[2],arD1[1]-1,arD1[0]);

                arD2 = dtInicioAte.split("/");
				var dt2 = new Date();
                dt2.setFullYear(arD2[2],arD2[1]-1,arD2[0]);

				var qtDiasDecorridos = diasDecorridos(dt1, dt2);
				if (qtDiasDecorridos > 30) {
					alert('O intervalo de pesquisa não pode ser superior a 30 dias.');
					return;
				}

				var hoje = new Date();
				qtDiasDecorridos = diasDecorridos(dt1, hoje);
				if (qtDiasDecorridos > 180) {
					alert('A data inicial não pode ser inferior a 180 dias.');
					return;
				}

			}

            if (!(validaDataFinal(dtFimDe,dtFimAte)) && dtFimDe != '' && dtFimAte != '') {
                $('dtFechamentoInicio').focus();
                alert('Por favor, digite corretamente as datas de início e fim de fechamento.');
                return;
            }

			if (dtFimDe != '' && dtFimAte != '') {
                arD1 = dtFimDe.split("/");

				var dt1 = new Date();
                dt1.setFullYear(arD1[2],arD1[1]-1,arD1[0]);

                arD2 = dtFimAte.split("/");
				var dt2 = new Date();
                dt2.setFullYear(arD2[2],arD2[1]-1,arD2[0]);

				var qtDiasDecorridos = diasDecorridos(dt1, dt2);
				if (qtDiasDecorridos > 30) {
					alert('O intervalo de pesquisa não pode ser superior a 30 dias.');
					return;
				}

				var hoje = new Date();
				qtDiasDecorridos = diasDecorridos(hoje, dt1);
				if (qtDiasDecorridos > 180) {
					alert('A data inicial não pode ser inferior a 180 dias.');
					return;
				}
			}

            var params = getFormParameters();

			new Ajax.Updater('divRelacionamento', '<%=request.getContextPath()%>/cliente/TelaInicial/pesquisarRelacionamento.do', {
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
        };

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
			if ($('contato')) {
                params.set('contato', $F('contato'));
            }
			if ($('estadoSelecionado')) {
                params.set('estadoSelecionado', $F('estadoSelecionado'));
            }

            <% // Chamada via ScreenPop (Linha não cadastrada)
            if ("26".equals(Form.getIdPessoaDePara()) && parametros.getNrLinha() != null
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
		};

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
        };

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
        };

        update = function(row) {
            top.atendimentoProcesso();
        };

        validaData = function(data) {
            if (data.value == '')
                return false;
            if (!validate_date(data.value)) {
                data.value = '';
                data.focus();
                alert("Data inválida");
            }
        };

        obtemDataAbertura = function() {

			data = new Date();
            dia = data.getDate();
            mes = data.getMonth() + 1;
            ano = data.getFullYear();

            if (dia < 16) {
                if (mes == 5 || mes == 7 || mes == 10 || mes == 12) // o mes anterior tem 30 dias
                    dia += 15;
                else if (mes == 3)  //o mes anterior é fevereiro ****falta fazer pra qd for ano bissexto****
                    dia += 13;
                else {
                    if (mes == 1) {  //quando for janeiro
                        mes = 13;
                        ano -= 1;
                    }
                    dia += 16;
                }
                mes -= 1;
            }
            else
                dia -= 15;

            dtAbertura.value = dia + "/" + mes + "/" + ano;
        };

        // Faz o radio de Linha ser selecionado pois é a pesquisa default
        setPesquisaLinha = function() {
            if (setaRadio) {
				if ($('divRadioPessoa')) $('divRadioPessoa').show();
				if ($('divRadioLinha')) $('divRadioLinha').show();
				if ($('radioTpLinhaCliente')) $('radioTpLinhaCliente').checked = true;
            }
        };

        // Faz o radio de Pessoa ser selecionado
        setPesquisaPessoa = function() {
            if (setaRadio) {
				if ($('divRadioPessoa')) $('divRadioPessoa').show();
				if ($('divRadioLinha')) $('divRadioLinha').hide();
				if ($('divRadioLinhaCliente')) $('divRadioLinhaCliente').hide();
				if ($('divRadioConta')) $('divRadioConta').hide();
				if ($('radioTpPessoa')) $('radioTpPessoa').checked = true;
            }
        };

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
        };

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
        };

        inserirComentario = function() {
            $('divComentario').show();
            ifrmComentario.location.href = '<%=request.getContextPath()%>/cliente/TelaInicial/inserirComentarioTracking.jsp';
        };

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
				top.frameApp.$('tipoRelacionamento').disabled = true;
				top.frameApp.$('idPos').disabled = true;
				top.frameApp.idChamadaTelefonica = "";
                top.frameApp.identificado = true;

                if (top.frameApp.pesquisaEfetuada
    		        && top.frameApp.inIdPosAutomatico
                    && !top.frameApp.inProspect) {
                        window.top.frameApp.submitIdPos();
                }

                window.top.frameApp.guardaIds();

			<% } else if (!"26".equals(Form.getIdPessoaDePara()) || parametros.getNrLinha() != null) { %>

                var params = $H();
                params.set('tpPesquisa', (linha && cliente) ? 4 : linha ? 2 : 3);
                params.set('inPrimeiraChamada', 1);

                <% // Chamada via ScreenPop (Linha não cadastrada)
                   if ("26".equals(Form.getIdPessoaDePara())
                        && parametros.getNrLinha() != null
                        && !ConstantesCRM.SVAZIO.equals(parametros.getNrLinha())) { %>
                top.frameApp.$('tipoRelacionamento').value = 6;
                params.set('tpPesquisa', 2);
                <% } %>

                if (top.frameApp.pesquisaEfetuada
                    && top.frameApp.inIdPosAutomatico
                    && !top.frameApp.inProspect) {
                        window.top.frameApp.submitIdPos();
                }

                window.top.frameApp.guardaIds();

				if (top.frameApp.$('divContentContainer').visible()) {
	                new Ajax.Updater('divRelacionamento', '<%=request.getContextPath()%>/cliente/TelaInicial/pesquisarRelacionamento.do', {
			            method: 'post',
						parameters: params,
			            evalScripts: true,
			            onComplete: function() {
                            if ($('inPrimeiraChamada')) {
							$('inPrimeiraChamada').value  = '';
                            }
                            if ($('vertical_container')) {
                            var bottomAccordion = new accordion('vertical_container', {onActivate : getProcessosByProtocolo});
                            }
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
        };

		obtemDetalhes = function(inACS, idAtendimento, inPortout) {

	        var height = 570;
	        var src = '';
			if (top.frameApp.dvAnimarAguarde != null) top.frameApp.startAnimation();

	        if (inACS) {
	            src = '<%=request.getContextPath()%>/workflow/AtendimentoDetalheACS/begin.do?IDATENDIMENTO='+idAtendimento;
	        } else if (inPortout) {
	            height = 550;
	            src = '<%=request.getContextPath()%>/workflow/AtendimentoFila/Portabilidade/getDetalheProcesso.do?idAtendimento=' + idAtendimento;
	        } else {
	            src = '<%=request.getContextPath()%>/workflow/AtendimentoDetalhe/begin2.do?idAtendimento='+idAtendimento+'&inMenu=N&fila=';
			}
	        window.top.frameApp.showPopupTI("Atendimento", 800, height, null, src);
	    };


        getAtendimentoProtocolo = function(nrProt) {
            top.frameApp.$('ti_comboPesquisa').selectedIndex = 13;      // Protocolo
            top.frameApp.$('ti_inputValorPesquisa').value = nrProt;
            top.frameApp.isScreenPop = false;
            top.frameApp.inIdPosAutomatico = true;
            top.frameApp.mostraIdPos();
            top.frameApp.nrProtocoloScreenPop = '';
            top.frameApp.validaPesquisa(true);
        };

        function getProcessosByProtocolo(elem) {
            var myElement = $(elem);
            var elemid = myElement.id;
            var estado = myElement.estado;
            var nrProtocolo = elemid.substring(12, elemid.length);
            protocoloAtivo = nrProtocolo;
            var inAtendimendoOuPendente = (estado.toLowerCase() == 'em atendimento' || estado.toLowerCase() == 'pendente') ? true : false;

            if (inAtendimendoOuPendente || myElement.innerHTML == '') {
                elem.innerHTML = '&nbsp;Carregando...';
                var ajax = new Ajax.Request('<%=request.getContextPath()%>/cliente/TelaInicial/getProcessosByProtocolo.do' , {
                        onSuccess :  ajax_text,
                        parameters : 'nrProtocolo=' + nrProtocolo
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

<body id="bodyRelacionamento" style="width:760px;overflow-x:hidden">

<html:form action="/cliente/TelaInicial/pesquisarRelacionamento.do" styleId="relacionamentoForm">
	<html:hidden name="Form" property="idPessoaDePara" styleId="idPessoaDePara" />
	<html:hidden name="Form" property="tpRelacionamento" styleId="tpRelacionamento" />
	<html:hidden name="Form" property="idPessoaLinhaHistorico" styleId="idPessoaLinhaHistorico" />
	<html:hidden name="Form" property="user" styleId="user" />
	<html:hidden name="Form" property="idContato" styleId="idContato" />
	<html:hidden name="Form" property="inPrimeiraChamada" styleId="inPrimeiraChamada" />

	<table id="tablePesquisa" width="760" border="0">
		<tr>
			<td width="130">
				<div id="divRadioPessoa">
					<html:radio styleClass="radio" name="Form" property="tpPesquisa" styleId="radioTpPessoa" value="3" />
					<label for="radioTpPessoa">
						<logic:equal property="idPessoaDePara" name="Form" value="26">
							Não Identificado
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
				<html:text name="Form" property="dtAberturaInicio" styleId="dtAberturaInicio" onkeyup="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAberturaInicio', '%d/%m/%Y');"/>
			</td>
			<td width="115">
				Até <html:text name="Form" property="dtAberturaFim" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtAberturaFim', '%d/%m/%Y');"/>
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
				<img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="arvoreContato()" border="0"/>
			</td>
            <% } else { %>
            <td colspan="2"></td>
            <% }%>
			<td>Fechamento de:</td>
			<td>
				<html:text name="Form" property="dtFechamentoInicio" styleId="dtFechamentoInicio" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFechamentoInicio', '%d/%m/%Y');"/>
			</td>
			<td>
				Até <html:text name="Form" property="dtFechamentoFim" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaData(this);" style="width: 70px" /><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:hand;" title="Calendário" onclick="return showCalendar('dtFechamentoFim', '%d/%m/%Y');"/>
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
			<td colspan="2">
				<html:select name="Form" property="estadoSelecionado" styleId="estadoSelecionado" style="width:200px">
					<html:option value=''>-- Selecione --</html:option>
					<html:option value='Concluído'>Concluído</html:option>
					<html:option value='Em Atendimento'>Em Atendimento</html:option>
					<html:option value='Encerrado'>Encerrado</html:option>
					<html:option value='Parcialmente Concluído'>Parcialmente Concluído</html:option>
					<html:option value='Pendente'>Pendente</html:option>
				</html:select>
			</td>
            <td colspan="2">
                <% if ("MG".equals(getRegional(parametros.getNrCodArea()))) {%>
                <img src="<%=request.getContextPath()%>/resources/images/bt_prot_mg_anteriores_nrml.gif" style="cursor:pointer" onmouseup="chamaPesquisaHistorico(true)" />
                <% } %>
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
				<img src="<%=request.getContextPath()%>\resources\images\bt_histor_nrml.gif" onmouseup="$('inPrimeiraChamada').value='1';chamaPesquisaHistorico(false)" />
                <img src="<%=request.getContextPath()%>\resources\images\bt_pesquisar_nrml.gif" onmouseup="$('inPrimeiraChamada').value='0';chamaPesquisarRelacionamento()" />
				<img src="<%=request.getContextPath()%>\resources\images\bt_limpar_nrml.gif" style="margin-right:3px;" onmouseup="limpar()" />
			</td>
		</tr>
	</table>
	<div id="divRelacionamento"></div>
</html:form>

<vivo:quadroFlutuante id="dvArvore"      idIframe="ifrmArvore"     width="720" height="300" spacesTop="90" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();" />
<vivo:quadroFlutuante id="divComentario" idIframe="ifrmComentario" src="" width="740" height="100" spacesTop="10" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Inserção de Comentário" scroll="auto" />

</body>
</html>