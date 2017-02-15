<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<acesso:controlInitEnv/>

<bean:define id="form" name="rWFInBoxPesquisaForm"/>
<bean:define id="formGrupos" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="RWFInBoxGruposUserForm" />
<bean:define id="gruposVO" name="formGrupos" property="gruposVO" />

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="Workflow"/>
<netui-template:setAttribute name="modulo" value="Processos In-Box"/>
<netui-template:section name="headerSection">

<link rel="stylesheet" type="text/css" href="../../resources/css/calendar.css">
<style>
    a:link {
        color: #000000;
        text-decoration: none;
    }
    a:visited {
        color: #FFFFFF;
        text-decoration: none;
    }
    a:hover {
        color: #FFFFFF;
    }
</style>
<script type="text/javascript" src="../../resources/scripts/calendar.js"></script>
<script type="text/javascript" src="../../resources/scripts/validacao.js"></script>
<script type="text/javascript" src="../../resources/scripts/vivoval.js"></script>
<script type="text/javascript" src="../../resources/scripts/RWFInBox.js"></script>
<script type="text/javascript" src="../../resources/scripts/RWFUser.js"></script>
<script type="text/javascript" src="../../resources/scripts/toolTip.js"></script>
<script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
<script type="text/javascript">
    <!--
    var ep=true;
    var ept=true;
    var controleDetalhe = true;
    var up = true;
    var frmsState = null;
    var gr=true;
    var voltar2=0;

    //Controle da tread de refresh automatico
    var treadRefresh;
    var currentTab=0;
    var currentMlSeconds;

    function stopRefresh() {
        //Limpa a tread se necessario
        window.clearInterval(treadRefresh);
    }

    //Controle da tread do refresh automático parametrizável
    function startRefresh(idTab,mlSecondsRefresh) {
        stopRefresh();
        //Inicializa o refresh automático
        treadRefresh = window.setInterval("submitPesquisar("+idTab+")", mlSecondsRefresh );
        currentTab = idTab;
        currentMlSeconds = mlSecondsRefresh;
    }

    function restartRefresh() {
        startRefresh(currentTab,currentMlSeconds);
    }

    function showIfr(a) {
        d=eval("d"+a);f=eval("ifr"+a);
        d.innerHTML=f.document.body.innerHTML;d.style.display='';
        //if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
        DoResizeHeaderTableVivo();
    }

    function showDiv(a,h){
        document.getElementById("d"+a).innerHTML=h;
        //top.frameApp.stopAnimation();
    }

    function carrUser() { dUser.innerHTML=ifrUser.document.body.innerHTML;dUser.style.display='';}

    function submitSuspeito() {
        if(top.frameApp.dvAnimarAguarde!=null) top.frameApp.startAnimation();
        var contSusp=0;var contNaoSusp=0;
        var aSuspeito=new Array();aCheckbox = document.getElementsByTagName('input');
        for(i=0;i<aCheckbox.length;i++) {
            if(aCheckbox[i].type=='checkbox' && aCheckbox[i].checked==true) {
                try { hist=window.ifrETrat.getH(aCheckbox[i].name);susp=window.ifrETrat.getS(aCheckbox[i].name);}
                catch(e) {try { hist=window.ifrEPau.getH(aCheckbox[i].name); susp = window.ifrEPau.getS(aCheckbox[i].name);}
                catch(e) {try {hist=window.ifrPesquisa.getH(aCheckbox[i].name); susp = window.ifrPesquisa.getS(aCheckbox[i].name);}
                catch(e) {}}}
                if (susp!="" || hist!=0) { aSuspeito[contSusp] = aCheckbox[i].name; contSusp++; aCheckbox[i].checked=false;}
                else contNaoSusp++;
            }
        }
        if(aSuspeito.length>0 && contNaoSusp==0){
            top.frameApp.stopAnimation(); alert("Nenhum atendimento selecionado pode ser marcado como suspeito!");return false;
        }
        var strSusp='';
        for(i=0;i<aSuspeito.length;i++){
            if(i==aSuspeito.length-1) strSusp=strSusp+aSuspeito[i];
            else strSusp=strSusp+aSuspeito[i]+",";
        }
        if(aSuspeito.length>0)
            if(!(confirm("Os seguintes pocessos não podem ser marcados como suspeitos: " + strSusp + " . \nDeseja continuar?"))) { top.frameApp.stopAnimation();return false;}
        f = document.getElementById("formListaAtendimentos");
        stopRefresh();
        dvSuspeito.style.display='';f.method="POST";f.action="suspeito.do";f.target="ifrmSuspeito";f.submit();
    }

    function validaDataOnBlur(data){
        if(data.value == '')
            return false;
        if(!validate_date(data.value)){
            data.value = '';
            data.focus();
            alert("Data inválida");
        }
    }

    function retornarAnterior()  {
        extra_vars="";
        filtro = top.frameCTI.filtro;
        if (filtro.telaOrigem==1) {
            if (filtro.psqAv!=null) {
                for (i=0; i<filtro.psqAv.length; i++) {
                    extra_vars+='&psqIdCampo='+filtro.psqAv[i].idCampo;
                    extra_vars+='&psqNmCampo='+filtro.psqAv[i].nmCampo;
                    extra_vars+='&psqTpComparacao='+filtro.psqAv[i].tpComparacao;
                    extra_vars+='&psqDsComparacao='+filtro.psqAv[i].dsComparacao;
                    extra_vars+='&psqValor='+filtro.psqAv[i].valor;
                    extra_vars+='&psqIdFormularioCampoValor='+filtro.psqAv[i].idFormularioCampoValor;
                }
            }
        }
        if (filtro.telaOrigem==3) {
            extra_vars +=
            "&login="+filtro.massa.login+
            "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
            "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
            "&textoContato="+filtro.massa.textoContato+
            "&idContato="+filtro.massa.idContato;
        }
        dvSuspeito.style.display='none';
        ativar_combos();
        window.location.href='/FrontOfficeWeb/workflow/AtendimentoInBox/begin.do?voltar=1'+extra_vars;
    }

    var voltar = 0;

    <logic:equal parameter="voltar" value="1">
    voltar=1;
    </logic:equal>
    -->
