<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<acesso:controlInitEnv/>

<bean:define id="Form"               name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm" type="admsistemas.admArvoreContato.workflow.ConfigGruposProcessosSequencia.ConfigGrupoProcessoSequenciaController.GruposProcessosForm"/>
<bean:define id="aContato"           name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm.gruposProcessos.gruposDisponiveis.grupoProcessoVO.arvoreContatoVO" />
<bean:define id="aGruposDisp"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm.gruposProcessos.gruposDisponiveis.grupoProcessoVO.grupoVOArray" />
<bean:define id="aGruposAbert"       name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm.gruposProcessos.gruposAbertura.grupoProcessoVO.grupoVOArray" />
<bean:define id="aGruposTrat"        name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm.gruposProcessos.gruposTratamento.grupoProcessoVO.grupoVOArray" />
<bean:define id="aGruposRet"         name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gruposProcessosForm.gruposProcessos.gruposRetorno.grupoProcessoVO.grupoVOArray" />
<bean:define id="aFluxosAtendimento" name="Form" property="gruposProcessos.fluxoAtendimentoVOArray"/>

<html>
<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript">
    <!--
        onload = function() {
            parent.oculta_div();
			if ($('sgFluxoAtendimentoAtual') && ($('sgFluxoAtendimentoAtual').value == 'MC1' || $('sgFluxoAtendimentoAtual').value == 'MC2')) {
				manageFluxoAtendimento($('sgFluxoAtendimentoAtual'));
			}
        }

        checkMeuCliente = function(o) {
            if ($('sgFluxoAtendimentoAtual')) {
                if (o.id == 'gruposRetorno'
                        && $('botoes_retorno').style.visibility == 'hidden') {
                    unselectAllOptions(o);
                } else if (o.id == 'gruposTratamento'
                        && $('botoes_tratamento').style.visibility == 'hidden') {
                    unselectAllOptions(o);
                }
            }
        }

        manageFluxoAtendimento = function(o) {
            if (o.value == 'MC1') {
                $('botoes_retorno').style.visibility = 'hidden';
                $('botoes_tratamento').style.visibility = 'hidden';
                $('ordenacao_retorno').style.visibility = 'hidden';
                $('ordenacao_tratamento').style.visibility = 'hidden';
                clearSelectList($('gruposTratamento'));
                clearSelectList($('gruposRetorno'));
                selectAllOptions($('gruposAbertura'));
                TransfereSelecaoLista($('gruposAbertura'), $('gruposTratamento'));
                TransfereSelecaoLista($('gruposAbertura'), $('gruposRetorno'));
                unselectAllOptions($('gruposAbertura'));
                if ($('gruposAbertura').options.length > 0) {
                    $('gruposAbertura').options[0].selected = true;
                    $('gruposAbertura').options[0].selected = false;
                }
            } else if (o.value == 'MC2') {
                $('botoes_retorno').style.visibility = 'hidden';
                $('botoes_tratamento').style.visibility = 'visible';
                $('ordenacao_retorno').style.visibility = 'hidden';
                $('ordenacao_tratamento').style.visibility = 'visible';
                clearSelectList($('gruposRetorno'));
                selectAllOptions($('gruposAbertura'));
                TransfereSelecaoLista($('gruposAbertura'), $('gruposRetorno'));
                unselectAllOptions($('gruposAbertura'));
                if ($('gruposAbertura').options.length > 0) {
                    $('gruposAbertura').options[0].selected = true;
                    $('gruposAbertura').options[0].selected = false;
                }
            } else {
                $('botoes_retorno').style.visibility = 'visible';
                $('botoes_tratamento').style.visibility = 'visible';
                $('ordenacao_retorno').style.visibility = 'visible';
                $('ordenacao_tratamento').style.visibility = 'visible';
            }
        }

    -->
    </script>
</head>
<body>

