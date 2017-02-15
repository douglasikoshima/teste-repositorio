<!--
Módulo.....: Gestão de Processos
Caso de Uso: Indicadores
Versão.....: $Author: a5112272 $ - $Revision: 1.2 $ - $Date: 2011/05/29 13:21:45 $
-->
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
   
<acesso:controlInitEnv/>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/toolTip.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css">

<bean:define id="indicadoresForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="indicadoresForm"/>
<bean:define id="regionaisVO" name="indicadoresForm" property="indicadoresPesquisaVO.WFRelatoriosFiltroRegionalVOArray"/>
<bean:define id="gruposVO" name="indicadoresForm" property="indicadoresPesquisaVO.WFGrupoVOArray"/>

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Indicadores"/>
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="wor_ind_verpagina">
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
        <vivo:sessao id="qdMain" height="480" width="790" label="Vivo Net >> Workflow" operacoes="Indicadores" scroll="N">
                <table width="690" cellspacing="0" cellpadding="0" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>
                
                <!--CONFIGURAÇÕES-->
                <vivo:quadro id="qdConfiguracoes" height="35" width="780" label="&nbsp;Configurações" scroll="N">

                    <form id="frmMonitoramento" name="frmMonitoramento" action="pesquisar.do">

                    <table width="100%" cellpadding="1" cellspacing="1" border="0">
                        <tr>
                            <td>Regional:</td>
                            <td>
                                <html:select name="indicadoresForm" property="regionalSel" style="width: 200px">
                                    <html:option value="-1" key="regionalSel">Todos</html:option>
                                    <html:options collection="regionaisVO" property="idRegional" labelProperty="dsRegional"/>
                                </html:select>
                            </td>
                            <td>Grupo:</td>
                            <td>
                                <html:select name="indicadoresForm" property="grupoSel" style="width: 200px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 332, 25);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 332, 25);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 332, 25);" onblur="HideTip();" onmouseout="HideTip();">
                                    <html:option value="-1" key="regionalSel">Todos</html:option>
                                    <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                                </html:select>
                            </td>
                            <td align="center"><acesso:controlHiddenItem nomeIdentificador="wor_ind_pesquisar"><vivo:botao id="btPesquisar" width="100px" height="15px" value="Pesquisar" styleClass="btTemplate" onclick="submitPesquisar();"/></acesso:controlHiddenItem></td>
                            <td align="center"><vivo:botao id="btLimpar"    width="100px" height="15px" value="Limpar"    styleClass="btTemplate" onclick="submitLimpar();"/></td>                            
                        </tr>
                    </table>
                    
                    </form>
                </vivo:quadro>
                <table width="775" cellspacing="1" cellpadding="1" border="0">
                    <tr>
                        <td style="height:5px;"></td>
                    </tr>
                </table>
                
                <!--CONTROLE ABAS-->
                <vivo:abaGrupo id="btAbaConfiguracao" width="780px" height="10px" styleClass="abatexto">
                    <acesso:controlHiddenItem nomeIdentificador="wor_ind_abatipoprocesso"><vivo:abaItem id="btIndTipo"     onclick="exibicaoAba(0);" value="Tipo de Processo" select="S"/></acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_ind_abanaturezacliente"><vivo:abaItem id="btIndNatureza" onclick="exibicaoAba(1);" value="Natureza de Cliente"/></acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_ind_abasegmentacaocarteira"><vivo:abaItem id="btIndSegCart"  onclick="exibicaoAba(2);" value="Segmentação/Carteira"/></acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_ind_abaestadolinha"><vivo:abaItem id="btIndEstLinha" onclick="exibicaoAba(3);" value="Estado da Linha"/></acesso:controlHiddenItem>
                    <acesso:controlHiddenItem nomeIdentificador="wor_ind_abaresumoacompanhamento"><vivo:abaItem id="btIndResumo"   onclick="exibicaoAba(4);" value="Resumo Acompanhamento"/></acesso:controlHiddenItem>
                </vivo:abaGrupo>
                <!--ABAS-->
                <table width="100%" cellpadding="0" cellspacing="0" border="1">
                    <!--Tipo de Processo-->
                    <tr id="trTipoProcesso">
                        <td>
                            <iframe id="ifrmTipoProcesso" name="ifrmTipoProcesso" scrolling="no" height="390" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>
                    
                    <!--Natureza-->
                    <tr id="trNatureza" style="display:none">
                        <td>
                            <iframe id="ifrmNatureza" name="ifrmNatureza" scrolling="no" height="390" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>

                    <!--Segmentacao e Carterização-->
                    <tr id="trSegCart" style="display:none">
                        <td>
                            <iframe id="ifrmSegCart" name="ifrmSegCart" scrolling="no" height="390" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>

                    <!--Estado da Linha-->
                    <tr id="trEstadoLinha" style="display:none">
                        <td>
                            <iframe id="ifrmEstado" name="ifrmEstado" scrolling="no" height="390" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>

                    <!--Resumo-->
                    <tr id="trResumo" style="display:none">
                        <td>
                            <iframe id="ifrmResumo" name="ifrmResumo" scrolling="no" height="390" width="775" frameborder="0"></iframe>
                        </td>
                    </tr>
                </table>
        </vivo:sessao>
        
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
        
        <script language="javascript">
            //Controle da carga das abas
            var controllTabs = new Array(0, 0, 0, 0, 0);
            var frmTarget    = new Array('ifrmTipoProcesso', 'ifrmNatureza', 'ifrmSegCart', 'ifrmEstado', 'ifrmResumo');
            var frmAction    = new Array('inicioTipoProcesso.do', 'inicioNaturezaCliente.do', 'inicioSegmentCart.do', 'inicioEstadoLinha.do', 'inicioResumo.do');
            var treadRefresh;
            var MinutesRefresh = '<bean:write name="indicadoresForm" property="atualizacao" format="###"/>';
            if (MinutesRefresh == "") {
                SecondsRefresh = 0;
            } else {
                SecondsRefresh = MinutesRefresh * 60;
            }
            
            //Controle da exibição
            function exibicaoAba(index) {
                //Controle da exibição
                var showTab = new Array('none', 'none', 'none', 'none', 'none');

                //Seleciona Aba
                if( index == 0 )        abaSelected(btAbaConfiguracao, btIndTipo);
                else if( index == 1)    abaSelected(btAbaConfiguracao, btIndNatureza);
                else if( index == 2)    abaSelected(btAbaConfiguracao, btIndSegCart);
                else if( index == 3)    abaSelected(btAbaConfiguracao, btIndEstLinha);
                else if( index == 4)    abaSelected(btAbaConfiguracao, btIndResumo);

                 //Para exibição da tab corrente
                showTab[index] = '';

                //Controla a exibição
                trTipoProcesso.style.display = showTab[0];
                trNatureza.style.display     = showTab[1];
                trSegCart.style.display      = showTab[2];
                trEstadoLinha.style.display  = showTab[3];
                trResumo.style.display       = showTab[4];
                
                //Controle da chamada do elemento
                if( controllTabs[index] == 0 ) {
                    
        mostrar_div();
                    //Inicio animação
                    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();

                    controllTabs[index] = 1;
                    
                    document.forms["frmMonitoramento"].target = frmTarget[index];
                    document.forms["frmMonitoramento"].action = frmAction[index];
                    document.forms["frmMonitoramento"].submit();
                }
            }            
            
            function submitPesquisar() {
        
                //Inicio animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
            
                document.forms[0].method = "POST";
                document.forms[0].action = "pesquisar.do";
                document.forms[0].target = "";
                document.forms[0].submit();

            }

            //Controle da tread do refresh automático parametrizável
            function startRefresh() {
                        
                //Limpa a tread se necessario
                window.clearInterval(treadRefresh);
            
                //Calcula o numero de milesegundos
                var mlSecondsRefresh = SecondsRefresh * 1000;
                
                //Inicializa o refresh automático
                treadRefresh = window.setInterval("submitPesquisar()", mlSecondsRefresh );
                
            }
            
            function submitLimpar() {
                document.forms[0].regionalSel.value="-1";
                document.forms[0].grupoSel.value="-1";
            }

            //Fim animação
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
            
            //Exibe a primeira aba
            exibicaoAba(0);
            
            // refresh
            startRefresh();
        </script>
   
    </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