</script>

</netui-template:section>
<netui-template:section name="bodySection">
<jsp:include page="/resources/menus/MenuPrincipal.jsp" />
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
<div id="ttip" style="display:none;position:absolute;max-width:200px;"></div>
<script type="text/javascript">
    var moveToolTip = true;
    var flLimpar = 0;

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
<vivo:sessao id="qdMain" height="470" width="790" label="Vivo Net >> Workflow" operacoes="Processos In-Box" scroll="no">
<table width="100%" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td>
        <vivo:abaGrupo id="btAbaPesquisas" width="250px" height="10px" styleClass="abatexto">
            <%--<acesso:controlHiddenItem nomeIdentificador="wor_riwb_abaestadoatendente">--%>
            <vivo:abaItem id="btCTIAtendente" onclick="exibicaoAbaPesquisas(0); pesquisaAba(0);" value="Estado Atendente" select="S"/>
            <%--</acesso:controlHiddenItem>--%>
            <%--<acesso:controlHiddenItem nomeIdentificador="wor_riwb_abapesquisa">--%>
            <vivo:abaItem id="btPesquisa" onclick="exibicaoAbaPesquisas(1); carregaFiltros(); pesquisaAba(2,1);" value="Pesquisa"/>
            <%--</acesso:controlHiddenItem>--%>
        </vivo:abaGrupo>
        </td>
        <td width="20"><img id="btMaxMin" name="btMaxMin" title="Click para minimizar/maximizar os parâmetros de pesquisa" src="../../resources/images/bt_lupa_aba.gif" style="cursor:pointer" onclick="resizeMaxMin();"></td>
    </tr>
