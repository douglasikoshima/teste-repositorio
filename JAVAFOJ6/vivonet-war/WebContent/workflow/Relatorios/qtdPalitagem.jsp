<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<bean:define id="gruposVO" name="relatorioForm" property="WFRelatoriosFiltrosVO.WFGrupoVOArray"/>
<netui-template:template templatePage="../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Vivo Net >> Workflow"/>
    <netui-template:setAttribute name="modulo" value="Workflow"/>
    <netui-template:section name="headerSection">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        <script language="javascript">
            <!--
            function submitGerar() {
                erros = "";
                dtInicio = document.forms[0].dtInicio;
                dtFim = document.forms[0].dtFim;
                hoje = getHoje();
                if (dtInicio.value == '') {
                    alert('Campo Data 1 vazio!\nUtilize uma data válida');
                    dtInicio.value = '<bean:write name="relatorioForm" property="dtInicio"/>';
                    return false;
                }
                if (dtFim.value == '') {
                    alert('Campo Data 2 vazio!\nUtilize uma data válida');
                    dtFim.value = '<bean:write name="relatorioForm" property="dtFim"/>';
                    return false;
                }
                if (dtInicio.value == dtFim.value) {
                    alert("Data 1 e Data 2 não podem ser iguais!");
                    dtInicio.value = '<bean:write name="relatorioForm" property="dtInicio"/>';
                    dtFim.value = '<bean:write name="relatorioForm" property="dtFim"/>';
                    return false;
                }
                if (dtInicio.value == hoje) {
                    alert("Data 1 não pode ser de hoje!");
                    dtInicio.value = '<bean:write name="relatorioForm" property="dtInicio"/>';
                    return false;
                }
                if (dtFim.value == hoje) {
                    alert("Data 2 não pode ser de hoje!");
                    dtFim.value = '<bean:write name="relatorioForm" property="dtFim"/>';
                    return false;
                }
                if (document.forms[0].alarme.value == "-1") {
                    document.forms[0].alarme.value == "100";
                }
                if (erros == "") {
                    //Inicio animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
                    document.forms[0].method = "POST";
                    document.forms[0].action = "gerarQtdPalitagem1.do";
                    document.forms[0].target = "";
                    document.forms[0].submit();
                } else {
                    alert(erros);
                    return false;
                }
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

            function getHoje() {
                data = new Date();
                dia = data.getDate();
                if (dia < 10) {
                    dia = "0"+dia;
                }
                mes = data.getMonth()+1;
                if (mes < 10) {
                    mes = "0"+mes;
                }
                ano = data.getFullYear();
                hoje = dia+"/"+mes+"/"+ano;
                return hoje;
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            -->
        </script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <%--acesso:controlHiddenItem nomeIdentificador="wor_rtrep_verpagina">--%>
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" width="1" height="3"></div>
            <vivo:sessao id="qdMain" height="475" width="790" label="Vivo Net >> Workflow >> Relatórios" operacoes="Quantidade Palitagem" scroll="N">
                <form id="formFiltro" tagId="formFiltro" action="gerarQtdPalitagem1.do" method="post">
                <table cellpadding="5" cellspacing="0" border="0" width="100%" height="100%">
                    <tr>
                        <td colspan="2" align="left" valign="top">
                            <table cellpadding="2" cellspacing="0" border="0" width="100%">
                                <tr>
                                    <td colspan="2"><b>Filtros para Geração do Relatório</b></td>
                                </tr>
                                <tr>
                                    <td>&nbsp;</td>
                                </tr>
                                <tr>
                                    <td><b>Data 1:</b></td>
                                    <td><input readonly type="text" id="dtInicio" name="dtInicio" value='<bean:write name="relatorioForm" property="dtInicio"/>' size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <B>Data 2:</b>
                                    </td>
                                    <td>
                                        <input readonly type="text" id="dtFim" name="dtFim" value='<bean:write name="relatorioForm" property="dtFim"/>' size="10" maxlength="10" onblur="validaData(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');"  onchange="Formatar(this.value, this.form.name, this.name, 'data');"/>
                                        <p>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left" width="20%">
                                        <b>Grupo:</b>
                                    </td>
                                    <td align="left">
                                        <div id="ttip" style="display:none;position:absolute;max-width:450px;"></div>
                                        <html:select name="relatorioForm" property="grupoSel" style="width: 450px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 85, 162, 30);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 85, 162, 30);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 85, 162, 30);" onblur="HideTip();" onmouseout="HideTip();">
                                            <html:option value="-1" key="grupoSel">Todos</html:option>
                                            <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                        </html:select>
                                        <html:hidden name="relatorioForm" value="nmGrupo" property="quebra"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="left">
                                        <b>Nível de Alarme :</b>
                                    </td>
                                    <td align="left">
                                        <html:select name="relatorioForm" property="alarme" style="width: 100px">
                                            <html:option value="-1" key="alarme">-- Selecione --</html:option>
                                            <html:option value="5" key="alarme">5%</html:option>
                                            <html:option value="10" key="alarme">10%</html:option>
                                            <html:option value="20" key="alarme">20%</html:option>
                                            <html:option value="30" key="alarme">30%</html:option>
                                            <html:option value="40" key="alarme">40%</html:option>
                                            <html:option value="50" key="alarme">50%</html:option>
                                            <html:option value="60" key="alarme">60%</html:option>
                                            <html:option value="70" key="alarme">70%</html:option>
                                            <html:option value="80" key="alarme">80%</html:option>
                                            <html:option value="90" key="alarme">90%</html:option>
                                            <html:option value="100" key="alarme">100%</html:option>
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
                <input type="hidden" name="somaDias1">
                <input type="hidden" name="somaDias2">
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
        </script>
    <%--</acesso:controlHiddenItem>--%>
    </netui-template:section>
</netui-template:template>