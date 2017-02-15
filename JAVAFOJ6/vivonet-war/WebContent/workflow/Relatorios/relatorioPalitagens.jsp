<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="gruposVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFGrupoVOArray"/>
<bean:define id="sessaoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSessaoVOArray"/>
<bean:define id="prazoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.alertaVOArray"/>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
<netui-template:setAttribute name="modulo" value="Workflow"/>
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/xtree.css"  />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
    <script type="text/javascript" language="javascript">
        function validarCampos() {
            var f        = document.forms[0];
            var dtInicio = f.dtInicio;
            var dtFim    = f.dtFim;
    
            if (dtInicio.value == "") {
                alert("Selecione a Data de Início!\n");
                return false;
            }
            if (dtFim.value == "") {
                alert("Selecione a Data Final!\n");
                return false;
            }
    
            if (!validaDataFinal(dtInicio.value,dtFim.value)) {
                alert("Data Inicial maior que Data Final!\n");
                return false;
            }
    
            if ( !valida1mes(dtInicio,dtFim) ) {
                alert("Intervalo de datas maior que 30 dias!\n");
                return false;
            }
    
            if ($('folhasSelecionadas').options.length == 0) {
                alert('Por favor, selecione ao menos uma folha de contato.');
                return false;
            }

            if ($F('grupoSel') == -1 && $F('dsLogin')=="") {
                alert("Por favor, selecione ao menos um grupo ou login.");
                return false;
            }
            f.flQuantitativo.value = $('flQuantitativo').checked ? "1":"0";
            return true;
        }

        function submitGerar() {
            f = document.forms[0];
            if (!validarCampos()) {
                return false;
            }
    
            //Inicio animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
            var objSelect = $('folhasSelecionadas');
            for(i = objSelect.options.length-1; i>=0; i--) objSelect.options[i].selected = true;
    
            f.method = "POST";
            f.action = "gerarRelatorioPalitagens.do";
            f.target = "";
            f.submit();
        }
    
        function submitArquivosGerados() {
            var f = document.forms[0];
            f.action = "palitagensArquivosGerados.do";
            f.target = "";
            f.submit();
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
    
        function expandirNo(idContato, nmContato, idContatoPai, inDisponibilidade, idNomeContato, nrNivel, inFolha, dsPath) {
    
            if (inDisponibilidade == '1') {
                if(document.getElementById('btHab'))
                    document.getElementById('btHab').style.display='none';
                if(document.getElementById('btDes'))
                    document.getElementById('btDes').style.display='block';
            } else {
                if(document.getElementById('btHab'))
                    document.getElementById('btHab').style.display='block';
                if(document.getElementById('btDes'))
                    document.getElementById('btDes').style.display='none';
            }
    
            $('idContatoOrigem').value = inFolha+"|"+idContato;
            $('nmContatoOrigem').value = nmContato;
    
            if (!tree.getSelected().isAddEnabled()) {
                return;
            }
    
            var f = document.forms[0];
            f.method = "POST";
            f.action = "montaArvoreParte.do";
            f.target = "iframeArvoreContato";
            f.submit();
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    
        }
    
        function capturaParametrosContatoRaiz(){$('idContatoOrigem').value="-1"}
        
        function capturaParametrosContato(idContato, nmContato, idContatoPai, inDisponibilidade, idNomeContato, nrNivel, inFolha, dsPath) {
            document.forms[0].idContatoOrigem.value = inFolha+"|"+nmContato+"|"+idContato;
            document.forms[0].nmContatoOrigem.value = nmContato;
        }
    
        function associarFolha() {
            var nmFolhaArvore, objSelect, flFolha, idObj, flObjectFolha;
            var idFolhaArvore = $F("idContatoOrigem");
            flObjectFolha = false;
            if (idFolhaArvore != "" && idFolhaArvore != "-1") {
                flObjectFolha = (idFolhaArvore.split("|")[0]=="1")?true:false;
            }
            if (idFolhaArvore != "" && idFolhaArvore != "-1" || !flObjectFolha) {
                idObj         = idFolhaArvore.split("|")[2];
                if (flObjectFolha) {
                    if (!verificaDuplicidade(flObjectFolha, idObj)) {
                        nmFolhaArvore = $F('nmContatoOrigem');
                        objSelect     = $('folhasSelecionadas');
                        objSelect.options[objSelect.options.length] = new Option(nmFolhaArvore, idFolhaArvore);
                        $("idContatoOrigem").value = "";
                    } else {
                        alert("Esta folha de contato já encontra-se selecionada.");
                    }
                }
            } else {
                alert("Por favor, selecione um folha de contato.");
            }
        }
    
        function excluirFolha() {
            var objSelect = $('folhasSelecionadas');
            for(i = objSelect.options.length-1; i>=0; i--)
                if(objSelect.options[i].selected)
                    objSelect.options[i] = null;
        }
    
        function verificaDuplicidade(flFolha, idObj) {
            var idObject, flObjectFolha;
            var objSelect = $('folhasSelecionadas');
            for (i = 0; i < objSelect.length; i++) {
                flObjectFolha = (objSelect.options[i].value.split("|")[0]=="1")?true:false;
                idObject      = objSelect.options[i].value.split("|")[2];
                if (flObjectFolha == flFolha && idObject == idObj) {
                    return true;
                }
            }
            return false;
        }
        //Fim animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
    </script>
</netui-template:section>
<netui-template:section name="bodySection">

<jsp:include page="/resources/menus/MenuPrincipal.jsp" />

<div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>

<vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relatórios" operacoes="Relatório de Palitagens" scroll="N">

    <form id="formFiltro" name="relatorioForm" action="gerarRelatorioPalitagens.do" onSubmit="return false;" method="post">
        <input type="hidden" id="idContatoOrigem" name="idContatoOrigem" />
        <input type="hidden" id="nmContatoOrigem" name="nmContatoOrigem" />
        <input type="hidden" id="somaDias" name="somaDias" />
        <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
            <tr>
                <td width="10%"><strong>Login:</strong></td>
                <td width="45%">
                    <input type="text" id="dsLogin" name="dsLogin" value="" style="width:200px;" />
                </td>
                <td width="45%">
                    <input type="checkbox" value="0" name="flQuantitativo" id="flQuantitativo" style="background:none;border:none" />
                    <strong>Quantitativo</strong>
                </td>
            </tr>
            <tr>
                <td><strong>Grupo:</strong></td>
                <td colspan="2">
                    <html:select name="relatorioForm" property="grupoSel" styleId="grupoSel" style="width:200px;margin-left:3px;" onmouseover="ativarToolTip(this,1);">
                        <html:option value="-1" key="grupoSel">--Selecionar--</html:option>
                        <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td><strong>Período:</strong></td>
                <td colspan="2">
                    <input type="text" id="dtInicio" name="dtInicio" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" />
                    <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">&nbsp;
                    <strong>Até&nbsp;</strong>
                    <input type="text" id="dtFim" name="dtFim" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');" />
                    <img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');">&nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="3">
                    <table>
                        <tr>
                            <td colspan="2" valign="top">
                                <strong>Árvore de Contatos:</strong><br/>
                                <div style="width:345px;height:320px;border:1px solid #ccc;display:block;padding:5px;overflow:auto;">
                                    <script>
                                        <%=(String)request.getAttribute("arvore")%>
                                    </script>
                                </div>
                            </td>
                            <td width="67" nowrap align="center">
                                <img id="btSelecionar"
                                                   value="Selecionar"
                                                   src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif"
                                                   style="border:none"
                                                   onMouseUp="associarFolha()" />
                                <br/><br/>
                                <img id="btExcluir"
                                                   value="Excluir"
                                                   src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif"
                                                   style="border:none;margin-left:4px;"
                                                   onMouseUp="excluirFolha()" />
                            </td>
                            <td valign="top">
                                <strong>Folhas selecionadas:</strong><br/>
                                <select multiple id="folhasSelecionadas" name="folhasSelecionadas" style="width:345px;height:318px;"></select>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="left" valign="bottom">
                    <img style="border:0px;"
                                       src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif"
                                       id="btVoltar"
                                       value="Voltar"
                                       onClick="document.location='../../index.jsp'; return false"/>
                </td>
                <td align="right" valign="bottom">

                    <img style="border:0px;"
                                       src="/FrontOfficeWeb/resources/images/bt_arqgerados_nrml.gif"
                                       id="btArquivosGerados"
                                       value="Arquivos Gerados"
                                       onClick="submitArquivosGerados(); return false"/>

                    <img style="border:0px;"
                                       src="/FrontOfficeWeb/resources/images/bt_gerarrelatorio_nrml.gif"
                                       id="btGerar"
                                       value="Gerar"
                                       onClick="submitGerar(); return false"/>
                </td>
            </tr>
        </table>
    </form>
</vivo:sessao>

<iframe name="iframeArvoreContato" height="0" width="0"></iframe>

</netui-template:section>
</netui-template:template>