</table>
<table id="tbConfiguracoes" width="780" cellspacing="0" cellpadding="0" border="0">
    <tr id="trEstadoAtendente">
        <td align="right" width="400px">
            <iframe id="ifrUser" name="ifrUser" src="RWFUser.do" scrolling="no" frameborder="0" width="0" height="0"></iframe>
            <iframe id="iframeUsuario" name="iframeUsuario" src="" style="visibility:hidden;" scrolling="no" frameborder="0" width="0" height="0"></iframe>
            <div id="dUser" style="display.none"></div>
        </td>
    </tr>
    <tr id="trPesquisa" style="display:none;">
        <td>
        <form action="RWFAtendimento.do" name="rWFInBoxPesquisaForm" id="rWFInBoxPesquisaForm" target="" method="post">
        <html:hidden property="idContato" name="form"></html:hidden><input type="hidden" name="somaDias">
        <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
        <tr id="trGrupo">
            <td>Grupo</td>
            <td colspan="3">
                <%--<iframe id="ifrGrupos" src="" height="0" width="0" frameborder="0"></iframe>
                <div id="dGrupos" style=""><a href="RWFGrupos.do"></a></div>--%>
                <div id="dGrupos" style="">
                    <html:select name="form" property="idGrupo" style="width=250px" onchange="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 125, 35);" onfocus="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 125, 35);" onmouseover="focaTipCampos(this.options[this.selectedIndex].text,this, 110, 125, 35);" onblur="HideTip();" onmouseout="HideTip();">
                        <html:option value=" "></html:option>
                        <html:options collection="gruposVO" property="idGrupo" labelProperty="dsGrupo"/>
                    </html:select>
                </div>
            </td>
        </tr>
        <tr id="trEstado">
            <%--wl:cache name="estados_subestados_INBOX" timeout="30s"--%>
            <%--jsp:include page="loadEstados.do" flush="true"/--%>
            <bean:define id="estadosVO" name="form" property="estadosVO.WFEstadoVOArray"/>
            <td>Estado</td>
            <td>
                <html:select name="form" property="idEstado" title="idEstado" style="width=250px" onchange="loadSubEstados(this.value);">
                    <html:option value="-1">&nbsp;</html:option>
                    <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado"/>
                </html:select>
            </td>
            <td>Sub-Estado</td>
            <td id="tdSubEstados">
                <select name="idSubEstado" style="width=250px" title="idSubEstado">
                    <option value="-1">&nbsp;</option>
                </select>
            </td>
            <script>
                <!--
                var arrCombos=new Array();
                <logic:iterate id="estadosVO" name="form" property="estadosVO.WFEstadoVOArray" indexId="idxEstados">
                    var combo = document.createElement("<SELECT NAME='idSubEstado'>");
                    combo.idEstado='<bean:write name="estadosVO" property="idEstado"/>';
                    combo.inFechadoCancelado='<bean:write name="estadosVO" property="inFiltro"/>';
                    <logic:iterate id="subEstadosVO" name="estadosVO" property="WFSubEstadoVOArray" indexId="idxSubEstados">
                        var opt = document.createElement("OPTION");
                        opt.value='<bean:write name="subEstadosVO" property="idSubEstado"/>';
                        opt.text='<bean:write name="subEstadosVO" property="dsSubEstado"/>';
                        combo.add(opt);
                    </logic:iterate>
                    arrCombos[arrCombos.length]=combo;
                </logic:iterate>

                function loadSubEstados(id) {
                    if(id == -1){
                        tdSubEstados.innerHTML="<select name='idSubEstado' style='width:250px'></select>";
                        inFechadoCancelado=-1;
                        return;
                    }
                    for(var i=0;i<arrCombos.length;i++){
                        if (arrCombos[i].idEstado == id){
                            tdSubEstados.innerHTML=arrCombos[i].outerHTML;
                            tdSubEstados.children[0].name='idSubEstado';
                            tdSubEstados.children[0].style.width='250px';
                            inFechadoCancelado=arrCombos[i].inFechadoCancelado;break;
                        }
                    }
                }
                -->
            </script>
            <%--/wl:cache--%>
        </tr>
        <tr>
            <td>Protocolo</td>
            <td width="43%">
                <html:text property="nrProtocolo" name="form" maxlength="30" style="width:100px" onkeyup ="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');"/>
            </td>
            <td>
                <netui:radioButtonGroup dataSource="{pageContext.form.radioButton}" defaultValue="" tagId="optGrpSel">
                    <netui:radioButtonOption value="0" styleClass="radio" onClick="habilitaData(this.value);">Abertura de</netui:radioButtonOption>
                </netui:radioButtonGroup>
            </td>
            <td width="30%">
                <html:text property="dtAberturaInicio" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaDataOnBlur(this);"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAberturaInicio', '%d/%m/%Y');">&nbsp;Até&nbsp;
                <html:text property="dtAberturaFim" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAberturaFim', '%d/%m/%Y');">
            </td>
        </tr>
        <tr>
            <td>Linha</td>
            <td><html:text property="nrLinha" name="form" maxlength="11" style="width:100px" onblur="formatPhoneNumberObj(this)"/></td>
            <td id="tdFechamentoLabel">
                <netui:radioButtonGroup dataSource="{pageContext.form.radioButton}" defaultValue="" tagId="optGrpSel">
                    <netui:radioButtonOption styleClass="radio" value="1" onClick="habilitaData(this.value);">Fechamento de</netui:radioButtonOption>
                </netui:radioButtonGroup>
            </td>
            <td id="tdFechamento"><html:text property="dtFechamentoInicio" name="form" size="10" maxlength="10" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');" onblur="validaDataOnBlur(this);"/><img id="imgCalendDtFecIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;display:none;" title="Calendário" onclick="return showCalendar('dtFechamentoInicio', '%d/%m/%Y');" >&nbsp;Até&nbsp;<html:text property="dtFechamentoFim" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtFecFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;display:none;" title="Calendário" onclick="return showCalendar('dtFechamentoFim', '%d/%m/%Y');"></td>
        </tr>
        <tr>
            <td width="12%">Contato</td>
            <td colspan="3">
                <table width="100%" cellspacing="0" cellpadding="0" border="0">
                <tr>
                    <td width="310"><html:text property="textoContato" name="form" maxlength="255" style="width:300px" readonly="true"/></td>
                    <td width="30">
                    	<img class="button"
                    		 style="border:0"
                    		 target="ifrmArvore"
                    		 src="../../resources/images/lupa_bg_transp.gif"
                    		 onClick="arvoreContato(); return false;" />
                    </td>
                    <td width="110"><img class="button" id="btPesquisar" src="../../resources/images/bt_pesquisar_nrml.gif" value="Pesquisar" style="border:none" onClick="salvaFiltros();submitPesquisar(currentTab); return false;"/></td>
                    <td>
                    	<img class="button"
                    	     src="../../resources/images/bt_limpar_nrml.gif"
                    	     style="border:none"
                    	     onClick="limparPesquisa(); return false;" />
                    </td>
                </tr>
                </table>
            </td>
        </tr>
        </form>
    </table>
    </td>
