<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="regionaisVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroRegionalVOArray"/>
<bean:define id="operadorasVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroOperadoraVOArray"/>
<bean:define id="gruposVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFGrupoVOArray"/>
<bean:define id="diretoriaVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroDiretoriaVOArray"/>
<bean:define id="areaVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroAreaVOArray"/>
<bean:define id="sessaoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroSessaoVOArray"/>
<bean:define id="cargoVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFRelatoriosFiltroCargoVOArray"/>
<bean:define id="estadosVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFEstadoVOArray"/>

<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript">
            function submitGerar() {
                erros = "";
        
                dtInicio = document.forms[0].dtInicio;
                dtFim = document.forms[0].dtFim;
        
                if (dtInicio.value == "") {
                    erros += "Selecione a Data de Início!\n";
                }
                if (dtFim.value == "") {
                    erros += "Selecione a Data Final!\n";
                }
        
                if (!validaDataFinal(dtInicio.value,dtFim.value)) {
                    erros += "Data Inicial maior que Data Final!\n";
                }
        
                if (document.forms[0].grupoSel.value == "-1"){
                    erros += "Selecione um Grupo!\n";
                }
        
                if (document.forms[0].tipoRelatorio.value == "-1") {
                    erros += "Selecione o tipo de Relatório!\n";
                }
        
                if (erros == "") {
                    if (!valida1mes(dtInicio,dtFim)) {
                        alert("Intervalo de Datas maior que 30 dias!\n");
                    } else {
                        //Inicio animação
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
                        document.forms[0].method = "POST";
                        document.forms[0].action = "gerarTempoMedioFila.do";
                        document.forms[0].target = "";
                        document.forms[0].submit();
                        return true;
                    }
                } else {
                    alert(erros);
                    return false;
                }
            }
        
            function submitOperadoras() {
                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        
                document.forms[0].method = "POST";
                document.forms[0].action = "obterRegionais.do";
                document.forms[0].target = "ifrmCombos";
                document.forms[0].submit();
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
        <acesso:controlHiddenItem nomeIdentificador="wor_tmfila_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
        <vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relatórios" operacoes="Tempo Médio de Análise e da Fila" scroll="N">
            <form id="formFiltro" action="gerarTempoMedioFila.do" method="post">
                <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
                    <tr>
                        <td colspan="2" align="left" valign="top">
                            <table border="0" cellpadding="2" cellspacing="0" width="100%">
                                <tr><td colspan="2"><b>Filtros para Geração do Relatório</b></td></tr>
                                <tr><td>&nbsp;</td></tr>
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
                                    <td align="left"><b>Grupo:</b></td>
                                    <td align="left">
                                        <div id="ttip" style="display:none;position:absolute;max-width:450px;"></div>
                                        <html:select name="relatorioForm" property="grupoSel" style="width: 450px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 65, 162, 30);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 65, 162, 30);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 65, 162, 30);" onblur="HideTip();" onmouseout="HideTip();">
                                            <html:option value="-1" key="grupoSel">Todos</html:option>
                                            <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><b>Operadora:</b></td>
                                    <td align="left">
                                        <html:select onchange="submitOperadoras();" name="relatorioForm" property="operadoraSel" style="width: 450px">
                                            <html:option value="-1" key="operadoraSel">Todos</html:option>
                                            <html:options collection="operadorasVO" property="idOperadora" labelProperty="dsOperadora"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="nmGrupoOperadora" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="20%"><b>UF:</b></td>
                                    <td align="left" width="80%">
                                        <html:select name="relatorioForm" property="regionalSel" style="width:450px">
                                            <html:option value="-1" key="regionalSel">Todos</html:option>
                                            <html:options collection="regionaisVO" property="idRegional" labelProperty="dsRegional"/>
                                        </html:select>
                                        <html:checkbox name="relatorioForm" value="UF" property="quebra" styleClass="checkbox" style="border:none;background:none;"> Quebra</html:checkbox>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left"><b>Status:</b></td>
                                    <td align="left">
                                        <html:select multiple="true" size="3" name="relatorioForm" property="estadoSel" style="width: 450px">
                                            <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado"/>
                                        </html:select>
                                    </td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="left"><b>Escolha o Relatório:</b></td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="tipoRelatorio" style="width:450px">
                                            <html:option value="-1" key="tipoRelatorio">-- Selecione --</html:option>
                                            <html:option value="1" key="tipoRelatorio">Tempo Médio Tratamento Representante</html:option>
                                            <html:option value="2" key="tipoRelatorio">Tempo Médio na Fila</html:option>
                                            <html:option value="3" key="tipoRelatorio">Tempo Medio em Pausa</html:option>
                                            <html:option value="4" key="tipoRelatorio">Tempo Médio em Pausa (Grupo)</html:option>
                                            <html:option value="5" key="tipoRelatorio">Tempo Médio Tratamento Processo</html:option>
                                        </html:select>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="bottom">
                            <acesso:controlHiddenItem nomeIdentificador="wor_tmfila_gerar">
                                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" id="btVoltar" value="Voltar" onClick="document.location='../../index.jsp'; return false"/>
                            </acesso:controlHiddenItem>
                        </td>
                        <td align="right" valign="bottom">
                            <acesso:controlHiddenItem nomeIdentificador="wor_tmfila_gerar">
                                <img style="border:0px;" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" id="btGerar"  value="Gerar"  onClick="submitGerar(); return false"/>
                            </acesso:controlHiddenItem>
                        </td>
                    </tr>
                </table>
                <iframe scrolling="yes" style="visibility:hidden;" name="ifrmCombos" height="0" width="0"></iframe>
                <input type="hidden" name="somaDias">
            </form>
        </vivo:sessao>
        <script>
            var moveToolTip = true;
            xBump=yBump=10;
            MSIE=document.all;
            NS6=document.getElementById&&!document.all;
            if(MSIE||NS6){
                ttipObj=document.all?document.all["ttip"]:document.getElementById?document.getElementById("ttip"):"";
            }
            
            function carregaToolTip(content) {
                ttipObj.innerHTML=content;
                ttipObj.style.display='';
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>