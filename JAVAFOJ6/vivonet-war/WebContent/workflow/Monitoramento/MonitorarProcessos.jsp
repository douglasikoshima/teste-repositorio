<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>
<bean:define id="monitoramentoForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm"/>
<bean:define id="WFGrupoVOArray"    name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="monitoramentoForm.monitoramentoPesquisaVO.WFGrupoVOArray"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Monitoramento"/>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="wor_mp_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />

        <!--APLICACAO-->
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
<script>
var moveToolTip = true;;

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

        <vivo:sessao id="qdMain" height="480" width="790" label="Vivo Net >> Workflow" operacoes="Monitoramento" scroll="N">
            <form id="frmMonitoramento" tagId="frmMonitoramento" action="begin.do" method="post" name="monitoramentoForm">
                <html:hidden name="monitoramentoForm" property="pesquisar" />
                <table width="690" cellspacing="0" cellpadding="0" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>

                <!--CONFIGURAÇÕES-->
                <vivo:quadro id="qdConfiguracoes" height="55" width="780" label="&nbsp;Configurações" scroll="N">
                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                        <tr>
                            <td>Grupo: </td>
                            <td colspan="7">
                                <html:select name="monitoramentoForm" property="idGrupo" style="width=250px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 85, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 85, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 85, 35);" onblur="HideTip();" onmouseout="HideTip();">
                                    <html:option value="0">Todos</html:option>
                                    <html:options collection="WFGrupoVOArray" property="idGrupo" labelProperty="dsGrupo"/>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>Data Inicio:</td>
                            <td><html:text name="monitoramentoForm" property="dtInicio" title="Data de Abertura Inicial" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');enabledRefresh();" onchange="Formatar(this.value, this.form.name, this.name, 'data');enabledRefresh();" onkeypress="keyEnter();" onblur="validacao(0);"/><img id="imgCalendDtFecIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtInicio', '%d/%m/%Y');"></td>
                            <td>Data Fim:</td>
                            <td><html:text name="monitoramentoForm" property="dtFim"    title="Data de Abertura Final"   size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');enabledRefresh();" onchange="Formatar(this.value, this.form.name, this.name, 'data');enabledRefresh();" onkeypress="keyEnter();" onblur="validacao(1);"/><img id="imgCalendDtFecIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFim', '%d/%m/%Y');"></td>
                            <td>Refresh:</td>
                            <td>
                                <Select id="optRefresh" name="optRefresh" onchange="startRefresh();">
                                    <option value="0">Desativado</option>
                                    <option value="1">1 minuto</option>
                                    <option value="5">5 minutos</option>
                                    <option value="10">10 minutos</option>
                                </Select>
                            </td>
                            <td align="center"><acesso:controlHiddenItem nomeIdentificador="wor_mp_pesquisar"><vivo:botao id="btPesquisar" width="100px" height="15px" value="Pesquisar" styleClass="btTemplate" onclick="pesquisa();"/></acesso:controlHiddenItem></td>
                            <td align="center"><vivo:botao id="btLimpar"    width="100px" height="15px" value="Limpar"    styleClass="btTemplate" onclick="limpar();"/></td>
                        </tr>
                    </table>
                </vivo:quadro>
                <table width="775" cellspacing="1" cellpadding="1" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>

                <!--CONTROLE ABAS-->
                <vivo:abaGrupo id="btAbaConfiguracao" width="780px" height="10px" styleClass="abatexto">
                    <acesso:controlHiddenItem nomeIdentificador="wor_mp_abageral"><vivo:abaItem id="btAnaliseGeral"     onclick="exibicaoAba(0);" value="Geral" select="S"/></acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_mp_abageralestado"><vivo:abaItem id="btAnaliseGrupos"    onclick="exibicaoAba(1);" value="Estado/Grupo"/></acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_mp_abaatendente"><vivo:abaItem id="btAnaliseAtendente" onclick="exibicaoAba(2);" value="Atendente"/></acesso:controlHiddenItem>
                </vivo:abaGrupo>
                <!--ABAS-->
                <table width="100%" cellpadding="0" cellspacing="0" border="1">
                    <!--GERAL-->
                    <tr id="trGeral" name="trGeral">
                        <td>
                            <iframe id="ifrmGeral" name="ifrmGeral" scrolling="no" height="370" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>

                    <!--GERAL ESTADO-->
                    <tr id="trGeralEstado" name="trGeralEstado" style="display:none">
                        <td>
                            <iframe id="ifrmGeralEstado" name="ifrmGeralEstado" scrolling="no" height="370" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>

                    <!--ATENDENTE-->
                    <tr id="trAtendente" name="trAtendente" style="display:none">
                        <td>
                            <iframe id="ifrmAtendente" name="ifrmAtendente" scrolling="no" height="370" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>
                </table>
            </form>
        </vivo:sessao>

        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>

        <script language="javascript">
            //Controle da carga das abas
            var controllTabs = new Array(0, 0, 0);
            var frmTarget    = new Array('ifrmGeral', 'ifrmGeralEstado', 'ifrmAtendente');
            var frmAction    = new Array('inicioGeral.do', 'inicioEstado.do', 'inicioAtendente.do');

            //Controle do inicio da pesquisa
            var startSearch = false;

            //Controle da tread de refresh automatico
            var treadRefresh;

            function keyEnter() {
                //Caso não seja o enter continua
                if( window.event.keyCode != 13 ) return;

                //Efetua o logon
                window.event.keyCode = 0;
                pesquisa();
            }

            function validacao(index) {
                if( (index == 0) && (document.forms["frmMonitoramento"].elements["dtInicio"].value.length > 0) ) {
                    if( ! validate_date(document.forms["frmMonitoramento"].elements["dtInicio"].value) ) {
                        alert("A data de abertura inicial informada não é válida");
                        document.forms["frmMonitoramento"].elements["dtInicio"].focus();
                        document.forms["frmMonitoramento"].elements["dtInicio"].value = "";
                        return;
                    }

                } else if( (index == 1) && (document.forms["frmMonitoramento"].elements["dtFim"].value.length > 0) ) {
                    if( ! validate_date(document.forms["frmMonitoramento"].elements["dtFim"].value) ) {
                        alert("A data de abertura final informada não é válida");
                        document.forms["frmMonitoramento"].elements["dtFim"].focus();
                        document.forms["frmMonitoramento"].elements["dtFim"].value = "";
                        return;
                    }
                }
            }

            function pesquisa() {
                //Efetua a validaçao
                if( document.forms["frmMonitoramento"].elements["dtInicio"].value.length == 0 ) {
                    document.forms["frmMonitoramento"].elements["dtInicio"].focus();
                    alert('Para executar a pesquisa é necessário informar a data de abertura inicial');
                    return;
                }

                //Informa que a pesquisa aconteceu
                startSearch = true;

                //Zera os elementos de controle das abas para novo processamento
                controllTabs[0] = 0;
                controllTabs[1] = 0;
                controllTabs[2] = 0;

                //Informa a action que é necessario uma nova pesquisa
                document.forms["frmMonitoramento"].elements["pesquisar"].value = "S";

                if( document.forms["frmMonitoramento"].elements["optRefresh"].value == "1" ){
                    //Limpa a tread se necessario
                    window.clearInterval(treadRefresh);

                    //Seta timeout minimo para grd qtd de regs.
                    treadRefresh = window.setInterval("pesquisar()", 150000 );
                }

                //Processa a pesquisa conforme a aba selecionada
                var indexTab;

                if(      btAnaliseGeral.style.cursor.length     == 0 ) indexTab = 0;
                else if( btAnaliseGrupos.style.cursor.length    == 0 ) indexTab = 1;
                else if( btAnaliseAtendente.style.cursor.length == 0 ) indexTab = 2;

                //Refresh na tab corrente
                exibicaoAba( indexTab );
            }

            function limpar() {
                document.forms["frmMonitoramento"].elements["idGrupo"].value       = "0";
                document.forms["frmMonitoramento"].elements["dtInicio"].value      = "";
                document.forms["frmMonitoramento"].elements["dtFim"].value         = "";
                document.forms["frmMonitoramento"].elements["optRefresh"].value    = "0";
              //  document.forms["frmMonitoramento"].elements["optRefresh"].disabled = true;

                document.forms["frmMonitoramento"].elements["dtInicio"].focus();

                //Para a tread de pesquisa
                startRefresh();
            }

            function enabledRefresh() {
                //Se data final não informada, permite refresh automático parametrizavél
                if( (! validate_date(document.forms["frmMonitoramento"].elements["dtInicio"].value)) || (document.forms["frmMonitoramento"].elements["dtFim"].value.length != 0) ) {
                    document.forms["frmMonitoramento"].elements["optRefresh"].value    = "0";
                 //   document.forms["frmMonitoramento"].elements["optRefresh"].disabled = true;
                    return;
                }

                //Abilita para refresh automatico parametrizável
                document.forms["frmMonitoramento"].elements["optRefresh"].disabled = false;
            }

            //Controle da exibição
            function exibicaoAba(index) {

                //Verifica se ocorreu a pesquisa
                if( ! startSearch ) return;

                //Controle da exibição
                var showTab = new Array('none', 'none', 'none');

                //Seleciona Aba
                if( index == 0)         abaSelected(btAbaConfiguracao, btAnaliseGeral);
                else if( index == 1)    abaSelected(btAbaConfiguracao, btAnaliseGrupos);
                else                    abaSelected(btAbaConfiguracao, btAnaliseAtendente);

                 //Para exibição da tab corrente
                showTab[index] = '';

                //Controla a exibição
                trGeral.style.display       = showTab[0];
                trGeralEstado.style.display = showTab[1];
                trAtendente.style.display   = showTab[2];

                //Controle da chamada do elemento
                if( controllTabs[index] == 0 ) {
                    //Liga animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    controllTabs[index] = 1;

                    document.forms["frmMonitoramento"].target = frmTarget[index];
                    document.forms["frmMonitoramento"].action = frmAction[index];
                    document.forms["frmMonitoramento"].submit();
                }

                //Informa a action que nao é necessário outra pesquisa
                document.forms["frmMonitoramento"].elements["pesquisar"].value = "N";
            }

            //Controle da tread do refresh automático parametrizável
            function startRefresh() {

                //Limpa a tread se necessario
                window.clearInterval(treadRefresh);

                //Verifica a desativiaçao do refresh
                if( document.forms["frmMonitoramento"].elements["optRefresh"].value == "0" )
                    return;

                //Calcula o numero de milesegundos
                var SecondsRefresh   = 60 * parseInt(document.forms["frmMonitoramento"].elements["optRefresh"].value);
                var mlSecondsRefresh = SecondsRefresh * 1000;

                //Inicializa o refresh automático
                treadRefresh = window.setInterval("pesquisar()", mlSecondsRefresh );
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

            //Exibe a primeira aba
            exibicaoAba(0);

            //Foca na data inicial
            document.forms["frmMonitoramento"].elements["dtInicio"].focus();
        </script>

    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>