</tr>
</table>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
<table border='0' cellpadding='0' cellspacing='0' width='560' height='10px' background='/FrontOfficeWeb/resources/images/aba_bkg_off.gif' class='abatexto'>
    <tr id='btAbaInBox' valign='top'>
        <td width='9'><img id='AbaLeft_btTratamento' src='/FrontOfficeWeb/resources/images/aba_left.gif' width='9' height='16'></td>
        <td id='btTratamento' background='/FrontOfficeWeb/resources/images/aba_bkg.gif' class='abaSelected' height='16'  onclick="exibicaoAbaPesquisas(0);pesquisaAba(0);">Processos Em Tratamento</td>
        <td width='9'><img id='AbaRight_btTratamento' src='/FrontOfficeWeb/resources/images/aba_right.gif' width='9' height='16'></td>

        <td width='9'><img id='AbaLeft_btPausa' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
        <td id='btPausa' style='cursor:pointer;' class='abaUnselected' height='16' onclick="exibicaoAbaPesquisas(0);pesquisaAba(1);">Processos Em Pausa</td>
        <td width='9'><img id='AbaRight_btPausa' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>

        <td width='9'><img id='AbaLeft_btPesquisaResult' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
        <td id='btPesquisaResult' style='cursor:pointer;' class='abaUnselected' height='16' onclick="exibicaoAbaPesquisas(1);carregaFiltros();pesquisaAba(2);">Resultado Pesquisa</td>
        <td width='9'><img id='AbaRight_btPesquisaResult' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>

        <td width='9'><img id='AbaLeft_btMensagensResult' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
        <td id='btMensagensResult' style='cursor:pointer;' class='abaUnselected' height='16' onclick="exibicaoAbaPesquisas(1);carregaFiltros();pesquisaAba(3);">Mensagens</td>
        <td width='9'><img id='AbaRight_btMensagensResult' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>

        <!--
        <td width='9'><img id='AbaLeft_btMensagensResultRC' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
        <td id='btMensagensResultRC' style='cursor:pointer;' class='abaUnselected' height='16' onclick="exibicaoAbaPesquisas(1);carregaFiltros();pesquisaAba(4);">Mensagens C. Prévio</td>
        <td width='9'><img id='AbaRight_btMensagensResultRC' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>
        -->
    </tr>