<acesso:controlHiddenItem nomeIdentificador="adm_grpr_verpagina">
        <form action="AplicaGruposProcessos" id="gruposProcessosForm" name="gruposProcessosForm" method="post">

            <html:hidden name="Form" property="nodeSelecionado" title="nodeSelecionado"/>
            <html:hidden name="aContato" property="idTipoRetornoContato" title="idTipoRetornoContato"/>
            <html:hidden name="aContato" property="inDisponibilidade" title="inDisponibilidade"/>
            <table width="740" border="0" cellspacing="0" cellpadding="0" align="center">
                <tr><td height="7"></td></tr>
                <tr>
                    <td colspan="3" align="center"><b>Configurar Grupos de Processos com Seqüência</b></td>
                </tr>
                <!-- <bean:write name="Form" property="gruposProcessos.sgFluxoAtendimentoAtual" /> -->
                <logic:notEqual name="Form" property="gruposProcessos.sgFluxoAtendimentoAtual" value="">
                <!--
                Se sgFluxoAtendimentoAtual == '' a folha é de workflow normal e não "Meu Cliente"
                Outros valores: MC1, MC2 e MC3
                 -->
                <tr>
                    <td colspan="3" valign="top">
                        <strong>Fluxo de atendimento:</strong>
                        <html:select name="Form"
                                     property="gruposProcessos.sgFluxoAtendimentoAtual"
                                     styleId="sgFluxoAtendimentoAtual"
                                     style="width:200px;margin-top:5px;"
                                     onchange="manageFluxoAtendimento(this)">
                            <html:options collection="aFluxosAtendimento" property="sgFluxoAtendimento" labelProperty="dsFluxoAtendimento" />
                        </html:select>
                    </td>
                </tr>
                </logic:notEqual>
                <tr><td height="4"></td></tr>
                <tr>
                    <td width="334" align="center">Grupos Dispon&iacute;veis</td>
                    <td width="72">&nbsp;</td>
                    <td width="334" align="center">Abertura</td>
                </tr>
                <tr>
                    <td rowspan="7" valign="top">
                        <html:radio name="Form" property="filtroGrupos" value="0" styleClass="radio" onclick="submitFiltro();"> Filtrar</html:radio>
                        <html:radio name="Form" property="filtroGrupos" value="1" styleClass="radio" onclick="submitFiltro();"> Sem Filtro</html:radio>
                        <html:select name="Form" property="gruposDisponiveis" styleId="gruposDisponiveis" multiple="true" size="20" style="width:323px;">
                            <html:options collection="aGruposDisp" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td align="center" valign="top">
                        <table height="80" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <img id="btAbert01"
                                                       src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif"
                                                       style="border:none;"
                                                       onClick="TransfereSelecaoLista(gruposProcessosForm.gruposDisponiveis, gruposProcessosForm.gruposAbertura, this); return false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img id="btAbert02"
                                                       src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif"
                                                       style="border:none;"
                                                       onClick="TransfereSelecaoListaTodos(gruposProcessosForm.gruposDisponiveis, gruposProcessosForm.gruposAbertura, this); return false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img id="btAbert03"
                                                       src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_x_over.gif"
                                                       style="border:none;"
                                                       onClick="LimpaSelecao(gruposProcessosForm.gruposAbertura, aGruposAbert); return false"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top" align="center">
                        <html:select name="Form" property="gruposAbertura" styleId="gruposAbertura" size="10" multiple="true" style="width:310px;height:74px;" ondblclick="document.all['btAbert03'].click();">
                            <html:options collection="aGruposAbert" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td align="center">
                        &nbsp;
                        <img src="/FrontOfficeWeb/resources/images/lupa_bg_transp.gif" onClick="mostraGruposAssociados(); return false" style="border:none;cursor:hand;" hspace="5" vspace="0"/>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center">Tratamento</td>
                </tr>
                <tr>
                    <td align="center" valign="top">
                        <table id="botoes_tratamento" height="80" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <img id="btTrat01"
                                                       src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif"
                                                       style="border:none;"
                                                       onClick="TransfereSelecaoLista(gruposProcessosForm.gruposDisponiveis, gruposProcessosForm.gruposTratamento, this); return false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img id="btTrat02"
                                                       src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif"
                                                       style="border:none;"
                                                       onClick="TransfereSelecaoListaTodos(gruposProcessosForm.gruposDisponiveis, gruposProcessosForm.gruposTratamento); return false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img id="btTrat03"
                                                       src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_x_over.gif"
                                                       style="border:none;"
                                                       onClick="LimpaSelecao(gruposProcessosForm.gruposTratamento, aGruposTrat); return false"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top" align="center">
                        <html:select name="Form"
                                     property="gruposTratamento"
                                     styleId="gruposTratamento"
                                     size="10"
                                     multiple="true"
                                     style="width:310px;height:74px;"
                                     onclick="checkMeuCliente(this)"
                                     ondblclick="document.all['btTrat03'].click();">
                            <html:options collection="aGruposTrat" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td align="center">
                        <div id="ordenacao_tratamento">
                            <vivo:botao id="bt01" width="25px" height="10px" value="+"  styleClass="btTemplate" onclick="grupoAcima(gruposProcessosForm.gruposTratamento)" />
                            <vivo:botao id="bt02" width="25px" height="10px" value="-" styleClass="btTemplate" onclick="grupoAbaixo(gruposProcessosForm.gruposTratamento)" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td align="center">Retorno</td>
                </tr>
                <tr>
                    <td valign="top" align="center">
                        <table id="botoes_retorno" height="80" cellpadding="0" cellspacing="0">
                            <tr>
                                <td>
                                    <img id="btRet01"
                                                       src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_rightaln_over.gif"
                                                       style="border:none;"
                                                       onClick="TransfereSelecaoLista(gruposProcessosForm.gruposDisponiveis, gruposProcessosForm.gruposRetorno, this); return false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img id="btRet02"
                                                       src="/FrontOfficeWeb/resources/images/bt_right_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_right_over.gif"
                                                       style="border:none;"
                                                       onClick="TransfereSelecaoListaTodos(gruposProcessosForm.gruposDisponiveis, gruposProcessosForm.gruposRetorno); return false"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <img id="btRet03"
                                                       src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                                       rolloverImage="/FrontOfficeWeb/resources/images/bt_x_over.gif"
                                                       style="border:none;"
                                                       onClick="LimpaSelecao(gruposProcessosForm.gruposRetorno, aGruposRet); return false"/>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td valign="top" align="center">
                        <html:select name="Form"
                                     property="gruposRetorno"
                                     styleId="gruposRetorno"
                                     size="10"
                                     multiple="true"
                                     style="width:310px;height:74px;"
                                     onclick="checkMeuCliente(this)"
                                     ondblclick="document.all['btRet03'].click();">
                            <html:options collection="aGruposRet" property="codigo" labelProperty="descricao" />
                        </html:select>
                    </td>
                    <td align="center">
                        <div id="ordenacao_retorno">
                            <vivo:botao id="bt01" width="25px" height="10px" value="+"  styleClass="btTemplate" onclick="grupoAcima(gruposProcessosForm.gruposRetorno)" />
                            <vivo:botao id="bt02" width="25px" height="10px" value="-" styleClass="btTemplate" onclick="grupoAbaixo(gruposProcessosForm.gruposRetorno)" />
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="4" align="right">
                    <acesso:controlHiddenItem nomeIdentificador="adm_grpr_gravar">
                        <img vspace="3" src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif" style="border:none;" onClick="Submit(); return false"/>
                    </acesso:controlHiddenItem>
                    </td>
                </tr>
            </table>
            <iframe scrolling="yes" style="visibility:hidden;" name="iframe" height="1px" width="1px"></iframe>
        </form>
    <script language="JavaScript">
        <!--
        // criacao de arrays de permissao de delecao para elementos das listas
        var aGruposAbert = new Array(
        <logic:iterate id="gruposAbert" name="Form" property="gruposProcessos.gruposAbertura.grupoProcessoVO.grupoVOArray">
            <logic:equal name="gruposAbert" property="status" value="1">
                <bean:write name="gruposAbert" property="codigo" format="###"/>,
            </logic:equal>
        </logic:iterate>0);

        var aGruposTrat = new Array(
        <logic:iterate id="gruposTrat" name="Form" property="gruposProcessos.gruposTratamento.grupoProcessoVO.grupoVOArray">
            <logic:equal name="gruposTrat" property="status" value="1">
                <bean:write name="gruposTrat" property="codigo" format="###"/>,
            </logic:equal>
        </logic:iterate>0);

        var aGruposRet = new Array(
        <logic:iterate id="gruposRet" name="Form" property="gruposProcessos.gruposRetorno.grupoProcessoVO.grupoVOArray">
            <logic:equal name="gruposRet" property="status" value="1">
                <bean:write name="gruposRet" property="codigo" format="###"/>,
            </logic:equal>
        </logic:iterate>0);

        function grupoAcima(lista) {
            var oSelected;
            var oSelectedAcima;
            for (i=0; i<lista.options.length; i++) {
                if (lista.options[i].selected) {
                    oSelected = lista.options[i];
                    if (i>0) {
                        oSelectedAcima = lista.options[i-1];
                        oSelectedAcima.swapNode(oSelected);
                    } else {
                        return;
                    }
                }
            }
        }

        function grupoAbaixo(lista) {
            var oSelected;
            var oSelectedAcima;
            for (i=lista.options.length-1; i>=0; i--) {
                if (lista.options[i].selected) {
                    oSelected = lista.options[i];
                    if (i<lista.options.length-1) {
                        oSelectedAcima = lista.options[i+1];
                        oSelectedAcima.swapNode(oSelected);
                    } else {
                        return;
                    }
                }
            }
        }

        function TransfereSelecaoLista(listaOrigem, listaDestino, obj) {
            for(var i = 0; i < listaOrigem.options.length; i++) {
                if (listaOrigem.options[i].selected) {
                    if(!VerificaDuplicidadeLista(listaOrigem.options[i].text, listaDestino)) {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        // Apenas para 'Meu Cliente'
                        if ($('sgFluxoAtendimentoAtual')
								&& (obj && obj.id == 'btAbert01')
                                && listaOrigem.id == 'gruposDisponiveis'
                                && $('sgFluxoAtendimentoAtual').value == 'MC1') {
                            $('gruposTratamento').options[$('gruposTratamento').options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            $('gruposRetorno').options[$('gruposRetorno').options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        } else if ($('sgFluxoAtendimentoAtual')
                                && (obj && obj.id == 'btAbert01')
								&& listaOrigem.id == 'gruposDisponiveis'
                                && $('sgFluxoAtendimentoAtual').value == 'MC2') {
                            $('gruposRetorno').options[$('gruposRetorno').options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        }
                    }
                }
            }
        }

        function TransfereSelecaoListaTodos(listaOrigem, listaDestino) {
            for(var i = 0; i < listaOrigem.options.length; i++) {
                if(!VerificaDuplicidadeLista(listaOrigem.options[i].text, listaDestino)) {
                    listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                }
            }
        }

        function LimpaSelecao(lista, aGrupos) {

            lstImpedido = "Grupos com associação não removidos:\n";
            naoRemovidos = false;
            var limpezaPermitida = true;

            if (lista.id == 'gruposRetorno' && $('botoes_retorno').style.visibility == 'hidden') {
                limpezaPermitida = false;
            }
            if (lista.id == 'gruposTratamento' && $('botoes_tratamento').style.visibility == 'hidden') {
                limpezaPermitida = false;
            }

            if (limpezaPermitida) {
                for(var i = 0; i < lista.options.length; i++) {
                    if(lista.options[i].selected) {
                        if (!VerificaGrupoEmArray(aGrupos, lista.options[i].value)) {
                            lista.options[i] = null;

                            // Apenas para 'Meu Cliente'
                            if ($('sgFluxoAtendimentoAtual')
                                    && lista.id == 'gruposAbertura'
                                    && $('sgFluxoAtendimentoAtual').value == 'MC1') {
                                $('gruposTratamento').options[i] = null;
                                $('gruposRetorno').options[i] = null;
                            } else if ($('sgFluxoAtendimentoAtual')
                                    && lista.id == 'gruposAbertura'
                                    && $('sgFluxoAtendimentoAtual').value == 'MC2') {
                                $('gruposRetorno').options[i] = null;
                            }

                            i = i - 1;

                        } else {
                            lstImpedido +=  lista.options[i].text+"\n";
                            naoRemovidos = true;
                        }
                    }
                }
                if (naoRemovidos) {
                    alert(lstImpedido);
                }
            }
        }

        function VerificaGrupoEmArray (aGrupos, idGrupo) {
            for(var i = 0; i < aGrupos.length; i++) {
                if (aGrupos[i] == idGrupo) {
                    return true;
                }
            }
            return false;
        }

        function VerificaDuplicidadeLista(valor, lista) {
            for(var i = 0; i < lista.options.length; i++) {
                if(lista.options[i].text == valor) {
                    return(true);
                }
            }
            return(false);
        }

        function Submit() {
            if( top.dvAnimarAguarde != null ) top.startAnimation();

            for(var i = 0; i < gruposProcessosForm.gruposDisponiveis.options.length; i++) {
                gruposProcessosForm.gruposDisponiveis.options[i].selected = false;
            }

            <logic:notEqual name="Form" property="gruposProcessos.sgFluxoAtendimentoAtual" value="">

            // Validação para folhas com fluxo Meu Cliente de atendimento

            if (gruposProcessosForm.gruposAbertura.options.length == 0) {
                alert("Selecione pelo menos um grupo para Abertura.");
                return false;
            }

            if (gruposProcessosForm.gruposTratamento.options.length == 0) {
                alert("Selecione pelo menos um grupo para Tratamento.");
                return false;
            }

            if (gruposProcessosForm.gruposRetorno.options.length == 0) {
                alert("Selecione pelo menos um grupo para Retorno!");
                return false;
            }


            </logic:notEqual>

            <logic:equal name="Form" property="gruposProcessos.sgFluxoAtendimentoAtual" value="">

            // Validação para folhas com fluxo normal de atendimento

            if (document.forms["gruposProcessosForm"].inDisponibilidade.value > 0){
                if (gruposProcessosForm.gruposAbertura.options.length == 0) {
                    alert("Selecione pelo menos um grupo de Abertura!");
                    return false;
                }
            }else{
                if (gruposProcessosForm.gruposAbertura.options.length == 0) {
                    if (gruposProcessosForm.gruposTratamento.options.length != 0) {
                        alert("Selecione pelo menos um grupo de Abertura ou não selecione grupos de Tratamento!");
                        return false;
                    }
                    if (gruposProcessosForm.gruposRetorno.options.length != 0) {
                        alert("Selecione pelo menos um grupo de Abertura ou não selecione grupos de Retorno!");
                        return false;
                    }
                }
            }

            if (gruposProcessosForm.gruposTratamento.options.length == 0) {
                if (gruposProcessosForm.gruposRetorno.options.length != 0) {
                    alert("Selecione pelo menos um grupo de Tratamento ou não selecione grupos de Retorno!");
                    return false;
                }
            }

            if (document.forms["gruposProcessosForm"].idTipoRetornoContato.value > 0){
                if (gruposProcessosForm.gruposRetorno.options.length == 0) {
                    alert("Existe algum atendimento configurado para Retorno por parte do Grupo de Retorno. Selecione pelo menos um grupo de Retorno!");
                    return false;
                }
            }
            </logic:equal>

            for(var i = 0; i < gruposProcessosForm.gruposAbertura.options.length; i++) {
                gruposProcessosForm.gruposAbertura.options[i].selected = true;
            }

            for(var i = 0; i < gruposProcessosForm.gruposTratamento.options.length; i++) {
                gruposProcessosForm.gruposTratamento.options[i].selected = true;
            }

            for(var i = 0; i < gruposProcessosForm.gruposRetorno.options.length; i++) {
                gruposProcessosForm.gruposRetorno.options[i].selected = true;
            }

            document.forms["gruposProcessosForm"].target = "iframe";
            document.forms["gruposProcessosForm"].action = "AplicaGruposProcessos.do";
            parent.mostrar_div();
            document.forms["gruposProcessosForm"].submit();

        }

        function submitFiltro() {
            parent.mostrar_div();
            //Monta o path seleção
            document.forms["gruposProcessosForm"].target = "";
            document.forms["gruposProcessosForm"].action = 'begin.do?idContato=<bean:write name="Form" property="nodeSelecionado"/>&dsPath=<%=Form.getDsPath().replaceAll("\\\\","\\\\\\\\")%>';
            document.forms["gruposProcessosForm"].submit();
        }

        //Detalhe do processo
        function mostraGruposAssociados() {
            //Inicializa a animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

            //Exibe o quadro flutuante
            parent.dvGrupoAssociado.style.display = '';
            srcPg = 'AssociacaoGrupos/begin.do?idContato=<bean:write name="Form" property="nodeSelecionado"/>&dsPath=<%=Form.getDsPath().replaceAll("\\\\","\\\\\\\\")%>';
            parent.document.getElementById('ifrmGrupoAssociado').src=srcPg;

            //Inicializa a animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        }

        document.body.style.backgroundColor = '#ededed';

        //Fim animação
        if( top.dvAnimarAguarde != null ) top.stopAnimation();
        -->
    </script>
</acesso:controlHiddenItem>

</body>
</html>
