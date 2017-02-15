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
<bean:define id="regionaisVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroRegionalVOArray"/>
<bean:define id="operadorasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroOperadoraVOArray"/>
<bean:define id="ufVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroUFVOArray"/>
<bean:define id="segmentosVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSegmentoVOArray"/>
<bean:define id="estadosVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFEstadoVOArray"/>
<bean:define id="carteirasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroCarteiraVOArray"/>
<bean:define id="gruposVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFGrupoVOArray"/>
<bean:define id="representantesVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRFRVOArray"/>
<bean:define id="diretoriaVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroDiretoriaVOArray"/>
<bean:define id="areaVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroAreaVOArray"/>
<bean:define id="sessaoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSessaoVOArray"/>
<bean:define id="cargoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroCargoVOArray"/>
<bean:define id="prazoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.alertaVOArray"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
<link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script language="javascript">



    function validarCampos() {
        f = document.forms[0];
        var dtInicio = f.dtInicio;
        var dtFim = f.dtFim;

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

        if (!valida1mes(dtInicio,dtFim)) {
            alert("Intervalo de Datas maior que 30 dias!\n");
            return false;
        }

        if (f.operadoraSel.value == -1) {
            alert("Selecione uma Operadora");
            return false;
        }

        if (f.regionalSel.value == -1) {
            alert("Selecione UF");
            return false;
        }

        if (f.grupoSel.value == -1) {
            alert("Selecione um Grupo");
            return false;
        }

        f.ufOperadoraText.value    = f.regionalSel.options[f.regionalSel.selectedIndex].text;
        f.grupoText.value          = f.grupoSel.options[f.grupoSel.selectedIndex].text;
        f.representanteText.value  = f.representanteSel.options[f.representanteSel.selectedIndex].text;
        f.prazoText.value          = f.prazo.options[f.prazo.selectedIndex].text;

        return true;

    }

    function submitGerar() {
        f = document.forms[0];
        if (!validarCampos()) {
            return false;
        }

        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        f.method = "POST";
        f.action = "gerarRelatorio1CRI.do";
        f.target = "";
        f.submit();
    }

    function submitExportar() {
        f = document.forms[0];
        if (!validarCampos()) {
            return false;
        }
        f.method = "GET";
        f.action = "exportarRelatorio1CRI.do";
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

    function submitOperadoras() {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].method = "POST";
        document.forms[0].action = "obterRegionais.do";
        document.forms[0].target = "ifrmCombos";
        document.forms[0].submit();

        top.frameCTI.mudaSelecionar=true;

    }

    function submitGrupo() {
        //Inicio animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

        document.forms[0].method = "POST";
        document.forms[0].action = "obterRepresentantes.do";
        document.forms[0].target = "ifrmCombos";
        document.forms[0].submit();
    }

    //Fim animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

</script>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <%--acesso:controlHiddenItem nomeIdentificador="wor_crirel1_verpagina" --%>
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relatórios" operacoes="Auditoria de Processos CRI" scroll="N">
            <form id="formFiltro" name="relatorioForm" action="gerarRelatorio1CRI.do" method="post">
                <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
                    <tr>
                        <td align="left" valign="top" colspan="2">
                            <table cellpadding="2" cellspacing="0" border="0" width="100%">
                                <tr>
                                    <td colspan="2"><b>Filtros para Geração do Relatório:</b></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><b>Data de Entrada do Processo:</b></td>
                                    <td><input type="text" id="dtInicio" name="dtInicio" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');">&nbsp;
                                        <B>até&nbsp;</b>
                                        <input type="text" id="dtFim" name="dtFim" size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');">&nbsp;
                                        <b>(Máximo 30 dias)</b>
                                        <p>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Operadora:</b>
                                    </td>
                                    <td align="left">
                                        <html:select onchange="submitOperadoras();" name="relatorioForm" property="operadoraSel" style="width: 450px" onmouseover="ativarToolTip(this,1);">
                                            <html:option value="-1" key="operadoraSel">Selecionar</html:option>
                                            <html:options collection="operadorasVO" property="idOperadora" labelProperty="dsOperadora"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="20%">
                                        <b>UF:</b>
                                    </td>
                                    <td align="left" width="80%">
                                        <html:select name="relatorioForm" property="regionalSel" style="width:450px" onmouseover="ativarToolTip(this,1);">
                                            <html:option value="-1" key="regionalSel">Selecionar</html:option>
                                            <html:options collection="regionaisVO" property="idRegional" labelProperty="dsRegional"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Grupo:</b>
                                    </td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="grupoSel" style="width: 450px" onchange="submitGrupo();" onmouseover="ativarToolTip(this,1);">
                                            <html:option value="-1" key="grupoSel">Selecionar</html:option>
                                            <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Representante:</b>
                                    </td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="representanteSel" style="width: 450px" onmouseover="ativarToolTip(this,1);">
                                            <html:option value="-1" key="representanteSel">Todos</html:option>
                                            <html:options collection="representantesVO" property="id" labelProperty="ds"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Prioridade:</b>
                                    </td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="prazo" style="width: 450px" onmouseover="ativarToolTip(this,1);">
                                            <html:option value="-1" key="prazo">Todos</html:option>
                                            <html:option value="1" key="prazo">No Prazo</html:option>
                                            <html:option value="2" key="prazo">Alerta</html:option>
                                            <html:option value="3" key="prazo">Vencido</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="bottom">
                            <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" id="btVoltar"  value="Voltar"  onClick="document.location='../../index.jsp'; return false"/>
                        </td>
                        <td align="right" valign="bottom">
                            <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" id="btGerar"  value="Gerar"  onClick="submitGerar(); return false"/>
                        </td>
                    </tr>
                </table>
                <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
                <input type="hidden" name="somaDias">
                <input type="hidden" name="ufOperadoraText">
                <input type="hidden" name="grupoText">
                <input type="hidden" name="representanteText">
                <input type="hidden" name="prazoText">
            </form>
        </vivo:sessao>
    <%--/acesso:controlHiddenItem --%>
    </netui-template:section>
</netui-template:template>