</table>
<iframe id="ifrETrat" name="ifrETrat" src="RWFAtendimento.do" scrolling="no" frameborder="0" width="0" height="0"></iframe>
<iframe id="ifrEPau" name="ifrEPau" src="" scrolling="no" frameborder="0" width="0" height="0"></iframe>
<iframe id="ifrPesquisa" name="ifrPesquisa" src="" scrolling="no" frameborder="0" width="0" height="0"></iframe>
<iframe id="ifrMensagens" name="ifrMensagens" src="" scrolling="no" frameborder="0" width="0" height="0"></iframe>
<iframe id="ifrMensagensRC" name="ifrMensagensRC" src="" scrolling="no" frameborder="0" width="0" height="0"></iframe>
<table width="780" cellspacing="0" cellpadding="0" border="0">
    <form id="formListaAtendimentos">
        <tr id="trAtendimentos">
            <td align="right" width="400px">
                <div id="dETrat" style="display:none"></div>
                <div id="dEPau" style="display:none"></div>
                <div id="dPesquisa" style="display:none"></div>
                <div id="dMensagens" style="display:none"></div>
                <div id="dMensagensRC" style="display:none"></div>
            </td>
        </tr>
    </form>
</table>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
<vivo:quadro id="qdOperacoes" height="35" width="780" label="Ação" scroll="N">
<table border="0" width="100%">
    <tr>
        <td width="50%" align="left"><img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.top.location='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/></td>
        <td width="50%" align="right">
            <acesso:controlHiddenItem nomeIdentificador="wor_riwb_suspeito">
                <img action="suspeito"
                	 id="btSuspeito"
                	 target="ifrmSuspeito"
                	 src="../../resources/images/bt_suspeito_over.gif"
                	 class="button"
                	 onClick="submitSuspeito(); return false;" />
            </acesso:controlHiddenItem>
        </td>
    </tr>
</table>
</vivo:quadro>
<script>
    // marcar a tela de origem
    top.frameCTI.filtro.telaOrigem=2;
    //if( top.frameApp.dvAnimarAguarde != null )
    //    top.frameApp.stopAnimation();
    if(top.frameApp.dvAnimarAguarde!=null) {
        top.frameApp.startAnimation();
    }
</script>
<vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();"/>
<vivo:quadroFlutuante id="dvSuspeito" idIframe="ifrmSuspeito" width="780" height="250" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Suspeito" onclick="ifrmSuspeito.fechar();"/>
<vivo:quadroFlutuante id="dvAlerta" idIframe="ifrmAlerta" width="443" height="220" spacesTop="190" spacesLeft="175" display="none" url="<%=request.getContextPath()%>" label="Alertas"/>
<vivo:quadroFlutuante id="dvEncaminhar" idIframe="ifrmEncaminhar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Encaminhar" onclick="ifrmEncaminhar.fechar();"/>
</vivo:sessao>
</netui-template:section>
</netui-template:template>